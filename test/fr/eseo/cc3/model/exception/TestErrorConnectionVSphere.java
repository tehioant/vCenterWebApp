package fr.eseo.cc3.model.exception;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class TestErrorConnectionVSphere {
	
	@Test
	public void testConstructeurFull() {
		ExceptionConnectionVSphere connection = new ExceptionConnectionVSphere("testMessage",500,"testAction");
		assertEquals(connection.getAction(),"testAction");
		assertEquals(connection.getCodeErreur(),500);

	}
	
	@Test
	public void testConstructeurMessage() {
		ExceptionConnectionVSphere connection = new ExceptionConnectionVSphere("testMessage");
		assertEquals(connection.getAction(),"erreur interne");
		assertEquals(connection.getCodeErreur(),1000);

	}
	
	@Test
	public void testGetAction() {
		ExceptionConnectionVSphere connection = new ExceptionConnectionVSphere("testName");
		assertEquals(connection.getAction(),"erreur interne");
	}
	
	@Test
	public void testGetCodeErreur() {
		ExceptionConnectionVSphere connection = new ExceptionConnectionVSphere("testName");
		assertEquals(connection.getCodeErreur(),1000);
	}

}
