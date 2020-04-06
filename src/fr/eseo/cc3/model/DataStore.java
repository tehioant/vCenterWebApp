package fr.eseo.cc3.model;


/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe DataStore.
 * 
 * Représente un emplacement de stockage pour les fichiers de {@link fr.eseo.cc3.model.vm.VirtualMachine.java}. Un emplacement de stockage peut être un volume VMFS, un répertoire sur Network Attached Storage ou un chemin de système de fichiers local.
 * Un magasin de données est indépendant de la plate-forme et indépendant de l'hôte. Par conséquent, les banques de données ne changent pas lorsque les {@link fr.eseo.cc3.model.vm.VirtualMachine.java} qu'elles contiennent sont déplacées entre les hôtes. La portée d'un magasin de données est un centre de données; le magasin de données est uniquement nommé dans le centre de données.
 * 
 * Toute référence à une {@link fr.eseo.cc3.model.vm.VirtualMachine.java} ou à un fichier accessible par un hôte dans le centre de données doit utiliser un chemin de magasin de données. Un chemin de magasin de données a la forme "[<magasin de données>] <chemin>", où <magasin de données> est le nom du magasin de données et <chemin> est un chemin délimité par des barres obliques à partir de la racine du magasin de données. Un exemple de chemin de magasin de données est "[storage] path / to / config.vmx".
 *
 * Toutes les références aux fichiers dans l'API VIM sont implicitement effectuées à l'aide de chemins de banque de données.
 * 
 * Lorsqu'un client crée une {@link fr.eseo.cc3.model.vm.VirtualMachine.java}, il peut spécifier le nom du magasin de données, en omettant le chemin; le système, c'est-à-dire VirtualCenter ou l'hôte, attribue automatiquement des noms de fichiers et crée des répertoires sur le datastore donné. Par exemple, en spécifiant My_Datastore comme emplacement pour une machine virtuelle appelée MyVm, vous obtenez un emplacement de banque de données de My_Datastore \ MyVm \ MyVm.vmx.
 * 
 * Les banques de données sont configurées par hôte. Dans le cadre de la configuration de l'hôte, un HostSystem peut être configuré pour monter un ensemble de lecteurs réseau. Plusieurs hôtes peuvent être configurés pour pointer vers le même emplacement de stockage. Il n'existe qu'un seul objet Datastore par Datacenter, pour chacun de ces emplacements partagés. Chaque objet Datastore conserve une référence à l'ensemble des hôtes qui ont monté le magasin de données. Un objet Datastore peut être supprimé uniquement si aucun hôte n'est actuellement monté sur le magasin de données.
 * 
 **/

public class DataStore{


	private String name;
	private String type;
	private long freeSpace;
	private long capacity;
	private String datastore;
	private String accessible;
	private String multipleHostAccess;
	private String thinProvisioningSupported;



	//Constructeur avec paramétre
	/**
	 *
	 * Constructeur de la classe DataStore.
	 * 
	 * @param pDatastore [type: String]
	 * @param pName [type: String]
	 * @param pType [type: String]
	 * @param pFreeSpace [type: long]
	 * @param pCapacity [type: long]
	 * @param pAccessible [type: String]
	 * @param pMultipleHostAccess [type: String]
	 * @param pThinProvisioningSupported [type: String]
	 *
	 * */
	public DataStore(String pDatastore, String pName, String pType, long pFreeSpace, long pCapacity, String pAccessible, String pMultipleHostAccess, String pThinProvisioningSupported) {
		this.datastore = pDatastore;
		this.name = pName;
		this.type = pType;
		this.freeSpace = pFreeSpace;
		this.capacity = pCapacity;
		this.accessible = pAccessible;
		this.multipleHostAccess = pMultipleHostAccess;
		this.thinProvisioningSupported = pThinProvisioningSupported;
	}

	/**
	 *
	 * Constructeur de la classe DataStore.
	 * 
	 * @param pDatastore [type: String]
	 * @param pName [type: String]
	 * @param pType [type: String]
	 * @param pFreeSpace [type: long]
	 * @param pCapacity [type: long]
	 *
	 * */
	public DataStore(String pDatastore, String pName, String pType, long pFreeSpace, long pCapacity) {
		this(pDatastore, pName, pType, pFreeSpace, pCapacity, null, null, null);
	}

