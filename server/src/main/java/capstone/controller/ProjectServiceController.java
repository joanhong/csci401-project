package capstone.controller;



import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.LoginData;
import capstone.model.PeerReviewData;
import capstone.model.Project;
import capstone.model.ProjectData;
import capstone.model.User;
import capstone.model.UserData;
import capstone.model.UserEmailsData;
import capstone.model.WeeklyReportData;
import capstone.repository.ProjectsRepository;
import capstone.session.UserSessionManager;
import capstone.sql.SQLDriver;
import mail.mailDriver;

@RestController
public class ProjectServiceController 
{
	private ProjectsRepository repository;
	private SQLDriver driver;
	public UserSessionManager usm;
	
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
		
		usm = new UserSessionManager();
		
		
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
		String timeStamp = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(new Date());
	   mailDriver maildriver = new mailDriver("csci401server", "drowssap$$$");
		
		String reportConfirmation = "A new project proposal was submitted for " + projectdata.getProjectName() +".\n\n"
															  + "TIME: " + timeStamp + "\n"
															  + "PROJECT NAME: " + projectdata.getProjectName() + "\n"
															  + "PROJECT DESCRIPTION: " + projectdata.getProjectDescription() + "\n"
															  + "TEAM MEMBER NAME: " + projectdata.getProjectSize() + "\n"
															  + "TECHNOLOGIES EXPECTED: "+ projectdata.getTechnologiesExpected() + "\n"
															  + "BACKGROUND REQUESTED: " + projectdata.getBackgroundRequested() + "\n\n"
															  + "For more information, visit the CSCI401 website or reply to this email.";
																
