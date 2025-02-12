package employeeController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import employeeEntity.Employee;
import employeeEntity.jdbcService.EmployeeService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/display")
public class DisplayEmp extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter writer = resp.getWriter();
        List<Employee> list=EmployeeService.fetchAll();       	 
        	 
        	 writer.append("<html><body>");
        	 writer.append("<h2>Employee Data</h2>");
        	 writer.append("<table border=1>");
        	 writer.append("<tr><th>ID</th><th>Name</th><th>Age</th><th>Email</th><th>Phone</th><th>Salary</th><th>edit</th></tr>");
        	 
        	 
        	 for(Employee e:list) {
        		 writer.append("<tr>");
        		 writer.print("<td>"+e.getId()+"</td>");
        		 writer.append("<td>"+e.getName()+"</td>");
        		 writer.append("<td>"+e.getAge()+"</td>");
        		 writer.append("<td>"+e.getEmail()+"</td>");
        		 writer.append("<td>"+e.getContact()+"</td>");
        		 writer.append("<td>"+e.getSal()+"</td>");
        		 writer.append("<td>");
                 writer.append("<a href='edit?id=" + e.getId() + "'>Edit</a> | ");
                 writer.append("<a href='delete?id=" + e.getId() + " '>Delete</a>");
                 writer.append("</td>");
        		 writer.append("</tr>");
        	 }      	 
        	 
        	 writer.append("</table>");
        	 writer.append("</body></html>");        	 
		
	}

	
}
           