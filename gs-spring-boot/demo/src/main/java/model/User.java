package model;

public class User {

	
	
	int userNumber;
	String userType;
	String firstName;
	String lastName;
	
	String year; //only valid if userType = Student
	String email;
	String username;
	String password; //encrypted
	String phone;
	int projectNumber;
	String companyName; //only valid if userType = Stakeholder
	String USC_ID; // only valid if userType = Student
	String semester;
	
	
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
		this.userType = userType;
	}
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
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
	
}

