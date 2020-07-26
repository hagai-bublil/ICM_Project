package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.scene.control.DatePicker;
import entities.DBMessage;
import entities.DBMessage.DBAction;
import entities.Request;
import entities.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.IClient;
import observableEntities.ObservableRequest;

/**
 * This class is the controller of: AssessorScreen and it contains all the methods that manage the screen
 *
 */
public class AssessorScreenController extends HomePageController implements Initializable,IClient {
	
	/** The user name label. */
	@FXML
    private Label userNameLabel;

    /** The home page. */
    @FXML
    private Pane homePage;

    /** The review assessment btn. */
    @FXML
    private Button reviewAssessment_btn;


    /** The submmit bnt. */
    @FXML
    private Button submmit_bnt;

    /** The my request bnt. */
    @FXML
    private Button myRequest_bnt;


	    /** The logout pane. */
    	@FXML
	    private Pane logout_pane;

	    /** The date label. */
    	@FXML
	    private Label dateLabel;

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

	    /** The assessor page. */
    	@FXML
	    private Pane assessorPage;

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

	    /** The date of submission column. */
    	@FXML
	    private TableColumn<ObservableRequest, String> dateOfSubmission_column;

	    /** The view culomn. */
    	@FXML
	    private TableColumn<ObservableRequest, Button> view_culomn;

	    /** The request view pane. */
    	@FXML
	    private Pane requestViewPane;


	    /** The request number set. */
    	@FXML
	    private Label requestNumberSet;

	    /** The information sys text area. */
    	@FXML
	    private Label informationSysTextArea;

	    /** The request system. */
    	@FXML
	    private TextArea requestSystem;

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

	    /** The extantion btn. */
    	@FXML
	    private Button extantion_btn;

	    /** The assessment report fill bnt. */
    	@FXML
	    private Button assessmentReportFill_bnt;

	    /** The set stage duration btn. */
    	@FXML
	    private Button setStageDuration_btn;

	    /** The stage durtion set. */
    	@FXML
	    private Pane stageDurtionSet;

	    /** The request number set 2. */
    	@FXML
	    private Label requestNumberSet2;

	    /** The due date string. */
    	@FXML
	    private TextField dueDateString;

	    /** The approve due date. */
    	@FXML
	    private Button approveDueDate;

	    /** The duration wait for approve. */
    	@FXML
	    private Pane durationWaitForApprove;

	    /** The wait for approve ok btn. */
    	@FXML
	    private Button waitForApproveOkBtn;

	    /** The assessment report pane. */
    	@FXML
	    private Pane assessmentReport_pane;

	    /** The assessment report fill text. */
    	@FXML
	    private TextField assessmentReportFill_text;

	    /** The finish report btn. */
    	@FXML
	    private Button finishReport_btn;
	    
	    /** The date picker. */
    	@FXML
	    private DatePicker datePicker;
	    
	    /** The extention request pane. */
    	@FXML
	    private Pane extentionRequest_pane;

	    /** The extention due date. */
    	@FXML
	    private DatePicker extentionDueDate;

	    /** The extention resons. */
    	@FXML
	    private TextArea extentionResons;

	    /** The submit the extention request. */
    	@FXML
	    private Button submitTheExtentionRequest;

	    /** The due date show label. */
    	@FXML
	    private Label dueDateShow_label;
	    
    	/** The view doc btn. */
    	@FXML
	    private Button viewDoc_btn;
	    
    	/** The specific request. */
    	//calass veriable
        private Request specificRequest;
		
		/** The user loged. */
		private User userLoged; 
		
		/** The Observable requests assessor. */
		private ObservableList<ObservableRequest> ObservableRequestsAssessor;
		
		/** The date. */
		private String date;
		
		/** The extention date. */
		private String extentionDate;

