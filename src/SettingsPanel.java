import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SettingsPanel extends JPanel {
	private JPanel logoutPanel, newPasswordPanel;
	private JLabel logoutLabel, newPasswordLabel;
	private JPasswordField newPasswordField;
	private JButton logoutButton, updatePasswordButton;

	// Action Constants
	public static final String LOGOUT = "Log out", UPDATE_PASSWORD = "Update Password";
	
	public SettingsPanel() {
		setupPanel();
	}
	
	private void setupPanel() {
		this.setPreferredSize(new Dimension(415, 200));
		logoutPanel = new JPanel();
		logoutLabel = new JLabel("Want to logout?");
		logoutButton = new JButton(LOGOUT);
		logoutPanel.add(logoutLabel);
		logoutPanel.add(logoutButton);
		
		newPasswordPanel = new JPanel();
		newPasswordLabel = new JLabel("New Password");
		newPasswordField = new JPasswordField(16);
		updatePasswordButton = new JButton(UPDATE_PASSWORD);
		newPasswordPanel.add(newPasswordLabel);
		newPasswordPanel.add(newPasswordField);
		newPasswordPanel.add(updatePasswordButton);
		
		this.add(logoutPanel);
		this.add(newPasswordPanel);
	}
	
	// Setter Methods
	public void addActionListener(ActionListener listener) {
		logoutButton.addActionListener(listener);
		updatePasswordButton.addActionListener(listener);
	}
	
	public void clearPasswordField() {
		newPasswordField.setText("");
	}
	
	// Getter Methods
	public String getPasswordText() {
		return String.valueOf(newPasswordField.getPassword());
	}
}
