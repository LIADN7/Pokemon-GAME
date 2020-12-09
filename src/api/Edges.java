package api;

public class Edges implements edge_data {
	private int src , dest , tag;
	private double weight;
	private String info;
	
	
	public Edges(int src, int dest , double weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
		this.info = "";
		this.tag = 0;
	}
	
	public Edges(edge_data e) {
		this.src = e.getSrc();
		this.dest = e.getDest();
		this.weight = e.getWeight();
		this.info = e.getInfo();
		this.tag = e.getTag();
	}

	@Override
	public int getSrc() {
		// TODO Auto-generated method stub
		return src;
	}

	@Override
	public int getDest() {
		// TODO Auto-generated method stub
		return dest;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return info;
	}

	@Override
	public void setInfo(String s) {
		// TODO Auto-generated method stub
		this.info = s;
	}

	@Override
	public int getTag() {
		// TODO Auto-generated method stub
		return tag;
	}

	@Override
	public void setTag(int t) {
		// TODO Auto-generated method stub
		this.tag = t;
	}

}
