package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Librarian extends Application {

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

	public void addLibrarian(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/add-librarian.fxml"));
			primaryStage.setTitle("LIBRARIAN ADD FROM");
			primaryStage.setScene(new Scene(root, 490, 600));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int saveLibrarian(String firstname, String lastname, String address, String dateOfBirth,
			int contactNumber, String username, String password, String email, int status) {
		int save = 0;
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO users(firstname, lastname, address, dateOfBirth, contactNumber, username, password, email, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, address);
			ps.setString(4, dateOfBirth);
			ps.setInt(5, contactNumber);
			ps.setString(6, username);
			ps.setString(7, password);
			ps.setString(8, email);
			ps.setInt(9, status);
			save = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return save;

	}

}
