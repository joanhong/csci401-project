package capstone.model;

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
	
	public String toString() {
		return ("Student #" + this.studentId + ": '" + this.name + "' | " + this.rankings);
	}
}
