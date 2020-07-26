package logic;

import java.awt.Desktop;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import MyFile.MyFile;
import entities.DBMessage;
import entities.Document;
import entities.DBMessage.DBAction;
import entities.ICMMessage;
import entities.Report;
import entities.Request;
import entities.User;
import entities.UserList;
import gui.GuiManager;
import javafx.application.Platform;
import ocsf.client.AbstractClient;

/**
 * This is the Controller between the server and the client. Every request from client UI to server will be sent through this. And backwards.
 * This class is a singleton class. You can only create one instance of this
 * class using - "createClientIfNotExist".
 */
public class ClientController extends AbstractClient
{
	final public static int DEFAULT_PORT = 5555;
	private static ClientController singletoneInstance = null;
	/**
	 * Constructs an instance of the ClientController.
	 * @param host the host ip
	 * @param port the port to connect
	 */
	  private ClientController(String host, int port)  
	  {		    
		  super(host, port); //Call the superclass constructor
		  try {
			openConnection();//open the connection with the server
		} catch (IOException e) {
			System.out.println("Connection refused: connect");
		}
	  }
	  /**
		 * Call this instead of the constructor
		 * 
		 * @param host the host ip
		 * @param port the port to connect
		 */
		public static ClientController createClientIfNotExist(String host, int port) throws IOException
		{
			if (singletoneInstance == null)
				singletoneInstance = new ClientController(host, port);
			return singletoneInstance;
		}
    
