package application;

import java.io.IOException;

import gui.ConnectToSereverForm_controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * This class holds the main function to start the ICM Server application.
 */
public class MainServer extends Application 
{
	
	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 */
	@Override
	public void start(Stage primaryStage) 
	{
		ConnectToSereverForm_controller guiController;
		Pane pane;
		try 
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/serverGui.fxml"));
			pane = loader.load();
			guiController= loader.getController();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return;
		}	
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Connect to server");
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> guiController.shutDown());//make sure safe shutdown
		primaryStage.show();
	}
	
	/**
	 * This method is responsible for the creation of the server instance.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		launch(args);
	}
}
