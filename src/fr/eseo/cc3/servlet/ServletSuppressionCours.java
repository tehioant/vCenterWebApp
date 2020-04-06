package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.CourDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class ServletSuppressionCours
 */
@WebServlet(name = "SuppressionCours", urlPatterns = { "/SuppressionCours" })
public class ServletSuppressionCours extends ServletCommon {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		DAOFactory dao = ((DAOFactory) getServletContext().getAttribute( "daofactory" ) );
		CourDao coursDaoImpl = dao.getCourDao();

		int numServeur = (int) request.getSession().getAttribute("serveur");
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("ListeCours", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);
		
		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {

			try {
				Cour cours = coursDaoImpl.trouver(request.getParameter("cours"), numServeur);
				coursDaoImpl.supprimer(cours);

			} catch (SQLException e) {
				request.setAttribute("erreur", e);
				out="WEB-INF/pagesErreur/ErrorConnection.jsp";
			}finally {
				response.sendRedirect(out);
			}
		}
	}

}