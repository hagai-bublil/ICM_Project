package entitiesQueries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import application.SqlConnection;
import entities.DBMessage;
import entities.ICMMessage;
import entities.User;
import entities.DBMessage.DBAction;
import entities.Request;

/**
 * This class hold the first stage changes
 *
 */
public class StageQueries {
	
	/**
	 *  this function create assessment and execution stage in the db*.
	 *
	 * @param req the req
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage createsAssessmentAndExecutionstage(Request req,SqlConnection icmDB) {
		String currentDateAsString = RequestsQueries.getcurrentDateAsString();
		String date = null,status=req.getStatus(),emailSubject,messageContent,firstName = null,lastName = null,id = null,mail = null,
				messageContent1,firstName1 = null,lastName1 = null,id1 = null,mail1 = null;
		String query1 = "INSERT INTO icm_db.assessment (requestNumber, stageManager, startDate) VALUES"+" ('" + req.getRequestNumber() + "', '" + req.getAssessment().getStageManager().getId() + "', '" + currentDateAsString+ "')";
		String query2 = "INSERT INTO icm_db.execution (requestNumber, stageManager) VALUES"+" ('" + req.getRequestNumber() + "', '" + req.getExecution().getStageManager().getId() + "')";
		int ans1 =icmDB.executeUpdate(query1);
		int ans2 =icmDB.executeUpdate(query2);
		 emailSubject = "you have beem appointed";
		 //set details for assessor email
		 firstName=req.getAssessment().getStageManager().getFirstName();
		 lastName=req.getAssessment().getStageManager().getLastName();
		 id=req.getAssessment().getStageManager().getId();
		 mail=req.getAssessment().getStageManager().getMail();
		 messageContent = "Dear "+firstName+" "+lastName+",\nThe Supervisor has appointed you as the assessor of request number: "+req.getRequestNumber()+".";
		 ICMMessage messageToSend = new ICMMessage("","",messageContent,"",id);
		 String msg = MessagesQueries.sendMessage(messageToSend,icmDB);//add message in notifications
		 //set details for executor email
		 firstName1=req.getExecution().getStageManager().getFirstName();
		 lastName1=req.getExecution().getStageManager().getLastName();
		 id1=req.getExecution().getStageManager().getId();
		 mail1=req.getExecution().getStageManager().getMail();
		 messageContent1 = "Dear "+firstName1+" "+lastName1+",\nThe Supervisor has appointed you as the executor of request number: "+req.getRequestNumber()+".";
		 ICMMessage messageToSend1 = new ICMMessage("","",messageContent1,"",id1);
		 String msg1 = MessagesQueries.sendMessage(messageToSend1,icmDB);//add message in notifications
		 try
		 {
			 //send emails
			 SendMailController.sendEmail(mail, emailSubject, messageContent);
			 SendMailController.sendEmail(mail1, emailSubject, messageContent1);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 //setDueDateForStage(Request req, SqlConnection icmDB);
		DBMessage returnMsg=new DBMessage(DBAction.createsAssessmentAndExecutionstage, ans2);
		return returnMsg;
	}
	
/**
 *  this function gets the due date of the current stage from db.
 *
 * @param req the req
 * @param icmDB the icm DB
 * @return the due date
 * @throws SQLException *
 */
	
