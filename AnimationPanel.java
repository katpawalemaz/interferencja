package zsdcfbhnm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class AnimationPanel extends JPanel implements Runnable
{
	int n=0;
	Color color;
	AnimationPanel()
	
	{
			super();
			setPreferredSize(new Dimension(300, 300));
	
	}
	
	public void changeColor(Color k)
	{
		color=k;
	}
	
	
	public void run() {

		while (true) {
			n++;
		
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			repaint(); //repaint domyslnie odpali sie w EDT
		}
	}
		
		
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			//g.setColor(Color.white);
			//g.fillRect(0, 0, getWidth(), getHeight());
			Graphics2D g2 = (Graphics2D) g;
		    g2.setStroke(new BasicStroke(7));
		   
			g2.setColor(color);
			
			g2.drawOval(200-n/2, 50-n/2, n, n);
			g2.drawOval(400-n/2, 50-n/2, n, n);
			
			
			
			
			
			
			
		

		}
		
		

	
	
	
}