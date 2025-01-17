package fr.eseo.cc3.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.vmware.vim25.HttpNfcLeaseDeviceUrl;
import com.vmware.vim25.HttpNfcLeaseInfo;
import com.vmware.vim25.HttpNfcLeaseState;
import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.OvfCreateDescriptorParams;
import com.vmware.vim25.OvfCreateDescriptorResult;
import com.vmware.vim25.OvfFile;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.HttpNfcLease;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

import fr.eseo.cc3.dao.bean.Utilisateur;
import fr.eseo.cc3.json.LeaseProgressUpdater;

/**
 * Servlet implementation class ExportOVA
 * Recupere la machine virtuelle que l'on a selectionné
 * Export de la machine virtuelle avec la methode de l'api vi (import vim25)
 * 
 */
@WebServlet("/ExportOVF")
public class ServletExportOVF extends ServletCommon {
	private static final long serialVersionUID = 1L;
	public static LeaseProgressUpdater leaseProgUpdater;


	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String servletOut;
		
		// On recupere l'utilisateur en cours et specifions la redirection
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String out=this.checkRole("AccueilAdmin","AccueilReferent" , "AccueilEtudiant", utilisateur);
		if(out.equals(PAGE_ERREUR_DROITS)) {
			request.getRequestDispatcher(out).forward( request, response );
		}else {
		
		
		String vmId = request.getParameter("vmId"); // identifiant de la vm 
		String vAppOrVmName = request.getParameter("vmName"); // Nom de la vm 
		String exportName = request.getParameter("nomExport"); // Nom de la vm 
		System.out.println(exportName);
		
		// Connection au vSphere avec vim25, API vi 
		ServiceInstance si = new ServiceInstance(new URL(this.getClient(request).getServerIP()+"sdk"), this.getClient(request).getUser(), this.getClient(request).getPassword(), true);
		Folder rootFolder = si.getRootFolder(); 
	    
	    
	  
		
		
		
	    // Code importer de http://www.doublecloud.org/2010/04/how-to-import-and-export-ovf-packages/
		// LeaseProgressUpdater aussi importer se trouve dans "fr.eseo.cc3.json"
		
		
	    String hostip = getHostIp(rootFolder, vAppOrVmName);
	    String entityType = "VirtualMachine";
	    String targetDir = "/Users/tehio/Desktop/" ;
	    String diectory = targetDir + vAppOrVmName;
	    
	    //HostSystem host1 = (HostSystem) si.getSearchIndex().findChild(null, vAppOrVmName) ;
	    //HostSystem host = (HostSystem) si.getSearchIndex().findByIp(null, hostip, false); 

	    InventoryNavigator iv = new InventoryNavigator(rootFolder);
	    
	    HttpNfcLease hnLease = null;
	    ManagedEntity me = null;

	    me = iv.searchManagedEntity("VirtualMachine", vAppOrVmName);
	    VirtualMachine convertVM = (VirtualMachine)me;
	    hnLease = convertVM.exportVm();
	    if(convertVM.getRuntime().getPowerState().toString().equals("poweredOn")){
	    	//TODO DIACRE ERROR Machine allumée on ne peut pas faire un export d'une machine allumée
	    	request.getRequestDispatcher(out).forward( request, response ); 
	    }
	    
	    
	    // Wait until the HttpNfcLeaseState is ready
	    HttpNfcLeaseState hls;
	    for(;;) {
	      hls = hnLease.getState();
	      if(hls == HttpNfcLeaseState.ready) {
	        break;
	      } if(hls == HttpNfcLeaseState.error) {
	    	//TODO DIACRE ERROR connection http non resolue
	    	  request.getRequestDispatcher(out).forward( request, response );
	    	  si.getServerConnection().logout();
	    	  return;
	      }
	    }
	    HttpNfcLeaseInfo httpNfcLeaseInfo = hnLease.getInfo();
	    httpNfcLeaseInfo.setLeaseTimeout(300*1000*1000);

	    //Note: the diskCapacityInByte could be many time bigger than
	    //the total size of VMDK files downloaded. 
	    //As a result, the progress calculated could be much less than reality.
	    long diskCapacityInByte = (httpNfcLeaseInfo.getTotalDiskCapacityInKB()) * 1024;

	    leaseProgUpdater = new LeaseProgressUpdater(hnLease, 5000);
	    leaseProgUpdater.start();

	    long alredyWrittenBytes = 0;
	    HttpNfcLeaseDeviceUrl[] deviceUrls = httpNfcLeaseInfo.getDeviceUrl();
	    if (deviceUrls != null) 
	    {
	      OvfFile[] ovfFiles = new OvfFile[deviceUrls.length];
	      for (int i = 0; i < deviceUrls.length; i++) 
	      {
	        String deviceId = deviceUrls[i].getKey();
	        String deviceUrlStr = deviceUrls[i].getUrl();
	        String diskFileName = deviceUrlStr.substring(deviceUrlStr.lastIndexOf("/") + 1);
	        String diskUrlStr = deviceUrlStr.replace("*", hostip);
	        
	        
	        String cookie = si.getServerConnection().getVimService().getWsc().getCookie();
	        long lengthOfDiskFile = writeVMDKFile(vAppOrVmName, diskUrlStr, cookie, alredyWrittenBytes, diskCapacityInByte);
	        alredyWrittenBytes += lengthOfDiskFile;
	        OvfFile ovfFile = new OvfFile();
	        ovfFile.setPath(diskFileName);
	        ovfFile.setDeviceId(deviceId);
	        ovfFile.setSize(lengthOfDiskFile);
	        ovfFiles[i] = ovfFile;
	      }
	      OvfCreateDescriptorParams ovfDescParams = new OvfCreateDescriptorParams();
	      ovfDescParams.setOvfFiles(ovfFiles);
	      OvfCreateDescriptorResult ovfCreateDescriptorResult = 
	        si.getOvfManager().createDescriptor(me, ovfDescParams);

	      String ovfPath = targetDir + vAppOrVmName + ".ovf";
	      FileWriter outFile = new FileWriter(ovfPath);
	      outFile.write(ovfCreateDescriptorResult.getOvfDescriptor());
	      outFile.close();
	    } 
	    
	    leaseProgUpdater.interrupt();
	    hnLease.httpNfcLeaseProgress(100);
	    hnLease.httpNfcLeaseComplete();

	    si.getServerConnection().logout();
		
	   
		
		}
		System.out.println(out);
		 response.sendRedirect(out);
	}
	
	
	
	


	private static long writeVMDKFile(String directory, String diskUrl, String cookie,  long bytesAlreadyWritten, long totalBytes) throws IOException, ServletException  {
		
		HttpsURLConnection conn = getHTTPConnection(diskUrl, cookie);
		InputStream in = conn.getInputStream();
		OutputStream outStream = new FileOutputStream(directory);
		byte[] buf = new byte[102400];
		int len = 0;
		long bytesWritten = 0;
		while ((len = in.read(buf)) > 0)  {
			outStream.write(buf, 0, len);
			bytesWritten += len;
			int percent = (int)(((bytesAlreadyWritten + bytesWritten) * 100) / totalBytes);
			leaseProgUpdater.setPercent(percent);
		}
		in.close();
		outStream.close();
		return bytesWritten;
	}
	
	
	
	private static TrustManager[ ] get_trust_mgr() {
	     TrustManager[ ] certs = new TrustManager[ ] {
	        new X509TrustManager() {
	           public X509Certificate[ ] getAcceptedIssuers() { return null; }
	           public void checkClientTrusted(X509Certificate[ ] certs, String t) { }
	           public void checkServerTrusted(X509Certificate[ ] certs, String t) { }
	         }
	      };
	      return certs;
	  }
	
	

	private static HttpsURLConnection getHTTPConnection(String urlStr, String cookieStr) throws IOException {
		HostnameVerifier hv = new HostnameVerifier()  {
			public boolean verify(String urlHostName, SSLSession session)  {
				return true;
			}
		};
		// Create a context that doesn't check certificates.
	    SSLContext ssl_ctx = null;
		try {
			ssl_ctx = SSLContext.getInstance("TLS");
		
	    TrustManager[ ] trust_mgr = get_trust_mgr();

			ssl_ctx.init(null,                // key manager
			             trust_mgr,           // trust manager
			             new SecureRandom());
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // random number generator
	    HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
		HttpsURLConnection conn = null;
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			URL url = new URL(urlStr);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(true);
			conn.setRequestProperty("Cookie","vmware-api-session-id=" +	cookieStr);
            
            conn.connect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// Guard against "bad hostname" errors during handshake.
	    conn.setHostnameVerifier(new HostnameVerifier() {
	        public boolean verify(String host, SSLSession sess) {
	            if (host.equals("192.168.4.42")) return true;
	            else return false;
	        }
	    });
		return conn;
	
	}
	
	
	
	private String getHostIp(Folder rootFolder, String vmName) throws InvalidProperty, RuntimeFault, RemoteException {
		ManagedEntity[] hostlist = new InventoryNavigator(rootFolder).searchManagedEntities("HostSystem");
		HostSystem host;
		String ip = null;
		for(int i=0; i < hostlist.length; i++) {
			host = (HostSystem) hostlist[i];
			for(int machine=0; machine < host.getVms().length; machine++) {
				if(vmName.equals(host.getVms()[machine].getName())) {
					ip = host.getConfig().getNetwork().getVnic()[0].getSpec().getIp().getIpAddress();
				}
			}
		}
		
		return ip;
		
	}
	
	
	
	
	
	
	
}
