package capstone.algorithm;



import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import capstone.sql.SQLDriver;



@RestController
public class ProjectServiceController 
{
	private ProjectsRepository repository;
	private SQLDriver driver;
	
	public ProjectServiceController(ProjectsRepository repository)
	{
		this.repository = repository;
		driver = new SQLDriver();
		driver.connect();
		Vector<Project> projectsVector = driver.getProjectsTable(); 
		for (Project p: projectsVector)
		{
			repository.save(p);
		}
	}
	
	@GetMapping("/projectsrep")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<Project> projects()
	{
		return repository.findAll().stream()
				.collect(Collectors.toList());
	}
//		@Autowired
//		ProjectService ps;
//		
//		@RequestMapping ("projects/all")
//		@ResponseBody
//		public Hashtable<String, Project> getAll()
//		{
//			return ps.getAll();
//		}
//		
//		@RequestMapping ("/projects/" + "{id}")
//		@ResponseBody
//		public Project getUser(@PathVariable("id") String id)
//		{
//			return ps.getProject(id);
//		}
		
}

	

