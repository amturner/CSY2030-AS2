import java.awt.event.ActionEvent;

public class AdminController extends Controller {
	private AdminModel model;
	
	public AdminController(AdminModel model) {
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		for (View view: views) {
			if (e.getActionCommand().equals(AdminView.LOGOUT)) {
				try {
					AdminView adminView = (AdminView) view;
					adminView.logout();
					break;
				}
				catch (Exception e1) {
					
				}
			}
			
			if (e.getActionCommand().contentEquals(AdminView.ADD_BRANCH)) {
				try {
					AdminView adminView = (AdminView) view;
					adminView.addBranch();
					break;
				}
				catch (Exception e1) {
					
				}
			}
		}
	}
}
