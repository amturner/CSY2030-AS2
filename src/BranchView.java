import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BranchView extends View {
	private BranchController controller;
	private BranchModel model;
	
	// Tabbed Pane
	private JTabbedPane tabbedPane = new JTabbedPane();
	// Main Panels
	private JPanel viewPropertiesPanel, addPropertyPanel, settingsPanel;
	
	// View Properties
	// Panels
	private JPanel listPropertiesPanel, editHousePanel, editFlatPanel;
	// listPropertiesPanel - Inner Panels
	private JPanel listPropertiesLeftPanel, listPropertiesRightPanel;
	// listPropertiesLeftPanel - Inner Panels
	private JPanel listPropertiesButtonPanel;
	// listPropertiesRightPanel - Inner Panels
	private JPanel filterPanel, addressSearchPanel, propertyTypePanel, sellingTypePanel;
	// List
	private JList propertiesList;
	private JScrollPane scrollPane;
	// Labels
	private JLabel propertyListingsLabel, propertyTypeLabel, sellingTypeLabel, addressSearchLabel, line1SearchLabel, line2SearchLabel, citySearchLabel, countySearchLabel, postcodeSearchLabel;
	// Dropdowns
	private JComboBox propertyTypeDropdown, sellingTypeDropdown;
	// Fields
	private JTextField line1SearchField, line2SearchField, citySearchField, countySearchField, postcodeSearchField;
	// Buttons
	private JButton editPropertyButton, updatePropertyButton, deletePropertyButton, sellPropertyButton, applyFilterButton, searchButton;

	// Add New Property
	// Panels
	private JPanel addPropertyLeftPanel, addPropertyRightPanel;
	// Labels
	private JLabel propertyNameLabel, noOfRoomsLabel, typeLabel, houseDetailsLabel, noOfFloorsLabell, flatDetailsLabel, floorNoLabel, monthlyChargeLabel, priceLabel, line1Label, line2Label, cityLabel, countyLabel, postcodeLabel;
	// Fields
	private JTextField nameField, noOfRoomsField, noOfFloorsField, floorNoField, monthlyChargeField, priceField, line1Field, line2Field, cityField, countyField, postcodeField;
	// Radio Buttons
	private JRadioButton houseRadioButton, flatRadioButton;
	// Checkboxes
	private JCheckBox gardenCheckbox, garageCheckbox;
	// Buttons
	private JButton addPropertyButton;
	
	// Settings
	// Panels
	private JPanel logoutPanel, newPasswordPanel;
	// Labels
	private JLabel logoutLabel, newPasswordLabel;
	// Fields
	private JPasswordField newPasswordField;
	// Buttons
	private JButton logoutButton, updatePasswordButton;
	
	
	// Action Constants
	public static final String PRINT_BRANCH_NAME = "Print Branch Name", ADD_PROPERTY = "Add Property", LIST_PROPERTIES = "List Properties";
	
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
		propertiesList = new JList(model.getPropertyChoices());
		propertiesList.setSelectionMode(JList.VERTICAL);
		propertiesList.setLayoutOrientation(JList.VERTICAL);
		propertiesList.setVisibleRowCount(-1);
		scrollPane = new JScrollPane(propertiesList);
		scrollPane.setPreferredSize(new Dimension(215, 200));
		listPropertiesButtonPanel = new JPanel();
		editPropertyButton = new JButton("Edit");
		deletePropertyButton = new JButton("Delete");
		sellPropertyButton = new JButton("Sell");
		listPropertiesButtonPanel.add(editPropertyButton);
		listPropertiesButtonPanel.add(deletePropertyButton);
		listPropertiesButtonPanel.add(sellPropertyButton);

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
		applyFilterButton = new JButton("Apply Filter");
		propertyTypePanel.add(propertyTypeLabel, BorderLayout.NORTH);
		propertyTypePanel.add(propertyTypeDropdown, BorderLayout.SOUTH);
		sellingTypePanel.add(sellingTypeLabel, BorderLayout.NORTH);
		sellingTypePanel.add(sellingTypeDropdown, BorderLayout.SOUTH);
		filterPanel.add(propertyTypePanel, BorderLayout.WEST);
		filterPanel.add(sellingTypePanel, BorderLayout.EAST);
		filterPanel.add(applyFilterButton, BorderLayout.SOUTH);
		//////// List Properties Panel - Inner Right - Address Search Elements
		addressSearchPanel = new JPanel();
		addressSearchPanel.setPreferredSize(new Dimension(180, 160));
		addressSearchLabel = new JLabel("Address Search");
		line1SearchLabel = new JLabel("Line 1");
		line2SearchLabel = new JLabel("Line 2");
		citySearchLabel = new JLabel("Town/City");
		countySearchLabel = new JLabel("County");
		postcodeSearchLabel = new JLabel("Postcode");
		line1SearchField = new JTextField(10);
		line2SearchField = new JTextField(10);
		citySearchField = new JTextField(10);
		countySearchField = new JTextField(10);
		postcodeSearchField = new JTextField(10);
		searchButton = new JButton("Search");
		addressSearchPanel.add(line1SearchLabel);
		addressSearchPanel.add(line1SearchField);
		addressSearchPanel.add(line2SearchLabel);
		addressSearchPanel.add(line2SearchField);
		addressSearchPanel.add(citySearchLabel);
		addressSearchPanel.add(citySearchField);
		addressSearchPanel.add(countySearchLabel);
		addressSearchPanel.add(countySearchField);
		addressSearchPanel.add(postcodeSearchLabel);
		addressSearchPanel.add(postcodeSearchField);
		addressSearchPanel.add(searchButton, BorderLayout.SOUTH);
		
		// Add elements to listPropertiesRightPanel.
		listPropertiesRightPanel.add(filterPanel);
		listPropertiesRightPanel.add(addressSearchLabel);
		listPropertiesRightPanel.add(addressSearchPanel);

		// Add inner panels to main list properties panel.
		listPropertiesPanel.add(listPropertiesLeftPanel, BorderLayout.WEST);
		listPropertiesPanel.add(listPropertiesRightPanel, BorderLayout.EAST);
		
		// Add list properties panel to view properties panel.
		viewPropertiesPanel.add(listPropertiesPanel);
		
		/*
		//// Edit House Panel
		editHousePanel = new JPanel();
		editHousePanel.setLayout(new CardLayout());
		editHousePanel.setPreferredSize(new Dimension(500, 300));
		
		//// Edit Flat Panel
		editFlatPanel = new JPanel();
		editFlatPanel.setLayout(new CardLayout());
		editFlatPanel.setPreferredSize(new Dimension(500, 300));
				*/
		
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
		//frame.pack();
		frame.setVisible(true);
	}
}
