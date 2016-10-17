package application.controller;

import validationsfxml.Popup;
import validationsfxml.ValidationController;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.model.Admin;
import application.model.Book;
import application.model.Database;
import application.model.Librarian;
import application.model.Login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookController{
	@FXML
	private TextField bookId;
	@FXML
	private TextField txtIsbn;
	@FXML
	private TextField studentId;
	@FXML
	Label lblISBN;
	@FXML
	Label lblStudentId;

	@FXML
	public void backToLibrarian(ActionEvent event) {

		((Node) event.getSource()).getScene().getWindow().hide();
		Librarian librarian = new Librarian();
		Stage primaryStage = new Stage();
		librarian.start(primaryStage);
	}

	@FXML
	public void deleteBookWithId(ActionEvent event) {
		String bokid = bookId.getText();
		if (bokid.isEmpty()) {
			Popup.emptyBookDeleteNotification();
		} else {
		//	boolean bokIdLength = ValidationController.validateBookId(bokid);
		//	if (bokIdLength) {
				int aBookId = Integer.parseInt(bookId.getText().toString());
				try {
					int delete = Book.deleteBookById(aBookId);
					if (delete > 0) {
						Stage primaryStage = new Stage();
						
						((Node) event.getSource()).getScene().getWindow().hide();
						Librarian librarian = new Librarian();
						librarian.start(primaryStage);
						Popup.getBookDeleteNotification();
						// System.out.println("Book ID " + aBookId +
					} else {
						Popup.wrongBookDeleteNotification();
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		//}
	}

	public void returnBook(ActionEvent event) {
		boolean bISBN = ValidationController.isTextFieldEmpty(txtIsbn, lblISBN, "ISBN is required.");
		boolean bStdId = ValidationController.isTextFieldEmpty(studentId, lblStudentId, "Student Id is required.");
		if (bISBN && bStdId) {
			int aBookIsbn = Integer.parseInt(txtIsbn.getText().toString());
			int aStudentId = Integer.parseInt(studentId.getText().toString());
			try {
				int delete = Book.returnBookByStudentId(aBookIsbn, aStudentId);
				if (delete > 0) {
					Stage primaryStage = new Stage();
					
					((Node) event.getSource()).getScene().getWindow().hide();
					
					Librarian librarian = new Librarian();
					librarian.start(primaryStage);
					Popup.getBookReturnNotification();
					// System.out.println("Book ISBN " + aBookIsbn
				} else {
					Popup.wrongissuseBookDeleteNotification();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
