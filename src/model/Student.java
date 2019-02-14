package model;

/**
 * 
 * @author Corey Schneider
 *
 */

import java.io.Serializable;

import util.Utilities;
import bags.CourseBag;
import bags.MasterCourseBag;
import exceptions.GpaException;


@SuppressWarnings("serial")
public class Student extends Person implements Serializable {

	private String major, id;
	private double gpa;
	private CourseBag courseBag;
	MasterCourseBag mcb;

	public Student() { }

	public Student(String firstName, String lastName, String phoneNumber, String major) {
		super(firstName, lastName, phoneNumber);
		/*		if(gpa < 0.0) {
			try {
				throw new GpaException("GPA is too low!");
			} catch (GpaException e) {
				this.gpa = 0.0;
			}
		} else if(gpa > 4.0) {
			try {
				throw new GpaException("GPA is too high!");
			} catch (GpaException e) {
				this.gpa = 4.0;
			}
		} else {
			this.gpa = Utilities.calculateGPA(courseBag.getCourseTakenArray());
		}*/
		this.major = major;
		this.id = ""+Person.staticId;
	}

	public Student(String firstName, String lastName, String phoneNumber, String major, CourseBag courseBag) {
		super(firstName, lastName, phoneNumber);
		if(gpa < 0.0) {
			try {
				throw new GpaException("GPA is too low!");
			} catch (GpaException e) {
				this.gpa = 0.0;
			}
		} else if(gpa > 4.0) {
			try {
				throw new GpaException("GPA is too high!");
			} catch (GpaException e) {
				this.gpa = 4.0;
			}
		} else {
			this.gpa = Utilities.calculateGPA(courseBag.getCourseTakenArray());
		}
		this.major = major;
		this.id = ""+Person.staticId;
		this.courseBag = courseBag;
	}

	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void refreshGpa() {
		gpa = Utilities.calculateGPA(courseBag.getCourseTakenArray());
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		if(gpa > 4.0 || gpa < 0.0) {
			try {
				throw new GpaException();
			} catch (GpaException e) {
				if (gpa > 4.0)
					this.gpa = 4.0;
				else if (gpa < 0.0)
					this.gpa = 0.0;
				else
					this.gpa = gpa;
			}
		}
	}
	public CourseBag getCourseBag() {
		return courseBag;
	}
	public void setCourseBag(CourseBag courseBag) {
		this.courseBag = courseBag;
	}
	@Override
	public String toString() {
		return "[Student] "+super.getFirstName()+" "+super.getLastName()+" [x] "+super.getPhoneNumber()+" [x] Major: "+major+" [x] ID: "+id+" [x] GPA: " + gpa + " [x] \ncourseBag: " + courseBag;
	}

	public String toBasicString() {
		return "Student, "+super.getFirstName()+", "+super.getLastName()+", "+super.getPhoneNumber()+", "+major+", "+id+", "+gpa+", "+courseBag.toBasicString();
	}

	public void addToCourseBag(Course course, String type) {
		course.setType(type);
		System.out.println("found 'er: "+course);
		courseBag.add(course);
	}


}
