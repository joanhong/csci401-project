package algorithm;

import java.util.Comparator;
import java.util.Vector;

public class Project implements Comparable {
	
	// basic info:
	public int projectId;
	public String name;
	public int minSize;
	public int maxSize;
	public Vector<Student> members;
	
	// popularity metrics:
	public double sum_p; // sum of all students' satisfaction scores
	public double p_max; // max possible satisfaction score
	public double n; // number of students interested in this project
	public double c; // cutoff
	public double popularity;

	public Project(String _name, double pop) {
		this.members = new Vector<Student>();
		this.sum_p = 0;
		this.p_max = 4;
		this.n = 0;
		this.c = 10;
		this.popularity = pop;
		this.name = _name;
	}
	
	public double getPopularity() {
		return this.popularity;
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
	
	public String toString() {
		return ("Project #" + this.projectId + ": '" 
				+ this.name + "' | " + this.minSize + "-" + this.maxSize + "  " + this.getPopularity());
	}
	
	public void printMembers() {
		for (Student s : this.members) {
			System.out.print(s.name + " ");
		}
		System.out.println("");
	}

	public int compareTo(Object o) {
	    if (!(o instanceof Project))
	        throw new ClassCastException();

	    	Project p = (Project) o;

	    return name.compareTo(p.name);
	}
	
	static class PopularityComparator implements Comparator<Object> {
	    public int compare(Object o1, Object o2) {
	      if (!(o1 instanceof Project) || !(o2 instanceof Project))
	        throw new ClassCastException();

	      Project p1 = (Project) o1;
	      Project p2 = (Project) o2;

	      return (int) (p2.popularity - p1.popularity);
	    }
	}
	
}