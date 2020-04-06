package fr.eseo.cc3.dao.implementation;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.ServeurDao;
import fr.eseo.cc3.dao.bean.Serveur;

public class TestServeurDaoImpl {

	private static	DAOFactory daoFactory = DAOFactory.getInstance(true);
	private ServeurDao serveurDao = (daoFactory.getServeurDao());

	private void compareServeur(Serveur serveur1, Serveur serveur2) {
		assertEquals(serveur1.getNumServeur(), serveur2.getNumServeur());
		assertEquals(serveur1.getNomServeur(), serveur2.getNomServeur());
		assertEquals(serveur1.getIpServeur(), serveur2.getIpServeur());
		assertEquals(serveur1.getIdentifiant(), serveur2.getIdentifiant());
		assertEquals(serveur1.getPassDecrypte(), serveur2.getPassDecrypte());
	}

	private void compareListeUtilisateur(ArrayList<Serveur> list1, ArrayList<Serveur> list2) {
		for(int i = 0; i < Math.max(list1.size(), list2.size()); i++) {
			compareServeur(list1.get(i), list2.get(i));
		}
	}

	@Test
	public void testTrouverSemiNomServeur() {
		Serveur serveur = new Serveur(1, "serveur test", "192.168.0.0", true);
		try {
			Serveur serveurBDD = this.serveurDao.trouverSemi("serveur test");
			compareServeur(serveur, serveurBDD);
		}
		catch(SQLException e) {
			fail();
		}
	}

	@Test
	public void testTrouverSemiNumServeur() {
		Serveur serveur = new Serveur(1, "serveur test", "192.168.0.0", true);
		try {
			Serveur serveurBDD = this.serveurDao.trouverSemi(1);
			compareServeur(serveur, serveurBDD);
		}
		catch(SQLException e) {
			fail();
		}
	}

	@Test
	public void testTrouverFullServeur() {
		Serveur serveur = new Serveur(1, "serveur test", "192.168.0.0", "quesque c", "bonjour", true);
		try {
			Serveur serveurBDD = this.serveurDao.trouverFull(1);
			compareServeur(serveur, serveurBDD);
		}
		catch(SQLException e) {
			fail();
		}
	}

	@Test
	public void testLister() {
		ArrayList<Serveur> serveurs = new ArrayList<Serveur>();
		Serveur serveur = new Serveur(1, "serveur test", "192.168.0.0", true);
		serveurs.add(serveur);
		serveur = new Serveur(2, "Default", "165.156.12.12", true);
		serveurs.add(serveur);

		ArrayList<Serveur> serveursBDD = new ArrayList<Serveur>();
		try {
			serveursBDD = this.serveurDao.lister();
			compareListeUtilisateur(serveurs, serveursBDD);
		}
		catch(SQLException e) {
			fail();
		}
	}

	@AfterClass
	public static void tearDownAfterClass() {
		daoFactory.shutdownConnection();
	}
}
