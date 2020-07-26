package gui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import MyFile.MyFile;
import entities.DBMessage;
import entities.ICMMessage;
import entities.Request;
import entities.User;
import entities.DBMessage.DBAction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import logic.IClient;
import observableEntities.ObservableMessage;

/**
 * This class is the controller of: ExternHomePageScreen and it contains all the methods that manage the screen
 *
 */
public class ExternHomePageController extends NotificationController implements IClient, Initializable {
	
	/** The anchor pane. */
	@FXML
	private AnchorPane anchorPane; //

	/** The home page. */
	@FXML
	private Pane homePage;//

	/** The vbox 2. */
	@FXML
	private VBox vbox2;//

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

	/** The user loged. */
	private User userLoged;
	
	/** The notification pane. */
	@FXML
	private Pane notificationPane; ///

	/** The notifications table view. */
	@FXML
	private TableView<ObservableMessage> notificationsTableView;

	/** The date sent table column. */
	@FXML
	private TableColumn<ObservableMessage, String> dateSentTableColumn;

	/** The notification content table column. */
	@FXML
	private TableColumn<ObservableMessage, String> notificationContentTableColumn;

	/** The erase table column. */
	@FXML
	private TableColumn<ObservableMessage, Button> eraseTableColumn;

	/** The observable msg list. */
	protected ObservableList<ObservableMessage> observableMsgList;// for table view

	/** The user information pane. */
	@FXML
	private Pane userInformationPane; /////

	/** The First name. */
	@FXML
	private Label FirstName; /////

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

	/** The My requests pane. */
	@FXML
	private Pane MyRequestsPane;

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

	/** The Exeaminition label. */
	@FXML
	private Label ExeaminitionLabel;

	/** The Assessment label. */
	@FXML
	private Label AssessmentLabel;

	/** The Review label. */
	@FXML
	private Label ReviewLabel;

	/** The Closing label. */
	@FXML
	private Label ClosingLabel;

	/** The Closing label 2. */
	@FXML
	private Label ClosingLabel2;

	/** The vbox 1. */
	@FXML
	private VBox vbox1;

	/** The notofication bnt. */
	@FXML
	private Button notofication_bnt;

	/** The my account bnt 1. */
	@FXML
	private Button myAccount_bnt1;

	/** The submit request pane. */
	@FXML
	private Pane submitRequestPane;

	/** The my requests 1. */
	@FXML
	private Label myRequests1;

	/** The System name 1. */
	@FXML
	private Label SystemName1;

	/** The cmb list system name 1. */
	@FXML
	private ComboBox<String> cmbListSystemName1;

	/** The Insert system name 1. */
	@FXML
	private Label InsertSystemName1;

	/** The Description of the existing situation 1. */
	@FXML
	private Label DescriptionOfTheExistingSituation1;

	/** The Description of the existing situation area 1. */
	@FXML
	private TextArea DescriptionOfTheExistingSituation_area1;

	/** The Insert the description of the existing situation 1. */
	@FXML
	private Label InsertTheDescriptionOfTheExistingSituation1;

	/** The Description of the current situation 1. */
	@FXML
	private Label DescriptionOfTheCurrentSituation1;

	/** The Description of change area 1. */
	@FXML
	private TextArea DescriptionOfChange_area1;

	/** The Insert the description of change 1. */
	@FXML
	private Label InsertTheDescriptionOfChange1;

	/** The Reason of the request 1. */
	@FXML
	private Label ReasonOfTheRequest1;

	/** The Your request ID. */
	@FXML
	private Label YourRequestID;

	/** The Reason of the request area 1. */
	@FXML
	private TextArea ReasonOfTheRequest_area1;

	/** The Insert the reason of the request 1. */
	@FXML
	private Label InsertTheReasonOfTheRequest1;

	/** The Notes 1. */
	@FXML
	private Label Notes1;

	/** The Notes area 1. */
	@FXML
	private TextArea Notes_area1;

	/** The Add documents. */
	@FXML
	private Button AddDocuments;

	/** The doc lbl. */
	@FXML
	private Label docLbl;

	/** The Submit button 1. */
	@FXML
	private Button Submit_button1;

