package gui;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.College;
import model.Course;
import model.Student;
import model.Textbook;
import util.Utilities;

public class CoursePane {

	private GridPane grid;

	@SuppressWarnings("unchecked")
	public CoursePane(College college) {
		grid = new GridPane();
		grid.setGridLinesVisible(true);
		grid.setPadding(new Insets(0, 10, 10, 20));

		/** Labels */
		Label helloLabel = new Label();
		helloLabel.setText("Below is the Master Course Bag which holds all courses the school offers.");
		GridPane.setHalignment(helloLabel, HPos.CENTER);
		//helloLabel.setPrefWidth(150);


		/** Table */
		TableView<Course> courseTable = new TableView<Course>();
		courseTable.setEditable(false);
		final ObservableList<Course> courseTableData = FXCollections.observableArrayList();
		courseTable.setItems(courseTableData);
		Course[] courseData = college.getMasterCourseBag().listAllCourses();
		System.out.println(courseData[1]);
		courseTableData.addAll(courseData);

		TableColumn crnCol = new TableColumn("CRN");
		crnCol.setMinWidth(60);
		crnCol.setPrefWidth(60);
		crnCol.setCellValueFactory(new PropertyValueFactory<Course, String>("crn"));
		crnCol.setCellFactory(TextFieldTableCell.forTableColumn());
		
		TableColumn titleCol = new TableColumn("Title");
		titleCol.setMinWidth(150);
		titleCol.setPrefWidth(220);
		titleCol.setCellValueFactory(new PropertyValueFactory<Course, String>("courseTitle"));
		titleCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn creditCol = new TableColumn("Credits");
		creditCol.setMinWidth(60);
		creditCol.setPrefWidth(60);
		creditCol.setCellValueFactory(new PropertyValueFactory<>("credits"));

		TableColumn textbookCol = new TableColumn("Textbook ISBN");
		textbookCol.setMinWidth(100);
		textbookCol.setPrefWidth(100);
		textbookCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		

		TableColumn textbookTitleCol = new TableColumn("Textbook title");
		textbookTitleCol.setMinWidth(100);
		textbookTitleCol.setPrefWidth(150);
		textbookTitleCol.setCellValueFactory(new PropertyValueFactory<>("textbook"));

		courseTable.setMaxWidth(600);
		courseTable.setMaxHeight(380);
		courseTable.setPrefWidth(600);
		courseTable.getColumns().addAll(crnCol, titleCol, creditCol, textbookCol, textbookTitleCol);


		/** Buttons */
		Button addBtn = new Button("Add course");
		addBtn.setPrefWidth(100);
		addBtn.setAlignment(Pos.TOP_CENTER);
		addBtn.setOnAction(e -> {
			System.out.println("Add course button pressed");

			try {
				Dialog<String> dialog = new Dialog<>();
				dialog.setTitle("Add course");
				dialog.setHeaderText("Add course. Be sure CRN is 5 digits and credits are whole numbers.");
				dialog.setResizable(true);

				Label titleLabel = new Label("Course title:");
				Label crnLabel = new Label("CRN (5 digits):");
				Label isbnLabel = new Label("Textbook ISBN:   ");
				Label creditLabel = new Label("Credits:");
				
				TextField titleField = new TextField();
				titleField.setPromptText("course title");
				
				TextField crnField = new TextField();
				crnField.setPromptText("CRN (5 digits!!)");

				TextField creditField = new TextField();
				creditField.setPromptText("credits");
				
				ObservableList<String> isbnList = FXCollections.observableArrayList();
				isbnList.addAll(college.getTextbookBag().listAllTextbookISBN());
				final ComboBox isbnListComboBox = new ComboBox(isbnList);

				/*TextField text1 = new TextField();
				TextField text2 = new TextField();*/

				GridPane grid2 = new GridPane();
				grid2.add(titleLabel, 1, 1);
				grid2.add(titleField, 2, 1);
				grid2.add(crnLabel, 1, 2);
				grid2.add(crnField, 2, 2);
				grid2.add(isbnLabel, 1, 3);
				grid2.add(isbnListComboBox, 2, 3);
				grid2.add(creditLabel, 1, 4);
				grid2.add(creditField, 2, 4);
				
				
				//grid2.add(gradeListComboBox, 2, 2);
				dialog.getDialogPane().setContent(grid2);
				ButtonType buttonTypeOk = new ButtonType("Add", ButtonData.OK_DONE);
				ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
				dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);

				Optional<String> result = dialog.showAndWait(); //need this to make the dialog pop up, crap tho

				Object isbnResult = isbnListComboBox.getValue();
				if(isbnResult == null) {
					Utilities.alert("Please select options.", AlertType.ERROR);
					return;
				}
				if(!creditField.getText().matches("[1234]{1}")) { //1, 2, 3 or 4. not really bulletproof but better than nothing. allows 11, 22, 13 etc
					Utilities.alert("Please enter a value from 1 to 4 credits.", AlertType.ERROR);
					return;
				}
				if(!crnField.getText().matches("[0-9]{5}")) { //regex for whole number, 5 digits
					Utilities.alert("Please enter a whole (0-9) numeric value for CRN.", AlertType.ERROR);
					return;
				}
				if(college.getMasterCourseBag().exists(crnField.getText())) {
					Utilities.alert("A course with that CRN already exists.", AlertType.ERROR);
					System.out.println("That course with that CRN already exists.");
					return;
				}
				//String courseTitle, String crn, Textbook textbook, double credits
				Textbook txtbk = college.getTextbookBag().find(isbnListComboBox.getValue().toString());
				Course c = new Course(titleField.getText(), crnField.getText(), txtbk, Double.parseDouble(creditField.getText()));
				
				college.getMasterCourseBag().add(c);
				System.out.println("Added "+c+" to MasterCourseBag successfully.");
				Utilities.alert("Added "+c+" to MasterCourseBag successfully.", AlertType.CONFIRMATION);

				courseTableData.add(c);
				courseTable.refresh();
				courseTableData.clear();
				courseTableData.addAll(college.getMasterCourseBag().listAllCourses());

			} catch(Exception err) {
				Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
			}
		});

