package fr.eseo.cc3.servlet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.eseo.cc3.clientapi.ClientVSphere;
import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao.bean.Utilisateur;

/**
 * Servlet implementation class ServletCommon
 */
@WebServlet("/ServletCommon")
public abstract class ServletCommon extends HttpServlet {
	static final long serialVersionUID = 1L;
	static final String ATTRIBUT_CLIENT="client";
	static final ResourceBundle CONFIGURATION_API = ResourceBundle.getBundle("configAPI");
	static final String PAGE_ERREUR_DROITS = "/WEB-INF/pagesErreur/ErrorDroitPage.jsp";


	ClientVSphere getClient(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (ClientVSphere) session.getAttribute(ATTRIBUT_CLIENT);
	}

	/**
	 * Permet de retiré toutes les élément stocker dans la session
	 * @param request 
	 * @see HttpServletRequest
	 */
	void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate(); 
	}

	/**
	 * Permet de d'attribuer le jsp ou le servlet de sortie, par defaut tout est renvoyer sur la page ErrorDroitPage.jsp
	 * @param administrateur : jsp ou servlet vers lequelle renvoyer la requête</br>dans le cas ou l'utilisateur est un administrateur.
	 * @param referent : jsp ou servlet vers lequelle renvoyer la requête</br>dans le cas ou l'utilisateur est un referent.
	 * @param etudiant : jsp ou servlet vers lequelle renvoyer la requête</br>dans le cas ou l'utilisateur est un etudiant.
	 * @param utilisateur Objet {@link Utilisateur} représentant la personne acutellement loggé
	 * @return le jsp ou le servlet le servlet doit rediriger la requête
	 */
	String checkRole(String administrateur, String referent, String etudiant, Utilisateur utilisateur) {
		String out;
		if(utilisateur==null) {
			out=PAGE_ERREUR_DROITS;
		}else {

			String role = utilisateur.getRole();
			

			if(role.equals("Administrateur")) {
				out=administrateur;
			}else if(role.equals("Referent") ) {
				out=referent;
			}else if (role.equals("Etudiant")) {
				out=etudiant;
			}else {
				out = PAGE_ERREUR_DROITS;
			}
		}
		return out;
	}
	
	ArrayList<String> getListOs(int numServeur) throws SQLException{
		return ((DAOFactory)getServletContext().getAttribute("daofactory")).getIsoDao().getListOsServeur(numServeur);
	}

}


