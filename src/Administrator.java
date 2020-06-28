import java.io.Serializable;
import java.util.ArrayList;

public class Administrator implements Serializable {
	private int id;
	private String username;
	private String password;
	
	public static int NEXT_ID = 1;
	
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
	
	public int getNextId() {
		return Administrator.NEXT_ID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Boolean validateLogin(String username, char[] password) {
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
	
	public String[] getBranchChoices() {
		String[] branchChoices = new String[getBranches().size()];
		
		for (int i=0; i<getBranches().size(); i++) {
			Branch branch = getBranch(i);
			branchChoices[i] = "ID: " + branch.getId() + " - Name: " + branch.getBranchName() + " - Phone: " + branch.getPhone() + " - Email: " + branch.getEmail() + " - Properties: " + branch.getPropertiesCount();
		}
		
		return branchChoices;
	}
	
	// Setter Methods
	public void setNextId(int id) {
		Administrator.NEXT_ID = id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addBranch(String username, String password, String name, String phone, String email) {
		AccountManager.addBranch(username, password, name, phone, email);
	}
	
	public void updateBranch(int branchId, String name, String phone, String email) {
		AccountManager.updateBranch(branchId, name, phone, email);
	}
	public void updateBranch(int branchId, String password, String name, String phone, String email) {
		AccountManager.updateBranch(branchId, password, name, phone, email);
	}

	public void deleteBranch(int branchId) {
		AccountManager.deleteBranch(branchId);
	}
}
