package gui;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import entities.Assessment;
import entities.DBMessage;
import entities.DBMessage.DBAction;
import entities.Execution;
import entities.Request;
import entities.User;
import entities.UserList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.IClient;
import observableEntities.ObservableRequest;

/**
 * This class is the controller of:SupervisorScreen  and it contains all the methods that manage the screen
 *
 */
public class SupervisorScreenController extends HomePageController implements Initializable,IClient {	
	        
        	/** The request view pane. */
        	@FXML
	        private Pane logout_pane,menuPage,SupervisorPage,requestViewPane;

	        /** The user nane lable. */
        	@FXML
	        private Label userNaneLable;

	        /** The log out bnt. */
        	@FXML
	        private Button logOutBnt;

	        /** The vbox 1. */
        	@FXML
	        private VBox vbox1;

	        /** The supervisor btn. */
        	@FXML
	        private Button supervisor_btn;

	        /** The assessor btn. */
        	@FXML
	        private Button assessor_btn;

	        /** The executor btn. */
        	@FXML
	        private Button executor_btn;

	        /** The examiner btn. */
        	@FXML
	        private Button examiner_btn;

	        /** The notofication bnt. */
        	@FXML
	        private Button notofication_bnt;

	        /** The my account bnt 1. */
        	@FXML
	        private Button myAccount_bnt1;

	        /** The contents page. */
        	@FXML
	        private VBox contentsPage;

	        /** The date lable. */
        	@FXML
	        private HBox dateLable;

	        /** The search text field. */
        	@FXML
	        private TextField search_textField;
	        
	        /** The table. */
        	@FXML
	        private TableView<ObservableRequest> table;

	        /** The sn column. */
        	@FXML
	        private TableColumn<ObservableRequest, Integer> sn_column;

	        /** The Date of submission column. */
        	@FXML
	        private TableColumn<ObservableRequest, String> DateOfSubmission_column;

	        /** The status column. */
        	@FXML
	        private TableColumn<ObservableRequest, String> status_column;

	        /** The view culomn. */
        	@FXML
	        private TableColumn<ObservableRequest, Button> view_culomn;

	        /** The information sys text area. */
        	@FXML
	        private Label informationSysTextArea;

	        /** The applicant name text area. */
        	@FXML
	        private TextArea applicantNameTextArea;

	        /** The submmition date text area. */
        	@FXML
	        private TextArea submmitionDateTextArea;

	        /** The Status text area. */
        	@FXML
	        private TextArea StatusTextArea;

	        /** The description text area. */
        	@FXML
	        private TextArea descriptionTextArea;

	        /** The sugestion text area. */
        	@FXML
	        private TextArea sugestionTextArea;

	        /** The reasons text area. */
        	@FXML
	        private TextArea reasonsTextArea;

	        /** The nots text area. */
        	@FXML
	        private TextArea notsTextArea;
	        
        	/** The info sys area. */
        	@FXML
	        private TextArea infoSysArea;
	        
        	/** The req num lablel. */
        	@FXML
	        private Label reqNumLablel;
	        
        	/** The Appointment bnt. */
        	@FXML
	        private Button Appointment_Bnt;

	        /** The duration bnt. */
        	@FXML
	        private Button duration_Bnt;

	        /** The close bnt. */
        	@FXML
	        private Button close_Bnt;

	        /** The extantion btn. */
        	@FXML
	        private Button extantion_btn;
	        
        	/** The suspend bnt. */
        	@FXML
	        private Button suspend_bnt;
	        
        	/** The date label. */
        	@FXML
	        private Label dateLabel;
	        
        	/** The user name label. */
        	@FXML
	        private Label userNameLabel;
	        
	        /** The menu pane. */
        	@FXML
	        private Pane menuPane;

	        /** The review assessment btn. */
        	@FXML
	        private Button reviewAssessment_btn;

	        /** The submmit bnt. */
        	@FXML
	        private Button submmit_bnt;

	        /** The my request bnt. */
        	@FXML
	        private Button myRequest_bnt;
	       
	        /** The appoitment pane. */
        	@FXML
	        private Pane appoitmentPane;

	        /** The selectpane. */
        	@FXML
	        private Pane selectpane;

	        /** The assessor vbox. */
        	@FXML
	        private VBox assessorVbox;

	        /** The assessor name lable. */
        	@FXML
	        private Label assessorNameLable;

