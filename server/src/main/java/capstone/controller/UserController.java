package capstone.controller;

import java.util.Collection;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.RegisteredStudentEmail;
import capstone.model.users.Admin;
import capstone.model.users.Stakeholder;
import capstone.model.users.Student;
import capstone.model.users.User;
import capstone.repository.RegisteredStudentEmailRepository;
import capstone.service.EmailService;
import capstone.service.UserService;
import capstone.util.Constants;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	@Autowired
	private RegisteredStudentEmailRepository regRepo;
	@Autowired
	private EmailService emailService;
	
	
	public UserController()
	{
	}
	
	@GetMapping("/all")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<User> getUsers()
	{
		return userService.getUsers();
	}
	
	@GetMapping("/stakeholders")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<Stakeholder> getStakeholders() {
		return userService.getStakeholders();
	}
	
	@GetMapping("/students")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<Student> getStudents() {
		return userService.getStudents(); 
	}
	
	public User findUser(String email) {
		return userService.findUserByEmail(email);
	}
	public User findUserByAddr(String addr) {
		return userService.findUserByAddr(addr);
	}
	
	@RequestMapping(value = "/loggedInUser",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody User loggedInUser(@RequestBody String name, HttpServletRequest request)
	{
		System.out.println("Logged in user");
		System.out.println("Received HTTP POST");
		String addr = request.getHeader(HttpHeaders.ORIGIN);
		System.out.println(addr);
		User curr = findUserByAddr(addr);
		String[] user = new String[2];
		user[0] = curr.getName();
		user[1] = curr.getEmail();
		if (curr != null) {
			return curr;
		}
		return null; //new ResponseEntity<Boolean>(uiRequestProcessor.saveData(a),HttpStatus.OK);
	}
	
	/* Registration */
	
	// Admin registration
	@PostMapping("/adminRegistrationAttempt")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String adminRegistrationAttempt(@RequestBody Map<String, String> info) {
		String email = info.get(Constants.EMAIL);
		String name = info.get(Constants.NAME);
		String phone = info.get(Constants.PHONE);
		String encryptedPassword = encryptPassword(info.get(Constants.PASSWORD));
		
		// Check if email is a registered student email and not already registered
		if (regRepo.findByEmail(email) != null && 
				userService.findStudentByEmail(email) == null) {
			Admin admin = new Admin();
			admin.setName(name);
			admin.setEmail(email);
			admin.setPhone(phone);
			admin.setPassword(encryptedPassword);
			userService.saveUser(admin);
			System.out.println("New admin created");
			return Constants.SUCCESS;
		}
		return Constants.EMPTY;
	}
	
	// Student registration
	@PostMapping("/studentRegistrationAttempt")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String studentRegistrationAttempt(@RequestBody Map<String, String> info) {
		String email = info.get(Constants.EMAIL);
		String name = info.get(Constants.NAME);
		String phone = info.get(Constants.PHONE);
		String encryptedPassword = encryptPassword(info.get(Constants.PASSWORD));
		
		// Check if email is a registered student email and not already registered
		if (regRepo.findByEmail(email) != null && userService.findStudentByEmail(email) == null) {
			Student s = new Student();
			s.setName(name);
			s.setEmail(email);
			s.setPhone(phone);
			s.setPassword(encryptedPassword);
			userService.saveUser(s);
			System.out.println("New student created");
			return Constants.SUCCESS;
		}
		return Constants.EMPTY;
	}
	
	// Stakeholder registration
	@PostMapping("/stakeholderRegistrationAttempt")
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String stakeholderRegistrationAttempt(@RequestBody Map<String, String> info) {
		String email = info.get(Constants.EMAIL);
		String name = info.get(Constants.NAME);
		String phone = info.get(Constants.PHONE);
		String companyName = info.get(Constants.COMPANY);
		String encryptedPassword = encryptPassword(info.get(Constants.PASSWORD));
		
		// Check if email has already been registered
		if (userService.findStakeholderByEmail(email) == null) {
			Stakeholder s = new Stakeholder();
			s.setName(name);
			s.setEmail(email);
			s.setPhone(phone);
			s.setOrganization(companyName);
			s.setPassword(encryptedPassword);
			userService.saveUser(s);
			System.out.println("New stakeholder created");
			return Constants.SUCCESS;
		}
		return Constants.EMPTY;
	}
	
	// Admin can register student emails and send an invitation to the platform
	@RequestMapping(value = "/studentEmailsRegistrationAttempt",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public void studentEmailRegistrationAttempt(@RequestBody Map<String, String> emailsData)
	{
		System.out.println(emailsData);
		System.out.println("Received HTTP POST");
		
		String[] emailsArray = emailsData.get(Constants.EMAILS).split("\n");
		
		for(String e : emailsArray)
		{
			// Save the email to registered student email table
			regRepo.save(new RegisteredStudentEmail(e));
			// Send an email invitation
			emailService.sendEmail("401 Platform Invite", "Congratulations! \nPlease sign up using the following link. \n \nlocalhost:3000/register/student", e);
			System.out.println("Sent invite to: " + e);
		}
	}
	
	/* Login */
	
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
}
