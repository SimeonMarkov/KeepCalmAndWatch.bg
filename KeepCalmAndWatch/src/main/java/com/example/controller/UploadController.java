package com.example.controller;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.User;
import com.example.model.Video;
import com.example.model.dao.DBUserDAO;
import com.example.model.dao.DBVideoDAO;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String goToUpload(Model model){
		System.out.println("Going to upload...");
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userJDBCTemplate = 
			      (DBUserDAO)context.getBean("DBUserDAO");
		User user = userJDBCTemplate.getUser("nikola");
		model.addAttribute(user);
		return "upload";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	 public ModelAndView confirmUpload(ModelMap model, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("thumbnail") byte[] thumbnail , @RequestParam("videoPath") String videoPath) {
			ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
				  ModelAndView mav = new ModelAndView("uploadFinalization");
				  User user = ((DBUserDAO) context.getBean("DBUserDAO")).getUser("nikola");
				  mav.addObject("message", "User with channel name " + user.getChannelName() + "uploaded a video with path " +  videoPath + ",title " + title + " and description " + description +  " was sucessfully uploaded!");
				  DBVideoDAO videoJDBCTemplate = 
					      (DBVideoDAO)context.getBean("DBVideoDAO");
				  Video video = new Video();
				 
				  
					video.setTitle(title);
					video.setDescription(description);
					video.setPath(videoPath);
					video.setViews(0);
					video.setLikes(0);
					video.setDislikes(0);
					video.setThumbnail(thumbnail);
					video.setUploadDate(Date.valueOf(LocalDate.now()));
					video.setUploader(user); //TODO: how to add the current logged user as an uploader
					videoJDBCTemplate.addVideo(video);
//			      model.addAttribute("name", video.getTitle());
//			      model.addAttribute("name", video.getDescription());
//			      model.addAttribute("name", video.getPath());
//			      model.addAttribute("name", video.getViews());
//			      model.addAttribute("name", video.getLikes());
//			      model.addAttribute("name", video.getDislikes());
//			      model.addAttribute("name", video.getThumbnail());
//			      model.addAttribute("name", video.getUploadDate());
//			      model.addAttribute("name", video.getUploader());
			      
			      return mav;
			   }

}
