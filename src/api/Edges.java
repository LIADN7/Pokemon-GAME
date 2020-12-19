package api;

/**
 * this class present the information of the edge_data type
 * has the information of the source and the destination nodes and the weight of the edge
 * @author liadn7
 * @author avielc11
 *
 */
public class Edges implements edge_data {
	private int src , dest , tag;
	private double weight;
	private String info;
	
	/**
	 * default constructor get the source, the destination and the weight
	 * @param src - int
	 * @param dest - int
	 * @param weight - double
	 */
	public Edges(int src, int dest , double weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
		this.info = "";
		this.tag = 0;
	}
	
	/**
	 * copy constructor
	 * @param e - edge_data type
	 */
	public Edges(edge_data e) {
		this.src = e.getSrc();
		this.dest = e.getDest();
		this.weight = e.getWeight();
		this.info = e.getInfo();
		this.tag = e.getTag();
	}

	@Override
	public int getSrc() {
		return src;
	}

	@Override
	public int getDest() {
		return dest;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
	}
}
