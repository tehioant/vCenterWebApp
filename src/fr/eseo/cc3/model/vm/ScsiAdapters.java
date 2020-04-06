package fr.eseo.cc3.model.vm;




/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe ScsiAdapters est un attribut de la classe {@link fr.eseo.cc3.model.vm.VirtualMachine.java}.
 * 
 */
public class ScsiAdapters {

	
	private int scsi_bus;
	private int scsi_unit;
	private String label;
	private String type;
	private String sharing;
	private String key;
	
	
	
	
	/**
	 * Constructeur de la classe ScsiAdapters.
	 * Le constructeur ne requiert aucun parametre
	 * 
	 * */
	public ScsiAdapters() {
		
	}
	

	
	
	/**
	 * Accesseur getScsiBus() recupere le scsi_bus du ScsiAdapters.
	 * 
	 * @return [type: int] scsi_bus du ScsiAdapters.
	 * */
	public int getScsiBus() {
		return this.scsi_bus;
	}
	/**
	 * Accesseur setScsiBus() definis le scsi_bus du ScsiAdapters.
	 * 
	 * @param [type: int] scsi_bus du ScsiAdapters.
	 * */
	public void setScsiBus(int bus) {
		this.scsi_bus = bus;
	}
	
	
	
	
	/**
	 * Accesseur getScsiUnit() recupere le scsi_unit du ScsiAdapters.
	 * 
	 * @return [type: int] scsi_unit du ScsiAdapters.
	 * */
	public int getScsiUnit() {
		return this.scsi_unit;
	}
	/**
	 * Accesseur setScsiUnit() definis le scsi_unit du ScsiAdapters.
	 * 
	 * @param [type: int] scsi_unit du ScsiAdapters.
	 * 
	 * */
	public void setScsiUnit(int unit) {
		this.scsi_unit = unit;
	}
	
	
	
	
	/**
	 * Accesseur getLabel() recupere le label du ScsiAdapters.
	 * 
	 * @return [type: String] label du ScsiAdapters.
	 * */
	public String getLabel() {
		return this.label;
	}
	/**
	 * Accesseur setLabel() definis le label du ScsiAdapters.
	 * 
	 * @param [type: String] label du ScsiAdapters.
	 * */
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	/**
	 * Accesseur getType() recupere le type du ScsiAdapters.
	 * 
	 * @return [type: String] type du ScsiAdapters.
	 * */
	public String getType() {
		return this.type;
	}
	/**
	 * Accesseur setType() definis le type du ScsiAdapters.
	 * 
	 * @param [type: String] type du ScsiAdapters.
	 * */
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	/**
	 * Accesseur getSharing() recupere le sharing du ScsiAdapters.
	 * 
	 * @return [type: String] sharing du ScsiAdapters.
	 * */
	public String getSharing() {
		return this.sharing;
	}
	/**
	 * Accesseur setSharing() definis le sharing du ScsiAdapters.
	 * 
	 * @param [type: String] sharing du ScsiAdapters.
	 * */
	public void setSharing(String sharing) {
		this.sharing = sharing;
	}
	
	
	
	
	/**
	 * Accesseur getKey() recupere le key du ScsiAdapters.
	 * 
	 * @return [type: String] key du ScsiAdapters.
	 * */
	public String getKey() {
		return this.key;
	}
	/**
	 * Accesseur setKey() definis le key du ScsiAdapters.
	 * 
	 * @param [type: String] key du ScsiAdapters.
	 * */
	public void setKey(String key) {
		this.key = key;
	}
	
	

}
