package fr.eseo.cc3.dao.implementation;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Test;

import fr.eseo.cc3.dao.DAOFactory;
import fr.eseo.cc3.dao._interface.VirtualMachineDao;
import fr.eseo.cc3.dao.bean.Cour;
import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.dao.bean.VirtualMachine;

public class TestVirtualMachineDaoImpl {
	
	private static DAOFactory daoFactory = DAOFactory.getInstance(true);
	private VirtualMachineDao virtualMachineDao = (daoFactory.getVirtualMachineDao());
	
	private void compareVirtualMachine(VirtualMachine vm1, VirtualMachine vm2){
		assertEquals(vm1.getNumVm(), vm2.getNumVm());
		assertEquals(vm1.getRefVm(), vm2.getRefVm());
		assertEquals(vm2.getDateCreation(), vm2.getDateCreation());
		assertEquals(vm2.getDateRendu(), vm2.getDateRendu());
		assertEquals(vm2.getDerniereConnexion(), vm2.getDerniereConnexion());
	}
	
	private void compareListeVirtualMachine(ArrayList<VirtualMachine> list1, ArrayList<VirtualMachine> list2) {
		for(int i = 0; i < Math.max(list1.size(), list2.size()); i++) {
			compareVirtualMachine(list1.get(i), list2.get(i));
		}
	}
	
	@Test
	public void testTrouverVm() throws SQLException {
		VirtualMachine vm = new VirtualMachine(1, "Ma premiere Vm", "VM-01", 1, "2018-04-04", "2018-06-14", "2018-05-22");
		VirtualMachine vmBDD = this.virtualMachineDao.trouver("VM-01", 1);
		compareVirtualMachine(vm, vmBDD);
	}
	
	@Test
	public void testTrouverVide() throws SQLException {
		VirtualMachine vmBDD = this.virtualMachineDao.trouver("azeazdazq", 1);
		assertNull(vmBDD);
	}
	
	@Test
	public void testLister() throws SQLException {
		ArrayList<VirtualMachine> vms = new ArrayList<VirtualMachine>();
		
		VirtualMachine vm = new VirtualMachine(1, "Ma premiere Vm", "VM-01", 1, "2018-04-04", "2018-06-14", "2018-05-22");
		vms.add(vm);
		vm = new VirtualMachine(2, "Une autre Vm", "VM-02", 1, "2018-04-04", "2018-06-14", "2018-05-23");
		vms.add(vm);
		vm = new VirtualMachine(3, "Vm overthetop", "VM-03", 1, "2018-04-04", "2018-06-14", "2018-05-10");
		vms.add(vm);
		vm = new VirtualMachine(4, "Vroum vom vm", "VM-04", 1, "2018-04-04", "2018-06-14", "2018-05-08");
		vms.add(vm);
		vm = new VirtualMachine(5, "Je suis une vm", "VM-05", 1, "2018-04-04", "2018-06-14", "2018-05-06");
		vms.add(vm);
		vm = new VirtualMachine(6, "Mon nom est vm", "VM-06", 1, "2018-04-04", "2018-06-14", "2018-05-10");
		vms.add(vm);
		vm = new VirtualMachine(7, "Vm un jour vm toujours", "VM-07", 1, "2018-04-04", "2018-06-14", "2018-05-16");
		vms.add(vm);
		vm = new VirtualMachine(8, "vm1", "VM-08", 1, "2018-01-09", "2018-06-27", "2018-05-03");
		vms.add(vm);
		vm = new VirtualMachine(9, "La vm", "VM-09", 1, "2018-01-09", "2018-06-27", "2018-05-13");
		vms.add(vm);
		vm = new VirtualMachine(10, "infrastructure1", "VM-10", 1, "2018-01-09", "2018-06-27", "2018-05-07");
		vms.add(vm);
		vm = new VirtualMachine(11, "infra2", "VM-11", 1, "2018-01-09", "2018-06-27", "2018-05-21");
		vms.add(vm);
		vm = new VirtualMachine(12, "coucou", "VM-12", 1, "2018-01-09", "2018-06-27", "2018-05-20");
		vms.add(vm);
		vm = new VirtualMachine(13, "insert the name", "VM-13", 1, "2018-01-09", "2018-06-27", "2018-05-09");
		vms.add(vm);
		vm = new VirtualMachine(14, "AAA", "VM-14", 1, "2018-01-09", "2018-06-27", "2018-05-07");
		vms.add(vm);
		vm = new VirtualMachine(15, "deadpool", "VM-15", 1, "2018-02-23", "2018-06-11", "2018-05-15");
		vms.add(vm);
		vm = new VirtualMachine(16, "une vm en or", "VM-16", 1, "2018-02-23", "2018-06-11", "2018-05-22");
		vms.add(vm);
		vm = new VirtualMachine(17, "Maman jai loupé ma vm", "VM-17", 1, "2018-02-23", "2018-06-11", "2018-05-22");
		vms.add(vm);
		vm = new VirtualMachine(18, "the dark vm", "VM-18", 1, "2018-02-23", "2018-06-11", "2018-05-16");
		vms.add(vm);
		vm = new VirtualMachine(19, "gpi1", "VM-19", 1, "2018-02-23", "2018-06-11", "2018-05-22");
		vms.add(vm);
		vm = new VirtualMachine(20, "gpi2", "VM-20", 1, "2018-02-23", "2018-06-11", "2018-05-02");
		vms.add(vm);
		vm = new VirtualMachine(21, "gpi3", "VM-21", 1, "2018-02-23", "2018-06-11", "2018-05-22");
		vms.add(vm);
		
		ArrayList<VirtualMachine> vmBDD = new ArrayList<VirtualMachine>();
		vmBDD = this.virtualMachineDao.lister(1);
		compareListeVirtualMachine(vmBDD, vms);
	}
	
