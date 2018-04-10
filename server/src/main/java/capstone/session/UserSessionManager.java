package capstone.session;

import java.util.Vector;

import capstone.model.User;
import capstone.sql.SQLDriver;

public class UserSessionManager 
{
	//Addresses in userAddresses correspond to the loggedInUsers' indices.
	Vector<User> activeUsers = new Vector<User>();
	Vector<String> activeUsersEmails = new Vector<String>();
	SQLDriver driver;
	
	public UserSessionManager() 
	{
		super();
		driver = new SQLDriver(5);  // TODO: have this be configured as NUM_RANKED, not hard-coded
		driver.connect();	
	} 
	
	public Boolean loginUser(String email, String ip)
	{
//		System.out.println("EMAIL = " + email + " IP ADDR = " + ip);
		if(!activeUsersEmails.contains(email))
		{
			User newactive = new User();
			//read database for User info and save into member variables
			//driver.getAllUsers
			//returns a vector of all users, from that pick out the one with this email
			//and then populate all his info in the currentUser object.
			Vector<User> allUsers = driver.getAllUsers();
//			System.out.println("SIZE OF ALL USERS "+ allUsers.size());
			for(User u: allUsers)
			{
//				System.out.println("U's EMAIL WAS  = "+ u.getEmail());
				if(u.getEmail().equals(email))
				{
//					System.out.println("EMAILS MATCHED!");
					newactive.setId(u.getId());
					newactive.setYear(u.getYear());
					newactive.setFullName(u.getFullName());
					newactive.setUserType(u.getUserType());
					newactive.setEmail(email);
					newactive.setIpaddress(ip);
				}
			}
//			System.out.println("NEW ACTIVE ADDRESS = " + newactive.getIpaddress());
			activeUsers.addElement(newactive);
			activeUsersEmails.addElement(email);
			return true;
		}
		else
		{
			System.out.println("User already logged in!");
			return false;
		}
	}
	
	public void logoutUser(String email)
	{
		if(activeUsersEmails.contains(email))
		{
			activeUsersEmails.removeElement(email);
			for(User u: activeUsers)
			{
				if(u.getEmail() == email)
				{
					activeUsers.remove(u);
				}
			}
		}
		else
		{
			System.out.println("User is already logged out.");
		}
	}
	
	public User getUser(String address)
	{
		System.out.println(address);
		for(User u: activeUsers)
		{
			System.out.println(u.getIpaddress());
			if(u.getIpaddress().equals(address))
			{
				return u;
			}
		}
		return null;
	}
	
}
