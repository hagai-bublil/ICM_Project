package entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Request.
 */
@SuppressWarnings("serial")
public class Request implements Serializable{
	
	/** The request number. */
	private int requestNumber;
	
	/** The applicant name. */
	private String applicantName;
	
	/** The applicant id. */
	private String applicantId;
	
	/** The system name. */
	private String systemName;
	
	/** The description of existing situation. */
	private String descriptionOfExistingSituation;
	
	/** The reasons. */
	private String requestDescription,reasons;
	
	/** The comments. */
	private String comments;
	
	/** The Status. */
	private String Status;//string
	
	/** The submission date. */
	private Date submissionDate;
	
	/** The supervisor. */
	//private String submissionDate;
	private User supervisor;
	
	/** The chairman. */
	private User chairman;
	
	/** The commissioner 1. */
	private User commissioner1;
	
	/** The commissioner 2. */
	private User commissioner2;
	
	/** The assessment. */
	private Assessment assessment;
	
	/** The review assessment. */
	private ReviewAssessment reviewAssessment;
	
	/** The execution. */
	private Execution execution;
	
	/** The examination. */
	private Examination examination;
	
	/** The closing. */
	private Closing closing;

	/**
	 * Instantiates a new request.
	 *
	 * @param requestNumber the request number
	 * @param applicantName the applicant name
	 * @param applicantId the applicant id
	 * @param systemName the system name
	 * @param descriptionOfExistingSituation the description of existing situation
	 * @param requestDescription the request description
	 * @param submissionDate the submission date
	 * @param comments the comments
	 * @param reasons the reasons
	 */
	/*public Request(int requestNumber,String applicantName,String applicantId,String systemName,String descriptionOfExistingSituation,String requestDescription,ItEmployee supervisor,String submissionDate,String comments) {
		this.requestNumber=requestNumber;
		this.applicantName=applicantName;
		this.applicantId=applicantId;
		this.systemName=systemName;
		this.descriptionOfExistingSituation=descriptionOfExistingSituation;
		this.requestDescription=requestDescription;
		this.setSubmissionDate(submissionDate);
		//this.setStatus(assessment);
		this.setSupervisor(supervisor);
		this.comments=comments;
		
	}*/
	public Request(int requestNumber,String applicantName,String applicantId,String systemName,String descriptionOfExistingSituation,String requestDescription,Date submissionDate,String comments, String reasons) {
		this.requestNumber=requestNumber;
		this.applicantName=applicantName;
		this.applicantId=applicantId;
		this.systemName=systemName;
		this.descriptionOfExistingSituation=descriptionOfExistingSituation;
		this.requestDescription=requestDescription;
		this.submissionDate=submissionDate;
		//this.setStatus(assessment);

		this.comments=comments;
		this.reasons=reasons;
	}
	
	/**
	 * Instantiates a new request.
	 *
	 * @param requestNumber the request number
	 * @param applicantName the applicant name
	 * @param applicantId the applicant id
	 * @param systemName the system name
	 * @param descriptionOfExistingSituation the description of existing situation
	 * @param requestDescription the request description
	 * @param submissionDate the submission date
	 * @param comments the comments
	 * @param reasons the reasons
	 * @param supervisor the supervisor
	 * @param chairman the chairman
	 * @param commissioner1 the commissioner 1
	 * @param commissioner2 the commissioner 2
	 */
	public Request(int requestNumber,String applicantName,String applicantId,String systemName,String descriptionOfExistingSituation,String requestDescription,Date submissionDate,String comments, String reasons, User supervisor, User chairman, User commissioner1, User commissioner2) {
		this.requestNumber=requestNumber;
		this.applicantName=applicantName;
		this.applicantId=applicantId;
		this.systemName=systemName;
		this.descriptionOfExistingSituation=descriptionOfExistingSituation;
		this.requestDescription=requestDescription;
		this.submissionDate=submissionDate;
		//this.setStatus(assessment);
		this.supervisor=supervisor;
		this.chairman=chairman;
		this.commissioner1=commissioner1;
		this.commissioner2=commissioner2;
		this.comments=comments;
		this.reasons=reasons;
	}
	
	/**
	 * Instantiates a new request.
	 *
	 * @param supervisor the supervisor
	 * @param chairman the chairman
	 * @param commissioner1 the commissioner 1
	 * @param commissioner2 the commissioner 2
	 */
	public Request(User supervisor, User chairman, User commissioner1, User commissioner2) {
		this.supervisor=supervisor;
		this.chairman=chairman;
		this.commissioner1=commissioner1;
		this.commissioner2=commissioner2;
	}
	
	/**
	 * Instantiates a new request.
	 */
	public Request() {//initial empty request
		this.requestNumber=0;
		this.applicantName="";
		this.applicantId="";
		this.systemName="";
		this.descriptionOfExistingSituation="";
		this.requestDescription="";
	//	this.setStatus(null);
		this.setSupervisor(null);
	}

	/**
	 * Gets the assessment.
	 *
	 * @return the assessment
	 */
	public Assessment getAssessment() {
		return assessment;
	}
	
	/**
	 * Sets the assessment.
	 *
	 * @param assessment the new assessment
	 */
	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
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
	 * Sets the applicant name.
	 *
	 * @param applicantName the new applicant name
	 */
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	/**
	 * Sets the applicant id.
	 *
	 * @param applicantId the new applicant id
	 */
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	
	/**
	 * Sets the system name.
	 *
	 * @param systemName the new system name
	 */
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	/**
	 * Sets the description of existing situation.
	 *
	 * @param descriptionOfExistingSituation the new description of existing situation
	 */
	public void setDescriptionOfExistingSituation(String descriptionOfExistingSituation) {
		this.descriptionOfExistingSituation = descriptionOfExistingSituation;
	}
	
