package entities;

import java.io.Serializable;
import java.sql.Date;
//import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Report.
 */
public class Report implements Serializable {

	/** The report date. */
	private String reportDate;
	
	/** The interval. */
	private int interval;
	
	/** The d 1. */
	private Date d2, d1;

	
	/** The average closing. */
	double averageClosing;
	
	/** The median closing. */
	int medianClosing;
	
	/** The standard deviation closing. */
	double standardDeviationClosing;
	
	/** The sum closing. */
	int sumClosing;

	/** The average activity. */
	double averageActivity;
	
	/** The median activity. */
	int medianActivity;
	
	/** The standard deviation activity. */
	double standardDeviationActivity;
	
	/** The sum activity. */
	int sumActivity;
	
	/** The average suspend. */
	double averageSuspend;
	
	/**
	 * Gets the list closing.
	 *
	 * @return the list closing
	 */
	public List<Double> getListClosing() {
		return listClosing;
	}

	/**
	 * Sets the list closing.
	 *
	 * @param listClosing the new list closing
	 */
	public void setListClosing(List<Double> listClosing) {
		this.listClosing = listClosing;
	}

	/**
	 * Gets the list activity.
	 *
	 * @return the list activity
	 */
	public List<Double> getListActivity() {
		return listActivity;
	}

	/**
	 * Sets the list activity.
	 *
	 * @param listActivity the new list activity
	 */
	public void setListActivity(List<Double> listActivity) {
		this.listActivity = listActivity;
	}

	/**
	 * Gets the list suspend.
	 *
	 * @return the list suspend
	 */
	public List<Double> getListSuspend() {
		return listSuspend;
	}

	/**
	 * Sets the list suspend.
	 *
	 * @param listSuspend the new list suspend
	 */
	public void setListSuspend(List<Double> listSuspend) {
		this.listSuspend = listSuspend;
	}

	/** The median suspend. */
	int medianSuspend;
	
	/** The standard deviation suspend. */
	double standardDeviationSuspend;
	
	/** The sum suspend. */
	int sumSuspend;
	
	/** The list closing. */
	List<Double> listClosing;
	
	/** The list activity. */
	List<Double> listActivity;
	
	/** The list suspend. */
	List<Double> listSuspend;

	/**
	 * Instantiates a new report.
	 *
	 * @param listClosing the list closing
	 * @param listActivity the list activity
	 * @param listSuspend the list suspend
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @param interval the interval
	 * @param averageClosing the average closing
	 * @param medianClosing the median closing
	 * @param standardDeviationClosing the standard deviation closing
	 * @param sumClosing the sum closing
	 * @param averageActivity the average activity
	 * @param medianActivity the median activity
	 * @param standardDeviationActivity the standard deviation activity
	 * @param sumActivity the sum activity
	 * @param averageSuspend the average suspend
	 * @param medianSuspend the median suspend
	 * @param standardDeviationSuspend the standard deviation suspend
	 * @param sumSuspend the sum suspend
	 */
	public Report(List<Double> listClosing, List<Double> listActivity, List<Double> listSuspend,Date d1,Date d2, int interval, double averageClosing, int medianClosing, 
			double standardDeviationClosing, int sumClosing, double averageActivity,
			int medianActivity, double standardDeviationActivity, int sumActivity, 
			double averageSuspend, int medianSuspend, double standardDeviationSuspend, int sumSuspend) {
		this.listClosing=listClosing;
		this.listActivity=listActivity;
		this.listSuspend=listSuspend;
		this.d1=d1;
		this.d2=d2;
		this.interval=interval;
		this.averageClosing=averageClosing;
		this.medianClosing=medianClosing;
		this.standardDeviationClosing=standardDeviationClosing;
		this.sumClosing=sumClosing;
		this.averageActivity=averageActivity;
		this.medianActivity=medianActivity;
		this.standardDeviationActivity=standardDeviationActivity;
		this.sumActivity=sumActivity;
		this.averageSuspend=averageSuspend;
		this.medianSuspend=medianSuspend;
		this.standardDeviationSuspend=standardDeviationSuspend;
		this.sumSuspend=sumSuspend;
	}

	/**
	 * Instantiates a new report.
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @param interval the interval
	 */
	public Report(Date d1, Date d2, int interval) {
		this.d1=d1;
		this.d2=d2;
		this.interval=interval;
	}

	/**
	 * Gets the report date.
	 *
	 * @return the report date
	 */
	public String getReportDate() {
		return reportDate;
	}

