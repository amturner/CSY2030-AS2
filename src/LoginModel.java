import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class LoginModel {
	private User loggedInUser;
	
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
	
	public User getLoggedInUser() {
		return loggedInUser;
	}
}