package com.keepcalmandwatch.model;

import java.time.LocalDateTime;
import java.util.*;

public class Playlist {

	private int id;
	private String title;
	private User user;
	private TreeMap<LocalDateTime, Video> videos;
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public TreeMap<LocalDateTime, Video> getVideos() {
		return videos;
	}
	public void setVideos(TreeMap<LocalDateTime, Video> videos) {
		this.videos = videos;
	}
	
	
}
