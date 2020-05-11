import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class LoginModel {	
	private ArrayList<User> users;
	private User loggedInUser;
	
	public LoginModel() {
		users = NationalSalesSystem.getUsers();
	}
	
	public boolean doesUserExist(String username) {
		boolean userExists = false;
		
		for (User user: users) {
			if (username.equals(user.getUsername())) {
				userExists = true;
				break;
			}
		}
		
		return userExists;
	}
	
	public boolean validateLogin(String username, char[] password) {
		boolean loggedIn = false;
		
		for (User user: users) {
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
	
	public void saveUsers() {
		try {
			FileOutputStream fos = new FileOutputStream("users.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			for (User user: users) {
				oos.writeObject(user);		
			}
			
			oos.close();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}