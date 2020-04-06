package fr.eseo.cc3.clientapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;


public class TestParametreClientVSphereLoginLogout {
	
	ClientVSphere connection = new ClientVSphere("etudiant@vsphere.bac", "N3twork!");
	
	@Test
    public void testLoginLogout() throws IOException, ExceptionConnectionVSphere, NoSuchMethodException{
		
	
		
		/* Pour les accees a� la methode privee. */
    	String methodeTestee = "login";
    	Class<?>[] parametresMethodetestee = {};
    			
    	java.lang.reflect.Method methode;
    	
    	try {
    		/* Accees a� la methode. */
    		
    	methode = ClientVSphere.class.getDeclaredMethod(methodeTestee, parametresMethodetestee);
    	methode.setAccessible(true);
    	methode.invoke(this.connection);
		
		String tokenConnection = connection.getToken();
		assertNotNull(tokenConnection);
		
		connection.logout();
		String tokenDeconnection = connection.getToken();
		assertNull(tokenDeconnection);
		
    	} catch (SecurityException e) {
		fail("Probleme de securitee sur la reflexion.");
		} catch (IllegalArgumentException e) {
			fail("Arguments de la me�thode de reflexion invalides.");
		} catch (IllegalAccessException e) {
			fail("Accees illegal a� la methode testee.");
		} catch (InvocationTargetException e) {
			fail("Probleme d'invocation de la methode testee.");

		}
	}
}
