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
	
	public static void getBookIssuedNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Data Issued Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Book Data has been issued successfully.");
		alter.show();
	}
	
	public static void getBookReturnNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Book Return Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Book Data has been return successfully.");
		alter.show();
	}

	// pop up for deleting Book data.
	public static void getBookDeleteNotification() {
		Alert alter = new Alert(AlertType.INFORMATION);
		alter.setTitle("Book  Delete Confirmation");
		alter.setHeaderText(null);
		alter.setContentText("Book has been Deleted successfully.");
		alter.show();
	}
	// pop up for deleting Libararian.
		public static void getLibrarianDeleteNotification() {
			Alert alter = new Alert(AlertType.INFORMATION);
			alter.setTitle("Libarian  Delete Confirmation");
			alter.setHeaderText(null);
			alter.setContentText("Librarian has been Deleted successfully.");
			alter.show();
		}
	
	public static void wrongBookDeleteNotification() {
		Alert alter = new Alert(AlertType.WARNING);
		alter.setTitle("Book  Delete WARNING");
		alter.setHeaderText(null);
		alter.setContentText("Invalid Book Id.");
		alter.show();
	}
	public static void wrongLibrarianDeleteNotification() {
		Alert alter = new Alert(AlertType.WARNING);
		alter.setTitle("Librarian  Delete WARNING");
		alter.setHeaderText(null);
		alter.setContentText("Invalid Librarian Id.");
		alter.show();
	}
	public static void wrongissuseBookDeleteNotification() {
		Alert alter = new Alert(AlertType.WARNING);
		alter.setTitle("Book  Issuse WARNING");
		alter.setHeaderText(null);
		alter.setContentText("Invalid Book Id or Student Id.");
		alter.show();
	}
	public static void wrongStudentDeleteNotification() {
		Alert alter = new Alert(AlertType.WARNING);
		alter.setTitle("Student  Delete WARNING");
		alter.setHeaderText(null);
		alter.setContentText("Invalid Student Id.");
		alter.show();
	}
	public static void emptyBookDeleteNotification() {
		Alert alter = new Alert(AlertType.WARNING);
		alter.setTitle("Empty Text  WARNING");
		alter.setHeaderText(null);
		alter.setContentText("Book id cannot be empty.");
		alter.show();
	
		
	}
	public static void emptyStudentDeleteNotification() {
		Alert alter = new Alert(AlertType.WARNING);
		alter.setTitle("Empty Text  WARNING");
		alter.setHeaderText(null);
		alter.setContentText("Student id cannot be empty.");
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
	// pop up for invalid user name or password.
		public static void getInvalidLoginNotification() {
			Alert alter = new Alert(AlertType.INFORMATION);
			alter.setTitle("Login");
			alter.setHeaderText(null);
			alter.setContentText("User name or password is invalid.");
			alter.show();
		}
		// pop up for Empty user name or password.
				public static void getEmptyLoginNotification() {
					Alert alter = new Alert(AlertType.INFORMATION);
					alter.setTitle("Login");
					alter.setHeaderText(null);
					alter.setContentText("Please enter the user name and password.");
					alter.show();
				}
}
