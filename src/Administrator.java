import java.io.Serializable;
import java.util.ArrayList;

public class Administrator implements Serializable {
	private int id;
	private String username;
	private String password;
	
	private static int nextId = 1;
	
	public Administrator(String username, String password) {
		id = getNextId();
		this.username = username;
		this.password = password;
		setNextId(id + 1);
	}
	
	// Getter Methods
	public int getId() {
		return id;
	}
	
	public static int getNextId() {
		return nextId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean validateLogin(String username, char[] password) {
		boolean valid = false;
		
		if (username.equals(this.username) && password.toString().equals(this.password)) {
			valid = true;
		}
		
		System.out.println(password.toString());
		
		return valid;
	}
	
	public Branch getBranch(int index) {
		return AccountManager.getBranches().get(index);
	}
	
	public ArrayList<Branch> getBranches() {
		return AccountManager.getBranches();
	}
	
	// Method for retrieving a list of branches for use with a JList.
	public ArrayList<String> getBranchChoices() {
		ArrayList<String> branchChoices = new ArrayList<String>();
		
		for (int i=0; i<getBranches().size(); i++) {
			Branch branch = getBranch(i);
			branchChoices.add("ID: " + branch.getId() + " - Name: " + branch.getBranchName() + " - Phone: " + branch.getPhone() + " - Email: " + branch.getEmail() + " - Properties: " + branch.getPropertiesCount());
		}
		
		return branchChoices;
	}
	
	// Setter Methods
	public static void setNextId(int id) {
		nextId = id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Method for adding a branch to the system.
	public void addBranch(String username, String password, String name, String phone, String email) {
		AccountManager.addBranch(username, password, name, phone, email);
	}
	
	// Methods for updating an existing branch in the system.
	public void updateBranch(int branchId, String name, String phone, String email) {
		AccountManager.updateBranch(branchId, name, phone, email);
	}
	public void updateBranch(int branchId, String password, String name, String phone, String email) {
		AccountManager.updateBranch(branchId, password, name, phone, email);
	}

	// Method for deleting a branch from the system.
	public void deleteBranch(int branchId) {
		AccountManager.deleteBranch(branchId);
	}
}
