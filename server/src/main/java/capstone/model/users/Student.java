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
	
	public String uscid; // only valid if userType = Student
	public String semester;
	int projectId;
	
	@Transient
	private Map<String, Integer> rankings;
	@Transient
	private List<String> orderedRankings;
	
	public Student() {
		setRankings(new HashMap<String, Integer>());
		setOrderedRankings(new Vector<String>());
	}
	
	
	
	public String toString() {
		return ("Student #" + this.uscid + ": '" + this.getName() + "' | " + this.getRankings());
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
