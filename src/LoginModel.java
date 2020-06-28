import java.util.*;
import java.io.*;

public class LoginModel {
	private Administrator loggedInAdministrator;
	private Branch loggedInBranch;
	
	// Getter Methods
	// Method for validating the login information provided by a user.
	public boolean validateLogin(String username, char[] password) {
		boolean loggedIn = false;
		
		for (Administrator administrator: AccountManager.getAdministrators()) {
			if (username.equals(administrator.getUsername()) && Arrays.equals(password, administrator.getPassword().toCharArray())) {
				loggedIn = true;
				loggedInAdministrator = administrator;
				break;
			}
		}
		
		if (!loggedIn) {
			for (Branch branch: AccountManager.getBranches()) {
				if (username.equals(branch.getUsername()) && Arrays.equals(password, branch.getPassword().toCharArray())) {
					loggedIn = true;
					loggedInBranch = branch;
					break;
				}
			}
		}
		
		return loggedIn;
	}
	
	// Method for returning the current logged in account.
	public Administrator getLoggedInAdministrator() {
		return loggedInAdministrator;
	}
	public Branch getLoggedInBranch() {
		return loggedInBranch;
	}
}