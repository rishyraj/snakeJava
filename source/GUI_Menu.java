import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class GUI_Menu extends JFrame
{
	private JButton b_play;
	private JButton b_rules;
	private JPanel jp;
	private JLabel lbl;
	public GUI_Menu ()
	{
		super ("Snake Game Menu");

		b_play=new JButton("PLAY!");
		b_play.setBounds(220,250,140,40);
		b_play.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					JFrame f=new JFrame("Snake");
					f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

					TheGame snakeGame=new TheGame();
					f.add(snakeGame);
					f.setSize(635,665);
					f.setVisible(true);		
				}
			}
		);

		b_rules=new JButton("How to Play");
		b_rules.setBounds(220,200,140,40);
		b_rules.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent event){
					JFrame f= new JFrame("Rules for Snake");
					f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

					GUI_Rules rulesScreen=new GUI_Rules();
					f.add(rulesScreen);
					f.setSize(650,400);
					f.setVisible(true);
				}
			}
		);

		this.setLayout(new FlowLayout());
		lbl=new JLabel(new ImageIcon(getClass().getResource("/icons/snake.png")));
		add(lbl);
		//validate();
		//setSize(600,600);
		//setVisible(true);
		
		add(b_rules);
		add(b_play);
	}
}