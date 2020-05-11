import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class AdminView extends View {
	private JLabel label;
	private JButton logoutButton;
	private AdminController controller;
	private AdminModel model;
	
	// Action Constants
	public static final String LOGOUT = "Log out";
	
	public AdminView(AdminController controller, AdminModel model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		frame.setSize(500, 300);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		label = new JLabel("Admin View");
		logoutButton = new JButton(LOGOUT);
		logoutButton.addActionListener(controller);
		
		frame.add(label);
		frame.add(logoutButton);
		frame.setVisible(true);
	}
	
	public void logout() {
		frame.dispose();
		
		// Remove view
		controller.removeView(id);
		count = count - 1;
		
		// Set up and display LoginView.
		LoginModel loginModel = new LoginModel();
		LoginController loginController = new LoginController(loginModel);
		new LoginView(loginController, loginModel);
	}
}
