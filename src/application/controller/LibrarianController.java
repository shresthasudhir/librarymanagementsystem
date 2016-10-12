package application.controller;

import application.model.Admin;
import application.model.Librarian;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
	public void addLibrarin(ActionEvent event) throws Exception {
		String afirstName = firstname.getText();
		String aLastName = lastname.getText();
		String aAddress = address.getText();
		String aDateOfBirth = dateOfBirth.getText();
		int aContactNumber = Integer.parseInt(contactNumber.getText().toString());
		String aEmail = email.getText();
		String aUserName = username.getText();
		String aPassword = password.getText();
		try {
			int saveLibraryData = Librarian.saveLibrarian(afirstName, aLastName, aAddress, aDateOfBirth, aContactNumber,
					aUserName, aPassword, aEmail, 2);
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

	@FXML
	public void back(ActionEvent event) {
		Stage primaryStage = new Stage();
		((Node) event.getSource()).getScene().getWindow().hide();
		Admin admin = new Admin();
		admin.start(primaryStage);
	}

	@FXML
	private TextField librarianId;

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
}
