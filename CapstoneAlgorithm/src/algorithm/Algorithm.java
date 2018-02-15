package algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Algorithm {

	private Vector<Project> projects;
	private Vector<Student> students;
	
	// i = project's rank
	public int getSatisfactionScore(int i) {
		int n = 3; // number of projects that each student can rank
		return ( ( (n-i+1) * (n-i)) / 2 ) + 1;
	}
	
	// populates vectors with projects and the students' rankings of those projects
	public void importData() {

        String line = null;
        
        // projects.txt
        try {
            BufferedReader projectsBR = new BufferedReader(new FileReader("src/projects.txt"));

            while((line = projectsBR.readLine()) != null) {                
                String[] elements = line.split(" ");
                
                Project newProject = new Project(elements[0], 0);
//                newProject.name = elements[0];
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
            BufferedReader studentsBR = new BufferedReader(new FileReader("src/rankings.txt"));

            while((line = studentsBR.readLine()) != null) {                
                String[] elements = line.split(" ");
                
                Student newStudent = new Student();
                newStudent.name = elements[0];
                newStudent.studentId = students.size();
                
                for (int i = 1; i <= 3; i++) { // for the student's Top 3 projects...
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
            System.out.println("\nDone reading in data.");
            
            studentsBR.close();         
        }
        catch(Exception e) {
            e.printStackTrace();
        }

	}
	
	@SuppressWarnings("unchecked")
	public Algorithm() {
		
		// init:
		projects = new Vector<Project>();
		students = new Vector<Student>();
		importData();
		
		// calculate each project's popularity scores
	    List projectsArrayList = new ArrayList();
		for (int i = 0; i < projects.size(); i++) {
			System.out.println(projects.elementAt(i).returnPopularityScore());
			projectsArrayList.add(new Project(projects.elementAt(i).name, projects.elementAt(i).popularity));
		}
        System.out.println("\nDone calculating popularity scores.\n");

	    Collections.sort(projectsArrayList);
	    ListIterator<Project> liter = projectsArrayList.listIterator();
	    while (liter.hasNext())
	      System.out.println(liter.next());
	    
	    Collections.sort(projectsArrayList, new Project.PopularityComparator());
	    
//	    for (int i = 0; i < projectsArrayList.size(); i++) {
//	    		Project p = (Project) projectsArrayList.get(i);
//	    		System.out.println(p.getPopularity());
//	    }
	    
	    liter = projectsArrayList.listIterator();
	    while (liter.hasNext())
	      System.out.println(liter.next());
		
		
		// "sort" projects by popularity (TODO)
		Vector<Project> sortedProjects = new Vector<Project>();
		sortedProjects.addElement(projects.elementAt(1));
		sortedProjects.addElement(projects.elementAt(0));
		sortedProjects.addElement(projects.elementAt(2));
		sortedProjects.addElement(projects.elementAt(3));
		
		// assign first, second, and third choices

		for (int i = 0; i < sortedProjects.size(); i++) {
			Project currProject = sortedProjects.elementAt(i);
			
			for (int j = 0; j < currProject.maxSize; j++) {
				Student s = getStudentWithMaxRanking(currProject);
				currProject.members.addElement(s);
				students.removeElement(s);
			}
			
			currProject.printMembers();
		}
	}

	Student getStudentWithMaxRanking(Project project) {
		Vector<Student> possibleStudents = new Vector<Student>();
		int highestRanking = 1; // since students can only rank 3

		for (Student student : students) {
			if ((student.rankings.containsKey(project.name))) { // if the Student ranked the Project at all
				if (student.rankings.get(project.name) == highestRanking) {
					possibleStudents.add(student);
				}
				else if (student.rankings.get(project.name) < highestRanking) { // better ranking
					highestRanking = student.rankings.get(project.name);
					possibleStudents.add(student);
				}
			}
		}

//		System.out.println(possibleStudents.size());
		int randomNum = ThreadLocalRandom.current().nextInt(0, possibleStudents.size());
//		System.out.println(randomNum);
		return possibleStudents.elementAt(randomNum);
	}

	public static void main(String[] args) {
		new Algorithm();
	}
}
