package capstone.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Driver;

import capstone.model.PeerReviewData;
import capstone.model.Project;
import capstone.model.Student;
import capstone.model.User;
import capstone.model.WeeklyReportData;

public class SQLDriver {
	private final static String DATABASE_NAME = "401_Platform";
	private final static String PASSWORD = "";
	
	private Connection con;
	private final static String confirmLoginAttempt ="SELECT COUNT(*) FROM " + DATABASE_NAME + ".Users WHERE EMAIL=? AND PASSWORD=?";
	private final static String findIfUserExists = "SELECT COUNT(*) FROM " + DATABASE_NAME + ".Users WHERE EMAIL=?";
	private final static String getUserID = "SELECT ID FROM " + DATABASE_NAME + ".Users WHERE USERNAME=?";
	private final static String addUser = "INSERT INTO " + DATABASE_NAME + ".Users(USERNAME,PASSWORD) VALUES(?,?)";
	private final static String addUserEntry = "INSERT INTO " + DATABASE_NAME + ".Users(USER_TYPE, FIRST_NAME, LAST_NAME, PHONE_NUM, EMAIL, PASSWORD) VALUES(?,?,?,?,?,?)";
	private final static String getUsername = "SELECT USERNAME FROM " + DATABASE_NAME + ".Users WHERE USER_ID=?";
	private final static String getName = "SELECT FIRST_NAME, LAST_NAME FROM " + DATABASE_NAME + ".Users WHERE USER_ID=?";
	private final static String getAllUsers = "SELECT * FROM " + DATABASE_NAME + ".Users";
	private final static String getAllStakeholders = "SELECT * FROM " + DATABASE_NAME + ".Users JOIN " + DATABASE_NAME + ".StakeholderInfo ON stakeholder_id = user_id JOIN " + DATABASE_NAME + ".Organizations ON organization_id = org_id";
	private final static String getAllProjects = "SELECT * FROM " + DATABASE_NAME + ".Projects";
	private final static String getProjectByName = "SELECT * FROM " + DATABASE_NAME + ".Projects WHERE NAME=?";
	private final static String getUserProjectRankings = "SELECT * FROM " + DATABASE_NAME + ".ProjectRankings WHERE STUDENT_ID=? ORDER BY rank";
	private final static String updatePassword = "UPDATE " + DATABASE_NAME + ".USERS SET PASSWORD=? WHERE EMAIL=?";
	private final static String getEncryptedPassword = "SELECT * FROM " + DATABASE_NAME + ".USERS WHERE EMAIL=?";
	private final static String updateUserEntry = "UPDATE " + DATABASE_NAME + ".USERS SET first_name = ?, last_name = ?, email =?, user_type=? WHERE user_id=?";
	private final static String updateUserEntryWithEmail = "UPDATE " + DATABASE_NAME + ".USERS SET first_name = ?, last_name = ?, phone_num=? WHERE email=?";
	private final static String getRankingsCount = "SELECT COUNT(*) FROM " + DATABASE_NAME + ".ProjectRankings";
	private final static String updateApprovalStatus = "UPDATE " + DATABASE_NAME + ".Projects SET status_id=? WHERE Project_id=?";
	private final static String getUserByEmail = "SELECT * FROM " + DATABASE_NAME + ".Users WHERE EMAIL=?";	
	
//	private final static String addWeeklyReport = "INSERT INTO " + DATABASE_NAME + 
//			".WeeklyReportsTable(idWeeklyReportsTable, studentName, studentuscusername, projectNumber, date, "
//			+ "thisWeeksTasksD1,thisWeeksTasksD2, thisWeeksTasksD3, thisWeeksTasksD4, thisWeeksTasksD5, thisWeeksTasksD6, thisWeeksTasksD7, "
//			+ "thisWeeksTasksH1, thisWeeksTasksH2, thisWeeksTasksH3, thisWeeksTasksH4, thisWeeksTasksH5, thisWeeksTasksH6, thisWeeksTasksH7, "
//			+ "nextWeeksTasksD1, nextWeeksTasksD2, nextWeeksTasksD3, nextWeeksTasksD4, nextWeeksTasksD5, nextWeeksTasksD6, nextWeeksTasksD7,"
//			+ "nextWeeksTasksH1, nextWeeksTasksH2, nextWeeksTasksH3, nextWeeksTasksH4, nextWeeksTasksH5, nextWeeksTasksH6, nextWeeksTasksH7 ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

//	private final static String addPeerReview = "INSERT INTO " + DATABASE_NAME + 
//			".PeerReviewsTable(id, uscusername, uscidnumber, teammateaddress, teamcount, positivefeedback, negativefeedback)"
//			+ "VALUES(?,?,?,?,?,?,?)";
	
