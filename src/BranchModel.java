import java.util.ArrayList;

public class BranchModel {
	private Branch currentUser;
	
	// Type Filter Constants
	public static final String ALL_PROPERTIES = "All", HOUSES = "Houses", FLATS = "Flats", UNSOLD = "Unsold", SOLD = "Sold"; 
	
	public BranchModel(Branch currentUser) {
		this.currentUser = currentUser;
	}

	// Getter Methods
	// Method for returning the current branch's name.
	public String getBranchName() {
		return currentUser.getBranchName();
	}
	
	// Method for retrieving a single property from the current branch.
	public Property getProperty(int index) {
		return currentUser.getProperty(index);
	}
	
	// Method for retrieving a list of properties from the current branch.
	public ArrayList<Property> getProperties() {
		return currentUser.getProperties();
	}
	
	// Methods for getting properties by an address.
	public ArrayList<Property> getPropertiesByAddress(String line1) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>();
		
		for (Property property: currentUser.getProperties()) {
			if (property.getAddressLine1().equals(line1)) {
				filteredProperties.add(property);
			}
		}
		
		return filteredProperties;
	}
	public ArrayList<Property> getPropertiesByAddress(String line1, String line2) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>();
		
		for (Property property: currentUser.getProperties()) {
			if (property.getAddressLine1().equals(line1) && property.getAddressLine2().equals(line2)) {
				filteredProperties.add(property);
			}
		}
		
		return filteredProperties;
	}
	public ArrayList<Property> getPropertiesByAddress(String line1, String line2, String city) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>();
		
		for (Property property: currentUser.getProperties()) {
			if (property.getAddressLine1().equals(line1) && property.getAddressLine2().equals(line2) && property.getAddressCity().equals(city)) {
				filteredProperties.add(property);
			}
		}
		
		return filteredProperties;
	}
	public ArrayList<Property> getPropertiesByAddress(String line1, String line2, String city, String county) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>();
		
		for (Property property: currentUser.getProperties()) {
			if (property.getAddressLine1().equals(line1) && property.getAddressLine2().equals(line2) && property.getAddressCity().equals(city) && 
				property.getAddressCounty().equals(county)) {
				filteredProperties.add(property);
			}
		}
		
		return filteredProperties;
	}
	public ArrayList<Property> getPropertiesByAddress(String line1, String line2, String city, String county, String postcode) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>();
		
		for (Property property: currentUser.getProperties()) {
			if (property.getAddressLine1().equals(line1) && property.getAddressLine2().equals(line2) && property.getAddressCity().equals(city) && 
				property.getAddressCounty().equals(county) && property.getAddressPostcode().equals(postcode)) {
				filteredProperties.add(property);
			}
		}
		
		return filteredProperties;
	}
	
	// Method for retrieving property choices for use with a JList.
	public String[] getPropertyChoices(String propertyType, String sellingType, ArrayList<Property> properties) {
		String[] propertyChoices = null;
		
		if (propertyType.equals(ALL_PROPERTIES)) {
			propertyChoices = new String[properties.size()];
			for (int i=0; i<properties.size(); i++) {
				Property property = properties.get(i);
				if (sellingType.equals(UNSOLD) && property.getSoldPrice() <= 0) {
					propertyChoices[i] = "ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: £" + property.getSellingPrice();
				}
				else if (sellingType.equals(SOLD) && property.getSoldPrice() > 0) {
					propertyChoices[i] = "ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: £" + property.getSellingPrice();
				}
			}
		}
		else if (propertyType.equals(HOUSES)) {
			propertyChoices = new String[properties.size()];
			for (int i=0; i<properties.size(); i++) {
				Property property = properties.get(i);
				if (property.getClass().getName().equals("House")) {
					if (sellingType.equals(UNSOLD) && property.getSoldPrice() <= 0) {
						propertyChoices[i] = "ID: " + property.getId() + " - Name: " + property.getName() + " - Type: "+ property.getClass().getName() + " - Price: £" + property.getSellingPrice();
					}
					else if (sellingType.equals(SOLD) && property.getSoldPrice() > 0) {
						propertyChoices[i] = "ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: £" + property.getSellingPrice();
					}	
				}
			}
		}
		else if (propertyType.equals(FLATS)) {
			propertyChoices = new String[properties.size()];
			for (int i=0; i<properties.size(); i++) {
				Property property = properties.get(i);
				if (property.getClass().getName().equals("Flat")) {
					if (sellingType.equals(UNSOLD) && property.getSoldPrice() <= 0) {
						propertyChoices[i] = "ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: £" + property.getSellingPrice();
					}
					else if (sellingType.equals(SOLD) && property.getSoldPrice() > 0) {
						propertyChoices[i] = "ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: £" + property.getSellingPrice();
					}
				}
			}
		}
		
		return propertyChoices;
	}
	
	// Setter Methods
	// Methods for adding a property to the current branch.
	public void addProperty(String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrPostcode, int noOfRooms, int noOfFloors, boolean hasGarden, boolean hasGarage, Double sellingPrice) {
		// Create new property instance with specified details.
		Property newProperty = new House(name, addrLine1, addrLine2, addrCity, addrCounty, addrPostcode, noOfRooms, noOfFloors, hasGarden, hasGarage, sellingPrice);
		// Add property to branch.
		currentUser.addProperty(newProperty);
		// Update current user.
		updateUser();
	}
	public void addProperty(String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrPostcode, int noOfRooms, int floorNo, Double sellingPrice, Double monthlyCharge) {
		// Create new property instance with specified details.
		Property newProperty = new Flat(name, addrLine1, addrLine2, addrCity, addrCounty, addrPostcode, noOfRooms, floorNo, sellingPrice, monthlyCharge);
		// Add property to branch.
		currentUser.addProperty(newProperty);
		// Update current user.
		updateUser();
	}
	
	// Methods for updating a property in the current branch.
	public void updateProperty(int index, String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrPostcode, int noOfRooms, int noOfFloors, boolean hasGarden, boolean hasGarage, Double sellingPrice) {
		House updatedProperty = (House) getProperty(index);
		// Update property attributes.
		updatedProperty.setName(name);
		updatedProperty.setAddress(addrLine1, addrLine2, addrCity, addrCounty, addrPostcode);
		updatedProperty.setNoOfRooms(noOfRooms);
		updatedProperty.setNoOfFloors(noOfFloors);
		updatedProperty.setGarden(hasGarden);
		updatedProperty.setGarage(hasGarage);
		updatedProperty.setSellingPrice(sellingPrice);
		// Replace property at index with updated property.
		currentUser.updateProperty(index, updatedProperty);
		// Update current user.
		updateUser();
	}
	public void updateProperty(int index, String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrPostcode, int noOfRooms, int floorNo, Double sellingPrice, Double monthlyCharge) {
		Flat updatedProperty = (Flat) getProperty(index);
		// Update property attributes.
		updatedProperty.setName(name);
		updatedProperty.setAddress(addrLine1, addrLine2, addrCity, addrCounty, addrPostcode);
		updatedProperty.setNoOfRooms(noOfRooms);
		updatedProperty.setFloorNo(floorNo);
		updatedProperty.setSellingPrice(sellingPrice);
		updatedProperty.setMonthlyCharge(monthlyCharge);
		// Replace property at index with updated property.
		currentUser.updateProperty(index, updatedProperty);
		// Update current user.
		updateUser();
	}
	
	// Method for listing a property as being "sold".
	public void sellProperty(int index, Double price) {
		Property updatedProperty = currentUser.getProperty(index);
		// Update property attribute.
		updatedProperty.setSoldPrice(price);
		// Replace property at index with updated property.
		currentUser.updateProperty(index, updatedProperty);
		// Update current user.
		updateUser();
	}
	
	// Method for deleting a property from the current branch.
	public void deleteProperty(int index) {
		// Delete property from branch.
		currentUser.deleteProperty(index);
		// Update current user.
		updateUser();
	}
	
	// Method for updating the current user in it's entirety with the system.
	public void updateUser() {
		Users.updateUser(currentUser.getId(), currentUser);
	}
	
	// Method for updating the current user's password with the system.
	public void updatePassword(String password) {
		Users.updatePassword(currentUser.getId(), password);
	}
}
