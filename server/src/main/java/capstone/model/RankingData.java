package capstone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RankingData
{
	
	@Id
	@GeneratedValue
	private long id;
	
	String project1;
	String project2;
	String project3;
	String project4;
	String project5;
	
	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public String getProject1() {
		return project1;
	}

	public void setProject1(String project1) {
		this.project1 = project1;
	}

	public String getProject2() {
		return project2;
	}

	public void setProject2(String project2) {
		this.project2 = project2;
	}

	public String getProject3() {
		return project3;
	}

	public void setProject3(String project3) {
		this.project3 = project3;
	}

	public String getProject4() {
		return project4;
	}

	public void setProject4(String project4) {
		this.project4 = project4;
	}

	public String getProject5() {
		return project5;
	}

	public void setProject5(String project5) {
		this.project5 = project5;
	}

	

	public RankingData()
	{
		
	}
	
}