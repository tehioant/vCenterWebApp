package fr.eseo.cc3.dao.implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.CourDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;

import org.junit.AfterClass;
import org.junit.Test;

public class TestCourDaoImpl {
	
	private	static DAOFactory daoFactory = DAOFactory.getInstance(true);
	private CourDao courDao = (daoFactory.getCourDao());

	private void compareCours(Cour cour1, Cour cour2){
		assertEquals(cour1.getNumCour(), cour2.getNumCour());
		assertEquals(cour1.getNomCour(), cour2.getNomCour());
		assertEquals(cour1.getNbEtudiant(), cour2.getNbEtudiant());
		assertEquals(cour1.getNbVm(), cour2.getNbVm());
		assertEquals(cour1.getServeur(), cour2.getServeur());
	}
	
	private void compareListeCours(ArrayList<Cour> list1, ArrayList<Cour> list2) {
		for(int i = 0; i < Math.max(list1.size(), list2.size()); i++) {
			compareCours(list1.get(i), list2.get(i));
		}
	}
	
	@Test
	public void testTrouver() {
		Cour cours = new Cour(4, "GPI_A4", 7, 6, 1);
		Cour coursBDD = courDao.trouver("GPI_A4", 1);
		compareCours(cours, coursBDD);
	}

	@Test
	public void testLister() {
		ArrayList<Cour> listeCours = new ArrayList<Cour>();
		
		Cour cours = new Cour(1, "GPI_A1", 8, 0, 1);
		listeCours.add(cours);
		cours = new Cour(2, "GPI_A2", 1, 0, 1);
		listeCours.add(cours);
		cours = new Cour(3, "GPI_A3", 1, 0, 1);
		listeCours.add(cours);
		cours = new Cour(4, "GPI_A4", 7, 6, 1);
		listeCours.add(cours);
		cours = new Cour(5, "GPI_A5", 9, 8, 1);
		listeCours.add(cours);
		cours = new Cour(6, "GPI_B1", 0, 0, 1);
		listeCours.add(cours);
		cours = new Cour(7, "GPI_B2", 0, 0, 1);
		listeCours.add(cours);
		cours = new Cour(8, "GPI_B3", 0, 0, 1);
		listeCours.add(cours);
		cours = new Cour(9, "GPI_B4", 0, 0, 1);
		listeCours.add(cours);
		cours = new Cour(10, "GPI_B5", 0, 0, 1);
		listeCours.add(cours);
		
		ArrayList<Cour> listeCoursBDD = courDao.lister(1);
		compareListeCours(listeCours, listeCoursBDD);
	}
	
	@Test
	public void testGetCourReferent() {
		ArrayList<Cour> listeCours = new ArrayList<Cour>();
		
		Cour cours = new  Cour(2, "GPI_A2", 1, 0, 1);
		listeCours.add(cours);
		cours = new Cour(4, "GPI_A4", 7, 6, 1);
		listeCours.add(cours);
		
		Utilisateur referent = new Utilisateur(13, "professeur1", "professeur1", "Gouedard", "Charles-Henri", "charles-henri.gouedard@reseau.eseo.fr", "Referent", "A1", "2018-04-19");
	
		ArrayList<Cour> listeCoursBDD = courDao.getCourReferent(referent, 1);
		
		compareListeCours(listeCours, listeCoursBDD);
	}
	
	@Test
	public void testGetCourEtudiant() {
		ArrayList<Cour> listeCours = new ArrayList<Cour>();
		
		Cour cours = new Cour(1, "GPI_A1", 8, 0, 1);
		listeCours.add(cours);
		cours = new Cour(5, "GPI_A5", 9, 8, 1);
		listeCours.add(cours);
		
		Utilisateur etudiant = new Utilisateur(5, "etudiant", "etudiant", "Bellion", "Bastien", "bastien.bellion@reseau.eseo.fr", "Etudiant", "I1", "2018-05-16");
	
		ArrayList<Cour> listeCoursBDD = courDao.getCourUtilisateur(etudiant, 1);
		
		compareListeCours(listeCours, listeCoursBDD);
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		daoFactory.shutdownConnection();
	}
}
