package fr.eseo.cc3.clientapi;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.eseo.cc3.json.JsonReader;
import fr.eseo.cc3.model.VirtualMachine;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;



@RunWith(Parameterized.class)
public class TestParametreClientVSphereChangeAlimentationVm {enum Type {SUBSTRACT, ADD}


		 ClientVSphere connection = new ClientVSphere("etudiant@vsphere.bac", "N3twork!");
		 JsonReader reader = new JsonReader();
		 public  String vm;
		 public  int etat;
		 public String reponseVMAttendu;
		 public Type type;
		 public int numeroTest;
		 
		  public TestParametreClientVSphereChangeAlimentationVm(Type ptype, int pNumeroTest,String pVM, int pEtat, String pReponseVMAttendu) {
		    	this.vm = pVM;
		    	this.etat = pEtat;
		    	this.reponseVMAttendu = pReponseVMAttendu;
		    	this.type = ptype;
		    	this.numeroTest =pNumeroTest;
		    	

		    }
		   


			@Parameterized.Parameters
		    public static List<Object[]> getParametres() {
		        return Arrays.asList(new Object[][] {
		                {Type.ADD,1, "vm-945", 0,"{\"value\":{\"cdroms\":[],\"memory\":{\"size_MiB\":4096,\"hot_add_enabled\":true},\"disks\":[{\"value\":{\"scsi\":{\"bus\":0,\"unit\":0},\"backing\":{\"vmdk_file\":\"[datastore1-33] TestCC3/TestCC3.vmdk\",\"type\":\"VMDK_FILE\"},\"label\":\"Hard disk 1\",\"type\":\"SCSI\",\"capacity\":17179869184},\"key\":\"2000\"}],\"parallel_ports\":[],\"sata_adapters\":[],\"cpu\":{\"hot_remove_enabled\":true,\"count\":1,\"hot_add_enabled\":true,\"cores_per_socket\":1},\"scsi_adapters\":[{\"value\":{\"scsi\":{\"bus\":0,\"unit\":7},\"pci_slot_number\":160,\"label\":\"SCSI controller 0\",\"type\":\"PVSCSI\",\"sharing\":\"NONE\"},\"key\":\"1000\"}],\"power_state\":\"POWERED_OFF\",\"floppies\":[],\"name\":\"TestCC3\",\"nics\":[],\"boot\":{\"delay\":0,\"retry_delay\":10000,\"enter_setup_mode\":false,\"type\":\"BIOS\",\"retry\":false},\"serial_ports\":[],\"guest_OS\":\"RHEL_7_64\",\"boot_devices\":[],\"hardware\":{\"upgrade_policy\":\"NEVER\",\"upgrade_status\":\"NONE\",\"version\":\"VMX_13\"}}}\n"},
		                {Type.ADD,2, "vm-945", 1,"{\"value\":{\"cdroms\":[],\"memory\":{\"hot_add_increment_size_MiB\":128,\"size_MiB\":4096,\"hot_add_enabled\":true,\"hot_add_limit_MiB\":65536},\"disks\":[{\"value\":{\"scsi\":{\"bus\":0,\"unit\":0},\"backing\":{\"vmdk_file\":\"[datastore1-33] TestCC3/TestCC3.vmdk\",\"type\":\"VMDK_FILE\"},\"label\":\"Hard disk 1\",\"type\":\"SCSI\",\"capacity\":17179869184},\"key\":\"2000\"}],\"parallel_ports\":[],\"sata_adapters\":[],\"cpu\":{\"hot_remove_enabled\":true,\"count\":1,\"hot_add_enabled\":true,\"cores_per_socket\":1},\"scsi_adapters\":[{\"value\":{\"scsi\":{\"bus\":0,\"unit\":7},\"pci_slot_number\":160,\"label\":\"SCSI controller 0\",\"type\":\"PVSCSI\",\"sharing\":\"NONE\"},\"key\":\"1000\"}],\"power_state\":\"POWERED_ON\",\"floppies\":[],\"name\":\"TestCC3\",\"nics\":[],\"boot\":{\"delay\":0,\"retry_delay\":10000,\"enter_setup_mode\":false,\"type\":\"BIOS\",\"retry\":false},\"serial_ports\":[],\"guest_OS\":\"RHEL_7_64\",\"boot_devices\":[],\"hardware\":{\"upgrade_policy\":\"NEVER\",\"upgrade_status\":\"NONE\",\"version\":\"VMX_13\"}}}\n"},
		                {Type.ADD,3,"vm-945", 2,"{\"value\":{\"cdroms\":[],\"memory\":{\"size_MiB\":4096,\"hot_add_enabled\":true},\"disks\":[{\"value\":{\"scsi\":{\"bus\":0,\"unit\":0},\"backing\":{\"vmdk_file\":\"[datastore1-33] TestCC3/TestCC3.vmdk\",\"type\":\"VMDK_FILE\"},\"label\":\"Hard disk 1\",\"type\":\"SCSI\",\"capacity\":17179869184},\"key\":\"2000\"}],\"parallel_ports\":[],\"sata_adapters\":[],\"cpu\":{\"hot_remove_enabled\":true,\"count\":1,\"hot_add_enabled\":true,\"cores_per_socket\":1},\"scsi_adapters\":[{\"value\":{\"scsi\":{\"bus\":0,\"unit\":7},\"pci_slot_number\":160,\"label\":\"SCSI controller 0\",\"type\":\"PVSCSI\",\"sharing\":\"NONE\"},\"key\":\"1000\"}],\"power_state\":\"SUSPENDED\",\"floppies\":[],\"name\":\"TestCC3\",\"nics\":[],\"boot\":{\"delay\":0,\"retry_delay\":10000,\"enter_setup_mode\":false,\"type\":\"BIOS\",\"retry\":false},\"serial_ports\":[],\"guest_OS\":\"RHEL_7_64\",\"boot_devices\":[],\"hardware\":{\"upgrade_policy\":\"NEVER\",\"upgrade_status\":\"NONE\",\"version\":\"VMX_13\"}}}\n"},

		                {Type.SUBSTRACT,4, "vm-945", 0,"{\"value\":{\"cdroms\":[{\"value\":{\"start_connected\":false,\"backing\":{\"device_access_type\":\"PASSTHRU\",\"type\":\"CLIENT_DEVICE\"},\"allow_guest_control\":true,\"label\":\"CD/DVD drive 1\",\"state\":\"NOT_CONNECTED\",\"type\":\"SATA\",\"sata\":{\"bus\":0,\"unit\":0}},\"key\":\"16000\"}],\"memory\":{\"size_MiB\":512,\"hot_add_enabled\":false},\"disks\":[{\"value\":{\"scsi\":{\"bus\":0,\"unit\":0},\"backing\":{\"vmdk_file\":\"[datastore1-34] CC3_TEST_1/CC3_TEST.vmdk\",\"type\":\"VMDK_FILE\"},\"label\":\"Hard disk 1\",\"type\":\"SCSI\",\"capacity\":2147483648},\"key\":\"2000\"}],\"parallel_ports\":[],\"sata_adapters\":[{\"value\":{\"bus\":0,\"pci_slot_number\":34,\"label\":\"SATA controller 0\",\"type\":\"AHCI\"},\"key\":\"15000\"}],\"cpu\":{\"hot_remove_enabled\":false,\"count\":1,\"hot_add_enabled\":false,\"cores_per_socket\":1},\"scsi_adapters\":[{\"value\":{\"scsi\":{\"bus\":0,\"unit\":7},\"pci_slot_number\":160,\"label\":\"SCSI controller 0\",\"type\":\"LSILOGICSAS\",\"sharing\":\"NONE\"},\"key\":\"1000\"}],\"power_state\":\"POWERED_OFF\",\"floppies\":[{\"value\":{\"start_connected\":false,\"backing\":{\"type\":\"CLIENT_DEVICE\"},\"allow_guest_control\":true,\"label\":\"Floppy drive 1\",\"state\":\"NOT_CONNECTED\"},\"key\":\"8000\"}],\"name\":\"CC3_TEST\",\"nics\":[{\"value\":{\"start_connected\":true,\"pci_slot_number\":32,\"backing\":{\"network_name\":\"VM Network\",\"type\":\"STANDARD_PORTGROUP\",\"network\":\"network-33\"},\"mac_address\":\"00:50:56:ad:88:b3\",\"mac_type\":\"ASSIGNED\",\"allow_guest_control\":true,\"wake_on_lan_enabled\":true,\"label\":\"Network adapter 1\",\"state\":\"NOT_CONNECTED\",\"type\":\"E1000\"},\"key\":\"4000\"}],\"boot\":{\"delay\":0,\"retry_delay\":10000,\"enter_setup_mode\":false,\"type\":\"BIOS\",\"retry\":false},\"serial_ports\":[],\"guest_OS\":\"WINDOWS_7_SERVER_64\",\"boot_devices\":[],\"hardware\":{\"upgrade_policy\":\"NEVER\",\"upgrade_status\":\"NONE\",\"version\":\"VMX_13\"}}}\r\n"},
		                
		        });
		    }
		    
