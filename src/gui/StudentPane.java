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
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.College;
import model.Course;
import model.Faculty;
import model.Person;
import model.Student;
import util.Utilities;
import bags.CourseBag;

/**
 * 
 * @author Corey Schneider
 *
 */

public class StudentPane {

	GridPane grid = new GridPane();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public StudentPane(College college) {
		grid.setGridLinesVisible(false);

		grid.setPadding(new Insets(0, 8, 8, 40));
		grid.setHgap(20);
		grid.setVgap(20);

		final int BUTTON_WIDTH = 150;
		final int TEXTFIELD_WIDTH = 150;
		final int LABEL_WIDTH = 150;

		/** First name */
		Label firstNameLabel = new Label();
		firstNameLabel.setText("First name:");
		firstNameLabel.setAlignment(Pos.CENTER);
		firstNameLabel.setPrefWidth(LABEL_WIDTH);

		TextField firstNameTextField = new TextField();
		firstNameTextField.setPromptText("first name");
		firstNameTextField.setPrefWidth(TEXTFIELD_WIDTH);
		//firstNameTextField.setEditable(false);

		/** Last name */
		Label lastNameLabel = new Label();
		lastNameLabel.setText("Last name:");
		lastNameLabel.setAlignment(Pos.CENTER);
		lastNameLabel.setPrefWidth(LABEL_WIDTH);

		TextField lastNameTextField = new TextField();
		lastNameTextField.setPromptText("last name");
		lastNameTextField.setPrefWidth(TEXTFIELD_WIDTH);
		//lastNameTextField.setEditable(false);

		/** Phone number */
		Label phoneLabel = new Label();
		phoneLabel.setText("Phone number:");
		phoneLabel.setAlignment(Pos.CENTER);
		phoneLabel.setPrefWidth(LABEL_WIDTH);

		TextField phoneTextField = new TextField();
		phoneTextField.setPromptText("phone number");
		phoneTextField.setPrefWidth(TEXTFIELD_WIDTH);
		//phoneTextField.setEditable(false);

		/** Major */
		Label majorLabel = new Label();
		majorLabel.setText("Major:");
		majorLabel.setAlignment(Pos.CENTER);
		majorLabel.setPrefWidth(LABEL_WIDTH);

		TextField majorTextField = new TextField();
		majorTextField.setPromptText("major");
		majorTextField.setPrefWidth(TEXTFIELD_WIDTH);
		//salaryTextField.setEditable(false);

		/** GPA */
		Label gpaLabel = new Label();
		gpaLabel.setText("GPA:");
		gpaLabel.setAlignment(Pos.CENTER);
		gpaLabel.setPrefWidth(LABEL_WIDTH);

		TextField gpaTextField = new TextField();
		gpaTextField.setPromptText("GPA");
		gpaTextField.setPrefWidth(TEXTFIELD_WIDTH);
		gpaTextField.setEditable(false);
		gpaTextField.setDisable(true);

		/** ID */
		Label idLabel = new Label();
		idLabel.setText("ID:");
		idLabel.setAlignment(Pos.CENTER);
		idLabel.setPrefWidth(LABEL_WIDTH);

		TextField idTextField = new TextField();
		idTextField.setPromptText("ID");
		idTextField.setPrefWidth(TEXTFIELD_WIDTH);
		idTextField.setEditable(false);
		idTextField.setDisable(true);

		/** Courses */
		/** Taken */
		Label courseTakenLabel = new Label();
		courseTakenLabel.setText("Courses taken:");
		courseTakenLabel.setAlignment(Pos.CENTER);
		courseTakenLabel.setPrefWidth(LABEL_WIDTH);
		Label courseTakenLabel2 = new Label();
		courseTakenLabel2.setText("Courses taken:");
		courseTakenLabel2.setAlignment(Pos.CENTER);
		courseTakenLabel2.setPrefWidth(LABEL_WIDTH);

		Label courseIPLabel = new Label();
		courseIPLabel.setText("Courses in progress:");
		courseIPLabel.setAlignment(Pos.CENTER);
		courseIPLabel.setPrefWidth(LABEL_WIDTH);
		Label courseIPLabel2 = new Label();
		courseIPLabel2.setText("In progress:");
		courseIPLabel2.setAlignment(Pos.CENTER);
		courseIPLabel2.setPrefWidth(LABEL_WIDTH);

		Label courseFutureLabel = new Label();
		courseFutureLabel.setText("Future courses:");
		courseFutureLabel.setAlignment(Pos.CENTER);
		courseFutureLabel.setPrefWidth(LABEL_WIDTH);
		Label courseFutureLabel2 = new Label();
		courseFutureLabel2.setText("Future:");
		courseFutureLabel2.setAlignment(Pos.CENTER);
		courseFutureLabel2.setPrefWidth(LABEL_WIDTH);

		/** Course tables */
		TableView<Course> courseTakenTable = new TableView<Course>();
		courseTakenTable.setEditable(true);

		TableView<Course> courseIPTable = new TableView<Course>();
		courseIPTable.setEditable(true);

		TableView<Course> courseFutureTable = new TableView<Course>();
		courseFutureTable.setEditable(true);

		final ObservableList<Course> courseTakenTableData = FXCollections.observableArrayList();
		final ObservableList<Course> courseIPTableData = FXCollections.observableArrayList();
		final ObservableList<Course> courseFutureTableData = FXCollections.observableArrayList();
		boolean editMode = false;

		courseTakenTable.setItems(courseTakenTableData);
		TableColumn crnCol = new TableColumn("CRN");
		crnCol.setMinWidth(45);
		crnCol.setPrefWidth(45);
		crnCol.setCellValueFactory(new PropertyValueFactory<Course, String>("crn"));
		crnCol.setCellFactory(TextFieldTableCell.forTableColumn());
		if(editMode) {
			crnCol.setOnEditCommit(new EventHandler<CellEditEvent<Course, String>>() {
				@Override
				public void handle(CellEditEvent<Course, String> t) {
					((Course) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setCrn(t.getNewValue());
				}
			}
					);
		} else {
			crnCol.setEditable(false);
		}
		TableColumn gradeCol = new TableColumn("Grade");
		gradeCol.setMinWidth(45);
		gradeCol.setPrefWidth(45);
		gradeCol.setCellValueFactory(new PropertyValueFactory<Course, String>("letterGrade"));
		gradeCol.setCellFactory(TextFieldTableCell.forTableColumn());
		if(editMode) {
			gradeCol.setOnEditCommit(new EventHandler<CellEditEvent<Course, String>>() {
				@Override
				public void handle(CellEditEvent<Course, String> t) {
					((Course) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setLetterGrade(t.getNewValue());
				}
			}
					);
		} else {
			gradeCol.setEditable(false);
		}
		TableColumn courseNameCol = new TableColumn("Course name");
		courseNameCol.setMinWidth(50);
		courseNameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("courseTitle"));
		courseNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		courseNameCol.setEditable(false);
		courseTakenTable.setMaxWidth(150);
		courseTakenTable.getColumns().addAll(crnCol, gradeCol, courseNameCol);


		/** In Progress */
		courseIPTable.setItems(courseIPTableData);
		TableColumn crnCol2 = new TableColumn("CRN");
		crnCol2.setMinWidth(45);
		crnCol2.setPrefWidth(45);
		crnCol2.setCellValueFactory(new PropertyValueFactory<Course, String>("crn"));
		crnCol2.setCellFactory(TextFieldTableCell.forTableColumn());
		if(editMode) {
			crnCol2.setOnEditCommit(new EventHandler<CellEditEvent<Course, String>>() {
				@Override
				public void handle(CellEditEvent<Course, String> t) {
					((Course) t.getTableView().getItems().get(t.getTablePosition().getRow())
							).setCrn(t.getNewValue());
				}
			}
					);
		} else {
			crnCol2.setEditable(false);
		}
		TableColumn courseNameCol2 = new TableColumn("Course name");
		courseNameCol2.setMinWidth(50);
		/*		courseNameCol2.setCellValueFactory(new Callback<CellDataFeatures<Course, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<Course, String> p) {
		         // p.getValue() returns the Person instance for a particular TableView row
		         return new ReadOnlyObjectWrapper(Utilities.college.getMasterCourseBag().getCourseName(p.getValue()));
		     }
		  });*/
		courseNameCol2.setCellValueFactory(new PropertyValueFactory<Course, String>("courseTitle"));
		courseNameCol2.setCellFactory(TextFieldTableCell.forTableColumn());
		courseNameCol2.setEditable(false);
		courseIPTable.setMaxWidth(150);
		courseIPTable.getColumns().addAll(crnCol2, courseNameCol2);


		/** Future */
		courseFutureTable.setItems(courseFutureTableData);
		TableColumn crnCol3 = new TableColumn("CRN");
		crnCol3.setMinWidth(45);
		crnCol3.setPrefWidth(45);
		crnCol3.setCellValueFactory(new PropertyValueFactory<Course, String>("crn"));
		crnCol3.setCellFactory(TextFieldTableCell.forTableColumn());
		crnCol3.setOnEditCommit(new EventHandler<CellEditEvent<Course, String>>() {
			@Override
			public void handle(CellEditEvent<Course, String> t) {
				((Course) t.getTableView().getItems().get(
						t.getTablePosition().getRow())
						).setCrn(t.getNewValue());
			}
		}
				);
		TableColumn courseNameCol3 = new TableColumn("Course name");
		courseNameCol3.setMinWidth(50);
		courseNameCol3.setCellValueFactory(new PropertyValueFactory<Course, String>("courseTitle"));
		courseNameCol3.setCellFactory(TextFieldTableCell.forTableColumn());
		courseNameCol3.setEditable(false);

		courseFutureTable.setMaxWidth(150);
		courseFutureTable.getColumns().addAll(crnCol3, courseNameCol3);

		/** Buttons */
		Button addBtn = new Button("Add student");
		addBtn.setPrefWidth(BUTTON_WIDTH);
		addBtn.setAlignment(Pos.TOP_CENTER);
		addBtn.setOnAction(e -> {
			System.out.println("Add student button pressed");
			try {
				//String firstName, String lastName, String phoneNumber, String major
				if(firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty()
						|| phoneTextField.getText().isEmpty() || majorTextField.getText().isEmpty()) {
					Utilities.alert("You left something blank. Go back and check again.", AlertType.ERROR);
					return;
				}
				Student student = new Student(firstNameTextField.getText(), lastNameTextField.getText(), phoneTextField.getText(), majorTextField.getText(), new CourseBag());
				college.getPeopleBag().add(student);
				System.out.println(student);
				idTextField.setText(student.getId());
				/*Faculty faculty = new Faculty(firstNameTextField.getText(), lastNameTextField.getText(), phoneTextField.getText(), titleTextField.getText(), new Double(salaryTextField.getText()));
				college.getPeopleBag().add(faculty);
				System.out.println(faculty);
				idTextField.setText(faculty.getId());*/
				Utilities.alert("Successfully added "+student.getFirstName()+" to the person bag!\nRemember to SAVE the college before exiting.", AlertType.INFORMATION);
			} catch(Exception err) {
				Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
			}
		});

		Button findBtn = new Button("Find student");
		findBtn.setPrefWidth(BUTTON_WIDTH);
		findBtn.setAlignment(Pos.TOP_CENTER);
		CourseBag courseBag = new CourseBag();
		findBtn.setOnAction(e -> {
			System.out.println("Find student button pressed");
			try {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Find Student");
				dialog.setHeaderText("Find student by ID");
				dialog.setContentText("Please enter the ID:");
				Optional<String> idInput = dialog.showAndWait();
				idInput.ifPresent(name -> {
					Person result = college.getPeopleBag().find(idInput.get());
					if(result instanceof Faculty) {
						Utilities.alert("Person with ID "+idInput.get()+" was found, however, it's a Faculty member.\nTry another ID.", AlertType.ERROR);
						System.out.println("This is an instance of a faculty member.");
					} else if(result instanceof Student) {
						courseBag.addTaken(((Student) result).getCourseBag().getCourseTakenArray());
						courseBag.addIP(((Student) result).getCourseBag().getCoursesInProgress());
						courseBag.addFuture(((Student) result).getCourseBag().getCoursesRequiredFuture());
						//lastNameTextField.getText(), phoneTextField.getText(),
						//titleTextField.getText(), new Double(salaryTextField.getText())
						firstNameTextField.setText(result.getFirstName());
						lastNameTextField.setText(result.getLastName());
						phoneTextField.setText(result.getPhoneNumber());
						majorTextField.setText(((Student) result).getMajor());
						gpaTextField.setText(String.valueOf(((Student) result).getGpa()));
						idTextField.setText(result.getId());
						courseTakenTableData.clear();
						courseTakenTableData.addAll(((Student) result).getCourseBag().getCourseTakenArray());
						courseIPTableData.clear();
						courseIPTableData.addAll(((Student) result).getCourseBag().getCoursesInProgress());
						courseFutureTableData.clear();
						courseFutureTableData.addAll(((Student) result).getCourseBag().getCoursesRequiredFuture());
						System.out.println(result.toBasicString());
					} else {
						Utilities.alert("Person with ID "+idInput.get()+" does not exist in the system.", AlertType.ERROR);
						System.out.println("This person does not exist.");
					}
				});
			} catch(Exception err) {
				Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
			}
		});

		Button removeBtn = new Button("Remove student");
		removeBtn.setPrefWidth(BUTTON_WIDTH);
		removeBtn.setAlignment(Pos.TOP_CENTER);
		removeBtn.setOnAction(e -> {
			System.out.println("Remove student button pressed");
			try {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Delete Student");
				dialog.setHeaderText("Delete student by ID");
				dialog.setContentText("Please enter the ID:");
				Optional<String> idInput = dialog.showAndWait();
				idInput.ifPresent(name -> {
					Person result = college.getPeopleBag().find(idInput.get());
					if(result instanceof Faculty) {
						Utilities.alert("Person with ID "+idInput.get()+" was found, however, it's a Faculty member.\nTry another ID.", AlertType.ERROR);
						System.out.println("This is an instance of a faculty.");
					}
					if(result instanceof Student) {
						System.out.println("this is an instance of a student.");
						college.getPeopleBag().delete(idInput.get());
					}
				});
			} catch(Exception err) {
				Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
			}
		});

		Button updateBtn = new Button("Update student");
		updateBtn.setAlignment(Pos.TOP_CENTER);
		updateBtn.setPrefWidth(BUTTON_WIDTH);
		updateBtn.setOnAction(e -> {
			System.out.println("Update student button pressed");
			try {
				if(!idTextField.getText().isEmpty()) {
					Student result = (Student)college.getPeopleBag().find(idTextField.getText());
					result.setFirstName(firstNameTextField.getText());
					result.setLastName(lastNameTextField.getText());
					result.setPhoneNumber(phoneTextField.getText());
					result.setMajor(majorTextField.getText());
					//result.getCourseBag().setCourseBag(courseBag);

					//We do NOT change ID or GPA here.
					Utilities.alert("Successfully updated person!\nRemember to press \"SAVE\" before exiting.", AlertType.CONFIRMATION);
					System.out.println("talkin about this guy? "+result.getFirstName());
				} else {
					Utilities.alert("Whoops! Couldn't update that person.\nTry pressing \"Find\" to load them first.", AlertType.WARNING);
				}
			} catch(Exception err) {
				Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
			}
		});

		Button addCourseTknBtn = new Button("Add");
		addCourseTknBtn.setPrefWidth(70);
		addCourseTknBtn.setAlignment(Pos.TOP_CENTER);
		addCourseTknBtn.setTooltip(new Tooltip("Add course taken."));
		addCourseTknBtn.setOnAction(e -> {
			System.out.println("add course taken button pressed");
			if(!idTextField.getText().isEmpty()) {
				try {
					Dialog<String> dialog = new Dialog<>();
					dialog.setTitle("Add course taken");
					dialog.setHeaderText("Add course taken. Be sure to enter the grade\nas a letter grade (A, B, B+).");
					dialog.setResizable(true);

					Label label1 = new Label("CRN: ");
					Label label2 = new Label("Grade: ");
					ObservableList<String> courseList = FXCollections.observableArrayList();
					courseList.addAll(college.getMasterCourseBag().listAllCoursesCrnName());
					final ComboBox courseListComboBox = new ComboBox(courseList);

					ObservableList<String> gradeList = FXCollections.observableArrayList();
					gradeList.addAll("A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F");
					final ComboBox gradeListComboBox = new ComboBox(gradeList);

					/*TextField text1 = new TextField();
					TextField text2 = new TextField();*/

					GridPane grid2 = new GridPane();
					grid2.add(label1, 1, 1);
					grid2.add(courseListComboBox, 2, 1);
					grid2.add(label2, 1, 2);
					grid2.add(gradeListComboBox, 2, 2);
					dialog.getDialogPane().setContent(grid2);
					ButtonType buttonTypeOk = new ButtonType("Add", ButtonData.OK_DONE);
					ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
					dialog.getDialogPane().getButtonTypes().addAll(buttonTypeOk, buttonTypeCancel);

					Optional<String> result = dialog.showAndWait(); //need this to make the dialog pop up, crap tho

					Object courseResult = courseListComboBox.getValue();
					Object gradeResult = gradeListComboBox.getValue();
					if(courseResult == null || gradeResult == null) {
						Utilities.alert("Please select options.", AlertType.ERROR);
						return;
					}
					String course = courseResult.toString().substring(0, 5);
					String grade = gradeResult.toString();
					System.out.println("course: "+course);
					System.out.println("grade: "+grade);

					Course tknCourse = college.getMasterCourseBag().find(course);
					tknCourse.setLetterGrade(grade);

					Student s = (Student)college.getPeopleBag().find(idTextField.getText());
					System.out.println("@@@@@@ GPA: "+s.getGpa());
					s.getCourseBag().addTaken(tknCourse);
					courseTakenTableData.clear();
					courseTakenTableData.addAll(s.getCourseBag().getCourseTakenArray());
					s.refreshGpa();
					gpaTextField.setText(String.valueOf(s.getGpa()));

				} catch(Exception err) {
					Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
				}
			} else {
				Utilities.alert("Try loading a student first.", AlertType.INFORMATION);
			}
		});

		Button addCourseIPBtn = new Button("Add");
		addCourseIPBtn.setPrefWidth(70);
		addCourseIPBtn.setAlignment(Pos.TOP_CENTER);
		addCourseIPBtn.setTooltip(new Tooltip("Add course in progress."));
		addCourseIPBtn.setOnAction(e -> {
			if(!idTextField.getText().isEmpty()) {
				try {
					List<String> newList = Arrays.asList(college.getMasterCourseBag().listAllCoursesCrnName());

					ChoiceDialog<String> dialog = new ChoiceDialog<>("", newList);
					dialog.setTitle("Add course in progress");
					dialog.setHeaderText("Add course in progress");
					dialog.setContentText("Please choose the CRN:");

					Optional<String> idInput = dialog.showAndWait();
					idInput.ifPresent(choice -> {
						String shortened = choice.substring(0, 5);
						System.out.println("you chose: "+choice);
						System.out.println("shortened to crn: "+shortened);
						Course result = college.getMasterCourseBag().find(shortened);
						if(result != null) {
							System.out.println("this what we're talkn about?: "+result);
							Student s = (Student)college.getPeopleBag().find(idTextField.getText());
							s.getCourseBag().addIP(result);
							courseIPTableData.add(result);
							courseIPTable.refresh();
							courseIPTableData.clear();
							courseIPTableData.addAll(s.getCourseBag().getCoursesInProgress());
						}
					});
				} catch(Exception err) {
					Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
				}
			} else {
				Utilities.alert("Try loading a student first.", AlertType.INFORMATION);
			}
		});

		Button addCourseFutureBtn = new Button("Add");
		addCourseFutureBtn.setPrefWidth(70);
		addCourseFutureBtn.setPrefHeight(Button.USE_PREF_SIZE);
		addCourseFutureBtn.setAlignment(Pos.TOP_CENTER);
		GridPane.setValignment(addCourseFutureBtn, VPos.TOP);
		addCourseFutureBtn.setTooltip(new Tooltip("Add future course."));
		addCourseFutureBtn.setOnAction(e -> {
			System.out.println("add course future button pressed");
			if(!idTextField.getText().isEmpty()) {
				try {
					List<String> newList = Arrays.asList(college.getMasterCourseBag().listAllCoursesCrnName());

					ChoiceDialog<String> dialog = new ChoiceDialog<>("", newList);
					dialog.setTitle("Add future course");
					dialog.setHeaderText("Add future course");
					dialog.setContentText("Please enter the CRN:");

					Optional<String> idInput = dialog.showAndWait();
					idInput.ifPresent(choice -> {
						String shortened = choice.substring(0, 5);
						System.out.println("you chose: "+choice);
						System.out.println("shortened to crn: "+shortened);
						Course result = college.getMasterCourseBag().find(shortened);
						if(result != null) {
							System.out.println("this what we're talkn about?: "+result);
							Student s = (Student)college.getPeopleBag().find(idTextField.getText());
							s.getCourseBag().addFuture(result);
							courseFutureTableData.add(result);
							courseFutureTable.refresh();
							courseFutureTableData.clear();
							courseFutureTableData.addAll(s.getCourseBag().getCoursesRequiredFuture());
						}
					});
					/*					Optional<String> idInput = dialog.showAndWait();
					idInput.ifPresent(crnNum -> {
						Course result = college.getMasterCourseBag().find(idInput.get());
						if(result != null) {
							System.out.println("this what we're talkn about?: "+result+" ------- "+result.getType());
							Student s = (Student)college.getPeopleBag().find(idTextField.getText());
							s.getCourseBag().addFuture(result);
							courseIPTableData.add(result);
							courseIPTable.refresh();
							courseFutureTableData.clear();
							courseFutureTableData.addAll(s.getCourseBag().getCoursesRequiredFuture());
						}
					});*/
				} catch(Exception err) {
					Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
				}
			} else {
				Utilities.alert("Try loading a student first.", AlertType.INFORMATION);
			}
		});

		Button deleteCourseTakenBtn = new Button("Delete");
		deleteCourseTakenBtn.setPrefWidth(70);
		deleteCourseTakenBtn.setPrefHeight(Button.USE_PREF_SIZE);
		deleteCourseTakenBtn.setAlignment(Pos.TOP_CENTER);
		GridPane.setHalignment(deleteCourseTakenBtn, HPos.RIGHT);
		deleteCourseTakenBtn.setTooltip(new Tooltip("Delete course taken."));
		deleteCourseTakenBtn.setOnAction(e -> {
			System.out.println("delete course taken button pressed");

			if(!idTextField.getText().isEmpty()) {
				try {
					Student s = (Student)college.getPeopleBag().find(idTextField.getText());
					List<String> newList = Arrays.asList(s.getCourseBag().getCourseTakenString());

					ChoiceDialog<String> dialog = new ChoiceDialog<>("", newList);
					dialog.setTitle("Delete course taken");
					dialog.setHeaderText("Delete course taken");
					dialog.setContentText("Please choose the CRN:");

					Optional<String> idInput = dialog.showAndWait();
					idInput.ifPresent(choice -> {
						String shortened = choice.substring(0, 5);
						System.out.println("you chose: "+choice);
						System.out.println("shortened to crn: "+shortened);
						Course result = college.getMasterCourseBag().find(shortened);
						if(result != null) {
							System.out.println("this what we're talkn about?: "+result);
							s.getCourseBag().deleteTaken(result);
							//courseIPTableData.remove(result);
							//courseIPTable.refresh();
							courseTakenTableData.clear();
							courseTakenTableData.addAll(s.getCourseBag().getCourseTakenArray());
							s.refreshGpa();
							gpaTextField.setText(String.valueOf(s.getGpa()));
						}
					});
				} catch(Exception err) {
					Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
				}
			} else {
				Utilities.alert("Try loading a student first.", AlertType.INFORMATION);
			}
		});

		Button deleteCourseIPBtn = new Button("Delete");
		deleteCourseIPBtn.setPrefWidth(70);
		deleteCourseIPBtn.setPrefHeight(Button.USE_PREF_SIZE);
		deleteCourseIPBtn.setAlignment(Pos.TOP_CENTER);
		GridPane.setHalignment(deleteCourseIPBtn, HPos.RIGHT);
		deleteCourseIPBtn.setTooltip(new Tooltip("Delete course in progress."));
		deleteCourseIPBtn.setOnAction(e -> {
			System.out.println("delete course in progress button pressed");

			if(!idTextField.getText().isEmpty()) {
				try {
					Student s = (Student)college.getPeopleBag().find(idTextField.getText());
					List<String> newList = Arrays.asList(s.getCourseBag().getCoursesInProgressString());

					ChoiceDialog<String> dialog = new ChoiceDialog<>("", newList);
					dialog.setTitle("Delete course in progress");
					dialog.setHeaderText("Delete course in progress");
					dialog.setContentText("Please choose the CRN:");

					Optional<String> idInput = dialog.showAndWait();
					idInput.ifPresent(choice -> {
						String shortened = choice.substring(0, 5);
						System.out.println("you chose: "+choice);
						System.out.println("shortened to crn: "+shortened);
						Course result = college.getMasterCourseBag().find(shortened);
						if(result != null) {
							System.out.println("this what we're talkn about?: "+result);
							s.getCourseBag().deleteIP(result);
							//courseIPTableData.remove(result);
							//courseIPTable.refresh();
							courseIPTableData.clear();
							courseIPTableData.addAll(s.getCourseBag().getCoursesInProgress());
						}
					});
				} catch(Exception err) {
					Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
				}
			} else {
				Utilities.alert("Try loading a student first.", AlertType.INFORMATION);
			}
		});

		Button deleteCourseFutureBtn = new Button("Delete");
		deleteCourseFutureBtn.setPrefWidth(70);
		deleteCourseFutureBtn.setPrefHeight(Button.USE_PREF_SIZE);
		deleteCourseFutureBtn.setAlignment(Pos.TOP_CENTER);
		GridPane.setValignment(deleteCourseFutureBtn, VPos.TOP);
		GridPane.setHalignment(deleteCourseFutureBtn, HPos.RIGHT);
		deleteCourseFutureBtn.setTooltip(new Tooltip("Delete future course."));
		deleteCourseFutureBtn.setOnAction(e -> {
			System.out.println("delete course future button pressed");

			if(!idTextField.getText().isEmpty()) {
				try {
					Student s = (Student)college.getPeopleBag().find(idTextField.getText());
					List<String> newList = Arrays.asList(s.getCourseBag().getCoursesRequiredFutureString());

					ChoiceDialog<String> dialog = new ChoiceDialog<>("", newList);
					dialog.setTitle("Delete future course");
					dialog.setHeaderText("Delete future course");
					dialog.setContentText("Please choose the CRN:");

					Optional<String> idInput = dialog.showAndWait();
					idInput.ifPresent(choice -> {
						String shortened = choice.substring(0, 5);
						System.out.println("you chose: "+choice);
						System.out.println("shortened to crn: "+shortened);
						Course result = college.getMasterCourseBag().find(shortened);
						if(result != null) {
							System.out.println("this what we're talkn about?: "+result);
							s.getCourseBag().deleteFuture(result);
							//courseIPTableData.remove(result);
							//courseIPTable.refresh();
							courseFutureTableData.clear();
							courseFutureTableData.addAll(s.getCourseBag().getCoursesRequiredFuture());
						}
					});
				} catch(Exception err) {
					Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
				}
			} else {
				Utilities.alert("Try loading a student first.", AlertType.INFORMATION);
			}
		});

		HBox btnBox = new HBox(20);

		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(addBtn, findBtn, removeBtn, updateBtn);

		grid.add(firstNameLabel, 0, 0);
		grid.add(firstNameTextField, 0, 1);

		grid.add(lastNameLabel, 1, 0);
		grid.add(lastNameTextField, 1, 1);

		grid.add(phoneLabel, 2, 0);
		grid.add(phoneTextField, 2, 1);

		grid.add(majorLabel, 0, 2);
		grid.add(majorTextField, 0, 3);

		grid.add(gpaLabel, 1, 2);
		grid.add(gpaTextField, 1, 3);

		grid.add(idLabel, 2, 2);
		grid.add(idTextField, 2, 3);

		grid.add(courseTakenLabel, 0, 4);
		grid.add(courseTakenTable, 0, 5);

		grid.add(courseIPLabel, 1, 4);
		grid.add(courseIPTable, 1, 5);

		grid.add(courseFutureLabel, 2, 4);
		grid.add(courseFutureTable, 2, 5);


		grid.add(courseTakenLabel2, 3, 0);
		grid.add(addCourseTknBtn, 3, 1);
		grid.add(deleteCourseTakenBtn, 3, 1);

		grid.add(courseIPLabel2, 3, 2);
		grid.add(addCourseIPBtn, 3, 3);
		grid.add(deleteCourseIPBtn, 3, 3);

		grid.add(courseFutureLabel2, 3, 4);
		grid.add(addCourseFutureBtn, 3, 5);
		grid.add(deleteCourseFutureBtn, 3, 5);

		//grid.add(phoneField, 2, 0, 2, 1);
		//		gridPane.add(addBtn, 0, 1);
		//		gridPane.add(findBtn, 1, 1);
		//		gridPane.add(removeBtn, 2, 1);
		//		gridPane.add(updateBtn, 3, 1);

		//[child], column, rows, column span, row span
		grid.add(addBtn, 0, 6);
		grid.add(findBtn, 1, 6);
		grid.add(removeBtn, 2, 6);
		grid.add(updateBtn, 3, 6);
		//grid.add(btnBox, 3, 8, 2, 5); //[child], column, rows, column span, row span
	}

	public GridPane getGridPane() {
		return grid;
	}

}
