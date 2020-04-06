package fr.eseo.cc3.dao.bean;

import java.io.Serializable;

/**
 * Définit l'objet Cour qui stock les informations concernants les cours
 */
public class Cour implements Serializable{
	
	/**
	 * Permet de rendre la classe sérialisable
	 */
	private static final long serialVersionUID = -1046718478769756641L;
	
	/**
	 * Numéro de cours dans la BDD, permet d'identifier un cours
	 */
	private long numCour;
	
	/**
	 * Nom d'un cours, identifie un cour pour l'utilisateur
	 */
	private String nomCour;
	
	/**
	 * Nombre d'étudiant inscrit à un cours
	 */
	private int nbEtudiant;
	
	/**
	 * Nombre de vm inscrite à un cours
	 */
	private int nbVm;
	
	/**
	 * Serveur sur le quel est inscrit le cours
	 */
	private int serveur;
	
	/**
	 * Si l'utilisateur est inscrit au cours
	 */
	private boolean inscrit;
	
	/**
	 * Créer un cours avec toutes les informations le concernant
	 * @param numCour : Numéro de cours dans la BDD, permet d'identifier un cours
	 * @param nomCour : Nom d'un cours, identifie un cour pour l'utilisateur
	 * @param nbEtudiant : Nombre d'étudiant inscrit à un cours
	 * @param nbVm : Nombre de vm inscrite à un cours
	 * @param serveur : Si l'utilisateur est inscrit au cours
	 */
	public Cour(long numCour, String nomCour, int nbEtudiant, int nbVm, int serveur) {
		super();
		this.numCour = numCour;
		this.nomCour = nomCour;
		this.nbEtudiant = nbEtudiant;
		this.nbVm = nbVm;
		this.serveur = serveur;
	}

	/**
	 * Créer un cours avec toutes les informations le concernant sauf le numéro du cours
	 * @param nomCour : Nom d'un cours, identifie un cour pour l'utilisateur
	 * @param nbEtudiant : Nombre d'étudiant inscrit à un cours
	 * @param nbVm : Nombre de vm inscrite à un cours
	 * @param serveur : Si l'utilisateur est inscrit au cours
	 */
	public Cour(String nomCour, int nbEtudiant, int nbVm, int serveur) {
		this(Long.valueOf(0), nomCour, nbEtudiant, nbVm, serveur);
	}
	
	/**
	 * Créer un cours basique
	 * @param nomCour : Nom d'un cours, identifie un cour pour l'utilisateur
	 * @param serveur : Si l'utilisateur est inscrit au cours
	 */
	public Cour(String nomCour, int serveur) {
		this(Long.valueOf(0), nomCour, 0, 0, serveur);
	}
	
	/**
	 * Créer un cours vide
	 */
	public Cour() {
		this(Long.valueOf(0), "", 0, 0, 0);
	}

	/**
	 * Donne le numéro du cours
	 * @return le numéro du cours
	 */
	public long getNumCour() {
		return numCour;
	}

	/**
	 * Modifie le numéro du cours
	 * @param numCour
	 */
	public void setNumCour(long numCour) {
		this.numCour = numCour;
	}

	/**
	 * Donne le nom du cours
	 * @return le nom du cours
	 */
	public String getNomCour() {
		return nomCour;
	}

	/**
	 * Modifie le nom du cours
	 * @param nomCour
	 */
	public void setNomCour(String nomCour) {
		this.nomCour = nomCour;
	}

	/**
	 * Donne le nombre d'étudiant inscrit au cours
	 * @return le nombre d'étudiant inscrit au cours
	 */
	public int getNbEtudiant() {
		return nbEtudiant;
	}

	/**
	 * Modifie le nombre d'étudiant inscrit au cours
	 * @param nbEtudiant
	 */
	public void setNbEtudiant(int nbEtudiant) {
		this.nbEtudiant = nbEtudiant;
	}

	/**
	 * Donne le nombre de vm inscrite au cours
	 * @return le nombre de vm inscrite au cours
	 */
	public int getNbVm() {
		return nbVm;
	}

	/**
	 * Modifie le nombre de vm inscrite au cours
	 * @param nbVm
	 */
	public void setNbVm(int nbVm) {
		this.nbVm = nbVm;
	}

	/**
	 * Donne le numéro de serveur sur le quel le cours est enregistré
	 * @return le numéro de serveur sur le quel le cours est enregistré
	 */
	public int getServeur() {
		return serveur;
	}

	/**
	 * Modifie le numéro du serveur sur le quel le cours est enregistré
	 * @param serveur
	 */
	public void setServeur(int serveur) {
		this.serveur = serveur;
	}

	/**
	 * Dit si l'étudiant est inscrit au cours
	 * @return si l'étudiant est inscrit au cours
	 */
	public boolean isInscrit() {
		return inscrit;
	}

	/**
	 * Modifie le fait qu'un étudiant est inscrit au cours
	 * @param inscrit
	 */
	public void setInscrit(boolean inscrit) {
		this.inscrit = inscrit;
	}

	@Override
	public String toString() {
		return "Cour [numCour=" + numCour + ", nomCour=" + nomCour + ", nbEtudiant=" + nbEtudiant + ", nbVm=" + nbVm
				+ ", serveur=" + serveur + "]";
	}
	

}
