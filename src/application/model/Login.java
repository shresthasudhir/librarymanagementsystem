package application.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Login extends Application {

	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/login.fxml"));
			primaryStage.setTitle("LIBRARY MANAGMENT SYSTEM");
			primaryStage.setScene(new Scene(root, 300, 275));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
