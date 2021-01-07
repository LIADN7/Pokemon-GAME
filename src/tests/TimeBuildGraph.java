package tests;

import java.util.Date;

import api.*;

public class TimeBuildGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		G_10_80();
		G_100_800();
		G_1000_8000();
		G_10000_80000();
		G_20000_160000();
		G_30000_240000();
		System.out.println("done");
		
	}
	/**
	 * 1
	 */
	public static void G_10_80() {
		DWGraph_Algo algo = new DWGraph_Algo();
		long start = new Date().getTime();
		algo.setGraph("./data/G_10_80_0.json");
		long end = new Date().getTime();
		System.out.println("successfully from \"G_10_80_0.json\" run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.shortestPathDist(0, 4);
		end = new Date().getTime();
		System.out.println("shortest path - 0 -->> 4 run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_component(0);
		end = new Date().getTime();
		System.out.println("connected_component -->> (0) run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_components();
		end = new Date().getTime();
		System.out.println("connected_components run time: "+((double)(end-start)/1000)+" sec");
		
	}
	
	/**
	 * 2
	 */
	public static void G_100_800() {
		DWGraph_Algo algo = new DWGraph_Algo();
		long start = new Date().getTime();
		algo.setGraph("./data/G_100_800_0.json");
		long end = new Date().getTime();
		System.out.println("successfully from \"G_100_800_0.json\" run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.shortestPathDist(48, 4);
		end = new Date().getTime();
		System.out.println("shortest path - 48 -->> 4 run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_component(0);
		end = new Date().getTime();
		System.out.println("connected_component -->> (0) run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_components();
		end = new Date().getTime();
		System.out.println("connected_components run time: "+((double)(end-start)/1000)+" sec");
		
	}
	
	/**
	 * 3
	 */
	public static void G_1000_8000() {
		DWGraph_Algo algo = new DWGraph_Algo();
		long start = new Date().getTime();
		algo.setGraph("./data/G_1000_8000_0.json");
		long end = new Date().getTime();
		System.out.println("successfully from \"G_1000_8000_0.json\" run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.shortestPathDist(400, 9050);
		end = new Date().getTime();
		System.out.println("shortest path - 400 -->> 9050 run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_component(0);
		end = new Date().getTime();
		System.out.println("connected_component -->> (0) run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_components();
		end = new Date().getTime();
		System.out.println("connected_components run time: "+((double)(end-start)/1000)+" sec");
		
	}
	
	/**
	 * 4
	 */
	public static void G_10000_80000() {
		DWGraph_Algo algo = new DWGraph_Algo();
		long start = new Date().getTime();
		algo.setGraph("./data/G_10000_80000_0.json");
		long end = new Date().getTime();
		System.out.println("successfully from \"G_10000_80000_0.json\" run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.shortestPathDist(7005, 70);
		end = new Date().getTime();
		System.out.println("shortest path - 7005 -->> 70 run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_component(0);
		end = new Date().getTime();
		System.out.println("connected_component -->> (0) run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_components();
		end = new Date().getTime();
		System.out.println("connected_components run time: "+((double)(end-start)/1000)+" sec");
		
	}
	
	/**
	 * 5
	 */
	public static void G_20000_160000() {
		DWGraph_Algo algo = new DWGraph_Algo();
		long start = new Date().getTime();
		algo.setGraph("./data/G_20000_160000_0.json");
		long end = new Date().getTime();
		System.out.println("successfully from \"G_20000_160000_0.json\" run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.shortestPathDist(5000, 15250);
		end = new Date().getTime();
		System.out.println("shortest path - 5000 -->> 15250 run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_component(0);
		end = new Date().getTime();
		System.out.println("connected_component -->> (0) run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_components();
		end = new Date().getTime();
		System.out.println("connected_components run time: "+((double)(end-start)/1000)+" sec");
		
	}
	
	/**
	 * 6
	 */
	public static void G_30000_240000() {
		DWGraph_Algo algo = new DWGraph_Algo();
		long start = new Date().getTime();
		algo.setGraph("./data/G_30000_240000_0.json");
		long end = new Date().getTime();
		System.out.println("successfully from \"G_30000_240000_0.json\" run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.shortestPathDist(0, 4);
		end = new Date().getTime();
		System.out.println("shortest path - 0 -->> 4 run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_component(0);
		end = new Date().getTime();
		System.out.println("connected_component -->> (0) run time: "+((double)(end-start)/1000)+" sec");
		start  = new Date().getTime();
		algo.connected_components();
		end = new Date().getTime();
		System.out.println("connected_components run time: "+((double)(end-start)/1000)+" sec");
		
	}
	
}
