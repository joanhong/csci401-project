package capstone.model.users;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
public class User 
{	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Id
	@GeneratedValue
	private Long userId;
	
	// Basic information
	private String name;
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
	private String email;
	private String phone;
	
	// Temporary
	private String addr;
	
	// Login
	@Transient
	private String password; //encrypted
	
	public Long getId() 
	{
		return userId;
	}
	public void setId(Long id) 
	{
		this.userId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPhone() 
	{
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
}
