import java.awt.event.ActionEvent;

public class BranchController extends Controller {
	private Branch model;
	
	public BranchController(Branch model) {
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getID());
		System.out.println(e.getActionCommand());
		
		for (View view: views) {
			BranchView branchView = (BranchView) view;
			if (e.getActionCommand().equals(BranchView.PRINT_BRANCH_NAME)) {
				branchView.printBranchName();	
			}
			
			if (e.getActionCommand().equals(BranchView.ADD_PROPERTY)) {
				branchView.addProperty();
			}
			
			if (e.getActionCommand().equals(BranchView.LIST_PROPERTIES)) {
				branchView.listProperties();
			}
		}
	}
}
