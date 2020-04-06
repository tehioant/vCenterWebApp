package fr.eseo.cc3.dao.bean;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCour {
	
	@Test
	public void testConstructeurEmpty() {
		Cour cour = new Cour();
		assertEquals(cour.getNumCour(), 0);
		assertEquals(cour.getNomCour(), "");
		assertEquals(cour.getNbEtudiant(), 0);
		assertEquals(cour.getNbVm(), 0);
		assertEquals(cour.getServeur(), 0);
	}
	
	@Test
	public void testConstructeurFull() {
		Cour cour = new Cour(5, "I1_A1", 16, 0, 1);
		assertEquals(cour.getNumCour(), 5);
		assertEquals(cour.getNomCour(), "I1_A1");
		assertEquals(cour.getNbEtudiant(), 16);
		assertEquals(cour.getNbVm(), 0);
		assertEquals(cour.getServeur(), 1);
	}
	
	@Test
	public void testConstructeurSemi() {
		Cour cour = new Cour("I1_A1", 1);
		assertEquals(cour.getNumCour(), 0);
		assertEquals(cour.getNomCour(), "I1_A1");
		assertEquals(cour.getNbEtudiant(), 0);
		assertEquals(cour.getNbVm(), 0);
		assertEquals(cour.getServeur(), 1);
	}
	
	@Test
	public void testConstructeurSemiFull() {
		Cour cour = new Cour("I1_A1", 16, 0, 1);
		assertEquals(cour.getNumCour(), 0);
		assertEquals(cour.getNomCour(), "I1_A1");
		assertEquals(cour.getNbEtudiant(), 16);
		assertEquals(cour.getNbVm(), 0);
		assertEquals(cour.getServeur(), 1);
	}
	
	@Test
	public void testSetNumCour() {
		Cour cour = new Cour(5, "I1_A1", 16, 0, 1);
		assertEquals(cour.getNumCour(), 5);
		cour.setNumCour(8);
		assertEquals(cour.getNumCour(), 8);
	}

	@Test
	public void testSetNomCour() {
		Cour cour = new Cour(5, "I1_A1", 16, 0, 1);
		assertEquals(cour.getNomCour(), "I1_A1");
		cour.setNomCour("I1_A2");
		assertEquals(cour.getNomCour(), "I1_A2");
	}
	
	@Test
	public void testSetNbEtudiant() {
		Cour cour = new Cour(5, "I1_A1", 16, 0, 1);
		assertEquals(cour.getNbEtudiant(), 16);
		cour.setNbEtudiant(17);
		assertEquals(cour.getNbEtudiant(), 17);
	}
	
	@Test
	public void testSetNbVm() {
		Cour cour = new Cour(5, "I1_A1", 16, 0, 1);
		assertEquals(cour.getNbVm(), 0);
		cour.setNbVm(5);
		assertEquals(cour.getNbVm(), 5);
	}
	
	@Test
	public void testSetServeur() {
		Cour cour = new Cour(5, "I1_A1", 16, 0, 1);
		assertEquals(cour.getServeur(), 1);
		cour.setServeur(4);
		assertEquals(cour.getServeur(), 4);
	}
	
	@Test
	public void testSetInscrit() {
		Cour cour = new Cour(5, "I1_A1", 16, 0, 1);
		cour.setInscrit(true);
		assertEquals(cour.isInscrit(), true);
		cour.setInscrit(false);
		assertEquals(cour.isInscrit(), false);
	}
	
	@Test
	public void testToString() {
		Cour cour = new Cour(5, "I1_A1", 16, 0, 1);
		assertEquals(cour.toString(), "Cour [numCour=" + "5" + ", nomCour=" + "I1_A1" + ", nbEtudiant=" + "16" + ", nbVm=" + "0"
				+ ", serveur=" + "1" + "]");

	}
}
