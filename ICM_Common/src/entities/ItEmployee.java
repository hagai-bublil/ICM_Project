package entities;

// TODO: Auto-generated Javadoc
/**
 * The Class ItEmployee.
 */
public class ItEmployee extends User{

	/** The web site. */
	private boolean moodle,informationStation,library,classroom,lab,webSite;
	
	/**
	 * Instantiates a new it employee.
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
	public ItEmployee(String idNumber, String userName, String password, String firstName, String lastName,
			String userType, String loginStatus, String phoneNumber, String mail, String departmentName,boolean moodle,boolean informationStation,boolean library,boolean classroom, boolean lab,boolean webSite) {

		super(idNumber, userName, password, firstName, lastName, userType, loginStatus, phoneNumber, mail, departmentName);
		this.moodle=moodle;
		this.informationStation=informationStation;
		this.library=library;
		this.classroom=classroom;
		this.lab=lab;
		this.webSite=webSite;	
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
	 * Checks if is information station.
	 *
	 * @return true, if is information station
	 */
	public boolean isInformationStation() {
		return informationStation;
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
	 * Checks if is classroom.
	 *
	 * @return true, if is classroom
	 */
	public boolean isClassroom() {
		return classroom;
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
	 * Checks if is web site.
	 *
	 * @return true, if is web site
	 */
	public boolean isWebSite() {
		return webSite;
	}	
}
