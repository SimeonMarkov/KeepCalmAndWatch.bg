package com.keepcalmandwatch.model.dao;

import java.util.List;

import javax.sql.DataSource;

import com.keepcalmandwatch.model.Playlist;

public interface IPlaylistDAO {
	
	public boolean addPlaylist(Playlist playlist);

	void setDataSource(DataSource dataSource);

	List<Playlist> getAllPlaylistsForUser(String username);
}
