package com.example.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.model.Comment;
import com.example.model.CommentMapper;
import com.example.model.User;

public class DBUserDAO implements IUserDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean addUser(User user) {
		String query = "INSERT INTO keepcalmandwatch.users (username, password, email, channel_name, description, avatar, registration_date, background) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";		
		user.setAvatar("https://s3.eu-central-1.amazonaws.com/keep-calm-avatars/default-avatar-ponsy-deer.jpg");
		user.setBackground("https://s3.eu-central-1.amazonaws.com/keep-calm-backgrounds/defaultbackground.jpg");
		jdbcTemplateObject.update(query, user.getUsername(), user.getPassword(), user.getEmail(), user.getChannelName(), user.getDescription(), user.getAvatar(), user.getRegistrationDate(), user.getBackground());
		return true;
	}
	
	@Override
	public boolean addComment(Comment comment){
		String query = "INSERT INTO keepcalmandwatch.comments (text, date, likes,dislikes,videos_videos_id,users_username) VALUES (?,?,?,?,?,?);";
		jdbcTemplateObject.update(query, comment.getText(), new Date(), comment.getLikes(), comment.getDislikes(), comment.getVideo().getId(), comment.getUser().getUsername());
		return true;
	}

	@Override
	public User getUser(String username) {
		if(!userExist(username)){
			return null;
		}
		String query = "select * from users where username = ?";
		User user = jdbcTemplateObject.queryForObject(query,
				new Object[] {username}, new UserMapper());
		return user;
	}
	
	@Override
	public User getUserBChannelName(String channelName){
		String query = "select * from users where channel_name = '" + channelName+ "';";
		User user = jdbcTemplateObject.queryForObject(query, new UserMapper());
		return user;
	}
	
	public List<User> getAllUsersByChannelName(String channelName){
		String query = "select * from users where channel_name like '%" + channelName +"%'";
		List<User> users = jdbcTemplateObject.query(query, new UserMapper());
		return users;
	}

	public User getUserByComment(int commentId){
		String query = "select username,password,email,channel_name,description,avatar,registration_date,background from users U inner join comments C on U.username = C.users_username where comments_id = " + commentId + ";";
		User user = jdbcTemplateObject.queryForObject(query, new UserMapper());
		return user;
	}
	
	@Override
	public List<User> listUsers() throws SQLException {
		String query = "select * from users";
	      List <User> users = jdbcTemplateObject.query(query, 
	                                new UserMapper());
	      return users;
	}

	public boolean userExist(String username){
		String query = "SELECT username FROM keepcalmandwatch.users WHERE username = ?";
			try {
				String s = jdbcTemplateObject.queryForObject(query, String.class, username);
			} catch (EmptyResultDataAccessException e) {
				return false;
			}
			return true;
	}
	
	public boolean channelExist(String channel){
		String query = "SELECT email FROM keepcalmandwatch.users WHERE email = ?;";
		try {
			String s = jdbcTemplateObject.queryForObject(query, String.class, channel);
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}

	public boolean emailExist(String email){
		String query = "SELECT channel_name FROM keepcalmandwatch.users WHERE channel_name = ?;";
		
		try {
			String s = jdbcTemplateObject.queryForObject(query, String.class, email);
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}
	
	public boolean updateUser(User user){
		String query = "UPDATE keepcalmandwatch.users SET password=?, email=?, channel_name=?, description=?, avatar=?, registration_date=?, background=? WHERE username = ?;";
		jdbcTemplateObject.update(query, user.getPassword(), user.getEmail(), user.getChannelName(), user.getDescription(), user.getAvatar(), user.getRegistrationDate(), user.getBackground(), user.getUsername());
		return true;
	}
}
