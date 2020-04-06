package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class ServletCreateSnapshot
 * 
 * Recupere la machine virtuelle que l'on a selectionné
 * Creer un snapshot avec la methode de l'api vi (import vim25)
 * 
 */
@WebServlet("/CreateSnapshot")
public class ServletCreateSnapshot extends ServletCommon {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("AccueilAdmin","AccueilReferent" , "AccueilEtudiant", utilisateur);
		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {
		
		ServiceInstance si = new ServiceInstance(new URL(this.getClient(request).getServerIP()+"sdk"), this.getClient(request).getUser(), this.getClient(request).getPassword(), true);
		
		 Folder rootFolder = si.getRootFolder(); 
	     ManagedEntity[] mes = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine"); 
	     VirtualMachine vm = null;
	     
	     for(int i=0; i < mes.length; i++) {
		    	vm = (VirtualMachine) mes[i];
		    	if(vm.getName().equals(request.getParameter("vmName"))) {
		    		vm.createSnapshot_Task(request.getParameter("nom"), request.getParameter("description"), true, true);
		    	} 
	     }
		
	     si.getServerConnection().logout();
		}
		response.sendRedirect(out);
	}
}
