package application.controller;

import application.model.Admin;
import application.model.Book;
import application.model.Librarian;
import application.model.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookController {
	@FXML
	private TextField bookId;
	@FXML
	private TextField txtIsbn;
	@FXML
	private TextField studentId;
	@FXML
	public void backToLibrarian(ActionEvent event) {

		((Node) event.getSource()).getScene().getWindow().hide();
		Librarian librarian = new Librarian();
		Stage primaryStage = new Stage();
		librarian.start(primaryStage);
	}
	
	@FXML
	public void deleteBookWithId(ActionEvent event) {
		int aBookId = Integer.parseInt(bookId.getText().toString());
		try {
			int delete = Book.deleteBookById(aBookId);
			if (delete > 0) {
				Stage primaryStage = new Stage();
				((Node) event.getSource()).getScene().getWindow().hide();
				Librarian librarian = new Librarian();
				librarian.start(primaryStage);
				System.out.println("Book ID " + aBookId + " delete successfully");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void returnBook(ActionEvent event) {
		int aBookIsbn = Integer.parseInt(txtIsbn.getText().toString());
		int aStudentId = Integer.parseInt(studentId.getText().toString());
		try {
			int delete = Book.returnBookByStudentId(aBookIsbn, aStudentId);
			if (delete > 0) {
				Stage primaryStage = new Stage();
				((Node) event.getSource()).getScene().getWindow().hide();
				Librarian librarian = new Librarian();
				librarian.start(primaryStage);
				System.out.println("Book ISBN " + aBookIsbn + " Return successfully");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
