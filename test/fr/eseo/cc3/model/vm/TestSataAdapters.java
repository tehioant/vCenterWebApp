package fr.eseo.cc3.model.vm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestSataAdapters {

	@Test
	public void testBus() {
		SataAdapters sataAdaptaters = new SataAdapters();
		sataAdaptaters.setBus(10);
		assertEquals(sataAdaptaters.getBus(),10);
	}
	
	@Test
	public void testLabel() {
		SataAdapters sataAdaptaters = new SataAdapters();
		sataAdaptaters.setLabel("testLabel");
		assertEquals(sataAdaptaters.getLabel(),"testLabel");
	}
	
	@Test
	public void testType() {
		SataAdapters sataAdaptaters = new SataAdapters();
		sataAdaptaters.setType("testType");
		assertEquals(sataAdaptaters.getType(),"testType");
	}
	
	@Test
	public void testKey() {
		SataAdapters sataAdaptaters = new SataAdapters();
		sataAdaptaters.setKey("testKey");
		assertEquals(sataAdaptaters.getKey(),"testKey");
	}
	
	@Test
	public void testUnit() {
		SataAdapters sataAdaptaters = new SataAdapters();
		sataAdaptaters.setUnit(10);
		assertEquals(sataAdaptaters.getUnit(),10);
	}
	
	
	@Test
	public void testPciSlot() {
		SataAdapters sataAdaptaters = new SataAdapters();
		sataAdaptaters.setPciSlot("testPciSlot");
		assertEquals(sataAdaptaters.getPciSlot(),"testPciSlot");
	}
	
	@Test
	public void testSharing() {
		SataAdapters sataAdaptaters = new SataAdapters();
		sataAdaptaters.setSharing("testSharing");
		assertEquals(sataAdaptaters.getSharing(),"testSharing");
	}
}
