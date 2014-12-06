package pantimator;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
=======
import java.util.Vector;
>>>>>>> master

public class AnimationPane extends JPanel {

	private static final long serialVersionUID = -4582247641791559232L;
    private final Component context = this;

	Image img;
	LayeredPanelList lpl;
	JScrollPane scrollframeHolder;
    JPanel frameHolder;
    Thumb thumbPanel;
    Paintimator painter;
    
    HashMap<Thumb, LayeredPanel> thumbMap;
    GridBagConstraints gbc = new GridBagConstraints();

    /**
     * This is a constructor
     * @throws IOException 
     */
<<<<<<< HEAD
    public AnimationPane(Paintimator painter) throws IOException {
    	this.painter = painter;
=======
/*    public AnimationPane() throws IOException {
>>>>>>> master
        init();
    }*/

    /**
     * Constructor that will be able to take a LayeredPanelList and parse through it for
     * frames
     * @param panelList
     * @throws IOException 
     */

    public AnimationPane(LayeredPanelList panelList) throws IOException {
        this.lpl = panelList;
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
        scrollframeHolder.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollframeHolder.getViewport().setOpaque(false);
        scrollframeHolder.setOpaque(false);
        
        thumbMap = new HashMap<Thumb, LayeredPanel>();
        
        this.add(scrollframeHolder);
        playButton();
        
    }

    private void playButton() throws IOException {
		// TODO Add play button functionality
    	RoundButton play;
		
    	//BufferedImage buttonIcon = ImageIO.read(new File("images/white-Button.png"));
		java.net.URL imageURL = AnimationPane.class.getResource("images/white-Button.png");
		  if (imageURL == null) {
			 System.out.println("Issue HERE loading play button in Animation Pane"); 
		  }
		 play = new RoundButton(new ImageIcon(imageURL));
		 play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

                BufferedImage[] b = new BufferedImage[0];
                Vector<BufferedImage> images = new Vector<BufferedImage>();

                for(LayeredPanel lp : lpl.getArray()){
                    images.add(lp.paneToBufferedImg());
                }


                AnimationPlayerPanel app = new AnimationPlayerPanel(images.toArray(b));

                JOptionPane.showOptionDialog(context, app, "Your Animation",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, new Object[0], null);
			}
		});
        this.add(play);
	}
    
    /*
     * Iterates through an array list of images to set the thumbnail frame
     */
    private void loadedFrameHolder(LayeredPanelList lpl) {
        this.lpl = lpl;
        int index = 1;
        frameHolder.removeAll(); // clears everything
        for (LayeredPanel lp : lpl.getArray()) {        	
        	Image img = lp.paneToImg().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        	newThumb(img, index);
        	thumbMap.put(thumbPanel, lp);
            index++;
        }
    }
    
    /*
     * Creates a new thumbnail frame and adds it to the frame holder
     */
    private void newThumb(Image img, int index) {
    	thumbPanel = Thumb.newInstance(this, img);
        thumbPanel.setToolTipText("Frame #" + index);
        thumbPanel.setPreferredSize(new Dimension(75, 75));
        frameHolder.add(thumbPanel);
    }
    
    /**
     * Public method for updating the animation frame
     * @param lpl
     */
    public void updateAnimation(LayeredPanelList lpl) { 
        loadedFrameHolder(lpl);
    }
    
    public void updateThumbArray(Thumb thumb) {
    	//painter.refreshDrawPanel(thumbMap.get(thumb));
    	for (Thumb t : thumbMap.keySet()) {
    		if (thumb != t && t.isFocused()) {
    			t.changeFocus();
    			break;
    		}
    	}
    	
    	thumb.changeFocus();
    }
}
