package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Admin extends Application {
	private String firstname;
	private String lastname;
	private String address;
	private java.sql.Date dateOfBirth;
	private long contactNumber;
	private String username;
	private String password;
	private String email;
	private int status;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public java.sql.Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(java.sql.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/admin.fxml"));
			primaryStage.setTitle("Library Management System : Admin");
			Scene scene = new Scene(root, 580, 400);
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("../css/admin.css").toExternalForm());
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(false);
			primaryStage.getIcons().add(new Image(this.getClass().getResource("../images/icon.jpg").toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int savaDatatoDataBase() {
		int save = 0;
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO users(firstname, lastname, address, dateOfBirth, contactNumber, username, password, email, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, getFirstname());
			ps.setString(2, getLastname());
			ps.setString(3, getAddress());
			ps.setDate(4, getDateOfBirth());
			ps.setLong(5, getContactNumber());
			ps.setString(6, getUsername());
			ps.setString(7, getPassword());
			ps.setString(8, getEmail());
			ps.setInt(9, getStatus());
			save = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return save;
	}

	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}

}
