package application.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import application.model.Admin;
import application.model.Book;
import application.model.Librarian;
import application.model.Login;
import application.model.Student;
import application.views.SearchStudent;
import application.views.StudentTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import validationsfxml.Popup;
import validationsfxml.ValidationController;

public class StudentController{

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
	
	@FXML
	Label lblFirstName;
	@FXML
	Label lblLastName;
	@FXML
	Label lblAddress;
	@FXML
	Label lblDOB;
	@FXML
	Label lblContact;
	@FXML
	Label lblEmail;
	@FXML
	Label lblUsername;
	@FXML
	Label lblPassword;

	private TextField studentId;

	public void addStudent(ActionEvent event) throws Exception {
		Student newStudent = new Student();
		
		boolean bfirstName = ValidationController.isTextFieldEmpty(firstname, lblFirstName, "First name is required.");
		boolean blastName = ValidationController.isTextFieldEmpty(lastname, lblLastName, "Last name is required.");
		boolean baddress = ValidationController.isTextFieldEmpty(address, lblAddress, "Address is required.");
		boolean bdateOfbirth = ValidationController.isDatePickerFieldEmpty(dateOfBirth, lblDOB,"Date Of Birth is required.");
		boolean bContanctNo = ValidationController.isTextFieldEmpty(contactNumber, lblContact,
				"Contact No is required.");
		boolean bemail = ValidationController.isTextFieldEmpty(email, lblEmail, "Email is required.");
		boolean buername = ValidationController.isTextFieldEmpty(username, lblUsername, "User name is required.");
		boolean bpassword = ValidationController.isTextFieldEmpty(password, lblPassword, "Password is required.");
		// boolean b = this.isTextFieldEmpty(password,
		// lblPassword,"Password is required.");
		
		boolean vemail = true;
		boolean vcontactNum = true;
		if (bemail) {
			vemail = ValidationController.validateEmail(email.getText());
		}
		
		if (bContanctNo) {
			String text = contactNumber.getText();
			//int num = Integer.parseInt(text);
			vcontactNum = ValidationController.validateContactNumber(text);

		}
		
if (bfirstName && blastName && baddress && bdateOfbirth && vcontactNum && vemail && buername && bpassword) {
			
			String afirstName = firstname.getText();
			String aLastName = lastname.getText();
			String aAddress = address.getText();
			LocalDate aDateOfBirth = dateOfBirth.getValue();
			Date date = java.sql.Date.valueOf(aDateOfBirth);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			long aContactNumber = Long.parseLong(contactNumber.getText().toString());

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
					Popup.getStudentSavedNotification();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	@FXML
	public void seeBookDueDate(ActionEvent event) throws Exception {
		StudentTable stuDyanamicTable = new StudentTable();
		Stage primaryStage = new Stage();
		stuDyanamicTable.start(primaryStage);
	}
	
	@FXML
	public void backToLibrarian(ActionEvent event) {
		Stage primaryStage = new Stage();
		((Node) event.getSource()).getScene().getWindow().hide();
		Librarian lib = new Librarian();
		lib.start(primaryStage);
	}
	
	@FXML
	public void logout(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Login loginPage = new Login();
		Stage primaryStage = new Stage();
		loginPage.start(primaryStage);
	}

	public void deleteStudentWithId(ActionEvent event) {
		int aStudentId = Integer.parseInt(studentId.getText().toString());
		try {
			int delete = Student.deleteStudentById(aStudentId);
			if (delete > 0) {
				Stage primaryStage = new Stage();
				((Node) event.getSource()).getScene().getWindow().hide();
				Librarian librarian = new Librarian();
				librarian.start(primaryStage);
				System.out.println("Student ID " + aStudentId + " delete successfully");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	@FXML
	private TextField filterField;
	public  void searchStudent(ActionEvent event) {
		String afilterField = filterField.getText();
		SearchStudent sh = new SearchStudent();
		sh.searchStudent(afilterField);
	}

}
