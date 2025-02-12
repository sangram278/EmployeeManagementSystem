package employeeUpdatation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import employeeEntity.jdbcService.EmployeeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class EditEmployee extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("id"));
				
			ResultSet rs=EmployeeService.edit(id);
			PrintWriter writer =resp.getWriter();
			
			try {		
				if(rs.next()) {
					writer.print("<html>");
					writer.print("<body>");
					writer.append("<div class='container'>");
					writer.print("<form action='update'>");
					writer.print("<table>");
					writer.print("<tr><td>Employee id:</td><td><input type='number' name='id' value='"+rs.getInt("id")+"' readonly/></td></tr>");
					writer.print("<tr><td>Employee Name:</td><td><input type='text' name='name' value='"+rs.getString("name")+"'/></td></tr>");
					writer.print("<tr><td>Employee age:</td><td><input type='number' name='age' value='"+rs.getInt("age")+"'/></td></tr>");
					writer.print("<tr><td>Employee email:</td><td><input type='email' name='email' value='"+rs.getString("email")+"'/></td></tr>");
					writer.print("<tr><td>Employee contact:</td><td><input type='number' name='contact' value='"+rs.getLong("contact")+"'/></td></tr>");
					writer.print("<tr><td>Employee salary:</td><td><input type='number' name='salary' value='"+rs.getLong("salary")+"'/></td></tr>");
					writer.print("<tr><td><input type='submit' name='submit' value='submit'/></td></tr>");
					writer.print("</table>");
					writer.print("</form>");
					writer.append("</div>");
					writer.print("</body>");
					writer.print("</html>");
				}else {
					writer.append("user not found");
				}
			}catch(SQLException e) {
			   e.printStackTrace();	
			}
		
//		RequestDispatcher rd=req.getRequestDispatcher("Edit.html");
//		rd.forward(req, resp);
	}

	
}
