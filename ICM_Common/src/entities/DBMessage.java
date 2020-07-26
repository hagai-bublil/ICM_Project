package entities;

import java.io.Serializable;

/**
 * This class describes the message object sent from the client to the server
 */
public class DBMessage implements Serializable
{
	
	/** The Action. */
	public DBAction Action;
	
	/** The Data. */
	public Object Data;
	
	/**
	 * The constructor of the message.
	 *
	 * @param action - enum of actions - how to classify the operation.
	 * @param data - an object of data, if no data is needed - send null.
	 */
	public DBMessage(DBAction action,Object data)
	{
		this.Action = action;
		this.Data = data;
	}
	
	/**
	 * The Enum DBAction.
	 */
	public enum DBAction
	{
		
		/** The Check user. */
		CheckUser, 
 /** The Create new request. */
 CreateNewRequest, 
 /** The is DB runing. */
 isDBRuning, 
 /** The Shut down. */
 ShutDown,
/** The Delays request report. */
DelaysRequestReport, 
 /** The Check request number. */
 CheckRequestNumber,
/** The Check my requestt. */
CheckMyRequestt,
		
		/** The get requests list for supervisor. */
		getRequestsListForSupervisor, 
 /** The Files. */
 Files, 
 /** The Find max num of id request. */
 FindMaxNumOfIdRequest, 
 /** The Find max num of id msg. */
 FindMaxNumOfIdMsg, 
 /** The New request msg spvsr. */
 NewRequestMsgSpvsr, 
		
		/** The Update user logout. */
		UpdateUserLogout,
/** The get notifications for user. */
getNotificationsForUser,
/** The remove msg. */
removeMsg,
/** The get user information. */
getUserInformation,
/** The Open file. */
OpenFile,
/** The get requests list for assessor. */
getRequestsListForAssessor,
		
		/** The set assessment due date. */
		setAssessmentDueDate,
/** The set report. */
setReport,
/** The set extention request. */
setExtentionRequest, 
 /** The get requests list for executor. */
 getRequestsListForExecutor, 
 /** The get requests list for employee. */
 getRequestsListForEmployee, 
		
		/** The set due date for execute stage. */
		setDueDateForExecuteStage, 
 /** The update stage completion. */
 updateStageCompletion,
/** The get requests list for review assessment. */
getRequestsListForReviewAssessment,
/** The set the reject. */
setTheReject,
/** The set the review details. */
setTheReviewDetails,
/** The set the review completion. */
setTheReviewCompletion,
/** The get employees for combo box. */
getEmployeesForComboBox,
		
		/** The update appointment. */
		updateAppointment,
/** The create assesment. */
createAssesment,
/** The getemployees list forassessor. */
getemployeesListForassessor,
/** The getemployees list forassessor combo box. */
getemployeesListForassessorComboBox,
		
		/** The check if appoitment was made. */
		checkIfAppoitmentWasMade,
/** The update user system maintenance appointment. */
updateUserSystemMaintenanceAppointment,
/** The Activity. */
Activity ,
/** The Delays. */
Delays,
/** The get all employees. */
getAllEmployees, 
 /** The update user system maintenance. */
 updateUserSystemMaintenance, 
		
		/** The get requests list for examiner. */
		getRequestsListForExaminer, 
 /** The return to executor stage. */
 returnToExecutorStage,
/** The creates assessment and executionstage. */
createsAssessmentAndExecutionstage,
/** The update status in DB. */
updateStatusInDB,
/** The get due date. */
getDueDate,
/** The set due date for stage. */
setDueDateForStage,
		
		/** The get names of employees. */
		getNamesOfEmployees,
/** The aprrove extention. */
/*getExtentionForSupervisor,*/aprroveExtention,
/** The reject extention. */
rejectExtention,
/** The suspend request. */
suspendRequest, 
 /** The closing request. */
 closingRequest,
/** The unsuspend request. */
unsuspendRequest,

	}

}
