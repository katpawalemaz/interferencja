package zsdcfbhnm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class AnimationPanel extends JPanel implements Runnable
{
	int n=0;
	int []u=new int[40];
	int f=-1600;
	int z=0;
	double y;
	Color color;
	int xPos=0, yPos=0;
	
	ArrayList<Integer> xNumbers;
	ArrayList<Integer> yNumbers;
	
	
	
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
			int ii;
			ii=0;
			
			
		
			for(ii=0;ii<39;ii++)
				u[ii]=40+40*ii+f;
			f++;
			
				

			
			if(n==1600){
				n=0;
				f=-1600;}
	
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			repaint(); //repaint domyslnie odpali sie w EDT
		}
	}
	
	public void list(){
		
		while(true){
			xNumbers.add(n);
			
			y=Math.sin(n*180/3.14);
			double yy=10*y;
			
			int y1=(int) yy;
			
			
			yNumbers.add(y1);
			
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			
		}
		
		
	}
	
	
		
		
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			
			
			Graphics2D g2 = (Graphics2D) g;
		    g2.setStroke(new BasicStroke(7));
		   
			g2.setColor(color);
			
			g2.drawOval(200, 50, 5, 5);
			g2.drawOval(400, 50, 5, 5);
			
			g2.drawOval(200-n/2, 50-n/2, n, n);
			g2.drawOval(400-n/2, 50-n/2, n, n);
			
			

			
			
			for(int ii=0;ii<39;ii++){
				g2.drawOval(200-u[ii]/2, 50-u[ii]/2, u[ii], u[ii]);
				g2.drawOval(400-u[ii]/2, 50-u[ii]/2, u[ii], u[ii]);
				
			}
				
			}
			
			
}
			
			
			
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		

		
		
		

	
	
