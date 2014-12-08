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
    JPanel frameHolder;
    Thumb thumbPanel;
    Paintimator painter;
	JButton play;
	private Clip button;
    HashMap<Thumb, LayeredPanel> thumbMap;
    GridBagConstraints gbc = new GridBagConstraints();


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
        playButton();
        
    }

    private void playButton() throws IOException {

		final java.net.URL imageURL = AnimationPane.class.getResource("images/play.png");
		final java.net.URL imageGreenURL = AnimationPane.class.getResource("images/playGreen.png");
		  if (imageURL == null) {
			 System.out.println("Issue loading play button in Animation Pane"); 
		  }
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
    private void loadedFrameHolder(LayeredPanelList lpl, int selected) {
        this.lpl = lpl;
        int index = 1;
        frameHolder.removeAll(); // clears everything
        for (LayeredPanel lp : lpl.getArray()) {        	
        	Image img = lp.paneToImg().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        	newThumb(img, index, selected);
        	thumbMap.put(thumbPanel, lp);
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