	    @SuppressWarnings("unused")
		@Override
		public void handleMessageFromServer(Object msg) //This method handles all data that comes in from the server.
	    {	
	    	if (msg == null)
				GuiManager.ShowErrorPopup("Something went wrong.\nPlease restart the program");
			if (!(msg instanceof DBMessage))
				return;// check if the message is DBMessage and not something else
			DBMessage message = (DBMessage) msg;
			DBAction action = message.Action;
			switch (action)
			{
			case isDBRuning:
				GuiManager.dbConnected = (boolean) message.Data;
				break;
			case ShutDown:
				Platform.runLater(() -> {
					GuiManager.ShowErrorPopup("The server was unexpectedly shut down.\n" + "Please restart your progrem.\n"
							+ "Everything you do now will not be saved.");
				});
				break;
			case OpenFile:
				if((String)message.Data==null) {
					GuiManager.CurrentGuiController.getMessageFromServer(message);
				}
				else {
				StringBuilder str=new StringBuilder((String)message.Data);
				String str1=  str.substring(str.lastIndexOf("IDRequest"),str.length());
							String path="C:\\icm_after\\"+str1;
				System.out.println(path);
				//File file = new File("C:\\icm_after\\IDRequest-16\\61756_Grades-Groups-20b-HW1.pdf");
				File file = new File(path);

				Desktop desktop = Desktop.getDesktop();
				if(file.exists())
					try {
						desktop.open(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			default:
				GuiManager.CurrentGuiController.getMessageFromServer(message);
				break;
			}
	    }
	    /**
	     * This method terminates the client.
	     */
	    public void quit()
	    {
	      try
	      {
	        closeConnection();
	      }
	      catch(IOException e) {}
	      System.exit(0);
	    }

		public void CheckValidUser(User user)
		{
			DBMessage message = new DBMessage(DBAction.CheckUser, user);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		  public void SubmitDocuments(MyFile file)
		  {  
			  int fileSize = file.getSize();
				System.out.println("Message received: " + file + " from ");
				System.out.println("length " + fileSize);

				MyFile ff = file;
				

				try {//File file = new File("D:/Data/" + item.getFileName());
					
					
					StringBuilder str=new StringBuilder(ff.getFileName());
					String str1=  str.substring(str.lastIndexOf("\\"),str.length());
					
					String path="C:\\icm_after\\" + "IDRequest-"+file.getRequestID();
					String path1="C:\\icm_after";

					String path2="C:\\icm_after\\" + "IDRequest-"+file.getRequestID()+"\\"+str1;
					
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
					Document doc= new Document(path2, file.getRequestID());
					
					 DBMessage dbMsg = new DBMessage(DBAction.Files, file);
				      sendToServer(dbMsg);	
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error send (Files)msg) to Server");
					e.printStackTrace();
				}
				
		  }
			
		public void CheckIfRequestExist(User userLoged)
		{
			DBMessage message = new DBMessage(DBAction.CheckRequestNumber, userLoged);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		public void DelaysRequestReport (Report report)
		{
			DBMessage message = new DBMessage(DBAction.DelaysRequestReport, report);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}	
		}
		public void CheckMyRequest(User userLoged)
		{
			DBMessage message = new DBMessage(DBAction.CheckMyRequestt, userLoged);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		public void SendMessageToSupervisor(ICMMessage msgSupervisor) {
			DBMessage message = new DBMessage(DBAction.NewRequestMsgSpvsr, msgSupervisor);
			try {
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public void updateUserLogOut(User user)
		{
			DBMessage message = new DBMessage(DBAction.UpdateUserLogout, user);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		public void getNotificationsForUser(User userLogedIn) 
		{
			DBMessage message = new DBMessage(DBAction.getNotificationsForUser, userLogedIn);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}	
		}

		public void removeNotification(String notificationNumber) 
		{
			DBMessage message = new DBMessage(DBAction.removeMsg, notificationNumber);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		public void SubmitNewRequest(Request RequestNum) {
			DBMessage message = new DBMessage(DBAction.CreateNewRequest, RequestNum);
			try {
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
		public void getRequestsList(User user) {
		//	System.out.println(user.toString());
			DBMessage message = new DBMessage(DBAction.getRequestsListForSupervisor, user);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		public void getMaxNumOfRequest() {
			DBMessage message = new DBMessage(DBAction.FindMaxNumOfIdRequest, null);
			try {			

				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public void getMaxNumOfMsg() {
			DBMessage message = new DBMessage(DBAction.FindMaxNumOfIdMsg, null);
			try {			

				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}


		public void OpenFile(int IDRequest) {
			System.out.println(" client controller");
			DBMessage message = new DBMessage(DBAction.OpenFile, IDRequest);
			try {
				System.out.println("send to server");
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}		
		}
		
		public void getRequestsListAssessor(User user) {
			DBMessage message = new DBMessage(DBAction.getRequestsListForAssessor, user);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		
		public void setTheChoosenDueDate(Request specificRequest) {
			DBMessage message = new DBMessage(DBAction.setAssessmentDueDate, specificRequest);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}		
			
		}
		
		public void setTheReport(Request specificRequest) {
			DBMessage message = new DBMessage(DBAction.setReport, specificRequest);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}	
		}
		
		public void setTheExtentionRequest(Request specificRequest) {
			DBMessage message = new DBMessage(DBAction.setExtentionRequest, specificRequest);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}	
		}

		public void setDueDateForExecuteStage(Request specificRequest) 
		{
			DBMessage message = new DBMessage(DBAction.setDueDateForExecuteStage, specificRequest);
			try 
			{			
				sendToServer(message);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}	
			
		}
		public void stageCompletion(Request specificRequest) 
		{
			DBMessage message = new DBMessage(DBAction.updateStageCompletion, specificRequest);
			try 
			{			
				sendToServer(message);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}	
			
		}
		public void getExecutorRequestList(User userLogedIn) {
			DBMessage message = new DBMessage(DBAction.getRequestsListForExecutor, userLogedIn);
			try 
			{			
				sendToServer(message);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}				
		}


		public void getRequestsListReviewAssessment(User userLogedIn){
			DBMessage message = new DBMessage(DBAction.getRequestsListForReviewAssessment, userLogedIn);
			try 
			{			
				sendToServer(message);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}				
		}
		
		
		public void stageReject(Request specificRequest) {
			DBMessage message = new DBMessage(DBAction.setTheReject, specificRequest);
			try 
			{			
				sendToServer(message);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}		
		}
		
		public void AskForMoreDetailsReview(Request spacificRequest) {
			DBMessage message = new DBMessage(DBAction.setTheReviewDetails, spacificRequest);
			try 
			{			
				sendToServer(message);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}	
		}
		public void ReviewstageCompletion(Request spacificRequest) {
			DBMessage message = new DBMessage(DBAction.setTheReviewCompletion, spacificRequest);
			try 
			{			
				sendToServer(message);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}	
		}
		
		
		public void getemployeesList(User userLoged) {
			DBMessage message = new DBMessage(DBAction.getEmployeesForComboBox,userLoged);
			try {			

				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		
		public void updateAppointmentEmployees(UserList list) {
			DBMessage message = new DBMessage(DBAction.updateAppointment, list);
			try {			
					//System.out.println("client to server"+user.toString());
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public void createsastage(Request req) {
			DBMessage message = new DBMessage(DBAction.createsAssessmentAndExecutionstage, req);
			try {			
					System.out.println("client to server"+req.toString());
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		
		public void getemployeesListForassessorComboBox(String sysname) {
			DBMessage message = new DBMessage(DBAction.getemployeesListForassessorComboBox, sysname);
			try {
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public void checkIfAppoitmentWasMade() {
			DBMessage message = new DBMessage(DBAction.checkIfAppoitmentWasMade, null);
			try {
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		
		public void updateUserSystemMaintenance(User user) {
			DBMessage message = new DBMessage(DBAction.updateUserSystemMaintenance, user);
			try {
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public void ActivityReport (Report report)
		{
			DBMessage message = new DBMessage(DBAction.Activity, report);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}	
		}
		public void DelaysReport(Report report)
		{
			DBMessage message = new DBMessage(DBAction.Delays, report);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}	
		}	

		public void getEmployeesList (User user)
		{
			System.out.println(user.toString());
			DBMessage message = new DBMessage(DBAction.getAllEmployees, user);
			try
			{
				sendToServer(message);
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}

		public void getExaminerRequestList(User userLogedIn) {
			DBMessage message = new DBMessage(DBAction.getRequestsListForExaminer, userLogedIn);
			try 
			{			
				sendToServer(message);
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}				
		}
		public void returnToExecutorStage(Request specificRequest) {
			DBMessage message = new DBMessage(DBAction.returnToExecutorStage, specificRequest);
			try 
			{
				sendToServer(message);
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
	
		}

		public void updateStatusInDB(Request req) {
			DBMessage message = new DBMessage(DBAction.updateStatusInDB, req);
			try {
				//System.out.println("send to server");
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		

		public void getDueDateForSupervisor(Request req) {
			//System.out.println("client controller");
			DBMessage message = new DBMessage(DBAction.getDueDate, req);
			try {
		//		System.out.println("send to server");
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public void setDueDateForStage(Request req) {
			//System.out.println("client controller");
			DBMessage message = new DBMessage(DBAction.setDueDateForStage, req);
			try {
				//System.out.println("send to server");
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		public void getNamesOfEmployees() {
			DBMessage message = new DBMessage(DBAction.getNamesOfEmployees, null);
			try {
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public void aprroveExtention(Request req) {
			//System.out.println("client controller");
			DBMessage message = new DBMessage(DBAction.aprroveExtention, req);
			try {
			//	System.out.println("send to server");
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public void rejectExtention(Request req) {
			//System.out.println("client controller");
			DBMessage message = new DBMessage(DBAction.rejectExtention, req);
			try {
			//	System.out.println("send to server");
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		public void suspendRequest(Request req) {
			//System.out.println("client controller");
			DBMessage message = new DBMessage(DBAction.suspendRequest, req);
			try {
			//	System.out.println("send to server");
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		public void unsuspendRequest(Request req) {
			//System.out.println("client controller");
			DBMessage message = new DBMessage(DBAction.unsuspendRequest, req);
			try {
			//	System.out.println("send to server");
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
	/*	public void getExtentionForSupervisor(Request req) {
			System.out.println("client controller");
			DBMessage message = new DBMessage(DBAction.getExtentionForSupervisor, req);
			try {
				System.out.println("send to server");
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}*/

		public void closeRequest(Request currentReq) {
			DBMessage message = new DBMessage(DBAction.closingRequest, currentReq);
			try {
				sendToServer(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}						
		}

}