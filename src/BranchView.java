import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class BranchView extends View {
	private BranchController controller;
	private Branch model;
	
	// Tabbed Pane
	private JTabbedPane tabbedPane = new JTabbedPane();
	// Main Panels
	private JPanel viewPropertiesPanel, settingsPanel, editPropertyPanel;
	private EditPropertyPanel addPropertyPanel;
	// View Properties
	// Panels
	private JPanel listPropertiesPanel;
	// listPropertiesPanel - Inner Panels
	private JPanel listPropertiesLeftPanel, listPropertiesRightPanel;
	// listPropertiesLeftPanel - Inner Panels
	private JPanel listPropertiesButtonPanel;
	// listPropertiesRightPanel - Inner Panels
	private JPanel filterPanel, propertyTypePanel, sellingTypePanel;
	private AddressPanel addressSearchPanel;
	// List
	private JList propertiesList;
	private JScrollPane scrollPane;
	// Labels
	private JLabel propertyListingsLabel, propertyTypeLabel, sellingTypeLabel, addressSearchLabel;
	// Dropdowns
	private JComboBox propertyTypeDropdown, sellingTypeDropdown;
	// Buttons
	private JButton editPropertyButton, deletePropertyButton, sellPropertyButton, applyFilterButton, searchButton;
	
	
	// Edit Property
	// Panels
	private EditPropertyPanel editPropertyInnerPanel;
	// Buttons
	private JButton viewPropertiesButton;
	
	// Settings
	// Panels
	private SettingsPanel settingsInnerPanel;
	
	
	// Action Constants
	public static final String VIEW_PROPERTIES = "View Properties", EDIT_PROPERTY = "Edit", DELETE_PROPERTY = "Delete", SELL_PROPERTY = "Sell", APPLY_FILTER = "Apply Filter", ADDRESS_SEARCH = "Search";
	// Panel Constants
	public static final String VIEW_PROPERTIES_PANEL = "View Properties", ADD_PROPERTY_PANEL = "Add Property", SETTINGS_PANEL = "Settings Panel";
	// Card Constants
	public static final String LIST_PROPERTIES_CARD = "List Properties", EDIT_PROPERTY_CARD = "Edit Property";
	
	
	public BranchView(BranchController controller, Branch model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		// Get screen size.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set up frame.
		frame.setSize(500, 405);
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
		listPropertiesPanel.setPreferredSize(new Dimension(frame.getWidth()-25, 335));

		////// List Properties Panel - Inner Left
		listPropertiesLeftPanel = new JPanel();
		listPropertiesLeftPanel.setPreferredSize(new Dimension(frame.getWidth()/2, 335));
		//////// List Properties Panel - Inner Left - Elements
		propertyListingsLabel = new JLabel("Listings by branch '" + model.getBranchName() + "'");
		propertiesList = new JList(model.getPropertyChoices(Branch.ALL_PROPERTIES, Branch.UNSOLD, model.getProperties()).toArray());
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
		listPropertiesRightPanel.setPreferredSize(new Dimension(frame.getWidth()/2, 335));
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
		String[] propertyTypeChoices = {"All", "Houses", "Flats"};
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
		editPropertyPanel = new JPanel();
		editPropertyPanel.setPreferredSize(new Dimension(475, 25));
		viewPropertiesButton = new JButton(VIEW_PROPERTIES);
		viewPropertiesButton.addActionListener(controller);
		editPropertyInnerPanel = new EditPropertyPanel(EditPropertyPanel.UPDATE_PROPERTY);
		editPropertyInnerPanel.addActionListener(controller);
		editPropertyPanel.add(viewPropertiesButton);
		editPropertyPanel.add(editPropertyInnerPanel);
		
		// Add "cards" to view properties panel.
		viewPropertiesPanel.add(listPropertiesPanel, LIST_PROPERTIES_CARD);
		viewPropertiesPanel.add(editPropertyPanel, EDIT_PROPERTY_CARD);
		
		// Add Property Panel
		addPropertyPanel = new EditPropertyPanel(EditPropertyPanel.ADD_PROPERTY);
		((EditPropertyPanel) addPropertyPanel).addActionListener(controller);
		
		// Settings Panel
		settingsPanel = new JPanel();
		settingsPanel.setPreferredSize(new Dimension(frame.getWidth()-25, 335));
		settingsInnerPanel = new SettingsPanel();
		((SettingsPanel) settingsInnerPanel).addActionListener(controller);
		settingsPanel.add(settingsInnerPanel);
		
		// Add main panels to tabbed pane.
		tabbedPane.addTab("View Properties", null, viewPropertiesPanel, "View Properties");
		tabbedPane.addTab("Add New Property", null, addPropertyPanel, "Add New Property");
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
	
	public void addProperty() {
		if (!addPropertyPanel.getNameText().isEmpty()) {
			if (addPropertyPanel.getNoOfRooms() >= 1) {
				if (addPropertyPanel.isRadioButtonSelected()) {
					if (addPropertyPanel.getSelectedType().equals(EditPropertyPanel.HOUSE)) {
						if (addPropertyPanel.getNoOfFloors() >= 1) {
							if (addPropertyPanel.getPrice() > 0.0) {
								if (addPropertyPanel.isAddressFilled()) {
									// Add property to branch.
									model.addProperty(addPropertyPanel.getNameText(), addPropertyPanel.getLine1Text(), addPropertyPanel.getLine2Text(), 
												addPropertyPanel.getCityText(), addPropertyPanel.getCountyText(), addPropertyPanel.getPostcodeText(), 
												addPropertyPanel.getNoOfRooms(), addPropertyPanel.getNoOfFloors(), addPropertyPanel.getGarden(), 
												addPropertyPanel.getGarage(), addPropertyPanel.getPrice());
									
									// Display success dialog.
									JOptionPane.showMessageDialog(null, "The property was successfully added!", "Property Added", JOptionPane.INFORMATION_MESSAGE);
									
									// Clear edit property panel inputs.
									addPropertyPanel.clear();
									
									// Update view properties list with latest data and according to filter.
									applyPropertiesFilter();	
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
							JOptionPane.showMessageDialog(null, "The amount of floors cannot be less than 1 or is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else if (addPropertyPanel.getSelectedType().equals(EditPropertyPanel.FLAT)) {
						if (addPropertyPanel.getFloorNo() > 0) {
							if (addPropertyPanel.getMonthlyCharge() > 0.0) {
								if (addPropertyPanel.getPrice() > 0.0) {
									if (addPropertyPanel.isAddressFilled()) {
										// Add property to branch.
										model.addProperty(addPropertyPanel.getNameText(), addPropertyPanel.getLine1Text(), addPropertyPanel.getLine2Text(), 
												addPropertyPanel.getCityText(), addPropertyPanel.getCountyText(), addPropertyPanel.getPostcodeText(), 
												addPropertyPanel.getNoOfRooms(), addPropertyPanel.getFloorNo(), addPropertyPanel.getPrice(), 
												addPropertyPanel.getMonthlyCharge());
										
										// Display success dialog.
										JOptionPane.showMessageDialog(null, "The property was successfully added!", "Property Added", JOptionPane.INFORMATION_MESSAGE);
										
										// Clear edit property panel inputs.
										addPropertyPanel.clear();
										
										// Update view properties list with latest data and according to filter.
										applyPropertiesFilter();
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
				JOptionPane.showMessageDialog(null, "The amount of rooms cannot be less than 1 or is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		else {
			// Display error dialog.
			JOptionPane.showMessageDialog(null, "You have not entered a property name.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateProperty() {
		if (!editPropertyInnerPanel.getNameText().isEmpty()) {
			if (editPropertyInnerPanel.getNoOfRooms() >= 1) {
				if (editPropertyInnerPanel.getSelectedType().equals(EditPropertyPanel.HOUSE)) {
					if (editPropertyInnerPanel.getNoOfFloors() >= 1) {
						if (editPropertyInnerPanel.getPrice() > 0.0) {
							if (editPropertyInnerPanel.isAddressFilled()) {
								// Update property attributes.
								model.updateProperty(propertiesList.getSelectedIndex(), editPropertyInnerPanel.getNameText(), editPropertyInnerPanel.getLine1Text(),
													editPropertyInnerPanel.getLine2Text(), editPropertyInnerPanel.getCityText(), editPropertyInnerPanel.getCountyText(),
													editPropertyInnerPanel.getPostcodeText(), editPropertyInnerPanel.getNoOfRooms(), editPropertyInnerPanel.getNoOfFloors(),
													editPropertyInnerPanel.getGarden(), editPropertyInnerPanel.getGarage(), editPropertyInnerPanel.getPrice());
								
								// Display success dialog.
								JOptionPane.showMessageDialog(null, "The property was successfully updated!", "Property Updated", JOptionPane.INFORMATION_MESSAGE);
							
								// Clear edit property panel inputs.
								editPropertyInnerPanel.clear();
								
								// Update view branches list with latest data.
								applyPropertiesFilter();	
								
								// Display the list of current branches.
								switchCards(viewPropertiesPanel, LIST_PROPERTIES_CARD);
							}	
							else {
								// Display error dialog.
								JOptionPane.showMessageDialog(null, "The address cannot be blank.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							// Display error dialog.
							JOptionPane.showMessageDialog(null, "The selling price cannot be less than 0 or is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						// Display error dialog.
						JOptionPane.showMessageDialog(null, "The amount of floors cannot be less than 1 or is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if (editPropertyInnerPanel.getSelectedType().equals(EditPropertyPanel.FLAT)) {
					if (editPropertyInnerPanel.getFloorNo() > 0) {
						if (editPropertyInnerPanel.getMonthlyCharge() > 0.0) {
							if (editPropertyInnerPanel.getPrice() > 0.0) {
								if (editPropertyInnerPanel.isAddressFilled()) {
									// Update property attributes.
									model.updateProperty(propertiesList.getSelectedIndex(), editPropertyInnerPanel.getNameText(), editPropertyInnerPanel.getLine1Text(),
														editPropertyInnerPanel.getLine2Text(), editPropertyInnerPanel.getCityText(), editPropertyInnerPanel.getCountyText(),
														editPropertyInnerPanel.getPostcodeText(), editPropertyInnerPanel.getNoOfRooms(), editPropertyInnerPanel.getFloorNo(),
														editPropertyInnerPanel.getPrice(), editPropertyInnerPanel.getMonthlyCharge());
								
									// Display success dialog.
									JOptionPane.showMessageDialog(null, "The property was successfully updated!", "Property Updated", JOptionPane.INFORMATION_MESSAGE);
								
									// Clear edit property panel inputs.
									editPropertyInnerPanel.clear();
									
									// Update view branches list with latest data.
									applyPropertiesFilter();	
									
									// Display the list of current branches.
									switchCards(viewPropertiesPanel, LIST_PROPERTIES_CARD);
								}
								else {
									// Display error dialog.
									JOptionPane.showMessageDialog(null, "The address cannot be blank.", "Error", JOptionPane.ERROR_MESSAGE);
								}
							}	
							else {
								// Display error dialog.
								JOptionPane.showMessageDialog(null, "The selling price cannot be less than 0 or is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}	
						else {
							// Display error dialog.
							JOptionPane.showMessageDialog(null, "The monthly charge cannot be less than 0 or is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						// Display error dialog.
						JOptionPane.showMessageDialog(null, "The floor number cannot be less than 1 or is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else {
				// Display error dialog.
				JOptionPane.showMessageDialog(null, "The amount of rooms cannot be less than 1 or is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			// Display error dialog.
			JOptionPane.showMessageDialog(null, "The property name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void sellProperty() {
		if (!propertiesList.isSelectionEmpty()) {
			if (!(model.getProperty(propertiesList.getSelectedIndex()).getSoldPrice() > 0)) {
				try {
					// Ask the user for the price that the property has sold for.
					Double soldPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "How much did this property sell for?", "Sell Property", JOptionPane.QUESTION_MESSAGE));
					
					if (soldPrice >= model.getProperty(propertiesList.getSelectedIndex()).getSellingPrice()) {
						// Sell property.
						model.sellProperty(propertiesList.getSelectedIndex(), soldPrice);
						
						// Update view branches list with latest data.
						applyPropertiesFilter();
						
						// Display success dialog.
						JOptionPane.showMessageDialog(null, "The selected property was successfully sold for £" + soldPrice + "!", "Property Sold", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						// Display error dialog.
						JOptionPane.showMessageDialog(null, "The selcted property cannot be sold for less than the current selling price.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (NumberFormatException e) {
					// Display error dialog.
					JOptionPane.showMessageDialog(null, "The price you have entered is not a number.\n\nPlease try again!", "Error", JOptionPane.ERROR_MESSAGE);
				}	
				catch (NullPointerException e) {
					
				}
			}
			else {
				// Display error dialog.
				JOptionPane.showMessageDialog(null, "The selected property has already been sold for £" + model.getProperty(propertiesList.getSelectedIndex()).getSoldPrice() +  ".", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			// Display error dialog.
			JOptionPane.showMessageDialog(null, "You have not selected a property.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void deleteProperty() {
		if (!propertiesList.isSelectionEmpty()) {
			// Display yes no dialog.
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected property?", "Delete Property", JOptionPane.YES_NO_OPTION);
			
			// Delete the branch if user clicked "Yes".
			if (choice == 0) {
				// Delete property from branch.
				model.deleteProperty(propertiesList.getSelectedIndex());

				// Update view branches list with latest data.
				applyPropertiesFilter();		
				
				// Display success dialog.
				JOptionPane.showMessageDialog(null, "The selected property was successfully deleted!", "Property Deleted", JOptionPane.INFORMATION_MESSAGE);
			}	
		}
		else {
			// Display error dialog.
			JOptionPane.showMessageDialog(null, "You have not selected a property.", "Error", JOptionPane.ERROR_MESSAGE);
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
	
	// Method for listing properties as specified by the user's filter choices.
	public void applyPropertiesFilter() {
		propertiesList.setListData(model.getPropertyChoices(propertyTypeDropdown.getSelectedItem().toString(), sellingTypeDropdown.getSelectedItem().toString(), model.getProperties()).toArray());
	}
	
	public void searchPropertyByAddress() {
		ArrayList<Property> properties = null;
		
		if (addressSearchPanel.getLine1Text().isEmpty() && addressSearchPanel.getLine2Text().isEmpty() && addressSearchPanel.getCityText().isEmpty() &&
			addressSearchPanel.getCountyText().isEmpty() && addressSearchPanel.getPostcodeText().isEmpty()) {
			// Update view properties list with latest data.
			properties = model.getProperties();
		}
		else if (!addressSearchPanel.getLine1Text().isEmpty() && addressSearchPanel.getLine2Text().isEmpty() && addressSearchPanel.getCityText().isEmpty() &&
				addressSearchPanel.getCountyText().isEmpty() && addressSearchPanel.getPostcodeText().isEmpty()) {
			// Update view properties list with latest data.
			properties = model.getPropertiesByAddress(addressSearchPanel.getLine1Text());
		}
		else if (addressSearchPanel.getLine1Text().isEmpty() && !addressSearchPanel.getLine2Text().isEmpty() && addressSearchPanel.getCityText().isEmpty() &&
				addressSearchPanel.getCountyText().isEmpty() && addressSearchPanel.getPostcodeText().isEmpty()) {
			// Update view properties list with latest data.
			properties = model.getPropertiesByAddress(addressSearchPanel.getLine2Text());
		}
		else if (addressSearchPanel.getLine1Text().isEmpty() && addressSearchPanel.getLine2Text().isEmpty() && !addressSearchPanel.getCityText().isEmpty() &&
				addressSearchPanel.getCountyText().isEmpty() && addressSearchPanel.getPostcodeText().isEmpty()) {
			// Update view properties list with latest data.
			properties = model.getPropertiesByAddress(addressSearchPanel.getCityText());
		}
		else if (addressSearchPanel.getLine1Text().isEmpty() && addressSearchPanel.getLine2Text().isEmpty() && addressSearchPanel.getCityText().isEmpty() &&
				!addressSearchPanel.getCountyText().isEmpty() && addressSearchPanel.getPostcodeText().isEmpty()) {
			// Update view properties list with latest data.
			properties = model.getPropertiesByAddress(addressSearchPanel.getCountyText());
		}
		else if (addressSearchPanel.getLine1Text().isEmpty() && addressSearchPanel.getLine2Text().isEmpty() && addressSearchPanel.getCityText().isEmpty() &&
				addressSearchPanel.getCountyText().isEmpty() && !addressSearchPanel.getPostcodeText().isEmpty()) {
			// Update view properties list with latest data.
			properties = model.getPropertiesByAddress(addressSearchPanel.getPostcodeText());
		}
		else if (!addressSearchPanel.getLine1Text().isEmpty() && !addressSearchPanel.getLine2Text().isEmpty() && addressSearchPanel.getCityText().isEmpty() &&
				addressSearchPanel.getCountyText().isEmpty() && addressSearchPanel.getPostcodeText().isEmpty()) {
			// Update view properties list with latest data.
			properties = model.getPropertiesByAddress(addressSearchPanel.getLine1Text(), addressSearchPanel.getLine2Text());
		}
		else if (!addressSearchPanel.getLine1Text().isEmpty() && !addressSearchPanel.getLine2Text().isEmpty() && !addressSearchPanel.getCityText().isEmpty() &&
				addressSearchPanel.getCountyText().isEmpty() && addressSearchPanel.getPostcodeText().isEmpty()) {
			// Update view properties list with latest data.
			properties = model.getPropertiesByAddress(addressSearchPanel.getLine1Text(), addressSearchPanel.getLine2Text(), addressSearchPanel.getCityText());
		}
		else if (!addressSearchPanel.getLine1Text().isEmpty() && !addressSearchPanel.getLine2Text().isEmpty() && !addressSearchPanel.getCityText().isEmpty() &&
				!addressSearchPanel.getCountyText().isEmpty() && addressSearchPanel.getPostcodeText().isEmpty()) {
			// Update view properties list with latest data.
			properties = model.getPropertiesByAddress(addressSearchPanel.getLine1Text(), addressSearchPanel.getLine2Text(), addressSearchPanel.getCityText(),
														addressSearchPanel.getCountyText());
		}
		else if (!addressSearchPanel.getLine1Text().isEmpty() && !addressSearchPanel.getLine2Text().isEmpty() && !addressSearchPanel.getCityText().isEmpty() &&
				!addressSearchPanel.getCountyText().isEmpty() && !addressSearchPanel.getPostcodeText().isEmpty()) {
			// Update view properties list with latest data.
			properties = model.getPropertiesByAddress(addressSearchPanel.getLine1Text(), addressSearchPanel.getLine2Text(), addressSearchPanel.getCityText(),
														addressSearchPanel.getCountyText(), addressSearchPanel.getPostcodeText());
		}
		else {
			properties = model.getProperties();
			JOptionPane.showMessageDialog(null, "The address search combination you are using is currently unsupported.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		propertiesList.setListData(model.getPropertyChoices(propertyTypeDropdown.getSelectedItem().toString(), sellingTypeDropdown.getSelectedItem().toString(), properties).toArray());
	}
	
	// Method for switching cards on a panel with the CardLayout layout.
	public void switchCards(Container parent, String name) {
		if (!name.equals(EDIT_PROPERTY_CARD)) {
			CardLayout cardLayout = (CardLayout) (parent.getLayout());
			cardLayout.show(parent, name);
		}
		else {
			if (!propertiesList.isSelectionEmpty()) {
				CardLayout cardLayout = (CardLayout) (parent.getLayout());
				cardLayout.show(parent, name);
				
				// Split string of selected property listing.
				String str = propertiesList.getSelectedValue().toString();
				String[] strSplit = str.split("-");
				String[] subStrSplit = strSplit[0].split(" ");
				
				// Retrieve the selected property's ID from subStrSplit[1].
				int selectedPropertyId = Integer.parseInt(subStrSplit[1]);
				
				//Property property = model.getProperty(propertiesList.getSelectedIndex());
				
				// Loop through all properties until property with matching ID is found.
				Property selectedProperty = null;
				for (Property property: model.getProperties()) {
					if (selectedPropertyId == property.getId()) {
						selectedProperty = property;
						break;
					}
				}
				
				if (selectedProperty.getClass().getName().equals(EditPropertyPanel.HOUSE)) {
					House house = (House) selectedProperty;
					editPropertyInnerPanel.fillFields(house.getName(), house.getNoOfRooms(), house.getNoOfFloors(), house.propertyHasGarden(),
							house.propertyHasGarage(), house.getSellingPrice(), house.getSoldPrice(), house.getAddressLine1(), 
							house.getAddressLine2(), house.getAddressCity(), house.getAddressCounty(), house.getAddressPostcode());
				}
				else if (selectedProperty.getClass().getName().equals(EditPropertyPanel.FLAT)) {
					Flat flat = (Flat) selectedProperty;
					editPropertyInnerPanel.fillFields(flat.getName(), flat.getNoOfRooms(), flat.getFloorNo(), flat.getMonthlyCharge(),
							flat.getSellingPrice(), flat.getSoldPrice(), flat.getAddressLine1(), flat.getAddressLine2(), 
							flat.getAddressCity(), flat.getAddressCounty(), flat.getAddressPostcode());
				}
				
				if (selectedProperty.getSoldPrice() > 0.0)
					editPropertyInnerPanel.propertySold(true);
				else
					editPropertyInnerPanel.propertySold(false);
					
			}
			else {
				// Display error dialog.
				JOptionPane.showMessageDialog(null, "You have not selected a property.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	// Method for returning a panel from the admin view.
	public JPanel getPanel(String panel) {
		JPanel returnedPanel = null;
		
		if (panel.equals(VIEW_PROPERTIES_PANEL)) {
			returnedPanel = viewPropertiesPanel;
		}
		else if (panel.equals(ADD_PROPERTY_PANEL)) {
			returnedPanel = addPropertyPanel;
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
