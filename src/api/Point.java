package api;

/**
 * this class present a Point.
 * 
 * has the option to know :
 * 		the weight of this point.
 * 		the the key.
 * 		prev - remember the shortest path from some another node in the graph.		
 * @author liadn7
 * @author avielc11
 */
public class Point {
	private int key, prev;
	private double weight;

	/**
	 * constructor 1 initial the point
	 * Default weight and prev equal to -1
	 * @param key - this.key will be equal to key.
	 */
	public Point(int key) {
		this.key = key;
		this.weight = -1;
		this.prev = -1;
	}
	
	/**
	 * constructor 2 initial the point
	 * @param key - this.key will be equal to key.
	 * @param w - this.weight will be equal to w.
	 * @param prev - this.prev will be equal to prev.
	 */
	public Point(int key, double w, int prev) {
		this.key = key;
		this.weight = w;
		this.prev = prev;
	}

	/**
	 * @return int - the key of this point
	 */
	public int getKey() {
		return key;
	}

	/**
	 * @return int - the previous of this point
	 */
	public int getPrev() {
		return prev;
	}

	/**
	 * @param prev - this.prev will be equal to prev.
	 */
	public void setPrev(int prev) {
		this.prev = prev;
	}

	/**
	 * @return double - the weight of this point
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight - this.weight will be equal to w.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
}
