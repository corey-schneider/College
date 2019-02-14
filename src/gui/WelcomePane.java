package gui;

import util.Utilities;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

//NOTE this class is garbage not being used
//NOTE this class is garbage not being used
//NOTE this class is garbage not being used
//NOTE this class is garbage not being used
//NOTE this class is garbage not being used
//NOTE this class is garbage not being used
//NOTE this class is garbage not being used
//NOTE this class is garbage not being used
//NOTE this class is garbage not being used
//NOTE this class is garbage not being used
public class WelcomePane {

	private GridPane grid = new GridPane();

	public WelcomePane() {
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
		pic.setImage(new Image("File:data/sccc_logo.png"));
		grid.getChildren().add(pic);
		GridPane.setConstraints(pic, 1, 0);

		Label labelWelcome = new Label("Welcome to the Suffolk County Community College terminal.");
		GridPane.setMargin(labelWelcome, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(labelWelcome, HPos.CENTER);
		GridPane.setConstraints(labelWelcome, 1, 1);

		Label labelWelcome2 = new Label("Please select an action:");
		//GridPane.setMargin(labelWelcome2, new Insets(10, 10, -30, 10));//for use with constaints(xxxx, 1, 1)
		GridPane.setHalignment(labelWelcome2, HPos.CENTER);
		GridPane.setConstraints(labelWelcome2, 1, 2);


		Button buttonStudents = new Button("Student Portal");
		buttonStudents.setTooltip(new Tooltip("Modify or view students in the school."));
		GridPane.setConstraints(buttonStudents, 0, 3);
		GridPane.setMargin(buttonStudents, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(buttonStudents, HPos.CENTER);
		buttonStudents.setOnAction(e -> {
			//TODO fix replaceScene(createGridPaneConst());
			System.out.println("Student button clicked!");
		});

		Button buttonFaculty = new Button("Faculty Portal");
		buttonFaculty.setTooltip(new Tooltip("Modify or view faculty members in the school."));
		GridPane.setConstraints(buttonFaculty, 1, 3);
		GridPane.setMargin(buttonFaculty, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(buttonFaculty, HPos.CENTER);
		buttonFaculty.setOnAction(e -> {
			//TODO fix 
			FacultyPane facultyPane = new FacultyPane(Utilities.college);
			MainFrame efd = new MainFrame();
			//efd.replaceScene(facultyPane.getGridPane());
			System.out.println("Faculty button clicked!");
		});

		Button buttonCourses = new Button("Course Portal");
		buttonCourses.setTooltip(new Tooltip("Modify or view all courses offered by the school."));
		GridPane.setConstraints(buttonCourses, 2, 3);
		GridPane.setMargin(buttonCourses, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(buttonCourses, HPos.CENTER);
		buttonCourses.setOnAction(e -> {
			System.out.println("Courses button clicked!");
		});

		Button buttonTextbooks = new Button("Textbook Portal");
		buttonTextbooks.setTooltip(new Tooltip("Modify or view all textbooks."));
		GridPane.setConstraints(buttonTextbooks, 0, 2);
		GridPane.setMargin(buttonTextbooks, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(buttonTextbooks, HPos.CENTER);
		buttonTextbooks.setOnAction(e -> {
			System.out.println("Extra button 1 clicked!");
		});

		Button extraButton2 = new Button("Extra button 2");
		extraButton2.setTooltip(new Tooltip("Modify or view all courses offered by the school."));
		GridPane.setConstraints(extraButton2, 2, 2);
		GridPane.setMargin(extraButton2, new Insets(10, 10, 10, 10));
		GridPane.setHalignment(extraButton2, HPos.CENTER);
		extraButton2.setOnAction(e -> {
			System.out.println("Extra button 2 clicked!");
		});

		//GridPane.setConstraints(labelWelcome, 1, 0);
		grid.getChildren().addAll(labelWelcome, labelWelcome2, buttonStudents, buttonFaculty, buttonCourses, buttonTextbooks, extraButton2/*, extraButton3*/);
	}

	public GridPane getGridPane() {
		return grid;
	}
}
