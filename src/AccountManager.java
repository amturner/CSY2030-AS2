import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;

public class AccountManager {
	private static ArrayList<Administrator> administrators = new ArrayList<Administrator>();
	private static ArrayList<Branch> branches = new ArrayList<Branch>();
	
	// Filename Constants
	private final static String ADMINS_FILE = "administrators.dat";
	private final static String BRANCHES_FILE = "branches.dat";
	// User Filter Constants
	public static final String ALL_USERS = "All Users", ADMINISTRATORS = "Administrators", BRANCHES = "Branches";
	
	// Getter Methods
	// Method for loading administrators and branches.
	public static void loadAll() {
		loadAdministrators();
		loadBranches();
	}
	
	// Method for loading administrators from a file.
	public static void loadAdministrators() {
		// Load users from file
		System.out.println("Loading administrators...");
		try {
			FileInputStream fis = new FileInputStream(ADMINS_FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Administrator obj = null;
			
			while ((obj=(Administrator)ois.readObject()) != null) {
				// Add administrator instance to ArrayList.
				administrators.add(obj);
				// Set Administrator.nextId to the next expected administrator ID.
				Administrator.setNextId(obj.getId() + 1);
			}
			
			ois.close();
			fis.close();
		} 
		catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("File '" + ADMINS_FILE + "' could not be found. File will be created on next save.");
		}
		catch (EOFException e) {
			//e.printStackTrace();
			System.out.println(administrators.size() + " administrator(s) loaded.");
		}
		catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Administrators could not be loaded.");
		}
		catch (ClassNotFoundException e) {
			//e.printStackTrace();
		}
		
