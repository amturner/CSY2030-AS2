import java.io.Serializable;

public abstract class User implements Serializable {
	protected int id;
	protected String username;
	protected String password;
	protected String userType;
	protected String lastLoggedIn;
	
	protected static int nextId = 1;
	
	// User Type Constants
	public static final String BRANCH = "Branch", ADMIN = "Administrator"; 
	
	/*
	public User(int id, String username, String password, String userType) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.userType = userType;
		User.nextId = getNextId();
	}
	*/
	
	public User(String username, String password, String userType) {
		id = User.nextId;
		this.username = username;
		this.password = password;
		this.userType = userType;
		User.nextId = getNextId();
	}
	
	// Getter Methods
	public int getId() {
		return id;
	}
	
	public int getNextId() {
		return id + 1;
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
