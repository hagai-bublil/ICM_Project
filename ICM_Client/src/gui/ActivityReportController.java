package gui;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.util.Date;
//import java.util.Date;
//import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import entities.DBMessage;
import entities.Report;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.IClient;

/**
 * This class is the controller of: ActivityReportScreen and it contains all the methods that manage the screen
 *
 */
public class ActivityReportController extends DirectorHomePageController implements IClient, Initializable {
    
    /** The anchor pane. */
    @FXML
    private AnchorPane anchorPane;

    /** The home page. */
    @FXML
    private Pane homePage;

    /** The vbox 1. */
    @FXML
    private VBox vbox1;

    /** The Registration btn. */
    @FXML
    private Button Registration_btn;

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

    /** The Activity screen. */
    @FXML
    private Pane ActivityScreen;

    /** The Activity report screen. */
    @FXML
    private Label ActivityReportScreen;

    /** The from. */
    @FXML
    private DatePicker from;

    /** The please inser date 1. */
    @FXML
    private Label pleaseInserDate1;

    /** The until. */
    @FXML
    private DatePicker until;

    /** The please inser date 2. */
    @FXML
    private Label pleaseInserDate2;

    /** The incorrect dates. */
    @FXML
    private Label incorrectDates;

    /** The interval text. */
    @FXML
    private TextField interval_text;

    /** The please inser interval. */
    @FXML
    private Label pleaseInserInterval;

    /** The title request num. */
    @FXML
    private Label titleRequestNum;

    /** The num of all activitiy req. */
    @FXML
    private Label numOfAllActivitiyReq;

    /** The median of activity req. */
    @FXML
    private Label medianOfActivityReq;

    /** The average of all activity req. */
    @FXML
    private Label averageOfAllActivityReq;

    /** The standard of activity req 1. */
    @FXML
    private Label standardOfActivityReq1;

    /** The num of all closing req. */
    @FXML
    private Label numOfAllClosingReq;

    /** The median of closing req. */
    @FXML
    private Label medianOfClosingReq;

    /** The average of all closing req. */
    @FXML
    private Label averageOfAllClosingReq;

    /** The standard of closing req. */
    @FXML
    private Label standardOfClosingReq;

    /** The num of all suspend req. */
    @FXML
    private Label numOfAllSuspendReq;

    /** The median of suspend req. */
    @FXML
    private Label medianOfSuspendReq;

    /** The average of all suspend req. */
    @FXML
    private Label averageOfAllSuspendReq;

    /** The standard of suspend req. */
    @FXML
    private Label standardOfSuspendReq;

    /** The logout pane. */
    @FXML
    private Pane logout_pane;

    /** The user name label. */
    @FXML
    private Label userNameLabel;

    /** The date label. */
    @FXML
    private Label dateLabel;
    
    /** The details request pane. */
    @FXML
    private Pane detailsRequestPane;

	/** The log out bnt. */
	@FXML
	private Button logOutBnt;
	
    /** The bar chart. */
    @FXML
    private BarChart<?, ?> barChart;
    
    /** The bar chart 2. */
    @FXML
    private BarChart<?, ?> barChart2;
    
    /** The bar chart 3. */
    @FXML
    private BarChart<?, ?> barChart3;
    
    /** The comfor pane 1. */
    @FXML
    private Pane comforPane1;

    /** The title request num 1. */
    @FXML
    private Label titleRequestNum1;

    /** The num of all activitiy req 1. */
    @FXML
    private Label numOfAllActivitiyReq1;

    /** The bar chart 1. */
    @FXML
    private BarChart<?, ?> barChart1;

    /** The sum duration. */
    @FXML
    private Label sumDuration;

    /** The bar chart 21. */
    @FXML
    private BarChart<?, ?> barChart21;
      
      /** The home bnt. */
      @FXML
	    private Button home_bnt;

    
    
	/** The user loged. */
	private User userLoged;
	// LocalDate d1;

	/** The d 1. */
	private Date d2, d1;
	
	/** The interval. */
	private int interval = 0;
	
	/** The report. */
	private Report report;
	
	/** The flag. */
	private boolean flag = false;
	
	/** The this report. */
	private Report thisReport;
	
	/** The flag msg. */
	private boolean flagMsg=false;
	 
 	/** The this node. */
 	private Node thisNode;
	    
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
	 * Preesed interval.
	 *
	 * @param event the event
	 */
	@FXML
	void PreesedInterval(ActionEvent event) {
	}

	/**
	 * Preesed from.
	 *
	 * @param event the event
	 */
	@FXML
	void PreesedFrom(ActionEvent event) {

	}
	
