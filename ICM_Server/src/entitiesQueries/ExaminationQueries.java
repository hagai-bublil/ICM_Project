package entitiesQueries;

import application.SqlConnection;
import entities.ICMMessage;
import entities.Request;
import entities.User;

/**
 * This class hold all the queries for examination stage table
 *
 */
public class ExaminationQueries 
{
	
	/**
	 * Return request to executor stage.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 */
	public static void returnRequestToExecutorStage (Request request,SqlConnection icmDB)
	{
		returnRequestToTheExecutiontable(request, icmDB);
		initializeTheRequestInTheClosingStage(request, icmDB);
		updateRequestStatusInTheRequestTable(request, icmDB);
		updateStageManager(request, icmDB);		
	}
	
	/**
	 * Update stage manager.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 */
	private static void updateStageManager(Request request,SqlConnection icmDB) {
		User executorStageManager=request.getExecution().getStageManager();
		String messageContent="Dear "+executorStageManager.getFirstName()+",\nIn checking the change for request number "
				+request.getRequestNumber()+", I found some failures:\n" 
				+ request.getExamination().getFailureDetails() +"\n The request comes back to you.";
		String emailSubject = "Request return to your care";
		ICMMessage messageToSend = new ICMMessage("" ,"",messageContent,executorStageManager.getType(), executorStageManager.getIdNumber());
		String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
		icmDB.executeUpdate(msg);
		try
		{
			SendMailController.sendEmail(executorStageManager.getMail(), emailSubject, messageContent);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	/**
	 * Update request status in the request table.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 */
	private static void updateRequestStatusInTheRequestTable(Request request, SqlConnection icmDB) 
	{
		String query="UPDATE icm_db.requests SET status = 'execution' WHERE requestNumber = '"+request.getRequestNumber()+"'";
		icmDB.executeUpdate(query);			
		
	}

	/**
	 * Return request to the executiontable.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 */
	private static void returnRequestToTheExecutiontable(Request request, SqlConnection icmDB) 
	{
		String query="UPDATE icm_db.execution SET startDate = '"+MessagesQueries.getcurrentDateAsString()+"', dueDate = NULL,"
				+ " supervisorDueDateApprove= NULL, IsDone=0, IsLate=0 WHERE requestNumber = '"+request.getRequestNumber()+"'";
		icmDB.executeUpdate(query);			
	}

	/**
	 * Initialize the request in the closing stage.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 */
	private static void initializeTheRequestInTheClosingStage(Request request,SqlConnection icmDB)
	{
		String query="UPDATE icm_db.examination SET startDate = NULL, dueDate = '0000-00-00', failureDetails = '"+request.getExamination().getFailureDetails()+"' WHERE requestNumber = '"+request.getRequestNumber()+"'";
		icmDB.executeUpdate(query);			
	}
}
