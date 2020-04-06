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

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;
import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletUtilisateurRessource
 */
@WebServlet(urlPatterns = {"/UtilisateurRessource"}, name = "ServletUtilisateurRessource")
public class ServletUtilisateurRessource extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUtilisateurRessource() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		int numServeur = (int) request.getSession().getAttribute("serveur");
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String jspOut=this.checkRole("WEB-INF/Administrateur/user.jsp", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);
		
		if(jspOut.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(jspOut).forward( request, response );
		}else{

			ClientVSphere client = this.getClient(request);
			JsonReader json = new JsonReader();

			ArrayList<VirtualMachine> vms=null;
			try {
				Utilisateur user = ((DAOFactory)getServletContext().getAttribute("daofactory")).getUtilisateurDao().trouver((String)request.getParameter("nomuser"));
			
				vms = ((DAOFactory)getServletContext().getAttribute("daofactory")).getVirtualMachineDao().getUserVm(user, numServeur);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			int totalRam = 0;
			int totalCapacity = 0;

			for(VirtualMachine vm : vms) {
				String reponseVm="";
				String requeteVm = CONFIGURATION_API.getString("GET_VM") + "/" + vm.getRefVm();

				try {
					reponseVm = client.getData(requeteVm);
					fr.eseo.cc3.model.VirtualMachine vmDetails = json.getVirtualMachineDetails(reponseVm);

					totalRam+=vmDetails.getMemorySizeMIB().getSize_MiB();
					totalCapacity+=vmDetails.getDisk().getCapacity();
				} catch (ExceptionConnectionVSphere e) {
					//TODO quelque chose
				}
			}

			request.setAttribute("totalRam", totalRam);
			request.setAttribute("totalCapacity", totalCapacity);

			RequestDispatcher dispat = request.getRequestDispatcher(jspOut);
			dispat.forward(request, response);

		}
	}
}
