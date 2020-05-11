import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class BranchView extends View {
	private JLabel label;
	private JButton button, button2;
	private BranchController controller;
	private Branch model;
	
	public static final String PRINT_BRANCH_NAME = "Print Branch Name", ADD_USERS = "Add Users";
	
	public BranchView(BranchController controller, Branch model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		frame.setSize(500, 300);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		label = new JLabel("Branch View");
		button = new JButton(PRINT_BRANCH_NAME);
		button.addActionListener(controller);
		
		button2 = new JButton(ADD_USERS);
		button2.addActionListener(controller);
		
		frame.add(label);
		frame.add(button);
		frame.add(button2);
		frame.setVisible(true);
	}
	
	public void printBranchName() {
		System.out.println(model.getBranchName());
	}
	
	public void addProperty() {
		frame.setVisible(false);

		JFrame addPropertyFrame = new JFrame();
		addPropertyFrame.setSize(500, 300);
		addPropertyFrame.setLayout(new FlowLayout());
		
		addPropertyFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				addPropertyFrame.setVisible(false);
				frame.setVisible(true);
			}
		});
		
		addPropertyFrame.setVisible(true);
	}
	
	public void listProperties() {
		frame.setVisible(false);
		
		JFrame listPropertiesFrame = new JFrame();
		listPropertiesFrame.setSize(500, 300);
		listPropertiesFrame.setLayout(new FlowLayout());
		
		JList propertiesList = new JList(model.getProperties());
		
		listPropertiesFrame.setVisible(true);
	}
}
