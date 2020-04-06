package fr.eseo.cc3.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.ServeurDao;
import fr.eseo.cc3.dao._interface.UtilisateurDao;
import fr.eseo.cc3.dao.bean.Serveur;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.hash.Hashage;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(urlPatterns = {"/ServletLogin"}, name = "Servlet4Login")
public class ServletLogin extends ServletCommon {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.clearSession(request);
		HttpSession session = request.getSession();
		
		DAOFactory dao =( (DAOFactory) getServletContext().getAttribute( "daofactory" ) );
		
		UtilisateurDao utilisateurDao =dao.getUtilisateurDao();
		ServeurDao serveurDao = dao.getServeurDao();

		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		int numServeur = Integer.parseInt(request.getParameter("serveur"));

		Boolean motDePasseValide = false;
		Utilisateur user = new Utilisateur();
		Serveur serveur = new Serveur();
		String jspOut = "Accueil";

		try {
			user = utilisateurDao.trouver(userName);
			serveur = serveurDao.trouverFull(numServeur);

			if(user != null) {
				motDePasseValide = Hashage.validatePassword(passWord, user.getHash());
			}

			if(motDePasseValide) {
				session.setAttribute("serveur", numServeur);
				session.setAttribute("utilisateur", user);
				session.setAttribute("client", new ClientVSphere(
						serveur.getIpServeur()
						, serveur.getIdentifiant()
						, serveur.getPassDecrypte()
						));

				if(user.getRole().equals("Administrateur")) {
					jspOut = "AccueilAdmin";
				}
				else if (user.getRole().equals("Etudiant") && serveur.isAfficher()) {
					jspOut = "AccueilEtudiant";
				}
				else if (user.getRole().equals("Referent")) {
					jspOut = "AccueilReferent";
				}
			}
		}
		catch(SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
			request.setAttribute("erreur", e);
			jspOut = "WEB-INF/pagesErreur/ErrorConnection.jsp";
		}

		finally {
			response.sendRedirect(jspOut);
		}
	}
}