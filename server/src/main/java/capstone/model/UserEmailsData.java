package capstone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserEmailsData
{
	@Id
	@GeneratedValue
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	
	String emails;
	
	public UserEmailsData(String emails) 
	{
		super();
		this.emails = emails;
	}
	public UserEmailsData()
	{
		
	}
	
}