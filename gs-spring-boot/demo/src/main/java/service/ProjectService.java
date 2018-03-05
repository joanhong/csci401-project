package service;

import java.util.Hashtable;

import org.springframework.stereotype.Service;


@Service
public class ProjectService {

	Hashtable<String, Project> projects = new Hashtable<String, Project>();

	
	public ProjectService()
	{
		Project p = new Project();
		p.setId("1");
		p.setProjectName("401 Project Platform");
		p.setProjectNumber(27);
		p.setSemester("Spring 2018");
		p.setStakeholderName("Prof. Jeffrey Milley");
		p.setStakeholderNumber(1);
		p.setTeamLeaderName("Joan Hong");
		projects.put("1", p);
		
		p = new Project();
		p.setId("2");
		p.setProjectName("Quizzly");
		p.setProjectNumber(1);
		p.setSemester("Spring 2018");
		p.setStakeholderName("Dr. John Smith");
		p.setStakeholderNumber(2);
		p.setTeamLeaderName("Tommy Trojan");
		p.setTeamLeaderStudentNumber(1);
		projects.put("2", p);
		
	}
	
	public Project getProject(String id)
	{
		if(projects.containsKey(id))
		{
			return projects.get(id);
		}
		else
		{
			return null;
		}
	}
	
	public Hashtable<String, Project> getAll()
	{
		return projects;
	}
	
}
