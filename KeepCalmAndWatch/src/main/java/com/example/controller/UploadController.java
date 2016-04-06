package com.example.controller;

import java.io.BufferedOutputStream;
import java.io.*;
import java.io.FileOutputStream;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.model.User;
import com.example.model.Video;
import com.example.model.dao.DBUserDAO;
import com.example.model.dao.DBVideoDAO;

@Controller
@SessionAttributes("LoggedUser")
@RequestMapping("/upload")
public class UploadController{

	@RequestMapping(method = RequestMethod.GET)
	public String goToUpload(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userJDBCTemplate = (DBUserDAO) context.getBean("DBUserDAO");
//		if (!model.containsAttribute("LoggedUser")) {
//			return "redirect:/login";
//		}
		return "upload";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView confirmUpload(ModelMap model, @RequestParam("title") String title,
			@RequestParam("description") String description,  @RequestParam("videoPath") MultipartFile file) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ModelAndView mav = new ModelAndView("uploadFinalization");

		DBVideoDAO videoJDBCTemplate = (DBVideoDAO) context.getBean("DBVideoDAO");
		Video video = new Video();

		
//		video.setTitle(title);
//		video.setDescription(description);
//		video.setPath("TO DO!!!");
//		video.setViews(0);
//		video.setLikes(0);
//		video.setDislikes(0);
//		video.setThumbnail(thumbnail);
//		video.setUploadDate(Date.valueOf(LocalDate.now()));
//		User user = (User) model.get("LoggedUser");
//		video.setUploader(user); // TODO: how to add the current logged user as
//									// an uploader
//		videoJDBCTemplate.addVideo(video);
//		mav.addObject("message",
//				((User) model.get("LoggedUser")).getChannelName()
//						+ ",Вие успешно качихте клип, намиращ се в директорията " + "path" + ", който е със заглавие: "
//						+ title + " и описание: " + description + " на дата " + video.getUploadDate());

		if (!file.isEmpty()) {
			try {
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
						new File("C:\\Users\\Pavel\\Documents\\Eclipse\\KeepCalmAndWatch" + "\\" + title + file.getOriginalFilename())));
				FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();

			} catch (Exception e) {
				System.out.println("Upload failed");
			}

			
		}

		return mav;
	}
	
	
	
}
