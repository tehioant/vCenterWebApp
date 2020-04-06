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
import fr.eseo.cc3.json.JsonReader;

import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletVirtualMachine
 */
@WebServlet(urlPatterns = {"/VirtualMachine"}, name = "VirtualMachine")
public class DetailVirtualMachine extends ServletCommon {

	private static final long serialVersionUID = 1L;    

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String jspOut=this.checkRole("WEB-INF/Administrateur/virtualmachine.jsp", "WEB-INF/Referent/virtualmachineRef.jsp", "WEB-INF/Etudiant/virtualmachineEtu.jsp", utilisateur);

		if(jspOut.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(jspOut).forward( request, response );
		}else {
			String vmId = request.getParameter("vmId");

			ClientVSphere client = getClient(request);
			JsonReader reader = new JsonReader();

			String reponseVm="";
			String requeteVm = CONFIGURATION_API.getString("GET_VM") +"/"+ vmId;
			try {
				reponseVm = client.getData(requeteVm);

				fr.eseo.cc3.model.VirtualMachine virtualMachine = reader.getVirtualMachineDetails(reponseVm);
				request.setAttribute("virtualMachine", virtualMachine);
				request.setAttribute("name", vmId);

			} catch (ExceptionConnectionVSphere e ) {

				request.setAttribute("erreur", e);
				jspOut="WEB-INF/pagesErreur/ErrorConnection.jsp";
			}finally {
				RequestDispatcher dispat = request.getRequestDispatcher(jspOut);
				dispat.forward(request, response);
			}
		}
	}
}