import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
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
	private AdminController controller;
	private AdminModel model;
	
	// Tabbed Pane
	private JTabbedPane tabbedPane = new JTabbedPane();
	// Main Panels
	private JPanel viewBranchesPanel, addBranchPanel, settingsPanel;
	
	// View Branches
	// Panels
	private JPanel listBranchesPanel, branchesButtonPanel, editBranchPanel;
	// List
	private JList branchesList;
	private JScrollPane scrollPane;
	// Buttons
	private JButton editBranchButton, deleteBranchButton;
	
	// Add Branch
	// Panels
	private JPanel branchesLabelPanel, accountDetailsLabelPanel, accountDetailsPanel, branchDetailsLabelPanel, branchDetailsPanel, addBranchButtonPanel;
	// Labels
	private JLabel branchesLabel, accountDetailsLabel, branchDetailsLabel, usernameLabel, passwordLabel, nameLabel, phoneLabel, emailLabel;
	// Fields
	private JTextField usernameField, nameField, phoneField, emailField;
	private JPasswordField passwordField;
	//Add Buttons
	private JButton addBranchButton;
	
	// Edit Branch
	// Panels
	private JPanel viewBranchesButtonPanel, accountDetailsLabelPanel2, accountDetailsPanel2, branchDetailsLabelPanel2, branchDetailsPanel2, updateBranchButtonPanel;
	// Labels
	private JLabel accountDetailsLabel2, branchDetailsLabel2, usernameLabel2, passwordLabel2, nameLabel2, phoneLabel2, emailLabel2;
	// Fields
	private JTextField nameField2, phoneField2, emailField2;
	private JPasswordField passwordField2;
	//Add Buttons
	private JButton viewBranchesButton, updateBranchButton;
	
	// Settings
	// Buttons
	private JButton logoutButton;
	
	// Action Constants
	public static final String LOGOUT = "Log out", ADD_BRANCH = "Add Branch", VIEW_BRANCHES = "View Branches", EDIT_BRANCH = "Edit Branch", UPDATE_BRANCH = "Update Branch", DELETE_BRANCH = "Delete Branch";
	// Panel Constants
	public static final String VIEW_BRANCHES_PANEL = "View Branches", ADD_BRANCH_PANEL = "Add Branch", SETTINGS_PANEL = "Settings Panel";
	// Card Constants
	public static final String LIST_BRANCHES_CARD = "List Branches", EDIT_BRANCH_CARD = "Edit Branch";
	
	public AdminView(AdminController controller, AdminModel model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		// Get screen size.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set up frame.
		frame.setSize(500, 325);
		frame.setLocation((int) (screenSize.getWidth()-frame.getWidth())/2, (int) (screenSize.getHeight()-frame.getHeight())/2);
		frame.setTitle(frame.getTitle() + " - Administration Area");
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// View Branches Panel
		viewBranchesPanel = new JPanel();
		viewBranchesPanel.setLayout(new CardLayout());
		viewBranchesPanel.setPreferredSize(new Dimension(475, 260));
		
		// View Branches Panel - List Branches
		listBranchesPanel = new JPanel();
		listBranchesPanel.setPreferredSize(new Dimension(475, 260));
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
		
		listBranchesPanel.add(branchesLabelPanel);
		listBranchesPanel.add(scrollPane);
		listBranchesPanel.add(branchesButtonPanel);
		
		// View Branches Panel - Edit Branch
		editBranchPanel = new JPanel();
		editBranchPanel.setPreferredSize(new Dimension(475, 260));
		
		viewBranchesButtonPanel = new JPanel();
		viewBranchesButtonPanel.setPreferredSize(new Dimension(475, 35));
		viewBranchesButton = new JButton(VIEW_BRANCHES);
		viewBranchesButton.addActionListener(controller);
		viewBranchesButtonPanel.add(viewBranchesButton);
		
		accountDetailsLabelPanel2 = new JPanel();
		accountDetailsLabelPanel2.setPreferredSize(new Dimension(475, 25));
		accountDetailsLabel2 = new JLabel("Account Details");
		accountDetailsLabelPanel2.add(accountDetailsLabel2);
		passwordLabel2 = new JLabel("Password");
		passwordField2 = new JPasswordField(20);
		
		branchDetailsLabelPanel2 = new JPanel();
		branchDetailsLabelPanel2.setPreferredSize(new Dimension(475, 25));
		branchDetailsLabel2 = new JLabel("Branch Details");
		branchDetailsLabelPanel2.add(branchDetailsLabel2);
		nameLabel2 = new JLabel("Name");
		nameField2 = new JTextField(20);
		phoneLabel2 = new JLabel("Phone");
		phoneField2 = new JTextField(20);
		emailLabel2 = new JLabel("Email");
		emailField2 = new JTextField(20);
		
		accountDetailsPanel2 = new JPanel();
		accountDetailsPanel2.setPreferredSize(new Dimension(300, 40));
		accountDetailsPanel2.add(passwordLabel2);
		accountDetailsPanel2.add(passwordField2);
		
		branchDetailsPanel2 = new JPanel();
		branchDetailsPanel2.setPreferredSize(new Dimension(300, 75));
		branchDetailsPanel2.add(nameLabel2);
		branchDetailsPanel2.add(nameField2);
		branchDetailsPanel2.add(phoneLabel2);
		branchDetailsPanel2.add(phoneField2);
		branchDetailsPanel2.add(emailLabel2);
		branchDetailsPanel2.add(emailField2);
		
		updateBranchButtonPanel = new JPanel();
		updateBranchButtonPanel.setPreferredSize(new Dimension(475, 35));
		updateBranchButton = new JButton(UPDATE_BRANCH);
		updateBranchButton.addActionListener(controller);
		updateBranchButtonPanel.add(updateBranchButton);
		
		editBranchPanel.add(viewBranchesButton);
		editBranchPanel.add(accountDetailsLabelPanel2);
		editBranchPanel.add(accountDetailsPanel2);
		editBranchPanel.add(branchDetailsLabelPanel2);
		editBranchPanel.add(branchDetailsPanel2);
		editBranchPanel.add(updateBranchButtonPanel);
		
		viewBranchesPanel.add(listBranchesPanel, LIST_BRANCHES_CARD);
		viewBranchesPanel.add(editBranchPanel, EDIT_BRANCH_CARD);
		
		// Add Branch Panel
		addBranchPanel = new JPanel();
		addBranchPanel.setPreferredSize(new Dimension(475, 260));
		
		accountDetailsLabelPanel = new JPanel();
		accountDetailsLabelPanel.setPreferredSize(new Dimension(475, 25));
		accountDetailsLabel = new JLabel("Account Details");
		accountDetailsLabelPanel.add(accountDetailsLabel);
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
		
		accountDetailsPanel = new JPanel();
		accountDetailsPanel.setPreferredSize(new Dimension(300, 60));
		accountDetailsPanel.add(usernameLabel);
		accountDetailsPanel.add(usernameField);
		accountDetailsPanel.add(passwordLabel);
		accountDetailsPanel.add(passwordField);
		
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
		
		addBranchPanel.add(accountDetailsLabelPanel);
		addBranchPanel.add(accountDetailsPanel);
		addBranchPanel.add(branchDetailsLabelPanel);
		addBranchPanel.add(branchDetailsPanel);
		addBranchPanel.add(addBranchButtonPanel);
		
		// Settings Panel
		settingsPanel = new JPanel();
		settingsPanel.setPreferredSize(new Dimension(475, 260));
		
		logoutButton = new JButton(LOGOUT);
		logoutButton.addActionListener(controller);
		settingsPanel.add(logoutButton);
		
		// Add main panels to tabbed pane.
		tabbedPane.addTab("View Branches", null, viewBranchesPanel, "View Branches");
		tabbedPane.addTab("Add New Branch", null, addBranchPanel, "Add New Branch");
		tabbedPane.addTab("Settings", null, settingsPanel, "Settings");
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				NationalSalesSystem.saveUsers();
			}
		});
		
		frame.add(tabbedPane);
		frame.pack();
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
		Branch branch = model.getBranch(branchesList.getSelectedIndex());
		if (!nameField2.getText().isEmpty()) {
			if (!phoneField2.getText().isEmpty()) {
				if (!emailField2.getText().isEmpty()) {
					if (phoneField2.getText().equals(branch.getPhone()) || !model.isPhoneUsed(phoneField2.getText())) {
						if (emailField2.getText().equals(branch.getEmail()) || !model.isEmailUsed(emailField2.getText())) {
							// Add new branch to system.
							if (!String.valueOf(passwordField.getPassword()).isEmpty())
								NationalSalesSystem.updateBranch(branch.getId(), String.valueOf(passwordField2.getPassword()), nameField2.getText(), phoneField2.getText(), emailField2.getText());
							else
								NationalSalesSystem.updateBranch(branch.getId(), nameField2.getText(), phoneField2.getText(), emailField2.getText());
							
							// Display success dialog.
							JOptionPane.showMessageDialog(null, "The branch was successfully updated!", "Branch Added", JOptionPane.INFORMATION_MESSAGE);
							
							// Clear form fields.
							passwordField2.setText("");
							nameField2.setText("");
							phoneField2.setText("");
							emailField2.setText("");
							
							// Update view branches list with latest data.
							branchesList.setListData(model.getBranchChoices());		
							
							switchCards(viewBranchesPanel, LIST_BRANCHES_CARD);
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
	
	// Method for switching cards on a panel with the CardLayout layout.
	public void switchCards(Container parent, String name) {
		CardLayout cardLayout = (CardLayout) (parent.getLayout());
		cardLayout.show(parent, name);
		
		if (name.equals(EDIT_BRANCH_CARD)) {
			Branch branch = model.getBranch(branchesList.getSelectedIndex());
			nameField2.setText(branch.getBranchName());
			phoneField2.setText(branch.getPhone());
			emailField2.setText(branch.getEmail());
		}
	}
	
	// Method for returning a panel from the admin view.
	public JPanel getPanel(String panel) {
		JPanel returnedPanel = null;
		
		if (panel.equals(VIEW_BRANCHES_PANEL)) {
			returnedPanel = viewBranchesPanel;
		}
		else if (panel.equals(ADD_BRANCH_PANEL)) {
			returnedPanel = addBranchPanel;
		}
		else if (panel.equals(SETTINGS_PANEL)) {
			returnedPanel = settingsPanel;
		}
		
		return returnedPanel;
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