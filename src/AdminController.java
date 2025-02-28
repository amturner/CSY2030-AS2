import java.awt.event.ActionEvent;

public class AdminController extends Controller {
	private Administrator model;
	
	public AdminController(Administrator model) {
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		for (View view: views) {
			if (e.getActionCommand().equals(SettingsPanel.LOGOUT)) {
				try {
					AdminView adminView = (AdminView) view;
					adminView.logout();
					break;
				}
				catch (Exception e1) {
					
				}
			}
			
			if (e.getActionCommand().equals(EditBranchPanel.ADD_BRANCH)) {
				try {
					AdminView adminView = (AdminView) view;
					adminView.addBranch();
					break;
				}
				catch (Exception e1) {
					
				}
			}
			
			if (e.getActionCommand().equals(AdminView.VIEW_BRANCHES)) {
				try {
					AdminView adminView = (AdminView) view;
					adminView.switchCards(adminView.getPanel(AdminView.VIEW_BRANCHES_PANEL), AdminView.LIST_BRANCHES_CARD);
				}
				catch (Exception e1) {

				}
			}
			
			if (e.getActionCommand().equals(AdminView.EDIT_BRANCH)) {
				try {
					AdminView adminView = (AdminView) view;
					adminView.switchCards(adminView.getPanel(AdminView.VIEW_BRANCHES_PANEL), AdminView.EDIT_BRANCH_CARD);
				}
				catch (Exception e1) {
					
				}
			}
			
			if (e.getActionCommand().equals(EditBranchPanel.UPDATE_BRANCH)) {
				try {
					AdminView adminView = (AdminView) view;
					adminView.updateBranch();
				}
				catch (Exception e1) {
					
				}
			}
			
			if (e.getActionCommand().contentEquals(SettingsPanel.UPDATE_PASSWORD)) {
				try {
					AdminView adminView = (AdminView) view;
					adminView.updatePassword();
				}
				catch (Exception e1) {
					
				}
			}
			
			if (e.getActionCommand().equals(AdminView.DELETE_BRANCH)) {
				try {
					AdminView adminView = (AdminView) view;
					adminView.deleteBranch();
					break;
				}
				catch (Exception e1) {

				}
			}
		}
	}
}
