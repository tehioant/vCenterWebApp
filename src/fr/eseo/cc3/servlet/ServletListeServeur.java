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
import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class ServletServeur
 */
@WebServlet(name = "ListeServeurs", urlPatterns = { "/ListeServeurs" })
public class ServletListeServeur extends ServletCommon {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAOFactory dao = ((DAOFactory) getServletContext().getAttribute( "daofactory" ) );
		ServeurDao serveurDao = dao.getServeurDao();
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String servletOut=this.checkRole("WEB-INF/Administrateur/listeServeurs.jsp", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);

		if(servletOut.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(servletOut).forward( request, response );
		}else {

			try {
				
				ArrayList<Serveur> listeServeur = serveurDao.lister();
				
				for (int i = 0 ; i < listeServeur.size() ; i++) {
					
					Serveur serveurs = listeServeur.get(i);
					String ip = serveurs.getIpServeur();
					serveurs.setIpServeur(ip);
				}
			
				request.setAttribute("listeServeurs", listeServeur);
				
			} catch (SQLException e) {
				request.setAttribute("erreur", e);
				servletOut="WEB-INF/pagesErreur/ErrorConnection.jsp";
			}finally {
				RequestDispatcher dispat = request.getRequestDispatcher(servletOut);
				dispat.forward(request, response);
			}
		}
	}	
	

}
