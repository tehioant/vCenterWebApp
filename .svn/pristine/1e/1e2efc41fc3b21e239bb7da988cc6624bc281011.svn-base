package fr.eseo.cc3.model;

import fr.eseo.cc3.model.vm.*;



/**
 * 
 * @author CC groupe 3.
 *
 **/
/**
 * 
 * 
 * VirtualMachine est un type d'objet géré pour la manipulation des machines virtuelles, y compris les modèles qui peuvent être déployés (de manière répétée) en tant que nouvelles machines virtuelles. 
 * Ce type fournit des méthodes pour configurer et contrôler une machine virtuelle.
 * VirtualMachine étend le type ManagedEntity car les machines virtuelles font partie d'un inventaire d'infrastructure virtuelle.
 *  Le parent d'une machine virtuelle doit être un dossier et une machine virtuelle n'a pas d'enfants.
 *
 * La destruction d'une machine virtuelle dispose de tout le stockage associé, y compris les disques virtuels. 
 * Pour supprimer une machine virtuelle tout en conservant son stockage sur disque virtuel, un client doit supprimer les disques virtuels de la machine virtuelle avant de les détruire.
 * 
 * 
 **/

public class VirtualMachine {



	private Cpu cpu;
	private Memory memorySizeMIB;
	private String name;
	private String powerState;
	private String vm;
	private String serial_ports;
	private String boot_devices;
	private CDroms cdroms;
	private Disk disk;
	private SataAdapters sata;
	private ScsiAdapters scsi;
	private Floppies floppies;
	private Nics nics;
	private Boot boot;
	private Hardware hardware;
	private String guestOS;


	//Constructeur avec paramètres

	/**
	 *
	 * Constructeur de la classe VirtualMachine.
	 * 
	 * @param pMemorySizeMIB [type: {@link fr.eseo.cc3.model.vm.Memory.java}]
	 * @param pVm [type: String]
	 * @param pName [type: String]
	 * @param pPowerState [type: String]
	 * @param pCpu [type: {@link fr.eseo.cc3.model.vm.Cpu.java}]
	 *
	 * */
	
	public VirtualMachine(Memory pMemorySizeMIB, String pVm, String pName, String pPowerState, Cpu pCpu) {

		cpu = pCpu;
		memorySizeMIB = pMemorySizeMIB;
		name = pName;
		powerState = pPowerState;
		vm = pVm;

	}

	//Constructeur par defaut
	/**
	 *
	 * Constructeur par defaut de la classe VirtualMachine.
	 * 
	 * */

	public VirtualMachine() {
		this(null, null, null, null, null);

	}


	/**
	 *
	 * Accesseur getCpu() recupere le {@link fr.eseo.cc3.model.vm.Cpu.java} de la VirtualMachine.
	 * 
	 * @return {@link fr.eseo.cc3.model.vm.Cpu.java}
	 *
	 * */
	public Cpu getCpu() {
		return this.cpu;
	}

	
	/**
	 * Accesseur getMemorySizeMIB() recupere la {@link fr.eseo.cc3.model.vm.Memory.java} de la VirtualMachine.
	 * 
	 * @return {@link fr.eseo.cc3.model.vm.Memory.java} 
	 * */
	public Memory getMemorySizeMIB() {
		return this.memorySizeMIB;
	}

	
	/**
	 * Accesseur getName() recupere le nom de la VirtualMachine.
	 * 
	 * @return [type: String] Nom de la Machine Virtuelle
	 * */
	public String getName() {
		return this.name;
	}

	/**
	 * Accesseur getPowerState() recupere l'etat de la VirtualMachine.
	 * 
	 * @return [type: String] powerstate de la Machine Virtuelle.
	 * */
	public String getPowerState() {
		return this.powerState;
	}

	/**
	 * Accesseur getVm() recupere l'identifiant de la VirtualMachine.
	 * 
	 * @return [type: String] powerstate de la Machine Virtuelle.
	 * */
	public String getVm() {
		return this.vm;
	}