	    /**
    	 * Assessor pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void assessor_pressed(ActionEvent event) {
			setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());		
			assessorPage.setVisible(true);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			requestViewPane.setVisible(false);
			stageDurtionSet.setVisible(false);
			durationWaitForApprove.setVisible(false);
			assessmentReport_pane.setVisible(false);
			extentionRequest_pane.setVisible(false);
            GuiManager.client.getRequestsListAssessor(userLoged);
            sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
            dateOfSubmission_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            view_culomn.setCellValueFactory(new PropertyValueFactory<>("viewButton"));
            ObservableRequestsAssessor = FXCollections.observableArrayList(); 
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
    	 * My request pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void myRequestPressed(ActionEvent event) {
	    	super.myRequestPressed(event);
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
    	 * Review assessment pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void reviewAssessmentPressed(ActionEvent event) {
	    	super.reviewAssessmentPressed(event);
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
    	 * Wait for approve ok btn func.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void waitForApproveOkBtn_func(ActionEvent event) {
			//back to the main assessor table
			assessorPage.setVisible(true);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			requestViewPane.setVisible(false);
			stageDurtionSet.setVisible(false);
			durationWaitForApprove.setVisible(false);
			assessmentReport_pane.setVisible(false);
			extentionRequest_pane.setVisible(false);
            GuiManager.client.getRequestsListAssessor(userLoged);
            sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
            dateOfSubmission_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            view_culomn.setCellValueFactory(new PropertyValueFactory<>("viewButton"));
            ObservableRequestsAssessor = FXCollections.observableArrayList();                 
	    }
		
	    /**
    	 * Log out display.
    	 *
    	 * @param event the event
    	 */
    	@FXML
		protected
	    void logOutDisplay(MouseEvent event) {
	    	super.logOutDisplay(event);
	    }
	    
    	/**
    	 * Pressed supervisor.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void PressedSupervisor(ActionEvent event) {
	    	super.PressedSupervisor(event);
	    }

	    /**
    	 * Extantion pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void extantion_pressed(ActionEvent event) {
	    	String exDate ="0002-11-30";
			GregorianCalendar calendar = new GregorianCalendar();
	    	Date dueDate = specificRequest.getAssessment().getDueDate();
	    	Date todayDate = calendar.getTime();
	    	Calendar c = Calendar.getInstance();
	        c.setTime(todayDate);
	    	c.add(Calendar.DATE, 3);
	    	Date todayPlusThree = c.getTime(); 
	        if((todayPlusThree.after(dueDate) || dueDate.equals(todayPlusThree)) && !specificRequest.getAssessment().getExtension() && specificRequest.getAssessment().getSupervisorDueDateApprove() && (specificRequest.getAssessment().getExtensionDateString().equals(exDate))) {
				assessorPage.setVisible(false);
				logout_pane.setVisible(true);
				vbox1.setVisible(true);
				requestViewPane.setVisible(false);
				stageDurtionSet.setVisible(false);
				durationWaitForApprove.setVisible(false);
				assessmentReport_pane.setVisible(false);
				extentionRequest_pane.setVisible(true);
	        }
	        else {
	        	GuiManager.ShowErrorPopup("You cannot submit an exception");
	        }
	    }
	    
	    /**
    	 * Sets the stage duration.
    	 *
    	 * @param event the new stage duration
    	 */
    	@FXML
	    void setStageDuration(ActionEvent event) {
	    	approveDueDate.setDisable(true);
	    	requestNumberSet2.setText(specificRequest.getRequestStringNumber());
	    	String exDate ="0002-11-30";
	    	String dueDate = specificRequest.getAssessment().getDueDateString();
	    	if(!specificRequest.getAssessment().getSupervisorDueDateApprove() && (dueDate.equals(exDate))) {
	    	assessorPage.setVisible(false);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			requestViewPane.setVisible(false);
			stageDurtionSet.setVisible(true);
			durationWaitForApprove.setVisible(false);
			assessmentReport_pane.setVisible(false);
			extentionRequest_pane.setVisible(false);
	    	} 
	    	else {
	    		GuiManager.ShowErrorPopup("You have already set a duration for this request!");
	    	}
	    }
	    
