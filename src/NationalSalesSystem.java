import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NationalSalesSystem {
	private static ArrayList<User> users = new ArrayList<User>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Load users from file
		System.out.println("Loading users...");
		try {
			FileInputStream fis = new FileInputStream("users.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			User obj = null;
			
			while ((obj=(User)ois.readObject()) != null) {
				if (obj.getClass().getName().equals(User.ADMIN)) {
					users.add(new Administrator(obj.getUsername(), obj.getPassword(), obj.getUserType()));
				}
				else if (obj.getClass().getName().equals(User.BRANCH)) {
					Branch branch = (Branch) obj;
					users.add(new Branch(branch.getUsername(), branch.getPassword(), branch.getBranchName(), branch.getPhone(), branch.getEmail(), branch.getUserType()));
				}
				
				System.out.println("Username: " + obj.getUsername() + ", Type: " + obj.getClass().getName());
			}
			
			ois.close();
		} catch (Exception e1) {
			//e1.printStackTrace();
		} 
		
		// Create admin user if no users exist.
		if (users.size() == 0) {
			System.out.println(users.size() + " users loaded.");
			System.out.println("No users currently exist. Adding admin user...");
			addAdministrator("admin", "password");
			addBranch("northants", "password", "Northampton", "0300 303 2772", "northampton@branch.com");
		}	
		else {
			System.out.println(users.size() + " users loaded.");
		}
		
		// Set up and display LoginView.
		LoginModel model = new LoginModel();
		LoginController controller = new LoginController(model);
		new LoginView(controller, model);
	}
	
	// Method for retrieving all users currently in the system.
	public static ArrayList<User> getUsers() {
		return users;
	}
	
	// Method for adding an administrator account to the system.
	public static void addAdministrator(String username, String password) {
		users.add(new Administrator(username, password, User.ADMIN));
	}
	
	// Method for adding a branch account to the system.
	public static void addBranch(String username, String password, String name, String phone, String email) {
		users.add(new Branch(username, password, name, phone, email, User.BRANCH));
	}
	
	// Method for deleting a user from the system.
	public static void deleteUser(int id) {
		for (int i=0; i<users.size(); i++) {
			if (id == users.get(i).getId()) {
				users.remove(i);
				break;
			}
		}
	}
}
