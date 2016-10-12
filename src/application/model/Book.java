package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Book extends Application{
	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/add-book.fxml"));
			primaryStage.setTitle("Add Book Page");
			primaryStage.setScene(new Scene(root, 475, 350));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int saveBook(int isbn, String bookname, String authorname, String publisher){
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
			primaryStage.setTitle("Issue Book Page");
			primaryStage.setScene(new Scene(root, 475, 250));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getBookId(int isbn){
		int id=0;
		try{
			Connection con=Database.getConnection();
			PreparedStatement ps=con.prepareStatement("select id from books where isbn=?");
			ps.setInt(1,isbn);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id"); 
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return id;
	}
	
	public static int saveIssueBook(int isbn, int studentId){
		int save = 0;
		int bookId = getBookId(isbn);
		String returnDate = null;
		int update=0;
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO issueBooks(studentId,bookId,returnedDate) values(?, ?, ?)");
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
				System.out.println("Cannot Issue this book"+ isbn);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return update;

	}

}
