import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BranchView extends View {
	private BranchController controller;
	private BranchModel model;
	
	// Tabbed Pane
	private JTabbedPane tabbedPane = new JTabbedPane();
	// Main Panels
	private JPanel viewPropertiesPanel, addPropertyPanel, editPropertyPanel, settingsPanel;
	
	// View Properties
	// Panels
	private JPanel listPropertiesPanel, editHousePanel, editFlatPanel;
	// listPropertiesPanel - Inner Panels
	private JPanel listPropertiesLeftPanel, listPropertiesRightPanel;
	// listPropertiesLeftPanel - Inner Panels
	private JPanel listPropertiesButtonPanel;
	// listPropertiesRightPanel - Inner Panels
	private JPanel filterPanel, propertyTypePanel, sellingTypePanel, addressSearchPanel;
	// List
	private JList propertiesList;
	private JScrollPane scrollPane;
	// Labels
	private JLabel propertyListingsLabel, propertyTypeLabel, sellingTypeLabel, addressSearchLabel;
	// Dropdowns
	private JComboBox propertyTypeDropdown, sellingTypeDropdown;
	// Buttons
	private JButton editPropertyButton, deletePropertyButton, sellPropertyButton, applyFilterButton, searchButton;
	
	// Settings
	// Panels
	private JPanel settingsInnerPanel;
	
	
	// Action Constants
	public static final String EDIT_PROPERTY = "Edit", DELETE_PROPERTY = "Delete", SELL_PROPERTY = "Sell", APPLY_FILTER = "Apply Filter", ADDRESS_SEARCH = "Search";
	
	public BranchView(BranchController controller, BranchModel model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		// Get screen size.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set up frame.
		frame.setSize(500, 340);
		frame.setLocation((int) (screenSize.getWidth()-frame.getWidth())/2, (int) (screenSize.getHeight()-frame.getHeight())/2);
		frame.setTitle(frame.getTitle() + " - Branch Administration Area");
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// View Properties
		//// Main Panel
		viewPropertiesPanel = new JPanel();
		viewPropertiesPanel.setLayout(new CardLayout());
		//// List Properties Panel
		listPropertiesPanel = new JPanel();
		listPropertiesPanel.setLayout(new BorderLayout());
		listPropertiesPanel.setPreferredSize(new Dimension(frame.getWidth()-25, 275));

		////// List Properties Panel - Inner Left
		listPropertiesLeftPanel = new JPanel();
		listPropertiesLeftPanel.setPreferredSize(new Dimension(frame.getWidth()/2, 275));
		//////// List Properties Panel - Inner Left - Elements
		propertyListingsLabel = new JLabel("Listings by branch '" + model.getBranchName() + "'");
		propertiesList = new JList(model.getPropertyChoices(model.ALL_PROPERTIES));
		propertiesList.setSelectionMode(JList.VERTICAL);
		propertiesList.setLayoutOrientation(JList.VERTICAL);
		propertiesList.setVisibleRowCount(-1);
		scrollPane = new JScrollPane(propertiesList);
		scrollPane.setPreferredSize(new Dimension(215, 200));
		listPropertiesButtonPanel = new JPanel();
		editPropertyButton = new JButton(EDIT_PROPERTY);
		editPropertyButton.addActionListener(controller);
		deletePropertyButton = new JButton(DELETE_PROPERTY);
		deletePropertyButton.addActionListener(controller);
		sellPropertyButton = new JButton(SELL_PROPERTY);
		sellPropertyButton.addActionListener(controller);
		listPropertiesButtonPanel.add(editPropertyButton);
		listPropertiesButtonPanel.add(deletePropertyButton);
		listPropertiesButtonPanel.add(sellPropertyButton);

		// Add elements to listPropertiesLeftPanel.
		listPropertiesLeftPanel.add(propertyListingsLabel);
		listPropertiesLeftPanel.add(scrollPane);
		listPropertiesLeftPanel.add(listPropertiesButtonPanel);
		
		////// List Properties Panel - Inner Right
		listPropertiesRightPanel = new JPanel();
		listPropertiesRightPanel.setPreferredSize(new Dimension(frame.getWidth()/2, 275));
		//////// List Properties Panel - Inner Right - Filter Elements
		filterPanel = new JPanel();
		filterPanel.setLayout(new BorderLayout());
		filterPanel.setPreferredSize(new Dimension(165, 75));
		propertyTypePanel = new JPanel();
		propertyTypePanel.setLayout(new BorderLayout());
		sellingTypePanel = new JPanel();
		sellingTypePanel.setLayout(new BorderLayout());
		propertyTypeLabel = new JLabel("Property Type");
		sellingTypeLabel = new JLabel("Selling Type");
		String[] propertyTypeChoices = {"All", "House", "Flat"};
		String[] sellingTypeChoices = {"Unsold", "Sold"};
		propertyTypeDropdown = new JComboBox(propertyTypeChoices);
		sellingTypeDropdown = new JComboBox(sellingTypeChoices);
		applyFilterButton = new JButton(APPLY_FILTER);
		applyFilterButton.addActionListener(controller);
		propertyTypePanel.add(propertyTypeLabel, BorderLayout.NORTH);
		propertyTypePanel.add(propertyTypeDropdown, BorderLayout.SOUTH);
		sellingTypePanel.add(sellingTypeLabel, BorderLayout.NORTH);
		sellingTypePanel.add(sellingTypeDropdown, BorderLayout.SOUTH);
		filterPanel.add(propertyTypePanel, BorderLayout.WEST);
		filterPanel.add(sellingTypePanel, BorderLayout.EAST);
		filterPanel.add(applyFilterButton, BorderLayout.SOUTH);
		//////// List Properties Panel - Inner Right - Address Elements
		addressSearchLabel = new JLabel("Address Search");
		addressSearchPanel = new AddressPanel();
		searchButton = new JButton(ADDRESS_SEARCH);
		searchButton.addActionListener(controller);
		
		// Add elements to listPropertiesRightPanel.
		listPropertiesRightPanel.add(filterPanel);
		listPropertiesRightPanel.add(addressSearchLabel);
		listPropertiesRightPanel.add(addressSearchPanel);
		listPropertiesRightPanel.add(searchButton);

		// Add inner panels to main list properties panel.
		listPropertiesPanel.add(listPropertiesLeftPanel, BorderLayout.WEST);
		listPropertiesPanel.add(listPropertiesRightPanel, BorderLayout.EAST);
		
		// Edit Property
		editPropertyPanel = new EditPropertyPanel(EditPropertyPanel.UPDATE_PROPERTY);
		
		// Add "cards" to view properties panel.
		viewPropertiesPanel.add(listPropertiesPanel);
		viewPropertiesPanel.add(editPropertyPanel);
		
		// Add Property Panel
		addPropertyPanel = new EditPropertyPanel(EditPropertyPanel.ADD_PROPERTY);
		((EditPropertyPanel) addPropertyPanel).addActionListener(controller);
		
		// Settings Panel
		settingsPanel = new JPanel();
		settingsPanel.setPreferredSize(new Dimension(frame.getWidth()-25, 275));
		settingsInnerPanel = new SettingsPanel();
		((SettingsPanel) settingsInnerPanel).addActionListener(controller);
		settingsPanel.add(settingsInnerPanel);
		
		// Add main panels to tabbed pane.
		tabbedPane.addTab("View Properties", null, viewPropertiesPanel, "View Properties");
		tabbedPane.addTab("Add New Property", null, addPropertyPanel, "Add New Property");
		tabbedPane.addTab("Settings", null, settingsPanel, "Settings");
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Users.saveUsers();
			}
		});
		
		frame.add(tabbedPane);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void addProperty() {
		if (!((EditPropertyPanel) addPropertyPanel).getNameText().isEmpty()) {
			if ((((EditPropertyPanel) addPropertyPanel).getNoOfRooms() >= 1)) {
				if (((EditPropertyPanel) addPropertyPanel).isRadioButtonSelected()) {
					if (((EditPropertyPanel) addPropertyPanel).getSelectedType().equals(EditPropertyPanel.HOUSE)) {
						if (((EditPropertyPanel) addPropertyPanel).getNoOfFloors() >= 1) {
							if (((EditPropertyPanel) addPropertyPanel).getPrice() > 0.0) {
								if (!((EditPropertyPanel) addPropertyPanel).isAddressFilled()) {
									model.addProperty(((EditPropertyPanel) addPropertyPanel).getNameText(), ((EditPropertyPanel) addPropertyPanel).getLine1Text(), 
												((EditPropertyPanel) addPropertyPanel).getLine2Text(), ((EditPropertyPanel) addPropertyPanel).getCityText(), 
												((EditPropertyPanel) addPropertyPanel).getCountyText(), ((EditPropertyPanel) addPropertyPanel).getPostcodeText(), 
												((EditPropertyPanel) addPropertyPanel).getNoOfRooms(), ((EditPropertyPanel) addPropertyPanel).getNoOfFloors(), 
												((EditPropertyPanel) addPropertyPanel).getGarden(), ((EditPropertyPanel) addPropertyPanel).getGarage(), 
												((EditPropertyPanel) addPropertyPanel).getPrice());
									
									// Display success dialog.
									JOptionPane.showMessageDialog(null, "The property was successfully added!", "Property Added", JOptionPane.INFORMATION_MESSAGE);
									
									// Update view properties list with latest data.
									propertiesList.setListData(model.getPropertyChoices(BranchModel.ALL_PROPERTIES));		
								}
								else {
									// Display error dialog.
									JOptionPane.showMessageDialog(null, "The address has not been filled.", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
							else {
								// Display error dialog.
								JOptionPane.showMessageDialog(null, "The price cannot be £0.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							// Display error dialog.
							JOptionPane.showMessageDialog(null, "The amount of floors cannot be 0.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if (((EditPropertyPanel) addPropertyPanel).getSelectedType().equals(EditPropertyPanel.FLAT)) {
						if ((((EditPropertyPanel) addPropertyPanel).getFloorNo() > 0)) {
							if ((((EditPropertyPanel) addPropertyPanel).getMonthlyCharge() > 0.0)) {
								if (((EditPropertyPanel) addPropertyPanel).getPrice() > 0.0) {
									if (!((EditPropertyPanel) addPropertyPanel).isAddressFilled()) {
										model.addProperty(((EditPropertyPanel) addPropertyPanel).getNameText(), ((EditPropertyPanel) addPropertyPanel).getLine1Text(), 
												((EditPropertyPanel) addPropertyPanel).getLine2Text(), ((EditPropertyPanel) addPropertyPanel).getCityText(), 
												((EditPropertyPanel) addPropertyPanel).getCountyText(), ((EditPropertyPanel) addPropertyPanel).getPostcodeText(), 
												((EditPropertyPanel) addPropertyPanel).getNoOfRooms(), ((EditPropertyPanel) addPropertyPanel).getFloorNo(), 
												((EditPropertyPanel) addPropertyPanel).getPrice(), ((EditPropertyPanel) addPropertyPanel).getMonthlyCharge());
										
										// Display success dialog.
										JOptionPane.showMessageDialog(null, "The property was successfully added!", "Property Added", JOptionPane.INFORMATION_MESSAGE);
										
										// Update view properties list with latest data.
										propertiesList.setListData(model.getPropertyChoices(BranchModel.ALL_PROPERTIES));	
									}
									else {
										// Display error dialog.
										JOptionPane.showMessageDialog(null, "The address has not been filled.", "Error", JOptionPane.ERROR_MESSAGE);
									}
								}
								else {
									// Display error dialog.
									JOptionPane.showMessageDialog(null, "The price cannot be £0.", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}
							else {
								// Display error dialog.
								JOptionPane.showMessageDialog(null, "The monthly charge cannot be £0.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							// Display error dialog.
							JOptionPane.showMessageDialog(null, "The floor number cannot be 0.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				else {
					// Display error dialog.
					JOptionPane.showMessageDialog(null, "You have not selected a property type.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				// Display error dialog.
				JOptionPane.showMessageDialog(null, "You have not entered an amount of rooms.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			// Display error dialog.
			JOptionPane.showMessageDialog(null, "You have not entered a property name.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateProperty() {

	}
	
	public void updatePassword() {
		if (!((SettingsPanel) settingsInnerPanel).getPasswordText().isEmpty()) {
			// Update admin user's password.
			model.updatePassword(((SettingsPanel) settingsInnerPanel).getPasswordText());
			
			// Display success dialog.
			JOptionPane.showMessageDialog(null, "Your password was successfully updated!", "Password Updated", JOptionPane.INFORMATION_MESSAGE);
			
			// Clear new password field.
			((SettingsPanel) settingsInnerPanel).clearPasswordField();
		}
		else {
			// Display error dialog.
			JOptionPane.showMessageDialog(null, "You have not entered a password.", "Error", JOptionPane.ERROR_MESSAGE);
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
