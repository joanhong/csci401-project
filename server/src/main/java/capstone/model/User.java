package capstone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User 
{
	
	@Id
	@GeneratedValue	
	int userId;
	String userType;
	String firstName;
	String lastName;	
	String email;
	String username;
	String password;
	String phone;	
	String ipAddress;

	public User() {}
	
	public User(String firstName) 
	{
		this.firstName = firstName;
	}
	
	public User(User u)
	{
		this.userId = u.getUserId();
		this.userType = u.getUserType();
		this.firstName = u.getFirstName();
		this.lastName = u.getLastName();
		this.email = u.getEmail();
		this.username = u.getUsername();
		this.password = u.getPassword();
		this.phone = u.getPhone();
		this.ipAddress = u.getIpAddress();
	}
	
	@Override
	public String toString()
	{
		return "User: { id=" + this.userId + ", firstName=" + firstName + '\'' + "}";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType)  {
		switch (userType) {
			case "1": this.userType = "Admin";
			break;
			
			case "2": this.userType = "Stakeholder";
			break;
			
			case "3": this.userType = "Student";
			break;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
}
