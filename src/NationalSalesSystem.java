
public class NationalSalesSystem {	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Load administrators and branches into the system.
		AccountManager.loadAll();
		
		// Set up and display LoginView.
		LoginModel model = new LoginModel();
		LoginController controller = new LoginController(model);
		new LoginView(controller, model);
	}
}
