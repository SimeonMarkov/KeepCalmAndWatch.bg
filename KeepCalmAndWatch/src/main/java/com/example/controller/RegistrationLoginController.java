package com.example.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.User;
import com.example.model.dao.DBUserDAO;

@Controller
public class RegistrationLoginController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String sayHello(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userJDBCTemplate = (DBUserDAO) context.getBean("DBUserDAO");
		User user = userJDBCTemplate.getUser("nikola");
		System.out.print("Channel name : " + user.getChannelName());
		System.out.print(", Email : " + user.getEmail());
		System.out.println(", Registered on : " + user.getRegistrationDate());
		model.addAttribute(user);
		return "test";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

		return new ModelAndView("register", "command", new User());
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("SpringWeb") User user, ModelMap model,
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
			model.addAttribute(user);
			return "test";
		}
	}
	
	
	
	private String encodePassword(String passwod){
		DigestSHA3 passdigesst = new DigestSHA3(256);
		passdigesst.update(passwod.getBytes());
		byte[] digest = passdigesst.digest();
		return Base64.getEncoder().encodeToString(digest);		//encodes to base64 string to fit in 45 character restriction
	}
}
