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
		List<CL_Pokemon> poke = g.getPoke();
		
		//add agent by "numA" - add by find the location for one of the "poke"
		for(int i = 0 ; i < g.getNumA() ; i++) {
			if(g.getPoke().size() > i) {
				int src = poke.get(i).get_edge().getSrc();
				game.addAgent(src);
			}
			else if(poke != null){
				int src = poke.get(0).get_edge().getSrc();
				game.addAgent(src);
			}
		} 
		g.stAGE(game);
		List<CL_Agent> ash = g.getAsh();
		List<List> list = new LinkedList<List>();
		
		//build array of list before the game start
		for(int i = 0 ; i < ash.size() ; i++) {
			
			NearestPoke(ash.get(i),poke);
			List<node_data> q = findPath(algo , ash.get(i));
			list.add(q);
		}
		
		//start the game
		game.startGame();
		
		 while(game.isRunning()) {
			 poke = g.getPoke();
			 for(int i = 0 ; i < ash.size() ; i++) {
				 if(list.get(i) == null) {
						//find the nearest "poke" near "ash"
						NearestPoke(ash.get(i),poke);
						//find the shortest path from "ash" to "poke"
						List<node_data> q = findPath(algo , ash.get(i));
						list.get(i).pl
						
				 }
			 }
			
			//move
		 }

		//draw the graph by "grp" and locate the pokemon by "poke"  on the graph
			 /** 
		 	* Frame frame = new Frame(game);
		 	* Panel panel = new Panel();
		 	* frame.add(panel);
		 	* frame.setVisible(true);
		 	*/
		
		
	}
	
	public static void NearestPoke(CL_Agent a, List<CL_Pokemon> poke) {
		
	}
	
	public static List<node_data> findPath(DWGraph_Algo algo, CL_Agent a){
		int src = a.get_curr_fruit().get_edge().getSrc();
		int dest = a.get_curr_fruit().get_edge().getDest();
		List<node_data> q = algo.shortestPath(a.getID(), src);
		q.add(algo.getGraph().getNode(dest));
		return q;
	}
	
	
	
}
