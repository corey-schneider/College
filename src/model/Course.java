package model;

import java.io.Serializable;

import util.Utilities;

@SuppressWarnings("serial")
public class Course implements Serializable {

	private String courseTitle;
	private String crn;
	private Textbook textbook;
	private double credits;
	private String type;
	private String letterGrade;
	private String isbn;

	public Course() { }

	public Course(String courseTitle, String crn, Textbook textbook, double credits) {
		this.courseTitle = courseTitle;
		this.crn = crn;
		this.textbook = textbook;
		this.credits = credits;
		this.isbn = textbook.getISBN();
	}

	public Course(String courseTitle, String crn, Textbook textbook, double credits, String type) {
		this.courseTitle = courseTitle;
		this.crn = crn;
		this.textbook = textbook;
		this.credits = credits;
		this.type = type;
		this.isbn = textbook.getISBN();
	}

	public Course(String courseTitle, String crn, Textbook textbook, double credits, String type, String letterGrade) {
		this.courseTitle = courseTitle;
		this.crn = crn;
		this.textbook = textbook;
		this.credits = credits;
		this.type = type;
		this.letterGrade = letterGrade;
		this.isbn = textbook.getISBN();
	}

	public Course(Course course) {
		this.courseTitle = course.courseTitle;
		this.crn = course.crn;
		this.textbook = course.textbook;
		this.credits = course.credits;
		this.type = course.type;
	}

	public Course(String courseTitle, String crn, String textbookISBN, double credits) {
		this.courseTitle = courseTitle;
		this.crn = crn;
		this.textbook = Utilities.college.getTextbookBag().find(textbookISBN);
		this.credits = credits;
	}

	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}
	public String getLetterGrade() {
		return letterGrade;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getIsbn() {
		return isbn;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCrn() {
		return crn;
	}
	public void setCrn(String crn) {
		this.crn = crn;
	}

	public Textbook getTextbook() {
		return textbook;
	}
	public void setTextbook(Textbook textbook) {
		this.textbook = textbook;
	}

	public double getCredits() {
		return credits;
	}
	public void setCredits(double credits) {
		this.credits = credits;
	}

	@Override
	public String toString() {
		if(letterGrade != null) {
			return "Course: "+courseTitle + " - CRN: " + crn + " - Credits: "+credits+" - Grade received: "+letterGrade+"\n"
					+ "Textbook: " + textbook + "\n";
		}
		return "Course: "+courseTitle + " - CRN: " + crn + " - Credits: "+credits+"\n"
		+ "Textbook: " + textbook + "\n";

	}


}
