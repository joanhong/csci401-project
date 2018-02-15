package algorithm;

import java.io.*;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Algorithm {

	private Vector<Project> projects;
	private Vector<Student> students;
	private Vector<Student> unassignedStudents = new Vector<Student>();
	private static final int NUM_RANKED = 3;
	
	// i = project's rank
	public int getSatisfactionScore(int i) {
		int n = NUM_RANKED; // number of projects that each student can rank
		return ( ( (n-i+1) * (n-i)) / 2 ) + 1;
	}
	
	// populates vectors with projects and the students' rankings of those projects
	public void importData() {

        String line = null;
        
        // projects.txt
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
                
                newProject.print();
            }
            
            projectsBR.close();         
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("");
        
        // rankings.txt
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
                    Integer p = getSatisfactionScore(i);
                    rankedProject.sum_p += p;
                    rankedProject.n += 1;     
                }

                students.addElement(newStudent);
                newStudent.print();
            }
            
            studentsBR.close();         
        }
        catch(Exception e) {
            e.printStackTrace();
        }

	}
	
	public Algorithm() {
		
		// init:
		projects = new Vector<Project>();
		students = new Vector<Student>();
		importData();
		
		// calculate each project's popularity scores
		for (int i = 0; i < projects.size(); i++) {
			System.out.println(projects.elementAt(i).returnPopularityScore());
		}
		
		// "sort" projects by popularity (TODO)
		Vector<Project> sortedProjects = new Vector<Project>();
		sortedProjects.addElement(projects.elementAt(1));
		sortedProjects.addElement(projects.elementAt(0));
		sortedProjects.addElement(projects.elementAt(3));
		sortedProjects.addElement(projects.elementAt(2));
		projects = sortedProjects;
		
		// assign first, second, and third choices
/*
		for (int i = 0; i < sortedProjects.size(); i++) {
			Project currProject = sortedProjects.elementAt(i);
			
			for (int j = 0; j < currProject.maxSize; j++) {
				Student s = getStudentWithMaxRanking(currProject);
				if (s!=null) {
					currProject.members.addElement(s);
					students.removeElement(s);
				}
			}
			
			System.out.print(currProject.name + " ");
			currProject.printMembers();
		} */
		

		AssignInitial();
		PrintProjects();
		EliminateProjects();
		Bump();
		PrintProjects();

		
	}
	
	void PrintProjects() {
		for (Project p: projects) {
			System.out.print(p.name + " ");
			p.printMembers();
		}		
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
				System.out.println("Eliminated " + p.name);
				for (Student s: p.members) {
					unassignedStudents.add(s);
				}
				projects.remove(projects.size()-1);
			}
		}		
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
	
	Student getStudentWithMaxRanking(Project project) {
		Vector<Student> possibleStudents = new Vector<Student>();
	//	int highestRanking = 1; // since students can only rank 3 //why would this make sense??
		int highestRanking = NUM_RANKED;
		
		for (Student student : students) {
			if ((student.rankings.containsKey(project.name))) { // if the Student ranked the Project at all
				if (student.rankings.get(project.name) == highestRanking) {
					possibleStudents.add(student);
				}
				else if (student.rankings.get(project.name) < highestRanking) { // better ranking
					highestRanking = student.rankings.get(project.name);
					possibleStudents.clear();
					possibleStudents.add(student);
				}
			}
		}

	//	System.out.println(project.name + " " + possibleStudents.size());
		if (possibleStudents.size()==0) {
			return null;
		}
		
		int randomNum = ThreadLocalRandom.current().nextInt(0, possibleStudents.size());
		return possibleStudents.elementAt(randomNum);
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
	
	boolean CanStop() { //assignment is satisfactory
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
		new Algorithm();
	}
}
