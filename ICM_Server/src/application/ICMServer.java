package application;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import MyFile.MyFile;
import entities.DBMessage;
import entities.DBMessage.DBAction;
import entities.Document;
import entities.ICMMessage;
import entities.Report;
import entities.Request;
import entities.User;
import entities.UserList;
import entitiesQueries.AssessmentQueries;
import entitiesQueries.ClosingQueries;
import entities.UserList;
import entitiesQueries.DocumentsQueries;
import entitiesQueries.ExaminationQueries;
import entitiesQueries.ExecutionQueries;
import entitiesQueries.MessagesQueries;
import entitiesQueries.ReportQueries;
import entitiesQueries.RequestsQueries;
import entitiesQueries.StageQueries;
import entitiesQueries.UsersQueries;
import javafx.scene.control.TextArea;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

// TODO: Auto-generated Javadoc
/**
 * This is the heart of ICM server side. in this class we get every message from
 * client and handle it.
 */
public class ICMServer extends AbstractServer 
{	 
	
	/** The log. */
	//Class variables *************************************************
	public TextArea log = null; 
	
	/** The icm DB. */
	private SqlConnection icmDB;
	
	/**
	 * Constructs an instance of the ICM server.
	 *
	 * @param port The port number to connect on.
	 * @param log the log
	 */

	public ICMServer(int port, TextArea log)
	{
		super(port);
		this.log = log;
	}
	
