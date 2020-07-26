package gui;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;

import entities.DBMessage;
import entities.User;
import gui.GuiManager.SCREENS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.IClient;

/**
 * This class is the controller of: HomePageScreen and it contains all the methods that manage the screen
 *
 */
public class HomePageController implements IClient, Initializable{
	  
  	/** The log out bnt. */
  	@FXML
	    private Button logOutBnt;
    
    /** The logout pane. */
    @FXML
    private Pane logout_pane;

    /** The home page. */
    @FXML
    private Pane homePage;

    /** The vbox 2. */
    @FXML
    private VBox vbox2;

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

    /** The requests btn. */
    @FXML
    private MenuButton requests_btn;

    /** The new request btn. */
    @FXML
    private MenuItem newRequest_btn;
    
    /** The View my requests btn. */
    @FXML
    private MenuItem ViewMyRequests_btn;
    
    /** The user name label. */
    @FXML
    private Label userNameLabel;
    
    /** The date label. */
    @FXML
    private Label dateLabel;
    
    /** The review assessment btn. */
    @FXML
    private Button reviewAssessment_btn;

    /** The submmit bnt. */
    @FXML
    private Button submmit_bnt;

    /** The my request bnt. */
    @FXML
    private Button myRequest_bnt;
	
	/** The this node. */
	private Node thisNode;
	
	/** The user loged. */
	private User userLoged;
	
	/** The anchor pane. */
	@FXML
    private AnchorPane anchorPane;

	    /**
    	 * Pressed supervisor.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void PressedSupervisor(ActionEvent event) 
	    {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
	    	thisNode = ((Node) event.getSource());
			GuiManager.SwitchScene(GuiManager.SCREENS.Supervisor, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);	
	    }
	    

	    /**
    	 * Pressed new request.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void Pressed_newRequest(ActionEvent event) {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
	       	thisNode = ((Node) event.getSource());
			GuiManager.SwitchScene(GuiManager.SCREENS.CreateRequests, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);	
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
		
		
	}
    
    /**
     * Assessor pressed.
     *
     * @param event the event
     */
    @FXML
    void assessor_pressed(ActionEvent event) {
    	thisNode = ((Node) event.getSource());
		GuiManager.SwitchScene(GuiManager.SCREENS.Assessor,(Stage)thisNode.getScene().getWindow());
    }

    /**
     * Executor pressed.
     *
     * @param event the event
     */
    @FXML
    void executor_pressed(ActionEvent event) {
    	thisNode = ((Node) event.getSource());
		GuiManager.SwitchScene(GuiManager.SCREENS.Executor, (Stage)thisNode.getScene().getWindow());
    }

    /**
     * Exsaminer pressed.
     *
     * @param event the event
     */
    @FXML
    void exsaminer_pressed(ActionEvent event) {
    	thisNode = ((Node) event.getSource());
		GuiManager.SwitchScene(GuiManager.SCREENS.Examiner, (Stage)thisNode.getScene().getWindow());
    }

    /**
     * My account pressed.
     *
     * @param event the event
     */
    @FXML
    void myAccount_pressed(ActionEvent event) {
    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
    	thisNode = ((Node) event.getSource());
		GuiManager.SwitchScene(GuiManager.SCREENS.Information, (Stage)thisNode.getScene().getWindow());
		GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
    }

    /**
     * Notifications pressed.
     *
     * @param event the event
     */
    @FXML
    void notifications_pressed(ActionEvent event) {
    	thisNode = ((Node) event.getSource());
		GuiManager.SwitchScene(GuiManager.SCREENS.Notifications, (Stage)thisNode.getScene().getWindow());
    }
    
    /**
     * Log out display.
     *
     * @param event the event
     */
    @FXML
    protected void logOutDisplay(MouseEvent event) {
    	 // logout
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("ICM Log Out");
		alert.setHeaderText("Are you sure you want to log out?");
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.OK)
		{
			Stage SeondStage = new Stage();
			SeondStage.setTitle("ICM");
			((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
			GuiManager.SwitchScene(SCREENS.login, SeondStage);
			
		}

		else if (option.get() == ButtonType.CANCEL)
		{
			alert.close();
		}
    }

    /**
     * My request pressed.
     *
     * @param event the event
     */
    @FXML
    void myRequestPressed(ActionEvent event) {
    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
		thisNode = ((Node) event.getSource());
		GuiManager.SwitchScene(GuiManager.SCREENS.MyRequests, (Stage)thisNode.getScene().getWindow());
		GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
    }

    /**
     * Review assessment pressed.
     *
     * @param event the event
     */
    @FXML
    void reviewAssessmentPressed(ActionEvent event) {
    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
       	thisNode = ((Node) event.getSource());
    		GuiManager.SwitchScene(GuiManager.SCREENS.ReviewAssessment,(Stage)thisNode.getScene().getWindow());
    		GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
    }

    /**
     * Submit pressed.
     *
     * @param event the event
     */
    @FXML
    void submitPressed(ActionEvent event) {
    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
		thisNode = ((Node) event.getSource());
		GuiManager.SwitchScene(GuiManager.SCREENS.CreateRequests, (Stage)thisNode.getScene().getWindow());
		GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
    }


	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());
		if(getUserLogedIn().getType().equals("supervisor")) 
			supervisor_btn.setDisable(false);
		else
			supervisor_btn.setDisable(true);
	}

}
