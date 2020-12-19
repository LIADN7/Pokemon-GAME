package api;

/**
 * this class present the information of node_data type. 
 * the node has unique key, info - String kind, tag , weight and location3D.
 * this node save the nodes that has connect and their tag save the weight.
 * the neighbor of the node points on another place in the memory from the ones in the graph. 
 * @author liadn7
 * @author aviel11
 *
 */
public class Nodes implements node_data {
	private static int defaulttkey = 0;
	private int key ;
	private int tag;
	private double  NW;
	private String info;
	private geo_location local;

	/**
	 * constructor 1 update the default key plus 1
	 * this.key equal to defualtkey the weight of this node equal to -1
	 *
	 */
	public Nodes() {
		defaulttkey++;
		this.key = defaulttkey;
		this.info = null;
		this.tag = 0;
		this.NW = -1;
		this.local = new GeoLocation();
	}	
	
	/**
	 * copy constructor
	 * @param node - type node_data
	 */
	public Nodes(node_data node) {
		this.key = node.getKey();
		this.info = node.getInfo();
		this.tag = node.getTag();
		this.NW = node.getWeight();
		this.local = new GeoLocation(node.getLocation());
	}
	
	/**
	 * constructor that get the key of another node_data and set the location 
	 * @param id - the key of another node_data
	 * @param e
	 */
	public Nodes(int id, geo_location e) {
		if(id > defaulttkey)
			defaulttkey = id + 1;
		this.key = id;
		this.info = null;
		this.tag = 0;
		this.NW = 0;
		this.local = new GeoLocation(e);
	}
		
	@Override
	public int getKey() {
		return key;
	}

	@Override
	public geo_location getLocation() {
		return local;
	}

	@Override
	public void setLocation(geo_location p) {
		this.local = p;
	}

	@Override
	public double getWeight() {
		return NW;
	}

	@Override
	public void setWeight(double w) {
		this.NW = w;
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