	/**
	 * Accesseur setCpu() definis le {@link fr.eseo.cc3.model.vm.Cpu.java} de la VirtualMachine.
	 * 
	 * @param cpu {@link fr.eseo.cc3.model.vm.Cpu.java} 
	 * */
	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	/**
	 * Accesseur setMemorySizeMIB() definis la {@link fr.eseo.cc3.model.vm.Memory.java} de la VirtualMachine.
	 * 
	 * @param cpu {@link fr.eseo.cc3.model.vm.Memory.java}
	 * */
	public void setMemorySizeMIB(Memory memorySizeMIB) {
		this.memorySizeMIB = memorySizeMIB;
	}

	/**
	 * Accesseur setName() definis le nouveau nom de la VirtualMachine.
	 * 
	 * @param name [type: String]
	 * */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Accesseur setPowerState() definis l'etat de la VirtualMachine.
	 * 
	 * @param powerState [type: String]
	 * */
	public void setPowerState(String powerState) {
		this.powerState = powerState;
	}

	/**
	 * Accesseur setVm() definis l'identifiant de la VirtualMachine.
	 * 
	 * @param vm [type: String]
	 * */
	public void setVm(String vm) {
		this.vm = vm;
	}

	/**
	 * Accesseur getSerial_ports() recupere la valeur serial_ports de la VirtualMachine.
	 * 
	 * @return the serial_ports [type: String]
	 */
	public String getSerial_ports() {
		return this.serial_ports;
	}

	/**
	 * Accesseur setSerial_ports() definis la valeur serial_ports de la VirtualMachine.
	 * 
	 * @param serial_ports le serial_ports à set
	 */
	public void setSerial_ports(String serial_ports) {
		this.serial_ports = serial_ports;
	}

	/**
	 * Accesseur getBoot_devices() recupere la valeur boot_devices de la VirtualMachine.
	 * 
	 * @return boot_devices [type: String]
	 */
	public String getBoot_devices() {
		return boot_devices;
	}

	/**
	 * Accesseur setBoot_devices() definis la valeur boot_devices de la VirtualMachine.
	 * 
	 * @param boot_devices le boot_devices à set
	 */
	public void setBoot_devices(String boot_devices) {
		this.boot_devices = boot_devices;
	}

	/**
	 * Accesseur getCdroms() recupere la valeur {@link fr.eseo.cc3.model.vm.CDroms.java} de la VirtualMachine.
	 * 
	 * @return {@link fr.eseo.cc3.model.vm.CDroms.java}
	 */
	public CDroms getCdroms() {
		return cdroms;
	}

	/**
	 * Accesseur setCdroms() definis la valeur {@link fr.eseo.cc3.model.vm.CDroms.java} de la VirtualMachine.
	 * 
	 * @param cdroms le {@link fr.eseo.cc3.model.vm.CDroms.java} à set
	 */
	public void setCdroms(CDroms cdroms) {
		this.cdroms = cdroms;
	}

	/**
	 * Accesseur getDisks() recupere la valeur {@link fr.eseo.cc3.model.vm.Disk.java} de la VirtualMachine.
	 * 
	 * @return  {@link fr.eseo.cc3.model.vm.Disk.java}
	 */
	public Disk getDisk() {
		return disk;
	}

	/**
	 * Accesseur setDisks() definis la valeur {@link fr.eseo.cc3.model.vm.Disk.java} de la VirtualMachine.
	 * 
	 * @param disk le {@link fr.eseo.cc3.model.vm.Disk.java} à set
	 */
	public void setDisk(Disk disk) {
		this.disk = disk;
	}

	/**
	 * Accesseur getSata() recupere la valeur {@link fr.eseo.cc3.model.vm.SataAdapters.java} de la VirtualMachine.
	 * 
	 * @return {@link fr.eseo.cc3.model.vm.SataAdapters.java}
	 */
	public SataAdapters getSata() {
		return sata;
	}

