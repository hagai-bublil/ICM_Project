package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The User entity represents a user in the system
 *
 */
public class User implements Serializable
{
	
	/** The id request. */
	private int idRequest;
	
	/** The user name. */
	private String userName;
	
	/** The password. */
	private String password;
	
	/** The id number. */
	private String idNumber;
	
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;	
	
	/** The user type. */
	private String userType;
	
	/** The login status. */
	private String loginStatus;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The mail. */
	private String mail;
	
	/** The department name. */
	private String departmentName;
	
	/** The available. */
	private boolean available=true;
	
	/** The role. */
	private String role;
	
	/** The messages. */
	private List<ICMMessage> messages;
	
	/** The requests. */
	private List<Request> requests;
	
	/** The supervisor requests. */
	private List<Request> supervisorRequests;
	
	/**
	 * Gets the id request.
	 *
	 * @return the id request
	 */
	public int getIdRequest() {
		return idRequest;
	}
	
	/**
	 * Sets the id request.
	 *
	 * @param idRequest the new id request
	 */
	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}
	
	/** The assessor requests. */
	private List<Request> assessorRequests;
	
	/** The review requests. */
	private List<Request> reviewRequests;
	
	/** The executor requests. */
	private List<Request> executorRequests;
	
	/** The examiner requests. */
	private List<Request> examinerRequests;
	
	/** The web site. */
	private boolean moodle,informationStation,library,classroom,lab,webSite;
	
	/**
	 * Instantiates a new user.
	 */
	public User() //for checking user exist
	{
		this.userName="not found";
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param idNumber the id number
	 * @param idRequest the id request
	 */
	public User(String idNumber, int idRequest) //for checking user exist
	{
		this.idNumber=idNumber;
		this.idRequest=idRequest;
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param userName the user name
	 * @param password the password
	 */
	public User(String userName, String password) //for checking user exist
	{
		this.userName=userName;
		this.password=password;
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param idNumber the id number
	 */
	public User(String idNumber) //for checking user exist
	{
		this.idNumber = idNumber;
	}
	
	/**
	 * Another constructor of the class uses. It is used when retrieving all the user details from the database
	 *
	 * @param idNumber the id number
	 * @param userName the user name
	 * @param password the password
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param userType the user type
	 * @param loginStatus the login status
	 * @param phoneNumber the phone number
	 * @param mail the mail
	 * @param departmentName the department name
	 * @param moodle the moodle
	 * @param informationStation the information station
	 * @param library the library
	 * @param classroom the classroom
	 * @param lab the lab
	 * @param webSite the web site
	 */
	//this constructor is for itEmployye
	public User(String idNumber, String userName, String password, String firstName, String lastName, String userType,
			String loginStatus, String phoneNumber, String mail, String departmentName,
			boolean moodle,boolean informationStation,boolean library,boolean classroom,boolean lab,boolean webSite) 
	{
		this.userName = userName;
		this.password = password;
		this.idNumber = idNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.loginStatus = loginStatus;
		this.phoneNumber=phoneNumber;
		this.mail=mail;
		this.departmentName=departmentName;
		this.moodle=moodle;
		this.informationStation=informationStation;
		this.library=library;
		this.classroom=classroom;
		this.lab=lab;
		this.webSite=webSite;
	
	}
	
	/**
	 * Instantiates a new user.
	 *
	 * @param idNumber the id number
	 * @param userName the user name
	 * @param password the password
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param userType the user type
	 * @param loginStatus the login status
	 * @param phoneNumber the phone number
	 * @param mail the mail
	 * @param departmentName the department name
	 */
	//this constructor is for regular user
	public User(String idNumber, String userName, String password, String firstName, String lastName, String userType,
			String loginStatus, String phoneNumber, String mail, String departmentName) 
	{
		this.userName = userName;
		this.password = password;
		this.idNumber = idNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.loginStatus = loginStatus;
		this.phoneNumber=phoneNumber;
		this.mail=mail;
		this.departmentName=departmentName;
	
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType()
	{
		return userType;
	}
	
	/**
	 * Gets the login status.
	 *
	 * @return the login status
	 */
	public String getLoginStatus()
	{
		return loginStatus;
	}
	
	/**
	 * Sets the login status.
	 *
	 * @param loginStatus the new login status
	 */
	public void setLoginStatus(String loginStatus)
	{
		this.loginStatus = loginStatus;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId()
	{
		return idNumber;
	}
	
	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public List<ICMMessage> getMessages()
	{
		return messages;
	}

	/**
	 * Sets the messages.
	 *
	 * @param msgs the new messages
	 */
	public void setMessages(List<ICMMessage> msgs)
	{
		this.messages = msgs;
	}
	
	/**
	 * Gets the requests.
	 *
	 * @return the requests
	 */
	public List<Request> getRequests()
	{
		return requests;
	}
	
	/**
	 * Sets the requests.
	 *
	 * @param requests the new requests
	 */
	public void setRequests(List<Request> requests)
	{
		this.requests = requests;
	}
	
	/**
	 * Gets the id number.
	 *
	 * @return the id number
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * Sets the id number.
	 *
	 * @param idNumber the new id number
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * Gets the user type.
	 *
	 * @return the user type
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * Sets the user type.
	 *
	 * @param userType the new user type
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the department name.
	 *
	 * @return the department name
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * Sets the department name.
	 *
	 * @param departmentName the new department name
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * Checks if is moodle.
	 *
	 * @return true, if is moodle
	 */
	public boolean isMoodle() {
		return moodle;
	}

	/**
	 * Sets the moodle.
	 *
	 * @param moodle the new moodle
	 */
	public void setMoodle(boolean moodle) {
		this.moodle = moodle;
	}

	/**
	 * Checks if is information station.
	 *
	 * @return true, if is information station
	 */
	public boolean isInformationStation() {
		return informationStation;
	}

	/**
	 * Sets the information station.
	 *
	 * @param informationStation the new information station
	 */
	public void setInformationStation(boolean informationStation) {
		this.informationStation = informationStation;
	}

	/**
	 * Checks if is library.
	 *
	 * @return true, if is library
	 */
	public boolean isLibrary() {
		return library;
	}

	/**
	 * Sets the library.
	 *
	 * @param library the new library
	 */
	public void setLibrary(boolean library) {
		this.library = library;
	}

	/**
	 * Checks if is classroom.
	 *
	 * @return true, if is classroom
	 */
	public boolean isClassroom() {
		return classroom;
	}

	/**
	 * Sets the classroom.
	 *
	 * @param classroom the new classroom
	 */
	public void setClassroom(boolean classroom) {
		this.classroom = classroom;
	}

	/**
	 * Checks if is lab.
	 *
	 * @return true, if is lab
	 */
	public boolean isLab() {
		return lab;
	}

	/**
	 * Sets the lab.
	 *
	 * @param lab the new lab
	 */
	public void setLab(boolean lab) {
		this.lab = lab;
	}

	/**
	 * Checks if is web site.
	 *
	 * @return true, if is web site
	 */
	public boolean isWebSite() {
		return webSite;
	}

	/**
	 * Sets the web site.
	 *
	 * @param webSite the new web site
	 */
	public void setWebSite(boolean webSite) {
		this.webSite = webSite;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Gets the supervisor requests.
	 *
	 * @return the supervisor requests
	 */
	public List<Request> getSupervisorRequests() {
		return supervisorRequests;
	}

	/**
	 * Sets the supervisor requests.
	 *
	 * @param supervisorRequests the new supervisor requests
	 */
	public void setSupervisorRequests(List<Request> supervisorRequests) {
		this.supervisorRequests = supervisorRequests;
	}

	/**
	 * Gets the assessor requests.
	 *
	 * @return the assessor requests
	 */
	public List<Request> getAssessorRequests() {
		return assessorRequests;
	}

	/**
	 * Sets the assessor requests.
	 *
	 * @param assessorRequests the new assessor requests
	 */
	public void setAssessorRequests(List<Request> assessorRequests) {
		this.assessorRequests = assessorRequests;
	}

	/**
	 * Gets the review requests.
	 *
	 * @return the review requests
	 */
	public List<Request> getReviewRequests() {
		return reviewRequests;
	}

	/**
	 * Sets the review requests.
	 *
	 * @param reviewRequests the new review requests
	 */
	public void setReviewRequests(List<Request> reviewRequests) {
		this.reviewRequests = reviewRequests;
	}

	/**
	 * Gets the executor requests.
	 *
	 * @return the executor requests
	 */
	public List<Request> getExecutorRequests() {
		return executorRequests;
	}

	/**
	 * Sets the executor requests.
	 *
	 * @param executorRequests the new executor requests
	 */
	public void setExecutorRequests(List<Request> executorRequests) {
		this.executorRequests = executorRequests;
	}

	/**
	 * Gets the examiner requests.
	 *
	 * @return the examiner requests
	 */
	public List<Request> getExaminerRequests() {
		return examinerRequests;
	}

	/**
	 * Sets the examiner requests.
	 *
	 * @param examinerRequests the new examiner requests
	 */
	public void setExaminerRequests(List<Request> examinerRequests) {
		this.examinerRequests = examinerRequests;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", idNumber=" + idNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", userType=" + userType + ", loginStatus=" + loginStatus
				+ ", phoneNumber=" + phoneNumber + ", mail=" + mail + ", departmentName=" + departmentName + ", moodle="
				+ moodle + ", informationStation=" + informationStation + ", library=" + library + ", classroom="
				+ classroom + ", lab=" + lab + ", webSite=" + webSite + "]";
	}
	
	/**
	 * Checks if is available.
	 *
	 * @return true, if is available
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/**
	 * Sets the available.
	 *
	 * @param available the new available
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	
	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	
}
