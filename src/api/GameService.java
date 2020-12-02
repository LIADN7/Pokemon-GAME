package api;
import java.util.*;
import java.io.File;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gameClient.CL_Agent;
import gameClient.CL_Pokemon;

public class GameService implements game_service {
	private DWGraph_Algo grpA = new DWGraph_Algo();
	private List<CL_Pokemon> poke = new LinkedList<CL_Pokemon>();
	private List<CL_Agent> ash = new LinkedList<CL_Agent>();
	
	
	@Override
	public String getGraph() {
		StringBuilder sb = new StringBuilder();
		Gson gson=new GsonBuilder().create();
		sb.append(gson.toJson(this.grpA.getGraph()));
		return sb.toString();
	}

	@Override
	public String getPokemons() {
		StringBuilder sb = new StringBuilder();
		Gson gson=new GsonBuilder().create();
		sb.append(gson.toJson(this.poke));
		return sb.toString();

	}

	@Override
	public String getAgents() {
		StringBuilder sb = new StringBuilder();
		Gson gson=new GsonBuilder().create();
		sb.append(gson.toJson(this.ash));
		return sb.toString();
	}

	@Override
	public boolean addAgent(int start_node) {
		CL_Agent r = new CL_Agent(this.grpA.getGraph(), start_node);
		ash.add(r);
		return false;
	}

	@Override
	public long startGame() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long stopGame() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long chooseNextEdge(int id, int next_node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long timeToEnd() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String move() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