	@Test
	public void testGetUserVm() throws SQLException {
		ArrayList<VirtualMachine> vms = new ArrayList<VirtualMachine>();
		VirtualMachine vm = new VirtualMachine(3, "Vm overthetop", "VM-03", 1, "2018-04-04", "2018-06-14", "2018-05-10");
		vms.add(vm);
		vm = new VirtualMachine(14, "AAA", "VM-14", 1, "2018-01-09", "2018-06-27", "2018-05-07");
		vms.add(vm);
		vm = new VirtualMachine(15, "deadpool", "VM-15", 1, "2018-02-23", "2018-06-11", "2018-05-15");
		vms.add(vm);
		
		Utilisateur user =  new Utilisateur(16, "etudiant7", "etudiant7", "Clément", "Gouin", "clement.gouin@reseau.eseo.fr", "Etudiant", "I1", "2018-05-01");
	
		ArrayList<VirtualMachine> vmBDD = new ArrayList<VirtualMachine>();
		vmBDD = this.virtualMachineDao.getUserVm(user, 1);
		
		compareListeVirtualMachine(vms, vmBDD);
	}
	
	@Test
	public void testGetVmCours() throws SQLException {
		ArrayList<VirtualMachine> vms = new ArrayList<VirtualMachine>();
		VirtualMachine vm = new VirtualMachine(8, "vm1", "VM-08", 1, "2018-01-09", "2018-06-27", "2018-05-03");
		vms.add(vm);
		vm = new VirtualMachine(9, "La vm", "VM-09", 1, "2018-01-09", "2018-06-27", "2018-05-13");
		vms.add(vm);
		vm = new VirtualMachine(10, "infrastructure1", "VM-10", 1, "2018-01-09", "2018-06-27", "2018-05-07");
		vms.add(vm);
		vm = new VirtualMachine(11, "infra2", "VM-11", 1, "2018-01-09", "2018-06-27", "2018-05-21");
		vms.add(vm);
		vm = new VirtualMachine(12, "coucou", "VM-12", 1, "2018-01-09", "2018-06-27", "2018-05-20");
		vms.add(vm);
		vm = new VirtualMachine(13, "insert the name", "VM-13", 1, "2018-01-09", "2018-06-27", "2018-05-09");
		vms.add(vm);
		
		Cour cours = new Cour(4, "GPI_A4", 8, 6, 1);
		
		ArrayList<VirtualMachine> vmBDD = new ArrayList<VirtualMachine>();
		vmBDD = this.virtualMachineDao.getVmCour(cours);
		
		compareListeVirtualMachine(vms, vmBDD);
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		daoFactory.shutdownConnection();
	}
}
