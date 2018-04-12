package capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.MailSender;

@SpringBootApplication
public class PlatformApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}
}
