import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;

public class TheGame extends JPanel implements ActionListener, KeyListener
{
	private Timer tm=new Timer(80, this);
	private int x=150, velX=0;
	private int y=180, velY=0;
	private int x_cor=0;
	private int y_cor=0;
	private int mc=0;
	private int c;
	private int ctr = 0;
	private int[] keyStrokes = new int[100];
	//private JLabel statusbar;
	//private int[] c= new int[1000];
	private boolean foodEat=true;
	private boolean b1=false;
	private ArrayList<Integer> snakePart_X=new ArrayList<Integer>();
	private ArrayList<Integer> snakePart_Y=new ArrayList<Integer>();
	//private Snake_PosVel s_part= new Snake_PosVel(x,velX,y,velY);
	// private Snake_PosVel s_part2= new Snake_PosVel(x+15,velX,y,velY);
	// private Snake_PosVel s_part3= new Snake_PosVel(x+30,velX,y,velY);
	private int length=1;
	private int temp_l=0;
	public TheGame ()
	{
		tm.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		snakePart_X.add(x);
		snakePart_Y.add(y);
		//statusbar=new JLabel("Score:0");
		//add(statusbar, BorderLayout.SOUTH);
		// snakePart_PosVel.add(s_part2);
		// snakePart_PosVel.add(s_part3);
		
	}
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		
		//System.out.println(length);
		g.setColor(Color.GREEN);
		g.drawString("Score: "+length,250,615);

		if (!b1)
		{
			for (int k=0;k<length-1;k++)
			{
				snakePart_X.set(k,((Integer)(snakePart_X.get(k+1))));
				snakePart_Y.set(k,((Integer)(snakePart_Y.get(k+1))));
			}
		}
		else
		{
			if (temp_l==2)
			{
				temp_l=-1;
				b1=false;
			}
			temp_l++;	
		}
		snakePart_X.set((length-1),x);
		snakePart_Y.set((length-1),y);	

		for (int j=length-1;j>=0;j--)
		{
			int tmp_x=(int)snakePart_X.get(j);
			//System.out.println(tmp_x);
			int tmp_y=(int)snakePart_Y.get(j);
			//System.out.println(tmp_y);
			g.setColor(Color.WHITE);
			g.fillRect(tmp_x,tmp_y, 15,15);
		}

		g.setColor(Color.GREEN);
		g.fillRect(foodPlacement_xcor(),foodPlacement_ycor(),15,15);

		b1=checkFoodCont();
		if (temp_l!=0)
		{
			b1=true;
		}
		if (b1)
		{
			length++;	
			if (velX==-15)
			{
				snakePart_X.add(x_cor+(15*(length-1)));
				snakePart_Y.add(y_cor);
			}
			if (velX==15)
			{
				snakePart_X.add(x_cor-(15*(length-1)));
				snakePart_Y.add(y_cor);
			}
			if (velY==-15)
			{
				snakePart_X.add(x_cor);
				snakePart_Y.add(y_cor+(15*(length-1)));

			}
			if (velY==15)
			{
				snakePart_X.add(x_cor);
				snakePart_Y.add(y_cor-(15*(length-1)));
			}
		}
		if (x>=600||y>=600||x<=0||y<=0)
		{
			g.setColor(Color.RED);
			g.fillRect(x,y,15,15);
			g.setColor(Color.GREEN);
			g.drawString("Score: "+length+"   GAME OVER--Went Outside Gamegrid",250,615);
			removeKeyListener(this);
			tm.stop();		
		}
		if (checkCollision())
		{
			g.setColor(Color.RED);
			g.fillRect(x,y,15,15);
			g.setColor(Color.GREEN);
			g.drawString("Score: "+length+"   GAME OVER--Collidied With Yourself",250,615);
			removeKeyListener(this);
			tm.stop();				
		}

		g.setColor(Color.ORANGE);
		for (int i=0;i<40;i++)
		{
			g.drawLine((15*(i+1)),15,(15*(i+1)),600);
			g.drawLine(15,(15*(i+1)),600,(15*(i+1)));
		}

	}

	public void actionPerformed(ActionEvent e)
	{
		ctr = 0;
		c = keyStrokes[0];
		if (c==KeyEvent.VK_LEFT&&velX!=15)
		{
			velX=-15;
			velY=0;
			x=x+velX;
			y=y+velY;
			repaint();
		}
		if(c==KeyEvent.VK_UP&&velY!=15)
		{
			velX=0;
			velY=-15;
			x=x+velX;
			y=y+velY;
			repaint();
		}
		if(c==KeyEvent.VK_RIGHT&&velX!=-15)
		{
			velX=15;
			velY=0;
			x=x+velX;
			y=y+velY;
			repaint();
		}
		if(c==KeyEvent.VK_DOWN&&velY!=-15)
		{
			velX=0;
			velY=15;
			x=x+velX;
			y=y+velY;
			repaint();
		}
		mc++;
	}

	public void keyPressed(KeyEvent e)
	{
		ctr++;
		if (c == 39 && e.getKeyCode() == 37) {
			c = 39;
		} else if (c == 37 && e.getKeyCode() == 39) {
			c = 37;
		} else if (c == 38 && e.getKeyCode() == 40) {
			c = 38;
		} else if (c == 40 && e.getKeyCode() == 38) {
			c = 40;
		} else {
			c=e.getKeyCode();
		}
		keyStrokes[ctr-1] = c;
	}


	public void keyReleased(KeyEvent e)
	{}
	
	public void keyTyped(KeyEvent e)
	{}

	public boolean checkCollision()
	{
		boolean colCond=false;
		int tempx1=(int)snakePart_X.get(length-1);
		int tempy1=(int)snakePart_Y.get(length-1);
		int tempx2=0;
		int tempy2=0;
		for (int j=length-2;j>=0;j--)
		{
			tempx2=(int)snakePart_X.get(j);
			tempy2=(int)snakePart_Y.get(j);			
			if (tempx1==tempx2&&tempy1==tempy2)
			{
				colCond=true;
				break;
			}
			else
				colCond=false;
		}
		return colCond;
	}

	public int foodPlacement_xcor ()
	{
		if (foodEat)
		{
			x_cor=(int)(40*Math.random())*15;
			for (int i = 0; i < snakePart_X.size(); i++) {
				if (x_cor == snakePart_X.get(i)) {
					x_cor = (int)(40*Math.random())*15;
					i = 0;
				}
			}
		}
		if (x_cor==0)
			x_cor=15;

		return x_cor;			
	}
	public int foodPlacement_ycor ()
	{
		if (foodEat)
		{
			y_cor=(int)(40*Math.random())*15;
			for (int i = 0; i < snakePart_Y.size(); i++) {
				if (y_cor == snakePart_Y.get(i)) {
					y_cor=(int)(40*Math.random())*15;
					i = 0;
				}
			}
			foodEat=false;
		}
		if (y_cor==0)
			y_cor=15;
		return y_cor;			
	}
	public boolean checkFoodCont()
	{
		if (x==x_cor&&y==y_cor)
		{
			foodEat=true;
			return true;
		}
		else
			return false;
	}
}