package com.example.model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

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
		String query = "select * from users where username = ?";
		User user = jdbcTemplateObject.queryForObject(query,
				new Object[] { username }, new UserMapper());
		return user;
	}

	
	@Override
	public List<User> listUsers() throws SQLException {
		String query = "select * from users";
	      List <User> users = jdbcTemplateObject.query(query, 
	                                new UserMapper());
	      return users;
	}

}
