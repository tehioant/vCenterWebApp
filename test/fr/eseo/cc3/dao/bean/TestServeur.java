package fr.eseo.cc3.dao.bean;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestServeur {
	
	@Test
	public void testConstructeurEmpty() {
		Serveur serveur = new Serveur();
		assertEquals(serveur.getNumServeur(), 0);
		assertEquals(serveur.getNomServeur(), "");
		assertEquals(serveur.getIpServeur(), "");
		assertEquals(serveur.getIdentifiant(), "");
		assertEquals(serveur.getPassDecrypte(), "");
		assertEquals(serveur.isAfficher(), false);
	}
	
	@Test
	public void testConstructeurSemiFull() {
		Serveur serveur = new Serveur("serveur1", "192.168.4.213", "test", "testpass", false);
		assertEquals(serveur.getNumServeur(), 0);
		assertEquals(serveur.getNomServeur(), "serveur1");
		assertEquals(serveur.getIpServeur(), "192.168.4.213");
		assertEquals(serveur.getIdentifiant(), "test");
		assertEquals(serveur.getPassDecrypte(), "testpass");
		assertEquals(serveur.isAfficher(), false);
	}
	
	@Test
	public void testConstructeurFull() {
		Serveur serveur = new Serveur(1, "serveur1", "192.168.4.213", "test", "testpass", false);
		assertEquals(serveur.getNumServeur(), 1);
		assertEquals(serveur.getNomServeur(), "serveur1");
		assertEquals(serveur.getIpServeur(), "192.168.4.213");
		assertEquals(serveur.getIdentifiant(), "test");
		assertEquals(serveur.getPassDecrypte(), "testpass");
		assertEquals(serveur.isAfficher(), false);
	}
	
	@Test
	public void testSetNumServeur() {
		Serveur serveur = new Serveur(1, "serveur1", "192.168.4.213", "test", "testpass", false);
		assertEquals(serveur.getNumServeur(), 1);
		serveur.setNumServeur(5);
		assertEquals(serveur.getNumServeur(), 5);
	}
	
	@Test
	public void testSetNomServeur() {
		Serveur serveur = new Serveur(1, "serveur1", "192.168.4.213", "test", "testpass", false);
		assertEquals(serveur.getNomServeur(), "serveur1");
		serveur.setNomServeur("serveur45");
		assertEquals(serveur.getNomServeur(), "serveur45");
	}
	
	@Test
	public void testSetIpServeur() {
		Serveur serveur = new Serveur(1, "serveur1", "192.168.4.213", "test", "testpass", false);
		assertEquals(serveur.getIpServeur(), "192.168.4.213");
		serveur.setIpServeur("192.168.4.2");
		assertEquals(serveur.getIpServeur(), "192.168.4.2");
	}
	
	@Test
	public void testSetIdentifiant() {
		Serveur serveur = new Serveur(1, "serveur1", "192.168.4.213", "test", "testpass", false);
		assertEquals(serveur.getIdentifiant(), "test");
		serveur.setIdentifiant("testauth");
		assertEquals(serveur.getIdentifiant(), "testauth");
	}
	
	@Test
	public void testSetAfficher() {
		Serveur serveur = new Serveur(1, "serveur1", "192.168.4.213", "test", "testpass", false);
		assertEquals(serveur.isAfficher(), false);
		serveur.setAfficher(true);
		assertEquals(serveur.isAfficher(), true);
	}
	
	@Test
	public void testToString() {
		Serveur serveur = new Serveur("serveur1", "192.168.4.213", "test", "testpass", false);
		assertEquals(serveur.toString(), "Serveur [numServeur=" + "0" + ", nomServeur=" + "serveur1" + ", ipServeur=" + "192.168.4.213"
				+ ", identifiant=" + "test" + ", passDecrypte=" + "testpass" + ", afficher=" + "false" + "]");
	}
}
