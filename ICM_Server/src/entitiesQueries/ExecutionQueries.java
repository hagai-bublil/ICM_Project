package entitiesQueries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import application.SqlConnection;
import entities.ICMMessage;
import entities.Request;
import entities.User;

import java.io.IOException;


/**
 * This class hold all the queries for execution stage table
 *
 */
public class ExecutionQueries {

	/**
	 * Sets the the execution due date.
	 *
	 * @param specificRequest the specific request
	 * @param icmDB the icm DB
	 */
	public static void setTheExecutionDueDate(Request specificRequest, SqlConnection icmDB) 
	{
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = format.format(calendar.getTime());
		String date = format.format(specificRequest.getExecution().getDueDate());
		String query = "UPDATE icm_db.execution SET dueDate = '"+ date +"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
		icmDB.executeUpdate(query);	
		String query2 = "SELECT supervisor FROM icm_db.requests WHERE (requestNumber ='"+specificRequest.getRequestNumber()+"')";
		ResultSet rs = icmDB.executeQuery(query2);
		String supervisorId = null;
		String supervisorFirstName = null;
		String supervisorMail = null;				
		try {
			if(rs.next()) {
				supervisorId = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query3 = "SELECT firstName, mail FROM icm_db.users WHERE idNumber ='"+supervisorId+"'";
		ResultSet rs2 = icmDB.executeQuery(query3);	
		try {
			if(rs2.next()) {
				supervisorFirstName = rs2.getString(1);
				supervisorMail=rs2.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String emailSubject = "Approval determination of stage duration";
		String messageContent = "Dear "+supervisorFirstName+",\nYou need to approve or change duration time for execution stage in request number: "+specificRequest.getRequestNumber()+".";
		ICMMessage messageToSend = new ICMMessage(supervisorId,todayDate,messageContent,"supervisor",supervisorId);
		String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
		icmDB.executeUpdate(msg);
		try
		{
			SendMailController.sendEmail(supervisorMail, emailSubject, messageContent);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	/**
	 * Update stage completion.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 */
	public static void updateStageCompletion(Request request, SqlConnection icmDB)
	{
		String messageContent = null;
		String nextStage = null;
		User nextStageManager = null;
		String dueDate = null;
		switch(request.getStatus())
		{
			case "assessment":
			{
				dueDate=getcurrentDatePlusWeekAsString();
				nextStageManager=null;   
				nextStage="reviewassessment";
				messageContent="";
				break;				
			}
			case "reviewassessment":
			{
				dueDate="";
				nextStageManager=request.getExecution().getStageManager();  
				nextStage="execution";
				messageContent="";
				break;				
			}				
			case "execution":
			{	
				dueDate=getcurrentDatePlusWeekAsString();
				nextStageManager=request.getCommissioner1();
				nextStage="examination";
				messageContent = "Dear "+nextStageManager.getFirstName()+",\nRequest number "+request.getRequestNumber()+" in the "
						+ nextStage +" stage awaits your treatment.\nDue date to end stage is "+ dueDate +".";
				break;
			}
			case "examination":
			{	

				dueDate=MessagesQueries.getcurrentDateAsString();
				nextStageManager=request.getSupervisor(); 
				nextStage="closing";
				messageContent="Dear "+nextStageManager.getFirstName()+",\nRequest number "+request.getRequestNumber()+" in the " 
						+ nextStage +" stage awaits your treatment.\n";
				String query = "INSERT INTO icm_db." + nextStage + " (requestNumber, stageManager) VALUES ('"+request.getRequestNumber()+"', '"+nextStageManager.getId()+"');";
				icmDB.executeUpdate(query);	
				query = "UPDATE icm_db.closing SET closingResult = 'complete' WHERE requestNumber = '"+request.getRequestNumber()+"'";
				icmDB.executeUpdate(query);	
				break;				
			}
		}
		updateIsDoneFlag(request, icmDB);
		CheckIfTheStageExceededTheDueDate(request, icmDB);
		transferToTheNextStage(request, icmDB, nextStageManager, nextStage, dueDate);
		updateTheNextStageManager(request, icmDB, nextStageManager, messageContent);
	}
	
	/**
	 * Update the next stage manager.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 * @param nextmanager the nextmanager
	 * @param messageContent the message content
	 */
	private static void updateTheNextStageManager(Request request, SqlConnection icmDB, User nextmanager, String messageContent) 
	{
		String emailSubject = "A new request awaits your care";
		ICMMessage messageToSend = new ICMMessage("" ,"",messageContent,nextmanager.getType(), nextmanager.getIdNumber());
		String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
		icmDB.executeUpdate(msg);
		try
		{
			SendMailController.sendEmail(nextmanager.getMail(), emailSubject, messageContent);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	/**
	 * Transfer to the next stage.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 * @param nextmanager the nextmanager
	 * @param nextStage the next stage
	 * @param dueDate the due date
	 */
	private static void transferToTheNextStage(Request request, SqlConnection icmDB, User nextmanager, String nextStage, String dueDate)
	{		
		String currentDate=MessagesQueries.getcurrentDateAsString();
		String query = "UPDATE icm_db." + nextStage + " SET startDate = '" +currentDate+ "', dueDate = '"+dueDate+"' WHERE requestNumber = '"+request.getRequestNumber()+"'"; 
		icmDB.executeUpdate(query);
		query = "UPDATE icm_db.requests SET status = '"+nextStage+"' WHERE requestNumber = '"+request.getRequestNumber()+"'";
		icmDB.executeUpdate(query);	
	}

	/**
	 * Update is done flag.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 */
	public static void updateIsDoneFlag(Request request, SqlConnection icmDB)
	{
		String query = "UPDATE icm_db."+request.getStatus()+" SET IsDone = 1 WHERE requestNumber = '"+request.getRequestNumber()+"'";
		icmDB.executeUpdate(query);	
	}
	
	/**
	 * Check if the stage exceeded the due date.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 */
	public static void CheckIfTheStageExceededTheDueDate(Request request, SqlConnection icmDB)
	{
		String query = "Select IsLate FROM icm_db."+request.getStatus()+" WHERE requestNumber = "+request.getRequestNumber()+";";
		ResultSet rs = icmDB.executeQuery(query);
		int lengthOfDelay =0;
			try {
				if(rs.next())
				{
					if(rs.getInt(1)==1) /*If the due date has passed, the delay time in the delays table is updated*/
					{
						GregorianCalendar calendar = new GregorianCalendar();
						try 
						{
							lengthOfDelay = betweenDates(request.getExecution().getDueDate(), calendar.getTime());
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
						query = "UPDATE icm_db.delays SET lengthOfDelay = "+lengthOfDelay+" WHERE (stageName = '"+request.getStatus()+"' AND requestNumber = "+request.getRequestNumber() +");";
						icmDB.executeUpdate(query);	
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
	
	/**
	 * Between dates.
	 *
	 * @param firstDate the first date
	 * @param secondDate the second date
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static int betweenDates(Date firstDate, Date secondDate) throws IOException
	{
	    return (int)ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
	}
	
	/**
	 * Gets the current date plus week as string.
	 *
	 * @return the current date plus week as string
	 */
	private static String getcurrentDatePlusWeekAsString()
	{
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date currentDate = new Date();
	    // convert date to calendar
	    Calendar c = Calendar.getInstance();
	    c.setTime(currentDate);
	    c.add(Calendar.DATE, 7);
	     // convert calendar to date
	    Date currentDatePlusWeek = c.getTime();
		String string =dateFormat.format(currentDatePlusWeek);
		return string;
	}

	
}
