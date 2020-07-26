package gui;


import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import entities.DBMessage;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.IClient;

/**
 * This class is the controller of: UserInformationScreen and it contains all the methods that manage the screen
 *
 */
public class UserInformationController extends HomePageController implements IClient, Initializable{	
	
	/** The anchor pane. */
	@FXML
    private AnchorPane anchorPane;

    /** The home page. */
    @FXML
    private Pane homePage;

    /** The vbox 1. */
    @FXML
    private VBox vbox1;

    /** The Registration btn. */
    @FXML
    private Button Registration_btn;

    /** The Statistical btn. */
    @FXML
    private Button Statistical_btn;

    /** The Emplyees btn. */
    @FXML
    private Button Emplyees_btn;

    /** The my account bnt 1. */
    @FXML
    private Button myAccount_bnt1;

    /** The submmit bnt. */
    @FXML
    private Button submmit_bnt;

    /** The my request bnt. */
    @FXML
    private Button myRequest_bnt;

    /** The user information screen. */
    @FXML
    private Pane userInformationScreen;

    /** The First name. */
    @FXML
    private Label FirstName;

    /** The First name text. */
    @FXML
    private Label FirstNameText;

    /** The Last name. */
    @FXML
    private Label LastName;

    /** The Last name text. */
    @FXML
    private Label LastNameText;

    /** The User name. */
    @FXML
    private Label UserName;

    /** The User name text. */
    @FXML
    private Label UserNameText;

    /** The ID number. */
    @FXML
    private Label IDNumber;

    /** The ID number text. */
    @FXML
    private Label IDNumberText;

    /** The Mail. */
    @FXML
    private Label Mail;

    /** The Mail text. */
    @FXML
    private Label MailText;

    /** The Phone number. */
    @FXML
    private Label PhoneNumber;

    /** The Phone number text. */
    @FXML
    private Label PhoneNumberText;

    /** The User type. */
    @FXML
    private Label UserType;

    /** The User type text. */
    @FXML
    private Label UserTypeText;

    /** The Department name. */
    @FXML
    private Label DepartmentName;

    /** The Department name text. */
    @FXML
    private Label DepartmentNameText;

    /** The Password. */
    @FXML
    private Label Password;

    /** The Password text. */
    @FXML
    private Label PasswordText;

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

    /** The vbox 2. */
    @FXML
private VBox vbox2;


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

/** The user loged. */
private User userLoged;

    /**
     * Pressed emplyees.
     *
     * @param event the event
     */
    @FXML
    void PressedEmplyees(ActionEvent event) {

    }

    /**
     * Pressed registration.
     *
     * @param event the event
     */
    @FXML
    void PressedRegistration(ActionEvent event) {

    }

    /**
     * Pressed statistical.
     *
     * @param event the event
     */
    @FXML
    void PressedStatistical(ActionEvent event) {

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
     * Assessor pressed.
     *
     * @param event the event
     */
    @FXML
    void assessor_pressed(ActionEvent event) {
    	super.assessor_pressed(event);
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
     * Log out display.
     *
     * @param event the event
     */
    @FXML
    protected void logOutDisplay(MouseEvent event) {
    	super.logOutDisplay(event);
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
     * My account pressed.
     *
     * @param event the event
     */
    @FXML
    void myAccount_pressed(ActionEvent event) {
    	super.myAccount_pressed(event);
    }

	
	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());
		if(!getUserLogedIn().getType().equals("supervisor"))
			supervisor_btn.setDisable(true);
		else 
			supervisor_btn.setDisable(false);
		logout_pane.setVisible(true);
		homePage.setVisible(true);
		userInformationScreen.setVisible(true);
	//	System.out.println(getUserLogedIn().getId());
		
		this.FirstNameText.setText(userLoged.getFirstName());
		this.LastNameText.setText(userLoged.getLastName());
		this.UserNameText.setText(userLoged.getUserName());
		this.IDNumberText.setText(userLoged.getId());
		this.MailText.setText(userLoged.getMail());
		this.PhoneNumberText.setText(userLoged.getPhoneNumber());
		this.UserTypeText.setText(userLoged.getType());
		this.DepartmentNameText.setText(userLoged.getDepartmentName());
		this.PasswordText.setText(userLoged.getPassword());
		//GuiManager.client.FillDetails(getUserLogedIn());
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
	 * Gets the user loged in.
	 *
	 * @return the user loged in
	 */
	@Override
	public User getUserLogedIn() {//îùúîù ùëøâò îçåáø
		// TODO Auto-generated method stub
		return userLoged; 
	}

	/**
	 * Sets the user loged in.
	 *
	 * @param userLoged the new user loged in
	 */
	@Override
	public void setUserLogedIn(User userLoged) {
		// TODO Auto-generated method stub 

        this.userLoged=userLoged;
        String userName = userLoged.getUserName();
        userNameLabel.setText(userName);
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String string = format.format(calendar.getTime());
        dateLabel.setText(string);


	}


}