	/**
	 * this function connect to the Data base. the name of the DB have to be
	 * "icm_db". Another thing is that we log off all user when first connecting to
	 * DB.
	 *
	 * @param dbName the db name
	 * @param dbPassword the db password
	 * @param userName the user name
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 */
	public void connectToDB(String dbName, String dbPassword, String userName)throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		icmDB = new SqlConnection(dbName, dbPassword, userName);
		if (isDBRunning())
		{
			// if server was off -> all users are disconnected.
			icmDB.executeUpdate("UPDATE icm_db.users SET loginStatus = 'off'");
		}
	}
	
	  /** This method handles any messages received from the client.
	   * @param msg The message received from the client.
	   * @param client The connection from which the message originated.  */ 
	  public void handleMessageFromClient(Object msg, ConnectionToClient client)
	{
		  log.setText("Message received from client: " + client + System.lineSeparator() + log.getText());
		  DBMessage dbMessage = (DBMessage) msg;
		  if (dbMessage.Action == DBAction.isDBRuning)
			{
				try
				{
					client.sendToClient(new DBMessage(DBAction.isDBRuning, isDBRunning()));
					return;
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			// update gui for getting message
			if (!isDBRunning())
				return;
		  try
			{
				switch (dbMessage.Action)
				{
					case CheckUser:
					{
						checkIfUserExist((User)dbMessage.Data, client);
						break;
					}
					case UpdateUserLogout:
					{
						updateUserLogout((User) dbMessage.Data, client);

						break;
					}
					case getNotificationsForUser:
					{
						getNotifications((User)dbMessage.Data, client);
						break;
					}
					case removeMsg:
					{
						removeMessage((String)dbMessage.Data, client);
						break;
					}
					case CreateNewRequest:
					{
						submitRequest((Request)dbMessage.Data, client);
						break;
					}
					case getRequestsListForSupervisor:{
					//	System.out.println("server"+((User)dbMessage.Data).toString());
						getAllRequests((User)dbMessage.Data,client);
					}
					case CheckRequestNumber:
					{
						checkIfRequestExist((User)dbMessage.Data, client);
						break;
					}
					case NewRequestMsgSpvsr: {
						NewMsgSpvsr((ICMMessage) dbMessage.Data, client);
						break;
					}
					case Files: {
						SubmitDoc((MyFile)dbMessage.Data, client);
						break;
					}
					case FindMaxNumOfIdRequest: {
						FindMaxRqst(client);
						break;
					}
					case FindMaxNumOfIdMsg: {
						FindMaxMsg(client);
						break;
					}

					case OpenFile: {
						Open_file((int)dbMessage.Data, client);
						break;
					}
					case getRequestsListForAssessor:{
						getAssessorRequests((User)dbMessage.Data,client);
						break;
					}

					case setAssessmentDueDate:{
						setAssessorDate((Request)dbMessage.Data,client);
					break;
					}
					case setReport:{
						setAssessorReport((Request)dbMessage.Data,client);
						break;
					}
					case setExtentionRequest:{
						setTheExtentionRequestInDB((Request)dbMessage.Data,client);
						break;
					}

					case setDueDateForExecuteStage:
					{
						setDueDate((Request)dbMessage.Data, client);
						break;
					}
					case updateStageCompletion:
					{
						stageCompletion((Request)dbMessage.Data, client);
						break;
					}
					case getRequestsListForExecutor:
					{
						getExecutorRequests((User)dbMessage.Data, client);
						break;
					}
					case getRequestsListForReviewAssessment:{
						getReviewAssessmentRequests((User)dbMessage.Data, client);
						break;
					}		
					case setTheReject:
					{
						reviewAssessmentReject((Request)dbMessage.Data, client);
						break;
					}
					case setTheReviewDetails:{
						ReviewAskforMoreDetails((Request)dbMessage.Data, client);
						break;
					}
					case setTheReviewCompletion:{
						ReviewUpdateExamination((Request)dbMessage.Data, client);
						break;
					}
					case getemployeesListForassessorComboBox: {
						getemployeesListForassessorComboBox((String)dbMessage.Data, client);
						break;
						}
					case checkIfAppoitmentWasMade:{
						checkIfAppoitmentWasMade(client);
						break;
					}
					case updateUserSystemMaintenance: {
						updateUserSystemMaintenance((User)dbMessage.Data, client);
						break;
					}
					case getEmployeesForComboBox: {
						getEmployeesForComboBox((User)dbMessage.Data,client);
						break;
					}
					case updateAppointment: {
						//System.out.println("sever-switch"+((Request)(dbMessage.Data)).toString());
						updateAppointmentEmployees((UserList)dbMessage.Data,client);
						break;
					}
					case createsAssessmentAndExecutionstage: {
						//System.out.println("sever-switch"+((Request)(dbMessage.Data)).toString());
						createsAssessmentAndExecutionstage((Request)dbMessage.Data,client);
						break;
					}
					case updateStatusInDB: {
						//System.out.println("sever-switch");
						updateStatusInDB((Request)dbMessage.Data,client);
						break;
					}
					case Activity:
					{
						GetActivityReport((Report)dbMessage.Data, client);
						break;
					}
					case Delays:
					{
						GetDelaysReport((Report)dbMessage.Data, client);
						break;
					}
					case getAllEmployees:
					{
						getAllEmployeesList((User)dbMessage.Data,client);
						break;
					}
					case getRequestsListForExaminer:
					{
						getExaminerRequests((User)dbMessage.Data, client);
						break;
					}
					case returnToExecutorStage:
					{
						returnToExecutorStage((Request)dbMessage.Data, client);
						break;
					}
					case setDueDateForStage:
					{
						System.out.println("server switch");
						setDueDateForStage((Request)dbMessage.Data, client);
						break;
					}
					case getDueDate:
					{
						getDueDate((Request)dbMessage.Data, client);
					}
					case getNamesOfEmployees:
					{
						getNamesOfEmployees(client);
						break;
					}
					/*case getExtentionForSupervisor:
					{
						getExtentionForSupervisor((Request)dbMessage.Data, client);
						break;
					}*/
					case aprroveExtention:
					{
						aprroveExtention((Request)dbMessage.Data, client);
						break;
					}
					case rejectExtention:
					{
						rejectExtention((Request)dbMessage.Data, client);
						break;
					}
					case suspendRequest:
					{
						suspendRequest((Request)dbMessage.Data, client);
						break;
					}

					case CheckMyRequestt:
					{
						CheckMyRequest((User)dbMessage.Data, client);
						break;
					}
					case DelaysRequestReport:
					{
						DelaysRequestReportfunc((Report)dbMessage.Data, client);
					break;
					}
					case closingRequest:
					{
						closingRequest((Request)dbMessage.Data, client);
						break;
					}
					case unsuspendRequest:
					{
						unsuspendRequest((Request)dbMessage.Data, client);
						break;
					}

					default:
						break;
				}
			}
		  catch (Exception e)
			{
				e.printStackTrace();
				
			}
	}

	/**
	 * Removes the message.
	 *
	 * @param msgToRemove the msg to remove
	 * @param client the client
	 */
	private void removeMessage(String msgToRemove, ConnectionToClient client) 
	  {
			MessagesQueries.removeMsg(msgToRemove,icmDB);
	  }
	
	/**
	 * Update user logout.
	 *
	 * @param userToUpdate the user to update
	 * @param client the client
	 */
	private void updateUserLogout(User userToUpdate, ConnectionToClient client) 
		{
			if (userToUpdate == null)
				return;
			userToUpdate.setLoginStatus("off");
			String query = UsersQueries.updateUserloginStatus(userToUpdate);
			icmDB.executeUpdate(query);
		}
	  
		/**
		 * Gets the notifications.
		 *
		 * @param user the user
		 * @param client the client
		 * @return the notifications
		 * @throws SQLException the SQL exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		private void getNotifications(User user, ConnectionToClient client) throws SQLException, IOException {
			DBMessage returnMsg = MessagesQueries.getNotifications(user,icmDB);
			client.sendToClient(returnMsg);
		
	} 
		
		/**
		 * Submit doc.
		 *
		 * @param msg the msg
		 * @param client the client
		 */
		public void SubmitDoc(MyFile msg, ConnectionToClient client) {
			int fileSize = msg.getSize();
			System.out.println("Message received: " + msg + " from " + client);
			System.out.println("length " + fileSize);

			MyFile ff = msg;
			

			try {//File file = new File("D:/Data/" + item.getFileName());
				
				
				StringBuilder str=new StringBuilder(ff.getFileName());
				String str1=  str.substring(str.lastIndexOf("\\"),str.length());
				
				String path="C:\\icm_after\\" + "IDRequest-"+msg.getRequestID();
				String path1="C:\\icm_after";

				String path2="C:\\icm_after\\" + "IDRequest-"+msg.getRequestID()+"\\"+str1;
				
				System.out.println(path+ " "+ path2);
				File dir1= new File(path1);
				if((!dir1.exists()))dir1.mkdir();
				
				File dir= new File(path);
				if((!dir.exists()))dir.mkdir();
				
				
				FileOutputStream fos = new FileOutputStream(path2);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				//bos.write(ff.getMybytearray(), 0, ff.getSize());
				DataOutputStream dataOutputStream= new DataOutputStream(bos);
				dataOutputStream.write(ff.getMybytearray(), 0, ff.getSize());
				dataOutputStream.close();
				Document doc= new Document(path2, msg.getRequestID());
				DBMessage returnMsg = DocumentsQueries.InsertDocToDB(doc, icmDB);
				System.out.println((int)returnMsg.Data + "serverdoc");
				client.sendToClient(returnMsg);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		/**
		 * Find max rqst.
		 *
		 * @param client the client
		 * @throws SQLException the SQL exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void FindMaxRqst(ConnectionToClient client) throws SQLException, IOException {
			DBMessage returnMsg = RequestsQueries.FindMaxReqst(icmDB);
			System.out.println("count server rqst"+ returnMsg.Data);
			client.sendToClient(returnMsg);

		}
		
		/**
		 * Find max msg.
		 *
		 * @param client the client
		 * @throws SQLException the SQL exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void FindMaxMsg(ConnectionToClient client) throws SQLException, IOException {
			DBMessage returnMsg = MessagesQueries.FindMaxMsg(icmDB);
			System.out.println(returnMsg.Data);
			client.sendToClient(returnMsg);
		}
		
		/**
		 * New msg spvsr.
		 *
		 * @param msg the msg
		 * @param client the client
		 * @throws SQLException the SQL exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void NewMsgSpvsr(ICMMessage msg, ConnectionToClient client) throws SQLException, IOException {
			DBMessage returnMsg = MessagesQueries.insertNewMsgToDB(msg, icmDB);
		//	DBMessage returnMsg = MessagesQueries.sendMessage(messageToSend, icmDB)(msg, icmDB);

			client.sendToClient(returnMsg);
		}
	  
		/**
		 * Check if user exist.
		 *
		 * @param userToCheck the user to check
		 * @param client the client
		 * @throws SQLException the SQL exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void checkIfUserExist(User userToCheck, ConnectionToClient client) throws SQLException, IOException
		{
			DBMessage returnMsg = UsersQueries.checkIfUserExist(userToCheck,icmDB);
			client.sendToClient(returnMsg);
		}
		
		/*public void submitNewRequestf(Request request, ConnectionToClient client) throws SQLException, IOException
		{
			DBMessage returnMsg = UsersQueries.checkIfUserExist(userToCheck,icmDB);
			client.sendToClient(returnMsg);
		}*/
		
		/**
		 * Checks if is DB running.
		 *
		 * @return true, if is DB running
		 */
		public boolean isDBRunning()
		{
			if (icmDB == null)
				return false;
			return icmDB.IsConnectionSucceeded();
		}
		
		  /**
  		 * Delays request reportfunc.
  		 *
  		 * @param report the report
  		 * @param client the client
  		 * @throws SQLException the SQL exception
  		 * @throws IOException Signals that an I/O exception has occurred.
  		 * @throws ParseException the parse exception
  		 */
  		public void DelaysRequestReportfunc(Report report, ConnectionToClient client) throws SQLException, IOException, ParseException
				{
					//DBMessage returnMsg = ReportQueries.DelaysRequestReport(report, icmDB);
					//System.out.println("good server down");
					//client.sendToClient(returnMsg);
				}
		  
		/**
		 * Submit request.
		 *
		 * @param request the request
		 * @param client the client
		 * @throws SQLException the SQL exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void submitRequest(Request request, ConnectionToClient client) throws SQLException, IOException
		{
			DBMessage returnMsg = RequestsQueries.insertRequestToDB(request,icmDB);
			client.sendToClient(returnMsg);
		}
		
		  /**
  		 * Server started.
  		 */
  		protected void serverStarted() /*This method overrides the one in the superclass. Called when the server starts listening for connections.*/
		  {
		    System.out.println("Server listening for connections on port " + getPort());
		  }

		  /**
  		 * Server stopped.
  		 */
  		protected void serverStopped() /*This method overrides the one in the superclass. Called when the server stops listening for connections.*/
		  {
		    System.out.println("Server has stopped listening for connections.");
		  }
	  
	  /**
  	 * Gets the all requests.
  	 *
  	 * @param UserLogedIn the user loged in
  	 * @param client the client
  	 * @return the all requests
  	 * @throws Exception the exception
  	 */
  	//returns to the client controller the list of requests
	  public void getAllRequests(User UserLogedIn ,ConnectionToClient client) throws Exception {
		   DBMessage returnMsg = RequestsQueries.getSupervisorRequest(UserLogedIn,icmDB);
			client.sendToClient(returnMsg);
	   }
	  
		
		/**
		 * Check if request exist.
		 *
		 * @param user the user
		 * @param client the client
		 * @throws SQLException the SQL exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void checkIfRequestExist(User user, ConnectionToClient client) throws SQLException, IOException
		{
			DBMessage returnMsg = RequestsQueries.CheckIfRequestExist(user, icmDB);
			client.sendToClient(returnMsg);
		}
		
		/**
		 * Gets the connection.
		 *
		 * @return the connection
		 */
		public SqlConnection getConnection()
		{
			return icmDB;
		}
		

		  /**
  		 * Gets the assessor requests.
  		 *
  		 * @param user the user
  		 * @param client the client
  		 * @return the assessor requests
  		 * @throws Exception the exception
  		 */
  		public void getAssessorRequests(User user,ConnectionToClient client) throws Exception {
			   DBMessage returnMsg = RequestsQueries.getAssessorRequest(user,icmDB);
			   client.sendToClient(returnMsg);

		  }
		  
  		/**
  		 * Sets the assessor date.
  		 *
  		 * @param specificRequest the specific request
  		 * @param client the client
  		 * @throws Exception the exception
  		 */
  		public void setAssessorDate(Request specificRequest,ConnectionToClient client)  throws Exception {
			   DBMessage returnMsg = AssessmentQueries.setTheAssessmentDueDate(specificRequest,icmDB);
			   client.sendToClient(returnMsg);
		  }
		  
		  
		  /**
  		 * Sets the assessor report.
  		 *
  		 * @param specificRequest the specific request
  		 * @param client the client
  		 * @throws Exception the exception
  		 */
  		public void setAssessorReport(Request specificRequest,ConnectionToClient client)  throws Exception{
			   DBMessage returnMsg = AssessmentQueries.setTheAssessmentReport(specificRequest,icmDB);
			   client.sendToClient(returnMsg);	  
		  }
		  
		  /**
  		 * Sets the the extention request in DB.
  		 *
  		 * @param specificRequest the specific request
  		 * @param client the client
  		 * @throws Exception the exception
  		 */
  		public void setTheExtentionRequestInDB(Request specificRequest,ConnectionToClient client)  throws Exception{
			   DBMessage returnMsg = RequestsQueries.setTheExtentionRequestDetails(specificRequest,icmDB);
			   client.sendToClient(returnMsg);	 
		  }

		/**
		 * Sets the due date.
		 *
		 * @param request the request
		 * @param client the client
		 */
		public void setDueDate(Request request, ConnectionToClient client)
		{
			ExecutionQueries.setTheExecutionDueDate(request, icmDB);	
		}
		
		/**
		 * Stage completion.
		 *
		 * @param request the request
		 * @param client the client
		 */
		public void stageCompletion (Request request, ConnectionToClient client)
		{
			ExecutionQueries.updateStageCompletion(request, icmDB);
		}
		
		/**
		 * Gets the executor requests.
		 *
		 * @param executor the executor
		 * @param client the client
		 * @return the executor requests
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void getExecutorRequests(User executor, ConnectionToClient client) throws IOException
		{
			DBMessage returnMsg = RequestsQueries.getRequestsForSpecificRole(executor, icmDB, "execution", "executor");
			client.sendToClient(returnMsg);
			try {
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		/**
		 * Gets the review assessment requests.
		 *
		 * @param reviewer the reviewer
		 * @param client the client
		 * @return the review assessment requests
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void getReviewAssessmentRequests(User reviewer, ConnectionToClient client) throws IOException{
			DBMessage returnMsg = RequestsQueries.getRequestsForSpecificRole(reviewer, icmDB, "reviewassessment", "reviewer");
			client.sendToClient(returnMsg);
		}

		/**
		 * Review assessment reject.
		 *
		 * @param request the request
		 * @param client the client
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void reviewAssessmentReject(Request request, ConnectionToClient client) throws IOException{
			DBMessage returnMsg = RequestsQueries.reviewAssessmentRejectUpdate(request, icmDB);
			client.sendToClient(returnMsg);
		}
		
		/**
		 * Review askfor more details.
		 *
		 * @param request the request
		 * @param client the client
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void ReviewAskforMoreDetails(Request request, ConnectionToClient client) throws IOException {
			DBMessage returnMsg = RequestsQueries.reviewAssessmentMoreDetails(request, icmDB);
			client.sendToClient(returnMsg);
		}
		
		/**
		 * Review update examination.
		 *
		 * @param request the request
		 * @param client the client
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void ReviewUpdateExamination(Request request, ConnectionToClient client) throws IOException  {
			DBMessage returnMsg = RequestsQueries.reviewAssessmentUpdateExamination(request, icmDB);
			client.sendToClient(returnMsg);
		}
		
		/**
		 * Gets the employees for combo box.
		 *
		 * @param user the user
		 * @param client the client
		 * @return the employees for combo box
		 */
		public void  getEmployeesForComboBox(User user,ConnectionToClient client){
			
			DBMessage returnMsg = UsersQueries.getItEmployees(user,icmDB);
			try {
				/*UserList list =(UserList)(returnMsg.Data);
				for(User u:list.user_list) {
					System.out.println("server "+u.toString());
				}*/
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	/**
	 * Update appointment employees.
	 *
	 * @param list the list
	 * @param client the client
	 */
	public void  updateAppointmentEmployees(UserList list,ConnectionToClient client)
	{
			//System.out.println("sever-updateAppointmentEmployee"+use.toString());
			DBMessage returnMsg = UsersQueries.updateAppointmentEmployees(list,icmDB);
			try {
			/*	UserList list =(UserList)(returnMsg.Data);
				for(User u:list.user_list) {
					System.out.println("server "+u.toString());
				}*/
				System.out.println("back form q-"+returnMsg.Data);
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	/**
	 * Creates assessment and executionstage.
	 *
	 * @param req the req
	 * @param client the client
	 */
	public void  createsAssessmentAndExecutionstage(Request req,ConnectionToClient client)
	{
		//System.out.println("sever-updateAppointmentEmployee"+req.toString());
		DBMessage returnMsg = StageQueries.createsAssessmentAndExecutionstage(req,icmDB);
		System.out.println(req.toString());
		if((int)returnMsg.Data==1)
		updateStatusInDB(req,client);
		else
		{		
		try {
			client.sendToClient(returnMsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

		/**
		 * Open file.
		 *
		 * @param IDRequest the ID request
		 * @param client the client
		 * @throws SQLException the SQL exception
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void Open_file(int IDRequest, ConnectionToClient client) throws SQLException, IOException {	
			DBMessage returnMsg = DocumentsQueries.OpenTheFile(IDRequest, icmDB);
			client.sendToClient(returnMsg);
		}
		
		/**
		 * Gets the employees list forassessor combo box.
		 *
		 * @param sysname the sysname
		 * @param client the client
		 * @return the employees list forassessor combo box
		 */
		public void getemployeesListForassessorComboBox(String sysname, ConnectionToClient client) {
			DBMessage returnMsg = UsersQueries.getemployeesListForassessorComboBox(sysname,icmDB);
			try {
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * Check if appoitment was made.
		 *
		 * @param client the client
		 */
		public void checkIfAppoitmentWasMade(ConnectionToClient client) {
			DBMessage returnMsg = UsersQueries.checkIfAppoitmentWasMade(icmDB);
			try {
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * Update user system maintenance.
		 *
		 * @param user the user
		 * @param client the client
		 */
		public void updateUserSystemMaintenance(User user, ConnectionToClient client) {
			DBMessage returnMsg = UsersQueries.updateUserSystemMaintenance(user,icmDB);
			try {
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		  /**
  		 * Gets the activity report.
  		 *
  		 * @param report the report
  		 * @param client the client
  		 * @throws SQLException the SQL exception
  		 * @throws IOException Signals that an I/O exception has occurred.
  		 * @throws ParseException the parse exception
  		 */
  		public void GetActivityReport(Report report, ConnectionToClient client) throws SQLException, IOException, java.text.ParseException
			{
				DBMessage returnMsg = ReportQueries.CreateActivityRequest(report, icmDB);
				client.sendToClient(returnMsg);
			}
		  
			/**
			 * Gets the delays report.
			 *
			 * @param report the report
			 * @param client the client
			 * @throws SQLException the SQL exception
			 * @throws IOException Signals that an I/O exception has occurred.
			 */
			public void GetDelaysReport(Report report, ConnectionToClient client) throws SQLException, IOException
			{
				//DBMessage returnMsg = ReportQueries.CreateDelaysRequest(report, icmDB);
				//client.sendToClient(returnMsg);
			}
		  
			/**
			 * Gets the all employees list.
			 *
			 * @param user the user
			 * @param client the client
			 * @return the all employees list
			 * @throws SQLException the SQL exception
			 * @throws IOException Signals that an I/O exception has occurred.
			 */
			public void getAllEmployeesList (User user, ConnectionToClient client) throws SQLException, IOException
			{
				DBMessage returnMsg = UsersQueries.GetAllEmployeesList(user,icmDB);
				client.sendToClient(returnMsg);
			}
			
			/**
			 * Gets the examiner requests.
			 *
			 * @param executor the executor
			 * @param client the client
			 * @return the examiner requests
			 * @throws IOException Signals that an I/O exception has occurred.
			 */
			public void getExaminerRequests(User executor, ConnectionToClient client) throws IOException
			{
				DBMessage returnMsg = RequestsQueries.getRequestsForSpecificRole(executor, icmDB, "examination", "examiner");
				client.sendToClient(returnMsg);
			}
			
			/**
			 * Return to executor stage.
			 *
			 * @param request the request
			 * @param client the client
			 * @throws IOException Signals that an I/O exception has occurred.
			 */
			public void returnToExecutorStage(Request request,ConnectionToClient client) throws IOException
			{
				ExaminationQueries.returnRequestToExecutorStage(request, icmDB);
			}
		
		/**
		 * Update status in DB.
		 *
		 * @param req the req
		 * @param client the client
		 */
		public void updateStatusInDB(Request req, ConnectionToClient client) {
			//System.out.println("sever-function before q");
			DBMessage returnMsg=RequestsQueries.updateStatusInDB(req,icmDB);
			
			try {
			//	System.out.println("sever-function after q");
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * Gets the due date.
		 *
		 * @param req the req
		 * @param client the client
		 * @return the due date
		 * @throws SQLException the SQL exception
		 */
		public void getDueDate(Request req, ConnectionToClient client) throws SQLException {
			//System.out.println("sever-function before q");
			DBMessage returnMsg=StageQueries.getDueDate(req,icmDB);
			
			try {
			//	System.out.println("sever-function after q");
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/**
		 * Sets the due date for stage.
		 *
		 * @param req the req
		 * @param client the client
		 */
		public void setDueDateForStage(Request req, ConnectionToClient client) {
			DBMessage returnMsg=StageQueries.setDueDateForStage(req,icmDB);
			
			try {
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Gets the names of employees.
		 *
		 * @param client the client
		 * @return the names of employees
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		public void getNamesOfEmployees(ConnectionToClient client) throws IOException
		{
			DBMessage returnMsg = UsersQueries.findNamesOfEmployees(icmDB);
			client.sendToClient(returnMsg);
		}
		
		/**
		 * Aprrove extention.
		 *
		 * @param req the req
		 * @param client the client
		 */
		public void aprroveExtention(Request req, ConnectionToClient client) {
			DBMessage returnMsg=StageQueries.aprroveExtention(req,icmDB);
			
			try {
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Reject extention.
		 *
		 * @param req the req
		 * @param client the client
		 */
		public void rejectExtention(Request req, ConnectionToClient client) {
			DBMessage returnMsg=StageQueries.rejectExtention(req,icmDB);
			try {
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Suspend request.
		 *
		 * @param req the req
		 * @param client the client
		 * @throws SQLException the SQL exception
		 */
		public void suspendRequest(Request req, ConnectionToClient client) throws SQLException {
			DBMessage returnMsg=RequestsQueries.suspendRequest(req,icmDB);
			try {
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Unsuspend request.
		 *
		 * @param req the req
		 * @param client the client
		 * @throws SQLException the SQL exception
		 */
		public void unsuspendRequest(Request req, ConnectionToClient client) throws SQLException {
			DBMessage returnMsg=RequestsQueries.unsuspendRequest(req,icmDB);
			try {
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Check my request.
		 *
		 * @param user the user
		 * @param client the client
		 * @throws IOException Signals that an I/O exception has occurred.
		 * @throws SQLException the SQL exception
		 */
		/*public void getExtentionForSupervisor(Request req, ConnectionToClient client) {
			DBMessage returnMsg=StageQueries.getExtentionForSupervisor(req,icmDB);
			
			try {
				client.sendToClient(returnMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		public void CheckMyRequest(User user, ConnectionToClient client) throws IOException, SQLException
		{
			DBMessage returnMsg = RequestsQueries.CheckMyRequests(user, icmDB);
			client.sendToClient(returnMsg);
		}

		/**
		 * Closing request.
		 *
		 * @param requestToClose the request to close
		 * @param client the client
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
		private void closingRequest(Request requestToClose, ConnectionToClient client) throws IOException 
		  {
			  DBMessage returnMsg = ClosingQueries.closeRequest(requestToClose, icmDB);
			  client.sendToClient(returnMsg);
			}
}