	/**
	 *
	 * Constructeur de la classe DataStore.
	 * 
	 * @param pAccessible [type: String]
	 * @param pMultipleHostAccess [type: String]
	 * @param pName [type: String]
	 * @param pType [type: String]
	 * @param pFreeSpace [type: long]
	 * @param pThinProvisioningSupported [type: String]
	 *
	 * */
	public DataStore(String pAccessible, String pMultipleHostAccess, String pName, String pType, long pFreeSpace, String pThinProvisioningSupported) {
		this(null, pName, pType, pFreeSpace, 0, pAccessible, pMultipleHostAccess, pThinProvisioningSupported);
	}


	//Constructeur par defaut
	/**
	 * Constructeur par defaut de la classe DataStore.
	 */

	public DataStore() {
		this(null, null, null, 0, 0, null, null, null);
	}

	
	/**
	 * Accesseur getName() recupere le nom du DataStore.
	 * 
	 * @return [type: String] nom du datastore
	 * */
	public String getName() {
		return this.name;
	}

	
	/**
	 * Accesseur getType() recupere le type du DataStore.
	 * 
	 * @return [type: String] type du datastore
	 * */
	public String getType() {
		return this.type;
	}

	public long getFreeSpace() {
		return this.freeSpace;
	}

	/**
	 * Accesseur getCapacity() recupere la capacité du DataStore.
	 * 
	 * @return [type: long] capacity du datastore
	 * */
	public long getCapacity() {
		return this.capacity;
	}

	/**
	 * Accesseur getDatastore() recupere l'identification du DataStore.
	 * 
	 * @return [type: String] datastore du datastore
	 * */
	public String getDatastore() {
		return this.datastore;
	}

	/**
	 * Accesseur getAccessible() recupere l'accessibilite du DataStore.
	 * 
	 * @return [type: String] getAccessible du datastore
	 * */
	public String getAccessible() {
		return this.accessible;
	}

	/**
	 * Accesseur getMultipleHostAccess() recupere le multipleHostAccess du DataStore.
	 * 
	 * @return [type: String] multipleHostAccess du datastore
	 * */
	public String getMultipleHostAccess() {
		return this.multipleHostAccess;
	}

	/**
	 * Accesseur getThinProvisioningSupported() recupere le thinProvisioningSupported du DataStore.
	 * 
	 * @return [type: String] thinProvisioningSupported du datastore
	 * */
	public String getThinProvisioningSupported() {
		return this.thinProvisioningSupported;
	}

	
	/**
	 * Accesseur setName() definis le nom du DataStore.
	 * 
	 * @param [type: String] nom du datastore
	 * */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Accesseur setName() definis le nom du DataStore.
	 * 
	 * @param [type: String] nom du datastore
	 * */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Accesseur setName() definis le nom du DataStore.
	 * 
	 * @param [type: String] nom du datastore
	 * */
	public void setFreeSpace(int freeSpace) {
		this.freeSpace = freeSpace;
	}

	/**
	 * Accesseur setName() definis le nom du DataStore.
	 * 
	 * @param [type: String] nom du datastore
	 * */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Accesseur setName() definis le nom du DataStore.
	 * 
	 * @param [type: String] nom du datastore
	 * */
	public void setDatastore(String datastore) {
		this.datastore = datastore;
	}

	/**
	 * Accesseur setAccessible() definis le nom du DataStore.
	 * 
	 * @param [type: String] accessible du datastore
	 * */
	public void setAccessible(String accessible) {
		this.accessible = accessible;
	}

	/**
	 * Accesseur setMultipleHostAccess() definis multipleHostAccess du DataStore.
	 * 
	 * @param [type: String] multipleHostAccess du datastore
	 * */
	public void setMultipleHostAccess(String multipleHostAccess) {
		this.multipleHostAccess = multipleHostAccess;
	}

	/**
	 * Accesseur setThinProvisioningSupported() definis thinProvisioningSupported du DataStore.
	 * 
	 * @param [type: String] thinProvisioningSupported du datastore
	 * */
	public void setThinProvisioningSupported(String thinProvisioningSupported) {
		this.thinProvisioningSupported = thinProvisioningSupported;
	}
}
