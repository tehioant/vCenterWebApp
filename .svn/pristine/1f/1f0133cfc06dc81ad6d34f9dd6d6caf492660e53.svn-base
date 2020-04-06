package fr.eseo.cc3.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TestCluster {
	
	@Test
	public void testConstructeurVide() {
		Cluster cluster = new Cluster();
		assertNull(cluster.getName());
		assertNull(cluster.getRessourcePool());
		assertTrue(cluster.getListeHosts().isEmpty());
	}
	
	@Test
	public void testConstructeurParam1() {
		Cluster cluster = new Cluster("nameTest", "ressource_test");
		assertEquals(cluster.getName(), "nameTest");
		assertEquals(cluster.getRessourcePool(), "ressource_test");
		assertTrue(cluster.getListeHosts().isEmpty());
	}
	
	@Test
	public void testConstructeurParam2() {
		Host host0 = new Host();
		Host host1 = new Host();
		Host host2 = new Host();
		
		ArrayList<Host> pListe = new ArrayList<Host>();
		pListe.add(host0);
		pListe.add(host1);
		pListe.add(host2);
		
		Cluster cluster = new Cluster("nameTest", "ressource_test", pListe);
		assertEquals(cluster.getName(), "nameTest");
		assertEquals(cluster.getRessourcePool(), "ressource_test");
		assertEquals(cluster.getListeHosts(), pListe);
	}
	
	@Test
	public void testGetNameEmpty() {
		Cluster cluster = new Cluster();
		assertNull(cluster.getName());
	}
	
	@Test
	public void testGetNameFull() {
		Cluster cluster = new Cluster();
		assertNull(cluster.getName());
	}
	
	@Test
	public void testGetRessourceEmpty() {
		Cluster cluster = new Cluster();
		assertNull(cluster.getRessourcePool());
	}
	
	@Test
	public void testGetRessourceFull() {
		Cluster cluster = new Cluster("nameTest", "ressource_test");
		assertEquals(cluster.getRessourcePool(), "ressource_test");
	}
	
	@Test
	public void testGetListeClusterEmpty() {
		Cluster cluster = new Cluster();
		assertTrue(cluster.getListeHosts().isEmpty());
	}
	
	@Test
	public void testGetListeClusterFull() {
		Host host0 = new Host();
		Host host1 = new Host();
		Host host2 = new Host();
		
		ArrayList<Host> pListe = new ArrayList<Host>();
		pListe.add(host0);
		pListe.add(host1);
		pListe.add(host2);
		
		Cluster cluster = new Cluster("nameTest", "ressource_test", pListe);
		assertEquals(cluster.getListeHosts(), pListe);
	}
	
	@Test
	public void testSetName() {
		Cluster cluster = new Cluster();
		assertNull(cluster.getName());
		
		cluster.setName("testSetName");
		assertEquals(cluster.getName(), "testSetName");
	}
	
	@Test
	public void testSetRessource() {
		Cluster cluster = new Cluster();
		assertNull(cluster.getRessourcePool());
		
		cluster.setRessourcePool("testSetRessource");
		assertEquals(cluster.getRessourcePool(), "testSetRessource");
	}
	
	@Test
	public void testSetListeHost() {
		Cluster cluster = new Cluster();
		assertTrue(cluster.getListeHosts().isEmpty());
		
		Host host0 = new Host();
		Host host1 = new Host();
		Host host2 = new Host();
		
		ArrayList<Host> pListe = new ArrayList<Host>();
		pListe.add(host0);
		pListe.add(host1);
		pListe.add(host2);
		
		cluster.setListeHosts(pListe);
		assertEquals(cluster.getListeHosts(), pListe);
	}
	
	@Test
	public void testGetDrs_enabled() {
		Cluster cluster = new Cluster();
		assertNull(cluster.getDrs_enabled());
	}
	
	@Test
	public void testGetDrs_enabledFull() {
		Cluster cluster = new Cluster("nameTest", "ressource_test");
		assertNull(cluster.getDrs_enabled());
	}
	
	@Test
	public void setGetDrs_enabled() {
		Cluster cluster = new Cluster();
		cluster.setDrs_enabled("testDrs_enabled");
		assertEquals(cluster.getDrs_enabled(),"testDrs_enabled");
	}
	
	@Test
	public void testGetHa_enabled() {
		Cluster cluster = new Cluster();
		assertNull(cluster.getHa_enabled());
	}
	
	@Test
	public void testGetHa_enabledFull() {
		Cluster cluster = new Cluster("nameTest", "ressource_test");
		assertNull(cluster.getHa_enabled());
	}
	
	@Test
	public void setGetHa_enabled() {
		Cluster cluster = new Cluster();
		cluster.setHa_enabled("testHa_enabled");
		assertEquals(cluster.getHa_enabled(),"testHa_enabled");
	}
	
	
	@Test
	public void setCluster() {
		Cluster cluster = new Cluster();
		cluster.setCluster("testCluster");
		assertEquals(cluster.getCluster(),"testCluster");
	}
}
