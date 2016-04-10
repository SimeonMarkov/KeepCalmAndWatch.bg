package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.User;
import com.example.model.Video;
import com.example.model.dao.DBUserDAO;
import com.example.model.dao.DBVideoDAO;

@Controller
@SessionAttributes("LoggedUser")
public class UserController {

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		if (session.getAttribute("LoggedUser") != null) {
			User user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
			return "profile";
		} else {
			return "redirect:login";
		}

	}

	@RequestMapping(value = "/channel", method = RequestMethod.GET)
	public String channel(Model model, @RequestParam("user") String channelName, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userDao = (DBUserDAO) context.getBean("DBUserDAO");
		DBVideoDAO videoDao = (DBVideoDAO) context.getBean("DBVideoDAO");
		if (session.getAttribute("LoggedUser") != null) {
			User user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
		}
		User chosenUser = null;
		try {
			chosenUser = userDao.getUserBChannelName(channelName);
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("noSuchUser", HttpStatus.NOT_FOUND);
			return "error";
		}
		List<Video> videosForChannelName = videoDao.getVideosForChannelName(channelName);

		model.addAttribute("ChosenUser", chosenUser);
		model.addAttribute("VideosForChannelName", videosForChannelName);
		System.out.println(videosForChannelName);

		return "channel";
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(ModelMap modelMap, HttpSession session, @RequestParam("channelName") String channelName,
			@RequestParam("email") String email, WebRequest webRequest, @RequestParam("password") String password,
			final RedirectAttributes redirectAttributes) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO dbUserDao = (DBUserDAO) context.getBean("DBUserDAO");
		String description = webRequest.getParameter("description");
		String newPassword = webRequest.getParameter("newPassword");
		String newPasswordConf = webRequest.getParameter("newPasswordConf");
		String fail = "";
		User user = (User) session.getAttribute("LoggedUser");

		if (dbUserDao.emailExist(email)) {
			fail = "Имейл адресът е вече зает!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		} else if (dbUserDao.channelExist(channelName)) {
			fail = "Канал с такова име вече съществува!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		} else if (newPassword != "" || newPasswordConf != "") {
			if (newPassword.length() < 6 || newPasswordConf.length() < 6) {
				fail = "Новата парола съдържа по-малко от 6 символа!";
				redirectAttributes.addFlashAttribute("message", fail);
				return "redirect:profile";
			}
			if (!newPassword.equals(newPasswordConf)) {
				fail = "Паролите са различни!";
				redirectAttributes.addFlashAttribute("message", fail);
				return "redirect:profile";
			}
		} else if (!RegistrationLoginController.isValidEmailAddress(email)) {
			fail = "Невалиден имейл адрес!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		} else if (!user.getPassword().equals(RegistrationLoginController.encodePassword(password))) {
			fail = "Грешна парола!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		} else {
			fail = "Успешно редактирахте профила си!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		}
		redirectAttributes.addFlashAttribute("message", fail);
		return "redirect:profile";
	}
}
