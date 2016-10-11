package application.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Librarian extends Application{
	
	public static void main(String[] args) {
		Application.launch(Librarian.class, args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/librarian.fxml"));
			primaryStage.setTitle("LIBRARY MANAGMENT SYSTEM");
			primaryStage.setScene(new Scene(root, 490, 500));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
