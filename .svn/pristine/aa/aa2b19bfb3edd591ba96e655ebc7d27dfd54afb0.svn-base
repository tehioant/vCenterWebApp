package fr.eseo.cc3.dao.bean;

import java.io.Serializable;

public class Serveur implements Serializable{

	private static final long serialVersionUID = 5145409292963150236L;
	
	/**
	 * Index du serveur dans la base de données
	 */
	private long numServeur;
	
	/**
	 * Nom du serveur pour l'identifier au près de l'utilisateur
	 */
	private String nomServeur;
	
	
	/**
	 * IP du serveur vsphere permet de s'y connecter
	 */
	private String ipServeur;
	
	
	/**
	 * Identifiant afin de se connecter sur le serveur
	 */
	private String identifiant;
	
	/**
	 * Mot de passe afin de s'identifier au serveur 
	 */
	private String passDecrypte;
	
	/**
	 * Définie si le serveur est accessible aux étudiants
	 */
	private boolean afficher;
	
	/**
	 * Créer un Serveur avec toutes les informations icompris sensible
	 * @param numServeur : Index du serveur dans la base de données
	 * @param nomServeur : Nom du serveur pour l'identifier au près de l'utilisateur
	 * @param ipServeur : IP du serveur vsphere permet de s'y connecter
	 * @param identifiant : Identifiant afin de se connecter sur le serveur
	 * @param passDecrypte : Mot de passe afin de s'identifier au serveur 
	 * @param afficher : Définie si le serveur est accessible aux étudiants
	 */
	public Serveur(long numServeur, String nomServeur, String ipServeur, String identifiant, String passDecrypte, boolean afficher) {
		this.numServeur = numServeur;
		this.nomServeur = nomServeur;
		this.ipServeur = ipServeur;
		this.identifiant = identifiant;
		this.passDecrypte = passDecrypte;
		this.afficher = afficher;
	}
	
	/*
	 * Créer un Serveur avec toutes les informations icompris sensible excepté l'index sur la BDD
	 * @param nomServeur : Nom du serveur pour l'identifier au près de l'utilisateur
	 * @param ipServeur : IP du serveur vsphere permet de s'y connecter
	 * @param identifiant : Identifiant afin de se connecter sur le serveur
	 * @param passDecrypte : Mot de passe afin de s'identifier au serveur 
	 * @param afficher : Définie si le serveur est accessible aux étudiants
	 */
	public Serveur(String nomServeur, String ipServeur, String identifiant, String passDecrypte, boolean afficher) {
		this(Long.valueOf(0), nomServeur, ipServeur, identifiant, passDecrypte, afficher);
	}
	
	/**
	 * Créer un Serveur avec toutes les informations sauf sensible
	 * @param numServeur : Index du serveur dans la base de données
	 * @param nomServeur : Nom du serveur pour l'identifier au près de l'utilisateur
	 * @param ipServeur : IP du serveur vsphere permet de s'y connecter
	 * @param afficher : Définie si le serveur est accessible aux étudiants
	 */
	public Serveur(long numServeur, String nomServeur, String ipServeur, boolean afficher) {
		this(numServeur, nomServeur, ipServeur, "", "", afficher);
	}
			
	/**
	 * Créer un objet Serveur vide
	 */
	public Serveur(){
		this(Long.valueOf(0), "", "", "", "", false);
	}

	/**
	 * Donne l'index du serveur
	 * @return l'index du serveur sur la BDD
	 */
	public long getNumServeur() {
		return numServeur;
	}

	/**
	 * Modifie l'index du serveur dans l'objet
	 * @param numServeur
	 */
	public void setNumServeur(long numServeur) {
		this.numServeur = numServeur;
	}

	/**
	 * Donne le nom du serveur
	 * @return le nom du serveur
	 */
	public String getNomServeur() {
		return nomServeur;
	}

	/**
	 * Modifie le nom du serveur
	 * @param nomServeur
	 */
	public void setNomServeur(String nomServeur) {
		this.nomServeur = nomServeur;
	}

	/**
	 * Donne l'IP du serveur
	 * @return l'IP du serveur
	 */
	public String getIpServeur() {
		return ipServeur;
	}

	/**
	 * Modifie l'IP du serveur
	 * @param ipServeur
	 */
	public void setIpServeur(String ipServeur) {
		this.ipServeur = ipServeur;
	}

	/**
	 * Donne l'identifiant pour se connecter au serveur
	 * @return l'identifiant pour se connecter au serveur
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * Modifie l'identifiant pour se connecter au serveur
	 * @param identifiant
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * Donne le mot de passe en claire du serveur
	 * @return le mot de passe en claire du serveur
	 */
	public String getPassDecrypte() {
		return passDecrypte;
	}

	/**
	 * Modifie le mot de passe d'accès au serveur
	 * @param passDecrypte
	 */
	public void setPassDecrypte(String passDecrypte) {
		this.passDecrypte = passDecrypte;
	}

	/**
	 * Dit si le serveur est accessible aux étudiants
	 * @return si le serveur est accessible aux étudiants
	 */
	public boolean isAfficher() {
		return afficher;
	}

	/**
	 * Modifie le fait que le serveur est accessible ou non aux étudiants
	 * @param afficher
	 */
	public void setAfficher(boolean afficher) {
		this.afficher = afficher;
	}

	@Override
	public String toString() {
		return "Serveur [numServeur=" + numServeur + ", nomServeur=" + nomServeur + ", ipServeur=" + ipServeur
				+ ", identifiant=" + identifiant + ", passDecrypte=" + passDecrypte + ", afficher=" + afficher + "]";
	}
}