
package fr.eseo.cc3.clientapi;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Base64;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.VirtualDevice;
import com.vmware.vim25.VirtualMachineTicket;
import com.vmware.vim25.mo.Datastore;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;
import com.vmware.vim25.mox.VirtualMachineDeviceManager;

import fr.eseo.cc3.model.exception.ExceptionAlimentationVm;
import fr.eseo.cc3.model.exception.ExceptionConnectionVSphere;

/**
 * @author Alexis Lefort
 * @version 1.1
 * Specification disponible sur Lucidchart
 */
public class ClientVSphere implements Serializable {
	
	//------------------Constantes-------------------------------------------
	
	/**
	 * Permet de rendre la classe sérialisable
	 */
	private static final long serialVersionUID = -8671185421685852547L;


/**
 * RessourceBundle nécéssaire pour accèder au fichier de configuration configAPI
 */
public static final ResourceBundle CONFIGURATION_API = ResourceBundle.getBundle("configAPI");


private static final String PROTOCOLE = "https://";

	//------------------Attributs--------------------------------------------		
		/**
		 * Adresse ipv4 du serveur VSphere avec lequel on communique
		 * @since 1.0
		 */
		private String serverIP;
		
		/**
		 * token d'identification pour VSphere
		 * @since 1.0
		 */
		private String token;
		
		/**
		 * login de connection à vsphere
		 * 
		 * @since 1.0
		 */
		private String user;
		
		/**
		 * mot de passe associé au login
		 * 
		 * @since 1.0
		 */
		private String password;

	//------------------Constructors-----------------------------------------

			
		/**
		 * Créer un ClientVSphere pour communiquer avec l'API de VMware à un serveur donné
		 * avec un token d'identification donnée
		 * 
		 * @param serverIP : adresse ipv4 du serveur VSphere.
		 * Le port peut également être précisé si besoin.
		 * Exemple : 192.172.7.56:8080
		 * @param user : login de connection à vsphere
		 * @param password : mot de passe associé au login
		 * 
		 * @since 1.0
		 */
		public ClientVSphere(String serverIP,String user,String password) {
			this.setServerIP(serverIP);
			this.setUser(user);
			this.setPassword(password);
		}
		
		/**
		 * Créer un ClientVSphere pour communiquer avec l'API de VMware au serveur par defaut
		 * ipv4 : 192.168.4.42
		 * 
		 * @param user : login valide de connection à vsphere
		 * @param password : mot de passe associé au login
		 * 
		 * @since 1.0
		 */
		public ClientVSphere(String user,String password) {
			this(CONFIGURATION_API.getString("DEFAUT_IP").trim(),user,password);
		}
	//------------------Getters/Setters---------------------------------------
		/**
		 * @return Adresse ipv4 du serveur VSphere 
		 * @since 1.0
		 */
		public String getServerIP() {
			return serverIP;
		}

		/**
		 * @param serverIP : adresse ipv4 du serveur VSphere. 
		 * Exemple : 192.172.7.56
		 * 
		 * @since 1.0
		 */
		private void setServerIP(String serverIP) {
			this.serverIP = serverIP;
		}
		
		
		/**
		 * Donne le token d'authentification nécessaire aux requêtes vers l'API de VSphere
		 * 
		 * @return le token d'authentification
		 * 
		 * @since 1.0
		 */
		public String getToken() {
			return this.token;
		}

		
		/**
		 * Modifie le token d'authentification nécessaire aux requêtes vers l'API de VSphere
		 * 
		 * @param token : le token d'authenfication
		 * 
		 * @since 1.0
		 */
		private void setToken(String token) {
			this.token = token;
		}
		
		/**
		 * Donne l'identifiant de connection utilisé
		 * 
		 * @return l'identifiant de connection
		 * 
		 * @since 1.0
		 */
		public String getUser() {
			return user;
		}

		/**
		 * Modifie l'identifiant de connection utilisé
		 * 
		 * @param user 
		 * 
		 * @since 1.0
		 */
		public void setUser(String user) {
			this.user = user;
		}

