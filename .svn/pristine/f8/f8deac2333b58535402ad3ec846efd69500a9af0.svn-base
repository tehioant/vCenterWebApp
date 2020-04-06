package fr.eseo.cc3.model;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;

import org.junit.Test;

public class TestDataCenter {
	
	@Test
	public void testConstructeurEmpty() {
		DataCenter datacenter = new DataCenter();
		assertEquals(datacenter.getName(), "Inconnu");
		assertEquals(datacenter.getDatacenter(), "Inconnu");
		assertEquals(datacenter.getDatastoreFolder(), "Inconnu");
		assertEquals(datacenter.getHostFolder(), "Inconnu");
		assertEquals(datacenter.getNetworkFolder(), "Inconnu");
		assertEquals(datacenter.getVmFolder(), "Inconnu");
	}
	
	@Test
	public void testConstructeurHalf() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter");
		assertEquals(datacenter.getName(), "testName");
		assertEquals(datacenter.getDatacenter(), "testDatacenter");
		assertEquals(datacenter.getDatastoreFolder(), "Inconnu");
		assertEquals(datacenter.getHostFolder(), "Inconnu");
		assertEquals(datacenter.getNetworkFolder(), "Inconnu");
		assertEquals(datacenter.getVmFolder(), "Inconnu");
	}
	
	@Test
	public void testConstructeurFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getName(), "testName");
		assertEquals(datacenter.getDatacenter(), "testDatacenter");
		assertEquals(datacenter.getDatastoreFolder(), "testDatastoreFolder");
		assertEquals(datacenter.getHostFolder(), "testHostFolder");
		assertEquals(datacenter.getNetworkFolder(), "testNetworkFolder");
		assertEquals(datacenter.getVmFolder(), "testVmFolder");
	}
	
	@Test
	public void testConstructeurDataCenterEmpty() {
		DataCenter datacenter = new DataCenter("testName", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getName(), "testName");
		assertEquals(datacenter.getDatacenter(), "Inconnu");
		assertEquals(datacenter.getDatastoreFolder(), "testDatastoreFolder");
		assertEquals(datacenter.getHostFolder(), "testHostFolder");
		assertEquals(datacenter.getNetworkFolder(), "testNetworkFolder");
		assertEquals(datacenter.getVmFolder(), "testVmFolder");
	}
	
	@Test
	public void testGetNameEmpty() {
		DataCenter datacenter = new DataCenter();
		assertEquals(datacenter.getName(),"Inconnu");
	}

	@Test
	public void testGetNameFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getName(), "testName");
	}

	@Test
	public void testGetDatacenterEmpty() {
		DataCenter datacenter = new DataCenter();
		assertEquals(datacenter.getDatacenter(), "Inconnu");
	}
	
	@Test
	public void testGetDatacenterFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getDatacenter(), "testDatacenter");
	}

	@Test
	public void testGetDatastoreFolderEmpty() {
		DataCenter datacenter = new DataCenter();
		assertEquals(datacenter.getDatastoreFolder(), "Inconnu");
	}
	
	@Test
	public void testGetDatastoreFolderFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getDatastoreFolder(), "testDatastoreFolder");
	}

	@Test
	public void testGetHostFolderEmpty() {
		DataCenter datacenter = new DataCenter();
		assertEquals(datacenter.getHostFolder(), "Inconnu");
	}
	
	@Test
	public void testGetHostFolderFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getHostFolder(), "testHostFolder");
	}

	@Test
	public void testGetNetworkFolderEmpty() {
		DataCenter datacenter = new DataCenter();
		assertEquals(datacenter.getNetworkFolder(), "Inconnu");
	}
	
	@Test
	public void testGetNetworkFolderFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getNetworkFolder(), "testNetworkFolder");
	}

	@Test
	public void testGetVmFolderEmpty() {
		DataCenter datacenter = new DataCenter();
		assertEquals(datacenter.getVmFolder(), "Inconnu");
	}
	
	@Test
	public void testGetVmFolderFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getVmFolder(), "testVmFolder");
	}



	@Test
	public void testSetNameEmpty() {
		DataCenter datacenter = new DataCenter();
		datacenter.setName("test");
		assertEquals(datacenter.getName(), "test");
	}

	@Test
	public void testSetNameFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getName(), "testName");
		datacenter.setName("test");
		assertEquals(datacenter.getName(), "test");
	}
	
	@Test
	public void testSetDatacenterEmpty() {
		DataCenter datacenter = new DataCenter();
		datacenter.setDatacenter("test");
		assertEquals(datacenter.getDatacenter(), "test");
	}
	
	@Test
	public void testSetDatacenterFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getDatacenter(), "testDatacenter");			
		datacenter.setDatacenter("test");
		assertEquals(datacenter.getDatacenter(), "test");
	}

	@Test
	public void testSetDatastoreFolderEmpty() {
		DataCenter datacenter = new DataCenter();
		datacenter.setDatastoreFolder("test");
		assertEquals(datacenter.getDatastoreFolder(), "test");
	}
	
	@Test
	public void testSetDatastoreFolderFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getDatastoreFolder(), "testDatastoreFolder");			
		datacenter.setDatastoreFolder("test");
		assertEquals(datacenter.getDatastoreFolder(), "test");
	}

	public void testSetHostFolderEmpty() {
		DataCenter datacenter = new DataCenter();
		datacenter.setHostFolder("test");
		assertEquals(datacenter.getHostFolder(), "test");
	}
	
	@Test
	public void testSetHostFolderFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getHostFolder(), "testHostFolder");			
		datacenter.setHostFolder("test");
		assertEquals(datacenter.getHostFolder(), "test");
	}

	@Test
	public void testSetNetworkFolderEmpty() {
		DataCenter datacenter = new DataCenter();
		datacenter.setNetworkFolder("test");
		assertEquals(datacenter.getNetworkFolder(), "test");
	}
	
	@Test
	public void testSetNetworkFolderFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getNetworkFolder(), "testNetworkFolder");			
		datacenter.setNetworkFolder("test");
		assertEquals(datacenter.getNetworkFolder(), "test");
	}

	@Test
	public void testSetVmFolderEmpty() {
		DataCenter datacenter = new DataCenter();
		datacenter.setVmFolder("test");
		assertEquals(datacenter.getVmFolder(), "test");
	}
	
	@Test
	public void testSetVmFolderFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		assertEquals(datacenter.getVmFolder(), "testVmFolder");			
		datacenter.setVmFolder("test");
		assertEquals(datacenter.getVmFolder(), "test");
	}

	@Test
	public void testSetListeClusterEmpty() {
		DataCenter datacenter = new DataCenter();
		
		Cluster cluster0 = new Cluster();
		Cluster cluster1 = new Cluster();
		Cluster cluster2 = new Cluster();
		
		ArrayList<Cluster> listeCluster = new ArrayList<Cluster>();
		listeCluster.add(cluster0);
		listeCluster.add(cluster1);
		listeCluster.add(cluster2);
		
		datacenter.setListeCluster(listeCluster);
		assertEquals(datacenter.getListeCluster(), listeCluster);
	}
	
	@Test
	public void testSetListeClusterFull() {
		DataCenter datacenter = new DataCenter("testName", "testDatacenter", "testDatastoreFolder", "testHostFolder", "testNetworkFolder", "testVmFolder");
		
		Cluster cluster0 = new Cluster();
		Cluster cluster1 = new Cluster();
		Cluster cluster2 = new Cluster();
		
		ArrayList<Cluster> listeCluster = new ArrayList<Cluster>();
		listeCluster.add(cluster0);
		listeCluster.add(cluster1);
		listeCluster.add(cluster2);
		
		datacenter.setListeCluster(listeCluster);
		assertEquals(datacenter.getListeCluster(), listeCluster);
	}

}
