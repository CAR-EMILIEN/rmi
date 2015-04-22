package rmi;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class SiteImpl extends UnicastRemoteObject implements SiteItf{
	
	private static final long serialVersionUID = 1L;
	private String msg;
	private ArrayList<SiteItf> fils;
	private int id;
	
	@Override
	public String getMsg() throws RemoteException {
		return msg;
	}
	
	@Override
	public void setMsg(String msg) throws RemoteException  {
		this.msg = msg;
	}

	@Override
	public ArrayList<SiteItf> getFils() throws RemoteException  {
		return fils;
	}

	@Override
	public void setFils(ArrayList<SiteItf> fils) throws RemoteException  {
		this.fils = fils;
	}

	@Override
	public int getId() throws RemoteException  {
		return id;
	}

	@Override
	public void setId(int id) throws RemoteException  {
		this.id = id;
	}

	public SiteImpl(int id) throws RemoteException {
		super();
		this.id = id;
		this.fils = new ArrayList<SiteItf>();
		this.msg = "";
		
		try {
			Naming.rebind("rmi://" + InetAddress.getLocalHost().getHostAddress() + "/Node_" + this.id , this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SiteImpl(int id, ArrayList<SiteItf> fils) throws RemoteException {
		super();
		this.id = id;
		this.fils = fils;
		this.msg = "";
		
	}
	
	public String toString()
	{
		return this.msg;
	}

	@Override
	public void sendMsg(final String msg) throws RemoteException {
		String name = null;
		try {
			name = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/Node_";
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SiteItf node = null;
		try {
			node = (SiteItf) Naming.lookup(name + this.id);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		node.setMsg(msg);
		System.out.println(msg + " " + node.getId());
		for (final SiteItf f : node.getFils()) {
			new Thread() {
				public void run() {
					synchronized(this) {
						try {
							f.sendMsg(msg);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
		try {
			Naming.rebind(name+node.getId(), node);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addChild(int id) throws RemoteException
	{
		try {
			String name = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/Node_";
			SiteItf sp = (SiteItf) Naming.lookup(name+this.id);
			
			SiteItf si = new SiteImpl(id);
			sp.getFils().add(si);
			
			Naming.rebind(name+si.getId(), si);
			Naming.rebind(name+sp.getId(), sp);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
