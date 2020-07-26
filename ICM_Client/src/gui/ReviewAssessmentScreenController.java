package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import entities.DBMessage;
import entities.Request;
import entities.User;
import entities.DBMessage.DBAction;
import entities.Examination;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.IClient;
import observableEntities.ObservableRequest;

/**
 * This class is the controller of: ReviewAssessmentScreen and it contains all the methods that manage the screen
 *
 */
public class ReviewAssessmentScreenController extends HomePageController implements Initializable,IClient  {

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

    /** The review assessment main screen. */
    @FXML
    private Pane reviewAssessmentMain_screen;

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

    /** The due date column. */
    @FXML
    private TableColumn<ObservableRequest, String> dueDate_column;

    /** The view request assessment report culomn. */
    @FXML
    private TableColumn<ObservableRequest, Button> viewRequestAssessmentReport_culomn;

    /** The Specific request pane. */
    @FXML
    private Pane SpecificRequest_pane;

    /** The request number set 1. */
    @FXML
    private Label requestNumberSet1;


    /** The approve due date. */
    @FXML
    private Button approveDueDate;

    /** The date picker. */
    @FXML
    private DatePicker datePicker;

    /** The view request tab pane. */
    @FXML
    private TabPane viewRequest_tabPane;

    /** The request view pane 1. */
    @FXML
    private Pane requestViewPane1;

    /** The information sys text area 1. */
    @FXML
    private Label informationSysTextArea1;

    /** The request system 1. */
    @FXML
    private TextArea requestSystem1;

    /** The applicant name text area 1. */
    @FXML
    private TextArea applicantNameTextArea1;

    /** The submmition date text area 1. */
    @FXML
    private TextArea submmitionDateTextArea1;

    /** The Status text area 1. */
    @FXML
    private TextArea StatusTextArea1;

    /** The description text area 1. */
    @FXML
    private TextArea descriptionTextArea1;

    /** The sugestion text area 1. */
    @FXML
    private TextArea sugestionTextArea1;

    /** The reasons text area 1. */
    @FXML
    private TextArea reasonsTextArea1;

    /** The nots text area 1. */
    @FXML
    private TextArea notsTextArea1;

    /** The assessment report pane. */
    @FXML
    private Pane assessmentReport_pane;

    /** The assessment report fill text 1. */
    @FXML
    private TextField assessmentReportFill_text1;

    /** The buttons hbox. */
    @FXML
    private HBox buttons_hbox;

    /** The extantion btn. */
    @FXML
    private Button extantion_btn;

    /** The confirm execution btn. */
    @FXML
    private Button confirmExecution_btn;

    /** The reject execution btn. */
    @FXML
    private Button rejectExecution_btn;

    /** The ask for more details btn. */
    @FXML
    private Button askForMoreDetails_btn;

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
    
    /** The appoint examiner btn. */
    @FXML
    private Button appointExaminer_btn;
    
    /** The examiner appoint screen. */
    @FXML
    private Pane examinerAppoint_screen;
    
    /** The view doc btn. */
    @FXML
    private Button viewDoc_btn;

    /** The examiner combo box. */
    @FXML
    private ComboBox<String> examinerComboBox;
    
    
    /** The ask for more details pane. */
    @FXML
    private Pane askForMoreDetails_pane;

    /** The Ask for detalis text. */
    @FXML
    private TextArea AskForDetalis_text;

    /** The Ask for detalis btn. */
    @FXML
    private Button AskForDetalis_btn;

    
    
    /** The specific request. */
    private Request specificRequest;
	
	/** The user loged. */
	private User userLoged; 
	
	/** The Observable requests review assessment. */
	private ObservableList<ObservableRequest> ObservableRequestsReviewAssessment;
	
	/** The extention date. */
	private String extentionDate;
	
