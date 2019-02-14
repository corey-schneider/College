package gui;

import java.util.Arrays;

import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import model.*;

public class AllStudentPane {

	private GridPane grid;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AllStudentPane(College college) {
		grid = new GridPane();
		grid.setGridLinesVisible(true);
		grid.setPadding(new Insets(0, 10, 10, 20));

		/** Labels */
		Label helloLabel = new Label();
		helloLabel.setText("Below are all the students currently enrolled in the college.");
		GridPane.setHalignment(helloLabel, HPos.CENTER);
		//helloLabel.setPrefWidth(150);


		/** Table */
		TableView<Student> studentTable = new TableView<Student>();
		studentTable.setEditable(false);
		final ObservableList<Student> studentTableData = FXCollections.observableArrayList();
		studentTable.setItems(studentTableData);
		Student[] studentData;
		studentData = college.getPeopleBag().listAllStudents();
		System.out.println("hi. "+Arrays.toString(college.getPeopleBag().listAllStudents()));
		//System.out.println("hi. students: "+college.getPeopleBag().listAllStudentsString());
		//System.out.println(studentData[1]);
		studentTableData.addAll(studentData);


		TableColumn idCol = new TableColumn("ID");
		idCol.setMinWidth(25);
		idCol.setPrefWidth(25);
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn firstNameCol = new TableColumn("First name");
		firstNameCol.setMinWidth(50);
		firstNameCol.setPrefWidth(120);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
		firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());

		TableColumn lastNameCol = new TableColumn("Last name");
		lastNameCol.setMinWidth(50);
		lastNameCol.setPrefWidth(120);
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		//creditCol.setCellFactory(TextFieldTableCell.forTableColumn());
		/*		creditCol.setOnEditCommit(new EventHandler<CellEditEvent<Course, String>>() {
			@Override
			public void handle(CellEditEvent<Course, String> t) {
				((Course) t.getTableView().getItems().get(
						t.getTablePosition().getRow())
						).setCredits(Double.parseDouble(t.getNewValue()));
			}
		});*/

		TableColumn majorCol = new TableColumn("Major");
		majorCol.setMinWidth(90);
		majorCol.setPrefWidth(130);
		majorCol.setCellValueFactory(new PropertyValueFactory<>("major"));

		TableColumn gpaCol = new TableColumn("GPA");
		gpaCol.setMinWidth(45);
		gpaCol.setPrefWidth(45);
		gpaCol.setCellValueFactory(new PropertyValueFactory<>("gpa"));
		
		TableColumn phoneCol = new TableColumn("Phone number");
		phoneCol.setMinWidth(90);
		phoneCol.setPrefWidth(110);
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		

		studentTable.setMaxWidth(600);
		studentTable.setMaxHeight(380);
		studentTable.setPrefWidth(600);
		studentTable.getColumns().addAll(idCol, firstNameCol, lastNameCol, majorCol, gpaCol, phoneCol/*, authorCol, publisherCol, priceCol*/);


