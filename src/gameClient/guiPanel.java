package gameClient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import api.*;
import gameClient.util.*; 


public class guiPanel extends JPanel{
	private myGame game;
	private Range2Range _w2f;

	public guiPanel(myGame game) {
		this.game = game;
		updatepanel();
		this.setBackground(Color.yellow);
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
		//ImageIcon image = new ImageIcon("./img/map.jpg");
		g.setColor(Color.yellow);
		g.fill3DRect(0, 0, w, h, true);
		drawPokemon(g);
		drawGraph(g);
		drawAgent(g);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//ImageIcon image = new ImageIcon("./img/map.jpg");
	}

	public void drawGraph(Graphics g) {
		for(node_data node : game.getGraph().getV()) {
			g.setColor(Color.white);
			drawNode(node,g);
			for(edge_data edge : game.getGraph().getE(node.getKey())) {
				g.setColor(Color.white);
				drawEdge(edge,g);
			}
		}
	}

	public void drawPokemon(Graphics g) {
		for(CL_Pokemon poke : game.getPoke()) {
			Point3D c = poke.getLocation();
			g.setColor(Color.green);
			if(poke.getType()<0) {g.setColor(Color.blue);}
			geo_location fp = this._w2f.world2frame(c);
			g.fillOval((int)fp.x()-10, (int)fp.y()-10, 2*10, 2*10);
		}
	}

	public void drawAgent(Graphics g) {
		g.setColor(Color.red);
		for(CL_Agent ash : game.getAsh()) {
			geo_location pos = ash.getLocation();
			geo_location fp = this._w2f.world2frame(pos);
			g.fillOval((int)fp.x()-5, (int)fp.y()-5, 2*5, 2*5);
			g.drawString(""+ash.getID(), (int)fp.x(), (int)fp.y()-4*5);
		}
		//repaint();
	}
	
	
	private void drawNode(node_data node , Graphics g) {
		geo_location pos = node.getLocation();
		geo_location fp = this._w2f.world2frame(pos);
		g.fillOval((int)fp.x()-5, (int)fp.y()-5, 2*5, 2*5);
		g.setColor(Color.black);
		g.drawString(""+node.getKey(), (int)fp.x(), (int)fp.y()-4*5);
	}
	
	private void drawEdge(edge_data e, Graphics g) {
		directed_weighted_graph gg = game.getGraph();
		geo_location s = gg.getNode(e.getSrc()).getLocation();
		geo_location d = gg.getNode(e.getDest()).getLocation();
		geo_location s0 = this._w2f.world2frame(s);
		geo_location d0 = this._w2f.world2frame(d);
		g.drawLine((int)s0.x(), (int)s0.y(), (int)d0.x(), (int)d0.y());
	}


}
