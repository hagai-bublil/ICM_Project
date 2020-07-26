package entitiesQueries;

import java.sql.ResultSet;
import java.sql.SQLException;

import application.SqlConnection;
import entities.Delay;

/**
 * This class hold all the queries for delay table
 *
 */
public class DelayQueries {
	
	/**
 * This method add a delay to the delay table in the data base
	 * @param delayToAdd the delay to add
	 * @param icmDB the icm DB
	 */
	public static void addDelay(Delay delayToAdd, SqlConnection icmDB)
	{ 
		int delaySN= getRowCount(icmDB)+1;
		String queryMsg = "INSERT INTO icm_db.delays (delaySN, stageManagerID, stageName, requestNumber, systemName) "
						+ "VALUES ('"+ delaySN +"', '" + delayToAdd.getStageManagerID() + "', '" + delayToAdd.getStageName()
						+ "', '" + delayToAdd.getRequestNumber() + "', '" + delayToAdd.getSystemName()  + "');";
		icmDB.executeUpdate(queryMsg);
		
	}
	
	/**
	 * Gets the row count.
	 *
	 * @param icmDB the icm DB
	 * @return the row count
	 */
	private static int getRowCount(SqlConnection icmDB)
	{
		int numOfRow = 0;
		String query = "select count(*) from icm_db.delays";
		ResultSet rs =icmDB.executeQuery(query);
		try {
			if(rs.next())
				numOfRow=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numOfRow;
	}

}
