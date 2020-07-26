package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import entities.DBMessage;
import entities.Request;
import entities.User;
import entities.UserList;
import entities.DBMessage.DBAction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.IClient;
import observableEntities.ObservableUsers;

/**
 * This class is the controller of: EmployeesDataScreen and it contains all the methods that manage the screen
 *
 */
public class EmployeesDataController extends DirectorHomePageController implements IClient, Initializable{
    
    /** The anchor pane. */
    @FXML
    private AnchorPane anchorPane;

    /** The home page. */
    @FXML
    private Pane homePage;

    /** The vbox 1. */
    @FXML
    private VBox vbox1;

    /** The Employees data screen. */
    @FXML
    private Pane EmployeesDataScreen;

    /** The Activity report screen. */
    @FXML
    private Label ActivityReportScreen;

    /** The Employeestable. */
    @FXML
    private TableView<ObservableUsers> Employeestable;

    /** The Id column. */
    @FXML
    private TableColumn<ObservableUsers,Integer> Id_column;

    /** The User name column. */
    @FXML
    private TableColumn<ObservableUsers,String> UserName_column;

    /** The Mail column. */
    @FXML
    private TableColumn<ObservableUsers,String> Mail_column;

    /** The User type culomn. */
    @FXML
    private TableColumn<ObservableUsers,String> UserType_culomn;

    /** The Department column. */
    @FXML
    private TableColumn<ObservableUsers,String> Department_column;

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
    
    /** The home bnt. */
    @FXML
    private Button home_bnt;
    
    /** The this node. */
    private Node thisNode;
    
    /** The user loged. */
    private User userLoged;
    
    /** The list of employees. */
    private  UserList listOfEmployees;
    
	/** The Observable all employees. */
	private ObservableList<ObservableUsers> ObservableAllEmployees;

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
		Id_column.setVisible(true);
		UserName_column.setVisible(true);
		Mail_column.setVisible(true);
		UserType_culomn.setVisible(true);
		Department_column.setVisible(true);
		GuiManager.client.getEmployeesList(userLoged);
		Id_column.setCellValueFactory(new PropertyValueFactory<>("IdNumber"));
		UserName_column.setCellValueFactory(new PropertyValueFactory<>("UserName"));
		Mail_column.setCellValueFactory(new PropertyValueFactory<>("Mail"));
		UserType_culomn.setCellValueFactory(new PropertyValueFactory<>("UserType"));
		Department_column.setCellValueFactory(new PropertyValueFactory<>("DepartmentName"));
		ObservableAllEmployees = FXCollections.observableArrayList();  
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
		//System.out.println(this.userLoged.toString());
		String userName = userLoged.getUserName();
		userNameLabel.setText(userName);
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		String string = format.format(calendar.getTime());
		dateLabel.setText(string);
		
	}

	/**
	 * Sets the employees table.
	 *
	 * @param listOfEmployees the new employees table
	 */
	protected void setEmployeesTable(UserList listOfEmployees){
		for(User u: listOfEmployees.user_list) {
			//System.out.println(u.getFirstName());
			ObservableUsers tempRequest = new ObservableUsers(u.getIdNumber(),u.getUserName(),u.getMail(),u.getUserType(),u.getDepartmentName());
			ObservableAllEmployees.add(tempRequest);
		}
		Employeestable.setItems(ObservableAllEmployees);
		Platform.runLater(new Runnable()
		{	
		@Override
		public void run()
		{
			Employeestable.getSortOrder().add(Id_column);				
		}
		});	
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
			if (msg.Action == DBAction.getAllEmployees)
			{
						// Avoid throwing IllegalStateException by running from a non-JavaFX thread.
						Platform.runLater(() -> {
						UserList listOfEmployees=new UserList();
						listOfEmployees=(UserList)msg.Data;
							setEmployeesTable(listOfEmployees);
							
						});
			}
		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
