package capstone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User 
{
	
	@Id
	@GeneratedValue
	private long id;
	
	int userNumber;
	String userType;
	String fullName;
	
	String year; //only valid if userType = Student
	String email;
	String username;
	String password; //encrypted
	String phone;
	int projectNumber;
	String companyName; //only valid if userType = Stakeholder
	String USC_ID; // only valid if userType = Student
	String semester;
	
	String ipaddress;
	
	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public User() {}
	
	public User(String fullName) 
	{
		this.fullName = fullName;
	}
	
	public User(User u)
	{
		this.id = u.getId();
		this.userNumber = u.getUserNumber();
		this.userType = u.getUserType();
		this.fullName = u.getFullName();
		this.year = u.getYear();
		this.email = u.getEmail();
		this.username = u.getUsername();
		this.password = u.getPassword();
	}
	
	@Override
	public String toString()
	{
		return "User{" +
				"id=" + id +
				", firstName=" + fullName + '\'' +
				'}';
	}
	
	public int getUserNumber() 
	{
		return userNumber;
	}
	
	public void setUserNumber(int userNumber) 
	{
		this.userNumber = userNumber;
	}
	
	public String getUserType() 
	{
		return userType;
	}
	
	public void setUserType(String userType) 
	{
		switch (userType) {
			case "1": this.userType = "Admin";
			break;
			
			case "2": this.userType = "Stakeholder";
			break;
			
			case "3": this.userType = "Student";
			break;
		}
	}
	public String getFullName() 
	{
		return fullName;
	}
	public void setFullName(String firstName) 
	{
		this.fullName = firstName;
	}
	
	public String getYear() 
	{
		return year;
	}
	public void setYear(String year) 
	{
		this.year = year;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getPhone() 
	{
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	public int getProjectNumber() 
	{
		return projectNumber;
	}
	public void setProjectNumber(int projectNumber) 
	{
		this.projectNumber = projectNumber;
	}
	public String getCompanyName() 
	{
		return companyName;
	}
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}
	public String getUSC_ID() 
	{
		return USC_ID;
	}
	public void setUSC_ID(String uSC_ID) 
	{
		USC_ID = uSC_ID;
	}
	public String getSemester() 
	{
		return semester;
	}
	public void setSemester(String semester) 
	{
		this.semester = semester;
	}
	public long getId() 
	{
		return id;
	}
	public void setId(long id) 
	{
		this.id = id;
	}
	
}
