package entitiesQueries;

//import java.awt.List;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import application.SqlConnection;
import entities.DBMessage;
import entities.DBMessage.DBAction;
import entities.Report;

/**
 * This class hold all the queries for reports 
 *
 */
public class ReportQueries {
	
	
	/**
	 * Gets the count of delays pair period.
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @param icmDB the icm DB
	 * @return the count of delays pair period
	 */
	public static double getCountOfDelaysPairPeriod(Date d1, Date d2, SqlConnection icmDB) {//get the number of all the activity requests between tow dates
//		Integer count = 0;
		Double count = 0.0;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		
		String queryMsg = "SELECT count(*) FROM icm_db.delays WHERE  and submissionDate<'" +d2+"';";
		ResultSet rs = icmDB.executeQuery(queryMsg);
		try {
			if (rs.next())
				count = rs.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;// + getNumofClosingPairPeriod(d1,d2, icmDB) + getNumofSuspendPairPeriod2(d1,d2,icmDB);

	}
	
	/**
	 * Creates the activity request.
	 *
	 * @param report the report
	 * @param icmDB the icm DB
	 * @return the DB message
	 * @throws ParseException the parse exception
	 */
	public static DBMessage CreateActivityRequest(Report report, SqlConnection icmDB) throws ParseException {
		//Date d1=
		Date d2	=addDays((Date)report.getD2(), 1);
		Date d1=(Date)report.getD1();
		int interval=report.getInterval();
		Date checkDate = (Date) d1;
		Date checkDate2 = (Date) (addDays(d1,interval));

		Date dateFirst;
		double averageClosing = 0;
		double averageActivity = 0;
		double averageSuspend = 0;
		int medianClosing = 0;
		int medianActivity = 0;
		int medianSuspend = 0;
		double standardDeviationClosing;
		double standardDeviationActivity;
		double standardDeviationSuspend;
		

		Integer i = 1;
		int j = 1;
		int sumClosing=0;
		int sumActivity=0;
		int sumSuspend=0;
		
//		List<Integer> listClosing=new ArrayList<>();
//		List<Integer> listActivity=new ArrayList<>();
//		List<Integer> listSuspend=new ArrayList<>();
		List<Double> listClosing=new ArrayList<>();
		List<Double> listActivity=new ArrayList<>();
		List<Double> listSuspend=new ArrayList<>();


		while (checkDate2.before(addDays(d2,1))) {
			System.out.println(checkDate);

			listActivity.add(getNumofActivityPairPeriod(checkDate, checkDate2, icmDB));
			listClosing.add((double)getNumofClosingPairPeriod(checkDate, checkDate2, icmDB));
			System.out.println(getNumofClosingPairPeriod(checkDate, checkDate2, icmDB));
			listSuspend.add((double)getNumofSuspendPairPeriod(checkDate, checkDate2, icmDB));
			
			checkDate=checkDate2;
			if (addDays(checkDate2, interval).after(d2))
				while (addDays(checkDate2, 1).before(addDays(d2,2))){
					checkDate2 = (Date) addDays(checkDate2, 1);
				}
			else {
				checkDate2 =  addDays(checkDate2, interval);
			}
			i++;

		}
		sumClosing=getNumofClosingPairPeriod(d1, d2, icmDB);//v
		sumActivity=(int)getNumofActivityPairPeriod(d1, d2, icmDB);
		sumSuspend=getNumofSuspendPairPeriod(d1, d2, icmDB);
		
		averageClosing = findAverage(listClosing);
		averageActivity = findAverage(listActivity);
		averageSuspend = findAverage(listSuspend);
		
		medianClosing = (int)findMedian(listClosing);
		medianActivity =(int) findMedian(listActivity);
		medianSuspend = (int)findMedian(listSuspend);
				
		standardDeviationClosing=findStandardDeviation(listClosing , averageClosing);
		standardDeviationActivity=findStandardDeviation(listActivity , averageActivity);
		standardDeviationSuspend=findStandardDeviation(listSuspend , averageSuspend);
		
		Report report1=new Report(listClosing,listActivity, listSuspend,d1, d2, interval,averageClosing,medianClosing,standardDeviationClosing,sumClosing, averageActivity, medianActivity ,standardDeviationActivity, sumActivity ,averageSuspend ,medianSuspend ,standardDeviationSuspend, sumSuspend);
		
		DBMessage returnMsg;
		returnMsg = new DBMessage(DBAction.Activity,report1);//if the request number is exist
		return returnMsg;
	}
	


	/**
	 * Find standard deviation.
	 *
	 * @param list the list
	 * @param average the average
	 * @return the double
	 */
	public static double findStandardDeviation(List<Double> list, double average) {// func that find the Standard Deviation of all the values in the map
		double standardDeviation = 0;
		ArrayList<Double> list2 = new ArrayList<>();
		double value;
		Double value2;
		for (int i = 0; i < list.size(); i++) {
			value=(double)list.get(i);
			value2=(value)-average;
			value2=Math.pow(value2, 2);
			list2.add(value2);
		}
	
		for(int i=0; i<list2.size(); i++)
			standardDeviation+=list2.get(i);
		standardDeviation=standardDeviation/list2.size();
		standardDeviation=Math.pow(standardDeviation, 0.5);
		return standardDeviation;
	}

	/**
	 * Find average.
	 *
	 * @param list the list
	 * @return the double
	 */
	public static double findAverage(List<Double> list) {// func that find the average of all the values in the map
		double average = 0;
		double total = 0;

		
		for (int i = 0; i < list.size(); i++) {
			total +=list.get(i);
		}

		average = total / list.size();
		return average;
	}

	/**
	 * Find median.
	 *
	 * @param list the list
	 * @return the double
	 */
	public static double findMedian(List<Double> list) {// func that find the median of all the values in the map
		int i = 0;
		List<Double> list1= new ArrayList<>();
		for (int j = 0; j < list.size(); j++) {
			list1.add(list.get(j));
		}
		//if (list.size() % 2 == 0)
			//i = (list.size() / 2) + 1;
		//else {
			i = list1.size()/2;
			i++;
			Collections.sort(list1); 
		//}

		return list1.get(i-1);
	}

	/**
	 * Gets the numof activity pair period.
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @param icmDB the icm DB
	 * @return the numof activity pair period
	 */
	public static double getNumofActivityPairPeriod(Date d1, Date d2, SqlConnection icmDB) {//get the number of all the activity requests between tow dates
//		Integer count = 0;
		Double count = 0.0;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		
		String queryMsg = "SELECT count(*) FROM icm_db.requests WHERE status!='closing' and suspend=0 and submissionDate<'" +d2+"';";
		ResultSet rs = icmDB.executeQuery(queryMsg);
		try {
			if (rs.next())
				count = rs.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;// + getNumofClosingPairPeriod(d1,d2, icmDB) + getNumofSuspendPairPeriod2(d1,d2,icmDB);

	}

	/**
	 * Gets the numof closing pair period.
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @param icmDB the icm DB
	 * @return the numof closing pair period
	 */
	public static int getNumofClosingPairPeriod(Date d1, Date d2, SqlConnection icmDB) {//get the number of requests that been closed between tow dates
		Integer count = 0;
		String queryMsg = "SELECT count(*) FROM icm_db.closing WHERE dueDate < '" + d2 +"' and dueDate>='"+ d1+ "';";
				
		ResultSet rs = icmDB.executeQuery(queryMsg);
		try {
			if (rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;

	}/// insert to DB at the coulmns of the suspend and reject- the date that suspend

	/**
	 * Gets the numof suspend pair period 2.
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @param icmDB the icm DB
	 * @return the numof suspend pair period 2
	 */
	public static int getNumofSuspendPairPeriod2(Date d1, Date d2, SqlConnection icmDB) {//get the number of suspend requests between tow dates 
		Integer count = 0;
		String queryMsg = "SELECT count(*) FROM icm_db.suspend as s WHERE ((s.dueDate >= '"+d1+"' and s.dueDate<'"+d2+"') or (s.startDate >= '"+d1+"' and s.startDate<'"+d2+"'));";
		ResultSet rs = icmDB.executeQuery(queryMsg);
		try {
			if (rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;

	}
	
	/**
	 * Gets the numof suspend pair period.
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @param icmDB the icm DB
	 * @return the numof suspend pair period
	 */
	public static int getNumofSuspendPairPeriod(Date d1, Date d2, SqlConnection icmDB) {//get the number of suspend requests between tow dates 
		Integer count = 0;
		String queryMsg = "SELECT count(*) FROM icm_db.suspend WHERE (dueDate >= '"+d1+"' and dueDate<'"+d2+"') or stilSuspend=1;";
		ResultSet rs = icmDB.executeQuery(queryMsg);
		try {
			if (rs.next())
				count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;

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

    	LocalDate dateTemp=date.toLocalDate().plusDays(days);
    	//System.out.println(date.valueOf(dateTemp));
		return date.valueOf(dateTemp);
	}

}


