package controller;



	import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Project;
import service.ProjectService;;

@RestController
public class ProjectServiceController 
{

		@Autowired
		ProjectService ps;
		
		@RequestMapping ("projects/all")
		@ResponseBody
		public Hashtable<String, Project> getAll()
		{
			return ps.getAll();
		}
		
		@RequestMapping ("/projects/" + "{id}")
		@ResponseBody
		public Project getUser(@PathVariable("id") String id)
		{
			return ps.getProject(id);
		}
		
}

	

