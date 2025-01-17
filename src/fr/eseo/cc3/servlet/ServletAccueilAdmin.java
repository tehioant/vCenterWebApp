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
import fr.eseo.cc3.dao._interface.CourDao;
import fr.eseo.cc3.dao._interface.UtilisateurDao;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.*;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class AccueilAdmin
 * 
 * 
 * 
 */
@WebServlet(urlPatterns = {"/AccueilAdmin"}, name = "ServletAccueilAdmin")
public class ServletAccueilAdmin extends ServletCommon {
	private static final long serialVersionUID = 1L;


	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		DAOFactory dao = ((DAOFactory) getServletContext().getAttribute( "daofactory" ) );
		CourDao coursDaoImpl = dao.getCourDao();
		VirtualMachineDao virtualMachineDao = dao.getVirtualMachineDao();
		UtilisateurDao utilisateirDao = dao.getUtilisateurDao();
		
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String jspOut=this.checkRole("WEB-INF/Administrateur/accueilAdmin.jsp", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);
		int numServeur = (int) request.getSession().getAttribute("serveur");
		
		if(jspOut.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(jspOut).forward( request, response );
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
			String reponseDatastore="";
			String reponseHote="";
			String reponseVm="";

			String requeteDatastore = CONFIGURATION_API.getString("GET_DATASTORE");
			String requeteHost = CONFIGURATION_API.getString("GET_HOST");
			String requeteVm = CONFIGURATION_API.getString("GET_VM");

			ClientVSphere client = this.getClient(request);
			JsonReader reader = new JsonReader();

			try {

				reponseDatastore = client.getData(requeteDatastore);
				reponseHote = client.getData(requeteHost);
				reponseVm = client.getData(requeteVm);

				ArrayList<DataStore> listeDatastore = reader.getListDataStore(reponseDatastore);
				ArrayList<Host> listeHost =	reader.getListHosts(reponseHote);
				ArrayList<VirtualMachine> listeVm = reader.getListVirtualMachine(reponseVm);
				ArrayList<Utilisateur> listeUser = ((DAOFactory)getServletContext().getAttribute("daofactory")).getUtilisateurDao().lister();

				request.setAttribute("listeDatastore", listeDatastore);
				request.setAttribute("listeHost", listeHost);
				request.getSession().setAttribute("listeVm", listeVm);
				request.getSession().setAttribute("listeUser", listeUser);
				
				
				// Liste des cours et referents
				ArrayList<Cour> listeCours = coursDaoImpl.lister(numServeur);
				ArrayList<Utilisateur> listeReferent = utilisateirDao.listerReferent();

				for (int i = 0 ; i < listeCours.size() ; i++) {

					Cour cours = listeCours.get(i);

					int nbUtilisateur = utilisateirDao.listerUtilisateurCours(cours).size();
					cours.setNbEtudiant(nbUtilisateur);
					int nbVm = virtualMachineDao.getVmCour(cours).size();
					cours.setNbVm(nbVm);
				}

				request.setAttribute("listeReferents", listeReferent);
				request.setAttribute("listeCours", listeCours);

			} catch (ExceptionConnectionVSphere e ) {

				request.setAttribute("erreur", e);
				jspOut="WEB-INF/pagesErreur/ErrorConnection.jsp";

			}catch (Exception e ) {
				e.printStackTrace();
				jspOut="WEB-INF/pagesErreur/ErrorConnection.jsp";

			}finally {
				RequestDispatcher dispat = request.getRequestDispatcher(jspOut);
				dispat.forward(request, response);
			}
		}
	}
}