package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.CourDao;
import fr.eseo.cc3.dao._interface.UtilisateurDao;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;
import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletAccueilEtudiant
 */
@WebServlet(urlPatterns = { "/AccueilEtudiant" }, name = "ServletAccueilEtudiant")
public class ServletAccueilEtudiant extends ServletCommon {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CourDao coursDaoImpl;
		VirtualMachineDao virtualMachineDao;
		UtilisateurDao utilisateurDao;
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole(PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, "WEB-INF/Etudiant/accueilEtu.jsp", utilisateur);

		ArrayList<fr.eseo.cc3.model.VirtualMachine> listeVm = new ArrayList<fr.eseo.cc3.model.VirtualMachine>();

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {


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

				DAOFactory dao = ((DAOFactory) getServletContext().getAttribute("daofactory" ));
				virtualMachineDao = dao.getVirtualMachineDao();
				coursDaoImpl = dao.getCourDao();
				utilisateurDao = dao.getUtilisateurDao();

				String reponseVm="";

				ClientVSphere client = getClient(request);
				client.login();//permet de vérifier si la connexion au vcenter est possible
				JsonReader reader = new JsonReader();
				StringBuilder stringBuilder = new StringBuilder();

				session.setAttribute("referentUtilisateur", utilisateurDao.getReferentUser(utilisateur));

				ArrayList<VirtualMachine> listevm =  virtualMachineDao.getUserVm(utilisateur, numServeur);
				ArrayList<Cour> listeCours = coursDaoImpl.getCourUtilisateurNonInscrit(utilisateur, numServeur);
				ArrayList<VirtualMachine> listeTemplate = virtualMachineDao.listeTemplate(numServeur);
				ArrayList<Cour> listeCoursUser = coursDaoImpl.getCourUtilisateur(utilisateur, numServeur);


				if(listevm.size()>0) {
					//Utilisation d'un string builder pour concaténer les strings car plus propre selon sonar
					stringBuilder.append(CONFIGURATION_API.getString("GET_VM")+"?filter.vms.1="+listevm.get(0).getRefVm());
					for (int i =1; i <listevm.size();i++) {
						stringBuilder.append("&filter.vms."+(i+1)+"="+listevm.get(i).getRefVm());
					}
					reponseVm = client.getData(stringBuilder.toString());
					listeVm = reader.getListVirtualMachine(reponseVm);
				}
				
				request.getSession().setAttribute("listeVm", listeVm);
				request.setAttribute("listeCours", listeCours);
				request.setAttribute("listeTemplate", listeTemplate);
				request.setAttribute("listeOs", this.getListOs(numServeur));
				request.setAttribute("listeCoursUser", listeCoursUser);

			} catch (ExceptionConnectionVSphere | SQLException | ConnectException e ) {

				request.setAttribute("erreur", e);
				out="WEB-INF/pagesErreur/ErrorConnection.jsp";
				request.getRequestDispatcher(out).include(request, response);
			} finally {
				RequestDispatcher dispat = request.getRequestDispatcher(out);

				dispat.forward(request, response);
			}
		}
	}
}
