package com.example.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

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
		
		String query = "INSERT INTO keepcalmandwatch.users (username, password, email, channel_name, description, avatar, registration_date) VALUES(?, ?, ?, ?, ?, ?, ?);";		
		jdbcTemplateObject.update(query, user.getUsername(), user.getPassword(), user.getEmail(), user.getChannelName(), user.getDescription(), user.getAvatar(), user.getRegistrationDate());
		return true;
	}

	@Override
	public User getUser(String username) {
		if(!userExist(username)){
			return null;
		}
		String query = "select * from users where username = ?";
		User user = jdbcTemplateObject.queryForObject(query,
				new Object[] { username }, new UserMapper());
		return user;
	}
	
	public List<User> getAllUsersByChannelName(String channelName){
		String query = "select * from users where channel_name like '%" + channelName +"%'";
		List<User> users = jdbcTemplateObject.query(query, new UserMapper());
		return users;
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
}
