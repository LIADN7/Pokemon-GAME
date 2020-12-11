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
		game_service g = Game_Server_Ex2.getServer(0);

		//build myGame
		myGame game = new myGame(g);
		System.out.println("start is good - dont touch");

		//build array of list before the game start
		HashMap<Integer, LinkedList<node_data>> list = new HashMap<Integer, LinkedList<node_data>>();
		Iterator<CL_Agent> movea = game.getAsh().iterator();
		while(movea.hasNext()) {
			
			CL_Agent coach = movea.next();
			LinkedList<node_data> q = game.NearestPoke(coach);
			list.put(coach.getID(), q);
		}
		System.out.println("find the next poemon to go to is good - can start create the GUI");
		
		//start the game
		g.startGame();
		while(g.isRunning()) {
			game.stPOKE(g);
			movea =game.getAsh().iterator();
			while(movea.hasNext()) {
				CL_Agent coach = movea.next();
				if(list.get(coach.getID())==null) {
					LinkedList<node_data> l = game.NearestPoke(coach);
					list.get(coach.getID()).addAll(l);
				}				
				if(list.get(coach.getID())!=null && coach.get_curr_edge()==null) {
					node_data node = list.get(coach.getID()).poll();
					g.chooseNextEdge(coach.getID(), node.getKey());
				}
			}

			//move
			g.move();
		}
	}

	//draw the graph by "grp" and locate the pokemon by "poke"  on the graph
	/** 
	 * Frame frame = new Frame(game);
	 * Panel panel = new Panel();
	 * frame.add(panel);
	 * frame.setVisible(true);
	 */






}