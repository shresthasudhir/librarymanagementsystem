package application.views;

import java.sql.Connection;
import java.sql.ResultSet;

import application.model.Database;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StudentTable extends Application {

	private ObservableList<ObservableList> data;
	private TableView tableview;

	public static void main(String[] args) {
		launch(args);
	}

	public void buildData() {
		Connection c;
		data = FXCollections.observableArrayList();

		try {
			c = Database.getConnection();
			String SQL = "SELECT id, firstname , lastname , address, dateOfBirth, contactNumber, username, email from users WHERE status = 3";
			ResultSet rs = c.createStatement().executeQuery(SQL);
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {

								return new SimpleStringProperty(param.getValue().get(j).toString());

							}

						});
				tableview.getColumns().addAll(col);
			}
			while (rs.next()) {
				ObservableList<String> row = FXCollections.observableArrayList();

				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					row.add(rs.getString(i));

				}
				data.add(row);
			}
			tableview.setItems(data);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}

	}

	@Override
	public void start(Stage stage) throws Exception {
		tableview = new TableView();
		buildData();
		Scene scene = new Scene(tableview,730, 450);
		stage.setTitle("Library Management System : See All Student");
		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
		stage.setFullScreen(false);
	}
}