		/** Buttons */
		Button addBtn = new Button("Add textbook");
		addBtn.setPrefWidth(100);
		addBtn.setAlignment(Pos.TOP_CENTER);
		addBtn.setOnAction(e -> {
			System.out.println("Add textbook button pressed");
/*
			try {
				Dialog<String> dialog = new Dialog<>();
				dialog.setTitle("Add Textbook");
				dialog.setHeaderText("Add textbook.");
				dialog.setResizable(true);

				Label titleLabel = new Label("Title:");
				Label isbnLabel = new Label("ISBN:");
				Label authorLabel = new Label("Author:");
				Label publisherLabel = new Label("Publisher:");
				Label priceLabel = new Label("Cost:");

				TextField titleField = new TextField();
				titleField.setPromptText("course title");

				TextField isbnField = new TextField();
				isbnField.setPromptText("ISBN (x digits!!)");

				TextField authorField = new TextField();
				authorField.setPromptText("author");

				TextField publisherField = new TextField();
				publisherField.setPromptText("publisher");

				TextField priceField = new TextField();
				priceField.setPromptText("cost");

				GridPane grid2 = new GridPane();
				grid2.add(titleLabel, 1, 1);
				grid2.add(titleField, 2, 1);
				grid2.add(isbnLabel, 1, 2);
				grid2.add(isbnField, 2, 2);
				grid2.add(authorLabel, 1, 3);
				grid2.add(authorField, 2, 3);
				grid2.add(publisherLabel, 1, 4);
				grid2.add(publisherField, 2, 4);
				grid2.add(priceLabel, 1, 5);
				grid2.add(priceField, 2, 5);


				//grid2.add(gradeListComboBox, 2, 2);
				dialog.getDialogPane().setContent(grid2);
				ButtonType buttonTypeOk = new ButtonType("Add", ButtonData.OK_DONE);
				ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
				dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);

				Optional<String> result = dialog.showAndWait(); //need this to make the dialog pop up, crap tho

				if(!isbnField.getText().matches("[0-9]{3}\\-[0-9]{10}")) { //"[0-9]{1,3}\-[0-9]{10}" regex for [(3#)-(10#)] -> ###-##########
					Utilities.alert("ISBN should have 3 digits followed by a dash and 10 digits.\nExample: 978-1234567890", AlertType.ERROR);
					return;
				}
				//String title, String author, String publisher, String ISBN, double price
				Textbook txtbk = new Textbook(titleField.getText(), authorField.getText(), publisherField.getText(), isbnField.getText(), Double.valueOf(priceField.getText()));

				college.getTextbookBag().add(txtbk);
				System.out.println("Added "+txtbk+" to TextbookBag successfully.");
				Utilities.alert("Added "+txtbk+" to TextbookBag successfully.", AlertType.CONFIRMATION);

				studentTableData.add(txtbk);
				studentTable.refresh();
				studentTableData.clear();
				studentTableData.addAll(college.getTextbookBag().listAllTextbooks());

			} catch(Exception err) {
				Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
			}*/

		});

		Button deleteBtn = new Button("Delete textbook");
		deleteBtn.setPrefWidth(100);
		deleteBtn.setAlignment(Pos.TOP_CENTER);
		deleteBtn.setOnAction(e -> {
			System.out.println("Delete textbook button pressed");
/*			try {
				String[] textbooks = college.getTextbookBag().listAllTextbooksString();
				List<String> newList = Arrays.asList(textbooks);

				ChoiceDialog<String> dialog = new ChoiceDialog<>("", newList);
				dialog.setTitle("Delete textbook");
				dialog.setHeaderText("Delete textbooks");
				dialog.setContentText("Please choose the ISBN:");

				Optional<String> idInput = dialog.showAndWait();
				idInput.ifPresent(choice -> {
					Textbook textbookToRemove;
					String shortened = choice.substring(0, 14);
					System.out.println("you chose: "+choice);
					System.out.println("shortened to crn: "+shortened);

					textbookToRemove = college.getTextbookBag().find(shortened);
					System.out.println("found this textbook: "+textbookToRemove);
					college.getTextbookBag().deleteISBN(shortened);

					studentTableData.clear();
					studentTableData.addAll(college.getTextbookBag().listAllTextbooks());
				});
			} catch(Exception ex) {
				Utilities.alert("error "+ex, AlertType.ERROR);
			}*/

		});

