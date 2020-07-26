package gui;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.IClient;
import java.net.URL;
import java.util.ResourceBundle;
import entities.DBMessage;
import entities.DBMessage.DBAction;
import entities.User;

/**
 * This class is the controller of: LogInScreen and it contains all the methods that manage the screen
 *
 */
public class LogInController implements IClient, Initializable
{
	
	/** The this node. */
	private Node thisNode;
	
	/** The log in pane. */
	@FXML
	private Pane logInPane;
    
    /** The user name textfield. */
    @FXML
    private TextField userName_textfield;

    /** The password textfield. */
    @FXML
    private PasswordField password_textfield;

    /** The log in button. */
    @FXML
    private Button logIn_button;

    /** The warning label. */
    @FXML
    private Label warningLabel;

    /** The user loged. */
    private User userLoged;
    
	/**
	 * Initialize.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		warningLabel.setText("");
	}
    
    /**
     * Log in pressed.
     *
     * @param event the event
     */
    @FXML
    void logInPressed(ActionEvent event) // press on login button
    {
    	if (GuiManager.client == null)
		{
			warningLabel.setText("Error connecting to server");
			logIn_button.setDisable(true);
			userName_textfield.setDisable(true);
			password_textfield.setDisable(true);
		} 
    	else if (GuiManager.dbConnected==false)
    	{
			warningLabel.setText("Error connecting to database\nPlease check server.");
			userName_textfield.setDisable(true);
			userName_textfield.setDisable(true);
			password_textfield.setDisable(true);
    	}
    	else
    	{
	    	warningLabel.setText("");
	    	thisNode = ((Node) event.getSource());
	    	GuiManager.client.CheckValidUser(new User(userName_textfield.getText(), password_textfield.getText()));
    	}
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
		try
		{
			if (msg.Action == DBAction.CheckUser)
			{
				if (msg.Data == null)
				{
					Platform.runLater(() -> {
						warningLabel.setText("Wrong user name or password.");
					});
				} 
				else if (msg.Data instanceof User)
				{
					if (((User) msg.Data).getUserName() == null)// if the user already connected
					{
						Platform.runLater(() -> {
							warningLabel.setText("User already connected");
						});

					} else
					{
						
						// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
						Platform.runLater(() -> {
							thisNode.getScene().getWindow().hide();
							GuiManager.CurrentGuiController.setUserLogedIn(((User) msg.Data));
							GuiManager.SwitchScene(GuiManager.userTypeFromString.get(((User) msg.Data).getType()), (Stage)thisNode.getScene().getWindow());
							GuiManager.CurrentGuiController.setUserLogedIn(((User) msg.Data));
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


}