	/**
	 * Sets the report date.
	 *
	 * @param reportDate the new report date
	 */
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * Gets the interval.
	 *
	 * @return the interval
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * Sets the interval.
	 *
	 * @param interval the new interval
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}

	/**
	 * Gets the d2.
	 *
	 * @return the d2
	 */
	public Date getD2() {
		return d2;
	}

	/**
	 * Sets the d2.
	 *
	 * @param d2 the new d2
	 */
	public void setD2(Date d2) {
		this.d2 = d2;
	}

	/**
	 * Gets the d1.
	 *
	 * @return the d1
	 */
	public Date getD1() {
		return d1;
	}

	/**
	 * Sets the d1.
	 *
	 * @param d1 the new d1
	 */
	public void setD1(Date d1) {
		this.d1 = d1;
	}

	/**
	 * Gets the average closing.
	 *
	 * @return the average closing
	 */
	public double getAverageClosing() {
		return averageClosing;
	}

	/**
	 * Sets the average closing.
	 *
	 * @param averageClosing the new average closing
	 */
	public void setAverageClosing(double averageClosing) {
		this.averageClosing = averageClosing;
	}

	/**
	 * Gets the median closing.
	 *
	 * @return the median closing
	 */
	public int getMedianClosing() {
		return medianClosing;
	}

	/**
	 * Sets the median closing.
	 *
	 * @param medianClosing the new median closing
	 */
	public void setMedianClosing(int medianClosing) {
		this.medianClosing = medianClosing;
	}

	/**
	 * Gets the standard deviation closing.
	 *
	 * @return the standard deviation closing
	 */
	public double getStandardDeviationClosing() {
		return standardDeviationClosing;
	}

	/**
	 * Sets the standard deviation closing.
	 *
	 * @param standardDeviationClosing the new standard deviation closing
	 */
	public void setStandardDeviationClosing(double standardDeviationClosing) {
		this.standardDeviationClosing = standardDeviationClosing;
	}

	/**
	 * Gets the sum closing.
	 *
	 * @return the sum closing
	 */
	public int getSumClosing() {
		return sumClosing;
	}

	/**
	 * Sets the sum closing.
	 *
	 * @param sumClosing the new sum closing
	 */
	public void setSumClosing(int sumClosing) {
		this.sumClosing = sumClosing;
	}

	/**
	 * Gets the average activity.
	 *
	 * @return the average activity
	 */
	public double getAverageActivity() {
		return averageActivity;
	}

	/**
	 * Sets the average activity.
	 *
	 * @param averageActivity the new average activity
	 */
	public void setAverageActivity(double averageActivity) {
		this.averageActivity = averageActivity;
	}

	/**
	 * Gets the median activity.
	 *
	 * @return the median activity
	 */
	public int getMedianActivity() {
		return medianActivity;
	}

	/**
	 * Sets the median activity.
	 *
	 * @param medianActivity the new median activity
	 */
	public void setMedianActivity(int medianActivity) {
		this.medianActivity = medianActivity;
	}

	/**
	 * Gets the standard deviation activity.
	 *
	 * @return the standard deviation activity
	 */
	public double getStandardDeviationActivity() {
		return standardDeviationActivity;
	}

	/**
	 * Sets the standard deviation activity.
	 *
	 * @param standardDeviationActivity the new standard deviation activity
	 */
	public void setStandardDeviationActivity(double standardDeviationActivity) {
		this.standardDeviationActivity = standardDeviationActivity;
	}

	/**
	 * Gets the sum activity.
	 *
	 * @return the sum activity
	 */
	public int getSumActivity() {
		return sumActivity;
	}

	/**
	 * Sets the sum activity.
	 *
	 * @param sumActivity the new sum activity
	 */
	public void setSumActivity(int sumActivity) {
		this.sumActivity = sumActivity;
	}

	/**
	 * Gets the average suspend.
	 *
	 * @return the average suspend
	 */
	public double getAverageSuspend() {
		return averageSuspend;
	}

	/**
	 * Sets the average suspend.
	 *
	 * @param averageSuspend the new average suspend
	 */
	public void setAverageSuspend(double averageSuspend) {
		this.averageSuspend = averageSuspend;
	}

	/**
	 * Gets the median suspend.
	 *
	 * @return the median suspend
	 */
	public int getMedianSuspend() {
		return medianSuspend;
	}

	/**
	 * Sets the median suspend.
	 *
	 * @param medianSuspend the new median suspend
	 */
	public void setMedianSuspend(int medianSuspend) {
		this.medianSuspend = medianSuspend;
	}

	/**
	 * Gets the standard deviation suspend.
	 *
	 * @return the standard deviation suspend
	 */
	public double getStandardDeviationSuspend() {
		return standardDeviationSuspend;
	}

	/**
	 * Sets the standard deviation suspend.
	 *
	 * @param standardDeviationSuspend the new standard deviation suspend
	 */
	public void setStandardDeviationSuspend(double standardDeviationSuspend) {
		this.standardDeviationSuspend = standardDeviationSuspend;
	}

	/**
	 * Gets the sum suspend.
	 *
	 * @return the sum suspend
	 */
	public int getSumSuspend() {
		return sumSuspend;
	}

	/**
	 * Sets the sum suspend.
	 *
	 * @param sumSuspend the new sum suspend
	 */
	public void setSumSuspend(int sumSuspend) {
		this.sumSuspend = sumSuspend;
	}

