package edu.neu.cs5200.jdbc.manager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.neu.cs5200.jdbc.entity.Actor;

public class ActorManager {
	public Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("java:comp/env/jdbc/JDBChw");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	// create actor
	public void createActor(Actor newActor) throws SQLException{
		Connection connection = null;
		String sql = "insert into actor values (?,?,?,?)";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newActor.getID());
			statement.setString(2, newActor.getFirstName());
			statement.setString(3, newActor.getLastName());
			statement.setDate(4, (Date) newActor.getDateOfBirth());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	
	//read all actors
	public List<Actor> readAllActors() throws SQLException{
		List<Actor> actors = new ArrayList<Actor>();
		Connection connection = null;
		String sql = "select * from actor";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()){
		    	Actor actor = new Actor();
		    	actor.setID(rs.getString("id")); 
		    	actor.setFirstName(rs.getString("firstname"));
		    	actor.setLastName(rs.getString("lastname"));
		    	actor.setDateOfBirth(rs.getDate("dateofbirth"));
		    	actors.add(actor);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return actors;
	}
	
	// read actor
	public Actor readActor(String actorId) throws SQLException{
		Actor actor = new Actor();
		Connection connection = null;
		String sql = "select * from actor where ID = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
			ResultSet rs = statement.executeQuery();
			 while (rs.next()){
				 actor.setID(rs.getString("id")); 
				 actor.setFirstName(rs.getString("firstname"));
				 actor.setLastName(rs.getString("lastname"));
				 actor.setDateOfBirth(rs.getDate("dateofbirth"));
			    }   
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return actor;
	}
	
	// update actor
	public void updateActor(String actorId, Actor actor) throws SQLException{
		Connection connection = null;
		String sql = "update Actor set ID = ?, firstname = ?, lastname = ?, dateofbirth = ? where id = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actor.getID());
			statement.setString(2, actor.getFirstName());
			statement.setString(3, actor.getLastName());
			statement.setDate(4, (Date) actor.getDateOfBirth());
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	
	// delete actor
	public void deleteActor(String actorId) throws SQLException{
		Connection connection = null;
		String sql = "delete from Actor where id = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, actorId);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}

}