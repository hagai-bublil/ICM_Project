package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.IClient;
import observableEntities.ObservableRequest;

/**
 * This class is the controller of: RequestDataScreen and it contains all the methods that manage the screen
 *
 */
public class RequestsDataController  extends DirectorHomePageController implements Initializable,IClient{
    
    /** The anchor pane. */
    @FXML
    private AnchorPane anchorPane;
    
    /** The home page. */
    @FXML
    private Pane homePage;

    /** The vbox 1. */
    @FXML
    private VBox vbox1;

    /** The Request page. */
    @FXML
    private Pane RequestPage;

    /** The Activity report screen 1. */
    @FXML
    private Label ActivityReportScreen1;

    /** The Request data page. */
    @FXML
    private Pane RequestDataPage;

    /** The contents page. */
    @FXML
    private VBox contentsPage;

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


    /** The request view pane. */
    @FXML
    private Pane requestViewPane;

    /** The req num lablel. */
    @FXML
    private Label reqNumLablel;

    /** The information sys text area. */
    @FXML
    private Label informationSysTextArea;

    /** The info sys area. */
    @FXML
    private TextArea infoSysArea;

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

    /** The logout pane. */
    @FXML
    private Pane logout_pane;

    /** The user name label. */
    @FXML
    private Label userNameLabel;

    /** The date label. */
    @FXML
    private Label dateLabel;

    /** The view doc bnt. */
    @FXML
    private Button viewDocBnt;

    /** The unsuspend bnt. */
    @FXML
    private Button unsuspend_bnt;

    /** The log out bnt. */
    @FXML
    private Button logOutBnt;
    
    /** The prev bnt. */
    @FXML
    private Button prev_bnt;
    
    /** The home bnt. */
    @FXML
    private Button home_bnt;
    
    
    /** The user loged. */
    private User userLoged;
    
    /** The observablerequest list. */
    private ObservableList<ObservableRequest> observablerequestList;// for table view...
    
    /** The current req. */
    Request currentReq;
    
    /** The reqnum. */
    private int reqnum;
    
    /** The this node. */
    private Node thisNode;

  
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
		unsuspend_bnt.setDisable(true);
		RequestDataPage.setVisible(true);
		logout_pane.setVisible(true);
		requestViewPane.setVisible(false);
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
			if (msg.Action == DBAction.getRequestsListForSupervisor)
			{
				// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
				Platform.runLater(() -> {
					getUserLogedIn().setSupervisorRequests(((User)msg.Data).getSupervisorRequests());
					for(Request r:getUserLogedIn().getSupervisorRequests()) {
						System.out.println(r.toString());
					}
					loadRequestsTable(getUserLogedIn());
				});
			}
			else 
			{
				if(msg.Action == DBAction.OpenFile) 
				{
					Platform.runLater(() -> {
						if((String)msg.Data==null) {
							GuiManager.ShowErrorPopup("The request dont have any documents");
						}
						
					});
				}
				else {
					if(msg.Action == DBAction.unsuspendRequest) {
						Platform.runLater(() -> {
							if((boolean)msg.Data==false) {
								GuiManager.ShowErrorPopup("problem");
							}
							else {
								//GuiManager.ShowMessagePopup("The due date has changed");
								unsuspend_bnt.setDisable(true);
								StatusTextArea.setText(currentReq.getStatus());
								GuiManager.ShowMessagePopup("Request un-suspended successfully");
							
							}
						});
					}
				}
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
	 * Open view request pane.
	 */
	//this function is called when the user presses the view button it will open the request details form and buttons for the supervisor to use
	@SuppressWarnings("deprecation")
	protected void openViewRequestPane() {
			//show only the requestViewPane
			unsuspend_bnt.setDisable(true);
			RequestDataPage.setVisible(false);
			logout_pane.setVisible(true);
			requestViewPane.setVisible(true);
			reqNumLablel.setText("Request "+currentReq.getRequestNumber());
			if(currentReq.getStatus().equals("suspend"))
				unsuspend_bnt.setDisable(false);
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
   	 * Unsuspend pressed.
   	 *
   	 * @param event the event
   	 */
   	@FXML
	    void unsuspend_Pressed(ActionEvent event) {
		   GuiManager.client.unsuspendRequest(this.currentReq);
	    }

	    /**
    	 * View doc pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void viewDocPressed(ActionEvent event) {
			GuiManager.client.OpenFile(this.currentReq.getRequestNumber());
	    }
	    
	    /**
    	 * Prev pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void prev_pressed(ActionEvent event) {
	    	RequestDataPage.setVisible(true);
			requestViewPane.setVisible(false);
			this.userLoged.getSupervisorRequests().clear();
			observablerequestList.clear();
			Platform.runLater(() -> {
				GuiManager.client.getRequestsList(getUserLogedIn());
				
			});
	    }
	    
    	/**
    	 * Home pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void home_pressed(ActionEvent event) {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
	    	thisNode = ((Node) event.getSource());
	    	//thisNode.getScene().getWindow().hide();
			GuiManager.SwitchScene(GuiManager.SCREENS.DirectorHomePage, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
	    }
	    
	  


	    
}
