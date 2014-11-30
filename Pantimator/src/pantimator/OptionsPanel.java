package pantimator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionsPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Paintimator master;
	private State currentState;
	private RoundButton magic, normal, marker, crayon, circle, square;
	private JPanel thicknessPanel;
	private JPanel opPanel;
	private RoundButton current, selected;
	private Dimension opSizeNoThick;
	private Dimension opSizeThick;
	private Clip button;
	
	public OptionsPanel(Paintimator p) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
		super();
		master = p;
		
		InputStream is = getClass().getResourceAsStream("sounds/button.wav");
		AudioInputStream ais = AudioSystem.getAudioInputStream(is);
        button = AudioSystem.getClip();
        button.open(ais);
		
		thicknessPanel = new JPanel(new GridBagLayout());
		//thicknessPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		thicknessPanel.setPreferredSize(new Dimension(100, 250));
		thicknessPanel.setOpaque(false);
		opPanel = new JPanel(new GridBagLayout());
		//opPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		opSizeThick = new Dimension(100, 500);
		opSizeNoThick = new Dimension(100, 750);
		opPanel.setOpaque(false);
		this.createThicknessPanel();
		this.createAllButtons();

		this.add(thicknessPanel);
		thicknessPanel.setVisible(false);
		this.add(opPanel);
		opPanel.setVisible(false);
	
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	}

	private void createThicknessPanel() throws IOException{
		//thickness
		//BufferedImage thicknessIcon = ImageIO.read(new File("images/thickness.png"));
		java.net.URL thicknessIcon = OptionsPanel.class.getResource("images/thickness.png");
		  if (thicknessIcon == null) {
			 System.out.println("Issue loading thickness button in Options Pane"); 
			 System.exit(-1);
		  }
		JLabel label = new JLabel(new ImageIcon(thicknessIcon));
		JLabel label2 = new JLabel(new ImageIcon(thicknessIcon));
		JLabel label3 = new JLabel(new ImageIcon(thicknessIcon));
		JLabel label4 = new JLabel(new ImageIcon(thicknessIcon));
		
		RecButton one = new RecButton(1);
		one.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				master.setBrushSize(1);
				System.out.println("clicked");
			}
		});
		one.setOpaque(true);
		
		RecButton two = new RecButton(5);
		two.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				master.setBrushSize(3);
				System.out.println("clicked");
			}
		});
		two.setOpaque(true);
		
		RecButton three = new RecButton(10);
		three.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				master.setBrushSize(25);
				System.out.println("clicked");
			}
		});
		three.setOpaque(true);
		
		RecButton four = new RecButton(15);
		four.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				master.setBrushSize(80);
				System.out.println("clicked");
			}
		});
		four.setOpaque(true);

		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.fill = GridBagConstraints.VERTICAL;
		cc.gridy = 0;
		cc.anchor = GridBagConstraints.LAST_LINE_END;
		thicknessPanel.add(label, cc);
		cc.gridy = 1;
		cc.anchor = GridBagConstraints.PAGE_START;
		thicknessPanel.add(one,cc);
		
		cc.insets = new Insets(5,0,0,0);
		cc.gridy = 2;
		cc.anchor = GridBagConstraints.LAST_LINE_END;
		thicknessPanel.add(label2, cc);
		cc.insets = new Insets(0,0,0,0);
		cc.gridy = 3;
		cc.anchor = GridBagConstraints.PAGE_START;
		thicknessPanel.add(two,cc);
		
		cc.insets = new Insets(5,0,0,0);
		cc.gridy = 4;
		cc.anchor = GridBagConstraints.LAST_LINE_END;
		thicknessPanel.add(label3, cc);
		cc.insets = new Insets(0,0,0,0);
		cc.gridy = 5;
		cc.anchor = GridBagConstraints.PAGE_START;
		thicknessPanel.add(three,cc);
		
		cc.insets = new Insets(5,0,0,0);
		cc.gridy = 6;
		cc.anchor = GridBagConstraints.LAST_LINE_END;
		thicknessPanel.add(label4, cc);
		cc.insets = new Insets(0,0,0,0);
		cc.gridy = 7;
		cc.anchor = GridBagConstraints.PAGE_START;
		thicknessPanel.add(four,cc);
		

	}
	
	private void createAllButtons() throws IOException{
		//Magic 
		//BufferedImage buttonIcon = ImageIO.read(new File("images/magicwhite-Button.png"));
		java.net.URL buttonIcon = OptionsPanel.class.getResource("images/magicwhite-Button.png");
		  if (buttonIcon == null) {
			 System.out.println("Issue loading play button in Animation Pane"); 
			 System.exit(-1);
		  }
		magic = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/green-Button.png");
		magic.setSelectedIcon(new ImageIcon(buttonIcon));
		magic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = magic;
				changeSelectedOption();
				master.setListenerState(8);
			}
		});
		
		//Pencil
		buttonIcon = OptionsPanel.class.getResource("images/normalwhite-Button.png");
		normal = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/green-Button.png");
		normal.setSelectedIcon(new ImageIcon(buttonIcon));
		normal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = normal;
				changeSelectedOption();
				master.setListenerState(2);
			}
		});
		
		//Crayon
		buttonIcon = OptionsPanel.class.getResource("images/crayonwhite-Button.png");
		crayon = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/green-Button.png");
		crayon.setSelectedIcon(new ImageIcon(buttonIcon));
		crayon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = crayon;
				changeSelectedOption();
				master.setListenerState(2);
			}
		});
		//Marker
		buttonIcon = OptionsPanel.class.getResource("images/markerwhite-Button.png");
		marker = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/green-Button.png");
		marker.setSelectedIcon(new ImageIcon(buttonIcon));
		marker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = marker;
				changeSelectedOption();
				master.setListenerState(2);
			}
		});
		
		//circle
		buttonIcon = OptionsPanel.class.getResource("images/cirwhite-Button.png");
		circle = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/green-Button.png");
		circle.setSelectedIcon(new ImageIcon(buttonIcon));
		circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = circle;
				changeSelectedOption();
				//master.setListenerState(2);
			}
		});


		//square
		buttonIcon = OptionsPanel.class.getResource("images/squarewhite-Button.png");
		square = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/green-Button.png");
		square.setSelectedIcon(new ImageIcon(buttonIcon));
		square.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = square;
				changeSelectedOption();
				//master.setListenerState(2);
			}
		});

	}
	
	public void setState(State s){
		currentState = s;
		this.updateOptions();
	}
	
	private void updateOptions(){
		currentState.updateOptions(this);
	}
	
	public void changeSelectedOption(){
		if(current != null){
			current.setSelected(false);
		}
		selected.setSelected(true);
		current = selected;

	}
	
	private void addDrawOptions(){
		opPanel.removeAll();
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(2,2,2,2);
		cc.gridx = 0;
		cc.gridy = 0;
		opPanel.add(normal,cc);
		
		cc.gridy = 1;
		opPanel.add(marker,cc);
		
		cc.gridy = 2;
		opPanel.add(crayon,cc);
		
		
		thicknessPanel.setVisible(true);
		opPanel.setPreferredSize(opSizeThick);
		opPanel.setVisible(true);
		this.revalidate();
		opPanel.revalidate();
		this.repaint();
		opPanel.repaint();
	}
	
	private void addPaintOptions(){
		opPanel.removeAll();
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(2,2,2,2);
		cc.gridx = 0;
		cc.gridy = 0;
		opPanel.add(magic, cc);
		
		
		thicknessPanel.setVisible(false);
		//opPanel.setPreferredSize(opSizeNoThick);
		opPanel.setVisible(true);
		this.revalidate();
		opPanel.revalidate();
		this.repaint();
		opPanel.repaint();
	}
	

	private void addLineOptions(){
		opPanel.removeAll();
		
		
		
		thicknessPanel.setVisible(true);
		opPanel.setPreferredSize(opSizeThick);
		opPanel.setVisible(true);
		this.revalidate();
		opPanel.revalidate();
		this.repaint();
		opPanel.repaint();
	}
	private void addSquareOptions(){
		opPanel.removeAll();
		
		
		
		thicknessPanel.setVisible(false);
		opPanel.setPreferredSize(opSizeNoThick);
		opPanel.setVisible(true);
		this.revalidate();
		opPanel.revalidate();
		this.repaint();
		opPanel.repaint();
	}
	
	private void addTriOptions(){
		opPanel.removeAll();
		
		
		
		thicknessPanel.setVisible(false);
		opPanel.setPreferredSize(opSizeNoThick);
		opPanel.setVisible(true);
		this.revalidate();
		opPanel.revalidate();
		this.repaint();
		opPanel.repaint();
	}
	private void addEraseOptions(){
		opPanel.removeAll();
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(2,2,2,2);
		cc.gridx = 0;
		cc.gridy = 0;
		opPanel.add(circle, cc);
		
		cc.gridy = 1;
		opPanel.add(square, cc);
		
		
		thicknessPanel.setVisible(true);
		opPanel.setPreferredSize(opSizeThick);
		opPanel.setVisible(true);
		this.revalidate();
		opPanel.revalidate();
		this.repaint();
		opPanel.repaint();
	}
	private void addCircleOptions(){
		opPanel.removeAll();
		
		
		
		thicknessPanel.setVisible(false);
		opPanel.setPreferredSize(opSizeNoThick);
		opPanel.setVisible(true);
		this.revalidate();
		opPanel.revalidate();
		this.repaint();
		opPanel.repaint();
	}
	private void addTextOptions(){
		opPanel.removeAll();
		
		
		
		thicknessPanel.setVisible(false);
		opPanel.setPreferredSize(opSizeNoThick);
		opPanel.setVisible(true);
		this.revalidate();
		opPanel.revalidate();
		this.repaint();
		opPanel.repaint();
	}

	
	
	 public static enum State {
	        LINE {
	        	public void updateOptions(OptionsPanel op){
	        		op.addLineOptions();
	        	}

	        },
	        DRAW{
	        	public void updateOptions(OptionsPanel op){
	        		op.addDrawOptions();
	        	}
	        },
	        PAINT{
	        	public void updateOptions(OptionsPanel op){
	        		op.addPaintOptions();
	        	}
	        	
	        },
	        TRIANGLE{
	        	public void updateOptions(OptionsPanel op){
	        		op.addTriOptions();
	        	}

	        },
	        ERASE {
	        	public void updateOptions(OptionsPanel op){
	        		op.addEraseOptions();
	        	}

	        },
	        CIRCLE{
	        	public void updateOptions(OptionsPanel op){
	        		op.addCircleOptions();
	        	}

	        },
	        SQUARE{
	        	public void updateOptions(OptionsPanel op){
	        		op.addSquareOptions();
	        	}

	        },

	        TEXT{
	        	public void updateOptions(OptionsPanel op){
	        		op.addTextOptions();
	        	}
	        },
	        
	        NONE;

	        //these are the possible methods each state can have
	        public void updateOptions(OptionsPanel op){}

	    }
	
	
	
}
