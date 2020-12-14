package gameClient;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.*;




//import org.graalvm.compiler.java.GraphBuilderPhase.Instance;

public class guiFrame extends JFrame implements ActionListener{

	private MenuItem saveResult;
	private MenuItem log;
	private MenuItem exit;
	
	
	private JButton btnNewButton;
	private boolean flag;
	private int lvl;
	private JTextField txtPressLevel;
	private String score;
	
	
	
	public int getlvl(){return this.lvl;}
	//	private JPanel contentPane;
	//	private JTextField txtPressLevel;

	public guiFrame(int x) {
		if(x==0) {
			initframe();
			initpanel();
			this.setVisible(true);

			addMenu();
			this.flag = true;

			while(flag) {
				btnNewButton.addActionListener(this);
			}
		}
		else {
			initframe();
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			addMenu();
			this.flag = true;			
			this.setVisible(true);
			
		}

	}



	public void initframe() {
		//		this.setTitle("Pokemon game (created by liad and aviel) "); //sets title of frame
		int frameWidth = 1000;
	    int frameHeight = 600;
		ImageIcon image = new ImageIcon("./img/icon.png"); //create an ImageIcon
		this.setIconImage(image.getImage()); //change icon of frame
		//this.setSize(1000, 600);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//setBounds(100, 100, 1000, 600);
		setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
		this.score="null";





	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == saveResult) save();
		else if(e.getSource() == btnNewButton)	stringCheck();
		else if(e.getSource() == exit) System.exit(0);
		else if(e.getSource() == log);	//aviel do
	}


	private void stringCheck() {
		String s = this.txtPressLevel.getText();
		if (s.length()>2)
			System.out.println("you give a wrong level");
		else if(s.length()==0) 
			System.out.println("you dont give a level");
		else if(s.length()==1) {
			char c = s.charAt(0);
			if(c>='0' && c<= '9') {
				this.lvl = Integer.parseInt(""+c);
				this.flag = false;
			}	
			else
				System.out.println("you give a wrong level");
		}
		else {
			char c0 = s.charAt(0);
			char c1 = s.charAt(1);		
			if((c0=='1' && c1>='0' && c1<= '9')||((c0== '2' && c1>='0' && c1<= '3' ))) {
				this.lvl = Integer.parseInt(s.substring(0,2));
				this.flag = false;
			}	
			else
				System.out.println("you give a wrong level");


		}


	}



	private void initpanel() {

		this.setLayout(null);

		class ImagePanel extends JComponent {
			private Image image;
			public ImagePanel(Image image) {
				this.image = image;
			}
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(image, 0, 0, this);
			}
		}
		File file = new File("./img/screen.jpg");
		BufferedImage myImage;
		try {
			myImage = ImageIO.read(file);
			this.setContentPane(new ImagePanel(myImage));			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		txtPressLevel = new JTextField();
		txtPressLevel.setText("0");
		txtPressLevel.setBounds(66, 79, 82, 71);
		txtPressLevel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtPressLevel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(txtPressLevel);
		txtPressLevel.setColumns(10);

		JLabel lblNewLabel = new JLabel("choose a level");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 11, 228, 57);
		this.add(lblNewLabel);

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\eclipse-workspace\\Ex2Year2\\img\\start.png"));
		btnNewButton.setBounds(370, 253, 228, 227);
		this.add(btnNewButton);

	}




	private void addMenu() {
		MenuBar menuB = new MenuBar();
		Menu menu = new Menu("options");
		menuB.add(menu);
		this.setMenuBar(menuB);
		
		
		saveResult = new MenuItem("save result");
		log = new MenuItem("load result to the server");
		exit = new MenuItem("Exit");
		
		menu.add(saveResult);
		menu.add(log);
		menu.add(exit);
		
		exit.addActionListener(this);
		saveResult.addActionListener(this);
		log.addActionListener(this);
	}

	public void addScore(String s) {
		this.score=s;
	}
	private void save() {
		try {
			PrintWriter pw=new PrintWriter(new File("score.txt"));

			pw.write(this.score);
			pw.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
}



