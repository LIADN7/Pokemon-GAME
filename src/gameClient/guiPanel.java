package gameClient;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import api.*;
import gameClient.util.*; 


public class guiPanel extends JPanel{
	private myGame game;
	private Range2Range _w2f;
	private game_service server;
	//	private long time;




	public guiPanel(myGame game, game_service server) {
		this.game = game;
		this.server = server;
		updatepanel();




	}











	public void update(myGame game) {
		this.game = game;
		updatepanel();
	}

	private void updatepanel() {
		//		Range rx = new Range(20,this.getWidth()-20);
		//		Range ry = new Range(this.getHeight()-10,150);
		Range rx = new Range(this.getHeight()+200,800);
		Range ry = new Range(500,this.getWidth()+100);
		Range2D frame = new Range2D(rx,ry);
		_w2f = myGame.w2f(game.getGraph(),frame);
	}



	public void paint(Graphics g) {
		int w = this.getWidth();
		int h = this.getHeight();
//		g.setColor(Color.orange);
		Image im = getToolkit().getImage("C:\\eclipse-workspace\\Ex2Year2\\img\\gameIn.jpg");
		g.drawImage(im, 0, 0, this);
		//g.fill3DRect(0, 0, w, h, true);
		drawTime(g);
		drawScore(g);
		drawPokemon(g);
		drawGraph(g);
		drawAgent(g);
	}


	public void drawScore(Graphics g) {
		int y = 15;
		for(CL_Agent coach : game.getAsh()) {
			g.setColor(Color.black);
			g.setFont(new Font("Tahoma", Font.BOLD, 15));
			g.drawString("triner ID: " + coach.getID() + " score: "+coach.getValue(), 10, y);
			y+=17;
		}

	}




	public void drawTime(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		g.drawString("time left: " + this.server.timeToEnd()/1000+" seconds", 758, 50);
	}





	public void drawGraph(Graphics g) {
		for(node_data node : game.getGraph().getV()) {
			g.setColor(Color.RED);
			drawNode(node,g);
			for(edge_data edge : game.getGraph().getE(node.getKey())) {
				g.setColor(Color.black);
				drawEdge(edge,g);
			}
		}
	}

	public void drawPokemon(Graphics g) {
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

	private String charm(double s) {
		if(s<=5) return "C:\\eclipse-workspace\\Ex2Year2\\img\\c0.jpeg";
		else if(s>5&&s<=10) return "C:\\eclipse-workspace\\Ex2Year2\\img\\c1.jpeg";
		else if(s>10&&s<=14) return "C:\\eclipse-workspace\\Ex2Year2\\img\\c2.jpeg";
		else return "C:\\eclipse-workspace\\Ex2Year2\\img\\c3.jpeg";
	}
	
	private String squir(double s) {
		if(s<=5) return "C:\\eclipse-workspace\\Ex2Year2\\img\\s0.jpeg";
		else if(s>5&&s<=10) return "C:\\eclipse-workspace\\Ex2Year2\\img\\s1.jpeg";
		else if(s>10&&s<=14) return "C:\\eclipse-workspace\\Ex2Year2\\img\\s2.jpeg";
		else return "C:\\eclipse-workspace\\Ex2Year2\\img\\s3.jpeg";
	}	
	
	
	
	
	
	public void drawAgent(Graphics g) {

		for(CL_Agent ash : game.getAsh()) {
			geo_location pos = ash.getLocation();
			geo_location fp = this._w2f.world2frame(pos);

			
			Image im = getToolkit().getImage("C:\\eclipse-workspace\\Ex2Year2\\img\\ash.png");
			g.drawImage(im, (int)fp.x()-5, (int)fp.y()-5, this);
			g.drawString(""+ash.getID(), (int)fp.x(), (int)fp.y()-4*5);
		}

	}


	private void drawNode(node_data node , Graphics g) {
		geo_location pos = node.getLocation();
		geo_location fp = this._w2f.world2frame(pos);
		g.fillRect((int)fp.x()-5, (int)fp.y()-5, 2*10, 2*10);
		g.setColor(Color.black);
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		g.drawString(""+node.getKey(), (int)fp.x(), (int)fp.y()-10);
	}

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
