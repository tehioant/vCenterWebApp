package fr.eseo.cc3.model;

import java.util.ArrayList;



/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe DataCenter centre de données.
 * 
 * 
 * L'interface avec l'objet conteneur commun pour les hôtes et les {@link fr.eseo.cc3.model.vm.VirtualMachine.java}. 
 * Chaque {@link fr.eseo.cc3.model.vm.Host.java} et {@link fr.eseo.cc3.model.vm.VirtualMachine.java} doivent se trouver dans un centre de données distinct de l'inventaire et les centres de données ne peuvent pas être imbriqués sous d'autres centres de données.
 * 
 **/

public class DataCenter {
	
	private static final String DEFAULT_NAME = "Inconnu";
	private static final String DEFAULT_DATACENTER = "Inconnu";
	private static final String DEFAULT_DATASTORE_FOLDER = "Inconnu";
	private static final String DEFAULT_HOST_FOLDER = "Inconnu";
	private static final String DEFAULT_NETWORK_FOLDER = "Inconnu";
	private static final String DEFAULT_VM_FOLDER = "Inconnu";
	private static final ArrayList<Cluster> DEFAULT_LIST_CLUSTER = new ArrayList<Cluster>();
	
	private String name;
	private String datacenter;
	private String datastoreFolder;
	private String hostFolder;
	private String networkFolder;
	private String vmFolder;

	ArrayList<Cluster> listeCluster;

	//Constructeur avec paramétres
	/**
	 *
	 * Constructeur de la classe DataCenter.
	 * 
	 * @param pName [type: String]
	 * @param pDatacenter [type: String]
	 * @param pDatastoreFolder [type: String]
	 * @param pHostFolder [type: String]
	 * @param pNetworkFolder [type: String]
	 * @param pVmFolder [type: String]
	 *
	 * */
	public DataCenter (String pName, String pDatacenter, String pDatastoreFolder, String pHostFolder, String pNetworkFolder, String pVmFolder) {

		this.setName(pName);
		this.setDatacenter(pDatacenter);
		this.setDatastoreFolder(pDatastoreFolder);
		this.setHostFolder(pHostFolder);
		this.setNetworkFolder(pNetworkFolder);
		this.setVmFolder(pVmFolder);

	}

	/**
	 *
	 * Constructeur de la classe DataCenter.
	 * 
	 * @param pName [type: String]
	 * @param pDatastoreFolder [type: String]
	 * @param pHostFolder [type: String]
	 * @param pNetworkFolder [type: String]
	 * @param pVmFolder [type: String]
	 *
	 * */
	public DataCenter (String pName, String pDatastoreFolder, String pHostFolder, String pNetworkFolder, String pVmFolder) {
		this(pName, null, pDatastoreFolder, pHostFolder, pNetworkFolder, pVmFolder);
	}


	/**
	 *
	 * Constructeur de la classe DataCenter.
	 * 
	 * @param pName [type: String]
	 * @param pDatacenter [type: String]
	 *
	 * */
	public DataCenter (String pName, String pDatacenter) {
		this(pName, pDatacenter, null, null, null, null);
	}

	//Constructeur par default
	/**
	 *
	 * Constructeur par default de la classe DataCenter.
	 * 
	 *
	 * */
	public DataCenter() {
		this(null, null, null, null, null, null);
	}

	/**
	 * Accesseur getName() recupere le nom du DataCenter.
	 * 
	 * @return [type: String] name du DataCenter
	 * */
	public String getName() {
		return this.name;
	}

	/**
	 * Accesseur getDatacenter() recupere le datacenter du DataCenter.
	 * 
	 * @return [type: String] datacenter du DataCenter
	 * */
	public String getDatacenter() {
		return this.datacenter;
	}

	/**
	 * Accesseur getDatastoreFolder() recupere le datastoreFolder du DataCenter.
	 * 
	 * @return [type: String] datastoreFolder du DataCenter
	 * */
	public String getDatastoreFolder() {
		return this.datastoreFolder;
	}

	/**
	 * Accesseur getHostFolder() recupere le hostFolder du DataCenter.
	 * 
	 * @return [type: String] hostFolder du DataCenter
	 * */
	public String getHostFolder() {
		return this.hostFolder;
	}

	/**
	 * Accesseur getNetworkFolder() recupere le networkFolder du DataCenter.
	 * 
	 * @return [type: String] networkFolder du DataCenter
	 * */
	public String getNetworkFolder() {
		return this.networkFolder;
	}

	/**
	 * Accesseur getVmFolder() recupere le vmFolder du DataCenter.
	 * 
	 * @return [type: String] vmFolder du DataCenter
	 * */
	public String getVmFolder() {
		return this.vmFolder;
	}

	/**
	 * Accesseur getListeCluster() recupere la listeCluster du DataCenter.
	 * 
	 * @return [type: ArrayList<Cluster>] listeCluster du DataCenter
	 * */
	public ArrayList<Cluster> getListeCluster() {
		return this.listeCluster;
	}

	/**
	 * Accesseur setName() definis la nom du DataCenter.
	 * 
	 * @param name [type: String] nom du DataCenter à definir
	 * */
	public void setName(String name) { 
		this.name = name==null?DEFAULT_NAME: name;
	}

	/**
	 * Accesseur setDatacenter() definis le datacenter du DataCenter.
	 * 
	 * @param name [type: String] datacenter du DataCenter à definir
	 * */
	public void setDatacenter(String datacenter) {
		this.datacenter = datacenter==null?DEFAULT_DATACENTER:datacenter;
	}

	/**
	 * Accesseur setDatastoreFolder() definis le datastoreFolder du DataCenter.
	 * 
	 * @param name [type: String] datastoreFolder du DataCenter à definir
	 * */
	public void setDatastoreFolder(String datastoreFolder) {
		this.datastoreFolder = datastoreFolder==null? DEFAULT_DATASTORE_FOLDER:datastoreFolder;
	}

	/**
	 * Accesseur setHostFolder() definis le hostFolder du DataCenter.
	 * 
	 * @param name [type: String] hostFolder du DataCenter à definir
	 * */
	public void setHostFolder(String hostFolder) {
		this.hostFolder = hostFolder==null?DEFAULT_HOST_FOLDER:hostFolder;
	}

	/**
	 * Accesseur setNetworkFolder() definis le networkFolder du DataCenter.
	 * 
	 * @param name [type: String] networkFolder du DataCenter à definir
	 * */
	public void setNetworkFolder(String networkFolder) {
		this.networkFolder = networkFolder==null?DEFAULT_NETWORK_FOLDER:networkFolder;
	}

	/**
	 * Accesseur setVmFolder() definis le vmFolder du DataCenter.
	 * 
	 * @param name [type: String] vmFolder du DataCenter à definir
	 * */
	public void setVmFolder(String vmFolder) {
		this.vmFolder = vmFolder==null?DEFAULT_VM_FOLDER:vmFolder;
	}

	/**
	 * Accesseur setListeCluster() definis la listeCluster du DataCenter.
	 * 
	 * @param name [type: ArrayList<Cluster>] listeCluster du DataCenter à definir
	 * */
	public void setListeCluster(ArrayList<Cluster> listeCluster) {
		this.listeCluster = listeCluster==null?DEFAULT_LIST_CLUSTER:listeCluster;
	}
}

