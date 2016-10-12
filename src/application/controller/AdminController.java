package application.controller;

import application.model.Librarian;
import application.model.Login;
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
	public void deleteLibrarin(ActionEvent event) {

	}

	@FXML
	public void seeAllLibrarin(ActionEvent event) {

	}

	@FXML
	public void seeAllStudent(ActionEvent event) {

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
