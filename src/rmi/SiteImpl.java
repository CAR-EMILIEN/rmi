package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class SiteImpl extends UnicastRemoteObject implements SiteItf{

	public String msg;
	private static final long serialVersionUID = 1L;

	public SiteImpl() throws RemoteException {
		super();
		
	}
	
	public String toString()
	{
		return "ola";
	}

	@Override
	public String sendMsg(String msg) throws RemoteException {
		this.msg = msg;
		return msg;
	}

}
