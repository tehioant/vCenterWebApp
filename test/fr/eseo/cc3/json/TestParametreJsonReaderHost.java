package fr.eseo.cc3.json;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.eseo.cc3.json.TestParametreJsonReaderVm.Type;
import fr.eseo.cc3.model.Host;

@RunWith(Parameterized.class)
public class TestParametreJsonReaderHost {enum Type {SUBSTRACT, ADD}
	
	 public  int nbElementAttendu;
	 public String hostName1Attendu;
	 public String hostName2Attendu;
	 public String host1Attendu;
	 public String host2Attendu;
	 public String stream;
	 public Type type;
	 
	  public TestParametreJsonReaderHost(Type pType,int pNbElementAttendu,String phostName1Attendu, String phostName2Attendu, String phost1Attendu, String phost2Attendu, String pStream) {
	    	this.nbElementAttendu = pNbElementAttendu;
	    	this.hostName1Attendu = phostName1Attendu;
	    	this.hostName2Attendu = phostName2Attendu;
	    	this.host1Attendu = phost1Attendu;
	    	this.host2Attendu = phost2Attendu;
	    	this.stream = pStream;
	    	this.type = pType;

	    }
	   


		@Parameterized.Parameters
	    public static List<Object[]> getParametres() {
	        return Arrays.asList(new Object[][] {
	                {Type.ADD,2,"192.168.4.33","192.168.4.34","host-12","host-15","{\"value\":[{\"host\":\"host-12\",\"name\":\"192.168.4.33\",\"connection_state\":\"CONNECTED\",\"power_state\":\"POWERED_ON\"},{\"host\":\"host-15\",\"name\":\"192.168.4.34\",\"connection_state\":\"CONNECTED\",\"power_state\":\"POWERED_ON\"}]}"},
	                {Type.ADD,1, "192.168.4.33", "", "host-12","","{\"value\":[{\"host\":\"host-12\",\"name\":\"192.168.4.33\",\"connection_state\":\"CONNECTED\",\"power_state\":\"POWERED_ON\"}]}"},
	                {Type.SUBSTRACT,1, "none", "", "none","","{\"value\":[{}]}"},

	        });
	    }
	
	@Test
	
	public void testHost() {
		Assume.assumeTrue(type == Type.ADD);
		
		JsonReader reader = new JsonReader();
		
		ArrayList<Host> hostRecu = new ArrayList<Host>();
		
		hostRecu= reader.getListHosts(stream);
		int nbElementRecu =  hostRecu.size();
		String hostName1Recu = hostRecu.get(0).getName();
		String host1Recu = hostRecu.get(0).getHost();
		
		assertEquals("longueur liste host",nbElementAttendu, nbElementRecu);
		assertEquals("Premier nom host",hostName1Attendu, hostName1Recu);
		assertEquals("Premier host",host1Attendu, host1Recu);
		
		if(nbElementRecu >1) {
			String hostName2Recu = hostRecu.get(1).getName();
			String host2Recu =  hostRecu.get(1).getHost();
			
			assertEquals("Deuxieme nom host",hostName2Attendu, hostName2Recu);
			assertEquals("Deuxi�me host",host2Attendu, host2Recu);
		}
	}
		
		@Test
		
		public void testHostException() {
			Assume.assumeTrue(type == Type.SUBSTRACT);
			
			JsonReader reader = new JsonReader();
			
			ArrayList<Host> hostRecu = new ArrayList<Host>();
			
			hostRecu= reader.getListHosts(stream);
			int nbElementRecu =  hostRecu.size();
			String hostName1Recu = hostRecu.get(0).getName();
			String host1Recu = hostRecu.get(0).getHost();
			
			assertEquals("longueur liste host",nbElementAttendu, nbElementRecu);
			assertEquals("Premier nom host",hostName1Attendu, hostName1Recu);
			assertEquals("Premier host",host1Attendu, host1Recu);
				
		
	}

}
