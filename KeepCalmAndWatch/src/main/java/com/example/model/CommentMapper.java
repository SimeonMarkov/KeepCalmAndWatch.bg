package com.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;

import com.example.model.dao.DBUserDAO;

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
		System.out.println(comment.getId() + "----------------------------------");
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBUserDAO userDao = ((DBUserDAO)context.getBean("DBUserDAO"));
		User user = userDao.getUserByComment(comment.getId());
		comment.setUser(user);
		return comment;
	}

}
