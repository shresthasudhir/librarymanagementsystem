package validationsfxml;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Popup {
	// pop up for saving library data.
	public static void getLibrarySavedNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Data Save Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Library Data has been saved successfully.");
		alter.show();
	}

	// pop up for deleting library data.
	public static void getLibraryDeleteNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Libarary Data Delete Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Libarary Data has been Deleted successfully.");
		alter.show();
	}

	// pop up for updating library data.
	public static void getLibraryUpdateNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Libarary Data Update Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Libarary Data has been Updated successfully.");
		alter.show();
	}

	// pop up for saving Student data.
	public static void getStudentSavedNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Data Save Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Student Data has been saved successfully.");
		alter.show();
	}

	// pop up for deleting Student data.
	public static void getStudentDeleteNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Student Data Delete Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Student Data has been Deleted successfully.");
		alter.show();
	}

	// pop up for updating Student data.
	public static void getStudentUpdateNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Student Data Update Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Student Data has been Updated successfully.");
		alter.show();
	}

	// pop up for saving Book data.
	public static void getBookSavedNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Data Save Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Book Data has been saved successfully.");
		alter.show();
	}

	// pop up for deleting Book data.
	public static void getBookDeleteNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Book Data Delete Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Book Data has been Deleted successfully.");
		alter.show();
	}

	// pop up for updating Book data.
	public static void getBookUpdateNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Book Data Update Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Book Data has been Updated successfully.");
		alter.show();
	}
}
