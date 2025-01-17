package fr.eseo.cc3.model;

import java.util.ArrayList;



/**
 * 
 * @author CC groupe 3
 *
 *
 * La classe Cluster.
 * 
 * 
 * Représente un cluster d'objets HostSystem en tant que ressource de calcul unifiée pour les {@link fr.eseo.cc3.model.vm.VirtualMachine.java}. 
 * Ce type est instancié à l'aide de la méthode Folder.createCluster dans l'API vi.
 * 
 **/


public class Cluster {
	
	private String drs_enabled;
	private String cluster;
	private String name;
	private String ha_enabled;
	private String ressourcePool;
	
	ArrayList<Host> listeHost = new ArrayList<Host>();
	
	//Constructeur avec paramétre
	/**
	 * Constructeur de la classe Cluster.
	 * 
	 * @param pName [type: {@link fr.eseo.cc3.model.vm.Memory.java}]
	 * @param pRessourcePool [type: String]
	 * @param plisteHost [type: ArrayList<Host>]
	 * */
		public Cluster (String pName, String pRessourcePool, ArrayList<Host> plisteHost) {

			this.name = pName;
			this.ressourcePool = pRessourcePool;
			this.listeHost=plisteHost;
		}
		
		/**
		 * Constructeur de la classe Cluster.
		 * 
		 * @param pName [type: {@link fr.eseo.cc3.model.vm.Memory.java}]
		 * @param pRessourcePool [type: String]
		 * */
		public Cluster(String pName, String pRessourcePool) {
			this.name=pName;
			this.ressourcePool=pRessourcePool;
		}
		
		//Constructeur par default
		/**
		 * Constructeur par default de la classe Cluster.
		 * 
		 * */
		public Cluster() {
			this.name=null;
			this.ressourcePool=null;
		}
		
		
		
	/**
	 * Accesseur getRessourcePool() recupere la valeur ressourcePool de Cluster.
	 * 
	 * @return [type: String] ressourcePool
	 */
	public String getRessourcePool() {
		return this.ressourcePool;
	}
	
	/**
	 * Accesseur setRessourcePool() definis la valeur ressourcePool de Cluster.
	 * 
	 * @param [type: String] ressourcePool le ressourcePool à set
	 */
	public void setRessourcePool(String ressourcePool) {
		this.ressourcePool=ressourcePool;
	}
	
	/**
	 * Accesseur getRessourcePool() recupere la valeur listeHost de Cluster.
	 * 
	 * @return [type: ArrayList<Host>]
	 */
	public ArrayList<Host> getListeHosts() {
		return this.listeHost;
	}
	
	/**
	 * Accesseur setListeHosts() definis la valeur listeHost de Cluster.
	 * 
	 * @param [type: ArrayList<Host>] listeHost la ArrayList<Host> à set
	 */
	public void setListeHosts(ArrayList<Host> listeHost) {
		this.listeHost = listeHost;
	}
	
	/**
	 * Accesseur getDrs_enabled() recupere la valeur drs_enabled de Cluster.
	 * 
	 * @return [type: String] drs_enabled 
	 */
	public String getDrs_enabled() {
		return this.drs_enabled;
	}
	
	/**
	 * Accesseur setDrs_enabled() definis la valeur drs_enabled de Cluster.
	 * 
	 * @param [type: String] drs_enabled le drs_enabled à set
	 */
	public void setDrs_enabled(String drs_enabled) {
		this.drs_enabled = drs_enabled;
	}
	
	/**
	 * Accesseur getCluster() recupere la valeur cluster de Cluster.
	 * 
	 * @return [type: String] cluster le cluster à set
	 */
	public String getCluster() {
		return this.cluster;
	}
	
	/**
	 * Accesseur setCluster() definis la valeur cluster de Cluster.
	 * 
	 * @param [type: String] cluster le cluster à set
	 */
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	
	/**
	 * Accesseur getName() recupere la valeur name de Cluster.
	 * 
	 * @return [type: String]
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Accesseur setName() definis la valeur name de Cluster.
	 * 
	 * @param [type: String] name le nom à set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Accesseur getHa_enabled() recupere la valeur ha_enabled de Cluster.
	 * 
	 * @return [type: String] ha_enabled le ha à set
	 */
	public String getHa_enabled() {
		return this.ha_enabled;
	}
	
	/**
	 * Accesseur setHa_enabled() definis la valeur ha_enabled de Cluster.
	 * 
	 * @param [type: String] ha_enabled le ha à set
	 */
	public void setHa_enabled(String ha_enabled) {
		this.ha_enabled = ha_enabled;
	}
	

		
}
