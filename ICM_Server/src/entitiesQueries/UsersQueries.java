package entitiesQueries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.SqlConnection;
import entities.DBMessage;
import entities.DBMessage.DBAction;
import ocsf.server.ConnectionToClient;
import entities.ItEmployee;
import entities.Request;
import entities.User;
import entities.UserList;

/**
 * This class hold all the queries for users
 * And the creation of users entities from ResaultSet
 *
 */
public class UsersQueries 
{
	
	/**
	 * Check if user exist.
	 *
	 * @param userToCheck the user to check
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage checkIfUserExist(User userToCheck,SqlConnection icmDB)
	{
		String query = UsersQueries.searchUserByUserNameAndPass(userToCheck); 
		ResultSet rs = icmDB.executeQuery(query);
		DBMessage returnMsg;
		int rowsNumber = getRowCount(rs);
		//Check if the user exist in the DB
		if (rowsNumber == 1)
		{
			try
			{
				rs.next();
			} catch (SQLException e)
			{
				e.printStackTrace();
				return null;
			}
			// if exist - create it
			User user = UsersQueries.CreateUserFromRS(rs);
			if (user.getLoginStatus().equals("on"))//check if he is logged already
			{
				user = new User(null, null);
				return new DBMessage(DBAction.CheckUser, user);// This is the message for logged already user.
			} else// if the user exist AND is not logged in -> connect him! 
			{
				user.setLoginStatus("on");
				query = UsersQueries.updateUserloginStatus(user);
				icmDB.executeUpdate(query);
				returnMsg = new DBMessage(DBAction.CheckUser, user);// This is the message for valid user.
			}
			return returnMsg;
		} else
		{
			returnMsg = new DBMessage(DBAction.CheckUser, null);// This is the message for non-existing user.
			return returnMsg;
		}
	}

	
	/**
	 * Search user by user name and pass.
	 *
	 * @param userToCheck the user to check
	 * @return the string
	 */
	private static String searchUserByUserNameAndPass(User userToCheck) 
	{
		if (userToCheck == null)
			return null;
		String queryMsg = "SELECT * FROM icm_db.users WHERE userName ='" + userToCheck.getUserName() + "' AND password ='" + userToCheck.getPassword() + "'";
		return queryMsg;
	}
	
