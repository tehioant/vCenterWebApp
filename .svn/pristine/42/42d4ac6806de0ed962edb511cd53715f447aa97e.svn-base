package fr.eseo.cc3.dao.bean;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestVirtualMachine {

	@Test
	public void testConstructeurEmpty() {
		VirtualMachine vm = new VirtualMachine();
		assertEquals(vm.getNumVm(), 0);
		assertEquals(vm.getNomVm(), "");
		assertEquals(vm.getRefVm(), "");
		assertEquals(vm.getServeur(), 0);
		assertEquals(vm.getDateCreation(), "");
		assertEquals(vm.getDateRendu(), "");
		assertEquals(vm.getDerniereConnexion(), "");
	}
	
	@Test
	public void testConstructeurFull() {
		VirtualMachine vm = new VirtualMachine(5, "Vm pour Gpi", "vm-54", 1, "12-5-2018", "12-5-2019", "12-11-2018");
		assertEquals(vm.getNumVm(), 5);
		assertEquals(vm.getNomVm(), "Vm pour Gpi");
		assertEquals(vm.getRefVm(), "vm-54");
		assertEquals(vm.getServeur(), 1);
		assertEquals(vm.getDateCreation(), "12-5-2018");
		assertEquals(vm.getDateRendu(), "12-5-2019");
		assertEquals(vm.getDerniereConnexion(), "12-11-2018");
	}
	
	@Test
	public void testConstructeurSemiFull() {
		VirtualMachine vm = new VirtualMachine("Vm pour Gpi", "vm-54", 1, "12-5-2018", "12-5-2019", "12-11-2018");
		assertEquals(vm.getNumVm(), 0);
		assertEquals(vm.getNomVm(), "Vm pour Gpi");
		assertEquals(vm.getRefVm(), "vm-54");
		assertEquals(vm.getServeur(), 1);
		assertEquals(vm.getDateCreation(), "12-5-2018");
		assertEquals(vm.getDateRendu(), "12-5-2019");
		assertEquals(vm.getDerniereConnexion(), "12-11-2018");
	}
	
	@Test
	public void testSetNumVm() {
		VirtualMachine vm = new VirtualMachine(5, "Vm pour Gpi", "vm-54", 1, "12-5-2018", "12-5-2019", "12-11-2018");
		assertEquals(vm.getNumVm(), 5);
		vm.setNumVm(2);
		assertEquals(vm.getNumVm(), 2);
	}
	
	@Test
	public void testSetNomVm() {
		VirtualMachine vm = new VirtualMachine(5, "Vm pour Gpi", "vm-54", 1, "12-5-2018", "12-5-2019", "12-11-2018");
		assertEquals(vm.getNomVm(), "Vm pour Gpi");
		vm.setNomVm("VmPourInfra");
		assertEquals(vm.getNomVm(), "VmPourInfra");
	}
	
	@Test
	public void testSetRefVm() {
		VirtualMachine vm = new VirtualMachine(5, "Vm pour Gpi", "vm-54", 1, "12-5-2018", "12-5-2019", "12-11-2018");
		assertEquals(vm.getRefVm(), "vm-54");
		vm.setRefVm("vm-55");
		assertEquals(vm.getRefVm(), "vm-55");
	}
	
	@Test
	public void testSetServeur() {
		VirtualMachine vm = new VirtualMachine(5, "Vm pour Gpi", "vm-54", 1, "12-5-2018", "12-5-2019", "12-11-2018");
		assertEquals(vm.getServeur(), 1);
		vm.setServeur(2);
		assertEquals(vm.getServeur(), 2);
	}

	@Test
	public void testSetDateCreation() {
		VirtualMachine vm = new VirtualMachine(5, "Vm pour Gpi", "vm-54", 1, "12-5-2018", "12-5-2019", "12-11-2018");
		assertEquals(vm.getDateCreation(), "12-5-2018");
		vm.setDateCreation("4-9-2020");
		assertEquals(vm.getDateCreation(), "4-9-2020");
	}
	
	@Test
	public void testSetDateRendu() {
		VirtualMachine vm = new VirtualMachine(5, "Vm pour Gpi", "vm-54", 1, "12-5-2018", "12-5-2019", "12-11-2018");
		assertEquals(vm.getDateRendu(), "12-5-2019");
		vm.setDateRendu("4-9-2020");
		assertEquals(vm.getDateRendu(), "4-9-2020");
	}
	
	@Test
	public void testSetDerniereConnexion() {
		VirtualMachine vm = new VirtualMachine(5, "Vm pour Gpi", "vm-54", 1, "12-5-2018", "12-5-2019", "12-11-2018");
		assertEquals(vm.getDerniereConnexion(), "12-11-2018");
		vm.setDerniereConnexion("4-9-2020");
		assertEquals(vm.getDerniereConnexion(), "4-9-2020");
	}
	
	@Test
	public void testToString() {
		VirtualMachine vm = new VirtualMachine(5, "Vm pour Gpi", "vm-54", 1, "12-5-2018", "12-5-2019", "12-11-2018");
		assertEquals(vm.toString(), "VirtualMachine [numVm=" + "5" + ", nomVm=" + "Vm pour Gpi" + ", refVm=" + "vm-54" + ", serveur=" + "1"
				+ ", dateCreation=" + "12-5-2018" + ", dateRendu=" + "12-5-2019" + ", derniereConnexion=" + "12-11-2018" + "]");
	}
}
