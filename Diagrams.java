package zsdcfbhnm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.*;

import zsdcfbhnm.Animation.SliderChangeListener;

import java.lang.NumberFormatException;



public class Diagrams extends JFrame implements ActionListener {
	
	    JPanel panelDiagrams1;
	    JPanel panelDiagrams2;
	    JPanel panelDiagrams3;
	    JPanel panelDiagrams4;
	    JPanel panelDiagrams5;
	 //   JRadioButton radioButton1;
	  //  JRadioButton radioButton2;
	    JButton buttonON;
	    JLabel label;
	    JMenuBar menuBar;
	    JMenu menu;
	    JMenuItem menuItem1;
	    JMenuItem menuItem2;
	    JLabel labelLambda1;
	    JLabel labelLambda2;
	    JLabel labelAmplitude1;
	    JLabel labelAmplitude2;
	    JSpinner spinnerLambda1;
	    JSpinner spinnerLambda2;
	    JSpinner spinnerAmplitude1;
	    JSpinner spinnerAmplitude2; 
	    ButtonGroup buttonGroup=new ButtonGroup();
	    XYSeries series;
	    XYSeries series2;
	    XYSeries series3;
	    XYSeries series1a, series2a, series3a;
	    XYSeries series1b, series2b, series3b;
	    XYSeriesCollection dataset2;
	    XYSeriesCollection dataset;
	   int amplituda1,amplituda2;
	   double lambda1, lambda2;
	   String s1, s2;
	   SpinnerNumberModel model;
	   SpinnerNumberModel model2;
	   SpinnerNumberModel model3;
	   SpinnerNumberModel model4;
	   JFreeChart chart2;
	   JFreeChart chart;
	   ChartPanel panelDiagrams2a;
	   ChartPanel panelDiagrams2b;
	   
	   static final int SLIDER_MIN = 0;
	   static final int SLIDER_MAX = 100;
	   static final int SLIDER_INIT = 0;
	   
	   JSlider phase;
	   int phaseValue;

	   
	   KeyStroke escapeKeyStroke;
		

