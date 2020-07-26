
package gui;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import entities.DBMessage;
import entities.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import logic.IClient;

/**
 * This class is the controller of: MyRequest screen and it contains all the methods that manage the screen
 *
 */
public class MyRequestController extends HomePageController implements IClient, Initializable {

	/** The anchor pane. */
	@FXML
	private AnchorPane anchorPane;

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

	/** The My screen screen. */
	@FXML
	private Pane MyScreenScreen;

	/** The Enter request 12. */
	@FXML
	private TextField EnterRequest12;

	/** The Ok search request. */
	@FXML
	private Button OkSearchRequest;

	/** The Request dont exist label 12. */
	@FXML
	private Label RequestDontExistLabel12;

	/** The Visible timeline. */
	@FXML
	private StackPane VisibleTimeline;

	/** The Execution labe. */
	@FXML
	private Label ExecutionLabe;

	/** The Assessment label. */
	@FXML
	private Label AssessmentLabel;

	/** The Review label. */
	@FXML
	private Label ReviewLabel;

	/** The Exeaminition label. */
	@FXML
	private Label ExeaminitionLabel;

	/** The Closing label. */
	@FXML
	private Label ClosingLabel;

	/** The Execution em. */
	@FXML
	private Circle Execution_em;

	/** The Execution fill. */
	@FXML
	private Circle Execution_fill;

	/** The Assessment em. */
	@FXML
	private Circle Assessment_em;

	/** The Assessment fill. */
	@FXML
	private Circle Assessment_fill;

	/** The Review em. */
	@FXML
	private Circle Review_em;

	/** The Review fill. */
	@FXML
	private Circle Review_fill;

	/** The Closing em. */
	@FXML
	private Circle Closing_em;

	/** The Closing fill. */
	@FXML
	private Circle Closing_fill;

	/** The Examination em. */
	@FXML
	private Circle Examination_em;

	/** The Exeaminition fill. */
	@FXML
	private Circle Exeaminition_fill;

	/** The Execution label. */
	@FXML
	private Label ExecutionLabel;
	
	/** The suspend label. */
	@FXML
	private Label suspendLabel;

	/** The user loged. */
	private User userLoged;

	/**
	 * Ok pressed.
	 *
	 * @param event the event
	 */
	@FXML
	void OkPressed(ActionEvent event) {
		Execution_em.setVisible(true);
		Execution_fill.setVisible(false);
		Assessment_em.setVisible(true);
		Assessment_fill.setVisible(false);
		Review_em.setVisible(true);
		Review_fill.setVisible(false);
		Closing_em.setVisible(true);
		Closing_fill.setVisible(false);
		Examination_em.setVisible(true);
		Exeaminition_fill.setVisible(false);
		suspendLabel.setVisible(false);
	//	RequestDontExistLabel12.setVisible(true);

		User user = new User(userLoged.getIdNumber(), Integer.parseInt(EnterRequest12.getText()));
		GuiManager.client.CheckMyRequest(user);// sent the request number to check if is exist to server

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
	 * Gets the message from server.
	 *
	 * @param msg the msg
	 * @return the message from server
	 */
	@Override
	public void getMessageFromServer(DBMessage msg) {
		switch (msg.Action) {
		case CheckMyRequestt: {
			switch (msg.Data.toString()) {
			case "assessment": {
				Assessment_fill.setVisible(true);
				RequestDontExistLabel12.setVisible(false);
				break;
			}
			case "initial": {
				Assessment_fill.setVisible(true);
				RequestDontExistLabel12.setVisible(false);
				break;
			}
			case "reviewassessment": {
				Review_fill.setVisible(true);
				RequestDontExistLabel12.setVisible(false);
				break;
			}
			case "examination": {
				Exeaminition_fill.setVisible(true);
				RequestDontExistLabel12.setVisible(false);
				break;
			}
			case "execution": {
				Execution_fill.setVisible(true);
				RequestDontExistLabel12.setVisible(false);
				break;
			}
			case "closing": {
				RequestDontExistLabel12.setVisible(false);
				Closing_fill.setVisible(true);
				break;
			}
			case "suspend": {
				RequestDontExistLabel12.setVisible(false);
				suspendLabel.setVisible(true);
				break;
			}
			default:
				RequestDontExistLabel12.setVisible(true);
				break;

			}
		}
		}
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
		this.userLoged = userLoged;
		String userName = userLoged.getUserName();
		userNameLabel.setText(userName);
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String string = format.format(calendar.getTime());
		dateLabel.setText(string);

	}

	/**
	 * Initialize.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Execution_em.setVisible(true);
		Execution_fill.setVisible(false);
		Assessment_em.setVisible(true);
		Assessment_fill.setVisible(false);
		Review_em.setVisible(true);
		Review_fill.setVisible(false);
		Closing_em.setVisible(true);
		Closing_fill.setVisible(false);
		Examination_em.setVisible(true);
		Exeaminition_fill.setVisible(false);
		suspendLabel.setVisible(false);

		setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());
		if(!getUserLogedIn().getType().equals("supervisor"))
			supervisor_btn.setDisable(true);
		else 
			supervisor_btn.setDisable(false);
			
	}

}
