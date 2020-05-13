import java.awt.event.ActionEvent;

public class BranchController extends Controller {
	private BranchModel model;
	
	public BranchController(BranchModel model) {
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		for (View view: views) {
			if (e.getActionCommand().equals(SettingsPanel.LOGOUT)) {
				try {
					BranchView branchView = (BranchView) view;
					branchView.logout();
					break;
				}
				catch (Exception e1) {
					
				}
			}
			
			if (e.getActionCommand().equals(SettingsPanel.UPDATE_PASSWORD)) {
				try {
					BranchView branchView = (BranchView) view;
					branchView.updatePassword();
				}
				catch (Exception e1) {
					
				}
			}
			
			if (e.getActionCommand().equals(EditPropertyPanel.ADD_PROPERTY)) {
				try {
					BranchView branchView = (BranchView) view;
					branchView.addProperty();
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
