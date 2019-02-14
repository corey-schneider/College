package bags;

import java.io.*;
import java.util.Scanner;

import javafx.scene.control.Alert.AlertType;
import util.Utilities;
import model.*;

/**
 * 
 * @author Corey Schneider
 *
 */

public class MasterCourseBag {

	Course[] courseArray;
	private int nElems;


	public MasterCourseBag(Course[] courses) {
		this.courseArray = courses;
	}

	public MasterCourseBag() { }

	public MasterCourseBag(int maxSize) {
		courseArray = new Course[maxSize];
		nElems = 0;
	}

	/**
	 * Lists courses by CRN
	 * @return All courses by CRN
	 */
	public String[] listAllCoursesCRN() {
		String[] courses = new String[nElems];
		for(int i = 0; i < nElems; i++) {
			if(courseArray[i] != null) {
				courses[i] = courseArray[i].getCrn();
			}
		}
		return courses;
	}

	/**
	 * Lists courses by CRN + title side-by-side
	 * @return All courses
	 */
	public String[] listAllCoursesCrnName() {
		String[] courses = new String[nElems];
		for(int i = 0; i < nElems; i++) {
			if(courseArray[i] != null) {
				courses[i] = courseArray[i].getCrn()+", "+courseArray[i].getCourseTitle();
			}
		}
		return courses;
	}

	/**
	 * Lists all courses
	 * @return All courses
	 */
	public Course[] listAllCourses() {
		Course[] courses = new Course[nElems];
		for(int i = 0; i < nElems; i++) {
			if(courseArray[i] != null) {
				courses[i] = courseArray[i];
			}
		}
		return courses;
	}

	/**
	 * Lists all courses by CRN
	 * @return All courses by CRN
	 */
	public String[] listAllCoursesString() {
		String[] courses = new String[nElems];
		for(int i = 0; i < nElems; i++) {
			if(courseArray[i] != null) {
				courses[i] = courseArray[i].getCrn()+" - "+courseArray[i].getCourseTitle();
			}
		}
		return courses;
	}

	/**
	 * Delete course by CRN
	 * @param crn
	 */
	public Course delete(String crn) {		
		int i = -1;
		for(i = 0; i < nElems; i++) {
			if(courseArray[i].getCrn().equalsIgnoreCase(crn)) {
				break;
			}
		}
		if(i == nElems) {
			return null;
		} else {
			Course temp = courseArray[i];
			for(int j = i; j < nElems - 1; j++) {
				courseArray[j] = courseArray[j + 1];
			}
			nElems--;
			return temp;
		}
	}
	
	/**
	 * 
	 * Delete course by Course
	 * @param courseToRemove
	 * @return
	 */
	public Course delete(Course courseToRemove) {
		int i = -1;
		for(i = 0; i < nElems; i++) {
			if(courseArray[i] == courseToRemove) {
				break;
			}
		}
		if(i == nElems) {
			return null;
		} else {
			Course temp = courseArray[i];
			for(int j = i; j < nElems - 1; j++) {
				courseArray[j] = courseArray[j + 1];
			}
			nElems--;
		System.out.println("new array size: "+nElems);
			return temp;
		}
	}
	/*	public Textbook delete(String id) {
		for(int i = 0; i < textbookArray.length; i++)
			if(textbookArray[i].getTitle().equalsIgnoreCase(id))
				return textbookArray[i] = null;
		return null;
	}*/

	public void add(Course... course) {
		for(int i = 0; i < course.length; i++)
			courseArray[nElems++] = course[i];
	}

	public void display() {
		toString();
	}

	public boolean exists(String crn) {
		try {
			for(int i = 0; i < nElems; i++) {
				if(courseArray[i].getCrn().equalsIgnoreCase(crn)) {
					return true;
				}
			}
		} catch(NullPointerException e) {
			System.out.println("Couldn't find that course with CRN: "+crn+".");
			return false;
		}
		return false;
	}
	/**
	 * Find a course, return course
	 * @param CRN - course number we're looking for
	 */
	public Course find(String crn) {
		try {
			for(int i = 0; i < courseArray.length; i++) {
				if(courseArray[i].getCrn().equalsIgnoreCase(crn)) {
					return courseArray[i];
				}
			}
		} catch(NullPointerException e) {
			Utilities.alert("Couldn't find that course with CRN: "+crn+".", AlertType.ERROR);
			System.out.println("Couldn't find that course with CRN: "+crn+".");
		}
		return null;
	}

	public String getCourseName(Course course) {
		try {
			for(int i = 0; i < courseArray.length; i++) {
				if(courseArray[i].getCourseTitle().equalsIgnoreCase(course.getCourseTitle())) {
					return courseArray[i].getCourseTitle();
				}
			}
		} catch(NullPointerException e) {
			System.out.println("Couldn't find that course name "+course.getCourseTitle()+".");
		}
		return null;
	}

