import java.io.Serializable;

public abstract class User implements Serializable {
	protected String username;
	protected String password;
	protected String userType;
	protected String lastLoggedIn;
	
	// User Type Constants
	public static final String BRANCH = "Branch", ADMIN = "Administrator"; 
	
	public User(String username, String password, String userType) {
		this.username = username;
		this.password = password;
		this.userType = userType;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
