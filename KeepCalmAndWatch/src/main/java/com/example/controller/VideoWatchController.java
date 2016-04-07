package com.example.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Comment;
import com.example.model.Video;
import com.example.model.dao.DBVideoDAO;

@Controller
@SessionAttributes(value="{LoggedUser,AllVideos}")
@RequestMapping("/watchVideo")
public class VideoWatchController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String watchVideo(Model model, @RequestParam("v") int id, HttpSession session){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBVideoDAO videoJDBCTemplate = (DBVideoDAO) context.getBean("DBVideoDAO");
		Video video = videoJDBCTemplate.getVideo(id);
		List<Comment> commentsToCurrentVideo = videoJDBCTemplate.getCommentsForSingleVideo(id);
		model.addAttribute("video", video);
		model.addAttribute("comments", commentsToCurrentVideo);
		try {
			if(session.getAttribute("AllVideos") == null){
				session.setAttribute("AllVideos", videoJDBCTemplate.listVideos());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "watchVideo";
	}
	
}
