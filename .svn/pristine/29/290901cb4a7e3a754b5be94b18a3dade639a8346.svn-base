package fr.eseo.cc3.dao._interface;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;

/**
 * UtilisateurDao définit les méthode implémenté dans UtilisateurDaoImpl
 */
public interface UtilisateurDao {

    /**
     * Créer un utilisateur sur la BDD
     * @param utilisateur récupère l'utilisateur qui doit être ajouté
     * @throws SQLException
     */
    void creer( Utilisateur utilisateur ) throws SQLException;
    
    /**
     * Met à jour un utilisateur
     * @param utilisateur récupère les nouvelles informations concernant l'utilisateur
     * @throws SQLException
     */
    void modifier( Utilisateur utilisateur) throws SQLException;
    
    /**
     * Supprime l'utilisateur passer en argument
     * @param utilisateur identifie quel utilisateur doit être supprimé
     * @throws SQLException
     */
    void supprimer( Utilisateur utilisateur) throws SQLException;

    /**
     * Trouve un utilisateur en fonction de son nomUtilisateur
     * @param nomUtilisateur le nom de l'utilisateur à trouver
     * @return l'utilisateur trouver dans un objet Utilisateur
     * @throws SQLException
     */
    Utilisateur trouver( String nomUtilisateur ) throws SQLException;
    
    /**
     * Liste les utilisateurs inscrit à un cours
     * @param cours définie le cours dont on veut les utilisateur inscrit
     * @return la liste des Utilisateur inscrit à ce cours
     * @throws SQLException
     */
    ArrayList<Utilisateur> listerUtilisateurCours(Cour cours) throws SQLException;
    
    /**
     * Récupère le referent d'un cours
     * @param cours dont on veut récupérer le referent
     * @return un objet Utilisateur contenant le referent du cours
     * @throws SQLException
     */
    ArrayList<Utilisateur> getReferent(Cour cours) throws SQLException;
   
    /**
     * Liste tous les utilisateurs
     * @return une liste d'utilisateur contenant tous les utilisateurs
     * @throws SQLException
     */
    ArrayList<Utilisateur> lister() throws SQLException;
    
    /**
     * Récupère les référents des cours auxquels l'utilisateur est inscrit
     * @param utilisateur dont on veut les référents des cours auxquelle il est inscrit
     * @return liste d'objet Utilisateur contenant les référérent
     * @throws SQLException
     */
    ArrayList<Utilisateur> getReferentUser(Utilisateur utilisateur) throws SQLException;
    
    /**
     * Récupère l'ensemble des référents
     * @return list d'objets Utilisateur contenant les référents
     * @throws SQLException
     */
    ArrayList<Utilisateur> listerReferent() throws SQLException;
    
    /**
     * Récupère l'utilisateur à qui appartient la vm
     * @param vm dont on veut l'utilisateur
     * @return l'utilisateur à qui appartient la vm
     * @throws SQLException
     */
    Utilisateur getUserVm(VirtualMachine vm) throws SQLException;
       
}
