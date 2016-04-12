package com.keepcalmandwatch.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.keepcalmandwatch.model.Playlist;
import com.keepcalmandwatch.model.User;

public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setChannelName(rs.getString("channel_name"));
		user.setDescription(rs.getString("description"));
		user.setAvatar(rs.getString("avatar"));
		user.setRegistrationDate(rs.getDate("registration_date"));
		user.setBackground(rs.getString("background"));
		return user;
	}

}
