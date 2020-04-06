package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.net.URL;
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
import javax.servlet.http.HttpSession;

import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.VirtualMachineCloneSpec;
import com.vmware.vim25.VirtualMachineRelocateSpec;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.DataStore;
import fr.eseo.cc3.model.Host;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletClonerVM
 */
@WebServlet(urlPatterns = { "/ClonerVM" }, name = "ServletClonerVM")
public class ServletClonerVM extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletClonerVM() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("AccueilAdmin","AccueilReferent" , "AccueilEtudiant", utilisateur);
		
		ClientVSphere client = this.getClient(request);
		int serveur = (int) request.getSession().getAttribute("serveur");
		JsonReader json = new JsonReader();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		
		VirtualMachineDao vmDao = ((DAOFactory)getServletContext().getAttribute("daofactory")).getVirtualMachineDao();
		
		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );

		}else {
			String vmName = request.getParameter("vmName");
			String vmId = request.getParameter("vmId");
			
			fr.eseo.cc3.dao.bean.VirtualMachine vmToFind = new fr.eseo.cc3.dao.bean.VirtualMachine();
			try {
				vmToFind = vmDao.trouver(vmId, serveur);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			HttpSession session = request.getSession();
			ServiceInstance si = new ServiceInstance(new URL("https://"+client.getServerIP()+"/sdk"), this.getClient(request).getUser(), this.getClient(request).getPassword(), true);

			Folder rootFolder = si.getRootFolder(); 
			VirtualMachine vm = (VirtualMachine) new InventoryNavigator(rootFolder).searchManagedEntity("VirtualMachine", vmName);

			VirtualMachineCloneSpec cloneSpec = new VirtualMachineCloneSpec();
			VirtualMachineRelocateSpec relocSpec = new VirtualMachineRelocateSpec();
			cloneSpec.setLocation(relocSpec);

			relocSpec.setDatastore(vm.getDatastores()[0].getMOR());
			relocSpec.setPool(vm.getResourcePool().getMOR());

			cloneSpec.setPowerOn(false);
			cloneSpec.setTemplate(false);

			String clonedName = "";
			
			if(vmToFind.getTemplate().contains("true")) {
				clonedName = vmName + "_clone_"+utilisateur.getNom();
			} else {
				clonedName = vmName + "_clone";
			}
			
			Task cloneTask = vm.cloneVM_Task((Folder) vm.getParent(), clonedName, cloneSpec);
			
			String reponseVm="";
			String requeteVm = CONFIGURATION_API.getString("GET_VM");
			
			try {
				reponseVm = client.getData(requeteVm);
			} catch (ExceptionConnectionVSphere e1) {
				e1.printStackTrace();
			}
			
			ArrayList<fr.eseo.cc3.model.VirtualMachine> listeVm = json.getListVirtualMachine(reponseVm);
			
			String refVmCloned = "";
			for(fr.eseo.cc3.model.VirtualMachine lookingForVm : listeVm) {
				if(lookingForVm.getName().equals(clonedName)) {
					refVmCloned = lookingForVm.getVm();
				}
			}
			
			fr.eseo.cc3.dao.bean.VirtualMachine vmCloned = new fr.eseo.cc3.dao.bean.VirtualMachine(clonedName, refVmCloned, serveur, date, date, date, "false");
			
			try {
				vmDao.creer(vmCloned);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			try {
				cloneTask.waitForTask();
				VirtualMachine nVm = (VirtualMachine) new InventoryNavigator(rootFolder).searchManagedEntity("VirtualMachine", clonedName);
				nVm.powerOffVM_Task().waitForTask();
				
				if(utilisateur.getRole().equals("Etudiant")) {
					vmDao.setUtilisateur(vmCloned, utilisateur);
				}
			} catch (InterruptedException | SQLException e) {
				e.printStackTrace();
			}
			
			si.getServerConnection().logout();
			response.sendRedirect(out);
		}
	}
}
