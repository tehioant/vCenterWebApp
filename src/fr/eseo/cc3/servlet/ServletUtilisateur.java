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
import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class ServletUtilisateur
 */
@WebServlet(urlPatterns = {"/Utilisateur"}, name = "ServletUtilisateur")
public class ServletUtilisateur extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String jspOut = this.checkRole("WEB-INF/Administrateur/accueilAdmin.jsp", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);
		ArrayList<Utilisateur> listeUser = new ArrayList<Utilisateur>();
			
		if(jspOut.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(jspOut).forward( request, response );
		}else{
			try {
				listeUser = ((DAOFactory)getServletContext().getAttribute("daofactory")).getUtilisateurDao().lister();
			}
			catch (SQLException e) {
				request.setAttribute("erreur", e);

				jspOut = "WEB-INF/pagesErreur/ErrorConnection.jsp";
			}finally {
				request.setAttribute("listeUser", listeUser);

				RequestDispatcher dispat = request.getRequestDispatcher(jspOut);
				dispat.forward(request, response);
			}
		}
	}
}
