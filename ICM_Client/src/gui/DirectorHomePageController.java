package gui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import entities.DBMessage;
import entities.User;
import entities.UserList;
import gui.GuiManager.SCREENS;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.IClient;

/**
 * This class is the controller of: DirectorHomePageScreen and it contains all the methods that manage the screen
 *
 */
public class DirectorHomePageController implements Initializable,IClient {

	    /** The maintenance appointment btn. */
    	@FXML
	    private Button maintenanceAppointment_btn;

	    /** The Data screen. */
    	@FXML
	    private Pane DataScreen;

	    /** The Activity report screen. */
    	@FXML
	    private Label ActivityReportScreen;

	    /** The datas screen. */
    	@FXML
	    private VBox datas_screen;

	    /** The Employees btn. */
    	@FXML
	    private Button Employees_btn;

	    /** The Requests btn. */
    	@FXML
	    private Button Requests_btn;
	
    /** The anchor pane. */
    @FXML
    private AnchorPane anchorPane;

    /** The home page. */
    @FXML
    private Pane homePage;

    /** The Home page V box. */
    @FXML
    private VBox HomePageVBox;

    /** The vbox 1. */
    @FXML
    private VBox vbox1;

    /** The appointment btn. */
    @FXML
    private Button appointment_btn;

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

    /** The appoitment pane. */
    @FXML
    private Pane appoitmentPane;

    /** The appointment employees. */
    @FXML
    private VBox appointmentEmployees;

    /** The supervisor combo box. */
    @FXML
    private ComboBox<String> supervisorComboBox;

    /** The chairman combo box. */
    @FXML
    private ComboBox<String> chairmanComboBox;

    /** The firstmember combo box. */
    @FXML
    private ComboBox<String> firstmemberComboBox;

    /** The second member combo box. */
    @FXML
    private ComboBox<String> secondMemberComboBox;

    /** The done appoint bnt. */
    @FXML
    private Button doneAppointBnt;

    /** The appointmentlable. */
    @FXML
    private Label appointmentlable;

    /** The replace employees vbox. */
    @FXML
    private VBox replaceEmployeesVbox;

    /** The selected supervisor. */
    @FXML
    private Label theSelectedSupervisor;

    /** The selected chairman. */
    @FXML
    private Label theSelectedChairman;

    /** The selected first M. */
    @FXML
    private Label theSelectedFirstM;

    /** The selected second M. */
    @FXML
    private Label theSelectedSecondM;

    /** The supervisor combo box replace. */
    @FXML
    private ComboBox<String> supervisorComboBoxReplace;

    /** The chairman combo box replace. */
    @FXML
    private ComboBox<String> chairmanComboBoxReplace;

    /** The firstmember combo box replace. */
    @FXML
    private ComboBox<String> firstmemberComboBoxReplace;

    /** The second member combo box replace. */
    @FXML
    private ComboBox<String> secondMemberComboBoxReplace;

    /** The done replace bnt. */
    @FXML
    private Button doneReplaceBnt;

    /** The replacementlable. */
    @FXML
    private Label replacementlable;
  

    /** The supervisor ok bnt. */
    @FXML
    private Button supervisorOkBnt;

    /** The chairman ok bnt. */
    @FXML
    private Button chairmanOkBnt;

    /** The membe 1 r ok bnt. */
    @FXML
    private Button membe1rOkBnt;

    /** The membe 2 r ok bnt. */
    @FXML
    private Button membe2rOkBnt;

    /** The supervisor ok bnt 1. */
    @FXML
    private Button supervisorOkBnt1;

    /** The chairman ok bnt 1. */
    @FXML
    private Button chairmanOkBnt1;

    /** The membe 1 r ok bnt 1. */
    @FXML
    private Button membe1rOkBnt1;

    /** The membe 2 r ok bnt 1. */
    @FXML
    private Button membe2rOkBnt1;

    /** The maintenance appointment pane. */
    @FXML
    private Pane maintenanceAppointmentPane;

    /** The maintenance maintenance vbox. */
    @FXML
    private VBox maintenanceMaintenanceVbox;

    /** The system combo box. */
    @FXML
    private ComboBox<String> systemComboBox;

    /** The employee combo box. */
    @FXML
    private ComboBox<String> employeeComboBox;

    /** The system ok bnt. */
    @FXML
    private Button systemOkBnt;

