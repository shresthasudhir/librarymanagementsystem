package validationsfxml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.model.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidationController {
	public static boolean isTextFieldEmpty(TextField txtfld) {
		boolean result = false;
		if (txtfld.getText() != null && !txtfld.getText().isEmpty()) {
			result = true;
		}
		return result;
	}

	public static boolean isTextFieldEmpty(DatePicker txtfld) {
		boolean result = false;
		if (txtfld.getPromptText() != null && !txtfld.getPromptText().isEmpty()) {
			result = true;
		}
		return result;
	}

	public static boolean isTextFieldEmpty(TextField txtfld, Label lbl,
			String sValidationText) {
		boolean result = true;
		String c = null;
		if (!isTextFieldEmpty(txtfld)) {
			result = false;
			c = sValidationText;
		}
		lbl.setText(c);
		return result;
	}

	public static boolean isTextFieldEmpty(DatePicker txtfld, Label lbl,
			String sValidationText) {
		boolean result = true;
		String c = null;
		if (!isTextFieldEmpty(txtfld)) {
			result = false;
			c = sValidationText;
		}
		lbl.setText(c);
		return result;
	}

	public static boolean validateEmail(String email) {
		Pattern p = Pattern
				.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
		Matcher m = p.matcher(email);
		if (m.find() && m.group().equals(email)) {
			return true;
		} else {
			Alert alter = new Alert(AlertType.WARNING);
			alter.setTitle("Email Validation");
			alter.setHeaderText(null);
			alter.setContentText("Invalid email");
			alter.show();

			return false;
		}

	}

	public static boolean validateContactNumber(String num) {
		int count = 0;

		if (num.matches("\\d{10}")) {
			return true;
		}

		else {
			Alert alter = new Alert(AlertType.WARNING);
			alter.setTitle("Contact Number Validation");
			alter.setHeaderText(null);
			alter.setContentText("Invalid Contact Number,must be 10 digits numbers.");
			alter.show();

			return false;
		}

	}
	public static boolean validateBookId(String num) {
		int count = 0;
		if (num.matches("\\d{5}")) {
			return true;
		}
		else {
			Alert alter = new Alert(AlertType.WARNING);
			alter.setTitle("Book Id Validation");
			alter.setHeaderText(null);
			alter.setContentText("Invalid book Id.");
			alter.show();
			return false;
		}
	}

	public static boolean EmptyString(String str) {

		if (str.isEmpty()) {
			Alert alter = new Alert(AlertType.WARNING);
			alter.setTitle("Empty String Validation");
			alter.setHeaderText(null);
			alter.setContentText("Field cannot be empty.");
			alter.show();

			return false;
		}
		return true;
	}
	
	public static boolean datePickerEmpty(DatePicker txtfld) {
		boolean result = false;
		if (txtfld.getValue() != null && !txtfld.getValue().toString().isEmpty()) {
			result = true;
		}
		return result;
	}
	
	public static boolean isDatePickerFieldEmpty(DatePicker txtfld, Label lbl,
			String sValidationText) {
		boolean result = true;
		String c = null;
		if (!datePickerEmpty(txtfld)) {
			result = false;
			c = sValidationText;
		}
		lbl.setText(c);
		return result;
	}
	
	public static boolean checkUsernameExist(String username){
		boolean result = true;
		try {
			Connection con = Database.getConnection();
			PreparedStatement checkStatus = con.prepareStatement("SELECT * FROM users WHERE username = ?");
			checkStatus.setString(1, username);
			ResultSet rs = checkStatus.executeQuery();
			if (rs.next()) {
				result = false;
				Alert alter = new Alert(AlertType.WARNING);
				alter.setTitle("Username Invalid");
				alter.setHeaderText(null);
				alter.setContentText(username +" username already exist.. please try another username");
				alter.show();
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}
}
