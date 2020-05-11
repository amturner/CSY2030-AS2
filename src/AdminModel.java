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
			branchChoices[i] = "ID: " + branch.getId() + " - Name: " + branch.getBranchName();
		}
		
		return branchChoices;
	}
}
