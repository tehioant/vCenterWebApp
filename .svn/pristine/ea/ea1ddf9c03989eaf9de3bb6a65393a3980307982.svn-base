package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vmware.vim25.VirtualMachineCloneSpec;
import com.vmware.vim25.VirtualMachineRelocateSpec;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.Task;
import com.vmware.vim25.mo.VirtualMachine;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class ServletMarquerTemplate
 */
@WebServlet(urlPatterns = { "/MarquerTemplate" }, name = "ServletMarquerTemplate")
public class ServletMarquerTemplate extends ServletCommon {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMarquerTemplate() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("AccueilAdmin","AccueilReferent" , "AccueilEtudiant", utilisateur);
		int serveur = (int) request.getSession().getAttribute("serveur");
		
		ClientVSphere client = this.getClient(request);
		
		ArrayList<fr.eseo.cc3.dao.bean.VirtualMachine> vmListe = null;
		
		VirtualMachineDao vmDao = ((DAOFactory)getServletContext().getAttribute("daofactory")).getVirtualMachineDao();
		
		try {
			vmListe = (ArrayList<fr.eseo.cc3.dao.bean.VirtualMachine>) ((DAOFactory)getServletContext().getAttribute("daofactory")).getVirtualMachineDao().lister(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );

		}else {
			String vmName = request.getParameter("vmName");
			String vmId = request.getParameter("vmId");
			
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

			String clonedName = vmName + "_template";

			Task cloneTask = vm.cloneVM_Task((Folder) vm.getParent(), clonedName, cloneSpec);
			
			try {
				cloneTask.waitForTask();
				VirtualMachine nVm = (VirtualMachine) new InventoryNavigator(rootFolder).searchManagedEntity("VirtualMachine", clonedName);
				nVm.powerOffVM_Task().waitForTask();
				nVm.markAsTemplate();
				
				fr.eseo.cc3.dao.bean.VirtualMachine vmAModifier = vmDao.trouver(vmId, serveur);

				vmAModifier.setTemplate("true");
				((DAOFactory)getServletContext().getAttribute("daofactory")).getVirtualMachineDao().modifier(vmAModifier);
				
			} catch (InterruptedException | SQLException e) {
				e.printStackTrace();
			}
			
			si.getServerConnection().logout();
			response.sendRedirect(out);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
