package pantimator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ToolPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Paintimator master;
	private OptionsPanel op;
	private RoundButton line, draw, text, erase, circle, square, 
		triangle, paint, undo, redo, bucket, stamp;
	private RoundButton selectedButton;
	private RoundButton newSelectedButton;
<<<<<<< HEAD
	private Clip button;
	private Clip undoSound;
	private Clip redoSound;


	public ToolPanel(Paintimator p, OptionsPanel o) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
=======


	public ToolPanel(Paintimator p, OptionsPanel o) throws IOException{
>>>>>>> Pres
		super(new GridBagLayout());
		master = p;
		op = o;

<<<<<<< HEAD
		//AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/button.wav"));
		InputStream is = getClass().getResourceAsStream("sounds/button.wav");
		AudioInputStream ais = AudioSystem.getAudioInputStream(is);
        button = AudioSystem.getClip();
        button.open(ais);
        
        is = getClass().getResourceAsStream("sounds/undo.wav");
		ais = AudioSystem.getAudioInputStream(is);
        undoSound = AudioSystem.getClip();
        undoSound.open(ais);
        
        is = getClass().getResourceAsStream("sounds/redo.wav");
		ais = AudioSystem.getAudioInputStream(is);
        redoSound = AudioSystem.getClip();
        redoSound.open(ais);
        
		
		java.net.URL buttonIcon = ToolPanel.class.getResource("images/undowhiteButton.png");
		  if (buttonIcon == null) {
			 System.out.println("Issue loading tool buttons in Tool Panel"); 
			 System.exit(-1);
		  }
		undo = new RoundButton(new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/undogreenButton.png");
=======
		BufferedImage buttonIcon = ImageIO.read(new File("images/undowhiteButton.png"));
		undo = new RoundButton(new ImageIcon(buttonIcon));
		buttonIcon = ImageIO.read(new File("images/undogreenButton.png"));
>>>>>>> Pres
		undo.setPressedIcon(new ImageIcon(buttonIcon));
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				undoSound.stop();
				undoSound.start();
				newSelectedButton = undo;
				changeButtonColors();
				master.undo();
				undo.setSelected(false);
				
			}
		});

<<<<<<< HEAD
		buttonIcon = ToolPanel.class.getResource("images/redowhiteButton.png");
		redo = new RoundButton(new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/redogreenButton.png");
=======
		buttonIcon = ImageIO.read(new File("images/redowhiteButton.png"));
		redo = new RoundButton(new ImageIcon(buttonIcon));
		buttonIcon = ImageIO.read(new File("images/redogreenButton.png"));
>>>>>>> Pres
		redo.setPressedIcon(new ImageIcon(buttonIcon));
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				redoSound.stop();
				redoSound.start();
				newSelectedButton = redo;
				changeButtonColors();
				master.redo();
				redo.setSelected(false);
			}
		});


		//Line
<<<<<<< HEAD
		buttonIcon = ToolPanel.class.getResource("images/linewhite-Button.png");
		line = new RoundButton(new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
=======
		buttonIcon = ImageIO.read(new File("images/linewhite-Button.png"));
		line = new RoundButton(new ImageIcon(buttonIcon)); 
		buttonIcon = ImageIO.read(new File("images/green-Button.png"));
>>>>>>> Pres
		line.setSelectedIcon(new ImageIcon(buttonIcon));
		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = line;
				changeButtonColors();
				master.setListenerState(1);
				op.setState(OptionsPanel.State.LINE);
			}
		});

		

		//Circle
<<<<<<< HEAD
		buttonIcon = ToolPanel.class.getResource("images/cirwhite-Button.png");
		circle = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
=======
		buttonIcon = ImageIO.read(new File("images/cirwhite-Button.png"));
		circle = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ImageIO.read(new File("images/green-Button.png"));
>>>>>>> Pres
		circle.setSelectedIcon(new ImageIcon(buttonIcon));
		circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = circle;
				changeButtonColors();
				master.setListenerState(5);
				op.setState(OptionsPanel.State.CIRCLE);
			}
		});

		//Square
<<<<<<< HEAD
		buttonIcon = ToolPanel.class.getResource("images/squarewhite-Button.png");
		square = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
=======
		buttonIcon = ImageIO.read(new File("images/squarewhite-Button.png"));
		square = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ImageIO.read(new File("images/green-Button.png"));
>>>>>>> Pres
		square.setSelectedIcon(new ImageIcon(buttonIcon));
		square.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = square;
				changeButtonColors();
				master.setListenerState(6);
				op.setState(OptionsPanel.State.SQUARE);
			}
		});

		//triangle
<<<<<<< HEAD
		buttonIcon = ToolPanel.class.getResource("images/triwhite-Button.png");
		triangle = new RoundButton( new ImageIcon(buttonIcon));  
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
=======
		buttonIcon = ImageIO.read(new File("images/triwhite-Button.png"));
		triangle = new RoundButton( new ImageIcon(buttonIcon));  
		buttonIcon = ImageIO.read(new File("images/green-Button.png"));
>>>>>>> Pres
		triangle.setSelectedIcon(new ImageIcon(buttonIcon));
		triangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = triangle;
				changeButtonColors();
				master.setListenerState(3);
				op.setState(OptionsPanel.State.TRIANGLE);
			}
		});

		//Draw
<<<<<<< HEAD
		buttonIcon = ToolPanel.class.getResource("images/drawwhiteButton.png");
		draw = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/drawgreenButton.png");
=======
		buttonIcon = ImageIO.read(new File("images/drawwhiteButton.png"));
		draw = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ImageIO.read(new File("images/drawgreenButton.png"));
