package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.User;

@Controller
public class UserController
{
	
	
	@RequestMapping ("/user")
	public String user (Model model)
	{
		User u = new User();
		u.setFirstName("Shantanu");
		u.setLastName("Gupta");
		u.setEmail("shantang@usc.edu");
		u.setPassword("08e7c3fq9");
		u.setPhone("2132455631");
		u.setProjectNumber(27);
		u.setUSC_ID("1628742631");
		u.setSemester("Spring 2018");
		u.setYear("Senior");
		model.addAttribute("user", u);
		return "userView";
	}
	
	@ResponseBody
	@RequestMapping ("/")
	String entry()
	{
		return "Welcome to the CSCI401 Portal!";
	}
}