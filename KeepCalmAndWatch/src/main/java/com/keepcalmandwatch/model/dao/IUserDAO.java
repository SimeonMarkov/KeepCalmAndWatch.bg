package com.keepcalmandwatch.model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.keepcalmandwatch.model.Comment;
import com.keepcalmandwatch.model.User;

public interface IUserDAO {

	public void setDataSource(DataSource ds);

	public boolean addUser(User user);
	
	User getUser(String username);

	List<User> listUsers() throws SQLException;

	User getUserBChannelName(String channelName);

	boolean addComment(Comment comment);

}
