package application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import application.model.Admin;
import application.model.Book;
import application.model.Librarian;
import application.model.Student;

import javafx.event.ActionEvent;

import javafx.scene.Node;
import javafx.scene.control.PasswordField;

import javafx.stage.Stage;

public class LibrarianController {
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
	private Label librarianId;
	

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
			boolean boolFirstName;  // to be continued...
 		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public void addLibrarin(ActionEvent event) throws Exception {
		String afirstName = firstname.getText();
		String aLastName = lastname.getText();
		String aAddress = address.getText();
		String aDateOfBirth = dateOfBirth.getText();
		int aContactNumber =  Integer.parseInt(contactNumber.getText().toString());
		String aEmail = email.getText();
		String aUserName = username.getText();
		String aPassword = password.getText();
		try {
			int saveLibraryData = Librarian.saveLibrarian(afirstName, aLastName, aAddress, aDateOfBirth, aContactNumber, aUserName, aPassword,
					aEmail, 2);
			if (saveLibraryData > 0) {
				Stage primaryStage = new Stage();
				((Node) event.getSource()).getScene().getWindow().hide();
				Admin admin = new Admin();
				admin.start(primaryStage);
				System.out.println("Successfully added librarian");
			}
		} catch(Exception e) {
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
		} catch(Exception e) {
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
		} catch(Exception e) {
			System.out.println(e);
		}		
	}
}
