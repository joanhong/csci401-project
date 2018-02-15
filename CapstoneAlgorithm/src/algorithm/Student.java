package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Student {
	
	public int studentId;
	public String name;
	public Map<String, Integer> rankings;
	public Vector<String> orderedRankings;
	
	public Student() {
		rankings = new HashMap<String, Integer>();
		orderedRankings = new Vector<String>();
	}
	
	public void print() {
		System.out.println("Student #" + this.studentId + ": '" 
							+ this.name + "' | " + this.rankings);
	}
}
