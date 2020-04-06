package fr.eseo.cc3.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.CourDao;

import fr.eseo.cc3.dao.bean.VirtualMachine;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import static fr.eseo.cc3.dao.DAOUtilitaire.*;

/**
 * CourDaoImpl implémente les fonctions permettant de récupérer les informations liées à un cours.
 */
public class CourDaoImpl implements CourDao {
	private DAOFactory daoFactory;

	/**
	 * Constructeur permettant de récupérer le daoFactory
	 * @param daoFactory 
	 */
	public CourDaoImpl( DAOFactory daoFactory ) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_SELECT_PAR_NOMUTILISATEUR = "SELECT numCours, nomCours, nbEtudiant, nbVm, serveur FROM Cours WHERE nomCours = ? AND serveur = ?";

	@Override
	public Cour trouver(String nomCours, int serveur) throws SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cour cour = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_PAR_NOMUTILISATEUR, false, nomCours, serveur);
			resultSet = preparedStatement.executeQuery();
			if ( resultSet.next() ) {
				cour = map( resultSet );
			}
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return cour;
	}


	/**
	 * Récupère les cours d'un serveur.
	 */
	private static final String SQL_SELECT_ALL = "SELECT numCours, nomCours, nbEtudiant, nbVm, serveur FROM Cours WHERE serveur = ?";

	@Override
	public ArrayList<Cour> lister(int serveur) throws SQLException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Cour> cours = new ArrayList<Cour>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_ALL, false, serveur);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next() ) {
				cours.add(map( resultSet ));
			}
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return cours;
	}

	/**
	 * Récupère les cours d'un utilisateur, referent ou utilisateur.
	 */
	private static final String SQL_SELECT_COUR_BY_UTILISATEUR = "SELECT numCours, nomCours, nbEtudiant, nbVm, serveur FROM Cours WHERE numCours IN (SELECT cours FROM Assiste WHERE user = ?) AND serveur = ?";
	
	@Override
	public ArrayList<Cour> getCourReferent(Utilisateur referent, int serveur) throws SQLException{
		return getCourUtilisateur(referent, serveur);
	}    

	@Override
	public ArrayList<Cour> getCourUtilisateur(Utilisateur utilisateur, int serveur) throws SQLException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Cour> cours = new ArrayList<Cour>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_COUR_BY_UTILISATEUR, false, utilisateur.getNumUtilisateur(), serveur);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next() ) {
				cours.add(map( resultSet ));
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return cours;
	}


	private static final String SQL_INSERT_COUR = "INSERT INTO Cours (nomCours, nbEtudiant, nbVm, serveur) VALUES (?, ?, ?, ?)";

	@Override
	public void creer( Cour cours ) throws SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_COUR, true, cours.getNomCour(), cours.getNbEtudiant(), cours.getNbVm(), cours.getServeur());
			int statut = preparedStatement.executeUpdate();
			if ( statut == 0 ) {
				throw new SQLException( "Échec de la création du cour, aucune ligne ajoutée dans la table." );
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if ( valeursAutoGenerees.next() ) {
				cours.setNumCour(valeursAutoGenerees.getLong(1));
			} else {
				throw new SQLException( "Échec de la création du cour en base, aucun ID auto-généré retourné." );
			}
		} finally {
			fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
		}
	}

	/**
	 * Supprime un cours
	 */
	private static final String SQL_DELETE_COUR = "DELETE FROM Cours WHERE numCours = ?";

	@Override
	public void supprimer( Cour cours ) throws SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_COUR, true, cours.getNumCour());
			resultSet = preparedStatement.executeQuery();
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
	}

	
	/**
	 * Modifie l'entierreter d'un cours.
	 */
	private static final String SQL_UPDATE = "UPDATE Cours SET numCours = ?, nomCours = ?, nbEtudiant = ?, nbVm = ?, serveur = ? WHERE numCour = ? AND serveur = ?";

	@Override
	public void modifier( Cour cours ) throws SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_UPDATE, true, cours.getNumCour(), cours.getNomCour(), cours.getNbEtudiant(), cours.getNbVm(), cours.getServeur(), cours.getNumCour(), cours.getServeur() );
			int statut = preparedStatement.executeUpdate();
			if ( statut == 0 ) {
				throw new SQLException( "Échec de la mise a jour du cours, aucune ligne mise a jour dans la table." );
			}
		} finally {
			fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
		}
	}


	/**
	 * Créer un lien entre un cour et un référent.
	 */
	private static final String SQL_INSERT_ETUDIANT = "INSERT INTO Assiste (cours, user) VALUES (?, ?)";

	@Override
	public void setReferent( Cour cours, Utilisateur referent ) throws SQLException{
		ajoutEtudiant(cours, referent);
	}

	@Override
	public void ajoutEtudiant( Cour cours, Utilisateur etudiant ) throws SQLException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_ETUDIANT, true, cours.getNumCour(), etudiant.getNumUtilisateur());
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new SQLException( "Échec de la créationd du referent, aucune ligne ajoutée dans la table." );
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();

		} finally {
			fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
		}
	}


	/**
	 * Supprime le lien entre un cour un utilisateur
	 */
	private static final String SQL_DELETE_ETUDIANT = "DELETE FROM Assiste WHERE cour = ? and user = ?";
	
	@Override
	public void deleteReferent( Cour cours, Utilisateur referent) throws SQLException {
		supressionEtudiant(cours, referent);
	}

	@Override
	public void supressionEtudiant( Cour cours, Utilisateur etudiant) throws SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_ETUDIANT, true, cours.getNumCour(), etudiant.getNumUtilisateur());
			resultSet = preparedStatement.executeQuery();
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
	}

	/**
	 * Récupère la liste des cours en spécifiant auxquels l'utitilisateur spécifié est inscrit.
	 */
	private static final String SQL_SELECT_COURS_NON_INSCRIT = "SELECT c.numCours, c.nomCours, c.nbEtudiant, c.nbVm, c.serveur, u.numUtilisateur FROM Cours c LEFT OUTER JOIN Assiste a ON a.cours = c.numCours AND c.serveur = ? LEFT OUTER JOIN Utilisateur u on u.numUtilisateur = a.user AND u.numUtilisateur = ?";

	@Override
	public ArrayList<Cour> getCourUtilisateurNonInscrit(Utilisateur utilisateur, int serveur) throws SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Cour> listeCours = new ArrayList<Cour>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_SELECT_COURS_NON_INSCRIT, false, serveur, utilisateur.getNumUtilisateur());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next() ) {
				Cour cours = map(resultSet);
				cours.setInscrit(resultSet.getString("numUtilisateur")==null?false:true);
				listeCours.add(cours);
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
		return listeCours;
	}


	/**
	 * Créer une lien entre une VM et un Cours.
	 */
	private static final String SQL_INSERT_VM = "INSERT INTO VmToCour (cours, machineVirtuel) VALUES (?, ?)";

	@Override
	public void ajoutVirtualMachine( Cour cours, VirtualMachine vm ) throws SQLException{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT_VM, true, cours.getNumCour(), vm.getNumVm());
			int statut = preparedStatement.executeUpdate();
			if ( statut == 0 ) {
				throw new SQLException( "Échec de la créationd du referent, aucune ligne ajoutée dans la table." );
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
		} finally {
			fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
		}
	}


	/**
	 * Requète supprimant le lien entre une VM et un Cours.
	 */
	private static final String SQL_DELETE_VMTOCOURS = "DELETE FROM VmToCour WHERE cours = ? and machineVirtuel = ?";


	@Override
	public void supressionVirtualMachine( Cour cours, VirtualMachine vm ) throws SQLException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_VMTOCOURS, true, cours.getNumCour(), vm.getNumVm());
			resultSet = preparedStatement.executeQuery();
		} finally {
			fermeturesSilencieuses( resultSet, preparedStatement, connexion );
		}
	}


	/**
	 * Transforme le retour de la BDD en objet Cour.
	 * @param resultSet Récupère la réponse de la base de données.
	 * @return Un objet Cour contenant les informations récupéré.
	 * @throws SQLException dans le cas ou une informations n'est pas récupérer correctement.
	 */
	private static Cour map( ResultSet resultSet ) throws SQLException {
		Cour cour = new Cour(resultSet.getLong("numCours"), resultSet.getString("nomCours"), resultSet.getInt("nbEtudiant"), resultSet.getInt("nbVm"), resultSet.getInt("serveur"));
		return cour;
	}
}