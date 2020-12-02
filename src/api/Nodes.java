package api;

import java.util.*;

import com.google.gson.annotations.SerializedName;

public class Nodes implements node_data {
	private static int defualtkey = 0;
	@SerializedName ("id")
	private int key ;
	private int tag , prev;
	private double  NW;
	private String info;
	private HashMap<Integer,Edges> forward;
	private HashMap<Integer,String> backwards;
	@SerializedName ("pos")
	private geo_location local;
	private int sizeOf;

	//constructor.1
	public Nodes() {
		defualtkey++;
		this.key = defualtkey;
		this.info = null;
		this.tag = 0;
		this.NW = 0;
		this.forward = new HashMap<Integer,Edges>();
		this.backwards = new HashMap<Integer,String>();
		this.local = null;
		this.sizeOf = 0;
	}	
	
	public void addTo(int num , double w) {
		if(forward.containsKey(num)) {
			if(forward.get(num).getWeight() != w) {
				Edges e = new Edges(this.key , num, w);
				forward.replace(num, forward.get(num), e);
			}
		}
		else {
			Edges e = new Edges(this.key , num, w);
			forward.put(num, e);
			sizeOf++;
		}
	}
	
	public void addFrom(Nodes n) {
		int num = n.getKey();
		if(!backwards.containsKey(num)) {
			backwards.put(num, "");
		}
	}
	
	public edge_data getEdge(int dest) {
		if(forward.containsKey(dest)) {
			return forward.get(dest);
		}
		return null;
	}
	
	public  Collection<Edges> getF() {
		return forward.values();
	}
	
	public  Collection<Integer> getB() {
		return backwards.keySet();
	}
	
	public void removeNodeF(int node) {
		// TODO Auto-generated method stub
		if(forward.containsKey(node)) {
			forward.remove(node);
			sizeOf--;
		}
	}
	
	public void removeNodeB(int node) {
		// TODO Auto-generated method stub
		if(backwards.containsKey(node))
			backwards.remove(node);
	}
		
	@Override
	public int getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public geo_location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLocation(geo_location p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return NW;
	}

	@Override
	public void setWeight(double w) {
		// TODO Auto-generated method stub
		this.NW = w;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return info;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		this.info = s;
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return tag;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub
		this.tag = t;
	}
	/**
	 * 
	 * @return the number of the nodes this node is connect to
	 */
	
	public int getSizeOf() {
		return this.sizeOf;
	}
	
	public void setPrev(int prev) {
		this.prev = prev;
	}
	
	public int getPrev() {
		return this.prev;
	}
}