	/** The Yourrequestsubmited 1. */
	@FXML
	private Label Yourrequestsubmited1;
	

//	@FXML
//	private AnchorPane anchorPane;
//
//	@FXML
//	private Pane homePage;
//
//	@FXML
//	private VBox vbox2;
//
//	@FXML
//	private VBox vbox1;

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

//	@FXML
//	private Button notofication_bnt;
//
//	@FXML
//	private Button myAccount_bnt1;
//
//	@FXML
//	private Button submmit_bnt;
//
//	@FXML
//	private Button myRequest_bnt;
//
//	@FXML
//	private Pane logout_pane;
//
//	@FXML
//	private Label userNameLabel;
//
//	@FXML
//	private Label dateLabel;
//
//	@FXML
//	private Button logOutBnt;
//
//	@FXML
//	private Pane MyScreenScreen;
//
//	@FXML
//	private TextField EnterRequest12;
//
//	@FXML
//	private Button OkSearchRequest;
//
//	@FXML
//	private Label RequestDontExistLabel12;
//
//	@FXML
//	private StackPane VisibleTimeline;

	/** The Execution labe. */
@FXML
	private Label ExecutionLabe;

//	@FXML
//	private Label AssessmentLabel;
//
//	@FXML
//	private Label ReviewLabel;
//
//	@FXML
//	private Label ExeaminitionLabel;
//
//	@FXML
//	private Label ClosingLabel;

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


	/** The request num. */
	private int requestNum;
	
	/** The system name. */
	private String systemName;
	
	/** The description of the existing situation. */
	private String descriptionOfTheExistingSituation;
	
	/** The request description. */
	private String requestDescription;
	
	/** The reasons. */
	private String reasons;
	
	/** The notes. */
	private String notes;

	/** The request give. */
	private Request requestGive;

	/** The list. */
	private ObservableList<String> list = FXCollections.observableArrayList("moodle", "informationStation", "library",
			"classroom","lab","webSite");

	/** The flag. */
	private boolean flag = true;
	
	/** The count request. */
	public static int countRequest = 1;
	
	/** The count msg. */
	private int count, countMsg;
	
	/** The flag doc. */
	private boolean flagDoc = false;
	
	/** The msg flag. */
	private boolean msgFlag = false;
	
	/** The flag rqst. */
	private boolean flagRqst = false;
	
