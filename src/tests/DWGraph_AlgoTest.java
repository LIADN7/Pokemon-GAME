package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import api.*;

class DWGraph_AlgoTest {
	private directed_weighted_graph dw;
	private dw_graph_algorithms algo;
	private Nodes a ,b ,c ,d ,e ,f ,g; 
	private edge_data edge;

	@BeforeEach
	void test() {
		dw = new DWGraph_DS();
		algo = new DWGraph_Algo();
		a = new Nodes();
		b = new Nodes();
		c = new Nodes();
		d = new Nodes();
		e = new Nodes();
		f = new Nodes();
		g = new Nodes();
		dw.addNode(a);
		dw.addNode(b);
		dw.addNode(c);
		dw.addNode(d);
	}
	
    /**
     * void init(directed_weighted_graph g)
     * directed_weighted_graph getGraph()
     */
	@Test
	void test1() {
		algo.init(dw);
		dw.connect(a.getKey(), d.getKey(), 0.21122);
		edge = algo.getGraph().getEdge(a.getKey(), d.getKey());
		assertEquals(edge.getWeight(), 0.21122);
		System.out.println("test 1 is completed - good job!");
	}
    
    
	/**
	 * directed_weighted_graph copy()
	 */
	@Test
	void test2() {
		algo.init(dw);
		directed_weighted_graph temp = algo.copy();
		assertEquals(temp, algo.getGraph());
		System.out.println("test 2 is completed - good job!");
	}
	
	/**
	 * boolean isConnected()
	 */
	@Test
	void test3() {
		dw.connect(a.getKey(), b.getKey(), 1);
		dw.connect(b.getKey(), c.getKey(), 1);
		dw.connect(c.getKey(), b.getKey(), 1);
		dw.connect(d.getKey(), b.getKey(), 1);
		dw.connect(c.getKey(), d.getKey(), 1);
		dw.connect(d.getKey(), c.getKey(), 1);
		dw.connect(b.getKey(), d.getKey(), 1);
		
		algo.init(dw);
		assertFalse(algo.isConnected());
		
		dw.connect(b.getKey(), a.getKey(), 1);
		
		assertTrue(algo.isConnected());
		System.out.println("test 3 is completed - good job!");
	}
	
	/**
	 * double shortestPathDist(int src, int dest)
	 * List<node_data> shortestPath(int src, int dest)
	 */
	@Test
	void test4() {
		dw.addNode(e);
		dw.addNode(f);
		dw.addNode(g);
		node_data h = new Nodes();
		dw.addNode(h);
		
		dw.connect(a.getKey(), b.getKey(), 1);
		dw.connect(b.getKey(), c.getKey(), 1);
		dw.connect(c.getKey(), d.getKey(), 1);
		dw.connect(d.getKey(), e.getKey(), 1);
		dw.connect(e.getKey(), f.getKey(), 1);
		dw.connect(f.getKey(), g.getKey(), 1);
		dw.connect(g.getKey(), h.getKey(), 1);
		
		algo.init(dw);
		double tr = algo.shortestPathDist(h.getKey(), a.getKey());
		assertEquals(-1, tr);
		double num = algo.shortestPathDist(a.getKey(), h.getKey());
		assertEquals(7, num);
		//need to be (22) -->> 25 -->> 26 -->> 28 -->> 29
		List<node_data> list = algo.shortestPath(a.getKey(), h.getKey());
		if(list != null) {
			for(int i = 0 ; i < list.size() ; i++)
				System.out.print(" -->> " + list.get(i).getKey());
		}
		System.out.println();
		
		System.out.println("test 4 is completed - good job!");
	}
	
	/**
	 * boolean save(String file)
	 */
	@Test
	void test5() {
		dw.addNode(e);
		dw.addNode(f);
		
		dw.connect(a.getKey(), b.getKey(), 1);
		dw.connect(a.getKey(), d.getKey(), 1);
		dw.connect(b.getKey(), c.getKey(), 1);
		dw.connect(c.getKey(), d.getKey(), 1);
		dw.connect(d.getKey(), e.getKey(), 1);
		dw.connect(e.getKey(), f.getKey(), 1);
		dw.connect(f.getKey(), g.getKey(), 1);
		dw.connect(e.getKey(), g.getKey(), 1);

		
		algo.init(dw);
		algo.save("test5.txt");
		System.out.println("test 5 is completed - good job!");
	}
	

//	/**
//	 * boolean load(String file)
//	 */
//	@Test
//	void test8() {
//		algo.init(dw);
//		algo.load("read.txt");
//		System.out.println("test 8 is completed - good job!");
//	}
	
	/**
	 * algo.connected_components()
	 */
	@Test
	void test6() {
		DWGraph_Algo algo = new DWGraph_Algo();
		DWGraph_DS g = new DWGraph_DS();
		for(int i=0;i<9;i++) {
			Nodes temp = new Nodes() ;
			g.addNode(temp);

		}
		for(int i=0;i<9;i++)
			for(int j=0;j<3;j++)
				g.connect(1000051+i,1000051+i+j,1);
		
		for(int i=0;i<7;i++)
			for(int j=1;j<3;j++) {
				g.connect(1000051+i+j,1000051+i,1);
				
			}	
		
		
		algo.init(g);
		System.out.println("test 6 is completed - good job!");
		
	}
	
	/**
	 * Builds a graph with a million vertices
	 *  and shows what the construction time is.
	 */
	@Test
	void GraphMillion() {
		long start = new Date().getTime();
		DWGraph_DS g = new DWGraph_DS();
		for(int i=0;i<10000;i++) {
			Nodes temp = new Nodes() ;
			g.addNode(temp);	
		}
		for(int i=0;i<10000;i++)
			for(int j=0;j<3;j++)
				g.connect(i,i+j,1);
		long end = new Date().getTime();
		System.out.println("Graph construction 10,000 nodes run time: "+((double)(end-start)/1000)+" secends");
		
		for(int i=10000;i<100000;i++) {
			Nodes temp = new Nodes() ;
			g.addNode(temp);			
		}
		for(int i=10000;i<100000;i++)
			for(int j=0;j<3;j++)
				g.connect(i,i+j,1);
		end = new Date().getTime();
		System.out.println("Graph construction 100,000 nodes run time: "+((double)(end-start)/1000)+" secends");

		for(int i=100000;i<1000000;i++) {
			Nodes temp = new Nodes() ;
			g.addNode(temp);			
		}
		for(int i=100000;i<1000000;i++)
			for(int j=0;j<3;j++)
				g.connect(i,i+j,1);
		end = new Date().getTime();
		System.out.println("Graph construction 1,000,000 nodes run time: "+((double)(end-start)/1000)+" secends");
		
	}

}
