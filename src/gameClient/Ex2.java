package gameClient;

import Server.Game_Server_Ex2;
import api.*;
import gameClient.*;
import gameClient.util.*;

import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class Ex2 implements Runnable{
	private static guiFrame frame;
	private static myGame game;
	private static HashMap<Integer, LinkedList<node_data>> map;

	
	
	public static void main(String[] args) {
		Thread client = new Thread(new Ex2());
		client.start();


	}
	@Override
	public void run() {
		
		guiFrame frame1;
		frame1 = new guiFrame(0);
		
		int scenario_num = frame1.getlvl();
		game_service server = Game_Server_Ex2.getServer(scenario_num);
		init(server);
		locateAgent();
		//paint(server);
		frame.setTitle("Pokemon game (created by liad and aviel) scenario_num is " + scenario_num);
		
		
		
		server.startGame();
		int ind=0;
		long dt= checkScenario(scenario_num);

		int j = 0;
		server.startGame();
		while(server.isRunning()) {
			server.move();
			j++;
			moveAgants(server);
			try {
				if(ind%1==0) {
					frame.repaint();
				}
				Thread.sleep(dt);
				ind++;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		int sum = 0;
		//System.out.println("login = " + server.login(206192999));
		for(CL_Agent a : game.getAsh())
			sum += a.getValue();
		String score = ""+"scenario_num = " + scenario_num + " point = " +  sum + " moves = " + j;
		System.out.println(score);
		frame.addScore(score);
		System.out.println("end game");
		server.stopGame();
	}

	public static void locateAgent() {
		map = new HashMap<Integer, LinkedList<node_data>>();
		for(CL_Agent coach : game.getAsh()) {
			LinkedList<node_data> q = game.NearestPoke(coach);
			q.poll();
			map.put(coach.getID(), q);
		}
	}

	public static void moveAgants(game_service server) {
		game.setAgent(server);
		game.setPokemons(server);
		for(CL_Agent coach : game.getAsh()) {
			int id = coach.getID();
			if(!coach.isMoving()) {
				if(map.get(id).isEmpty()) {
					LinkedList<node_data> l = game.NearestPoke(coach);
					while(!l.isEmpty())
						map.get(id).add(l.poll()); 
				}				
				if(!map.get(id).isEmpty()) {
					node_data node = map.get(id).poll();
					server.chooseNextEdge(id, node.getKey());
				}
			}
		}
	}

	public void init(game_service server) {
		game = new myGame(server);


		frame = new guiFrame(1);				
		
		guiPanel panel = new guiPanel(game, server);
		frame.add(panel);
		
		SimplePlayer player = new SimplePlayer("./img/music.mp3");
		Thread playerThread = new Thread(player);
		playerThread.start();		
		panel.update(game);
		

	}

	public static long checkScenario(int num) {
		if(num == 0) {
			return 127;  
		}
		if(num == 5 || num == 4)
			return 90;
		else
			return 100;
	}
}