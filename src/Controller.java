import java.awt.event.*;
import java.util.ArrayList;

public abstract class Controller implements ActionListener {
	protected ArrayList<View> views = new ArrayList<View>();
	
	public void addView(View view) {
		views.add(view);
	}
	
	public void removeView(int index) {
		views.remove(index);
	}
	
	public void listViewIndexes() {
		for (int i=0; i<views.size(); i++) {
			System.out.println("Index: " + views.indexOf(views.get(i)) + ", Value: " + views.get(i));
		}
	}
}
