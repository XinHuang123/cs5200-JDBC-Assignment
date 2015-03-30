package edu.neu.cs5200.jdbc.manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.neu.cs5200.jdbc.entity.Comment;


public class CommentManager {
	static java.util.Date today = new java.util.Date();
	static java.sql.Date date = new java.sql.Date(today.getTime());
	public Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("java:comp/env/jdbc/JDBChw");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//create comment
	public void createComment(Comment newComment) throws SQLException {
		Connection connection = null;
		String sql = "insert into comment values (?,?,?,?)";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment.getComment());
			statement.setString(2, newComment.getMovieID());
			statement.setString(3, newComment.getUserID());
			statement.setDate(4, (Date) newComment.getDate());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	
	//read all comments
	public List<Comment> readAllComments() throws SQLException {
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = null;
		String sql = "select * from comment";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet results = statement.executeQuery(sql);
		    
		    while (results.next()){
		    	Comment comment = new Comment();
		    	comment.setComment(results.getString("comment")); 
		    	comment.setMovieID(results.getString("movieid"));
		    	comment.setUserID(results.getString("userid"));
		    	comment.setDate(results.getDate("date"));
		    	comments.add(comment);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return comments;
	}
	
	//read all comments for username
	public List<Comment> readAllCommentsForUsername(String username) throws SQLException {
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = null;
		String sql = "select * from comment where userid = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
		    ResultSet results = statement.executeQuery(sql);
		    
		    while (results.next()){
		    	Comment comment = new Comment();
		    	comment.setComment(results.getString("comment")); 
		    	comment.setMovieID(results.getString("movieid"));
		    	comment.setUserID(results.getString("userid"));
		    	comment.setDate(results.getDate("date"));
		    	comments.add(comment);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return comments;
	}
	
	//read all comment for movie
	public List<Comment> readAllCommentsForMovie(String movieId) throws SQLException {
		List<Comment> comments = new ArrayList<Comment>();
		Connection connection = null;
		String sql = "select * from comment where movieid = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
		    ResultSet results = statement.executeQuery(sql);
		    
		    while (results.next()){
		    	Comment comment = new Comment();
		    	comment.setComment(results.getString("comment")); 
		    	comment.setMovieID(results.getString("movieid"));
		    	comment.setUserID(results.getString("userid"));
		    	comment.setDate(results.getDate("date"));
		    	comments.add(comment);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return comments;
	}
	public Comment readCommentForId(String commentId) throws SQLException {
		Comment comment = new Comment();
		Connection connection = null;
		String sql = "select * from comment where comment = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			ResultSet results = statement.executeQuery();
			 while (results.next()){
				 comment.setComment(results.getString("comment")); 
				 comment.setMovieID(results.getString("movieid"));
				 comment.setUserID(results.getString("userid"));
				 comment.setDate(results.getDate("date"));
			    }   
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return comment;
	}
	
	//update comment
	public void updateComment(String commentId, String newComment) throws SQLException {
		Connection connection = null;
		String sql = "update Comment set comment = ?, date = ? where comment = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newComment);
			statement.setDate(2, date);
			statement.setString(3, commentId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	
	//delete comment
	public void deleteComment(String commentId) throws SQLException {
		Connection connection = null;
		String sql = "delete from Comment where comment = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, commentId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
}