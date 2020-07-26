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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.IClient;
/**
 * This class is the controller of: UserInformationDirectorScreen and it contains all the methods that manage the screen
 *
 */
public class UserInformationDirectorController extends DirectorHomePageController implements IClient, Initializable{
    
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
    
    /** The user loged. */
    private User userLoged;
    
    /** The home bnt. */
    @FXML
    private Button home_bnt;
    
    /** The this node. */
    private Node thisNode;
    
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
