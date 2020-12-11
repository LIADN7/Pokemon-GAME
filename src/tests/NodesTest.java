package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.*;

class NodesTest {
	private Nodes a, b, c; 
	
	@BeforeEach
	void test() {
		a = new Nodes();
		b = new Nodes();
		c = new Nodes();
	}

	/**
	 * function int getKey()
	 */
	@Test
	void test1() {
		assertEquals(a.getKey(), 1);
		assertEquals(b.getKey(), 2);
		assertEquals(c.getKey(), 3);
		System.out.println("test 1 is OK!");
	}
	
	/**
	 * function 
	 * geo_location getLocation();
	 * void setLocation(geo_location p); 
	 */
	@Test
	void test2() {
		geo_location p = new GeoLocation(1.2, 2.1, 3.2);
		a.setLocation(p);
		assertEquals(a.getLocation().x(), 1.2);
		assertEquals(a.getLocation().y(), 2.1);
		assertEquals(a.getLocation().z(), 3.2);
		System.out.println("test 2 is OK!");
	}
	
	/**
	 * function  
	 * double getWeight();
	 * void setWeight(double w);
	 */
	@Test
	void test3() {
		a.setWeight(5.2);
		assertEquals(a.getWeight(), 5.2);
		System.out.println("test 3 is OK!");
	}
	
	/**
	 * function  
	 * String getInfo();
	 * void setInfo(String s);
	 */
	@Test
	void test4() {
		a.setInfo("black");
		assertEquals(a.getInfo(), "black");
		System.out.println("test 4 is OK!");
	}
	
	/**
	 * function 
	 * int getTag();
	 * void setTag(int t); 
	 */
	@Test
	void test5() {
		a.setTag(2);
		assertEquals(a.getTag(), 2);
		System.out.println("test 5 is OK!");
	}
}
