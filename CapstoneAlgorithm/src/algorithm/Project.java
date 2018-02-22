package algorithm;

import java.io.PrintWriter;
import java.util.Vector;

public class Project {
	
	public int projectId;
	public String name;
	public int minSize;
	public int maxSize;
	public Vector<Student> members;
	
	// popularity metrics:
	public double sum_p; // sum of all students' satisfaction scores
	public double p_max; // maximum satisfaction score for a single student (if NUM_RANKED = 3, this is 4)
	public double n; // number of students interested in this project
	public double c; // cutoff
	public double popularity;
	
	public double projSatScore;

	public Project() {
		members = new Vector<Student>();
		sum_p = 0;
		p_max = 4;
		n = 0;
		c = 10;
		popularity = 0;
	}
	
	public double returnProjSatScore() {
		double maxScore = p_max * maxSize; // max score possible
		
		double totalScore = 0;
		for (Student student : members) {
			int ranking = student.rankings.get(this.name);
			totalScore += Algorithm.getStudentSatScore(ranking);
		}
		
		this.projSatScore = totalScore / maxScore;
		return this.projSatScore;
	}
	
	public double returnPopularity() {
		double first = (2 * this.sum_p) / (this.n * this.p_max);
		double _popularity = ( first + (this.n / this.c) ) / 3;
		
		this.popularity = _popularity;
		return _popularity;
	}
	
	public String toString() {
		return ("Project #" + this.projectId + ": '" + this.name + "' | " + this.minSize + "-" + this.maxSize);
	}
	
	public void printMembers(PrintWriter writer) {
		for (Student s : this.members) {
			writer.print(s.name + " ");
		}
		writer.println("");
	}
	
}