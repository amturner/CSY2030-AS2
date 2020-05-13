import java.awt.event.ActionEvent;

public class BranchController extends Controller {
	private BranchModel model;
	
	public BranchController(BranchModel model) {
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getID());
		System.out.println(e.getActionCommand());
		
		for (View view: views) {
			BranchView branchView = (BranchView) view;
		}
	}
}
