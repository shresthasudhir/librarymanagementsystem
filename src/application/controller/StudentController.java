package application.controller;

import application.model.Admin;
import application.model.Librarian;
import application.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentController {
	
	@FXML
	private TextField firstname;
	@FXML
	private TextField lastname;
	@FXML
	private TextField address;
	@FXML
	private TextField dateOfBirth;
	@FXML
	private TextField contactNumber;
	@FXML
	private TextField email;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	
	public void addStudent(ActionEvent event) throws Exception {
		String afirstName = firstname.getText();
		String aLastName = lastname.getText();
		String aAddress = address.getText();
		String aDateOfBirth = dateOfBirth.getText();
		int aContactNumber =  Integer.parseInt(contactNumber.getText().toString());
		String aEmail = email.getText();
		String aUserName = username.getText();
		String aPassword = password.getText();
		try {
			int saveStudentData = Student.saveStudent(afirstName, aLastName, aAddress, aDateOfBirth, aContactNumber, aUserName, aPassword,
					aEmail, 3);
			if (saveStudentData > 0) {
				Stage primaryStage = new Stage();
				((Node) event.getSource()).getScene().getWindow().hide();
				Librarian librarian = new Librarian();
				librarian.start(primaryStage);
				System.out.println("Successfully added Student");
			}
		} catch(Exception e) {
			System.out.println(e);
		}	
		
	}
	
	@FXML
	public void backToLibrarian(ActionEvent event) {
		Stage primaryStage = new Stage();
		((Node) event.getSource()).getScene().getWindow().hide();
		Librarian lib = new Librarian();
		lib.start(primaryStage);
	}
	
	

}
