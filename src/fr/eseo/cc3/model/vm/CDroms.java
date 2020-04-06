package fr.eseo.cc3.model.vm;






/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe CDroms est un attribut de la classe {@link fr.eseo.cc3.model.vm.VirtualMachine.java}.
 * 
 */
public class CDroms {
	
	
	
	
	private boolean start_connected;
	private String backing_device_access_type;
	private String backing_type;
	private boolean allow_guest_control;
	private String label;
	private String state;
	private String type;
	private String sata_bus;
	private String sata_unit;
	private String key;
	
	
	// Constructeur
	/**
	 *
	 * Constructeur par default de la classe CDroms.
	 * 
	 *
	 * */
	public CDroms() {
		
	}
	

	// Accesseurs
	
	// KEY
	/**
	 * Accesseur setKey() definis la key de CDroms.
	 * 
	 * @param [type: String] key de CDroms à definir
	 * */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * Accesseur getKey() recupere la key de CDroms.
	 * 
	 * @return [type: String] key de CDroms
	 * */
	public String getKey() {
		return this.key;
	}
	
	
	// backing_device_access_type
	/**
	 * Accesseur setBacking_device_access_type() definis backing_device_access_type CDroms.
	 * 
	 * @param [type: String] backing_device_access_type de CDroms à definir
	 * */
	public void setBacking_device_access_type(String bdat) {
		this.backing_device_access_type = bdat;
	}
	
	/**
	 * Accesseur getBacking_device_access_type() recupere backing_device_access_type de CDroms.
	 * 
	 * @return [type: String] backing_device_access_type de CDroms
	 * */
	public String getBacking_device_access_type() {
		return this.backing_device_access_type;
	}
	
	
	
	// backing_device_access_type
	/**
	 * Accesseur setBacking_type() definis backing_type de CDroms.
	 * 
	 * @param [type: String] backing_type de CDroms à definir
	 * */
	public void setBacking_type(String bt) {
		this.backing_type = bt;
	}
	
	/**
	 * Accesseur getBacking_type() recupere backing_type de CDroms.
	 * 
	 * @return [type: String] backing_type de CDroms
	 * */
	public String getBacking_type() {
		return this.backing_type;
	}
	
	
	
	// backing_device_access_type
	/**
	 * Accesseur getState() definis l'etat de CDroms.
	 * 
	 * @param [type: String] state de CDroms à definir
	 * */
	public void setAllow_guest_control(boolean b) {
		this.allow_guest_control = b;
	}
	
	/**
	 * Accesseur getAllow_guest_control() recupere allow_guest_control de CDroms.
	 * 
	 * @return [type: boolean] allow_guest_control de CDroms
	 * */
	public boolean getAllow_guest_control() {
		return this.allow_guest_control;
	}
	
	
	// Start_connected
	/**
	 * Accesseur getStart_connected() recupere start_connected de CDroms.
	 * 
	 * @return [type: boolean] start_connected de CDroms
	 * */
	public boolean getStart_connected() {
		return this.start_connected;
	}


	/**
	 * Accesseur setStart_connected() definis start_connected de CDroms.
	 * 
	 * @param [type: boolean] start_connected de CDroms à definir
	 * */
	public void setStart_connected(boolean b) {
		this.start_connected = b;
	}

	
	// Label
	/**
	 * Accesseur getLabel() recupere le label de CDroms.
	 * 
	 * @return [type: String] label de CDroms
	 * */
	public String getLabel() {
		return this.label;
	}


	/**
	 * Accesseur setLabel() definis le label de CDroms.
	 * 
	 * @param [type: String] label de CDroms à definir
	 * */
	public void setLabel(String label) {
		this.label = label;
	}

	
	// Type
	/**
	 * Accesseur getType() recupere le type de CDroms.
	 * 
	 * @return [type: String] type de CDroms
	 * */
	public String getType() {
		return this.type;
	}


	/**
	 * Accesseur setType() definis le type de CDroms.
	 * 
	 * @param [type: String] type de CDroms à definir
	 * */
	public void setType(String type) {
		this.type = type;
	}

	
	// Sata_bus
	/**
	 * Accesseur getSata_bus() recupere le sata_bus de CDroms.
	 * 
	 * @return [type: String] sata_bus de CDroms
	 * */
	public String getSata_bus() {
		return this.sata_bus;
	}


	/**
	 * Accesseur setSata_bus() definis le sata_bus de CDroms.
	 * 
	 * @param [type: String] sata_bus de CDroms à definir
	 * */
	public void setSata_bus(String sata_bus) {
		this.sata_bus = sata_bus;
	}

	
	// Sata_unit
	/**
	 * Accesseur getSata_unit() recupere le sata_unit de CDroms.
	 * 
	 * @return [type: String] sata_unit de CDroms
	 * */
	public String getSata_unit() {
		return this.sata_unit;
	}


	/**
	 * Accesseur setSata_unit() definis le sata_unit de CDroms.
	 * 
	 * @param [type: String] sata_unit de CDroms à definir
	 * */
	public void setSata_unit(String sata_unit) {
		this.sata_unit = sata_unit;
	}
	
	/**
	 * Accesseur getState() recupere l'etat de CDroms.
	 * 
	 * @return [type: String] state de CDroms
	 * */
	public String getState() {
		return this.state;
	}


	/**
	 * Accesseur getState() definis l'etat de CDroms.
	 * 
	 * @param [type: String] state de CDroms à definir
	 * */
	public void setState(String state) {
		this.state = state;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
