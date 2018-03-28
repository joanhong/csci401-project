package capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mail.mailDriver;

@SpringBootApplication
public class PlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
//		mailDriver mD = new mailDriver("csci401server", "drowssap$$$");
//		mD.sendEmail("401 Platform Invite!", "Congratulations! The mailDriver totally works!", "shantang@usc.edu");
	}
}
