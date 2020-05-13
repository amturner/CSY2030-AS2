import java.util.ArrayList;

public class Branch extends User {
	private String branchName;
	private String phone;
	private String email;
	private ArrayList<Property> properties = new ArrayList<Property>();
	
	public Branch(int id, String username, String password, String branchName, String phone, String email, String userType) {
		super(id, username, password, userType);
		this.branchName = branchName;
		this.phone = phone;
		this.email = email;
	}
	
	public Branch(String username, String password, String branchName, String phone, String email, String userType) {
		super(username, password, userType);
		this.branchName = branchName;
		this.phone = phone;
		this.email = email;
	}
	
	// Setter Methods
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addProperty(Property property) {
		properties.add(property);
	}
	
	public void deleteProperty(int index) {
		properties.remove(index);
	}
	
	// Getter Methods
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
}
