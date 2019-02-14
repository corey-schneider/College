package gui;


import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.College;
import util.Utilities;

/**
 * @author Corey Schneider
 * An example of a menu bar. The example includes use of the system bar, if the
 * current platform supports a system bar.
 */
public class MainFrame extends Application {


	private static final int APP_SIZE_X = 700;
	private static final int APP_SIZE_Y = 420;
	GridPane mainGridPane;

	College college = Utilities.college;

	VBox vbox = new VBox(20);

	public Parent createContent() {

		mainGridPane = welcomePane();
		vbox.setPrefSize(APP_SIZE_X, APP_SIZE_Y);
		final MenuBar menuBar = new MenuBar();

		/*		MenuItem removeGridConst = new MenuItem("Remove gridConst");
		removeGridConst.setOnAction((ActionEvent t) -> {
			vbox.getChildren().removeAll(mainGridPane);
		});
		MenuItem addGridConst = new MenuItem("Add gridConst2");
		addGridConst.setOnAction((ActionEvent t) -> {
			System.out.println("removed");
			//vbox.getChildren().addAll(grid2);
		});

		Menu subMenuGridPanes = new Menu("Grid panes");
		subMenuGridPanes.getItems().addAll(removeGridConst, addGridConst);*/

		MenuItem saveEntireBag = new MenuItem("Save entire bag");
		saveEntireBag.setOnAction((ActionEvent t) -> {
			college.save();
		});/*
		Menu subMenuSave = new Menu("Save");
		subMenuSave.getItems().addAll(saveEntireBag);*/

		MenuItem subMenuLoad = new MenuItem("Load entire bag");
		subMenuLoad.setOnAction((ActionEvent t) -> {
			college.load();
		});

		MenuItem importMCB = new MenuItem("Master course bag");
		importMCB.setOnAction((ActionEvent t) -> {
			college.getMasterCourseBag().importData();
		});
		MenuItem importPeopleBag = new MenuItem("People bag");
		importPeopleBag.setOnAction((ActionEvent t) -> {
			college.getPeopleBag().importData();
		});
		MenuItem importTextbookBag = new MenuItem("Textbook bag");
		importTextbookBag.setOnAction((ActionEvent t) -> {
			college.getTextbookBag().importData();
		});
		Menu subMenuImport = new Menu("Import from txt");
		subMenuImport.getItems().addAll(importMCB, importPeopleBag, importTextbookBag);

		MenuItem exportAll = new MenuItem("Export all data");
		exportAll.setOnAction(e -> {
			college.exportAll();
			Utilities.alert("Exported all files to text.\nCheck data\\output folder.", AlertType.CONFIRMATION);
		});

		MenuItem subMenuExit = new MenuItem("Save & exit");
		subMenuExit.setOnAction(e -> {
			college.save();
			Platform.exit();
		});

		MenuItem subMenuExitWithoutSave = new MenuItem("Exit without saving");
		subMenuExitWithoutSave.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Save work");
			//alert.setHeaderText("Look, a Confirmation Dialog");
			alert.setContentText("Are you sure you want to exit without saving your work?");

			ButtonType yes = new ButtonType("Save work");
			ButtonType no = new ButtonType("Delete work");
			ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(yes, no, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == yes) {
				System.out.println("user pressed save");
				college.save();
				Platform.exit();
			} else if (result.get() == no) {
				System.out.println("user pressed delete work");
				Platform.exit();
			} else {
				System.out.println("user pressed cancel");
			}

			Platform.exit();
		});

		// Options menu
		Menu fileMenu = new Menu("File");
		fileMenu.getItems().addAll(saveEntireBag, subMenuLoad, subMenuImport,  exportAll, new SeparatorMenuItem(), subMenuExit, subMenuExitWithoutSave/*, subMenuExport, subMenuExit*/);
		//Menu optionMenu = new Menu("Options");
		//optionMenu.getItems().addAll(subMenuGridPanes);
		//Menu homeMenu = new Menu("Home");


		Menu navigateMenu = new Menu("Navigate");

		MenuItem subMenuHome = new MenuItem("Go home");
		subMenuHome.setOnAction((ActionEvent t) -> {
			System.out.println("Clicked on home button");
			replaceScene(welcomePane());
		});

		MenuItem subMenuStudent = new MenuItem("List all students");
		subMenuStudent.setOnAction((ActionEvent t) -> {
			AllStudentPane asp = new AllStudentPane(college);
			replaceScene(asp.getGridPane());
		});
		MenuItem subMenuFaculty = new MenuItem("List all faculty");
		subMenuFaculty.setOnAction((ActionEvent t) -> {
			AllFacultyPane afp = new AllFacultyPane(college);
			replaceScene(afp.getGridPane());
		});
		MenuItem subMenuTxtbk = new MenuItem("List all textbooks");
		subMenuTxtbk.setOnAction((ActionEvent t) -> {
			TextbookPane tbp = new TextbookPane(college);
			replaceScene(tbp.getGridPane());
		});
		MenuItem subMenuCourse = new MenuItem("List all courses");
		subMenuCourse.setOnAction((ActionEvent t) -> {
			CoursePane coursePane = new CoursePane(college);
			replaceScene(coursePane.getGridPane());
		});
		navigateMenu.getItems().addAll(subMenuHome, new SeparatorMenuItem(), subMenuStudent, subMenuFaculty, subMenuTxtbk, subMenuCourse);


		Menu countMenu = new Menu("Count");
		MenuItem subMenuStudentCt = new MenuItem("Student count");
		subMenuStudentCt.setOnAction((ActionEvent t) -> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Student count");
			alert.setHeaderText("Student count");
			alert.setContentText("Students: "+college.getPeopleBag().listAllStudents().length+"\n"+college.getPeopleBag().listAllStudentsString());
			ButtonType okButton = new ButtonType("OK", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(okButton);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == okButton) {
			}
		});

		MenuItem subMenuFacultyCt = new MenuItem("Faculty count");
		subMenuFacultyCt.setOnAction((ActionEvent t) -> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Faculty count");
			alert.setHeaderText("Faculty count");
			alert.setContentText("Faculty: "+college.getPeopleBag().listAllFaculty().length+"\n"+college.getPeopleBag().listAllFacultyString());
			ButtonType okButton = new ButtonType("OK", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(okButton);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == okButton) {
			}
		});

		MenuItem subMenuPersonCt = new MenuItem("Person count");
		subMenuPersonCt.setOnAction((ActionEvent t) -> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Person count");
			alert.setHeaderText("Person count");
			alert.setContentText("People: "+college.getPeopleBag().nElems+"\n"+college.getPeopleBag().listAllPeopleString());
			ButtonType okButton = new ButtonType("OK", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(okButton);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == okButton) {
			}
		});

		MenuItem subMenuAllCt = new MenuItem("All count");
		subMenuAllCt.setOnAction((ActionEvent t) -> {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("All count");
			alert.setHeaderText("All count");
			alert.setContentText("All people: "+college.getPeopleBag().nElems+"\n"+college.getPeopleBag().listAllPeopleString()+"\n\n"
					+ "Students: "+college.getPeopleBag().listAllStudents().length+"\n"+college.getPeopleBag().listAllStudentsString()+"\n\n"
					+ "Faculty: "+college.getPeopleBag().listAllFaculty().length+"\n"+college.getPeopleBag().listAllFacultyString());
			ButtonType okButton = new ButtonType("OK", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(okButton);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == okButton) {
			}
		});

		countMenu.getItems().addAll(subMenuStudentCt, subMenuFacultyCt, subMenuPersonCt, subMenuAllCt);

		menuBar.getMenus().addAll(fileMenu, /*homeMenu,*/ navigateMenu, countMenu);


		vbox.getChildren().addAll(menuBar);
		vbox.getChildren().addAll(mainGridPane);
		return vbox;
	}

	private GridPane welcomePane() {
		GridPane grid = new GridPane();
		grid.setGridLinesVisible(false);

		grid.setPadding(new Insets(8, 8, 8, 8));
		RowConstraints rowinfo3 = new RowConstraints();
		rowinfo3.setPercentHeight(50);


		ColumnConstraints colInfo2 = new ColumnConstraints();
		colInfo2.setPercentWidth(25);

		ColumnConstraints colInfo3 = new ColumnConstraints();
		colInfo3.setPercentWidth(50);

		grid.getRowConstraints().add(rowinfo3);//2*50 percent
		grid.getRowConstraints().add(rowinfo3);

		grid.getColumnConstraints().add(colInfo2); //25 percent
		grid.getColumnConstraints().add(colInfo3); //50 percent
		grid.getColumnConstraints().add(colInfo2); //25 percent

		/*String IMAGE = "data/sccc_logo.png";
        Image ICON = new Image(getClass().getResourceAsStream(IMAGE));
        ImageView imageView = new ImageView(ICON);*/

		ImageView pic = new ImageView();
		pic.setImage(new Image("File:data/uncc_logo2.png"));
		grid.getChildren().add(pic);
		GridPane.setConstraints(pic, 1, 0);

		Label labelWelcome = new Label("Welcome to the University of North Carolina at Charlotte terminal.");
		GridPane.setMargin(labelWelcome, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(labelWelcome, HPos.CENTER);
		GridPane.setConstraints(labelWelcome, 1, 1);

		Label labelWelcome2 = new Label("Please select an action:");
		//GridPane.setMargin(labelWelcome2, new Insets(10, 10, -30, 10));//for use with constaints(xxxx, 1, 1)
		GridPane.setHalignment(labelWelcome2, HPos.CENTER);
		GridPane.setConstraints(labelWelcome2, 1, 2);

		Label myName = new Label("Corey Schneider 2019");
		myName.setTextFill(Color.web("#a0a0a0"));
		GridPane.setHalignment(myName, HPos.RIGHT);
		GridPane.setConstraints(myName, 2, 4);


		Button buttonStudents = new Button("Student Portal");
		buttonStudents.setTooltip(new Tooltip("Modify or view students in the school."));
		GridPane.setConstraints(buttonStudents, 0, 3);
		GridPane.setMargin(buttonStudents, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(buttonStudents, HPos.CENTER);
		buttonStudents.setOnAction((ActionEvent t) -> {
			StudentPane studentPane = new StudentPane(college);
			replaceScene(studentPane.getGridPane());
			System.out.println("Student button clicked!");
		});

		Button buttonFaculty = new Button("Faculty Portal");
		buttonFaculty.setTooltip(new Tooltip("Modify or view faculty members in the school."));
		GridPane.setConstraints(buttonFaculty, 1, 3);
		GridPane.setMargin(buttonFaculty, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(buttonFaculty, HPos.CENTER);
		buttonFaculty.setOnAction((ActionEvent t) -> {
			FacultyPane facultyPane = new FacultyPane(college);
			replaceScene(facultyPane.getGridPane());
			System.out.println("Faculty button clicked!");
		});

		Button buttonCourses = new Button("Course Portal");
		buttonCourses.setTooltip(new Tooltip("Modify or view all courses offered by the school."));
		GridPane.setConstraints(buttonCourses, 2, 2);
		GridPane.setMargin(buttonCourses, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(buttonCourses, HPos.CENTER);
		buttonCourses.setOnAction((ActionEvent t) -> {
			CoursePane cp = new CoursePane(college);
			replaceScene(cp.getGridPane());
			System.out.println("Courses button clicked!");
		});

		Button buttonTextbooks = new Button("Textbook Portal");
		buttonTextbooks.setTooltip(new Tooltip("Modify or view all textbooks."));
		GridPane.setConstraints(buttonTextbooks, 0, 2);
		GridPane.setMargin(buttonTextbooks, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(buttonTextbooks, HPos.CENTER);
		buttonTextbooks.setOnAction((ActionEvent t) -> {
			System.out.println("Textbook button clicked!");
			TextbookPane tbp = new TextbookPane(college);
			replaceScene(tbp.getGridPane());
		});

		Button buttonExit = new Button("Exit Terminal");
		buttonExit.setTooltip(new Tooltip("Exit with a choice of saving work."));
		GridPane.setConstraints(buttonExit, 2, 3);
		GridPane.setMargin(buttonExit, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(buttonExit, HPos.CENTER);
		buttonExit.setOnAction((ActionEvent t) -> {
			System.out.println("exit button clicked!");


			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Save work");
			//alert.setHeaderText("Look, a Confirmation Dialog");
			alert.setContentText("Would you like to save your work before exiting?");

			ButtonType yes = new ButtonType("Save work");
			ButtonType no = new ButtonType("Delete work");
			ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(yes, no, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == yes) {
				System.out.println("user pressed save");
				college.save();
				Platform.exit();
			} else if (result.get() == no) {
				System.out.println("user pressed delete work");
				Platform.exit();
			} else {
				System.out.println("user pressed cancel");
			}

		});

		//GridPane.setConstraints(labelWelcome, 1, 0);
		grid.getChildren().addAll(labelWelcome, labelWelcome2, myName, buttonStudents, buttonFaculty, buttonCourses, buttonTextbooks, buttonExit/*, extraButton3*/);

		return grid;
	}

	//TODO was here --faculty

	//TODO was here ---------

	private Parent replaceScene(GridPane newGrid) {
		//vbox.getChildren().clear();
		vbox.getChildren().removeAll(mainGridPane);
		mainGridPane = newGrid;
		vbox.getChildren().addAll(newGrid);
		return mainGridPane;

	}

	/*	BorderPane root = new BorderPane();
	Scene scene = new Scene(root, APP_SIZE_X, APP_SIZE_Y);
	@Override
	public void start(Stage primaryStage) throws Exception {
		root.setTop(createContent());
		root.setCenter(welcomePane());
		primaryStage.getIcons().add(new Image("File:data/sccc_icon.png"));
		primaryStage.setTitle("SCCC Terminal");
		primaryStage.setResizable(true);
		primaryStage.setScene(scene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				// we don't want to save yet -->> college.save();
				Platform.exit();
			}
		});
	}*/

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.getIcons().add(new Image("File:data/uncc_icon.png"));
		primaryStage.setTitle("UNCC Terminal");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("UNCC Terminal exit");
				alert.setHeaderText("Exit without saving your work");
				alert.setContentText("Be sure to save your work from the \"File\" menu before exiting.");
				ButtonType saveButton = new ButtonType("Save and exit");
				ButtonType exitButton = new ButtonType("Exit without saving");
				ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

				alert.getButtonTypes().setAll(saveButton, exitButton, cancelButton);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == saveButton) {
					System.out.println("Saving...");
					college.save();
					Platform.exit();
				} else if (result.get() == exitButton) {
					System.out.println("Exiting without saving...");
					Platform.exit();
				} else {
					System.out.println("User cancelled exit dialog");
					we.consume();
				}
			}
		});
	}


	/**
	 * Java main for when running without JavaFX launcher
	 */
	public static void main(String[] args) {
		launch(args);
	}
}