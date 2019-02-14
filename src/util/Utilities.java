package util;

import java.text.DecimalFormat;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.College;
import model.Course;

/**
 * 
 * @author Corey Schneider
 *
 */

public class Utilities {

	public static final boolean DEBUG = true;

	//calls the college constructor only once so we aren't re-loading the data every time we need to use the constructor.
	public static College college = new College();

	/**
	 * 
	 * @param message to be displayed to the user
	 * @param alertType AlertType.WARNING, ERROR, INFORMATION, etc
	 */
	public static void alert(String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		if(alertType.equals(AlertType.WARNING))
			alert.setTitle("Warning");
		else if(alertType.equals(AlertType.ERROR))
			alert.setTitle("Error");
		else if(alertType.equals(AlertType.INFORMATION))
			alert.setTitle("Information");
		else if(alertType.equals(AlertType.CONFIRMATION))
			alert.setTitle("Confirmation");
		else if(alertType.equals(AlertType.NONE))
			alert.setTitle("Info");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * 
	 * @param course array of courses to calculate the GPA
	 * @return student's GPA
	 */
	public static double calculateGPA(Course[] course) {
		DecimalFormat df = new DecimalFormat("#.##");
		double credits = 0.0;
		double points = 0.0;
		try {
			for(int i = 0; i < course.length; i++) {
				if(course[i] != null) {
					//System.out.println("[UTILITIES]: Course: "+course[i].getCourseTitle()+"  [-]  "+course[i].getCredits()+" credits  [-]  Earned grade: "+course[i].getLetterGrade()+" ("+letterGradeToPoints(course[i].getLetterGrade())+")");
					credits += course[i].getCredits();
					points += course[i].getCredits() * letterGradeToPoints(course[i].getLetterGrade());
				}
			}
			//System.out.println("[UTILITIES]: Total credits: "+credits+"  -  Points: "+points);
			return new Double(df.format(points/credits));
		} catch(NullPointerException e) {
			System.out.println("[Utilities]: calculateGPA() : Null pointer exception.");
			return 0.0;
		}
	}

	public static double letterGradeToPoints(String letterGrade) {
		double grade = 0.0;
		switch(letterGrade.toUpperCase()) {
		case "A+":
		case "A":
			grade = 4.0;
			break;
		case "A-":
			grade = 3.7;
			break;
		case "B+":
			grade = 3.3;
			break;
		case "B":
			grade = 3.0;
			break;
		case "B-":
			grade = 2.7;
			break;
		case "C+":
			grade = 2.3;
			break;
		case "C":
			grade = 2.0;
			break;
		case "C-":
			grade = 1.7;
			break;
		case "D+":
			grade = 1.3;
			break;
		case "D":
			grade = 1.0;
			break;
		case "F":
			grade = 0.0;
			break;
		default:
			System.out.println("A wrong grade was input. Problematic grade is: \""+letterGrade+"\"");
			break;

		}
		//System.out.println("Grade: "+grade);
		return grade;
	}
}
