package fr.eseo.cc3.dao._interface;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;

/**
 * VirtualMachineDao définit les méthode implémenté dans VirtualMachineDaoImpl
 */
public interface VirtualMachineDao {
	
	/**
	 * Créer une vm
	 * @param vm récupère la vm qui doit être créer
	 * @throws SQLException
	 */
	void creer( VirtualMachine vm ) throws SQLException;
    
    /**
     * Modifie une vm
     * @param vm récupère les nouvelles informations concernant la vm à modifier
     * @throws SQLException
     */
    void modifier( VirtualMachine vm ) throws SQLException;
    
    /**
     * Supprime la vm passer en argument
     * @param refVm définit quelle vm doit être supprimé
     * @param serveur définit sur quel serveur se trouve la vm à supprimer
     * @throws SQLException
     */
    void supprimer( String refVm, int serveur ) throws SQLException;
    
    /**
     * Définit le propriétaire d'une vm
     * @param vm définit la vm qui doit recevoir un propriétaire
     * @param utilisateur définit le propriétaire de la vm
     * @throws SQLException
     */
    void setUtilisateur( VirtualMachine vm, Utilisateur utilisateur ) throws SQLException;
    
    /**
     * Supprime le propriétaire d'un utilisateur
     * @param vm définit la vm dont le l'utilisateur doit être supprimé
     * @param utilisateur définit le propriétaire de la vm qui doit être supprimé
     * @throws SQLException
     */
    void deleteUtilisateur( VirtualMachine vm, Utilisateur utilisateur ) throws SQLException;

    /**
     * Trouve une vm selon sa reference et le serveur sur lequel elle se trouve
     * @param refVm 
     * @param serveur
     * @return
     * @throws SQLException
     */
    VirtualMachine trouver( String refVm, int serveur ) throws SQLException;
    
    /**
     * Liste toutes les vm d'un serveur donné
     * @param serveur définit le serveur ou ce trouve les vm qui doivent être listé
     * @return une liste de vm
     * @throws SQLException
     */
    ArrayList<VirtualMachine> lister(int serveur) throws SQLException;
    
    /**
     * Liste les vm dites "Template"
     * @param serveur définit le serveur sur le quel se trouve les template que l'on veut lister
     * @return la liste des vm "Template"
     * @throws SQLException
     */
    ArrayList<VirtualMachine> listeTemplate(int serveur) throws SQLException;
    
    /**
     * Récupère les vm d'un utilisateur
     * @param utilisateur définit l'utilisateur dont on veut récupérer les vm
     * @param serveur définit sur quel serveur sont stocké les vm que l'ont veut récupérer
     * @return la liste des vm d'un utilisateur
     * @throws SQLException
     */
    ArrayList<VirtualMachine> getUserVm(Utilisateur utilisateur, int serveur) throws SQLException;
    
    /**
     * Récupère l'ensemble des vm d'un cours
     * @param cour définit le cours dont on veut les vm
     * @return une liste de vm
     * @throws SQLException
     */
    ArrayList<VirtualMachine> getVmCour(Cour cour) throws SQLException;
}
