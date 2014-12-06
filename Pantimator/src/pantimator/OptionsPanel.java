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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Paintimator master;
	private State currentState;

	private RoundButton buttonIcon, pencil, crayon, marker, 
						th1, th2, th3, th4, th5,
						s1, s2, s3, s4, s5, s6, s7, s8, s9,
						c1, c2, c3, c4, c5, c6, c7, c8, c9,
						p1,p2,p3,p4,p5,p6,p7,p8,p9,
						b1,b2,b3,b4,b5,b6,b7,b8,b9,
						e1,e2,e3,e4,e5,e6,e7,e8,e9,
						l1,l2,l3,l4,l5,l6,l7,l8,l9,
						t1,t2,t3,t4,t5,t6,t7,t8,t9;
						
	
	private RoundButton current, selected, thSel, thCur;
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
		this.setState(State.DRAW);

	}
	
	private void createAllButtons(){
		buttonIcon = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/white-Button.png")));
		pencil = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whitePencil.png")));
		pencil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = pencil;
				updateListener();
				changeSelectedOption();
				master.setCanvasCursor(CanvasCursor.PENCIL);
				
			}
		});
		crayon = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteCrayon.png")));
		crayon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = crayon;
				updateListener();
				changeSelectedOption();
				master.setCanvasCursor(CanvasCursor.PENCIL);
			}
		});
		marker = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteMarker.png")));
		marker.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = marker;
				updateListener();
				changeSelectedOption();
				master.setCanvasCursor(CanvasCursor.PENCIL);
			}
		});

		s1 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareNormal.png")));
		s1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = s1;
				updateListener();
				changeSelectedOption();
				
			}
		});
		s2 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareFilled.png")));
		s2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = s2;
				updateListener();
				changeSelectedOption();
				
			}
		});
		s3 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareBrickLines.png")));
		s3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = s3;
				updateListener();
				changeSelectedOption();
				
			}
		});
		s4 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareZebra.png")));
		s4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = s4;
				updateListener();
				changeSelectedOption();
				
			}
		});
		s5 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteSquarestripes.png")));
		s5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = s5;
				updateListener();
				changeSelectedOption();
				
			}
		});
		s6 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareHearts.png")));
		s6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = s6;
				updateListener();
				changeSelectedOption();
				
			}
		});
		s7 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareChecker2.png")));
		s7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = s7;
				updateListener();
				changeSelectedOption();
				
			}
		});
		s8 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteSquareLep.png")));
		s8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = s8;
				updateListener();
				changeSelectedOption();
				
			}
		});
		s9 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteSquarelatch.png")));	
		s9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = s9;
				updateListener();
				changeSelectedOption();
				
			}
		});
		c1 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleNormal.png")));
		c1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = c1;
				updateListener();
				changeSelectedOption();
				
			}
		});
		c2 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleEmpty.png")));
		c2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = c2;
				updateListener();
				changeSelectedOption();
				
			}
		});
		c3 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleBrick.png")));
		c3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = c3;
				updateListener();
				changeSelectedOption();
				
			}
		});
		c4 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleChecker.png")));
		c4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = c4;
				updateListener();
				changeSelectedOption();
				
			}
		});
		c5 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleLep.png")));
		c5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = c5;
				updateListener();
				changeSelectedOption();
				
			}
		});
		c6 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteCirclePaws.png")));
		c6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = c6;
				updateListener();
				changeSelectedOption();
				
			}
		});
		c7 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteCircleStripe.png")));
		c7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = c7;
				updateListener();
				changeSelectedOption();
				
			}
		});
		e1 = s1;
		e1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = e1;
				updateListener();
				changeSelectedOption();
				
			}
		});
		e2 = c1;
		e2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = e2;
				updateListener();
				changeSelectedOption();
				
			}
		});
		e3 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteEraserSmSq.png")));
		e3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = e3;
				updateListener();
				changeSelectedOption();
				
			}
		});
		e4 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteEraserSmCir.png")));
		e3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = e3;
				updateListener();
				changeSelectedOption();
				
			}
		});
		e5 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteEraserDy.png")));
		e5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = e5;
				updateListener();
				changeSelectedOption();
				
			}
		});
		t1 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteTriNormal.png")));
		t1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = t1;
				updateListener();
				changeSelectedOption();
				
			}
		});
		t2 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteTriEmpty.png")));
		t2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = t2;
				updateListener();
				changeSelectedOption();
				
			}
		});
		p1 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whitePaint6.png")));
		p1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = p1;
				updateListener();
				changeSelectedOption();
				
			}
		});
		p2 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whitePaint2.png")));
		p2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = p2;
				updateListener();
				changeSelectedOption();
				
			}
		});
		p3 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whitePaint1.png")));
		p3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = p3;
				updateListener();
				changeSelectedOption();
				
			}
		});
		p4 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whitePaint3.png")));
		pencil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = pencil;
				updateListener();
				changeSelectedOption();
				
			}
		});
		p5 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whitePaint4.png")));
		p5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = p5;
				updateListener();
				changeSelectedOption();
				
			}
		});
		p6 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whitePaint5.png")));
		p6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				selected = p6;
				updateListener();
				changeSelectedOption();
				
			}
		});
		th1 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteth1.png")));
		th1.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		th1.setBorderPainted(false);
		th1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				master.setBrushSize(2);
				thSel = th1;
				updateListener();
				changeSelectedTh();
				
			}
		});
		th2 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteth2.png")));
		th2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		th2.setBorderPainted(false);
		th2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				master.setBrushSize(4);
				thSel = th2;
				updateListener();
				changeSelectedTh();
				
			}
		});
		th3 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteth3.png")));
		th3.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		th3.setBorderPainted(false);
		th3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				master.setBrushSize(9);
				thSel = th3;
				updateListener();
				changeSelectedTh();
				
			}
		});
		th4 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteth4.png")));
		th4.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		th4.setBorderPainted(false);
		th4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				master.setBrushSize(20);
				thSel = th4;
				updateListener();
				changeSelectedTh();
				
			}
		});
		th5 = new RoundButton(new ImageIcon(OptionsPanel.class.getResource("images/whiteth5.png")));
		th5.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		th5.setBorderPainted(false);
		th5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				master.setBrushSize(40);
				thSel = th5;
				updateListener();
				changeSelectedTh();
				
			}
		});
	
	}
	
	public void setState(State s){
		currentState = s;
		updateOptions();
		updateListener();
	}
	
	private void updateListener(){
		currentState.updateListener(this);
	}
	
	private void updateOptions(){
		currentState.updateOptions(this);
	}
	
	public void setButtonBackgroundColor(Color c){
//		one.setBackground(c);
//		two.setBackground(c);
//		three.setBackground(c);
//		four.setBackground(c);
//		five.setBackground(c);
//		six.setBackground(c);
//		seven.setBackground(c);
//		eight.setBackground(c);
//		nine.setBackground(c);
	}
	
	public void changeSelectedOption(){
		if(current != null){
			current.setSelected(false);
		}
		selected.setSelected(true);
		current = selected;

	}
	
	public void changeSelectedTh(){
		if(thCur != null){
			thCur.setSelected(false);
			thCur.setBorderPainted(false);
		}
		thSel.setSelected(true);
		thSel.setBorderPainted(true);
		thCur = thSel;

	}
	
	private void addDrawOptions(){
		this.removeAll();

		GridBagConstraints cc = new GridBagConstraints();
		cc.fill = GridBagConstraints.VERTICAL;
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(th1,cc);
		
		cc.gridy = 1;
		this.add(th2,cc);
		
		cc.gridy = 2;
		this.add(th3,cc);
		
		cc.gridy = 3;
		this.add(th4,cc);
		
		cc.gridy = 4;
		this.add(th5,cc);
		
		cc.gridy = 5;
		this.add(buttonIcon,cc);
		
		cc.gridy = 6;
		this.add(pencil,cc);
		
		cc.gridy = 7;
		this.add(marker,cc);
		
		cc.gridy = 8;
		this.add(crayon,cc);
		
		this.revalidate();
		this.repaint();
	}
	
	private void addPaintOptions(){
		this.removeAll();
		master.setBrushSize(3);
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(p1, cc);
		
		cc.gridx = 0;
		cc.gridy = 1;
		this.add(p2, cc);
		
		cc.gridx = 0;
		cc.gridy = 2;
		this.add(p3, cc);
		
		cc.gridx = 0;
		cc.gridy = 3;
		this.add(p4, cc);
		
		cc.gridx = 0;
		cc.gridy = 4;
		this.add(p5, cc);
		
		cc.gridx = 0;
		cc.gridy = 5;
		this.add(p6, cc);
		
		this.revalidate();
		this.repaint();
	}
	

	private void addLineOptions(){
		this.removeAll();

		GridBagConstraints cc = new GridBagConstraints();
		cc.fill = GridBagConstraints.VERTICAL;
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(th1,cc);
		
		cc.gridy = 1;
		this.add(th2,cc);
		
		cc.gridy = 2;
		this.add(th3,cc);
		
		cc.gridy = 3;
		this.add(th4,cc);
		
		cc.gridy = 4;
		this.add(th5,cc);
		
		cc.gridy = 5;
		this.add(buttonIcon,cc);
		
		
		this.revalidate();
		this.repaint();
	}
	private void addSquareOptions(){
		this.removeAll();
		master.setBrushSize(3);
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(s1, cc);
		
		cc.gridx = 0;
		cc.gridy = 1;
		this.add(s2, cc);
		
		cc.gridx = 0;
		cc.gridy = 2;
		this.add(s3, cc);
		
		cc.gridx = 0;
		cc.gridy = 3;
		this.add(s4, cc);
		
		cc.gridx = 0;
		cc.gridy = 4;
		this.add(s5, cc);
		
		cc.gridx = 0;
		cc.gridy = 5;
		this.add(s6, cc);
		
		cc.gridx = 0;
		cc.gridy = 6;
		this.add(s7, cc);
		
		cc.gridx = 0;
		cc.gridy = 7;
		this.add(s8, cc);
		
		cc.gridx = 0;
		cc.gridy = 8;
		this.add(s9, cc);

		this.revalidate();
		this.repaint();
	}
	
	private void addTriOptions(){
		this.removeAll();
		master.setBrushSize(3);
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(t1, cc);
		
		cc.gridx = 0;
		cc.gridy = 1;
		this.add(t2, cc);

		this.revalidate();
		this.repaint();
	}
	private void addEraseOptions(){
		this.removeAll();

		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(e1, cc);
		
		cc.gridx = 0;
		cc.gridy = 1;
		this.add(e2, cc);
		
		cc.gridx = 0;
		cc.gridy = 2;
		this.add(e3, cc);
		
		cc.gridx = 0;
		cc.gridy = 3;
		this.add(e4, cc);
		
		cc.gridx = 0;
		cc.gridy = 4;
		this.add(e5, cc);
		
		this.revalidate();
		this.repaint();
	}
	private void addCircleOptions(){
		this.removeAll();
		master.setBrushSize(3);
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(c1, cc);
		
		cc.gridx = 0;
		cc.gridy = 1;
		this.add(c2, cc);
		
		cc.gridx = 0;
		cc.gridy = 2;
		this.add(c3, cc);
		
		cc.gridx = 0;
		cc.gridy = 3;
		this.add(c4, cc);
		
		cc.gridx = 0;
		cc.gridy = 4;
		this.add(c5, cc);
		
		cc.gridx = 0;
		cc.gridy = 5;
		this.add(c6, cc);
		
		cc.gridx = 0;
		cc.gridy = 6;
		this.add(c7, cc);
		

		this.revalidate();
		this.repaint();
	}
	private void addTextOptions(){
		this.removeAll();

		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(buttonIcon, cc);

		this.revalidate();
		this.repaint();
	}
	
	private void addBucketOptions(){
		this.removeAll();
		GridBagConstraints cc = new GridBagConstraints();
		cc.insets = new Insets(3,0,3,0);
		cc.gridx = 0;
		cc.gridy = 0;
		this.add(buttonIcon, cc);

		this.revalidate();
		this.repaint();
	}

	
	
	 public static enum State {
	        LINE {
	        	public void updateOptions(OptionsPanel op){
	        		op.master.setCanvasCursor(CanvasCursor.LINE);
	        		op.addLineOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(1);
	        	}

	        },
	        DRAW{
	        	public void updateOptions(OptionsPanel op){
	                op.master.setCanvasCursor(CanvasCursor.DEFAULT);
	        		op.addDrawOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(2);
	        	}
	        },
	        PAINT{
	        	public void updateOptions(OptionsPanel op){
	        		op.master.setCanvasCursor(CanvasCursor.PAINT);
	        		op.addPaintOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(8);
	        	}
	        	
	        },
	        TRIANGLE{
	        	public void updateOptions(OptionsPanel op){
	        		op.master.setCanvasCursor(CanvasCursor.TRIANGLE);
	        		op.addTriOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(3);
	        	}

	        },
	        ERASE {
	        	public void updateOptions(OptionsPanel op){
	        		op.master.setCanvasCursor(CanvasCursor.ERASER);
	        		op.addEraseOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(4);
	        	}

	        },
	        CIRCLE{
	        	public void updateOptions(OptionsPanel op){
	        		op.master.setCanvasCursor(CanvasCursor.CIRCLE);
	        		op.addCircleOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(5);
	        	}

	        },
	        SQUARE{
	        	public void updateOptions(OptionsPanel op){
	        		op.master.setCanvasCursor(CanvasCursor.RECTANGLE);
	        		op.addSquareOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(6);
	        	}

	        },

	        TEXT{
	        	public void updateOptions(OptionsPanel op){
	        		op.master.setCanvasCursor(CanvasCursor.TEXT);
	        		op.addTextOptions();
	        	}
	        	public void updateListener(OptionsPanel op){
	        		op.master.setListenerState(7);
	        	}
	        },
	        BUCKET{
	        	public void updateOptions(OptionsPanel op){
	        		op.master.setCanvasCursor(CanvasCursor.BUCKET);
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
