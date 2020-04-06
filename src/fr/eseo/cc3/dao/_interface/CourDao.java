package fr.eseo.cc3.dao._interface;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;

/**
 * CourDao définit les méthode implémenté dans CourDaoImpl
 */
public interface CourDao {

	/**
	 * Créer un cours sur la BDD.
	 * @param cours récupère le cours qui va être créé.
	 * @throws SQLException lors d'un erreur de communication avec la BDD.
	 */
    void creer( Cour cour ) throws SQLException;
    
	/**
	 * Modifie un cours par le cours passer en argument.
	 * @param cours récupère la nouvelle version du cours.
	 * @throws SQLException lors d'un erreur de communication avec la BDD. 
	 */
    void modifier( Cour cour) throws SQLException;
    
	/**
	 * Supprime le cours passer en paramêtre selon son num.
	 * @param cours récupère le cours qui doit être supprimer
	 * @throws SQLException lors d'un erreur de communication avec la BDD. 
	 */
    void supprimer( Cour cour) throws SQLException;
    
	/**
	 * Créer un lien entre un cour et un référent.
	 * @param cours permet d'identifier le cours qui est concerné.
	 * @param referent permet d'identifier le referent qui est concercné.
	 * @throws SQLException lors d'un erreur de communication avec la BDD. 
	 */
    void setReferent( Cour cour, Utilisateur referent ) throws SQLException;
    
	/**
	 * Créer un lien entre un cour et un utilisateur.
	 * @param cours permet d'identifier le cours qui est concerné.
	 * @param utilisateur permet d'identifier l'utilisateur qui est concercné.
	 * @throws SQLException lors d'un erreur de communication avec la BDD. 
	 */
    void ajoutEtudiant( Cour cour, Utilisateur etudiant ) throws SQLException;
    
	/* 
	 * Supprime le lien entre un cours et un referent.
	 * @param cours permet d'identifier le cours qui est concerné.
	 * @param referent permet d'identifier le referent qui est concercné.
	 * @throws SQLException lors d'un erreur de communication avec la BDD. 
	 */
    void deleteReferent( Cour cour, Utilisateur referent ) throws SQLException;
    
	/** 
	 * Créer un lien entre une vm et un cours.
	 * @param cours permet d'identifier le cours qui est concerné.
	 * @param vm permet d'identifier la vm qui est concercné.
	 * @throws SQLException lors d'un erreur de communication avec la BDD.
	 */
    void ajoutVirtualMachine( Cour cour, VirtualMachine vm ) throws SQLException;
    
	/* 
	 * Supprime le lien entre un cours et un utilisateur.
	 * @param cours permet d'identifier le cours qui est concerné.
	 * @param utilisateur permet d'identifier l'utilisateur qui est concercné.
	 * @throws SQLException lors d'un erreur de communication avec la BDD. 
	 */
    void supressionEtudiant( Cour cour, Utilisateur etudiant ) throws SQLException;
    
	/**
	 * Supprime le lien entre une VM et un Cours.
	 * @param cours permet d'identifier le cours qui est concerné.
	 * @param vm permet d'identifier la vm qui est concercné.
	 * @throws SQLException lors d'un erreur de communication avec la BDD.
	 */
    void supressionVirtualMachine( Cour cour, VirtualMachine vm ) throws SQLException;

	/**
	 * Récupère un cours selon sont nom et le serveur sur le quel il est stocké.
	 * @param nomCours est le nom du cours que l'ont cherche.
	 * @param serveur le serveur sur lequel se trouve le cour chercher.
	 * @return le cours trouver selon le nom.
	 * @throws SQLException lors d'un erreur de communication avec la BDD.
	 */
    Cour trouver( String nomCour, int numServeur ) throws SQLException;
    
	/**
	 * Liste tout les cours présent sur un serveur.
	 * @param serveur sur le quel on veut récupèrer les cours.
	 * @return la liste des cours présent sur un serveur.
	 * @throws SQLException lors d'un erreur de communication avec la BDD.
	 */
    ArrayList<Cour> lister(int serveur) throws SQLException;
    
	/**
	 * Récupère la liste des cours dont l'utilisateur est referent.
	 * @param utilisateur récupère l'utilisateur concerné.
	 * @param serveur récupère le numéro du serveur sur lequel sont les cours.
	 * @return la liste de cours dont l'utilisateur est le référent.
	 * @throws SQLException lors d'un erreur de communication avec la BDD.
	 */
    ArrayList<Cour> getCourReferent(Utilisateur referent, int serveur) throws SQLException;

    /**
	 * Récupère la liste des cours auxquel un utilisateur est inscrit.
	 * @param utilisateur récupère l'utilisateur concerné.
	 * @param serveur récupère le numéro du serveur sur lequel sont les cours.
	 * @return la liste de cours auxquels est inscrits un utilisateur.
	 * @throws SQLException lors d'un erreur de communication avec la BDD.
	 */
    ArrayList<Cour> getCourUtilisateur(Utilisateur utilisateur, int serveur) throws SQLException;
    
	/**
	 * Récupère la liste des cours en spécifiant auxquels l'utitilisateur spécifié est inscrit.
	 * @param utilisateur identifie l'utilisateur concerné.
	 * @param serveur identifie sur quel serveur se trouve le cours.
	 * @return la liste de cours spécifiant les cours auxquels l'utilisateur est inscrit.
	 * @throws SQLException lors d'un erreur de communication avec la BDD.
	 */
    ArrayList<Cour> getCourUtilisateurNonInscrit(Utilisateur utilisateur, int serveur) throws SQLException;

}
