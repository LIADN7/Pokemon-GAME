package gameClient;

import java.util.HashMap;
import java.util.LinkedList;

import Server.Game_Server_Ex2;
import api.game_service;
import api.node_data;

public class Temp  implements Runnable{
	private static guiFrame frame;
	private static myGame game;
	private static HashMap<Integer, LinkedList<node_data>> map;
	
	/**
	 * run the game - first ask which scenario to run , run the chosen scenario and finely ask if save the result.
	 * if click on the yes then save the result on the server.
	 */
	@Override
	public void run() {
		startGame();
		
	}
	
	public void startGame() {
		guiFrame frame1;
		frame1 = new guiFrame(0, null);
		
		//waiting for select level
		while(frame1.getFlag() && Ex2.ID.equals("")) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		frame1.dispose();
		SimplePlayer player = new SimplePlayer("./img/music.mp3");
		Thread playerThread = new Thread(player);
		playerThread.start();
		int scenario_num = Ex2.scenario;
		game_service server = Game_Server_Ex2.getServer(scenario_num);
		init(server);
		locateAgent();
		
		frame.setTitle("Pokemon game (created by liadn7 and avielc11) scenario_num is " + scenario_num);
		frame.addId(frame1);
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
		for(CL_Agent a : game.getAsh())
			sum += a.getValue();
		String score = ""+"scenario_num = " + scenario_num + " point = " +  sum + " moves = " + j;
		System.out.println(score);
		frame.addScore(score);
		System.out.println("end game");
//		playerThread.stop();
		server.stopGame();
	}

	/**
	 * first step for the game
	 * direct the agent to the nearest pokemon.
	 */
	public static void locateAgent() {
		map = new HashMap<Integer, LinkedList<node_data>>();
		for(CL_Agent coach : game.getAsh()) {
			LinkedList<node_data> q = game.NearestPoke(coach);
			q.poll();
			map.put(coach.getID(), q);
		}
	}

	/**
	 * update the data about the pokemon and the agent.
	 * f the agent does not move then move it towards the next node in the list towards the Pokemon
	 * @param server - type game_service
	 */
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

	/**
	 * create the panel with all the locatioon of the graph,the agent and the pokemon.
	 * @param server - type game_srevice
	 */
	public void init(game_service server) {
		game = new myGame(server);
		frame = new guiFrame(1,server);					
		guiPanel panel = new guiPanel(game, server);
		frame.add(panel);		
		panel.update(game);
	}


	/**
	 * check which scenario is chosen
	 * @param num - the scenario number
	 * @return - the miliseconds the game will wait
	 */
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