	/** The Constant DEFAULT_PORT. */
	final public static int DEFAULT_PORT = 5555; // The default port to connect on.

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
		vbox2.setVisible(false);
		notificationPane.setVisible(false);
		submitRequestPane.setVisible(false);
		MyRequestsPane.setVisible(false);
		userInformationPane.setVisible(true);
		this.FirstNameText.setText(userLoged.getFirstName());
		this.LastNameText.setText(userLoged.getLastName());
		this.UserNameText.setText(userLoged.getUserName());
		this.IDNumberText.setText(userLoged.getId());
		this.MailText.setText(userLoged.getMail());
		this.PhoneNumberText.setText(userLoged.getPhoneNumber());
		this.UserTypeText.setText(userLoged.getType());
		this.DepartmentNameText.setText(userLoged.getDepartmentName());
		this.PasswordText.setText(userLoged.getPassword());
	}

	/**
	 * My request pressed.
	 *
	 * @param event the event
	 */
	@FXML
	void myRequestPressed(ActionEvent event) {
		vbox2.setVisible(false);
		notificationPane.setVisible(false);
		submitRequestPane.setVisible(false);
		userInformationPane.setVisible(false);
		MyRequestsPane.setVisible(true);
	}

	/**
	 * Notifications pressed.
	 *
	 * @param event the event
	 */
	@FXML
	void notifications_pressed(ActionEvent event) {
		vbox2.setVisible(false);
		submitRequestPane.setVisible(false);
		userInformationPane.setVisible(false);
		MyRequestsPane.setVisible(false);
		notificationPane.setVisible(true);
	}

	/**
	 * Submit pressed.
	 *
	 * @param event the event
	 */
	@FXML
	void submitPressed(ActionEvent event) {
		vbox2.setVisible(false);
		userInformationPane.setVisible(false);
		MyRequestsPane.setVisible(false);
		notificationPane.setVisible(false);
		submitRequestPane.setVisible(true);
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		GuiManager.client.getNamesOfEmployees();
		setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());
		GuiManager.client.getNotificationsForUser(getUserLogedIn());
		dateSentTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateSent"));
		notificationContentTableColumn.setCellValueFactory(new PropertyValueFactory<>("msgContent"));
		eraseTableColumn.setCellValueFactory(new PropertyValueFactory<>("eraseButton"));
		observableMsgList = FXCollections.observableArrayList();
		cmbListSystemName1.setItems(list);// inital the combo box

		/*
		 * VisibleTimeline.setVisible(false);//Hidden timeline
		 * ExeaminitionStatus.setVisible(false); ClosingStatus.setVisible(false);
		 * ExecutionStatus.setVisible(false); ReviewStatus.setVisible(false);
		 * AssessmentStatus.setVisible(false);
		 */
	}

	/**
	 * Gets the message from server.
	 *
	 * @param msg the msg
	 * @return the message from server
	 */
	@Override
	public void getMessageFromServer(DBMessage msg) {
		DBMessage message = (DBMessage) msg;
		DBAction action = message.Action;
		switch (action) {
		case getNotificationsForUser: {
			// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
			Platform.runLater(() -> {
				getUserLogedIn().setMessages(((User) msg.Data).getMessages());
				setMessages((User) msg.Data);
			});
			break;
		}
		case getNamesOfEmployees: {
			requestGive = (Request) msg.Data;
			break;
		}
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
		case FindMaxNumOfIdRequest: {
			count = (int) msg.Data;
			flagRqst = true;
			break;
		}
		case FindMaxNumOfIdMsg: {
			countMsg = (int) msg.Data;
			msgFlag = true;
			break;
		}
		case CreateNewRequest: {
			Yourrequestsubmited1.setVisible(true);
			YourRequestID.setVisible(true);
			flagDoc = false;
			Submit_button1.setDisable(true);
			AddDocuments.setDisable(true);
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			DescriptionOfTheExistingSituation_area1.setText("");
			DescriptionOfChange_area1.setText("");
			ReasonOfTheRequest_area1.setText("");
			Notes_area1.setText("");
			msgFlag = false;
			Submit_button1.setDisable(false);
			AddDocuments.setDisable(false);

			Yourrequestsubmited1.setVisible(false);
			YourRequestID.setVisible(false);
			docLbl.setVisible(false);

			// GuiManager.client.getMaxNumOfMsg();// call the function from ClientControllet
			System.out.println(countMsg);
			ICMMessage msgSupervisor = new ICMMessage(Integer.toString(++countMsg), dateLabel.getText(), // idmessage
																											// missed
					"A new change request was accepted. Application Number: " + Integer.toString(count));
			GuiManager.client.SendMessageToSupervisor(msgSupervisor);// call the function from ClientControllet

		}
		default:
			break;
		}

	}

	/**
	 * Sets the messages.
	 *
	 * @param user the new messages
	 */
	protected void setMessages(User user) {
		for (ICMMessage msg : getUserLogedIn().getMessages()) {
			System.out.println(msg.getMessageContent() + " " + msg.getRecipientUserId());
			@SuppressWarnings("deprecation")
			ObservableMessage tempMsg = new ObservableMessage(msg.getId(),
					msg.getDateSending().toLocaleString().substring(10), msg.getMessageContent());
			tempMsg.getEraseButton().setOnAction(e -> {
				ObservableMessage selectedItem = notificationsTableView.getSelectionModel().getSelectedItem();
				notificationsTableView.getItems().remove(selectedItem);
				remove(selectedItem);
			});
			observableMsgList.add(tempMsg);
		}
		notificationsTableView.setItems(observableMsgList);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				notificationsTableView.getSortOrder().add(dateSentTableColumn);
			}
		});
	}

	/**
	 * Removes the.
	 *
	 * @param selectedItem the selected item
	 */
	@SuppressWarnings("unlikely-arg-type")
	private void remove(ObservableMessage selectedItem) {
		getUserLogedIn().getMessages().remove(selectedItem); /* Delete the message from the user's message list */
		GuiManager.client.removeNotification(selectedItem.getIndex());
	}

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
		RequestDontExistLabel12.setVisible(false);

		User user = new User(userLoged.getIdNumber(), Integer.parseInt(EnterRequest12.getText()));
		GuiManager.client.CheckMyRequest(user);// sent the request number to check if is exist to server

	}

	/**
	 * Gets the request number text field.
	 *
	 * @return the request number text field
	 */
	TextField getRequestNumber_textField() {
		return EnterRequest12;
	}

	/**
	 * Pressed add documents.
	 *
	 * @param event the event
	 */
	@FXML
	void PressedAddDocuments(ActionEvent event) {

		flagDoc = true;

		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("All", "*.txt", "*.pdf", "*.jpg");

		FileChooser.ExtensionFilter fileExtensions1 = new FileChooser.ExtensionFilter("txt", "*.txt");
		FileChooser.ExtensionFilter fileExtensions2 = new FileChooser.ExtensionFilter("pdf", "*.pdf");
		FileChooser.ExtensionFilter fileExtensions3 = new FileChooser.ExtensionFilter("jpg", "*.jpg");

		fileChooser.getExtensionFilters().add(fileExtensions);
		fileChooser.getExtensionFilters().add(fileExtensions1);
		fileChooser.getExtensionFilters().add(fileExtensions2);
		fileChooser.getExtensionFilters().add(fileExtensions3);

		Window wind = AddDocuments.getScene().getWindow();
		docLbl.setText(fileChooser.showOpenDialog(wind).getPath());
		docLbl.setVisible(true);
	}

	/**
	 * Submit pressed.
	 *
	 * @param event the event
	 */
	@FXML
	void SubmitPressed(ActionEvent event) {// create the request and send to function that insert it to DB
		System.out.println("aaa");
		GuiManager.client.getMaxNumOfRequest();// func that find the max requestId
		System.out.println("bbb");
		GuiManager.client.getMaxNumOfMsg();// func that find the max msgID
		System.out.println("ccc");
		while (msgFlag != true) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("ddd");
		count++;

		Yourrequestsubmited1.setVisible(false);
		YourRequestID.setVisible(false);
		YourRequestID.setText("Your request's ID is " + count);

		try {
			 systemName= cmbListSystemName1.getSelectionModel().getSelectedItem();
			if (systemName==null) {// check if system name insert
				flag = false;
				InsertSystemName1.setVisible(true);

			} else {
				flag = true;
				InsertSystemName1.setVisible(false);
			}
			descriptionOfTheExistingSituation = getTextFromTextAreaAndCheckIfInsert(// get the description of the exist
																					// situation
					DescriptionOfTheExistingSituation_area1, InsertTheDescriptionOfTheExistingSituation1);

			requestDescription = getTextFromTextAreaAndCheckIfInsert(DescriptionOfChange_area1, // get the description
																								// of the request
					InsertTheDescriptionOfChange1);

			reasons = getTextFromTextAreaAndCheckIfInsert(ReasonOfTheRequest_area1, InsertTheReasonOfTheRequest1);// get
																													// the
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			} 

			if (flag == true) {
				System.out.println("fff");
				notes = Notes_area1.getText();
				Request request = new Request(count, this.userLoged.getFirstName() + " " + this.userLoged.getLastName(),
						this.userLoged.getId(), systemName, descriptionOfTheExistingSituation, requestDescription,
						null/* (Date)dateLabe.getText() */, notes, reasons, requestGive.getSupervisor(),
						requestGive.getChairman(), requestGive.getCommissioner1(), requestGive.getCommissioner2());

				System.out.println(request.getRequestNumber() + "', '" + request.getApplicantName() + "', '"
						+ request.getApplicantId());
				GuiManager.client.SubmitNewRequest(request);// call the function that submit the request to DB
				if (flagDoc) {
					MyFile msg = new MyFile(docLbl.getText(), count);
					String LocalfilePath = docLbl.getText();

					try {

						File newFile = new File(LocalfilePath);

						byte[] mybytearray = new byte[(int) newFile.length()];
						FileInputStream fis = new FileInputStream(newFile);
						BufferedInputStream bis = new BufferedInputStream(fis);

						msg.initArray(mybytearray.length);
						msg.setSize(mybytearray.length);

						bis.read(msg.getMybytearray(), 0, mybytearray.length);
					} catch (Exception e) {

						System.out.println("Error send (Files)msg) to ClientController");
					}
					while (flagDoc != true) {
						try {
							Thread.sleep(500);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					if (flagDoc)
						GuiManager.client.SubmitDocuments(msg);// func that insert doc to DB
					// GuiManager.client.SubmitDocuments(msg);// func that insert doc to DB
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

//   @FXML
//    void SubmitPressed(ActionEvent event) {
//    	// create the request and send to function that insert it to DB
//    			GuiManager.client.getMaxNumOfRequest();// func that find the max requestId
//    			GuiManager.client.getMaxNumOfMsg();// func that find the max msgID
//    			while (msgFlag != true) {
//    				try {
//    					Thread.sleep(500);
//    				} catch (Exception e) {
//    					e.printStackTrace();
//    				}
//    			}
//    			count++;
//
//    			Yourrequestsubmited1.setVisible(false);
//
//    			try {
//    				systemName = (String) cmbListSystemName1.getSelectionModel().getSelectedItem();
//    				if (systemName.length() < 2) {// check if system name insert
//    					flag = false;
//    					InsertSystemName1.setVisible(true);
//
//    				} else {
//    					flag = true;
//    					InsertSystemName1.setVisible(false);
//    				}
//    				descriptionOfTheExistingSituation = getTextFromTextAreaAndCheckIfInsert(// get the description of the exist
//    																						// situation
//    						DescriptionOfTheExistingSituation_area1, InsertTheDescriptionOfTheExistingSituation1);
//
//    				requestDescription = getTextFromTextAreaAndCheckIfInsert(DescriptionOfChange_area1, // get the description
//    																									// of the request
//    						InsertTheDescriptionOfChange1);
//
//    				reasons = getTextFromTextAreaAndCheckIfInsert(ReasonOfTheRequest_area1, InsertTheReasonOfTheRequest1);// get
//    																														// the
//    																														// reasons
//    																														// of
//    																														// the
//    																														// request
//
//    				if (flag == true) {
//    					notes = Notes_area1.getText();
//    					Request request = new Request(count, this.userLoged.getFirstName() + " " + this.userLoged.getLastName(),
//    							this.userLoged.getId(), systemName, descriptionOfTheExistingSituation, requestDescription,
//    							null/* (Date)dateLabe.getText() */, notes, reasons);
//    					GuiManager.client.SubmitNewRequest(request);// call the function that submit the request to DB
//    					if (flagDoc) {
//    						MyFile msg = new MyFile(docLbl.getText(), count);
//    						String LocalfilePath = docLbl.getText();
//
//    						try {
//
//    							File newFile = new File(LocalfilePath);
//
//    							byte[] mybytearray = new byte[(int) newFile.length()];
//    							FileInputStream fis = new FileInputStream(newFile);
//    							BufferedInputStream bis = new BufferedInputStream(fis);
//
//    							msg.initArray(mybytearray.length);
//    							msg.setSize(mybytearray.length);
//
//    							bis.read(msg.getMybytearray(), 0, mybytearray.length);
//    						} catch (Exception e) {
//
//    							System.out.println("Error send (Files)msg) to ClientController");
//    						}
//    						while (flagDoc != true) {
//    							try {
//    								Thread.sleep(500);
//    							} catch (Exception e) {
//    								e.printStackTrace();
//    							}
//
//    						}
//    						if (flagDoc)
//    							GuiManager.client.SubmitDocuments(msg);// func that insert doc to DB
//    					}
//
//    				}
//
//    			} catch (Exception e) {
//
//    			}
//    		
/**
 * Gets the text from text area and check if insert.
 *
 * @param textArea the text area
 * @param l the l
 * @return the text from text area and check if insert
 */
//    }
	public String getTextFromTextAreaAndCheckIfInsert(TextArea textArea, Label l) {
		if ((textArea.getText() == null || textArea.getText().isEmpty())) {
			l.setVisible(true);
			flag = false;
		} else {
			flag = true;
			l.setVisible(false);
		}

		return textArea.getText();

	}

}
