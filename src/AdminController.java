import java.awt.event.ActionEvent;

public class AdminController extends Controller {
	private AdminModel model;
	
	public AdminController(AdminModel model) {
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getID());
		
		for (View view: views) {
			AdminView adminView = (AdminView) view;
			adminView.notFalse();
		}
	}
}
