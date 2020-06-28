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
}
