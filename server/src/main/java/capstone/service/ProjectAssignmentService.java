package capstone.service;

import java.io.*;
import java.util.*;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import capstone.model.Project;
import capstone.model.Student;
import capstone.sql.SQLDriver; 

@Service
public class ProjectAssignmentService {

	PrintWriter writer;
	
	private Vector<Project> projects;
	private Vector<Student> students;
	private Vector<Student> unassignedStudents = new Vector<Student>();
	
	private static int NUM_RANKED;
	private static String folder_name;
	public double algoSatScore = 0; // overall satisfaction of this matching
	public SQLDriver driver;
	
	public static int getStudentSatScore(int i) { // i = project's rank
		return ( ( (NUM_RANKED-i+1) * (NUM_RANKED-i)) / 2 ) + 1;
	}
	
	// populates vectors from text file data
	public void importDataLocally() {
		
		// projects
        String line = null;
        try {
            BufferedReader projectsBR = new BufferedReader(new FileReader(folder_name + "/projects.txt"));

            while((line = projectsBR.readLine()) != null) {                
                String[] elements = line.split(" ");
                
                Project newProject = new Project(getStudentSatScore(1));
                newProject.name = elements[0];
                newProject.projectId = projects.size();
                newProject.minSize = Integer.parseInt(elements[1]);
                newProject.maxSize = Integer.parseInt(elements[2]);
                
                projects.addElement(newProject);
                
                writer.println(newProject);
            }
            
            projectsBR.close();         
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        writer.println("");
        
        // rankings
        try {
            BufferedReader studentsBR = new BufferedReader(new FileReader(folder_name + "/rankings.txt"));

            while((line = studentsBR.readLine()) != null) {                
                String[] elements = line.split(" ");
                
                Student newStudent = new Student();
                newStudent.name = elements[0];
                newStudent.studentId = students.size();
                
                for (int i = 1; i <= NUM_RANKED; i++) { // for the student's Top 3 projects...
            		int projectId = Integer.parseInt(elements[i]);
            		Project rankedProject = projects.elementAt(projectId - 1); // !!! SUBTRACT 1, as the ranking's indices skip 0 for readability
                		
                		// add rankedProject to the Student data structure:
                    String projectName = rankedProject.name;
                    newStudent.rankings.put(projectName, i);
                    newStudent.orderedRankings.addElement(projectName);
                    
                    // popularity metrics:
                    Integer p = getStudentSatScore(i);
                    rankedProject.sum_p += p;
                    rankedProject.n += 1;     
                }

                students.addElement(newStudent);
                writer.println(newStudent);
            }
            
            writer.println("");
            studentsBR.close();         
        }
        catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	// populates vectors from SQL DB
	public void importDataFromDatabase() {

        // projects
		projects = driver.getProjectsTable();
		
		for(Project p : projects) {
			writer.print(p);
		}
		writer.println("");
        
        // rankings
		//HARDCODING 5 (for now)
		int num_students = (driver.getRankingsTableCount()/5); // TODO: figure out more intuitive way to configure this
		students = driver.getUsersWithRankings(projects, num_students);
		
		for(Student s : students) {
			writer.print(s);
		}
		writer.println("");

		// calculate popularity metrics:
		for (Student s : students) {
		    Iterator it = s.rankings.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        String projectName = (String) pair.getKey();
		        int rank = (int) pair.getValue();
		        
		        Project rankedProject = GetProjectWithName(projectName);
	            Integer p = getStudentSatScore(rank);
	            rankedProject.sum_p += p;
	            rankedProject.n += 1;
		    }
		}
        
	}
	public ProjectAssignmentService() {
		
	}
	
	public void run(int iteration, int _NUM_RANKED, String _folder_name) {
		
		NUM_RANKED = _NUM_RANKED;
		folder_name = _folder_name;
		
		// set up output text file for this iteration
		String filename = folder_name + "/iterations/" + Integer.toString(iteration) + ".txt";
		try {
			writer = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// init SQL connection
		driver = new SQLDriver(NUM_RANKED);
		driver.connect();
				
		// import data
		projects = new Vector<Project>();
		students = new Vector<Student>();
//		importDataLocally();
		importDataFromDatabase();

		// commented out once SQL table was populated
//		populateProjectTable();
		
		// commented out once SQL table was populated
//		populateRankingsTable();
		
		// calculate each project's popularity scores
		writer.println("Project Popularity Scores:");
		for (Project p : projects) {
			writer.println(p.name + " " + p.returnPopularity());
		}
		writer.println("");
		
		// sort projects by popularity in descending order
		Collections.sort(projects, new Project.popularityComparator());
		
		AssignInitial();
		PrintProjects();
		EliminateProjects();
		Bump();
		PrintProjects();
		//JSONOutput();
		
		// calculate this iteration's overall sat score:
		double totalProjSatScores = 0;
		for (Project p : projects) {
			totalProjSatScores += p.returnProjSatScore();
		}
		algoSatScore = totalProjSatScores / projects.size();		
		writer.println(algoSatScore);
		writer.close();

		PlaceUnassignedStudents();
	}
	
	private void populateRankingsTable() 
	{
		for(Student s: students)
		{
			System.out.println("Student name" + s.name + " " + s.studentId+1);
			for (Map.Entry<String, Integer> entry : s.rankings.entrySet()) 
			{
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
				Project p  = GetProjectWithName(entry.getKey());
				int projectNumber = p.projectId+1;
				int studentID = s.studentId+1;
				driver.addProjectRankingEntry(studentID, s.name, projectNumber, entry.getValue(), entry.getKey());
			}
		}		
	}

	private void populateProjectTable() 
	{
		System.out.println("Number of project entries in vector = "+ projects.size());
		for(Project p : projects)
		{
			System.out.println("ADDED PROJECT "+ p.name);
			driver.addProjectEntry(p.projectId, p.projectId, p.name, "Pending Approval", p.maxSize, p.minSize);
		}
	}

	void PrintProjects() {
		for (Project p : projects) {
			writer.print(p.name + " ");
			p.printMembers(writer);
		}		
		writer.println("");
	}
	
	/*void JSONOutput() { //outputs JSON of each project
	    ObjectMapper mapper = new ObjectMapper();
	    for (int i=0; i<projects.size(); i++) {
		    try {  
		        // Writing to a file  
		    	mapper.writeValue(new File("src/json/project"+i+".json"), projects.elementAt(i));
		       // String jsonStr = mapper.writeValueAsString(projects.elementAt(i));
	           // System.out.println(jsonStr);
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }
	    }	
	}*/
	
	public String JSONOutputWeb() {
		//String jsonStr = "[";
		String jsonStr = "[";
		ObjectMapper mapper = new ObjectMapper();
		for (int i=0; i<projects.size(); i++) {
		    try {  
		        // Writing to a file  
		    	   if (i != 0) {
		    		   jsonStr += ",";
		    	   }
		    	   jsonStr += mapper.writeValueAsString(projects.elementAt(i));
	           // System.out.println(jsonStr);
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }
	    }
		jsonStr += "]";
		return jsonStr;
	}
	
	void AssignInitial() {
		for (Student s: students)
			unassignedStudents.add(s);
		Collections.shuffle(unassignedStudents);
		
		for (int choice = 0; choice < NUM_RANKED; choice++) {
			Vector<Student> toRemove = new Vector<Student>();
			
			for (Student s: unassignedStudents) {
				String projname = s.orderedRankings.elementAt(choice);
				Project p = GetProjectWithName(projname);
				if (p.members.size() < p.maxSize) {
					p.members.addElement(s);
					toRemove.add(s);
				}
			}
			unassignedStudents.removeAll(toRemove);
		}
	}
	
	void EliminateProjects() {
		for (int i=projects.size()-1; i>0; i--) {
			Project p = projects.elementAt(i);
			if (p.members.size() < p.minSize && (GetTotalMaxSpots()-p.maxSize) >= students.size()) {
				writer.println("Eliminated " + p.name);
				for (Student s: p.members) {
					unassignedStudents.add(s);
				}
				projects.remove(i);
			}
		}
		writer.println("");
	}

	void Bump() {
		Collections.shuffle(unassignedStudents);
		Vector<Student> toRemove = new Vector<Student>();
		for (Student s: unassignedStudents) {
			if (BumpHelper(s, 0))
				toRemove.add(s);
		}
		unassignedStudents.removeAll(toRemove);
	}
	
	boolean BumpHelper(Student s, int level) {
		if (level>3)
			return false;
		for (int i=0; i<s.orderedRankings.size(); i++) {
			Project p = GetProjectWithName(s.orderedRankings.elementAt(i));
			if (p!=null && p.members.size() < p.maxSize) { //found a spot for them
				p.members.add(s);
				return true;
			}
		}
		
		Project p = GetProjectWithName(s.orderedRankings.elementAt(0));
		
		if (p == null) 
		{
			return false;
		}
		
		Random rand = new Random();
		int index = rand.nextInt(p.members.size());
		Student displaced = p.members.elementAt(index);
		if (BumpHelper(displaced, level+1)) {
			p.members.remove(index);
			p.members.add(s);
		}
		
		return true;
	}
	
	Project GetProjectWithName(String projname) {
		for (int j=0; j<projects.size(); j++) {
			if (projects.elementAt(j).name.equals(projname))
				return projects.elementAt(j);
		}
		return null;
	}
	
	int GetTotalMaxSpots() {
		int maxspots = 0;
		for (Project p: projects)
			maxspots+= p.maxSize;
		return maxspots;
	}
	
	boolean CanStop() { // assignment is satisfactory
		int numstudents = 0;
		for (Project p: projects) {
			if (!p.members.isEmpty() && 
				(p.members.size() < p.minSize || p.members.size() > p.maxSize))
				return false;
			numstudents+= p.members.size();
		}
		if (numstudents != students.size())
			return false;
		return true;
	}
	
	void PlaceUnassignedStudents() {
		if (!unassignedStudents.isEmpty()) {
			Project unassignedProj = new Project();
			unassignedProj.setName("Unassigned");
			for (Student s: unassignedStudents) {
				unassignedProj.members = new Vector<Student>();
				unassignedProj.members.add(s);
			}
			projects.add(unassignedProj);
		}		
	}
}