package capstone.user;

import java.util.Vector;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import capstone.sql.SQLDriver;

@Component
public class UserCommandLineRunner implements CommandLineRunner {
	private final UserRepository repository;
	public SQLDriver driver;
	public Vector<User> userVector;
	
	public UserCommandLineRunner(UserRepository repository)
	{
		this.repository = repository;
	}
	
	@Override
	public void run(String... arg0) throws Exception 
	{
		
		driver = new SQLDriver();
		driver.connect();
		userVector = driver.getAllUsers();
//		for(int i=0; i<userVector.size(); i++)
//		{
//			repository.save(userVector.iterator().);
//		}
		
		for(User u: userVector)
		{
			repository.save(u);
		}
		
		// TODO Auto-generated method stub
//		Stream.of("Joan Hong", "Ingrid Wang").forEach(firstName ->
//				repository.save(new User(firstName))
//		);
//		repository.findAll().forEach(System.out::println);
	}	
}
