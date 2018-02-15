package controller;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import service.UserService;

@RestController
public class UserRestController 
{

	@Autowired
	UserService us;
	
	@RequestMapping ("users/all")
	@ResponseBody
	public Hashtable<String, User> getAll()
	{
		return us.getAll();
	}
	
	@RequestMapping ("/users/" + "{id}")
	@ResponseBody
	public User getUser(@PathVariable("id") String id)
	{
		return us.getUser(id);
	}
	
}
