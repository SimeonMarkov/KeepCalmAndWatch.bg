package com.example.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.User;
import com.example.model.Video;

public class VideoMapper implements RowMapper<Video>{

	@Override
	public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
		Video video = new Video();
		video.setTitle(rs.getString("title"));
		video.setDescription(rs.getString("description"));
		video.setViews(0);
		video.setLikes(0);
		video.setDislikes(0);
		video.setThumbnail(rs.getBytes("thumbnail"));
		video.setUploadDate(rs.getDate("upload_date"));
		User user = new User();
		user.setUsername(rs.getString("users_username"));
		video.setUploader(user); //TODO: how to add the current logged user as an uploader
	    return video;
	}

}
