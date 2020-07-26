package entities;

/**
 * The Execution entity represents a row in the execution table in the data base
 *
 */
public class Execution extends AbstractStage{

	/** The change done. */
	private boolean changeDone;
	
	/** The supervisor due date approve. */
	private boolean supervisorDueDateApprove;

/**
 * Instantiates a new execution.
 *
 * @param requestNumber the request number
 * @param startDate the start date
 * @param dueDate the due date
 * @param extension the extension
 * @param extensionTime the extension time
 * @param extensionReason the extension reason
 * @param changeDone the change done
 * @param supervisorDueDateApprov the supervisor due date approv
 * @param IsDone the is done
 * @param IsLate the is late
 */
/*	public Execution(int requestNumber, ItEmployee stageManager, String startDate, String dueDate, boolean exception,
			int exceptionTime, boolean extension, int extensionTime, String extensionReason) {
		super(requestNumber, stageManager, startDate, dueDate, exception, exceptionTime, extension, extensionTime,
				extensionReason,"execution");
	}*/
	public Execution(int requestNumber,
			String startDate,String dueDate,
			boolean extension,int extensionTime,String extensionReason,boolean changeDone,boolean supervisorDueDateApprov,int IsDone,int IsLate) {
		super(requestNumber, startDate, dueDate, extension, extensionTime,
				extensionReason, IsDone, IsLate);
		this.changeDone=changeDone;
		setSupervisorDueDateApprove(supervisorDueDateApprov);
	}
	
	/**
	 * Instantiates a new execution.
	 *
	 * @param stageName the stage name
	 */
	public Execution(String stageName) {
		super(stageName);
	}
	
	/**
	 * Checks if is change done.
	 *
	 * @return true, if is change done
	 */
	public boolean isChangeDone() {
		return changeDone;
	}
	
	/**
	 * Sets the change done.
	 *
	 * @param changeDone the new change done
	 */
	public void setChangeDone(boolean changeDone) {
		this.changeDone = changeDone;
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
