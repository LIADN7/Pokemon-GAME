package api;

import java.util.*;

public class DWGraph_DS implements directed_weighted_graph {
	private int edgeSize , nodeSize, change;
	private HashMap<Integer,node_data> nodes;
	private HashMap<Integer,Connection> ribs;
	
	public DWGraph_DS() {
		edgeSize = 0;
		nodeSize = 0;
		change = 0;
		nodes = new HashMap<Integer,node_data>();
		ribs = new HashMap<Integer,Connection>();
	}
	
	@Override
	public node_data getNode(int key) {
		// TODO Auto-generated method stub
		if(nodes.containsKey(key)) {
			return nodes.get(key);
		}
		return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		// TODO Auto-generated method stub
		if(nodes.containsKey(src)) {
			Connection temp = ribs.get(src);
			edge_data e = temp.getEdge(dest);
			return e;
		}
		return null;
	}

	@Override
	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		Connection temp = new Connection(n.getKey());
		nodes.put(n.getKey(),(Nodes) n);
		ribs.put(temp.getKey(), temp);
		nodeSize++;
		change++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		if(w >= 0) {
			if(getNode(src) != null && getNode(dest) != null) {
				Connection t1 = ribs.get(src);
				Connection t2 = ribs.get(dest);
				//Nodes t2 = (Nodes) getNode(dest);
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
		// TODO Auto-generated method stub
		return ribs.get(node_id).getF();
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		if(getNode(key) != null) {
			node_data temp = getNode(key);
			for(int num : ribs.get(key).getB()) {
				Connection n = ribs.get(num);
				n.removeNodeF(key);
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
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return nodeSize;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return edgeSize;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return change;
	}
	
	@Override
	public boolean equals(Object b) {
		// TODO Auto-generated method stub
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