		    private static final ResourceBundle CONFIGURATION_API = ResourceBundle.getBundle("configAPI");
		
		    
		    @Test
		    public void testClientVSphereChangeAlimentationV() throws IOException, ExceptionConnectionVSphere{
		    	
		    	Assume.assumeTrue(type == Type.ADD);
		    	
		    	int power;
		    	String requeteVmCible = CONFIGURATION_API.getString("GET_VM")+"/vm-945";
		    	
		    	String reponseVmCible = connection.getData(requeteVmCible);
		    	VirtualMachine vmCible = reader.getVirtualMachineDetails(reponseVmCible);
		    	String powerState = vmCible.getPowerState();
		    	
		    	switch(powerState) {
		    		case("POWERED_ON"):
		    			power=1;
		    			break;
		    		default :
		    			power=0;
		    	}
		    	if(numeroTest==1 && power != 1) {
		    		connection.changeAlimentationVm(vm,1);
		    	}
		    	
		    	String requeteVm = CONFIGURATION_API.getString("GET_VM")+"/vm-945";
		    	connection.changeAlimentationVm(vm,etat);
		    	String reponseVM = connection.getData(requeteVm);
		    	
		    		
		    			/* Assertion. */

		    		assertEquals("Etat conforme.", reponseVMAttendu, reponseVM);
		    
		    
	}
		    
		    
		    
		    
		    @Test(expected=ExceptionConnectionVSphere.class)
		    public void testClientVSphereChangeAlimentationVMErreur() throws IOException, ExceptionConnectionVSphere{
		    	Assume.assumeTrue(type == Type.SUBSTRACT);
		    	connection.changeAlimentationVm(vm,0);
		    	  	
		    	String requeteVm = CONFIGURATION_API.getString("GET_VM")+"/vm-945";
		    	connection.changeAlimentationVm(vm,etat);
		    	String reponseVM = connection.getData(requeteVm);
		    	
		    		
		    			/* Assertion. */

		    		assertEquals("Etat conforme.", reponseVMAttendu, reponseVM);
		    
		    
	}

		    @Test(expected=ExceptionConnectionVSphere.class)
		    public void testClientVSphereChangeAlimentationVMErreurEtat() throws IOException, ExceptionConnectionVSphere{
		    	Assume.assumeTrue(type == Type.SUBSTRACT);
		    	connection.changeAlimentationVm(vm,3);
		    	  	
		    	String requeteVm = CONFIGURATION_API.getString("GET_VM")+"/vm-945";
		    	connection.changeAlimentationVm(vm,etat);
		    	String reponseVM = connection.getData(requeteVm);
		    	
		    		
		    			/* Assertion. */

		    		assertEquals("Etat conforme.", reponseVMAttendu, reponseVM);
		    
		    
	}
}