	        /** The assessor combo box. */
        	@FXML
	        private ComboBox<String> assessorComboBox;

	        /** The done button. */
        	@FXML
	        private Button doneButton;
	       
	        /** The assessor appoint prev. */
        	@FXML
	        private Button assessorAppointPrev;
	        
        	/** The succsessful appointment label. */
        	@FXML
	        private Label succsessfulAppointmentLabel;
	        
	        /** The view doc bnt. */
        	@FXML
	        private Button viewDocBnt;
	        
	        /** The executor selectpane. */
        	@FXML
	        private Pane executorSelectpane;

	        /** The executor vbox. */
        	@FXML
	        private VBox executorVbox;

	        /** The executor combo box. */
        	@FXML
	        private ComboBox<String> executorComboBox;
	        
	        /** The stage name for duration lable. */
        	@FXML
	        private Label stageNameForDurationLable;

	        /** The date for duration lable. */
        	@FXML
	        private Label dateForDurationLable;

	        /** The date picker for duration. */
        	@FXML
	        private DatePicker datePickerForDuration;

	        /** The set duration. */
        	@FXML
	        private Button setDuration;
	        
        	/** The duration pane. */
        	@FXML
	        private Pane durationPane;

	        /** The duration approve pane. */
        	@FXML
	        private Pane durationApprovePane;
	        
        	/** The succsessful appointment label 1. */
        	@FXML
	        private Label succsessfulAppointmentLabel1;
	        
        	/** The assessor vbox 1. */
        	@FXML
	        private VBox assessorVbox1;
	        
        	/** The assessor appoint prev 1. */
        	@FXML
	        private Button assessorAppointPrev1;

	        /** The extention request pane. */
        	@FXML
	        private Pane extentionRequestPane;

	        /** The extention pane. */
        	@FXML
	        private Pane extentionPane;

	        /** The assessor vbox 11. */
        	@FXML
	        private VBox assessorVbox11;

	        /** The assessor appoint prev 2. */
        	@FXML
	        private Button assessorAppointPrev2;

	        /** The stage name forextention lable. */
        	@FXML
	        private Label stageNameForextentionLable;

	        /** The date forextention lable. */
        	@FXML
	        private Label dateForextentionLable;

	        /** The extention reason lable. */
        	@FXML
	        private Label extentionReasonLable;

	        /** The approve extention. */
        	@FXML
	        private Button approveExtention;

	        /** The reject extention. */
        	@FXML
	        private Button rejectExtention;

      	        
/** The list of employees. */
////////////////////////////////////////////private class employees///////////////////////////////////////////////////////
	        private UserList listOfEmployees;
	        
        	/** The current req. */
        	private	 Request currentReq;
	        
        	/** The assessor. */
        	private	 User assessor;
	    	
	    	/** The observablerequest list. */
	    	private ObservableList<ObservableRequest> observablerequestList;// for table view...
	      	
	      	/** The this node. */
	      	private Node thisNode;
	        
        	/** The reqnum. */
        	private int reqnum;
	        
        	/** The user loged. */
        	private User userLoged;
	        
        	/** The date. */
        	private String date = null;
	        
	        /**
        	 * Assessor membe prev pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void assessorMembePrevPressed(ActionEvent event) {

	        }

	
	        
	        /**
        	 * Done appoint preesed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void doneAppointPreesed(ActionEvent event) {
	        	  String assessorFullName= assessorComboBox.getSelectionModel().getSelectedItem();
	        	  String executorFullName= executorComboBox.getSelectionModel().getSelectedItem();
	        	  if(assessorFullName!=null) {
	        		  User assessor=findEmployeeByfullName(assessorFullName);
	        		  currentReq.setAssessment(new Assessment("assessment"));
	      	   			currentReq.getAssessment().setStageManager(assessor);
	        	  }
	        	  else {
	        		  	currentReq.setAssessment(new Assessment("assessment"));
	        		  	currentReq.getAssessment().setStageManager(assessor); 
	        	  }
	        	  if(executorFullName!=null) {
	        		  User executor=findEmployeeByfullName(executorFullName);
	        		  currentReq.setExecution(new Execution("Execution"));
	      	   			currentReq.getExecution().setStageManager(executor);
	        	  }
	        	  this.currentReq.setStatus("assessment");
	        	  StatusTextArea.setText(currentReq.getStatus());
	        	GuiManager.client.createsastage(currentReq);
	        }
	        
	        /**
        	 * Find employee byfull name.
        	 *
        	 * @param fullname the fullname
        	 * @return the user
        	 */
        	@SuppressWarnings("unused")
			private User findEmployeeByfullName(String fullname)
	        {
	         	StringTokenizer defaultTokenizer = new StringTokenizer(fullname);
	      	   	String first=defaultTokenizer.nextToken();
	      	   	String last=defaultTokenizer.nextToken();
	      	   	for(User u: listOfEmployees.user_list) {
	      	   	//	System.out.println("u: "+u.getFirstName()+" "+u.getLastName());
	      	   	//	System.out.println("select: "+first+" "+last);
	      	   		if(u.getFirstName().equals(first)&& u.getLastName().equals(last)) {
	      	   				return u;
	      	   		}
	      	   	}
	      	   	return null;
	        }
	        
