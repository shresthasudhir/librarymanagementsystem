package application.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Admin extends Application{
	
	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/admin.fxml"));
			primaryStage.setTitle("LIBRARY MANAGMENT SYSTEM");
			primaryStage.setScene(new Scene(root, 475, 450));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
