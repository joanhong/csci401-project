package capstone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Proposal
{
	@Id
	@GeneratedValue
	private Long id;
	String projectName;
	int projectSize;
	String technologiesExpected;
	String backgroundRequested;
	String projectDescription;
	
	// Stakeholder information
	int stakeholderId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getProjectSize() {
		return projectSize;
	}
	public void setProjectSize(int projectSize) {
		this.projectSize = projectSize;
	}
	public String getTechnologiesExpected() {
		return technologiesExpected;
	}
	public void setTechnologiesExpected(String technologiesExpected) {
		this.technologiesExpected = technologiesExpected;
	}
	public String getBackgroundRequested() {
		return backgroundRequested;
	}
	public void setBackgroundRequested(String backgroundRequested) {
		this.backgroundRequested = backgroundRequested;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	Proposal()
	{
		
	}
	public Proposal(String projectName, int projectSize, String technologiesExpected, String backgroundRequested,
			String projectDescription) {
		super();
		this.projectName = projectName;
		this.projectSize = projectSize;
		this.technologiesExpected = technologiesExpected;
		this.backgroundRequested = backgroundRequested;
		this.projectDescription = projectDescription;
	}
	
}