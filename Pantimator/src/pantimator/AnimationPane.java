package pantimator;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    HashMap<Thumb, LayeredPanel> thumbMap;
    GridBagConstraints gbc = new GridBagConstraints();


    public AnimationPane(Paintimator painter) throws IOException {
    	this.painter = painter;
    }

    public AnimationPane(LayeredPanelList panelList, Paintimator p) throws IOException {
        this.lpl = panelList;
        painter = p;
        init();
    }
    
    public void thumbSelected(int index){
    	painter.thumbSelect(index-1);
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

		java.net.URL imageURL = AnimationPane.class.getResource("images/play.png");
		  if (imageURL == null) {
			 System.out.println("Issue loading play button in Animation Pane"); 
		  }
<<<<<<< HEAD
=======
		 //Mark
		  btnHolder = new JPanel(new GridBagLayout());
		  btnHolder.setOpaque(false);
		  
		  
		  btn05x = new JButton("0.5x"); 
		  btn05x.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		  btn05x.setBackground(Color.black);
		  btn05x.addActionListener(new ActionListener(){
			  @Override
				public void actionPerformed(ActionEvent e) {
				  
				  btn05x.setBackground(Color.blue);
				  frameRate = 100;
				  
				  if(btn10x != null){
					  btn10x.setBackground(Color.black);
				  }
				  
				  if(btn15x != null){
					  btn15x.setBackground(Color.black);
				  }
			  }
		  });
		  
		  btn10x = new JButton("1.0x"); 
		  btn10x.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		  btn10x.setBackground(Color.blue);
		  btn10x.addActionListener(new ActionListener(){
			  @Override
				public void actionPerformed(ActionEvent e) {
				  
				  btn10x.setBackground(Color.blue);
				  frameRate = 200;
				  
				  if(btn05x != null){
					  btn05x.setBackground(Color.black);
				  }
				  
				  if(btn15x != null){
					  btn15x.setBackground(Color.black);
				  }
			  }
		  });
		  
		  btn15x = new JButton("1.5x"); 
		  btn15x.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		  btn15x.setBackground(Color.black);
		  btn15x.addActionListener(new ActionListener(){
			  @Override
				public void actionPerformed(ActionEvent e) {
				  
				  btn15x.setBackground(Color.blue);
				  frameRate = 300;
				  
				  if(btn10x != null){
					  btn10x.setBackground(Color.black);
				  }
				  
				  if(btn05x != null){
					  btn05x.setBackground(Color.black);
				  }
			  }
		  });
		  
		  
>>>>>>> parent of 6e8d9f2... Updates
		 play = new JButton(new ImageIcon(imageURL));
		 play.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		 play.setOpaque(true);
		 play.setContentAreaFilled(false);
		 play.setBackground(Color.BLACK);
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
<<<<<<< HEAD
        this.add(play);
=======
		 
		 gbc.gridx = 0;
		 gbc.gridy = 0;
		 gbc.gridheight = 1;
		 gbc.gridwidth = 3;
		 btnHolder.add(play, gbc);
		 
		 gbc.gridx = 0;
		 gbc.gridy = 1;
		 gbc.gridheight = 1;
		 gbc.gridwidth = 1;
		 btnHolder.add(btn05x, gbc);
		 
		 gbc.gridx = 1;
		 gbc.gridy = 1;
		 gbc.gridheight = 1;
		 gbc.gridwidth = 1;
		 btnHolder.add(btn10x, gbc);
		 
		 gbc.gridx = 2;
		 gbc.gridy = 1;
		 gbc.gridheight = 1;
		 gbc.gridwidth = 1;
		 btnHolder.add(btn15x, gbc);
		 
		 this.add(btnHolder);
		 //this.add(play);
        //this.add(btn1x);
        //this.setBackground(Color.blue);
>>>>>>> parent of 6e8d9f2... Updates
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
