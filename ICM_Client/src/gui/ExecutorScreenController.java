package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;

import entities.DBMessage;
import entities.Request;
import entities.User;
import entities.DBMessage.DBAction;
import gui.GuiManager.SCREENS;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.stage.Stage;
import logic.IClient;
import observableEntities.ObservableMessage;
import observableEntities.ObservableRequest;

/**
 * This class is the controller of: ExecutorScreen and it contains all the methods that manage the screen
 *
 */
public class ExecutorScreenController extends HomePageController implements IClient, Initializable
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

    /** The executor main pane. */
    @FXML
    private Pane executorMain_Pane;

    /** The contents page. */
    @FXML
    private VBox contentsPage;

    /** The date lable. */
    @FXML
    private HBox dateLable;

    /** The table. */
    @FXML
    private TableView<ObservableRequest> table;

    /** The due date column. */
    @FXML
    private TableColumn<ObservableRequest, String> dueDate_column;
    
    /** The sn column. */
    @FXML
    private TableColumn<ObservableRequest, Integer> sn_column;

    /** The view culomn. */
    @FXML
    private TableColumn<ObservableRequest, Button> view_culomn;
    
    /** The observable request list. */
    protected ObservableList<ObservableRequest> observableRequestList;// for table view

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

    /** The stage durtion set pane. */
    @FXML
    private Pane stageDurtionSet_Pane; 

    /** The approve due date. */
    @FXML
    private Button approveDueDate; 

    /** The date picker. */
    @FXML
    private DatePicker datePicker;

    /** The assessment report pane. */
    @FXML
    private Pane assessmentReport_pane;

    /** The assessment report fill text. */
    @FXML
    private TextField assessmentReportFill_text;

    /** The extantion btn. */
    @FXML
    private Button extantion_btn;

    /** The set stage duration btn. */
    @FXML
    private Button setStageDuration_btn; 

    /** The change is done btn. */
    @FXML
    private Button theChangeIsDone_btn;

    /** The logout pane. */
    @FXML
    private Pane logout_pane;

    /** The user name label. */
    @FXML
    private Label userNameLabel;

    /** The date label. */
    @FXML
    private Label dateLabel;
    
    /** The Specific request pane. */
    @FXML
    private Pane SpecificRequest_pane;  

    /** The log out bnt. */
    @FXML
    private Button logOutBnt;
    
    /** The view request tab pane. */
    @FXML
    private TabPane viewRequest_tabPane; 

    /** The buttons hbox. */
    @FXML
    private HBox buttons_hbox; 
    
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
    
    /** The back to table btn. */
    @FXML
    private Button backToTable_btn;
    
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
						getUserLogedIn().setExecutorRequests(((User)msg.Data).getExecutorRequests());
						loadRequestsTable(getUserLogedIn());
					});
		}
		else {
			if(msg.Action == DBAction.OpenFile)
			{
				Platform.runLater(() -> {
					if((String)msg.Data==null) {
						GuiManager.ShowErrorPopup("The request dont have any documents");
					}
					
				});
			}
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
		for (Request req : userLogedIn.getExecutorRequests())
		{
			ObservableRequest temp;
			if(req.getExecution().getSupervisorDueDateApprove()) {
				temp = new ObservableRequest(req.getRequestNumber(),req.getExecution().getDueDateString());
			}
			else{
				temp = new ObservableRequest(req.getRequestNumber(),"not set yet");
			}
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
		executorMain_Pane.setVisible(false);		
		SpecificRequest_pane.setVisible(true);	
		buttons_hbox.setVisible(true);	
	    approveDueDate.setDisable(true);   	
	    submitTheExtentionRequest.setDisable(true);
    	GregorianCalendar calendar = new GregorianCalendar();
    	if(specificRequest.getExecution().getDueDateString().equals("0002-11-30")) /*If the deadline has not yet been set*/
    	{
    		setStageDuration_btn.setDisable(false);
	    	theChangeIsDone_btn.setDisable(true);
    	}
    	else
    	{
	    	setStageDuration_btn.setDisable(true);
	    	if (specificRequest.getExecution().getSupervisorDueDateApprove())
	    		theChangeIsDone_btn.setDisable(false);
	    	else theChangeIsDone_btn.setDisable(true);
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
    	}
    	backToTable_btn.setVisible(true);
    	backToTable_btn.setDisable(false);

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
     * Approve due date func.
     *
     * @param event the event
     */
    @FXML
    void approveDueDate_func(ActionEvent event) 
    {
    	stageDurtionSet_Pane.setVisible(false);   
    	viewRequest_tabPane.setVisible(true);
		buttons_hbox.setVisible(true);
		backToTable_btn.setVisible(true);
		setStageDuration_btn.setDisable(true);
		specificRequest.getExecution().setDueDate(date);
        GuiManager.client.setDueDateForExecuteStage(specificRequest);
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
     * Date picker func.
     *
     * @param event the event
     */
    @FXML
    void datePicker_func(ActionEvent event) { 
    	LocalDate today = LocalDate.now();
    	if(datePicker.getValue().isBefore(today) || datePicker.getValue().isEqual(today))
    		 GuiManager.ShowErrorPopup("You have selected an invalid date! \nplease select a different date.");
    	else 
    		{
    			date = datePicker.getValue().toString();
    			approveDueDate.setDisable(false);
    		}
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
    void extantion_pressed(ActionEvent event)
    {
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
	protected void logOutDisplay(MouseEvent event) 
    {
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
     * Sets the stage duration func.
     *
     * @param event the new stage duration func
     */
    @FXML
    void setStageDuration_func(ActionEvent event) 
    {
    	viewRequest_tabPane.setVisible(false);
    	buttons_hbox.setVisible(false);
    	backToTable_btn.setVisible(false);
    	stageDurtionSet_Pane.setVisible(true);   	
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
     * The change is done func.
     *
     * @param event the event
     */
    @FXML
    void theChangeIsDone_func(ActionEvent event) 
    {
    	int i;
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ICM");
		alert.setHeaderText("Are you sure you're done?");
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.OK)
		{
			for(i=0;i<getUserLogedIn().getExecutorRequests().size();i++)
				if(getUserLogedIn().getExecutorRequests().get(i).getRequestNumber()==requestNumber)
					getUserLogedIn().getExecutorRequests().remove(getUserLogedIn().getExecutorRequests().get(i));
			SpecificRequest_pane.setVisible(false);
			loadRequestsTable(this.getUserLogedIn());
			executorMain_Pane.setVisible(true);
			GuiManager.client.stageCompletion(specificRequest);
		}
		else if (option.get() == ButtonType.CANCEL)
		{
			alert.close();
		}
    }

	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());
		if(!getUserLogedIn().getType().equals("supervisor"))
			supervisor_btn.setDisable(true);
		else 
			supervisor_btn.setDisable(false);
			
		GuiManager.client.getExecutorRequestList(getUserLogedIn());
		sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
		view_culomn.setCellValueFactory(new PropertyValueFactory<>("viewButton"));
		dueDate_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
		observableRequestList = FXCollections.observableArrayList();	
	}
	
    /**
     * Pressed back btn.
     *
     * @param event the event
     */
    @FXML
    void PressedBackBtn(ActionEvent event) 
    {
    	extentionRequest_pane.setVisible(false);
    	stageDurtionSet_Pane.setVisible(false);   
    	viewRequest_tabPane.setVisible(true);
		buttons_hbox.setVisible(true);
		backToTable_btn.setVisible(true);
    }
    
    /**
     * Submit the extention request func.
     *
     * @param event the event
     */
    @FXML
    void submitTheExtentionRequest_func(ActionEvent event)
    {    	
    	specificRequest.getExecution().setExtensionReason(extentionResons.getText());
    	specificRequest.getExecution().setExtensionDate(extentionDate);
        GuiManager.client.setTheExtentionRequest(specificRequest);
        GuiManager.ShowMessagePopup("The extension request was submitted!");
    	extentionRequest_pane.setVisible(false);   
    	viewRequest_tabPane.setVisible(true);
		buttons_hbox.setVisible(true);
		extantion_btn.setDisable(true);
		backToTable_btn.setVisible(true);
    }

    /**
     * Extention due date func.
     *
     * @param event the event
     */
    @FXML
    void extentionDueDate_func(ActionEvent event)
    {  	
    	LocalDate today = LocalDate.now();
    	if(extentionDueDate.getValue().isBefore(today) || extentionDueDate.getValue().isEqual(today))
    		 GuiManager.ShowErrorPopup("You have selected an invalid date! \nplease select a different date.");
    	else
    	{
    		extentionDate = extentionDueDate.getValue().toString(); 
     		submitTheExtentionRequest.setDisable(false);   		
    	}
    }
    

    /**
     * Back to table pressed.
     *
     * @param event the event
     */
    @FXML
    void backToTable_Pressed(ActionEvent event) 
    {
    	SpecificRequest_pane.setVisible(false);	
		executorMain_Pane.setVisible(true);	
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