		Button deleteBtn = new Button("Delete course");
		deleteBtn.setPrefWidth(100);
		deleteBtn.setAlignment(Pos.TOP_CENTER);
		deleteBtn.setOnAction(e -> {
			System.out.println("Delete course button pressed");
			
				String[] course = college.getMasterCourseBag().listAllCoursesString();
				List<String> newList = Arrays.asList(course);

				ChoiceDialog<String> dialog = new ChoiceDialog<>("", newList);
				dialog.setTitle("Delete course");
				dialog.setHeaderText("Delete courses");
				dialog.setContentText("Please choose the CRN:");

				Optional<String> idInput = dialog.showAndWait();
				idInput.ifPresent(choice -> {
					Course courseToRemove;
					String shortened = choice.substring(0, 5);
					System.out.println("you chose: "+choice);
					System.out.println("shortened to crn: "+shortened);

					courseToRemove = college.getMasterCourseBag().find(shortened);
					college.getMasterCourseBag().delete(courseToRemove);

					/*courseTableData.remove(courseToRemove);
					courseTable.refresh();*/
					courseTableData.clear();
					courseTableData.addAll(college.getMasterCourseBag().listAllCourses());
/*					if(result != null) {
						System.out.println("this what we're talkn about?: "+result);
						s.getCourseBag().deleteIP(result);
						//courseIPTableData.remove(result);
						//courseIPTable.refresh();
						courseIPTableData.clear();
						courseIPTableData.addAll(s.getCourseBag().getCoursesInProgress());
					}*/
				});
		});
		
