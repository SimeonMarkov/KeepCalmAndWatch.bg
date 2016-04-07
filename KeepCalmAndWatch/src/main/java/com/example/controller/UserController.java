package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.User;

@Controller
@SessionAttributes("LoggedUser")
public class UserController {

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session){
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		User user = (User) session.getAttribute("LoggedUser");
		model.addAttribute(user);
		return "profile";
	}
}
