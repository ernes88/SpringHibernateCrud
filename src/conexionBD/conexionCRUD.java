package conexionBD;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class conexionCRUD
 */
@WebServlet("/conexionCRUD")
public class conexionCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public conexionCRUD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jdbcUrl="jdbc:mysql://localhost:3306/springhibernatebasedcrud?useSSL=false";		//como estamos haciendo pruebas en local no necesitamos el protocolo seguro de comunicacion SSL.	
		String usuario="root";
		String contrasena="";
		String driver="com.mysql.jdbc.Driver";
		
		try{
			PrintWriter printWriter=response.getWriter();
			printWriter.print("Nombre de la base de datos: "+jdbcUrl + "<br>");
			Class.forName(driver);
			Connection connection=DriverManager.getConnection(jdbcUrl, usuario, contrasena);
			printWriter.print("Estado de la conexión: Conectado a la base de datos");
			connection.close();		
		}
		catch(Exception e){
			e.printStackTrace();
		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
