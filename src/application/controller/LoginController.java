package application.controller;

import application.model.Admin;
import application.model.Librarian;
import application.model.Student;

import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	public TextField userName;
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private Label errorMessageLabel;

	@FXML
	protected void signUpButton(ActionEvent event) throws Exception {
		try {
			String username = userName.getText();
			String password = passwordField.getText();
			if (User.validateUser(username, password)) {
				int status = User.getUserStatus(username);
				int userId = User.getUserId(username);
	
				Stage primaryStage = new Stage();
				if (status == 1) {
					((Node) event.getSource()).getScene().getWindow().hide();
					Admin admin = new Admin();
					admin.start(primaryStage);

				} else if (status == 2) {
					((Node) event.getSource()).getScene().getWindow().hide();
					Librarian librarian = new Librarian();
					librarian.startLibrarian(primaryStage, userId);

				} else if (status == 3) {
					((Node) event.getSource()).getScene().getWindow().hide();
					Student student = new Student();
					student.startStudent(primaryStage,userId);

				} else if(status == 4){
					// do something special
					//errorMessageLabel.setFill(Color.FIREBRICK);
					errorMessageLabel.setText("Your Library Subcription Expired");

				}
			}else{
				errorMessageLabel.setText("Username or Passport didnt Matched");
			}

		} catch (Exception e) {
			//System.out.println("invalid username or passport");
			errorMessageLabel.setText("Invalid Username or Passport");
			e.printStackTrace();
		}

	}
}
