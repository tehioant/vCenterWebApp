package fr.eseo.cc3.dao.bean;

import java.io.Serializable;

/**
 * Définit l'objet Utilisateur qui stock les informations concernant les utilisateurs
 */
public class Utilisateur implements Serializable{
	
	private static final long serialVersionUID = 4253486176237242483L;
	
	/**
	 * Index de l'utilisateur sur la base de données
	 */
	private long numUtilisateur;
	
	/**
	 * Nom de l'utilisateur avec lequel il se connecte
	 */
	private String nomUtilisateur;
	
	/**
	 * Nom de famille de l'utilisateur
	 */
	private String nom;
	
	/**
	 * Hash du mot de passe de l'utilisateur
	 */
	private String hash;
	
	/**
	 * Prénom de l'utilisateur
	 */
	private String prenom;
	
	/**
	 * Année de l'étudiant (P1, P2, I1...)
	 */
	private String annee;
	
	/**
	 * Email de l'utilisateur
	 */
	private String email;
	
	/**
	 * Date de la dernière connexion de l'étudiant
	 */
	private String derniereConnexion;
	
	/**
	 * Rôle de l'utilisateur (Etudiant, Referent, Admin)
	 */
	private String role;
	
	/**
	 * @param numUtilisateur : Index de l'utilisateur sur la base de données
	 * @param nomUtilisateur : Nom de l'utilisateur avec lequel il se connecte
	 * @param hash : Hash du mot de passe de l'utilisateur
	 * @param nom : Nom de famille de l'utilisateur
	 * @param prenom : Prénom de l'utilisateur
	 * @param email : Email de l'utilisateur
	 * @param role : Rôle de l'utilisateur (Etudiant, Referent, Admin)
	 * @param annee : Année de l'étudiant (P1, P2, I1...)
	 * @param derniereConnexion : Date de la dernière connexion de l'étudiant
	 */
	public Utilisateur(long numUtilisateur, String nomUtilisateur, String hash, String nom, String prenom, String email, String role
			, String annee, String derniereConnexion) {
		super();
		this.numUtilisateur = numUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.nom = nom;
		this.hash = hash;
		this.prenom = prenom;
		this.annee = annee;
		this.email = email;
		this.derniereConnexion = derniereConnexion;
		this.role = role;
	}
	
	/**
	 * @param nomUtilisateur : Nom de l'utilisateur avec lequel il se connecte
	 * @param hash : Hash du mot de passe de l'utilisateur
	 * @param nom : Nom de famille de l'utilisateur
	 * @param prenom : Prénom de l'utilisateur
	 * @param email : Email de l'utilisateur
	 * @param role : Rôle de l'utilisateur (Etudiant, Referent, Admin)
	 * @param annee : Année de l'étudiant (P1, P2, I1...)
	 * @param derniereConnexion : Date de la dernière connexion de l'étudiant
	 */
	public Utilisateur(String nomUtilisateur, String hash, String nom, String prenom, String email, String role
			, String annee, String derniereConnexion) {
		this(0, nomUtilisateur, hash, nom, prenom, email, role, annee, derniereConnexion);
	}
	
	/**
	 * Créer un objet Utilisateur vide
	 */
	public Utilisateur() {
		this("", "", "", "", "", "", "", "");
	}


	/**
	 * Donne l'index de l'utilisateur sur la BDD
	 * @return l'index de l'utilisateur sur la BDD
	 */
	public long getNumUtilisateur() {
		return numUtilisateur;
	}


	/**
	 * Modifie l'index de l'utilisateur sur la BDD
	 * @param numUtilisateur
	 */
	public void setNumUtilisateur(long numUtilisateur) {
		this.numUtilisateur = numUtilisateur;
	}
	
	/**
	 * Donne le nom d'utilisateur
	 * @return le nom de l'utilisateur
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	/**
	 * Modifie le nom d'utilisateur
	 * @param numUtilisateur
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	/**
	 * Donne le nom de famille de l'utilisateur
	 * @return le nom de famille de l'utilisateur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom de l'utilisateur
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Donne le hash du mot de passe de l'utilisateur
	 * @return le hash du mot de passe de l'utilisateur
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Modifie le hash du mot de passe de l'utilisateur
	 * @param hash
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Donne le prenom de l'utilisateur
	 * @return le prenom de l'utilisateur
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Modifie le prenom de l'utilisateur
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Donne l'année de l'étudiant
	 * @return l'année de l'étudiant
	 */
	public String getAnnee() {
		return annee;
	}

	/**
	 * Modifie l'année de l'étudiant
	 * @param annee
	 */
	public void setAnnee(String annee) {
		this.annee = annee;
	}

	/**
	 * Donne l'email de l'étudiant
	 * @return l'email de l'étudiant
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modifie l'email de l'étudiant
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Donne la date de la dernière connexion de l'utilisateur
	 * @return la date de la dernière connexion de l'utilisateur
	 */
	public String getDerniereConnexion() {
		return derniereConnexion;
	}

	/**
	 * Modifie la date de la dernière connexion de l'utilisateur
	 * @param derniereConnexion
	 */
	public void setDerniereConnexion(String derniereConnexion) {
		this.derniereConnexion = derniereConnexion;
	}

	/**
	 * Donne le rôle de l'utilisateur
	 * @return le rôle de l'utilisateur
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Modifie le rôle de l'utilisateur
	 * @param role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Utilisateur [numUtilisateur=" + numUtilisateur + ", nomUtilisateur=" + nomUtilisateur + ", nom=" + nom
				+ ", hash=" + hash + ", prenom=" + prenom + ", annee=" + annee + ", email=" + email
				+ ", derniereConnexion=" + derniereConnexion + ", role=" + role + "]";
	}
}