		/**
		 * @return mot de passe associé au login
		 * 
		 * @since 1.0
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password : mot de passe associé au login
		 * 
		 * @since 1.0
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		
		
	//------------------Methodes-----------------------------------------------
		
		/**
		 * Transforme un objet InputStream en un String formaté JSON
		 * @param stream : l'objet InputStream à convertir
		 * @return un String au format JSON
		 * @throws IOException 
		 * 
		 * @since 1.0
		 */
		private String convertStreamToString(InputStream stream) throws IOException {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			  StringBuilder sb = new StringBuilder();

			  String line = null;
			  try {
			      while ((line = reader.readLine()) != null) {
			          sb.append(line + "\n");
			          
			      }
			  } finally {
			          stream.close();
			  }
			return sb.toString();
			
		}
		
		
		/**
		 * retourne un objet {@link HttpsURLConnection} paramètré à une URL donnée et un type de 
		 * méthode de requête. Egalement l'objet est paramètré comme ceci : <br/>
		 * setDoOutput : true </br>
		 * setDoInput : true </br>
		 * setUsesCaches : false
		 * 
		 * @param url : l'url à laquelle se connecter
		 * 
		 * @param typeMethode : la methode de requête de la connection https
		 * 
		 * @return l'objet {@link HttpsURLConnection} construit
		 * @throws IOException 
		 * @since 1.0
		 */
		private HttpsURLConnection createHttpsUrlConnection(String url,String typeMethode) throws IOException {
	        URL adresse;
	        
	        HttpsURLConnection connector =null;

				adresse = new URL(url);
				connector = (HttpsURLConnection) adresse.openConnection();
		        connector.setRequestMethod(typeMethode);
		        connector.setDoOutput(true);
		        connector.setDoInput(true);
		        connector.setUseCaches(false);
			return connector;
			
		}
	        

		/**
		 * Permet de se connecter à VSphere et de récupérer le token d'identification avec un login et un mot de passe
		 * 
		 * @param user nom d'utilisateur
		 * @param password mot de passe de l'utilisateur
		 * @throws IOException 
		 * @throws ExceptionConnectionVSphere 
		 */
		public void login() throws IOException, ExceptionConnectionVSphere {
			
			HttpsURLConnection connector;
			
			String url = PROTOCOLE+this.getServerIP() + CONFIGURATION_API.getString("SESSION").trim();
			String authorization = this.getUser() + ":" + this.getPassword();
            String authorizationHeader = "Basic "+ Base64.getEncoder().encodeToString(authorization.getBytes());
            
            
            connector = this.createHttpsUrlConnection(url, "POST");
            connector.setRequestProperty("Authorization", authorizationHeader);
            
			String stream = convertStreamToString(connector.getInputStream());
			JSONObject obj = new JSONObject(stream);
			String token = (String) obj.get("value");
			
			this.checkResponse(connector.getResponseCode(),url); // on verifie que la requête c'est bien passée
			
			this.setToken(token);
            
		}
		
		/**
		 * Permet de fermer la session à vsphere et d'invalider le token
		 * 
		 * @throws IOException 
		 * @throws ExceptionConnectionVSphere 
		 * 
		 */
		public void logout() throws IOException, ExceptionConnectionVSphere {
			HttpsURLConnection connector;
			String url = PROTOCOLE + this.getServerIP()+CONFIGURATION_API.getString("SESSION").trim() ;
			connector = this.createHttpsUrlConnection(url, "DELETE");
			connector.setRequestProperty("Cookie", "vmware-api-session-id="+this.getToken());
			this.checkResponse(connector.getResponseCode(),url); // on verifie que la requête c'est bien passée
			this.setToken(null);
			
		}
		
		/**
		 * Permet de vérifier si le token d'authentification est toujours valide
		 * 
		 * @return un booléen présencisant si le token est encore valide
		 * @throws IOException 
		 * @throws ExceptionConnectionVSphere 
		 */
		private boolean isSessionAlive() throws IOException, ExceptionConnectionVSphere {
			HttpsURLConnection connector;
			boolean alive = false;
			
			String url = PROTOCOLE+this.getServerIP() +CONFIGURATION_API.getString("SESSION")+"?~action=get";
			connector = this.createHttpsUrlConnection(url, "POST");
			connector.setRequestProperty("Cookie", "vmware-api-session-id="+this.getToken());
			
			int codeResponse = connector.getResponseCode();
			
			if (codeResponse==200) {
				alive = true;
			} else if(codeResponse==401) {
				alive = false;
			} else {
				throw new ExceptionConnectionVSphere("la requete à retourner le code d'erreur "+codeResponse, codeResponse,url);
			}
			
			
			return alive;
		}
		
		
		/**
		 * Permet d'envoyer une requête type "Get" à VSphere pour récupèrer une ressoruce
		 * @param requete adresse de la ressource à récupèrer
		 * @return la réponse à la requête dans un String formaté JSON
		 * @throws IOException 
		 * @throws ExceptionConnectionVSphere 
		 */
		public String getData(String requete) throws IOException, ExceptionConnectionVSphere {
			
			if (!this.isSessionAlive()) {
				this.login();
			}
			
			HttpsURLConnection connector;
			String url = PROTOCOLE+this.getServerIP() + requete;
			connector = this.createHttpsUrlConnection(url, "GET");
			connector.setRequestProperty("Cookie", "vmware-api-session-id="+this.getToken());
			
			this.checkResponse(connector.getResponseCode(),url); // on verifie que la requête c'est bien passée

			return convertStreamToString(connector.getInputStream());

		}
		
