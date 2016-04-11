package com.keepcalmandwatch.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.keepcalmandwatch.model.Playlist;
import com.keepcalmandwatch.model.User;

public class PlaylistMapper implements RowMapper<Playlist>{

	@Override
	public Playlist mapRow(ResultSet rs, int rowNum) throws SQLException {
		Playlist playlist = new Playlist();
		playlist.setTitle(rs.getString("title"));
		User user = new User();
		user.setUsername(rs.getString("users_username"));
		playlist.setUser(user);
		playlist.setId(rs.getInt("playlist_id"));
		return playlist;
	}

}
