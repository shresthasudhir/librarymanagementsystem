package application.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Login extends Application {

	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/login.fxml"));
			primaryStage.setTitle("Library Management System");
			Scene scene = new Scene(root, 600, 275);
			primaryStage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("../css/login.css").toExternalForm());
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(false);
			primaryStage.getIcons().add(new Image(this.getClass().getResource("../images/icon.jpg").toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
