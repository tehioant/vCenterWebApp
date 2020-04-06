package fr.eseo.cc3.dao.implementation;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.UtilisateurDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;

public class TestUtilisateurDaoImpl {

	private	static DAOFactory daoFactory = DAOFactory.getInstance(true);
	private UtilisateurDao utilisateurDao = (daoFactory.getUtilisateurDao());

	private void compareUtilisateur(Utilisateur user1, Utilisateur user2) {
		assertEquals(user1.getNumUtilisateur(), user2.getNumUtilisateur());
		assertEquals(user1.getNom(), user2.getNom());
		assertEquals(user1.getHash(), user2.getHash());
		assertEquals(user1.getPrenom(), user2.getPrenom());
		assertEquals(user1.getAnnee(), user2.getAnnee());
		assertEquals(user1.getEmail(), user2.getEmail());
		assertEquals(user1.getDerniereConnexion(), user2.getDerniereConnexion());
		assertEquals(user1.getRole(), user2.getRole());
	}

	private void compareListeUtilisateur(ArrayList<Utilisateur> list1, ArrayList<Utilisateur> list2) {
		for(int i = 0; i < Math.max(list1.size(), list2.size()); i++) {
			compareUtilisateur(list1.get(i), list2.get(i));
		}
	}

	@Test
	public void testTrouverEtudiant() {
		Utilisateur user = new Utilisateur(5, "etudiant", "etudiant", "Bellion", "Bastien", "bastien.bellion@reseau.eseo.fr", "Etudiant", "I1", "2018-05-16");
		Utilisateur userbdd = this.utilisateurDao.trouver("etudiant");
		compareUtilisateur(user, userbdd);
	}

	@Test
	public void testTrouverVide() {
		Utilisateur userbdd = this.utilisateurDao.trouver("azeafze");
		assertNull(userbdd);
	}

	@Test
	public void testLister() {
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		Utilisateur user = new Utilisateur(5, "etudiant", "etudiant", "Bellion", "Bastien", "bastien.bellion@reseau.eseo.fr", "Etudiant", "I1", "2018-05-16");
		users.add(user);
		user = new Utilisateur(6, "admin", "admin", "Le Fort", "Alexis", "alexis.lefort@reseau.eseo.fr", "Administrateur", "A1", "2018-05-23");
		users.add(user);
		user = new Utilisateur(7, "professeur", "professeur", "Gervy", "Nicolas", "nicolas.gervy@reseau.eseo.fr", "Referent", "A1", "2018-05-15");
		users.add(user);
		user = new Utilisateur(8, "etudiant1", "etudiant1", "mohaer", "Baptiste", "baptiste.mohaer@reseau.eseo.fr", "Etudiant", "I1", "2018-05-13");
		users.add(user);
		user = new Utilisateur(9, "etudiant2", "etudiant2", "Diacre", "Alexandre", "alexandre.diacre@reseau.eseo.fr", "Etudiant", "I1", "2018-05-01");
		users.add(user);
		user = new Utilisateur(10, "etudiant3", "etudiant3", "Le Merle", "Alexandre", "alexandre.lemerle@reseau.eseo.fr", "Etudiant", "P2", "2018-05-14");
		users.add(user);
		user = new Utilisateur(11, "etudiant4", "etudiant4", "Azare", "Simon", "simon.azare@reseau.eseo.fr", "Etudiant", "P2", "2018-05-21");
		users.add(user);
		user = new Utilisateur(12, "etudiant5", "etudiant5", "Tehio", "Antoine", "antoine.tehio@reseau.eseo.fr", "Etudiant", "P1", "2018-05-16");
		users.add(user);
		user = new Utilisateur(13, "professeur1", "professeur1", "Gouedard", "Charles-Henri", "charles-henri.gouedard@reseau.eseo.fr", "Referent", "A1", "2018-04-19");
		users.add(user);
		user = new Utilisateur(14, "professeur2", "professeur2", "Viot", "Nicolas", "nicolas.viot@reseau.eseo.fr", "Referent", "A1", "2018-05-15");
		users.add(user);
		user = new Utilisateur(15, "etudiant6", "etudiant6", "Baptiste", "Beduneau", "baptiste.beduneau@reseau.eseo.fr", "Etudiant", "I1", "2018-05-13");
		users.add(user);
		user = new Utilisateur(16, "etudiant7", "etudiant7", "Clément", "Gouin", "clement.gouin@reseau.eseo.fr", "Etudiant", "I1", "2018-05-01");
		users.add(user);
		user = new Utilisateur(17, "etudiant8", "etudiant8", "Vincent", "léo", "leo.vincent@reseau.eseo.fr", "Etudiant", "I1", "2018-05-14");
		users.add(user);
		user = new Utilisateur(18, "etudiant9", "etudiant9", "Combrouz", "Baptiste", "baptiste.combrouz@reseau.eseo.fr", "Etudiant", "I1", "2018-05-21");
		users.add(user);
		user = new Utilisateur(19, "etudiant10", "etudiant10", "Germain", "Jean", "jean.germain@reseau.eseo.fr", "Etudiant", "I1", "2018-05-13");
		users.add(user);
		user = new Utilisateur(20, "etudiant11", "etudiant11", "Merceron", "Sophie", "sophie.merceron@reseau.eseo.fr", "Etudiant", "I1", "2018-05-01");
		users.add(user);
		user = new Utilisateur(21, "etudiant12", "etudiant12", "Goujon", "Benoit", "benoit.goujon@reseau.eseo.fr", "Etudiant", "I1", "2018-05-14");
		users.add(user);
		user = new Utilisateur(22, "etudiant13", "etudiant13", "Henry", "Julien", "julein.henry@reseau.eseo.fr", "Etudiant", "I1", "2018-05-21");
		users.add(user);

		ArrayList<Utilisateur> usersBDD = new ArrayList<Utilisateur>();
		usersBDD = this.utilisateurDao.lister();
		compareListeUtilisateur(usersBDD, users);
	}

