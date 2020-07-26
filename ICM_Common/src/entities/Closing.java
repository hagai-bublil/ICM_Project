package entities;

/**
 * The Closing entity represents a row in the closing table in the data base
 *
 */
public class Closing extends AbstractStage{


	/** The closing result. */
	private String closingResult;
	
	/**
	 * Instantiates a new closing.
	 *
	 * @param stageName the stage name
	 */
	/*public Closing(int requestNumber, ItEmployee stageManager, String startDate, String dueDate, boolean exception,
			int exceptionTime, boolean extension, int extensionTime, String extensionReason,String closingResult) {
		super(requestNumber, stageManager, startDate, dueDate, exception, exceptionTime, extension, extensionTime,
				extensionReason,"closing");
		this.closingResult=closingResult;
	}*/
	public Closing(String stageName) {
		super(stageName);
		this.closingResult="";
	}
	
	/**
	 * Instantiates a new closing.
	 *
	 * @param requestNumber the request number
	 * @param startDate the start date
	 * @param dueDate the due date
	 * @param extension the extension
	 * @param extensionTime the extension time
	 * @param extensionReason the extension reason
	 * @param closingResult the closing result
	 * @param IsDone the is done
	 * @param IsLate the is late
	 */
	public Closing(int requestNumber,
			String startDate,String dueDate,
			boolean extension,int extensionTime,String extensionReason,String closingResult,int IsDone,int IsLate) {
		super(requestNumber, startDate, dueDate, extension, extensionTime,
				extensionReason, IsDone,IsLate);
		this.closingResult=closingResult;
	}

	/**
	 * Gets the closing result.
	 *
	 * @return the closing result
	 */
	public String getClosingResult() {
		return closingResult;
	}

	/**
	 * Sets the closing result.
	 *
	 * @param closingResult the new closing result
	 */
	public void setClosingResult(String closingResult) {
		this.closingResult = closingResult;
	}

	
}
