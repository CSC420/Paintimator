package pantimator;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Paintimator master;
	private State currentState;
	private RoundButton magic, normal, marker, crayon, circle, square;
	private RoundButton current, selected;
	private Clip button;
	
	public OptionsPanel(Paintimator p) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
		super();
		master = p;
		
		InputStream is = getClass().getResourceAsStream("sounds/button.wav");
		AudioInputStream ais = AudioSystem.getAudioInputStream(is);
        button = AudioSystem.getClip();
        button.open(ais);
		this.setLayout(new GridBagLayout());
		this.createAllButtons();
		this.addDrawOptions();

	}

	
	private void createAllButtons() throws IOException{
		//Magic 
		//BufferedImage buttonIcon = ImageIO.read(new File("images/magicwhite-Button.png"));
		java.net.URL buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
		  if (buttonIcon == null) {
			 System.out.println("Issue loading button in options Pane"); 
		  }
		magic = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
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
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
		normal = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
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
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
		crayon = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
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
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
		marker = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
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
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
		circle = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
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
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
		square = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = OptionsPanel.class.getResource("images/white-Button.png");
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
		this.removeAll();
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(2,2,2,2);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(normal,cc);
		
		cc.gridy = 1;
		this.add(marker,cc);
		
		cc.gridy = 2;
		this.add(crayon,cc);
		
		this.revalidate();
		this.repaint();
	}
	
	private void addPaintOptions(){
		this.removeAll();
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(2,2,2,2);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(magic, cc);
		
		this.revalidate();
		this.repaint();
	}
	

	private void addLineOptions(){
		this.removeAll();
		
		this.revalidate();
		this.repaint();
	}
	private void addSquareOptions(){
		this.removeAll();
		

		this.revalidate();
		this.repaint();
	}
	
	private void addTriOptions(){
		this.removeAll();
		

		this.revalidate();
		this.repaint();
	}
	private void addEraseOptions(){
		this.removeAll();
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(2,2,2,2);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(circle, cc);
		
		cc.gridy = 1;
		this.add(square, cc);
		
		
		this.revalidate();
		this.repaint();
	}
	private void addCircleOptions(){
		this.removeAll();
		

		this.revalidate();
		this.repaint();
	}
	private void addTextOptions(){
		this.removeAll();
		

		this.revalidate();
		this.repaint();
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
