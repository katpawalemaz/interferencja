package zsdcfbhnm;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Window  extends JFrame implements LineListener{

	JLabel label;
	JLabel label2;
	JButton buttonDiagrams;
	JButton buttonAnimation;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JPanel panel5;
    
    String audioFilePath = "";
	Clip audioClip = null;
	File audioFile = null;
	AudioInputStream audioStream = null;
	
	final String inFileName = "inter.wav";
	
	
    boolean playCompleted = false;
  
	public Window()  throws HeadlessException{
		super("Projekt");
		
		this.setSize(800,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
			    
			    play(inFileName);
			    
			    
			    
				
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
			 
			 
			 void play(String audioFilePath) {
			    	
			        try {
			            audioFile = new File(audioFilePath);
			            audioStream = AudioSystem.getAudioInputStream(audioFile);
			            AudioFormat format = audioStream.getFormat();
			            DataLine.Info info = new DataLine.Info(Clip.class, format);
			            audioClip = (Clip) AudioSystem.getLine(info);
			            audioClip.addLineListener(this);
			            audioClip.open(audioStream);
			            
			            /**
			             *  Play the audio clip in a new thread not to block the GUI.
			             *  It helps in this case, but is not really necessary. 
			             */
			            Thread thread = new Thread(new Runnable() {

			                public void run() {
			                	 audioClip.start();
			     	            while(!playCompleted){          	
			     	            	    	            	
			     	            }
			     	            audioClip.close();

			     	            try {
			 						audioStream.close();
			 					} catch (IOException e) {
			 				            e.printStackTrace();
			 					}
			                            
			                }
			                        
			            });
			            thread.start();
			            
			            

			        } catch (UnsupportedAudioFileException ex) {
			            System.out.println("The specified audio file is not supported.");
			            ex.printStackTrace();
			        } catch (LineUnavailableException ex) {
			            System.out.println("Audio line for playing back is unavailable.");
			            ex.printStackTrace();
			        } catch (IOException e1) {
			            System.out.println("Error playing the audio file.");
						e1.printStackTrace();
					} 
			         
			    }
			 
			
			 
			public static void main(String[] args) {
				
				
				
				
				SwingUtilities.invokeLater(
				 new Runnable(){
				public void run() {
					Window window=new Window();
					window.setVisible(true);
					 }
				}); }



			@Override
			public void update(LineEvent event) {
				// TODO Auto-generated method stub
				
				 LineEvent.Type type = event.getType();
		         
			        if (type == LineEvent.Type.START) {
			            System.out.println("Playback started.");
			             
			        } else if (type == LineEvent.Type.STOP) {
			            playCompleted = true;
			            System.out.println("Playback completed.");
			        }
				
			}
		
				

}
	    	
		
		    

