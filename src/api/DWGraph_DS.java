package api;

import java.util.*;

public class DWGraph_DS implements directed_weighted_graph {
	private int edgeSize , nodeSize, change;
	private HashMap<Integer,Nodes> nodes;
	
	public DWGraph_DS() {
		edgeSize = 0;
		nodeSize = 0;
		change = 0;
		nodes = new HashMap<Integer,Nodes>();
	}
	
	public HashMap<Integer,Nodes> getHash() {return this.nodes;}
	
	
	
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
		Nodes temp = (Nodes) getNode(src);
		return temp.getEdge(dest);
	}

	@Override
	public void addNode(node_data n) {
		// TODO Auto-generated method stub
		nodes.put(n.getKey(),(Nodes) n);
		nodeSize++;
		change++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub
		if(w >= 0) {
			if(getNode(src) != null && getNode(dest) != null) {
				Nodes t1 = (Nodes) getNode(src);
				Nodes t2 = (Nodes) getNode(dest);
				t1.addTo(dest, w);
				t2.addFrom(t1);
				edgeSize++;
				change++;
			}
		}
	}

	@Override
	public Collection<node_data> getV() {
		Iterator<Nodes> moves = this.nodes.values().iterator();
		Collection<node_data> t = new LinkedList<node_data>();
		while(moves.hasNext()) {
			t.add(moves.next());
		}
		return t;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		Nodes temp = (Nodes) getNode(node_id);
		Iterator<Edges> moves = temp.getF().iterator();
		Collection<edge_data> t = new LinkedList<edge_data>();
		while(moves.hasNext()) {
			t.add(moves.next());
		}
		return t;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		if(getNode(key) != null) {
			Nodes temp = (Nodes) getNode(key);
			for( int n : temp.getB()) {
				Nodes curr = (Nodes) getNode(n);
				curr.removeNodeF(temp.getKey());
				curr.removeNodeB(temp.getKey());
				edgeSize--;
			}
			nodes.remove(temp.getKey());
			change++;
			return temp;
		}
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		if(getNode(src) != null && getNode(dest) != null) {
			Nodes t1 = (Nodes) getNode(src);
			Nodes t2 = (Nodes) getNode(dest);
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
}
