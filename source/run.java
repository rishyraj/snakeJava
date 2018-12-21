import javax.swing.*;
import java.awt.*;

public class run 
{
	public static void main (String[] args)
	{
		GUI_Menu menu= new GUI_Menu();
		menu.pack();
		menu.setVisible(true);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}