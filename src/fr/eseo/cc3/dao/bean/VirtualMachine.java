package fr.eseo.cc3.dao.bean;

import java.io.Serializable;

/**
 * VirtualMachine implement l'objet qui stock les information d'un machine virtuelle
 */
public class VirtualMachine implements Serializable{
	
	private static final long serialVersionUID = 8479348489401693576L;
	
	/**
	 * Index de la vm dans la BDD
	 */
	private long numVm;
	
	/**
	 * Nom de la vm, il identifie une vm auprès des utilisateurs
	 */
	private String nomVm;
	
	/**
	 * Reference d'une vm dans vsphere
	 */
	private String refVm;
	
	/**
	 * Seruveur sur lequel se trouve la vm
	 */
	private int serveur;
	
	/**
	 * Date à laquelle la vm à été créée
	 */
	private String dateCreation;
	
	/**
	 * Date à laquelle la vm doit être rendu au prof
	 */
	private String dateRendu;
	
	/**
	 * Date à laquelle dernière connexion à la vm à été effectué
	 */
	private String derniereConnexion;
	
	/**
	 * True or False si une vm est une vm template
	 */
	private String template;
	

	/**
	 * Créer une VirtualMachine avec toutes les informations
	 * @param numVm : Index de la vm dans la BDD
	 * @param nomVm : Nom de la vm, il identifie une vm auprès des utilisateurs
	 * @param refVm : Reference d'une vm dans vsphere
	 * @param serveur : Seruveur sur lequel se trouve la vm
	 * @param dateCreation : Date à laquelle la vm à été créée
	 * @param dateRendu : Date à laquelle la vm doit être rendu au prof
	 * @param derniereConnexion : Date à laquelle dernière connexion à la vm à été effectué
	 * @param template : true or false si une vm est une vm template
	 */
	public VirtualMachine(long numVm, String nomVm, String refVm, int serveur, String dateCreation, String dateRendu,
			String derniereConnexion, String template) {
		super();
		this.numVm = numVm;
		this.nomVm = nomVm;
		this.refVm = refVm;
		this.serveur = serveur;
		this.dateCreation = dateCreation;
		this.dateRendu = dateRendu;
		this.derniereConnexion = derniereConnexion;
		this.template = template;
	}

	/**
	 * Créer une VirtualMachine avec toutes les informations sauf le numéro de vm
	 * @param nomVm : Nom de la vm, il identifie une vm auprès des utilisateurs
	 * @param refVm : Reference d'une vm dans vsphere
	 * @param serveur : Seruveur sur lequel se trouve la vm
	 * @param dateCreation : Date à laquelle la vm à été créée
	 * @param dateRendu : Date à laquelle la vm doit être rendu au prof
	 * @param derniereConnexion : Date à laquelle dernière connexion à la vm à été effectué
	 * @param template : true or false si une vm est une vm template
	 */
	public VirtualMachine(String nomVm, String refVm, int serveur, String dateCreation, String dateRendu,
			String derniereConnexion, String template) {
		super();
		this.nomVm = nomVm;
		this.refVm = refVm;
		this.serveur = serveur;
		this.dateCreation = dateCreation;
		this.dateRendu = dateRendu;
		this.derniereConnexion = derniereConnexion;
		this.template = template;
	}

	/**
	 * Créer un objet VirtualMachine vide
	 */
	public VirtualMachine(){
		this.numVm = 0;
		this.nomVm = "";
		this.refVm = "";
		this.serveur = 0;
		this.dateCreation = "";
		this.dateRendu = "";
		this.derniereConnexion = "";
		this.template = "false";
	}
	
	/**
	 * Donne l'index de la vm dans la BDD
	 * @return l'index de la vm dans la BDD
	 */
	public long getNumVm() {
		return numVm;
	}

	/**
	 * Modifie l'index de la vm dans la BDD
	 * @param numVm
	 */
	public void setNumVm(long numVm) {
		this.numVm = numVm;
	}

	/**
	 * Donne le nom de la vm
	 * @return le nom de la vm
	 */
	public String getNomVm() {
		return nomVm;
	}

	/**
	 * Modifie le nom de la vm
	 * @param nomVm
	 */
	public void setNomVm(String nomVm) {
		this.nomVm = nomVm;
	}

	/**
	 * Donne la référence de la vm sur Vsphere
	 * @return la référence de la vm sur Vsphere
	 */
	public String getRefVm() {
		return refVm;
	}

	/**
	 * Modifie la référence de l'objet vm
	 * @param refVm
	 */
	public void setRefVm(String refVm) {
		this.refVm = refVm;
	}

	/**
	 * Donne le serveur sur lequel la vm est stocké
	 * @return le serveur sur lequel la vm est stocké
	 */
	public int getServeur() {
		return serveur;
	}

	/**
	 * Modifie le serveur sur l'objet vm
	 * @param serveur
	 */
	public void setServeur(int serveur) {
		this.serveur = serveur;
	}

	/**
	 * Donne la date de création de la vm
	 * @return la date de création de la vm
	 */
	public String getDateCreation() {
		return dateCreation;
	}

	/**
	 * Modifie la date de création de la vm
	 * @param dateCreation
	 */
	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Donne la date de rendu de la vm
	 * @return la date de rendu de la vm
	 */
	public String getDateRendu() {
		return dateRendu;
	}

	/**
	 * Modifie la date de rendu de la vm
	 * @param dateRendu
	 */
	public void setDateRendu(String dateRendu) {
		this.dateRendu = dateRendu;
	}

	/**
	 * Donne la date de derniere connexion a la vm
	 * @return la date de derniere connexion a la vm
	 */
	public String getDerniereConnexion() {
		return derniereConnexion;
	}

	/**
	 * Modifie la date de derniere connexion a la vm
	 * @param derniereConnexion
	 */
	public void setDerniereConnexion(String derniereConnexion) {
		this.derniereConnexion = derniereConnexion;
	}

	/** 
	 * Dit si la vm est une vm template
	 * @return si la vm est une vm template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * Modifie si la vm est une vm template
	 * @param template
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	@Override
	public String toString() {
		return "VirtualMachine [numVm=" + numVm + ", nomVm=" + nomVm + ", refVm=" + refVm + ", serveur=" + serveur
				+ ", dateCreation=" + dateCreation + ", dateRendu=" + dateRendu + ", derniereConnexion="
				+ derniereConnexion + ", template=" + template + "]";
	}
}