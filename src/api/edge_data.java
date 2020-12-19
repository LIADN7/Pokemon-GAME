package api;
/**
 * This interface represents the set of operations applicable on a 
 * directional edge_data in a (directional) weighted graph.
 * @author boaz.benmoshe
 *
 */
public interface edge_data {
	/**
	 * @return The id of the source node of this edge.
	 */
	public int getSrc();
	/**
	 * @return The id of the destination node of this edge
	 */
	public int getDest();
	/**
	 * @return the weight of this edge (positive value).
	 */
	public double getWeight();
	/**
	 * @return the remark (meta data) associated with this edge.
	 */
	public String getInfo();
	/**
	 * Allows changing the remark (meta data) associated with this edge.
	 * @param s - the remark
	 */
	public void setInfo(String s);
	/**
	 * @return Temporal data (aka color: e,g, white, gray, black) 
	 * which can be used be algorithms 
	 */
	public int getTag();
	/** 
	 * This method allows setting the "tag" value for temporal marking an edge - common
	 * practice for marking by algorithms.
	 * @param t - the new value of the tag
	 */
	public void setTag(int t);
}