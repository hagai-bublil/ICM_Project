package gui;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import entities.DBMessage;
import entities.DBMessage.DBAction;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import logic.ClientController;
import logic.IClient;
import javafx.scene.control.Button;

/**
 * A static class, handling all the GUI's controllers. This is the main Controller between the
 * communication with the server (aka "ClientController") and the GUI (all of
 * the FXML controllers).
 */
public class GuiManager 
{
	/**
	 * our singleton of the client instance.
	 *
	 */
	public static ClientController client;
	/**
	 * Will be updated in order to now what screen is shown for the user.
	 */
	public static IClient CurrentGuiController;
	public static boolean dbConnected = false;
	public static int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	private static String serverIp;
	private static Stage primaryStageConst;
	public static enum SCREENS
	{

		HomePage,login,CreateRequests,MyRequests,Supervisor,RequestViewForSupervisor,Notifications,Information,DirectorHomePage, ExternHomePage,Assessor, Executor,ReviewAssessment,
		ActivityReport,InformationDirector,myRequestDirector,DelaysExecution,Datas,employeesDatas,requestsDatas, Examiner,NewRequestDirector;

	}
	
	/**
	 * Map from string to a type of user screen 
	 *So we know what homepage to open
	 */
	public static Map<String, SCREENS> userTypeFromString = new HashMap<String, SCREENS>()
	{
		{
			put("itemployee", SCREENS.HomePage);
			put("director", SCREENS.DirectorHomePage);
			put("generalUser", SCREENS.ExternHomePage);
			put("supervisor", SCREENS.HomePage);
			put("chairman", SCREENS.HomePage);
			put("commissioner1", SCREENS.HomePage);
			put("commissioner2", SCREENS.HomePage);
		}
	};
	
	/**
	 * Map between user screen type to FXML path. this not all the paths available -
	 * only the necessary here. You can add as much as you want.
	 */
	public static Map<SCREENS, String> availableFXML = new HashMap<SCREENS, String>()
	{
		{
			put(SCREENS.login, "LogInScreen.fxml");
			put(SCREENS.HomePage, "/gui/HomePageScreen.fxml");
			put(SCREENS.CreateRequests, "/gui/SubmitNewRequest.fxml");
			put(SCREENS.MyRequests, "/gui/MyRequest.fxml");
			put(SCREENS.Supervisor, "/gui/SupervisorScreen.fxml");
			put(SCREENS.Notifications, "/gui/NotificationScreen.fxml");
			put(SCREENS.Information, "/gui/UserInformationScreen.fxml");
			put(SCREENS.DirectorHomePage, "/gui/DirectorHomePageScreen.fxml");
			put(SCREENS.ExternHomePage, "/gui/ExternHomePageScreen.fxml");
			put(SCREENS.Assessor, "/gui/AssessorScreen.fxml");
			put(SCREENS.Executor, "/gui/ExecutorScreen.fxml");
			put(SCREENS.ReviewAssessment, "/gui/ReviewAssessmentScreen.fxml");
			put(SCREENS.Datas, "/gui/DataScreen.fxml");
			put(SCREENS.employeesDatas, "/gui/EmployeesDataScreen.fxml");
			put(SCREENS.requestsDatas, "/gui/RequestsDataScreen.fxml");
			put(SCREENS.ActivityReport, "/gui/ActivityReportScreen.fxml");
			put(SCREENS.InformationDirector, "/gui/UserInformationDirector.fxml");
			put(SCREENS.myRequestDirector, "/gui/MyRequestDirectorScreen.fxml");
			put(SCREENS.Examiner, "/gui/ExaminerScreen.fxml");
			put(SCREENS.NewRequestDirector, "/gui/SubmitNewRequestDirectorScreen.fxml");

		}
	};
	
