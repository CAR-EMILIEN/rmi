package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SiteItf extends Remote {
	
	public String sendMsg(String msg) throws RemoteException;

}