//	public Report(String systemName, Date d1, Date d2) {
//		this.setSystemName(systemName);
//		this.setD1(d1);
//		this.setD2(d2);
//	}

//	public Report(String NumberfrozenRequests, String NumberclosedRequests, String NumberrejectedRequests) {
//		this.NumberclosedRequests = NumberclosedRequests;
//		this.NumberrejectedRequests = NumberrejectedRequests;
//		this.NumberfrozenRequests = NumberfrozenRequests;
//	}
//
//	public Report(String reportDate, Date d1, Date d2, String NumbeactiveRequests, String NumberfrozenRequests,
//			String NumberclosedRequests, String NumberrejectedRequests, String amountWorking) {
//		this.reportDate = reportDate;
//		this.setD1(d1);
//		this.setD2(d2);
//		this.NumbeactiveRequests = NumbeactiveRequests;
//		this.NumbeactiveRequests = NumbeactiveRequests;
//		this.NumberfrozenRequests = NumberfrozenRequests;
//		this.NumberclosedRequests = NumberclosedRequests;
//		this.NumberrejectedRequests = NumberrejectedRequests;
//		this.amountWorking = amountWorking;
//	}


	/*public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getNumbeactiveRequests() {
		return NumbeactiveRequests;
	}

	public void setNumbeactiveRequests(String numbeactiveRequests) {
		NumbeactiveRequests = numbeactiveRequests;
	}

	public String getNumberfrozenRequests() {
		return NumberfrozenRequests;
	}

	public void setNumberfrozenRequests(String numberfrozenRequests) {
		NumberfrozenRequests = numberfrozenRequests;
	}

	public String getNumberclosedRequests() {
		return NumberclosedRequests;
	}

	public void setNumberclosedRequests(String numberclosedRequests) {
		NumberclosedRequests = numberclosedRequests;
	}

	public String getNumberrejectedRequests() {
		return NumberrejectedRequests;
	}

	public void setNumberrejectedRequests(String numberrejectedRequests) {
		NumberrejectedRequests = numberrejectedRequests;
	}

	public String getAmountWorking() {
		return amountWorking;
	}

	public void setAmountWorking(String amountWorking) {
		this.amountWorking = amountWorking;
	}

	public Date getD1() {
		return d1;
	}

	public void setD1(Date d1) {
		this.d1 = d1;
	}

	public Date getD2() {
		return d2;
	}

	public void setD2(Date d2) {
		this.d2 = d2;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public List<Report> getMedian() {
		return median;
	}

	public void setMedian(List<Report> median) {
		this.median = median;
	}

	public List<Report> getStandardDeviation() {
		return StandardDeviation;
	}

	public void setStandardDeviation(List<Report> standardDeviation) {
		StandardDeviation = standardDeviation;
	}

	public List<Report> getFrequencyDistribution() {
		return FrequencyDistribution;
	}

	public void setFrequencyDistribution(List<Report> frequencyDistribution) {
		FrequencyDistribution = frequencyDistribution;
	}

	public List<Report> getDurationOfWork() {
		return DurationOfWork;
	}

	public void setDurationOfWork(List<Report> durationOfWork) {
		DurationOfWork = durationOfWork;
	}*/

	/*
	 * public LocalDate getD1() { return d1; } public void setD1(LocalDate d1) {
	 * this.d1 = d1; } public LocalDate getD2() { return d2; } public void
	 * setD2(LocalDate d2) { this.d2 = d2; }
	 * 
	 */

}
