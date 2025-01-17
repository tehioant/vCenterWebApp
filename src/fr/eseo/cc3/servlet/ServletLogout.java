package fr.eseo.cc3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletLogout
 */
@WebServlet(urlPatterns = {"/Logout"}, name = "ServletLogout")
public class ServletLogout extends ServletCommon {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		doPost(request,response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ClientVSphere client = getClient(request);
		String jspOut="Accueil";
		this.clearSession(request);
		try {
			client.logout();
		
		} catch (ExceptionConnectionVSphere | NullPointerException e) {
			jspOut="Accueil";
		} finally {
			response.sendRedirect(jspOut);
		}
		
	}

}
