package gameClient;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import api.*;
import gameClient.util.*; 


/**
 * represent the panel of the Pokemon game
 * @author liadn7
 * @author avielc11
 */
public class guiPanel extends JPanel{
	private myGame game;
	private Range2Range _w2f;
	private game_service server;

	/**
	 * constructor for the game
	 * @param game - type myGame
	 * @param server - type gsme_service
	 */
	public guiPanel(myGame game, game_service server) {
		this.game = game;
		this.server = server;
		updatepanel();
	}

	public void update(myGame game) {
		this.game = game;
		updatepanel();
	}

	/**
	 * Translate the location of the nodes, the pokemon and the agent to the location on the screen (panel)
	 */
	private void updatepanel() {
		Range rx = new Range(this.getHeight()+200,800);
		Range ry = new Range(500,this.getWidth()+100);
		Range2D frame = new Range2D(rx,ry);
		_w2f = myGame.w2f(game.getGraph(),frame);
	}

	/**
	 * Draws the graph , the pokemon and the agent connect to game.
	 * and also the time has left to the the game and the score of the agent until now
	 */
	public void paint(Graphics g) {
		Image im = getToolkit().getImage("./img/gameIn.jpg");
		g.drawImage(im, 0, 0, this);
		drawGraph(g);
		drawPokemon(g);
		drawAgent(g);
		drawScore(g);
		drawTime(g);
	}

	/**
	 * draw the graph
	 * @param g --> Graphics
	 */
	private void drawGraph(Graphics g) {
		for(node_data node : game.getGraph().getV()) {
			g.setColor(Color.RED);
			drawNode(node,g);
			for(edge_data edge : game.getGraph().getE(node.getKey())) {
				g.setColor(Color.black);
				drawEdge(edge,g);
			}
		}
	}

	/**
	 * draw the Pokemons on the graph
	 * @param g --> Graphics
	 */
	private void drawPokemon(Graphics g) {
		for(CL_Pokemon poke : game.getPoke()) {
			double rank = poke.getValue();
			int typez=poke.getType();
			Point3D c = poke.getLocation();
			if(typez<0) {
				Image im = getToolkit().getImage(""+charm(rank));
				geo_location fp = this._w2f.world2frame(c);
				g.drawImage(im, (int)fp.x()-10, (int)fp.y()-10, this);

			}
			else {
				Image im = getToolkit().getImage(""+squir(rank));
				geo_location fp = this._w2f.world2frame(c);
				g.drawImage(im, (int)fp.x()-10, (int)fp.y()-10, this);

			}
		}
	}

	/**
	 * draw the agent on the graph
	 * @param g --> Graphics
	 */
	private void drawAgent(Graphics g) {
		for(CL_Agent ash : game.getAsh()) {
			geo_location pos = ash.getLocation();
			geo_location fp = this._w2f.world2frame(pos);
			Image im = getToolkit().getImage("./img/ash.png");
			g.drawImage(im, (int)fp.x()-5, (int)fp.y()-5, this);
			g.drawString(""+ash.getID(), (int)fp.x(), (int)fp.y()-4*5);
		}

	}

	/**
	 * draw the score for each agent
	 * @param g --> Graphics
	 */
	private void drawScore(Graphics g) {
		int y = 15;
		for(CL_Agent coach : game.getAsh()) {
			g.setColor(Color.black);
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			g.drawString("triner ID: " + coach.getID() + " score: "+coach.getValue(), 10, y);
			y+=17;
		}
	}

	/**
	 * draw the time that has left until the game is will end (in seconds)
	 * @param g --> Graphics
	 */
	private void drawTime(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		g.drawString("time left: " + this.server.timeToEnd()/1000+" seconds", 758, 50);
	}

	/**
	 * check if the pokemon on the rib from high node to low node
	 * @param s --> Pokemon type by bonus amount
	 * @return File location of the Pokemon
	 */
	private String charm(double s) {
		if(s<=5) return "./img/c0.jpeg";
		else if(s>5&&s<=8) return "./img/c1.jpeg";
		else if(s>8&&s<=12) return "./img/c2.jpeg";
		else if(s>12&&s<=14) return "./img/c3.jpeg";
		else return "./img/Lugia.png";
	}


	/**
	 * check if the pokemon on the rib low node to high node
	 * @param s --> Pokemon type by bonus amount
	 * @return File location of the Pokemon
	 */
	private String squir(double s) {
		if(s<=5) return "./img/s0.jpeg";
		else if(s>5&&s<=8) return "./img/s1.jpeg";
		else if(s>8&&s<=12) return "./img/s2.jpeg";
		else if(s>12&&s<=14) return "./img/s3.jpeg";
		else return "./img/Mewtwo.jpg";
	}	

	/**
	 * draw the nodes of the graph
	 * @param node --> Node of the graph
	 * @param g --> Graphics
	 */
	private void drawNode(node_data node , Graphics g) {
		geo_location pos = node.getLocation();
		geo_location fp = this._w2f.world2frame(pos);
		g.fillRect((int)fp.x()-5, (int)fp.y()-5, 2*10, 2*10);
		g.setColor(Color.black);
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		g.drawString(""+node.getKey(), (int)fp.x(), (int)fp.y()-10);
	}

	/**
	 * draw the edges(rib) between two nodes on the graph
	 * @param e --> Edge of the graph
	 * @param g --> Graphics
	 */
	private void drawEdge(edge_data e, Graphics g) {
		directed_weighted_graph gg = game.getGraph();
		geo_location s = gg.getNode(e.getSrc()).getLocation();
		geo_location d = gg.getNode(e.getDest()).getLocation();
		geo_location s0 = this._w2f.world2frame(s);
		geo_location d0 = this._w2f.world2frame(d);
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());
	}
	
}
