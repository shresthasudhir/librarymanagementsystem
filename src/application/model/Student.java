package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import application.controller.StudentController;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.util.Callback;

public class Student extends Application {

	private StringProperty firstname;
	private StringProperty lastname;
	private StringProperty address;
	private ObjectProperty<java.sql.Date> dateOfBirth;
	private LongProperty contactNumber;
	private StringProperty username;
	private StringProperty password;
	private StringProperty email;
	private IntegerProperty status;

	public Student() {
	}

	public Student(String firstname, String lastname, String address, java.sql.Date dateOfBirth, Long contactNumber,
			String email) {
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.address = new SimpleStringProperty(address);
		this.dateOfBirth = new SimpleObjectProperty<>(dateOfBirth);
		this.contactNumber = new SimpleLongProperty(contactNumber);
		
		this.email = new SimpleStringProperty(email);
		
	}

	public String getFirstname() {
		return firstname.get();
	}

	public void setFirstname(String firstname) {
		this.firstname.set(firstname);
	}

	public StringProperty firstNameProperty() {
		return firstname;
	}

	public String getLastname() {
		return lastname.get();
	}

	public void setLastname(String lastname) {
		this.lastname.set(lastname);
	}

	public StringProperty lastNameProperty() {
		return lastname;
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address.set(address);
	}

	public StringProperty addressProperty() {
		return address;
	}

	public java.sql.Date getDateOfBirth() {
		return dateOfBirth.get();
	}

	public void setDateOfBirth(java.sql.Date dateOfBirth) {
		this.dateOfBirth.set(dateOfBirth);
	}

	public ObjectProperty<java.sql.Date> dataOfBirthProperty() {
		return dateOfBirth;
	}

	public long getContactNumber() {
		return contactNumber.get();
	}

	public void setContactNumber(long aContactNumber) {
		this.contactNumber.set(aContactNumber);
	}

	public LongProperty contactNumberProperty() {
		return contactNumber;
	}

	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public String getPassword() {
		return password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public StringProperty emailProperty() {
		return email;
	}

	public int getStatus() {
		return status.get();
	}

	public void setStatus(int status) {
		this.status.set(status);
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

	public void startStudent(Stage primaryStage, int userId) {
		try {
			StudentController.setStudentId(userId);

			String studentUserName = User.getUserFNameLName(userId);
			final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxmlfile/student.fxml"));
			fxmlLoader.getNamespace().put("stuName", studentUserName);

			Parent root = fxmlLoader.load();

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
			primaryStage.setTitle("STUDENT ADD FORM");
			primaryStage.setScene(new Scene(root, 570, 550));
			primaryStage.getIcons().add(new Image(this.getClass().getResource("../images/addStudent.png").toString()));
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

	public void deleteStudentById(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Student.class.getResource("../fxmlfile/delete-student.fxml"));
			primaryStage.setTitle("DELETE LIBRARIAN");
			AnchorPane page = (AnchorPane) loader.load();
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.getIcons().add(new Image(this.getClass().getResource("../images/delete.png").toString()));
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

	public void searchStudentPage(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(Student.class.getResource("../fxmlfile/search-student.fxml"));
			primaryStage.setTitle("Search Student");
			AnchorPane page = (AnchorPane) loader.load();
			Scene scene = new Scene(page);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
