import javax.swing.*;

public abstract class View {
	protected int id;
	protected JFrame frame;
	protected static int count = 0;
	
	public View() {
		id = count;
		count = count + 1;
		frame = new JFrame();
		frame.setTitle("National Property Sales System");
	}
}
