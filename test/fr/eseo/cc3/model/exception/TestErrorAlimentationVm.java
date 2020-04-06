package fr.eseo.cc3.model.exception;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class TestErrorAlimentationVm {
	
	@Test
	public void testConstructeur() {
		ExceptionAlimentationVm vm = new ExceptionAlimentationVm("testName",500);
		assertEquals(vm.getVmName(),"testName");
		assertEquals(vm.getCodeErreur(),500);

	}
	
	@Test
	public void testGetVmName() {
		ExceptionAlimentationVm vm = new ExceptionAlimentationVm("testName",500);
		assertEquals(vm.getVmName(),"testName");
	}
	
	@Test
	public void testGetCodeErreur() {
		ExceptionAlimentationVm vm = new ExceptionAlimentationVm("testName",500);
		assertEquals(vm.getCodeErreur(),500);
	}

}
