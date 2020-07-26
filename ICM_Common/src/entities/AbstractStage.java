package entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This abstract class is a general description of a stage and all types of stages are inherited from this class
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractStage implements Serializable{
	
	/** The request number. */
	protected int requestNumber;
	
	/** The stage manager. */
	protected User stageManager;
	
	/** The extension date. */
	//protected String startDate,dueDate;
	protected Date startDate,dueDate,extensionDate;
	//protected boolean exception;
	/** The extension. */
	//protected int exceptionTime;
	protected boolean extension;
	
	/** The extension time. */
	protected int extensionTime;
	
	/** The stage name. */
	protected String extensionReason,stageName;
	
	/** The Is done. */
	protected int IsDone;
	
	/** The Is late. */
	protected int IsLate;
	
	
	
	/**
	 * Instantiates a new abstract stage.
	 *
	 * @param requestNumber the request number
	 * @param stageManager the stage manager
	 * @param startDate the start date
	 * @param dueDate the due date
	 * @param exceptionTime the exception time
	 * @param extension the extension
	 * @param extensionTime the extension time
	 * @param extensionReason the extension reason
	 * @param stageName the stage name
	 * @param IsDone the is done
	 * @param IsLate the is late
	 */
	public AbstractStage(int requestNumber,User stageManager,
			Date startDate,Date dueDate,int exceptionTime, 
			boolean extension,int extensionTime,String extensionReason,String stageName,int IsDone,int IsLate) {
		this.requestNumber=requestNumber;
		this.stageManager = stageManager;
		this.startDate=startDate;
		this.dueDate=dueDate;
	//	this.exception = exception;
	//	this.exceptionTime = exceptionTime;
		this.extension = extension;
		this.setExtensionTime(extensionTime);
		this.setExtensionReason(extensionReason);
		this.stageName=stageName;
		this.IsDone=IsDone;
		this.IsLate=IsLate;
	}
	
	/**
	 * Instantiates a new abstract stage.
	 *
	 * @param stageName the stage name
	 */
	public AbstractStage(String stageName) {
		this.stageName=stageName;
		this.requestNumber=0;
		this.stageManager = null;
		//this.exception = false;
		//this.exceptionTime = 0;
	//	this.extension = 0;
		this.setExtensionTime(0);
		this.setExtensionReason("");
	}
	
	/**
	 * Instantiates a new abstract stage.
	 *
	 * @param requestNumber the request number
	 * @param startDate the start date
	 * @param dueDate the due date
	 * @param extension the extension
	 * @param extensionTime the extension time
	 * @param extensionReason the extension reason
	 * @param IsDone the is done
	 * @param IsLate the is late
	 */
	public AbstractStage(int requestNumber,String startDate,String dueDate,
			boolean extension,int extensionTime,String extensionReason,int IsDone,int IsLate) {
		this.requestNumber=requestNumber;
		//this.stageManager = stageManager;
		this.setStartDate(startDate);
		this.setDueDate(dueDate);
		//this.exception = exception;
		//this.exceptionTime = exceptionTime;
		this.extension = extension;
		this.setExtensionTime(extensionTime);
		this.setExtensionReason(extensionReason);
		//this.stageName=stageName;
		this.IsDone=IsDone;
		this.IsLate=IsLate;
	}

	/**
	 * Gets the stage name.
	 *
	 * @return the stage name
	 */
	public String getStageName() {
		return stageName;
	}
	
	/**
	 * Sets the stage name.
	 *
	 * @param stageName the new stage name
	 */
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	
	/**
	 * Gets the request number.
	 *
	 * @return the request number
	 */
	public int getRequestNumber() {
		return requestNumber;
	}

	/**
	 * Gets the stage manager.
	 *
	 * @return the stage manager
	 */
	public User getStageManager() {
		return stageManager;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		if(startDate!=null) {
					try {
			this.startDate=new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		} catch (ParseException e) 
		{
			e.printStackTrace();
		} 
		}
	//	this.startDate=startDate;
	}

	/**
	 * Gets the due date.
	 *
	 * @return the due date
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * Gets the due date string.
	 *
	 * @return the due date string
	 */
	public String getDueDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(getDueDate());
	return date;
	}
	
	
	/**
	 * Sets the due date.
	 *
	 * @param dueDate the new due date
	 */
	public void setDueDate(String dueDate) {
		if(dueDate!=null) {
			try {
				this.dueDate=new SimpleDateFormat("yyyy-MM-dd").parse(dueDate);
				} catch (ParseException e) 
			{
					e.printStackTrace();
			} 
		}
		//this.dueDate=dueDate;
	}