	/**
	 * Check details.
	 *
	 * @return true, if successful
	 */
	public boolean checkDetails() {
			
			
			if (interval_text.getText() != null && !interval_text.getText().isEmpty())
				interval = Integer.parseInt(interval_text.getText());
			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
				if(from.getValue()!=null) {
				d1 = new java.sql.Date(df.parse(from.getValue().toString()).getTime());
				pleaseInserDate1.setVisible(false);
				}
				if(until.getValue()!=null) {
				d2 = new java.sql.Date(df.parse(until.getValue().toString()).getTime());
				pleaseInserDate2.setVisible(false);
				}

			} catch (ParseException e) {
				System.out.println("blaaaaaa");
				e.printStackTrace();
			}

			if (d1 == null) {
				pleaseInserDate1.setVisible(true);
				flag = true;
			}
			else {
				flag=false;
			}
			if (d2 == null) {
				pleaseInserDate2.setVisible(true);
				flag = true;
			}
			else {
				flag=false;
			}
			if (d1 != null && d2 != null) {
				if (d1.after(d2)) {
					incorrectDates.setVisible(true);
					flag = true;
				}
				else {
					incorrectDates.setVisible(false);
					flag=false;
				}
			}

			
				System.out.println(getDifferenceDays(d1,d2));
			if (interval == 0 || getDifferenceDays(d1,d2)<interval) {
				pleaseInserInterval.setVisible(true);
				flag = true;
			} else pleaseInserInterval.setVisible(false);
			return flag;
	}

	/**
	 * Preesed OK.
	 *
	 * @param event the event
	 * @throws ParseException the parse exception
	 */
	@FXML
	void PreesedOK(ActionEvent event) throws ParseException {
		
		//vbox1.setVisible(false);
			flag=checkDetails();
		if (flag == false) {

			

			report = new Report(d1, d2, interval);
			report.setD1(d1);
			report.setD2(d2);
			report.setInterval(interval);
			ActivityScreen.setVisible(false);
		//	vbox1.setVisible(false);
			detailsRequestPane.setVisible(true);
			Report report=new Report(d1,d2,interval);
			
			GuiManager.client.ActivityReport(report);	

			DetailsPeriod();
			
			try {
				Thread.sleep(1500);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Adds the days.
	 *
	 * @param date the date
	 * @param days the days
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date addDays(Date date, int days) throws ParseException {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		date = new java.sql.Date(df.parse(date.toString()).getTime());

    	LocalDate dateTemp=((java.sql.Date) date).toLocalDate().plusDays(days);
    	//System.out.println(date.valueOf(dateTemp));
		return java.sql.Date.valueOf(dateTemp);
	}
	
	/**
	 * Details period.
	 *
	 * @throws ParseException the parse exception
	 */
	public void DetailsPeriod() throws ParseException {
		
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int i=0;
		Date date1= (java.sql.Date)thisReport.getD1();
		int interval=thisReport.getInterval();
		Date date2=addDays(date1, interval);
		
		XYChart.Series set1= new XYChart.Series<>();
		XYChart.Series set2= new XYChart.Series<>();
		XYChart.Series set3= new XYChart.Series<>();

	
		 System.out.println(thisReport.getD1());
		 titleRequestNum.setText("Statistical information from "+thisReport.getD1()+" to "+addDays(thisReport.getD2(), -1));
		 numOfAllActivitiyReq.setText(Integer.toString(thisReport.getSumActivity()));
		 medianOfActivityReq.setText(Integer.toString(thisReport.getMedianActivity()));
		 averageOfAllActivityReq.setText(Double.toString(thisReport.getAverageActivity()));
		 standardOfActivityReq1.setText(Double.toString(thisReport.getStandardDeviationActivity()));
		 
		 
		 numOfAllClosingReq.setText(Integer.toString(thisReport.getSumClosing()));
		 medianOfClosingReq.setText(Integer.toString(thisReport.getMedianClosing()));
		 averageOfAllClosingReq.setText(Double.toString(thisReport.getAverageClosing()));
		 standardOfClosingReq.setText(Double.toString(thisReport.getStandardDeviationClosing()));
		 
		 numOfAllSuspendReq.setText(Integer.toString(thisReport.getSumSuspend()));
		 medianOfSuspendReq.setText(Integer.toString(thisReport.getMedianSuspend()));
		 averageOfAllSuspendReq.setText(Double.toString(thisReport.getAverageSuspend()));
		 standardOfSuspendReq.setText(Double.toString(thisReport.getStandardDeviationSuspend()));
		 
		 System.out.println(thisReport.getListClosing().size());
			while(i<thisReport.getListClosing().size()) {
				
				set1.getData().add(new XYChart.Data(/*date1.toString()*/Integer.toString(i+1),thisReport.getListActivity().get(i)));
				set2.getData().add(new XYChart.Data(/*date1.toString()*/Integer.toString(i+1),thisReport.getListClosing().get(i)));
				set3.getData().add(new XYChart.Data(/*date1.toString()*/Integer.toString(i+1),thisReport.getListSuspend().get(i)));

				//barChart.getData().add(set1);
					date1=date2;
					date2=addDays(date2, interval);
					i++;
				}barChart.getData().addAll(set1);
				barChart2.getData().addAll(set2);
				barChart3.getData().addAll(set3);
			
	
	}
	
	/**
	 * Preesed until.
	 *
	 * @param event the event
	 * @throws ParseException the parse exception
	 */
	@FXML
	void PreesedUntil(ActionEvent event) throws ParseException {

	}

	/**
	 * Initialize.
	 *
	 * @param location the location
	 * @param resources the resources
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		detailsRequestPane.setVisible(false);
		ActivityScreen.setVisible(true);
		comforPane1.setVisible(false);
	
	}

	/**
	 * Pressed data.
	 *
	 * @param event the event
	 */
	@FXML
	void PressedData(ActionEvent event) {
		super.PressedData(event);
	}

	/**
	 * Pressed new request.
	 *
	 * @param event the event
	 */
	@FXML
	void PressedNewRequest(ActionEvent event) {
		super.PressedNewRequest(event);
	}

	/**
	 * Pressed statistical.
	 *
	 * @param event the event
	 */
	@FXML
	void PressedStatistical(ActionEvent event) {
		// HomePageVBox.setVisible(false);

		ActivityScreen.setVisible(true);
	}
	
	/**
	 * Preesed delays.
	 *
	 * @param event the event
	 */
	@FXML
	void PreesedDelays(ActionEvent event) {
		ActivityScreen.setVisible(false);
		comforPane1.setVisible(true);
		
		flag=checkDetails();
		if (flag == false) {
			report = new Report(d1, d2, interval);
			report.setD1(d1);
			report.setD2(d2);
			report.setInterval(interval);
			ActivityScreen.setVisible(false);
			detailsRequestPane.setVisible(true);
			Report report=new Report(d1,d2,interval);
			
			GuiManager.client.DelaysRequestReport(report);	
		}
	}
	
	/**
	 * Press home.
	 *
	 * @param event the event
	 */
	@FXML
	void pressHome(ActionEvent event) {
		User userLogedIn=GuiManager.CurrentGuiController.getUserLogedIn();
    	thisNode = ((Node) event.getSource());
    	thisNode.getScene().getWindow().hide();
		GuiManager.SwitchScene(GuiManager.SCREENS.DirectorHomePage, (Stage)thisNode.getScene().getWindow());
		GuiManager.CurrentGuiController.setUserLogedIn(userLogedIn);
	}
	
	/**
	 * Preesed performence.
	 *
	 * @param event the event
	 */
	@FXML
	void PreesedPerformence(ActionEvent event) {
		// HomePageVBox.setVisible(false);
//		ActivityScreen.setVisible(false);
//		//comforPane1.setVisible(true);
//		flag=checkDetails();
//		if (flag == false) {
//			report = new Report(d1, d2, interval);
//			report.setD1(d1);
//			report.setD2(d2);
//			report.setInterval(interval);
//			ActivityScreen.setVisible(false);
//			detailsRequestPane.setVisible(true);
//			Report report=new Report(d1,d2,interval);
//			
//			GuiManager.client.ExtentionRequestReport(report);	
			
			
	//	}
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
	 * Gets the message from server.
	 *
	 * @param msg the msg
	 * @return the message from server
	 */
	@Override
	public void getMessageFromServer(DBMessage msg) {
		switch (msg.Action) {
		case Activity: {
			thisReport=(Report)msg.Data;
			thisReport.setD1(((Report)msg.Data).getD1());
			System.out.println(thisReport.getD1());
			flagMsg=true;
		}

		}

	}

	/**
	 * Sets the user loged in.
	 *
	 * @param userLoged the new user loged in
	 */
	@Override
	public void setUserLogedIn(User userLoged) {
		// TODO Auto-generated method stub
		this.userLoged = userLoged;
		String userName = userLoged.getUserName();
		userNameLabel.setText(userName);
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String string = format.format(calendar.getTime());
		dateLabel.setText(string);
	}

	/**
	 * Gets the user loged in.
	 *
	 * @return the user loged in
	 */
	@Override
	public User getUserLogedIn() {
		// TODO Auto-generated method stub
		return userLoged;
	}
	
	/**
	 * Gets the difference days.
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @return the difference days
	 */
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

}
