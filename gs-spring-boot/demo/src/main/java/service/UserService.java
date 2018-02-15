package service;

import java.util.Hashtable;

import org.springframework.stereotype.Service;

import model.User;

@Service
public class UserService 
{
	Hashtable<String, User> users = new Hashtable<String, User>();
	
	public UserService()
	{
		User u = new User();
		u.setId("1");
		u.setUSC_ID("1628742631");
		u.setFirstName("Tommy");
		u.setLastName("Trojan");
		u.setUserType("Student");
		u.setUsername("ttrojan");
		u.setEmail("ttrojan@usc.edu");
		u.setSemester("Spring 2018");
		users.put("1", u);
		
		u = new User();
		u.setId("2");
		u.setUSC_ID("1987532831");
		u.setFirstName("Jeffrey");
		u.setLastName("Miller");
		u.setUserType("Admin");
		u.setUsername("jeffrey.miller");
		u.setEmail("jeffrey.miller@usc.edu");
		u.setSemester("Spring 2018");
		users.put("2", u);
		
	}
	
	public User getUser(String id)
	{
		if(users.containsKey(id))
		{
			return users.get(id);
		}
		else
		{
			return null;
		}
	}
	
	public Hashtable<String, User> getAll()
	{
		return users;
	}
	
}