	/**
	 * Gets the row count.
	 *
	 * @param resultSet the result set
	 * @return the row count
	 */
	private static int getRowCount(ResultSet resultSet)
	{
		if (resultSet == null)
		{
			return 0;
		}
		try
		{
			resultSet.last();
			return resultSet.getRow();
		} 
		catch (SQLException exp)
		{
			exp.printStackTrace();
		} 
		finally
		{
			try
			{
				resultSet.beforeFirst();
			} 
			catch (SQLException exp)
			{
				exp.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * Create a new user object with the data we received from the database.
	 *
	 * @param rs The ResultSet returned from the database with the user's data
	 * @return Object of the logged in user
	 */
	public static User CreateUserFromRS(ResultSet rs)
	{
		User userToCreate = null;
		try
		{
			userToCreate = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));

		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return userToCreate;
	}
	
	/**
	 * Update userlogin status.
	 *
	 * @param userToUpdate the user to update
	 * @return the string
	 */
	public static String updateUserloginStatus(User userToUpdate)
	{
		if (userToUpdate == null)
			return null;
		String queryMsg = "UPDATE icm_db.users SET loginStatus = '"+userToUpdate.getLoginStatus()+"' WHERE idNumber = '"+userToUpdate.getId()+"'";
		return queryMsg;
	}
	
	/**
	 * Gets the messages for user.
	 *
	 * @param user the user
	 * @return the messages for user
	 */
	public static String getMessagesForUser(User user)
	{
		String queryMsg ="SELECT * FROM icm_db.messages WHERE (recipientUserType ='"+user.getType()+"' AND recipientUserId IS NULL ) "+ "OR recipientUserId ='"+user.getId()+"'";
		return queryMsg;
	}
	
	/**
	 * Creates the user from string.
	 *
	 * @param stageManager the stage manager
	 * @param icmDB the icm DB
	 * @return the user
	 */
	//this function returns an itemployee -getting its id 
	public static User CreateUserFromString(String stageManager,SqlConnection icmDB)
	{
		User userToCreate = new User();
		if(stageManager!="0")
		{
		Integer id=Integer.parseInt(stageManager);
		String query ="SELECT * FROM icm_db.users WHERE idNumber ='"+(int)id+"'" ; 
		ResultSet rs = icmDB.executeQuery(query);
		try
		{
			if(rs.next())
			userToCreate = new User(rs.getString(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10),rs.getBoolean(11),rs.getBoolean(12),rs.getBoolean(13),rs.getBoolean(14),rs.getBoolean(15),rs.getBoolean(16));
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		}
		return userToCreate;
	
	}
	
	/**
	 * Gets the emploeey by his role.
	 *
	 * @param role the role
	 * @param icmDB the icm DB
	 * @return the emploeey by his role
	 */
	//this function returns an itemployee -getting its id 
	public static User getEmploeeyByHisRole(String role,SqlConnection icmDB)
	{
		User uniqueEmloyee = new User();
		String query ="SELECT * FROM icm_db.users WHERE userType ='"+role+"'" ; 
		ResultSet rs = icmDB.executeQuery(query);
		try
		{
			if(rs.next())
			uniqueEmloyee = new User(rs.getString(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10),rs.getBoolean(11),rs.getBoolean(12),rs.getBoolean(13),rs.getBoolean(14),rs.getBoolean(15),rs.getBoolean(16));
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return uniqueEmloyee;
	}
	
	/**
	 * Gets the it employees.
	 *
	 * @param user the user
	 * @param icmDB the icm DB
	 * @return the it employees
	 */
	//this function returns an itemployee -getting its id 
	public static DBMessage getItEmployees(User user,SqlConnection icmDB) {
		String id=user.getId();
	//	System.out.println("1"+system);
		String query ="SELECT * FROM icm_db.users WHERE idNumber!='"+id+"' AND userType!='generalUser' " ; 
		UserList list=new UserList();
		list.user_list=new ArrayList<User>();
		DBMessage returnMsg;
		ResultSet rs = icmDB.executeQuery(query);
		try {
		//	System.out.println("2"+system);
			while(rs.next()) {
				list.user_list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10),rs.getBoolean(11),rs.getBoolean(12),rs.getBoolean(13),rs.getBoolean(14),rs.getBoolean(15),rs.getBoolean(16)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		returnMsg = new DBMessage(DBAction.getEmployeesForComboBox,list);// This is the message for non-existing user.
		return returnMsg;
	}
	
	
	
	/**
	 * Update appointment employees.
	 *
	 * @param list the list
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage updateAppointmentEmployees(UserList list,SqlConnection icmDB) {
		DBMessage returnMsg;
		Integer q1=0;
		DBMessage checkMsg=checkIfAppoitmentWasMade(icmDB);//return the list of selected employees if there us one
		UserList theFirstSelectedEmployees=((UserList)checkMsg.Data);
		if (!theFirstSelectedEmployees.user_list.isEmpty()) //reset the selected employees  to itemployee
		{
			for(User u:theFirstSelectedEmployees.user_list) {
				String queryMsg1 = "UPDATE icm_db.users SET userType= 'itemployee' WHERE idNumber= '"+u.getId()+"' ";
				q1=icmDB.executeUpdate(queryMsg1);	
			}
		}
			for(User u:list.user_list) {
			
			String role=u.getRole();
			String queryMsg = "UPDATE icm_db.requests SET "+role+"= '"+u.getId()+"'";
			q1=icmDB.executeUpdate(queryMsg);
			String queryMsg2 = "UPDATE icm_db.users SET userType= '"+role+"' WHERE idNumber= '"+u.getId()+"' ";
			 q1=icmDB.executeUpdate(queryMsg2);
		}
		returnMsg = new DBMessage(DBAction.updateAppointment,q1);// This is the message for non-existing user.
		return returnMsg;
		
	}
	
	/**
	 * Gets the employees list forassessor combo box.
	 *
	 * @param sysname the sysname
	 * @param icmDB the icm DB
	 * @return the employees list forassessor combo box
	 */
	public static DBMessage getemployeesListForassessorComboBox (String sysname,SqlConnection icmDB) {
			String query ="SELECT * FROM icm_db.users WHERE "+sysname+"='1' AND userType!='generalUser' " ; 
			UserList list=new UserList();
			list.user_list=new ArrayList<User>();
			DBMessage returnMsg;
			ResultSet rs = icmDB.executeQuery(query);
			try {
				while(rs.next()) {
					list.user_list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10),rs.getBoolean(11),rs.getBoolean(12),rs.getBoolean(13),rs.getBoolean(14),rs.getBoolean(15),rs.getBoolean(16)));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			returnMsg = new DBMessage(DBAction.getemployeesListForassessorComboBox,list);// This is the message for non-existing user.
			return returnMsg;
	}
	
	/**
	 * Check if appoitment was made.
	 *
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage checkIfAppoitmentWasMade(SqlConnection icmDB) {
		String query ="SELECT * FROM icm_db.users WHERE  userType ='supervisor' or  userType ='chairman' "
				+ "or  userType ='commissioner1' or  userType ='commissioner2'" ; 
		UserList list=new UserList();
		list.user_list=new ArrayList<User>();
		DBMessage returnMsg;
		ResultSet rs = icmDB.executeQuery(query);
		try {
				while(rs.next()) {
					list.user_list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getString(10),rs.getBoolean(11),rs.getBoolean(12),rs.getBoolean(13),rs.getBoolean(14),rs.getBoolean(15),rs.getBoolean(16)));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			returnMsg = new DBMessage(DBAction.checkIfAppoitmentWasMade,list);	
		return returnMsg;
	}
	
	/**
	 * Update user system maintenance.
	 *
	 * @param user the user
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage updateUserSystemMaintenance (User user,SqlConnection icmDB) {
		DBMessage returnMsg;
		String role=user.getRole();
		String queryMsg = "UPDATE icm_db.users SET "+role+"= '1' WHERE idNumber= '"+user.getId()+"' ";
		 int q1=icmDB.executeUpdate(queryMsg);
		 returnMsg = new DBMessage(DBAction.updateUserSystemMaintenance,q1);// This is the message for non-existing user.
		return returnMsg;
	}
	
	
	/**
	 * Gets the all employees list.
	 *
	 * @param user the user
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage GetAllEmployeesList(User user,SqlConnection icmDB) {
	//	System.out.println("1"+system);
		String query ="SELECT * FROM icm_db.users WHERE userType!='generalUser' " ; 
		UserList list=new UserList();
		list.user_list=new ArrayList<User>();
		//System.out.println(query);
		DBMessage returnMsg;
		ResultSet rs = icmDB.executeQuery(query);
		try {
		//	System.out.println("2"+system);
			while(rs.next()) {
				list.user_list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
					rs.getString(10),rs.getBoolean(11),rs.getBoolean(12),rs.getBoolean(13),rs.getBoolean(14),rs.getBoolean(15),rs.getBoolean(16)));
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		returnMsg = new DBMessage(DBAction.getAllEmployees,list);// This is the message for non-existing user.
		return returnMsg;
	}
	
	/**
	 * Find names of employees.
	 *
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static  DBMessage findNamesOfEmployees(SqlConnection icmDB) {
		String queryMsg = "SELECT idNumber FROM icm_db.users WHERE userType = 'supervisor' ;";
		String queryMsg2 = "SELECT idNumber FROM icm_db.users WHERE userType = 'chairman';";
		String queryMsg3 = "SELECT idNumber FROM icm_db.users WHERE userType = 'commissioner1';";
		String queryMsg4 = "SELECT idNumber FROM icm_db.users WHERE userType = 'commissioner2';";

		String supervisor=null;
		String chairman=null;
		String commissioner1=null;
		String commissioner2=null;
		
		ResultSet rs = icmDB.executeQuery(queryMsg);
		try {
				if(rs.next()) {
				supervisor=Integer.toString(rs.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		User userSuper=new User(supervisor);
		
		ResultSet rs2 = icmDB.executeQuery(queryMsg2);
		try {
				if(rs2.next()) {
				chairman=Integer.toString(rs2.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		User userChairman=new User(chairman);

		ResultSet rs3 = icmDB.executeQuery(queryMsg3);
		try {
				if(rs3.next()) {
				commissioner1=Integer.toString(rs3.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		User userComm1=new User(commissioner1);
		
		ResultSet rs4 = icmDB.executeQuery(queryMsg4);
		try {
				if(rs4.next()) {
				commissioner2=Integer.toString(rs4.getInt(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		User userComm2=new User(commissioner2);
		
		Request request=new Request(userSuper, userChairman, userComm1, userComm2);
		DBMessage returnMsg;
		returnMsg = new DBMessage(DBAction.getNamesOfEmployees,request);
				return returnMsg;	

	}
	
	/**
	 * Gets the mail ofh user by user ID.
	 *
	 * @param id the id
	 * @param icmDB the icm DB
	 * @return the mail ofh user by user ID
	 */
	public static String getMailOfhUserByUserID(String id, SqlConnection icmDB) 
	{
		String query = "SELECT mail FROM icm_db.users WHERE idNumber ='" + id + "'";
		ResultSet rs=icmDB.executeQuery(query);
		String mailOfUser = null;
		try {
			if(rs.next())
			{
				mailOfUser=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mailOfUser;
	}
	
	
	
}
