package gui;

/**
 * 
 * @author Corey Schneider
 *
 */

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import util.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.College;
import model.Course;
import model.Faculty;
import model.Student;
import model.Textbook;

public class AllFacultyPane {

	private GridPane grid;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AllFacultyPane(College college) {
		grid = new GridPane();
		grid.setGridLinesVisible(true);
		grid.setPadding(new Insets(0, 10, 10, 20));

		/** Labels */
		Label helloLabel = new Label();
		helloLabel.setText("Below is all faculty currently enrolled in the college.");
		GridPane.setHalignment(helloLabel, HPos.CENTER);
		//helloLabel.setPrefWidth(150);


		/** Table */
		TableView<Faculty> facultyTable = new TableView<Faculty>();
		facultyTable.setEditable(false);
		final ObservableList<Faculty> facultyTableData = FXCollections.observableArrayList();
		facultyTable.setItems(facultyTableData);
		Faculty[] facultyData = college.getPeopleBag().listAllFaculty();

		//System.out.println(facultyData[1]);
		facultyTableData.addAll(facultyData);


		TableColumn idCol = new TableColumn("ID");
		idCol.setMinWidth(100);
		idCol.setPrefWidth(100);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn titleCol = new TableColumn("First name");
		titleCol.setMinWidth(150);
		titleCol.setPrefWidth(200);
		titleCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn authorCol = new TableColumn("Author");
		authorCol.setMinWidth(80);
		authorCol.setPrefWidth(120);
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		//creditCol.setCellFactory(TextFieldTableCell.forTableColumn());
		/*		creditCol.setOnEditCommit(new EventHandler<CellEditEvent<Course, String>>() {
			@Override
			public void handle(CellEditEvent<Course, String> t) {
				((Course) t.getTableView().getItems().get(
						t.getTablePosition().getRow())
						).setCredits(Double.parseDouble(t.getNewValue()));
			}
		});*/

		TableColumn publisherCol = new TableColumn("Publisher");
		publisherCol.setMinWidth(90);
		publisherCol.setPrefWidth(110);
		publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));

		TableColumn priceCol = new TableColumn("Cost");
		priceCol.setMinWidth(60);
		priceCol.setPrefWidth(60);
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

		facultyTable.setMaxWidth(600);
		facultyTable.setMaxHeight(380);
		facultyTable.setPrefWidth(600);
		facultyTable.getColumns().addAll(idCol, titleCol/*, authorCol, publisherCol, priceCol*/);

		VBox btnBox = new VBox(20);
		btnBox.setAlignment(Pos.CENTER);
		//btnBox.getChildren().addAll(addBtn, deleteBtn, editBtn);


		/** Add to grid */
		grid.add(helloLabel, 0, 0);
		grid.add(facultyTable, 0, 1);
		grid.add(btnBox, 1, 1);
	}

	public GridPane getGridPane() {
		return grid;
	}
}