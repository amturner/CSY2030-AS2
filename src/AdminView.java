import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AdminView extends View {
	private JTabbedPane tabbedPane = new JTabbedPane();
	private JPanel viewBranchesPanel, addBranchPanel, settingsPanel;
	private JPanel branchesLabelPanel, userDetailsLabelPanel, userDetailsPanel, branchDetailsLabelPanel, branchDetailsPanel, branchesButtonPanel, addBranchButtonPanel;
	private JLabel branchesLabel, userDetailsLabel, branchDetailsLabel, usernameLabel, passwordLabel, nameLabel, phoneLabel, emailLabel;
	private JList branchesList;
	private JScrollPane scrollPane;
	private JTextField usernameField, nameField, phoneField, emailField;
	private JPasswordField passwordField;
	private JButton logoutButton, addBranchButton, editBranchButton, deleteBranchButton;
	
	private AdminController controller;
	private AdminModel model;
	
	// Action Constants
	public static final String LOGOUT = "Log out", ADD_BRANCH = "Add Branch", EDIT_BRANCH = "Edit Branch", DELETE_BRANCH = "Delete Branch";
	
	public AdminView(AdminController controller, AdminModel model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		frame.setSize(500, 325);
		frame.setTitle(programTitle + " - Administration Area");
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		logoutButton = new JButton(LOGOUT);
		logoutButton.addActionListener(controller);
		
		// View Branches Panel
		viewBranchesPanel = new JPanel();
		viewBranchesPanel.setPreferredSize(new Dimension(475, 255));
		
		branchesLabelPanel = new JPanel();
		branchesLabelPanel.setPreferredSize(new Dimension(475, 25));
		branchesLabel = new JLabel("Branches");
		branchesLabelPanel.add(branchesLabel);
		branchesList = new JList(model.getBranchChoices());
		branchesList.setSelectionMode(JList.VERTICAL);
		branchesList.setLayoutOrientation(JList.VERTICAL);
		branchesList.setVisibleRowCount(-1);
		scrollPane = new JScrollPane(branchesList);
		scrollPane.setPreferredSize(new Dimension(350, 170));
		
		branchesButtonPanel = new JPanel();
		branchesButtonPanel.setPreferredSize(new Dimension(475, 35));
		editBranchButton = new JButton(EDIT_BRANCH);
		editBranchButton.addActionListener(controller);
		deleteBranchButton = new JButton(DELETE_BRANCH);
		deleteBranchButton.addActionListener(controller);
		branchesButtonPanel.add(editBranchButton);
		branchesButtonPanel.add(deleteBranchButton);
		
		viewBranchesPanel.add(branchesLabelPanel);
		viewBranchesPanel.add(scrollPane);
		viewBranchesPanel.add(branchesButtonPanel);
		
		// Add Branch Panel
		addBranchPanel = new JPanel();
		addBranchPanel.setPreferredSize(new Dimension(475, 255));
		
		userDetailsLabelPanel = new JPanel();
		userDetailsLabelPanel.setPreferredSize(new Dimension(475, 25));
		userDetailsLabel = new JLabel("User Details");
		userDetailsLabelPanel.add(userDetailsLabel);
		usernameLabel = new JLabel("Username");
		usernameField = new JTextField(20);
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField(20);
		
		branchDetailsLabelPanel = new JPanel();
		branchDetailsLabelPanel.setPreferredSize(new Dimension(475, 25));
		branchDetailsLabel = new JLabel("Branch Details");
		branchDetailsLabelPanel.add(branchDetailsLabel);
		nameLabel = new JLabel("Name");
		nameField = new JTextField(20);
		phoneLabel = new JLabel("Phone");
		phoneField = new JTextField(20);
		emailLabel = new JLabel("Email");
		emailField = new JTextField(20);
		
		userDetailsPanel = new JPanel();
		userDetailsPanel.setPreferredSize(new Dimension(300, 60));
		userDetailsPanel.add(usernameLabel);
		userDetailsPanel.add(usernameField);
		userDetailsPanel.add(passwordLabel);
		userDetailsPanel.add(passwordField);
		
		branchDetailsPanel = new JPanel();
		branchDetailsPanel.setPreferredSize(new Dimension(300, 75));
		branchDetailsPanel.add(nameLabel);
		branchDetailsPanel.add(nameField);
		branchDetailsPanel.add(phoneLabel);
		branchDetailsPanel.add(phoneField);
		branchDetailsPanel.add(emailLabel);
		branchDetailsPanel.add(emailField);
		
		addBranchButtonPanel = new JPanel();
		addBranchButtonPanel.setPreferredSize(new Dimension(475, 35));
		addBranchButton = new JButton(ADD_BRANCH);
		addBranchButton.addActionListener(controller);
		addBranchButtonPanel.add(addBranchButton);
		
		addBranchPanel.add(userDetailsLabelPanel);
		addBranchPanel.add(userDetailsPanel);
		addBranchPanel.add(branchDetailsLabelPanel);
		addBranchPanel.add(branchDetailsPanel);
		addBranchPanel.add(addBranchButtonPanel);
		
		// Settings Panel
		settingsPanel = new JPanel();
		settingsPanel.setPreferredSize(new Dimension(475, 255));
		settingsPanel.add(logoutButton);
		
		tabbedPane.addTab("View Branches", null, viewBranchesPanel, "View Branches");
		tabbedPane.addTab("Add New Branch", null, addBranchPanel, "Add New Branch");
		tabbedPane.addTab("Settings", null, settingsPanel, "Settings");
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				NationalSalesSystem.saveUsers();
			}
		});
		
		frame.add(tabbedPane);
		frame.setVisible(true);
	}
	
	public void addBranch() {
		if (!usernameField.getText().isEmpty()) {
			if (!String.valueOf(passwordField.getPassword()).isEmpty()) {
				if (!nameField.getText().isEmpty()) {
					if (!phoneField.getText().isEmpty()) {
						if (!emailField.getText().isEmpty()) {
							if (!model.isUsernameUsed(usernameField.getText())) {
								if (!model.isPhoneUsed(phoneField.getText())) {
									if (!model.isEmailUsed(emailField.getText())) {
										// Add new branch to system.
										NationalSalesSystem.addBranch(usernameField.getText(), String.valueOf(passwordField.getPassword()), nameField.getText(), phoneField.getText(), emailField.getText());
										
										// Display success dialog.
										JOptionPane.showMessageDialog(null, "The branch was successfully added!", "Branch Added", JOptionPane.INFORMATION_MESSAGE);
										
										// Clear form fields.
										usernameField.setText("");
										passwordField.setText("");
										nameField.setText("");
										phoneField.setText("");
										emailField.setText("");
										
										// Update view branches list with latest data.
										branchesList.setListData(model.getBranchChoices());		
									}
									else {
										// Display error dialog.
										JOptionPane.showMessageDialog(null, "The email provided is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
									}	
								}
								else {
									// Display error dialog.
									JOptionPane.showMessageDialog(null, "The phone number provided is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
								}	
							}
							else {
								// Display error dialog.
								JOptionPane.showMessageDialog(null, "The username provided is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							// Display error dialog.
							JOptionPane.showMessageDialog(null, "You have not entered an email address.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						// Display error dialog.
						JOptionPane.showMessageDialog(null, "You have not entered a phone number.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					// Display error dialog.
					JOptionPane.showMessageDialog(null, "You have not entered a branch name.", "Error", JOptionPane.ERROR_MESSAGE);
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
	
	public void updateBranch() {
		
	}
	
	public void deleteBranch() {
		if (!branchesList.isSelectionEmpty()) {
			// Display yes no dialog.
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected branch?\n\nThis will result in the lost of any associated properties.", "Error", JOptionPane.YES_NO_OPTION);
			
			if (choice == 0) {
				int userId = model.getBranches().get(branchesList.getSelectedIndex()).getId();
				NationalSalesSystem.deleteUser(userId);
				branchesList.setListData(model.getBranchChoices());	
				
				
				// Display success dialog.
				JOptionPane.showMessageDialog(null, "The selected branch was successfully deleted!", "Branch Deleted", JOptionPane.INFORMATION_MESSAGE);
			}	
		}
		else {
			// Display error dialog.
			JOptionPane.showMessageDialog(null, "You have not selected a branch.", "Error", JOptionPane.ERROR_MESSAGE);
		}
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
