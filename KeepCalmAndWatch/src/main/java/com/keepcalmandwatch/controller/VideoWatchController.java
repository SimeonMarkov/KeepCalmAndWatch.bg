package com.keepcalmandwatch.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.keepcalmandwatch.model.Comment;
import com.keepcalmandwatch.model.User;
import com.keepcalmandwatch.model.Video;
import com.keepcalmandwatch.model.dao.DBUserDAO;
import com.keepcalmandwatch.model.dao.DBVideoDAO;

@Controller
@SessionAttributes(value = "{LoggedUser,AllVideos}")
public class VideoWatchController {

	@RequestMapping(value = "/watchVideo", method = RequestMethod.GET)
	public String watchVideo(Model model, @RequestParam("v") int id,
			HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		DBVideoDAO videoJDBCTemplate = (DBVideoDAO) context
				.getBean("DBVideoDAO");
		Video video = null;
		try {
			video = videoJDBCTemplate.getVideo(id);
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("badUrl", HttpStatus.NOT_FOUND);
			return "error";
		}
		List<Comment> commentsToCurrentVideo = videoJDBCTemplate
				.getCommentsForSingleVideo(id);
		model.addAttribute("video", video);
		model.addAttribute("comments", commentsToCurrentVideo);
		try {
			if (session.getAttribute("AllVideos") == null) {
				session.setAttribute("AllVideos",
						videoJDBCTemplate.listVideos());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "watchVideo";
	}

	@RequestMapping(value = "/submitComment", method = RequestMethod.POST)
	@ResponseBody
	public void submitComment(@RequestBody String json) { // @RequestParam("channelName")
															// String
															// channelName,
															// @RequestParam("text")
															// String text,
															// @RequestParam("videoId")
															// int videoId){
		System.out.println(json);

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		DBUserDAO userDao = (DBUserDAO) context.getBean("DBUserDAO");
		DBVideoDAO videoDao = (DBVideoDAO) context.getBean("DBVideoDAO");
		JsonObject jsonObjectParsed = (JsonObject) new JsonParser().parse(json);
		Comment comment = new Comment();
		System.out.println("Kurec ---> "
				+ jsonObjectParsed.get("text").getAsString());
		comment.setText(jsonObjectParsed.get("text").getAsString());
		comment.setDatetime(LocalDateTime.now());
		comment.setLikes(0);
		comment.setDislikes(0);
		
		User user = userDao.getUserBChannelName(jsonObjectParsed.get("channelName").getAsString());
		comment.setUser(user);
		
		Video video = videoDao.getVideo(jsonObjectParsed.get("videoId").getAsInt());
		comment.setVideo(video);
		
		userDao.addComment(comment);
	}
}
