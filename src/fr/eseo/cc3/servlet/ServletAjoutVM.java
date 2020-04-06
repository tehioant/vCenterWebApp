package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import edu.emory.mathcs.backport.java.util.Collections;
import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;
import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.DataStore;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletAjoutVM
 */
@WebServlet(urlPatterns = { "/AjoutVM" }, name = "ServletAjoutVM")
public class ServletAjoutVM extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		int numServeur = (int) request.getSession().getAttribute("serveur");
		String out=this.checkRole("AccueilAdmin","AccueilReferent" , "AccueilEtudiant", utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {

			Utilisateur userVm = new Utilisateur();
			JsonReader json = new JsonReader();

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(new Date());

			String requeteDatastore = CONFIGURATION_API.getString("GET_DATASTORE");
			ClientVSphere client = this.getClient(request);
			String reponseDatastore="";

			VirtualMachineDao virtualMachineDao =((DAOFactory)getServletContext().getAttribute("daofactory")).getVirtualMachineDao();

			try {

				reponseDatastore = client.getData(requeteDatastore);
				ArrayList<DataStore> listeDatastore = json.getListDataStore(reponseDatastore);

				JSONObject vmRef = new JSONObject(client.sendData("POST", CONFIGURATION_API.getString("GET_VM") 
						,json.createVMRequest(request.getParameter("name"), 
								request.getParameter("os"),
								this.getDatastoreMaxCapacity(listeDatastore),
								request.getParameter("config")
								)
						)
				);
				
				String requeteVm = CONFIGURATION_API.getString("GET_VM")+"/"+vmRef.getString("value");
				String responseVm = client.getData(requeteVm);
				
				fr.eseo.cc3.model.VirtualMachine virtualMachine = json.getVirtualMachineDetails(responseVm);
				
				VirtualMachine vm = new VirtualMachine(virtualMachine.getName(), vmRef.getString("value"), numServeur, date, date, date, "false");
				
				virtualMachineDao.creer(vm);

				/*
				Cour cour = new Cour(request.getParameter("cours"),0 ,0 ,numServeur);
				virtualMachineDao.getVmCour(cour);*/

				if(utilisateur.getRole().equals("Administrateur")) {
					virtualMachineDao.setUtilisateur(vm, userVm);
				} else if (utilisateur.getRole().equals("Etudiant")) {
					virtualMachineDao.setUtilisateur(vm, utilisateur);
				}

			} catch (ExceptionConnectionVSphere | IOException | SQLException e) {
				request.setAttribute("erreur", e);
				out="WEB-INF/pagesErreur/ErrorConnection.jsp";
			} finally {
				response.sendRedirect(out);
			}
		}
	}

	/**
	Permet de choisir le datastore automatiquement en fonction de la place disponible.
	@param pDs La liste des datastores du serveur.
	@return L'information datastore des Datastores.
	 */
	protected String getDatastoreMaxCapacity(ArrayList<DataStore> pDs) {
		ArrayList<String> datastore = new ArrayList<String>();
		ArrayList<Long> freeSpace = new ArrayList<Long>();
		for(DataStore ds : pDs) {
			datastore.add(ds.getDatastore());
			freeSpace.add(ds.getFreeSpace());
		}
		long freeSpaceMax = (long) Collections.max(freeSpace);
		int index = freeSpace.indexOf(freeSpaceMax);
		return datastore.get(index);
	}
}
