package fr.eseo.cc3.model;

import java.util.ArrayList;



/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe {@link fr.eseo.cc3.model.vm.Host.java}.
 * 
 **/

public class Host {

	private static final String DEFAULT_NAME = "Inconnu";
	private static final String DEFAULT_CONNECTION_STATE = "Inconnu";
	private static final String DEFAULT_POWER_STATE = "Inconnu";
	private static final String DEFAULT_HOST="Inconnu";
	private static final ArrayList<VirtualMachine> DEFAULT_LIST_VM= new ArrayList<VirtualMachine>();


	private String name;
	private String connectionState;
	private String powerState;
	private String host;
	ArrayList<VirtualMachine> listeVm = new ArrayList<VirtualMachine>();



	//Constructeur avec paramétre
	/**
	 *
	 * Constructeur de la classe {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @param pHost [type: String]
	 * @param pName [type: String]
	 * @param pConnectionState [type: String]
	 * @param pPowerState [type: String]
	 *
	 * */
	public Host (String pHost, String pName, String pConnectionState, String pPowerState) {
		this.host = pHost;
		this.name = pName;
		this.connectionState = pConnectionState;
		this.powerState = pPowerState;
	}


	//Constructeur par default
	/**
	 *
	 * Constructeur par default de la classe {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * */
	public Host() {
		this(null, null, null, null);
	}

	/**
	 * Accesseur getName() recupere le nom de {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @return [type: String] name de Host
	 * */
	public String getName() {
		return this.name;
	}


	/**
	 * Accesseur getConnectionState() recupere l'etat de connection de {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @return [type: String] connectionState de Host
	 * */
	public String getConnectionState() {
		return this.connectionState;
	}

	/**
	 * Accesseur getPowerState() recupere l'etat de {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @return [type: String] powerState de Host
	 * */
	public String getPowerState() {
		return this.powerState;
	}

	/**
	 * Accesseur getHost() recupere l'identifiant de {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @return [type: String] host de Host
	 * */
	public String getHost() {
		return this.host;
	}

	/**
	 * Accesseur getName() recupere le nom de {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @return [type: ArrayList<VirtualMachine>] name de Host
	 * */
	public ArrayList<VirtualMachine> getListeVm() {
		return this.listeVm;
	}

	/**
	 * Accesseur setName() definis le nom de {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @param [type: String] name de Host à definir
	 * */
	public void setName(String name) {
		this.name = name==null?DEFAULT_NAME:name;
	}

	/**
	 * Accesseur setConnectionState() definis l'etat de connection de {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @param [type: String] connectionState de Host à definir
	 * */
	public void setConnectionState(String connectionState) {
		switch(connectionState){
		case("CONNECTED") :
			this.connectionState = "Connecté";
			break;
		case("DISCONNECTED") :
			this.connectionState="Déconnecté";
			break;
		case("NOT_RESPONDING") :
			this.connectionState = "Ne répond pas";
			break;
		default :
			this.connectionState =DEFAULT_POWER_STATE;
		}
	}

	/**
	 * Accesseur setPowerState() definis le l'etat de {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @param [type: String] powerState de Host à definir
	 * */
	public void setPowerState(String powerState) {
		switch(powerState){
		case("POWERED_OFF") :
			this.powerState = "Hors tension";
			break;
		case("POWERED_ON") :
			this.powerState="Sous tension";
			break;
		case("SUSPENDED") :
			this.powerState = "Interrompu";
			break;
		default :
			this.powerState =DEFAULT_POWER_STATE;
		}
	}

	/**
	 * Accesseur setHost() definis l'identifiant de {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @param [type: String] host de Host à definir
	 * */
	public void setHost(String host) {
		this.host = host==null?DEFAULT_HOST:host;
	}

	/**
	 * Accesseur setHost() definis une liste de machine virtuelle dans {@link fr.eseo.cc3.model.vm.Host.java}.
	 * 
	 * @param [type: ArrayList<VirtualMachine>] listeVm de machine virtuelle à definir
	 * */
	public void setListeVm(ArrayList<VirtualMachine> listeVm) {
		this.listeVm = listeVm==null?DEFAULT_LIST_VM:listeVm;
	}


}
