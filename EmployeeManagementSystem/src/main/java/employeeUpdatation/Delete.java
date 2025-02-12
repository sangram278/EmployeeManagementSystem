package employeeUpdatation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import employeeEntity.jdbcService.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteEmp")
public class Delete extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("id"));
		PrintWriter writer=resp.getWriter();
			
			if(EmployeeService.Delete(id)>0) {
				System.out.println("deleted successfully");
				resp.sendRedirect("Home.html");
			}else {
				writer.append("employee doesn't exist");
			}
	}

	
}
