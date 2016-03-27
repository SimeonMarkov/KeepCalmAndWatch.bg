package com.example.model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.model.Video;

public class DBVideoDAO implements IVideoDAO{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean addVideo(Video video) {
		 String query = "INSERT INTO videos (title, description, path, views, likes, dislikes, thumbnail, upload_date, users_username) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";        
		 jdbcTemplateObject.update(query, video.getTitle(), video.getDescription(), video.getPath(), 0, 0, 0, video.getThumbnail(), video.getUploadDate(), video.getUploader());
		 return true;

	}

	@Override
	public Video getVideo(int video_id) {
		String query = "select * from videos where videos_id = ?";
		Video video = jdbcTemplateObject.queryForObject(query,
				new Object[] { video_id }, new VideoMapper());
		return video;
	}

	@Override
	public List<Video> listVideos() throws SQLException {
		String query = "select * from videos";
	      List <Video> videos = jdbcTemplateObject.query(query, 
	                                new VideoMapper());
	      return videos;
	}

}
