package pantimator;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AnimationPane extends JPanel {
    BufferedImage bi;
    Image img;

    JScrollPane scrollframeHolder;

    JPanel frameHolder;
    JPanel thumbPanel;

    ArrayList<Image> thumbs;

    GridBagConstraints gbc = new GridBagConstraints();

    public AnimationPane() {
        init();
    }

    public AnimationPane(LayeredPanelList panelList) {
        init();
    }

    private void init() {
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setLayout(new GridBagLayout());
        gbc.insets = new Insets(5,5,5,5);
        frameHolder = new JPanel(new FlowLayout());
        frameHolder.setMaximumSize(new Dimension(950, 25));
        scrollframeHolder = new JScrollPane(frameHolder);
        scrollframeHolder.setBorder(new BevelBorder(BevelBorder.LOWERED));
        scrollframeHolder.setPreferredSize(new Dimension(1000, 60));
        scrollframeHolder.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        thumbs = new ArrayList<Image>();

        if (!thumbs.isEmpty()) {
            loadedFrameHolder(thumbs);
        } else {
            //sampleFrameHolder();
            defaultFrameHolder();
        }

        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(scrollframeHolder, gbc);
        JButton play = new JButton();
        play.setPreferredSize(new Dimension(75, 25));
        play.setText("Play");

        this.add(play);
    }

    private void sampleFrameHolder() {
        /*gbc.insets = new Insets(2,2,2,2);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridy = 0;*/

        for (int i = 0; i < 50; i++) {
            //gbc.gridx = i;
            thumbPanel = new JPanel();
            thumbPanel.setToolTipText("Frame " + (i + 1));
            thumbPanel.setPreferredSize(new Dimension(25,25));
            thumbPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            frameHolder.add(thumbPanel);
        }
    }

    private void defaultFrameHolder() {
        gbc.insets = new Insets(2,2,2,2);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.gridy = 0;

        newThumb(null,1);
    }

    private void loadedFrameHolder(ArrayList<Image> lpl) {
        int index = 0;

        frameHolder.removeAll();
        for (Image b : lpl) {
            newThumb(b, index);
            index++;
        }
        frameHolder.repaint();
    }

    private void newThumb(Image b, int index) {
        thumbPanel = new ThumbPane();
        thumbPanel.setToolTipText("Frame #" + index);
        thumbPanel.setPreferredSize(new Dimension(50, 50));
        thumbPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        if (b != null) {
            thumbPanel.repaint();
        }
        frameHolder.add(thumbPanel);
    }

    public void updateAnimation(LayeredPanel lp, boolean isNewProj) {
        bi = lp.paneToImg();
        img = bi.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        if (!isNewProj) {
            thumbs.add(img);
        } else {
            thumbs.clear();
            thumbs.add(img);
        }
        loadedFrameHolder(thumbs);
    }

    private class ThumbPane extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(img, 0, 0, thumbPanel);
        }
    }
}
