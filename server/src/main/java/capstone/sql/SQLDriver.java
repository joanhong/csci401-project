package capstone.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Driver;

import capstone.algorithm.Project;
import capstone.user.User;



public class SQLDriver {
	private Connection con;
	private final static String confirmLoginAttempt ="SELECT COUNT(*) FROM 401_Platform.Users WHERE USERNAME=? AND PASSWORD=?";
	private final static String findIfUserExists = "SELECT COUNT(*) FROM 401_Platform.Users WHERE USERNAME=?";
	private final static String getUserID = "SELECT ID FROM 401_Platform.Users WHERE USERNAME=?";
	private final static String addUser = "INSERT INTO 401_Platform.Users(USERNAME,PASSWORD) VALUES(?,?)";
	private final static String getUsername = "SELECT USERNAME FROM 401_Platform.Users WHERE USER_ID=?";
	private final static String getAllUsers = "SELECT * FROM 401_Platform.Users";
	private final static String getAllProjects = "SELECT * FROM 401_Platform.Projects";

	private final static String addProjectEntry = "INSERT INTO 401_Platform.Projects(Project_id, Project_number, Project_name, Project_status, Max_size, Min_size) \n" + 
			"VALUES (?,?,?,?,?,?)";
	private final static String addProjectRankingEntry = "INSERT INTO 401_Platform.ProjectRankings(studentNumber, studentName, projectNumber, rank, projectName)\n" + 
			"VALUES (?,?,?,?,?)";
	
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
	
	public void addProjectEntry(int project_id, int project_number, String project_name, String project_status, int max_size, int min_size)
	{
		try
		{
			PreparedStatement ps = con.prepareStatement(addProjectEntry);
			ps.setInt(1, project_id);
			ps.setInt(2, project_number);
			ps.setString(3, project_name);
			ps.setString(4,  project_status);
			ps.setInt(5, max_size);
			ps.setInt(6, min_size);

			ps.executeUpdate();
			System.out.println("Added projectEntry to SQL!");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void addProjectRankingEntry(int studentNumber, String studentName, int projectNumber, int projectRank, String projectName)
	{
		try
		{
			PreparedStatement ps = con.prepareStatement(addProjectRankingEntry);
			ps.setInt(1, studentNumber);
			ps.setString(2, studentName);
			ps.setInt(3, projectNumber);
			ps.setInt(4,  projectRank);
			ps.setString(5, projectName);

			ps.executeUpdate();
			System.out.println("Added projectRankingEntry to SQL!");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Vector<Project> getProjectsTable()
	{
		String projectname = null;
		Vector<Project> returnVector = new Vector<Project>();
		try{
			PreparedStatement ps = con.prepareStatement(getAllProjects);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				Project p = new Project(result.getInt(1));
				p.setProjectNumber(result.getInt(2));
				p.setProjectName(result.getString("PROJECT_NAME"));
				p.setMaxSize(result.getInt(15));
				p.setMinSize(result.getInt(16));
				p.setStatus(result.getString("PROJECT_STATUS"));
				
				returnVector.addElement(p);	
			}		
		}catch (SQLException e){e.printStackTrace();}
		return returnVector; //returns true if the user name is in use in the DB
	}
	
}
