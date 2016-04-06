package com.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.jdbc.core.RowMapper;

public class CommentMapper implements RowMapper<Comment> {

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment = new Comment();
		comment.setId(rs.getInt("comments_id"));
		comment.setText(rs.getString("text"));
		comment.setDatetime(LocalDateTime.ofInstant(rs.getTimestamp("date").toInstant(),ZoneOffset.ofHours(0)));
		comment.setLikes(rs.getInt("likes"));
		comment.setDislikes(rs.getInt("dislikes"));
		Video video = new Video();
		video.setId(rs.getInt("videos_videos_id"));
		comment.setVideo(video);
		User user = new User();
		user.setUsername(rs.getString("users_username"));
		comment.setUser(user);
		return comment;
	}

}
