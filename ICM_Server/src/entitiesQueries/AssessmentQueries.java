package entitiesQueries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import application.SqlConnection;
import entities.DBMessage;
import entities.ICMMessage;
import entities.Request;
import entities.User;
import entities.DBMessage.DBAction;

/**
 * This class hold all the queries for assessment stage table
 *
 */
public class AssessmentQueries {
	
	/**
	 * This method updates the due date of the assessment stage in the Data Base, and send message and mail to
	 * the supervisor to confirm or change the date.
	 * @param specificRequest the specific request
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage setTheAssessmentDueDate(Request specificRequest,SqlConnection icmDB){	

		String todayDate = getcurrentDateAsString();
		DBMessage returnMsg;
		String date = specificRequest.getAssessment().getDueDateString();
		String query = "UPDATE icm_db.assessment SET dueDate = '"+ date +"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
		icmDB.executeUpdate(query);	
		String query2 = "SELECT supervisor FROM icm_db.requests WHERE (requestNumber ='"+specificRequest.getRequestStringNumber()+"')";
		ResultSet rs = icmDB.executeQuery(query2);
		String supervisorId = null;
		String supervisorFirstName = null;
				
		try {
			if(rs.next()) {
				supervisorId = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query3 = "SELECT firstName FROM icm_db.users WHERE idNumber ='"+supervisorId+"'";
		ResultSet rs2 = icmDB.executeQuery(query3);	
		try {
			if(rs2.next()) {
				supervisorFirstName = rs2.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        //message
		String messageContent = "Dear "+supervisorFirstName+",\nYou need to approve or change duration time for assessment stge in request number:"+specificRequest.getRequestStringNumber()+".";
		ICMMessage messageToSend = new ICMMessage(supervisorId,todayDate,messageContent,"supervisor",supervisorId);
		String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
		icmDB.executeUpdate(msg);
		//mail
		String emailSubject = "duration time for assessment stge submitted";
		try {
			SendMailController.sendEmail(specificRequest.getSupervisor().getMail(), emailSubject, messageContent);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		returnMsg = new DBMessage(DBAction.setAssessmentDueDate,specificRequest);
		return returnMsg;
		
		}

	/**
 * This method set the assessment report in the assessment table in the Data Base, and do initializes for the next stage
	 * @param specificRequest the specific request
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage setTheAssessmentReport(Request specificRequest,SqlConnection icmDB){
	
		DBMessage returnMsg;
		String query = "UPDATE icm_db.assessment SET assessmentReport = '"+ specificRequest.getAssessment().getAssessmentReport() +"' WHERE requestNumber = '"+specificRequest.getRequestStringNumber()+"'";
        icmDB.executeUpdate(query);
        setForReviewAssessment(specificRequest,icmDB);
		returnMsg = new DBMessage(DBAction.setReport,specificRequest);
		return returnMsg;
	
	}
	

	/**
 * This method initializes the next stage:reviewAssessment, send message and mail to the supervisor
 * and the next stage manager.
	 * @param specificRequest the specific request
	 * @param icmDB the icm DB
	 */
	public static void setForReviewAssessment(Request specificRequest,SqlConnection icmDB) {
		ExecutionQueries.updateIsDoneFlag(specificRequest, icmDB);
		ExecutionQueries.CheckIfTheStageExceededTheDueDate(specificRequest, icmDB);
		//ExecutionQueries.updateIsDoneFlag(specificRequest, icmDB);
		String weekFromToday = "NOT";
		String todayDate = getcurrentDateAsString();
		try {
			weekFromToday = getcurrentDatePlusWeekAsString();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String query2 = "SELECT chairman FROM icm_db.requests WHERE (requestNumber ='"+specificRequest.getRequestStringNumber()+"')";
		ResultSet rs = icmDB.executeQuery(query2);
		String chairmanID = null;
		try {
			if(rs.next()) {
				chairmanID = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
        String query = "INSERT INTO icm_db.reviewassessment (requestNumber,stageManager,startDate,dueDate,extension,extensionTime,extensionReason,extraDetails,decision,IsDone,IsLate)"
        		+ " VALUES ("+specificRequest.getRequestStringNumber()+","+chairmanID+",'"+todayDate+"','"+weekFromToday+"',0,0,0,0,0,0,0)";
        icmDB.executeUpdate(query);
        query = "UPDATE icm_db.requests SET status = 'reviewassessment' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
        icmDB.executeUpdate(query);
        //message for supervisor
        String messageContent = "Dear "+specificRequest.getSupervisor().getFirstName()+",\nRequest number:"+specificRequest.getRequestStringNumber()+" was updated to \nreview assessment report stage.";
        ICMMessage messageToSend = new ICMMessage(specificRequest.getSupervisor().getId(),todayDate,messageContent,"supervisor",specificRequest.getSupervisor().getId());
        String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
        icmDB.executeUpdate(msg);
        //mail for supervisor
     	String emailSubject = "Request stag was updated to: review assessment report";
     	try {
     		SendMailController.sendEmail(specificRequest.getSupervisor().getMail(), emailSubject, messageContent);
     	}
     	catch(Exception e) {
     		e.printStackTrace();
     	}        
     	 //message for the next stage manager
        String messageContent2 = "Dear "+specificRequest.getChairman().getFirstName()+",\nRequest number:"+specificRequest.getRequestStringNumber()+" was updated to \nreview assessment report stage, you are the stage manager.";
        ICMMessage messageToSend2 = new ICMMessage(specificRequest.getSupervisor().getId(),todayDate,messageContent,"chairman",specificRequest.getSupervisor().getId());
        String msg2 = MessagesQueries.sendMessage(messageToSend2,icmDB);
        icmDB.executeUpdate(msg2);
        //mail for supervisor
     	String emailSubject2 = "Request stag was updated to: review assessment report";
     	try {
     		SendMailController.sendEmail(specificRequest.getSupervisor().getMail(), emailSubject2, messageContent2);
     	}
     	catch(Exception e) {
     		e.printStackTrace();
     	}       
     	
     	
     	
	}

	/**
	 * This method return the current Date as a String.
	 * @return current Date as a String.
	 */
	public static String getcurrentDateAsString()
	{
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String string = format.format(calendar.getTime());
		return string;
	}
	
	/**
	 * This method return the current Date plus week as a String.
	 * @return the current date plus week as string
	 * @throws ParseException the parse exception
	 */
	public static String getcurrentDatePlusWeekAsString() throws ParseException
	{
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date currentDate = new Date();
	    // convert date to calendar
	    Calendar c = Calendar.getInstance();
	    c.setTime(currentDate);
	    c.add(Calendar.DATE, 7); //same with c.add(Calendar.DAY_OF_MONTH, 1);
	        // convert calendar to date
	    Date currentDatePlusWeek = c.getTime();
		String string =dateFormat.format(currentDatePlusWeek);
		return string;
	}

}
