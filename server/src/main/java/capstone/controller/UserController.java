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
import capstone.repository.AdminRepository;
import capstone.repository.RegisteredStudentEmailRepository;
import capstone.repository.SessionRepository;
import capstone.repository.StakeholderRepository;
import capstone.repository.StudentRepository;
import capstone.repository.UserBaseRepository;
import capstone.service.EmailService;
import capstone.util.Constants;

@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private StakeholderRepository stakeholderRepo;
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private RegisteredStudentEmailRepository regRepo;
	@Autowired
	private EmailService emailService;
	@Autowired
	private SessionRepository sessions;
	
	
	public UserController(StudentRepository student, StakeholderRepository stakeholder, AdminRepository admin)
	{
		this.studentRepo = student;
		this.stakeholderRepo = stakeholder;
		this.adminRepo = admin;
	}
	
	@GetMapping("/all")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<User> getUsers()
	{
		Collection<User> users = new Vector<>();
		users.addAll((Collection<? extends User>) studentRepo.findAll());
		users.addAll((Collection<? extends User>) stakeholderRepo.findAll());
		users.addAll((Collection<? extends User>) adminRepo.findAll());
		return users;
	}
	
	@GetMapping("/stakeholders")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<Stakeholder> getStakeholders() {
		return (Collection<Stakeholder>) stakeholderRepo.findAll();
	}
	
	@GetMapping("/students")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<Student> getStudents() {
		return (Collection<Student>) studentRepo.findAll();
	}
	
	public User findUser(String email) {
		User u = studentRepo.findByEmail(email);
		if (u == null) {
			u = stakeholderRepo.findByEmail(email);
		}
		if (u == null) {
			u = adminRepo.findByEmail(email);
		}
		return u;
	}
	public User findUserByAddr(String addr) {
		User u = studentRepo.findByAddr(addr);
		if (u == null) {
			u = stakeholderRepo.findByAddr(addr);
		}
		if (u == null) {
			u = adminRepo.findByAddr(addr);
		}
		return u;
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
		if (regRepo.findByEmail(email) != null && studentRepo.findByEmail(email) == null) {
			Admin admin = new Admin();
			admin.setName(name);
			admin.setEmail(email);
			admin.setPhone(phone);
			admin.setPassword(encryptedPassword);
			adminRepo.save(admin);
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
		if (regRepo.findByEmail(email) != null && studentRepo.findByEmail(email) == null) {
			Student s = new Student();
			s.setName(name);
			s.setEmail(email);
			s.setPhone(phone);
			s.setPassword(encryptedPassword);
			studentRepo.save(s);
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
		if (stakeholderRepo.findByEmail(email) == null) {
			Stakeholder s = new Stakeholder();
			s.setName(name);
			s.setEmail(email);
			s.setPhone(phone);
			s.setCompanyName(companyName);
			s.setPassword(encryptedPassword);
			stakeholderRepo.save(s);
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
	
	@RequestMapping(value = "/loginAttempt",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String loginAttempt(@RequestBody Map<String, String> loginData, HttpServletRequest request, HttpSession session) {
		
		System.out.println("Received HTTP POST");
		System.out.println(loginData);
		System.out.println(loginData.get(Constants.EMAIL));
		
		session.invalidate();
		
		// check if username exists in database
		// check if username/password combo is valid
		String email = loginData.get("email");
		HttpSession newSession = request.getSession();
		newSession.setAttribute("email", email);
		if (studentRepo.findByEmail(email) != null) {
			Student s = studentRepo.findByEmail(email);
			s.setAddr(request.getHeader(HttpHeaders.ORIGIN));
			studentRepo.save(s);
			//sessions.save(new Session(newSession, s));
			return Constants.STUDENT;
		} else if (stakeholderRepo.findByEmail(email) != null) {
			Stakeholder s = stakeholderRepo.findByEmail(email);
			s.setAddr(request.getHeader(HttpHeaders.ORIGIN));
			//sessions.save(new Session(newSession, s));
			stakeholderRepo.save(s);
			return Constants.STAKEHOLDER;
			//return s;
		} else if (adminRepo.findByEmail(email) != null) {
			Admin a = adminRepo.findByEmail(email);
			//sessions.save(new Session(newSession, a));
			adminRepo.save(a);
			return Constants.ADMIN;
			//return a;
		} 
		//return null;
		return Constants.EMPTY;
	}
	
	@RequestMapping(value = "/loginUser",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody User loginUser(@RequestBody Map<String, String> loginData, HttpServletRequest request, HttpSession session) {
		
		System.out.println("Received HTTP POST");
		System.out.println(loginData);
		System.out.println(loginData.get(Constants.EMAIL));
		
		session.invalidate();
		
		// check if username exists in database
		// check if username/password combo is valid
		String email = loginData.get("email");
		HttpSession newSession = request.getSession();
		newSession.setAttribute("email", email);
		if (studentRepo.findByEmail(email) != null) {
			Student s = studentRepo.findByEmail(email);
			//sessions.save(new Session(newSession, s));
			return s;
		} else if (stakeholderRepo.findByEmail(email) != null) {
			Stakeholder s = stakeholderRepo.findByEmail(email);
			//sessions.save(new Session(newSession, s));
			//return Constants.STAKEHOLDER;
			return s;
		} else if (adminRepo.findByEmail(email) != null) {
			Admin a = adminRepo.findByEmail(email);
			//sessions.save(new Session(newSession, a));
			//return Constants.ADMIN;
			return a;
		} 
		return null;
	}
	
	@RequestMapping(value = "/logoutAttempt",consumes= "application/json",produces= "application/json", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public @ResponseBody String logoutAttempt(@RequestBody String email, HttpServletRequest request)
	{
		//String addr = request.getHeader(HttpHeaders.ORIGIN);
		System.out.println("Received HTTP POST");
		System.out.println(email);
		/*User u = findUser(email);
		u.setAddr("");
		if (u.getClass() == Student.class) {
			studentRepo.save((Student)u);
		} else if (u.getClass() == Stakeholder.class) {
			stakeholderRepo.save((Stakeholder)u);
		} else if (u.getClass() == Admin.class) {
			adminRepo.save((Admin)u);
		}*/
		return "LoggedOut";
	}
	
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
