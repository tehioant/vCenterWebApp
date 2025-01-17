package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletSupprimerVM
 */

@WebServlet(urlPatterns = {"/SupprimerVm"}, name = "SupprimerVM")

public class SupprimerVM extends ServletCommon {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int numServeur = (int) request.getSession().getAttribute("serveur");
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out = this.checkRole("AccueilAdmin", "AccueilReferent", "AccueilEtudiant", utilisateur);
		int banniere = 0;

		if(out.equals(PAGE_ERREUR_DROITS)) {
			request.getRequestDispatcher(out).forward( request, response );
		}else {
			
			VirtualMachineDao virtualMachineDao = ( (DAOFactory) getServletContext().getAttribute( "daofactory" ) ).getVirtualMachineDao();
			String vm =request.getParameter("vmId");
			ClientVSphere client = this.getClient(request);
			String requete;
			
			try {
				//suppression de la vm sur vSphere
				requete= CONFIGURATION_API.getString("GET_VM").trim()+"/"+vm;
				client.deleteData(requete);
				//suppression de la vm dans la bdd
				virtualMachineDao.supprimer(vm,numServeur);

			}catch (ExceptionConnectionVSphere | SQLException e) {
				banniere = 1;
				request.setAttribute("banniere", banniere);
				request.getRequestDispatcher(out).include(request, response);
			}finally {
				response.sendRedirect(out);
			}
		}
	}
}