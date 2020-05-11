import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NationalSalesSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<User> users = new ArrayList<User>();
		
		// Load users from file
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
				
				System.out.println("Username: " + obj.getUsername() + ", User Type: " + obj.getClass().getName());
			}
			
			ois.close();
		} catch (Exception e1) {
			//e1.printStackTrace();
		} 
		
		// Create admin user if no users exist.
		if (users.size() == 0) {
			System.out.println("No users currently exist. Adding admin user.");
			users.add(new Administrator("admin", "password", User.ADMIN));
			users.add(new Branch("branch", "password", "Northampton", "0300 303 2772", "northampton@branch.com", User.BRANCH));
		}	
		else {
			System.out.println("Users loaded");
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("users.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			for (User user: users) {
				oos.writeObject(user);	
			}
			
			oos.close();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		// Set up and display LoginView.
		LoginModel model = new LoginModel(users);
		LoginController controller = new LoginController(model);
		new LoginView(controller, model);
	}

}
