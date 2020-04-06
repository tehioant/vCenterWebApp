package fr.eseo.cc3.model.vm;



/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe SataAdapters est un attribut de la classe {@link fr.eseo.cc3.model.vm.VirtualMachine.java}.
 * 
 */
public class SataAdapters {
	

	
	private int bus;
	private int unit;
	private String label;
	private String type;
	private String key; 
	private String pciSlot;
	private String sharing;
	
	// Constructeur
	/**
	 * Constructeur de la classe SataAdapters.
	 * Le constructeur ne requiert aucun parametre
	 * 
	 * */
	public SataAdapters() {
		
	}
	
	
	
	// Accesseurs
	
	// Bus
	/**
	 * Accesseur getBus() recupere le bus du SataAdapters.
	 * 
	 * @return bus [type: int] bus du SataAdapters.
	 * */
	public int getBus() {
		return this.bus;
	}
	/**
	 * Accesseur setBus() definis le bus du SataAdapters.
	 * 
	 * @param bus [type: int] bus du SataAdapters.
	 * */
	public void setBus(int bus) {
		this.bus = bus;
	}
	
	// Label
	/**
	 * Accesseur getLabel() recupere le label du SataAdapters.
	 * 
	 * @return label [type: String] label du SataAdapters.
	 * */
	public String getLabel() {
		return this.label;
	}
	/**
	 * Accesseur setLabel() definis le label du SataAdapters.
	 * 
	 * @param label [type: String] label du SataAdapters.
	 * */
	public void setLabel(String label) {
		this.label = label;
	}
	
	// Type
	/**
	 * Accesseur getType() recupere le type du SataAdapters.
	 * 
	 * @return type [type: String] type du SataAdapters.
	 * */
	public String getType() {
		return this.type;
	}
	/**
	 * Accesseur setType() definis le type du SataAdapters.
	 * 
	 * @param type [type: String] type du SataAdapters.
	 * */
	public void setType(String type) {
		this.type = type;
	}
	
	// Key
	/**
	 * Accesseur setKey() definis le key du SataAdapters.
	 * 
	 * @param key [type: String] key du SataAdapters.
	 * */
	public String getKey() {
		return this.key;
	}
	/**
	 * Accesseur setKey() definis le key du SataAdapters.
	 * 
	 * @param key [type: String] key du SataAdapters.
	 * */
	public void setKey(String key) {
		this.key = key;
	}



	/**
	 * Accesseur getUnit() recupere le unit du SataAdapters.
	 * 
	 * @return unit [type: int] unit du SataAdapters.
	 * */
	public int getUnit() {
		return unit;
	}

	/**
	 * Accesseur setUnit() definis le unit du SataAdapters.
	 * 
	 * @param unit [type: int] unit du SataAdapters.
	 * */
	public void setUnit(int unit) {
		this.unit = unit;
	}



	/**
	 * Accesseur getPciSlot() recupere le pciSlot du SataAdapters.
	 * 
	 * @return [type: String] pciSlot du SataAdapters.
	 * */
	public String getPciSlot() {
		return pciSlot;
	}

	/**
	 * Accesseur setPciSlot() definis le pciSlot du SataAdapters.
	 * 
	 * @param [type: String] pciSlot du SataAdapters.
	 * */
	public void setPciSlot(String pciSlot) {
		this.pciSlot = pciSlot;
	}



	/**
	 * Accesseur getSharing() recupere le sharing du SataAdapters.
	 * 
	 * @return [type: String] sharing du SataAdapters.
	 * */
	public String getSharing() {
		return sharing;
	}

	/**
	 * Accesseur setSharing() definis le sharing du SataAdapters.
	 * 
	 * @param [type: String] sharing du SataAdapters.
	 * */
	public void setSharing(String sharing) {
		this.sharing = sharing;
	}
}
