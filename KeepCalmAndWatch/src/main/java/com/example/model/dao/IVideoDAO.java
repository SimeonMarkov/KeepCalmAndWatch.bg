package com.example.model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.example.model.Video;

public interface IVideoDAO {

	public void setDataSource(DataSource ds);

	public boolean addVideo(Video video);
	
	Video getVideo(int video_id);

	List<Video> listVideos() throws SQLException;

}
