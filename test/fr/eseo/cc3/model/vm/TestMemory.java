package fr.eseo.cc3.model.vm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestMemory {
	
	@Test
	public void testConstructeur() {
		Memory memory = new Memory(10);
		assertEquals(memory.getSize_MiB(),10);
	}

	@Test
	public void testSize_MiB() {
		Memory memory = new Memory();
		memory.setSize_MiB(10);
		assertEquals(memory.getSize_MiB(),10);
	}
	
	@Test
	public void testHot_add_enabled() {
		Memory memory = new Memory();
		memory.setHot_add_enabled(true);
		assertTrue(memory.getHot_add_enabled());
	}
}
