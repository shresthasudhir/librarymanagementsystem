package application.controller;

import java.time.LocalDate;
import java.util.Date;

import application.model.Admin;
import application.model.Librarian;
import application.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
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
	private DatePicker dateOfBirth;
	@FXML
	private TextField contactNumber;
	@FXML
	private TextField email;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;

	public void addStudent(ActionEvent event) throws Exception {
		Student newStudent = new Student();
		String afirstName = firstname.getText();
		String aLastName = lastname.getText();
		String aAddress = address.getText();

		LocalDate aDateOfBirth = dateOfBirth.getValue();
		Date date = java.sql.Date.valueOf(aDateOfBirth);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		int aContactNumber = Integer.parseInt(contactNumber.getText().toString());
		String aEmail = email.getText();
		String aUserName = username.getText();
		String aPassword = password.getText();

		newStudent.setFirstname(afirstName);
		newStudent.setLastname(aLastName);
		newStudent.setAddress(aAddress);
		newStudent.setDateOfBirth(sqlDate);
		newStudent.setContactNumber(aContactNumber);
		newStudent.setEmail(aEmail);
		newStudent.setUsername(aUserName);
		newStudent.setPassword(aPassword);
		newStudent.setStatus(3);
		try {
			int saveStudentData = newStudent.saveDatatoDataBase();
			if (saveStudentData > 0) {
				Stage primaryStage = new Stage();
				((Node) event.getSource()).getScene().getWindow().hide();
				Librarian librarian = new Librarian();
				librarian.start(primaryStage);
				System.out.println("Successfully added Student");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
