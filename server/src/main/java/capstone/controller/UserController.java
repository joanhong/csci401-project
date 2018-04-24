package capstone.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.User;
import capstone.repository.UserRepository;
import capstone.service.EncryptPassword;
import capstone.sql.SQLDriver;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {
	public SQLDriver driver;
	private UserRepository repository;

	public UserController(UserRepository repository) {
		this.repository = repository;
		driver = new SQLDriver(5); // TODO: have this be configured as NUM_RANKED, not hard-coded
		driver.connect();

	}

	@GetMapping("/users")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<User> users() {
		Vector<User> userVector = driver.getAllUsers();
		for (User u : userVector) {
			repository.save(u);
		}
		return repository.findAll().stream().collect(Collectors.toList());
	}

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:3000")
	public String login(@RequestBody User login) throws ServletException {

		String jwtToken = "";

		if (login.getEmail() == null || login.getPassword() == null) {
			return "";
		}

		String email = login.getEmail();
		String password = login.getPassword();

		User user = driver.getUserByEmail(email);

		if (user == null) {
			throw new ServletException("Invalid login");
		}
		
//		if (!EncryptPassword.checkPassword(password, user.getPassword())) {
//			throw new ServletException("Invalid login");
//		}

		String userType = user.getUserType();

		jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		// System.out.println("Jwt: " + jwtToken);
		return jwtToken + "," + userType;
	}
	
	@PostMapping("/adminRegistrationAttempt")
	@CrossOrigin(origins = "http://localhost:3000")
	public String registerAdmin(@RequestBody Map<String, String> info) {
		System.out.println("Admin registered");
		String firstName = info.get("firstName");
		String lastName = info.get("lastName");
		String phone = info.get("phone");
		String email = info.get("email");
		String password = EncryptPassword.encryptPassword(info.get("password"));
		driver.addUserEntry(1, firstName, lastName, phone, email, password);
		return "SUCCESS";
	}
	
	@PostMapping("/studentRegistrationAttempt")
	@CrossOrigin(origins = "http://localhost:3000")
	public String registerStudent(@RequestBody Map<String, String> info) {
		System.out.println("Student registered");
		String firstName = info.get("firstName");
		String lastName = info.get("lastName");
		String phone = info.get("phone");
		String email = info.get("email");
		String password = EncryptPassword.encryptPassword(info.get("password"));
		driver.addUserEntry(3, firstName, lastName, phone, email, password);
		return "SUCCESS";
	}
	
	@PostMapping("/stakeholderRegistrationAttempt")
	@CrossOrigin(origins = "http://localhost:3000")
	public String registerStakeholder(@RequestBody Map<String, String> info) {
		System.out.println("Stakeholder registered");
		String firstName = info.get("firstName");
		String lastName = info.get("lastName");
		String phone = info.get("phone");
		String email = info.get("email");
		String password = EncryptPassword.encryptPassword(info.get("password"));
		driver.addUserEntry(2, firstName, lastName, phone, email, password);
		String organization = info.get("company");
		Integer orgId = driver.addOrganization(organization);
		User user = driver.getUserByEmail(email);
		driver.addStakeholderInfoEntry(user.getUserId(), orgId);
		return "SUCCESS";
	}
}
