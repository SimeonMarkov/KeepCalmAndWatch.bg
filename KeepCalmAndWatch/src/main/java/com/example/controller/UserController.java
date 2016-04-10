package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.User;
import com.example.model.Video;
import com.example.model.dao.DBUserDAO;
import com.example.model.dao.DBVideoDAO;

@Controller
@SessionAttributes("LoggedUser")
public class UserController {

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session){
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		if(session.getAttribute("LoggedUser") != null){
			User user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
		}
		return "profile";
	}
	
	@RequestMapping(value = "/channel", method = RequestMethod.GET)
	public String channel(Model model, @RequestParam("user") String channelName, HttpSession session){
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		DBUserDAO userDao = (DBUserDAO) context.getBean("DBUserDAO");
		DBVideoDAO videoDao = (DBVideoDAO) context.getBean("DBVideoDAO");
		if(session.getAttribute("LoggedUser") != null){
			User user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
		}
		User chosenUser = null;
		try{
			chosenUser = userDao.getUserBChannelName(channelName);
		}
		catch(EmptyResultDataAccessException e){
			model.addAttribute("noSuchUser",HttpStatus.NOT_FOUND);
			return "error";
		}
		List<Video> videosForChannelName = videoDao.getVideosForChannelName(channelName);
		
		model.addAttribute("ChosenUser", chosenUser);
		model.addAttribute("VideosForChannelName", videosForChannelName);
		System.out.println(videosForChannelName);
		
		return "channel";
	}
}
