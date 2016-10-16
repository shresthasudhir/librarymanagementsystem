package application.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.model.Book;
import application.model.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookSearchController implements Initializable {
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

	public BookSearchController() {
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
		columnId.setCellValueFactory(cellData -> cellData.getValue().IdProperty());
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
				String lowerCaseFilter = newValue.toLowerCase();
				if (book.getBookName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (book.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (book.getIsbn().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (book.getPublisher().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
