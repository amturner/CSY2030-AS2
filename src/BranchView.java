import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BranchView extends View {
	private JLabel label;
	private JButton button, button2, button3;
	private BranchController controller;
	private Branch model;
	
	// Action Constants
	public static final String PRINT_BRANCH_NAME = "Print Branch Name", ADD_PROPERTY = "Add Property", LIST_PROPERTIES = "List Properties";
	
	public BranchView(BranchController controller, Branch model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		// Get screen size.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		// Set up frame.
		frame.setSize(500, 300);
		frame.setLocation((int) (screenSize.getWidth()-frame.getWidth())/2, (int) (screenSize.getHeight()-frame.getHeight())/2);
		frame.setTitle(frame.getTitle() + " - Branch Administration Area");
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		label = new JLabel("Branch View");
		button = new JButton(PRINT_BRANCH_NAME);
		button.addActionListener(controller);
		
		button2 = new JButton(ADD_PROPERTY);
		button2.addActionListener(controller);
		
		button3 = new JButton(LIST_PROPERTIES);
		button3.addActionListener(controller);
		
		frame.add(label);
		frame.add(button);
		frame.add(button2);
		frame.add(button3);
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
				addPropertyFrame.dispose();
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
		
		JList propertiesList = new JList(model.getProperties().toArray());
		propertiesList.setSelectionMode(JList.VERTICAL);
		propertiesList.setLayoutOrientation(JList.VERTICAL);
		propertiesList.setVisibleRowCount(-1);
		
		JScrollPane scrollPane = new JScrollPane(propertiesList);
		scrollPane.setPreferredSize(new Dimension(250, 80));
		
		listPropertiesFrame.add(scrollPane);
		
		listPropertiesFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				listPropertiesFrame.dispose();
				frame.setVisible(true);
			}
		});
		
		listPropertiesFrame.setVisible(true);
	}
}
