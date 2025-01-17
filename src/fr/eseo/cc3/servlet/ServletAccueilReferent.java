package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;
import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;



import fr.eseo.cc3.dao._interface.CourDao;
import fr.eseo.cc3.dao.bean.Cour;
/**
 * Servlet implementation class ServletAccueilReferent
 */
@WebServlet(urlPatterns = { "/AccueilReferent" }, name = "ServletAccueilReferent")

public class ServletAccueilReferent extends ServletCommon {
	private static final long serialVersionUID = 1L;
	private static final ResourceBundle CONFIGURATION_API = ResourceBundle.getBundle("configAPI");



	/**
	 * @see  HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VirtualMachineDao  virtualMachineDao;
		CourDao courDao;
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole(PAGE_ERREUR_DROITS, "WEB-INF/Referent/accueilReferent.jsp", PAGE_ERREUR_DROITS, utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {
			virtualMachineDao = ( (DAOFactory) getServletContext().getAttribute( "daofactory" ) ).getVirtualMachineDao();
			courDao = ( (DAOFactory) getServletContext().getAttribute( "daofactory" ) ).getCourDao();


			
			try {
				int banniere = (int) request.getAttribute("banniere");
				request.setAttribute("banniere", banniere);

			} catch (NullPointerException e) {
				request.setAttribute("banniere", 0);
			} 

			try {
				int banniere1 = (int) request.getAttribute("banniere1");
				request.setAttribute("banniere1", banniere1);

			} catch (NullPointerException e) {
				request.setAttribute("banniere1", 0);
			} 

			try {
				HttpSession session = request.getSession();

				int numServeur = (int) session.getAttribute("serveur");	
				String reponselisteVm="";

				ClientVSphere client = this.getClient(request);
				client.login();//permet de vérifier si la connexion au vcenter est possible
				JsonReader reader = new JsonReader();

				ArrayList<Cour> cour = courDao.getCourReferent(utilisateur, numServeur);

				String requeteListeVmCour2 ="";
				ArrayList<fr.eseo.cc3.model.VirtualMachine> listeVm = new ArrayList<>();
				int k=2;
				if(cour.size()>0) {
					for (int i=0 ; i< cour.size() ;i++) {

						ArrayList<VirtualMachine> requeteListeVmCour = virtualMachineDao.getVmCour(cour.get(i));

						if(requeteListeVmCour.size()>0) {
							for (int j =0; j <requeteListeVmCour.size();j++) {
								if(requeteListeVmCour2.length() ==0) {
									requeteListeVmCour2 += CONFIGURATION_API.getString("GET_VM")+"?filter.vms.1="+requeteListeVmCour.get(j).getRefVm();
								}
								else {
									requeteListeVmCour2 += "&filter.vms."+(k++)+"="+requeteListeVmCour.get(j).getRefVm();
								}
							}
							reponselisteVm = client.getData(requeteListeVmCour2);
							listeVm = reader.getListVirtualMachine(reponselisteVm);
						}
					}
				}

				request.setAttribute("listeOs", this.getListOs(numServeur));
				request.getSession().setAttribute("listeVm", listeVm);

			} catch (ExceptionConnectionVSphere | SQLException | ConnectException e) {
				request.setAttribute("erreur", e);
				out="WEB-INF/pagesErreur/ErrorConnection.jsp";
				request.getRequestDispatcher(out).include(request, response);
			}

			RequestDispatcher dispat = request.getRequestDispatcher(out);
			dispat.forward(request, response);
		}
	}
}