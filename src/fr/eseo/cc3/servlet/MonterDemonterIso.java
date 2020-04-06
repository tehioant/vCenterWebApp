package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.IsoDao;
import fr.eseo.cc3.dao.bean.Iso;
import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class monterIso
 */
@WebServlet(urlPatterns = {"/MonterDemonterIso"}, name = "MonterDemonterIso")
public class MonterDemonterIso extends ServletCommon {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("AccueilAdmin", "AccueilReferent", "AccueilEtudiant", utilisateur);

		if(out.equals(PAGE_ERREUR_DROITS)) {
			this.getServletContext().getRequestDispatcher(out).forward( request, response );
		}else {
			
			DAOFactory dao = ((DAOFactory) getServletContext().getAttribute("daofactory" ));
			IsoDao isoDao = dao.getIsoDao();
			HttpSession session = request.getSession();
			ClientVSphere client = this.getClient(request);
			
			int numServeur = (int) session.getAttribute("serveur");
			String nomIso = request.getParameter("nomIso");
			String nomVm = request.getParameter("nomVm");
			String action = request.getParameter("action");
			
			String nomDatastore = client.getVmDatastore(nomVm).getName();
			Iso iso =null;
			
			try {
				iso = isoDao.getIso(numServeur, nomIso, nomDatastore);
				if(action.equals("1")) {
					client.mountIso(nomVm, iso.getChemin());
				}else if (action.equals("0")) {
					client.unmountIso(nomVm, iso.getChemin());
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException(e);
			} catch (SQLException | RuntimeException e) {
				e.printStackTrace();
			}finally {
				response.sendRedirect(out);
			}



		}
	}

}
