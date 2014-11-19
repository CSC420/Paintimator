package pantimator;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
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
     */
    public AnimationPane() {
        init();
    }
    
    /**
     * Constructor that will be able to take a LayeredPanelList and parse through it for
     * frames
     * @param panelList
     */
    public AnimationPane(LayeredPanelList panelList) {
    	lpl = panelList;
        init();
    }

    private void init() {
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

    private void playButton() {
		// TODO Add play button functionality
    	JButton play = new JButton();
        play.setPreferredSize(new Dimension(75, 25));
        play.setText("Play");
        this.add(play);
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
}
