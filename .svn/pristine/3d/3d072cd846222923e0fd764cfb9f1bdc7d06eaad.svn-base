package fr.eseo.cc3.model.vm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCpu {
	
	@Test
	public void testConstructeur() {
		Cpu cpu = new Cpu(10);
		assertEquals(cpu.getCount(),10);
	}
	
	@Test
	public void testHot_remove_enabled() {
		Cpu cpu = new Cpu();
		cpu.setHot_remove_enabled(true);
		assertTrue(cpu.getHot_remove_enabled());
	}

	@Test
	public void testHot_add_enabled() {
		Cpu cpu = new Cpu();
		cpu.setHot_add_enabled(true);
		assertTrue(cpu.getHot_add_enabled());
	}
	
	@Test
	public void testCount() {
		Cpu cpu = new Cpu();
		cpu.setCount(10);
		assertEquals(cpu.getCount(),10);
	}
	
	@Test
	public void testCores_per_socket() {
		Cpu cpu = new Cpu();
		cpu.setCores_per_socket(10);
		assertEquals(cpu.getCores_per_socket(),10);
	}
}
