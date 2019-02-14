package gui;

/**
 * 
 * @author Corey Schneider
 *
 */

import java.util.Optional;

import model.College;
import model.Faculty;
import model.Person;
import model.Student;
import util.Utilities;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FacultyPane {

	private GridPane grid = new GridPane();
	public FacultyPane(College college) {
		grid.setGridLinesVisible(false);

		grid.setPadding(new Insets(50, 8, 8, 130));
		grid.setHgap(20);
		grid.setVgap(20);

		final int BUTTON_WIDTH = 100;
		final int TEXTFIELD_WIDTH = 150;
		final int LABEL_WIDTH = 150;

		/** Title */
		Label titleLabel = new Label();
		titleLabel.setText("Title: ");
		titleLabel.setAlignment(Pos.CENTER);
		titleLabel.setPrefWidth(LABEL_WIDTH);
		titleLabel.setMaxWidth(TEXTFIELD_WIDTH);

		TextField titleTextField = new TextField();
		titleTextField.setPromptText("title");
		titleTextField.setPrefWidth(TEXTFIELD_WIDTH);
		titleTextField.setMaxWidth(TEXTFIELD_WIDTH);
		//titleTextField.setEditable(false);

		/** First name */
		Label firstNameLabel = new Label();
		firstNameLabel.setText("First name: ");
		firstNameLabel.setAlignment(Pos.CENTER);
		firstNameLabel.setPrefWidth(LABEL_WIDTH);

		TextField firstNameTextField = new TextField();
		firstNameTextField.setPromptText("first name");
		firstNameTextField.setPrefWidth(TEXTFIELD_WIDTH);
		//firstNameTextField.setEditable(false);

		/** Last name */
		Label lastNameLabel = new Label();
		lastNameLabel.setText("Last name: ");
		lastNameLabel.setAlignment(Pos.CENTER);
		lastNameLabel.setPrefWidth(LABEL_WIDTH);

		TextField lastNameTextField = new TextField();
		lastNameTextField.setPromptText("last name");
		lastNameTextField.setPrefWidth(TEXTFIELD_WIDTH);
		//lastNameTextField.setEditable(false);

		/** Phone number */
		Label phoneLabel = new Label();
		phoneLabel.setText("Phone number: ");
		phoneLabel.setAlignment(Pos.CENTER);
		phoneLabel.setPrefWidth(LABEL_WIDTH);
		phoneLabel.setMaxWidth(TEXTFIELD_WIDTH);

		TextField phoneTextField = new TextField();
		phoneTextField.setPromptText("phone number");
		phoneTextField.setPrefWidth(TEXTFIELD_WIDTH);
		phoneTextField.setMaxWidth(TEXTFIELD_WIDTH);
		//phoneTextField.setEditable(false);

		/** Salary */
		Label salaryLabel = new Label();
		salaryLabel.setText("Salary: ");
		salaryLabel.setAlignment(Pos.CENTER);
		salaryLabel.setPrefWidth(LABEL_WIDTH);

		TextField salaryTextField = new TextField();
		salaryTextField.setPromptText("salary");
		salaryTextField.setPrefWidth(TEXTFIELD_WIDTH);
		//salaryTextField.setEditable(false);

		/** ID */
		Label idLabel = new Label();
		idLabel.setText("ID: ");
		idLabel.setAlignment(Pos.CENTER);
		idLabel.setPrefWidth(LABEL_WIDTH);

		TextField idTextField = new TextField();
		idTextField.setPromptText("ID");
		idTextField.setPrefWidth(TEXTFIELD_WIDTH);
		idTextField.setEditable(false);
		idTextField.setDisable(true);


		/** Buttons */
		Button addBtn = new Button("Add faculty");
		addBtn.setPrefWidth(BUTTON_WIDTH);
		addBtn.setOnAction(e -> {
			System.out.println("Add faculty button pressed");
			try {
				if(firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty()
						|| phoneTextField.getText().isEmpty() || titleTextField.getText().isEmpty() || salaryTextField.getText().isEmpty()) {
					Utilities.alert("You left something blank. Go back and check again.", AlertType.ERROR);
					return;
				}
				//String firstName, String lastName, String phoneNumber, String title, double salary
				Faculty faculty = new Faculty(firstNameTextField.getText(), lastNameTextField.getText(), phoneTextField.getText(), titleTextField.getText(), new Double(salaryTextField.getText()));
				college.getPeopleBag().add(faculty);
				System.out.println(faculty);
				idTextField.setText(faculty.getId());
				Utilities.alert("Successfully added "+faculty.getFirstName()+" to the person bag!\nRemember to SAVE the college before exiting.", AlertType.INFORMATION);
			} catch(Exception err) {
				Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
			}
			/*			Person person = new Person(nameField.getText(), phoneField.getText());
			college.getPersonBag().insert(person);
			System.out.println(person);
			nameField.clear();
			phoneField.clear();*/
		});

		Button findBtn = new Button("Find faculty");
		findBtn.setPrefWidth(BUTTON_WIDTH);
		findBtn.setOnAction(e -> {
			System.out.println("Find faculty button pressed");
			try {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Find Faculty");
				dialog.setHeaderText("Find faculty member by ID");
				dialog.setContentText("Please enter the ID:");
				Optional<String> idInput = dialog.showAndWait();
				idInput.ifPresent(name -> {
					Person result = college.getPeopleBag().find(idInput.get());
					if(result instanceof Student) {
						Utilities.alert("Person with ID "+idInput.get()+" was found, however, it's a Student.\nTry another ID.", AlertType.ERROR);
						System.out.println("This is an instance of a student.");
					} else if(result instanceof Faculty) {
						//lastNameTextField.getText(), phoneTextField.getText(),
						//titleTextField.getText(), new Double(salaryTextField.getText())
						firstNameTextField.setText(result.getFirstName());
						lastNameTextField.setText(result.getLastName());
						phoneTextField.setText(result.getPhoneNumber());
						titleTextField.setText(((Faculty) result).getTitle());
						salaryTextField.setText(String.valueOf(((Faculty) result).getSalary()));
						idTextField.setText(result.getId());
						System.out.println(result);
					} else {
						Utilities.alert("Person with ID "+idInput.get()+" does not exist in the system.", AlertType.ERROR);
						System.out.println("This person does not exist.");
					}
				});
			} catch(Exception err) {
				Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
			}
		});

		Button removeBtn = new Button("Remove faculty");
		removeBtn.setPrefWidth(BUTTON_WIDTH);
		removeBtn.setOnAction(e -> {
			System.out.println("Remove faculty button pressed");
			try {
				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Delete Faculty");
				dialog.setHeaderText("Delete faculty member by ID");
				dialog.setContentText("Please enter the ID:");
				Optional<String> idInput = dialog.showAndWait();
				idInput.ifPresent(name -> {
					Person result = college.getPeopleBag().find(idInput.get());
					if(result instanceof Student) {
						Utilities.alert("Person with ID "+idInput.get()+" was found, however, it's a Student.\nTry another ID.", AlertType.ERROR);
						System.out.println("This is an instance of a student.");
					}
					if(result instanceof Faculty) {
						System.out.println("this is an instance of a faculty.");
						college.getPeopleBag().delete(idInput.get());
					}
				});
			} catch(Exception err) {
				Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
			}
		});

		Button updateBtn = new Button("Update faculty");
		updateBtn.setPrefWidth(BUTTON_WIDTH);
		updateBtn.setOnAction(e -> {
			System.out.println("Update faculty button pressed");
			try {
				if(!idTextField.getText().isEmpty()) {
					Faculty result = (Faculty)college.getPeopleBag().find(idTextField.getText());
					result.setTitle(titleTextField.getText());
					result.setFirstName(firstNameTextField.getText());
					result.setLastName(lastNameTextField.getText());
					result.setPhoneNumber(phoneTextField.getText());
					result.setSalary(new Double(salaryTextField.getText()));
					Utilities.alert("Successfully updated person!\nRemember to press \"SAVE\" before exiting.", AlertType.CONFIRMATION);
					System.out.println("talkin about this guy? "+result.getFirstName());
				} else {
					Utilities.alert("Whoops! Couldn't update that person.\nTry pressing \"Find\" to load them first.", AlertType.WARNING);
				}
			} catch(Exception err) {
				Utilities.alert("Error! Please validate all data.", AlertType.ERROR);
			}
		});

		HBox btnBox = new HBox(20);

		//btnBox.setAlignment(Pos.CENTER);

		btnBox.getChildren().addAll(addBtn, findBtn, removeBtn, updateBtn);
		GridPane.setHalignment(addBtn, HPos.CENTER);
		GridPane.setHalignment(findBtn, HPos.CENTER);
		GridPane.setHalignment(removeBtn, HPos.CENTER);
		GridPane.setHalignment(updateBtn, HPos.CENTER);

		grid.add(titleLabel, 0, 0);
		grid.add(titleTextField, 0, 1);

		grid.add(firstNameLabel, 1, 0);
		grid.add(firstNameTextField, 1, 1);

		grid.add(lastNameLabel, 2, 0);
		grid.add(lastNameTextField, 2, 1);

		grid.add(phoneLabel, 0, 2);
		grid.add(phoneTextField, 0, 3);

		grid.add(salaryLabel, 1, 2);
		grid.add(salaryTextField, 1, 3);

		grid.add(idLabel, 2, 2);
		grid.add(idTextField, 2, 3);

		//grid.add(phoneField, 2, 0, 2, 1);
		//		gridPane.add(addBtn, 0, 1);
		//		gridPane.add(findBtn, 1, 1);
		//		gridPane.add(removeBtn, 2, 1);
		//		gridPane.add(updateBtn, 3, 1);

		//[child], column, rows, column span, row span
		//grid.add(btnBox, 0, 4);
		grid.add(addBtn, 0, 4);
		grid.add(findBtn, 1, 4);
		grid.add(removeBtn, 2, 4);
		grid.add(updateBtn, 1, 5);
		//grid.add(btnBox, 3, 8, 2, 5); //[child], column, rows, column span, row span
	}

	public GridPane getGridPane() {
		return grid;
	}
}
