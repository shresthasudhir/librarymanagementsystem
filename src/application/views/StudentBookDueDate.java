package application.views;

import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import application.model.Database;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;

public class StudentBookDueDate extends Application {

	private ObservableList<ObservableList> data;
	private TableView tableview;

	public static void main(String[] args) {
		launch(args);
	}

	public void buildData(ArrayList<String> bookName, ArrayList<java.sql.Date> returnDate) {
		
		data = FXCollections.observableArrayList();

		try {

			TableColumn no = new TableColumn("No.");
			no.setMinWidth(100);
			no.setCellValueFactory(
					new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

						public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {

							return new SimpleStringProperty(param.getValue().get(0).toString());

						}

					});
			
			TableColumn firstCol = new TableColumn("Book Name");
			firstCol.setMinWidth(150);
			firstCol.setCellValueFactory(
					new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

						public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {

							return new SimpleStringProperty(param.getValue().get(1).toString());

						}

					});
			
			
			TableColumn secondCol = new TableColumn("Return Date");
			secondCol.setMinWidth(100);
			secondCol.setCellValueFactory(
					new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

						public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {

							return new SimpleStringProperty(param.getValue().get(2).toString());

						}

					});

			
			tableview.getColumns().addAll(no,firstCol, secondCol);
			//tableview.getColumns().addAll(no);
			
			for (int i = 0; i < bookName.size(); i++) {
				
				ObservableList<String> rowData = FXCollections.observableArrayList();
			
				rowData.add(Integer.toString(i+1));
				rowData.add(bookName.get(i));
				rowData.add(returnDate.get(i).toString());
				//System.out.println(Integer.toString(i+1)+ " " + bookName.get(i) + " " + returnDate.get(i));
				
				data.add(rowData);
			}
			
			tableview.setItems(data);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}

	}

	public ArrayList<Integer> getIssuedBookIds(int studentId) throws SQLException {

		ArrayList<Integer> list = new ArrayList<>();

		Connection con = Database.getConnection();
		PreparedStatement ps = con
				.prepareStatement("SELECT bookId FROM issueBooks WHERE studentId = ? AND returnedDate IS NULL");
		ps.setInt(1, studentId);
		ResultSet rst = ps.executeQuery();

		while (rst.next()) {
			list.add(rst.getInt("bookId"));
		}
		return list;
	}

	public ArrayList<java.sql.Date> getBookTakenDate(int studentId) throws SQLException {

		ArrayList<java.sql.Date> list = new ArrayList<>();

		Connection con = Database.getConnection();
		PreparedStatement ps = con
				.prepareStatement("SELECT takenDate FROM issueBooks WHERE studentId = ? AND returnedDate IS NULL");
		ps.setInt(1, studentId);
		ResultSet rst = ps.executeQuery();

		while (rst.next()) {
			list.add(rst.getDate("takenDate"));
		}
		return list;
	}

	public String getIssuedBookName(int bookId) {
		String name = "";
		try {
			Connection con = Database.getConnection();
			PreparedStatement ps = con.prepareStatement("select bookName from books where id=?");
			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("bookName");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return name;
	}

	@Override
	public void start(Stage stage) throws Exception {
		//tableview = new TableView();
		// buildData();
		Scene scene = new Scene(tableview, 730, 450);
		stage.setTitle("Student view page");
		stage.setScene(scene);
		stage.show();
		stage.setResizable(false);
		stage.setFullScreen(false);
	}

	public void startBookDueDate(Stage stage, int studentId) throws Exception {
		tableview = new TableView();

		ArrayList<Integer> bookIdArray = getIssuedBookIds(studentId);
		ArrayList<java.sql.Date> takenDateArray = getBookTakenDate(studentId);

		ArrayList<String> bookName = new ArrayList<String>();

		for (int i = 0; i < bookIdArray.size(); i++) {
			String name = getIssuedBookName(bookIdArray.get(i));
			bookName.add(name);
			//System.out.println(name);
		}

		ArrayList<java.sql.Date> returnDateArray = new ArrayList<java.sql.Date>();

		for (int i = 0; i < takenDateArray.size(); i++) {

			java.sql.Date takenDate = takenDateArray.get(i);

			Calendar cal = Calendar.getInstance();
			cal.setTime(takenDate);
			cal.add(Calendar.DATE, 15);
			java.sql.Date returnDate = new java.sql.Date(cal.getTimeInMillis());
			returnDateArray.add(returnDate);
		}

		buildData(bookName, returnDateArray);
		 Scene scene = new Scene(tableview, 350, 250);
		 stage.setTitle("Library Management System : Book Due Date");
		 stage.setScene(scene);
		 stage.show();
		 stage.setResizable(false);
		 stage.setFullScreen(false);
		 stage.getIcons().add(new Image(this.getClass().getResource("../images/icon.jpg").toString()));
	}
}
