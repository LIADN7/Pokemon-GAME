package api;

import java.util.*;

/**
 * this class is presenting a directional weighted (positive) graph. . 
 * the graph is contains nodes who are very similar to dots inside regular graph
 * in this class we can add and delete nodes , connect between them or disconnect,
 * who connect to who is important.
 * get know the edge between two nodes if there is any and get the information of 
 * any node inside the graph. 
 * all the nodes must have a unique key.
 * in addition - the graph remember the number of nodes, edges and 
 * how many changes was make.
 * @author liadn7
 * @author avielc11
 */
public class DWGraph_DS implements directed_weighted_graph {
	private int edgeSize , nodeSize, change;
	private HashMap<Integer,node_data> nodes;
	private HashMap<Integer,Connection> ribs;
	
	/**
	 * constructor1 - build a new graph. 
	 * the graph has no edges,nodes and no changes was make.
	 */
	public DWGraph_DS() {
		edgeSize = 0;
		nodeSize = 0;
		change = 0;
		nodes = new HashMap<Integer,node_data>();
		ribs = new HashMap<Integer,Connection>();
	}
	
	@Override
	public node_data getNode(int key) {
		if(nodes.containsKey(key)) {
			return nodes.get(key);
		}
		return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if(src != dest) {
			if(nodes.containsKey(src)) {
				Connection temp = ribs.get(src);
				edge_data e = temp.getEdge(dest);
				return e;
			}
		}
		return null;
	}

	@Override
	public void addNode(node_data n) {
		Connection temp = new Connection(n.getKey());
		nodes.put(n.getKey(),(Nodes) n);
		ribs.put(temp.getKey(), temp);
		nodeSize++;
		change++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(w >= 0 && src != dest) {
			if(getNode(src) != null && getNode(dest) != null) {
				Connection t1 = ribs.get(src);
				Connection t2 = ribs.get(dest);
				t1.addTo(dest, w);
				t2.addFrom(src);
				edgeSize++;
				change++;
			}
		}
	}

	@Override
	public Collection<node_data> getV() {
		return nodes.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return ribs.get(node_id).getF();
	}

	@Override
	public node_data removeNode(int key) {
		if(getNode(key) != null) {
			node_data temp = getNode(key);
			for(int num : ribs.get(key).getB()) {
				Connection n = ribs.get(num);
				if(n.removeNodeF(key))
					edgeSize--;
			}
			for(edge_data n : ribs.get(key).getF()) {
				Connection behind = ribs.get(n.getDest());
				behind.removeNodeB(key);
				edgeSize--;
			}
			nodes.remove(key);
			ribs.remove(key);
			nodeSize--;
			change++;
			return temp;
		}
		return null;
	}
	
	@Override
	public edge_data removeEdge(int src, int dest) {
		if(src != dest) {
			if(getNode(src) != null && getNode(dest) != null) {
				Connection t1 = ribs.get(src);
				Connection t2 = ribs.get(dest);
				edge_data e = t1.getEdge(dest);
				if(e != null) {
					t1.removeNodeF(dest);
					t2.removeNodeB(src);
					edgeSize--;
					change++;
				}
				return e;
			}
		}
		return null;
	}

	@Override
	public int nodeSize() {
		return nodeSize;
	}

	@Override
	public int edgeSize() {
		return edgeSize;
	}

	@Override
	public int getMC() {
		return change;
	}
	
	/**
	 * chack if two graphs is equals
	 */
	@Override
	public boolean equals(Object b) {
		if(this == b) {
			return true;
		}
		if(b == null) {
			return false;
		}
		if(this.getClass() != b.getClass()) {
			return false;
		}
		if((b instanceof DWGraph_DS)) {
			directed_weighted_graph bb = (DWGraph_DS) b;
			if(this.nodeSize() != bb.nodeSize()) {
				return false;
			}
			if(this.edgeSize() != bb.edgeSize()) {
				return false;
			}
			for(node_data na : this.getV()) {
				for(edge_data ea : this.getE(na.getKey())) {
					edge_data edge = bb.getEdge(na.getKey(), ea.getDest());
					if(edge == null) 
						return false;
					if(ea.getWeight() != edge.getWeight())
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
