import java.io.Serializable;

public abstract class User implements Serializable {
	protected int id;
	protected String username;
	protected String password;
	protected String userType;
	protected String lastLoggedIn;
	
	private static int count = 0;
	
	// User Type Constants
	public static final String BRANCH = "Branch", ADMIN = "Administrator"; 
	
	public User(String username, String password, String userType) {
		id = count + 1;
		this.username = username;
		this.password = password;
		this.userType = userType;
		
		count = count + 1;
	}
	
	// Getter Methods
	public int getId() {
		return id;
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
	
	// Setter Methods
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setLastLoggedIn(String date) {
		lastLoggedIn = date;
	}
}
