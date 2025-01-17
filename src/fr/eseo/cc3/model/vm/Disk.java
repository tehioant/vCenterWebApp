package fr.eseo.cc3.model.vm;






/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe {@link fr.eseo.cc3.model.vm.Disk.java} est un attribut de la classe {@link fr.eseo.cc3.model.vm.VirtualMachine.java}.
 * 
 */




public class Disk {
	
	
	
	
	private int scsi_bus;
	private int scsi_unit;
	
	private String backing_vmdk_file;
	private String backing_type;

	private String label;
	private String type;
	private long capacity ;
	private int key;
	
	
	/**
	 * Accesseur getScsi_bus() recupere le scsi_bus du Disk.
	 * 
	 * @return [type: int] scsi_bus du Disk
	 * */
	public int getScsi_bus() {
		return this.scsi_bus;
	}
	/**
	 * Accesseur setScsi_bus() defini le scsi_bus du Disk.
	 * 
	 * @param [type: int] scsi_bus du Disk à definir
	 * */
	public void setScsi_bus(int i) {
		this.scsi_bus = i;
	}
	
	
	/**
	 * Accesseur getScsi_unit() recupere le scsi_unit du Disk.
	 * 
	 * @return [type: int] scsi_unit du Disk
	 * */
	public int getScsi_unit() {
		return this.scsi_unit;
	}
	/**
	 * Accesseur setScsi_unit() defini le scsi_unit du Disk.
	 * 
	 * @param [type: int] scsi_unit du Disk à definir
	 * */
	public void setScsi_unit(int i) {
		this.scsi_unit = i;
	}
	
	
	/**
	 * Accesseur getBacking_vmdk_file() recupere le backing_vmdk_file du Disk.
	 * 
	 * @return [type: String] backing_vmdk_file du Disk
	 * */
	public String getBacking_vmdk_file() {
		return this.backing_vmdk_file;
	}
	/**
	 * Accesseur setBacking_vmdk_file() defini le backing_vmdk_file du Disk.
	 * 
	 * @param [type: String] backing_vmdk_file du Disk à definir
	 * */
	public void setBacking_vmdk_file(String backing_vmdk_file) {
		this.backing_vmdk_file = backing_vmdk_file;
	}
	
	
	/**
	 * Accesseur getBacking_type() recupere le backing_type du Disk.
	 * 
	 * @return [type: String] backing_type du Disk
	 * */
	public String getBacking_type() {
		return this.backing_type;
	}
	/**
	 * Accesseur setBacking_type() defini le backing_type du Disk.
	 * 
	 * @param [type: String] backing_type du Disk à definir
	 * */
	public void setBacking_type(String backing_type) {
		this.backing_type = backing_type;
	}
	
	
	/**
	 * Accesseur getLabel() recupere le label du Disk.
	 * 
	 * @return [type: String] label du Disk
	 * */
	public String getLabel() {
		return this.label;
	}
	/**
	 * Accesseur setLabel() defini le label du Disk.
	 * 
	 * @param [type: String] label du Disk à definir
	 * */
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	/**
	 * Accesseur getType() recupere le type du Disk.
	 * 
	 * @return [type: String] type du Disk
	 * */
	public String getType() {
		return this.type;
	}
	/**
	 * Accesseur setType() defini le type du Disk.
	 * 
	 * @param [type: String] type du Disk à definir
	 * */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * Accesseur getCapacity() recupere la capacité du Disk.
	 * 
	 * @return [type: long] capacity du Disk
	 * */
	public long getCapacity() {
		return this.capacity;
	}
	/**
	 * Accesseur setCapacity() defini la capacité du Disk.
	 * 
	 * @param [type: long] capacity du Disk à definir
	 * */
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}
	
	
	/**
	 * Accesseur getKey() recupere le key du Disk.
	 * 
	 * @return [type: int] key du Disk
	 * */
	public int getKey() {
		return this.key;
	}
	/**
	 * Accesseur setKey() defini le key du Disk.
	 * 
	 * @param [type: int] key du Disk à definir
	 * */
	public void setKey(int key) {
		this.key = key;
	}

}