	@Test
	public void testListerUtilisateurCours() {
		Cour cour = new Cour(1,	"GPI_A1", 16, 0, 1);

		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		Utilisateur user = new Utilisateur(5, "etudiant", "etudiant", "Bellion", "Bastien", "bastien.bellion@reseau.eseo.fr", "Etudiant", "I1", "2018-05-16");
		users.add(user);
		user = new Utilisateur(15, "etudiant6", "etudiant6", "Baptiste", "Beduneau", "baptiste.beduneau@reseau.eseo.fr", "Etudiant", "I1", "2018-05-13");
		users.add(user);
		user = new Utilisateur(16, "etudiant7", "etudiant7", "Clément", "Gouin", "clement.gouin@reseau.eseo.fr", "Etudiant", "I1", "2018-05-01");
		users.add(user);
		user = new Utilisateur(17, "etudiant8", "etudiant8", "Vincent", "léo", "leo.vincent@reseau.eseo.fr", "Etudiant", "I1", "2018-05-14");
		users.add(user);
		user = new Utilisateur(18, "etudiant9", "etudiant9", "Combrouz", "Baptiste", "baptiste.combrouz@reseau.eseo.fr", "Etudiant", "I1", "2018-05-21");
		users.add(user);
		user = new Utilisateur(19, "etudiant10", "etudiant10", "Germain", "Jean", "jean.germain@reseau.eseo.fr", "Etudiant", "I1", "2018-05-13");
		users.add(user);
		user = new Utilisateur(20, "etudiant11", "etudiant11", "Merceron", "Sophie", "sophie.merceron@reseau.eseo.fr", "Etudiant", "I1", "2018-05-01");
		users.add(user);

		ArrayList<Utilisateur> usersBDD = new ArrayList<Utilisateur>();
		usersBDD = this.utilisateurDao.listerUtilisateurCours(cour);
		assertEquals(usersBDD.size(), users.size());
		compareListeUtilisateur(usersBDD, users);
	}

	@Test
	public void testListerGetReferent() {
		Cour cour = new Cour(1,	"GPI_A1", 16, 0, 1);

		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		Utilisateur user = new Utilisateur(7, "professeur", "professeur", "Gervy", "Nicolas", "nicolas.gervy@reseau.eseo.fr", "Referent", "A1", "2018-05-15");
		users.add(user);

		ArrayList<Utilisateur> usersBDD = new ArrayList<Utilisateur>();
		try {
			usersBDD = this.utilisateurDao.getReferent(cour);
		}
		catch(SQLException e){
			fail("Erreur DAO");
		}
		compareListeUtilisateur(usersBDD, users);
	}

	@Test
	public void testListerReferent() {
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		Utilisateur user = new Utilisateur(7, "professeur", "professeur", "Gervy", "Nicolas", "nicolas.gervy@reseau.eseo.fr", "Referent", "A1", "2018-05-15");
		users.add(user);
		user = new Utilisateur(13, "professeur1", "professeur1", "Gouedard", "Charles-Henri", "charles-henri.gouedard@reseau.eseo.fr", "Referent", "A1", "2018-04-19");
		users.add(user);
		user = new Utilisateur(14, "professeur2", "professeur2", "Viot", "Nicolas", "nicolas.viot@reseau.eseo.fr", "Referent", "A1", "2018-05-15");
		users.add(user);
		ArrayList<Utilisateur> usersBDD = new ArrayList<Utilisateur>();
		usersBDD = this.utilisateurDao.listerReferent();
		compareListeUtilisateur(usersBDD, users);
	}

	@Test
	public void testGetUserVmUserUnique() {
		VirtualMachine vm = new VirtualMachine(1, "Ma premiere Vm", "VM-01", 1, "2018-04-04", "2018-06-14", "2018-05-22");

		Utilisateur user = new Utilisateur(5, "etudiant", "etudiant", "Bellion", "Bastien", "bastien.bellion@reseau.eseo.fr", "Etudiant", "I1", "2018-05-16");
		Utilisateur userBDD = new Utilisateur();
		try {
			userBDD = this.utilisateurDao.getUserVm(vm);
		}
		catch(SQLException e){
			fail("Erreur DAO");
		}

		compareUtilisateur(user, userBDD);
	}
	
	@Test
	public void testGetReferentUser() {
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		Utilisateur user = new Utilisateur(7, "professeur", "professeur", "Gervy", "Nicolas", "nicolas.gervy@reseau.eseo.fr", "Referent", "A1", "2018-05-15");
		users.add(user);
		user = new Utilisateur(14, "professeur2", "professeur2", "Viot", "Nicolas", "nicolas.viot@reseau.eseo.fr", "Referent", "A1", "2018-05-15");
		users.add(user);
		user = new Utilisateur(16, "etudiant7", "etudiant7", "Clément", "Gouin", "clement.gouin@reseau.eseo.fr", "Etudiant", "I1", "2018-05-01");
		
		ArrayList<Utilisateur> usersBDD = new ArrayList<Utilisateur>();
		usersBDD = this.utilisateurDao.getReferentUser(user);
		compareListeUtilisateur(usersBDD, users);
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		daoFactory.shutdownConnection();
	}
}