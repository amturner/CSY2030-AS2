
public class BranchModel {
	private Branch currentUser;
	
	// Filter Constants
	public static final String ALL_PROPERTIES = "All", HOUSES = "Houses", FLATS = "Flats"; 
	
	public BranchModel(Branch currentUser) {
		this.currentUser = currentUser;
	}
	
	public String getBranchName() {
		return currentUser.getBranchName();
	}
	
	public String[] getPropertyChoices(String filter) {
		String[] propertyChoices = null;
		
		if (filter.equals(ALL_PROPERTIES)) {
			propertyChoices = new String[currentUser.getProperties().size()];
			for (int i=0; i<currentUser.getProperties().size(); i++) {
				Property property = currentUser.getProperty(i);
				propertyChoices[i] = "ID: " + property.getId() + " - Name: " + property.getName() + " - " + property.getClass().getName();
			}
		}
		else if (filter.equals(HOUSES)) {
			propertyChoices = new String[currentUser.getProperties().size()];
			for (int i=0; i<currentUser.getProperties().size(); i++) {
				Property property = currentUser.getProperty(i);
				propertyChoices[i] = "ID: " + property.getId() + " - Name: " + property.getName() + " - " + property.getClass().getName();
			}
		}
		
		return propertyChoices;
	}
	
	public void addProperty(String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrPostcode, int noOfRooms, int noOfFloors, boolean hasGarden, boolean hasGarage, Double sellingPrice) {
		Property newProperty = new House(name, addrLine1, addrLine2, addrCity, addrCounty, addrPostcode, noOfRooms, noOfFloors, hasGarden, hasGarage, sellingPrice);
		currentUser.addProperty(newProperty);
		updateUser();
	}
	
	public void addProperty(String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrPostcode, int noOfRooms, int floorNo, Double sellingPrice, Double monthlyCharge) {
		Property newProperty = new Flat(name, addrLine1, addrLine2, addrCity, addrCounty, addrPostcode, noOfRooms, floorNo, sellingPrice, monthlyCharge);
		currentUser.addProperty(newProperty);
		updateUser();
	}
	
	public void updateUser() {
		Users.updateUser(currentUser.getId(), currentUser);
	}
	
	public void updatePassword(String password) {
		Users.updatePassword(currentUser.getId(), password);
	}
}
