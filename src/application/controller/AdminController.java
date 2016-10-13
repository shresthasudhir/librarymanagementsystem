package application.controller;

import application.model.Librarian;
import application.model.Login;
import application.views.LibraryTable;
import application.views.StudentTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class AdminController {
	@FXML
	protected void addLibrarian(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Librarian librarin = new Librarian();
		Stage primaryStage = new Stage();
		librarin.addLibrarian(primaryStage);
	}

	@FXML
	public void deleteLibrarian(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Librarian librarin = new Librarian();
		Stage primaryStage = new Stage();
		librarin.deleteLibrarian(primaryStage);
	}

	public void seeAllLibrarin(ActionEvent event) throws Exception {
		LibraryTable dynamicTable = new LibraryTable();
		Stage primaryStage = new Stage();
		dynamicTable.start(primaryStage);
	}
	@FXML
	public void seeAllStudent(ActionEvent event) throws Exception {
		StudentTable stuDyanamicTable = new StudentTable();
		Stage primaryStage = new Stage();
		stuDyanamicTable.start(primaryStage);
	}

	@FXML
	public void seeFine(ActionEvent event) {
	}

	@FXML
	public void logout(ActionEvent event) {
		((Node) event.getSource()).getScene().getWindow().hide();
		Login logout = new Login();
		Stage primaryStage = new Stage();
		logout.start(primaryStage);
	}

}
