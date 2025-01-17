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
import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.VirtualMachine;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletInfoCluster
 */
@WebServlet(urlPatterns = {"/ServletHost"}, name = "Servlet4Host")
public class ServletHost extends ServletCommon {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6768696732208326717L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("WEB-INF/Administrateur/host.jsp", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {

			String hostId=request.getParameter("hostId");
			String reponseVm="";

			ClientVSphere client = getClient(request);
			JsonReader reader = new JsonReader();

			String requeteVm = CONFIGURATION_API.getString("GET_VM")+"?filter.hosts.1="+hostId;

			try {
				reponseVm = client.getData(requeteVm);
				ArrayList<VirtualMachine> listeVm = reader.getListVirtualMachine(reponseVm);
				request.getSession().setAttribute("listeVm", listeVm); //TODO pb sérialisation

			} catch (ExceptionConnectionVSphere e ) {
				request.setAttribute("erreur", e);
				out="WEB-INF/pagesErreur/ErrorConnection.jsp";
			} finally {
				RequestDispatcher dispat = request.getRequestDispatcher(out);
				dispat.forward(request, response);
			}

		}

	}	

}