	/**
	 * Accesseur setSata() definis la valeur {@link fr.eseo.cc3.model.vm.SataAdapters.java} de la VirtualMachine.
	 * 
	 * @param sata le {@link fr.eseo.cc3.model.vm.SataAdapters.java} à set
	 */
	public void setSata(SataAdapters sata) {
		this.sata = sata;
	}

	/**
	 * Accesseur getScsi() recupere la valeur {@link fr.eseo.cc3.model.vm.ScsiAdapters.java} de la VirtualMachine.
	 * 
	 * @return {@link fr.eseo.cc3.model.vm.ScsiAdapters.java}
	 */
	public ScsiAdapters getScsi() {
		return scsi;
	}

	/**
	 * Accesseur setScsi() definis la valeur {@link fr.eseo.cc3.model.vm.ScsiAdapters.java} de la VirtualMachine.
	 * 
	 * @param scsi le {@link fr.eseo.cc3.model.vm.ScsiAdapters.java} à set
	 */
	public void setScsi(ScsiAdapters scsi) {
		this.scsi = scsi;
	}

	/**
	 * Accesseur getFloppies() recupere la valeur {@link fr.eseo.cc3.model.vm.Floppies.java} de la VirtualMachine.
	 * 
	 * @return {@link fr.eseo.cc3.model.vm.Floppies.java}
	 */
	public Floppies getFloppies() {
		return floppies;
	}

	/**
	 * Accesseur setFloppies() definis la valeur {@link fr.eseo.cc3.model.vm.Floppies.java} de la VirtualMachine.
	 * 
	 * @param floppies le {@link fr.eseo.cc3.model.vm.Floppies.java} à set
	 */
	public void setFloppies(Floppies floppies) {
		this.floppies = floppies;
	}

	/**
	 * Accesseur getNics() recupere la valeur {@link fr.eseo.cc3.model.vm.Nics.java} de la VirtualMachine.
	 * 
	 * @return {@link fr.eseo.cc3.model.vm.Nics}
	 */
	public Nics getNics() {
		return nics;
	}

	/**
	 * Accesseur setNics() definis la valeur {@link fr.eseo.cc3.model.vm.Nics.java} de la VirtualMachine.
	 * 
	 * @param nics le {@link fr.eseo.cc3.model.vm.Nics} à set
	 */
	public void setNics(Nics nics) {
		this.nics = nics;
	}

	/**
	 * Accesseur getBoot() recupere la valeur {@link fr.eseo.cc3.model.vm.Boot.java} de la VirtualMachine.
	 * 
	 * @return {@link fr.eseo.cc3.model.vm.Boot.java}
	 */
	public Boot getBoot() {
		return boot;
	}

	/**
	 * Accesseur setScsi() definis la valeur {@link fr.eseo.cc3.model.vm.Boot.java} de la VirtualMachine.
	 * 
	 * @param boot le {@link fr.eseo.cc3.model.vm.Boot.java} à set
	 */
	public void setBoot(Boot boot) {
		this.boot = boot;
	}

	/**
	 * Accesseur getHardware() recupere la valeur {@link fr.eseo.cc3.model.vm.Hardware.java} de la VirtualMachine.
	 * 
	 * @return {@link fr.eseo.cc3.model.vm.Hardware.java}
	 */
	public Hardware getHardware() {
		return hardware;
	}

	/**
	 * Accesseur setHardware() definis la valeur {@link fr.eseo.cc3.model.vm.Hardware.java} de la VirtualMachine.
	 * 
	 * @param hardware le {@link fr.eseo.cc3.model.vm.Hardware.java} à set
	 */
	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}

	/**
	 * Accesseur getGuestOS() recupere la valeur guestOS de la VirtualMachine.
	 * 
	 * @return guestOS [type: String]
	 */
	public String getGuestOS() {
		return guestOS;
	}

	/**
	 * Accesseur setGuestOS() definis la valeur guestOS de la VirtualMachine.
	 * 
	 * @param [type: String] guestOS le guestOS à set
	 */
	public void setGuestOS(String guestOS) {
		this.guestOS = guestOS;
	}


}

