import java.io.Serializable;

public abstract class Property implements Serializable {
	protected int id;
	protected String name;
	protected String addrLine1, addrLine2, addrCity, addrCounty, addrPostcode;
	protected int noOfRooms;
	protected Double sellingPrice, soldPrice = 0.00;
	
	public static int NEXT_ID = 1;
	
	public Property(String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrPostcode, int noOfRooms, Double sellingPrice) {
		id = getNextId();
		this.name = name;
		this.addrLine1 = addrLine1;
		this.addrLine2 = addrLine2;
		this.addrCity = addrCity;
		this.addrCounty = addrCounty;
		this.addrPostcode = addrPostcode;
		this.noOfRooms = noOfRooms;
		this.sellingPrice = sellingPrice;
		setNextId(id + 1);
	}
	
	// Getter Methods
	public int getId() {
		return id;
	}
	
	public int getNextId() {
		return Property.NEXT_ID;
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
	
	// Setter Methods
	public void setNextId(int id) {
		Property.NEXT_ID = id;
	}
	
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
}
