package fr.eseo.cc3.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assume;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.eseo.cc3.model.VirtualMachine;

@RunWith(Parameterized.class)
public class TestParametreJsonReaderVm {enum Type {SUBSTRACT, ADD}

	 public  int nbElementAttendu;
	 public String vmName1Attendu;
	 public String vmName2Attendu;
	 public String vm1Attendu;
	 public String vm2Attendu;
	 public String stream;
	 public int memory_size_MiB1Attendu;
	 public int memory_size_MiB2Attendu;
	 public String power_state1Attendu;
	 public String power_state2Attendu;
	 public int cpu_count1Attendu;
	 public int cpu_count2Attendu;
	 public Type type;
	 
	 
	 
	 
	  public TestParametreJsonReaderVm(Type pType,int pNbElementAttendu,String pvmName1Attendu, String pvmName2Attendu, String pvm1Attendu, String pvm2Attendu, String pStream, int pMemery_size_MIB1,int pMemery_size_MIB2, String pPower_state1,String pPower_state2, int pCpu_count1, int pCpu_count2 ) {
	    	this.nbElementAttendu = pNbElementAttendu;
	    	this.vmName1Attendu = pvmName1Attendu;
	    	this.vmName2Attendu = pvmName2Attendu;
	    	this.vm1Attendu = pvm1Attendu;
	    	this.vm2Attendu = pvm2Attendu;
	    	this.stream = pStream;
	    	this.memory_size_MiB1Attendu = pMemery_size_MIB1;
	    	this.memory_size_MiB2Attendu = pMemery_size_MIB2;
	    	this.power_state1Attendu = pPower_state1;
	    	this.power_state2Attendu = pPower_state2;
	    	this.cpu_count1Attendu = pCpu_count1;
	    	this.cpu_count2Attendu = pCpu_count2;
	    	this.type =pType;
	    	

	    }


		@Parameterized.Parameters
	    public static List<Object[]> getParametres() {
	        return Arrays.asList(new Object[][] {
	                {Type.ADD,2,"CC3_TEST","CCgrp8","vm-51","vm-529","{\"value\":[{\"memory_size_MiB\":512,\"vm\":\"vm-51\",\"name\":\"CC3_TEST\",\"power_state\":\"POWERED_ON\",\"cpu_count\":2},{\"memory_size_MiB\":1024,\"vm\":\"vm-529\",\"name\":\"CCgrp8\",\"power_state\":\"POWERED_ON\",\"cpu_count\":1}]}",512,1024,"POWERED_ON","POWERED_ON",2,1},
	                {Type.ADD,1, "CC3_TEST", "", "vm-51","","{\"value\":[{\"memory_size_MiB\":512,\"vm\":\"vm-51\",\"name\":\"CC3_TEST\",\"power_state\":\"POWERED_ON\",\"cpu_count\":2}]}",512,0,"POWERED_ON","",2,0},
	                {Type.SUBSTRACT,1, "none", "", "none","","{\"value\":[{}]}",0,0,"none","",0,0},

	        });
	    }
	
	@Test
	
	public void testvm() {
		Assume.assumeTrue(type == Type.ADD);
		
		JsonReader reader = new JsonReader();
		
		ArrayList<VirtualMachine> vmRecu = new ArrayList<VirtualMachine>();
		
		vmRecu= reader.getListVirtualMachine(stream);
		
		int nbElementRecu =  vmRecu.size();
		String vmName1Recu = vmRecu.get(0).getName();
		String vm1Recu = vmRecu.get(0).getVm();
		int memory_size_MiB1Recu =  vmRecu.get(0).getMemorySizeMIB().getSize_MiB();
		String power_state1Recu = vmRecu.get(0).getPowerState();
		int cpu_count1Recu = vmRecu.get(0).getCpu().getCount();
		
		assertEquals("longueur liste vm",nbElementAttendu, nbElementRecu);
		assertEquals("Premier nom vm",vmName1Attendu, vmName1Recu);
		assertEquals("Premier vm",vm1Attendu, vm1Recu);
		assertEquals("Premier memory_size_MIB",memory_size_MiB1Attendu, memory_size_MiB1Recu);
		assertEquals("Premier power_state",power_state1Attendu, power_state1Recu);
		assertEquals("Premier cpu_count",cpu_count1Attendu, cpu_count1Recu);
		
		if(nbElementRecu >1) {
			String vmName2Recu = vmRecu.get(1).getName();
			String vm2Recu =  vmRecu.get(1).getVm();
			int memory_size_MiB2Recu = vmRecu.get(1).getMemorySizeMIB().getSize_MiB();
			String power_state2Recu = vmRecu.get(1).getPowerState();
			int cpu_count2Recu = vmRecu.get(1).getCpu().getCount();
			
			assertEquals("Deuxieme nom vm",vmName2Attendu, vmName2Recu);
			assertEquals("Deuxi�me vm",vm2Attendu, vm2Recu);
			assertEquals("Deuxi�me memory_size_MIB",memory_size_MiB2Attendu, memory_size_MiB2Recu);
			assertEquals("Deuxi�me power_state",power_state2Attendu, power_state2Recu);
			assertEquals("Deuxi�me power_state1Recu",cpu_count2Attendu, cpu_count2Recu);
		}
		
		
		 
	}
	
	@Test    
	public void testvmException() {
		Assume.assumeTrue(type == Type.SUBSTRACT);


		JsonReader reader = new JsonReader();

		ArrayList<VirtualMachine> vmRecu = new ArrayList<VirtualMachine>();

		vmRecu= reader.getListVirtualMachine(stream);

		int nbElementRecu =  vmRecu.size();
		String vmName1Recu = vmRecu.get(0).getName();
		String vm1Recu = vmRecu.get(0).getVm();
		int memory_size_MiB1Recu =  vmRecu.get(0).getMemorySizeMIB().getSize_MiB();
		String power_state1Recu = vmRecu.get(0).getPowerState();
		int cpu_count1Recu = vmRecu.get(0).getCpu().getCount();

		assertEquals("longueur liste vm",nbElementAttendu, nbElementRecu);
		assertEquals("Premier nom vm",vmName1Attendu, vmName1Recu);
		assertEquals("Premier vm",vm1Attendu, vm1Recu);
		assertEquals("Premier memory_size_MIB",memory_size_MiB1Attendu, memory_size_MiB1Recu);
		assertEquals("Premier power_state",power_state1Attendu, power_state1Recu);
		assertEquals("Premier cpu_count",cpu_count1Attendu, cpu_count1Recu);



	}


}
