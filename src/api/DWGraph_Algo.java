package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
		directed_weighted_graph w1 = new DWGraph_DS();
		for(node_data node : myGraph.getV()) {
			node_data temp = new Nodes(node);
			w1.addNode(temp);
		}
		for(node_data node : myGraph.getV()) {
			for(edge_data edge : myGraph.getE(node.getKey())) {
				w1.connect(node.getKey(), edge.getDest(), edge.getWeight());
			}
		}
		return w1;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		if(myGraph.nodeSize() <= 1) {
			return true;
		}
		LinkedList<Integer> moves = new LinkedList<Integer>();
		HashMap<Integer,String> in = new HashMap<Integer,String>();
		int num = myGraph.getV().iterator().next().getKey();

		moves.add(num);

		while(!moves.isEmpty()) {
			int tempNum = moves.poll();
			if(myGraph.getE(tempNum).size() == 0) {
				return false;
			}
			for(edge_data n : myGraph.getE(tempNum)) {
				num = n.getDest();
				if(!in.containsKey(num)) {
					moves.add(num);
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
		HashMap<Integer,Point> map = bfs(src,dest);
		if(map.containsKey(dest))
			return map.get(dest).getWeight();
		return -1;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		node_data t1 = myGraph.getNode(src);
		LinkedList<node_data> list = new LinkedList<node_data>();
		if(t1 == null || myGraph.getNode(dest) == null) {
			return null;
		}
		if(src == dest) {
			list.add(t1);
			return list;
		}
		HashMap<Integer,Point> map = bfs(src,dest);
		if(map == null) {
			return null;
		}
		Point point = map.get(dest);
		node_data t3 = myGraph.getNode(dest);
		list.add(t3);
		while(point.getPrev() != src) {
			t3 = myGraph.getNode(point.getPrev());
			list.addFirst(t3);
			point = map.get(point.getPrev());

		}
		return list;
	}

	@Override
	public boolean save(String file) {
		try {
			StringBuilder sb = new StringBuilder();
			PrintWriter pw=new PrintWriter(new File(file));
			Gson gson=new GsonBuilder().setPrettyPrinting().create();
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
		//DWGraph_DS a = new DWGraph_DS();
//		try 
//		{
//			GsonBuilder builder = new GsonBuilder();
//			builder.registerTypeAdapter(DWGraph_DS.class, new GraphJsonDeserializer());
//			Gson gson = builder.create();			
//			//continue as usual.. 
//
//			FileReader reader = new FileReader(file);
//			DWGraph_DS a = gson.fromJson(reader, DWGraph_DS.class);	
//			System.out.println(a);
//			return true;
//		} 
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return false;
//		}
		try {
			FileReader read = new FileReader(file);	
			Gson gson=new Gson();
			DWGraph_DS a=gson.fromJson(read, DWGraph_DS.class);
			this.myGraph = a;
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

	private HashMap<Integer,Point> bfs(int src , int dest){
		HashMap<Integer,Point> map = new HashMap<Integer,Point>();
		LinkedList<Integer> qtemp = new LinkedList<Integer>();
		Point curr = new Point(src,0,-1);
		map.put(src, curr);
		qtemp.add(src);
		double count = 0;
		double way = -1;
		while(!qtemp.isEmpty()) {
			boolean bool = false;
			int poll = qtemp.poll();
			double weight = map.get(poll).getWeight();
			for(edge_data temp : myGraph.getE(poll)) {
				int TempN = temp.getDest();
				count = temp.getWeight() + weight;
				if(TempN == dest && (way > count || way == -1)) {
					Point end = new Point(dest);
					if(!map.containsKey(dest)) {
						map.put(dest, end);
					}
					map.get(dest).setWeight(count);
					map.get(dest).setPrev(poll);
					way = count;
					bool = true;
				}
				else if(update(TempN,count,poll,map) && !bool) {
					qtemp.add(TempN);
				}
			}
		}
		if(map.containsKey(dest)) {
			return map;
		}
		return null;
	}

	private boolean update(int node, double weight, int prev, HashMap<Integer,Point> map) {
		if(!map.containsKey(node)) {
			Point temp = new Point(node,weight,prev);
			map.put(node, temp);
			return true;
		}
		else {
			Point temp = map.get(node);
			if(temp.getWeight() > weight){
				temp.setWeight(weight);
				temp.setPrev(prev);
			}
		}
		return false;
	}
	
	
}
