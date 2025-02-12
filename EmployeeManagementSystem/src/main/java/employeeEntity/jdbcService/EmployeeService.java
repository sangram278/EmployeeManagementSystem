package employeeEntity.jdbcService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import employeeEntity.Employee;
import jakarta.servlet.http.HttpServlet;

public class EmployeeService extends HttpServlet {

	private static String url="jdbc:postgresql://localhost:5432/jspider?user=postgres&password=1234";
	private static Connection cn;
	private static PreparedStatement ps;
//Establish Connection
	static {
		try {
			Class.forName("org.postgresql.Driver");
			cn=DriverManager.getConnection(url);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/*Adding employee using Add() method*/
	
	public static int Add(Employee e) {
        String query="insert into employees values(?,?,?,?,?,?)";		
		
		try {
			ps=cn.prepareStatement(query);
			ps.setInt(1,e.getId());
			ps.setString(2,e.getName());
			ps.setInt(3, e.getAge());
			ps.setString(4, e.getEmail());
			ps.setLong(5, e.getContact());
			ps.setLong(6,e.getSal());
			
			return ps.executeUpdate();			
			
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		return 0;
	}
	
	/*Fetch all data from database using fetchAll() method*/
	
	public static List<Employee> fetchAll(){
		
		List<Employee> list=new ArrayList<>();
		
		 String query = "SELECT id, name, age, email, contact, salary FROM employees";
    	 
    	 try {
    		 ps = cn.prepareStatement(query);
    		 ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				 list.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"), rs.getLong("contact"), rs.getLong("salary")));
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}      	 
		
		return list;
	}
	
	
	public static int Delete(int id) {
		
		String delete="delete from employees where id=?";
		try {
			ps = cn.prepareStatement(delete);
			ps.setInt(1,id);
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public static ResultSet edit(int id) {
		
		String user="select * from employees where id=?";
		
		try {
			ps = cn.prepareStatement(user);
			ps.setInt(1, id);
			
			return ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static int Update(int id, String name, int age, String email, String contact, long sal) {
		
		String update="update employees set name=?, age=?, email=?, contact=?, salary=? where id=?";
		try {
			ps = cn.prepareStatement(update);
			ps.setString(1, name);
			ps.setInt(2, age);
			ps.setString(3,email);
			ps.setLong(4, Long.parseLong(contact));
			ps.setLong(5, sal);
			ps.setInt(6, id);
			
			return ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
	
}
