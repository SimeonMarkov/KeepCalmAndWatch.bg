package com.keepcalmandwatch.model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.keepcalmandwatch.model.Comment;
import com.keepcalmandwatch.model.Video;

public interface IVideoDAO {

	public void setDataSource(DataSource ds);

	public boolean addVideo(Video video) throws SQLException;
	
	Video getVideo(int video_id);

	List<Video> listVideos() throws SQLException;

	List<Video> getAllVideosLike(String title);

	List<Comment> getCommentsForSingleVideo(int videoId) throws SQLException;

	List<Video> getVideosForChannelName(String channelName);

}
