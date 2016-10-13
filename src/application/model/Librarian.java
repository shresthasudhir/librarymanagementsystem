package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Librarian extends Admin{
	
	public int librarianId;
	
	public void setLibrarianId(int user_id){
	    this.librarianId = user_id;
	}
	
	public int getLibrarianId(){
		return this.librarianId;
	}

	public static void main(String[] args) {
		Application.launch(Librarian.class, args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/librarian.fxml"));
			primaryStage.setTitle("LIBRARY MANAGMENT SYSTEM");
			primaryStage.setScene(new Scene(root, 490, 500));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startLibrarian(Stage primaryStage,int librarianId) {
		try {
			setLibrarianId(librarianId);
			String libUserName = User.getUserFNameLName(librarianId);
			final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxmlfile/librarian.fxml"));
			fxmlLoader.getNamespace().put("librarianId", libUserName);
			
			Parent root = fxmlLoader.load();
			
			primaryStage.setTitle("LIBRARY MANAGMENT SYSTEM - Librarian Page");
			primaryStage.setScene(new Scene(root, 490, 500));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addLibrarian(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/add-librarian.fxml"));
			primaryStage.setTitle("LIBRARIAN ADD FROM");
			primaryStage.setScene(new Scene(root, 600, 500));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteLibrarian(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/delete-librarian.fxml"));
			primaryStage.setTitle("DELETE LIBRARIAN");
			primaryStage.setScene(new Scene(root, 500, 250));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

public static int deleteLibrarian(int id) {
		int delete = 0;
		int status = 0;
		try {
			Connection con = Database.getConnection();
			PreparedStatement checkStatus = con.prepareStatement("SELECT status FROM users WHERE id = ?");
			checkStatus.setInt(1, id);
			ResultSet rs = checkStatus.executeQuery();
			if (rs.next()) {
				status = rs.getInt("status");
			}
			System.out.println(status);
			if (status != 4) {
				PreparedStatement ps = con.prepareStatement("UPDATE users SET status = ? WHERE id = ?");
				ps.setInt(1, 4);
				ps.setInt(2, id);
				delete = ps.executeUpdate();
			} else {
				System.out.println("Librarian " + id + " is already deleted");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return delete;
	}
}
