import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI_Rules extends JPanel
{
	public GUI_Rules ()
	{
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.BLACK);

		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.BOLD, 30)); 
		g.drawString("HOW TO PLAY",100,50);

		g.setFont(new Font("TimesRoman", Font.BOLD, 15));
		g.drawString("1. Use the arrow keys to control the snake.", 75,80);
		g.drawString("2. Navigate to the green square and go through it", 75,100);
		g.drawString("3. The snake will grow longer and you get a point", 75,120);
		g.drawString("4. Your Goal is to get as many points as possible", 75,140);
		g.drawString("5. You lose the game when you hit yourself or hit the border of the gamegrid", 75,160);
	}
}