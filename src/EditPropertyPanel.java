import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditPropertyPanel extends JPanel {
	// Panels
	private JPanel editPropertyLeftPanel, editPropertyRightPanel;
	// Inner Panels
	private JPanel typePanel, typeInnerPanel, houseDetailsPanel, houseDetailsInnerPanel, houseDetailsInnerPanel2, flatDetailsPanel, flatDetailsInnerPanel, addressPanel;
	// Labels
	private JLabel propertyNameLabel, noOfRoomsLabel, typeLabel, houseDetailsLabel, noOfFloorsLabel, flatDetailsLabel, floorNoLabel, monthlyChargeLabel, priceLabel, addressLabel;
	// Fields
	private JTextField nameField, noOfRoomsField, noOfFloorsField, floorNoField, monthlyChargeField, priceField;
	// Radio Buttons
	private JRadioButton houseRadioButton, flatRadioButton;
	// Button Group
	private ButtonGroup radioButtonGroup;
	// Checkboxes
	private JCheckBox gardenCheckbox, garageCheckbox;
	// Buttons
	private JButton submitButton;
	
	// Type Constants
	public static final String HOUSE = "House", FLAT = "Flat";
	// Action Constants
	public static final String ADD_PROPERTY = "Add Property", UPDATE_PROPERTY = "Update Property";
	
	public EditPropertyPanel(String type) {
		setupPanel();
		
		if (type.equals(ADD_PROPERTY)) {
			submitButton.setText(ADD_PROPERTY);	
		}
		else if (type.equals(UPDATE_PROPERTY)) {
			houseRadioButton.setEnabled(false);
			flatRadioButton.setEnabled(false);
			submitButton.setText(UPDATE_PROPERTY);	
		}
	}
	
	private void setupPanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(475, 300));
		//// Edit Property - Inner Left Panel
		editPropertyLeftPanel = new JPanel();
		//frame.getWidth()/2
		editPropertyLeftPanel.setPreferredSize(new Dimension(240, 275));
		propertyNameLabel = new JLabel("Property Name");
		noOfRoomsLabel = new JLabel("No. Of Rooms");
		typeLabel = new JLabel("Type");
		houseDetailsLabel = new JLabel("House Details");
		noOfFloorsLabel = new JLabel("No. Of Floors");
		flatDetailsLabel = new JLabel("Flat Details");
		floorNoLabel = new JLabel("Floor No.");
		monthlyChargeLabel = new JLabel("Monthly Charge");
		nameField = new JTextField(10);
		noOfRoomsField = new JTextField(10);
		noOfFloorsField = new JTextField(10);
		floorNoField = new JTextField(10);
		monthlyChargeField = new JTextField(10);
		
		typePanel = new JPanel();
		typeInnerPanel = new JPanel();
		typePanel.setPreferredSize(new Dimension(110, 60));
		typePanel.add(typeLabel);
		radioButtonGroup = new ButtonGroup();
		houseRadioButton = new JRadioButton("House");
		flatRadioButton = new JRadioButton("Flat");
		radioButtonGroup.add(houseRadioButton);
		radioButtonGroup.add(flatRadioButton);
		typeInnerPanel.add(houseRadioButton);
		typeInnerPanel.add(flatRadioButton);
		typePanel.add(typeInnerPanel);
		
		houseDetailsPanel = new JPanel();
		houseDetailsInnerPanel = new JPanel();
		houseDetailsInnerPanel2 = new JPanel();
		houseDetailsPanel.setPreferredSize(new Dimension(190, 90));
		houseDetailsPanel.add(houseDetailsLabel);
		gardenCheckbox = new JCheckBox("Garden");
		garageCheckbox = new JCheckBox("Garage");
		houseDetailsInnerPanel.add(noOfFloorsLabel);
		houseDetailsInnerPanel.add(noOfFloorsField);
		houseDetailsInnerPanel2.add(gardenCheckbox);
		houseDetailsInnerPanel2.add(garageCheckbox);
		houseDetailsPanel.add(houseDetailsInnerPanel);
		houseDetailsPanel.add(houseDetailsInnerPanel2);
		
		flatDetailsPanel = new JPanel();
		flatDetailsInnerPanel = new JPanel();
		flatDetailsPanel.setPreferredSize(new Dimension(215, 80));
		flatDetailsInnerPanel.setPreferredSize(new Dimension(215, 80));
		flatDetailsPanel.add(flatDetailsLabel);
		flatDetailsInnerPanel.add(floorNoLabel);
		flatDetailsInnerPanel.add(floorNoField);
		flatDetailsInnerPanel.add(monthlyChargeLabel);
		flatDetailsInnerPanel.add(monthlyChargeField);
		flatDetailsPanel.add(flatDetailsInnerPanel);
		
		editPropertyLeftPanel.add(propertyNameLabel);
		editPropertyLeftPanel.add(nameField);
		editPropertyLeftPanel.add(noOfRoomsLabel);
		editPropertyLeftPanel.add(noOfRoomsField);
		editPropertyLeftPanel.add(typePanel);
		editPropertyLeftPanel.add(houseDetailsPanel);
		editPropertyLeftPanel.add(flatDetailsPanel);
		//// Edit Property - Inner Right Panel
		editPropertyRightPanel = new JPanel();
		editPropertyRightPanel.setPreferredSize(new Dimension(205, 275));
		priceLabel = new JLabel("Price");
		priceField = new JTextField(10);
		addressLabel = new JLabel("Address");
		addressPanel = new AddressPanel();
		submitButton = new JButton("");
		editPropertyRightPanel.add(priceLabel);
		editPropertyRightPanel.add(priceField);
		editPropertyRightPanel.add(addressLabel);
		editPropertyRightPanel.add(addressPanel);
		editPropertyRightPanel.add(submitButton);
		
		this.add(editPropertyLeftPanel, BorderLayout.WEST);
		this.add(editPropertyRightPanel, BorderLayout.EAST);
	}
	
	// Setter Methods
	public void fillFields(String name, int noOfRooms, int noOfFloors, boolean hasGarden, boolean hasGarage, Double price, String line1, String line2, String city, String county, String postcode) {
		setSelectedType(HOUSE);
		nameField.setText(name);
		noOfRoomsField.setText(Integer.toString(noOfRooms));
		noOfFloorsField.setText(Integer.toString(noOfFloors));
		setGarden(hasGarden);
		setGarage(hasGarage);
		priceField.setText(Double.toString(price));
		((AddressPanel) addressPanel).setLine1Text(line1);
		((AddressPanel) addressPanel).setLine2Text(line2);
		((AddressPanel) addressPanel).setCityText(city);
		((AddressPanel) addressPanel).setCountyText(county);
		((AddressPanel) addressPanel).setPostcodeText(postcode);
	}
	
	public void fillFields(String name, int noOfRooms, int floorNo, Double monthlyCharge, Double price, String line1, String line2, String city, String county, String postcode) {
		setSelectedType(FLAT);
		nameField.setText(name);
		noOfRoomsField.setText(Integer.toString(noOfRooms));
		floorNoField.setText(Integer.toString(floorNo));
		monthlyChargeField.setText(Double.toString(monthlyCharge));
		priceField.setText(Double.toString(price));
		((AddressPanel) addressPanel).setLine1Text(line1);
		((AddressPanel) addressPanel).setLine2Text(line2);
		((AddressPanel) addressPanel).setCityText(city);
		((AddressPanel) addressPanel).setCountyText(county);
		((AddressPanel) addressPanel).setPostcodeText(postcode);
	}
	
	public void setGarden(boolean value) {
		gardenCheckbox.setSelected(value);
	}
	
	public void setGarage(boolean value) {
		garageCheckbox.setSelected(value);
	}
	
	public void setSelectedType(String type) {
		if (type.equals(HOUSE)) {
			houseRadioButton.setSelected(true);
			flatRadioButton.setSelected(false);
		}
		else if (type.equals(FLAT)) {
			houseRadioButton.setSelected(false);
			flatRadioButton.setSelected(true);
			setGarden(false);
			setGarage(false);
		}
	}
	
	public void addActionListener(ActionListener listener) {
		submitButton.addActionListener(listener);
	}
	
	// Getter Methods
	public String getNameText() {
		return nameField.getText();
	}
	
	public int getNoOfRooms() {
		try {
			return Integer.parseInt(noOfRoomsField.getText());
		}
		catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public int getNoOfFloors() {
		try {
			return Integer.parseInt(noOfFloorsField.getText());
		}
		catch (NumberFormatException e) {
			return 0;
		}	
	}
	
	public int getFloorNo() {
		try {
			return Integer.parseInt(floorNoField.getText());
		}
		catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public boolean getGarden() {
		if (gardenCheckbox.isSelected())
			return true;
		else
			return false;
	}
	
	public boolean getGarage() {
		if (garageCheckbox.isSelected())
			return true;
		else
			return false;
	}
	
	public Double getPrice() {
		try {
			return Double.parseDouble(priceField.getText());
		}
		catch (NumberFormatException e) {
			return 0.0;
		}
	}
	
	public Double getMonthlyCharge() {
		try {
			return Double.parseDouble(monthlyChargeField.getText());
		}
		catch (NumberFormatException e) {
			return 0.0;
		}
	}
	
	public String getLine1Text() {
		return ((AddressPanel) addressPanel).getLine1Text();
	}
	
	public String getLine2Text() {
		return ((AddressPanel) addressPanel).getLine2Text();
	}
	
	public String getCityText() {
		return ((AddressPanel) addressPanel).getCityText();
	}

	public String getCountyText() {
		return ((AddressPanel) addressPanel).getCountyText();
	}
	
	public String getPostcodeText() {
		return ((AddressPanel) addressPanel).getPostcodeText();
	}

	public boolean isRadioButtonSelected() {
		if (houseRadioButton.isSelected() || flatRadioButton.isSelected())
			return true;
		else
			return false;
	}
	
	public boolean isCheckboxSelected() {
		if (gardenCheckbox.isSelected() || garageCheckbox.isSelected())
			return true;
		else
			return false;
	}
	
	public String getSelectedType() {
		if (houseRadioButton.isSelected()) {
			return HOUSE;
		}
		else if (flatRadioButton.isSelected()) {
			return FLAT;
		}
		else {
			return null;
		}
	}
	
	public boolean isAddressFilled() {
		if (!getLine1Text().isEmpty() && !getLine2Text().isEmpty() && !getCityText().isEmpty() && !getCountyText().isEmpty() && !getPostcodeText().isEmpty())
			return true;
		else
			return false;
	}
}
