import java.io.Serializable;
import java.util.ArrayList;

public class Branch implements Serializable {
	private int id;
	private String username;
	private String password;
	private String branchName;
	private String phone;
	private String email;
	private ArrayList<Property> properties = new ArrayList<Property>();
	
	private static int nextId = 1;
	
	// Type Filter Constants
	public static final String ALL_PROPERTIES = "All", HOUSES = "Houses", FLATS = "Flats", UNSOLD = "Unsold", SOLD = "Sold"; 
	
	public Branch(String username, String password, String branchName, String phone, String email) {
		id = getNextId();
		this.username = username;
		this.password = password;
		this.branchName = branchName;
		this.phone = phone;
		this.email = email;
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
		
		return valid;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Property getProperty(int index) {
		return properties.get(index);
	}
	
	public ArrayList<Property> getProperties() {
		return properties;
	}
	
	public int getPropertiesCount() {
		return properties.size();
	}
	
	// Methods for getting properties by an address.
	public ArrayList<Property> getPropertiesByAddress(String field) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>();
		
		for (Property property: properties) {
			if (property.getAddressLine1().equals(field) || property.getAddressLine2().equals(field) || property.getAddressCity().equals(field) || 
				property.getAddressCounty().equals(field) || property.getAddressPostcode().equals(field)) {
				filteredProperties.add(property);
			}
		}
		
		return filteredProperties;
	}
	public ArrayList<Property> getPropertiesByAddress(String line1, String line2) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>();
		
		for (Property property: properties) {
			if (property.getAddressLine1().equals(line1) && property.getAddressLine2().equals(line2)) {
				filteredProperties.add(property);
			}
		}
		
