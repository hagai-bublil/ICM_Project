package observableEntities;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

/**
 * A request entity to show in javafx TableView.
 */
public class ObservableRequest
{
	
	/** The request number. */
	public SimpleIntegerProperty requestNumber;
	
	/** The applicant name. */
	public SimpleStringProperty applicantName;
	
	/** The system name. */
	public SimpleStringProperty systemName;
	
	/** The description of existing situation. */
	public SimpleStringProperty descriptionOfExistingSituation;
	
	/** The request description. */
	public SimpleStringProperty requestDescription;
	
	/** The status. */
	public SimpleStringProperty status;
	
	/** The stage manager. */
	public SimpleStringProperty stageManager;
	
	/** The chairman. */
	public SimpleStringProperty chairman;
	
	/** The committee member 1. */
	public SimpleStringProperty committeeMember1;
	
	/** The committee member 2. */
	public SimpleStringProperty committeeMember2;
	
	/** The date of submission. */
	public SimpleStringProperty dateOfSubmission;
	
	/** The due date. */
	public SimpleStringProperty dueDate;
		
	/** The view button. */
	private Button viewButton;
	
	/** The view request button. */
	private Button viewRequestButton;
	
	/**
	 * Instantiates a new observable request.
	 *
	 * @param requestNumber the request number
	 * @param applicantName the applicant name
	 * @param systemName the system name
	 * @param descriptionOfExistingSituation the description of existing situation
	 * @param requestDescription the request description
	 * @param status the status
	 * @param stageManager the stage manager
	 * @param chairman the chairman
	 * @param committeeMember1 the committee member 1
	 * @param committeeMember2 the committee member 2
	 * @param submissionDate the submission date
	 */
	public ObservableRequest( int requestNumber, String applicantName, String systemName,String descriptionOfExistingSituation,
			String requestDescription,String status,String stageManager,String chairman,String committeeMember1,String committeeMember2,String submissionDate)//, String returndate)
	{
		this.requestNumber= new SimpleIntegerProperty(requestNumber);
		this.applicantName=new SimpleStringProperty(applicantName);
		this.systemName=new SimpleStringProperty(systemName);
		this.descriptionOfExistingSituation=new SimpleStringProperty(descriptionOfExistingSituation);
		this.requestDescription=new SimpleStringProperty(requestDescription);
		this.status=new SimpleStringProperty(status);
		this.stageManager=new SimpleStringProperty(stageManager);
		this.chairman=new SimpleStringProperty(chairman);
		this.committeeMember1=new SimpleStringProperty(committeeMember1);
		this.committeeMember2=new SimpleStringProperty(committeeMember2);
		this.dateOfSubmission=new SimpleStringProperty(submissionDate);
	}
	
	/**
	 * Instantiates a new observable request.
	 *
	 * @param requestNumber the request number
	 * @param submissionDate the submission date
	 * @param status the status
	 */
	public ObservableRequest( int requestNumber, String submissionDate, String status) {
		this.requestNumber= new SimpleIntegerProperty(requestNumber);
		this.dateOfSubmission=new SimpleStringProperty(submissionDate);
		this.status=new SimpleStringProperty(status);
		this.setViewButton(new Button("view"));
		//System.out.println("observ"+this.dateOfSubmission.toString());
	}

	/**
	 * Gets the request number.
	 *
	 * @return the request number
	 */
	public int getRequestNumber() {
		return requestNumber.get();
	}

	/**
	 * Sets the request number.
	 *
	 * @param requestNumber the new request number
	 */
	public void setRequestNumber(SimpleIntegerProperty requestNumber) {
		this.requestNumber = requestNumber;
	}

	/**
	 * Gets the applicant name.
	 *
	 * @return the applicant name
	 */
	public SimpleStringProperty getApplicantName() {
		return applicantName;
	}

	/**
	 * Sets the applicant name.
	 *
	 * @param applicantName the new applicant name
	 */
	public void setApplicantName(SimpleStringProperty applicantName) {
		this.applicantName = applicantName;
	}

	/**
	 * Gets the system name.
	 *
	 * @return the system name
	 */
	public SimpleStringProperty getSystemName() {
		return systemName;
	}

	/**
	 * Sets the system name.
	 *
	 * @param systemName the new system name
	 */
	public void setSystemName(SimpleStringProperty systemName) {
		this.systemName = systemName;
	}

	/**
	 * Gets the description of existing situation.
	 *
	 * @return the description of existing situation
	 */
	public SimpleStringProperty getDescriptionOfExistingSituation() {
		return descriptionOfExistingSituation;
	}

