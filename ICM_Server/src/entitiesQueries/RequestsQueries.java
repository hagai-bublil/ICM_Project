package entitiesQueries;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import application.SqlConnection;
import entities.Assessment;
import entities.Closing;
import entities.DBMessage;
import entities.DBMessage.DBAction;
import entities.Examination;
import entities.Execution;
import entities.ICMMessage;
import entities.Request;
import entities.ReviewAssessment;
import entities.User;

/**
 * This class hold all the queries for requests
 * And the creation of request entities from ResaultSet
 *
 */
public class RequestsQueries {
	

	/**
	 * Gets the user infor.
	 *
	 * @param user the user
	 * @return the user infor
	 */
	public static String getUserInfor(User user)
	{
		String queryMsg ="SELECT * FROM icm_db.requests WHERE applicantId ='" + user.getId()+ "'";
		return queryMsg;
	}
	
	
		/**
		 * Check if request exist.
		 *
		 * @param user the user
		 * @param icmDB the icm DB
		 * @return the DB message
		 * @throws SQLException the SQL exception
		 */
		public static DBMessage CheckIfRequestExist(User user,SqlConnection icmDB) throws SQLException
		{
			//UsersQueries user1;
			User user1;
			String queryMsg= RequestsQueries.getUserInfor(user);
			ResultSet rs = icmDB.executeQuery(queryMsg);
			createRequestsLisrFromRS(user,rs,icmDB,"myRequest");
			DBMessage returnMsg;

					returnMsg = new DBMessage(DBAction.CheckRequestNumber,user);//if the request number is exist
					return returnMsg;
			}

			//	 returnMsg = new DBMessage(DBAction.CheckRequestNumber,null);// the request number doesn't exist
				// return returnMsg;
//	}
		
		/**
			 * Find max reqst.
			 *
			 * @param icmDB the icm DB
			 * @return the DB message
			 */
			public static DBMessage FindMaxReqst(SqlConnection icmDB) {
			String query = "SELECT MAX(requestNumber) FROM icm_db.requests";
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
			DBMessage returnMsg=new DBMessage(DBAction.FindMaxNumOfIdRequest, max);
			return returnMsg;
		}

		/**
		 * Insert request to DB.
		 *
		 * @param request the request
		 * @param icmDB the icm DB
		 * @return the DB message
		 */
		public static DBMessage insertRequestToDB(Request request, SqlConnection icmDB) {//this function insert request to request's table
			String currentDateAsString = getcurrentDateAsString();

			String query = "INSERT INTO icm_db.requests (requestNumber, applicantName, applicantId, systemName, descriptionOfExistingSituation,requestDescription, submissionDate, comments, status, reasons,supervisor, chairman, commissioner1, commissioner2)"
					+ " VALUES ('" + request.getRequestNumber() + "', '" + request.getApplicantName() + "', '" + request.getApplicantId() + "','"
					+ request.getSystemName() + "', '" + request.getDescriptionOfExistingSituation() + "', '"
					+ request.getRequestDescription() + "','"
					+ currentDateAsString /*request.getSubmissionDate()*/ + "','" + request.getComments() + "','" + "initial" + "','" + request.getReasons() + "', '" + request.getSupervisor().getIdNumber() + "','" + request.getChairman().getIdNumber() + "','" + request.getCommissioner1().getIdNumber() + "','" + request.getCommissioner2().getIdNumber() + "')";
			
			int msg =icmDB.executeUpdate(query);
			DBMessage returnMsg=new DBMessage(DBAction.CreateNewRequest, msg);
			return returnMsg;

		}	
		
		/**
		 * Gets the supervisor request.
		 *
		 * @param UserLogedIn the user loged in
		 * @param icmDB the icm DB
		 * @return the supervisor request
		 */
		//this function returns db massage with the requests list to icm server
		public static  DBMessage getSupervisorRequest( User UserLogedIn,SqlConnection icmDB)
		{
			DBMessage returnMsg;
			String query = RequestsQueries.getSupervisorRequestQuries();
			ResultSet rs = icmDB.executeQuery(query);
			createRequestsLisrFromRS(UserLogedIn, rs, icmDB,"supervisor");
			returnMsg = new DBMessage(DBAction.getRequestsListForSupervisor, UserLogedIn);// This is the message for valid user.
			return returnMsg;
		}
		