	/**
	 * Sets the request description.
	 *
	 * @param requestDescription the new request description
	 */
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	
	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}


	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return Status;
	}


	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.Status = status;
	}

	/**
	 * Gets the submission date string.
	 *
	 * @return the submission date string
	 */
	public String getSubmissionDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(getSubmissionDate());
	return date;
	}	
	
	/**
	 * Gets the submission date.
	 *
	 * @return the submission date
	 */
	public Date getSubmissionDate() {
		//System.out.println("get"+submissionDate.toString());
		return this.submissionDate;
	}
	
	/**
	 * Sets the submission date.
	 *
	 * @param submissionDate the new submission date
	 */
	@SuppressWarnings("deprecation")
	public void setSubmissionDate(String submissionDate) {
		if(submissionDate!=null)
		{
		try {
			this.submissionDate=new SimpleDateFormat("yyyy-MM-dd").parse(submissionDate);
			//System.out.println("set in "+submissionDate.toString());
			//System.out.println("set out"+this.submissionDate.toLocaleString().substring(10));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			this.submissionDate=null;
		}
		//this.submissionDate=submissionDate;
	}


	/**
	 * Gets the execution.
	 *
	 * @return the execution
	 */
	public Execution getExecution() {
		return execution;
	}


	/**
	 * Sets the execution.
	 *
	 * @param execution the new execution
	 */
	public void setExecution(Execution execution) {
		this.execution = execution;
	}


	/**
	 * Gets the examination.
	 *
	 * @return the examination
	 */
	public Examination getExamination() {
		return examination;
	}


	/**
	 * Sets the examination.
	 *
	 * @param examination the new examination
	 */
	public void setExamination(Examination examination) {
		this.examination = examination;
	}


	/**
	 * Gets the closing.
	 *
	 * @return the closing
	 */
	public Closing getClosing() {
		return closing;
	}


	/**
	 * Sets the closing.
	 *
	 * @param closing the new closing
	 */
	public void setClosing(Closing closing) {
		this.closing = closing;
	}


	/**
	 * Gets the supervisor.
	 *
	 * @return the supervisor
	 */
	public User getSupervisor() {
		return supervisor;
	}


	/**
	 * Sets the supervisor.
	 *
	 * @param supervisor the new supervisor
	 */
	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}


	/**
	 * Gets the chairman.
	 *
	 * @return the chairman
	 */
	public User getChairman() {
		return chairman;
	}


	/**
	 * Sets the chairman.
	 *
	 * @param chairman the new chairman
	 */
	public void setChairman(User chairman) {
		this.chairman = chairman;
	}


	/**
	 * Gets the commissioner 1.
	 *
	 * @return the commissioner 1
	 */
	public User getCommissioner1() {
		return commissioner1;
	}


	/**
	 * Sets the commissioner 1.
	 *
	 * @param commissioner1 the new commissioner 1
	 */
	public void setCommissioner1(User commissioner1) {
		this.commissioner1 = commissioner1;
	}


	/**
	 * Gets the commissioner 2.
	 *
	 * @return the commissioner 2
	 */
	public User getCommissioner2() {
		return commissioner2;
	}


	/**
	 * Sets the commissioner 2.
	 *
	 * @param commissioner2 the new commissioner 2
	 */
	public void setCommissioner2(User commissioner2) {
		this.commissioner2 = commissioner2;
	}


	/**
	 * Gets the review assessment.
	 *
	 * @return the review assessment
	 */
	public ReviewAssessment getReviewAssessment() {
		return reviewAssessment;
	}


	/**
	 * Sets the review assessment.
	 *
	 * @param reviewAssessment the new review assessment
	 */
	public void setReviewAssessment(ReviewAssessment reviewAssessment) {
		this.reviewAssessment = reviewAssessment;
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
	 * Gets the applicant name.
	 *
	 * @return the applicant name
	 */
	public String getApplicantName() {
		return applicantName;
	}


	/**
	 * Gets the applicant id.
	 *
	 * @return the applicant id
	 */
	public String getApplicantId() {
		return applicantId;
	}


	/**
	 * Gets the system name.
	 *
	 * @return the system name
	 */
	public String getSystemName() {
		return systemName;
	}


	/**
	 * Gets the description of existing situation.
	 *
	 * @return the description of existing situation
	 */
	public String getDescriptionOfExistingSituation() {
		return descriptionOfExistingSituation;
	}


	/**
	 * Gets the request description.
	 *
	 * @return the request description
	 */
	public String getRequestDescription() {
		return requestDescription;
	}
	
	/**
	 * Gets the reasons.
	 *
	 * @return the reasons
	 */
	public String getReasons() {
		return reasons;
	}
	
	/**
	 * Sets the reasons.
	 *
	 * @param reasons the new reasons
	 */
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Request [requestNumber=" + requestNumber + ", applicantName=" + applicantName + ", applicantId="
				+ applicantId + ", systemName=" + systemName + ", descriptionOfExistingSituation="
				+ descriptionOfExistingSituation + ", requestDescription=" + requestDescription + ", reasons=" + reasons
				+ ", comments=" + comments + ", Status=" + Status + ", submissionDate=" + submissionDate + "]";
	}
	
	/**
	 * Gets the request string number.
	 *
	 * @return the request string number
	 */
	public String getRequestStringNumber() {
		return String.valueOf(requestNumber);
	}
	

}



