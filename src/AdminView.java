import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class AdminView extends View {
	private AdminController controller;
	private Administrator model;
	
	// Tabbed Pane
	private JTabbedPane tabbedPane = new JTabbedPane();
	// Main Panels
	private JPanel viewBranchesPanel, settingsPanel;
	private EditBranchPanel addBranchPanel;
	
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
	private JPanel branchesLabelPanel;
	// Labels
	private JLabel branchesLabel;

	// Edit Branch
	// Panels
	private JPanel viewBranchesButtonPanel;
	private EditBranchPanel editBranchFormPanel;
	// Buttons
	private JButton viewBranchesButton;
	
	// Settings
	// Panels
	private SettingsPanel settingsInnerPanel;
	
	// Action Constants
	public static final String VIEW_BRANCHES = "View Branches", EDIT_BRANCH = "Edit Branch", DELETE_BRANCH = "Delete Branch";
	// Panel Constants
	public static final String VIEW_BRANCHES_PANEL = "View Branches", ADD_BRANCH_PANEL = "Add Branch", SETTINGS_PANEL = "Settings Panel";
	// Card Constants
	public static final String LIST_BRANCHES_CARD = "List Branches", EDIT_BRANCH_CARD = "Edit Branch";
	
	public AdminView(AdminController controller, Administrator model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		// Get screen size.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set up frame.
		frame.setSize(500, 325);
		frame.setLocation((int) (screenSize.getWidth()-frame.getWidth())/2, (int) (screenSize.getHeight()-frame.getHeight())/2);
		frame.setTitle(frame.getTitle() + " - System Administration Area");
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// View Branches Panel
		viewBranchesPanel = new JPanel();
		viewBranchesPanel.setLayout(new CardLayout());
		viewBranchesPanel.setPreferredSize(new Dimension(475, 285));
		
		// View Branches Panel - List Branches
		listBranchesPanel = new JPanel();
		listBranchesPanel.setPreferredSize(new Dimension(475, 285));
		branchesLabelPanel = new JPanel();
		branchesLabelPanel.setPreferredSize(new Dimension(475, 25));
		branchesLabel = new JLabel("Branches");
		branchesLabelPanel.add(branchesLabel);
		branchesList = new JList(model.getBranchChoices().toArray());
		branchesList.setSelectionMode(JList.VERTICAL);
		branchesList.setLayoutOrientation(JList.VERTICAL);
		branchesList.setVisibleRowCount(-1);
		scrollPane = new JScrollPane(branchesList);
		scrollPane.setPreferredSize(new Dimension(375, 170));
		
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
		editBranchPanel.setPreferredSize(new Dimension(475, 285));
		
		viewBranchesButtonPanel = new JPanel();
		viewBranchesButtonPanel.setPreferredSize(new Dimension(475, 35));
		viewBranchesButton = new JButton(VIEW_BRANCHES);
		viewBranchesButton.addActionListener(controller);
		viewBranchesButtonPanel.add(viewBranchesButton);
		
		editBranchFormPanel = new EditBranchPanel(EditBranchPanel.UPDATE_BRANCH);
		editBranchFormPanel.addActionListener(controller);
		
		editBranchPanel.add(viewBranchesButton);
		editBranchPanel.add(editBranchFormPanel);
		
		viewBranchesPanel.add(listBranchesPanel, LIST_BRANCHES_CARD);
		viewBranchesPanel.add(editBranchPanel, EDIT_BRANCH_CARD);
		
		// Add Branch Panel
		addBranchPanel = new EditBranchPanel(EditBranchPanel.ADD_BRANCH);
		addBranchPanel.addActionListener(controller);
		
		// Settings Panel
		settingsPanel = new JPanel();
		settingsPanel.setPreferredSize(new Dimension(475, 285));
		settingsInnerPanel = new SettingsPanel();
		settingsInnerPanel.addActionListener(controller);
		settingsPanel.add(settingsInnerPanel);
		
		// Add main panels to tabbed pane.
		tabbedPane.addTab("View Branches", null, viewBranchesPanel, "View Branches");
		tabbedPane.addTab("Add New Branch", null, addBranchPanel, "Add New Branch");
		tabbedPane.addTab("Settings", null, settingsPanel, "Settings");
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				AccountManager.saveAll();
			}
		});
		
		frame.add(tabbedPane);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void addBranch() {
		if (!addBranchPanel.getUsernameText().isEmpty()) {
			if (!addBranchPanel.getPasswordText().isEmpty()) {
				if (!addBranchPanel.getNameText().isEmpty()) {
					if (!addBranchPanel.getPhoneText().isEmpty()) {
						if (!addBranchPanel.getEmailText().isEmpty()) {
							if (!AccountManager.doesUserExist(addBranchPanel.getUsernameText())) {
								if (AccountManager.isPhoneValid(addBranchPanel.getPhoneText())) {
									if (!AccountManager.isPhoneUsed(addBranchPanel.getPhoneText())) {
										if (AccountManager.isEmailValid(addBranchPanel.getEmailText())) {
											if (!AccountManager.isEmailUsed(addBranchPanel.getEmailText())) {
												// Add new branch to system.
												AccountManager.addBranch(addBranchPanel.getUsernameText(), addBranchPanel.getPasswordText(), addBranchPanel.getNameText(), addBranchPanel.getPhoneText(), addBranchPanel.getEmailText());
												
												// Display success dialog.
												JOptionPane.showMessageDialog(null, "The branch was successfully added!", "Branch Added", JOptionPane.INFORMATION_MESSAGE);
												
												// Clear form fields.
												addBranchPanel.clearFields();
												
												// Update view branches list with latest data.
												branchesList.setListData(model.getBranchChoices().toArray());		
											}
											else {
												// Display error dialog.
												JOptionPane.showMessageDialog(null, "The email address provided is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
											}		
										}
										else {
											// Display error dialog.
											JOptionPane.showMessageDialog(null, "The email address provided is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
										}
									}
									else {
										// Display error dialog.
										JOptionPane.showMessageDialog(null, "The phone number provided is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
									}		
								}
								else {
									// Display error dialog.
									JOptionPane.showMessageDialog(null, "The phone number provided is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
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
		
		
		if (!editBranchFormPanel.getNameText().isEmpty()) {
			if (!editBranchFormPanel.getPhoneText().isEmpty()) {
				if (!editBranchFormPanel.getEmailText().isEmpty()) {
					if (AccountManager.isPhoneValid(editBranchFormPanel.getPhoneText())) {
						if (editBranchFormPanel.getPhoneText().equals(branch.getPhone()) || !AccountManager.isPhoneUsed(editBranchFormPanel.getPhoneText())) {
							if (AccountManager.isEmailValid(editBranchFormPanel.getEmailText())) {
								if (editBranchFormPanel.getEmailText().equals(branch.getEmail()) || !AccountManager.isEmailUsed(editBranchFormPanel.getEmailText())) {
									// Add new branch to system.
									if (!editBranchFormPanel.getPasswordText().isEmpty())
										AccountManager.updateBranch(branch.getId(), editBranchFormPanel.getPasswordText(), editBranchFormPanel.getNameText(), editBranchFormPanel.getPhoneText(), editBranchFormPanel.getEmailText());
									else
										AccountManager.updateBranch(branch.getId(), editBranchFormPanel.getNameText(), editBranchFormPanel.getPhoneText(), editBranchFormPanel.getEmailText());
									
									// Display success dialog.
									JOptionPane.showMessageDialog(null, "The branch was successfully updated!", "Branch Updated", JOptionPane.INFORMATION_MESSAGE);
									
									// Clear form fields.
									editBranchFormPanel.clearFields();
									
									// Update view branches list with latest data.
									branchesList.setListData(model.getBranchChoices().toArray());		
									
									// Display the list of current branches.
									switchCards(viewBranchesPanel, LIST_BRANCHES_CARD);
								}
								else {
									// Display error dialog.
									JOptionPane.showMessageDialog(null, "The email address provided is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
								}		
							}
							else {
								// Display error dialog.
								JOptionPane.showMessageDialog(null, "The email address provided is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							// Display error dialog.
							JOptionPane.showMessageDialog(null, "The phone number provided is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
						}		
					}
					else {
						// Display error dialog.
						JOptionPane.showMessageDialog(null, "The phone number provided is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					// Display error dialog.
					JOptionPane.showMessageDialog(null, "The email address cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				// Display error dialog.
				JOptionPane.showMessageDialog(null, "The phone number cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			// Display error dialog.
			JOptionPane.showMessageDialog(null, "The branch name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updatePassword() {
		if (!settingsInnerPanel.getPasswordText().isEmpty()) {
			// Update admin user's password.
			model.setPassword(settingsInnerPanel.getPasswordText());
			
			// Display success dialog.
			JOptionPane.showMessageDialog(null, "Your password was successfully updated!", "Password Updated", JOptionPane.INFORMATION_MESSAGE);
			
			// Clear new password field.
			settingsInnerPanel.clearPasswordField();
		}
		else {
			// Display error dialog.
			JOptionPane.showMessageDialog(null, "You have not entered a password.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void deleteBranch() {
		if (!branchesList.isSelectionEmpty()) {
			// Display yes no dialog.
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected branch?\n\nThis will result in the loss of any associated properties.", "Delete Branch", JOptionPane.YES_NO_OPTION);
			
			// Delete the branch if user clicked "Yes".
			if (choice == 0) {
				int branchId = model.getBranches().get(branchesList.getSelectedIndex()).getId();
				model.deleteBranch(branchId);
				branchesList.setListData(model.getBranchChoices().toArray());	
				
				
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
		if (!name.equals(EDIT_BRANCH_CARD)) {
			CardLayout cardLayout = (CardLayout) (parent.getLayout());
			cardLayout.show(parent, name);
		}
		else {
			if (!branchesList.isSelectionEmpty()) {
				CardLayout cardLayout = (CardLayout) (parent.getLayout());
				cardLayout.show(parent, name);
				
				Branch branch = model.getBranch(branchesList.getSelectedIndex());
				editBranchFormPanel.setUsernameText(branch.getUsername());
				editBranchFormPanel.setNameText(branch.getBranchName());
				editBranchFormPanel.setPhoneText(branch.getPhone());
				editBranchFormPanel.setEmailText(branch.getEmail());
			}
			else {
				// Display error dialog.
				JOptionPane.showMessageDialog(null, "You have not selected a branch.", "Error", JOptionPane.ERROR_MESSAGE);
			}
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
		
		// Print message to console.
		System.out.println("User logged out. Displaying log in screen.");
		
		// Set up and display LoginView.
		LoginModel loginModel = new LoginModel();
		LoginController loginController = new LoginController(loginModel);
		new LoginView(loginController, loginModel);
	}
}