import java.util.ArrayList;

public class AdminModel {
	private User currentUser;
	
	public AdminModel(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public Branch getBranch(int index) {
		return (Branch) Users.getUsers(Users.BRANCHES).get(index);
	}
	
	public ArrayList<User> getBranches() {
		return Users.getUsers(Users.BRANCHES);
	}
	
	public String[] getBranchChoices() {
		String[] branchChoices = new String[Users.getUsers(Users.BRANCHES).size()];
		
		for (int i=0; i<Users.getUsers(Users.BRANCHES).size(); i++) {
			Branch branch = (Branch) Users.getUsers(Users.BRANCHES).get(i);
			branchChoices[i] = "ID: " + branch.getId() + " - Name: " + branch.getBranchName() + " - Phone: " + branch.getPhone() + " - Email: " + branch.getEmail() + " - Properties: " + branch.getPropertiesCount();
		}
		
		return branchChoices;
	}
	
	public void updatePassword(String password) {
		Users.updatePassword(currentUser.getId(), password);
	}
	
	public boolean isUsernameUsed(String username) {
		boolean usernameFound = false;
		
		for (User branch: Users.getUsers(Users.BRANCHES)) {
			if (branch.getUsername().equals(username)) {
				usernameFound = true;
				break;
			}
		}
		
		return usernameFound;
	}
	
	public boolean isPhoneUsed(String phone) {
		boolean phoneFound = false;
		
		for (User branch: Users.getUsers(Users.BRANCHES)) {
			Branch currentBranch = (Branch) branch;
			if (currentBranch.getPhone().equals(phone)) {
				phoneFound = true;
				break;
			}
		}
		
		return phoneFound;
	}
	
	public boolean isEmailUsed(String email) {
		boolean emailFound = false;
		
		for (User branch: Users.getUsers(Users.BRANCHES)) {
			Branch currentBranch = (Branch) branch;
			if (currentBranch.getEmail().equals(email)) {
				emailFound = true;
				break;
			}
		}
		
		return emailFound;
	}
}
