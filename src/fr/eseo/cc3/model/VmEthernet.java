package fr.eseo.cc3.model;


/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe VmEthernet.
 * 
 **/

public class VmEthernet {
	
	
	
	private int nic;
	
	/**
	 * Constructeur de la classe VmEthernet.
	 * 
	 * @param pnic
	 */
	
	public VmEthernet (int pnic) {
		this.nic=pnic;
	}
	
	/**
	 * Accesseur de la classe VmEthernet.
	 * 
	 * @return nic 
	 */
	public int getNic() {
		return this.nic;
	}

}