	        /**
        	 * Assessor prev pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void assessorPrevPressed(ActionEvent event) {
	        	extentionRequestPane.setVisible(false);
		        extentionPane.setVisible(false);
	        	durationPane.setVisible(false);
				durationApprovePane.setVisible(false);
	        	succsessfulAppointmentLabel.setVisible(false);
				appoitmentPane.setVisible(false);
				selectpane.setVisible(false);
				assessorVbox.setVisible(false);
				requestViewPane.setVisible(true);
				Appointment_Bnt.setDisable(true);
				duration_Bnt.setDisable(false);
				close_Bnt.setDisable(false);
				extantion_btn.setDisable(false);
				suspend_bnt.setDisable(false);
				setDuration.setDisable(true);
				
	        }

	        /**
        	 * My request pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void myRequestPressed(ActionEvent event) {
	        	super.myRequestPressed(event);
	        }

	        /**
        	 * Review assessment pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void reviewAssessmentPressed(ActionEvent event) {
	        	super.reviewAssessmentPressed(event);
	        }

	        /**
        	 * Assessor pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void assessor_pressed(ActionEvent event) {
	        	super.assessor_pressed(event);
	        }

	    
	        /**
        	 * Exsaminer pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void exsaminer_pressed(ActionEvent event) {
	        	super.exsaminer_pressed(event);
	        }

	       
	        /**
        	 * My account pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void myAccount_pressed(ActionEvent event) {
	        	super.myAccount_pressed(event);
	        }

	        /**
        	 * Submit pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void submitPressed(ActionEvent event) {
	        	super.submitPressed(event);
	        }
	        
        	/**
        	 * Notifications pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void notifications_pressed(ActionEvent event) {
	        	super.notifications_pressed(event);
	        }

	        /**
        	 * Log out display.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	      protected void logOutDisplay(MouseEvent event) {
	        	super.logOutDisplay(event);
	        }
	        
        	/**
        	 * Pressed supervisor.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void PressedSupervisor(ActionEvent event) {
	        	extentionRequestPane.setVisible(false);
		        extentionPane.setVisible(false);
	        	durationPane.setVisible(false);
				durationApprovePane.setVisible(false);
	        	SupervisorPage.setVisible(true);
				logout_pane.setVisible(true);
			//	menuPane.setVisible(true);
				requestViewPane.setVisible(false);
				appoitmentPane.setVisible(false);
				selectpane.setVisible(false);
				this.userLoged.getSupervisorRequests().clear();
				observablerequestList.clear();
				GuiManager.client.getRequestsList(getUserLogedIn());
	        }


	        /**
        	 * Appiontment pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void appiontment_Pressed(ActionEvent event) {
	        	//show the select-appointment pane
	        	extentionRequestPane.setVisible(false);
		        extentionPane.setVisible(false);
	        	SupervisorPage.setVisible(false);
	        	requestViewPane.setVisible(false);
				logout_pane.setVisible(true);
				appoitmentPane.setVisible(true);
				durationPane.setVisible(false);
				durationApprovePane.setVisible(false);
				if(this.currentReq.getStatus().equals("initial"))
				{
					//executorSelectpane.setVisible(false);
					selectpane.setVisible(true);
					assessorVbox.setVisible(true);
					
				}
				GuiManager.client.getemployeesListForassessorComboBox(this.currentReq.getSystemName());
				
	        }

	        /**
        	 * Close pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void close_pressed(ActionEvent event) {
	        	GuiManager.client.closeRequest(this.currentReq);
	        }

	        /**
        	 * Duration pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void duration_pressed(ActionEvent event) {
	        	Platform.runLater(() -> {
	        	GuiManager.client.getDueDateForSupervisor(this.currentReq);
	        	});
	        }

	        
	        /**
        	 * Extantion pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void extantion_pressed(ActionEvent event) {
	        	/*Platform.runLater(() -> {
		        	GuiManager.client.getExtentionForSupervisor(this.currentReq);
	        	});*/
	        	String date=null,extentionReason=null;
	        	stageNameForextentionLable.setText("Please apporve or reject the extention request for the "+this.currentReq.getStatus()+" stage:");
	        	boolean ans=false;
	        	switch(this.currentReq.getStatus()) {
	        	/////////assessment
				case "assessment":
					if(this.currentReq.getAssessment().getExtension()==true) {
						GuiManager.ShowMessagePopup("Extention request already approved");
						break;
					}
					date=this.currentReq.getAssessment().getExtensionDateString();
					if(date.equals("0002-11-30")){
						GuiManager.ShowMessagePopup("The extension request was not filed");
						break;
					}
					extentionReason=this.currentReq.getAssessment().getExtensionReason();
					ans=true;
					break;
				////////////////	reviewassessment
				case "reviewassessment":
					if(this.currentReq.getReviewAssessment().getExtension()==true) {
						GuiManager.ShowMessagePopup("Extention request already approved");
						break;
					}
					date=this.currentReq.getReviewAssessment().getExtensionDateString();
					if(date.equals("0002-11-30")){
						GuiManager.ShowMessagePopup("The extension request was not filed");
						break;
					}
					extentionReason=this.currentReq.getReviewAssessment().getExtensionReason();
					ans=true;
					break;
					///////////////////execution
				case "execution":
					System.out.println(this.currentReq.getExecution().getExtension());
					if(this.currentReq.getExecution().getExtension()==true) {
						GuiManager.ShowMessagePopup("Extention request already approved");
						break;
					}
					date=this.currentReq.getExecution().getExtensionDateString();
					if(date.equals("0002-11-30")){
						//System.out.println("before break1");
						GuiManager.ShowMessagePopup("The extension request was not filed");
						break;
					}
					extentionReason=this.currentReq.getExecution().getExtensionReason();
					ans=true;
					break;
					///////////////examination
				case "examination":
					if(this.currentReq.getExamination().getExtension()==true) {
						GuiManager.ShowMessagePopup("Extention request already approved");
						break;
					}
					date=this.currentReq.getExamination().getExtensionDateString();
					if(date.equals("0002-11-30")){
						GuiManager.ShowMessagePopup("The extension request was not filed");
						break;
					}
					extentionReason=this.currentReq.getExamination().getExtensionReason();
					ans=true;
					break;
				default:
					GuiManager.ShowMessagePopup("Problem");
					ans=false;
					break;	
	        	}
	        	System.out.println(ans);
	        	if(ans==true) {
	        		dateForextentionLable.setText("The stage manager asked for extention until: "+date);
	        		extentionReasonLable.setText("due to: " +extentionReason);
	        		SupervisorPage.setVisible(false);
		        	requestViewPane.setVisible(false);
					//logout_pane.setVisible(true);
					appoitmentPane.setVisible(false);
					selectpane.setVisible(false);
					assessorVbox.setVisible(false);
					durationPane.setVisible(false);
					durationApprovePane.setVisible(false);
		        	extentionRequestPane.setVisible(true);
			        extentionPane.setVisible(true);
	        	} 
	        	else {
	        	/*	extentionRequestPane.setVisible(false);
			        extentionPane.setVisible(false);
	        		requestViewPane.setVisible(true);*/
	        	}
	        	
	        }

	        /**
        	 * Suspend pressed.
        	 *
        	 * @param event the event
        	 */
        	@FXML
	        void suspend_Pressed(ActionEvent event) {
	        		GuiManager.client.suspendRequest(this.currentReq);
	        }

			/**
			 * Initialize.
			 *
			 * @param arg0 the arg 0
			 * @param arg1 the arg 1
			 */
			@Override
			public void initialize(URL arg0, ResourceBundle arg1) {
				//show only the SupervisorPage
				setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());
				SupervisorPage.setVisible(true);
				logout_pane.setVisible(true);
			//	menuPane.setVisible(true);
				durationPane.setVisible(false);
				durationApprovePane.setVisible(false);
				requestViewPane.setVisible(false);
				appoitmentPane.setVisible(false);
				selectpane.setVisible(false);
				extentionRequestPane.setVisible(false);
		        extentionPane.setVisible(false);
			//	System.out.println(getUserLogedIn().toString());
				GuiManager.client.getRequestsList(getUserLogedIn());
				sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
				DateOfSubmission_column.setCellValueFactory(new PropertyValueFactory<>("dateOfSubmission"));
				status_column.setCellValueFactory(new PropertyValueFactory<>("status"));
				view_culomn.setCellValueFactory(new PropertyValueFactory<>("viewButton"));
				observablerequestList = FXCollections.observableArrayList();
			}

			/**
			 * Gets the user loged in.
			 *
			 * @return the user loged in
			 */
			@Override
			public User getUserLogedIn() {
				return this.userLoged;
			}

			/**
			 * Sets the user loged in.
			 *
			 * @param userLoged the new user loged in
			 */
			@Override
			public void setUserLogedIn(User userLoged) {
				this.userLoged=userLoged;
				//System.out.println(this.userLoged.toString());
				String userName = userLoged.getUserName();
				userNameLabel.setText(userName);
				GregorianCalendar calendar = new GregorianCalendar();
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				String string = format.format(calendar.getTime());
				dateLabel.setText(string);
				
			}

			/**
			 * Gets the message from server.
			 *
			 * @param msg the msg
			 * @return the message from server
			 */
			@Override
			public void getMessageFromServer(DBMessage msg) {
				try
				{
					switch (msg.Action)
					{
					case getRequestsListForSupervisor:
								// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
								Platform.runLater(() -> {
									getUserLogedIn().setSupervisorRequests(((User)msg.Data).getSupervisorRequests());
									/*for(Request r:getUserLogedIn().getSupervisorRequests()) {
										System.out.println(r.toString());
									}*/
									loadRequestsTable(getUserLogedIn());
								});
								break;
								
					case getemployeesListForassessorComboBox:
						Platform.runLater(() -> {
							assessorComboBox.setDisable(false);
							executorComboBox.setDisable(false);
							listOfEmployees=(UserList)msg.Data;
						/*	for(User u:listOfEmployees.user_list) {
								System.out.println("controller "+u.toString());
							}*/
							Random rand = new Random();
							if(listOfEmployees.user_list.size()>0) {
								assessor=listOfEmployees.user_list.get(rand.nextInt(listOfEmployees.user_list.size()));
								assessorNameLable.setText("The system chose "+assessor.getFirstName()+" "+assessor.getLastName()+" "+"for the assessor role, do you approve?");
								loadComboBox(listOfEmployees);
							}
							else {
								assessorNameLable.setText("There aren't any employees that work on "+this.currentReq.getSystemName());
								assessorComboBox.setDisable(true);
								executorComboBox.setDisable(true);
							}
						});
						break;
					case createsAssessmentAndExecutionstage:
						Platform.runLater(() -> {
							GuiManager.ShowErrorPopup("Problem");					
							//System.out.println("controller-switch before");
							//GuiManager.client.updateStatusInDB(this.currentReq);
							
						});
						break;
					case updateStatusInDB:
						Platform.runLater(() -> {
							///System.out.println("controller-switch after");
						if((boolean)msg.Data==true) {
							//System.out.println("show pop up");	
							succsessfulAppointmentLabel.setVisible(true);
							//this.currentReq.setStatus("assessment");
							//StatusTextArea.setText(currentReq.getStatus());
							GuiManager.ShowMessagePopup("The request moved to the assessment stage!");
							duration_Bnt.setDisable(true);
						}
						else {
							GuiManager.ShowErrorPopup("The request didnt moved to the assessment stage");
							//System.out.println("didnt moved");
						}
						});
						break;
					case OpenFile:
						Platform.runLater(() -> {
							if((String)msg.Data==null) {
								GuiManager.ShowErrorPopup("The request dont have any documents");
							}
							
						});
						break;
					case setDueDateForStage:
						Platform.runLater(() -> {
							if((boolean)msg.Data==false) {
								GuiManager.ShowErrorPopup("The due date didn't change");
							}
							else {
								GuiManager.ShowMessagePopup("The due date is set");
							}
							
						});
						break;
					case getDueDate:
						Platform.runLater(() -> {
							
							if((boolean)msg.Data==false) {
								GuiManager.ShowErrorPopup("The stage manager didn't set the stage duration yet");
							}
							else {
								SupervisorPage.setVisible(false);
					        	requestViewPane.setVisible(false);
								//logout_pane.setVisible(true);
								appoitmentPane.setVisible(false);
								selectpane.setVisible(false);
								assessorVbox.setVisible(false);
								durationPane.setVisible(true);
								durationApprovePane.setVisible(true);
								extentionRequestPane.setVisible(false);
						        extentionPane.setVisible(false);
								//GuiManager.client.getDueDate(currentReq);
								if(this.currentReq.getStatus().equals("assessment"))
								{
									stageNameForDurationLable.setText("Please apporve or change the Assessment stage due date:");
									dateForDurationLable.setText("The stage manager set the due date to: "+this.currentReq.getAssessment().getDueDateString());
								}
								else
								{
									if(this.currentReq.getStatus().equals("execution"))
									{
										stageNameForDurationLable.setText("Please apporve or change the Execution stage due date:");
										dateForDurationLable.setText("The stage manager set the due date to: "+this.currentReq.getExecution().getDueDateString());
									}
							
								}
							}
							
						});
						break;
					case aprroveExtention:
						Platform.runLater(() -> {
							if((boolean)msg.Data==false) {
								GuiManager.ShowErrorPopup("problem");
							}
							else {
								//GuiManager.ShowMessagePopup("The due date has changed");
								GuiManager.ShowMessagePopup("The extention request was approved!");
							}
						});
						break;
					case rejectExtention:
						Platform.runLater(() -> {
							if((boolean)msg.Data==false) {
								GuiManager.ShowErrorPopup("problem");
							}
							else {
								//GuiManager.ShowMessagePopup("The due date has changed");
								GuiManager.ShowMessagePopup("The extention request was rejected!");
							}
						});
						break;
					case suspendRequest:
						Platform.runLater(() -> {
							if((boolean)msg.Data==false) {
								GuiManager.ShowErrorPopup("problem");
							}
							else {
								//GuiManager.ShowMessagePopup("The due date has changed");
								Appointment_Bnt.setDisable(true);
								duration_Bnt.setDisable(true);
								extantion_btn.setDisable(true);
								close_Bnt.setDisable(true);
								suspend_bnt.setDisable(true);
								GuiManager.ShowMessagePopup("Request suspended successfully\nif you would like to unsuspend the request, please contact the manager");
							
							}
						});
						break;
					case closingRequest:
						Platform.runLater(() -> {
								GuiManager.ShowMessagePopup("A message of closing was sent to the request applicant.");
						});
						break;
					default:
						break;
					}
				} 
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
			/**
			 * Load requests table.
			 *
			 * @param userLogedIn the user loged in
			 */
			@SuppressWarnings("deprecation")
			protected void loadRequestsTable(User userLogedIn)
			{
				for (Request req : userLogedIn.getSupervisorRequests())
				{
					//System.out.println("req"+req.getSubmissionDate().toString());
					ObservableRequest temp = new ObservableRequest(req.getRequestNumber(),req.getSubmissionDateString(),req.getStatus());
					temp.getViewButton().setOnAction(e -> 
					{
						currentReq=req;
						//reqnum=req.getRequestNumber()-1;
						openViewRequestPane();
					});		
					observablerequestList.add(temp);
				}
				table.setItems(observablerequestList);
				Platform.runLater(new Runnable()
				{			
					@Override
					public void run()
					{
						table.getSortOrder().add(DateOfSubmission_column);				
					}
				});	
			}
			
			/**
			 * this function is called when the user presses the view button it will open the request details form and buttons for the supervisor to use*.
			 */
			@SuppressWarnings("deprecation")
			protected void openViewRequestPane() {
				//show only the requestViewPane
				duration_Bnt.setDisable(true);
				durationPane.setVisible(false);
				durationApprovePane.setVisible(false);
				SupervisorPage.setVisible(false);
				logout_pane.setVisible(true);
				requestViewPane.setVisible(true);
				reqNumLablel.setText("Request "+currentReq.getRequestNumber());
				if(currentReq.getStatus().equals("initial"))
					Appointment_Bnt.setDisable(false);
				else
					Appointment_Bnt.setDisable(true);
				if((this.currentReq.getStatus().equals("assessment")&&currentReq.getAssessment().getSupervisorDueDateApprove()==false)||(this.currentReq.getStatus().equals("execution")&&currentReq.getExecution().getSupervisorDueDateApprove()==false))
				{
					
					//GuiManager.client.getDueDateForSupervisor(this.currentReq);
					duration_Bnt.setDisable(false);
					
				}
				else {
					
					duration_Bnt.setDisable(true);
				}
				if(currentReq.getStatus().equals("suspend")) {
					duration_Bnt.setDisable(true);
					Appointment_Bnt.setDisable(true);
					extantion_btn.setDisable(true);
					close_Bnt.setDisable(true);
					suspend_bnt.setDisable(true);
					}
				infoSysArea.setText(currentReq.getSystemName());
				applicantNameTextArea.setText(currentReq.getApplicantName());
				submmitionDateTextArea.setText(currentReq.getSubmissionDateString());
				StatusTextArea.setText(currentReq.getStatus());
				descriptionTextArea.setText(currentReq.getDescriptionOfExistingSituation());
				sugestionTextArea.setText(currentReq.getRequestDescription());
				reasonsTextArea.setText(currentReq.getReasons());
				notsTextArea.setText(currentReq.getComments());
				
				//todo file area
			}
			
			/**
			 * Load combo box.
			 *
			 * @param list the list
			 */
			void loadComboBox(UserList list) {
				 ObservableList<String> itEmployees = FXCollections.observableArrayList();
				 for(User user:list.user_list) {
					 if(user.isAvailable()&& !user.equals(assessor)) {
					 String str=user.getFirstName()+" "+user.getLastName();
					 itEmployees.add(str);
					 }
				 }
				 assessorComboBox.setItems(itEmployees);
				 executorComboBox.setItems(itEmployees);
			}
			
			/**
			 * this function open the document of the request, if the request doesn't have doc an error massage will appear*.
			 *
			 * @param event the event
			 */
		    @FXML
		    void viewDocPressed(ActionEvent event) {
		    	GuiManager.client.OpenFile(this.currentReq.getRequestNumber());
		    }	
		    
		    /**
    		 * Executor pressed.
    		 *
    		 * @param event the event
    		 */
    		@FXML
		    void executor_pressed(ActionEvent event) {
		    	super.executor_pressed(event);
		    }
		    

		    
		    /**
    		 * Sets the duration pressed.
    		 *
    		 * @param event the new duration pressed
    		 */
    		@FXML
		    void setDuration_pressed(ActionEvent event) {
		    	System.out.println("controller");
		    	GuiManager.client.setDueDateForStage(currentReq);
		    }
		    
		    /**
    		 * Date picker for duration func.
    		 *
    		 * @param event the event
    		 */
    		@FXML
		    void datePickerForDuration_func(ActionEvent event) {
		    	LocalDate today = LocalDate.now();
		    	if(datePickerForDuration.getValue().isBefore(today) || datePickerForDuration.getValue().isEqual(today))
		    		 GuiManager.ShowErrorPopup("You have selected an invalid date! \nplease select a different date.");
		    	else 
		    		{
		    			date = datePickerForDuration.getValue().toString();
		    			if(this.currentReq.getStatus().equals("assessment")) {
		    				currentReq.getAssessment().setDueDate(date);
		    			}
		    			if(this.currentReq.getStatus().equals("execution")){
		    				currentReq.getExecution().setDueDate(date);
		    			}
						{
		    			setDuration.setDisable(false);
		    		}
		    }

	    }
		    
		    /**
    		 * Aprrove extention pressed.
    		 *
    		 * @param event the event
    		 */
    		@FXML
		    void aprroveExtention_pressed(ActionEvent event) {
		    	GuiManager.client.aprroveExtention(currentReq);
		    }
		    
		    /**
    		 * Reject extention pressed.
    		 *
    		 * @param event the event
    		 */
    		@FXML
		    void rejectExtention_pressed(ActionEvent event) {
		    	GuiManager.client.rejectExtention(currentReq);
		    }
		    
		    
		    
		    
		    
}

		
	    


