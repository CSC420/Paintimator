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
		triangle, paint, bucket, stamp, op1;
	private RoundButton selectedButton;
	private RoundButton newSelectedButton;
	private Clip button, sam;


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
        
        is = getClass().getResourceAsStream("sounds/sam.wav");
        ais = AudioSystem.getAudioInputStream(is);
        sam = AudioSystem.getClip();
        sam.open(ais);

		//Line
        java.net.URL buttonIcon = ToolPanel.class.getResource("images/whiteLine.png");
		line = new RoundButton(new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/brownLine.png");
		line.setSelectedIcon(new ImageIcon(buttonIcon));
		line.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = line;
				changeButtonColors();
				op.setState(OptionsPanel.State.LINE);
			}
		});

		

		//Circle
		buttonIcon = ToolPanel.class.getResource("images/whiteCircle.png");
		circle = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/yellowCircle.png");
		circle.setSelectedIcon(new ImageIcon(buttonIcon));
		circle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = circle;
				changeButtonColors();
				op.setState(OptionsPanel.State.CIRCLE);
			}
		});

		//Square
		buttonIcon = ToolPanel.class.getResource("images/whiteSquare.png");
		square = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/greenSquare.png");
		square.setSelectedIcon(new ImageIcon(buttonIcon));
		square.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = square;
				changeButtonColors();
				op.setState(OptionsPanel.State.SQUARE);
			}
		});

		//triangle
		buttonIcon = ToolPanel.class.getResource("images/whiteTriangle.png");
		triangle = new RoundButton( new ImageIcon(buttonIcon));  
		buttonIcon = ToolPanel.class.getResource("images/blueTriangle.png");
		triangle.setSelectedIcon(new ImageIcon(buttonIcon));
		triangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = triangle;
				changeButtonColors();
				op.setState(OptionsPanel.State.TRIANGLE);
			}
		});

		//Draw
		buttonIcon = ToolPanel.class.getResource("images/whiteDraw.png");
		draw = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/greenDraw.png");
		draw.setSelectedIcon(new ImageIcon(buttonIcon));
		//draw.setPressedIcon(new ImageIcon(buttonIcon));
		draw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = draw;
				changeButtonColors();
				op.setState(OptionsPanel.State.DRAW);
			}
		});  



		//Text
		buttonIcon = ToolPanel.class.getResource("images/whiteText.png");
		text = new RoundButton( new ImageIcon(buttonIcon)); 
		buttonIcon = ToolPanel.class.getResource("images/orangeText.png");
		text.setSelectedIcon(new ImageIcon(buttonIcon));
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sam.stop();
				sam.start();
				newSelectedButton = text;
				changeButtonColors();
				op.setState(OptionsPanel.State.TEXT);
			}
		});

		//erasing
		buttonIcon = ToolPanel.class.getResource("images/whiteEraser.png");
		erase = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/blackEraser.png");
		erase.setSelectedIcon(new ImageIcon(buttonIcon));
		//erase.setPressedIcon(new ImageIcon(buttonIcon));
		erase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = erase;
				changeButtonColors();
				op.setState(OptionsPanel.State.ERASE);
			}
		});

		//paint
		buttonIcon = ToolPanel.class.getResource("images/whitePaint.png");
		paint = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/bluePaint.png");
		paint.setSelectedIcon(new ImageIcon(buttonIcon));
		//paint.setPressedIcon(new ImageIcon(buttonIcon));
		paint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = paint;
				changeButtonColors();

				op.setState(OptionsPanel.State.PAINT);
			}
		});

		//Bucket

		buttonIcon = ToolPanel.class.getResource("images/whiteBucket.png");
		bucket = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ToolPanel.class.getResource("images/redBucket.png");
		bucket.setSelectedIcon(new ImageIcon(buttonIcon));
		bucket.setPressedIcon(new ImageIcon(buttonIcon));
		bucket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
				newSelectedButton = bucket;
				changeButtonColors();
				op.setState(OptionsPanel.State.BUCKET);
			}
		});
		

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;

		c.insets = new Insets(3,0,3,0);
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
		//this.add(stamp,c);
		
		c.gridy = 8;
		//c.insets = new Insets(2,20,2,5);
		this.add(bucket,c);
		
		c.gridy = 9;
		//c.insets = new Insets(2,20,2,5);
		this.add(text,c);
		
		c.gridy = 10;
		//c.insets = new Insets(2,20,2,5);
		this.add(erase,c);
	
	}
	

	public void changeButtonColors(){
		if(selectedButton != null){
			selectedButton.setSelected(false);
		}
		newSelectedButton.setSelected(true);
		selectedButton = newSelectedButton;

	}

}
