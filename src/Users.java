import java.io.*;
import java.util.ArrayList;

public class Users {
	private static ArrayList<User> users = new ArrayList<User>();
	
	// User Filter Constants
	public static final String ALL_USERS = "All Users", ADMINISTRATORS = "Administrators", BRANCHES = "Branches";
	
	// Method for loading system users from a file.
	public static void loadUsers() {
		// Load users from file
		System.out.println("Loading users...");
		try {
			FileInputStream fis = new FileInputStream("users.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			User obj = null;
			
			while ((obj=(User)ois.readObject()) != null) {
				if (obj.getClass().getName().equals(User.ADMIN)) {
					users.add(new Administrator(obj.getId(), obj.getUsername(), obj.getPassword(), obj.getUserType()));
				}
				else if (obj.getClass().getName().equals(User.BRANCH)) {
					Branch branch = (Branch) obj;
					users.add(new Branch(obj.getId(), branch.getUsername(), branch.getPassword(), branch.getBranchName(), branch.getPhone(), branch.getEmail(), branch.getUserType()));
				}
				
				//System.out.println("Username: " + obj.getUsername() + ", Type: " + obj.getClass().getName());
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
		}	
		else {
			System.out.println(users.size() + " users loaded.");
		}
	}
	
	// Method for saving users in the system to a file.
	public static void saveUsers() {
		System.out.println("Saving users...");
		try {
			FileOutputStream fos = new FileOutputStream("users.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			for (User user: users) {
				oos.writeObject(user);		
			}
			
			oos.close();
			System.out.println("Users saved.");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Users could not be saved.");
		}
	}
	
	// Method for retrieving all users currently in the system.
	public static ArrayList<User> getUsers(String filter) {
		ArrayList<User> returnedUsers = null;
		if (filter.equals(ALL_USERS))
			returnedUsers = users;
		else if (filter.equals(ADMINISTRATORS)) {
			ArrayList<User> administrators = new ArrayList<User>();
			
			for (User user: users) {
				if (user.getUserType().equals(User.ADMIN)) {
					administrators.add(user);
				}
			}
			
			returnedUsers = administrators;
		}
		else if (filter.equals(BRANCHES)) {
			ArrayList<User> branches = new ArrayList<User>();
			
			for (User user: users) {
				if (user.getUserType().equals(User.BRANCH)) {
					branches.add(user);
				}
			}
			
			returnedUsers = branches;
		}
		
		return returnedUsers;
	}
	
	// Method for adding an administrator account to the system.
	public static void addAdministrator(String username, String password) {
		users.add(new Administrator(username, password, User.ADMIN));
	}
	
	// Method for adding a branch account to the system.
	public static void addBranch(String username, String password, String name, String phone, String email) {
		users.add(new Branch(username, password, name, phone, email, User.BRANCH));
	}
	
	// Method for updating a branch account without a password.
	public static void updateBranch(int userId, String name, String phone, String email) {
		for (int i=0; i<users.size(); i++) {
			if (userId == users.get(i).getId()) {
				Branch branch = (Branch) users.get(i);
				branch.setBranchName(name);
				branch.setPhone(phone);
				branch.setEmail(email);
				users.set(i, branch);
				saveUsers();
				break;
			}
		}
	}
	
	// Method for updating a branch account with a password.
	public static void updateBranch(int userId, String password, String name, String phone, String email) {
		for (int i=0; i<users.size(); i++) {
			if (userId == users.get(i).getId()) {
				Branch branch = (Branch) users.get(i);
				branch.setPassword(password);
				branch.setBranchName(name);
				branch.setPhone(phone);
				branch.setEmail(email);
				users.set(i, branch);
				saveUsers();
				break;
			}
		}
	}
	
	// Method for updating a user's password.
	public static void updatePassword(int id, String password) {
		for (int i=0; i<users.size(); i++) {
			if (id == users.get(i).getId()) {
				users.get(i).setPassword(password);
				saveUsers();
				break;
			}
		}
	}
	
	// Method for deleting a user from the system.
	public static void deleteUser(int id) {
		for (int i=0; i<users.size(); i++) {
			if (id == users.get(i).getId()) {
				users.remove(i);
				saveUsers();
				break;
			}
		}
	}
}
