package fr.eseo.cc3.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletChangementEtatVm
 */

@WebServlet(urlPatterns = {"/ChangementEtatVm"}, name = "ServletChangementEtatVm")
public class ServletChangementEtatVm extends ServletCommon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("AccueilAdmin","AccueilReferent" ,"AccueilEtudiant", utilisateur);
		int banniere1 = 0;

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {

			int etat; 

			String vm =request.getParameter("vmId");// identifiant de la vm
			ClientVSphere client = this.getClient(request);

			try {
				etat = Integer.parseInt(request.getParameter("etat"));

			}catch(NumberFormatException e) {
				etat = -1;
			}


			try {
				client.changeAlimentationVm(vm, etat);
			} catch (ExceptionConnectionVSphere | IOException e) {
				banniere1 = 1;
				request.setAttribute("banniere1", banniere1);
				request.getRequestDispatcher(out).include(request, response);

			} finally {
				response.sendRedirect(out);
			}


		}
	}
}
