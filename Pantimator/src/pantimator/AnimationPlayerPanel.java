package pantimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * Created by wilhelmi on 12/4/14.
 */
public class AnimationPlayerPanel extends JPanel {
    private BufferedImage[] images;
    private JLabel animation;
    private JToggleButton b;
    private int frameRate = 200;

    public AnimationPlayerPanel(BufferedImage[] imgs){
        images = imgs;
        animation = new JLabel(new ImageIcon(images[0]));
        b = new JToggleButton("Start");

        this.setLayout(new BorderLayout());
        this.add(animation, BorderLayout.CENTER);

        JPanel buttonBuffer = new JPanel();
        buttonBuffer.add(b);

        this.add(buttonBuffer, BorderLayout.PAGE_END);
        playAnimation();
    }//end Constructor
    
    public AnimationPlayerPanel(BufferedImage[] imgs, int fr){
        this.frameRate = fr;
        images = imgs;
        animation = new JLabel(new ImageIcon(images[0]));
        b = new JToggleButton("Start");

        this.setLayout(new BorderLayout());
        this.add(animation, BorderLayout.CENTER);

        JPanel buttonBuffer = new JPanel();
        buttonBuffer.add(b);

        this.add(buttonBuffer, BorderLayout.PAGE_END);
        playAnimation();
    }

    public void playAnimation(){
        for(BufferedImage bi : images) {
            ActionListener animate = new ActionListener() {

                private int index = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (index<images.length-1) {
                        index++;
                    } else {
                        index = 0;
                    }
                    animation.setIcon(new ImageIcon(images[index]));
                }
            };

            final Timer timer = new Timer(frameRate,animate);
            ActionListener startStop = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (b.isSelected()) {
                        timer.start();
                        b.setText("Stop");
                    } else {
                        timer.stop();
                        b.setText("Start");
                    }
                }
            };
            b.addActionListener(startStop);

        }//end for
    }//end playAnimation

}//end AniimationPlayerPanel
