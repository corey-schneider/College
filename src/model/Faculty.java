package model;

import java.io.Serializable;
import java.text.DecimalFormat;

@SuppressWarnings("serial")
public class Faculty extends Person implements Serializable {

	private String title, id;
	private double salary;
	DecimalFormat df = new DecimalFormat("###,###.00");
	
	public Faculty() { }
	
	public Faculty(String firstName, String lastName, String phoneNumber, String title, double salary) {
		super(firstName, lastName, phoneNumber);
		this.title = title;
		id = ""+Person.staticId;
		this.salary = salary;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "[Faculty] "+title+" "+super.getFirstName()+" "+super.getLastName()+" [x] "+super.getPhoneNumber()+" [x] ID: "+id+" [x] Salary: $"+df.format(salary);
	}

	/**
	 * Basic string, used for dumping bags/arrays to text files.
	 * Not the best to read. toString() is more eye-friendly.
	 */
	public String toBasicString() {
		return "Faculty, "+title+", "+super.getFirstName()+", "+super.getLastName()+", "+super.getPhoneNumber()+", "+id+", "+salary;
	}
	
	
}
