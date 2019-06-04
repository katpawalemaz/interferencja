package zsdcfbhnm;


import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Window  extends JFrame {

	JLabel label;
	JLabel label2;
	JButton buttonDiagrams;
	JButton buttonAnimation;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JPanel panel5;
  
	public Window()  throws HeadlessException{
		super("Projekt");
		
		this.setSize(800,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		
		
		
		panel1.setBackground(Color.white);
		panel2.setBackground(Color.white);
		panel3.setBackground(Color.white);
		

		this.add(panel1, BorderLayout.CENTER);
		this.add(panel2, BorderLayout.LINE_START);
		this.add(panel3, BorderLayout.LINE_END);
		
		panel1.setLayout(new GridLayout(1,3));
		panel2.setLayout(new GridLayout(1,3));
		panel3.setLayout(new GridLayout(1,3));
		
		buttonAnimation=new JButton("Animacje");
		buttonAnimation.setBackground(new Color(109,192,255));
		
		label = new JLabel("              Odkryj Interfernecje na nowo!");
		panel1.add(label);
		label.setForeground(new Color(13,85,255));
		label.setFont(new Font("Baskerville Old Face", Font.ITALIC, 40));
		
		panel1.addMouseListener(new MotionListener());
	
		
		buttonDiagrams=new JButton("Wykresy");
		buttonDiagrams.setBackground(new Color(109,192,255));
		buttonDiagrams.addActionListener(new ButtonListenerDiagrams());
		buttonAnimation.addActionListener(new ButtonListenerAnimation());
		
	
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
	    Action escapeAction = new AbstractAction() {
	      public void actionPerformed(ActionEvent e) {
	        System.exit(0);
	      }
	    };
	    getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	        escapeKeyStroke, "ESCAPE");
	    getRootPane().getActionMap().put("ESCAPE", escapeAction); 
		
		
	}
	
	public class MotionListener implements MouseListener {
			@Override
			public void mouseClicked(MouseEvent event) {
				label.setText("                Interfernecja");
	        	panel3.add(buttonAnimation);
			    panel2.add(buttonDiagrams);
				
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	       
	    };
	    
	    public class ButtonListenerDiagrams extends Diagrams implements ActionListener{

	    	@Override
			public void actionPerformed(ActionEvent arg0) {
	    		Diagrams  frame=new Diagrams();
				frame.setVisible(true);
				
	    		}
				
			
		 };
		 
		 public class ButtonListenerAnimation extends Animation implements ActionListener{

		    	@Override
				public void actionPerformed(ActionEvent arg0) {
		    		Animation  frame=new Animation();
					frame.setVisible(true);
		    		}
					
				
			 };
			 
			
			 
				public static void main(String[] args) {
					SwingUtilities.invokeLater(
					 new Runnable(){
					public void run() {
						Window window=new Window();
						window.setVisible(true);
					 }
					}); }
		
				

}
	    	
		
		    

