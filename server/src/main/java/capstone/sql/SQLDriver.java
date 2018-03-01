package capstone.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Driver;

import capstone.user.User;



public class SQLDriver {
	private Connection con;
	private final static String confirmLoginAttempt ="SELECT COUNT(*) FROM 401_Platform.Users WHERE USERNAME=? AND PASSWORD=?";
	private final static String findIfUserExists = "SELECT COUNT(*) FROM 401_Platform.Users WHERE USERNAME=?";
	private final static String getUserID = "SELECT ID FROM 401_Platform.Users WHERE USERNAME=?";
	private final static String addUser = "INSERT INTO 401_Platform.Users(USERNAME,PASSWORD) VALUES(?,?)";
	private final static String getUsername = "SELECT USERNAME FROM 401_Platform.Users WHERE USER_ID=?";
	private final static String getAllUsers = "SELECT * FROM 401_Platform.Users";
	
	public SQLDriver(){
		try{
			new Driver();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	private String getSQLPassword(){
		
		String password = "password";
		
		return password;
	}
	public void connect(){
		try{
			String DBpass = "jdbc:mysql://localhost:3306/401_Platform?user=root&password=";
			String password = getSQLPassword();
			DBpass+= password;
			con = DriverManager.getConnection(DBpass);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public int getUserID(String Username){
		int ID = 0;
		try{
			PreparedStatement ps = con.prepareStatement(getUserID);
			ps.setString(1,Username);
			ResultSet result = ps.executeQuery();			
			while(result.next()){
				ID = result.getInt(1);	
			}		
		}catch (SQLException e){e.printStackTrace();}
		return ID; //returns true if the user name is in use in the DB
	}
	public String getUsername(int userID){
		String username = null;
		try{
			PreparedStatement ps = con.prepareStatement(getUsername);
			ps.setInt(1,userID);
			ResultSet result = ps.executeQuery();			
			while(result.next()){
				username = result.getString(1);	
			}		
		}catch (SQLException e){e.printStackTrace();}
		return username; //returns true if the user name is in use in the DB
	}
	
	public Vector<User> getAllUsers()
	{
		String username = null;
		Vector<User> returnVector = new Vector<User>();
		try{
			PreparedStatement ps = con.prepareStatement(getAllUsers);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				User u = new User();
				u.setId(result.getInt(1));
				u.setUserNumber(result.getInt(2));
				u.setUserType(result.getString("USER_TYPE"));
				u.setFullName(result.getString("FULL_NAME"));
				u.setYear(result.getString("YEAR"));
				u.setEmail(result.getString("EMAIL"));
				//do more u.set for other columns in the table.
				
				returnVector.addElement(u);	
			}		
		}catch (SQLException e){e.printStackTrace();}
		return returnVector; //returns true if the user name is in use in the DB
	}
	
	
	
	public boolean doesExist(String Username){
		int count = 0;
		try{
			PreparedStatement ps = con.prepareStatement(findIfUserExists);
			ps.setString(1,Username);
			ResultSet result = ps.executeQuery();			
			while(result.next()){
				count = result.getInt(1);	
			}		
		}catch (SQLException e){e.printStackTrace();}
		return count == 1; //returns true if the user name is in use in the DB
	}

	public boolean confirmLoginAttempt(String Username, String Password){
		int count = 0;
		try{
			PreparedStatement ps = con.prepareStatement(confirmLoginAttempt);
			ps.setString(1,Username);
			ps.setString(2,Password);
			ResultSet result = ps.executeQuery();			
			while(result.next()){
				count = result.getInt(1);	
			}		
		}catch (SQLException e){e.printStackTrace();}
		return count == 1; //returns true if user is in the DB
	}
	public void registerUser(String Username, String Password){
		try{
			PreparedStatement ps = con.prepareStatement(addUser);
			ps.setString(1, Username);
			ps.setString(2, Password);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
