package com.keepcalmandwatch.model;
 

import java.time.LocalDateTime;
import java.util.*;

public class User {

	private String username;
	private String password;
	private String email;
	private String channelName;
	private String description;
	private String avatar;
	private TreeSet<Video> uploaded;
	private HashSet<User> subscribers;
	private HashSet<User> subscriptions;
	private TreeMap<Comment, LocalDateTime> comments;
	private Date registrationDate;
	private TreeMap<LocalDateTime, Video> history;
	private HashSet<Playlist> playlists;
	private Playlist favorites;
	private String background;
	
	//getters and setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public TreeSet<Video> getUploaded() {
		return uploaded;
	}

	public void setUploaded(TreeSet<Video> uploaded) {
		this.uploaded = uploaded;
	}

	public HashSet<User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(HashSet<User> subscribers) {
		this.subscribers = subscribers;
	}

	public HashSet<User> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(HashSet<User> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public TreeMap<Comment, LocalDateTime> getComments() {
		return comments;
	}

	public void setComments(TreeMap<Comment, LocalDateTime> comments) {
		this.comments = comments;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public TreeMap<LocalDateTime, Video> getHistory() {
		return history;
	}

	public void setHistory(TreeMap<LocalDateTime, Video> history) {
		this.history = history;
	}

	public HashSet<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(HashSet<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Playlist getFavorites() {
		return favorites;
	}

	public void setFavorites(Playlist favorites) {
		this.favorites = favorites;
	}

	public String getBackground() {
		return background;
	}
	
	public void setBackground(String background) {
		this.background = background;
	}


}
