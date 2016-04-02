package com.example.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.User;
import com.example.model.dao.DBUserDAO;

@Controller
@SessionAttributes("LoggedUser")
public class RegistrationLoginController {

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String loadHomePage(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userJDBCTemplate = 
			      (DBUserDAO)context.getBean("DBUserDAO");
		if(model.containsAttribute("LoggedUser")){
			return "test";
		}
	    return "redirect:/login";
	}	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView getToLoginPage(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		return new ModelAndView("login", "command", new User());
	}	
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String submitLogin(Model model, @ModelAttribute("SpringWeb")User user, @RequestParam String username, @RequestParam String password, final RedirectAttributes redirectAttributes) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO dbUserDao = (DBUserDAO)context.getBean("DBUserDAO");
		user = dbUserDao.getUser(username);
		if(user == null || !(user.getPassword().equals(password))){
			redirectAttributes.addFlashAttribute("fail","Грешно потребителско име или парола!");
			return "redirect:/login";
		}
		model.addAttribute("LoggedUser",user);
	    return "redirect:/index";
	}	
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	
		return new ModelAndView("register", "command", new User());
	}
	

	  @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	   public String registerUser(@ModelAttribute("SpringWeb")User user, ModelMap model, final RedirectAttributes redirectAttributes) {
		  ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		  user.setRegistrationDate(Date.valueOf(LocalDate.now()));
		  DBUserDAO dbUserDao = (DBUserDAO)context.getBean("DBUserDAO");
		  String fail = "";
		  if(dbUserDao.emailExist(user.getEmail())){
			  fail = "Имейл адресът е вече зает!";
			  redirectAttributes.addFlashAttribute("fail", fail);
			  return "redirect:/register";
		  }else if(dbUserDao.channelExist(user.getChannelName())){
			  fail = "Канал с такова име вече съществува!";
			  redirectAttributes.addFlashAttribute("fail", fail);
			  return "redirect:/register";
		  }else if(dbUserDao.userExist(user.getUsername())){
			  fail = "Потребителското име е вече заето!";
			  redirectAttributes.addFlashAttribute("fail", fail);
			  return "redirect:/register";
		  } else if(user.getPassword().length()<6){
			  fail = "Паролата съдържа по-малко от 6 символа!";
			  redirectAttributes.addFlashAttribute("fail", fail);
			  return "redirect:/register";
		  }else {
		  dbUserDao.addUser(user);
		  model.addAttribute(user);
	      return "test";
	      }
	   }
}

