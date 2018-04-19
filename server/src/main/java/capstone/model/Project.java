package capstone.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import capstone.model.users.Stakeholder;
import capstone.model.users.Student;
import capstone.util.ProjectAssignment;

@Entity
public class Project implements Comparable<Object> {
	
	@Id
	@GeneratedValue
	private Long projectId;
	
	/*@MapsId("leader_id")
	@OneToOne(targetEntity=Student.class)
	private Student teamLeader = null;*/
	
	//@MapsId("member_id")
	@OneToMany(targetEntity=Student.class)
	private Collection<Student> members;
	
	private String status = "Pending Approval";
	private String semester;
	
	// From proposal
	/*@MapsId("stakeholder_id")
	@OneToOne(targetEntity=Stakeholder.class)
	private Stakeholder stakeholder = null;*/

	private String name;
	private String technologies;
	private String background;
	private String description;
	private int minSize;
	private int maxSize;
	
	// popularity metrics:
	@Transient
	private double sum_p; // sum of all students' satisfaction scores
	@Transient
	private double p_max; // maximum satisfaction score for a single student (if NUM_RANKED = 3, this is 4)
	@Transient
	private double n; // number of students interested in this project
	@Transient
	private double c; // cutoff
	@Transient
	private double popularity;
	@Transient
	private double projSatScore;
	
	public Project() {
		
	}
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public Collection<Student> getMembers() {
		return members;
	}

	public void setMembers(Collection<Student> members) {
		this.members = members;
	}

	public double getSum_p() {
		return sum_p;
	}

	public void setSum_p(double sum_p) {
		this.sum_p = sum_p;
	}

	public double getP_max() {
		return p_max;
	}

	public void setP_max(double p_max) {
		this.p_max = p_max;
	}

	public double getN() {
		return n;
	}

	public void setN(double n) {
		this.n = n;
	}

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public double getProjSatScore() {
		return projSatScore;
	}

	public void setProjSatScore(double projSatScore) {
		this.projSatScore = projSatScore;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Project(int _p_max) {
		members = new Vector<Student>();
		sum_p = 0;
		p_max = _p_max;
		n = 0;
		c = 10;
		popularity = 0;
	}
	
	public double returnProjSatScore() {
		double maxScore = p_max * maxSize; // max score possible
		
		double totalScore = 0;
		for (Student student : members) {
			int ranking = student.getRankings().get(this.getName());
			totalScore += ProjectAssignment.getStudentSatScore(ranking);
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
		return ("Project #" + this.projectId + ": '" + this.getName() + "' | " + this.minSize + "-" + this.maxSize + " " + this.p_max);
	}
	
	/* Comparator Stuff */

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof Project))
			throw new ClassCastException();

		Project p = (Project) o;

		return (this.getName()).compareTo(p.getName());
	}

	public String getTechnologies() {
		return technologies;
	}

	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// sorts by popularity in descending order
	public static class popularityComparator implements Comparator<Object> {
		public int compare(Object o1, Object o2) {
			if (!(o1 instanceof Project) || !(o2 instanceof Project))
				throw new ClassCastException();
			
			Project p1 = (Project) o1;
			Project p2 = (Project) o2;
						
	        if (p1.returnPopularity() > p2.returnPopularity()) return -1;
	        else if (p1.returnPopularity() < p2.returnPopularity()) return 1;
	        else return 0;
		}
	}

}