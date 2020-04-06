package fr.eseo.cc3.clientapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;


@RunWith(Parameterized.class)
public class TestParametreClientVSphereGetData {


		 ClientVSphere connection = new ClientVSphere("etudiant@vsphere.bac", "N3twork!");
		 public  String requeteEnvoye;
		 public  String reponseARecevoir;
		 
		  public TestParametreClientVSphereGetData(String pRequeteEnvoye, String pReponseARecevoir) {
			  this.requeteEnvoye = pRequeteEnvoye;
			  this.reponseARecevoir = pReponseARecevoir;

		    }
		   


			@Parameterized.Parameters
		    public static List<Object[]> getParametres() {
		        return Arrays.asList(new Object[][] {
		                { "/rest/vcenter/host", "{\"value\":[{\"host\":\"host-31\",\"name\":\"192.168.4.33\",\"connection_state\":\"CONNECTED\",\"power_state\":\"POWERED_ON\"},{\"host\":\"host-37\",\"name\":\"192.168.4.34\",\"connection_state\":\"CONNECTED\",\"power_state\":\"POWERED_ON\"},{\"host\":\"host-41\",\"name\":\"192.168.4.27\",\"connection_state\":\"CONNECTED\",\"power_state\":\"POWERED_ON\"}]}\n"},
		                { "/rest/vcenter/cluster", "{\"value\":[{\"drs_enabled\":false,\"cluster\":\"domain-c26\",\"name\":\"Production\",\"ha_enabled\":true},{\"drs_enabled\":false,\"cluster\":\"domain-c28\",\"name\":\"Test\",\"ha_enabled\":false}]}\n"},
		                { "/rest/vcenter/datacenter", "{\"value\":[{\"name\":\"Datacenter-BacASable\",\"datacenter\":\"datacenter-21\"}]}\n"}, 
		                { "/rest/vcenter/network", "{\"value\":[{\"name\":\"GourpePorts\",\"type\":\"DISTRIBUTED_PORTGROUP\",\"network\":\"dvportgroup-128\"},{\"name\":\"autoportgroup2\",\"type\":\"DISTRIBUTED_PORTGROUP\",\"network\":\"dvportgroup-1497\"},{\"name\":\"LiaisonMontante\",\"type\":\"DISTRIBUTED_PORTGROUP\",\"network\":\"dvportgroup-124\"},{\"name\":\"autoportgroup4\",\"type\":\"DISTRIBUTED_PORTGROUP\",\"network\":\"dvportgroup-1500\"},{\"name\":\"VM Network\",\"type\":\"STANDARD_PORTGROUP\",\"network\":\"network-33\"},{\"name\":\"PortsBloques\",\"type\":\"DISTRIBUTED_PORTGROUP\",\"network\":\"dvportgroup-129\"},{\"name\":\"autoportgroup\",\"type\":\"DISTRIBUTED_PORTGROUP\",\"network\":\"dvportgroup-1458\"},{\"name\":\"Service\",\"type\":\"DISTRIBUTED_PORTGROUP\",\"network\":\"dvportgroup-791\"},{\"name\":\"autoportgroup3\",\"type\":\"DISTRIBUTED_PORTGROUP\",\"network\":\"dvportgroup-1498\"},{\"name\":\"DPortGroup\",\"type\":\"DISTRIBUTED_PORTGROUP\",\"network\":\"dvportgroup-125\"}]}\n"}, 
		                { "/rest/vcenter/folder", "{\"value\":[{\"folder\":\"group-d1\",\"name\":\"Datacenters\",\"type\":\"DATACENTER\"},{\"folder\":\"group-h23\",\"name\":\"host\",\"type\":\"HOST\"},{\"folder\":\"group-n25\",\"name\":\"network\",\"type\":\"NETWORK\"},{\"folder\":\"group-s24\",\"name\":\"datastore\",\"type\":\"DATASTORE\"},{\"folder\":\"group-v22\",\"name\":\"vm\",\"type\":\"VIRTUAL_MACHINE\"},{\"folder\":\"group-v30\",\"name\":\"Discovered virtual machine\",\"type\":\"VIRTUAL_MACHINE\"}]}\n"}, 
		                
		        });
		    }
		    
		    @Test
		    public void testGetData() throws IOException, ExceptionConnectionVSphere, NoSuchMethodException {
		    	

		    	/* Pour les accees a� la methode privee. */
		    	String methodeTestee = "login";
		    	Class<?>[] parametresMethodetestee = {};
		    			
		    	java.lang.reflect.Method methode;
		    	
		    	try {
		    		/* Accees a� la methode. */
		    		
		    	methode = ClientVSphere.class.getDeclaredMethod(methodeTestee, parametresMethodetestee);
		    	methode.setAccessible(true);
		    	methode.invoke(this.connection);
	
		    	String reponseRequeteCreer = this.connection.getData(requeteEnvoye);		    			
		    	/* Assertion. */

		    	assertEquals("requete conforme.",reponseRequeteCreer , reponseARecevoir);
		    	
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

