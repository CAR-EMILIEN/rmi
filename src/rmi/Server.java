package rmi;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Server  {

	public static void main(String[] args) throws RemoteException, UnknownHostException {
		SiteItf obj = null;
		String name = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/Node_";
		try {			
				obj = new SiteImpl(0);
				Naming.bind(name+0, obj);
				System.out.println("HelloServer declare aupres du serveur de noms");
			} catch (Exception e) {
				System.out.println("HelloImpl erreur : " + e.getMessage());
				e.printStackTrace();
			}
		for(int i=0;i<3;i++)
		{
			try {
				obj= (SiteItf) Naming.lookup(name+i);
			} catch (MalformedURLException | NotBoundException e) {
				e.printStackTrace();
			}
			obj.addChild((2*i)+1);
			obj.addChild((2*i)+2);
			
		}
		
	}

}
