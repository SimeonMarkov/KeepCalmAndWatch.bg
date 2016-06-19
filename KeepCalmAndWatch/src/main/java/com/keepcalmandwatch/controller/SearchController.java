package com.keepcalmandwatch.controller;


import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.keepcalmandwatch.model.User;
import com.keepcalmandwatch.model.dao.DBPlaylistDAO;
import com.keepcalmandwatch.model.dao.DBUserDAO;
import com.keepcalmandwatch.model.dao.DBVideoDAO;

@Controller
public class SearchController {
	private static final String VIDEO_CATEGORY = "c";

	@SuppressWarnings("resource")
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("searchBar") String searchBar, @RequestParam("category") String category,
			Model model) {

		if (searchBar != null) {
			ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
			switch (category) {
			case "videos": {
				DBVideoDAO dbVideoDao = (DBVideoDAO) context.getBean("DBVideoDAO");
				model.addAttribute("VideoSearch", dbVideoDao.getAllVideosLike(searchBar));
				break;
			}
			case "users": {
				DBUserDAO dbUserDao = (DBUserDAO) context.getBean("DBUserDAO");
				model.addAttribute("ChannelNameLike", dbUserDao.getAllUsersByChannelName(searchBar));
				break;
			}
			default:
				return "error";
			}

		}

		return "resultFromSearch";
	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String searchVideoByCategory(Model model, @RequestParam(VIDEO_CATEGORY) String category) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBVideoDAO videoDao = (DBVideoDAO) context.getBean("DBVideoDAO");
		String displayCategory = "";
		if (category.equals("Music")) {
			displayCategory = "Музика";
		} else if (category.equals("Sport")) {
			displayCategory = "Спорт";
		} else if (category.equals("Games")) {
			displayCategory = "Игри";
		} else if (category.equals("News")) {
			displayCategory = "Новини";
		} else if (category.equals("Funny")) {
			displayCategory = "Забавни";
		}
		model.addAttribute("displayCategory", displayCategory);
		model.addAttribute("Category", category);
		model.addAttribute("VideoSearch", videoDao.getVideosByCategory(category));
		return "resultFromSearch";

	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/favoriteVideos", method = RequestMethod.GET)
	public String favoriteVideos(Model model, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		context.getBean("DBUserDAO");
		DBPlaylistDAO playlistDAO = (DBPlaylistDAO) context.getBean("DBPlaylistDAO");
		User user = null;
		if (session.getAttribute("LoggedUser") != null) {
			user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
			user.setFavorites(playlistDAO.getFavorites(user));
		} else {
			return "redirect:login";
		}
		model.addAttribute("VideoSearch", playlistDAO.getFavoriteVideos(user.getFavorites()));
		return "resultFromSearch";

	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
	public String subscriptions(Model model, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		context.getBean("DBUserDAO");
		context.getBean("DBPlaylistDAO");
		User user = null;
		if (session.getAttribute("LoggedUser") != null) {
			user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
		} else {
			return "redirect:login";
		}
		DBUserDAO dbUserDao = (DBUserDAO) context.getBean("DBUserDAO");
		
		model.addAttribute("ChannelNameLike", dbUserDao.getSubscriptions(user));
		return "resultFromSearch";
	}
}