/*	public boolean isException() {
		return exception;
	}

	public int getExceptionTime() {
		return exceptionTime;
	}

	public boolean isExtension() {
		return extension;
	}*/
	

	/**
 * Gets the extension time.
 *
 * @return the extension time
 */
public int getExtensionTime() {
		return extensionTime;
	}

	/**
	 * Sets the extension time.
	 *
	 * @param extensionTime the new extension time
	 */
	public void setExtensionTime(int extensionTime) {
		this.extensionTime = extensionTime;
	}

	/**
	 * Gets the extension reason.
	 *
	 * @return the extension reason
	 */
	public String getExtensionReason() {
		return extensionReason;
	}

	/**
	 * Sets the extension reason.
	 *
	 * @param extensionReason the new extension reason
	 */
	public void setExtensionReason(String extensionReason) {
		this.extensionReason = extensionReason;
	}
		
		/**
		 * Sets the request number.
		 *
		 * @param requestNumber the new request number
		 */
		public void setRequestNumber(int requestNumber) {
		this.requestNumber = requestNumber;
	}

	/**
	 * Sets the stage manager.
	 *
	 * @param stageManager the new stage manager
	 */
	public void setStageManager(User stageManager) {
		this.stageManager = stageManager;
	}

	/*public void setException(boolean exception) {
		this.exception = exception;
	}

	public void setExceptionTime(int exceptionTime) {
		this.exceptionTime = exceptionTime;
	}*/

	/*public void setExtension(boolean extension) {
		this.extension = extension;
	}*/
	/**
	 * Sets the assessment report.
	 *
	 * @param assessmentReport the new assessment report
	 */
	//the functions of the sons
	public void setAssessmentReport(String assessmentReport) {}
	
	/**
	 * Sets the closing result.
	 *
	 * @param closingResult the new closing result
	 */
	public void setClosingResult(String closingResult) {}
	
	/**
	 * Sets the failure details.
	 *
	 * @param failureDetails the new failure details
	 */
	public void setFailureDetails(String failureDetails) {}
	
	/**
	 * Sets the examination result.
	 *
	 * @param examinationResult the new examination result
	 */
	public void setExaminationResult(String examinationResult) {}
	
	/**
	 * Sets the change done.
	 *
	 * @param changeDone the new change done
	 */
	public void setChangeDone(String changeDone) {}
	
	/**
	 * Sets the decision.
	 *
	 * @param decision the new decision
	 */
	public void setDecision(String decision) {}
	
	/**
	 * Sets the extra details.
	 *
	 * @param extraDetails the new extra details
	 */
	public void setExtraDetails(String extraDetails) {}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	//public String getAssessmentReport() {}
	@Override
	public String toString() {
		return "AbstractStage [requestNumber=" + requestNumber + ", startDate=" + startDate + ", dueDate=" + dueDate
				+ ", stageName=" + stageName 
				+ ", extension=" + extension + ", extensionTime=" + extensionTime + ", extensionReason="
				+ extensionReason + "]";
	}

	/**
	 * Gets the extension.
	 *
	 * @return the extension
	 */
	public boolean getExtension() {
		return extension;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Sets the due date.
	 *
	 * @param dueDate the new due date
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * Checks if is is done.
	 *
	 * @return the int
	 */
	public int isIsDone() {
		return IsDone;
	}
	
	/**
	 * Sets the checks if is done.
	 *
	 * @param isDone the new checks if is done
	 */
	public void setIsDone(int isDone) {
		IsDone = isDone;
	}
	
	/**
	 * Checks if is is late.
	 *
	 * @return the int
	 */
	public int isIsLate() {
		return IsLate;
	}
	
	/**
	 * Sets the checks if is late.
	 *
	 * @param isLate the new checks if is late
	 */
	public void setIsLate(int isLate) {
		IsLate = isLate;
	}
	
	/**
	 * Sets the extension.
	 *
	 * @param extension the new extension
	 */
	public void setExtension(boolean extension) {
		this.extension = extension;
	}

	
	
/*	
	public void setNewDueDate(String dueDate) {
		this.dueDate=dueDate;
	}*/
	
	
	/**
 * Gets the extension date.
 *
 * @return the extension date
 */
//inbar
	public Date getExtensionDate() {
		return extensionDate;
	}

	/**
	 * Gets the extension date string.
	 *
	 * @return the extension date string
	 */
	public String getExtensionDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(getExtensionDate());
	return date;
	}	
	
	/**
	 * Sets the extension date.
	 *
	 * @param extensionDate the new extension date
	 */
	public void setExtensionDate(String extensionDate) {
		if(extensionDate!=null) {
			try {
				this.extensionDate=new SimpleDateFormat("yyyy-MM-dd").parse(extensionDate);
				} catch (ParseException e) 
			{
					e.printStackTrace();
			} 
		}
	}

}
