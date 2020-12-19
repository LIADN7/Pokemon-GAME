package gameClient;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * represent the panel of the save result for game
 * @author liadn7
 * @author avielc11
 */
public class guiSave extends JFrame implements ActionListener{
	private JButton btnYes;
	private JButton btnNo;
	private boolean flag = false;
	private int YON;
	/**
	 * Create the frame. the frame has two option to save the result of the game or not.
	 */
	public guiSave() {
		initialize();
		while(!flag) {
			btnYes.addActionListener(this);
			btnNo.addActionListener(this);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//this.setResizable(false);
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("do you want to save the result?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(100, 69, 242, 40);
		this.getContentPane().add(lblNewLabel);
		
		btnYes = new JButton("Yes");
		btnYes.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		btnYes.setBounds(257, 146, 85, 21);
		this.getContentPane().add(btnYes);
		
		btnNo = new JButton("No");
		btnNo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		btnNo.setBounds(100, 146, 85, 21);
		this.getContentPane().add(btnNo);
	}
	
	/**
	 * if click on the yes bottom then save the result 
	 * else do nothing.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnYes) { 
			flag = true;
			YON = 0;
		}
		else if(e.getSource() == btnNo) {
			flag = true;
			YON = 1;
		}
	}
	
	/**
	 * @return if the bottom yes click return 0 else return 1. 
	 */
	public int getYON() {
		return this.YON;
	}
}
