package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Homeservlet
 */
@WebServlet("/Home")
public class Homeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Homeservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().print("<h1> hello my name is bintunimana Pacifique and my id is 25496</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String enterdid = request.getParameter("id");
		if (!enterdid.matches("\\d+")) {
			response.getWriter().print("<h1>id must be numbers</h1>");
			return;
		}
		Integer id =Integer.parseInt(enterdid);
		try { 
			String db_url="jdbc:postgresql://localhost:5432/best_programming";
			String username="postgres";
			String pass="";
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(db_url,username,pass);
			PreparedStatement pst = con.prepareStatement("select *from student where id = ?");
			pst.setInt(1, id);
			ResultSet rs =  pst.executeQuery();
			if (rs.next()) {
				String name = rs.getString("names");
				response.getWriter().print("<h1>your name is "+name+" and id is "+id+"</h1>");
				con.close();
				return;
			}
			
		}
catch (SQLException e) {
	// TODO: handle exception
} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	response.getWriter().print("<h2>id doesn't exist</h2>")	;
	}

}
