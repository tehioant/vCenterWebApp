package fr.eseo.cc3.dao.bean;

import java.io.Serializable;

import fr.eseo.cc3.dao._interface.IsoDao;


/**
 *Objet représentant la table iso. Cette classe est utilisée par le Dao.
 *@see {@link IsoDao}
 *
 */
public class Iso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4939105106453624290L;
	
	/**
	 * Clef primaire de la table
	 */
	private int keyIso ;
	
	/**
	 * nom de l'iso
	 */
	private String nomIso;
	
	/**
	 * Chemin pour accèder à l'iso sur vsphere.
	 */
	private String chemin;
	
	/**
	 * Numéro du serveur où est stocker l'iso.
	 * @see {@link Serveur}
	 */
	private int numServeur;
	
	/**
	 * Nom de l'os pour lequel est prévu l'iso.
	 */
	private String os;
	
	
	/**
	 * Créer un objet Iso avec tous les champs présent dans la table.
	 * @param keyIso Clef primaire de la table
	 * @param nomIso nom de l'iso
	 * @param chemin Chemin pour accèder à l'iso sur vsphere.
	 * @param numServeur Numéro du serveur où est stocker l'iso.
	 * @param os Nom de l'os pour lequel est prévu l'iso.
	 */
	
	public Iso(int keyIso, String nomIso, String chemin, int numServeur, String os) {
		super();
		this.keyIso = keyIso;
		this.nomIso = nomIso;
		this.chemin = chemin;
		this.numServeur = numServeur;
		this.os=os;
	}
	
	
	/**
	 * @return l'attribut keyIso
	 */
	public int getKeyIso() {
		return keyIso;
	}


	/**
	 * @return l'attribut nomIso
	 */
	public String getNomIso() {
		return nomIso;
	}

	/**
	 * @return l'attribut chemin
	 */
	public String getChemin() {
		return chemin;
	}

	
	/**
	 * @return l'attribut numServeur
	 */
	public int getNumServeur() {
		return numServeur;
	}


	/**
	 * @return l'attribut os
	 */
	public String getOs() {
		return os;
	}

	
	@Override
	public String toString() {
		return "Iso [keyIso=" + keyIso + ", nomIso=" + nomIso + ", chemin=" + chemin + ", numServeur=" + numServeur
				+ ", os=" + os + "]";
	}




}
