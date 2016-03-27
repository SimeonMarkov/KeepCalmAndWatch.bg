package com.example.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.User;
import com.example.model.dao.DBUserDAO;

@Controller
public class HelloController {

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String sayHello(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userJDBCTemplate = 
			      (DBUserDAO)context.getBean("DBUserDAO");
		User user = userJDBCTemplate.getUser("nikola");
	      System.out.print("Channel name : " + user.getChannelName() );
	      System.out.print(", Email : " + user.getEmail() );
	      System.out.println(", Registered on : " + user.getRegistrationDate());
	    model.addAttribute(user);
	    return "test";
	}	
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String register(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	
	    return "register";
	}
	

}

