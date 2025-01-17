package fr.eseo.cc3.model.vm;

/**
 * 
 * @author CC groupe 3
 *
 * La classe Nics est un attribut de la classe {@link fr.eseo.cc3.model.vm.VirtualMachine.java}.
 *
 */

// Nics = list of ethernet adapters
public class Nics {

	
	private boolean start_connected;
	private String backing_network_name;
	private String backing_type;
	private String backing_network;
	private String mac_address;
	private String mac_type;
	private boolean allow_guest_control;
	private String label;
	private String type;
	private String key;
	private boolean wake_on_lan_enabled;
	private String state;
	private String pciSlot;
	
	
	
	
	
	
	
	
	/**
	 * Constructeur de la classe {@link fr.eseo.cc3.model.vm.Memory.java}.
	 * Le constructeur ne requiert aucun parametre
	 * 
	 * 
	 * */
	public Nics() {
		
	}



	
	// Accesseurs
	/**
	 * Accesseur getStart_connected() recupere le start_connected du Nics.
	 * 
	 * @return [type: boolean] start_connected du Nics.
	 * */
	public boolean getStart_connected() {
		return this.start_connected;
	}


	/**
	 * Accesseur setStart_connected() definis le start_connected du Nics.
	 * 
	 * @param [type: boolean] start_connected du Nics.
	 * */
	public void setStart_connected(boolean b) {
		this.start_connected = b;
	}



	/**
	 * Accesseur getStart_connected() recupere le start_connected du Nics.
	 * 
	 * @return [type: boolean] start_connected du Nics.
	 * */
	public String getBacking_network_name() {
		return this.backing_network_name;
	}


	/**
	 * Accesseur setStart_connected() definis le start_connected du Nics.
	 * 
	 * @param [type: boolean] start_connected du Nics.
	 * */
	public void setBacking_network_name(String backing_network_name) {
		this.backing_network_name = backing_network_name;
	}



	/**
	 * Accesseur getStart_connected() recupere le start_connected du Nics.
	 * 
	 * @return [type: boolean] start_connected du Nics.
	 * */
	public String getBacking_type() {
		return this.backing_type;
	}

	/**
	 * Accesseur setStart_connected() definis le start_connected du Nics.
	 * 
	 * @param [type: boolean] start_connected du Nics.
	 * */
	public void setBacking_type(String backing_type) {
		this.backing_type = backing_type;
	}



	/**
	 * Accesseur getStart_connected() recupere le start_connected du Nics.
	 * 
	 * @return [type: boolean] start_connected du Nics.
	 * */
	public String getBacking_network() {
		return this.backing_network;
	}

	/**
	 * Accesseur setStart_connected() definis le start_connected du Nics.
	 * 
	 * @param [type: boolean] start_connected du Nics.
	 * */
	public void setBacking_network(String backing_network) {
		this.backing_network = backing_network;
	}



	/**
	 * Accesseur getStart_connected() recupere le start_connected du Nics.
	 * 
	 * @return [type: boolean] start_connected du Nics.
	 * */
	public String getMac_address() {
		return this.mac_address;
	}

	/**
	 * Accesseur setStart_connected() definis le start_connected du Nics.
	 * 
	 * @param [type: boolean] start_connected du Nics.
	 * */
	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}



	/**
	 * Accesseur getStart_connected() recupere le start_connected du Nics.
	 * 
	 * @return [type: boolean] start_connected du Nics.
	 * */
	public String getMac_type() {
		return this.mac_type;
	}

	/**
	 * Accesseur setStart_connected() definis le start_connected du Nics.
	 * 
	 * @param [type: boolean] start_connected du Nics.
	 * */
	public void setMac_type(String mac_type) {
		this.mac_type = mac_type;
	}



	/**
	 * Accesseur getStart_connected() recupere le start_connected du Nics.
	 * 
	 * @return [type: boolean] start_connected du Nics.
	 * */
	public boolean getAllow_guest_control() {
		return this.allow_guest_control;
	}

	/**
	 * Accesseur setStart_connected() definis le start_connected du Nics.
	 * 
	 * @param [type: boolean] start_connected du Nics.
	 * */
	public void setAllow_guest_control(boolean b) {
		this.allow_guest_control = b;
	}



	/**
	 * Accesseur getLabel() recupere le label du Nics.
	 * 
	 * @return [type: boolean] label du Nics.
	 * */
	public String getLabel() {
		return this.label;
	}

	/**
	 * Accesseur setLabel() definis le label du Nics.
	 * 
	 * @param [type: boolean] label du Nics.
	 * */
	public void setLabel(String label) {
		this.label = label;
	}



	/**
	 * Accesseur getType() recupere le type du Nics.
	 * 
	 * @return [type: String] type du Nics.
	 * */
	public String getType() {
		return this.type;
	}

	/**
	 * Accesseur setType() definis le type du Nics.
	 * 
	 * @param [type: String] type du Nics.
	 * */
	public void setType(String type) {
		this.type = type;
	}



	/**
	 * Accesseur getKey() recupere le key du Nics.
	 * 
	 * @return [type: String] key du Nics.
	 * */
	public String getKey() {
		return this.key;
	}


	/**
	 * Accesseur setKey() definis le key du Nics.
	 * 
	 * @param [type: String] key du Nics.
	 * */
	public void setKey(String key) {
		this.key = key;
	}




	/**
	 * Accesseur getWake_on_lan_enabled() recupere le wake_on_lan_enabled du Nics.
	 * 
	 * @return [type: boolean] wake_on_lan_enabled du Nics.
	 * */
	public boolean getWake_on_lan_enabled() {
		return this.wake_on_lan_enabled;
	}

	/**
	 * Accesseur setWake_on_lan_enabled() definis le wake_on_lan_enabled du Nics.
	 * 
	 * @param [type: boolean] wake_on_lan_enabled du Nics.
	 * */
	public void setWake_on_lan_enabled(boolean b) {
		this.wake_on_lan_enabled = b;
	}




	/**
	 * Accesseur getState() recupere l'etat du Nics.
	 * 
	 * @return [type: String] state du Nics.
	 * */
	public String getState() {
		return this.state;
	}

	/**
	 * Accesseur setState() definis l'etat du Nics.
	 * 
	 * @param [type: String] state du Nics.
	 * */
	public void setState(String state) {
		this.state = state;
	}




	/**
	 * Accesseur getPciSlot() recupere le pciSlot du Nics.
	 * 
	 * @return [type: String] pciSlot du Nics.
	 * */
	public String getPciSlot() {
		return pciSlot;
	}




	/**
	 * Accesseur setPciSlot() definis le pciSlot du Nics.
	 * 
	 * @param [type: String] pciSlot du Nics.
	 * */
	public void setPciSlot(String pciSlot) {
		this.pciSlot = pciSlot;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
