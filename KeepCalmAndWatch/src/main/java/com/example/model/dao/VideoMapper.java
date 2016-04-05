package com.example.model.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.User;
import com.example.model.Video;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class VideoMapper implements RowMapper<Video>{

	@SuppressWarnings("restriction")
	@Override
	public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
		Video video = new Video();
		video.setId(rs.getInt("videos_id"));
		video.setTitle(rs.getString("title"));
		video.setDescription(rs.getString("description"));
		video.setViews(rs.getInt("views"));
		video.setLikes(rs.getInt("likes"));
		video.setDislikes(rs.getInt("dislikes"));
		Blob blob = rs.getBlob("thumbnail");
		byte[] bdata = blob.getBytes(1, (int) blob.length());
		video.setThumbnail(Base64.encode(bdata));
		video.setUploadDate(rs.getDate("upload_date"));
		User user = new User();
		user.setUsername(rs.getString("users_username"));
		video.setUploader(user); //TODO: how to add the current logged user as an uploader
	    return video;
	}

}
