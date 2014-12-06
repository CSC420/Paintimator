package pantimator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
	private RoundButton one, two, three, four, five, six,
						seven, eight, nine;
	private ImageIcon buttonIcon, pencil, crayon, marker, 
						magic, th1, th2, th3, th4,
						s1, s2, s3, s4, s5, s6, s7, s8, s9,
						c1, c2, c3, c4, c5, c6, c7, c8, c9,
						p1,p2,p3,p4,p5,p6,p7,p8,p9,
						b1,b2,b3,b4,b5,b6,b7,b8,b9,
						e1,e2,e3,e4,e5,e6,e7,e8,e9,
						l1,l2,l3,l4,l5,l6,l7,l8,l9,
						t1,t2,t3,t4,t5,t6,t7,t8,t9;
						
	
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
		this.loadButtonImages();
		this.createAllButtons();
		this.addDrawOptions();

	}
	
	private void loadButtonImages(){
		buttonIcon = new ImageIcon(OptionsPanel.class.getResource("images/white-Button.png"));
		pencil = new ImageIcon(OptionsPanel.class.getResource("images/whitePencil.png"));
		crayon = new ImageIcon(OptionsPanel.class.getResource("images/whiteCrayon.png"));
		marker = new ImageIcon(OptionsPanel.class.getResource("images/whiteMarker.png"));
		magic = new ImageIcon(OptionsPanel.class.getResource("images/white-Button.png"));
		s1 = new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareNormal.png"));
		s2 = new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareFilled.png"));
		s3 = new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareBrickLines.png"));
		s4 = new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareZebra.png"));
		s5 = new ImageIcon(OptionsPanel.class.getResource("images/whiteSquarestripes.png"));
		s6 = new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareHearts.png"));
		s7 = new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareChecker2.png"));
		s8 = new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareLep.png"));
		s9 = new ImageIcon(OptionsPanel.class.getResource("images/whiteSquarelatch.png"));	
		c1 = new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleNormal.png"));
		c2 = new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleEmpty.png"));
		c3 = new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleBrick.png"));
		c4 = new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleChecker.png"));
		c5 = new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleLep.png"));
		c6 = new ImageIcon(OptionsPanel.class.getResource("images/whiteCirclePaws.png"));
		c7 = new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleStripe.png"));
		e1 = s1;
		e2 = c1;
		e3 = new ImageIcon(OptionsPanel.class.getResource("images/whiteEraserSmSq.png"));
		e4 = new ImageIcon(OptionsPanel.class.getResource("images/whiteEraserSmCir.png"));
		e5 = new ImageIcon(OptionsPanel.class.getResource("images/whiteEraserDy.png"));
		t1 = new ImageIcon(OptionsPanel.class.getResource("images/whiteTriNormal.png"));
		t2 = new ImageIcon(OptionsPanel.class.getResource("images/whiteTriEmpty.png"));
		p1 = new ImageIcon(OptionsPanel.class.getResource("images/whitePaint6.png"));
		p2 = new ImageIcon(OptionsPanel.class.getResource("images/whitePaint2.png"));
		p3 = new ImageIcon(OptionsPanel.class.getResource("images/whitePaint1.png"));
		p4 = new ImageIcon(OptionsPanel.class.getResource("images/whitePaint3.png"));
		p5 = new ImageIcon(OptionsPanel.class.getResource("images/whitePaint4.png"));
		p6 = new ImageIcon(OptionsPanel.class.getResource("images/whitePaint5.png"));
	
	
	}

	
	private void createAllButtons() throws IOException{

		one = new RoundButton(buttonIcon); 
		one.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = one;
				updateListener();
				changeSelectedOption();
				
			}
		});
		
		two = new RoundButton(buttonIcon); 
		two.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = two;
				changeSelectedOption();
				updateListener();
			}
		});
		
		three = new RoundButton(buttonIcon); 
		three.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = three;
				changeSelectedOption();
				updateListener();
			}
		});
		
		four = new RoundButton(buttonIcon); 
		four.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = four;
				changeSelectedOption();
				updateListener();
			}
		});
		
		five = new RoundButton(buttonIcon); 
		five.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = five;
				changeSelectedOption();
				updateListener();
			}
		});
		
		six = new RoundButton(buttonIcon); 
		six.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = six;
				changeSelectedOption();
				updateListener();
			}
		});
		
		seven = new RoundButton(buttonIcon); 
		seven.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = seven;
				changeSelectedOption();
				updateListener();
			}
		});
		
		eight = new RoundButton(buttonIcon); 
		eight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = eight;
				changeSelectedOption();
				updateListener();
			}
		});
		
		nine = new RoundButton(buttonIcon); 
		nine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = nine;
				changeSelectedOption();
				updateListener();
			}
		});

	}
	
	public void setState(State s){
		currentState = s;
		this.updateOptions();
	}
	
	private void updateListener(){
		currentState.updateListener(this);
	}
	
	private void updateOptions(){
		currentState.updateOptions(this);
	}
	
	public void setButtonBackgroundColor(Color c){
		one.setBackground(c);
		two.setBackground(c);
		three.setBackground(c);
		four.setBackground(c);
		five.setBackground(c);
		six.setBackground(c);
		seven.setBackground(c);
		eight.setBackground(c);
		nine.setBackground(c);
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
		one.setIcon(pencil);
		one.setOpaque(false);
		two.setIcon(marker);
		two.setOpaque(false);
		three.setIcon(crayon );
		three.setOpaque(false);
		GridBagConstraints cc = new GridBagConstraints();
		cc.fill = GridBagConstraints.VERTICAL;
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(one,cc);
		
		cc.gridy = 1;
		this.add(two,cc);
		
		cc.gridy = 2;
		this.add(three,cc);
		
		this.revalidate();
		this.repaint();
	}
	
	private void addPaintOptions(){
		this.removeAll();
		one.setIcon(p1);
		one.setOpaque(false);
		two.setIcon(p2);
		one.setOpaque(false);
		three.setIcon(p3);
		three.setOpaque(false);
		four.setIcon(p4);
		four.setOpaque(false);
		five.setIcon(p5);
		five.setOpaque(false);
		six.setIcon(p6);
		six.setOpaque(false);

		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(one, cc);
		
		cc.gridx = 0;
		cc.gridy = 1;
		this.add(two, cc);
		
		cc.gridx = 0;
		cc.gridy = 2;
		this.add(three, cc);
		
		cc.gridx = 0;
		cc.gridy = 3;
		this.add(four, cc);
		
		cc.gridx = 0;
		cc.gridy = 4;
		this.add(five, cc);
		
		cc.gridx = 0;
		cc.gridy = 5;
		this.add(six, cc);
		
		this.revalidate();
		this.repaint();
	}
	

	private void addLineOptions(){
		this.removeAll();
		one.setIcon(buttonIcon);
		one.setOpaque(false);
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(one, cc);
		this.revalidate();
		this.repaint();
	}
	private void addSquareOptions(){
		this.removeAll();
		one.setIcon(s1);
		one.setOpaque(false);
		two.setIcon(s2);
		two.setOpaque(true);
		three.setIcon(s3);
		three.setOpaque(true);
		four.setIcon(s4);
		four.setOpaque(false);
		five.setIcon(s5);
		five.setOpaque(false);
		six.setIcon(s6);
		six.setOpaque(false);
		seven.setIcon(s7);
		seven.setOpaque(false);
		eight.setIcon(s8);
		eight.setOpaque(true);
		nine.setIcon(s9);
		nine.setOpaque(false);
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(one, cc);
		
		cc.gridx = 0;
		cc.gridy = 1;
		this.add(two, cc);
		
		cc.gridx = 0;
		cc.gridy = 2;
		this.add(three, cc);
		
		cc.gridx = 0;
		cc.gridy = 3;
		this.add(four, cc);
		
		cc.gridx = 0;
		cc.gridy = 4;
		this.add(five, cc);
		
		cc.gridx = 0;
		cc.gridy = 5;
		this.add(six, cc);
		
		cc.gridx = 0;
		cc.gridy = 6;
		this.add(seven, cc);
		
		cc.gridx = 0;
		cc.gridy = 7;
		this.add(eight, cc);
		
		cc.gridx = 0;
		cc.gridy = 8;
		this.add(nine, cc);

		this.revalidate();
		this.repaint();
	}
	
	private void addTriOptions(){
		this.removeAll();
		one.setIcon(t1);
		one.setOpaque(false);
		two.setIcon(t2);
		two.setOpaque(true);

		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(one, cc);
		
		cc.gridx = 0;
		cc.gridy = 1;
		this.add(two, cc);

		this.revalidate();
		this.repaint();
	}
	private void addEraseOptions(){
		this.removeAll();
		one.setIcon(e1);
		one.setOpaque(false);
		two.setIcon(e2);
		two.setOpaque(false);
		three.setIcon(e3);
		three.setOpaque(false);
		four.setIcon(e4);
		four.setOpaque(false);
		five.setIcon(e5);
		five.setOpaque(false);

		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(one, cc);
		
		cc.gridx = 0;
		cc.gridy = 1;
		this.add(two, cc);
		
		cc.gridx = 0;
		cc.gridy = 2;
		this.add(three, cc);
		
		cc.gridx = 0;
		cc.gridy = 3;
		this.add(four, cc);
		
		cc.gridx = 0;
		cc.gridy = 4;
		this.add(five, cc);
		
		this.revalidate();
		this.repaint();
	}
	private void addCircleOptions(){
		this.removeAll();
		one.setIcon(c1);
		one.setOpaque(false);
		two.setIcon(c2);
		two.setOpaque(true);
		three.setIcon(c3);
		three.setOpaque(false);
		four.setIcon(c4);
		four.setOpaque(false);
		five.setIcon(c5);
		five.setOpaque(false);
		six.setIcon(c6);
		six.setOpaque(false);
		seven.setIcon(c7);
		seven.setOpaque(false);
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(one, cc);
		
		cc.gridx = 0;
		cc.gridy = 1;
		this.add(two, cc);
		
		cc.gridx = 0;
		cc.gridy = 2;
		this.add(three, cc);
		
		cc.gridx = 0;
		cc.gridy = 3;
		this.add(four, cc);
		
		cc.gridx = 0;
		cc.gridy = 4;
		this.add(five, cc);
		
		cc.gridx = 0;
		cc.gridy = 5;
		this.add(six, cc);
		
		cc.gridx = 0;
		cc.gridy = 6;
		this.add(seven, cc);
		

		this.revalidate();
		this.repaint();
	}
	private void addTextOptions(){
		this.removeAll();
		one.setIcon(buttonIcon);
		one.setOpaque(false);
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(one, cc);

		this.revalidate();
		this.repaint();
	}
	
	private void addBucketOptions(){
		this.removeAll();
		one.setIcon(buttonIcon);
		one.setOpaque(false);
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(one, cc);

		this.revalidate();
		this.repaint();
	}

	
	
	 public static enum State {
	        LINE {
	        	public void updateOptions(OptionsPanel op){
	        		op.addLineOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(1);
	        	}

	        },
	        DRAW{
	        	public void updateOptions(OptionsPanel op){
	        		op.addDrawOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(2);
	        	}
	        },
	        PAINT{
	        	public void updateOptions(OptionsPanel op){
	        		op.addPaintOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(8);
	        	}
	        	
	        },
	        TRIANGLE{
	        	public void updateOptions(OptionsPanel op){
	        		op.addTriOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(3);
	        	}

	        },
	        ERASE {
	        	public void updateOptions(OptionsPanel op){
	        		op.addEraseOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(4);
	        	}

	        },
	        CIRCLE{
	        	public void updateOptions(OptionsPanel op){
	        		op.addCircleOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(5);
	        	}

	        },
	        SQUARE{
	        	public void updateOptions(OptionsPanel op){
	        		op.addSquareOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(6);
	        	}

	        },

	        TEXT{
	        	public void updateOptions(OptionsPanel op){
	        		op.addTextOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(7);
	        	}
	        },
	        BUCKET{
	        	public void updateOptions(OptionsPanel op){
	        		op.addBucketOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(9);
	        	}
	        },
	        
	        NONE;

	        //these are the possible methods each state can have
	        public void updateOptions(OptionsPanel op){}
	        public void updateListener(OptionsPanel op){}
	    }
	
	
	
}
