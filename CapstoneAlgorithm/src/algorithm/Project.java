package algorithm;

import java.util.Vector;

public class Project {
	
	// basic info:
	public int projectId;
	public String name;
	public int minSize;
	public int maxSize;
	public Vector<Student> members;
	
	// popularity metrics:
	public double sum_p; // sum of all students' satisfaction scores
	public double p_max;
	public double n; // number of students interested in this project
	public double c; // cutoff
	public double popularity;

	public Project() {
		members = new Vector<Student>();
		sum_p = 0;
		p_max = 5; // ???
		n = 0;
		c = 10; // pre-determined
		popularity = 0;
	}
	
	public double returnPopularityScore() {
		System.out.println("\n" + this.sum_p + " " + this.p_max + " " + this.n);
		
		double first = (2 * this.sum_p) / (this.n * this.p_max);
				
		double _popularity = ( first + (this.n / this.c) ) / 3;
		
		this.popularity = _popularity;
		
		return _popularity;
	}
	
	public void print() {
		System.out.println("Project #" + this.projectId + ": '" 
							+ this.name + "' | " + this.minSize + "-" + this.maxSize);
	}
	
	public void printMembers() {
		for (Student s : this.members) {
			System.out.print(s.name + " ");
		}
		System.out.println("");
	}
	
}