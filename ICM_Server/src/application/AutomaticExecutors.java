package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import entities.Delay;
import entities.ICMMessage;
import entities.User;
import entitiesQueries.DelayQueries;
import entitiesQueries.MessagesQueries;
import entitiesQueries.RequestsQueries;
import entitiesQueries.SendMailController;
import entitiesQueries.UsersQueries;

// TODO: Auto-generated Javadoc
/**
 * All of our automatic function will be here.
 * Make sure you call shutDown() when exiting the program unless you want a background executers.  
 * @author adial
 *
 */
public class AutomaticExecutors 
{

	/** The icm DB. */
	private static SqlConnection icmDB; 
	
	/** The executor. */
	ScheduledThreadPoolExecutor executor = null;
	
	/**
	 * Instantiates a new automatic executors.
	 *
	 * @param icmDb the icm db
	 */
	public AutomaticExecutors(SqlConnection icmDb)
	{	
		if(icmDb == null)
			return;
		AutomaticExecutors.icmDB = icmDb;
		executor = new ScheduledThreadPoolExecutor(1);
		executor.scheduleAtFixedRate(() -> reminderBeforeScheduledCompletionDate(), 0, 15, TimeUnit.MINUTES);
		executor.scheduleAtFixedRate(() -> checkAndUpdateLateCompletion(), 0, 15, TimeUnit.MINUTES);
	}
	
	/**
	 * Shut down.
	 */
	public void shutDown()
	{
		executor.shutdown();
	}
	
	/**
	 * reminderBeforeReturnDate:
	 * This method suppose to run automatically every 24 hours.
	 * If the current date is one day before the scheduled completion date,
	 *  than a remainder email and message sent to the person responsible
	 */
	static void reminderBeforeScheduledCompletionDate()
	{
		ArrayList<String> stageNames = new ArrayList<>();
		stageNames.add("assessment");
		stageNames.add("reviewassessment");
		stageNames.add("examination");
		stageNames.add("execution");
		for(String stage : stageNames)
		{
			String query = getRequestsDuedateTomorrow(stage);
			ResultSet rsRequestsDuedateTomorrow = icmDB.executeQuery(query); /* get the request numbers and their stage managers who need to complete the stage tomorrow.*/
			User stageManager = null;	
			/* in order to send mails to all of the stage Managers with the request information*/
			ArrayList<User> stageManagersToInform = new ArrayList<User>();
			ArrayList<String> requestNumbers = new ArrayList<>();
			try {
				while (rsRequestsDuedateTomorrow.next())
				{
					query=getStatusOfRequest(rsRequestsDuedateTomorrow.getString(1));
					ResultSet statusOfRequest = icmDB.executeQuery(query);
					if(statusOfRequest.next() && !statusOfRequest.getString(1).equals("suspend"))
					{
						stageManager=UsersQueries.CreateUserFromString(rsRequestsDuedateTomorrow.getString(2), icmDB);
						stageManagersToInform.add(stageManager);
						requestNumbers.add(rsRequestsDuedateTomorrow.getString(1));
					}
				}
			} catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			int i = 0;
			// Send email to all of the stage Managers one day before The deadline for complete the stage
			for (User stage_manager: stageManagersToInform)
			{
				/* send a message to the stage Manager */
				ICMMessage message;
				String messageContent;
				messageContent = "Dear " + stage_manager.getFirstName() + ",\n"
						+ "Date of completion of the \"" + stage + "\" "
						+ "stage for an request number: \"" + requestNumbers.get(i) + "\" "
						+ "is tomorrow.\n"
						+ "Please complete the stage until tomorrow, or excute stage extension (if extension is possible).";		
				message = new ICMMessage("", getCurrentDateAsString(), messageContent, stage_manager.getType(), stage_manager.getId());
				query = MessagesQueries.sendMessage(message, icmDB);
				icmDB.executeUpdate(query); // add a new message to messages table

				/* send an email to the stage manager */
				String emailSubject = "Reminder: One day before completion day";
				String emailMessage = "Dear " + stage_manager.getFirstName() + ",\n"
						+ "Date of completion of the \"" + stage + "\" "
						+ "stage for an request number: \"" + requestNumbers.get(i) + "\" "
						+ "is tomorrow.\n"
						+ "Please complete the stage until tomorrow, or excute stage extension (if extension is possible).";		
				try
				{
					SendMailController.sendEmail(stage_manager.getMail(), emailSubject, emailMessage);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}	
				i++;
			}
		}	
	}
	
	/**
	 * Gets the requests duedate tomorrow.
	 *
	 * @param tableName the table name
	 * @return the requests duedate tomorrow
	 */
	private static String getRequestsDuedateTomorrow(String tableName)
	{
		LocalDate tomorrowDate=LocalDate.parse(getCurrentDateAsString()).plusDays(1);
		String queryMsg = "SELECT requestNumber, stageManager FROM icm_db." + tableName + " WHERE dueDate = '"+tomorrowDate + "'";
		return queryMsg;
	}
	
