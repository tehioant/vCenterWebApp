package fr.eseo.cc3.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestDataStore{

	@Test
	public void testConstructeurEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getDatastore());
		assertNull(datastore.getName());
		assertNull(datastore.getType());
		assertEquals(datastore.getFreeSpace(), 0);
		assertEquals(datastore.getCapacity(), 0);
		assertNull(datastore.getAccessible());
		assertNull(datastore.getMultipleHostAccess());
		assertNull(datastore.getThinProvisioningSupported());
	}
	
	@Test
	public void testConstructeurHalfFirst() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20);
		assertEquals(datastore.getDatastore(), "testDatastore");
		assertEquals(datastore.getName(), "testName");
		assertEquals(datastore.getType(), "testType");
		assertEquals(datastore.getFreeSpace(), 10);
		assertEquals(datastore.getCapacity(), 20);
		assertNull(datastore.getAccessible());
		assertNull(datastore.getMultipleHostAccess());
		assertNull(datastore.getThinProvisioningSupported());
	}
	
	@Test
	public void testConstructeurHalfSecond() {
		DataStore datastore = new DataStore("testAccessible", "testMultipleHostAccess", "testName", "testType",  10, "testThinProvisioningSupported");
		assertNull(datastore.getDatastore());
		assertEquals(datastore.getName(), "testName");
		assertEquals(datastore.getType(), "testType");
		assertEquals(datastore.getFreeSpace(), 10);
		assertEquals(datastore.getCapacity(), 0);
		assertEquals(datastore.getAccessible(), "testAccessible");
		assertEquals(datastore.getMultipleHostAccess(), "testMultipleHostAccess");
		assertEquals(datastore.getThinProvisioningSupported(), "testThinProvisioningSupported");
	}

	@Test
	public void testConstructeurFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getDatastore(), "testDatastore");
		assertEquals(datastore.getName(), "testName");
		assertEquals(datastore.getType(), "testType");
		assertEquals(datastore.getFreeSpace(), 10);
		assertEquals(datastore.getCapacity(), 20);
		assertEquals(datastore.getAccessible(), "testAccessible");
		assertEquals(datastore.getMultipleHostAccess(), "testMultipleHostAccess");
		assertEquals(datastore.getThinProvisioningSupported(), "testThinProvisioningSupported");
	}

	@Test
	public void testGetDatastoreEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getDatastore());
	}

	@Test
	public void testGetDatastoreFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getDatastore(), "testDatastore");
	}

	@Test
	public void testGetNameEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getName());
	}

	@Test
	public void testGetNameFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getName(), "testName");
	}

	@Test
	public void testGetTypeEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getType());
	}

	@Test
	public void testGetTypeFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getType(), "testType");
	}

	@Test
	public void testGetFreeSpaceEmpty() {
		DataStore datastore = new DataStore();
		assertEquals(datastore.getFreeSpace(),0);
	}

	@Test
	public void testGetFreeSpaceFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getFreeSpace(), 10);
	}

	@Test
	public void testGetCapacityEmpty() {
		DataStore datastore = new DataStore();
		assertEquals(datastore.getCapacity(),0);
	}

	@Test
	public void testGetCapacityFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getCapacity(), 20);
	}

	@Test
	public void testGetAccessibleEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getAccessible());
	}

	@Test
	public void testGetAccessibleFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getAccessible(), "testAccessible");
	}

	@Test
	public void testGetMultipleHostAccessEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getMultipleHostAccess());
	}

	@Test
	public void testGetMultipleHostAccessFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getMultipleHostAccess(), "testMultipleHostAccess");
	}

	@Test
	public void testGetThinProvisioningSupportedEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getThinProvisioningSupported());
	}

	@Test
	public void testGetThinProvisioningSupportedFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getThinProvisioningSupported(), "testThinProvisioningSupported");
	}

	@Test
	public void testSetDatastoreEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getDatastore());
		datastore.setDatastore("testDatastore");
		assertEquals(datastore.getDatastore(), "testDatastore");
	}

	@Test
	public void testSetDatastoreFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getDatastore(), "testDatastore");
		datastore.setDatastore("test");
		assertEquals(datastore.getDatastore(), "test");
	}

	@Test
	public void testSetNameEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getName());
		datastore.setName("testName");
		assertEquals(datastore.getName(), "testName");
	}

	@Test
	public void testSetNameFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getName(), "testName");
		datastore.setName("test");
		assertEquals(datastore.getName(), "test");
	}

	@Test
	public void testSetTypeEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getType());
		datastore.setType("testType");
		assertEquals(datastore.getType(), "testType");
	}

	@Test
	public void testSetTypeFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getType(), "testType");
		datastore.setType("test");
		assertEquals(datastore.getType(), "test");
	}

	@Test
	public void testSetFreeSpaceEmpty() {
		DataStore datastore = new DataStore();
		assertEquals(datastore.getFreeSpace(), 0);
		datastore.setFreeSpace(10);
		assertEquals(datastore.getFreeSpace(), 10);
	}

	@Test
	public void testSetFreeSpaceFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getFreeSpace(), 10);
		datastore.setFreeSpace(50);
		assertEquals(datastore.getFreeSpace(), 50);
	}

	@Test
	public void testSetCapacityEmpty() {
		DataStore datastore = new DataStore();
		assertEquals(datastore.getCapacity(),0);
		datastore.setCapacity(10);
		assertEquals(datastore.getCapacity(), 10);
	}

	@Test
	public void testSetCapacityFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getCapacity(), 20);
		datastore.setCapacity(100);
		assertEquals(datastore.getCapacity(), 100);
	}

	@Test
	public void testSetAccessibleEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getAccessible());
		datastore.setAccessible("testAccessible");
		assertEquals(datastore.getAccessible(), "testAccessible");
	}

	@Test
	public void testSetAccessibleFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getAccessible(), "testAccessible");
		datastore.setAccessible("test");
		assertEquals(datastore.getAccessible(), "test");
	}

	@Test
	public void testSetMultipleHostAccessEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getMultipleHostAccess());
		datastore.setMultipleHostAccess("testMultipleHostAccess");
		assertEquals(datastore.getMultipleHostAccess(), "testMultipleHostAccess");
	}

	@Test
	public void testSetMultipleHostAccessFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getMultipleHostAccess(), "testMultipleHostAccess");
		datastore.setMultipleHostAccess("test");
		assertEquals(datastore.getMultipleHostAccess(), "test");
	}

	@Test
	public void testSetThinProvisioningSupportedEmpty() {
		DataStore datastore = new DataStore();
		assertNull(datastore.getMultipleHostAccess());
		datastore.setThinProvisioningSupported("testThinProvisioningSupported");
		assertEquals(datastore.getThinProvisioningSupported(), "testThinProvisioningSupported");
	}

	@Test
	public void testSetThinProvisioningSupportedFull() {
		DataStore datastore = new DataStore("testDatastore", "testName", "testType", 10, 20, "testAccessible", "testMultipleHostAccess", "testThinProvisioningSupported");
		assertEquals(datastore.getThinProvisioningSupported(), "testThinProvisioningSupported");
		datastore.setThinProvisioningSupported("test");
		assertEquals(datastore.getThinProvisioningSupported(), "test");
	}
}
