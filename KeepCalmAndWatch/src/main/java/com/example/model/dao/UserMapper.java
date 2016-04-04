package com.example.model.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.model.User;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class UserMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setChannelName(rs.getString("channel_name"));
		user.setDescription(rs.getString("description"));
		Blob blob = rs.getBlob("avatar");
		byte[] bdata = blob.getBytes(1, (int) blob.length());
		user.setAvatar(Base64.encode(bdata));
		user.setRegistrationDate(rs.getDate("registration_date"));
	    return user;
	}

}
