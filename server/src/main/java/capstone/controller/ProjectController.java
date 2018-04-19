package capstone.controller;



import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.Project;
import capstone.model.users.Student;
import capstone.repository.ProjectsRepository;
import capstone.service.ProjectService;
import capstone.service.UserService;

@RestController
@RequestMapping("/projects")
public class ProjectController 
{
	@Autowired
	private ProjectsRepository repository;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	
	public ProjectController(ProjectsRepository repository)
	{
		this.repository = repository;
//		encryptUserPasswords();
	}
	
	@GetMapping("/")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<Project> getProjects()
	{
		return (List<Project>) repository.findAll();
	}	
	
	
	
  
	@PostMapping("assignment")
	@CrossOrigin(origins = "http://localhost:3000")
	public String projectAssignment()
	{
		return projectService.runAlgorithm((Vector<Project>)getProjects(), (Vector<Student>)userService.getStudents());
	}
}

	

