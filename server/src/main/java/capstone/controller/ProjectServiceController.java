package capstone.controller;



import java.util.Collection;
import java.util.Vector;
import java.util.stream.Collectors;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.LoginData;
import capstone.model.Project;
import capstone.model.ProjectData;
import capstone.model.User;
import capstone.repository.ProjectsRepository;
import capstone.sql.SQLDriver;

@RestController
public class ProjectServiceController 
{
	private ProjectsRepository repository;
	private SQLDriver driver;
	
	public ProjectServiceController(ProjectsRepository repository)
	{
		this.repository = repository;
		driver = new SQLDriver(5); // TODO: have this be configured as NUM_RANKED, not hard-coded
		driver.connect();
		Vector<Project> projectsVector = driver.getProjectsTable(); 
		for (Project p: projectsVector)
		{
			repository.save(p);
		}
//		encryptUserPasswords();
	}
	
	@GetMapping("/projectsrep")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<Project> projects()
	{
		return repository.findAll().stream()
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/projectData",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody ProjectData saveData(@RequestBody ProjectData projectdata)
	{
		System.out.println("Received HTTP POST");
		System.out.println(projectdata);
		System.out.println(projectdata.getProjectName());
		System.out.println(projectdata.getProjectSize());
		System.out.println(projectdata.getTechnologiesExpected());
		System.out.println(projectdata.getBackgroundRequested());
		System.out.println(projectdata.getProjectDescription());

//	   String projectName = request.getParameter("projectName");
//	   int projectSize = Integer.valueOf(request.getParameter("projectSize"));
//	   String technologiesExpected = request.getParameter("technologiesExpected");
//	   String backgroundRequested = request.getParameter("backgroundRequested");
//	   String projectDescription = request.getParameter("projectDescription");
//	   
	   Project p = new Project();
	   p.name = projectdata.getProjectName();
	   p.setProjectNumber((int)repository.count());
	   p.setProjectName(projectdata.getProjectName());
	   p.setMaxSize(projectdata.getProjectSize());
	   p.setMinSize(3); //HARDCODED MIN_SIZE = 3
	   p.setProjectDescription(projectdata.getProjectDescription());
	   p.setTechnologiesExpected(projectdata.getTechnologiesExpected());
	   p.setBackgroundRequested(projectdata.getBackgroundRequested());
	   p.setStatus("Pending Approval");
//	   
	   //add project to project repository
	   repository.save(p);
		
		
	   
//	   System.out.println("Received HTTP POST, saved to REPO");
	   //add project to SQL table using driver.addProject();
	   driver.addProjectEntry(p.getProjectNumber(), p.getProjectNumber(), p.getProjectName(), p.getStatus(), p.getMaxSize(), p.getMinSize());
	   
		return projectdata; //new ResponseEntity<Boolean>(uiRequestProcessor.saveData(a),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/loginAttempt",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody LoginData loginAttempt(@RequestBody LoginData logindata)
	{
		
		System.out.println("Received HTTP POST");
		System.out.println(logindata);
		System.out.println(logindata.getEmail());
		System.out.println(logindata.getPassword());
		
		//CASE 1
		//DOES USERNAME EXIST?
		if(driver.doesExist(logindata.getEmail()))
		{
			System.out.println("USER EMAIL EXISTS, CHECKING PASSWORD...");
			//IF IT DOES, CHECK IF USERNAME PASSWORD COMBO IS VALID
			String encryptedPassword = driver.getEncryptedPassword(logindata.getEmail());
			if(checkPassword(logindata.getPassword(), encryptedPassword))
			{
				if(driver.confirmLoginAttempt(logindata.getEmail(), encryptedPassword))
					{
						System.out.println("LOGIN SUCCESSFUL");
						return logindata;
					}
			}		
			else
			{
				System.out.println("INVALID PASSWORD");
			}
		}
		else
		{
			System.out.println("INVALID USERNAME : DOES NOT EXIST");
		}
		

		return null; //new ResponseEntity<Boolean>(uiRequestProcessor.saveData(a),HttpStatus.OK);
	}
	
	
//	@RequestMapping(value = "/projectData", method = RequestMethod.POST, headers="content-type=application/json")
//	public String post(@ModelAttribute("Project") Project project, ModelMap modelMap) 
//	{
//		System.out.println("RECEIVED POST REQUEST!");
//		return "";
//	}
	String encryptPassword(String textPassword)
	{
		String encryptedPassword;
		StrongPasswordEncryptor bte = new StrongPasswordEncryptor();
		encryptedPassword = bte.encryptPassword(textPassword);
		return encryptedPassword;
	}
	Boolean checkPassword(String plainPassword, String encryptedPassword)
	{
		StrongPasswordEncryptor bte = new StrongPasswordEncryptor();
		return bte.checkPassword(plainPassword,encryptedPassword);
	}
	void encryptUserPasswords()
	{
		Vector<User> allUsers = driver.getAllUsers();
		for(User u: allUsers)
		{
			System.out.println("INITIAL PASSWORD = " + u.getPassword());
			System.out.println("INITIAL EMAIL = " + u.getEmail());
			String encryptedPass = encryptPassword(u.getPassword());
			System.out.println("ENCRYPTEDPASS= " + encryptedPass);
			driver.updatePassword(u.getEmail(), encryptedPass);
		}
	}
	
		
}

	

