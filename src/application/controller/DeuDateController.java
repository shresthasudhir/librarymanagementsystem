package application.controller;

import java.net.URL;
import java.util.ResourceBundle;


import application.model.DeuDateModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeuDateController implements Initializable{
	
	@FXML
	private TableView<DeuDateModel> personTable;
	@FXML
	private TableColumn<DeuDateModel, String> firstNameColumn;
	@FXML
	private TableColumn<DeuDateModel, String> lastNameColumn;

	private ObservableList<DeuDateModel> masterData = FXCollections.observableArrayList();

	/**
	 * Just add some sample data in the constructor.
	 */
	public DeuDateController() {
		masterData.add(new DeuDateModel("Hans", "Muster"));
		masterData.add(new DeuDateModel("Ruth", "Mueller"));
		masterData.add(new DeuDateModel("Heinz", "Kurz"));
		masterData.add(new DeuDateModel("Cornelia", "Meier"));
		masterData.add(new DeuDateModel("Werner", "Meyer"));
		masterData.add(new DeuDateModel("Lydia", "Kunz"));
		masterData.add(new DeuDateModel("Anna", "Best"));
		masterData.add(new DeuDateModel("Stefan", "Meier"));
		masterData.add(new DeuDateModel("Martin", "Mueller"));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 * 
	 * Initializes the table columns and sets up sorting and filtering.
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		
		personTable.setItems(masterData);
	}
	

}
