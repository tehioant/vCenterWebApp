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
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Serveur;
import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class ServletSupprimerServeur
 */
@WebServlet(name = "SuppressionServeurs", urlPatterns = { "/SuppressionServeurs" })
public class ServletSupprimerServeur extends ServletCommon {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		DAOFactory dao = ((DAOFactory) getServletContext().getAttribute( "daofactory" ) );
		ServeurDao serveurDao = dao.getServeurDao();
		VirtualMachineDao vmDao = dao.getVirtualMachineDao();
		
		int numServeur = (int) request.getSession().getAttribute("serveur");
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String servletOut=this.checkRole("ListeServeurs", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);

		if(servletOut.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(servletOut).forward( request, response );
		}else {

			try {
				Serveur serveurs = serveurDao.trouverSemi(request.getParameter("serveurs"));
				ArrayList<fr.eseo.cc3.dao.bean.VirtualMachine> listeVm = vmDao.lister(numServeur);
				boolean sup = true;
				
				for ( int i=0 ; i< listeVm.size();i++) {
					if(listeVm.get(i).getServeur() == serveurs.getNumServeur()) {
						servletOut="WEB-INF/pagesErreur/ErreurSuppressionServeur.jsp";
						sup=false;
					}					
				}
				if (sup == true) {
					serveurDao.supprimer(serveurs);
				}
				
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
