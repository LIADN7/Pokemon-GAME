package api;

/**
 * This class represents a position on the graph (a relative position
 * on an edge - between two consecutive nodes).
 */
public interface edge_location {
    /**
     * @return the edge on which the location is.
     */
    public edge_data getEdge();
    /**
     * @return the relative ration [0,1] of the location between src and dest.
     */
    public double getRatio();
}