		/**
		 * Gets the supervisor request quries.
		 *
		 * @return the supervisor request quries
		 */
		//returns a query for getting all the requests
		private static String getSupervisorRequestQuries() {
			String queryMsg ="SELECT * FROM icm_db.requests";
			return queryMsg;
		}
		
		/**
		 * Creates the requests lisr from RS.
		 *
		 * @param user the user
		 * @param rs the rs
		 * @param icmDB the icm DB
		 * @param role the role
		 */
		//this function returns a list of requests	
		public static  void createRequestsLisrFromRS(User user,ResultSet rs,SqlConnection icmDB,String role)
		{
			List<Request> Requests = new ArrayList<Request>();
			try
			{
				while(rs.next())
				{
					Request req=new Request();
					req.setRequestNumber(rs.getInt(1));//(Integer.parseInt(rs.getString(1)));
					req.setApplicantName(rs.getString(2));
					req.setApplicantId(rs.getString(3));
					req.setSystemName(rs.getString(4));
					req.setDescriptionOfExistingSituation(rs.getString(5));
					req.setRequestDescription(rs.getString(6));
					req.setComments(rs.getString(7));
					req.setStatus(rs.getString(8));
					req.setSubmissionDate(rs.getString(9));
					//System.out.println(req.getSubmissionDate().toString());
					req.setReasons(rs.getString(14));
				//	System.out.println(req.getReasons());
					getAssessmentStageSFromDB(req,icmDB);
					getReviewAssessmentStageSFromDB(req,icmDB);
					getExecutionStageSFromDB(req,icmDB);
					getExaminationStageSFromDB(req,icmDB);
					getClosingStageSFromDB(req,icmDB);
					req.setSupervisor(UsersQueries.CreateUserFromString(rs.getString(10),icmDB));
					req.setChairman(UsersQueries.CreateUserFromString(rs.getString(11),icmDB));
					req.setCommissioner1(UsersQueries.CreateUserFromString(rs.getString(12),icmDB));
					req.setCommissioner2(UsersQueries.CreateUserFromString(rs.getString(13),icmDB));	
					Requests.add(req);	

					}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				Requests = new ArrayList<Request>();
			}
			switch(role) {
			case "supervisor":
				user.setSupervisorRequests(Requests);
				break;
			case "assessor":
				user.setAssessorRequests(Requests);
				break;
			case "executor":
			{
				user.setExecutorRequests(Requests);
				break;
			}	
			case "examiner":
			{
				user.setExaminerRequests(Requests);
				break;
			}
			case "reviewer":
			{
				user.setReviewRequests(Requests);
				break;
			}				
			case "myRequest":
				user.setRequests(Requests);
				break;
				default:
					user.setSupervisorRequests(Requests);
					break;
			}	
		}
		
