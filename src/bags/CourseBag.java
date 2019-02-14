package bags;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.control.Alert.AlertType;
import util.Utilities;
import model.Course;

/**
 * 
 * @author Corey Schneider
 *
 */

@SuppressWarnings("serial")
public class CourseBag implements Serializable {

	Course[] coursesTaken = new Course[50];
	Course[] coursesInProgress = new Course[50];
	Course[] coursesRequiredFuture = new Course[50];

	public CourseBag(Course[] coursesTaken, Course[] coursesInProgress, Course[] coursesRequiredFuture) {
		this.coursesTaken = coursesTaken;
		this.coursesInProgress = coursesInProgress;
		this.coursesRequiredFuture = coursesRequiredFuture;
	}

	public CourseBag() { }


	/**
	 * method to load data from binary file
	 */	
	public void load() {
		String fileName = "data/course_bag.dat";
		FileInputStream inFile;
		ObjectInputStream inStream = null;
		Course tempCourse;
		Course obj;

		try {
			inFile = new FileInputStream(fileName);
			inStream = new ObjectInputStream(inFile);
			obj = (Course)inStream.readObject();

			while (true) {
				tempCourse = (Course)inStream.readObject();
				//String title, String author, String publisher, String ISBN, double price
				//Course course = new Course(tempCourse);
				if(obj.getType().equalsIgnoreCase("ip") || obj.getType().equalsIgnoreCase("future")) {//taken, ip, future
					Course course = new Course(tempCourse.getCourseTitle(), tempCourse.getCrn(), tempCourse.getTextbook(), tempCourse.getCredits(), tempCourse.getType());
					add(course);
				}
				if(obj.getType().equalsIgnoreCase("taken")) {//taken, ip, future
					Course course = new Course(tempCourse.getCourseTitle(), tempCourse.getCrn(), tempCourse.getTextbook(), tempCourse.getCredits(), tempCourse.getType(), tempCourse.getLetterGrade());
					add(course);
				}
				/*				if(obj.getType().equalsIgnoreCase("future")) {//taken, ip, future
					Course course = new Course(tempCourse.getCourseTitle(), tempCourse.getCrn(), tempCourse.getTextbook(), tempCourse.getCredits(), tempCourse.getType());
					add(course);
				}*/
			}

		} catch (FileNotFoundException e) {
			System.out.println("File named "+ fileName +" not found.\n");
		} catch (EOFException e) { // catch EOF
			try {
				if(Utilities.DEBUG)
					System.out.println("[CourseBag]: Loaded "+fileName+" into memory successfully.");
				inStream.close();
			} catch (IOException ex) { }
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	/**
	 * method to save the bag / array(s) onto HDD as binary file
	 * -- not currently implemented anywhere
	 */
	public void save() {
		String fileName = "data/course_bag.dat";
		FileOutputStream outFile;
		ObjectOutputStream outStream;
		Course tempCourse;

		try {
			outFile = new FileOutputStream(fileName);
			outStream = new ObjectOutputStream(outFile);

			boolean foundData = false;
			for (int i = 0; i < coursesTaken.length; i++) {
				if(coursesTaken[i] != null) {
					foundData = true;
					tempCourse = coursesTaken[i];
					outStream.writeObject(tempCourse); // this one line writes an object
					if(Utilities.DEBUG)
						System.out.println("Obj written: "+tempCourse);
				}
				if(i == coursesTaken.length-1 && !foundData)
					System.out.println("[CourseBag]: There are no objects in the array for us to save. Did you load() or importData()?");
			}

			for (int i = 0; i < coursesInProgress.length; i++) {
				if(coursesInProgress[i] != null) {
					foundData = true;
					tempCourse = coursesInProgress[i];
					outStream.writeObject(tempCourse); // this one line writes an object
					if(Utilities.DEBUG)
						System.out.println("Obj written: "+tempCourse);
				}
				if(i == coursesInProgress.length-1 && !foundData)
					System.out.println("[CourseBag]: There are no objects in the array for us to save. Did you load() or importData()?");
			}

			for (int i = 0; i < coursesRequiredFuture.length; i++) {
				if(coursesRequiredFuture[i] != null) {
					foundData = true;
					tempCourse = coursesRequiredFuture[i];
					outStream.writeObject(tempCourse); // this one line writes an object
					if(Utilities.DEBUG)
						System.out.println("Obj written: "+tempCourse);
				}
				if(i == coursesRequiredFuture.length-1 && !foundData)
					System.out.println("[CourseBag]: There are no objects in the array for us to save. Did you load() or importData()?");
			}

			outStream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Individually insert course(s) into the CourseBag array
	 * @param course course(s) to be inserted into the array
	 * @param type "taken", "ip" (in progress), "future"
	 */
	public void add(Course... course) {
		for(int j = 0; j < course.length; j++) {
			if(course[j].getType().equalsIgnoreCase("taken")) {
				for(int i = 0; i < coursesTaken.length; i++) {
					if(coursesTaken[i] == null) {
						coursesTaken[i] = course[j];
						break;
					}
				}
			} else if(course[j].getType().equalsIgnoreCase("ip")) {
				for(int i = 0; i < coursesInProgress.length; i++) {
					if(coursesInProgress[i] == null) {
						coursesInProgress[i] = course[j];
						break;
					}
				}
			} else if(course[j].getType().equalsIgnoreCase("future")) {
				for(int i = 0; i < coursesRequiredFuture.length; i++) {
					if(coursesRequiredFuture[i] == null) {
						coursesRequiredFuture[i] = course[j];
						break;
					}
				}
			}
		}
	}

	public void addTaken(Course... course) {
		for(int j = 0; j < course.length; j++) {
			for(int i = 0; i < coursesTaken.length; i++) {
				if(coursesTaken[i] == null) {
					coursesTaken[i] = course[j];
					break;
				}
			}
		}
	}

	public void addIP(Course... course) {
		for(int j = 0; j < course.length; j++) {
			for(int i = 0; i < coursesInProgress.length; i++) {
				if(coursesInProgress[i] == null) {
					coursesInProgress[i] = course[j];
					break;
				}
			}
		}
	}

	public void addFuture(Course... course) {
		for(int j = 0; j < course.length; j++) {
			for(int i = 0; i < coursesRequiredFuture.length; i++) {
				if(coursesRequiredFuture[i] == null) {
					coursesRequiredFuture[i] = course[j];
					break;
				}
			}
		}
	}

	public void deleteTaken(Course... course) {
		try {
			for(int j = 0; j < course.length; j++) {
				for(int i = 0; i < coursesTaken.length; i++) {
					if(course[j] == coursesTaken[i]) {
						System.out.println("Found "+course[j]+" and "+coursesTaken[i]);
						coursesTaken[i] = null;
						//break;
					}
				}
			}
		} catch(NullPointerException e) {
			Utilities.alert("Couldn't delete that course.", AlertType.ERROR);
			System.out.println("Couldn't delete that course.");
		}
	}

	public void deleteIP(Course... course) {
		try {
			for(int j = 0; j < course.length; j++) {
				for(int i = 0; i < coursesInProgress.length; i++) {
					if(course[j] == coursesInProgress[i]) {
						System.out.println("Found "+course[j]+" and "+coursesInProgress[i]);
						coursesInProgress[i] = null;
						//break;
					}
				}
			}
		} catch(NullPointerException e) {
			Utilities.alert("Couldn't delete that course.", AlertType.ERROR);
			System.out.println("Couldn't delete that course.");
		}
	}

	public void deleteFuture(Course... course) {
		try {
			for(int j = 0; j < course.length; j++) {
				for(int i = 0; i < coursesRequiredFuture.length; i++) {
					if(course[j] == coursesRequiredFuture[i]) {
						System.out.println("Found "+course[j]+" and "+coursesRequiredFuture[i]);
						coursesRequiredFuture[i] = null;
						//break;
					}
				}
			}
		} catch(NullPointerException e) {
			Utilities.alert("Couldn't delete that course.", AlertType.ERROR);
			System.out.println("Couldn't delete that course.");
		}
	}
	
	public void display() {		
		toString();		
	}
	
	/**
	 * Find a course
	 * @param courseName course we're looking for
	 */
	public Course find(String courseName) {
		try {
			for(int i = 0; i < coursesTaken.length || i < coursesInProgress.length || i < coursesRequiredFuture.length; i++) {
				if(coursesTaken[i].getCourseTitle().equalsIgnoreCase(courseName)) {
					return coursesTaken[i];
				}
				if(coursesInProgress[i].getCourseTitle().equalsIgnoreCase(courseName)) {
					return coursesInProgress[i];
				}
				if(coursesRequiredFuture[i].getCourseTitle().equalsIgnoreCase(courseName)) {
					return coursesRequiredFuture[i];
				}
			}
		} catch(NullPointerException e) {
			System.out.println("Cannot find a course named \""+courseName+"\". Are you sure that's the correct name?");
		}
		return null;
	}

	/*	public Person delete(String crn) {
		for(int i = 0; i < personArray.length; i++)
			if(personArray[i].getId().equalsIgnoreCase(id))
				return personArray[i] = null;
		return null;
	}*/

	//debug method
	public String getCoursesTaken() {
		String coursesTkn = "";
		for(int i = 0; i < coursesTaken.length; i++) {
			if(coursesTaken[i] != null) {
				coursesTkn += coursesTaken[i];
			}
		}
		return coursesTkn;
	}

	public Course[] getCourseTakenArray() {
		for(int i = 0; i < coursesTaken.length; i++) {
			if(coursesTaken[i] != null) {
				return coursesTaken;
			}
		}
		return null;
	}

	//debug method
	public String getCourseTakenArrayString() {
		for(int i = 0; i < coursesTaken.length; i++) {
			if(coursesTaken[i] != null) {
				return coursesTaken[i]+"";
			}
		}
		return null;
	}

	public String[] getCourseTakenString() {
		String[] courses = new String[50];
		for(int i = 0; i < coursesTaken.length; i++) {
			if(coursesTaken[i] != null) {
				courses[i] = coursesTaken[i].getCrn()+", "+coursesTaken[i].getCourseTitle();
			}
		}
		return courses;
	}

	//debug method
	public void setCoursesTaken(Course[] coursesTaken) {
		this.coursesTaken = coursesTaken;
	}


	public Course[] getCoursesInProgress() {
		return coursesInProgress;
	}
	public String[] getCoursesInProgressString() {
		String[] courses = new String[50];
		for(int i = 0; i < coursesInProgress.length; i++) {
			if(coursesInProgress[i] != null) {
				courses[i] = coursesInProgress[i].getCrn()+", "+coursesInProgress[i].getCourseTitle();
			}
		}
		return courses;
	}

	//debug method
	public void setCoursesInProgress(Course[] coursesInProgress) {
		this.coursesInProgress = coursesInProgress;
	}

	public Course[] getCoursesRequiredFuture() {
		return coursesRequiredFuture;
	}

	public String[] getCoursesRequiredFutureString() {
		String[] courses = new String[50];
		for(int i = 0; i < coursesRequiredFuture.length; i++) {
			if(coursesRequiredFuture[i] != null) {
				courses[i] = coursesRequiredFuture[i].getCrn()+", "+coursesRequiredFuture[i].getCourseTitle();
			}
		}
		return courses;
	}

	//debug method
	public void setCoursesRequiredFuture(Course[] coursesRequiredFuture) {
		this.coursesRequiredFuture = coursesRequiredFuture;
	}

	/*	@Override
	public String toString() {
		return "CourseBag [coursesTaken=" + Arrays.toString(coursesTaken) + ", coursesInProgress="
				+ Arrays.toString(coursesInProgress) + ", coursesRequiredFuture="
				+ Arrays.toString(coursesRequiredFuture) + "]";
	}*/
	@Override
	public String toString() {
		String coursesTkn = "";
		String coursesIP = "";
		String coursesFuture = "";
		for(int i = 0; i < coursesTaken.length; i++) {
			if(coursesTaken[i] != null) {
				coursesTkn += "\n"+coursesTaken[i];
			}
		}
		for(int i = 0; i < coursesInProgress.length; i++) {
			if(coursesInProgress[i] != null) {
				coursesIP += "\n"+coursesInProgress[i];
			}
		}
		for(int i = 0; i < coursesRequiredFuture.length; i++) {
			if(coursesRequiredFuture[i] != null) {
				coursesFuture += "\n"+coursesRequiredFuture[i];
			}
		}
		return "Taken: "+coursesTkn+"\n"
		+ "In progress: "+coursesIP+"\n"
		+ "Future: "+coursesFuture;
	}

	public String toBasicString() {
		String coursesTkn = "";
		String coursesIP = "";
		String coursesFuture = "";
		for(int i = 0; i < coursesTaken.length; i++) {
			if(coursesTaken[i] != null) {
				coursesTkn += coursesTaken[i].getCrn()+", ";
			}
		}
		for(int i = 0; i < coursesInProgress.length; i++) {
			if(coursesInProgress[i] != null) {
				coursesIP += coursesInProgress[i].getCrn()+", ";
			}
		}
		for(int i = 0; i < coursesRequiredFuture.length; i++) {
			if(coursesRequiredFuture[i] != null) {
				coursesFuture += coursesRequiredFuture[i].getCrn()+", ";
			}
		}
		return "Taken: "+coursesTkn+"\n"
		+ "In progress: "+coursesIP+"\n"
		+ "Future: "+coursesFuture;
	}


}
