package com.keepcalmandwatch.model.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.keepcalmandwatch.model.Playlist;

public class DBPlaylistDAO implements IPlaylistDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean addPlaylist(Playlist playlist) {
		String query = "INSERT INTO keepcalmandwatch.playlists (title,users_username,playlist_id) VALUES (?,?,?)";
		jdbcTemplateObject.update(query, playlist.getTitle(), playlist.getUser().getUsername(), playlist.getId());
		return true;
	}
	
	@Override
	public List<Playlist> getAllPlaylistsForUser(String username){
		String query = "SELECT title,users_username,playlist_id FROM keepcalmandwatch.playlists WHERE users_username = ?";
		List<Playlist> playlists = jdbcTemplateObject.query(query,new Object[]{username}, new PlaylistMapper());
		System.out.println(playlists);
		return playlists;
	}

}
