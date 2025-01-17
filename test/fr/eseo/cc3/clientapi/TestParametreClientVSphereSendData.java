package fr.eseo.cc3.clientapi;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.VirtualMachine;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

@RunWith(Parameterized.class)
public class TestParametreClientVSphereSendData {

	
	 ClientVSphere connection = new ClientVSphere("etudiant@vsphere.bac", "N3twork!");
	 static JsonReader reader = new JsonReader();
	 public String methode;
	 public String request;
	 public String body;
	 public String reponseAttendu;

	  public TestParametreClientVSphereSendData(String pMethode,String pRequest,String pBody) {
	    	this.methode = pMethode;
	    	this.request = pRequest;
	    	this.body = pBody;

	    }
	  
	  	static String requeteEnvoyer = reader.createVMRequest("nameLow1", "DEBIAN_10", "datastore-32", "low");
	   


		@Parameterized.Parameters
	    public static List<Object[]> getParametres() {
	        return Arrays.asList(new Object[][] {
	                {"POST",CONFIGURATION_API.getString("GET_VM"),requeteEnvoyer},

	                
	        });
	    }
	    
	    private static final ResourceBundle CONFIGURATION_API = ResourceBundle.getBundle("configAPI");
	
	    
	    @Test
	    public void testASendData() throws IOException, ExceptionConnectionVSphere{
	    	
	    	connection.getData(request);
	    	connection.sendData(methode, request, body);
	    	
	    	String requeteVmCible = CONFIGURATION_API.getString("GET_VM");
	    	
	    	String reponseVmCible = connection.getData(requeteVmCible);
	    	ArrayList<VirtualMachine> vmCible = reader.getListVirtualMachine(reponseVmCible);
	    	
	    	Boolean vmCreer = false;
	    	
	    	
	    	for (int i=0; i< vmCible.size();i++) {
	    		String nomCreer = vmCible.get(i).getName();
	    		String nomSupprimer = vmCible.get(i).getVm();
	    		if(nomCreer.equals("nameLow1")) {
	    			 vmCreer = true;
	    			 String requete = CONFIGURATION_API.getString("GET_VM").trim()+"/"+nomSupprimer;
	    			 connection.deleteData(requete);;
	    		}
	    		
	    	}
	    	assertTrue(vmCreer);
	    	
	    }	
	    	
	    @Test
	 	 public void testSupprimeVm() throws IOException, ExceptionConnectionVSphere{
	    	
	    	String requeteVmCible = CONFIGURATION_API.getString("GET_VM");
	    	String reponseVmCible = connection.getData(requeteVmCible);
	    	ArrayList<VirtualMachine> vmCibleSupprimer = reader.getListVirtualMachine(reponseVmCible);
	    	Boolean vmSupprimer = true;
	    	
	    	for (int i=0; i< vmCibleSupprimer.size();i++) {
	    		String nomSupprimer = vmCibleSupprimer.get(i).getName();
	    		if(nomSupprimer.equals("nameLow1")) {
	    			vmSupprimer = false;
	    		}
	    		
	    	}
	    	assertTrue(vmSupprimer);
	    	
	    }
}
