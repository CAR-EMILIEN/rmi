package rmi;

import java.rmi.Naming;

public class client {
	
	
	public static void main(String args[]) {
		try {
			// Récupération d'un proxy sur l'objet
			SiteItf obj =
					(SiteImpl) Naming.lookup("//machine/HelloServer");
			// Appel d'une méthode sur l'objet distant
			String message = obj.sendMsg("toto");
			System.out.println(message);
	} catch (Exception e) {
		e.printStackTrace();
	}
	}


}
