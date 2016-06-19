package com.keepcalmandwatch.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.keepcalmandwatch.model.User;
import com.keepcalmandwatch.model.Video;

public class VideoMapper implements RowMapper<Video>{

	@Override
	public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
		Video video = new Video();
		video.setPath(rs.getString("path"));
		video.setId(rs.getInt("videos_id"));
		video.setTitle(rs.getString("title"));
		video.setDescription(rs.getString("description"));
		video.setViews(rs.getInt("views"));
		video.setLikes(rs.getInt("likes"));
		video.setDislikes(rs.getInt("dislikes"));
		video.setThumbnail(rs.getString("thumbnail"));
		video.setUploadDate(rs.getDate("upload_date"));
		User user = new User();
		user.setUsername(rs.getString("users_username"));
		video.setUploader(user);
	    return video;
	}

}