		// Create new administrators if no administrators exist.
		if (administrators.size() == 0) {
			System.out.println("No administrators currently exist. Adding new administrator account...");
			addAdministrator("admin", "changeme");
		}	
	}
	
	// Method for loading administrators from a file.
	public static void loadBranches() {
		// Load users from file
		System.out.println("Loading branches...");
		try {
			FileInputStream fis = new FileInputStream(BRANCHES_FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Branch obj = null;
			
			while ((obj=(Branch)ois.readObject()) != null) {
				// Add user instance to ArrayList.
				branches.add(obj);
				// Set User.nextId to the next expected user ID.
				Branch.setNextId(obj.getId() + 1);
				if (((Branch) obj).getProperties().size() != 0) {
					// Set Property.nextId to the next expected property ID.
					Property.setNextId(((Branch) obj).getProperties().get(((Branch) obj).getProperties().size()-1).getId() + 1);
				}
			}
			
			ois.close();
			fis.close();
		} 
		catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("File '" + ADMINS_FILE + "' could not be found. File will be created on next save.");
		}
		catch (EOFException e) {
			//e.printStackTrace();
			System.out.println(branches.size() + " branch(es) loaded.");
		}
		catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Administrators could not be loaded.");
		}
		catch (ClassNotFoundException e) {
			//e.printStackTrace();
		}
	}
	
	// Method for retrieving all administrators currently in the system.
	public static ArrayList<Administrator> getAdministrators() {
		return administrators;
	}
	
	// Method for retrieving all branches currently in the system.
	public static ArrayList<Branch> getBranches() {
		return branches;
	}
	
	// Method for checking whether a username is already in use.
	public static boolean doesUserExist(String username) {
		boolean usernameFound = false;
		
		for (Administrator administrator: administrators) {
			if (administrator.getUsername().equals(username)) {
				usernameFound = true;
				break;
			}
		}
		
		if (!usernameFound) {
			for (Branch branch: branches) {
				if (branch.getUsername().equals(username)) {
					usernameFound = true;
					break;
				}
			}			
		}

		return usernameFound;
	}
	
	// Method for checking whether a phone number is already in use.
	public static boolean isPhoneUsed(String phone) {
		boolean phoneFound = false;
		
		for (Branch branch: branches) {
			if (branch.getPhone().equals(phone)) {
				phoneFound = true;
				break;
			}
		}	
		
		return phoneFound;
	}
	
	// Method for checking whether an email address is already in use.
	public static boolean isEmailUsed(String email) {
		boolean emailFound = false;
		
		for (Branch branch: branches) {
			if (branch.getEmail().equals(email)) {
				emailFound = true;
				break;
			}
		}	
		
		return emailFound;
	}
	
	// Method for checking whether a phone number is valid.
	public static boolean isPhoneValid(String phone) {
		// java.util.regex Pattern class: https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
		Pattern pattern = Pattern.compile("^[0-9]{11}$");
		Matcher matcher = pattern.matcher(phone);
		
		return matcher.matches();
	}
	
	// Method for checking whether an email address is valid.
	public static boolean isEmailValid(String email) {
		// java.util.regex Pattern class: https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
		// RegEx Pattern From: https://regexlib.com/REDetails.aspx?regexp_id=26
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();
	}
	
	// Setter Methods
	// Method for saving administrators and branches.
	public static void saveAll() {
		saveAdministrators();
		saveBranches();
	}
	
	// Method for saving administrators to a file.
	public static void saveAdministrators() {
		System.out.println("Saving administrators...");
		try {
			FileOutputStream fos = new FileOutputStream(ADMINS_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			for (Administrator administrator: administrators) {
				oos.writeObject(administrator);		
			}
			
			oos.close();
			fos.close();
			
			System.out.println("Administrators saved.");
		} 
		catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Administrators could not be saved.");
		}
	}
	
	// Method for saving branches to a file.
	public static void saveBranches() {
		System.out.println("Saving branches...");
		try {
			FileOutputStream fos = new FileOutputStream(BRANCHES_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			for (Branch branch: branches) {
				oos.writeObject(branch);		
			}
			
			oos.close();
			fos.close();
			
			System.out.println("Branches saved.");
		} 
		catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Branches could not be saved.");
		}
	}
	
	
	// Method for adding an administrator account to the system.
	public static void addAdministrator(String username, String password) {
		administrators.add(new Administrator(username, password));
	}
	
	// Method for adding a branch account to the system.
	public static void addBranch(String username, String password, String name, String phone, String email) {
		branches.add(new Branch(username, password, name, phone, email));
	}
	
	// Method for updating an administrator's password.
	public static void updateAdministratorPassword(int adminId, String password) {
		for (int i=0; i<administrators.size(); i++) {
			if (adminId == administrators.get(i).getId()) {
				Administrator administrator = administrators.get(i);
				administrator.setPassword(password);
				administrators.set(i, administrator);
				saveAdministrators();
				break;
			}
		}
	}
	
	// Method for updating a branch account without a password.
	public static void updateBranch(int branchId, String name, String phone, String email) {
		for (int i=0; i<branches.size(); i++) {
			if (branchId == branches.get(i).getId()) {
				Branch branch = branches.get(i);
				branch.setBranchName(name);
				branch.setPhone(phone);
				branch.setEmail(email);
				branches.set(i, branch);
				saveBranches();
				break;
			}
		}
	}
	// Method for updating a branch account with a password.
	public static void updateBranch(int branchId, String password, String name, String phone, String email) {
		for (int i=0; i<branches.size(); i++) {
			if (branchId == branches.get(i).getId()) {
				Branch branch = branches.get(i);
				branch.setPassword(password);
				branch.setBranchName(name);
				branch.setPhone(phone);
				branch.setEmail(email);
				branches.set(i, branch);
				saveBranches();
				break;
			}
		}
	}
	public static void updateBranch(int branchId, Branch branch) {
		for (int i=0; i<branches.size(); i++) {
			if (branchId == branches.get(i).getId()) {
				branches.set(i, branch);
				saveBranches();
				break;
			}
		}
	}
	
	// Method for deleting an administrator from the system.
	public static void deleteAdministrator(int adminId) {
		for (int i=0; i<administrators.size(); i++) {
			if (adminId == administrators.get(i).getId()) {
				administrators.remove(i);
				saveAdministrators();
				break;
			}
		}
	}
	
	// Method for deleting a branch from the system.
	public static void deleteBranch(int branchId) {
		for (int i=0; i<branches.size(); i++) {
			if (branchId == branches.get(i).getId()) {
				branches.remove(i);
				saveBranches();
				break;
			}
		}
	}
}