		return filteredProperties;
	}
	public ArrayList<Property> getPropertiesByAddress(String line1, String line2, String city) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>();
		
		for (Property property: properties) {
			if (property.getAddressLine1().equals(line1) && property.getAddressLine2().equals(line2) && property.getAddressCity().equals(city)) {
				filteredProperties.add(property);
			}
		}
		
		return filteredProperties;
	}
	public ArrayList<Property> getPropertiesByAddress(String line1, String line2, String city, String county) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>();
		
		for (Property property: properties) {
			if (property.getAddressLine1().equals(line1) && property.getAddressLine2().equals(line2) && property.getAddressCity().equals(city) && 
				property.getAddressCounty().equals(county)) {
				filteredProperties.add(property);
			}
		}
		
		return filteredProperties;
	}
	public ArrayList<Property> getPropertiesByAddress(String line1, String line2, String city, String county, String postcode) {
		ArrayList<Property> filteredProperties = new ArrayList<Property>();
		
		for (Property property: properties) {
			if (property.getAddressLine1().equals(line1) && property.getAddressLine2().equals(line2) && property.getAddressCity().equals(city) && 
				property.getAddressCounty().equals(county) && property.getAddressPostcode().equals(postcode)) {
				filteredProperties.add(property);
			}
		}
		
		return filteredProperties;
	}
	
	// Method for retrieving property choices for use with a JList.
	public ArrayList<String> getPropertyChoices(String propertyType, String sellingType, ArrayList<Property> properties) {
		ArrayList<String> propertyChoices = new ArrayList<String>();
		
		if (propertyType.equals(ALL_PROPERTIES)) {
			for (int i=0; i<properties.size(); i++) {
				Property property = properties.get(i);
				if (sellingType.equals(UNSOLD) && property.getSoldPrice() <= 0) {
					propertyChoices.add("ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: �" + property.getSellingPrice());
				}
				else if (sellingType.equals(SOLD) && property.getSoldPrice() > 0) {
					propertyChoices.add("ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: �" + property.getSellingPrice());
				}
			}
		}
		else if (propertyType.equals(HOUSES)) {
			for (int i=0; i<properties.size(); i++) {
				Property property = properties.get(i);
				if (property.getClass().getName().equals("House")) {
					if (sellingType.equals(UNSOLD) && property.getSoldPrice() <= 0) {
						propertyChoices.add("ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: �" + property.getSellingPrice());
					}
					else if (sellingType.equals(SOLD) && property.getSoldPrice() > 0) {
						propertyChoices.add("ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: �" + property.getSellingPrice());
					}	
				}
			}
		}
		else if (propertyType.equals(FLATS)) {
			for (int i=0; i<properties.size(); i++) {
				Property property = properties.get(i);
				if (property.getClass().getName().equals("Flat")) {
					if (sellingType.equals(UNSOLD) && property.getSoldPrice() <= 0) {
						propertyChoices.add("ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: �" + property.getSellingPrice());
					}
					else if (sellingType.equals(SOLD) && property.getSoldPrice() > 0) {
						propertyChoices.add("ID: " + property.getId() + " - Name: " + property.getName() + " - Type: " + property.getClass().getName() + " - Price: �" + property.getSellingPrice());
					}
				}
			}
		}	
		
		return propertyChoices;
	}
	
	// Setter Methods
	public static void setNextId(int id) {
		nextId = id;
	}
	
	public void setPassword(String password) {
		this.password = password;
		AccountManager.updateBranch(id, this);
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	// Methods for adding a property to the branch.
	public void addProperty(String name, String line1, String line2, String city, String county, String postcode, int noOfRooms, int noOfFloors, boolean hasGarden, boolean hasGarage, Double sellingPrice) {
		// Create new property instance with specified details.
		properties.add(new House(name, line1, line2, city, county, postcode, noOfRooms, noOfFloors, hasGarden, hasGarage, sellingPrice));
		// Update branch.
		AccountManager.updateBranch(id, this);
	}
	public void addProperty(String name, String line1, String line2, String city, String county, String postcode, int noOfRooms, int floorNo, Double sellingPrice, Double monthlyCharge) {
		// Create new property instance with specified details.
		properties.add(new Flat(name, line1, line2, city, county, postcode, noOfRooms, floorNo, sellingPrice, monthlyCharge));
		// Update branch.
		AccountManager.updateBranch(id, this);
	}
	
	// Methods for updating a property in the branch.
	public void updateProperty(int index, String name, String line1, String line2, String city, String county, String postcode, int noOfRooms, int noOfFloors, boolean hasGarden, boolean hasGarage, Double sellingPrice) {
		House updatedProperty = (House) properties.get(index);
		// Update property attributes.
		updatedProperty.setName(name);
		updatedProperty.setAddress(line1, line2, city, county, postcode);
		updatedProperty.setNoOfRooms(noOfRooms);
		updatedProperty.setNoOfFloors(noOfFloors);
		updatedProperty.setGarden(hasGarden);
		updatedProperty.setGarage(hasGarage);
		updatedProperty.setSellingPrice(sellingPrice);
		// Replace property at index with updated property.
		properties.set(index, updatedProperty);
		// Update branch.
		AccountManager.updateBranch(id, this);
	}
	public void updateProperty(int index, String name, String line1, String line2, String city, String county, String postcode, int noOfRooms, int floorNo, Double sellingPrice, Double monthlyCharge) {
		Flat updatedProperty = (Flat) properties.get(index);
		// Update property attributes.
		updatedProperty.setName(name);
		updatedProperty.setAddress(line1, line2, city, county, postcode);
		updatedProperty.setNoOfRooms(noOfRooms);
		updatedProperty.setFloorNo(floorNo);
		updatedProperty.setSellingPrice(sellingPrice);
		updatedProperty.setMonthlyCharge(monthlyCharge);
		// Replace property at index with updated property.
		properties.set(index, updatedProperty);
		// Update branch.
		AccountManager.updateBranch(id, this);
	}
	
	// Method for marking property at a specificed index as being "sold".
	public void sellProperty(int index, Double price) {
		Property updatedProperty = properties.get(index);
		// Update property attribute.
		updatedProperty.setSoldPrice(price);
		// Replace property at index with updated property.
		properties.set(index, updatedProperty);
		// Update branch.
		AccountManager.updateBranch(id, this);
	}
	
	// Method for deleting a property from the branch.
	public void deleteProperty(int index) {
		// Delete property at specified index from the branch.
		properties.remove(index);
		// Update branch.
		AccountManager.updateBranch(id, this);
	}
}
