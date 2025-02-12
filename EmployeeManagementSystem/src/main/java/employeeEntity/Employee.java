package employeeEntity;

import java.util.ArrayList;
import java.util.List;

public class Employee {

	private int id;
	private String name;
	private int age;
	private String email;
	private long contact;
	private long sal;
	public Employee(int id, String name, int age, String email, long contact, long sal) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.sal = sal;
		this.contact=contact;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getEmail() {
		return email;
	}
	public long getSal() {
		return sal;
	}
	public long getContact() {
		return contact;
	}
	
	
}
