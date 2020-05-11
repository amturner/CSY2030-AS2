import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class AdminView extends View {
	private JLabel label;
	private AdminController controller;
	private AdminModel model;
	
	public AdminView(AdminController controller, AdminModel model) {
		this.controller = controller;
		this.model = model;
		
		this.controller.addView(this);
		
		frame.setSize(500, 300);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		label = new JLabel("Admin View");
		
		frame.add(label);
		frame.setVisible(true);
	}
	
	public void notFalse() {
		System.out.println(model.notFalse());	
	}
}
