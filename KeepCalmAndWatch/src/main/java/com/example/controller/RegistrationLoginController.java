package com.example.controller;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
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
import com.example.model.dao.DBVideoDAO;

@Controller
@SessionAttributes(value={"LoggedUser","AllVideos"})
public class RegistrationLoginController {

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String loadHomePage(ModelMap modelMap ,HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBVideoDAO dbVideoDao = 
			      (DBVideoDAO)context.getBean("DBVideoDAO");
		try {
			if(session.getAttribute("AllVideos") == null){
				session.setAttribute("AllVideos",dbVideoDao.listVideos());
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(session.getAttribute("LoggedUser") != null){
			return "loggedHeaderAndNav";
		}
	    return "unloggedHeaderAndNav";
	}	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView getToLoginPage(ModelMap modelMap, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		if(session.getAttribute("LoggedUser") != null){
			return new ModelAndView("redirect:/index", "command", session.getAttribute("LoggedUser"));
		}
		return new ModelAndView("login", "command", new User());
	}	
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String submitLogin(ModelMap modelMap, HttpSession session, @ModelAttribute("SpringWeb")User user, @RequestParam String username, @RequestParam String password, final RedirectAttributes redirectAttributes) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO dbUserDao = (DBUserDAO)context.getBean("DBUserDAO");
		user = dbUserDao.getUser(username);
		password = encodePassword(password);
		if(user == null || !(user.getPassword().equals(password))){
			redirectAttributes.addFlashAttribute("fail","Грешно потребителско име или парола!");
			return "redirect:/login";
		}
		modelMap.addAttribute("LoggedUser",user);
		session.setAttribute("LoggedUser", user);
	    return "redirect:/index";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)

	public ModelAndView register(ModelMap modelMap, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		if(modelMap.containsAttribute("LoggedUser")){
			return new ModelAndView("redirect:/loggedHeaderAndNav", "command", session.getAttribute("LoggedUser"));
		}
		return new ModelAndView("register", "command", new User());
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("SpringWeb") User user, ModelMap modelMap,
			final RedirectAttributes redirectAttributes) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		user.setRegistrationDate(Date.valueOf(LocalDate.now()));
		DBUserDAO dbUserDao = (DBUserDAO) context.getBean("DBUserDAO");
		String fail = "";

		if (dbUserDao.emailExist(user.getEmail())) {
			fail = "Имейл адресът е вече зает!";
			redirectAttributes.addFlashAttribute("fail", fail);
			return "redirect:/register";
		} else if (dbUserDao.channelExist(user.getChannelName())) {
			fail = "Канал с такова име вече съществува!";
			redirectAttributes.addFlashAttribute("fail", fail);
			return "redirect:/register";
		} else if (dbUserDao.userExist(user.getUsername())) {
			fail = "Потребителското име е вече заето!";
			redirectAttributes.addFlashAttribute("fail", fail);
			return "redirect:/register";
		} else if (user.getPassword().length() < 6) {
			fail = "Паролата съдържа по-малко от 6 символа!";
			redirectAttributes.addFlashAttribute("fail", fail);
			return "redirect:/register";
		} else {
			user.setPassword(encodePassword(user.getPassword()));
			dbUserDao.addUser(user);
			modelMap.addAttribute(user);
			return "test";
		}
	}
	
	@RequestMapping(value="/logout")
	public String logoutUser(ModelMap modelMap, HttpSession session){
		modelMap.remove("LoggedUser", modelMap.get("LoggedUser"));
		session.removeAttribute("LoggedUser");
		session.invalidate();
		return "unloggedHeaderAndNav";
	}
	
	
	
	private String encodePassword(String passwod){
		DigestSHA3 passdigesst = new DigestSHA3(256);
		passdigesst.update(passwod.getBytes());
		byte[] digest = passdigesst.digest();
		return Base64.getEncoder().encodeToString(digest);		//encodes to base64 string to fit in 45 character restriction
	}
}
