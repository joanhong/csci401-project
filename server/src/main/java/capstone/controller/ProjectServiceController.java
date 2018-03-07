package capstone.controller;



import java.util.Collection;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.Project;
import capstone.model.User;
import capstone.repository.ProjectsRepository;
import capstone.sql.SQLDriver;

@Entity
class ProjectData
{
	@Id
	@GeneratedValue
	private long id;
	String projectName;
	int projectSize;
	String technologiesExpected;
	String backgroundRequested;
	String projectDescription;
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
	ProjectData()
	{
		
	}
	public ProjectData(String projectName, int projectSize, String technologiesExpected, String backgroundRequested,
			String projectDescription) {
		super();
		this.projectName = projectName;
		this.projectSize = projectSize;
		this.technologiesExpected = technologiesExpected;
		this.backgroundRequested = backgroundRequested;
		this.projectDescription = projectDescription;
	}
	
}

@Entity
class LoginData
{
	@Id
	@GeneratedValue
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	String email;
	String password;
	
	public LoginData(String email, String password) 
	{
		super();
		this.email = email;
		this.password = password;
	}
	public LoginData()
	{
		
	}
	
}


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
		System.out.println(projectdata.projectName);
		System.out.println(projectdata.projectSize);
		System.out.println(projectdata.technologiesExpected);
		System.out.println(projectdata.backgroundRequested);
		System.out.println(projectdata.projectDescription);

//	   String projectName = request.getParameter("projectName");
//	   int projectSize = Integer.valueOf(request.getParameter("projectSize"));
//	   String technologiesExpected = request.getParameter("technologiesExpected");
//	   String backgroundRequested = request.getParameter("backgroundRequested");
//	   String projectDescription = request.getParameter("projectDescription");
//	   
	   Project p = new Project();
	   p.name = projectdata.projectName;
	   p.setProjectNumber((int)repository.count());
	   p.setProjectName(projectdata.projectName);
	   p.setMaxSize(projectdata.projectSize);
	   p.setMinSize(3); //HARDCODED MIN_SIZE = 3
	   p.setProjectDescription(projectdata.projectDescription);
	   p.setTechnologiesExpected(projectdata.technologiesExpected);
	   p.setBackgroundRequested(projectdata.backgroundRequested);
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
		System.out.println(logindata.email);
		System.out.println(logindata.password);
		
		//CASE 1
		//DOES USERNAME EXIST?
		if(driver.doesExist(logindata.email))
		{
			System.out.println("USER EMAIL EXISTS, CHECKING PASSWORD...");
			//IF IT DOES, CHECK IF USERNAME PASSWORD COMBO IS VALID
			String encryptedPassword = driver.getEncryptedPassword(logindata.email);
			if(checkPassword(logindata.password, encryptedPassword))
			{
				if(driver.confirmLoginAttempt(logindata.email, encryptedPassword))
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

	