	/**
	 * create the client singleton type. initial server communication
	 * 
	 * @param fxmlPath
	 * @param primaryStage
	 */
	public  void InitialPrimeryStage( Stage primaryStage)
	{
		getServerIp();
		try
		{
			client = ClientController.createClientIfNotExist(serverIp, ClientController.DEFAULT_PORT);// get connection
			client.sendToServer(new DBMessage(DBAction.isDBRuning, null));// check DB
		} catch (Exception e)
		{
			client = null;
		} finally
		{
			try
			{
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("LogInScreen.fxml"));
				System.out.println(loader.getLocation());
				Pane root = loader.load();
				CurrentGuiController = loader.getController();
				Scene Scene = new Scene(root);
				primaryStage.setScene(Scene);
				primaryStage.setOnCloseRequest(e -> shutDown());// make sure safe shutdown
				primaryStage.setTitle("ICM");
				primaryStage.show();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Pop up a error message
	 * 
	 * @param msg - what to show in the error message
	 */
	public static void ShowErrorPopup(String msg)
	{
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Unexpected Error");
			alert.setHeaderText("");
			alert.setContentText(msg);
			alert.showAndWait();
		});
	}
	
	/**
	 * Pop up a information message
	 * 
	 * @param msg - what to show in the message
	 */
	public static void ShowMessagePopup(String msg)
	{
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText("");
			alert.setContentText(msg);
			alert.showAndWait();
		});
	}
	
	/**
	 * Wherever you need to switch scene (e.g. from login to user screen) this
	 * function initial all the needed data and update the "CurrentGuiController"
	 * 
	 * @param screenEnum - the screen you want to switch to from the enum list.
	 */
	public static void SwitchScene(SCREENS screenEnum, Stage SeondStage)
	{
		try
		{
			if (screenEnum == SCREENS.login)
				client.updateUserLogOut(CurrentGuiController.getUserLogedIn());
		//	Stage SeondStage = new Stage();
			FXMLLoader loader = new FXMLLoader(GuiManager.class.getResource(availableFXML.get(screenEnum)));
			Parent root = loader.load();
			CurrentGuiController = loader.getController();
			Scene scene = new Scene(root);
			SeondStage.setOnCloseRequest(e -> shutDown());// make sure safe shutdown
			SeondStage.setScene(scene);
			SeondStage.show();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * The initial window for the connection to server
	 */
	private static void getServerIp()
	{
		final Stage dialog = new Stage();
		dialog.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			@Override
			public void handle(WindowEvent arg0)
			{
				System.exit(0);
			}
		});
		dialog.initStyle(StageStyle.DECORATED);
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.setTitle("Connect to Server");
		VBox dialogVbox = new VBox(10);
		Label headline = new Label("Enter Server IP");
		headline.setFont(new Font(25));
		Label warning = new Label("(Make sure you are on the same lan)");
		warning.setFont(new Font(12));
		dialogVbox.setAlignment(Pos.CENTER);
		javafx.scene.control.TextField ipTextField = new javafx.scene.control.TextField("Example: 162.123.1.206");
		ipTextField.setMaxWidth(170);
		Button button = new Button("OK");
		button.setOnMouseClicked(new EventHandler<Event>()
		{
			@Override
			public void handle(Event e)
			{
				if (ipTextField.getText().isEmpty() || ipTextField.getText().contains("Example: 162.123.1.206"))
					return;
				serverIp = ipTextField.getText();
				dialog.close();
			}
		});
		dialogVbox.getChildren().addAll(headline, warning, ipTextField, button);
		Scene dialogScene = new Scene(dialogVbox, 300, 200);
		dialog.setScene(dialogScene);
		dialog.showAndWait();
	}
	/**
	 * A "safe shutdown" function doing logout and closing the connection to the
	 * server.
	 */
	public static void shutDown()
	{
		if (client == null)
			return;
		try
		{
			if (CurrentGuiController instanceof LogInController
					|| CurrentGuiController instanceof HomePageController)
			{
				client.updateUserLogOut(CurrentGuiController.getUserLogedIn());
			}
			client.closeConnection();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

}