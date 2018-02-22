package capstone.user;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController 
{
	private UserRepository repository;
	
	public UserController(UserRepository repository)
	{
		this.repository = repository;
	}
	
	@GetMapping("/users")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<User> users()
	{
		return repository.findAll().stream()
				.collect(Collectors.toList());
	}
}
