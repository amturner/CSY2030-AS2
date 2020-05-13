import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EditBranchPanel extends JPanel {	
	private JPanel accountDetailsLabelPanel, accountDetailsPanel, branchDetailsLabelPanel, branchDetailsPanel, submitButtonPanel;
	private JLabel accountDetailsLabel, branchDetailsLabel, usernameLabel, passwordLabel, nameLabel, phoneLabel, emailLabel;
	private JTextField usernameField, nameField, phoneField, emailField;
	private JPasswordField passwordField;
	private JButton submitButton;
	
	// Action Constants
	public static final String ADD_BRANCH = "Add Branch", UPDATE_BRANCH = "Update Branch";
	
	public EditBranchPanel(String type) {
		setupPanel();
		
		if (type.equals(ADD_BRANCH)) {
			submitButton.setText(ADD_BRANCH);	
		}
		else if (type.equals(UPDATE_BRANCH)) {
			usernameField.setEnabled(false);
			submitButton.setText(UPDATE_BRANCH);	
		}
	}
	
	private void setupPanel() {
		this.setPreferredSize(new Dimension(475, 285));
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
		
		submitButtonPanel = new JPanel();
		submitButtonPanel.setPreferredSize(new Dimension(475, 35));
		submitButton = new JButton("");
		submitButtonPanel.add(submitButton);
		
		this.add(accountDetailsLabelPanel);
		this.add(accountDetailsPanel);
		this.add(branchDetailsLabelPanel);
		this.add(branchDetailsPanel);
		this.add(submitButtonPanel);
	}
	
	// Setter Methods
	public void setUsernameText(String text) {
		usernameField.setText(text);
	}
	
	public void setNameText(String text) {
		nameField.setText(text);
	}
	
	public void setPhoneText(String text) {
		phoneField.setText(text);
	}
	
	public void setEmailText(String text) {
		emailField.setText(text);
	}
	
	public void addActionListener(ActionListener listener) {
		submitButton.addActionListener(listener);
	}
	
	public void clearFields() {
		usernameField.setText("");
		passwordField.setText("");
		nameField.setText("");
		phoneField.setText("");
		emailField.setText("");
	}

	// Getter Methods
	public String getUsernameText() {
		return usernameField.getText();
	}
	
	public String getPasswordText() {
		return String.valueOf(passwordField.getPassword());
	}
	
	public String getNameText() {
		return nameField.getText();
	}
	
	public String getPhoneText() {
		return phoneField.getText();
	}
	
	public String getEmailText() {
		return emailField.getText();
	}
}
