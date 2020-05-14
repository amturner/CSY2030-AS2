import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class EditPropertyPanel extends JPanel {
	// Panels
	private JPanel editPropertyLeftPanel, editPropertyRightPanel;
	// Inner Panels
	private JPanel typePanel, typeInnerPanel, houseDetailsPanel, houseDetailsInnerPanel, houseDetailsInnerPanel2, flatDetailsPanel, flatDetailsInnerPanel, addressPanel;
	// Labels
	private JLabel propertyNameLabel, noOfRoomsLabel, typeLabel, houseDetailsLabel, noOfFloorsLabel, flatDetailsLabel, floorNoLabel, monthlyChargeLabel, sellingPriceLabel, soldPriceLabel, addressLabel;
	// Fields
	private JTextField nameField, noOfRoomsField, noOfFloorsField, floorNoField, monthlyChargeField, sellingPriceField, soldPriceField;
	// Radio Buttons
	private JRadioButton houseRadioButton, flatRadioButton;
	// Button Group
	private ButtonGroup radioButtonGroup;
	// Checkboxes
	private JCheckBox gardenCheckbox, garageCheckbox;
	// Buttons
	private JButton submitButton;
	
	private String panelType;
	
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
		this.setPreferredSize(new Dimension(475, 335));
		//// Edit Property - Inner Left Panel
		editPropertyLeftPanel = new JPanel();
		//frame.getWidth()/2
		editPropertyLeftPanel.setPreferredSize(new Dimension(240, 335));
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
		noOfFloorsField.setEnabled(false);
		floorNoField.setEnabled(false);
		monthlyChargeField.setEnabled(false);
		
		typePanel = new JPanel();
		typeInnerPanel = new JPanel();
		typePanel.setPreferredSize(new Dimension(110, 60));
		typePanel.add(typeLabel);
		
		radioButtonGroup = new ButtonGroup();
		houseRadioButton = new JRadioButton("House");
		flatRadioButton = new JRadioButton("Flat");
		houseRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					noOfFloorsField.setEnabled(true);
					gardenCheckbox.setEnabled(true);
					garageCheckbox.setEnabled(true);
				}
				else {
					noOfFloorsField.setEnabled(false);
					noOfFloorsField.setText("");
					gardenCheckbox.setEnabled(false);
					gardenCheckbox.setSelected(false);
					garageCheckbox.setEnabled(false);
					garageCheckbox.setSelected(false);
				}
			}
		});
		flatRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					floorNoField.setEnabled(true);
					monthlyChargeField.setEnabled(true);
				}
				else {
					floorNoField.setEnabled(false);
					floorNoField.setText("");
					monthlyChargeField.setEnabled(false);
					monthlyChargeField.setText("");
				}
			}
		});
		
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
		gardenCheckbox.setEnabled(false);
		garageCheckbox.setEnabled(false);
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
		editPropertyRightPanel.setPreferredSize(new Dimension(205, 335));
		sellingPriceLabel = new JLabel("Selling Price");
		soldPriceLabel = new JLabel("Sold Price");
		sellingPriceField = new JTextField(10);
		soldPriceField = new JTextField(10);
		soldPriceField.setEnabled(false);
		addressLabel = new JLabel("Address");
		addressPanel = new AddressPanel();
		submitButton = new JButton("");
		editPropertyRightPanel.add(sellingPriceLabel);
		editPropertyRightPanel.add(sellingPriceField);
		editPropertyRightPanel.add(soldPriceLabel);
		editPropertyRightPanel.add(soldPriceField);
		editPropertyRightPanel.add(addressLabel);
		editPropertyRightPanel.add(addressPanel);
		editPropertyRightPanel.add(submitButton);
		
		this.add(editPropertyLeftPanel, BorderLayout.WEST);
		this.add(editPropertyRightPanel, BorderLayout.EAST);
	}
	
	// Setter Methods
	public void fillFields(String name, int noOfRooms, int noOfFloors, boolean hasGarden, boolean hasGarage, Double sellingPrice, Double soldPrice, String line1, String line2, String city, String county, String postcode) {
		setSelectedType(HOUSE);
		nameField.setText(name);
		noOfRoomsField.setText(Integer.toString(noOfRooms));
		noOfFloorsField.setText(Integer.toString(noOfFloors));
		setGarden(hasGarden);
		setGarage(hasGarage);
		sellingPriceField.setText(Double.toString(sellingPrice));
		soldPriceField.setText(Double.toString(soldPrice));
		((AddressPanel) addressPanel).setLine1Text(line1);
		((AddressPanel) addressPanel).setLine2Text(line2);
		((AddressPanel) addressPanel).setCityText(city);
		((AddressPanel) addressPanel).setCountyText(county);
		((AddressPanel) addressPanel).setPostcodeText(postcode);
	}
	
	public void fillFields(String name, int noOfRooms, int floorNo, Double monthlyCharge, Double sellingPrice, Double soldPrice, String line1, String line2, String city, String county, String postcode) {
		setSelectedType(FLAT);
		nameField.setText(name);
		noOfRoomsField.setText(Integer.toString(noOfRooms));
		floorNoField.setText(Integer.toString(floorNo));
		monthlyChargeField.setText(Double.toString(monthlyCharge));
		sellingPriceField.setText(Double.toString(sellingPrice));
		soldPriceField.setText(Double.toString(soldPrice));
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
		panelType = type;
		
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
	
	public void propertySold(boolean value) {
		if (panelType != null) {
			if (value) {
				nameField.setEnabled(false);
				noOfRoomsField.setEnabled(false);
				noOfFloorsField.setEnabled(false);
				gardenCheckbox.setEnabled(false);
				garageCheckbox.setEnabled(false);
				floorNoField.setEnabled(false);
				monthlyChargeField.setEnabled(false);
				sellingPriceField.setEnabled(false);
				((AddressPanel) addressPanel).inputsEnabled(false);
				submitButton.setEnabled(false);	
			}
			else {
				nameField.setEnabled(true);
				noOfRoomsField.setEnabled(true);
				
				if (panelType.equals(HOUSE)) {
					noOfFloorsField.setEnabled(true);
					gardenCheckbox.setEnabled(true);
					garageCheckbox.setEnabled(true);
					floorNoField.setEnabled(false);
					monthlyChargeField.setEnabled(false);
				}
				else if (panelType.equals(FLAT)) {
					noOfFloorsField.setEnabled(false);
					gardenCheckbox.setEnabled(false);
					garageCheckbox.setEnabled(false);
					floorNoField.setEnabled(true);
					monthlyChargeField.setEnabled(true);
				}
				
				sellingPriceField.setEnabled(true);
				((AddressPanel) addressPanel).inputsEnabled(true);
				submitButton.setEnabled(true);	
			}	
		}
		else {
			System.out.println("The panel type has not yet been set.");
		}
	}
	
	public void clear() {
		// Clear Fields
		nameField.setText("");
		noOfRoomsField.setText("");
		noOfFloorsField.setText("");
		floorNoField.setText("");
		monthlyChargeField.setText("");
		sellingPriceField.setText("");
		soldPriceField.setText("");
		((AddressPanel) addressPanel).setLine1Text("");
		((AddressPanel) addressPanel).setLine2Text("");
		((AddressPanel) addressPanel).setCityText("");
		((AddressPanel) addressPanel).setCountyText("");
		((AddressPanel) addressPanel).setPostcodeText("");
		// Clear Radio Buttons
		houseRadioButton.setSelected(false);
		flatRadioButton.setSelected(false);
		// Clear Checkboxes
		gardenCheckbox.setSelected(false);
		garageCheckbox.setSelected(false);
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
			return Double.parseDouble(sellingPriceField.getText());
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
		return ((AddressPanel) addressPanel).isAddressFilled();
	}
}