	/**
	 * Gets the current date as string.
	 *
	 * @return the current date as string
	 */
	private static String getCurrentDateAsString()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String string = format.format(calendar.getTime());
		return string;
	}

	/**
	 * checkAndUpdateLateCompletion:
	 * This method suppose to run automatically every 24 hours. If a stage manager is
	 * late in completing the stage execution, an exception is defined 
	 * and the stage manager "isLate" flag will change to "yes".
	 * A message with the stage manager details is send to the stage manager,
	 * the supervisor and the department manager.
	 */
	static void checkAndUpdateLateCompletion()
	{
		ArrayList<String> stageNames = new ArrayList<>();
		stageNames.add("assessment");
		stageNames.add("reviewassessment");
		stageNames.add("examination");
		stageNames.add("execution");
		for(String stage : stageNames)
		{
			String query = getLatessToBeReported(stage);
			ResultSet rsLates = icmDB.executeQuery(query); /* get the request numbers and their stage managers who late*/
			User stageManager = null;	
			/* To report the delay to the stage manager, the department manager and the supervisor*/
			ArrayList<User> stageManagersToInform = new ArrayList<User>();
			ArrayList<String> requestNumbers = new ArrayList<>();
			try {
				while (rsLates.next())
				{
					query=getStatusOfRequest(rsLates.getString(1));
					ResultSet statusOfRequest = icmDB.executeQuery(query);
					if(statusOfRequest.next() && !statusOfRequest.getString(1).equals("suspend"))
					{
							stageManager=UsersQueries.CreateUserFromString(rsLates.getString(2), icmDB);
							stageManagersToInform.add(stageManager);
							requestNumbers.add(rsLates.getString(1));
							icmDB.executeUpdate(updesIsLate(stage, rsLates.getString(1))); //update the flag IsLate to 1							
					}
				}
			} catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			int i = 0;
			// Send email to all of the stage Managers one day before The deadline for complete the stage
			for (User stage_manager: stageManagersToInform)
			{	
				/*Delay documentation in the database*/
				String systemName=RequestsQueries.getSystemNameByRequestNumber(Integer.parseInt(requestNumbers.get(i)), icmDB);
				Delay newDelay= new Delay(stage_manager.getId(), stage, requestNumbers.get(i), systemName);
				DelayQueries.addDelay(newDelay, icmDB);
				/* send a message to the stage Manager */
				ICMMessage message;
				String messageContent;
				messageContent = "Dear " + stage_manager.getFirstName() + ",\n"
						+ "You are late at completing the stage \"" + stage + "\" "
						+ "for an request number: \"" + requestNumbers.get(i) + "\" "
						+ ".\nPlease complete this stage as soon as possible.\n";		
				message = new ICMMessage("", getCurrentDateAsString(), messageContent, stage_manager.getType(), stage_manager.getId());
				query = MessagesQueries.sendMessage(message, icmDB);
				icmDB.executeUpdate(query); // add a new message to messages table
				/* send an email to the stage manager */		
				String emailSubject = "Delay in completing the stage";
				try
				{
					SendMailController.sendEmail(stage_manager.getMail(), emailSubject, messageContent);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}	
				
				/* send a message to the supervisor */
				User supervisor=UsersQueries.getEmploeeyByHisRole("supervisor", icmDB);
				messageContent = "Dear " + supervisor.getFirstName() + ",\n"
						+  "The employee: " + stage_manager.getFirstName() +" "+stage_manager.getLastName()
						+ " is late at completing the stage " + stage + " " 
						+ "for an request number: " + requestNumbers.get(i) + ".";
				message = new ICMMessage("", getCurrentDateAsString(), messageContent, supervisor.getType(), supervisor.getId());
				query = MessagesQueries.sendMessage(message, icmDB);
				icmDB.executeUpdate(query); // add a new message to messages table
				/* send an email to the supervisor */	
				emailSubject = "Delay in completing the stage";
				try
				{
					SendMailController.sendEmail(supervisor.getMail(), emailSubject, messageContent);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}					
								
				/* send a message to the manager */
				User manager=UsersQueries.getEmploeeyByHisRole("director", icmDB);				
				messageContent = "Dear " + manager.getFirstName() + ",\n"
						+  "The employee: " + stage_manager.getFirstName() +" "+stage_manager.getLastName()
						+ " is late at completing the stage " + stage + "\" " 
						+ "for an request number: \"" + requestNumbers.get(i) + ".";	
				message = new ICMMessage("", getCurrentDateAsString(), messageContent, manager.getType(), manager.getId());
				query = MessagesQueries.sendMessage(message, icmDB);
				icmDB.executeUpdate(query); // add a new message to messages table
				/* send an email to the manager */
				emailSubject = "Delay in completing the stage";
				try
				{
					SendMailController.sendEmail(manager.getMail(), emailSubject, messageContent);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}					

				i++;
			}
		}	
	}
	
	/**
	 * Gets the latess to be reported.
	 *
	 * @param tableName the table name
	 * @return the latess to be reported
	 */
	private static String getLatessToBeReported(String tableName) 
	{
		String queryMsg = "SELECT requestNumber, stageManager FROM icm_db." + tableName + " WHERE ((dueDate < CURRENT_DATE()) AND (IsLate=0) AND (IsDone=0)) AND dueDate!='0000-00-00';";
		return queryMsg;
	}

	/**
	 * Updes is late.
	 *
	 * @param tableName the table name
	 * @param requestNumber the request number
	 * @return the string
	 */
	private static String updesIsLate(String tableName, String requestNumber) 
	{
		String queryMsg = "UPDATE icm_db."+tableName+" SET IsLate = 1 WHERE (requestNumber = '"+requestNumber+"');";
		return queryMsg;
	}
	
	/**
	 * Gets the status of request.
	 *
	 * @param requestNumber the request number
	 * @return the status of request
	 */
	private static String getStatusOfRequest(String requestNumber) 
	{
		String queryMsg = "SELECT status FROM icm_db.requests WHERE requestNumber='"+requestNumber+"'";
		return queryMsg;
	}	
}
