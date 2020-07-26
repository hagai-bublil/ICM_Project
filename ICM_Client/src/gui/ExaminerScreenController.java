package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;

import entities.DBMessage;
import entities.Request;
import entities.User;
import entities.DBMessage.DBAction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.IClient;
import observableEntities.ObservableRequest;

/**
 * This class is the controller of: ExaminerScreen and it contains all the methods that manage the screen
 *
 */
public class ExaminerScreenController extends HomePageController implements IClient, Initializable
{

    /** The anchor pane. */
    @FXML
    private AnchorPane anchorPane;

    /** The home page. */
    @FXML
    private Pane homePage;

    /** The vbox 1. */
    @FXML
    private VBox vbox1;

    /** The supervisor btn. */
    @FXML
    private Button supervisor_btn;

    /** The assessor btn. */
    @FXML
    private Button assessor_btn;

    /** The review assessment btn. */
    @FXML
    private Button reviewAssessment_btn;

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

    /** The submmit bnt. */
    @FXML
    private Button submmit_bnt;

    /** The my request bnt. */
    @FXML
    private Button myRequest_bnt;

    /** The examiner main pane. */
    @FXML
    private Pane examinerMain_Pane;

    /** The contents page. */
    @FXML
    private VBox contentsPage;

    /** The date lable. */
    @FXML
    private HBox dateLable;

    /** The table. */
    @FXML
    private TableView<ObservableRequest> table;

    /** The sn column. */
    @FXML
    private TableColumn<ObservableRequest, Integer> sn_column;

    /** The view culomn. */
    @FXML
    private TableColumn<ObservableRequest, Button> view_culomn;
    
    /** The observable request list. */
    protected ObservableList<ObservableRequest> observableRequestList;// for table view
    
    /** The due date column. */
    @FXML
    private TableColumn<ObservableRequest, String> dueDate_column;

    /** The Specific request pane. */
    @FXML
    private Pane SpecificRequest_pane;

    /** The request number set. */
    @FXML
    private Label requestNumberSet;

    /** The buttons hbox. */
    @FXML
    private HBox buttons_hbox;

    /** The Report failure details btn. */
    @FXML
    private Button ReportFailureDetails_btn;

    /** The extantion btn. */
    @FXML
    private Button extantion_btn;

    /** The approval btn. */
    @FXML
    private Button approval_btn;

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

    /** The view request tab pane. */
    @FXML
    private TabPane viewRequest_tabPane;

    /** The request view pane. */
    @FXML
    private Pane requestViewPane;

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

    /** The assessment report pane. */
    @FXML
    private Pane assessmentReport_pane;

    /** The assessment report fill text. */
    @FXML
    private TextField assessmentReportFill_text;

    /** The back to table btn. */
    @FXML
    private Button backToTable_btn;

    /** The Failure report pane. */
    @FXML
    private Pane FailureReport_pane;

    /** The Failure details text area. */
    @FXML
    private TextArea FailureDetails_textArea;

    /** The submit failure report btn. */
    @FXML
    private Button submitFailureReport_btn;

    /** The logout pane. */
    @FXML
    private Pane logout_pane;

    /** The user name label. */
    @FXML
    private Label userNameLabel;

    /** The date label. */
    @FXML
    private Label dateLabel;

    /** The log out bnt. */
    @FXML
    private Button logOutBnt;
    
    /** The view doc btn. */
    @FXML
    private Button viewDoc_btn;
    
    /** The user loged. */
    private User userLoged;
    
    /** The request number. */
    private int requestNumber;
    
    /** The date. */
    private String date = null;
    
    /** The specific request. */
    private Request specificRequest;
    
    /** The index. */
    int index=0;
    
    /** The extention date. */
    private String extentionDate;
    
    /** The failuredetails. */
    private String  failuredetails;
    
