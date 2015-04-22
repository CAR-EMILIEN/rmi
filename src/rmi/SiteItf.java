package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SiteItf extends Remote {
	
	public void sendMsg(String msg) throws RemoteException;

	public void addChild(int i) throws RemoteException;

	public String getMsg() throws RemoteException;

	void setId(int id) throws RemoteException;

	int getId() throws RemoteException;

	void setFils(ArrayList<SiteItf> fils) throws RemoteException;

	ArrayList<SiteItf> getFils() throws RemoteException;

	void setMsg(String msg) throws RemoteException;

}
