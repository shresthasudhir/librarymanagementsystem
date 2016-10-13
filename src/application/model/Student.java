package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Student extends Application {

	private String firstname;
	private String lastname;
	private String address;
	private java.sql.Date dateOfBirth;
	private int contactNumber;
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

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
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

	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/student.fxml"));
			primaryStage.setTitle("Student Page");
			primaryStage.setScene(new Scene(root, 500, 450));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addStudent(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/add-student.fxml"));
			primaryStage.setTitle("STUDENT ADD FROM");
			primaryStage.setScene(new Scene(root, 490, 600));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int saveDatatoDataBase() {
		int save = 0;
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO users(firstname, lastname, address, dateOfBirth, contactNumber, username, password, email, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, getFirstname());
			ps.setString(2, getLastname());
			ps.setString(3, getAddress());
			ps.setDate(4, getDateOfBirth());
			ps.setInt(5, getContactNumber());
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
	
	public void deleteStudentById(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/delete-student.fxml"));
			primaryStage.setTitle("DELETE LIBRARIAN");
			primaryStage.setScene(new Scene(root, 500, 250));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static int deleteStudentById(int id) {
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
				System.out.println("Student " + id + " is already deleted");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return delete;
	}
}
