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
import javax.servlet.http.HttpSession;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.IsoDao;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.VirtualMachine;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ListIso
 */
@WebServlet("/ListIso")
public class ListIso extends ServletCommon {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("WEB-INF/Commun/selectionIso.jsp", "WEB-INF/Commun/selectionIso.jsp", "WEB-INF/Commun/selectionIso.jsp", utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {
			
			HttpSession session = request.getSession();
			ClientVSphere client = this.getClient(request);
			JsonReader reader = new JsonReader();
			ArrayList<String>listeIso = new ArrayList<>();
			
			DAOFactory dao = ((DAOFactory) getServletContext().getAttribute("daofactory" ));
			IsoDao isoDao = dao.getIsoDao();
			
			int numServeur = (int) session.getAttribute("serveur");
			String vm = request.getParameter("vmId");
			
			String reponseVm="";
			String requeteVm = CONFIGURATION_API.getString("GET_VM") +"/"+ vm;
			
			try {
				
				reponseVm = client.getData(requeteVm);
				VirtualMachine virtualMachine = reader.getVirtualMachineDetails(reponseVm);
				
				listeIso = isoDao.getListIsoOs(numServeur, virtualMachine.getGuestOS());
				request.setAttribute("listeIso", listeIso);
				request.setAttribute("nomVm", request.getParameter("nomVm"));
				request.setAttribute("action", request.getParameter("action"));
			}catch(SQLException | ExceptionConnectionVSphere e) {
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
