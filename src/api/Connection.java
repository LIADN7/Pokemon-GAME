package api;

import java.util.Collection;
import java.util.HashMap;

public class Connection {
	private HashMap<Integer,edge_data> forward;
	private HashMap<Integer,Integer> backwards;
	private int key;
	
	public Connection(int key) {
		this.key = key;
		this.forward = new HashMap<Integer,edge_data>();
		this.backwards = new HashMap<Integer,Integer>();
	}
	
	public void addTo(int num , double w) {
		if(forward.containsKey(num)) {
			if(forward.get(num).getWeight() != w) {
				edge_data e = new Edges(this.key , num, w);
				forward.replace(num, forward.get(num), e);
			}
		}
		else {
			edge_data e = new Edges(this.key , num, w);
			forward.put(num, e);
		}
	}
	
	public void addFrom(int ni) {
		if(!backwards.containsKey(ni)) {
			backwards.put(ni, ni);
		}
	}
	
	public edge_data getEdge(int dest) {
		if(forward.containsKey(dest)) {
			return forward.get(dest);
		}
		return null;
	}
	
	public  Collection<edge_data> getF() {
		return forward.values();
	}
	
	public  Collection<Integer> getB() {
		return backwards.values();
	}
	
	public void removeNodeF(int node) {
		// TODO Auto-generated method stub
		if(forward.containsKey(node)) {
			forward.remove(node);
		}
	}
	
	public void removeNodeB(int node) {
		// TODO Auto-generated method stub
		if(backwards.containsKey(node))
			backwards.remove(node);
	}
	
	public int getKey() {
		// TODO Auto-generated method stub
		return key;
	}
	
}
