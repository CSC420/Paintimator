package pantimator;

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
import javax.swing.JPanel;

public class ToolPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Paintimator master;
	private OptionsPanel op;
	private RoundButton line, draw, text, erase, circle, square, 
		triangle, paint, bucket, stamp;
	private RoundButton selectedButton;
	private RoundButton newSelectedButton;
	private Clip button;


	public ToolPanel(Paintimator p, OptionsPanel o) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
		super(new GridBagLayout());
		//super();
		master = p;
		op = o;


		//AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/button.wav"));
		InputStream is = getClass().getResourceAsStream("sounds/button.wav");
		AudioInputStream ais = AudioSystem.getAudioInputStream(is);
        button = AudioSystem.getClip();
        button.open(ais);
       


		//Line
        java.net.URL buttonIcon = ToolPanel.class.getResource("images/linewhite-Button.png");
		line = new RoundButton(new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
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
		buttonIcon = ToolPanel.class.getResource("images/cirwhite-Button.png");
		circle = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
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
		buttonIcon = ToolPanel.class.getResource("images/squarewhite-Button.png");
		square = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
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
		buttonIcon = ToolPanel.class.getResource("images/triwhite-Button.png");
		triangle = new RoundButton( new ImageIcon(buttonIcon));  
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
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
		buttonIcon = ToolPanel.class.getResource("images/whiteDraw.png");
		draw = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/whiteDraw.png");
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
		buttonIcon = ToolPanel.class.getResource("images/textwhite-Button.png");
		text = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
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
		buttonIcon = ToolPanel.class.getResource("images/erasewhiteButton.png");
		erase = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/erasegreenButton.png");
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
		buttonIcon = ToolPanel.class.getResource("images/paintwhite-Button.png");
		paint = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/green-Button.png");
		paint.setSelectedIcon(new ImageIcon(buttonIcon));
		paint.setPressedIcon(new ImageIcon(buttonIcon));
		paint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = paint;
				changeButtonColors();
			//	master.setListenerState(8);
				op.setState(OptionsPanel.State.PAINT);
			}
		});

		//Bucket

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

				changeButtonColors();
				//	master.setListenerState(8);
				//op.setState(OptionsPanel.State.PAINT);
			}
		});
		
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


		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;

		c.insets = new Insets(2,10,2,10);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		this.add(draw, c);

		//c.insets = new Insets(2,20,2,5);
		c.gridy = 2;
		//this.add(paint, c);

		c.gridy = 3;
		//c.insets = new Insets(2,20,2,5);
		//this.add(line,c);

		c.gridy = 4;
		//c.insets = new Insets(2,2,2,20);
		//this.add(circle,c);

		c.gridy = 5;
		//c.insets = new Insets(2,20,2,5);
		//this.add(square,c);

		c.gridy = 6;
		//c.insets = new Insets(2,2,2,20);
		//this.add(triangle,c);

		c.gridy = 7;
		//c.insets = new Insets(2,20,2,5);
		//this.add(stamp,c);
		
		c.gridy = 8;
		//c.insets = new Insets(2,20,2,5);
		//this.add(bucket,c);
		
		c.gridy = 9;
		//c.insets = new Insets(2,20,2,5);
		//this.add(text,c);
		
		c.gridy = 10;
		//c.insets = new Insets(2,20,2,5);
		//this.add(erase,c);
	
	}

	public void changeButtonColors(){
		if(selectedButton != null){
			selectedButton.setSelected(false);
		}
		newSelectedButton.setSelected(true);
		selectedButton = newSelectedButton;

	}

}
