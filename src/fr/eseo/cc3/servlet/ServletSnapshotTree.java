package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vmware.vim25.VirtualMachineSnapshotTree;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class ServletCreateSnapshot
 */
@WebServlet("/SnapshotTree")
public class ServletSnapshotTree extends ServletCommon {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("WEB-INF/Administrateur/snapshot.jsp", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);
		
		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {
		
		HttpSession session = request.getSession();
		ServiceInstance si = new ServiceInstance(new URL("https://192.168.4.42/sdk"), this.getClient(request).getUser(), this.getClient(request).getPassword(), true);
			
		     
		 Folder rootFolder = si.getRootFolder(); 
	     ManagedEntity[] mes = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine"); 
	     VirtualMachine vm = null;
	     
	     System.out.println(request.getParameter("vmName"));
	     
	     VirtualMachineSnapshotTree[] SnapList = null;
	     
	     for(int i=0; i < mes.length; i++) {
		    	vm = (VirtualMachine) mes[i];
		    	if(vm.getName().equals(request.getParameter("vmName"))) {
		    		try {
		    			SnapList = vm.getSnapshot().getRootSnapshotList();
		    		}
		    		catch(NullPointerException e){
		    			SnapList = null; 
		    		}
		    		
		    		//save in session
		    		session.setAttribute("SnaphotTree", SnapList);
		    	} 
	     }
		
	     si.getServerConnection().logout();
		}
		RequestDispatcher dispat = request.getRequestDispatcher(out);
		dispat.forward(request, response);
	}
}
