package application.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.model.Database;
import application.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentSearchController implements Initializable {
	@FXML
	private TextField filterTable;
	@FXML
	TableView<Student> studentTable;
	@FXML
	TableColumn<Student, String> firstNameColumn;
	@FXML
	TableColumn<Student, String> lastNameColumn;
	@FXML
	TableColumn<Student, String> addressColumn;
	@FXML
	TableColumn<Student, java.sql.Date> dateOfBirthColumn;
	@FXML
	TableColumn<Student, Number> contactNumberColumn;
	@FXML
	TableColumn<Student, String> emailColumn;

	private ObservableList<Student> masterData = FXCollections.observableArrayList();

	public StudentSearchController() {
		try {
			Connection con = Database.getConnection();
			ResultSet rs = con.createStatement().executeQuery(
					"SELECT firstname, lastname, address, dateOfBirth, contactNumber, email FROM users WHERE status = 3");
			while (rs.next()) {
				masterData.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getLong(5), rs.getString(6)));
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
		dateOfBirthColumn.setCellValueFactory(cellData -> cellData.getValue().dataOfBirthProperty());
		contactNumberColumn.setCellValueFactory(cellData -> cellData.getValue().contactNumberProperty());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		
		FilteredList<Student> filterData = new FilteredList<>(masterData, p->true);
		
		filterTable.textProperty().addListener((observale, oldValue, newValue) -> {
				filterData.setPredicate(student -> {
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					String lowerCaseFilter = newValue.toLowerCase();
					if(student.getFirstname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true;
					} else if (student.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true;
					} else if (student.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true;
					} else if (student.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
						return true;
					}
					return false;
				});
		});
		SortedList<Student> sortedData = new SortedList<>(filterData);
		sortedData.comparatorProperty().bind(studentTable.comparatorProperty());
		studentTable.setItems(sortedData);
		
	}

}
