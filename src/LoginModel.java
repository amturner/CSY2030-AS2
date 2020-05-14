import java.util.*;
import java.io.*;

public class LoginModel {
	private User loggedInUser;
	
	// Getter Methods
	// Method for verifying if a user already exists.
	public boolean doesUserExist(String username) {
		boolean userExists = false;
		
		for (User user: Users.getUsers(Users.ALL_USERS)) {
			if (username.equals(user.getUsername())) {
				userExists = true;
				break;
			}
		}
		
		return userExists;
	}
	
	// Method for validating the login information provided by a user.
	public boolean validateLogin(String username, char[] password) {
		boolean loggedIn = false;
		
		for (User user: Users.getUsers(Users.ALL_USERS)) {
			if (username.equals(user.getUsername()) && Arrays.equals(password, user.getPassword().toCharArray())) {
				loggedIn = true;
				loggedInUser = user;
				break;
			}
		}
		
		return loggedIn;
	}
	
	// Method for returning the current logged in user.
	public User getLoggedInUser() {
		return loggedInUser;
	}
}