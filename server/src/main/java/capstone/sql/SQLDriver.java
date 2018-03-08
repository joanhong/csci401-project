package capstone.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Driver;

import capstone.model.Project;
import capstone.model.Student;
import capstone.model.User;

public class SQLDriver {
	private final static String DATABASE_NAME = "401_platform";
	private final static String PASSWORD = "";
	
	private Connection con;
	private final static String confirmLoginAttempt ="SELECT COUNT(*) FROM " + DATABASE_NAME + ".Users WHERE EMAIL=? AND PASSWORD=?";
	private final static String findIfUserExists = "SELECT COUNT(*) FROM " + DATABASE_NAME + ".Users WHERE EMAIL=?";
	private final static String getUserID = "SELECT ID FROM " + DATABASE_NAME + ".Users WHERE USERNAME=?";
	private final static String addUser = "INSERT INTO " + DATABASE_NAME + ".Users(USERNAME,PASSWORD) VALUES(?,?)";
	private final static String getUsername = "SELECT USERNAME FROM " + DATABASE_NAME + ".Users WHERE USER_ID=?";
	private final static String getAllUsers = "SELECT * FROM " + DATABASE_NAME + ".Users";
	private final static String getAllProjects = "SELECT * FROM " + DATABASE_NAME + ".Projects";
	private final static String getUserProjectRankings = "SELECT * FROM " + DATABASE_NAME + ".ProjectRankings WHERE STUDENTNUMBER=?";
	private final static String updatePassword = "UPDATE " + DATABASE_NAME + ".USERS SET PASSWORD=? WHERE EMAIL=?";
	private final static String getEncryptedPassword = "SELECT * FROM 401_Platform.USERS WHERE EMAIL=?";
	
	private final static String addProjectEntry = "INSERT INTO " + DATABASE_NAME + ".Projects(Project_id, Project_number, Project_name, Project_status, Max_size, Min_size) \n" + 
			"VALUES (?,?,?,?,?,?)";
	private final static String addProjectRankingEntry = "INSERT INTO " + DATABASE_NAME + ".ProjectRankings(studentNumber, studentName, projectNumber, rank, projectName)\n" + 
			"VALUES (?,?,?,?,?)";
	
	private static int NUM_RANKED;
	
	public SQLDriver(int _NUM_RANKED){
		
		NUM_RANKED = _NUM_RANKED;

		try{
			new Driver();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	private String getSQLPassword(){
		return PASSWORD;
	}
	public void connect(){
		try{
			String DBpass = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?user=root&password=";
			String password = getSQLPassword();
			DBpass+= password;
			con = DriverManager.getConnection(DBpass);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static int getStudentSatScore(int i) { // i = project's rank
		return ( ( (NUM_RANKED-i+1) * (NUM_RANKED-i)) / 2 ) + 1;
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
				u.setPassword(result.getString("PASSWORD"));
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
		Vector<Project> returnVector = new Vector<Project>();
		try{
			PreparedStatement ps = con.prepareStatement(getAllProjects);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				Project p = new Project(getStudentSatScore(1));
				
				p.setProjectId(result.getInt(1));
				p.setProjectNumber(result.getInt(2));
				p.setProjectName(result.getString("PROJECT_NAME"));
				p.setName(result.getString("PROJECT_NAME"));
				p.setMaxSize(result.getInt(15));
				p.setMinSize(result.getInt(16));
				p.setStatus(result.getString("PROJECT_STATUS"));
				
				returnVector.addElement(p);				
			}		
		}catch (SQLException e){e.printStackTrace();}
		return returnVector; //returns true if the user name is in use in the DB
	}
	
	// returns vector of all Students and their rankings
	// requires vector of existing Projects
	public Vector<Student> getUsersWithRankings(Vector<Project> projects, int numStudents)
	{ 
		Vector<Student> students = new Vector<Student>();
		
		for (int studentId = 1; studentId <= numStudents; studentId++) { // for each student

			Student newStudent = new Student();
			newStudent.studentId = studentId;
			
			try {		
	    			
	    			PreparedStatement ps = con.prepareStatement(getUserProjectRankings);
	    			ps.setInt(1, studentId);
	    			ResultSet result = ps.executeQuery();
	    			
	    			while(result.next()) { // for each ranking...
	                
		    			newStudent.name = result.getString("STUDENTNAME");
	            		int projectId = result.getInt(3);
	            		int rank = result.getInt(4);
	            		
	            		// add rankedProject:
            			Project rankedProject = projects.elementAt(projectId - 1); // !!! SUBTRACT 1, as the ranking's indices skip 0 for readability
	            		String projectName = rankedProject.getName();
	            		newStudent.rankings.put(projectName, rank);
	            		newStudent.orderedRankings.addElement(projectName);
				
					students.addElement(newStudent);
				}
	    			
			} catch (SQLException e){e.printStackTrace();}
			
			students.addElement(newStudent);
		}	
		
		return students;
	}
	
	public void updatePassword(String email, String password)
	{
		try
		{
			PreparedStatement ps = con.prepareStatement(updatePassword);
			ps.setString(1, password);
			ps.setString(2, email);
			ps.executeUpdate();
			System.out.println("Updated password for user: "+ email);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public String getEncryptedPassword(String email)
	{
			try{
				PreparedStatement ps = con.prepareStatement(getEncryptedPassword);
				ps.setString(1,email);
				ResultSet result = ps.executeQuery();			
				while(result.next()){
					return result.getString("PASSWORD");	
				}		
			}catch (SQLException e){e.printStackTrace();}
			return null;
	}
	
}
