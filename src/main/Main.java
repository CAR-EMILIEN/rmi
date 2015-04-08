package main;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmi.SiteImpl;
import tree.RMITree;

public class Main {

	public static void main(String[] args) throws RemoteException {
	
		try {
				SiteImpl obj = new SiteImpl();
				Naming.rebind("Server", obj);
				System.out.println("HelloServer d�clar� aupr�s du serveur de noms");
			} catch (Exception e) {
				System.out.println("HelloImpl erreur : " + e.getMessage());
				e.printStackTrace();
			}
	}
}