	private final static String addProjectEntry = "INSERT INTO " + DATABASE_NAME + ".Projects(project_name, status_id, min_size, max_size, technologies, background, description) \n" + 
			"VALUES (?,?,?,?,?,?,?)";
	private final static String addProjectRankingEntry = "INSERT INTO " + DATABASE_NAME + ".ProjectRankings(student_id, project_id, rank)\n" + 
			"VALUES (?,?,?)";
	
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
				u.setUserId(result.getInt(1));
				u.setUserTypeWithInt(result.getInt(2));
				u.setFirstName(result.getString("FIRST_NAME"));
				u.setLastName(result.getString("LAST_NAME"));
				u.setEmail(result.getString("EMAIL"));
				u.setPassword(result.getString("PASSWORD"));
				u.setPhone(result.getString("PHONE_NUM"));
				
				returnVector.addElement(u);	
			}		
		}catch (SQLException e){e.printStackTrace();}
		return returnVector; // returns true if the user name is in use in the DB
	}
	
	public Vector<User> getAllStakeholders()
	{
		Vector<User> returnVector = new Vector<User>();
		try{
			PreparedStatement ps = con.prepareStatement(getAllStakeholders);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				User u = new User();
				
				u.setUserId(result.getInt(1));
				u.setFirstName(result.getString("FIRST_NAME"));
				u.setLastName(result.getString("LAST_NAME"));
				u.setUserType("Stakeholder");
				u.setEmail(result.getString("EMAIL"));
//				u.setCompanyName(result.getString("ORGANIZATION"));
				
				returnVector.addElement(u);	
			}		
		}catch (SQLException e){e.printStackTrace();}
		return returnVector; //returns true if the user name is in use in the DB
	}
	
	public void addWeeklyReportEntry(WeeklyReportData weeklyreportdata) {
//		try {
//	        PreparedStatement ps = con.prepareStatement(addWeeklyReport);
//	        ps.setInt(1,  (int)weeklyreportdata.getId());
//	        ps.setString(2, weeklyreportdata.getName());
//	        ps.setString(3, weeklyreportdata.getUscusername());
//	        ps.setString(4,  weeklyreportdata.getProject());
//	        ps.setString(5, weeklyreportdata.getReportdate());
//	        ps.setString(6, weeklyreportdata.getLastWeekTasksD1());
//	        ps.setString(7, weeklyreportdata.getLastWeekTasksD2());
//	        ps.setString(8, weeklyreportdata.getLastWeekTasksD3());
//	        ps.setString(9, weeklyreportdata.getLastWeekTasksD4());
//	        ps.setString(10, weeklyreportdata.getLastWeekTasksD5());
//	        ps.setString(11, weeklyreportdata.getLastWeekTasksD6());
//	        ps.setString(12, weeklyreportdata.getLastWeekTasksD7());
//	        
//	        ps.setString(13, weeklyreportdata.getLastWeekTasksH1());
//	        ps.setString(14, weeklyreportdata.getLastWeekTasksH2());
//	        ps.setString(15, weeklyreportdata.getLastWeekTasksH3());
//	        ps.setString(16, weeklyreportdata.getLastWeekTasksH4());
//	        ps.setString(17, weeklyreportdata.getLastWeekTasksH5());
//	        ps.setString(18, weeklyreportdata.getLastWeekTasksH6());
//	        ps.setString(19, weeklyreportdata.getLastWeekTasksH7());
//	        
//	        ps.setString(20, weeklyreportdata.getNextWeekTasksD1());
//	        ps.setString(21, weeklyreportdata.getNextWeekTasksD2());
//	        ps.setString(22, weeklyreportdata.getLastWeekTasksD3());
//	        ps.setString(23, weeklyreportdata.getLastWeekTasksD4());
//	        ps.setString(24, weeklyreportdata.getLastWeekTasksD5());
//	        ps.setString(25, weeklyreportdata.getLastWeekTasksD6());
//	        ps.setString(26, weeklyreportdata.getLastWeekTasksD7());
//			ps.setString(27, weeklyreportdata.getLastWeekTasksH1());
//			ps.setString(28, weeklyreportdata.getLastWeekTasksH2());
//			ps.setString(29, weeklyreportdata.getLastWeekTasksH3());
//			ps.setString(30, weeklyreportdata.getLastWeekTasksH4());
//			ps.setString(31, weeklyreportdata.getLastWeekTasksH5());
//			ps.setString(32, weeklyreportdata.getLastWeekTasksH6());
//			ps.setString(33, weeklyreportdata.getLastWeekTasksH7());
//			
//			ps.executeUpdate();
//			System.out.println("Added WEEKLYREPORTENTRY to SQL DATABASE!");
//		} catch(SQLException e){
//		e.printStackTrace();
//		}
	}


	
	public Vector<Project> getAllProjects()
	{
		Vector<Project> returnVector = new Vector<Project>();
		try{
			PreparedStatement ps = con.prepareStatement(getAllProjects);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				Project u = new Project();
				u.setProjectId(result.getInt(1));
				u.setProjectName(result.getString("PROJECT_NAME"));
				u.setMaxSize(result.getInt(6));
				u.setMinSize(result.getInt(7));
				//do more u.set for other columns in the table.
				
				returnVector.addElement(u);	
			}		
		}catch (SQLException e){e.printStackTrace();}
		return returnVector; //returns true if the user name is in use in the DB
	}
	////
	
	
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
	
	public int getRankingsTableCount(){
		int count = 0;
		try{
			PreparedStatement ps = con.prepareStatement(getRankingsCount);
			ResultSet result = ps.executeQuery();			
			while(result.next()){
				count = result.getInt(1);	
			}		
		}catch (SQLException e){e.printStackTrace();}
		return count; //returns true if the user name is in use in the DB
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

	public void addUserEntry(int user_type, String first_name, String last_name, String phone, String email, String password){
		
		try{
			PreparedStatement ps = con.prepareStatement(addUserEntry);
			ps.setInt(1, user_type);
			ps.setString(2, first_name);
			ps.setString(3, last_name);
			ps.setString(4, phone);
			ps.setString(5, email);
			ps.setString(6, password);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void addPeerReviewEntry(PeerReviewData peerreviewdata)
	{
//		try
//		{
//			PreparedStatement ps = con.prepareStatement(addPeerReview);
//			ps.setInt(1,  (int)peerreviewdata.getId());
//			ps.setString(2, peerreviewdata.getUscusername());
//			ps.setString(3, peerreviewdata.getUscidnumber());
//			ps.setString(4,  peerreviewdata.getTeammateaddress());
//			ps.setString(5, peerreviewdata.getTeamcount());
//			ps.setString(6, peerreviewdata.getPositivefeedback());
//			ps.setString(7, peerreviewdata.getNegativefeedback());
//			
//			ps.executeUpdate();
//			System.out.println("Added PEERREVIEWENTRY to SQL DATABASE!");
//		}catch(SQLException e){
//			e.printStackTrace();
//		}
	}
		
	public void addProjectEntry(Project project)
	{		
		try
		{
			PreparedStatement ps = con.prepareStatement(addProjectEntry);
			ps.setString(1, project.getProjectName());
			ps.setInt(2, 1); // "pending approval"
			ps.setInt(3, project.getMinSize());
			ps.setInt(4, project.getMaxSize());
			ps.setString(5,  project.getTechnologiesExpected());
			ps.setString(6,  project.getBackgroundRequested());
			ps.setString(7,  project.getDescription());
			ps.executeUpdate();			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
	
	public void addProjectRankingEntry(int studentId,  int projectId, int projectRank)
	{
		try
		{
			PreparedStatement ps = con.prepareStatement(addProjectRankingEntry);
			ps.setInt(1, studentId);
			ps.setInt(2, projectId);
			ps.setInt(3,  projectRank);
			ps.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public Vector<Project> getProjectsTable()
	{
		Vector<Project> projects = new Vector<Project>();

		try{
			PreparedStatement ps = con.prepareStatement(getAllProjects);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				Project newProject = new Project(getStudentSatScore(1));
				
				newProject.setProjectId(result.getInt(1));
				newProject.setProjectName(result.getString("PROJECT_NAME"));
				newProject.setMaxSize(result.getInt(6));
				newProject.setMinSize(result.getInt(7));
				newProject.setStatusType(result.getString("STATUS_ID"));
				
				projects.addElement(newProject);				
			}		
		} catch (SQLException e) {e.printStackTrace();}
		
		return projects;
	}
	
	public User getUserByEmail(String email)
	{
		String username = null;
		User u = null;
		try{
			PreparedStatement ps = con.prepareStatement(getUserByEmail);
			ps.setString(1, email);
			ResultSet result = ps.executeQuery();
			while(result.next())
			{
				u = new User();
				u.setUserId(result.getInt(1));
				u.setUserType(result.getString("USER_TYPE"));
				u.setFirstName(result.getString("FIRST_NAME"));
				u.setLastName(result.getString("LAST_NAME"));
				u.setEmail(result.getString("EMAIL"));
				u.setPassword(result.getString("PASSWORD"));
				u.setPhone(result.getString("PHONE_NUM"));
			}		
		}catch (SQLException e){e.printStackTrace();}
		return u; // returns true if the user name is in use in the DB
	}
	
	// returns vector of all Students and their rankings
	// requires vector of existing Projects
	public Vector<Student> getUsersWithRankings(Vector<Project> projects, int numStudents)
	{ 
		Vector<Student> students = new Vector<Student>();
		
		for (int studentId = 1; studentId <= numStudents; studentId++) { // for each student

			Student newStudent = new Student();
			newStudent.setUserId(studentId);
			newStudent.setStudentId(studentId); // TODO
			
			try {		
	    			
	    			PreparedStatement ps = con.prepareStatement(getUserProjectRankings);
	    			ps.setInt(1, studentId);
	    			ResultSet result = ps.executeQuery();
	    			
	    			PreparedStatement ps2 = con.prepareStatement(getName);
	    			ps2.setInt(1, studentId);
	    			ResultSet result2 = ps2.executeQuery();
	    			while (result2.next()) {
		    			newStudent.setFirstName(result2.getString("FIRST_NAME"));
		    			newStudent.setLastName(result2.getString("LAST_NAME"));
	    			}
	    			
	    			while(result.next()) { // for each ranking...
	                
	            		int projectId = result.getInt(2);
	            		int rank = result.getInt(3);
	            		
	            		// add rankedProject:
            			Project rankedProject = projects.elementAt(projectId - 1); // !!! SUBTRACT 1, as the ranking's indices skip 0 for readability
	            		String projectName = rankedProject.getProjectName();
	            		newStudent.rankings.put(projectName, rank);
	            		newStudent.orderedRankings.addElement(projectName);				
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
	
	public void updateProjectStatus(String projectName, int projectStatus)
	{
		try
		{
			PreparedStatement ps2 = con.prepareStatement(getProjectByName);
			ps2.setString(1, projectName);
			ResultSet result = ps2.executeQuery();
			int projectId = result.getInt(1);
			
			PreparedStatement ps = con.prepareStatement(updateApprovalStatus);
			ps.setInt(1, projectStatus);
			ps.setInt(2, projectId);
			ps.executeUpdate();
			System.out.println("Approved project - "+ projectName);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	///
	public void addUserInfoUpdate(User userdata)
	{
		//UPDATE 401_Platform.USERS SET Full_name = ?, year = ?, email =?, user_type=? WHERE Full_name=?;
		//If name was updated then how will you find it? BUG
		try
		{
			PreparedStatement ps = con.prepareStatement(updateUserEntry);
			ps.setString(1, userdata.getFirstName());
			ps.setString(2, userdata.getLastName());
			ps.setString(3, userdata.getEmail());
			ps.setInt(4, userdata.getUserTypeWithInt());
			ps.setInt(5, userdata.getUserId());
			ps.executeUpdate();
			
			System.out.println("Updated info for user: "+ userdata.getFirstName() + " " + userdata.getLastName());
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	///
	
	public void updateUserProfile(User userdata)
	{
		//UPDATE 401_Platform.USERS SET Full_name = ?, year = ?, email =?, user_type=? WHERE Full_name=?;
		//If name was updated then how will you find it? BUG
		try
		{
			PreparedStatement ps = con.prepareStatement(updateUserEntryWithEmail);
			ps.setString(1, userdata.getFirstName());
			ps.setString(2, userdata.getLastName());
			ps.setString(3, userdata.getPhone());
			ps.setString(4, userdata.getEmail());
			ps.executeUpdate();
			
			System.out.println("Updated info for user: "+ userdata.getFirstName() + " " + userdata.getLastName());
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
