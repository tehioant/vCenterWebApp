package fr.eseo.cc3.json;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestParametreJsonReaderUpdateVMRequest {
	
	private String type;
	private String parameter;
	private String requestAttendu;

	 
	  public TestParametreJsonReaderUpdateVMRequest(String pType,String pParameter, String pRequestAttendu) {
	    	this.type = pType;
	    	this.parameter = pParameter;
	    	this.requestAttendu = pRequestAttendu;

	    }
	   


		@Parameterized.Parameters
	    public static List<Object[]> getParametres() {
	        return Arrays.asList(new Object[][] {
	                {"typeDefault","parameterDefault","{}"},
	                {"cpu","parameterCpu","{\"spec\":{\"hot_remove_enabled\":true,\"count\":\"parameterCpu\",\"hot_add_enabled\":true,\"cores_per_socket\":1}}"},
	                {"memory","parameterMemory","{\"spec\":{\"size_MiB\":\"parameterMemory\",\"hot_add_enabled\":true}}"},

	        });
	    }
	
	@Test
	
	public void testHost() {
		
		JsonReader reader = new JsonReader();
		
		String request = reader.updateVMRequest(type,parameter);
		
		
		assertEquals("request",request, requestAttendu);
		
	}

}
