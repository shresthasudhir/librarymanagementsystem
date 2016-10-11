package application.controller;

import application.model.Admin;
import application.model.Librarian;
import application.model.Login;
import application.model.Student;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
				Stage primaryStage = new Stage();
				if (status == 1) {
					((Node) event.getSource()).getScene().getWindow().hide();
					Admin admin = new Admin();
					admin.start(primaryStage);

				} else if (status == 2) {
					// do librarian stuff
					((Node) event.getSource()).getScene().getWindow().hide();
					Librarian librarian = new Librarian();
					librarian.start(primaryStage);
								
				} else if (status == 3) {
					// do student stuff
					((Node) event.getSource()).getScene().getWindow().hide();
					Student student = new Student();
					student.start(primaryStage);
				} else {
					// do something special
					
				}
			}

		} catch (Exception e) {
			System.out.println("invalid username or passport");
			e.printStackTrace();
		}

	}
}
