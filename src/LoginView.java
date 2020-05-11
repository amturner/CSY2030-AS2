import java.awt.FlowLayout;

import javax.swing.*;

public class LoginView extends View {
	private JTextField usernameField;
	private JTextField passwordField;
	private JButton loginButton;
	private LoginController controller;
	private LoginModel model;

	public LoginView(LoginController controller, LoginModel model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		frame.setSize(500, 300);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		usernameField = new JTextField(20);
		passwordField = new JTextField(20);
		loginButton = new JButton("Login");
		loginButton.addActionListener(controller);
		
		frame.add(usernameField);
		frame.add(passwordField);
		frame.add(loginButton);
		
		frame.setVisible(true);
	}
	
	public void login() {
		if (model.validateLogin(usernameField.getText(), passwordField.getText())) {
			System.out.println("Login success");
			frame.dispose();
			
			// Remove view
			controller.removeView(id);
			count = count - 1;
			
			// Create new view instance according to user type.
			if (model.getLoggedInUser().getUserType().equals(User.ADMIN)) {
				AdminModel adminModel = new AdminModel();
				AdminController adminController = new AdminController(adminModel);
				new AdminView(adminController, adminModel);	
			}
			else if (model.getLoggedInUser().getUserType().equals(User.BRANCH)) {
				Branch branchModel = (Branch) model.getLoggedInUser();
				BranchController branchController = new BranchController(branchModel);
				new BranchView(branchController, branchModel);
			}
		}
		else {
			System.out.println("Login failure");
		}
	}
}
