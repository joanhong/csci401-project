package capstone.model.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stakeholder extends User {
	
	private String companyName; //only valid if userType = Stakeholder
	private int projectNumber;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getProjectNumber() 
	{
		return projectNumber;
	}
	public void setProjectNumber(int projectNumber) 
	{
		this.projectNumber = projectNumber;
	}
}
