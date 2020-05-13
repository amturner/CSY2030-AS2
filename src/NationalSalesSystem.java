
public class NationalSalesSystem {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Load users into the system.
		Users.loadUsers();
		
		// Set up and display LoginView.
		LoginModel model = new LoginModel();
		LoginController controller = new LoginController(model);
		new LoginView(controller, model);
	}
}
