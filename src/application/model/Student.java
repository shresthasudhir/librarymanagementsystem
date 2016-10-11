package application.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Student extends Application{
	
	public static void main(String[] args) {
		Application.launch(Login.class, args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/student.fxml"));
			primaryStage.setTitle("Student Page");
			primaryStage.setScene(new Scene(root, 475, 400));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
