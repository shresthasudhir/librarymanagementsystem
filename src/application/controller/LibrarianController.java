package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LibrarianController {

	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField address;
	@FXML
	private TextField dateOfBirth;
	@FXML
	private TextField contact;
	@FXML
	private TextField email;
	@FXML
	private TextField username;
	@FXML
	private TextField password;

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
			boolean boolFirstName; 
 		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}
