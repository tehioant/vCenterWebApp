package fr.eseo.cc3.dao.bean;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUtilisateur {

	@Test
	public void testConstructeurEmpty() {
		Utilisateur user = new Utilisateur();
		assertEquals(user.getNumUtilisateur(), 0);
		assertEquals(user.getNomUtilisateur(), "");
		assertEquals(user.getNom(), "");
		assertEquals(user.getHash(), "");
		assertEquals(user.getPrenom(), "");
		assertEquals(user.getAnnee(), "");
		assertEquals(user.getEmail(), "");
		assertEquals(user.getDerniereConnexion(), "");
		assertEquals(user.getRole(), "");
	}

	@Test
	public void testConstructeurSemiFull() {
		Utilisateur user = new Utilisateur("Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getNumUtilisateur(), 0);
		assertEquals(user.getNomUtilisateur(),"Bellioba");
		assertEquals(user.getNom(),"Bellion");
		assertEquals(user.getHash(),"password");
		assertEquals(user.getPrenom(),"Bastien");
		assertEquals(user.getAnnee(),"Josephson");
		assertEquals(user.getEmail(),"Bastien.bellion@reseau.eseo.fr");
		assertEquals(user.getDerniereConnexion(),"14-4-2018");
		assertEquals(user.getRole(),"Etudiant");
	}
	
	@Test
	public void testConstructeurFull() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getNumUtilisateur(), 5);
		assertEquals(user.getNomUtilisateur(),"Bellioba");
		assertEquals(user.getNom(),"Bellion");
		assertEquals(user.getHash(),"password");
		assertEquals(user.getPrenom(),"Bastien");
		assertEquals(user.getAnnee(),"Josephson");
		assertEquals(user.getEmail(),"Bastien.bellion@reseau.eseo.fr");
		assertEquals(user.getDerniereConnexion(),"14-4-2018");
		assertEquals(user.getRole(),"Etudiant");
	}
	
	@Test
	public void testSetNumUtilisateur() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getNumUtilisateur(), 5);
		user.setNumUtilisateur(8);
		assertEquals(user.getNumUtilisateur(), 8);
	}
	
	@Test
	public void testSetNomUtilisateur() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getNomUtilisateur(), "Bellioba");
		user.setNomUtilisateur("Lefortal");
		assertEquals(user.getNomUtilisateur(), "Lefortal");
	}
	
	@Test
	public void testSetMotDePasse() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getHash(), "password");
		user.setHash("motdepasse");
		assertEquals(user.getHash(), "motdepasse");
	}
	
	@Test
	public void testSetNom() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getNom(), "Bellion");
		user.setNom("Lefort");
		assertEquals(user.getNom(), "Lefort");
	}
	
	@Test
	public void testSetPrenom() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getPrenom(), "Bastien");
		user.setPrenom("Alexis");
		assertEquals(user.getPrenom(), "Alexis");
	}
	

	@Test
	public void testSetAnnee() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getAnnee(), "Josephson");
		user.setAnnee("Langevin");
		assertEquals(user.getAnnee(), "Langevin");
	}
	
	@Test
	public void testSetEmail() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getEmail(), "Bastien.bellion@reseau.eseo.fr");
		user.setEmail("alexis.lefort@reseau.eseo.fr");
		assertEquals(user.getEmail(), "alexis.lefort@reseau.eseo.fr");
	}
	
	@Test
	public void testSetDerniereConnexion() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getDerniereConnexion(), "14-4-2018");
		user.setDerniereConnexion("14-1-1555");
		assertEquals(user.getDerniereConnexion(), "14-1-1555");
	}
	
	@Test
	public void testSetRole() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getRole(), "Etudiant");
		user.setRole("Admin");
		assertEquals(user.getRole(), "Admin");
	}
	
	@Test
	public void testToString() {
		Utilisateur user = new Utilisateur(5, "Bellioba", "password", "Bellion", "Bastien",
				"Bastien.bellion@reseau.eseo.fr", "Etudiant","Josephson", "14-4-2018");
		assertEquals(user.getNumUtilisateur(), 5);
		assertEquals(user.getNomUtilisateur(),"Bellioba");
		assertEquals(user.getNom(),"Bellion");
		assertEquals(user.getHash(),"password");
		assertEquals(user.getPrenom(),"Bastien");
		assertEquals(user.getAnnee(),"Josephson");
		assertEquals(user.getEmail(),"Bastien.bellion@reseau.eseo.fr");
		assertEquals(user.getDerniereConnexion(),"14-4-2018");
		assertEquals(user.getRole(),"Etudiant");
		assertEquals(user.toString(), "Utilisateur [numUtilisateur=" + "5" + ", nomUtilisateur=" + "Bellioba" + ", nom=" + "Bellion"
				+ ", hash=" + "password" + ", prenom=" + "Bastien" + ", annee=" + "Josephson" + ", email=" + "Bastien.bellion@reseau.eseo.fr"
				+ ", derniereConnexion=" + "14-4-2018" + ", role=" + "Etudiant" + "]");
	}
}
