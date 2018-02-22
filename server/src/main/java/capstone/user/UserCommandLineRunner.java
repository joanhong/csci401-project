package capstone.user;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {
	private final UserRepository repository;
	
	public UserCommandLineRunner(UserRepository repository)
	{
		this.repository = repository;
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		Stream.of("Joan Hong", "Ingrid Wang").forEach(firstName ->
				repository.save(new User(firstName))
		);
		repository.findAll().forEach(System.out::println);
	}	
}
