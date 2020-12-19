package api;

import java.util.Collection;
/**
 * This interface represents a directional_weighted_graph.
 * The interface has a road-system or communication network in mind - 
 * and should support a large number of nodes (over 100,000).
 * The implementation should be based on an efficient compact representation 
 * (should NOT be based on a n*n matrix).
 *
 */

public interface directed_weighted_graph {
	/**
	 * returns the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	public node_data getNode(int key);
	/**
	 * Note: this method should run in O(1) time.
	 * @param src - the node that start of the edge
	 * @param dest - the node that destinatin of the edge
	 * @return the data of the edge (src,dest), null if none.
	 */
	public edge_data getEdge(int src, int dest);
	/**
	 * adds a new node to the graph with the given node_data.
	 * Note: this method should run in O(1) time.
	 * @param n - the node_data need to add.
	 */
	public void addNode(node_data n);
	/**
	 * Connects an edge with weight w between node src to node dest.
	 * * Note: this method should run in O(1) time.
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src to dest.
	 */
	public void connect(int src, int dest, double w);
	/**
	 * This method returns a pointer (shallow copy) for the
	 * collection representing all the nodes in the graph. 
	 * Note: this method should run in O(1) time.
	 * @return the Collection of all the node_data
	 */
	public Collection<node_data> getV();

	/**
	 * collection representing all the edges getting out of 
	 * the given node (all the edges starting (source) at the given node). 
	 * Note: this method should run in O(k) time, k being the collection size.
	 * @param node_id - the number of the key of the node
	 * @return the Collection of all the edge_data
	 */
	public Collection<edge_data> getE(int node_id);
	/**
	 * Deletes the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(k), V.degree=k, as all the edges should be removed.
	 * @param key - the number of the node.
	 * @return the data of the removed node (null if none). 
	 */
	public node_data removeNode(int key);
	/**
	 * Deletes the edge from the graph,
	 * Note: this method should run in O(1) time.
	 * @param src - the start node of the edge
	 * @param dest - the destination node of the edge
	 * @return the data of the removed edge (null if none).
	 */
	public edge_data removeEdge(int src, int dest);
	/** 
	 * Note: this method should run in O(1) time. 
	 * @return the number of vertices (nodes) in the graph.
	 */
	public int nodeSize();
	/** 
	 * Note: this method should run in O(1) time.
	 * @return the number of edges (assume directional graph).
	 */
	public int edgeSize();
	/**
	 * @return the Mode Count - for testing changes in the graph.
	 */
	public int getMC();
}