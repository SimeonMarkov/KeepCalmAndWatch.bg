package com.keepcalmandwatch.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.keepcalmandwatch.model.User;
import com.keepcalmandwatch.model.Video;
import com.keepcalmandwatch.model.dao.DBPlaylistDAO;
import com.keepcalmandwatch.model.dao.DBUserDAO;
import com.keepcalmandwatch.model.dao.DBVideoDAO;

@Controller
@SessionAttributes("LoggedUser")
public class UserController {

	@SuppressWarnings({ "unused", "resource" })
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		if (session.getAttribute("LoggedUser") != null) {
			User user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
			return "profile";
		} else {
			return "redirect:login";
		}
	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/channel", method = RequestMethod.GET)
	public String channel(Model model, @RequestParam("user") String channelName, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userDao = (DBUserDAO) context.getBean("DBUserDAO");
		DBVideoDAO videoDao = (DBVideoDAO) context.getBean("DBVideoDAO");
		DBPlaylistDAO playlistDAO = (DBPlaylistDAO)context.getBean("DBPlaylistDAO");
		User user = null;
		if (session.getAttribute("LoggedUser") != null) {
			user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
		}
		User chosenUser = null;
		try {
			chosenUser = userDao.getUserBChannelName(channelName);
			chosenUser.setFavorites(playlistDAO.getFavorites(chosenUser));
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("noSuchUser", HttpStatus.NOT_FOUND);
			return "error";
		}
		List<Video> videosForChannelName = videoDao.getVideosForChannelName(channelName);
		List<Video> favoriteVideosForChannel = playlistDAO.getFavoriteVideos(chosenUser.getFavorites());
		model.addAttribute("ChosenUser", chosenUser);
		model.addAttribute("VideosForChannelName", videosForChannelName);
		model.addAttribute("favorites", favoriteVideosForChannel);
		if(user != null && chosenUser != null){
			if(userDao.isSubscribed(chosenUser, user)){
				model.addAttribute("subscribed", true);
			}
		}
		return "channel";
	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(ModelMap modelMap, HttpSession session, @RequestParam("channelName") String channelName,
			@RequestParam("email") String email, WebRequest webRequest, @RequestParam("password") String password,
			final RedirectAttributes redirectAttributes) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO dbUserDao = (DBUserDAO) context.getBean("DBUserDAO");
		String description = webRequest.getParameter("description");
		String newPassword = webRequest.getParameter("newPassword");
		String newPasswordConf = webRequest.getParameter("newPasswordConf");
		String fail = "";
		User user = (User) session.getAttribute("LoggedUser");

		if (dbUserDao.emailExist(email)) {
			fail = "Имейл адресът е вече зает!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		} else if (dbUserDao.channelExist(channelName)) {
			fail = "Канал с такова име вече съществува!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		} else if (!RegistrationLoginController.isValidEmailAddress(email)) {
			fail = "Невалиден имейл адрес!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		} else if (!user.getPassword().equals(RegistrationLoginController.encodePassword(password))) {
			fail = "Грешна парола!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		} else if (newPassword != "" || newPasswordConf != "") {
			if (newPassword.length() < 6 || newPasswordConf.length() < 6) {
				fail = "Новата парола съдържа по-малко от 6 символа!";
				redirectAttributes.addFlashAttribute("message", fail);
				return "redirect:profile";
			} else if (!newPassword.equals(newPasswordConf)) {
				fail = "Паролите са различни!";
				redirectAttributes.addFlashAttribute("message", fail);
				return "redirect:profile";
			} else { 
				user.setDescription(description);
				user.setEmail(email);
				user.setChannelName(channelName);
				if(newPassword != "" && newPassword.length() >= 6 && newPassword.equals(newPasswordConf)){
					user.setPassword(RegistrationLoginController.encodePassword(newPassword));
				}
				dbUserDao.updateUser(user);
				fail = "Успешно редактирахте профила си!";
				redirectAttributes.addFlashAttribute("message", fail);
				return "redirect:profile";
			}
		}else { 
			user.setDescription(description);
			user.setEmail(email);
			user.setChannelName(channelName);
			if(newPassword != "" && newPassword.length() >= 6 && newPassword.equals(newPasswordConf)){
				user.setPassword(RegistrationLoginController.encodePassword(newPassword));
			}
			dbUserDao.updateUser(user);
			fail = "Успешно редактирахте профила си!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		}
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/avatarUpdate", method = RequestMethod.POST)
	public String updateUser(ModelMap modelMap, HttpSession session, WebRequest webRequest,
			final RedirectAttributes redirectAttributes, @RequestParam("avatar") MultipartFile avatar) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		String fail = "";
		if(FilenameUtils.getExtension(avatar.getOriginalFilename()).equals("jpg") ||
				FilenameUtils.getExtension(avatar.getOriginalFilename()).equals("png")){
			
			User user = (User) session.getAttribute("LoggedUser");
			AWSCredentials credentials = new BasicAWSCredentials(
					"AKIAIDEAOQSKMINEQSVA",
					"o94Ozi37icf6+HoROskITlkAvdwRdphYXsPmrya4");
			AmazonS3Client s3client = new AmazonS3Client(credentials);

			// amazon folder
			String bucketName = "keep-calm-avatars";
			
			File convFile = new File(avatar.hashCode()
					+ avatar.getOriginalFilename());
			
			try {
				// converts to files in order to upload to amazon s3
				convFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(convFile);
				fos.write(avatar.getBytes());
				fos.close();
			} catch (IOException e1) {
				System.out.println("File could not be created");
				e1.printStackTrace();
			}
			
			s3client.putObject(new PutObjectRequest(bucketName, convFile
					.getName(), convFile)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			String avatarPath = s3client.getResourceUrl(bucketName,
					convFile.getName());
			user.setAvatar(avatarPath);
			DBUserDAO dbUserDao = (DBUserDAO) context.getBean("DBUserDAO");
			dbUserDao.updateUser(user);
			fail = "Успешно редактирахте профила си!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		} else {
			fail="Аватарът може да бъде само от тип jpg или png!";
			redirectAttributes.addFlashAttribute("message", fail);
			return "redirect:profile";
		}
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public String subscribe(Model model, @RequestParam("user") String channelName, HttpSession session, WebRequest webRequest){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userDao = (DBUserDAO) context.getBean("DBUserDAO");
		User user = null;
		if (session.getAttribute("LoggedUser") != null) {
			user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
		}else{
			return "redirect:login";
		}
		User chosenUser = null;
		try {
			chosenUser = userDao.getUserBChannelName(channelName);
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("noSuchUser", HttpStatus.NOT_FOUND);
			return "error";
		}
		
		model.addAttribute("ChosenUser", chosenUser);
		if(user != null && chosenUser != null){
			if(!userDao.isSubscribed(chosenUser, user)){
				userDao.subscribe(chosenUser, user);
				model.addAttribute("subscribed", true);
			}
		}
		return "channel";
	}
	
	@SuppressWarnings("resource")
	@RequestMapping(value = "/unSubscribe", method = RequestMethod.GET)
	public String unSubscribe(Model model, @RequestParam("user") String channelName, HttpSession session, WebRequest webRequest){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userDao = (DBUserDAO) context.getBean("DBUserDAO");
		User user = null;
		if (session.getAttribute("LoggedUser") != null) {
			user = (User) session.getAttribute("LoggedUser");
			model.addAttribute(user);
		}else{
			return "redirect:login";
		}
		User chosenUser = null;
		try {
			chosenUser = userDao.getUserBChannelName(channelName);
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("noSuchUser", HttpStatus.NOT_FOUND);
			return "error";
		}
		
		model.addAttribute("ChosenUser", chosenUser);
		if(user != null && chosenUser != null){
			if(userDao.isSubscribed(chosenUser, user)){
				userDao.unSubscribe(chosenUser, user);
				model.addAttribute("subscribed", null);
			}
		}
		return "channel";
	}
}
