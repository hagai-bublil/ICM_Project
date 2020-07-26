package entities;

/**
 * The Assessment entity represents a row in the assessment table in the data base
 *
 */
public class Assessment extends AbstractStage{

	/** The assessment report. */
	private String assessmentReport;
	
	/** The supervisor due date approve. */
	private boolean supervisorDueDateApprove;
	
	/**
	 * Instantiates a new assessment.
	 *
	 * @param stageName the stage name
	 */
	/*public Assessment(int requestNumber, ItEmployee stageManager, String startDate, String dueDate, boolean exception,
			int exceptionTime, boolean extension, int extensionTime, String extensionReason,String assessmentReport ) {
		super(requestNumber, stageManager, startDate, dueDate, exception, exceptionTime, extension, extensionTime,
				extensionReason,"assessment");
		this.assessmentReport=assessmentReport;
	}*/
	public Assessment(String stageName) {
		super(stageName);
		this.assessmentReport="";
	}
	
	/**
	 * Instantiates a new assessment.
	 *
	 * @param requestNumber the request number
	 * @param startDate the start date
	 * @param dueDate the due date
	 * @param extension the extension
	 * @param extensionTime the extension time
	 * @param extensionReason the extension reason
	 * @param assessmentReport the assessment report
	 * @param supervisorDueDateApprove the supervisor due date approve
	 * @param IsDone the is done
	 * @param IsLate the is late
	 */
	public Assessment(int requestNumber,String startDate,String dueDate,
			boolean extension,int extensionTime,String extensionReason,String assessmentReport,boolean supervisorDueDateApprove, int IsDone,int IsLate) {
		super(requestNumber, startDate, dueDate, extension, extensionTime,extensionReason,IsDone,IsLate);
		this.assessmentReport=assessmentReport;
		this.supervisorDueDateApprove=supervisorDueDateApprove;
	}
	
	/**
	 * Gets the assessment report.
	 *
	 * @return the assessment report
	 */
	public String getAssessmentReport() {
		return assessmentReport;
	}
	
	/**
	 * Sets the assessment report.
	 *
	 * @param assessmentReport the new assessment report
	 */
	public void setAssessmentReport(String assessmentReport) {
		this.assessmentReport = assessmentReport;
	}
	
	/**
	 * Checks if is supervisor due date approve.
	 *
	 * @return true, if is supervisor due date approve
	 */
	public boolean isSupervisorDueDateApprove() {
		return supervisorDueDateApprove;
	}
	
	/**
	 * Sets the supervisor due date approve.
	 *
	 * @param supervisorDueDateApprove the new supervisor due date approve
	 */
	public void setSupervisorDueDateApprove(boolean supervisorDueDateApprove) {
		this.supervisorDueDateApprove = supervisorDueDateApprove;
	}
	
	/**
	 * Gets the supervisor due date approve.
	 *
	 * @return the supervisor due date approve
	 */
	public boolean getSupervisorDueDateApprove() {
		return this.supervisorDueDateApprove;
	}

}
