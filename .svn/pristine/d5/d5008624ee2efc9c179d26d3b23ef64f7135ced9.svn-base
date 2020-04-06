package fr.eseo.cc3.dao._interface;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.eseo.cc3.dao.bean.Iso;

/**
 * Cette interface permet d'implementer les méthodes d'accès à la base de donnée
 */
public interface IsoDao {
	 

	/**
	 * Permet de récupèrer la liste des Os disponible pour un serveur donné
	 * @param numServeur le numéro du serveur auquel on est connecté
	 * @return la liste des OS disponible
	 * @throws SQLException lorsque la requête ne s'exécute pas correctement
	 */
	ArrayList<String> getListOsServeur(int numServeur) throws SQLException;
	
	/**
	 * Permet de récupèrer la liste de tout les iso disponible pour un os et un serveur donné
	 * @param numServeur le numéro du serveur auquel on est connecté
	 * @param os le nom de l'os dont on veut récupèrer les iso
	 * @return la liste de tout les iso disponibles
	 * @throws SQLException lorsque la requête ne s'exécute pas correctement
	 */
	ArrayList<String> getListIsoOs(int numServeur, String os) throws SQLException;
	
	/**
	 * Permet de récupèrer un objet iso dans la bdd
	 * @param numServeur numéro du serveur sur lequelle est stocké l'iso
	 * @param nomIso nom de l'iso
	 * @param nomDatastore nom du datastore sur lequelle est sotcké l'iso
	 * @return un {@link Iso}
	 * @throws SQLException lorsque la requête ne s'exécute pas correctement
	 */
	Iso getIso(int numServeur,String nomIso,String nomDatastore) throws SQLException;
}
