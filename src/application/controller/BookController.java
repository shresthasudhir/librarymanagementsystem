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

public class BookController implements Initializable {
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
			boolean bokIdLength = ValidationController.validateBookId(bokid);
			if (bokIdLength) {
				int aBookId = Integer.parseInt(bookId.getText().toString());
				try {
					int delete = Book.deleteBookById(aBookId);
					if (delete > 0) {
						Stage primaryStage = new Stage();
						((Node) event.getSource()).getScene().getWindow().hide();
						Popup.getBookDeleteNotification();
						Librarian librarian = new Librarian();
						librarian.start(primaryStage);
						// System.out.println("Book ID " + aBookId +
					} else {
						Popup.wrongBookDeleteNotification();
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
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
					Popup.getBookReturnNotification();
					Librarian librarian = new Librarian();
					librarian.start(primaryStage);
					// System.out.println("Book ISBN " + aBookIsbn
				} else {
					Popup.wrongissuseBookDeleteNotification();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	@FXML private TextField filterTable;
	@FXML
	private TableView<Book> bookTable;
	@FXML
	private TableColumn<Book, Number> columnId;
	@FXML
	private TableColumn<Book, String> isbnColumn;
	@FXML
	private TableColumn<Book, String> bookNameColumn;
	@FXML
	private TableColumn<Book, String> authorColumn;
	@FXML
	private TableColumn<Book, String> publisherColumn;

	private ObservableList<Book> masterData = FXCollections.observableArrayList();

	public BookController() {
		try {
			Connection con = Database.getConnection();
			ResultSet rs = con.createStatement().executeQuery("SELECT id,  isbn, bookName, author, publisher FROM books");
			while (rs.next()) {
				masterData.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(4)));
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		columnId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
		isbnColumn.setCellValueFactory(cellData -> cellData.getValue().IsbnProperty());
		bookNameColumn.setCellValueFactory(cellData -> cellData.getValue().bookNameProperty());
		authorColumn.setCellValueFactory(cellData -> cellData.getValue().authhorProperty());
		publisherColumn.setCellValueFactory(cellData -> cellData.getValue().publisherProperty());
		
		FilteredList<Book> filterData = new FilteredList<>(masterData, p->true);
		
		filterTable.textProperty().addListener((observale, oldValue, newValue) -> {
			filterData.setPredicate(book ->  {
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilert = newValue.toLowerCase();
				if (book.getBookName().toLowerCase().indexOf(lowerCaseFilert) != -1) {
					return true;
				} else if (book.getAuthor().toLowerCase().indexOf(lowerCaseFilert) != -1) {
					return true;
				} else if (book.getIsbn().toLowerCase().indexOf(lowerCaseFilert) != -1) {
					return true;
				} else if (book.getPublisher().toLowerCase().indexOf(lowerCaseFilert) != -1) {
					return true;
				}
				return false;
			});
		});
		SortedList<Book> sortedData = new SortedList<>(filterData);
		sortedData.comparatorProperty().bind(bookTable.comparatorProperty());

		bookTable.setItems(sortedData);

	}
}
