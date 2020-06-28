import java.io.Serializable;

public abstract class Property implements Serializable {
	protected int id;
	protected String name;
	protected String addrLine1, addrLine2, addrCity, addrCounty, addrPostcode;
	protected int noOfRooms;
	protected Double sellingPrice, soldPrice = 0.00;
	
	protected static int nextId = 1;
	
	public Property(String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrPostcode, int noOfRooms, Double sellingPrice) {
		id = Property.nextId;
		this.name = name;
		this.addrLine1 = addrLine1;
		this.addrLine2 = addrLine2;
		this.addrCity = addrCity;
		this.addrCounty = addrCounty;
		this.addrPostcode = addrPostcode;
		this.noOfRooms = noOfRooms;
		this.sellingPrice = sellingPrice;
		Property.nextId = Property.nextId + 1;
	}
	
	// Setter Methods
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrPostcode) {
		this.addrLine1 = addrLine1;
		this.addrLine2 = addrLine2;
		this.addrCity = addrCity;
		this.addrCounty = addrCounty;
		this.addrPostcode = addrPostcode;
	}
	
	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}
	
	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public void setSoldPrice(Double soldPrice) {
		this.soldPrice = soldPrice;
	}
	
	// Getter Methods
	public int getId() {
		return id;
	}
	
	public int getNextId() {
		return id + 1;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNoOfRooms() {
		return noOfRooms;
	}
	
	public String getAddressLine1() {
		return addrLine1;
	}
	
	public String getAddressLine2() {
		return addrLine2;
	}
	
	public String getAddressCity() {
		return addrCity;
	}
	
	public String getAddressCounty() {
		return addrCounty;
	}
	
	public String getAddressPostcode() {
		return addrPostcode;
	}
	
	public Double getSellingPrice() {
		return sellingPrice;
	}
	
	public Double getSoldPrice() {
		return soldPrice;
	}
}
