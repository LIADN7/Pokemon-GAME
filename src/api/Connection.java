package api;

/**
 * this class is part of the DWGraph_DS and present the information of rib on the graph. 
 * the rib has unique key - as the node that needed. and has the information 
 * of:
 * 		1. the key of nodes that has path to this current node.
 * 				save only the key
 * 		2. this ribs also save the nodes that has path from this current node to them
 * 				create edge_data from this node to the other 
 *  
 * @author liadn7
 * @author aviel11
 */
import java.util.Collection;
import java.util.HashMap;

public class Connection {
	private HashMap<Integer,edge_data> forward;
	private HashMap<Integer,Integer> backwards;
	private int key;

	/**
	 * constructor 1 initial the rib with the attach key
	 * @param key - the key of the node needed
	 */
	public Connection(int key) {
		this.key = key;
		this.forward = new HashMap<Integer,edge_data>();
		this.backwards = new HashMap<Integer,Integer>();
	}

	/**
	 * this rib has path from the key node to the given num and has the weight - w
	 * (create edge_data between them
	 * @param num - the key of the node
	 * @param w - the weight (distance) between the nodes
	 */
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

	/**
	 * this rib has path from the given ni to the key node  
	 * @param ni - the key of the node 
	 */
	public void addFrom(int ni) {
		if(!backwards.containsKey(ni)) {
			backwards.put(ni, ni);
		}
	}

	/**
	 * return the edge between 
	 * @param dest - represent the key of the destination node
	 * @return edge_data
	 */
	public edge_data getEdge(int dest) {
		if(forward.containsKey(dest)) {
			return forward.get(dest);
		}
		return null;
	}

	/**
	 * return all the edge_data that start from this node
	 * @return the Collection of all the edge_data
	 */
	public  Collection<edge_data> getF() {
		return forward.values();
	}

	/**
	 * return all the number of the node that has path to this node
	 * @return the Collection of the number
	 */
	public  Collection<Integer> getB() {
		return backwards.values();
	}

	/**
	 * delete the edge_data that his destination is the given node
	 * @param node - the key of the destination node
	 * @return true if contains the node and succesefully deleted
	 */
	public boolean removeNodeF(int node) {
		if(forward.containsKey(node)) {
			forward.remove(node);
			return true;
		}
		return false;
	}

	/**
	 * delete the path from node to this current node
	 * @param node - the key of the source node
	 */
	public void removeNodeB(int node) {
		if(backwards.containsKey(node))
			backwards.remove(node);
	}

	/**
	 * the key of this rib
	 * @return the key of the rib.
	 */
	public int getKey() {
		return key;
	}

}
