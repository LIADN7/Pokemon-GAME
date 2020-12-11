package api;

public class Nodes implements node_data {
	private static int defualtkey = 0;
	private int key ;
	private int tag;
	private double  NW;
	private String info;
	private geo_location local;

	//constructor.1
	public Nodes() {
		defualtkey++;
		this.key = defualtkey;
		this.info = null;
		this.tag = 0;
		this.NW = -1;
		this.local = new GeoLocation();
	}	
	
	public Nodes(node_data node) {
		this.key = node.getKey();
		this.info = node.getInfo();
		this.tag = node.getTag();
		this.NW = node.getWeight();
		this.local = new GeoLocation(node.getLocation());
	}
	
	public Nodes(int id, geo_location e) {
		if(id>defualtkey)
			defualtkey = id+1;
		this.key = id;
		this.info = null;
		this.tag = 0;
		this.NW = 0;
		this.local = new GeoLocation(e);;
	}
		
	@Override
	public int getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public geo_location getLocation() {
		// TODO Auto-generated method stub
		return local;
	}

	@Override
	public void setLocation(geo_location p) {
		// TODO Auto-generated method stub
		this.local = p;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return NW;
	}

	@Override
	public void setWeight(double w) {
		// TODO Auto-generated method stub
		this.NW = w;
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