	/** The employee list. */
	private ObservableList<String> employeeList; 

	
    /**
     * Appoint examiner func.
     *
     * @param event the event
     */
    @FXML
    void appointExaminer_func(ActionEvent event) {
		
		logout_pane.setVisible(true);
		vbox1.setVisible(true);
		reviewAssessmentMain_screen.setVisible(true);
		SpecificRequest_pane.setVisible(false);
		extentionRequest_pane.setVisible(false);
		examinerAppoint_screen.setVisible(false);
		askForMoreDetails_pane.setVisible(false);
        GuiManager.client.getRequestsListReviewAssessment(userLoged);
        sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
        dueDate_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        viewRequestAssessmentReport_culomn.setCellValueFactory(new PropertyValueFactory<>("viewRequestButton"));
        ObservableRequestsReviewAssessment = FXCollections.observableArrayList();
    }
    
    /**
     * Examiner combo box set.
     *
     * @param event the event
     */
    @FXML
    void examinerComboBox_set(ActionEvent event) {

    	Platform.runLater(() -> {
	    	String fullName= examinerComboBox.getSelectionModel().getSelectedItem();
	    	//System.out.println(fullName);
	    	StringTokenizer defaultTokenizer = new StringTokenizer(fullName);
	    	String first=defaultTokenizer.nextToken();
	    	String last=defaultTokenizer.nextToken();
	    	if(first.equals(specificRequest.getCommissioner1().getFirstName())&&last.equals(specificRequest.getCommissioner1().getLastName())){
	    		specificRequest.setExamination(new Examination("examination",1));
	    		specificRequest.getExamination().setStageManager(new User(specificRequest.getCommissioner1().getIdNumber()));
	    	}
	    	else {
	    		specificRequest.setExamination(new Examination("examination",2));
	    		specificRequest.getExamination().setStageManager(new User(specificRequest.getCommissioner2().getIdNumber()));
	    	}
	        //the next stage update
			GuiManager.client.ReviewstageCompletion(specificRequest);
	    	});
    }
    
