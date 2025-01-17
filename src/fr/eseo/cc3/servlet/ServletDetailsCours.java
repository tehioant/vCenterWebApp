package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.CourDao;
import fr.eseo.cc3.dao._interface.UtilisateurDao;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;

/**
 * Servlet implementation class ServletDetailsCours
 */
@WebServlet(name = "DetailsCours", urlPatterns = { "/DetailsCours" })
public class ServletDetailsCours extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAOFactory dao = ((DAOFactory) getServletContext().getAttribute( "daofactory" ) );
		CourDao coursDaoImpl = dao.getCourDao();
		VirtualMachineDao virtualMachineDao = dao.getVirtualMachineDao();
		UtilisateurDao utilisateirDao = dao.getUtilisateurDao();
		
		int numServeur = (int) request.getSession().getAttribute("serveur");
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String jspOut=this.checkRole("WEB-INF/Administrateur/listeVmCours.jsp", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);

		if(jspOut.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(jspOut).forward( request, response );
		}else {

			try {
				Cour cours = coursDaoImpl.trouver(request.getParameter("nomCours"), numServeur);
				ArrayList<VirtualMachine> listevm = virtualMachineDao.getVmCour(cours);
				ArrayList<Utilisateur> listeUtilisateur = new ArrayList<>();

				for (int i = 0 ; i < listevm.size() ; i++) {
					listeUtilisateur.add(utilisateirDao.getUserVm(listevm.get(i)));
				}

				request.setAttribute("listeVm", listevm);
				request.setAttribute("listeUtilisateur", listeUtilisateur);

			} catch (SQLException e) {
				request.setAttribute("erreur", e);
				jspOut="WEB-INF/pagesErreur/ErrorConnection.jsp";
			}finally {
				RequestDispatcher dispat = request.getRequestDispatcher(jspOut);
				dispat.forward(request, response);
			}

		}
	}

}
