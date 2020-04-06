package fr.eseo.cc3.model.vm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestBoot {


		
		
		@Test
		public void testDelay() {
			Boot boot = new Boot();
			boot.setDelay(10);
			assertEquals(boot.getDelay().intValue(),10);
		}
		
		@Test
		public void testRetryDelay() {
			Boot boot = new Boot();
			boot.setRetryDelay(20);
			assertEquals(boot.getRetryDelay().intValue(),20);
		}
		
		@Test
		public void testEnterSetupMode() {
			Boot boot = new Boot();
			boot.setEnterSetupMode(true);
			assertTrue(boot.getEnterSetupMode());
		}
		
		@Test
		public void testType() {
			Boot boot = new Boot();
			boot.setType("testType");
			assertEquals(boot.getType(),"testType");
		}
		
		@Test
		public void testRetry() {
			Boot boot = new Boot();
			boot.setRetry(true);
			assertTrue(boot.getRetry());
		}
		

	}


