package capstone.service;

import java.util.Hashtable;
import java.util.Vector;

import org.springframework.stereotype.Service;

import capstone.model.Project;
import capstone.sql.SQLDriver;


@Service
public class ProjectService {

	Hashtable<String, Project> projects;
	SQLDriver driver;
	
	public ProjectService()
	{
		
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
