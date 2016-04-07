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
public class SearchController {

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("searchBar") String searchBar,
			@RequestParam("category") String category, Model model) {

		if (searchBar != null) {
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"beans.xml");
			switch (category) {
			case "videos": {
				DBVideoDAO dbVideoDao = (DBVideoDAO) context
						.getBean("DBVideoDAO");
				model.addAttribute("VideoNameLike",
						dbVideoDao.getAllVideosLike(searchBar));
				break;
			}
			case "users": {
				DBUserDAO dbUserDao = (DBUserDAO) context.getBean("DBUserDAO");
				model.addAttribute("ChannelNameLike",
						dbUserDao.getAllUsersByChannelName(searchBar));
				break;
			}
			default: // TODO: error page
				break;
			}

		}

		return "resultFromSearch";
	}
}
