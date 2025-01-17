package fr.eseo.cc3.clientapi;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;



@RunWith(Parameterized.class) 
public class TestParametreClientVsphereGetDataErreur {enum Type {SUBSTRACT, ADD}

	 ClientVSphere connection = new ClientVSphere("etudiant@vsphere.bac", "N3twork!");
	 public  String requeteEnvoye;
	 public  String reponseARecevoir;
	 public  Type type;
	 
	  public TestParametreClientVsphereGetDataErreur(Type pType, String pRequeteEnvoye, String pReponseARecevoir) {
		  this.requeteEnvoye = pRequeteEnvoye;
		  this.reponseARecevoir = pReponseARecevoir;
		  this.type = pType;

	    }
	   


		@Parameterized.Parameters
	    public static List<Object[]> getParametres() {
	        return Arrays.asList(new Object[][] {
	        	{Type.SUBSTRACT, ".rest.vcenter.vm", "{\"value\":[{\"memory_size_MiB\":512,\"vm\":\"vm-51\",\"name\":\"CC3_TEST\",\"power_state\":\"POWERED_OFF\",\"cpu_count\":2},{\"memory_size_MiB\":1024,\"vm\":\"vm-529\",\"name\":\"CCgrp8\",\"power_state\":\"POWERED_ON\",\"cpu_count\":1},{\"memory_size_MiB\":1024,\"vm\":\"vm-53\",\"name\":\"Nouvelle machine virtuelle\",\"power_state\":\"POWERED_ON\",\"cpu_count\":1},{\"memory_size_MiB\":512,\"vm\":\"vm-84\",\"name\":\"test\",\"power_state\":\"POWERED_OFF\",\"cpu_count\":1}]}\n"},
                { Type.SUBSTRACT,"restvcenterhost", "{\"value\":[{\"host\":\"host-12\",\"name\":\"192.168.4.33\",\"connection_state\":\"CONNECTED\",\"power_state\":\"POWERED_ON\"},{\"host\":\"host-15\",\"name\":\"192.168.4.34\",\"connection_state\":\"CONNECTED\",\"power_state\":\"POWERED_ON\"},{\"host\":\"host-17\",\"name\":\"192.168.4.27\",\"connection_state\":\"CONNECTED\",\"power_state\":\"POWERED_ON\"}]}\n"},
                { Type.SUBSTRACT,"rest,vcenter,cluster", "{\"value\":[{\"drs_enabled\":false,\"cluster\":\"domain-c144\",\"name\":\"Cluster_test\",\"ha_enabled\":false},{\"drs_enabled\":true,\"cluster\":\"domain-c7\",\"name\":\"Cluster-Production\",\"ha_enabled\":true},{\"drs_enabled\":true,\"cluster\":\"domain-c9\",\"name\":\"Cluster-Dev\",\"ha_enabled\":true}]}\n"},
                { Type.SUBSTRACT,";rest;vcenter;datacenter", "{\"value\":[{\"name\":\"Cluster_IIT2\",\"datacenter\":\"datacenter-139\"},{\"name\":\"Data-center\",\"datacenter\":\"datacenter-2\"}]}\n"}, 
                {Type.ADD, "/rest/vcenter/datastore", ""}, 
                { Type.ADD,"/rest/vcenter/network", "azeerty46516"}, 
                
        });
	    }
	    
	    @Rule
	    public ExpectedException thrown = ExpectedException.none();

	    
	    @Test(expected=UnknownHostException.class)
	    public void testGetDataRequeteEnvoie() throws IOException, ExceptionConnectionVSphere, NoSuchMethodException {
	    	 Assume.assumeTrue(type == Type.SUBSTRACT);

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

	    	assertNotSame("requete conforme.",reponseRequeteCreer , reponseARecevoir);
	    	
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
	    
	    @Test
	    public void testGetDataReponseARecevoir() throws IOException, ExceptionConnectionVSphere, NoSuchMethodException {
	    	 Assume.assumeTrue(type == Type.ADD);

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

	    	assertNotSame("requete conforme.",reponseRequeteCreer , reponseARecevoir);
	    	
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
