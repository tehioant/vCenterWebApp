package fr.eseo.cc3.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletExportOVFinter
 * 
 * Servlet intermediaire pour passer les parametres au jsp
 * 
 */
@WebServlet("/ExportOVFinter")
public class ServletExportOVFinter extends HttpServlet {
	private static final long serialVersionUID = 1L;
    


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String out = "WEB-INF/Commun/exportOVFinter.jsp";
		
		request.setAttribute("vmId", request.getParameter("vmId")); // identifiant de la vm 
		request.setAttribute("vmName", request.getParameter("vmName")); // Nom de la vm 
		
		
		request.getRequestDispatcher(out).forward( request, response );
	}

}
