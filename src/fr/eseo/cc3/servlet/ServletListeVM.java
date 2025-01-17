package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.VirtualMachine;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * Servlet implementation class ServletListeVM
 */
@WebServlet("/ListeVM")
public class ServletListeVM extends ServletCommon {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListeVM() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String jspOut=this.checkRole("WEB-INF/Administrateur/listeCours.jsp", PAGE_ERREUR_DROITS, PAGE_ERREUR_DROITS, utilisateur);

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

			String reponseVm="";

			String requeteVm = CONFIGURATION_API.getString("GET_VM");

			ClientVSphere client = this.getClient(request);
			JsonReader reader = new JsonReader();

			try {

				reponseVm = client.getData(requeteVm);

				ArrayList<VirtualMachine> listeVm = reader.getListVirtualMachine(reponseVm);

				request.getSession().setAttribute("listeVm", listeVm);

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
