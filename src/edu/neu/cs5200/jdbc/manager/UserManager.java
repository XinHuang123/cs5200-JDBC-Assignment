package edu.neu.cs5200.jdbc.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import edu.neu.cs5200.jdbc.entity.User;

public class UserManager {
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
	// create a user
	public void createUser(User newUser) throws SQLException{
		Connection connection = null;
		String sql = "insert into user values (?,?,?,?,?,?)";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, newUser.getUserName());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getFirstName());
			statement.setString(4, newUser.getLastName());
			statement.setString(5, newUser.getEmail());
			statement.setDate(6, date);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	//read all users
	public List<User> readAllUsers() throws SQLException{
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		String sql = "select * from user";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
		    ResultSet results = statement.executeQuery(sql);
		    
		    while (results.next()){
		    	User user = new User();
		    	user.setUserName(results.getString("username"));
		    	user.setPassword(results.getString("password"));
		    	user.setFirstName(results.getString("firstname"));
		    	user.setLastName(results.getString("lastname"));
		    	user.setEmail(results.getString("email"));
		    	user.setDateOfBirth(results.getDate("dateofbirth"));
		    	users.add(user);
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return users;
	}
	//read a user
	public User readUser(String username) throws SQLException{
		User user = new User();
		Connection connection = null;
		String sql = "select * from user where username = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet results = statement.executeQuery();
			 while (results.next()){
				 	user.setUserName(results.getString("username"));
			    	user.setPassword(results.getString("password"));
			    	user.setFirstName(results.getString("firstname"));
			    	user.setLastName(results.getString("lastname"));
			    	user.setEmail(results.getString("email"));
			    	user.setDateOfBirth(results.getDate("dateofbirth"));
			    }
		   
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
		return user;
	}
	//update user
	public void updateUser(String username, User user) throws SQLException{
		Connection connection = null;
		String sql = "update User set password = ?, firstname = ?, lastname = ?, email = ? where username = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			statement.setDate(6, date);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}
	//delete a user
	public void deleteUser(String username) throws SQLException{
		Connection connection = null;
		String sql = "delete from User where username = ?";
		try{
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.execute();
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			connection.close();
		}
	}

}

