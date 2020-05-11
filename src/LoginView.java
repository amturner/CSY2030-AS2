import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

public class LoginView extends View {
	private JPanel labelPanel, formPanel;
	private JLabel mainLabel, usernameLabel, passwordLabel, statusLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private LoginController controller;
	private LoginModel model;

	public LoginView(LoginController controller, LoginModel model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		// Get screen size.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set up frame.
		frame.setSize(355, 150);
		frame.setLocation((int) (screenSize.getWidth()-frame.getWidth())/2, (int) (screenSize.getHeight()-frame.getHeight())/2);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		labelPanel = new JPanel();
		formPanel = new JPanel();
		formPanel.setPreferredSize(new Dimension(355, 95));
		
		// Set up UI elements.
		mainLabel = new JLabel("National Property Sales System");
		usernameLabel = new JLabel("Username");
		usernameField = new JTextField(20);
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField(20);
		loginButton = new JButton("Log in");
		loginButton.addActionListener(controller);
		statusLabel = new JLabel("");
		statusLabel.setForeground(Color.RED);
		
		// Add UI elements to panels.
		labelPanel.add(mainLabel);
		formPanel.add(usernameLabel);
		formPanel.add(usernameField);
		formPanel.add(passwordLabel);
		formPanel.add(passwordField);
		formPanel.add(loginButton);
		
		// Add panels to main panel.
		frame.add(labelPanel, BorderLayout.PAGE_START);
		frame.add(formPanel, BorderLayout.CENTER);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				NationalSalesSystem.saveUsers();
			}
		});
		
		// Display frame.
		frame.pack();
		frame.setVisible(true);
	}
	
	// Method for logging the user in.
	public void login() {
		if (!usernameField.getText().isEmpty()) {
			if (passwordField.getPassword().length != 0) {
				if (model.doesUserExist(usernameField.getText())) {
					if (model.validateLogin(usernameField.getText(), passwordField.getPassword())) {
						System.out.println("Login success");
						frame.dispose();
						
						// Remove view.
						controller.removeView(id);
						count = count - 1;
						
						// Create new view instance according to user type.
						// AdminView
						if (model.getLoggedInUser().getUserType().equals(User.ADMIN)) {
							AdminModel adminModel = new AdminModel();
							AdminController adminController = new AdminController(adminModel);
							new AdminView(adminController, adminModel);	
						}
						// BranchView
						else if (model.getLoggedInUser().getUserType().equals(User.BRANCH)) {
							Branch branchModel = (Branch) model.getLoggedInUser();
							BranchController branchController = new BranchController(branchModel);
							new BranchView(branchController, branchModel);
						}
					}
					else {
						// Display error dialog.
						JOptionPane.showMessageDialog(null, "Your password is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
					}			
				}
				else {
					// Display error dialog.
					JOptionPane.showMessageDialog(null, "The user requested does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
			else {
				// Display error dialog.
				JOptionPane.showMessageDialog(null, "You have not entered a password.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			// Display error dialog.
			JOptionPane.showMessageDialog(null, "You have not entered a username.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
