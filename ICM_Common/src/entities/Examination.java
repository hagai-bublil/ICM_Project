package entities;

/**
 * The Examination entity represents a row in the examination table in the data base
 *
 */
public class Examination extends AbstractStage{
	
	/** The failure details. */
	private String failureDetails;
	
	/** The examination result. */
	private String examinationResult;
	
	/**
	 * Instantiates a new examination.
	 *
	 * @param stageName the stage name
	 * @param num the num
	 */
	/*public Examination(int requestNumber, ItEmployee stageManager, String startDate, String dueDate, boolean exception,
			int exceptionTime, boolean extension, int extensionTime, String extensionReason,String failureDetails,String examinationResult) {
		super(requestNumber, stageManager, startDate, dueDate, exception, exceptionTime, extension, extensionTime,
				extensionReason,"examination");
		this.failureDetails=failureDetails;
		this.examinationResult=examinationResult;
	}*/
	public Examination(String stageName,int num) {
		super(stageName);

	}
	
	/**
	 * Instantiates a new examination.
	 *
	 * @param stageName the stage name
	 */
	public Examination(String stageName) {
		super(stageName);
		this.failureDetails="";
		this.examinationResult="";
	}
	
	/**
	 * Instantiates a new examination.
	 *
	 * @param requestNumber the request number
	 * @param startDate the start date
	 * @param dueDate the due date
	 * @param extension the extension
	 * @param extensionTime the extension time
	 * @param extensionReason the extension reason
	 * @param failureDetails the failure details
	 * @param examinationResult the examination result
	 * @param IsDone the is done
	 * @param IsLate the is late
	 */
	public Examination(int requestNumber,
			String startDate,String dueDate,
			boolean extension,int extensionTime,String extensionReason,String failureDetails,String examinationResult, int IsDone,int IsLate) {
		super(requestNumber, startDate, dueDate, extension, extensionTime,
				extensionReason, IsDone,IsLate);
		this.failureDetails=failureDetails;
		this.examinationResult=examinationResult;
	}

	/**
	 * Gets the failure details.
	 *
	 * @return the failure details
	 */
	public String getFailureDetails() {
		return failureDetails;
	}

	/**
	 * Sets the failure details.
	 *
	 * @param failureDetails the new failure details
	 */
	public void setFailureDetails(String failureDetails) {
		this.failureDetails = failureDetails;
	}

	/**
	 * Gets the examination result.
	 *
	 * @return the examination result
	 */
	public String getExaminationResult() {
		return examinationResult;
	}

	/**
	 * Sets the examination result.
	 *
	 * @param examinationResult the new examination result
	 */
	public void setExaminationResult(String examinationResult) {
		this.examinationResult = examinationResult;
	}
	
}
