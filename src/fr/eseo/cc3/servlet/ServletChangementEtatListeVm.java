package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.model.VirtualMachine;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletChangementEtatListeVm
 */
@WebServlet(urlPatterns = { "/ChangementEtatListeVm" }, name = "ServletChangementEtatListeVm")
public class ServletChangementEtatListeVm extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("AccueilAdmin", "AccueilReferent", "AccueilEtudiant", utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {

			int etat; 
			ClientVSphere client = this.getClient(request);
			ArrayList<VirtualMachine> listeVm = (ArrayList<VirtualMachine>) request.getSession().getAttribute("listeVm");

			try {
				etat = Integer.parseInt(request.getParameter("etat"));

			}catch(NumberFormatException e) {
				etat = -1;
			}
			
			for (VirtualMachine vm : listeVm) {
				try {
					client.changeAlimentationVm(vm.getVm(), etat);
					Thread.sleep(200);
				} catch (ExceptionConnectionVSphere e) {
					if (e.getCodeErreur()!=400) {
						request.setAttribute("erreur", e);
						out = "WEB-INF/pagesErreur/ErreurChangementEtat.jsp";
					}
				} catch (InterruptedException e) {
					try {
						client.changeAlimentationVm(vm.getVm(), etat);
					} catch (ExceptionConnectionVSphere e1) {
						request.setAttribute("erreur", e);
						out = "WEB-INF/pagesErreur/ErreurChangementEtat.jsp";
					}
				} 
			}
			response.sendRedirect(out);
		}
	}
}