		maildriver.sendEmail("New Project Proposal Submitted for " + projectdata.getProjectName(), reportConfirmation, "csci401server@gmail.com");

	   
	   
	   
		return projectdata; //new ResponseEntity<Boolean>(uiRequestProcessor.saveData(a),HttpStatus.OK);
	}
	//XXXXXXXXXXXX
	@RequestMapping(value = "/peerReviewForm",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody PeerReviewData peerReviewSubmissionAttempt(@RequestBody PeerReviewData peerreviewdata)
	{
		System.out.println("Received HTTP POST");
		String timeStamp = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(new Date());
		String timeCode = new SimpleDateFormat("MMddHHmmss").format(new Date());
//		timeCode.replaceAll(".", "");
		
		peerreviewdata.setId(Integer.parseInt(timeCode));
		System.out.println(peerreviewdata.getId());
		System.out.println(peerreviewdata.getUscidnumber());
		System.out.println(peerreviewdata.getUscusername());
		
		//use sql to send this data to weeklyreportstable
		driver.addPeerReviewEntry(peerreviewdata);
		mailDriver maildriver = new mailDriver("csci401server", "drowssap$$$");
		
		String reportConfirmation = "A peer review was submitted for " + peerreviewdata.getTeammateaddress() +".\n\n"
															  + "TIME: " + timeStamp + "\n"
															  + "USC USERNAME: " + peerreviewdata.getUscusername() + "\n"
															  + "USC ID: " + peerreviewdata.getUscidnumber() + "\n"
															  + "TEAM MEMBER NAME: " + peerreviewdata.getTeammateaddress() + "\n"
															  + "POSITIVE FEEDBACK: "+ peerreviewdata.getPositivefeedback() + "\n"
															  + "NEED IMPROVEMENT: " + peerreviewdata.getNegativefeedback() + "\n\n"
															  + "For more information, visit the CSCI401 website or reply to this email.";
																
		maildriver.sendEmail("Peer Review Submitted for " + peerreviewdata.getTeammateaddress(), reportConfirmation, "csci401server@gmail.com");
		maildriver.sendEmail("Peer Review Confirmation", reportConfirmation, peerreviewdata.getUscusername()+"@usc.edu");

		
		return peerreviewdata;
	}
	//XXXXXXXXXX
	
	//XXXXXXXXXXXX
		@RequestMapping(value = "/userInfoUpdate",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
		@CrossOrigin(origins = "http://localhost:3000")
		public @ResponseBody UserData userInfoUpdateAttempt(@RequestBody UserData userdata)
		{
			System.out.println("Received HTTP POST");
			String timeStamp = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(new Date());
			String timeCode = new SimpleDateFormat("MMddHHmmss").format(new Date());
//			timeCode.replaceAll(".", "");
			
			userdata.setId(Integer.parseInt(timeCode));
			System.out.println(userdata.getId());
			System.out.println(userdata.getName());
			System.out.println(userdata.getYear());
			System.out.println(userdata.getEmail());
			System.out.println(userdata.getUserType());
			
			//use sql to send this data to weeklyreportstable
			driver.addUserInfoUpdate(userdata);
//			mailDriver maildriver = new mailDriver("csci401server", "drowssap$$$");
			return userdata;
		}
		//XXXXXXXXXX
	
	
	
	
	@RequestMapping(value = "/weeklyReportForm",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody WeeklyReportData weeklyReportSubmissionAttempt(@RequestBody WeeklyReportData weeklyreportdata)
	{
		System.out.println("Received HTTP POST");
		String timeStamp = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss").format(new Date());
		String timeCode = new SimpleDateFormat("MMddHHmmss").format(new Date());
//		timeCode.replaceAll(".", "");
		
		weeklyreportdata.setId(Integer.parseInt(timeCode));
		System.out.println(weeklyreportdata.getId());
		System.out.println(weeklyreportdata.getName());
		System.out.println(weeklyreportdata.getUscusername());
		
		//use sql to send this data to weeklyreportstable
		driver.addWeeklyReportEntry(weeklyreportdata);
		mailDriver maildriver = new mailDriver("csci401server", "drowssap$$$");
		
		String reportConfirmation = weeklyreportdata.getName() + " submitted a weekly report.\n\n"
															  + "TIME: " + timeStamp + "\n"
															  + "USC USERNAME: " + weeklyreportdata.getUscusername() + "\n"
															  + "PROJECT NAME: " + weeklyreportdata.getProject() + "\n\n"
															  + "For more information, visit the CSCI401 website or reply to this email.";
																
		maildriver.sendEmail("Weekly Report Submitted by " + weeklyreportdata.getName(), reportConfirmation, "csci401server@gmail.com");
		maildriver.sendEmail("Weekly Report Confirmation", reportConfirmation, weeklyreportdata.getUscusername()+"@usc.edu");

		
		return weeklyreportdata;
	}

	
	
	@RequestMapping(value = "/StudentRegistrationAttempt",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody UserEmailsData weeklyReportSubmissionAttempt(@RequestBody UserEmailsData emailsdata)
	{
		System.out.println("Received HTTP POST");
//		System.out.println(emailsdata.getEmails());
		
		String[] emailsArray = emailsdata.getEmails().split("\n");
		
		mailDriver maildriver = new mailDriver("csci401server", "drowssap$$$");
		
		for(String e : emailsArray)
		{
			maildriver.sendEmail("401 Platform Invite", "Congratulations! \nPlease sign up using the following link. \n \nlocalhost:3000/register/", e);
			System.out.println("Sent invite to: " + e);
		}
		
//		use sql to send this data to users table
//		driver.adduser(user email); //preferably do this when the user goes to the link in the email
		
		
		return emailsdata;
	}
	
	//////
	
	@RequestMapping(value = "/logoutAttempt",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String logoutAttempt(@RequestBody String email, HttpServletRequest request)
	{
		String addr = request.getHeader(HttpHeaders.ORIGIN);
		System.out.println("Received HTTP POST");
		System.out.println(email);
		System.out.println(addr);
		if(usm.logoutUser(addr))
		{
			return "LoggedOut";
		}
		else
		{
			return "Failed";
		}
	}
	
	//////
	@RequestMapping(value = "/loginAttempt",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String loginAttempt(@RequestBody LoginData logindata, HttpServletRequest request)
	{
		
		System.out.println("Received HTTP POST");
		System.out.println(logindata);
		System.out.println(logindata.getEmail());
		String addr = request.getHeader(HttpHeaders.ORIGIN);
		System.out.println(addr);
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
						for(User ux : usm.getActiveUsers())
						{
							System.out.println("IP WAS = "+ ux.getIpaddress());
							if(ux.getIpaddress().equals(addr))
							{
								System.out.println("A login already exists from this origin.");
								return "MultipleLogins";
							}
						}
						System.out.println("LOGIN SUCCESSFUL");
						//NOTE: hardcoded localhost and 3000 for now, it should actually 
						//get that from the POST request.
						usm.loginUser(logindata.getEmail(), addr);
						User u1 = usm.getUser(addr);
						System.out.println("RETURNING USERTYPE = " + u1.getUserType());
						return u1.getUserType();
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

	

