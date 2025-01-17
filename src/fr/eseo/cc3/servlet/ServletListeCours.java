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
import fr.eseo.cc3.dao._interface.CourDao;
import fr.eseo.cc3.dao._interface.UtilisateurDao;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class ServletListeCours
 */
@WebServlet(name = "ListeCours", urlPatterns = { "/ListeCours" })
public class ServletListeCours extends ServletCommon {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAOFactory dao = ((DAOFactory) getServletContext().getAttribute( "daofactory" ) );
		CourDao coursDaoImpl = dao.getCourDao();
		VirtualMachineDao virtualMachineDao = dao.getVirtualMachineDao();
		UtilisateurDao utilisateirDao = dao.getUtilisateurDao();
		
		int numServeur = (int) request.getSession().getAttribute("serveur");
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("WEB-INF/Administrateur/listeCours.jsp", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {

			try {
				ArrayList<Cour> listeCours = coursDaoImpl.lister(numServeur);
				ArrayList<Utilisateur> listeReferent = utilisateirDao.listerReferent();

				for (int i = 0 ; i < listeCours.size() ; i++) {

					Cour cours = listeCours.get(i);

					int nbUtilisateur = utilisateirDao.listerUtilisateurCours(cours).size();
					cours.setNbEtudiant(nbUtilisateur);
					int nbVm = virtualMachineDao.getVmCour(cours).size();
					cours.setNbVm(nbVm);
				}

				request.setAttribute("listeReferents", listeReferent);
				request.setAttribute("listeCours", listeCours);

			} catch (SQLException e) {
				request.setAttribute("erreur", e);
				out="WEB-INF/pagesErreur/ErrorConnection.jsp";
			}finally {
				RequestDispatcher dispat = request.getRequestDispatcher(out);
				dispat.forward(request, response);
			}
		}
	}
}