package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

@WebServlet(urlPatterns = {"/ListeNics"}, name = "ServletListeNics")
public class ServletListeNics extends ServletCommon{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("AccueilAdmin", "AccueilReferent", "AccueilEtudiant", utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {
			String nics;
			List<String> ArrayNics;

			String vm =request.getParameter("vmId"); // identifiant de la vm 
			ClientVSphere client = this.getClient(request);

			String url = "/rest/vcenter/vm/"+vm+"/hardware/ethernet";

			try {
				nics = client.getData(url);
				ArrayNics = Arrays.asList(nics.split("\\s*,\\s*"));

			} catch (ExceptionConnectionVSphere | IOException e) {

				request.setAttribute("erreur", e);
				out="WEB-INF/pagesErreur/ErrorConnection.jsp";

			} finally {

				RequestDispatcher dispat = request.getRequestDispatcher(out);
				dispat.forward(request, response);
			}
		}
	}
}