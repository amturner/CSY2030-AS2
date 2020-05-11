
public class Flat extends Property {
	private int floorNo;
	private Double monthlyCharge;

	public Flat(String name, String addrLine1, String addrLine2, String addrCity, String addrCounty, String addrCountry, String addrPostcode, int noOfRooms, int floorNo, Double sellingPrice, Double monthlyCharge) {
		super(name, addrLine1, addrLine2, addrCity, addrCounty, addrCountry, addrPostcode, noOfRooms, sellingPrice);
		this.floorNo = floorNo;
		this.monthlyCharge = monthlyCharge;
	}
	
	// Setter Methods
	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}
	
	public void setMonthlyCharge(Double monthlyCharge) {
		this.monthlyCharge = monthlyCharge;
	}
	
	// Getter Methods
	public int getFloorNo() {
		return floorNo;
	}
	
	public Double getMonthlyCharge() {
		return monthlyCharge;
	}
}
