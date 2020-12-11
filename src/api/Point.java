package api;

public class Point {
	private int key, prev;
	private double weight;
	
	public Point(int key) {
		this.key = key;
		this.weight = -1;
		this.prev = -1;
	}
	
	public Point(int key, double w, int prev) {
		this.key = key;
		this.weight = w;
		this.prev = prev;
	}
	
	public int getKey() {
		return key;
	}

	public int getPrev() {
		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
