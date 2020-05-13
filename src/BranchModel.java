
public class BranchModel {
	private Branch currentUser;
	
	public BranchModel(Branch currentUser) {
		this.currentUser = currentUser;
	}
	
	public String getBranchName() {
		return currentUser.getBranchName();
	}
	
	public String[] getPropertyChoices() {
		String[] propertyChoices = new String[currentUser.getProperties().size()];
		
		for (int i=0; i<currentUser.getProperties().size(); i++) {
			Property property = currentUser.getProperty(i);
			propertyChoices[i] = "ID: " + property.getId() + " - Name: " + property.getName() + " - " + property.getClass().getName();
		}
		
		return propertyChoices;
	}
}
