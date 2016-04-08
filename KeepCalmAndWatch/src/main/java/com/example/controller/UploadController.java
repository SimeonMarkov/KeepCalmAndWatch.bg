package com.example.controller;

import java.io.*;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;

import org.apache.commons.io.FilenameUtils;
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

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.identitymanagement.model.AccessKey;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.model.User;
import com.example.model.Video;
import com.example.model.dao.DBUserDAO;
import com.example.model.dao.DBVideoDAO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.xuggle.xuggler.IContainer;

@Controller
@RequestMapping("/upload")
@SessionAttributes("LoggedUser")
public class UploadController {

	@RequestMapping(method = RequestMethod.GET)
	public String goToUpload(ModelMap modelMap, HttpSession session) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		DBUserDAO userJDBCTemplate = (DBUserDAO) context.getBean("DBUserDAO");
		if (!(session.getAttribute("LoggedUser") != null)) {
			return "redirect:/login";
		}
		return "upload";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView confirmUpload(ModelMap model,
			@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("videoPath") MultipartFile file,
			@RequestParam("thumbnail") MultipartFile thumbnail,
			@RequestParam("category") String category) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		ModelAndView mav = new ModelAndView("uploadFinalization");

		if ((FilenameUtils.getExtension(file.getOriginalFilename()).equals(
				"mp4") && FilenameUtils.getExtension(
				thumbnail.getOriginalFilename()).equals("jpg"))
				|| (FilenameUtils.getExtension(file.getOriginalFilename())
						.equals("mp4") && FilenameUtils.getExtension(
						thumbnail.getOriginalFilename()).equals("png"))
				|| (FilenameUtils.getExtension(file.getOriginalFilename())
						.equals("ogv") && FilenameUtils.getExtension(
						thumbnail.getOriginalFilename()).equals("jpg"))
				|| (FilenameUtils.getExtension(file.getOriginalFilename())
						.equals("ogv") && FilenameUtils.getExtension(
						thumbnail.getOriginalFilename()).equals("png"))) {
			DBVideoDAO videoJDBCTemplate = (DBVideoDAO) context
					.getBean("DBVideoDAO");
			Video video = new Video();
			// set amazon username and pass
			AWSCredentials credentials = new BasicAWSCredentials(
					"AKIAIDEAOQSKMINEQSVA",
					"o94Ozi37icf6+HoROskITlkAvdwRdphYXsPmrya4");
			AmazonS3Client s3client = new AmazonS3Client(credentials);

			// amazon folder
			String bucketName = "keep-calm-videos";
			s3client.createBucket(bucketName);

			File convFile = new File(file.hashCode()
					+ file.getOriginalFilename());
			File convThumbnail = new File(file.hashCode()
					+ thumbnail.getOriginalFilename());
			try {
				// converts to files in order to upload to amazon s3
				convFile.createNewFile();
				FileOutputStream fos = new FileOutputStream(convFile);
				fos.write(file.getBytes());
				fos.close();
			
			} catch (IOException e1) {
				System.out.println("File could not be created");
				e1.printStackTrace();
			}

			s3client.putObject(new PutObjectRequest(bucketName, convFile
					.getName(), convFile)
					.withCannedAcl(CannedAccessControlList.PublicRead));

			String videoPath = s3client.getResourceUrl(bucketName,
					convFile.getName());
			String thumbnailPath = s3client.getResourceUrl(bucketName,
					convThumbnail.getName());

			video.setTitle(title);
			video.setDescription(description);
			video.setPath(videoPath);
			video.setViews(0);
			video.setLikes(0);
			video.setDislikes(0);
			video.setThumbnail(thumbnail.getOriginalFilename());
			video.setUploadDate(Date.valueOf(LocalDate.now()));
			User user = (User) model.get("LoggedUser");
			video.setUploader((User) model.get("LoggedUser"));
			video.setCategory(category);
			final String filename = video.getPath();
			IContainer container = IContainer.make();
			int result = container.open(filename, IContainer.Type.READ, null);
			long duration = container.getDuration();
			video.setDuration(duration);
			videoJDBCTemplate.addVideo(video);
			mav.addObject(
					"message",
					((User) model.get("LoggedUser")).getChannelName()
							+ ",Вие успешно качихте клип, намиращ се в директорията "
							+ "path" + ", който е със заглавие: " + title
							+ " и описание: " + description + " на дата "
							+ video.getUploadDate());

		}
		else {
			System.out.println("You cannot proceed with the upload!!!!!!!!!!!!!!");
			System.out.println(FilenameUtils.getExtension(file.getOriginalFilename()));
			System.out.println(FilenameUtils.getExtension(thumbnail.getOriginalFilename()));
			mav.addObject("message", "Не можете да качите файл с този формат!");
		}
		return mav;
	}

}
