package gui;

import java.io.IOException;
import application.AutomaticExecutors;
import application.ICMServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * This class is the GUI controller of the server screen.
 *
 */
public class ConnectToSereverForm_controller
{
	
	/** The server. */
	private ICMServer server;
	
    /** The server port text field. */
    @FXML
    private TextField serverPortTextField;
    
    /** The server log text area. */
    @FXML
    public TextArea server_log_textArea;
    
    /** The connect button. */
    @FXML
    private Button connectButton;
    
    /** The stop server button. */
    @FXML
    private Button stopServerButton;
    
    /** The db name text field. */
    @FXML
    private TextField dbNameTextField;

    /** The db user name text field. */
    @FXML
    private TextField dbUserNameTextField;

    /** The db pass text field. */
    @FXML
    private PasswordField dbPassTextField;
    
    /** The executer. */
    private AutomaticExecutors executer = null;
 
    /**
     * Connect pressed.
     *
     * @param event the event
     */
    @FXML
    void connectPressed(ActionEvent event) 
    {
    	server = new ICMServer(Integer.parseInt(serverPortTextField.getText()), server_log_textArea);
	    try 
	    {
	    	server.listen(); //Start listening for connections
	    	server_log_textArea.setText("Server running on port " + serverPortTextField.getText() + System.lineSeparator()+ server_log_textArea.getText());
	    } 
	    catch (Exception ex) 
	    {
	    	server_log_textArea.setText("ERROR - Could not listen for clients!");
	    	System.out.println("ERROR - Could not listen for clients!");
	    }
	    try {
			server.connectToDB(dbNameTextField.getText(), dbPassTextField.getText(), dbUserNameTextField.getText());
			executer = new AutomaticExecutors(server.getConnection());
		} catch (Exception ex) 
	    {
			server_log_textArea.setText(ex.getMessage() + System.lineSeparator() + server_log_textArea.getText());
		}
		finally
		{
			if (server.isDBRunning())
				server_log_textArea.setText("Database connected successfully!" + System.lineSeparator() + server_log_textArea.getText());
		}

    }
    
    /**
     * Stop pressed.
     *
     * @param event the event
     */
    @FXML
    void stopPressed(ActionEvent event)
    {
		server.stopListening();
		try
		{
			server.close();
		} catch (Exception e)
		{
			server_log_textArea.setText(e.getMessage() + System.lineSeparator() + server_log_textArea.getText());
		}
		server_log_textArea.setText("Server Stopped" + System.lineSeparator() + server_log_textArea.getText());
		server_log_textArea.setText("All clients disconnected" + System.lineSeparator() + server_log_textArea.getText());
		shutDown();
		return;
    }
    
    /**
     * Shut down.
     */
    public void shutDown()
    {
    	if (server != null)
		{
			if (server.isListening())
				server.stopListening();
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	if (executer != null)
			executer.shutDown();
    }
}
