package gameClient;

import Server.Game_Server_Ex2;
import api.*;
import gameClient.*;
import gameClient.util.*;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class Ex2 {

	/**
	 * start the project. 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		game_service game = Game_Server_Ex2.getServer(16);

		//build myGame
		myGame g = new myGame(game);
		DWGraph_Algo algo = new DWGraph_Algo();
		algo.init(g.getGrp());

		


			
			HashMap<Integer, LinkedList<node_data>> list = new HashMap<Integer, LinkedList<node_data>>();

			Iterator<CL_Agent> movea = g.getAsh().iterator();
			//build array of list before the game start
			while(movea.hasNext()) {
				CL_Agent tempA = movea.next();
				LinkedList<node_data> q = NearestPoke(tempA, g.getPoke(), algo);
				list.put(tempA.getID(), q);
			}

			//start the game
			game.startGame();

			while(game.isRunning()) {
				g.stGRP(game);

//				Iterator<List > moveGame = list.values().iterator();
				movea =g.getAsh().iterator();
				while(movea.hasNext()) {
					CL_Agent t = movea.next();
					if(list.get(t.getID())==null) {
						NearestPoke(t, g.getPoke(), algo);
					}
					if(list.get(t.getID())!=null && t.get_curr_edge()==null) {
						node_data node = list.get(t.getID()).poll();
						game.chooseNextEdge(t.getID(), node.getKey());
					}
				}




				//move
			}
		}

		//draw the graph by "grp" and locate the pokemon by "poke"  on the graph
		/** 
		 * Frame frame = new Frame(game);
		 * Panel panel = new Panel();
		 * frame.add(panel);
		 * frame.setVisible(true);
		 */


	

	public static LinkedList<node_data> NearestPoke(CL_Agent a, LinkedList<CL_Pokemon> poke, DWGraph_Algo algo) {
		double dist=-1;
		int shortEnd=-1;
		int start = a.getSrcNode();
		boolean flag = true;
		Iterator<CL_Pokemon> moves = poke.iterator() ;


		while(moves.hasNext()) {
			CL_Pokemon pok = moves.next();
			int  p = pok.get_edge().getSrc();
			double end = algo.shortestPathDist(start, p);	
			if(dist == 0 || (end != -1 && end < dist)){
				dist = end;
				shortEnd=p;
				a.set_curr_fruit(pok);
			}
		}
		int src = a.get_curr_fruit().get_edge().getSrc();
		int dest = a.get_curr_fruit().get_edge().getDest();
		LinkedList<node_data> q = (LinkedList<node_data>) algo.shortestPath(a.getID(), src);
		q.add(algo.getGraph().getNode(dest));
		return q;	

	}




}
