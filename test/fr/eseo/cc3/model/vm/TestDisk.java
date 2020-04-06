package fr.eseo.cc3.model.vm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestDisk {

	@Test
	public void testScsi_bus() {
		Disk disk = new Disk();
		disk.setScsi_bus(10);
		assertEquals(disk.getScsi_bus(),10);
	}
	
	@Test
	public void testScsi_unit() {
		Disk disk = new Disk();
		disk.setScsi_unit(10);
		assertEquals(disk.getScsi_unit(),10);
	}
	
	@Test
	public void testBacking_vmdk_file() {
		Disk disk = new Disk();
		disk.setBacking_vmdk_file("testBacking_vmdk_file");
		assertEquals(disk.getBacking_vmdk_file(),"testBacking_vmdk_file");
	}
	
	@Test
	public void testBacking_type() {
		Disk disk = new Disk();
		disk.setBacking_type("testBacking_type");
		assertEquals(disk.getBacking_type(),"testBacking_type");
	}
	
	@Test
	public void testLabel() {
		Disk disk = new Disk();
		disk.setLabel("testLabel");
		assertEquals(disk.getLabel(),"testLabel");
	}
	
	@Test
	public void testType() {
		Disk disk = new Disk();
		disk.setType("testType");
		assertEquals(disk.getType(),"testType");
	}
	
	@Test
	public void testCapacity() {
		Disk disk = new Disk();
		disk.setCapacity(10);
		assertEquals(disk.getCapacity(),10);
	}
	
	@Test
	public void testKey() {
		Disk disk = new Disk();
		disk.setKey(10);
		assertEquals(disk.getKey(),10);
	}
}
