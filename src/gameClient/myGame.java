package gameClient;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import api.*;
import gameClient.util.Point3D;

public class myGame {
	private directed_weighted_graph grp;
	private List<CL_Agent> ash;
	private List<CL_Pokemon> poke;
	private List<String> _info;  //maybe to do
	private int numA;
	
	public myGame(game_service game) {
		stGRP(game);
		stPOKE(game);
		numOfAgent(game);
	}
	
	public void stGRP(game_service game) {
		try {
			Gson gson = new Gson();
			this.grp = gson.fromJson(game.getGraph(), DWGraph_DS.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void stPOKE(game_service game) {
		this.poke = new  LinkedList<CL_Pokemon>();
		try {
			JSONObject first = new JSONObject(game.getPokemons());
			JSONArray second = first.getJSONArray("Pokemons");
			Gson gson = new Gson();
			for(int i = 0 ; i < second.length() ; i++) {
			
				JSONObject third = second.getJSONObject(i);
				JSONObject fourth = third.getJSONObject("Pokemon");
				int t = fourth.getInt("type");
				double v = fourth.getDouble("value");
				String p = fourth.getString("pos");
				CL_Pokemon f = new CL_Pokemon(new Point3D(p), t, v, 0, null);
				this.poke.add(f);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	//check if it work??? 
	public void stAGE(game_service game) {
		//check if it work??? 
		this.ash = new  LinkedList<CL_Agent>();
		try {
			JSONObject first = new JSONObject(game.getAgents());
			JSONArray second = first.getJSONArray("Agents");
			Gson gson = new Gson();
			for(int i = 0 ; i < second.length() ; i++) {
			
				JSONObject third = second.getJSONObject(i);
				JSONObject fourth = third.getJSONObject("Agents");
				int node = fourth.getInt("_curr_node");
				CL_Agent f = new CL_Agent(this.grp , node);
				this.ash.add(f);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void numOfAgent(game_service game) {
		String info = game.toString();
		JSONObject line;
		try {
			line = new JSONObject(info);
			JSONObject ttt = line.getJSONObject("GameServer");
			numA = ttt.getInt("agents");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	////////////////////////////////////////////////
	/////////////getters & setters//////////////////
	////////////////////////////////////////////////
	public directed_weighted_graph getGrp() {
		return grp;
	}

	public void setGrp(directed_weighted_graph grp) {
		this.grp = grp;
	}

	public List<CL_Agent> getAsh() {
		return ash;
	}

	public void setAsh(List<CL_Agent> ash) {
		this.ash = ash;
	}

	public List<CL_Pokemon> getPoke() {
		return poke;
	}

	public void setPoke(List<CL_Pokemon> poke) {
		this.poke = poke;
	}

	public int getNumA() {
		return numA;
	}

	public void setNumA(int numA) {
		this.numA = numA;
	}
}
