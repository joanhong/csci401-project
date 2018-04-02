package capstone.model.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stakeholder extends User {
	@Id
	@GeneratedValue
	Long id;
	String companyName; //only valid if userType = Stakeholder
	int projectNumber;
	
	public int getProjectNumber() 
	{
		return projectNumber;
	}
	public void setProjectNumber(int projectNumber) 
	{
		this.projectNumber = projectNumber;
	}
}
