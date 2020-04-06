package fr.eseo.cc3.model.vm;




/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe {Floppies est un attribut de la classe {@link fr.eseo.cc3.model.vm.VirtualMachine.java}.
 * 
 *
 *
 *
 */



public class Floppies {
	
	
	
	
	private boolean start_connected;
	private String backing_type;
	private boolean allow_guest_control;
	private String label;
	private String state;
	private String key;
	
	
	
	

	// Constructeur
	/**
	 * Constructeur de la classe {Floppies.
	 * Le constructeur ne requiert aucun parametre
	 * 
	 * */
	public Floppies() {
		
	}
	
	
	// Accesseurs
	/**
	 * Accesseur getStart_connected() recupere le start_connected du {Floppies.
	 * 
	 * @return [type: boolean] start_connected du {Floppies.
	 * */
	public boolean getStart_connected() {
		return this.start_connected;
	}
	/**
	 * Accesseur getStart_connected() definis le start_connected du {Floppies.
	 * 
	 * @param [type: boolean] start_connected du {Floppies.
	 * */
	public void setStart_connected(boolean b) {
		this.start_connected = b;
	}



	/**
	 * Accesseur getBackingType() definis le backing_type du {Floppies.
	 * 
	 * @return [type: String] backing_type du {Floppies.
	 * */
	public String getBackingType() {
		return this.backing_type;
	}
	/**
	 * Accesseur getBackingType() definis le backing_type du {Floppies.
	 * 
	 * @param [type: String] backing_type du {Floppies.
	 * */
	public void setBackingType(String type) {
		this.backing_type = type;
	}



	/**
	 * Accesseur getAllow_guest_control() recupere le allow_guest_control du {Floppies.
	 * 
	 * @return [type: boolean] allow_guest_control du {Floppies.
	 * */
	public boolean getAllow_guest_control() {
		return this.allow_guest_control;
	}
	/**
	 * Accesseur getAllow_guest_control() definis le allow_guest_control du {Floppies.
	 * 
	 * @param [type: boolean] allow_guest_control du {Floppies.
	 * */
	public void setAllow_guest_control(boolean b) {
		this.allow_guest_control = b;
	}

	
	
	/**
	 * Accesseur getLabel() recupere le label du {Floppies.
	 * 
	 * @return [type: String] label du {Floppies.
	 * */
	public String getLabel() {
		return this.label;
	}
	/**
	 * Accesseur getLabel() definis le label du {Floppies.
	 * 
	 * @param [type: String] label du {Floppies.
	 * */
	public void setLabel(String label) {
		this.label = label;
	}



	/**
	 * Accesseur getState() recupere l'etat du {Floppies.
	 * 
	 * @return [type: String] state du {Floppies.
	 * */
	public String getState() {
		return this.state;
	}
	/**
	 * Accesseur getState() definis l'etat du {Floppies.
	 * 
	 * @param [type: String] state du {Floppies.
	 * */
	public void setState(String state) {
		this.state = state;
	}
	
	
	/**
	 * Accesseur getKey() recupere la key du {Floppies.
	 * 
	 * @return [type: String] key du {Floppies.
	 * */
	public String getKey() {
		return this.key;
	}
	/**
	 * Accesseur getKey() definis la key du {Floppies.
	 * 
	 * @param [type: String] key du {Floppies.
	 * */
	public void setKey(String key) {
		this.key = key;
	}
}
