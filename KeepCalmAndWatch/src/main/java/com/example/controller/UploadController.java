package com.example.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	 public String confirmUpload(@ModelAttribute("SpringWeb") Video video, 
			   ModelMap model) {
			      model.addAttribute("name", video.getTitle());
			      model.addAttribute("name", video.getDescription());
			      model.addAttribute("name", video.getPath());
			      model.addAttribute("name", video.getViews());
			      model.addAttribute("name", video.getLikes());
			      model.addAttribute("name", video.getDislikes());
			      model.addAttribute("name", video.getThumbnail());
			      model.addAttribute("name", video.getUploadDate());
			      model.addAttribute("name", video.getUploader());
			      
			      return "uploadFinalization";
			   }


}