	/**
	 * Find a course, return string
	 * @param CRN - course number we're looking for
	 */
	public String find2(String crn) {
		for(int i = 0; i < courseArray.length; i++) {
			if(courseArray[i].getCrn().equalsIgnoreCase(crn)) {
				return courseArray[i].toString();
			}
		}
		return null;
	}

	/**
	 * method to save the bag / array(s) onto HDD as binary file
	 */
	public void save() {
		String fileName = "data/master_course_bag.dat";
		FileOutputStream outFile;
		ObjectOutputStream outStream;
		Course tempCourse;

		try {
			outFile = new FileOutputStream(fileName);
			outStream = new ObjectOutputStream(outFile);

			for (int i = 0; i < courseArray.length; i++) {
				if(courseArray[i] != null) {
					tempCourse = courseArray[i];
					outStream.writeObject(tempCourse); // this one line writes an object
					if(Utilities.DEBUG)
						System.out.println("Obj written: "+tempCourse);
				}
			}

			outStream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * method to load data from a binary file previously saved for that bag to use.
	 */
	public void load() {
		String fileName = "data/master_course_bag.dat";
		FileInputStream inFile;
		ObjectInputStream inStream = null;
		Course tempCourse;

		try {
			inFile = new FileInputStream(fileName);
			inStream = new ObjectInputStream(inFile);

			while (true) {
				tempCourse = (Course)inStream.readObject();
				//String courseTitle, String crn, Textbook textbook, double credits
				Course txtbk = new Course(tempCourse.getCourseTitle(), tempCourse.getCrn(), tempCourse.getTextbook(), tempCourse.getCredits());
				add(txtbk);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File named "+ fileName +" not found.\n");
		} catch (EOFException e) { // catch EOF
			try {
				if(Utilities.DEBUG)
					System.out.println("[MasterCourseBag]: Loaded "+fileName+" into memory successfully.");
				inStream.close();
			} catch (IOException ex) { }
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	//This is done, working perfectly with importData().
	/**
	 * Method to save bag content into a text file.
	 */
	public void exportData() {
		FileWriter writer;
		try {
			writer = new FileWriter("data/output/master_course_bag_output.txt");

			writer.write("## Course Name, CRN, Textbook title, Textbook author, Textbook publisher, Textbook ISBN, Textbook cost, Course credits");
			writer.write(String.format("%n")); //new line
			writer.write("## -------------------------------------------------------------------------------------------------------------------");
			writer.write(String.format("%n")); //new line
			writer.flush();

			// data
			for(int i = 0; i < courseArray.length; i++) {
				if(courseArray[i] != null) {
					//writer.write(courses[i]+""); //writes the array as-is
					writer.write(courseArray[i].getCourseTitle()+", "+courseArray[i].getTextbook().getTitle()+", "
							+ ""+courseArray[i].getTextbook().getAuthor()+", "+courseArray[i].getTextbook().getPublisher()+", "
							+ ""+courseArray[i].getTextbook().getISBN()+", "+courseArray[i].getTextbook().getPrice()+", "
							+ ""+courseArray[i].getCredits());
					writer.write(String.format("%n"));
				}
			}
			writer.write("\n");
			writer.flush();

			writer.close();
			System.out.println("Successfully exported!");
		} catch (IOException e) {
			System.out.println("[MasterCourseBag]: Error exporting data. exportData method");
		} 
	}

	/**
	 * Reads data from txt file, creates objects using data to put into the array.
	 */
	public void importData() {
		String fileName = "data/master_course_bag_import.txt";
		File file = new File(fileName);
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				String data = in.nextLine();
				if(data.startsWith("##")) {
					continue; //skip the line
				}
				String[] tokens = data.split(", ");
				//String title, String author, String publisher, String ISBN, double price
				//Course course = new Course(tokens[0], tokens[1], new Textbook(tokens[2], tokens[3], tokens[4], tokens[5], new Double(tokens[6])), new Double(tokens[7]));

				//course name, crn, textbook isbn, credits
				Course course = new Course(tokens[0], tokens[1], Utilities.college.getTextbookBag().find(tokens[2]), new Double(tokens[3]));
				add(course);
			}
			in.close();
			System.out.println("[MasterCourseBag]: Imported master course bag from "+fileName+" successfully.");
		} catch (FileNotFoundException e) {
			System.out.println("File "+fileName+" not found.");
		} catch (Exception e) {
			System.out.println("Error loading data from "+fileName+". Carefully check the data for typos.");
		}
	}

	@Override
	public String toString() {
		String data = "";
		for(int i = 0; i < courseArray.length; i++) {
			if(courseArray[i] != null) {
				data += courseArray[i]+"\n";
			}
		}
		return data;
	}


}
