package bags;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.Alert.AlertType;
import model.*;
import util.Utilities;


public class PeopleBag {

	Person[] personArray;
	public static int nElems;

	public PeopleBag(int maxSize) {
		personArray = new Person[maxSize];
		nElems = 0;
	}

	public PeopleBag() { }

	/**
	 * Insert Person datatype to the personArray
	 * @param person Person(s) to be inserted into the array
	 */
	public void add(Person... person) {
		for(int j = 0; j < person.length; j++)
			personArray[nElems++] = person[j];
	}

	public void display() {
		toString();
	}

	/**
	 * Find a person by their ID
	 * @param id - Person's ID
	 * @return the person who's being searched for
	 */
	public Person find(String id) {
		try {
			for(int i = 0; i < nElems; i++) {
				if(personArray[i].getId().equalsIgnoreCase(id)) {
					return personArray[i];
				}
			}
		} catch(NullPointerException e) {
			Utilities.alert("Couldn't find a person with ID #"+id+"... Are you sure that's the right ID?", AlertType.ERROR);
			System.out.println("Couldn't find a person with ID #"+id+"... Are you sure that's the right ID?");
		}
		return null;
	}

	/**
	 * Find a person by their FIRST name
	 * @param name - Person's first name
	 * @return the person who's being searched for
	 */
	public Person findByFirstName(String name) {
		try {
			for(int i = 0; i < nElems; i++) {
				if(personArray[i].getFirstName().equalsIgnoreCase(name)) {
					return personArray[i];
				}
			}
		} catch(NullPointerException e) {
			Utilities.alert("Couldn't find a person with first name "+name+"... Are you sure that's the right name?", AlertType.ERROR);
			System.out.println("Couldn't find a person with first name "+name+"... Are you sure that's the right name?");
		}
		return null;
	}

	/**
	 * Delete a person by their ID
	 * @param id - Person's ID
	 * @return the person who's being deleted
	 */
	public Person delete(String id) {
		try {/*
			for(int i = 0; i < nElems; i++)
				if(personArray[i].getId().equalsIgnoreCase(id))
					return personArray[i] = null;*/

			int i = -1;
			for(i = 0; i < nElems; i++) {
				if(personArray[i].getId().equalsIgnoreCase(id)) {
					break;
				}
			}
			if(i == nElems) {
				return null;
			} else {
				Person temp = personArray[i];
				for(int j = i; j < nElems-1; j++) {
					personArray[j] = personArray[j + 1];
				}
				nElems--;
				System.out.println("number of persons: "+nElems);
				Utilities.alert("Successfully deleted person with ID "+id+".", AlertType.CONFIRMATION);
				return temp;
			}
		} catch(NullPointerException e) {
			Utilities.alert("Couldn't delete a person with ID #"+id+"... Are you sure that's the right ID?", AlertType.ERROR);
			System.out.println("Couldn't delete a person with ID #"+id+"... Are you sure that's the right ID?");
		}
		return null;
	}

