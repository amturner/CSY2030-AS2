
public class Administrator extends User {
	public Administrator(int id, String username, String password, String userType) {
		super(id, username, password, userType);
	}
	
	public Administrator(String username, String password, String userType) {
		super(username, password, userType);
	}
	
	public void addBranch (String name, String password, String phone, String email) {
		
	}
}
