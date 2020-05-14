import java.io.*;
import java.util.ArrayList;

public class Users {
	private static ArrayList<User> users = new ArrayList<User>();
	
	// Filename Constant
	private final static String FILENAME = "users.dat";
	// User Filter Constants
	public static final String ALL_USERS = "All Users", ADMINISTRATORS = "Administrators", BRANCHES = "Branches";
	
	// Getter Methods
	// Method for loading system users from a file.
	public static void loadUsers() {
		// Load users from file
		System.out.println("Loading users...");
		try {
			FileInputStream fis = new FileInputStream(FILENAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			User obj = null;
			
			while ((obj=(User)ois.readObject()) != null) {
				// Add user instance to ArrayList.
				users.add(obj);
				// Set User.nextId to the next expected user ID.
				User.nextId = obj.getNextId();
				if (obj.getClass().getName().equals(User.BRANCH) && ((Branch) obj).getProperties().size() != 0) {
					// Set Property.nextId to the next expected property ID.
					Property.nextId = ((Branch) obj).getProperties().get(((Branch) obj).getProperties().size()-1).getNextId();
				}
			}
			
			ois.close();
			fis.close();
		} 
		catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("File '" + FILENAME + "' could not be found. File will be created on next save.");
		}
		catch (EOFException e) {
			//e.printStackTrace();
			System.out.println(users.size() + " user(s) loaded.");
		}
		catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Users could not be loaded.");
		}
		catch (ClassNotFoundException e) {
			//e.printStackTrace();
		}
		
		// Create admin user if no users exist.
		if (users.size() == 0) {
			System.out.println("No users currently exist. Adding admin user...");
			addAdministrator("admin", "password");
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
	
	// Setter Methods
	// Method for saving users in the system to a file.
	public static void saveUsers() {
		System.out.println("Saving users...");
		try {
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			for (User user: users) {
				oos.writeObject(user);		
			}
			
			oos.close();
			fos.close();
			
			System.out.println("Users saved.");
		} 
		catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Users could not be saved.");
		}
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
	
	public static void updateUser(int id, User user) {
		for (int i=0; i<users.size(); i++) {
			if (id == users.get(i).getId()) {
				users.set(i, user);
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