	public static DBMessage getDueDate(Request req,SqlConnection icmDB) throws SQLException {
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String query = "Select dueDate FROM icm_db."+req.getStatus()+" WHERE requestNumber = "+req.getRequestNumber()+";";
		ResultSet rs = icmDB.executeQuery(query);
		String date = null;
		boolean ans=false;
		if(rs.next()) {
			 
			date=rs.getString(1);
			if(!date.equals("0000-00-00")) {
				if(req.getStatus().equals("execution")) {
					//date = format.format(rs.getString(1));
					req.getExecution().setDueDate(rs.getString(1));
				}
				if(req.getStatus().equals("assessment")) {
					//date = format.format(rs.getString(1));
					req.getAssessment().setDueDate(rs.getString(1));
				}
				ans=true;
			}
		}
		DBMessage returnMsg=new DBMessage(DBAction.getDueDate, ans);
		return returnMsg;
		}
	
	
	
	
	/**
	 *  this function sets the new due date of the current stage in db*.
	 *
	 * @param req the req
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage setDueDateForStage(Request req, SqlConnection icmDB) 
	{
		//System.out.println(req.toString());
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = format.format(calendar.getTime());
		String date = null,status=req.getStatus(),emailSubject,messageContent,firstName = null,lastName = null,id = null,mail = null;
		boolean ans=false;
		
		if(status.equals("execution")) {
			date = format.format(req.getExecution().getDueDate());
		}
		if(status.equals("assessment")) {
			date = format.format(req.getAssessment().getDueDate());
		}
		String query = "UPDATE icm_db."+status+" SET dueDate = '"+ date +"' WHERE requestNumber = '"+req.getRequestNumber()+"'";
		if (icmDB.executeUpdate(query)==1)
		{
			String query1 = "UPDATE icm_db."+status+" SET supervisorDueDateApprove = '1' WHERE requestNumber = '"+req.getRequestNumber()+"'";
			if(icmDB.executeUpdate(query1)==1) {
				ans=true;
			}
			if(status.equals("execution"))
			{
				//set details for executor email
				firstName=req.getExecution().getStageManager().getFirstName();
				lastName=req.getExecution().getStageManager().getLastName();
				id=req.getExecution().getStageManager().getId();
				mail=req.getExecution().getStageManager().getMail();

			}
		
			if(status.equals("assessment"))
			{
				//set details for assessor email
				firstName=req.getAssessment().getStageManager().getFirstName();
				lastName=req.getAssessment().getStageManager().getLastName();
				id=req.getAssessment().getStageManager().getId();
				mail=req.getAssessment().getStageManager().getMail();
			}
		
			 emailSubject = "Change fo stage duration";
			 messageContent = "Dear "+firstName+" "+lastName+",\nThe Supervisor approved or set your stage's due date in request number: "+req.getRequestNumber()+".";
			 ICMMessage messageToSend = new ICMMessage("","",messageContent,"",id);
			 String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
			 try
			 {
				 //send email
				 SendMailController.sendEmail(mail, emailSubject, messageContent);
				 ans=true;
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
		}
		//System.out.println(ans);
		DBMessage returnMsg=new DBMessage(DBAction.setDueDateForStage, ans);
		return returnMsg;
				
	}
	
	
	/**
	 * this function approves the extention request un the db-setting the due date,extensionDate and extension*.
	 *
	 * @param req the req
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage aprroveExtention(Request req, SqlConnection icmDB) 
	{
		String emailSubject,messageContent,fullName = null,id = null,mail = null;
		String query="";
		boolean ans=true;
		emailSubject="Approval of extention request";
		switch(req.getStatus()) {
    	/////////assessment
		case "assessment":
			query = "UPDATE icm_db.assessment SET extension = '1',extensionDate ='0000-00-00',dueDate='"+req.getAssessment().getExtensionDateString()+"' WHERE requestNumber = '"+req.getRequestNumber()+"'";
			fullName=req.getAssessment().getStageManager().getFirstName()+req.getAssessment().getStageManager().getLastName();
			id=req.getAssessment().getStageManager().getId();
			mail=req.getAssessment().getStageManager().getMail();
			break;
		////////////////	reviewassessment
		case "reviewassessment":
			query = "UPDATE icm_db.reviewassessment SET extension = '1',extensionDate ='0000-00-00',dueDate='"+req.getReviewAssessment().getExtensionDateString()+"' WHERE requestNumber = '"+req.getRequestNumber()+"'";
			fullName=req.getReviewAssessment().getStageManager().getFirstName()+req.getReviewAssessment().getStageManager().getLastName();
			id=req.getReviewAssessment().getStageManager().getId();
			mail=req.getReviewAssessment().getStageManager().getMail();
			break;
			///////////////////execution
		case "execution":
			query = "UPDATE icm_db.execution SET extension = '1',extensionDate ='0000-00-00',dueDate='"+req.getExecution().getExtensionDateString()+"' WHERE requestNumber = '"+req.getRequestNumber()+"'";
			fullName=req.getExecution().getStageManager().getFirstName()+req.getExecution().getStageManager().getLastName();
			id=req.getExecution().getStageManager().getId();
			mail=req.getExecution().getStageManager().getMail();
			
			break;
			///////////////examination
		case "examination":
			query = "UPDATE icm_db.examination SET extension = '1',extensionDate ='0000-00-00',dueDate='"+req.getExamination().getExtensionDateString()+"' WHERE requestNumber = '"+req.getRequestNumber()+"'";
			fullName=req.getExamination().getStageManager().getFirstName()+req.getExamination().getStageManager().getLastName();
			id=req.getExamination().getStageManager().getId();
			mail=req.getExamination().getStageManager().getMail();
			break;
		default:
			break;	
    	}
			if (icmDB.executeUpdate(query)==1) {
					ans=true;
					 messageContent = "Dear "+fullName+",\nThe Supervisor approved your extention request for request number: "+req.getRequestNumber()+".";
					 ICMMessage messageToSend = new ICMMessage("","",messageContent,"",id);
					 String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
					 try
					 {
						 //send email
						 SendMailController.sendEmail(mail, emailSubject, messageContent);
						 ans=true;
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 } 
			}
			else
				ans=false;
			DBMessage returnMsg=new DBMessage(DBAction.aprroveExtention, ans);
			return returnMsg;
		}
		


	
	
	
/**
 * Reject extention.
 *
 * @param req the req
 * @param icmDB the icm DB
 * @return the DB message
 */
public static DBMessage rejectExtention(Request req, SqlConnection icmDB) 
{
	String emailSubject,messageContent,fullName = null,id = null,mail = null;
	String query="";
	boolean ans=true;
	emailSubject="Reject of extention request";
	switch(req.getStatus()) {
	/////////assessment
	case "assessment":
		query = "UPDATE icm_db.assessment SET extensionDate ='0000-00-00',extensionReason= NULL WHERE requestNumber = '"+req.getRequestNumber()+"'";
		fullName=req.getAssessment().getStageManager().getFirstName()+req.getAssessment().getStageManager().getLastName();
		id=req.getAssessment().getStageManager().getId();
		mail=req.getAssessment().getStageManager().getMail();
		break;
	////////////////	reviewassessment
	case "reviewassessment":
		query = "UPDATE icm_db.reviewassessment SET extensionDate ='0000-00-00',extensionReason= NULL WHERE requestNumber = '"+req.getRequestNumber()+"'";
		fullName=req.getReviewAssessment().getStageManager().getFirstName()+req.getReviewAssessment().getStageManager().getLastName();
		id=req.getReviewAssessment().getStageManager().getId();
		mail=req.getReviewAssessment().getStageManager().getMail();
		break;
		///////////////////execution
	case "execution":
		query = "UPDATE icm_db.execution SET extensionDate ='0000-00-00',extensionReason= NULL WHERE requestNumber = '"+req.getRequestNumber()+"'";
		query = "UPDATE icm_db.execution SET extension = '1',extensionDate ='0000-00-00',dueDate='"+req.getExecution().getDueDateString()+"' WHERE requestNumber = '"+req.getRequestNumber()+"'";
		fullName=req.getExecution().getStageManager().getFirstName()+req.getExecution().getStageManager().getLastName();
		id=req.getExecution().getStageManager().getId();
		mail=req.getExecution().getStageManager().getMail();
		break;
		///////////////examination
	case "examination":
		query = "UPDATE icm_db.examination SET extensionDate ='0000-00-00',extensionReason= NULL WHERE requestNumber = '"+req.getRequestNumber()+"'";
		fullName=req.getExamination().getStageManager().getFirstName()+req.getExamination().getStageManager().getLastName();
		id=req.getExamination().getStageManager().getId();
		mail=req.getExamination().getStageManager().getMail();
		break;
	default:
		break;	
	}
		if (icmDB.executeUpdate(query)==1) {
			ans=true;
				 messageContent = "Dear "+fullName+",\nThe Supervisor rejected your extention request for request number: "+req.getRequestNumber()+".";
				 ICMMessage messageToSend = new ICMMessage("","",messageContent,"",id);
				 String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
				 try
				 {
					 //send email
					 SendMailController.sendEmail(mail, emailSubject, messageContent);
					 ans=true;
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 } 
		}
			
		else
			ans=false;
		DBMessage returnMsg=new DBMessage(DBAction.rejectExtention, ans);
		return returnMsg;
	}
	
}
	
/*	public static DBMessage getExtentionForSupervisor(Request req, SqlConnection icmDB) 
	{
		String query = "Select extension,extensionReason,extensionDate FROM icm_db."+req.getStatus()+" WHERE requestNumber = "+req.getRequestNumber()+";";
		ResultSet rs = icmDB.executeQuery(query);
		String extentionDate,extentionReason,ans;
		boolean isExtentionApproved;
		try {
			if(rs.next()) {
				isExtentionApproved=rs.getBoolean(1);
				if(isExtentionApproved==true)
				{
					ans="Extention request already approved";
				}
				else {
					extentionReason=rs.getString(2);
					extentionDate=rs.getString(3);
					if(extentionDate=="0002-11-30") {
						ans="The extension request was not filed";
					}
					else {
						
					}
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

