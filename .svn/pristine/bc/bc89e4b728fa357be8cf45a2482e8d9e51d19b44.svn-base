package fr.eseo.cc3.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TestHost {
	
	@Test
	public void testConstructeurEmpty() {
		Host host = new Host();
		assertNull(host.getHost());
		assertNull(host.getName());
		assertNull(host.getConnectionState());
		assertNull(host.getPowerState());
	}
	
	@Test
	public void testConstructeurFull() {
		Host host = new Host("testHost", "testName", "testConnectionState", "testPowerState");
		assertEquals(host.getHost(), "testHost");
		assertEquals(host.getName(), "testName");
		assertEquals(host.getConnectionState(), "testConnectionState");
		assertEquals(host.getPowerState(), "testPowerState");
	}
	
	@Test
	public void testGetHostEmpty() {
		Host host = new Host();
		assertNull(host.getHost());
	}
	
	@Test
	public void testGetHostFull() {
		Host host = new Host("testHost", "testName", "testConnectionState", "testPowerState");
		assertEquals(host.getHost(), "testHost");
	}
	
	@Test
	public void testGetNameEmpty() {
		Host host = new Host();
		assertNull(host.getName());
	}
	
	@Test
	public void testGetNameFull() {
		Host host = new Host("testHost", "testName", "testConnectionState", "testPowerState");
		assertEquals(host.getName(), "testName");
	}
	
	@Test
	public void testGetConnectionStateEmpty() {
		Host host = new Host();
		assertNull(host.getConnectionState());
	}
	
	@Test
	public void testGetConnectionStateFull() {
		Host host = new Host("testHost", "testName", "testConnectionState", "testPowerState");
		assertEquals(host.getConnectionState(), "testConnectionState");
	}
	
	@Test
	public void testGetPowerStateEmpty() {
		Host host = new Host();
		assertNull(host.getPowerState());
	}
	
	@Test
	public void testGetPowerStateFull() {
		Host host = new Host("testHost", "testName", "testConnectionState", "testPowerState");
		assertEquals(host.getPowerState(), "testPowerState");
	}
	
	@Test
	public void testSetHostEmpty() {
		Host host = new Host();
		assertNull(host.getHost());
		host.setHost("test");
		assertEquals(host.getHost(), "test");
	}
	
	@Test
	public void testSetHostFull() {
		Host host = new Host("testHost", "testName", "testConnectionState", "testPowerState");
		assertEquals(host.getHost(), "testHost");
		host.setHost("test");
		assertEquals(host.getHost(), "test");
	}
	
	@Test
	public void testSetNameEmpty() {
		Host host = new Host();
		assertNull(host.getName());
		host.setName("test");
		assertEquals(host.getName(), "test");
	}
	
	@Test
	public void testSetNameFull() {
		Host host = new Host("testHost", "testName", "testConnectionState", "testPowerState");
		assertEquals(host.getName(), "testName");
		host.setName("test");
		assertEquals(host.getName(), "test");
	}
	
	@Test
	public void testSetConnectionStateEmpty() {
		Host host = new Host();
		assertNull(host.getConnectionState());
		host.setConnectionState("test");
		assertEquals(host.getConnectionState(), "Inconnu");
	}
	
	@Test
	public void testSetConnectionStateFull() {
		Host host = new Host("testHost", "testName", "testConnectionState", "testPowerState");
		assertEquals(host.getConnectionState(), "testConnectionState");
		host.setConnectionState("test");
		assertEquals(host.getConnectionState(), "Inconnu");
	}
	
	@Test
	public void testSetPowerStateEmpty() {
		Host host = new Host();
		assertNull(host.getPowerState());
		host.setPowerState("test");
		assertEquals(host.getPowerState(), "Inconnu");
	}
	
	@Test
	public void testSetPowerStateFull() {
		Host host = new Host("testHost", "testName", "testConnectionState", "testPowerState");
		assertEquals(host.getPowerState(), "testPowerState");
		host.setPowerState("test");
		assertEquals(host.getPowerState(), "Inconnu");
	}
	
	@Test
	public void testGetListeVm() {
		Host host = new Host();
		assertTrue(host.getListeVm().isEmpty());
	}
	
	@Test
	public void testSetListeVm() {
		Host host = new Host();
		VirtualMachine vm0 = new VirtualMachine();
		VirtualMachine vm1 = new VirtualMachine();
		VirtualMachine vm2 = new VirtualMachine();
		
		ArrayList<VirtualMachine> vmListe = new ArrayList<VirtualMachine>();
		vmListe.add(vm0);
		vmListe.add(vm1);
		vmListe.add(vm2);
		
		host.setListeVm(vmListe);
		assertEquals(host.getListeVm(),vmListe);
	}

	@Test
	public void testsetConnectionState() {
		Host host = new Host();
		host.setConnectionState("CONNECTED");
		assertEquals(host.getConnectionState(),"Connecté");
		
		host.setConnectionState("DISCONNECTED");
		assertEquals(host.getConnectionState(),"Déconnecté");
		
		host.setConnectionState("NOT_RESPONDING");
		assertEquals(host.getConnectionState(),"Ne répond pas");
		
		
	}
	
	@Test
	public void testsetPowerState() {
		Host host = new Host();
		host.setPowerState("POWERED_OFF");
		assertEquals(host.getPowerState(),"Hors tension");
		
		host.setPowerState("POWERED_ON");
		assertEquals(host.getPowerState(),"Sous tension");
		
		host.setPowerState("SUSPENDED");
		assertEquals(host.getPowerState(),"Interrompu");
		
		
	}
}
