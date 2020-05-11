
public class House extends Property {
	private int noOfFloors;
	private boolean hasGarden;
	private boolean hasGarage;
		
	public House(String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrCountry, String addrPostcode, int noOfRooms, int noOfFloors, boolean hasGarden, boolean hasGarage, Double sellingPrice) {
		super(name, addrLine1, addrLine2, addrCity, addrCounty, addrCountry, addrPostcode, noOfRooms, sellingPrice);
		this.noOfFloors = noOfFloors;
		this.hasGarage = hasGarden;
		this.hasGarage = hasGarage;
	}

	// Setter Methods
	public void setNoOfFloors(int noOfFloors) {
		this.noOfFloors = noOfFloors;
	}
	
	public void setGarden(boolean value) {
		hasGarden = value;
	}
	
	public void setGarage(boolean value) {
		hasGarage = value;
	}
	
	// Getter Methods
	public int getNoOfFloors() {
		return noOfFloors;
	}
	
	public boolean propertyHasGarden() {
		return hasGarden;
	}
	
	public boolean propertyHasGarage() {
		return hasGarage;
	}
}
