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
	
	private void populateProjectsTable() {
		for(Project p : projects) {
			driver.addProjectEntry(p);
		}
	}
	
	private void populateRankingsTable() {
		for(Student s: students) {
			for (Map.Entry<String, Integer> entry : s.rankings.entrySet()) {
				Project p  = GetProjectWithName(entry.getKey());
				int projectId = p.getProjectId()+1;
				int studentId = s.getStudentId() + 1;
				
				driver.addProjectRankingEntry(studentId, projectId, entry.getValue());
			}
		}
	}
	
	private void populateUsersTable() {
		for(Student s: students) {
			driver.addUserEntry(3, s.getFirstName(), s.getLastName(), s.getPhone(), s.getEmail(), s.getPassword());
		}
		driver.addUserEntry(1, "admin", "holderson", "1234567890","admin@usc.edu", "admin");
	}
	
	// Imports data from local text files, populates the database tables for Projects, Users, and Project Rankings, and terminates the program.
	public void importDataLocallyAndPopulateDatabase(boolean populateDB) {
				
		// import projects from text file
        String line = null;
        try {
            BufferedReader projectsBR = new BufferedReader(new FileReader(folder_name + "/projects.txt"));

            while((line = projectsBR.readLine()) != null) {                
                String[] elements = line.split(" ");
                
                Project newProject = new Project(getStudentSatScore(1));
                newProject.setProjectName(elements[0]);
                newProject.setProjectId(projects.size());
                newProject.setMinSize(Integer.parseInt(elements[1]));
                newProject.setMaxSize(Integer.parseInt(elements[2]));
                projects.addElement(newProject);
                
                writer.println(newProject);
            }
            
            projectsBR.close();         
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        writer.println("");
        
        // import users and rankings from text file
        try {
            BufferedReader studentsBR = new BufferedReader(new FileReader(folder_name + "/rankings.txt"));

            while((line = studentsBR.readLine()) != null) {                
                String[] elements = line.split(" ");
                
                Student newStudent = new Student();
                newStudent.setFirstName(elements[0]);
                newStudent.setStudentId(students.size());
                newStudent.setUserId(students.size());
                
                for (int rank = 1; rank <= NUM_RANKED; rank++) { // for the student's Top 3 projects...
	            		int projectId = Integer.parseInt(elements[rank]);
	            		Project rankedProject = projects.elementAt(projectId - 1); // !!! SUBTRACT 1, as the ranking's indices skip 0 for readability
                		
                		// add rankedProject to the Student data structure:
                    String projectName = rankedProject.getProjectName();
                    newStudent.rankings.put(projectName, rank);
                    newStudent.orderedRankings.addElement(projectName);
                    
                    // popularity metrics:
                    Integer p = getStudentSatScore(rank);
                    rankedProject.incSum_p(p);
                    rankedProject.incN();
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
        
        // populate database if needed
        if (populateDB) {
	        populateProjectsTable();
	        populateUsersTable();
	        populateRankingsTable();
	        System.out.println("DATABASE POPULATION COMPLETED. ENDING PROGRAM.");
	        System.exit(0);
        }
	}
	
	// populates vectors from SQL DB
	public void importDataFromDatabase() {

        // projects
		this.projects = driver.getProjectsTable();
		for(Project p : projects) {
			writer.println(p);
		}
		writer.println("");
        
        // rankings
		int num_students = (driver.getRankingsTableCount() / NUM_RANKED); // TODO: figure out more intuitive way to configure this
		this.students = driver.getUsersWithRankings(projects, num_students);
		for(Student s : students) {
			writer.println(s);
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
	            rankedProject.incSum_p(p);
	            rankedProject.incN();
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

		// !!! DON'T PASS 'TRUE' UNLESS YOUR DATABASE IS EMPTY !!!
//		importDataLocallyAndPopulateDatabase(false);

		importDataFromDatabase();
		
		// calculate each project's popularity scores
		writer.println("Project Popularity Scores:");
		for (Project p : projects) {
			writer.println(p.getProjectName() + " " + p.calculatePopularity());
		}
		writer.println("");
		
		// sort projects by popularity in descending order
		Collections.sort(projects, new Project.popularityComparator());
		
		AssignInitial();
		PrintProjectAssignments();
		EliminateProjects();
		Bump();
		PrintProjectAssignments();

		// calculate this iteration's overall satisfaction score:
		double totalProjSatScores = 0;
		for (Project p : projects) {
			totalProjSatScores += p.returnProjSatScore();
		}
		algoSatScore = totalProjSatScores / projects.size();		
		writer.println(algoSatScore);
		writer.close();
		
		PlaceUnassignedStudents();
	}

	void PrintProjectAssignments() {
		for (Project p : projects) {
			writer.print(p.getProjectName() + " ");
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
				if (p.members.size() < p.getMaxSize()) {
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
			if (p.members.size() < p.getMinSize() && (GetTotalMaxSpots()-p.getMaxSize()) >= students.size()) {
				writer.println("Eliminated " + p.getProjectName());
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
			if (p!=null && p.members.size() < p.getMaxSize()) { //found a spot for them
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
			if (projects.elementAt(j).getProjectName().equals(projname))
				return projects.elementAt(j);
		}
		return null;
	}
	
	int GetTotalMaxSpots() {
		int maxspots = 0;
		for (Project p: projects)
			maxspots += p.getMaxSize();
		return maxspots;
	}
	
	boolean CanStop() { // assignment is satisfactory
		int numstudents = 0;
		for (Project p: projects) {
			if (!p.members.isEmpty() && 
				(p.members.size() < p.getMinSize() || p.members.size() > p.getMaxSize()))
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
			unassignedProj.setProjectName("Unassigned");
			unassignedProj.members = new Vector<Student>();
			for (Student s: unassignedStudents) {
				unassignedProj.members.add(s);
			}
			projects.add(unassignedProj);
		}		
	}
}