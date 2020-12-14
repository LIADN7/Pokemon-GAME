package gameClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;

import javax.swing.*;

public class guiFrame extends JFrame{
	
	public guiFrame() {
		initframe();
		addMenu();
	}
	
	public void initframe() {
//		this.setTitle("Pokemon game (created by liad and aviel) "); //sets title of frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
		ImageIcon image = new ImageIcon("./img/icon.png"); //create an ImageIcon
		this.setIconImage(image.getImage()); //change icon of frame
		this.setSize(1000, 600);
		this.setVisible(true);
	}
	
	public void addMenu() {
		MenuBar menu = new MenuBar();
		Menu m = new Menu("menu");
		menu.add(m);
		this.setMenuBar(menu);
	}
}



