package pantimator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AnimationPane extends JPanel {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9151202800709578455L;

	LayeredPanelList lpl;
    
    JScrollPane scrollframeHolder;

    JPanel frameHolder;
    Thumb thumbPanel;

    ArrayList<Image> thumbs;

    GridBagConstraints gbc = new GridBagConstraints();
    
    /**
     * This is a constructor
     * @throws IOException 
     */
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
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        
        // make frame holder
        frameHolder = new JPanel(new FlowLayout());
        frameHolder.setMaximumSize(new Dimension(800, 25));
        
        // make scroll frame
        scrollframeHolder = new JScrollPane(frameHolder);
        scrollframeHolder.setBorder(new BevelBorder(BevelBorder.LOWERED));
        scrollframeHolder.setPreferredSize(new Dimension(825, 60));
        scrollframeHolder.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        thumbs = new ArrayList<Image>();
        
        this.add(scrollframeHolder);
        playButton();
    }

    private void playButton() throws IOException {
		// TODO Add play button functionality
    	RoundButton play;
		BufferedImage buttonIcon = ImageIO.read(new File("images/white-Button.png"));
		play = new RoundButton( new ImageIcon(buttonIcon));
		buttonIcon = ImageIO.read(new File("images/green-Button.png"));
		play.setSelectedIcon(new ImageIcon(buttonIcon));
		play.setPressedIcon(new ImageIcon(buttonIcon));
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//do whatever here
			}
		});
        this.add(play);
	}

	private void sampleFrameHolder() {    	
        for (int i = 0; i < 50; i++) {
          //  thumbPanel = new JPanel();
            thumbPanel.setToolTipText("Frame " + (i + 1));
            thumbPanel.setPreferredSize(new Dimension(25,25));
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
        int index = 0;
        System.out.println(lpl.getSize());
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
            index++;
        }
    }
    
    /*
     * Creates a new thumbnail frame and adds it to the frame holder
     */
    private void newThumb(Image img, int index) {
        thumbPanel = Thumb.newInstance(img);
        thumbPanel.setToolTipText("Frame #" + index);
        thumbPanel.setPreferredSize(new Dimension(25, 25));
        thumbPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        frameHolder.add(thumbPanel);
    }
    
    /**
     * Public method for updating the animation frame
     * @param lp
     */

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

    }
}
