package pantimator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AnimationPane extends JPanel {

	private static final long serialVersionUID = -4582247641791559232L;

	Image img;
<<<<<<< HEAD
	LayeredPanelList lpl;
=======
	private Image backgroundImg;
	private Boolean hasBackground = false;

	LayeredPanelList lpl;
    
>>>>>>> Pres
    JScrollPane scrollframeHolder;
    JPanel frameHolder;
    Thumb thumbPanel;
<<<<<<< HEAD
=======

>>>>>>> Pres
    ArrayList<Image> thumbs;
    GridBagConstraints gbc = new GridBagConstraints();
    
<<<<<<< HEAD
=======
    /**
     * This is a constructor
     * @throws IOException 
     */
>>>>>>> Pres
    public AnimationPane() throws IOException {
        init();
    }
    
    /**
     * Constructor that will be able to take a LayeredPanelList and parse through it for
     * frames
     * @param panelList
     * @throws IOException 
     */

    public AnimationPane(LayeredPanelList panelList) throws IOException {
        init();
    }

    private void init() throws IOException {
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
        scrollframeHolder.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollframeHolder.getViewport().setOpaque(false);
        scrollframeHolder.setOpaque(false);
        thumbs = new ArrayList<Image>();
        
        this.add(scrollframeHolder);
        playButton();
        
    }

    private void playButton() throws IOException {
		// TODO Add play button functionality
    	RoundButton play;
<<<<<<< HEAD
		
    	//BufferedImage buttonIcon = ImageIO.read(new File("images/white-Button.png"));
		java.net.URL imageURL = AnimationPane.class.getResource("images/white-Button.png");
		  if (imageURL == null) {
			 System.out.println("Issue loading play button in Animation Pane"); 
			 System.exit(-1);
		  }
		 play = new RoundButton(new ImageIcon(imageURL));
		 imageURL = AnimationPane.class.getResource("images/green-Button.png");
		 if (imageURL == null) {
			 System.out.println("Issue loading play button in Animation Pane"); 
			 System.exit(-1);
		  }
		play.setSelectedIcon(new ImageIcon(imageURL));
		play.setPressedIcon(new ImageIcon(imageURL));
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JPanel(),
						"If I were a real button I would play an animation",
						"Play Button",
						JOptionPane.OK_OPTION);
=======
		BufferedImage buttonIcon = ImageIO.read(new File("images/white-Button.png"));
		play = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ImageIO.read(new File("images/green-Button.png"));
		play.setSelectedIcon(new ImageIcon(buttonIcon));
		play.setPressedIcon(new ImageIcon(buttonIcon));
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//do whatever here
>>>>>>> Pres
			}
		});
        this.add(play);
	}

	private void sampleFrameHolder() {    	
        for (int i = 0; i < 50; i++) {
          //  thumbPanel = new JPanel();
            thumbPanel.setToolTipText("Frame " + (i + 1));
            thumbPanel.setPreferredSize(new Dimension(75,75));
            thumbPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            frameHolder.add(thumbPanel);
        }
    }

    @SuppressWarnings("unused")
	private void defaultFrameHolder() {
        newThumb(null, 1);
    }
    
    /*
     * Iterates through an array list of images to set the thumbnail frame
     */
    private void loadedFrameHolder(LayeredPanelList lpl) {
<<<<<<< HEAD
        int index = 1;
     //   System.out.println(lpl.getSize());
        frameHolder.removeAll(); // clears everything
        for (LayeredPanel lp : lpl.getArray()) {        	
        	Image img = lp.paneToImg().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        	newThumb(img, index);
=======
        int index = 0;
     //   System.out.println(lpl.getSize());
        frameHolder.removeAll(); // clears everything
        for (LayeredPanel lp : lpl.getArray()) {        	
        	//Image img = lp.paneToImg().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        	//thumbPanel = ThumbPane.newInstance(img);
        	thumbPanel = new Thumb();
        	thumbPanel.add(lp);
            thumbPanel.setToolTipText("Frame #" + (index + 1));
            thumbPanel.setPreferredSize(new Dimension(25, 25));
            thumbPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            frameHolder.add(thumbPanel);
>>>>>>> Pres
            index++;
        }
    }
    
    /*
     * Creates a new thumbnail frame and adds it to the frame holder
     */
    private void newThumb(Image img, int index) {
        thumbPanel = Thumb.newInstance(img);
        thumbPanel.setToolTipText("Frame #" + index);
        thumbPanel.setPreferredSize(new Dimension(75, 75));
        thumbPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        frameHolder.add(thumbPanel);
    }
    
    /**
     * Public method for updating the animation frame
     * @param lp
     */
    
<<<<<<< HEAD
=======
    @Override
    public void paintComponent(Graphics g){
    	super.paintComponent(g);
    	if(hasBackground){
    	g.drawImage(backgroundImg, 0, 0, null);
    	}
    }
    
    public void setBackgroundImage(Image im){
    	backgroundImg = im;
    	hasBackground = true;
    }
>>>>>>> Pres
    
    /*
     * Sets the thumbnail image to the thumbnail frame
     */
<<<<<<< HEAD

    public void updateAnimation(LayeredPanelList lpl) { 
        loadedFrameHolder(lpl);
=======

    public void updateAnimation(LayeredPanelList lpl) { 
        loadedFrameHolder(lpl);
    }
       private class ThumbPane extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3670839323703410770L;

		@SuppressWarnings("unused")
		public void ThumbPane() {}
    	
	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	
	     //   g.drawImage(img, 0, 0, null);
	    }

>>>>>>> Pres
    }

}