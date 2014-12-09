package pantimator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class AnimationPane extends JPanel {

	private static final long serialVersionUID = -4582247641791559232L;
    private final Component context = this;

	Image img;
	LayeredPanelList lpl;
	JScrollPane scrollframeHolder;
    JPanel frameHolder, btnHolder;
    Thumb thumbPanel;
    Paintimator painter;
	JButton play, btn05Speed, btn10Speed, btn15Speed;
	private Clip button;
    HashMap<Thumb, LayeredPanel> thumbMap;
    GridBagConstraints gbc = new GridBagConstraints();
    int frameRate = 200;


    public AnimationPane(Paintimator painter) throws IOException {
    	this.painter = painter;
    }

    public AnimationPane(LayeredPanelList panelList, Paintimator p) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        this.lpl = panelList;
        painter = p;
        init();
    }
    
    public void thumbSelected(int index){
    	painter.thumbSelect(index-1);
    }

    private void init() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        // make frame holder
        frameHolder = new JPanel(new FlowLayout());
        frameHolder.setMaximumSize(new Dimension(775, 125));
        frameHolder.setOpaque(false);
        
        // make scroll frame
        scrollframeHolder = new JScrollPane(frameHolder);
        //scrollframeHolder.setBorder(new BevelBorder(BevelBorder.LOWERED));
        scrollframeHolder.setBorder(BorderFactory.createEmptyBorder());
        scrollframeHolder.setPreferredSize(new Dimension(790, 110));
        scrollframeHolder.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollframeHolder.getViewport().setOpaque(false);
        scrollframeHolder.setOpaque(false);
        
        thumbMap = new HashMap<Thumb, LayeredPanel>();
        InputStream is = getClass().getResourceAsStream("sounds/button2.wav");
		AudioInputStream ais = AudioSystem.getAudioInputStream(is);
        button = AudioSystem.getClip();
        button.open(ais);
        this.add(scrollframeHolder);
        
        btnHolder  = new JPanel(new GridBagLayout());
        btnHolder.setOpaque(false);
        playButton();
        
    }

    private void playButton() throws IOException {

		final java.net.URL imageURL = AnimationPane.class.getResource("images/play.png");
		final java.net.URL imageGreenURL = AnimationPane.class.getResource("images/playGreen.png");
		final java.net.URL image05URL = AnimationPane.class.getResource("images/05.png");
		final java.net.URL image10URL = AnimationPane.class.getResource("images/10.png");
		final java.net.URL image15URL = AnimationPane.class.getResource("images/15.png");
		final java.net.URL image05GreenURL = AnimationPane.class.getResource("images/05Green.png");
		final java.net.URL image10GreenURL = AnimationPane.class.getResource("images/10Green.png");
		final java.net.URL image15GreenURL = AnimationPane.class.getResource("images/15Green.png");
		  if (imageURL == null) {
			 System.out.println("Issue loading play button in Animation Pane"); 
		  }
		  
		  btn05Speed = new JButton(new ImageIcon(image05URL));
		  btn05Speed.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		  btn05Speed.setOpaque(true);
		  btn05Speed.setContentAreaFilled(false);
		  btn05Speed.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frameRate = 300;
					btn05Speed.setIcon(new ImageIcon(image05GreenURL));
					
					if(btn10Speed != null){
						btn10Speed.setIcon(new ImageIcon(image10URL));
					}
					
					if(btn15Speed != null){
						btn15Speed.setIcon(new ImageIcon(image15URL));
					}
					
				}
			});
		  
		  btn10Speed = new JButton(new ImageIcon(image10GreenURL));
		  btn10Speed.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		  btn10Speed.setOpaque(true);
		  btn10Speed.setContentAreaFilled(false);
		  btn10Speed.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frameRate = 200;
					btn10Speed.setIcon(new ImageIcon(image10GreenURL));
					
					if(btn05Speed != null){
						btn05Speed.setIcon(new ImageIcon(image05URL));
					}
					
					if(btn15Speed != null){
						btn15Speed.setIcon(new ImageIcon(image15URL));
					}
					
				}
			});
		  
		  btn15Speed = new JButton(new ImageIcon(image15URL));
		  btn15Speed.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		  btn15Speed.setOpaque(true);
		  btn15Speed.setContentAreaFilled(false);
		  btn15Speed.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frameRate = 100;
					btn15Speed.setIcon(new ImageIcon(image15GreenURL));
					
					if(btn10Speed != null){
						btn10Speed.setIcon(new ImageIcon(image10URL));
					}
					
					if(btn05Speed != null){
						btn05Speed.setIcon(new ImageIcon(image05URL));
					}
					
				}
		  });
		  
		 play = new JButton(new ImageIcon(imageURL));
		 play.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		 play.setOpaque(true);
		 play.setContentAreaFilled(false);
		 play.setBackground(Color.BLACK);
		 play.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				//play.setBorder(BorderFactory.createLineBorder(Color.YELLOW,1));
				play.setIcon(new ImageIcon(imageGreenURL));
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				//play.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
				play.setIcon(new ImageIcon(imageURL));
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			 
				 
		 });
		
		 play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button.stop();
				button.start();
                BufferedImage[] b = new BufferedImage[0];
                Vector<BufferedImage> images = new Vector<BufferedImage>();

                for(LayeredPanel lp : lpl.getArray()){
                    images.add(lp.paneToBufferedImg());
                }


                AnimationPlayerPanel app = new AnimationPlayerPanel(images.toArray(b), frameRate);

                JOptionPane.showOptionDialog(context, app, "Your Animation",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, new Object[0], null);
			}
		});

		 gbc.gridx = 0;
		 gbc.gridy = 0;
		 gbc.gridheight = 1;
		 gbc.gridwidth = 3;
		 btnHolder.add(play, gbc);
		
		 gbc.gridx = 0;
		 gbc.gridy = 1;
		 gbc.gridheight = 1;
		 gbc.gridwidth = 1;
		 btnHolder.add(btn05Speed, gbc);
		 
		 
		 gbc.gridx = 1;
		 gbc.gridy = 1;
		 gbc.gridheight = 1;
		 gbc.gridwidth = 1;
		 gbc.weightx = 2.4;
		 btnHolder.add(btn10Speed, gbc);
		 
		 
		 gbc.gridx = 2;
		 gbc.gridy = 1;
		 gbc.gridheight = 1;
		 gbc.gridwidth = 1;
		 gbc.weightx = 0.0;
		 btnHolder.add(btn15Speed, gbc);
		 
		 this.add(btnHolder); 
		 //this.add(play);
	}
    
    /*
     * Iterates through an array list of images to set the thumbnail frame
     */
    private void loadedFrameHolder(LayeredPanelList lpl, int selected) {
        this.lpl = lpl;
        int index = 1;
        frameHolder.removeAll(); // clears everything
        for (LayeredPanel lp : lpl.getArray()) {        	
        	Image img = lp.paneToImg().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        	newThumb(img, index, selected);
            index++;
        }
    }
    
    /*
     * Creates a new thumbnail frame and adds it to the frame holder
     */
    private void newThumb(Image img, int index, int selected) {
    	thumbPanel = Thumb.newInstance(this, img, index);
        thumbPanel.setToolTipText("Frame #" + index);
        thumbPanel.setPreferredSize(new Dimension(75, 75));
        if(index == selected){
        	thumbPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }
        frameHolder.add(thumbPanel);
    }
    
    /**
     * Public method for updating the animation frame
     * @param lpl
     */
    public void updateAnimation(LayeredPanelList lpl, int selected) { 
        loadedFrameHolder(lpl, selected);
    }
}
