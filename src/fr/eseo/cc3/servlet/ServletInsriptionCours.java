package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.CourDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class ServletInsriptionCours
 */
@WebServlet(name = "InsriptionCours", urlPatterns = { "/InsriptionCours" })
public class ServletInsriptionCours extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAOFactory dao = ((DAOFactory) getServletContext().getAttribute( "daofactory" ) );
		CourDao coursDaoImpl = dao.getCourDao();
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		String out = this.checkRole(PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, "AccueilEtudiant", utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {

			try {
				Cour cours = coursDaoImpl.trouver(request.getParameter("nomCours"), 1);
				coursDaoImpl.ajoutEtudiant(cours, utilisateur);

			} catch (SQLException e) {
				request.setAttribute("erreur", e);
				out="WEB-INF/pagesErreur/ErrorConnection.jsp";
			}finally {
				response.sendRedirect(out);
			}

		}
	}

}