	public void save() {
		String fileName;
		FileOutputStream outFile;
		ObjectOutputStream outStream;
		Person tempPerson;

		fileName = "data/people_bag.dat";

		try {
			outFile = new FileOutputStream(fileName);
			outStream = new ObjectOutputStream(outFile);

			for (int i = 0; i < personArray.length; i++) {
				if(personArray[i] != null) {
					tempPerson = personArray[i];
					outStream.writeObject(tempPerson); // this one line writes an object
					if(Utilities.DEBUG)
						System.out.println("Obj written: "+tempPerson.toBasicString());
				}
			}

			outStream.close();
			if(Utilities.DEBUG) {
				System.out.println("[PeopleBag]: Saved bag to "+fileName+" successfully.");
				Utilities.alert("Saved bag to "+fileName+" successfully.", AlertType.CONFIRMATION);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void load() {
		String fileName = "data/people_bag.dat";
		FileInputStream inFile;
		ObjectInputStream inStream = null;
		Student tempStudent;
		Faculty tempFaculty;


		try {
			inFile = new FileInputStream(fileName);
			inStream = new ObjectInputStream(inFile);
			Object obj;

			while (true) {
				obj = inStream.readObject();

				if(obj instanceof Student) {
					tempStudent = (Student) obj;
					Student student = new Student(tempStudent.getFirstName(), tempStudent.getLastName(), tempStudent.getPhoneNumber(), tempStudent.getMajor(), tempStudent.getCourseBag());
					add(student); //add the person to the bag
					//if(Utilities.DEBUG)
					//	System.out.println("Student: "+student.toBasicString() + "\n");
				}

				if(obj instanceof Faculty) {
					tempFaculty = (Faculty) obj;
					//String firstName, String lastName, String phoneNumber, String title, double salary
					Faculty faculty = new Faculty(tempFaculty.getFirstName(), tempFaculty.getLastName(), tempFaculty.getPhoneNumber(), tempFaculty.getTitle(), tempFaculty.getSalary());
					add(faculty); //add the person to the bag
					//if(Utilities.DEBUG)
					//	System.out.println("Faculty: "+faculty.toString() + "\n");
				}

			}


		} catch (FileNotFoundException e) {
			System.out.println("[PeopleBag]: File named "+ fileName +" not found.\n");
			Utilities.alert("Couldn't find file "+fileName+" to load!", AlertType.ERROR);
		} catch (EOFException e) { // catch EOF
			try {
				if(Utilities.DEBUG)
					System.out.println("[PeopleBag]: Loaded "+fileName+" into memory successfully.");
				inStream.close();
			} catch (IOException ex) { }
		} catch (IOException e) {
			System.out.println("[PeopleBag]: IO Exception.");
			Utilities.alert("Couldn't load. IO error, PeopleBag class. Reloading...", AlertType.ERROR);

		} catch (ClassNotFoundException e) {
			System.out.println("[PeopleBag]: Class not found exception.");
			Utilities.alert("[PeopleBag]: Class not found exception.", AlertType.ERROR);
		}
	}

	/**
	 * Saves content of bag into a text file.
	 */
	public void exportData() {
		FileWriter writer;
		try {
			writer = new FileWriter("data/output/people_bag_output.txt");

			writer.write("## Faculty: title, first name, last name, phone number, ID, salary");
			writer.write(String.format("%n")); //new line
			writer.write("## Student: first name, last name, phone number, major, ID, GPA, courses [taken/in progress/future]");
			writer.write(String.format("%n"));
			writer.flush();

			// data
			for(int i = 0; i < personArray.length; i++) {
				if(personArray[i] != null) {
					writer.write(personArray[i].toBasicString());
					writer.write(String.format("%n"));
				}
			}

			writer.flush();

			writer.close();
			System.out.println("Successfully exported!");
		} catch (IOException e) {
			System.out.println("Error exporting data. PeopleBag class; exportData method");
			Utilities.alert("Couldn't export data from PeopleBag!", AlertType.ERROR);
			//Utilities.alert("Error exporting PeopleBag data to a text file.", AlertType.ERROR);
		} 
	}

	/**
	 * Reads data from text file, creates objects using data to put into the array.
	 */

	public void importData() {
		String fileName = "data/people_bag_import.txt";
		File file = new File(fileName);
		try {
			Scanner in = new Scanner(file);
			while (in.hasNextLine()) {
				String data = in.nextLine();
				if(data.startsWith("##")) {
					continue; //skip the line
				}
				String[] tokens = data.split(", |: "); //split by ", " or ": "
				if(data.contains("student")) {
					//Student: String firstName, String lastName, String phoneNumber, String major, CourseBag courseBag
					//CourseBag: Course[] coursesTaken, Course[] coursesInProgress, Course[] coursesRequiredFuture
					//Course: String courseTitle, String crn, Textbook textbook, double credits, String type
					//College c = new College();
					College c = Utilities.college;
					CourseBag cb = new CourseBag();
					Course course;
					try {
						for(int i = 4; i < 30; i++) {
							if(tokens[i].contains("T")) {
								System.out.println(tokens[1]+" TAKEN: tokens["+i+"] has crn "+tokens[i+1]+" with a grade of "+tokens[i+2]);
								System.out.println("CRN: "+tokens[i+1]+"  --  Class: "+c.getMasterCourseBag().find(tokens[i+1]));
								course = c.getMasterCourseBag().find(tokens[i+1]);
								course.setType("taken");
								course.setLetterGrade(tokens[i+2]);
								cb.add(course);
							}
							if(tokens[i].contains("IP")) {
								System.out.println(tokens[1]+" IN PROG: tokens["+i+"] has crn "+tokens[i+1]);
								System.out.println("CRN: "+tokens[i+1]+"  --  Class: "+c.getMasterCourseBag().find(tokens[i+1]));
								course = c.getMasterCourseBag().find(tokens[i+1]);
								course.setType("ip");
								cb.add(course);
							}
							if(tokens[i].contains("F")) {
								System.out.println(tokens[1]+" FUTURE: tokens["+i+"] has crn "+tokens[i+1]);
								System.out.println("CRN: "+tokens[i+1]+"  --  Class: "+c.getMasterCourseBag().find(tokens[i+1]));
								course = c.getMasterCourseBag().find(tokens[i+1]);
								course.setType("future");
								cb.add(course);
							}
						}
					} catch(ArrayIndexOutOfBoundsException e) {
						if(Utilities.DEBUG)
							System.out.println("end of person -------------------------------");
					}
					Student student = new Student(tokens[1], tokens[2], tokens[3], tokens[4], cb);
					add(student);
				}
				if(data.contains("faculty")) {
					//Faculty: String firstName, String lastName, String phoneNumber, String title, double salary
					//IMPORTANT: tokens[0] is for "professor" or "student" keyword.
					Faculty faculty = new Faculty(tokens[1], tokens[2], tokens[3], tokens[4], new Double(tokens[5]));
					add(faculty);
				}
			}
			in.close();
			System.out.println("[PeopleBag]: Imported people from "+fileName+" successfully.");
		} catch (FileNotFoundException e) {
			System.out.println("[PeopleBag]: Error importing data ; importData method");
			Utilities.alert("Couldn't import data from PeopleBag!", AlertType.ERROR);
		}
	}



	@Override
	public String toString() {
		String people = "";
		System.out.print("PeopleBag holds:");
		for(int i = 0; i < nElems; i++) {
			if(personArray[i] != null) {
				people += "\n"+personArray[i]+"\n";
			}
		}
		return people;
	}

	public String toBasicString() {
		String people = "";
		for(int i = 0; i < nElems; i++) {
			if(personArray[i] != null) {
				people += "\n"+personArray[i].toBasicString()+"\n";
			}
		}
		return people;
	}
static Student[] result;
static Student[] students;
	public Student[] listAllStudents() {
		students = new Student[nElems];
		int count = 0;
		for(Person s : personArray) {
			if (s != null && s instanceof Student) {
				try {
				students[count++] = (Student)s;
				} catch(ArrayIndexOutOfBoundsException e) {
					System.out.println("array out of bounds. "+e.getMessage());
				}
				//for(int i = 0; i < textbook.length; i++)
				//textbookArray[nElems++] = textbook[i];
			}
		}
		//Student[] result = students;
		result = Arrays.stream(students).filter(value -> value != null).toArray(size -> new Student[size]);
		return result;
		/*		for(int i = 0; i < nElems; i++) {
			if(personArray[i] == null) {
				System.out.println("null at "+i);
			}
			if(personArray[i] instanceof Student) {
				students[i] = (Student)personArray[i];
			} 
		}*/
	}

	public Faculty[] listAllFaculty() {
		Faculty[] faculty = new Faculty[nElems];
		int count = 0;
		for(Person s : personArray) {
			if(s != null && s instanceof Faculty) {
				faculty[count++] = (Faculty)s;
			}
		}
		Faculty[] result = Arrays.stream(faculty).filter(value -> value != null).toArray(size -> new Faculty[size]); //removes null objects from faculty array, since size is worst-case nElems
		return result;
	}

	public String listAllStudentsString() {
		String studentRslt = "";
		//Student[] students = new Student[nElems];
		for(int i = 0; i < nElems; i++) {
			if(personArray[i] != null && personArray[i] instanceof Student) {
				students[i] = (Student)personArray[i];
				studentRslt += students[i].getFirstName()+", ";
			}
		}
		return studentRslt;
	}
	
	public String listAllFacultyString() {
		String facultyRslt = "";
		Faculty[] faculty = new Faculty[nElems];
		for(int i = 0; i < nElems; i++) {
			if(personArray[i] != null && personArray[i] instanceof Faculty) {
				faculty[i] = (Faculty)personArray[i];
				facultyRslt += faculty[i].getFirstName()+", ";
			}
		}
		return facultyRslt;
	}
	
	public String listAllStudentsString2() {
		String studentRslt = "";
		Student[] students = new Student[nElems];
		for(int i = 0; i < nElems; i++) {
			if(personArray[i] != null && personArray[i] instanceof Student) {
				students[i] = (Student)personArray[i];
				studentRslt += "("+i+")["+nElems+"]"+students[i].getFirstName()+", ";
			}
		}
		return studentRslt;
	}

	public String listAllPeopleString() {
		String personRslt = "";
		Person[] person = new Person[nElems];
		for(int i = 0; i < nElems; i++) {
			if(personArray[i] != null) {
				person[i] = personArray[i];
				personRslt += person[i].getFirstName()+", ";
			}
		}
		return personRslt;
	}

}