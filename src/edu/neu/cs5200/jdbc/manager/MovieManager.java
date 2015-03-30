package edu.neu.cs5200.jdbc.manager;

	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.neu.cs5200.jdbc.entity.Movie;

public class MovieManager {
	public Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("java:comp/env/jdbc/JDBChw");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	// create movie
	public void createMovie(Movie newMovie) throws SQLException{
		Connection connection = null;
		String sql = "insert into movie values (?,?,?,?)";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newMovie.getID());
			statement.setString(2, newMovie.getTitle());
			statement.setString(3, newMovie.getPosterImage());
			statement.setDate(4, (java.sql.Date) newMovie.getReleaseDate());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	
	// read all movies
	public List<Movie> readAllMovies() throws SQLException{
		List<Movie> movies = new ArrayList<Movie>();
		Connection connection = null;
		String sql = "select * from movie";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet results = statement.executeQuery(sql);
		    
		    while (results.next()){
		    	Movie movie = new Movie();
		    	movie.setID(results.getString("id"));
		    	movie.setPosterImage(results.getString("posterImage"));
		    	movie.setReleaseDate(results.getDate("releaseDate"));
		    	movie.setTitle(results.getString("title"));
		    	movies.add(movie);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return movies;
	}
	
	// read movie
	public Movie readMovie(String movieId) throws SQLException{
		Movie movie = new Movie();
		Connection connection = null;
		String sql = "select * from movie where id = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			ResultSet results = statement.executeQuery();
			 while (results.next()){
				 	movie.setID(results.getString("id"));
				    movie.setPosterImage(results.getString("posterImage"));
				    movie.setReleaseDate(results.getDate("releaseDate"));
				    movie.setTitle(results.getString("title"));
			    }
		   
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return movie;
		
	}
	
	// update movie
	public void updateMovie(String movieId, Movie movie) throws SQLException{
		Connection connection = null;
		String sql = "update Movie set title = ?, posterImage = ?, releaseDate = ? where id = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(4, movie.getID());
			statement.setString(2, movie.getPosterImage());
			statement.setDate (3, (java.sql.Date) movie.getReleaseDate());
			statement.setString(1, movie.getTitle());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		
	}
	
	//delete movie
	public void deleteMovie(String movieId) throws SQLException{
		Connection connection = null;
		String sql = "delete from Movie where id = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, movieId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	
}