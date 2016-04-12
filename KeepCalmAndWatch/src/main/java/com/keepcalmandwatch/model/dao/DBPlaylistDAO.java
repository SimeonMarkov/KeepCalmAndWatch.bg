package com.keepcalmandwatch.model.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.neo4j.cypher.internal.compiler.v2_1.docbuilders.queryGraphDocBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.keepcalmandwatch.model.Playlist;
import com.keepcalmandwatch.model.User;
import com.keepcalmandwatch.model.Video;

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
		String query = "INSERT INTO keepcalmandwatch.playlists (title, users_username) VALUES (?,?)";
		jdbcTemplateObject.update(query, playlist.getTitle(), playlist.getUser().getUsername());
		return true;
	}
	
	@Override
	public List<Playlist> getAllPlaylistsForUser(String username){
		String query = "SELECT title,users_username,playlist_id FROM keepcalmandwatch.playlists WHERE users_username = ?";
		List<Playlist> playlists = jdbcTemplateObject.query(query,new Object[]{username}, new PlaylistMapper());
		System.out.println(playlists);
		return playlists;
	}

	public boolean createFavorites(User user){
		if(!favoritesExists(user)){
			String query = "INSERT INTO playlists (title, users_username) VALUES ('Favorites', ?);";
			jdbcTemplateObject.update(query, user.getUsername());
		}
		return true;
	}
	
	public boolean favoritesExists(User user){
		String query = "SELECT playlist_id FROM playlists WHERE title='Favorites' AND users_username = ?;";
		try {
			Integer i = jdbcTemplateObject.queryForObject(query, Integer.class, user.getUsername());
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		return true;
	}
	
	public Playlist getFavorites(User user){
		if(!favoritesExists(user)){
			createFavorites(user);
		}
		String query = "SELECT title, users_username, playlist_id FROM keepcalmandwatch.playlists WHERE title='Favorites' AND users_username = ?";
		Playlist favorites = jdbcTemplateObject.queryForObject(query, new Object[]{user.getUsername()}, new PlaylistMapper());
		return favorites;
	}
	
	public boolean addToFavorites(Playlist favorites, int videoID){
		String query = "INSERT INTO playlists_to_videos (date_added, playlist_id, videos_id) VALUES (?, ?, ?);";
		jdbcTemplateObject.update(query, Date.valueOf(LocalDate.now()), favorites.getId(), videoID);
		return true;
	}
	
	public boolean removeFromFavorites(Playlist favorites, int videoID){
		String query = "DELETE FROM playlists_to_videos WHERE playlist_id=? AND videos_id=?;";
		jdbcTemplateObject.update(query, favorites.getId(), videoID);
		return true;
	}
	
	public List<Video> getFavoriteVideos(Playlist favorites){
		String query = "SELECT videos_id FROM keepcalmandwatch.playlists_to_videos WHERE playlist_id = ?;";
		List<Integer> videoIDs = jdbcTemplateObject.queryForList(query, Integer.class, favorites.getId());
		List<Video> videos = new ArrayList<Video>();
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		DBVideoDAO videoDao = (DBVideoDAO) context.getBean("DBVideoDAO");
		for(Integer i : videoIDs){
			videos.add(videoDao.getVideo(i));
		}
		return videos;
	}
}