    /**
     * Pressed back btn.
     *
     * @param event the event
     */
    @FXML
    void PressedBackBtn(ActionEvent event) {

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
    void approveDueDate_func(ActionEvent event) {

    }

    /**
     * Ask for more details func.
     *
     * @param event the event
     */
    @FXML
    void askForMoreDetails_func(ActionEvent event) {
    	String exDate ="0002-11-30";
    	if(specificRequest.getReviewAssessment().getExtension()||(!specificRequest.getReviewAssessment().getExtension()&&specificRequest.getReviewAssessment().getExtensionDateString().equals(exDate)))
    	{
		  	examinerAppoint_screen.setVisible(false);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			reviewAssessmentMain_screen.setVisible(false);
			SpecificRequest_pane.setVisible(false);
			extentionRequest_pane.setVisible(false);
			askForMoreDetails_pane.setVisible(true);
    	}
    	else {
    		GuiManager.ShowErrorPopup("You submit an exception\n,wait for the result");
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
     * Confirm execution func.
     *
     * @param event the event
     */
    @FXML
    void confirmExecution_func(ActionEvent event) {
    	String exDate ="0002-11-30";	      	
    	if(specificRequest.getReviewAssessment().getExtension()||(!specificRequest.getReviewAssessment().getExtension()&&specificRequest.getReviewAssessment().getExtensionDateString().equals(exDate)))
    	{
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ICM");
		alert.setHeaderText("Are you sure you're confirm the execution?");
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.OK)
		{

		  	examinerAppoint_screen.setVisible(true);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			reviewAssessmentMain_screen.setVisible(false);
			SpecificRequest_pane.setVisible(false);
			extentionRequest_pane.setVisible(false);
			askForMoreDetails_pane.setVisible(false);
			//combobox set
		    employeeList = FXCollections.observableArrayList(specificRequest.getCommissioner1().getFirstName()+" "+specificRequest.getCommissioner1().getLastName(),specificRequest.getCommissioner2().getFirstName()+" "+specificRequest.getCommissioner2().getLastName());
			examinerComboBox.setItems(employeeList);
		}
		else if (option.get() == ButtonType.CANCEL)
		{
			alert.close();
		}
    	}
    	else {
    		GuiManager.ShowErrorPopup("You submit an exception\n,wait for the result");
    	}
    }

    /**
     * Date picker func.
     *
     * @param event the event
     */
    @FXML
    void datePicker_func(ActionEvent event) {

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
    	String exDate ="0002-11-30";
		GregorianCalendar calendar = new GregorianCalendar();
    	Date dueDate = specificRequest.getReviewAssessment().getDueDate();
    	Date todayDate = calendar.getTime();
    	Calendar c = Calendar.getInstance();
        c.setTime(todayDate);
    	c.add(Calendar.DATE, 3);
    	Date todayPlusThree = c.getTime();
        if((todayPlusThree.after(dueDate) || dueDate.equals(todayPlusThree)) && !specificRequest.getReviewAssessment().getExtension() && (specificRequest.getReviewAssessment().getExtensionDateString().equals(exDate))) {
        	logout_pane.setVisible(true);
    		vbox1.setVisible(true);
    		reviewAssessmentMain_screen.setVisible(false);
    		SpecificRequest_pane.setVisible(false);
    		askForMoreDetails_pane.setVisible(false);
    		extentionRequest_pane.setVisible(true);
    		examinerAppoint_screen.setVisible(false);
        }
        else {
        	GuiManager.ShowErrorPopup("You cannot submit an exception");
        }
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
     * Log out display.
     *
     * @param event the event
     */
    @FXML
	protected
    void logOutDisplay(MouseEvent event) {
    	super.logOutDisplay(event);;
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
     * Reject execution func.
     *
     * @param event the event
     */
    @FXML
    void rejectExecution_func(ActionEvent event) {
    	String exDate ="0002-11-30";
    	if(specificRequest.getReviewAssessment().getExtension()||(!specificRequest.getReviewAssessment().getExtension()&&specificRequest.getReviewAssessment().getExtensionDateString().equals(exDate)))
    	{
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ICM");
		alert.setHeaderText("Are you sure you're reject the execution?");
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.OK)
		{	
	       // the next stage closing
			GuiManager.client.stageReject(specificRequest);
			logout_pane.setVisible(true);
			vbox1.setVisible(true);
			reviewAssessmentMain_screen.setVisible(true);
			SpecificRequest_pane.setVisible(false);
			askForMoreDetails_pane.setVisible(false);
			extentionRequest_pane.setVisible(false);
    		examinerAppoint_screen.setVisible(false);
	        GuiManager.client.getRequestsListReviewAssessment(userLoged);
	        sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
	        dueDate_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
	        viewRequestAssessmentReport_culomn.setCellValueFactory(new PropertyValueFactory<>("viewRequestButton"));
	        ObservableRequestsReviewAssessment = FXCollections.observableArrayList();
		}
		else if (option.get() == ButtonType.CANCEL)
		{
			alert.close();
    	}
    	}
    	else
    	{
    		GuiManager.ShowErrorPopup("You submit an exception\n,wait for the result");
    	}
    	
    }

    /**
     * Review assessment pressed.
     *
     * @param event the event
     */
    @FXML
    void reviewAssessmentPressed(ActionEvent event) {
		setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());		
		logout_pane.setVisible(true);
		vbox1.setVisible(true);
		reviewAssessmentMain_screen.setVisible(true);
		SpecificRequest_pane.setVisible(false);
		extentionRequest_pane.setVisible(false);
		examinerAppoint_screen.setVisible(false);
		askForMoreDetails_pane.setVisible(false);
        GuiManager.client.getRequestsListReviewAssessment(userLoged);
        sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
        dueDate_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        viewRequestAssessmentReport_culomn.setCellValueFactory(new PropertyValueFactory<>("viewRequestButton"));
        ObservableRequestsReviewAssessment = FXCollections.observableArrayList(); 
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

    	specificRequest.getReviewAssessment().setExtensionReason(extentionResons.getText());
    	specificRequest.getReviewAssessment().setExtensionDate(extentionDate);
        GuiManager.client.setTheExtentionRequest(specificRequest);
        GuiManager.ShowMessagePopup("The extension request was submitted!");
		logout_pane.setVisible(true);
		vbox1.setVisible(true);
		reviewAssessmentMain_screen.setVisible(true);
		SpecificRequest_pane.setVisible(false);
		extentionRequest_pane.setVisible(false);
		examinerAppoint_screen.setVisible(false);
		askForMoreDetails_pane.setVisible(false);
        GuiManager.client.getRequestsListReviewAssessment(userLoged);
        sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
        dueDate_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        viewRequestAssessmentReport_culomn.setCellValueFactory(new PropertyValueFactory<>("viewRequestButton"));
        ObservableRequestsReviewAssessment = FXCollections.observableArrayList(); 
        GuiManager.client.getRequestsListReviewAssessment(userLoged);

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
			
		logout_pane.setVisible(true);
		vbox1.setVisible(true);
		reviewAssessmentMain_screen.setVisible(true);
		SpecificRequest_pane.setVisible(false);
		extentionRequest_pane.setVisible(false);
		examinerAppoint_screen.setVisible(false);
		askForMoreDetails_pane.setVisible(false);
        GuiManager.client.getRequestsListReviewAssessment(userLoged);
        sn_column.setCellValueFactory(new PropertyValueFactory<>("requestNumber"));
        dueDate_column.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        viewRequestAssessmentReport_culomn.setCellValueFactory(new PropertyValueFactory<>("viewRequestButton"));
        ObservableRequestsReviewAssessment = FXCollections.observableArrayList(); 
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
			if(msg.Action == DBAction.getRequestsListForEmployee)	
			{
				Platform.runLater(() -> {
					getUserLogedIn().setReviewRequests(((User)msg.Data).getReviewRequests());
					setRequestsReviewerTable(getUserLogedIn());						
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
	 * Sets the requests reviewer table.
	 *
	 * @param user the new requests reviewer table
	 */
	protected void setRequestsReviewerTable(User user){
		for(Request r : user.getReviewRequests()) {
			ObservableRequest tempRequest = new ObservableRequest();
			tempRequest.setRequestNumber(new SimpleIntegerProperty(r.getRequestNumber()));
			tempRequest.setDueDate(new SimpleStringProperty(r.getReviewAssessment().getDueDateString()));
			tempRequest.setViewRequestButton(new Button("View"));
			tempRequest.getViewRequestButton().setOnAction(e->{
				viewRequestReviewer(r);
			});
			ObservableRequestsReviewAssessment.add(tempRequest);
		}
		table.setItems(ObservableRequestsReviewAssessment);
	
    Platform.runLater(new Runnable() {
    	@Override
    	public void run() {
    		table.getSortOrder().add(sn_column);
    		}
    	});
	}
	
	/**
	 * View request reviewer.
	 *
	 * @param request the request
	 */
	public void viewRequestReviewer(Request request) {	
		specificRequest = request;
		logout_pane.setVisible(true);
		vbox1.setVisible(true);
		reviewAssessmentMain_screen.setVisible(false);
		SpecificRequest_pane.setVisible(true);
		askForMoreDetails_pane.setVisible(false);
		extentionRequest_pane.setVisible(false);
		this.assessmentReportFill_text1.setText(request.getAssessment().getAssessmentReport());
    	this.requestNumberSet1.setText(request.getRequestStringNumber());
		this.requestSystem1.setText(request.getSystemName());
		this.applicantNameTextArea1.setText(request.getApplicantName());
		this.StatusTextArea1.setText(request.getStatus());
		this.submmitionDateTextArea1.setText(request.getSubmissionDateString());
		this.descriptionTextArea1.setText(request.getDescriptionOfExistingSituation());
		this.sugestionTextArea1.setText(request.getRequestDescription());
		this.reasonsTextArea1.setText(request.getReasons());
		this.notsTextArea1.setText(request.getComments());
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
     * Ask for detalis func.
     *
     * @param event the event
     */
    @FXML
    void AskForDetalis_func(ActionEvent event) {
    	specificRequest.getReviewAssessment().setExtraDetails(AskForDetalis_text.getText());;
    	GuiManager.ShowMessagePopup("The request return to assessment stage");
    	logout_pane.setVisible(true);
		vbox1.setVisible(true);
		reviewAssessmentMain_screen.setVisible(true);
		SpecificRequest_pane.setVisible(false);
		askForMoreDetails_pane.setVisible(false);
		extentionRequest_pane.setVisible(false);
    	GuiManager.client.AskForMoreDetailsReview(specificRequest);	
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
