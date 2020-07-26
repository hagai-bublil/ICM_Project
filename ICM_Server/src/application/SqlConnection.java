package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
// TODO: Auto-generated Javadoc
/**
 * This class is responsible for the DB connection with the server.
 *
 */
public class SqlConnection 
{
	
	/** The conn. */
	private Connection conn = null;//class variable for the connection
	
	/** The Is connection succeeded. */
	private boolean IsConnectionSucceeded = false;
	
	/**
	 * Instantiates a new sql connection.
	 *
	 * @param dbName the db name
	 * @param dbPassword the db password
	 * @param userName the user name
	 */
	public SqlConnection(String dbName, String dbPassword, String userName)// the function connects to the data base 
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();//define the driver
		    System.out.println("Driver definition succeed");
		} 
		catch (Exception ex) 
		{/* handle the error*/
			System.out.println("Driver definition failed");
		}		    
		try
		{
			conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName+"?serverTimezone=IST" , userName, dbPassword);//connect to the sql table
			IsConnectionSucceeded=true;
		} 
		catch (SQLException ex)
		{/* handle any errors*/
			IsConnectionSucceeded=false;
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	 }

	/**
	 * Execute update.
	 *
	 * @param msg the msg
	 * @return the int
	 */
	public int executeUpdate(String msg)
	{
		try
		{
			Statement stmt = conn.createStatement();
			return stmt.executeUpdate(msg);
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Execute query.
	 *
	 * @param query the query
	 * @return the result set
	 */
	public ResultSet executeQuery(String query)
	{
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Checks if is connection succeeded.
	 *
	 * @return true, if successful
	 */
	public boolean IsConnectionSucceeded()
	{
		return IsConnectionSucceeded;
	}
}