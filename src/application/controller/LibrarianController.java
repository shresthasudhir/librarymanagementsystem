package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Date;

import application.model.Admin;
import application.model.Book;
import application.model.Librarian;
import application.model.Login;
import application.model.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AmbientLight;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LibrarianController {
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
	private TextField txtISBN;
	@FXML
	private TextField txtBookName;
	@FXML
	private TextField txtAuthorName;
	@FXML
	private TextField txtPublisher;

	@FXML
	private TextField txtIssueBookISBN;
	@FXML
	private TextField txtIssueStudentId;
	@FXML
	private TextField txtLibrarianId;
	@FXML
	private Label lblWelcome;
	@FXML
	private Label lblLibrarianId;

	@FXML
	private TextField librarianId;

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

	public static boolean isTextFieldEmpty(TextField txtfld) {
		boolean result = false;
		if (txtfld.getText() != null && !txtfld.getText().isEmpty()) {
			result = true;
		}
		return result;
	}

	public static boolean isTextFieldEmpty(TextField txtfld, Label lbl, String sValidationText) {
		boolean result = true;
		String c = null;
		if (!isTextFieldEmpty(txtfld)) {
			result = false;
			c = sValidationText;
		}
		lbl.setText(c);
		return result;
	}

	protected static boolean validateAddLibrarian(String firstname, String lastname) {
		boolean status = false;

		try {
			boolean boolFirstName; // to be continued...
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public void addLibrarin(ActionEvent event) throws Exception {
		Admin newLibrarian = new Librarian();

		boolean bfirstName = ValidationController.isTextFieldEmpty(firstname, lblFirstName, "First name is required.");
		boolean blastName = ValidationController.isTextFieldEmpty(lastname, lblLastName, "Last name is required.");
		boolean baddress = ValidationController.isTextFieldEmpty(address, lblAddress, "Address is required.");
		boolean bdateOfbirth = ValidationController.isDatePickerFieldEmpty(dateOfBirth, lblDOB,
				"Date Of Birth is required.");
		boolean bContanctNo = ValidationController.isTextFieldEmpty(contactNumber, lblContact,
				"Contact No is required.");
		boolean bemail = ValidationController.isTextFieldEmpty(email, lblEmail, "Email is required.");
		boolean buername = ValidationController.isTextFieldEmpty(username, lblUsername, "User name is required.");
		boolean bpassword = ValidationController.isTextFieldEmpty(password, lblPassword, "Password is required.");
		// boolean b = this.isTextFieldEmpty(password,
		// lblPassword,"Password is required.");

		if (bfirstName && blastName && baddress && bdateOfbirth && bContanctNo && bemail && buername && bpassword) {

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

			newLibrarian.setFirstname(afirstName);
			newLibrarian.setLastname(aLastName);
			newLibrarian.setAddress(aAddress);
			newLibrarian.setDateOfBirth(sqlDate);
			newLibrarian.setContactNumber(aContactNumber);
			newLibrarian.setEmail(aEmail);
			newLibrarian.setUsername(aUserName);
			newLibrarian.setPassword(aPassword);
			newLibrarian.setStatus(2);
			try {
				int saveLibraryData = newLibrarian.savaDatatoDataBase();
				if (saveLibraryData > 0) {
					Stage primaryStage = new Stage();
					((Node) event.getSource()).getScene().getWindow().hide();
					Admin admin = new Admin();
					admin.start(primaryStage);
					System.out.println("Successfully added librarian");
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	@FXML
	public void backToAdmin(ActionEvent event) {
		Stage primaryStage = new Stage();
		((Node) event.getSource()).getScene().getWindow().hide();
		Admin ad = new Admin();
		System.out.println(ad);
		ad.start(primaryStage);
	}

	@FXML
	public void back(ActionEvent event) {
		Stage primaryStage = new Stage();
		((Node) event.getSource()).getScene().getWindow().hide();
		Librarian lib = new Librarian();
		int libId = lib.getLibrarianId();
		System.out.println(libId);
		lib.startLibrarian(primaryStage, libId);
	}

	@FXML
	public void deleteLibrarian(ActionEvent event) {
		int aLibrarianId = Integer.parseInt(librarianId.getText().toString());
		try {
			int delete = Librarian.deleteLibrarian(aLibrarianId);
			if (delete > 0) {
				Stage primaryStage = new Stage();
				((Node) event.getSource()).getScene().getWindow().hide();
				Admin admin = new Admin();
				admin.start(primaryStage);
				System.out.println("Librarian " + aLibrarianId + " delete successfully");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@FXML
	protected void addStudent(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Student student = new Student();
		Stage primaryStage = new Stage();
		student.addStudent(primaryStage);
	}

	@FXML
	protected void addBookPage(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Book book = new Book();
		Stage primaryStage = new Stage();
		book.start(primaryStage);
	}

	@FXML
	protected void issueBookPage(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Book book = new Book();
		Stage primaryStage = new Stage();
		book.showIssueBookPage(primaryStage);
	}

	public void addBook(ActionEvent event) throws Exception {
		int isbn = Integer.parseInt(txtISBN.getText().toString());
		String bookName = txtBookName.getText();
		String authorName = txtAuthorName.getText();
		String publisher = txtPublisher.getText();
		try {
			int saveBookData = Book.saveBook(isbn, bookName, authorName, publisher);
			if (saveBookData > 0) {
				Stage primaryStage = new Stage();
				((Node) event.getSource()).getScene().getWindow().hide();
				Librarian librarian = new Librarian();
				librarian.start(primaryStage);
				System.out.println("Successfully book added.");
			}
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	public void issueBook(ActionEvent event) throws Exception {
		int isbn = Integer.parseInt(txtIssueBookISBN.getText().toString());
		int studentId = Integer.parseInt(txtIssueStudentId.getText().toString());

		try {
			int saveIssueBookData = Book.saveIssueBook(isbn, studentId);
			if (saveIssueBookData > 0) {
				Stage primaryStage = new Stage();
				((Node) event.getSource()).getScene().getWindow().hide();
				Librarian librarian = new Librarian();
				librarian.start(primaryStage);
				System.out.println("Successfully book issued.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@FXML
	public void logout(ActionEvent event) {

		((Node) event.getSource()).getScene().getWindow().hide();
		Login loginPage = new Login();
		Stage primaryStage = new Stage();
		loginPage.start(primaryStage);
	}

	public void deleteBook(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Book book = new Book();
		Stage primaryStage = new Stage();
		book.deleteBookByLibrarian(primaryStage);
	}
	public void deleteStudent(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Student student = new Student();
		Stage primaryStage = new Stage();
		student.deleteStudentById(primaryStage);
	}
	public void returnBook(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Book book = new Book();
		Stage primaryStage = new Stage();
		book.returnBookPage(primaryStage);
	}
}
