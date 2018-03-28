package mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class mailDriver 
{
	
	public String email; //Preferably your gmail account
	public String password;
	
	
	public mailDriver(String email, String password) 
	{
		super();
		this.email = email;
		this.password = password;
	}


	public String getEmail() 
	{
		return email;
	}


	public void setEmail(String email) 
	{
		this.email = email;
	}


	public String getPassword() 
	{
		return password;
	}


	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public Boolean sendEmail(String subjectLine, String messageBody, String toEmail )
	{
		Properties props;
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() 
			{
				protected PasswordAuthentication getPasswordAuthentication() 
				{
					return new PasswordAuthentication(email, password);
				}
			});

		try 
		{

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toEmail));
			message.setSubject(subjectLine);
			message.setText(messageBody);

			Transport.send(message);
			return true;

		} 
		catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		}
		
	}
	
	
	

}
