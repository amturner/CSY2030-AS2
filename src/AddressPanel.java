import java.awt.*;
import javax.swing.*;

public class AddressPanel extends JPanel {
	private JLabel addressLabel, line1Label, line2Label, cityLabel, countyLabel, postcodeLabel;
	private JTextField line1Field, line2Field, cityField, countyField, postcodeField;
	
	public AddressPanel() {
		setupPanel();
	}
	
	private void setupPanel() {
		this.setPreferredSize(new Dimension(180, 125));
		line1Label = new JLabel("Line 1");
		line2Label = new JLabel("Line 2");
		cityLabel = new JLabel("Town/City");
		countyLabel = new JLabel("County");
		postcodeLabel = new JLabel("Postcode");
		line1Field = new JTextField(10);
		line2Field = new JTextField(10);
		cityField = new JTextField(10);
		countyField = new JTextField(10);
		postcodeField = new JTextField(10);
		this.add(line1Label);
		this.add(line1Field);
		this.add(line2Label);
		this.add(line2Field);
		this.add(cityLabel);
		this.add(cityField);
		this.add(countyLabel);
		this.add(countyField);
		this.add(postcodeLabel);
		this.add(postcodeField);
	}
	
	// Setter Methods
	public void setLine1Text(String text) {
		line1Field.setText(text);
	}
	
	public void setLine2Text(String text) {
		line2Field.setText(text);
	}
	
	public void setCityText(String text) {
		cityField.setText(text);
	}
	
	public void setCountyText(String text) {
		countyField.setText(text);
	}
	
	public void setPostcodeText(String text) {
		postcodeField.setText(text);
	}
	
	// Getter Methods
	public String getLine1Text() {
		return line1Field.getText();
	}
	
	public String getLine2Text() {
		return line2Field.getText();
	}
	
	public String getCityText() {
		return cityField.getText();
	}
	
	public String getCountyText() {
		return countyField.getText();
	}
	
	public String getPostcodeText() {
		return postcodeField.getText();
	}
	
	public boolean isAddressFilled() {
		if (!getLine1Text().isEmpty() && !getLine2Text().isEmpty() && !getCityText().isEmpty() && !getCountyText().isEmpty() && !getPostcodeText().isEmpty())
			return true;
		else
			return false;
	}
}
