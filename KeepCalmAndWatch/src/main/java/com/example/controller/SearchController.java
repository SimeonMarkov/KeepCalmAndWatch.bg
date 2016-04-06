package com.example.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.dao.DBUserDAO;
import com.example.model.dao.DBVideoDAO;

@Controller
@RequestMapping("/search")
public class SearchController {

	@RequestMapping(value="/users",method=RequestMethod.POST)
	public String searchUsers(@RequestParam("searchBar") String searchUser, Model model) {

	    if(searchUser != null){
	    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	        DBUserDAO dbUserDao = (DBUserDAO)context.getBean("DBUserDAO");
	        model.addAttribute("ChannelNameLike",dbUserDao.getAllUsersByChannelName(searchUser));
	    }

	    return "resultFromSearch";
	}
	@RequestMapping(value="/clips",method=RequestMethod.POST)
	public String searchVideos(@RequestParam("searchBar") String searchVideo, Model model) {

	    if(searchVideo != null){
	    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	        DBVideoDAO dbUserDao = (DBVideoDAO)context.getBean("DBVideoDAO");
	        model.addAttribute("ChannelNameLike",dbUserDao.getAllVideosLike(searchVideo));
	    }

	    return "search";
	}
}
