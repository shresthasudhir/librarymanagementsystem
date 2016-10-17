package application.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.text.SimpleAttributeSet;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Book extends Application {
	private IntegerProperty  id;
	private StringProperty isbn;
	private StringProperty bookName;
	private StringProperty author;
	private StringProperty  publisher;
	
	public Book() {};
	
	public Book(int id, String isbn, String bookName, String author, String publisher) {
		this.id = new SimpleIntegerProperty(id);
		this.isbn = new SimpleStringProperty(isbn);
		this.bookName = new SimpleStringProperty(bookName);
		this.author = new SimpleStringProperty(author);
		this.publisher = new SimpleStringProperty(publisher);
	}
	
	public int getId() {
		return id.get();
	}
	
	public void setId(int id) {
		this.id.set(id);
	}
	public IntegerProperty IdProperty() {
		return id;
	}
	public String getIsbn() {
		return isbn.get();
	}
	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}
	public StringProperty IsbnProperty() {
		return isbn;
	}
	
	public String getBookName() {
		return bookName.get();
	}
	public void setBookName(String bookName) {
		this.bookName.set(bookName);
	}
	public StringProperty bookNameProperty() {
		return bookName;
	}
	
	public String getAuthor() {
		return author.get();
	}
	public void setAuthor(String author) {
		this.author.set(author);
	}
	public StringProperty authhorProperty() {
		return author;
	}
	
	public String getPublisher() {
		return publisher.get();
	}
	public void setPublisher(String publisher) {
		this.publisher.set(publisher);
	}
	public StringProperty publisherProperty() {
		return publisher;
	}
	
	
	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/add-book.fxml"));
			primaryStage.setTitle("Library Management System : Add Book");	
			Scene scene = new Scene(root, 475, 350);
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("../css/librarian.css").toExternalForm());
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(false);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int saveBook(int isbn, String bookname, String authorname, String publisher) {
		int save = 0;
		int status = 1;
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO books(isbn, bookName, author, publisher,status) values(?, ?, ?, ?, ?)");
			ps.setInt(1, isbn);
			ps.setString(2, bookname);
			ps.setString(3, authorname);
			ps.setString(4, publisher);
			ps.setInt(5, status);
			save = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return save;

	}

	public void showIssueBookPage(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/issue-book.fxml"));
			primaryStage.setTitle("Library Management System : Issue Book");
			Scene scene = new Scene(root, 475, 250);
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("../css/librarian.css").toExternalForm());
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getBookId(int isbn) {
		int id = 0;
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement("select id from books where isbn=?");
			ps.setInt(1, isbn);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}

	public static int saveIssueBook(int isbn, int studentId) {
		int save = 0;
		int bookId = getBookId(isbn);
		String returnDate = null;
		int update = 0;
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO issueBooks(studentId,bookId,returnedDate) values(?, ?, ?)");
			ps.setInt(1, studentId);
			ps.setInt(2, bookId);
			ps.setString(3, returnDate);
			save = ps.executeUpdate();
			System.out.println(save);
			if (save != 0) {
				PreparedStatement pss = con.prepareStatement("UPDATE books SET status = ? WHERE id = ?");
				pss.setInt(1, 2);
				pss.setInt(2, bookId);
				update = pss.executeUpdate();
			} else {
				System.out.println("Cannot Issue this book" + isbn);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return update;

	}
	
	public static int returnBookByStudentId(int isbn, int studentId) {
		int returnBook = 0;
		int status = 0;
		int id = 0;
		int bookId = getBookId(isbn);
		try {
			Connection con = Database.getConnection();
			PreparedStatement checkId = con.prepareStatement("SELECT id FROM issuebooks WHERE bookId = ? and studentId = ? and returnedDate IS null");
			checkId.setInt(1, bookId);
			checkId.setInt(2,studentId);
			ResultSet rs = checkId.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
			if (id > 0) {
				PreparedStatement ps = con.prepareStatement("UPDATE issuebooks SET returnedDate = ? WHERE bookId = ? and studentId = ?");
				ps.setDate(1, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
				ps.setInt(2, bookId);
				ps.setInt(3, studentId);
				returnBook = ps.executeUpdate();
				if (returnBook > 0) {
					PreparedStatement ps2 = con.prepareStatement("UPDATE books SET status = ? WHERE id = ?");
					ps2.setInt(1, 1);
					ps2.setInt(2, bookId);
					status = ps2.executeUpdate();
				}
				
			} else {
				System.out.println("Book " + isbn + " is return Successfully");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	public static int deleteBookById(int id) {
		int delete = 0;
		int status = 0;
		try {
			Connection con = Database.getConnection();
			PreparedStatement checkStatus = con.prepareStatement("SELECT status FROM books WHERE id = ?");
			checkStatus.setInt(1, id);
			ResultSet rs = checkStatus.executeQuery();
			if (rs.next()) {
				status = rs.getInt("status");
			}
			System.out.println(status);
			if (status != 4) {
				PreparedStatement ps = con.prepareStatement("UPDATE books SET status = ? WHERE id = ?");
				ps.setInt(1, 4);
				ps.setInt(2, id);
				delete = ps.executeUpdate();
			} else {
				System.out.println("Book " + id + " is already deleted");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return delete;
	}

	public void deleteBookByLibrarian(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/delete-book.fxml"));
			primaryStage.setTitle("Library Management System : Delete Book");
			
			Scene scene = new Scene(root, 380, 230);
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("../css/librarian.css").toExternalForm());
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void returnBookPage(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/return-book.fxml"));
			primaryStage.setTitle("Library Management System : Return Book");
			
			Scene scene = new Scene(root, 475, 300);
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("../css/librarian.css").toExternalForm());
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(false);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

