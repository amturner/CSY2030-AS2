import java.util.ArrayList;

public class AdminModel {
	private ArrayList<User> branches;
	
	public AdminModel() {
		fetchBranches();
	}
	
	public void fetchBranches() {
		branches = NationalSalesSystem.getUsers(NationalSalesSystem.BRANCHES);
	}
	
	public ArrayList<User> getBranches() {
		return branches;
	}
	
	public String[] getBranchChoices() {
		fetchBranches();
		
		String[] branchChoices = new String[branches.size()];
		
		for (int i=0; i<branches.size(); i++) {
			Branch branch = (Branch) branches.get(i);
			branchChoices[i] = "ID: " + branch.getId() + " - Name: " + branch.getBranchName() + " - Properties: " + branch.getPropertiesCount();
		}
		
		return branchChoices;
	}
	
	public boolean isUsernameUsed(String username) {
		boolean usernameFound = false;
		
		for (User branch: branches) {
			if (branch.getUsername().equals(username)) {
				usernameFound = true;
				break;
			}
		}
		
		return usernameFound;
	}
	
	public boolean isPhoneUsed(String phone) {
		boolean phoneFound = false;
		
		for (User branch: branches) {
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
		
		for (User branch: branches) {
			Branch currentBranch = (Branch) branch;
			if (currentBranch.getEmail().equals(email)) {
				emailFound = true;
				break;
			}
		}
		
		return emailFound;
	}
}
