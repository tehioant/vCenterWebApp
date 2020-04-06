package fr.eseo.cc3.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vmware.vim25.InvalidState;
import com.vmware.vim25.VirtualMachineTicket;
import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class Console
 */
@WebServlet("/Console")
public class Console extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String outErreur=this.checkRole("AccueilAdmin","AccueilReferent" , "AccueilEtudiant", utilisateur);
		
		if(outErreur.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(outErreur).forward( request, response );
		}else {
			boolean erreur=true;
			
			try {
			ClientVSphere client = this.getClient(request);
			VirtualMachineTicket ticket = client.getVirtualMachineTicket(request.getParameter("vmName"));
			request.setAttribute("ticket", ticket);
			erreur=false;
			}catch (IOException e) {
				erreur=true;
			}
			
			request.setAttribute("erreur", erreur);
			request.getRequestDispatcher("WEB-INF/Commun/console.jsp").forward(request, response);
		}
	}

}
