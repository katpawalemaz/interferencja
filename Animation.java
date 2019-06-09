package zsdcfbhnm;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.*;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import javax.imageio.*;
import javax.swing.*;
import javax.swing.event.*;





public class Animation extends JFrame {
	
	static final int SLIDER_MIN = 5;
	static final int SLIDER_MAX = 50;
	static final int SLIDER_INIT = 5;
	
	JPanel panelAnimation1;
	AnimationPanel panelAnimation2;
	JPanel panelAnimation3;
	JPanel panelAnimation4;
	JPanel panelAnimation5;
	JButton buttonON;
	JButton buttonColorBackground;
	JButton buttonColorAnimation;
	JSlider slider;
	JLabel labelLambda;
	JLabel labelSlider;
	JSpinner spinner;
	SpinnerNumberModel model1;
	BufferedImage image;
	JTextField fieldSlider;
	JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuItem1;
    
    public static int lambda;
	
	
	public  Animation()  throws HeadlessException{
		super("Animacja");
	
		this.setSize(1000,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
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
		menuItem1 = new JMenuItem("Zamknięcie okna");
		
		
		menu.add(menuItem1);
		menuItem1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			dispose();
			}});


		
		
		panelAnimation1=new JPanel();
		panelAnimation2=new AnimationPanel();
		panelAnimation3=new JPanel();
		panelAnimation4=new JPanel();
		panelAnimation5=new JPanel();
		
		
		this.add(panelAnimation1, BorderLayout.PAGE_START);
		this.add(panelAnimation2, BorderLayout.CENTER);
		this.add(panelAnimation3, BorderLayout.PAGE_END);
		this.add(panelAnimation4, BorderLayout.LINE_START);
		this.add(panelAnimation5, BorderLayout.LINE_END);
		
		panelAnimation4.setLayout(new GridLayout(4,1));
		//BoxLayout boxLayout = new BoxLayout(panelAnimation4, BoxLayout.Y_AXIS); 
		//panelAnimation4.setLayout(boxLayout);
		labelLambda = new JLabel("λ:");
		panelAnimation4.add(labelLambda);
		labelLambda.setHorizontalAlignment(JLabel.CENTER);
		SpinnerNumberModel model1 = new SpinnerNumberModel(0.0, 0.0, 9.0, 1.0);  
		spinner = new JSpinner(model1);
		
		 spinner.addChangeListener(new ChangeListener() {
	            @Override
	            public void stateChanged(ChangeEvent e) {
	            	lambda = (int)spinner.getValue();
	            
	            }
		 });
		
		panelAnimation4.add(spinner);
		
		buttonColorBackground=new JButton("Wybierz kolor tła");
		panelAnimation4.add(buttonColorBackground);
		buttonColorBackground.addActionListener(new Colors());
		
		
		buttonColorAnimation=new JButton("Wybierz kolor animacji");
		
		ActionListener AnimationColorListener = new ActionListener()
	   	{
	    	public void actionPerformed(ActionEvent arg0)
	    	{
	    		Color newColor = JColorChooser.showDialog(null,"Choose Background Color",panelAnimation2.getBackground());
	    		panelAnimation2.changeColor(newColor);
	    	}
	 	};
	 	buttonColorAnimation.addActionListener(AnimationColorListener);
	 	
		
		
		panelAnimation4.add(buttonColorAnimation);
		
		
		
		labelSlider=new JLabel("Odległość: ");
		panelAnimation1.add(labelSlider);
		
		slider=new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
		panelAnimation1.add(slider);
		slider.addChangeListener(new SliderChangeListener());
		slider.setPaintTicks(true);
		slider.setPaintTrack(true);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(0);
		slider.setPaintLabels(true);
		
		fieldSlider = new JTextField("       ");
		 panelAnimation1.add(fieldSlider);
		
		//buttonON=new JButton("ON/OFF");
		//panelAnimation5.add(buttonON);
		//URL resource=getClass().getResource("java.picture/fototapety-fale-w-wodzie.JPG");
		
	
		
		
		panelAnimation2.setBackground(Color.white);
		
		ExecutorService exec = Executors.newFixedThreadPool(1);
		exec.execute(panelAnimation2);
		exec.shutdown();

		
		
	    
		//image = new ImageIcon(new URL("fototapety-fale-w-wodzie.jpg")).getImage();
		
	}	
	
	public class SliderChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent arg0) {
			String value = String.format("%d", slider.getValue());
			fieldSlider.setText(value);
	  }
	 }

	public class Colors implements ActionListener{	
	public void actionPerformed(ActionEvent e) {
		Color newColor = JColorChooser.showDialog(null,"Choose Background Color",panelAnimation2.getBackground());
		panelAnimation2.setBackground(newColor);
        }	
	}
	
	
}
	
	
	
	//public class ImageComponent  {
		//public void paintComponent(Graphics g) {
	    //    g.drawImage(image, 0, 0, null);
	    //}
	//}
	

