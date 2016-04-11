package com.keepcalmandwatch.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.keepcalmandwatch.model.Comment;
import com.keepcalmandwatch.model.Video;
import com.xuggle.xuggler.IContainer;

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
		 String query = "INSERT INTO videos (title, description, path, views, likes, dislikes, thumbnail, upload_date, users_username, category, duration) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";        
		 IContainer container = IContainer.make();
		 int result = container.open(video.getPath(), IContainer.Type.READ, null);
		 long duration = container.getDuration();
		 
		 jdbcTemplateObject.update(query, video.getTitle(), video.getDescription(), video.getPath(), 0, 0, 0, video.getThumbnail(), video.getUploadDate(), video.getUploader().getUsername(), video.getCategory(), duration);
		 return true;

	}

	@Override
	public Video getVideo(int video_id){
		String query = "select videos_id, title, description, path, views, likes, dislikes, thumbnail, upload_date, users_username, category, duration from videos where videos_id = ?";
		Video video = null;
		video = jdbcTemplateObject.queryForObject(query,
		new Object[] { video_id }, new VideoMapper());
		return video;
	}
	
	public List<Video> getVideosByCategory(String category){
		System.out.println(category);
		String query = "select videos_id, title, description, path, views, likes, dislikes, thumbnail, upload_date, users_username, category, duration from videos where category = ?";
		List<Video> videos = jdbcTemplateObject.query(query, new Object[]{category}, new VideoMapper());
		return videos;
	}
	
	@Override
	public List<Video> getVideosForChannelName(String channelName){
		String query = "select videos_id,title,V.description,path,views,likes,dislikes,thumbnail,upload_date,users_username,category,duration from videos V inner join users U on V.users_username = U.username where channel_name = '" + channelName + "';";
		List<Video> videos = jdbcTemplateObject.query(query, new VideoMapper());
		return videos;
	}

	@Override
	public List<Video> getAllVideosLike(String videoTitle){
		String query = "select * from videos where title like '%" + videoTitle + "%'";
		List<Video> videos = jdbcTemplateObject.query(query, new VideoMapper());
		return videos;
	}
	@Override
	public List<Video> listVideos() throws SQLException {
		String query = "select * from videos";
	      List <Video> videos = jdbcTemplateObject.query(query, 
	                                new VideoMapper());
	      return videos;
	}
	
	@Override
	public List<Comment> getCommentsForSingleVideo(int videoId){
		String query = "select comments_id,text,date,C.likes,C.dislikes,videos_videos_id,C.users_username from comments C inner join videos V on C.videos_videos_id = V.videos_id where C.videos_videos_id = " + videoId + " order by date desc;";
		List<Comment> comments = jdbcTemplateObject.query(query, new CommentMapper());
		return comments;
	}
}
