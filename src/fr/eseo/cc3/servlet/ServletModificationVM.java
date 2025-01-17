package fr.eseo.cc3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletModificationVM
 */
@WebServlet(urlPatterns = { "/ModificationVM" }, name = "ServletModificationVM")
public class ServletModificationVM extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/Administrateur/modificationVM.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out = this.checkRole("AccueilAdmin", "AccueilReferent", "AccueilEtudiant", utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {
			try {
				JsonReader json = new JsonReader();
				String httpRequest = CONFIGURATION_API.getString("GET_VM")+"/"+request.getParameter("vmId")+"/hardware/";

				ClientVSphere client = this.getClient(request);
				
				if(!request.getParameter("cpu").equals("0")) {
					client.sendData("PATCH", httpRequest+"cpu", json.updateVMRequest("cpu", request.getParameter("cpu")));
				}
				if(!request.getParameter("memory").equals("0")) {
				client.sendData("PATCH", httpRequest+"memory", json.updateVMRequest("memory", request.getParameter("memory")));
				}
				response.sendRedirect(out);
			} catch (ExceptionConnectionVSphere | IOException e) {
				request.setAttribute("erreur", e);
				request.getRequestDispatcher("WEB-INF/pagesErreur/ErrorConnection.jsp").forward(request, response);

			} 

		}
	}
}
