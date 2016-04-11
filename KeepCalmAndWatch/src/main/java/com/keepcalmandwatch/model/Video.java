package com.keepcalmandwatch.model;

import java.sql.Date;
import java.time.LocalDate;

public class Video implements Comparable<Video>{

	private int id;
	private String path;
	private String title;
	private String description;
	private int views;
	private int likes;
	private int dislikes;
	private User uploader;
	private String thumbnail;
	private Date uplodadDate;
	private String category;
	private double duration;
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if(description.isEmpty()){
			this.description = "No description is set...";
		}
		else{
			this.description = description;
		}
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	public User getUploader() {
		return uploader;
	}
	public void setUploader(User uploader) {
		this.uploader = uploader;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Date getUploadDate() {
		return uplodadDate;
	}
	public void setUploadDate(Date uplodadDate) {
		this.uplodadDate = uplodadDate;
	}


	@Override
	public int compareTo(Video o) {
		if(this.id == o.id){
			return 0;
		} else{
			return this.uplodadDate.compareTo(o.uplodadDate);
		}
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object video) {
		return this.id == ((Video) video).id;
	}
	
}
