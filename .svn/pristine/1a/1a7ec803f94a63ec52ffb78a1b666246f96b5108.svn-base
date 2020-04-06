package fr.eseo.cc3.dao._interface;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.eseo.cc3.dao.bean.Serveur;


/**
 * ServeurDao définit les méthode implémenté dans ServeurDaoImpl
 */
public interface ServeurDao {

    /**
     * Créer un serveur.
     * @param serveur récupère le serveur à créer.
     * @throws SQLException lors d'un erreur de communication avec la BDD.
     */
	void creer(Serveur serveur) throws SQLException;

    /**
     * Modifie les informations non sensible d'un serveur.
     * @param serveur récupère les informations a mettre a jour.
     * @throws SQLException lors d'un erreur de communication avec la BDD.
     */
	void modifierSemi(Serveur serveur) throws SQLException;

    /**
     * Modifie entièrement un serveur.
     * @param serveur récupère les informations a mettre a jour.
     * @throws SQLException lors d'un erreur de communication avec la BDD.
     */
	void modifierFull(Serveur serveur) throws SQLException;

    /**
     * Supprime un serveur.
     * @param serveur récupère le serveur qui doit être supprimé.
     * @throws SQLException lors d'un erreur de communication avec la BDD.
     */
	void supprimer(Serveur serveur) throws SQLException;
	
    /**
     * Récupère les information non sensible d'un serveur selon son nom.
     * @param nomServeur contient le nom du serveur dont on veut récupèrer les informations.
     * @return serveur retourne les informations non sensible du serveur trouver.
     * @throws SQLException lors d'un erreur de communication avec la BDD.
     */
	Serveur trouverSemi(String nomServeur) throws SQLException;
	   
    /**
     * Récupère les information non sensible d'un serveur selon son numéro dans la BDD.
     * @param numServeur contient le numéro du serveur dont on veut récupèrer les informations.
     * @return serveur retourne les informations non sensible du serveur trouver.
     * @throws SQLException lors d'un erreur de communication avec la BDD.
     */
	Serveur trouverSemi(int numServeur) throws SQLException;
    
    /**
     * Récupère les information sensible d'un serveur selon son numéro dans la BDD.
     * @param numServeur contient le numéro du serveur dont on veut récupèrer les informations.
     * @return serveur retourne les informations sensible du serveur trouver.
     * @throws SQLException lors d'un erreur de communication avec la BDD.
     */
	Serveur trouverFull(int numServeur) throws SQLException;
    
    /** 
     * Liste tous les serveurs éxistant.
     * @return la liste de serveur.
     * @throws SQLException lors d'un erreur de communication avec la BDD.
     */
	ArrayList<Serveur> lister() throws SQLException;
}