		Button editBtn = new Button("Edit textbook");
		editBtn.setPrefWidth(100);
		editBtn.setAlignment(Pos.TOP_CENTER);
		editBtn.setOnAction(e -> {
/*			try {
				String[] textbooks = college.getTextbookBag().listAllTextbooksString();
				List<String> newList = Arrays.asList(textbooks);

				ChoiceDialog<String> dialog = new ChoiceDialog<>("", newList);
				dialog.setTitle("Edit textbook");
				dialog.setHeaderText("Edit textbooks");
				dialog.setContentText("Please choose the ISBN:");

				Optional<String> idInput = dialog.showAndWait();
				idInput.ifPresent(choice -> {
					Textbook textbookToEdit;
					String shortened = choice.substring(0, 14);
					System.out.println("you chose: "+choice);
					System.out.println("shortened to crn: "+shortened);

					textbookToEdit = college.getTextbookBag().find(shortened);
					System.out.println("found this textbook: "+textbookToEdit);
					//college.getTextbookBag().deleteISBN(shortened);

					courseTableData.remove(courseToRemove);
					courseTable.refresh();


					try {
						Dialog<String> dialog2 = new Dialog<>();
						dialog2.setTitle("Edit Textbook");
						dialog2.setHeaderText("Edit textbook.");
						dialog2.setResizable(true);

						Label titleLabel = new Label("Title:");
						Label isbnLabel = new Label("ISBN:");
						Label authorLabel = new Label("Author:");
						Label publisherLabel = new Label("Publisher:");
						Label priceLabel = new Label("Cost:");

						TextField titleField = new TextField();
						titleField.setPromptText("course title");
						titleField.setText(textbookToEdit.getTitle());

						TextField isbnField = new TextField();
						isbnField.setPromptText("ISBN (x digits!!)");
						isbnField.setEditable(false);
						isbnField.setDisable(true);
						isbnField.setText(textbookToEdit.getISBN());

						TextField authorField = new TextField();
						authorField.setPromptText("author");
						authorField.setText(textbookToEdit.getAuthor());

						TextField publisherField = new TextField();
						publisherField.setPromptText("publisher");
						publisherField.setText(textbookToEdit.getPublisher());

						TextField priceField = new TextField();
						priceField.setPromptText("cost");
						priceField.setText(String.valueOf(textbookToEdit.getPrice()));

						TextField text1 = new TextField();
						TextField text2 = new TextField();

						GridPane grid2 = new GridPane();
						grid2.add(titleLabel, 1, 1);
						grid2.add(titleField, 2, 1);
						grid2.add(isbnLabel, 1, 2);
						grid2.add(isbnField, 2, 2);
						grid2.add(authorLabel, 1, 3);
						grid2.add(authorField, 2, 3);
						grid2.add(publisherLabel, 1, 4);
						grid2.add(publisherField, 2, 4);
						grid2.add(priceLabel, 1, 5);
						grid2.add(priceField, 2, 5);


						//grid2.add(gradeListComboBox, 2, 2);
						dialog2.getDialogPane().setContent(grid2);
						ButtonType buttonTypeOk = new ButtonType("Save", ButtonData.OK_DONE);
						ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
						dialog2.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);

						Optional<String> result = dialog2.showAndWait(); //need this to make the dialog pop up, crap tho

						//String title, String author, String publisher, String ISBN, double price
						Textbook txtbk = new Textbook(titleField.getText(), authorField.getText(), publisherField.getText(), isbnField.getText(), Double.valueOf(priceField.getText()));

						textbookToEdit.setTitle(titleField.getText());
						textbookToEdit.setAuthor(authorField.getText());
						textbookToEdit.setPublisher(publisherField.getText());
						textbookToEdit.setPrice(Double.valueOf(priceField.getText()));
						
						System.out.println("Updated "+textbookToEdit.getTitle()+" to TextbookBag successfully.");
						Utilities.alert("Updated "+textbookToEdit.getTitle()+" to TextbookBag successfully.", AlertType.CONFIRMATION);


						studentTableData.clear();
						studentTableData.addAll(college.getTextbookBag().listAllTextbooks());



					} catch(Exception ex) {
						Utilities.alert("error "+ex, AlertType.ERROR);
					}
				});

			} catch(Exception ex) {
				Utilities.alert("error "+ex, AlertType.ERROR);
			}*/
		});

		VBox btnBox = new VBox(20);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(addBtn, deleteBtn, editBtn);


		/** Add to grid */
		grid.add(helloLabel, 0, 0);
		grid.add(studentTable, 0, 1);
		grid.add(btnBox, 1, 1);
	}

	public GridPane getGridPane() {
		return grid;
	}
}