	/**
	 * Gets the user loged in.
	 *
	 * @return the user loged in
	 */
	@Override
	public User getUserLogedIn() 
	{
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
	public void getMessageFromServer(DBMessage msg) 
	{
		if (msg.Action == DBAction.getRequestsListForEmployee)
		{
					// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
					Platform.runLater(() -> {
						getUserLogedIn().setExaminerRequests(((User)msg.Data).getExaminerRequests());
						loadRequestsTable(getUserLogedIn());
					});
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

    /**
     * Load requests table.
     *
     * @param userLogedIn the user loged in
     */
    private void loadRequestsTable(User userLogedIn) 
    {
    	observableRequestList.clear();
		for (Request req : userLogedIn.getExaminerRequests())
		{
			ObservableRequest temp = new ObservableRequest(req.getRequestNumber(),req.getExamination().getDueDateString());
			temp.getViewButton().setOnAction(e -> 
			{
				requestNumber=temp.getRequestNumber();
				openViewRequestPane(req);
				});		
			observableRequestList.add(temp);
		}
		table.setItems(observableRequestList);
		Platform.runLater(new Runnable()
		{			
			@Override
			public void run()
			{
				table.getSortOrder().add(sn_column);				
			}
		});			
	}

	/**
	 * Open view request pane.
	 *
	 * @param request the request
	 */
	private void openViewRequestPane(Request request) 
	{
		specificRequest=request;
		assessmentReportFill_text.setText(specificRequest.getAssessment().getAssessmentReport());
		requestViewPane.setVisible(true);
    	this.requestNumberSet.setText(String.valueOf(request.getRequestNumber()));
		this.requestSystem.setText(request.getSystemName());
		this.applicantNameTextArea.setText(request.getApplicantName());
		this.StatusTextArea.setText(request.getStatus());
		this.submmitionDateTextArea.setText(request.getSubmissionDateString());
		this.descriptionTextArea.setText(request.getDescriptionOfExistingSituation());
		this.sugestionTextArea.setText(request.getRequestDescription());
		this.reasonsTextArea.setText(request.getReasons());
		this.notsTextArea.setText(request.getComments());		
		examinerMain_Pane.setVisible(false);		
		SpecificRequest_pane.setVisible(true);	
		buttons_hbox.setVisible(true);	 	
	    submitTheExtentionRequest.setDisable(true);
    	GregorianCalendar calendar = new GregorianCalendar();
    	Date dueDate = specificRequest.getExecution().getDueDate();
	    Date todayDate = calendar.getTime();
	    Calendar c = Calendar.getInstance();
	    c.setTime(todayDate);
	    c.add(Calendar.DATE, 3);
	    Date todayPlusThree = c.getTime();
	   if((todayPlusThree.after(dueDate) || dueDate.equals(todayPlusThree)) && 
       		!specificRequest.getExecution().getExtension() && specificRequest.getExecution().getSupervisorDueDateApprove()) 
	    	extantion_btn.setDisable(false);
	    else
	    	extantion_btn.setDisable(true);
	   	backToTable_btn.setVisible(true);
	   	backToTable_btn.setDisable(false);
	   	approval_btn.setDisable(false);
	}
    
    /**
     * Pressed back btn.
     *
     * @param event the event
     */
    @FXML
    void PressedBackBtn(ActionEvent event) {
    	extentionRequest_pane.setVisible(false);
    	FailureReport_pane.setVisible(false);   
    	viewRequest_tabPane.setVisible(true);
		buttons_hbox.setVisible(true);
		backToTable_btn.setVisible(true);
    }

    /**
     * Pressed report failure details.
     *
     * @param event the event
     */
    @FXML
    void PressedReportFailureDetails(ActionEvent event) {
       	viewRequest_tabPane.setVisible(false);
    	buttons_hbox.setVisible(false);
    	backToTable_btn.setVisible(false);
    	FailureReport_pane.setVisible(true);   	
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
     * Approval pressed.
     *
     * @param event the event
     */
    @FXML
    void approvalPressed(ActionEvent event) {
    	int i;
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ICM");
		alert.setHeaderText("Are you sure you want to approve the test?");
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.OK)
		{
			for(i=0;i<getUserLogedIn().getExaminerRequests().size();i++)
				if(getUserLogedIn().getExaminerRequests().get(i).getRequestNumber()==requestNumber)
					getUserLogedIn().getExaminerRequests().remove(getUserLogedIn().getExaminerRequests().get(i));
			SpecificRequest_pane.setVisible(false);
			loadRequestsTable(this.getUserLogedIn());
			examinerMain_Pane.setVisible(true);
			GuiManager.client.stageCompletion(specificRequest);
		}
		else if (option.get() == ButtonType.CANCEL)
		{
			alert.close();
		}
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
     * Back to table pressed.
     *
     * @param event the event
     */
    @FXML
    void backToTable_Pressed(ActionEvent event) {
    	SpecificRequest_pane.setVisible(false);
    	examinerMain_Pane.setVisible(true);
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
     * Extantion pressed.
     *
     * @param event the event
     */
    @FXML
    void extantion_pressed(ActionEvent event) {
    	viewRequest_tabPane.setVisible(false);
    	buttons_hbox.setVisible(false);		
    	backToTable_btn.setVisible(false);
    	extentionRequest_pane.setVisible(true);  
    }

    /**
     * Extention due date func.
     *
     * @param event the event
     */
    @FXML
    void extentionDueDate_func(ActionEvent event) {
    	viewRequest_tabPane.setVisible(false);
    	buttons_hbox.setVisible(false);		
    	backToTable_btn.setVisible(false);
    	extentionRequest_pane.setVisible(true);  
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
     * Submit failure report func.
     *
     * @param event the event
     */
    @FXML
    void submitFailureReport_func(ActionEvent event) {
    	failuredetails=FailureDetails_textArea.getText();
    	specificRequest.getExamination().setFailureDetails(failuredetails);
    	submitFailureReport_btn.setVisible(false);
    	GuiManager.ShowMessagePopup("The request was returned to the execution stage");   	
    	int i;
		for(i=0;i<getUserLogedIn().getExaminerRequests().size();i++)
				if(getUserLogedIn().getExaminerRequests().get(i).getRequestNumber()==requestNumber)
					getUserLogedIn().getExaminerRequests().remove(getUserLogedIn().getExaminerRequests().get(i));
			SpecificRequest_pane.setVisible(false);
			loadRequestsTable(this.getUserLogedIn());
			examinerMain_Pane.setVisible(true);
			GuiManager.client.returnToExecutorStage(specificRequest);

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
     * Submit the extention request func.
     *
     * @param event the event
     */
    @FXML
    void submitTheExtentionRequest_func(ActionEvent event) {
    	failuredetails=FailureDetails_textArea.getText();
    	GuiManager.client.returnToExecutorStage(specificRequest);
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
			
		GuiManager.client.getExaminerRequestList(getUserLogedIn());
		sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
		dueDate_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		view_culomn.setCellValueFactory(new PropertyValueFactory<>("viewButton"));
		observableRequestList = FXCollections.observableArrayList();			
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