	    /**
    	 * Date picker func.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void datePicker_func(ActionEvent event) {
	    	date = datePicker.getValue().toString();  
	    	LocalDate today = LocalDate.now();
	    	if(datePicker.getValue().isBefore(today) || datePicker.getValue().isEqual(today))
	    		 GuiManager.ShowErrorPopup("You have selected an invalid date! \nplease select a different date.");
	    	else {
	    		approveDueDate.setDisable(false);
	    	}
	    }
	       
	    /**
    	 * Approve due date func.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void approveDueDate_func(ActionEvent event) {
	    	//setStageDuration_btn.setDisable(true);
	    	assessorPage.setVisible(true);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			requestViewPane.setVisible(false);
			stageDurtionSet.setVisible(false);
			assessmentReport_pane.setVisible(false);
			extentionRequest_pane.setVisible(false);
			GuiManager.ShowMessagePopup("Wait for the supervaisor approve");
			specificRequest.getAssessment().setDueDate(date);
            GuiManager.client.setTheChoosenDueDate(specificRequest);
            
	    }
	    
	    /**
    	 * Assessment report fill func.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void assessmentReportFill_func(ActionEvent event) {
	    	if(specificRequest.getAssessment().getSupervisorDueDateApprove()) {
	    	assessorPage.setVisible(false);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			requestViewPane.setVisible(false);
			stageDurtionSet.setVisible(false);
			durationWaitForApprove.setVisible(false);
			assessmentReport_pane.setVisible(true);
			extentionRequest_pane.setVisible(false);
	    	}
	    	else {
	    		GuiManager.ShowErrorPopup("The duration of the stage not yet \nbeen approved by the supervisor.\nyou cannot fill the report.");
	    	}
	    }
	    
	    /**
    	 * Finish report func.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void finishReport_func(ActionEvent event) {
			specificRequest.getAssessment().setAssessmentReport(assessmentReportFill_text.getText());
            GuiManager.client.setTheReport(specificRequest);
            GuiManager.ShowMessagePopup("The request was moved to the \nnext stage: review assessment report.");
            //initialize
			assessorPage.setVisible(true);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			requestViewPane.setVisible(false);
			stageDurtionSet.setVisible(false);
			durationWaitForApprove.setVisible(false);
			assessmentReport_pane.setVisible(false);
			extentionRequest_pane.setVisible(false);
            GuiManager.client.getRequestsListAssessor(userLoged);
            sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
            dateOfSubmission_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            view_culomn.setCellValueFactory(new PropertyValueFactory<>("viewButton"));
            ObservableRequestsAssessor = FXCollections.observableArrayList(); 
            
	    }

		/**
		 * Gets the user loged in.
		 *
		 * @return the user loged in
		 */
		@Override
		public User getUserLogedIn() {
			return userLoged;
		}

		/**
		 * Sets the user loged in.
		 *
		 * @param userLoged the new user loged in
		 */
		@Override
		public void setUserLogedIn(User userLoged) {
	        this.userLoged=userLoged;
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
				if(msg.Action == DBAction.getRequestsListForAssessor)	
				{
					Platform.runLater(() -> {
						getUserLogedIn().setAssessorRequests(((User)msg.Data).getAssessorRequests());
						setRequestsAssessorTable(getUserLogedIn());						
					});
				}
				if(msg.Action == DBAction.setAssessmentDueDate) {
					//message to the supervisor approve the during
				}
				if(msg.Action == DBAction.OpenFile)
				{
					Platform.runLater(() -> {
						if((String)msg.Data==null) {
							GuiManager.ShowErrorPopup("The request dont have any documents");
						}
						
					});
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}	
		}

