import java.util.ArrayList;
import java.io.*;

public class LoginModel {	
	private ArrayList<User> users;
	private User loggedInUser;
	
	public LoginModel(ArrayList<User> users) {
		this.users = users;
	}
	
	public boolean validateLogin(String username, String password) {
		boolean userFound = false;
		for (User user: users) {
			if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				userFound = true;
				loggedInUser = user;
				break;
			}
		}
		
		return userFound;
	}
	
	public User getLoggedInUser() {
		return loggedInUser;
	}
}