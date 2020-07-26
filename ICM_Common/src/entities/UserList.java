package entities;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class UserList.
 */
public class UserList implements Serializable{

/** The user list. */
public List<User> user_list;

/**
 * Instantiates a new user list.
 *
 * @param user_list the user list
 */
public UserList(List<User> user_list) {
	//super();
	this.user_list = user_list;
}

/**
 * Instantiates a new user list.
 */
public UserList() {
	// TODO Auto-generated constructor stub
}



}
