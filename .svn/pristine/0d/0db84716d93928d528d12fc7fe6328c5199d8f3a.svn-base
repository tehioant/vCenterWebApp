package fr.eseo.cc3.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import fr.eseo.cc3.model.vm.*;

public class TestVirtualMachine {

	@Test
	public void testConstructeurEmpty() {
		VirtualMachine vm = new VirtualMachine();
		assertEquals(vm.getMemorySizeMIB(), null);
		assertNull(vm.getVm());
		assertNull(vm.getName());
		assertNull(vm.getPowerState());
	}

	@Test
	public void testConstructeurFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getMemorySizeMIB().getSize_MiB(), 50);
		assertEquals(vm.getVm(), "testVM");
		assertEquals(vm.getName(), "testName");
		assertEquals(vm.getPowerState(), "testPowerState");
		assertEquals(vm.getCpu().getCount(), 4);
	}

	@Test
	public void testGetMemorySizeMIBEmpty() {
		VirtualMachine vm = new VirtualMachine();
		assertEquals(vm.getMemorySizeMIB(), null);
	}

	@Test
	public void testGetMemorySizeMIBFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getMemorySizeMIB().getSize_MiB(), 50);
	}
	
	@Test
	public void testGetVmEmpty() {
		VirtualMachine vm = new VirtualMachine();
		assertNull(vm.getVm());
	}

	@Test
	public void testGetVmFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getVm(), "testVM");
	}

	@Test
	public void testGetNameEmpty() {
		VirtualMachine vm = new VirtualMachine();
		assertNull(vm.getName());
	}

	@Test
	public void testGetNameFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getName(), "testName");
	}

	@Test
	public void testGetPowerStateEmpty() {
		VirtualMachine vm = new VirtualMachine();
		assertNull(vm.getPowerState());
	}

	@Test
	public void testGetPowerStateFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getPowerState(), "testPowerState");
	}

	@Test
	public void testGetCpuCountFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getCpu().getCount(), 4);
	}
	
	@Test
	public void testSetMemorySizeMIBEmpty() {
		VirtualMachine vm = new VirtualMachine();
		assertEquals(vm.getMemorySizeMIB(), null);
		vm.setMemorySizeMIB(new Memory(50));
		assertEquals(vm.getMemorySizeMIB().getSize_MiB(), 50);
	}

	@Test
	public void testSetMemorySizeMIBFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getMemorySizeMIB().getSize_MiB(), 50);
		vm.setMemorySizeMIB(new Memory(100));
		assertEquals(vm.getMemorySizeMIB().getSize_MiB(), 100);
	}
	
	@Test
	public void testSetVmEmpty() {
		VirtualMachine vm = new VirtualMachine();
		assertNull(vm.getVm());
		vm.setVm("testVM");
		assertEquals(vm.getVm(), "testVM");
	}

	@Test
	public void testSetVmFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getVm(), "testVM");
		vm.setVm("test");
		assertEquals(vm.getVm(), "test");
	}

	@Test
	public void testSetNameEmpty() {
		VirtualMachine vm = new VirtualMachine();
		assertNull(vm.getName());
		vm.setName("test");
		assertEquals(vm.getName(), "test");
	}

	@Test
	public void testSetNameFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getName(), "testName");
		vm.setName("test");
		assertEquals(vm.getName(), "test");
	}

	@Test
	public void testSetPowerStateEmpty() {
		VirtualMachine vm = new VirtualMachine();
		assertNull(vm.getPowerState());
		vm.setPowerState("test");
		assertEquals(vm.getPowerState(), "test");
	}

	@Test
	public void testSetPowerStateFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getPowerState(), "testPowerState");
		vm.setPowerState("test");
		assertEquals(vm.getPowerState(), "test");
	}


	@Test
	public void testSetCpuCountFull() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertEquals(vm.getCpu().getCount(), 4);
		vm.setCpu(new Cpu(10));
		assertEquals(vm.getCpu().getCount(), 10);
	}
	
	@Test
	public void testGetSerial_ports() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getSerial_ports());
	}
	
	@Test
	public void testSetSerial_ports() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		vm.setSerial_ports("testSerialPort");
		assertEquals(vm.getSerial_ports(),"testSerialPort");
	}

	@Test
	public void testGetBoot_devices() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getBoot_devices());
	}
	
	@Test
	public void testSetBoot_devices() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		vm.setBoot_devices("testBoot_devices");
		assertEquals(vm.getBoot_devices(),"testBoot_devices");
	}
	
	
	@Test
	public void testGetCdroms() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getCdroms());
	}
	
	@Test
	public void testSetCdroms() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		CDroms testCdroms = null;
		vm.setCdroms(testCdroms);
		assertNull(vm.getCdroms());
	}
	
	@Test
	public void testGetDisk() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getDisk());
	}
	
	@Test
	public void testSetDisk() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		Disk testDisk = null;
		vm.setDisk(testDisk);
		assertNull(vm.getDisk());
	}
	
	
	@Test
	public void testGetSataAdapters() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getSata());
	}
	
	@Test
	public void testSetSataAdapters() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		SataAdapters testSataAdapters = null;
		vm.setSata(testSataAdapters);
		assertNull(vm.getSata());
	}
	
	@Test
	public void testGetScsiAdapters() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getScsi());
	}
	
	@Test
	public void testSetScsiAdapters() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		ScsiAdapters testScsiAdapters = null;
		vm.setScsi(testScsiAdapters);
		assertNull(vm.getScsi());
	}
	
	@Test
	public void testGetFloppies() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getFloppies());
	}
	
	@Test
	public void testSetFloppies() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		Floppies testFloppies = null;
		vm.setFloppies(testFloppies);
		assertNull(vm.getFloppies());
	}
	
	@Test
	public void testGetNics() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getNics());
	}
	
	@Test
	public void testSetNics() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		Nics testNics = null;
		vm.setNics(testNics);
		assertNull(vm.getNics());
	}
	
	@Test
	public void testGetBoot() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getBoot());
	}
	
	@Test
	public void testSetBoot() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		Boot testBoot = null;
		vm.setBoot(testBoot);
		assertNull(vm.getBoot());
	}
	
	@Test
	public void testGetHardware() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getHardware());
	}
	
	@Test
	public void testSetHardware() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		Hardware testHardware = null;
		vm.setHardware(testHardware);
		assertNull(vm.getHardware());
	}
	
	@Test
	public void testGetGuestOS() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		assertNull(vm.getGuestOS());
	}
	
	@Test
	public void testSetGuestOS() {
		VirtualMachine vm = new VirtualMachine(new Memory(50), "testVM", "testName", "testPowerState", new Cpu(4));
		vm.setGuestOS("testGuestOS");
		assertEquals(vm.getGuestOS(),"testGuestOS");
	}
	
	

}