		/**
		 * Initialize.
		 *
		 * @param arg0 the arg 0
		 * @param arg1 the arg 1
		 */
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());		
			if(!getUserLogedIn().getType().equals("supervisor"))
				supervisor_btn.setDisable(true);
			else 
				supervisor_btn.setDisable(false);
				
	        assessorPage.setVisible(true);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			requestViewPane.setVisible(false);
			stageDurtionSet.setVisible(false);
			durationWaitForApprove.setVisible(false);
			assessmentReport_pane.setVisible(false);
			extentionRequest_pane.setVisible(false);
            GuiManager.client.getRequestsListAssessor(userLoged);
            sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
            dateOfSubmission_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
            view_culomn.setCellValueFactory(new PropertyValueFactory<>("viewButton"));
            ObservableRequestsAssessor = FXCollections.observableArrayList();                 
		}
	
		/**
		 * Sets the requests assessor table.
		 *
		 * @param user the new requests assessor table
		 */
		protected void setRequestsAssessorTable(User user){
			for(Request r : user.getAssessorRequests()) {
				ObservableRequest tempRequest;
				if(r.getAssessment().getSupervisorDueDateApprove()) {
					tempRequest = new ObservableRequest(r.getRequestNumber(),r.getAssessment().getDueDateString());
				}
				else{
					tempRequest = new ObservableRequest(r.getRequestNumber(),"not set yet");
				}
				tempRequest.getViewButton().setOnAction(e->{
					viewRequestAssessor(r);
				});
				ObservableRequestsAssessor.add(tempRequest);
			}
			table.setItems(ObservableRequestsAssessor);
		
	    Platform.runLater(new Runnable() {
	    	@Override
	    	public void run() {
	    		table.getSortOrder().add(sn_column);
	    		}
	    	});
		}
		
		/**
		 * View request assessor.
		 *
		 * @param request the request
		 */
		public void viewRequestAssessor(Request request) {	
			
			specificRequest = request;
			vbox1.setVisible(true);
			assessorPage.setVisible(false);
			requestViewPane.setVisible(true);
		//	if(specificRequest.getAssessment().getSupervisorDueDateApprove()) {
		//		dueDateShow_label.setText(specificRequest.getAssessment().getDueDateString());
		//	}
		//	else{
		//		dueDateShow_label.setText("not set yet");
		//	}

	    	this.requestNumberSet.setText(request.getRequestStringNumber());
			this.requestSystem.setText(request.getSystemName());
			this.applicantNameTextArea.setText(request.getApplicantName());
			this.StatusTextArea.setText(request.getStatus());
			this.submmitionDateTextArea.setText(request.getSubmissionDateString());
			this.descriptionTextArea.setText(request.getDescriptionOfExistingSituation());
			this.sugestionTextArea.setText(request.getRequestDescription());
			this.reasonsTextArea.setText(request.getReasons());
			this.notsTextArea.setText(request.getComments());
		}	
		
	    /**
    	 * Extention due date func.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void extentionDueDate_func(ActionEvent event) {
	    	LocalDate today = LocalDate.now();
	    	extentionDate = extentionDueDate.getValue().toString(); 
	    	if(extentionDueDate.getValue().isBefore(today) || extentionDueDate.getValue().isEqual(today))
	    		 GuiManager.ShowErrorPopup("You have selected an invalid date! \nplease select a different date.");
	    }
	    
	    /**
    	 * Submit the extention request func.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void submitTheExtentionRequest_func(ActionEvent event) {
	    	specificRequest.getAssessment().setExtensionReason(extentionResons.getText());
	    	specificRequest.getAssessment().setExtensionDate(extentionDate);
            GuiManager.client.setTheExtentionRequest(specificRequest);
            GuiManager.ShowMessagePopup("The extension request was submitted!");
			assessorPage.setVisible(true);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			requestViewPane.setVisible(false);
			stageDurtionSet.setVisible(false);
			durationWaitForApprove.setVisible(false);
			assessmentReport_pane.setVisible(false);
			extentionRequest_pane.setVisible(false);
	    }
	    
	    /**
    	 * View doc func.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void viewDoc_func(ActionEvent event) {
	    	GuiManager.client.OpenFile(this.specificRequest.getRequestNumber());
	    }	 

}


