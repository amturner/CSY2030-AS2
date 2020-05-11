import java.awt.event.*;
import java.util.ArrayList;

public class LoginController extends Controller {
	private LoginModel model;

	public LoginController(LoginModel model) {
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		for (View view: views) {
			try {
				LoginView loginView = (LoginView) view;
				loginView.login();
				break;
			}
			catch (Exception e1) {
				
			}
		}
	}
}