		/**
	   	Permet d'envoyer une requête avec un body en json.
	    @param pMethod POST pour une requête post (ex : création vm) ou PATCH (ex : modification hardware vm).
	    @param pRequest Terminaison HTTP de la requête en fonction de si on souhaite envoyer des données VM, Datastore ou autre.
	    @param pBody La requête JSON transformée en String.
	    @return Le résultat de la requête (vide ou nom de vm, ça dépend de la requête effectuée)
		 */
		public String sendData(String pMethod, String pRequest,String pBody) throws IOException, ExceptionConnectionVSphere {
			if (!this.isSessionAlive()) {
				this.login();
			}
			
			String url = PROTOCOLE+this.getServerIP() + pRequest;
			System.out.print(url);
			URL obj = new URL(url);
			
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Cookie", "vmware-api-session-id="+this.getToken());
			con.addRequestProperty("Content-type", "application/json");
			if(pMethod=="PATCH") {
				con.addRequestProperty("X-HTTP-Method-Override", "PATCH");
			}
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			
			byte[] outputInBytes = pBody.getBytes("UTF-8");
			OutputStream os = con.getOutputStream();
			os.write(outputInBytes);
			os.flush();
			os.close();
			
			this.checkResponse(con.getResponseCode(), url);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			return response.toString();

		}
		
		
		
		/**
		 * Permet d'allummer, éteindre ou suspendre une vm
		 * 
		 * @param vm : identifiant de la vm dont on veut changer l'état
		 * @param etat : entier indiquant l'action que l'on veut effectuer <br/>
		 * 0 : éteindre <br/>
		 * 1 : allumer <br/>
		 * 2 : suspendre <br/>
		 * @return le code réponse de la requête url ou 1000 si le le paramètre etat n'est pas valide
		 * @throws IOException
		 * @throws ExceptionConnectionVSphere 
		 * @throws ExceptionAlimentationVm 
		 */
		public void changeAlimentationVm(String vm,int etat) throws IOException, ExceptionConnectionVSphere {
			HttpsURLConnection connector;
			
			if (!this.isSessionAlive()) {
				this.login();
			}
			
			
			String url = PROTOCOLE + this.getServerIP() + CONFIGURATION_API.getString("POWER_VM").trim()+"/"+vm+"/";
			switch (etat) {
			case(0) :
				url =url+ "power/stop";
				break;
			
			case(1) :
				url =url + "power/start";
				break;
				
			case(2) :
				url = url +"power/suspend";
				break;
				
			default :
				throw new ExceptionConnectionVSphere("l'état "+etat+" n'existe pas!!");
			}
			connector = this.createHttpsUrlConnection(url, "POST");
			connector.setRequestProperty("Cookie", "vmware-api-session-id="+this.getToken());
			
			int codeReponse = connector.getResponseCode();
			
			this.checkResponse(codeReponse, url);
		}
		
		
		/**
		 * Permet d'envoyer une requête type "Delete" à VSphere pour récupèrer une ressource.
		 * @param requete adresse de la ressource à supprimer
		 * @throws IOException
		 * @throws ErrorConnectionVSphere
		 * @since 1.1
		 */
		public void deleteData(String requete) throws IOException, ExceptionConnectionVSphere {
			if (!this.isSessionAlive()) {
				this.login();
			}
			
			HttpsURLConnection connector;
			String url = PROTOCOLE+this.getServerIP() + requete;
			connector = this.createHttpsUrlConnection(url, "DELETE");
			connector.setRequestProperty("Cookie", "vmware-api-session-id="+this.getToken());
			
			this.checkResponse(connector.getResponseCode(),url); // on verifie que la requête c'est bien passée
		}
		
		
		/**
		 * Permet de récupérer le ticket d'authentification nécessaire pour l'utilisation de la console
		 * @param vmName le nom de la vm dont on veut récupèrer le ticket
		 * @return un {@link VirtualMachineTicket} 
		 * @throws MalformedURLException 
		 * @throws RemoteException 
		 */
		public VirtualMachineTicket getVirtualMachineTicket(String vmName) throws RemoteException, MalformedURLException {
			ServiceInstance si = this.getServiceInstance();
			si.getServerConnection();
			
			VirtualMachine vm = this.getVirtualMachine(vmName,si);
			VirtualMachineTicket ticket=vm.acquireTicket("webmks");
			
			si.getServerConnection().logout();
			return  ticket;
		
		}
		
		
		/**
		 * Permet de récupérer un objet ServiceInstance nécessaire à l'utilisation des requêtes de VI Java
		 * @return un {@link ServiceInstance}
		 * @throws RemoteException
		 * @throws MalformedURLException
		 */
		private ServiceInstance getServiceInstance() throws RemoteException, MalformedURLException {
			return new ServiceInstance(new URL(PROTOCOLE+this.getServerIP()+"/sdk"), this.getUser(), this.getPassword(), true);
		}
		
