package capstone.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Student extends User {
	
	// correlates to database table "StudentInfo"
	int studentId;
	int semester;
	int projectId;
	int uscId;
	
	// used for project matching
	public Map<String, Integer> rankings;
	public Vector<String> orderedRankings;
	
	public Student() {
		rankings = new HashMap<String, Integer>();
		orderedRankings = new Vector<String>();
	}
	
	public String toString() {
		return ("Student #" + this.studentId + ": '" + this.firstName + " " + this.lastName + "' | " + this.rankings);
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUscId() {
		return uscId;
	}

	public void setUscId(int uscId) {
		this.uscId = uscId;
	}
}
