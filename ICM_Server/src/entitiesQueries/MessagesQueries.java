package entitiesQueries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import application.SqlConnection;
import entities.DBMessage;
import entities.User;
import entities.DBMessage.DBAction;
import entities.ICMMessage;

// TODO: Auto-generated Javadoc
/**
 * This class hold all the queries for messages
 * And the creation of ICMMessage entities .
 *
 * @author adial
 */
public class MessagesQueries 
{
	
	/**
	 * Returns the messages to this user that are in the database.
	 *
	 * @param user the user
	 * @param icmDB the icm DB
	 * @return the notifications
	 */
	public static DBMessage getNotifications(User user, SqlConnection icmDB) {
		String query = MessagesQueries.getMessagesForUser(user);
		ResultSet rs = icmDB.executeQuery(query);
		List<ICMMessage> messages = MessagesQueries.createMessagesLisrFromRS(rs);
		user.setMessages(messages);
		DBMessage returnMsg;
		returnMsg = new DBMessage(DBAction.getNotificationsForUser, user);
		return returnMsg;		
	}

	/**
	 * Creates the messages lisr from RS.
	 *
	 * @param rs the rs
	 * @return the list
	 */
	private static List<ICMMessage> createMessagesLisrFromRS(ResultSet rs) {
		List<ICMMessage> messages = new ArrayList<ICMMessage>();
		try {
			while(rs.next())
			{
				try
				{
					ICMMessage msgToCreate = new ICMMessage(rs.getString(1), rs.getString(2), rs.getString(3));
					messages.add(msgToCreate);		
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				} 
			}
		} 
		catch (SQLException e) 
		{
			messages = new ArrayList<ICMMessage>();
			e.printStackTrace();
		}
		return messages;
	}
	
	/**
	 * Writes a query that returns all messages for the user.
	 *
	 * @param user the user
	 * @return the messages for user
	 */
	private static String getMessagesForUser(User user) 
	{
	//	String queryMsg ="SELECT * FROM icm_db.messages WHERE (recipientUserType ='"+user.getType()+"' AND recipientUserId IS NULL ) "+ "OR recipientUserId ='"+user.getId()+"'";
		String queryMsg ="SELECT * FROM icm_db.messages WHERE  recipientUserId ='"+user.getId()+"'";
		return queryMsg;
	}

	/**
	 * Deletes a message from the database by the message ID number.
	 *
	 * @param msgToRemove the msg to remove
	 * @param icmDB the icm DB
	 */
	public static void removeMsg(String msgToRemove, SqlConnection icmDB) 
	{
		String query = MessagesQueries.removeMsgFromDB(msgToRemove);
		icmDB.executeUpdate(query);
	}

	/**
	 * Removes the msg from DB.
	 *
	 * @param msgToRemove the msg to remove
	 * @return the string
	 */
	private static String removeMsgFromDB(String msgToRemove) 
	{
		String queryMsg ="DELETE FROM icm_db.messages WHERE idmessage ='"+msgToRemove + "'";
		
		return queryMsg;
	}
	
	/**
	 * Send message.
	 *
	 * @param messageToSend the message to send
	 * @param icmDB the icm DB
	 * @return the string
	 */
	public static String sendMessage(ICMMessage messageToSend, SqlConnection icmDB)
	{ 
		if (messageToSend == null)	
			return null;
		String currentDateAsString = getcurrentDateAsString();
		String queryMsg = "INSERT INTO icm_db.messages (idmessage, dateSending, messageContent, recipientUserType, recipientUserId) "
						+ "VALUES ('"+getIDOfTheNewMsg(icmDB)+"', '" + currentDateAsString + "', '" + messageToSend.getMessageContent() 
						+ "', '" + messageToSend.getRecipientUserType() + "', '" + messageToSend.getRecipientUserId() + "');";
		return queryMsg;
	}
	
	/**
	 * Insert new msg to DB.
	 *
	 * @param msg1 the msg 1
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage insertNewMsgToDB(ICMMessage msg1, SqlConnection icmDB) {
		String currentDateAsString = getcurrentDateAsString();
		String query = "INSERT INTO icm_db.messages (idmessage, dateSending, messageContent, recipientUserType) VALUES ('" + msg1.getId() + 
				"', '" + currentDateAsString + "', '" + msg1.getMessageContent() + "','"
				+ "supervisor" + "')";
		System.out.println("qur2");

		int msg =icmDB.executeUpdate(query);
		DBMessage returnMsg=new DBMessage(DBAction.NewRequestMsgSpvsr, msg);
		return returnMsg;
	}
	
	/**
	 * Find max msg.
	 *
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage FindMaxMsg(SqlConnection icmDB) {
		String query = "SELECT MAX(idmessage) FROM icm_db.messages";
		String msg = null;
		Integer max;
		ResultSet rs =icmDB.executeQuery(query);
		try {
			if(rs.next())
				msg=rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		max=Integer.parseInt(msg);
		DBMessage returnMsg=new DBMessage(DBAction.FindMaxNumOfIdMsg, max);
		return returnMsg;
	}

	/**
	 * Gets the ID of the new msg.
	 *
	 * @param icmDB the icm DB
	 * @return the ID of the new msg
	 */
	private static int getIDOfTheNewMsg(SqlConnection icmDB)
	{
		int newIdMsg = 0;
		String query = "SELECT MAX(idmessage) FROM icm_db.messages";
		ResultSet rs=icmDB.executeQuery(query);
		try 
		{
			if(rs.next())
				newIdMsg=rs.getInt(1)+1;
		} 
		catch (NumberFormatException | SQLException e) 
		{
			e.printStackTrace();
		}
		return newIdMsg;
	}
	
	/**
	 * Gets the current date as string.
	 *
	 * @return the current date as string
	 */
	public static String getcurrentDateAsString()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String string = format.format(calendar.getTime());
		return string;
	}
}