		/**
		 * Permet de récupèrer un objet représentant une machine virtuelle avec Vi Java
		 * @param vmName le nom de la vm à récupèrer
		 * @param si un objet {@link ServiceInstance}
		 * @return un objet {@link VirtualMachine}
		 * 
		 * @throws RemoteException 
		 * 
		 * @see getServiceInstance
		 */
		public VirtualMachine getVirtualMachine(String vmName, ServiceInstance si) throws RemoteException {
			
			VirtualMachine vm=null;
			Folder rootFolder = si.getRootFolder(); 
			ManagedEntity[] mes = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine"); 
			
			boolean find =false;
			for(int i=0; i < mes.length && !find; i++) {
				vm = (VirtualMachine) mes[i];
				if(vm.getName().equals(vmName)) {
					find=true;
				}
			}
			if(!find) {
				vm=null;
			}
			return vm;
			
			
		}
		
		
		
		/**
		 * Permet de mettre un iso dans les machines virtuelles du vcenter
		 * @param vmName le nom de la vm dans laquelle on veut insérer l'iso
		 * @param isoPath le chemin vers le fichier iso stocker dans un datastore. <br>
		 * 	ex : [datastore33-1] ISO/< nom iso>
		 * @throws RemoteException
		 * @throws MalformedURLException
		 * @throws InterruptedException
		 */
		public void mountIso(String vmName, String isoPath) throws RemoteException, MalformedURLException, InterruptedException{
			ServiceInstance si = this.getServiceInstance();

			VirtualMachine vm = this.getVirtualMachine(vmName, si);
			VirtualMachineDeviceManager vmdm = new VirtualMachineDeviceManager(vm);
			
			boolean startConnected = true;
			vmdm.addCdDriveFromIso(isoPath, startConnected);
			
			si.getServerConnection().logout();
		}
		
		/**
		 * Permet de retirer un iso d'une machine virtuelle de vcenter
		 * @param vmName le nom de la vm dont on veut retirer l'iso
		 * @param isoPath le chemin vers le fichier iso qui est actuellement dans la vm <br>
		 * 	ex : [datastore33-1] ISO/< nom iso>
		 * @throws RemoteException
		 * @throws MalformedURLException
		 */
		public void unmountIso(String vmName,String isoPath) throws RemoteException, MalformedURLException {
			ServiceInstance si = this.getServiceInstance();
			
			VirtualMachine vm = this.getVirtualMachine(vmName, si);
			VirtualMachineDeviceManager vmdm = new VirtualMachineDeviceManager(vm);
			VirtualDevice cdrom = vmdm.getDeviceByBackingFileName(isoPath);
			
		    vmdm.removeDevice(cdrom, false);// doit absoulement être à false, sinon cela supprime l'iso du datastore
		    
		    si.getServerConnection().logout();
		}
		
		public Datastore getVmDatastore(String nomVm) throws InvalidProperty, RuntimeFault, RemoteException, MalformedURLException {
			ServiceInstance si = this.getServiceInstance();
			return this.getVirtualMachine(nomVm, si).getDatastores()[0];
		}

		
		
		/**
		 * 
		 * Permet de vérfier si la requête à Vsphere c'est bien déroulée. Sinon une {@link ExceptionConnectionVSphere} est levée.
		 * 
		 * @param codeResponse : code reponse retourné par la requête
		 * @param action : requête envoyer à Vsphere
		 * @throws ExceptionConnectionVSphere
		 */
		private void checkResponse(int codeResponse,String action) throws ExceptionConnectionVSphere {
			if(codeResponse!=200) {
				throw new ExceptionConnectionVSphere("la requete à retourner le code d'erreur "+codeResponse, codeResponse,action);
			}
		}
}
