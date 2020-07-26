package gui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import MyFile.MyFile;
import entities.DBMessage;
import entities.ICMMessage;
import entities.Request;
import entities.User;
import gui.GuiManager.SCREENS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import logic.IClient;

/**
 * This class is the controller of:SubmitNewRequestDirectorScreen and it contains all the methods that manage the screen
 *
 */
public class SubmitNewRequestDirectorController extends DirectorHomePageController implements IClient, Initializable{
	
	/** The vbox 11. */
	@FXML
	private VBox vbox11;


	/** The Add documents. */
	@FXML
	private Button AddDocuments;

	/** The examiner btn 1. */
	@FXML
	private Button examiner_btn1;

	/** The logout pane 1. */
	@FXML
	private Pane logout_pane1;

	/** The page pane. */
	@FXML
	private Pane pagePane;

	/** The user name label. */
	@FXML
	private Label userNameLabel;

	/** The doc lbl. */
	@FXML
	private Label docLbl;

	/** The log out bnt. */
	@FXML
	private Button logOutBnt;

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

	/** The Description of change 1. */
	@FXML
	private Label DescriptionOfChange1;

	/** The Description of change area 1. */
	@FXML
	private TextArea DescriptionOfChange_area1;

	/** The Insert the description of change 1. */
	@FXML
	private Label InsertTheDescriptionOfChange1;
	
	/** The Your request ID. */
	@FXML
	private Label YourRequestID;

	/** The Reason of the request 1. */
	@FXML
	private Label ReasonOfTheRequest1;

	/** The Reason of the request area 1. */
	@FXML
	private TextArea ReasonOfTheRequest_area1;

	/** The Insert the reason of the request 1. */
	@FXML
	private Label InsertTheReasonOfTheRequest1;

	/** The Notes 1. */
	@FXML
	private Label Notes1;
	
	/** The msg doc add. */
	@FXML
	private Label msg_doc_add;

	/** The Notes area 1. */
	@FXML
	private TextArea Notes_area1;

	/** The date label. */
	@FXML
	private Label dateLabel;

	/** The Submit button 1. */
	@FXML
	private Button Submit_button1;

	/** The Yourrequestsubmited 1. */
	@FXML
	private Label Yourrequestsubmited1;

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
	
	/** The user loged. */
	private User userLoged;
	
	/** The this node. */
	private Node thisNode;
	
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
	
	/** The request give. */
	private Request requestGive;

	/** The msg. */
	private MyFile msg;

	/** The list files. */
	private List<File> listFiles = new ArrayList<File>();

	/** The Constant DEFAULT_PORT. */
	final public static int DEFAULT_PORT = 5555; // The default port to connect on.

	/** The list. */
	private ObservableList<String> list = FXCollections.observableArrayList("moodle", "informationStation", "library",
			"classroom","lab","webSite");


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
	 * Submit pressed.
	 *
	 * @param event the event
	 */
	@FXML
	void SubmitPressed(ActionEvent event) {// create the request and send to function that insert it to DB
		GuiManager.client.getMaxNumOfRequest();// func that find the max requestId
		GuiManager.client.getMaxNumOfMsg();// func that find the max msgID
		while (msgFlag != true) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
																													// reasons
																													// of
																													// the
																													// request

			if (flag == true) {
				notes = Notes_area1.getText();
				Request request = new Request(count, this.userLoged.getFirstName() + " " + this.userLoged.getLastName(),
						this.userLoged.getId(), systemName, descriptionOfTheExistingSituation, requestDescription,
						null/* (Date)dateLabe.getText() */, notes, reasons, requestGive.getSupervisor(), requestGive.getChairman(), requestGive.getCommissioner1(), requestGive.getCommissioner2());
				
				System.out.println(request.getRequestNumber() + "', '" + request.getApplicantName() + "', '" + request.getApplicantId());
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

	/**
	 * Pressed add documents.
	 *
	 * @param event the event
	 */
	@FXML
	void PressedAddDocuments(ActionEvent event) {

		flagDoc = true;
		// msg_doc_add.setVisible(false);

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
	 * Gets the text from text area and check if insert.
	 *
	 * @param textArea the text area
	 * @param l the l
	 * @return the text from text area and check if insert
	 */
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
	 * Gets the message from server.
	 *
	 * @param msg the msg
	 * @return the message from server
	 */
	@Override
	public void getMessageFromServer(DBMessage msg) {
		switch (msg.Action) {
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

		case Files: {

			break;
		}
		case NewRequestMsgSpvsr: {
			break;
		}
		case getNamesOfEmployees: {
			requestGive= (Request)msg.Data;
			break;
		}

		}

	}

	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//	  TODO Auto-generated method stub
		setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());
		cmbListSystemName1.setItems(list);// inital the combo box
		GuiManager.client.getNamesOfEmployees();

	}


}
