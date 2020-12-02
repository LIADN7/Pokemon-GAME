package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DWGraph_Algo implements dw_graph_algorithms{
	private directed_weighted_graph myGraph;
	
	public DWGraph_Algo() {
		this.myGraph = new DWGraph_DS();
	}
	
	
	@Override
	public void init(directed_weighted_graph g) {
		// TODO Auto-generated method stub
		myGraph = g;
		
	}

	@Override
	public directed_weighted_graph getGraph() {
		// TODO Auto-generated method stub
		return myGraph;
	}

	@Override
	public directed_weighted_graph copy() {

			Gson gsonF = new GsonBuilder().create();
			Gson gsonW = new Gson();
			DWGraph_DS a=gsonW.fromJson(gsonF.toJson(this.myGraph), DWGraph_DS.class);
			return a;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		if(myGraph.nodeSize() <= 1) {
			return true;
		}

		LinkedList<Nodes> moves = new LinkedList<Nodes>();
		HashMap<Integer,String> in = new HashMap<Integer,String>();
		int num = myGraph.getV().iterator().next().getKey();

		moves.add((Nodes) myGraph.getNode(num));
		in.put(num, "v");
		
		while(!moves.isEmpty()) {
			Nodes temp = moves.poll();
			if(temp.getSizeOf() == 0) {
				return false;
			}
			for(edge_data n : temp.getF()) {
				num = n.getDest();
				if(!in.containsKey(num)) {
					moves.add((Nodes) myGraph.getNode(num));
					in.put(num, "v");
				} 
			}
		}
		if(myGraph.nodeSize() == in.size()) {
			return true;
		}
		return false;
	}
	
	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		if(myGraph.getNode(src) == null || myGraph.getNode(dest) == null) {
			return -1;
		}
		if(src == dest) return 0;
		LinkedList<Nodes> q = bfs(src,dest);
		if(q.isEmpty()) {
			return -1;
		}
		double count = myGraph.getNode(dest).getWeight();
		while(!q.isEmpty()) {
			Nodes n = q.poll();
			n.setWeight(0);;
			n.setPrev(0);
		}
		return count-1;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		node_data t1 = myGraph.getNode(src);
		LinkedList<node_data> list = new LinkedList<node_data>();
		if(t1 == null || myGraph.getNode(dest) == null) {
			return list;
		}
		if(src == dest) {
			list.add(t1);
			return list;
		}
		LinkedList<Nodes> q = bfs(src,dest);
		if(q.isEmpty()) {
			return list;
		}
		Nodes t2 = (Nodes) myGraph.getNode(dest);
		node_data t3 = t2;
		list.add(t3);
		while(t2.getKey() != src) {
			t3 = myGraph.getNode(t2.getPrev());
			list.addFirst(t3);
			t2.setPrev(0);
			t2.setWeight(0);
			t2 = (Nodes) myGraph.getNode(t3.getKey());;

		}
		t2.setPrev(0);
		t2.setWeight(0);;
		return list;
	}

	@Override
	public boolean save(String file) {
		try {
			StringBuilder sb = new StringBuilder();
			PrintWriter pw=new PrintWriter(new File(file));
			Gson gson=new GsonBuilder().create();
			sb.append(gson.toJson(this.myGraph));
			pw.write(sb.toString());
			pw.close();
			return true;
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean load(String file) {

		try {
			FileReader read = new FileReader(file);	
			Gson gson=new Gson();
			DWGraph_DS a=gson.fromJson(read, DWGraph_DS.class);
			this.myGraph=a;
			return true;
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @param start node.
     * @param dest - end (target) node.
	 * @returns the weight of the shortest path between src to dest.
	 * if no such path --> returns -1
	 */
	private LinkedList<Nodes> bfs(int src , int dest){
		LinkedList<Nodes> q = new LinkedList<Nodes>();
		LinkedList<Nodes> qtemp = new LinkedList<Nodes>();
		Nodes curr = (Nodes) myGraph.getNode(src);
		q.add(curr);
		curr.setWeight(1);;
		qtemp.add(curr);
		double count = 0;
		while(!qtemp.isEmpty()) {
			boolean bool = false;
			curr = qtemp.poll();
			double weight = curr.getWeight();
			for(edge_data temp :curr.getF()) {
				int TempN = temp.getDest();
				Nodes n = (Nodes) myGraph.getNode(TempN);
				count = temp.getWeight() + weight;
				if(TempN == dest && n.getWeight() > count) {
					n.setWeight(count);
					n.setPrev(curr.getKey());
					q.add(n);
					bool = true;
				}
				else if((n.getWeight() > count  || n.getWeight() == 0) && !bool) {
					n.setWeight(count);
					n.setPrev(curr.getKey());
					qtemp.add(n);
					q.add(n);
				}
			}
		}
		if(myGraph.getNode(dest).getWeight() != 0) {
			return q;
		}
		while(!(q.isEmpty())) {
			curr = q.poll();
			curr.setWeight(0);;
			curr.setPrev(0);
		}
		return q;
	}

}