    /** The employee ok bnt. */
    @FXML
    private Button employeeOkBnt;

    /** The done appoint maintenance bnt. */
    @FXML
    private Button doneAppointMaintenanceBnt;

    /** The maintenance lable. */
    @FXML
    private Label maintenanceLable;
    
    /** The Statistical screen. */
    @FXML
    private Pane StatisticalScreen;

    /** The Activity btn. */
    @FXML
    private Button Activity_btn;

    /** The Performence btn. */
    @FXML
    private Button Performence_btn;

    /** The Delays btn. */
    @FXML
    private Button Delays_btn;
        
        /** The home bnt. */
        @FXML
	    private Button home_bnt;
 
    
/** The secondmember. */
//////////////////////////////////////////////private class variables  //////////////////////////////////////////////////////////////////////////  
    private  User userLoged,supervisor,chairman,firstmember,secondmember;
    
    /** The appointment. */
    boolean appointment;
    
    /** The list of chosen employees. */
    private  UserList listOfEmployees,listOfChosenEmployees;
	
	/** The systemlist. */
	private ObservableList<String> systemlist = FXCollections.observableArrayList("moodle", "informationStation", "library",
			"classroom","lab","webSite");
  	
	  /** The this node. */
	  private Node thisNode;
  	
	  /** The flag. */
	  protected int flag;
	
	  /**
  	 * Done appoint maintenance pressed.
  	 *
  	 * @param event the event
  	 */
  	@FXML
	    void doneAppointMaintenancePressed(ActionEvent event) {
	    	Platform.runLater(() -> {
	    		String sysname=systemComboBox.getSelectionModel().getSelectedItem();
	        	String fullName= employeeComboBox.getSelectionModel().getSelectedItem();
	        	System.out.println(fullName+" "+sysname);
	        	StringTokenizer defaultTokenizer = new StringTokenizer(fullName);
	        	String first=defaultTokenizer.nextToken();
	        	String last=defaultTokenizer.nextToken();
	        	for(User u: listOfEmployees.user_list)
	        	{
	        		if(u.getFirstName().equals(first)&& u.getLastName().equals(last)) {
	        			u.setRole(sysname);
	        			GuiManager.client.updateUserSystemMaintenance(u);	
	        		}	
	        	}
	        	});
	    }
	    
	    
	    /**
    	 * Employee combo box pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void employeeComboBoxPressed(ActionEvent event) {
	    }

	    /**
    	 * System combo boxpressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void systemComboBoxpressed(ActionEvent event) {
	    	maintenanceLable.setVisible(false);
	    }
	    

	    /**
    	 * Maintenance appointment pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void maintenanceAppointmentPressed(ActionEvent event) {
	    	Platform.runLater(() -> {
	    		DataScreen.setVisible(false);
	    		HomePageVBox.setVisible(false);
	    		appoitmentPane.setVisible(false);
	    		maintenanceAppointmentPane.setVisible(true);
	    		systemComboBox.setItems(systemlist);
	    		GuiManager.client.getemployeesList(this.userLoged);
	    	});
	    }

	

	    /**
    	 * Pressed statistical.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void PressedStatistical(ActionEvent event) {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
			thisNode = ((Node) event.getSource());
	    	//thisNode.getScene().getWindow().hide();
			GuiManager.SwitchScene(GuiManager.SCREENS.ActivityReport, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);

	    }
	    
	/**
	 * Appointment.
	 *
	 * @param event the event
	 */
	/*if the appointment was made the replacement page will open
	  - the initial db must be without supervisor and committe so the appointment page will show*/
	    @FXML
	    void appointment(ActionEvent event) {
	    	Platform.runLater(() -> {
	    	maintenanceAppointmentPane.setVisible(false);
	    	HomePageVBox.setVisible(false);
	    	appoitmentPane.setVisible(true);
	    	appointmentlable.setVisible(false);
			replacementlable.setVisible(false);
			DataScreen.setVisible(false);
	    	if (!this.appointment) {
	    		replaceEmployeesVbox.setVisible(false);
	    		appointmentEmployees.setVisible(true);
	    		chairmanOkBnt.setDisable(true);
	    		membe1rOkBnt.setDisable(true);
	    		membe2rOkBnt.setDisable(true);
	    		
	    	}
	    	else {
	    		appointmentEmployees.setVisible(false);
	    		replaceEmployeesVbox.setVisible(true);
	    		theSelectedSupervisor.setText("The supervisor is-"+this.supervisor.getFirstName()+" "+this.supervisor.getLastName());
	    		theSelectedChairman.setText("The chairman is-"+this.chairman.getFirstName()+" "+this.chairman.getLastName());
	    		theSelectedFirstM.setText("The first member is-"+this.firstmember.getFirstName()+" "+this.firstmember.getLastName());
	    		theSelectedSecondM.setText("The first member is-"+this.secondmember.getFirstName()+" "+this.secondmember.getLastName());
	    		theSelectedSupervisor.setVisible(true);
	    		theSelectedChairman.setVisible(true);
	    		theSelectedFirstM.setVisible(true);
	    		theSelectedSecondM.setVisible(true);
	    	}
	    	GuiManager.client.getemployeesList(this.userLoged);
	    	});
	    }
	    
