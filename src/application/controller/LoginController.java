package application.controller;

import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {
	public LoginController() {
	}

	@FXML
	private TextField userName;
	@FXML
	private PasswordField passwordField;

	@FXML
	protected void signUpButton(ActionEvent event) throws Exception {
		try {
			String username = userName.getText();
			String password = passwordField.getText();
			if (User.validateUser(username, password)) {
				int status = User.getUserStatus(username);
				if (status == 1) {
					// do admin stuff
					AdminController.main(new String[] {});
				} else if (status == 2) {
					// do librarian stuff
				} else if (status == 3) {
					// do student stuff
				} else {
					//do something specail
				}
			}

		} catch (Exception e) {
			System.out.println("invalid username or passport");
		}

	}
}