		Button editBtn = new Button("Edit course");
		editBtn.setPrefWidth(100);
		editBtn.setAlignment(Pos.TOP_CENTER);
		editBtn.setOnAction(e -> {
			System.out.println("edit course button pressed");
			String[] course = college.getMasterCourseBag().listAllCoursesString();
			List<String> newList = Arrays.asList(course);

			ChoiceDialog<String> dialog = new ChoiceDialog<>("", newList);
			dialog.setTitle("Edit course");
			dialog.setHeaderText("Edit course");
			dialog.setContentText("Please choose the CRN:");

			Optional<String> idInput = dialog.showAndWait();
			idInput.ifPresent(choice -> {
				Course courseToEdit;
				String shortened = choice.substring(0, 5);
				System.out.println("you chose: "+choice);
				System.out.println("shortened to crn: "+shortened);

				courseToEdit = college.getMasterCourseBag().find(shortened);
				System.out.println("talkin about: "+courseToEdit);
				//college.getMasterCourseBag().delete(courseToRemove);
				try {
					Dialog<String> dialog2 = new Dialog<>();
					dialog2.setTitle("Edit course");
					dialog2.setHeaderText("Edit course. Be sure CRN is 5 digits and credits are whole numbers.");
					dialog2.setResizable(true);

					Label titleLabel = new Label("Course title:");
					Label crnLabel = new Label("CRN (5 digits):");
					Label isbnLabel = new Label("Textbook ISBN:   ");
					Label creditLabel = new Label("Credits:");
					
					TextField titleField = new TextField();
					titleField.setPromptText("course title");
					titleField.setText(courseToEdit.getCourseTitle());
					
					TextField crnField = new TextField();
					crnField.setPromptText("CRN (5 digits!!)");
					crnField.setText(courseToEdit.getCrn());
					crnField.setEditable(false);
					crnField.setFocusTraversable(false);
					crnField.setDisable(true);

					TextField creditField = new TextField();
					creditField.setPromptText("credits");
					creditField.setText(String.valueOf(courseToEdit.getCredits()));
					
					ObservableList<String> isbnList = FXCollections.observableArrayList();
					isbnList.addAll(college.getTextbookBag().listAllTextbookISBN());
					final ComboBox isbnListComboBox = new ComboBox(isbnList);

					/*TextField text1 = new TextField();
					TextField text2 = new TextField();*/

					GridPane grid2 = new GridPane();
					grid2.add(titleLabel, 1, 1);
					grid2.add(titleField, 2, 1);
					grid2.add(crnLabel, 1, 2);
					grid2.add(crnField, 2, 2);
					grid2.add(isbnLabel, 1, 3);
					grid2.add(isbnListComboBox, 2, 3);
					grid2.add(creditLabel, 1, 4);
					grid2.add(creditField, 2, 4);
					
					
					//grid2.add(gradeListComboBox, 2, 2);
					dialog2.getDialogPane().setContent(grid2);
					ButtonType buttonTypeOk = new ButtonType("Add", ButtonData.OK_DONE);
					ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
					dialog2.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);

					Optional<String> result = dialog2.showAndWait(); //need this to make the dialog pop up, crap tho

					Object isbnResult = isbnListComboBox.getValue();
					if(isbnResult == null) {
						Utilities.alert("Please select options.", AlertType.ERROR);
						return;
					}
					if(!creditField.getText().matches("[1234]{1}")) { //1, 2, 3 or 4. not really bulletproof but better than nothing. allows 11, 22, 13 etc
						Utilities.alert("Please enter a value from 1 to 4 credits.", AlertType.ERROR);
						return;
					}
					if(!crnField.getText().matches("[0-9]{5}")) { //regex for whole number, 5 digits
						Utilities.alert("Please enter a whole (0-9) numeric value for CRN.", AlertType.ERROR);
						return;
					}/*
					if(!college.getMasterCourseBag().exists(crnField.getText())) {
						Utilities.alert("If you're changing the CRN, just make a new class.", AlertType.ERROR);
						System.out.println("That course with that CRN already exists.");
						return;
					}*/
					//String courseTitle, String crn, Textbook textbook, double credits
					//Textbook txtbk = college.getTextbookBag().find(isbnListComboBox.getValue().toString());
					//Course c = new Course(titleField.getText(), crnField.getText(), txtbk, Double.parseDouble(creditField.getText()));
					
					//college.getMasterCourseBag().add(c);

					Textbook textbookToEdit;
					String shortened2 = choice.substring(0, 5);
					System.out.println("you chose: "+choice);
					System.out.println("shortened to crn: "+shortened);
					
					courseToEdit.setCourseTitle(titleField.getText());
					courseToEdit.setCredits(Double.valueOf(creditField.getText()));
					System.out.println("isbnListComboBox:::::: "+isbnListComboBox.getValue().toString());
					courseToEdit.setIsbn(isbnListComboBox.getValue().toString());
					System.out.println("Edited "+courseToEdit.getCrn()+" to MasterCourseBag successfully.");
					Utilities.alert("Edited "+courseToEdit.getCrn()+" to MasterCourseBag successfully.", AlertType.CONFIRMATION);

					//courseTableData.add(c);
					courseTable.refresh();
					courseTableData.clear();
					courseTableData.addAll(college.getMasterCourseBag().listAllCourses());

				} catch(Exception err) {
					Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
				}
				courseTableData.clear();
				courseTableData.addAll(college.getMasterCourseBag().listAllCourses());
			});
		});
		

		VBox btnBox = new VBox(20);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(addBtn, deleteBtn, editBtn);


		/** Add to grid */
		grid.add(helloLabel, 0, 0);
		grid.add(courseTable, 0, 1);
		grid.add(btnBox, 1, 1);

	}
	public GridPane getGridPane() {
		return grid;
	}
}
