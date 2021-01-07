Pokemon game
-----------------

api - done - Point. GeoLocation. Edges. Connection Nodes. DWGraph_DS. DWGraph_Algo.

gameClient - done - 
			 need - Ex2. myGame. guiPanel. guiFrame.

the class Point presents a point
the point has :
* 		key - only can get the key - public.
* 		weight - can get and set the weight - public.
* 		prev - remember the shortest path from some another node in the graph - can get and set the prev - public.

the class GeoLocation present location of the node in  three dimensions. 
type - geo_location
the GeoLocation has:
* 	x, y, z - all double and defined only in the constructor.
*	x, y, z - can get - public.
*	the option to read from file x,y,z. 
*	the option to copy the information from Point3D.
* 	the get the distance from another geo_location.

the class Edges present the information of a edge_data type
the Edges has:
*	has start node - keep his key - can get the key - public.
*	has end node - keep his key - can get the key - public.
*	has weight - keep the distance between them - can get the weight - public.
*	has info - can get and set the info - public.
*	has tag - can get and set the tag  - public.

the class Connection has default constructors - only get key.
this class is part of the DWGraph_DS and present all the ribs that go to and go from the rib on the graph.
use in hasmap method - 
*	forward - save the edge that this key is the source node.
*	backwards - save the numbers (keys) this key is the destination node.
in all the function i use in hashmap function.
the Edges has: (all public)
*	addTo(int num , double w) - check if forward contains the given num. if true -> check if w=(to the weight between this key to num) if false -> set edge weight to w.
                                                                         if false -> create edge between this key to num with weight - w and add it to forward and put the edge in forward by atach it to num.
*	addFrom(int ni) - check if backwards contains the given ni. if false -> add to ni to backwards
*	getEdge(int dest) - search the edge in forward if forward contains edge that atach to the number -> return the edge.
*	removeNodeF(int node) - delete the edge atach to the given number
*	removeNodeB(int node) - delete the number atach to the given number
*	key - can get the key - public 

the class Nodes has 3 constructors and present the information of a node_data type
	1) new Nodes. 
	2) deep copy - copy all the data from another node_data.
	3) get key and geo_location.
the Nodes has: (all public)
*	key - int - can get.
*	tag - int - can get and set.
*	weight - double - can get and set.
*	info - String - can get and set.
*	local - geo_location - can get and set.


the class WGraph_DS has one constructor and present the information of a directed_weighted_graph type
	1) new Graph_DS.
the WGraph_DS has:
*	edgeSize - int - can get.
*	nodeSize - int - can get.
*	change - int - can get.
*	nodes - hashmap<integer,node_data> - save all the node inside the graph - can get as a collection by use the function getV().
*	ribs - hashmap<integer,connection> - save all the rib that in the graph - can get all the edge_data as a collection by use the function getE().
also has the option to ask if two nodes are connected and get the wieght of the edge between them. connecet between two nodes with direction or disconnect,
get all the information about node in the graph ,to add and delete nodes ,and has the information 
about the edge ,nodes sizes, and how much changes was made in the graph.
1. first check that the node who has the key - node_id - is different from null. if true than go over the collection 
of the node and create a list that get the info of the node from the graph and the key and the tag (present the weight) from the getNi function.
return the nodes that has connect to this current node.
the info we get from the graph is to see if it not has been changed.

the class WGraph_Algo  has object type graph and can return the question what the weight between two nodes if there is
any, what the path between them and if there is a valid path between all the nodes.
also can save the information about the current WGraph_DS to file and get infomation about WGraph_DS and build new one.  

explain about bfs -
the function add the key of the node that has been seen to the list and craete Point with the weight from node(src) untils the current node. the wight represent the sum of all the wieght of the nodes go through between node(src) to this current node. after create the Point add it to the hashmap - map.
this functionrun in O(n) at the worst case - all the node can see only one time because the map save their key.
in the best case O(1) - if the given number are equals then return 0 or only node(src) = node(dest). if the node(src) or the node(dest) not in the graph return null or -1.

1. isConnected - check if there is a valid path from one node to the rest of the nodes in this graph. in this function i use at the hashmap called "in" that remember all the node that has been seen. at the end check if the hashmap size is equals to the number of the node.
first if there is only one node in the graph return true beause no need for any connection.
build new linkedlist called "moves" that go over all the node that connected. the first node - call it temporary - FIRST (no matter who)and add it to "moves". than go over with a loop on all his nieghbor, if the sum of the neighbor is '0' return false. else if "in" does not contains the key of the neighbor add it to "moves" and "in". than poll the first one in the line (type queue) and do the same thing. after "moves" see all the nodes that has valid path to FIRST than get out of the loop. 
if "in" size equal to the number of the node in the graph return true else return false.
if true than it mean each node connect to another node.

2. shortestPathDist - get two numbers , first check if there is a nodes with the key src and dest in the graph. if there is than send to the function "bfs" that return a hashmap of Point. check if the hashmap contains a key that equal to the dest key. if true than return the weight of the weight this key is connect to. 
if the hashmap not cotains than it mean there is no such path from node(src) to node(dest) 	

3. shortestPath -  get two numbers , first check if there isa nodes with the key src and dest in the graph. if there is than send to the function "bfs" that return a hashmap of Point. check if the hashmap contains a key that equal to the dest key. if true than return the weight of the weight this key is connect to. 
if the hashmap not cotains than it mean there is no such path from node(src) to node(dest) 
add new HashMap - "map" = function bfs.
add new linkedlist - "qtemp". (contain nodes). 
first add the node(dest)go with a loop -  to "qtemp" and then get the key of the prev of the Point create get the information about the node that atach to to the key, in the graph, and add it to "qtemp". return qtemp.

4. save - take the information from the file and make them by jason in the graph 

5. load - take the information from the file and make them by jason in the graph



**[@authors liadn7](https://github.com/liadn7)**

**[@authors avielc11](https://github.com/avielc11)**