	/**
	 * Sets the description of existing situation.
	 *
	 * @param descriptionOfExistingSituation the new description of existing situation
	 */
	public void setDescriptionOfExistingSituation(SimpleStringProperty descriptionOfExistingSituation) {
		this.descriptionOfExistingSituation = descriptionOfExistingSituation;
	}

	/**
	 * Gets the request description.
	 *
	 * @return the request description
	 */
	public SimpleStringProperty getRequestDescription() {
		return requestDescription;
	}

	/**
	 * Sets the request description.
	 *
	 * @param requestDescription the new request description
	 */
	public void setRequestDescription(SimpleStringProperty requestDescription) {
		this.requestDescription = requestDescription;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status.get();
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(SimpleStringProperty status) {
		this.status = status;
	}

	/**
	 * Gets the stage manager.
	 *
	 * @return the stage manager
	 */
	public SimpleStringProperty getStageManager() {
		return stageManager;
	}

	/**
	 * Sets the stage manager.
	 *
	 * @param stageManager the new stage manager
	 */
	public void setStageManager(SimpleStringProperty stageManager) {
		this.stageManager = stageManager;
	}

	/**
	 * Gets the chairman.
	 *
	 * @return the chairman
	 */
	public SimpleStringProperty getChairman() {
		return chairman;
	}

	/**
	 * Sets the chairman.
	 *
	 * @param chairman the new chairman
	 */
	public void setChairman(SimpleStringProperty chairman) {
		this.chairman = chairman;
	}

	/**
	 * Gets the committee member 1.
	 *
	 * @return the committee member 1
	 */
	public SimpleStringProperty getCommitteeMember1() {
		return committeeMember1;
	}

	/**
	 * Sets the committee member 1.
	 *
	 * @param committeeMember1 the new committee member 1
	 */
	public void setCommitteeMember1(SimpleStringProperty committeeMember1) {
		this.committeeMember1 = committeeMember1;
	}

	/**
	 * Gets the committee member 2.
	 *
	 * @return the committee member 2
	 */
	public SimpleStringProperty getCommitteeMember2() {
		return committeeMember2;
	}

	/**
	 * Sets the committee member 2.
	 *
	 * @param committeeMember2 the new committee member 2
	 */
	public void setCommitteeMember2(SimpleStringProperty committeeMember2) {
		this.committeeMember2 = committeeMember2;
	}
	
	/**
	 * Gets the date of submission.
	 *
	 * @return the date of submission
	 */
	public String getDateOfSubmission() {
		return dateOfSubmission.get();
	}
	
	/**
	 * Sets the date of submission.
	 *
	 * @param dateOfSubmission the new date of submission
	 */
	public void setDateOfSubmission(SimpleStringProperty dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}
	
	
	
	/**
	 * Gets the due date.
	 *
	 * @return the due date
	 */
	public String getDueDate() {
		return dueDate.get();
	}
	
	/**
	 * Sets the due date.
	 *
	 * @param dueDate the new due date
	 */
	public void setDueDate(SimpleStringProperty dueDate) {
		this.dueDate = dueDate;
	}
	
	/**
	 * Gets the view button.
	 *
	 * @return the view button
	 */
	public Button getViewButton() {
		return viewButton;
	}
	
	/**
	 * Sets the view button.
	 *
	 * @param viewButton the new view button
	 */
	public void setViewButton(Button viewButton) {
		this.viewButton = viewButton;
	}
	
	/**
	 * Gets the view request button.
	 *
	 * @return the view request button
	 */
	public Button getViewRequestButton() {
		return viewRequestButton;
	}
	
	/**
	 * Sets the view request button.
	 *
	 * @param viewRequestButton the new view request button
	 */
	public void setViewRequestButton(Button viewRequestButton) {
		this.viewRequestButton = viewRequestButton;
	}
	
	/**
	 * Instantiates a new observable request.
	 *
	 * @param requestNumber the request number
	 * @param dueDate the due date
	 */
	public ObservableRequest(int requestNumber, String dueDate) {
		this.requestNumber= new SimpleIntegerProperty(requestNumber);
		this.dueDate=new SimpleStringProperty(dueDate);
		this.setViewButton(new Button("View"));
	}
	
	/**
	 * Instantiates a new observable request.
	 */
	public ObservableRequest() {
	
	}

	/**
	 * Observable request assessment report.
	 *
	 * @param requestNumber the request number
	 * @param dueDate the due date
	 */
	public void ObservableRequestAssessmentReport(int requestNumber, String dueDate) {
		this.requestNumber= new SimpleIntegerProperty(requestNumber);
		this.dueDate=new SimpleStringProperty(dueDate);
		this.setViewRequestButton(new Button("View"));
	}


}
