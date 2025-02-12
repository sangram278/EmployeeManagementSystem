package employeeService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import employeeEntity.Employee;
import employeeEntity.jdbcService.EmployeeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reg")
public class Register extends HttpServlet {

	String url="jdbc:postgresql://localhost:5432/jspider?user=postgres&password=1234";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(req.getParameter("EmpId"));
		String name=req.getParameter("EmpName");
		int age=Integer.parseInt(req.getParameter("EmpAge"));
		
		String email=req.getParameter("EmpMail");
		String mailexp="[a-zA-Z0-9]+(@)(gmail|yahoo)(.com)";
		Pattern mail=Pattern.compile(mailexp);
		
		String contact=req.getParameter("EmpPh");
		String exp="([6-9])([0-9]){9}";
		Pattern ph=Pattern.compile(exp);
		
		long salary=Integer.parseInt(req.getParameter("EmpSal"));

         
		PrintWriter writer = resp.getWriter();
		
		if(mail.matcher(email).matches()&&ph.matcher(contact).matches()) {	
			
			String query="select * from employees where id=? OR email=? OR contact=?;";
			
			try {
				 
				Class.forName("org.postgresql.Driver");
	            Connection cn = DriverManager.getConnection(url);
				
				PreparedStatement ps=cn.prepareStatement(query);
				ps.setInt(1, id);
				ps.setString(2, email);
				ps.setLong(3, Long.parseLong(contact));
				
				ResultSet rs=ps.executeQuery();
			          
				if(rs.next()) {
					if(id==rs.getInt("id")&&email.equals(rs.getString("email"))&&Long.parseLong(contact)==rs.getLong("contact")) {
						writer.append("Employee already exist");
					}
					else if(id==rs.getInt("id")) {
						writer.append("id already exist");
					}else if(email.equals(rs.getString("email"))) {
						writer.append("email already exist");
					}else if(Long.parseLong(contact)==rs.getLong("contact")) {
						writer.append("contact already exist");
					}
				}else {
										
					Employee emp=new Employee(id, name, age, email, Long.parseLong(contact), salary);
					
					if(EmployeeService.Add(emp)>0) {
						System.out.println("register successfully");
						writer.print("register successfully");
						resp.sendRedirect("Home.html");
					}else {
						RequestDispatcher rd=req.getRequestDispatcher("Register.html");
						rd.include(req, resp);
					}
					
				}
				
//				 rs.close();
//	        	 ps.close();
//	        	 cn.close();
				
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}			
			
		}else {
			writer.append("Enter correct email & mobile number");
		}		
	}
}
