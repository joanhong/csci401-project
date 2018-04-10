package capstone.controller;

import java.util.Collection;
import java.util.Vector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.User;
import capstone.repository.UserRepository;
import capstone.sql.SQLDriver;

@RestController
public class UserController 
{
	public SQLDriver driver;
	private UserRepository repository;
	
	public UserController(UserRepository repository)
	{
		this.repository = repository;
		driver = new SQLDriver(5);  // TODO: have this be configured as NUM_RANKED, not hard-coded
		driver.connect();
		
	}
	
	@GetMapping("/users")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<User> users()
	{
		Vector<User> userVector = driver.getAllUsers();
		for(User u: userVector)
		{
			repository.save(u);
		}
		return repository.findAll().stream()
				.collect(Collectors.toList());
	}
}
