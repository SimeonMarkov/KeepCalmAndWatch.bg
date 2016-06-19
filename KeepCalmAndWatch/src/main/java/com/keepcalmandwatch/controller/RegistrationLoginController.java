package com.keepcalmandwatch.controller;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keepcalmandwatch.model.User;
import com.keepcalmandwatch.model.dao.DBUserDAO;
import com.keepcalmandwatch.model.dao.DBVideoDAO;


@Controller
@SessionAttributes(value={"LoggedUser", "AllVideos"})
public class RegistrationLoginController {

	@SuppressWarnings("resource")
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String loadHomePage(ModelMap modelMap ,HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBVideoDAO dbVideoDao = 
			      (DBVideoDAO)context.getBean("DBVideoDAO");
		try {
			session.setAttribute("AllVideos",dbVideoDao.listVideos());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(session.getAttribute("LoggedUser") != null){
			return "index";
		}
	    return "index";
	}	
	
	@SuppressWarnings({ "unused", "resource" })
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView getToLoginPage(ModelMap modelMap, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		if(session.getAttribute("LoggedUser") != null){
			return new ModelAndView("redirect:/index", "command", session.getAttribute("LoggedUser"));
		}
		return new ModelAndView("login", "command", new User());
	}	
	
	@SuppressWarnings("resource")
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
	
	@SuppressWarnings({ "unused", "resource" })
	@RequestMapping(value="/register", method = RequestMethod.GET)

	public ModelAndView register(ModelMap modelMap, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		if(modelMap.containsAttribute("LoggedUser")){
			return new ModelAndView("redirect:/index", "command", session.getAttribute("LoggedUser"));
		}
		return new ModelAndView("register", "command", new User());
	}

	@SuppressWarnings("resource")
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
		} else if (!isValidEmailAddress(user.getEmail())) {
			fail = "Невалиден имейл адрес!";
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
		return "index";
	}
	
	
	
	public static String encodePassword(String passwod){
		DigestSHA3 passdigesst = new DigestSHA3(256);
		passdigesst.update(passwod.getBytes());
		byte[] digest = passdigesst.digest();
		return Base64.getEncoder().encodeToString(digest);		//encodes to base64 string to fit in 45 character restriction
	}
	
	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
}