>>>>>>> Pres
		draw.setSelectedIcon(new ImageIcon(buttonIcon));
		draw.setPressedIcon(new ImageIcon(buttonIcon));
		draw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = draw;
				changeButtonColors();
			//	master.setListenerState(2);
				op.setState(OptionsPanel.State.DRAW);
			}
		});  



		//Text
<<<<<<< HEAD
		buttonIcon = ToolPanel.class.getResource("images/textwhite-Button.png");
		text = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
=======
		buttonIcon = ImageIO.read(new File("images/textwhite-Button.png"));
		text = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ImageIO.read(new File("images/green-Button.png"));
>>>>>>> Pres
		text.setSelectedIcon(new ImageIcon(buttonIcon));
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = text;
				changeButtonColors();
				master.setListenerState(7);
				op.setState(OptionsPanel.State.TEXT);
			}
		});

		//erasing
<<<<<<< HEAD
		buttonIcon = ToolPanel.class.getResource("images/erasewhiteButton.png");
		erase = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/erasegreenButton.png");
=======
		buttonIcon = ImageIO.read(new File("images/erasewhiteButton.png"));
		erase = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ImageIO.read(new File("images/erasegreenButton.png"));
>>>>>>> Pres
		erase.setSelectedIcon(new ImageIcon(buttonIcon));
		erase.setPressedIcon(new ImageIcon(buttonIcon));
		erase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = erase;
				changeButtonColors();
				master.setListenerState(4);
				op.setState(OptionsPanel.State.ERASE);
			}
		});

		//paint
<<<<<<< HEAD
		buttonIcon = ToolPanel.class.getResource("images/paintwhite-Button.png");
		paint = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
=======
		buttonIcon = ImageIO.read(new File("images/paintwhite-Button.png"));
		paint = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ImageIO.read(new File("images/green-Button.png"));
>>>>>>> Pres
		paint.setSelectedIcon(new ImageIcon(buttonIcon));
		paint.setPressedIcon(new ImageIcon(buttonIcon));
		paint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
				button.stop();
				button.start();
=======
>>>>>>> Pres
				newSelectedButton = paint;
				changeButtonColors();
			//	master.setListenerState(8);
				op.setState(OptionsPanel.State.PAINT);
			}
		});

		//Bucket
<<<<<<< HEAD
		buttonIcon = ToolPanel.class.getResource("images/bucketwhite-Button.png");
		bucket = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
		bucket.setSelectedIcon(new ImageIcon(buttonIcon));
		bucket.setPressedIcon(new ImageIcon(buttonIcon));
		bucket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = bucket;
=======
		buttonIcon = ImageIO.read(new File("images/bucketwhite-Button.png"));
		bucket = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ImageIO.read(new File("images/green-Button.png"));
		bucket.setSelectedIcon(new ImageIcon(buttonIcon));
		bucket.setPressedIcon(new ImageIcon(buttonIcon));
		bucket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newSelectedButton = bucket;
				changeButtonColors();
				//	master.setListenerState(8);
				//op.setState(OptionsPanel.State.PAINT);
			}
		});
		
		//Stamp
		buttonIcon = ImageIO.read(new File("images/stampwhite-Button.png"));
		stamp = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ImageIO.read(new File("images/green-Button.png"));
		stamp.setSelectedIcon(new ImageIcon(buttonIcon));
		stamp.setPressedIcon(new ImageIcon(buttonIcon));
		stamp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newSelectedButton = stamp;
>>>>>>> Pres
				changeButtonColors();
				//	master.setListenerState(8);
				//op.setState(OptionsPanel.State.PAINT);
			}
		});
<<<<<<< HEAD
		
		//Stamp
		buttonIcon = ToolPanel.class.getResource("images/stampwhite-Button.png");
		stamp = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
		stamp.setSelectedIcon(new ImageIcon(buttonIcon));
		stamp.setPressedIcon(new ImageIcon(buttonIcon));
		stamp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = stamp;
				changeButtonColors();
				//	master.setListenerState(8);
				//op.setState(OptionsPanel.State.PAINT);
			}
		});
=======
>>>>>>> Pres
	


		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(2,8,2,2);
		this.add(undo, c);

		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(2,2,2,8);
		this.add(redo, c);

		c.insets = new Insets(2,10,2,10);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(draw, c);

		//c.insets = new Insets(2,20,2,5);
		c.gridy = 2;
		this.add(paint, c);

		c.gridy = 3;
		//c.insets = new Insets(2,20,2,5);
		this.add(line,c);

		c.gridy = 4;
		//c.insets = new Insets(2,2,2,20);
		this.add(circle,c);

		c.gridy = 5;
		//c.insets = new Insets(2,20,2,5);
		this.add(square,c);

		c.gridy = 6;
		//c.insets = new Insets(2,2,2,20);
		this.add(triangle,c);

		c.gridy = 7;
		//c.insets = new Insets(2,20,2,5);
		this.add(stamp,c);
		
		c.gridy = 8;
		//c.insets = new Insets(2,20,2,5);
		this.add(bucket,c);
		
		c.gridy = 9;
		//c.insets = new Insets(2,20,2,5);
		this.add(text,c);
		
		c.gridy = 10;
		//c.insets = new Insets(2,20,2,5);
		this.add(erase,c);
	
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	}

	public void changeButtonColors(){
		if(selectedButton != null){
			selectedButton.setSelected(false);
		}
		newSelectedButton.setSelected(true);
		selectedButton = newSelectedButton;

	}

}
