package application.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeuDateView extends Application{
	    @Override
	    public void start(Stage primaryStage) throws Exception {
	    	try {
				Parent root = FXMLLoader.load(getClass().getResource("../fxmlfile/PersonTable.fxml"));
				primaryStage.setTitle("See Book Deu Date - Page");
				primaryStage.setScene(new Scene(root, 490, 500));
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

	    /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	    	Application.launch(DeuDateView.class, args);
	    }
	    
}
