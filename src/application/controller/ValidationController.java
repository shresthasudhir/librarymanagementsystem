package application.controller;

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
}
