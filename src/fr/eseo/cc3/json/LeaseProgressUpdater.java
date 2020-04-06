package fr.eseo.cc3.json;


import java.rmi.RemoteException;

import org.slf4j.event.Level;

import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.Timedout;
import com.vmware.vim25.mo.HttpNfcLease;

/**
 * http://vijava.sf.net
 * @author Steve Jin (sjin@vmware.com)
 */

public class LeaseProgressUpdater extends Thread {
	
	private HttpNfcLease httpNfcLease = null;
	private int progressPercent = 0;
	private int updateInterval;

	public LeaseProgressUpdater(HttpNfcLease httpNfcLease, int updateInterval) {
		this.httpNfcLease = httpNfcLease;
		this.updateInterval = updateInterval;
	}

	public void run() {
		while (true) {
			try {
				httpNfcLease.httpNfcLeaseProgress(progressPercent);
				Thread.sleep(updateInterval);
			} catch(InterruptedException ie) {
				Thread.currentThread().interrupt();
				break;
			} catch (Timedout e) {
				e.printStackTrace();
			} catch (RuntimeFault e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setPercent(int percent) {
		this.progressPercent = percent;
	}
	
	
}