package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import entities.DBMessage;
import entities.User;
import entities.DBMessage.DBAction;
import entities.ICMMessage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.IClient;
import observableEntities.ObservableMessage;
/**
 * This class is the controller of: NotificationScreen and it contains all the methods that manage the screen
 *
 */
public class NotificationController extends HomePageController implements IClient, Initializable
{

  /*  @FXML
    private Pane logout_pane;*/

    /** The home page. */
  @FXML
    private Pane homePage;

    /** The vbox 2. */
    @FXML
    private VBox vbox2;

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
 
    /** The user loged. */
    private User userLoged;
    
    /** The user name label. */
    @FXML
    private Label userNameLabel;

    /** The date label. */
    @FXML
    private Label dateLabel;
    
    /** The observable msg list. */
    protected ObservableList<ObservableMessage> observableMsgList;// for table view
    
    /** The review assessment btn. */
    @FXML
    private Button reviewAssessment_btn;

    /** The submmit bnt. */
    @FXML
    private Button submmit_bnt;

    /** The my request bnt. */
    @FXML
    private Button myRequest_bnt;

    /** The log out display. */
    @FXML
    private Button logOutDisplay;
  
    /** The logout pane. */
    @FXML
    private Pane logout_pane;

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
     * Pressed new request.
     *
     * @param event the event
     */
    @FXML
    void Pressed_newRequest(ActionEvent event) {
    	super.Pressed_newRequest(event);
    }
	
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
	public void getMessageFromServer(DBMessage msg) {

		try
		{
			if (msg.Action == DBAction.getNotificationsForUser)
			{
						// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
						Platform.runLater(() -> {
							getUserLogedIn().setMessages(((User)msg.Data).getMessages());
							setMessages((User)msg.Data);
						});
			}
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Initialize.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());
		if(!getUserLogedIn().getType().equals("supervisor"))
			supervisor_btn.setDisable(true);
		else 
			supervisor_btn.setDisable(false);
			
		GuiManager.client.getNotificationsForUser(getUserLogedIn());
		dateSentTableColumn.setCellValueFactory(new PropertyValueFactory<>("dateSent"));
		notificationContentTableColumn.setCellValueFactory(new PropertyValueFactory<>("msgContent"));
		eraseTableColumn.setCellValueFactory(new PropertyValueFactory<>("eraseButton"));
		observableMsgList = FXCollections.observableArrayList();		
	}
	
	/**
	 * Sets the messages.
	 *
	 * @param user the new messages
	 */
	protected void setMessages(User user)
	{	
		for (ICMMessage msg : getUserLogedIn().getMessages())
		{System.out.println(msg.getMessageContent()+" "+msg.getRecipientUserId());
			@SuppressWarnings("deprecation")
			ObservableMessage tempMsg = new ObservableMessage(msg.getId(), msg.getDateSending().toLocaleString().substring(10), msg.getMessageContent());
			tempMsg.getEraseButton().setOnAction(e -> {
				ObservableMessage selectedItem = notificationsTableView.getSelectionModel().getSelectedItem();
				notificationsTableView.getItems().remove(selectedItem);
				remove(selectedItem);
			});		
			observableMsgList.add(tempMsg);
		}
		notificationsTableView.setItems(observableMsgList);
		Platform.runLater(new Runnable()
		{			
			@Override
			public void run()
			{
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
	private void remove(ObservableMessage selectedItem) 
	{
		getUserLogedIn().getMessages().remove(selectedItem); /*Delete the message from the user's message list*/
		GuiManager.client.removeNotification(selectedItem.getIndex());
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
    void myAccountPressed(ActionEvent event) //?????
    {
    	
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
     * My account pressed.
     *
     * @param event the event
     */
    @FXML
    void myAccount_pressed(ActionEvent event) {
    	super.myAccount_pressed(event);
    }

}
