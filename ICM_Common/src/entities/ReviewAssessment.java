package entities;

/**
 * The ReviewAssessment entity represents a row in the reviewassessment table in the data base
 *
 */
public class ReviewAssessment extends AbstractStage{

	/** The decision. */
	private String decision;
	
	/** The extra details. */
	private String extraDetails;
	
	/**
	 * Instantiates a new review assessment.
	 *
	 * @param stageName the stage name
	 */
	/*public ReviewAssessment(int requestNumber, ItEmployee stageManager, String startDate, String dueDate,
			boolean exception, int exceptionTime, boolean extension, int extensionTime, String extensionReason,String decision,String extraDetails) {
		super(requestNumber, stageManager, startDate, dueDate, exception, exceptionTime, extension, extensionTime,
				extensionReason,"reviewAssessment");
		this.decision=decision;
		this.extraDetails=extraDetails;
		
	}*/
	public ReviewAssessment(String stageName) {
		super(stageName);
		this.decision="";
		this.extraDetails="";
	}
	
	/**
	 * Instantiates a new review assessment.
	 *
	 * @param requestNumber the request number
	 * @param startDate the start date
	 * @param dueDate the due date
	 * @param extension the extension
	 * @param extensionTime the extension time
	 * @param extensionReason the extension reason
	 * @param decision the decision
	 * @param extraDetails the extra details
	 * @param IsDone the is done
	 * @param IsLate the is late
	 */
	public ReviewAssessment(int requestNumber,
			String startDate,String dueDate,
			boolean extension,int extensionTime,String extensionReason,String decision,String extraDetails,int IsDone,int IsLate) {
		super(requestNumber, startDate, dueDate,extension, extensionTime,
				extensionReason, IsDone, IsLate);
		this.decision=decision;
		this.extraDetails=extraDetails;
	}
	
	/**
	 * Gets the decision.
	 *
	 * @return the decision
	 */
	public String getDecision() {
		return decision;
	}
	
	/**
	 * Sets the decision.
	 *
	 * @param decision the new decision
	 */
	public void setDecision(String decision) {
		this.decision = decision;
	}
	
	/**
	 * Gets the extra details.
	 *
	 * @return the extra details
	 */
	public String getExtraDetails() {
		return extraDetails;
	}
	
	/**
	 * Sets the extra details.
	 *
	 * @param extraDetails the new extra details
	 */
	public void setExtraDetails(String extraDetails) {
		this.extraDetails = extraDetails;
	}

}
