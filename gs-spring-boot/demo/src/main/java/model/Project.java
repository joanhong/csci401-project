package model;

import java.time.LocalDate;
import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Project {
	
	String id;
	int projectNumber;
	String projectName;
	int stakeholderNumber;
	String stakeholderName;
	String teamLeaderName;
	int teamLeaderStudentNumber;
	Vector<String> membersList;
	String status;
	LocalDate dueDate;
	String semester;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getProjectNumber() {
		return projectNumber;
	}
	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getStakeholderNumber() {
		return stakeholderNumber;
	}
	public void setStakeholderNumber(int stakeholderNumber) {
		this.stakeholderNumber = stakeholderNumber;
	}
	public String getStakeholderName() {
		return stakeholderName;
	}
	public void setStakeholderName(String stakeholderName) {
		this.stakeholderName = stakeholderName;
	}
	public String getTeamLeaderName() {
		return teamLeaderName;
	}
	public void setTeamLeaderName(String teamLeaderName) {
		this.teamLeaderName = teamLeaderName;
	}
	public int getTeamLeaderStudentNumber() {
		return teamLeaderStudentNumber;
	}
	public void setTeamLeaderStudentNumber(int teamLeaderStudentNumber) {
		this.teamLeaderStudentNumber = teamLeaderStudentNumber;
	}
	public Vector<String> getMembersList() {
		return membersList;
	}
	public void setMembersList(Vector<String> membersList) {
		this.membersList = membersList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	
	
}
