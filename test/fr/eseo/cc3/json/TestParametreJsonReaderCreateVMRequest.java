package fr.eseo.cc3.json;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.eseo.cc3.model.Host;

@RunWith(Parameterized.class)
public class TestParametreJsonReaderCreateVMRequest {


	private String os;
	private String name;
	private String config;
	private String datastore;
	private String requestAttendu;
	 
	  public TestParametreJsonReaderCreateVMRequest(String pName,String pOs, String pDatastore, String pConfig, String pRequest) {
	    	this.name = pName;
	    	this.os = pOs;
	    	this.datastore = pDatastore;
	    	this.config = pConfig;
	    	this.requestAttendu = pRequest;

	    }
	   


		@Parameterized.Parameters
	    public static List<Object[]> getParametres() {
	        return Arrays.asList(new Object[][] {
                	{"nameLow","osLow","datastoreLow","low","{\"spec\":{\"memory\":{\"size_MiB\":1024,\"hot_add_enabled\":true},\"name\":\"nameLow\",\"cpu\":{\"hot_remove_enabled\":true,\"count\":1,\"hot_add_enabled\":true,\"cores_per_socket\":1},\"placement\":{\"folder\":\"group-v22\",\"datastore\":\"datastoreLow\"},\"guest_OS\":\"osLow\"}}"},
	                {"nameDefault","osDefault","datastoreDefault","configDefault","{\"spec\":{\"memory\":{\"size_MiB\":4096,\"hot_add_enabled\":true},\"name\":\"nameDefault\",\"cpu\":{\"hot_remove_enabled\":true,\"count\":1,\"hot_add_enabled\":true,\"cores_per_socket\":1},\"placement\":{\"folder\":\"group-v22\",\"datastore\":\"datastoreDefault\"},\"guest_OS\":\"osDefault\"}}"},
	                {"nameMin","osMin","datastoreMin","min","{\"spec\":{\"memory\":{\"size_MiB\":4096,\"hot_add_enabled\":true},\"name\":\"nameMin\",\"cpu\":{\"hot_remove_enabled\":true,\"count\":1,\"hot_add_enabled\":true,\"cores_per_socket\":1},\"placement\":{\"folder\":\"group-v22\",\"datastore\":\"datastoreMin\"},\"guest_OS\":\"osMin\"}}"},
	                {"nameMid","osMid","datastoreMid","mid","{\"spec\":{\"memory\":{\"size_MiB\":4096,\"hot_add_enabled\":true},\"name\":\"nameMid\",\"cpu\":{\"hot_remove_enabled\":true,\"count\":2,\"hot_add_enabled\":true,\"cores_per_socket\":1},\"placement\":{\"folder\":\"group-v22\",\"datastore\":\"datastoreMid\"},\"guest_OS\":\"osMid\"}}"},
	                {"nameHigh","osHigh","datastoreHigh","high","{\"spec\":{\"memory\":{\"size_MiB\":8192,\"hot_add_enabled\":true},\"name\":\"nameHigh\",\"cpu\":{\"hot_remove_enabled\":true,\"count\":2,\"hot_add_enabled\":true,\"cores_per_socket\":1},\"placement\":{\"folder\":\"group-v22\",\"datastore\":\"datastoreHigh\"},\"guest_OS\":\"osHigh\"}}"},
	                {"nameUltra","osUltra","datastoreUltra","ultra","{\"spec\":{\"memory\":{\"size_MiB\":8192,\"hot_add_enabled\":true},\"name\":\"nameUltra\",\"cpu\":{\"hot_remove_enabled\":true,\"count\":2,\"hot_add_enabled\":true,\"cores_per_socket\":1},\"placement\":{\"folder\":\"group-v22\",\"datastore\":\"datastoreUltra\"},\"guest_OS\":\"osUltra\"}}"},

	        });
	    }
	
	@Test
	
	public void testCreateVmRequest() {
		
		JsonReader reader = new JsonReader();
		
		String request = reader.createVMRequest(name, os, datastore, config);
		
		
		
		assertEquals("request",request, requestAttendu);
		
	}
}
