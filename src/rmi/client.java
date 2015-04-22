package rmi;

import java.net.InetAddress;
import java.rmi.Naming;

public class client {
	
	
	public static void main(String args[]) {
		try {
			String name = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/Node_"+args[0];
			// Recuperation d'un proxy sur l'objet
			SiteItf obj =
					(SiteItf) Naming.lookup(name);
		// Appel d'une methode sur l'objet distant
			obj.sendMsg("toto");
			System.out.println("toto");
			Naming.rebind(name, obj);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}


}
