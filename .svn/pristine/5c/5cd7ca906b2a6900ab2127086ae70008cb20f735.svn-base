package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.ServeurDao;
import fr.eseo.cc3.dao.bean.Serveur;

/**
 * Servlet implementation class Accueil
 */
@WebServlet(name = "Accueil", urlPatterns = { "/Accueil" })
public class Accueil extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.clearSession(request);
		String servletOut = "WEB-INF/Commun/index.jsp";
		ServeurDao serveurDao;
		ArrayList<Serveur> listServeur = new ArrayList<Serveur>();
		serveurDao = ( (DAOFactory) getServletContext().getAttribute( "daofactory" ) ).getServeurDao();
		try {
			listServeur = serveurDao.lister();
		}
		catch (SQLException e) {
			request.setAttribute("erreur", e);
			servletOut = "WEB-INF/pagesErreur/ErrorConnection.jsp";
			request.getRequestDispatcher(servletOut).include(request, response);
		}finally {
			request.setAttribute("serveurs", listServeur);
			RequestDispatcher dispat = request.getRequestDispatcher(servletOut);
			dispat.forward(request, response);
		}

	}

}