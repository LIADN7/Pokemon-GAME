package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.*;

class DWGraph_DSTest {
	private directed_weighted_graph dw;
	private Nodes a ,b ,c ,d ,e ,f ,g; 
	private edge_data edge;

	@BeforeEach
	void test() {
		dw = new DWGraph_DS();
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
	 * check function:
	 * node_data getNode(int key)
	 * void addNode(node_data n)
	 */
	@Test
	void test1() {
		a.setWeight(5.2);
		assertEquals(dw.getNode(a.getKey()).getWeight(), 5.2);

		System.out.println("test 1 is completed - good job!");
	}

	/**
	 * Collection<node_data> getV()
	 * node_data removeNode(int key)
	 */
	@Test
	void test2() {
		dw.addNode(e);
		dw.addNode(f);
		dw.addNode(g);
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 8 ; i < 15 ; i++) {
			list.add(i);
		}
		for(node_data n : dw.getV()) {
			assertEquals(n.getKey(), list.poll());
		}
		dw.removeNode(a.getKey());
		for(int i = 9 ; i < 15 ; i++) {
			list.add(i);
		}
		for(node_data n : dw.getV()) {
			assertEquals(n.getKey(), list.poll());
		}
		System.out.println("test 2 is completed - good job!");
	}

	/**
	 * void connect(int src, int dest, double w)
	 * edge_data getEdge(int src, int dest)
	 */
	@Test
	void test3() {
		dw.connect(a.getKey(), b.getKey(), 1);
		dw.connect(a.getKey(), b.getKey(), 35.4);
		dw.connect(c.getKey(), a.getKey(), -1);
		dw.connect(b.getKey(), d.getKey(), 5.2);
		
		edge_data edge = dw.getEdge(a.getKey(), b.getKey());
		assertEquals(edge.getSrc(), a.getKey());
		assertEquals(edge.getDest(), b.getKey());
		assertNotEquals(edge.getWeight(), 1);
		assertEquals(edge.getWeight(), 35.4);
		assertNotEquals(edge.getSrc(), c.getKey());
		if(dw.getEdge(b.getKey(), a.getKey()) != null) {
			fail("FIX LINE 90");
		}
		
		edge = dw.getEdge(c.getKey(), a.getKey());
		if(edge != null) {
			fail("the connection c to a is not ok");
		}

		edge = dw.getEdge(b.getKey(), d.getKey());
		assertNotEquals(edge.getSrc(), d.getKey());
		assertEquals(edge.getDest(), d.getKey());
		System.out.println("test 3 is completed - good job!");
	}
	/**
	 * Collection<edge_data> getE(int node_id)
	 */
	@Test
	void test4() {
		dw.connect(a.getKey(), b.getKey(), 2.3);
		dw.connect(a.getKey(), c.getKey(), 0);
		dw.connect(a.getKey(), d.getKey(), 9.3);
		
		for(edge_data temp : dw.getE(a.getKey())) {
			if(temp.getDest() == b.getKey() && temp.getSrc() == a.getKey()) 
				assertEquals(temp.getWeight(), 2.3);
			else if(temp.getDest() == c.getKey() && temp.getSrc() == a.getKey()) 
				assertEquals(temp.getWeight(), 0);
			else if(temp.getDest() == d.getKey() && temp.getSrc() == a.getKey()) 
				assertEquals(temp.getWeight(), 9.3);
			else
				fail("the connection is not shold be exist");
		}
		
		System.out.println("test 4 is completed - good job!");
	}
	
	/**
	 * node_data removeNode(int key)
	 * edge_data removeEdge(int src, int dest)
	 */
	@Test
	void test5() {
		dw.connect(a.getKey(), b.getKey(), 2.3);
		dw.connect(a.getKey(), c.getKey(), 7.2);
		dw.removeNode(a.getKey());
		edge = dw.getEdge(a.getKey(), b.getKey());
		if(edge != null)
			fail("the connection is notexist");
		edge = dw.getEdge(a.getKey(), c.getKey());
		if(edge != null)
			fail("the connection is notexist");
		
		dw.connect(c.getKey(), b.getKey(), 0.2);
		edge = dw.getEdge(c.getKey(), b.getKey());
		assertEquals(edge.getSrc(), c.getKey());
		dw.removeEdge(c.getKey(), b.getKey());
		edge = dw.getEdge(c.getKey(), b.getKey());
		edge = dw.getEdge(c.getKey(), b.getKey());
		if(edge != null)
			fail("the connection is notexist");
		
		System.out.println("test 5 is completed - good job!");
	}
	
	/**
	 * 	int nodeSize()
	 * int edgeSize()
	 * int getMC()
	 */
	@Test
	void test6() {
		dw.connect(a.getKey(), b.getKey(), 1);
		dw.connect(b.getKey(), c.getKey(), 1);
		dw.connect(c.getKey(), b.getKey(), 1);
		dw.connect(d.getKey(), b.getKey(), 1);
		dw.connect(c.getKey(), d.getKey(), 1);
		dw.connect(d.getKey(), c.getKey(), 1);
		dw.connect(b.getKey(), d.getKey(), 1);
		
		assertEquals(dw.nodeSize(), 4);
		assertEquals(dw.edgeSize(), 7);
		assertEquals(dw.getMC(), 11);

		dw.removeEdge(b.getKey(), c.getKey());
		
		assertEquals(dw.edgeSize(), 6);
		assertEquals(dw.getMC(), 12);
		
		dw.removeNode(b.getKey());
		
		assertEquals(dw.nodeSize(), 3);
		assertEquals(dw.edgeSize(), 2);
		assertEquals(dw.getMC(), 13);
		
		System.out.println("test 6 is completed - good job!");
	}

}
