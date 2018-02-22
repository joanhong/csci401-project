package algorithm;

import java.io.*;
import java.util.*;

public class Algorithm {

	PrintWriter writer;
	
	private Vector<Project> projects;
	private Vector<Student> students;
	private Vector<Student> unassignedStudents = new Vector<Student>();
	
	private static int NUM_RANKED;
	public double algoSatScore = 0; // overall satisfaction of this matching
	
	public static int getStudentSatScore(int i) { // i = project's rank
		return ( ( (NUM_RANKED-i+1) * (NUM_RANKED-i)) / 2 ) + 1;
	}
	
	// populates vectors with projects and the students' rankings of those projects
	public void importData() {

        String line = null;
        
        // projects
        try {
            BufferedReader projectsBR = new BufferedReader(new FileReader("src/algorithm/projects.txt"));

            while((line = projectsBR.readLine()) != null) {                
                String[] elements = line.split(" ");
                
                Project newProject = new Project();
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
            BufferedReader studentsBR = new BufferedReader(new FileReader("src/algorithm/rankings.txt"));

            while((line = studentsBR.readLine()) != null) {                
                String[] elements = line.split(" ");
                
                Student newStudent = new Student();
                newStudent.name = elements[0];
                newStudent.studentId = students.size();
                
                for (int i = 1; i <= NUM_RANKED; i++) { // for the student's Top 3 projects...
            		int projectId = Integer.parseInt(elements[i]);
            		Project rankedProject = projects.elementAt(projectId - 1); // WE SUBTRACT 1, as the indices in rankings.txt skip 0 for readability with the paper example
                		
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
	
	public Algorithm(int iteration, int _NUM_RANKED) {
		
		NUM_RANKED = _NUM_RANKED;
		
		// set up output txt file for this iteration
		String filename = "src/algorithm/iterations/" + Integer.toString(iteration) + ".txt";
		try {
			writer = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// import data:
		projects = new Vector<Project>();
		students = new Vector<Student>();
		importData();
		
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
		
		// calculate this iteration's overall sat score:
		double totalProjSatScores = 0;
		for (Project p : projects) {
			totalProjSatScores += p.returnProjSatScore();
		}
		algoSatScore = totalProjSatScores / projects.size();
		
		writer.println(algoSatScore);
		writer.close();
	}
	
	void PrintProjects() {
		for (Project p : projects) {
			writer.print(p.name + " ");
			p.printMembers(writer);
		}		
		writer.println("");
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
			if (p.members.size() < p.minSize
					&& (GetTotalMaxSpots()-p.maxSize) >= students.size()) {
				writer.println("Eliminated " + p.name);
				for (Student s: p.members) {
					unassignedStudents.add(s);
				}
				projects.remove(projects.size()-1);
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

	public static void main(String[] args) {
		
	}
}
