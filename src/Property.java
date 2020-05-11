
public abstract class Property {
	protected int id;
	protected String name;
	protected String addrLine1, addrLine2, addrCity, addrCounty, addrCountry, addrPostcode;
	protected int noOfRooms;
	protected Double sellingPrice = 0.00, soldPrice = 0.00;
	
	protected static int count = 0;
	
	public Property(String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrCountry, String addrPostcode, int noOfRooms, Double sellingPrice) {
		id = count + 1;
		this.name = name;
		this.addrLine1 = addrLine1;
		this.addrLine2 = addrLine2;
		this.addrCity = addrCity;
		this.addrCounty = addrCounty;
		this.addrCountry = addrCountry;
		this.addrPostcode = addrPostcode;
		this.noOfRooms = noOfRooms;
		this.sellingPrice = sellingPrice;
	
		count = count + 1;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrCountry, String addrPostcode) {
		this.addrLine1 = addrLine1;
		this.addrLine2 = addrLine2;
		this.addrCity = addrCity;
		this.addrCounty = addrCounty;
		this.addrCountry = addrCountry;
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
	
	public String getName() {
		return name;
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
	
	public String getAddressCountry() {
		return addrCountry;
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