	    public  Diagrams()  throws HeadlessException{
		super("Wykresy");
		
		
		this.setSize(1000,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
	    Action escapeAction = new AbstractAction() {
	      public void actionPerformed(ActionEvent e) {
	        System.exit(0);
	      }
	    };
	   getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	       escapeKeyStroke, "ESCAPE");
	    getRootPane().getActionMap().put("ESCAPE", escapeAction);
	        
	        
	        
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		menuItem1 = new JMenuItem("Zapisz");
		menu.add(menuItem1);
		menuItem2 = new JMenuItem("Zamknięcie okna");
		menu.add(menuItem2);
		
		menuItem1.addActionListener(new ActionListener(){
			@Override
	    	public void actionPerformed(ActionEvent arg0)
	    	{
	    		saves();
	 
	    	}
			});
	 	
		
		menuItem2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}});
			
		panelDiagrams1=new JPanel();
		panelDiagrams2=new JPanel();
		panelDiagrams3=new JPanel();
		panelDiagrams4=new JPanel();
		panelDiagrams5=new JPanel();
		
		
		this.add(panelDiagrams1, BorderLayout.PAGE_START);
		this.add(panelDiagrams2, BorderLayout.CENTER);
		this.add(panelDiagrams3, BorderLayout.PAGE_END);
		this.add(panelDiagrams4, BorderLayout.LINE_START);
		this.add(panelDiagrams5, BorderLayout.LINE_END);
		
		
		
		

		panelDiagrams5.setLayout(new GridBagLayout()); 
		buttonON=new JButton("ON");
		panelDiagrams5.add(buttonON);
		
		
		 panelDiagrams4.setLayout(new GridLayout(3,1));
		 label = new JLabel("Wybierz:");
		 panelDiagrams4.add(label);
		// radioButton1 = new JRadioButton("interfernecja konstruktywna");
		// panelDiagrams4.add(radioButton1);
		// radioButton1.setActionCommand("0");
		// radioButton1.addActionListener(this);
		// radioButton2 = new JRadioButton("interfernecja destruktywna");
		// panelDiagrams4.add(radioButton2);
		// radioButton2.setActionCommand("1");
		// radioButton2.addActionListener(this);
		 
		// buttonGroup.add(radioButton1);
		// buttonGroup.add(radioButton2);
		 
		 phase = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
		 panelDiagrams4.add(phase);
		 phase.setPaintTicks(true);
		 phase.setPaintTrack(true);
		 phase.setMajorTickSpacing(25);
		 phase.setMinorTickSpacing(0);
		 phase.setPaintLabels(true);
		 
		 phase.addChangeListener(new ChangeListener() {
	            @Override
	            public void stateChanged(ChangeEvent e) {
	            	phaseValue=phase.getValue();
	            	series.clear();
		   			series2.clear();
		   			series3.clear();
	            	Funkcja1();
	            	
	            //	repaint();
	            	
	            }
		 });
		 
		 
		
		 panelDiagrams3.setLayout(new GridLayout(2,8));
		 labelLambda1 = new JLabel("λ1:");
		 panelDiagrams3.add(labelLambda1);
		 labelLambda1.setHorizontalAlignment(JLabel.CENTER);
		 labelLambda2 = new JLabel("λ2:");
		 panelDiagrams3.add(labelLambda2);
		 labelLambda2.setHorizontalAlignment(JLabel.CENTER);
		 labelAmplitude1 = new JLabel("A1:");
		 panelDiagrams3.add(labelAmplitude1);
		 labelAmplitude1.setHorizontalAlignment(JLabel.CENTER);
		 labelAmplitude2 = new JLabel("A2:");
		 panelDiagrams3.add(labelAmplitude2);
		 labelAmplitude2.setHorizontalAlignment(JLabel.CENTER);
		
		 
		 model = new SpinnerNumberModel(0,0,50,1);	
		 spinnerLambda1=new JSpinner(model);
		 panelDiagrams3.add(spinnerLambda1);  
		 
		 spinnerLambda1.addChangeListener(new ChangeListener() {
		            @Override
		            public void stateChanged(ChangeEvent e) {
		            	lambda1 = (int)spinnerLambda1.getValue();
		            	series.clear();
			   			series2.clear();
			   			series3.clear();
		            }
			 });
		
		 model2 = new SpinnerNumberModel(0,0,50,1);	
		 spinnerLambda2=new JSpinner(model2);
		 panelDiagrams3.add(spinnerLambda2);
			 
		spinnerLambda2.addChangeListener(new ChangeListener() {
		            @Override
		            public void stateChanged(ChangeEvent e) {
		            	lambda2 = (int)spinnerLambda2.getValue();
		            	series.clear();
			   			series2.clear();
			   			series3.clear();
		            }
			 });
		            
		 model3 = new SpinnerNumberModel(0,0,50,1);	           
		 spinnerAmplitude1 = new JSpinner(model3);
		 panelDiagrams3.add(spinnerAmplitude1);
		
		spinnerAmplitude1.addChangeListener(new ChangeListener() {
	            @Override
	            public void stateChanged(ChangeEvent e) {
	            	amplituda1 =(int) spinnerAmplitude1.getValue();
	            	series.clear();
		   			series2.clear();
		   			series3.clear();
	            }
		 });
		
		 model4 = new SpinnerNumberModel(0,0,50,1);
		 spinnerAmplitude2 = new JSpinner(model4);
		 panelDiagrams3.add(spinnerAmplitude2); 
		 
		spinnerAmplitude2.addChangeListener(new ChangeListener() {
	            @Override
	            public void stateChanged(ChangeEvent e) {
	            	amplituda2 =(int) spinnerAmplitude2.getValue();
	            	series.clear();
		   			series2.clear();
		   			series3.clear();
	            }
		 });
		 
		 series = new XYSeries("Pierwsza fala");
		 series2 = new XYSeries("Druga fala");
		 dataset2 = new XYSeriesCollection();
		 dataset2.addSeries(series);
		 dataset2.addSeries(series2);
		 chart = ChartFactory.createXYLineChart("","X","Y",dataset2,PlotOrientation.VERTICAL,true,true,false);
		 
		 panelDiagrams2a=new ChartPanel(chart);
		
		 
	     series3=new XYSeries("Seria 1");
		 dataset=new XYSeriesCollection();
			
		 dataset.addSeries(series3);
		 chart2=ChartFactory.createXYLineChart("","X","Y",dataset,PlotOrientation.VERTICAL,true,true,false);
		 panelDiagrams2b=new ChartPanel(chart2);
		 
		 panelDiagrams2.setLayout(new GridLayout(2,1));
		 panelDiagrams2.add(panelDiagrams2a);
		 panelDiagrams2.add(panelDiagrams2b);
		 
		 
		 
		 buttonON.addActionListener(new ButtonONListener());

		 
	}
	    
	    
	   
		   		@Override
	    		public void actionPerformed(ActionEvent e) {
	   			 if (e.getActionCommand()=="0")
	   				{
	   				series.clear();
	   				series2.clear();
	   				series3.clear();
	   				
	   				}
	   			 else if (e.getActionCommand()=="1")
	   			 {
	   				series.clear();
		   			series2.clear();
		   			series3.clear();
	   				
	   				
	   			 }
	   			 
	   	   }
	    		
	   
	   		
	   
	    		void saves()
	    		{
	    			JFileChooser chooser = new JFileChooser();
	    			chooser.showDialog(null, "Wybierz");
	    			Dimension size = panelDiagrams2.getSize();
	    			BufferedImage image = new BufferedImage(
	                        size.width, size.height 
	                                  , BufferedImage.TYPE_INT_RGB);
	    			Graphics2D g2 = image.createGraphics();
	    	        panelDiagrams2.paint(g2);
	    	        File file = chooser.getSelectedFile();
	    			try
	    			{
	    				ImageIO.write((BufferedImage) image, "png", new File(file.getAbsolutePath()));
	    			}
	    			catch(IOException e)
	    			{
	    				e.printStackTrace();
	    			}
	    		}
	    		
	    		
	    		
	    		void Funkcja1() {
	    			for(double i=0;i<10;i+=0.1) {
	    				double x=i*2*3.14/lambda1 +phaseValue;
	    				double sinx=amplituda1*Math.sin(x);
	    				series.add(i,sinx);
	    			}
		    			for(double i=0;i<10;i+=0.1) {
						double sinx=amplituda2*Math.sin(i*2*3.14/lambda2);
						series2.add(i,sinx);
					}
					for(double i=0;i<10;i+=0.1) {
						double sinx1=amplituda2*Math.sin(i*2*3.14/lambda2);
						double sinx2=amplituda1*Math.sin(i*2*3.14/lambda1 +phaseValue);
						double sinx=sinx1+sinx2;
						series3.add(i,sinx);
					}
	    		}
	    		
	    	/*	void Funkcja2() {
	    				for(double i=0;i<10;i+=0.1) {
	    					double sinx=amplituda1*Math.sin(i*2*3.14/lambda1);
	    					series.add(i,sinx);
	    				}
	    				for(double i=0;i<10;i+=0.1) {
	    					double sinx=amplituda2*Math.sin(i*2*3.14/lambda2);
	    					series2.add(i,sinx);
	    				}
	    				for(double i=0;i<10;i+=0.1) {
	    					double sinx1=amplituda1*Math.sin(i*2*3.14/lambda1);
	    					double sinx2=amplituda2*Math.sin(i*2*3.14/lambda2);
	    					double sinx=sinx1-sinx2;
	    					series3.add(i,sinx);
	    				}
	    		}*/

	public class ButtonONListener implements ActionListener{	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			Funkcja1();
		
		
		
		
			
	}
	}
	
	
	

	
	
}