package entitiesQueries;

import java.sql.ResultSet;

import application.SqlConnection;
import entities.DBMessage;
import entities.ICMMessage;
import entities.Request;
import entities.User;
import entities.DBMessage.DBAction;

/**
 * This class hold all the queries for closing stage table
 *
 */
public class ClosingQueries {

	/**
	 * This method updates the request status to the closing stage in the Data Base, set the closing result,
	 * and sending appropriate message and mail to the supervisor and to the request applicant.
	 * @param requestToClose the request to close
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage closeRequest(Request requestToClose, SqlConnection icmDB) 
	{
		ExecutionQueries.updateIsDoneFlag(requestToClose, icmDB);
		ExecutionQueries.CheckIfTheStageExceededTheDueDate(requestToClose, icmDB);
		String emailSubject = "Closing application you submitted";
		String messageContent=null;
		String query="SELECT closingResult FROM icm_db.closing WHERE requestNumber = "+requestToClose.getRequestNumber()+"";
		ResultSet rs=icmDB.executeQuery(query);
		try
		{
			if(rs.next())
			{
				if(rs.getString(1).equals("reject"))
				{					
					messageContent="Dear "+requestToClose.getApplicantName()+",\nAccording to the decision of the Information Systems Team, "
							+ "the request for change number "+requestToClose.getRequestNumber()+", which you submitted, was rejected.\n" + 
							"Thank you and good day in the";
				}
				else if(rs.getString(1).equals("complete"))
				{
					messageContent="Dear "+requestToClose.getApplicantName()+",\nThe request for change number "+requestToClose.getRequestNumber()+""
							+ " that you submitted, accepted and completed.\nYou can watch the change was requested in the system.\n"
							+ "Thank you so much, and have a good day";
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		ICMMessage messageToApplicant = new ICMMessage("" ,"",messageContent, "", requestToClose.getApplicantId());
		String msg = MessagesQueries.sendMessage(messageToApplicant,icmDB);
		icmDB.executeUpdate(msg);
		String mail=UsersQueries.getMailOfhUserByUserID(requestToClose.getApplicantId(), icmDB);
		try
		{
			SendMailController.sendEmail(mail, emailSubject, messageContent);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return new DBMessage(DBAction.closingRequest,requestToClose);
	}

}
