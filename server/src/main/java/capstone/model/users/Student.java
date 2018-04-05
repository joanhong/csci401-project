package capstone.model.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Student extends User {
	
	@Id
	@GeneratedValue
	Long id;
	public String USC_ID; // only valid if userType = Student
	public String year; //only valid if userType = Student
	public String semester;
	int projectNumber;
	
	@Transient
	private Map<String, Integer> rankings;
	@Transient
	private List<String> orderedRankings;
	
	public Student() {
		setRankings(new HashMap<String, Integer>());
		setOrderedRankings(new Vector<String>());
	}
	
	public int getProjectNumber() 
	{
		return projectNumber;
	}
	public void setProjectNumber(int projectNumber) 
	{
		this.projectNumber = projectNumber;
	}
	
	public String toString() {
		return ("Student #" + this.USC_ID + ": '" + this.getFirstName() + "' | " + this.getRankings());
	}

	public Map<String, Integer> getRankings() {
		return rankings;
	}

	public void setRankings(Map<String, Integer> rankings) {
		this.rankings = rankings;
	}

	public List<String> getOrderedRankings() {
		return orderedRankings;
	}

	public void setOrderedRankings(Vector<String> orderedRankings) {
		this.orderedRankings = orderedRankings;
	}
}
