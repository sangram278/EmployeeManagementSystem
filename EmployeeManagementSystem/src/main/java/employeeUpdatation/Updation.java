package employeeUpdatation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

import employeeEntity.jdbcService.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class Updation extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		int age=Integer.parseInt(req.getParameter("age"));
		String email=req.getParameter("email");
		String mailexp="[a-zA-Z0-9]+(@)(gmail|yahoo)(.com)";
		Pattern mail=Pattern.compile(mailexp);
		String contact=req.getParameter("contact");
		String exp="([6-9])([0-9]){9}";
		Pattern ph=Pattern.compile(exp);
		long sal=Long.parseLong(req.getParameter("salary"));
		
		PrintWriter writer=resp.getWriter();
		
		if(mail.matcher(email).matches()&&ph.matcher(contact).matches()) {			
				
				if(EmployeeService.Update(id,name,age,email,contact,sal)>0) {
					System.out.println("sucessfully update");
					resp.sendRedirect("Home.html");
				}
				else {
					writer.append("updation failed");
				}
		}
		else {
			writer.append("enter correct email and contact");
		}
	}

	
}
