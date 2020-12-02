package api;

public class tester {

	public static void main(String[] args) {
		Nodes a1 = new Nodes();
		Nodes a2 = new Nodes();
		Nodes a3 = new Nodes();		

		Nodes a4 = new Nodes();
		Nodes a5 = new Nodes();
		Nodes a6 = new Nodes();
		
		directed_weighted_graph w = new DWGraph_DS();
		
		w.addNode(a1);
		w.addNode(a2);
		w.addNode(a3);
		w.addNode(a4);
		w.addNode(a5);
		w.addNode(a6);
		
		
		w.connect(1, 2, 1);
		w.connect(1, 3, 1);
		w.connect(2, 4, 1);
		w.connect(2, 5, 1);
		w.connect(3, 6, 1);
		w.connect(4, 2, 1);
		w.connect(5, 3, 1);
		w.connect(6, 3, 1);
		w.connect(6, 5, 1);
		
		dw_graph_algorithms t = new DWGraph_Algo();
		t.init(w);
		t.save("temp1.txt");	

		
		dw_graph_algorithms ff = new DWGraph_Algo();
		ff.init(t.copy());
		
		ff.save("temp2.txt");
//		directed_weighted_graph fff = ff.getGraph();
//		System.out.println(ff.save("temp1.txt"));
		
//		System.out.println(fff.edgeSize());
//		System.out.println(w.edgeSize());	
//		System.out.println(fff.nodeSize());
//		System.out.println(w.nodeSize());
//		System.out.println(fff.getMC());
//		System.out.println(w.getMC());

	}

}
