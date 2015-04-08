package tree;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import rmi.SiteImpl;

public class RMITree {

	public RMITree root;
	public RMITree pere;
	public List<RMITree> fils;
	public SiteImpl rmi;
	
	public RMITree() throws RemoteException
	{
		this.root=this;
		this.pere=null;
		this.fils=new ArrayList<RMITree>();
		this.rmi=new SiteImpl();
	}
	
	public RMITree(SiteImpl s) throws RemoteException
	{
		this();
		this.rmi=s;
	}
	
	public void addFils(RMITree s)
	{
		fils.add(s);
		s.pere = this;
		s.root = this.root;
	}
	
	public boolean isRoot()
	{
		return pere!=null;
	}
	
	public boolean isLeaf()
	{
		return fils.isEmpty();
	}
	
	@Override
	public String toString()
	{
		return this.root.toString(0);
	}
		
	
	public String toString(int depth)
	{
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<depth;i++)
		{
			sb.append(" ");
		}
			sb.append(this.rmi.toString());
			sb.append("\n");
		
		for(RMITree rmit : fils)
		{
			sb.append(rmit.toString(depth+1));
		}

		return sb.toString();
	}
	
}
