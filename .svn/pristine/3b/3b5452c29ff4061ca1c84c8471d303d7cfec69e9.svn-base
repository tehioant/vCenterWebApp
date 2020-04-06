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
import fr.eseo.cc3.dao._interface.UtilisateurDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
 

/**
 * Servlet implementation class ServletCreationCours
 */
@WebServlet(name = "CreationCours", urlPatterns = { "/CreationCours" })
public class ServletCreationCours extends ServletCommon {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory dao = ((DAOFactory) getServletContext().getAttribute( "daofactory" ) );
		CourDao coursDaoImpl = dao.getCourDao();
		UtilisateurDao utilisateirDao = dao.getUtilisateurDao();
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("ListeCours", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {

				try {
						Cour cours = new Cour(request.getParameter("nom"), 0, 0, 1);
						Utilisateur referent = utilisateirDao.trouver(request.getParameter("nomUtilisateurReferent"));
						coursDaoImpl.creer(cours);
						coursDaoImpl.setReferent(cours,referent);


				} catch (SQLException e) {
					request.setAttribute("erreur", e);
					out="WEB-INF/pagesErreur/ErrorConnection.jsp";
					request.getRequestDispatcher(out).include(request, response);
				}finally {
					response.sendRedirect(out);
				}
		}
	}

}
