package com.example.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Video;
import com.example.model.dao.DBVideoDAO;

@Controller
@SessionAttributes("LoggedUser")
@RequestMapping("/watchVideo")
public class VideoWatchController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String watchVideo(Model model, @RequestParam("v") int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBVideoDAO videoJDBCTemplate = (DBVideoDAO) context.getBean("DBVideoDAO");
		Video video = videoJDBCTemplate.getVideo(id);
		model.addAttribute("video", video);
		return "watchVideo";
	}
	
}