		/**
		 * Gets the assessment stage S from DB.
		 *
		 * @param req the req
		 * @param icmDB the icm DB
		 * @return the assessment stage S from DB
		 */
		//		return the assessment StageS From DB
		public static  void getAssessmentStageSFromDB (Request req,SqlConnection icmDB) 
		{
			String stageManagerId;
			String query ="SELECT * FROM icm_db.assessment WHERE requestNumber='"+req.getRequestNumber()+"'";
			ResultSet rs = icmDB.executeQuery(query);
		try {
			if(rs.next()) {
					/*(,String dueDate,boolean exception,int exceptionTime,
			boolean extension,int extensionTime,String extensionReason,String assessmentReport) {*/
					req.setAssessment(new Assessment(rs.getInt(1),rs.getString(3),rs.getString(4),
							rs.getBoolean(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getBoolean(9),rs.getInt(10),rs.getInt(11)));
					req.getAssessment().setExtensionDate(rs.getString(12));
					stageManagerId=rs.getString(2);
					if(stageManagerId!="null")
						req.getAssessment().setStageManager(UsersQueries.CreateUserFromString(stageManagerId,icmDB));
						
					}
					}
						catch (SQLException e) {
						req.setReviewAssessment(null);
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			

		}
		
		/**
		 * Gets the review assessment stage S from DB.
		 *
		 * @param req the req
		 * @param icmDB the icm DB
		 * @return the review assessment stage S from DB
		 */
		//		return the reviewassessment StageS From DB
		public static void getReviewAssessmentStageSFromDB (Request req,SqlConnection icmDB) 
			{
			String stageManagerId;
			String query ="SELECT * FROM icm_db.reviewassessment WHERE requestNumber='"+req.getRequestNumber()+"'";
			ResultSet rs = icmDB.executeQuery(query);
			
				try {
					if(rs.next()) {
					/*(,String dueDate,boolean exception,int exceptionTime,
			boolean extension,int extensionTime,String extensionReason,String assessmentReport) {*/
					req.setReviewAssessment(new ReviewAssessment(rs.getInt(1),rs.getString(3),rs.getString(4),
							rs.getBoolean(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getInt(11)));
					stageManagerId=rs.getString(2);
					req.getReviewAssessment().setExtensionDate(rs.getString(12));
					if(stageManagerId!="null")
					req.getReviewAssessment().setStageManager(UsersQueries.CreateUserFromString(stageManagerId,icmDB));
					
				}
				}
					catch (SQLException e) {
					req.setReviewAssessment(null);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		
		/**
		 * Gets the execution stage S from DB.
		 *
		 * @param req the req
		 * @param icmDB the icm DB
		 * @return the execution stage S from DB
		 */
		//		return the Execution StageS From DB
		public static void getExecutionStageSFromDB (Request req,SqlConnection icmDB)
			{
				String stageManagerId;
				String query ="SELECT * FROM icm_db.execution WHERE requestNumber='"+req.getRequestNumber()+"'";
				ResultSet rs = icmDB.executeQuery(query);
					try {
						if(rs.next()) {
						req.setExecution(new Execution(rs.getInt(1),rs.getString(3),rs.getString(4),
								rs.getBoolean(5),rs.getInt(6),rs.getString(7),rs.getBoolean(8),rs.getBoolean(9),rs.getInt(10),rs.getInt(11)));
						stageManagerId=rs.getString(2);
						req.getExecution().setExtensionDate(rs.getString(12));
						if(stageManagerId!="null")
						req.getExecution().setStageManager(UsersQueries.CreateUserFromString(stageManagerId,icmDB));
						}
					} catch (SQLException e) {
						req.setExecution(null);
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
					
		/**
		 * Gets the examination stage S from DB.
		 *
		 * @param req the req
		 * @param icmDB the icm DB
		 * @return the examination stage S from DB
		 */
		//	return the Examinatio StageS From DB
		public static void getExaminationStageSFromDB (Request req,SqlConnection icmDB)
			{
				String stageManagerId;
				String query ="SELECT * FROM icm_db.examination WHERE requestNumber='"+req.getRequestNumber()+"'";
				ResultSet rs = icmDB.executeQuery(query);
					try {
						if(rs.next()) {
						req.setExamination(new Examination(rs.getInt(1),rs.getString(3),rs.getString(4),
								rs.getBoolean(5),
								rs.getInt(6),
								rs.getString(7),
								rs.getString(8),
								rs.getString(9),
								rs.getInt(11),
								rs.getInt(12)));
						stageManagerId=rs.getString(2);
						req.getExamination().setExtensionDate(rs.getString(13));
						if(stageManagerId!="null")
						req.getExamination().setStageManager(UsersQueries.CreateUserFromString(stageManagerId,icmDB));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		
		/**
		 * Gets the closing stage S from DB.
		 *
		 * @param req the req
		 * @param icmDB the icm DB
		 * @return the closing stage S from DB
		 */
		//	return the Closing StageS From DB
		public static void getClosingStageSFromDB (Request req,SqlConnection icmDB)
			{
				String stageManagerId;
				String query ="SELECT * FROM icm_db.closing WHERE requestNumber='"+req.getRequestNumber()+"'";
				ResultSet rs = icmDB.executeQuery(query);
					try {
						if(rs.next()) {
						req.setClosing(new Closing(rs.getInt(1),rs.getString(3),rs.getString(4),
								rs.getBoolean(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getInt(10),rs.getInt(11)));
						stageManagerId=rs.getString(2);
						//System.out.println(stageManagerId);
						if(stageManagerId!="null")
						req.getClosing().setStageManager(UsersQueries.CreateUserFromString(stageManagerId,icmDB));
						}
					} catch (SQLException e) {
						req.setClosing(null);
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		
		/**
		 * Returns the name of the system  in which he was asked to make a change by the change request number.
		 *
		 * @param requestNumber the request number
		 * @param icmDB the icm DB
		 * @return the name of the system
		 */
		
public static String getSystemNameByRequestNumber(int requestNumber, SqlConnection icmDB)
		{
			String systemName=null;
			String query ="SELECT systemName FROM icm_db.requests WHERE requestNumber='"+ requestNumber +"'";
			ResultSet rs = icmDB.executeQuery(query);
			try {
				if(rs.next())
					systemName=rs.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
				//fsadfasd
			}
			return systemName;
		}	
		
		/**
		 * Gets the request by status.
		 *
		 * @param status the status
		 * @param icmDB the icm DB
		 * @return the request by status
		 */
		public static ResultSet getRequestByStatus(String status, SqlConnection icmDB)
		{
			String query ="SELECT * FROM icm_db.requests WHERE status='"+ status +"';";
			ResultSet rs = icmDB.executeQuery(query);
			return rs;
		}
		
		/**
		 * Returns a DBMessage with the user's object with all the requests it is responsible for at the requested stage.
		 *
		 * @param user the user
		 * @param icmDB the icm DB
		 * @param status the status
		 * @param role the role
		 * @return the requests for specific role
		 */
		public static DBMessage getRequestsForSpecificRole(User user, SqlConnection icmDB, String status, String role)
		{	
			int i=0;
			DBMessage returnMsg;
			ResultSet rs=getRequestByStatus(status, icmDB);
			createRequestsLisrFromRS(user,rs,icmDB, role);
			switch(status)
			{
				case "assessment":
				{
					for(i=0; i<user.getAssessorRequests().size(); i++) 
					{
								if(IsThisUserResponsible(user.getAssessorRequests().get(i),user,icmDB, status) == false) {
									user.getAssessorRequests().remove(user.getAssessorRequests().get(i));
								}
					}	
					break;
				}
				case "execution":
				{
					for(i=0; i<user.getExecutorRequests().size(); i++) 
					{
								if(IsThisUserResponsible(user.getExecutorRequests().get(i),user,icmDB, status) == false) 
									user.getExecutorRequests().remove(user.getExecutorRequests().get(i));

					}	
					break;
				}
				case "examination":
				{
					for(i=0; i<user.getExaminerRequests().size(); i++) 
					{
						if(IsThisUserResponsible(user.getExaminerRequests().get(i),user,icmDB, status) == false) 
							user.getExaminerRequests().remove(user.getExaminerRequests().get(i));
					}
					break;
				}
				case "reviewassessment":
				{
					for(i=0; i< user.getReviewRequests().size(); i++) 
					{
							if(IsThisUserResponsible(user.getReviewRequests().get(i),user,icmDB, status) == false) 
								user.getReviewRequests().remove(user.getReviewRequests().get(i));		
					}
					break;
				}
				default:
				break;
			}
			returnMsg = new DBMessage(DBAction.getRequestsListForEmployee ,user);
			return returnMsg;
		}

		/**
		 * Gets the assessor request.
		 *
		 * @param user the user
		 * @param icmDB the icm DB
		 * @return the assessor request
		 */
		public static DBMessage getAssessorRequest(User user,SqlConnection icmDB)
		{
			int i=0;
			DBMessage returnMsg;
			String query = "SELECT * FROM icm_db.requests WHERE status = 'assessment'";
			ResultSet rs = icmDB.executeQuery(query);
			createRequestsLisrFromRS(user,rs,icmDB,"assessor");
			//System.out.println(user.getAssessorRequests().get(1).toString());
			for(i=0;i<user.getAssessorRequests().size();i++) 
			{
						if(getAssessorRequestQuries(user.getAssessorRequests().get(i),user,icmDB) == false) {
							System.out.println(user.getAssessorRequests().get(i).getRequestNumber());
							user.getAssessorRequests().remove(user.getAssessorRequests().get(i));
						}
			}	
			System.out.println("in2");
			returnMsg = new DBMessage(DBAction.getRequestsListForAssessor,user);
			return returnMsg;
		}


		/**
		 * Gets the assessor request quries.
		 *
		 * @param requestToCheck the request to check
		 * @param user the user
		 * @param icmDB the icm DB
		 * @return the assessor request quries
		 */
		public static boolean getAssessorRequestQuries(Request requestToCheck,User user,SqlConnection icmDB) {
			String queryMsg = "SELECT requestNumber FROM icm_db.assessment WHERE (requestNumber ='"+requestToCheck.getRequestStringNumber()+"'AND stageManager='"+ user.getId()+"')";
			ResultSet rs = icmDB.executeQuery(queryMsg);

			try {
				if(rs.next()) {
					if(rs.getInt(1) == requestToCheck.getRequestNumber()) {
						rs.close();
						return true;
					}
					else {	
						rs.close();
						return false;
					}
				}
				
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		}
	
		/**
		 * Checks if is this user responsible.
		 *
		 * @param requestToCheck the request to check
		 * @param user the user
		 * @param icmDB the icm DB
		 * @param tableName the table name
		 * @return true, if successful
		 */
		public static boolean IsThisUserResponsible(Request requestToCheck,User user,SqlConnection icmDB, String tableName) {
			String queryMsg = "SELECT requestNumber FROM icm_db."+ tableName +" WHERE (requestNumber = '"+requestToCheck.getRequestNumber()+"' AND stageManager='"+ user.getId()+ "')";
			ResultSet rs = icmDB.executeQuery(queryMsg);
			try {
				if(rs.next())
				{
					if(rs.getInt(1) == requestToCheck.getRequestNumber()) 
					{
						return true;
					}					
				}
				return false;
			
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		/**
		 * Sets the the extention request details.
		 *
		 * @param specificRequest the specific request
		 * @param icmDB the icm DB
		 * @return the DB message
		 */
		public static DBMessage setTheExtentionRequestDetails(Request specificRequest, SqlConnection icmDB) {
			DBMessage returnMsg;
			String query;
			String todayDate =AssessmentQueries.getcurrentDateAsString();
			switch (specificRequest.getStatus())
			{
				case "assessment":
				{
					query = "UPDATE icm_db.assessment SET extensionDate = '"+specificRequest.getAssessment().getExtensionDateString() +"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
					icmDB.executeUpdate(query);
					query = "UPDATE icm_db.assessment SET extensionReason = '"+specificRequest.getAssessment().getExtensionReason()+"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
					icmDB.executeUpdate(query);
					//message
					String messageContent = "Dear "+specificRequest.getSupervisor().getFirstName()+",\nExtension request for request number:"+specificRequest.getRequestStringNumber()+" was submitted\nyou need to approve or reject.";
					ICMMessage messageToSend = new ICMMessage(specificRequest.getSupervisor().getId(),todayDate,messageContent,"supervisor",specificRequest.getSupervisor().getId());
					String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
					icmDB.executeUpdate(msg);
					//mail
					String emailSubject = "New extension request submitted";
					try {
						SendMailController.sendEmail(specificRequest.getSupervisor().getMail(), emailSubject, messageContent);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					break;
				}
				case "reviewassessment":
				{
					query = "UPDATE icm_db.reviewassessment SET extensionDate = '"+specificRequest.getReviewAssessment().getExtensionDateString() +"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
					icmDB.executeUpdate(query);
					query = "UPDATE icm_db.reviewassessment SET extensionReason = '"+specificRequest.getReviewAssessment().getExtensionReason()+"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
					icmDB.executeUpdate(query);
					//message
					String messageContent = "Dear "+specificRequest.getSupervisor().getFirstName()+",\nExtension request for request number:"+specificRequest.getRequestStringNumber()+" was submitted\nyou need to approve or reject.";
					ICMMessage messageToSend = new ICMMessage(specificRequest.getSupervisor().getId(),todayDate,messageContent,"supervisor",specificRequest.getSupervisor().getId());
					String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
					icmDB.executeUpdate(msg);
					//mail
					String emailSubject = "New extension request submitted";
					try {
						SendMailController.sendEmail(specificRequest.getSupervisor().getMail(), emailSubject, messageContent);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					break;
				}
				case "examination":
				{
					query = "UPDATE icm_db.examination SET extensionDate = '"+specificRequest.getExamination().getExtensionDateString() +"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
					icmDB.executeUpdate(query);
					query = "UPDATE icm_db.examination SET extensionReason = '"+specificRequest.getExamination().getExtensionReason()+"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
					icmDB.executeUpdate(query);
					//message
					String messageContent = "Dear "+specificRequest.getSupervisor().getFirstName()+",\nExtension request for request number:"+specificRequest.getRequestStringNumber()+" was submitted\nyou need to approve or reject.";
					ICMMessage messageToSend = new ICMMessage(specificRequest.getSupervisor().getId(),todayDate,messageContent,"supervisor",specificRequest.getSupervisor().getId());
					String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
					icmDB.executeUpdate(msg);
					//mail
					String emailSubject = "New extension request submitted";
					try {
						SendMailController.sendEmail(specificRequest.getSupervisor().getMail(), emailSubject, messageContent);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					break;
				}
				case "execution":
				{
					query = "UPDATE icm_db.execution SET extensionDate = '"+specificRequest.getExecution().getExtensionDateString() +"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
					icmDB.executeUpdate(query);
					query = "UPDATE icm_db.execution SET extensionReason = '"+specificRequest.getExecution().getExtensionReason()+"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
					icmDB.executeUpdate(query);
					//message
					String messageContent = "Dear "+specificRequest.getSupervisor().getFirstName()+",\nExtension request for request number:"+specificRequest.getRequestStringNumber()+" was submitted\nyou need to approve or reject.";
					ICMMessage messageToSend = new ICMMessage(specificRequest.getSupervisor().getId(),todayDate,messageContent,"supervisor",specificRequest.getSupervisor().getId());
					String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
					icmDB.executeUpdate(msg);
					//mail
					String emailSubject = "New extension request submitted";
					try {
						SendMailController.sendEmail(specificRequest.getSupervisor().getMail(), emailSubject, messageContent);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					break;
				}
				default:
					break;
			}
			returnMsg = new DBMessage(DBAction.setExtentionRequest,specificRequest);
			return returnMsg;
		}
		
		/**
		 * Review assessment reject update.
		 *
		 * @param specificRequest the specific request
		 * @param icmDB the icm DB
		 * @return the DB message
		 */
		public static DBMessage reviewAssessmentRejectUpdate(Request specificRequest,SqlConnection icmDB) {
			DBMessage returnMsg = new DBMessage(DBAction.setTheReject,specificRequest);
			String todayDate = AssessmentQueries.getcurrentDateAsString();
	        ExecutionQueries.updateIsDoneFlag(specificRequest, icmDB);
	        CheckIfTheStageExceededTheDueDate(specificRequest, icmDB);
	        String query = "UPDATE icm_db.reviewassessment SET decision = 'reject' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
	        icmDB.executeUpdate(query);	
	        query = "INSERT INTO icm_db.closing (requestNumber,stageManager,startDate,dueDate,extension,extensionTime,extensionReason,closingResult,closingcol,IsDone,IsLate)"
	        		+ " VALUES ("+specificRequest.getRequestStringNumber()+","+specificRequest.getSupervisor().getId()+",'"+todayDate+"','0000-00-00',0,0,null,'reject in review assessment',0,0,0)";
	        icmDB.executeUpdate(query);
	        query = "UPDATE icm_db.requests SET status = 'closing' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
	        icmDB.executeUpdate(query);
	        query = "UPDATE icm_db.requests SET rejected = '1' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
	        icmDB.executeUpdate(query);
	        query="DELETE FROM icm_db.execution WHERE requestNumber = '"+specificRequest.getRequestNumber()+"';";
	        icmDB.executeUpdate(query);
	    	//message
			String messageContent = "Dear "+specificRequest.getSupervisor().getFirstName()+",\nthe execution for request number:"+specificRequest.getRequestStringNumber()+" was rejected by the reviewer,\n the request status update to: closing stage.";
			ICMMessage messageToSend = new ICMMessage(specificRequest.getSupervisor().getId(),todayDate,messageContent,"supervisor",specificRequest.getSupervisor().getId());
			String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
			icmDB.executeUpdate(msg);
			//mail
			String emailSubject = "Reviewer reject request";
			try {
				SendMailController.sendEmail(specificRequest.getSupervisor().getMail(), emailSubject, messageContent);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return returnMsg;
		}
	
		/**
		 * Check if the stage exceeded the due date.
		 *
		 * @param request the request
		 * @param icmDB the icm DB
		 */
		private static void CheckIfTheStageExceededTheDueDate(Request request, SqlConnection icmDB)
		{
			String query = "Select IsLate FROM icm_db."+request.getStatus()+" WHERE requestNumber = "+request.getRequestNumber()+";";
			ResultSet rs = icmDB.executeQuery(query);
			int lengthOfDelay =0;
				try {
					if(rs.next())
					{
						if(rs.getInt(1)==1) //If the due date has passed, the delay time in the delays table is updated
						{
							GregorianCalendar calendar = new GregorianCalendar();
							try 
							{
								lengthOfDelay = betweenDates(request.getReviewAssessment().getDueDate(), calendar.getTime());
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
		 * Review assessment more details.
		 *
		 * @param specificRequest the specific request
		 * @param icmDB the icm DB
		 * @return the DB message
		 */
		public static DBMessage reviewAssessmentMoreDetails(Request specificRequest,SqlConnection icmDB) {
			String todayDate = AssessmentQueries.getcurrentDateAsString();
			DBMessage returnMsg = new DBMessage(DBAction.setTheReviewDetails,specificRequest);
			//message
			String messageContent = "Dear "+specificRequest.getAssessment().getStageManager().getFirstName()+",\nThe reviewer wants more details:"+specificRequest.getReviewAssessment().getExtraDetails()+" about request number:"+specificRequest.getRequestStringNumber()+", the application goes back to the assessment stage";
			ICMMessage messageToSend = new ICMMessage(specificRequest.getAssessment().getStageManager().getId(),todayDate,messageContent,"assessor",specificRequest.getAssessment().getStageManager().getId());
			String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
			icmDB.executeUpdate(msg);
			//mail
			String emailSubject = "Reviewer ask for more details";
			try {
			SendMailController.sendEmail(specificRequest.getAssessment().getStageManager().getMail(), emailSubject, messageContent);
			}
			catch(Exception e) {
			      e.printStackTrace();
			}

			String query = "DELETE FROM icm_db.reviewassessment WHERE requestNumber ='"+specificRequest.getRequestStringNumber()+"'";
			icmDB.executeUpdate(query);
			//return the request to start the assessment stage
			        query = "UPDATE icm_db.assessment SET startDate = '"+todayDate+"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
			        icmDB.executeUpdate(query);
			        query = "UPDATE icm_db.assessment SET dueDate = '0000-00-00' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
			        icmDB.executeUpdate(query);
			        query = "UPDATE icm_db.assessment SET assessmentReport = null WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
			        icmDB.executeUpdate(query);
			        query = "UPDATE icm_db.assessment SET supervisorDueDateApprove = '0' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
			        icmDB.executeUpdate(query);  
			        query = "UPDATE icm_db.assessment SET IsDone = '0' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
			        icmDB.executeUpdate(query);
			        query = "UPDATE icm_db.assessment SET IsLate = '0' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
			        icmDB.executeUpdate(query);
			        query = "UPDATE icm_db.requests SET status = 'assessment' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
			        icmDB.executeUpdate(query);

			return returnMsg;
			}

	
	/**
	 * Review assessment update examination.
	 *
	 * @param specificRequest the specific request
	 * @param icmDB the icm DB
	 * @return the DB message
	 */
	public static DBMessage reviewAssessmentUpdateExamination(Request specificRequest, SqlConnection icmDB) {
		DBMessage returnMsg = new DBMessage(DBAction.setTheReviewCompletion,specificRequest);
		String todayDate = AssessmentQueries.getcurrentDateAsString();
		String  query;
		updateIsDoneFlag(specificRequest, icmDB);
		CheckIfTheStageExceededTheDueDate(specificRequest, icmDB);
        query = "INSERT INTO icm_db.examination (requestNumber,stageManager)"
        		+ " VALUES ("+specificRequest.getRequestStringNumber()+","+specificRequest.getExamination().getStageManager().getId()+")";
        icmDB.executeUpdate(query);
		
        query = "UPDATE icm_db.requests SET status = 'execution' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
        icmDB.executeUpdate(query);		
		
        query = "UPDATE icm_db.execution SET startDate = '"+todayDate+"' WHERE requestNumber = '"+specificRequest.getRequestNumber()+"'";
        icmDB.executeUpdate(query);	
		//message
		String messageContent = "Dear "+specificRequest.getExecution().getStageManager().getFirstName()+",\nThe reviewer update the request number:"+specificRequest.getRequestStringNumber()+"status\n to the execution stage.";
		ICMMessage messageToSend = new ICMMessage(specificRequest.getExecution().getStageManager().getId(),todayDate,messageContent,"executor",specificRequest.getExecution().getStageManager().getId());
		String msg = MessagesQueries.sendMessage(messageToSend,icmDB);
		icmDB.executeUpdate(msg);
		//mail
		String emailSubject = "Reviewer request update to execution";
		try {
			SendMailController.sendEmail(specificRequest.getExecution().getStageManager().getMail(), emailSubject, messageContent);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        
		return returnMsg;
	}
		
	/**
	 * Update is done flag.
	 *
	 * @param request the request
	 * @param icmDB the icm DB
	 */
	static void updateIsDoneFlag(Request request, SqlConnection icmDB)
	{
		String query = "UPDATE icm_db."+request.getStatus()+" SET IsDone = 1 WHERE requestNumber = '"+request.getRequestNumber()+"'";
		icmDB.executeUpdate(query);	
	}

		/**
		 *  this function update the status of the request in the db*.
		 *
		 * @param req the req
		 * @param icmDB the icm DB
		 * @return the DB message
		 */
		
		public static DBMessage updateStatusInDB(Request req,SqlConnection icmDB) {
			DBMessage returnMsg;
			String query;
			boolean ans;
			query = "UPDATE icm_db.requests SET status = '"+req.getStatus()+"' WHERE requestNumber = '"+req.getRequestNumber()+"'";
			if(icmDB.executeUpdate(query)==1) {
			//	System.out.println(" q-true");
				ans=true;
			}
			else {
			//	System.out.println(" q-false");
				ans=false;
			}
			returnMsg= new DBMessage(DBAction.updateStatusInDB,ans);
			return returnMsg;
		}
		
		
		/**
		 * Suspend request.
		 *
		 * @param req the req
		 * @param icmDB the icm DB
		 * @return the DB message
		 * @throws SQLException the SQL exception
		 */
		public static DBMessage suspendRequest(Request req,SqlConnection icmDB) throws SQLException {
			String todayDate = AssessmentQueries.getcurrentDateAsString();
			DBMessage returnMsg,updateStatus;
			String query,query1,query2;
			boolean ans=false;
			 query1 = "Select stilSuspend FROM icm_db.suspend WHERE requestNumber = '"+req.getRequestStringNumber()+"';";
			ResultSet rs = icmDB.executeQuery(query1);
			if(rs.next()) {
				query2 = "UPDATE icm_db.suspend SET stilSuspend = '1' WHERE requestNumber = '"+req.getRequestStringNumber()+"';";
			       if( icmDB.executeUpdate(query2)==1) {
			    	   req.setStatus("suspend");
			    	   updateStatus=updateStatusInDB(req,icmDB);
			    	   if((boolean)updateStatus.Data==true)
			    	   	{
							ans=true;
						}
			       }
			}
			else {
				query = "INSERT INTO icm_db.suspend (requestNumber,startDate,stilSuspend,prevStatus) VALUES ('"+req.getRequestStringNumber()+"','"+todayDate+"','1','"+req.getStatus()+"')";
		       if( icmDB.executeUpdate(query)==1)
		       {
		    	   req.setStatus("suspend");
		    	   updateStatus=updateStatusInDB(req,icmDB);
		    	   if((boolean)updateStatus.Data==true)
		    	   	{
						ans=true;
					}
		       }
			}
			
			returnMsg= new DBMessage(DBAction.suspendRequest,ans);
			return returnMsg;
		}
		
		
		/**
		 * Check my requests.
		 *
		 * @param user the user
		 * @param icmDB the icm DB
		 * @return the DB message
		 * @throws SQLException the SQL exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public static DBMessage CheckMyRequests(User user, SqlConnection icmDB) throws SQLException, IOException
		{
			DBMessage returnMsg;
			String query = "Select status FROM icm_db.requests WHERE applicantId = '" + Integer.parseInt(user.getId())+"' and requestNumber= '"+user.getIdRequest()+"';";
			ResultSet rs = icmDB.executeQuery(query);
			String status= "nul";
				if(rs.next())
					status=rs.getString(1);
				returnMsg= new DBMessage(DBAction.CheckMyRequestt,status);
				return returnMsg;	
			
		}
		
		/**
		 * Unsuspend request.
		 *
		 * @param req the req
		 * @param icmDB the icm DB
		 * @return the DB message
		 * @throws SQLException the SQL exception
		 */
		public static DBMessage unsuspendRequest(Request req,SqlConnection icmDB) throws SQLException
		{
			String todayDate = AssessmentQueries.getcurrentDateAsString();
			DBMessage returnMsg,updateStatus;
			String query,query1;
			boolean ans=false;
			query = "UPDATE icm_db.suspend SET dueDate = '"+todayDate+"',stilSuspend='0'  WHERE requestNumber = '"+req.getRequestNumber()+"'";
		       if( icmDB.executeUpdate(query)==1)
		       {
		    	   query1 = "Select prevStatus FROM icm_db.suspend WHERE requestNumber = '"+req.getRequestNumber()+"'";
					ResultSet rs = icmDB.executeQuery(query1);
					 if(rs.next()) {
						 req.setStatus( rs.getString(1));
						 updateStatus=updateStatusInDB(req,icmDB);
				    	   if((boolean)updateStatus.Data==true)
				    	   	{
								ans=true;
							}
					 }
		       }
			returnMsg= new DBMessage(DBAction.unsuspendRequest,ans);
			return returnMsg;				 
		}
		
		
		
		

}