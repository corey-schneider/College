package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Person implements Serializable {
	
	private String firstName, lastName, phoneNumber, id;
	static int staticId = 0; //never 0, starts off at 1 in constructor below
	
	
	public Person(String firstName, String lastName, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.id = ""+staticId++;
	}
	
	public Person() {
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public abstract String toBasicString();

}
