import java.awt.event.ActionEvent;

public class BranchController extends Controller {
	private Branch model;
	
	public BranchController(Branch model) {
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
			
			if (e.getActionCommand().equals(BranchView.APPLY_FILTER)) {
				try {
					BranchView branchView = (BranchView) view;
					branchView.applyPropertiesFilter();
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if (e.getActionCommand().equals(BranchView.ADDRESS_SEARCH)) {
				try {
					BranchView branchView = (BranchView) view;
					branchView.searchPropertyByAddress();
				}
				catch (Exception e1) {
					e1.printStackTrace();
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
			
			if (e.getActionCommand().equals(BranchView.VIEW_PROPERTIES)) {
				try {
					BranchView branchView = (BranchView) view;
					branchView.switchCards(branchView.getPanel(BranchView.VIEW_PROPERTIES_PANEL), BranchView.LIST_PROPERTIES_CARD);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if (e.getActionCommand().equals(BranchView.EDIT_PROPERTY)) {
				try {
					BranchView branchView = (BranchView) view;
					branchView.switchCards(branchView.getPanel(BranchView.VIEW_PROPERTIES_PANEL), BranchView.EDIT_PROPERTY_CARD);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if (e.getActionCommand().equals(EditPropertyPanel.UPDATE_PROPERTY)) {
				try {
					BranchView branchView = (BranchView) view;
					branchView.updateProperty();
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if (e.getActionCommand().equals(BranchView.SELL_PROPERTY)) {
				try {
					BranchView branchView = (BranchView) view;
					branchView.sellProperty();
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
			if (e.getActionCommand().equals(BranchView.DELETE_PROPERTY)) {
				try {
					BranchView branchView = (BranchView) view;
					branchView.deleteProperty();
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
