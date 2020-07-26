package observableEntities;

import javafx.beans.property.SimpleStringProperty;

/**
 * A user entity to show in javafx TableView
 *
 */
public class ObservableUsers {

		/** The Id number. */
		public SimpleStringProperty IdNumber;
		
		/** The User name. */
		public SimpleStringProperty UserName;
		
		/** The Mail. */
		public SimpleStringProperty Mail;
		
		/** The User type. */
		public SimpleStringProperty UserType;
		
		/** The Department name. */
		public SimpleStringProperty DepartmentName;
		
		/**
		 * Instantiates a new observable users.
		 *
		 * @param IdNumber the id number
		 * @param UserName the user name
		 * @param Mail the mail
		 * @param UserType the user type
		 * @param DepartmentName the department name
		 */
		public ObservableUsers (String IdNumber, String UserName,String Mail,String UserType, String  DepartmentName )
		{
			this.IdNumber=new SimpleStringProperty(IdNumber);
			this.UserName=new SimpleStringProperty(UserName);
			this.Mail=new SimpleStringProperty(Mail);
			this.UserType=new SimpleStringProperty(UserType);
			this.DepartmentName=new SimpleStringProperty(DepartmentName); 
		//	this.LoginStatus= new SimpleStringProperty(LoginStatus);			
		}

		/**
		 * Gets the id number.
		 *
		 * @return the id number
		 */
		public String getIdNumber() {
			return IdNumber.get();
		}

		/**
		 * Sets the id number.
		 *
		 * @param idNumber the new id number
		 */
		public void setIdNumber(SimpleStringProperty idNumber) {
			IdNumber = idNumber;
		}

		/**
		 * Gets the user name.
		 *
		 * @return the user name
		 */
		public String getUserName() {
			return UserName.get();
		}

		/**
		 * Sets the user name.
		 *
		 * @param userName the new user name
		 */
		public void setUserName(SimpleStringProperty userName) {
			UserName = userName;
		}

		/**
		 * Gets the mail.
		 *
		 * @return the mail
		 */
		public String getMail() {
			return Mail.get();
		}


		/**
		 * Sets the mail.
		 *
		 * @param mail the new mail
		 */
		public void setMail(SimpleStringProperty mail) {
			Mail = mail;
		}

		/**
		 * Gets the user type.
		 *
		 * @return the user type
		 */
		public String getUserType() {
			return UserType.get();
		}

		/**
		 * Sets the user type.
		 *
		 * @param userType the new user type
		 */
		public void setUserType(SimpleStringProperty userType) {
			UserType = userType;
		}

		/**
		 * Gets the department name.
		 *
		 * @return the department name
		 */
		public String getDepartmentName() {
			return DepartmentName.get();
		}

		/**
		 * Sets the department name.
		 *
		 * @param departmentName the new department name
		 */
		public void setDepartmentName(SimpleStringProperty departmentName) {
			DepartmentName = departmentName;
		}
		
		

}
