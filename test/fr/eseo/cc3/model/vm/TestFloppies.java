package fr.eseo.cc3.model.vm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestFloppies {

	
	@Test
	public void testStart_connected() {
		Floppies floppies = new Floppies();
		floppies.setStart_connected(true);
		assertTrue(floppies.getStart_connected());
	}
	
	@Test
	public void testBackingType() {
		Floppies floppies = new Floppies();
		floppies.setBackingType("testBackingType");
		assertEquals(floppies.getBackingType(),"testBackingType");
	}
	
	@Test
	public void testAllow_guest_control() {
		Floppies floppies = new Floppies();
		floppies.setAllow_guest_control(true);
		assertTrue(floppies.getAllow_guest_control());
	}
	
	@Test
	public void testLabel() {
		Floppies floppies = new Floppies();
		floppies.setLabel("testLabel");
		assertEquals(floppies.getLabel(),"testLabel");
	}
	
	@Test
	public void testState() {
		Floppies floppies = new Floppies();
		floppies.setState("testState");
		assertEquals(floppies.getState(),"testState");
	}
	
	@Test
	public void testKey() {
		Floppies floppies = new Floppies();
		floppies.setKey("testKey");
		assertEquals(floppies.getKey(),"testKey");
	}
}
