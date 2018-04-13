package capstone.controller;

import java.util.Collection;
import java.util.Vector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import capstone.model.User;
import capstone.repository.StakeholderRepository;
import capstone.sql.SQLDriver;

@RestController
public class StakeholderController 
{
	public SQLDriver driver;
	private StakeholderRepository repository;
	
	public StakeholderController(StakeholderRepository repository)
	{
		this.repository = repository;
		driver = new SQLDriver(5);  // TODO: have this be configured as NUM_RANKED, not hard-coded
		driver.connect();
	}
	
	@GetMapping("/stakeholders")
	@CrossOrigin(origins = "http://localhost:3000")
	public Collection<User> stakeholders()
	{
		Vector<User> stakeholders = driver.getAllStakeholders();
		for(User u: stakeholders)
		{
			repository.save(u);
		}
		return repository.findAll().stream()
				.collect(Collectors.toList());
	}
}