	    /**
    	 * Supervisor combo boxpressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void supervisorComboBoxpressed(ActionEvent event) {
	    	Platform.runLater(() -> {
	    	String fullName= supervisorComboBox.getSelectionModel().getSelectedItem();
	    //	System.out.println(fullName);
	    	StringTokenizer defaultTokenizer = new StringTokenizer(fullName);
	    	String first=defaultTokenizer.nextToken();
	    	String last=defaultTokenizer.nextToken();
	    	for(User u: listOfEmployees.user_list)
	    	{
	    		if(u.getFirstName().equals(first)&& u.getLastName().equals(last)) {
	    			u.setAvailable(false);
	    			u.setRole("supervisor");
	    			supervisor=u;	
	    		}	
	    	}
	    	chairmanOkBnt.setDisable(false);
	    	chairmanComboBox.setDisable(false);
	    	loadComboBox(listOfEmployees);	
	    	});
	    }

	    /**
    	 * Chairman combo box pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void chairmanComboBoxPressed(ActionEvent event) {
	    	Platform.runLater(() -> {
	    	String fullName= chairmanComboBox.getSelectionModel().getSelectedItem();
	    	//System.out.println(fullName);
	    	StringTokenizer defaultTokenizer = new StringTokenizer(fullName);
	    	String first=defaultTokenizer.nextToken();
	    	String last=defaultTokenizer.nextToken();
	    	for(User u: listOfEmployees.user_list)
	    	{
	    		if(u.getFirstName().equals(first)&& u.getLastName().equals(last)) {
	    			u.setAvailable(false);
	    			u.setRole("chairman");
	    			chairman=u;
	    		}	
	    	}
	    	membe1rOkBnt.setDisable(false);
			 firstmemberComboBox.setDisable(false);
	    	loadComboBox(listOfEmployees);
	    	});
	    }
	    
	

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
    	 * Firstmember combo box pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void firstmemberComboBoxPressed(ActionEvent event) {
	    	Platform.runLater(() -> {
	    	String fullName= firstmemberComboBox.getSelectionModel().getSelectedItem();
	    	//System.out.println(fullName);
	    	StringTokenizer defaultTokenizer = new StringTokenizer(fullName);
	    	String first=defaultTokenizer.nextToken();
	    	String last=defaultTokenizer.nextToken();
	    	for(User u: listOfEmployees.user_list)
	    	{
	    		if(u.getFirstName().equals(first)&& u.getLastName().equals(last)) {
	    			u.setAvailable(false);
	    			u.setRole("commissioner1");
	    			firstmember=u;;
	    				
	    		}	
	    	}
	    	membe2rOkBnt.setDisable(false);
	    	 secondMemberComboBox.setDisable(false);
	    	loadComboBox(listOfEmployees);
	    	});
	    }
	    
	    
	    /**
    	 * Second member combo box pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void secondMemberComboBoxPressed(ActionEvent event) {
	    	Platform.runLater(() -> {
	    	String fullName= secondMemberComboBox.getSelectionModel().getSelectedItem();
	    	//System.out.println(fullName);
	    	StringTokenizer defaultTokenizer = new StringTokenizer(fullName);
	    	String first=defaultTokenizer.nextToken();
	    	String last=defaultTokenizer.nextToken();
	    	for(User u: listOfEmployees.user_list)
	    	{
	    		if(u.getFirstName().equals(first)&& u.getLastName().equals(last)) {
	    			u.setAvailable(false);
	    			u.setRole("commissioner2");
	    			secondmember=u;
	    		}	
	    	}
	    	loadComboBox(listOfEmployees);	
	    	});
	    }
	    
    	/**
    	 * Done appoint pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void doneAppointPressed(ActionEvent event) {
	    	//listOfChosenEmployees.user_list=new ArrayList<User>();
	    	listOfChosenEmployees.user_list.clear();
	    	Platform.runLater(() -> {
	    	listOfChosenEmployees=new UserList(new ArrayList<User>());
	    	listOfChosenEmployees.user_list.add(supervisor);
	    	listOfChosenEmployees.user_list.add(chairman);
	    	listOfChosenEmployees.user_list.add(firstmember);
	    	listOfChosenEmployees.user_list.add(secondmember);
	    	supervisor.setAvailable(true);
	    	chairman.setAvailable(true);
	    	firstmember.setAvailable(true);
	    	secondmember.setAvailable(true);
	    	/*chairmanComboBoxReplace.setDisable(true);
	    	firstmemberComboBoxReplace.setDisable(true);
	    	secondMemberComboBoxReplace.setDisable(true);*/
			loadComboBox(listOfEmployees);
	    	GuiManager.client.updateAppointmentEmployees(listOfChosenEmployees);	
	    	});
	    }
	    
    	/**
    	 * Done replace pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void doneReplacePressed(ActionEvent event) {

	    }
	    
	    /**
    	 * Supervisor combo box replace pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void supervisorComboBoxReplacePressed(ActionEvent event) {
	    	Platform.runLater(() -> {
	        	String fullName= supervisorComboBoxReplace.getSelectionModel().getSelectedItem();
	        	//System.out.println(fullName);
	        	StringTokenizer defaultTokenizer = new StringTokenizer(fullName);
	        	String first=defaultTokenizer.nextToken();
	        	String last=defaultTokenizer.nextToken();
	        	for(User u: listOfEmployees.user_list)
	        	{

	        		if(u.getFirstName().equals(first)&& u.getLastName().equals(last)) {
	        			u.setAvailable(false);
	        			u.setRole("supervisor");
	        			supervisor=u;
	        		}	
	        	}
	        	loadComboBox(listOfEmployees);	
	        	});
	    }

	    
	    /**
    	 * Chairman combo box replace pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void chairmanComboBoxReplacePressed(ActionEvent event) {
	    	Platform.runLater(() -> {
	        	String fullName= chairmanComboBoxReplace.getSelectionModel().getSelectedItem();
	        	System.out.println(fullName);
	        	StringTokenizer defaultTokenizer = new StringTokenizer(fullName);
	        	String first=defaultTokenizer.nextToken();
	        	String last=defaultTokenizer.nextToken();
	        	for(User u: listOfEmployees.user_list)
	        	{
	        		if(u.getFirstName().equals(first)&& u.getLastName().equals(last)) {
	        			u.setAvailable(false);
	        			u.setRole("chairman");
	        			chairman=u;	
	        		}	
	        	}
	        	loadComboBox(listOfEmployees);
	        	});
	    }

	    /**
    	 * Firstmember combo box replace pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void firstmemberComboBoxReplacePressed(ActionEvent event) {
	    	Platform.runLater(() -> {
	        	String fullName= firstmemberComboBoxReplace.getSelectionModel().getSelectedItem();
	        //	System.out.println(fullName);
	        	StringTokenizer defaultTokenizer = new StringTokenizer(fullName);
	        	String first=defaultTokenizer.nextToken();
	        	String last=defaultTokenizer.nextToken();
	        	for(User u: listOfEmployees.user_list)
	        	{
	        		if(u.getFirstName().equals(first)&& u.getLastName().equals(last)) {
	        			u.setAvailable(false);
	        			u.setRole("commissioner1");
	        			firstmember=u;
	        				
	        		}	
	        	}
	        	loadComboBox(listOfEmployees);
	        	});
	    }
	    
	    /**
    	 * Second member combo box replace pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void secondMemberComboBoxReplacePressed(ActionEvent event) {
	    	Platform.runLater(() -> {
	        	String fullName= secondMemberComboBoxReplace.getSelectionModel().getSelectedItem();
	        	StringTokenizer defaultTokenizer = new StringTokenizer(fullName);
	        	String first=defaultTokenizer.nextToken();
	        	String last=defaultTokenizer.nextToken();
	        	for(User u: listOfEmployees.user_list)
	        	{
	        		if(u.getFirstName().equals(first)&& u.getLastName().equals(last)) {
	        			u.setAvailable(false);
	        			u.setRole("commissioner2");
	        			secondmember=u;	
	        		}	
	        	}
	        	});
	    }


	    /**
    	 * My account pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void myAccount_pressed(ActionEvent event) {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
	    	thisNode = ((Node) event.getSource());
			GuiManager.SwitchScene(GuiManager.SCREENS.InformationDirector, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
	    }

	    /**
    	 * My request pressed.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void myRequestPressed(ActionEvent event) {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
			thisNode = ((Node) event.getSource());
			GuiManager.SwitchScene(GuiManager.SCREENS.myRequestDirector, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
	    }


		/**
		 * Initialize.
		 *
		 * @param arg0 the arg 0
		 * @param arg1 the arg 1
		 */
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			setUserLogedIn(GuiManager.CurrentGuiController.getUserLogedIn());
			DataScreen.setVisible(false);
			replaceEmployeesVbox.setVisible(false);
			appointmentEmployees.setVisible(false);
			appoitmentPane.setVisible(false);
			StatisticalScreen.setVisible(false);
			HomePageVBox.setVisible(true);
			maintenanceAppointmentPane.setVisible(false);
			DataScreen.setVisible(false);
			GuiManager.client.checkIfAppoitmentWasMade();
			
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
				switch (msg.Action)
				{
				case checkIfAppoitmentWasMade:
					Platform.runLater(() -> {
						listOfChosenEmployees=(UserList)msg.Data;
					if(listOfChosenEmployees.user_list.isEmpty())
						appointment=false;
					else {
						appointment=true;
					 for(User user:listOfChosenEmployees.user_list) {
						 switch (user.getType())
						 {
						 case "supervisor":
							 supervisor=user;
							 supervisor.setRole("supervisor");
							 break; 
						 case "chairman":
							 chairman=user;
							 chairman.setRole("chairman");
							 break; 
						 case "commissioner1":
							 firstmember=user;
							 firstmember.setRole("commissioner1");
							 break; 
						 case "commissioner2":
							 secondmember=user;
							 secondmember.setRole("commissioner2");
							 break;
						 default:
								break;	 
						 }
					 }
					theSelectedSupervisor.setText("The supervisor is-"+this.supervisor.getFirstName()+" "+this.supervisor.getLastName());
		    		theSelectedChairman.setText("The chairman is-"+this.chairman.getFirstName()+" "+this.chairman.getLastName());
		    		theSelectedFirstM.setText("The first member is-"+this.firstmember.getFirstName()+" "+this.firstmember.getLastName());
		    		theSelectedSecondM.setText("The first member is-"+this.secondmember.getFirstName()+" "+this.secondmember.getLastName());
		    		theSelectedSupervisor.setVisible(true);
		    		theSelectedChairman.setVisible(true);
		    		theSelectedFirstM.setVisible(true);
		    		theSelectedSecondM.setVisible(true);
		    		}
					});
					break;
				case getEmployeesForComboBox:
					Platform.runLater(() -> {
						listOfEmployees=(UserList)msg.Data;
						/*for(User u:listOfEmployees.user_list) {
							System.out.println("controller "+u.toString());
						}*/
						chairmanComboBox.setDisable(true);
						 firstmemberComboBox.setDisable(true);
						 secondMemberComboBox.setDisable(true);
						loadComboBox(listOfEmployees);
						loadComboBoxForMaintenance(listOfEmployees);
					});
					break;
				case updateAppointment:
					Platform.runLater(() -> {
					if((int)msg.Data==1) {
					this.appointment = true;
						appointmentlable.setVisible(true);
						replacementlable.setVisible(true);
					}
					else {
						appointmentlable.setText("problem");
						appointmentlable.setVisible(true);
					}
					});
					break;
				case updateUserSystemMaintenance:
					Platform.runLater(() -> {
						if ((int)msg.Data==1) {
							maintenanceLable.setVisible(true);
						}
						else {
							maintenanceLable.setText("problem");
							maintenanceLable.setVisible(true);
						}
					});
					break;
				default:
					break;
				}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		/**
		 * Load combo box.
		 *
		 * @param list the list
		 */
		private void loadComboBox(UserList list) {
			 ObservableList<String> itEmployees = FXCollections.observableArrayList();
			 for(User user:list.user_list) {
				 if(user.isAvailable()) {
				 String str=user.getFirstName()+" "+user.getLastName();
				 itEmployees.add(str);
				 }
			 }
			 
			 	//return itEmployees;
			 if(!appointment)
			 {
			 supervisorComboBox.setItems(itEmployees);
			 chairmanComboBox.setItems(itEmployees);
			 firstmemberComboBox.setItems(itEmployees);
			 secondMemberComboBox.setItems(itEmployees);
			 }
			 else
			 {
				 supervisorComboBoxReplace.setItems(itEmployees);
				 chairmanComboBoxReplace.setItems(itEmployees);
				 firstmemberComboBoxReplace.setItems(itEmployees);
				 secondMemberComboBoxReplace.setItems(itEmployees); 
			 }
			 
		}
		
		/**
		 * Load combo box for maintenance.
		 *
		 * @param list the list
		 */
		private void loadComboBoxForMaintenance(UserList list) {
			 ObservableList<String> itEmployees = FXCollections.observableArrayList();
			 for(User user:list.user_list) {
				// if(user.isAvailable()) {
				 String str=user.getFirstName()+" "+user.getLastName();
				 itEmployees.add(str);
				// }
			 }
			 employeeComboBox.setItems(itEmployees); 
		}
		
	
	
	
	
	
	
	    /**
    	 * Preesed activity.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void PreesedActivity(ActionEvent event) {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
	    	thisNode = ((Node) event.getSource());
	    	thisNode.getScene().getWindow().hide();
			GuiManager.SwitchScene(GuiManager.SCREENS.ActivityReport, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
	    }

	    /**
    	 * Preesed delays.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void PreesedDelays(ActionEvent event) {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
	    	thisNode = ((Node) event.getSource());
	    	thisNode.getScene().getWindow().hide();
			GuiManager.SwitchScene(GuiManager.SCREENS.DelaysExecution, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
	    }

	    /**
    	 * Preesed performence.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void PreesedPerformence(ActionEvent event) {

	    }

	    /**
    	 * Pressed data.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void PressedData(ActionEvent event) {
	    	replaceEmployeesVbox.setVisible(false);
			appointmentEmployees.setVisible(false);
			appoitmentPane.setVisible(false);
			StatisticalScreen.setVisible(false);
			HomePageVBox.setVisible(false);
			maintenanceAppointmentPane.setVisible(false);
			DataScreen.setVisible(true);
	    }

	    /**
    	 * Pressed new request.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void PressedNewRequest(ActionEvent event) {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
	    	thisNode = ((Node) event.getSource());
	    	//thisNode.getScene().getWindow().hide();
			GuiManager.SwitchScene(GuiManager.SCREENS.NewRequestDirector, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);

	    }


	    /**
    	 * Log out display.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void logOutDisplay(MouseEvent event) {
	    	 // logout
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("ICM Log Out");
			alert.setHeaderText("Are you sure you want to log out?");
			Optional<ButtonType> option = alert.showAndWait();
			if (option.get() == ButtonType.OK)
			{
				Stage SeondStage = new Stage();
				SeondStage.setTitle("ICM");
				((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
				GuiManager.SwitchScene(SCREENS.login, SeondStage);
				
			}

			else if (option.get() == ButtonType.CANCEL)
			{
				alert.close();
			}
	    }
	    

	    /**
    	 * Preesed employees 1.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void PreesedEmployees1(ActionEvent event) {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
	    	thisNode = ((Node) event.getSource());
			GuiManager.SwitchScene(GuiManager.SCREENS.employeesDatas, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
	    }


	    /**
    	 * Preesed requests.
    	 *
    	 * @param event the event
    	 */
    	@FXML
	    void PreesedRequests(ActionEvent event) {
	    	User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
	    	thisNode = ((Node) event.getSource());
	    	//thisNode.getScene().getWindow().hide();
			GuiManager.SwitchScene(GuiManager.SCREENS.requestsDatas, (Stage)thisNode.getScene().getWindow());
			GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
	    }
}