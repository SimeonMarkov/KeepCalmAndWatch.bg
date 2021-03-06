package com.keepcalmandwatch.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.keepcalmandwatch.model.Comment;
import com.keepcalmandwatch.model.User;
import com.keepcalmandwatch.model.Video;
import com.keepcalmandwatch.model.dao.DBPlaylistDAO;
import com.keepcalmandwatch.model.dao.DBUserDAO;
import com.keepcalmandwatch.model.dao.DBVideoDAO;

@Controller
@SessionAttributes(value = "{LoggedUser,AllVideos}")
public class VideoWatchController {

	@SuppressWarnings("resource")
	@RequestMapping(value = "/watchVideo", method = RequestMethod.GET)
	public String watchVideo(Model model, @RequestParam("v") int id, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBVideoDAO videoJDBCTemplate = (DBVideoDAO) context.getBean("DBVideoDAO");
		Video video = null;
		try {
			video = videoJDBCTemplate.getVideo(id);
			model.addAttribute("uploader", video.getUploader().getUsername());
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("badUrl", HttpStatus.NOT_FOUND);
			return "error";
		}
		List<Comment> commentsToCurrentVideo = videoJDBCTemplate.getCommentsForSingleVideo(id);

		// get all videos from the same category
		List<Video> suggestedVideos = videoJDBCTemplate.getVideosByCategory(video.getCategory());
		suggestedVideos.remove(video);
		Collections.reverse(suggestedVideos);
		suggestedVideos.add(video);
		model.addAttribute("video", video);
		model.addAttribute("comments", commentsToCurrentVideo);
		model.addAttribute("SuggestedVideos", suggestedVideos);
		return "watchVideo";
	}

	@SuppressWarnings("resource")
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

		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userDao = (DBUserDAO) context.getBean("DBUserDAO");
		DBVideoDAO videoDao = (DBVideoDAO) context.getBean("DBVideoDAO");
		JsonObject jsonObjectParsed = (JsonObject) new JsonParser().parse(json);
		Comment comment = new Comment();
		System.out.println("json---> " + jsonObjectParsed.get("text").getAsString());
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

	@SuppressWarnings({ "resource", "unused" })
	@RequestMapping(value = "/favorite", method = RequestMethod.GET)
	public String favorite(Model model, @RequestParam("v") int id, HttpSession session) {

		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userDao = (DBUserDAO) context.getBean("DBUserDAO");
		DBPlaylistDAO playlistDAO = (DBPlaylistDAO)context.getBean("DBPlaylistDAO");
		User user = null;
		if (session.getAttribute("LoggedUser") != null) {
			user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
			user.setFavorites(playlistDAO.getFavorites(user));
			playlistDAO.addToFavorites(user.getFavorites(), id);
		}else{
			return "redirect:login";
		}
		
		return "redirect:watchVideo?v=" + id;
	}
}
