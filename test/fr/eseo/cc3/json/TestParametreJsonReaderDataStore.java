package fr.eseo.cc3.json;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.eseo.cc3.json.TestParametreJsonReaderHost.Type;
import fr.eseo.cc3.model.DataCenter;
import fr.eseo.cc3.model.DataStore;

@RunWith(Parameterized.class)
public class TestParametreJsonReaderDataStore {enum Type {SUBSTRACT, ADD}

	 public  int nbElementAttendu;
	 public String dataStoreName1Attendu;
	 public String dataStoreName2Attendu;
	 public String dataStore1Attendu;
	 public String dataStore2Attendu;
	 public String stream;
	 public Type type;
	 
	  public TestParametreJsonReaderDataStore(Type pType,int pNbElementAttendu,String pDataStoreName1Attendu, String pDataStoreName2Attendu, String pDataStore1Attendu, String pDataStore2Attendu, String pStream) {
	    	this.nbElementAttendu = pNbElementAttendu;
	    	this.dataStoreName1Attendu = pDataStoreName1Attendu;
	    	this.dataStoreName2Attendu = pDataStoreName2Attendu;
	    	this.dataStore1Attendu = pDataStore1Attendu;
	    	this.dataStore2Attendu = pDataStore2Attendu;
	    	this.stream = pStream;
	    	this.type = pType;

	    }
	   


		@Parameterized.Parameters
	    public static List<Object[]> getParametres() {
	        return Arrays.asList(new Object[][] {
	                {Type.ADD,2,"datastore1-33","datastore1-34","datastore-13","datastore-16","{\"value\":[{\"datastore\":\"datastore-13\",\"name\":\"datastore1-33\",\"type\":\"VMFS\",\"free_space\":25270681600,\"capacity\":34896609280},{\"datastore\":\"datastore-16\",\"name\":\"datastore1-34\",\"type\":\"VMFS\",\"free_space\":29269950464,\"capacity\":34896609280}]}"},
	                {Type.ADD,1, "datastore1-33", "", "datastore-13","","{\"value\":[{\"datastore\":\"datastore-13\",\"name\":\"datastore1-33\",\"type\":\"VMFS\",\"free_space\":25270681600,\"capacity\":34896609280}]}"},
	                {Type.SUBSTRACT,1, "none", "", "none","","{\"value\":[{}]}"},

	        });
	    }
	
	@Test
	
	public void testDataStore() {
		Assume.assumeTrue(type == Type.ADD);
		
		JsonReader reader = new JsonReader();
		
		ArrayList<DataStore> dataStoreRecu = new ArrayList<DataStore>();
		
		dataStoreRecu= reader.getListDataStore(stream);
		int nbElementRecu =  dataStoreRecu.size();
		String dataStoreName1Recu = dataStoreRecu.get(0).getName();
		String dataStore1Recu = dataStoreRecu.get(0).getDatastore();
		
		assertEquals("longueur liste datastore",nbElementAttendu, nbElementRecu);
		assertEquals("Premier nom datastore",dataStoreName1Attendu, dataStoreName1Recu);
		assertEquals("Premier datastore",dataStore1Attendu, dataStore1Recu);
		
		if(nbElementRecu >1) {
			String dataStoreName2Recu = dataStoreRecu.get(1).getName();
			String dataStore2Recu =  dataStoreRecu.get(1).getDatastore();
			
			assertEquals("Deuxieme nom datastore",dataStoreName2Attendu, dataStoreName2Recu);
			assertEquals("Deuxi�me datastore",dataStore2Attendu, dataStore2Recu);
		}
		
		
		
	}
	
	
@Test
	
	public void testDataStoreException() {
		Assume.assumeTrue(type == Type.SUBSTRACT);
		
		JsonReader reader = new JsonReader();
		
		ArrayList<DataStore> dataStoreRecu = new ArrayList<DataStore>();
		
		dataStoreRecu= reader.getListDataStore(stream);
		int nbElementRecu =  dataStoreRecu.size();
		String dataStoreName1Recu = dataStoreRecu.get(0).getName();
		String dataStore1Recu = dataStoreRecu.get(0).getDatastore();
		
		assertEquals("longueur liste datastore",nbElementAttendu, nbElementRecu);
		assertEquals("Premier nom datastore",dataStoreName1Attendu, dataStoreName1Recu);
		assertEquals("Premier datastore",dataStore1Attendu, dataStore1Recu);
		
	}
}
