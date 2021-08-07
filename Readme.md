Pokemon game
-----------------

![](https://github.com/LIADN7/Pokemon-GAME/blob/master/img/icon.png)



the class Point presents a point.

the class GeoLocation present location of the node in  three dimensions. 

the class Edges present the information of a edge_data type

the class Connection has default constructors - only get key.
this class is part of the DWGraph_DS and present all the ribs that go to and go from the rib on the graph.

the class Nodes has 3 constructors and present the information of a node_data type



the class WGraph_DS has one constructor and present the information of a directed_weighted_graph type.
the WGraph_DS has the option to ask if two nodes are connected and get the wieght of the edge between them. connecet between two nodes with direction or disconnect,
get all the information about node in the graph ,to add and delete nodes ,and has the information 
about the edge ,nodes sizes, and how much changes was made in the graph.
1. first check that the node who has the key - node_id - is different from null. if true than go over the collection 
of the node and create a list that get the info of the node from the graph and the key and the tag (present the weight) from the getNi function.
return the nodes that has connect to this current node.
the info we get from the graph is to see if it not has been changed.

the class WGraph_Algo  has object type graph and can return the question what the weight between two nodes if there is
any, what the path between them and if there is a valid path between all the nodes.
also can save the information about the current WGraph_DS to file and get infomation about WGraph_DS and build new one.  

**explain about bfs:**
the function add the key of the node that has been seen to the list and craete Point with the weight from node(src) untils the current node. the wight represent the sum of all the wieght of the nodes go through between node(src) to this current node. after create the Point add it to the hashmap - map.
this functionrun in O(n) at the worst case - all the node can see only one time because the map save their key.
in the best case O(1) - if the given number are equals then return 0 or only node(src) = node(dest). if the node(src) or the node(dest) not in the graph return null or -1.

***

## the game

The game gets a directed graph which represents ways and targets,
It also receives Pokemon placements from the server and places "agents" on points.

The purpose of the game is to catch a lot of Pokemon in the shortest time and "moves" as low as possible.

The movement of "agents" between vertex works by the BFS method and each "agent" searches for the nearest Pokemon.

The game also shows this in the graphical interface which is created in the GUI

**the progress of the game:**

1) The Main window will appear first and the player need to put his id and chose a level betwen 0 to 23 -> ([0,23]).

![](https://github.com/LIADN7/Pokemon-GAME/blob/master/img/Main.png)


There are "options" that allow you to:
* Start the game.
* Save result -> Save the score on the PC (in the project folder) on txt file, nameof the file is "score.txt"
* Uploads the result to the server.
* Exit -> to Exit from the game.

![](https://github.com/LIADN7/Pokemon-GAME/blob/master/img/Main_options.png)


After pressing the button to start the game, the game start to run and shown on screen the "agents" move to catch the Pokemon


![](https://github.com/LIADN7/Pokemon-GAME/blob/master/img/GamePlay11.png)

![](https://github.com/LIADN7/Pokemon-GAME/blob/master/img/GamePlay0.png)


**[@authors liadn7](https://github.com/liadn7)**

**[@authors avielc11](https://github.com/avielc11)**
