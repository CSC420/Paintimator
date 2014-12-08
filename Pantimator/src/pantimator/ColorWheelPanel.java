package pantimator;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ColorWheelPanel extends JPanel implements MouseListener {
	    private final Paintimator master;

	    private String imgPath;
	    private Image image = null;
	    private Ellipse2D.Float innerCircle, outerCircle;
	    private Color centerColor = Color.black;
	    private Clip button;
	    private BufferedImage bimage;
	    public ColorWheelPanel(Paintimator master) throws IOException, UnsupportedAudioFileException, LineUnavailableException{
	        this.master = master;
	        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			InputStream is = getClass().getResourceAsStream("sounds/colorWheel.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(is);
	        button = AudioSystem.getClip();
	        button.open(ais);
	        java.net.URL icon = ColorWheelPanel.class.getResource("images/color_wheel2.png");
			  if (icon == null) {
				 System.out.println("Issue loading Color Wheel in ColorWheelPanel"); 
				 System.exit(-1);
			  }

	    
	        image = new ImageIcon(icon).getImage();
	        innerCircle = new Ellipse2D.Float(image.getWidth(null)/3, image.getHeight(null)/3,
	                image.getWidth(null)/3, image.getHeight(null)/3);
	        outerCircle = new Ellipse2D.Float(0,0,image.getWidth(null),image.getHeight(null));

	        Dimension size = new Dimension(image.getWidth(null), image.getHeight(null));
	        setPreferredSize(size);
	        setMinimumSize(size);
	        setMaximumSize(size);
	        setSize(size);
	        setLayout(null);

	        addMouseListener(this);

	        // Create a buffered image with transparency
	        bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	        // Draw the image on to the buffered image
	        Graphics2D bGr = bimage.createGraphics();
	        bGr.drawImage(image, 0, 0, null);
	        bGr.dispose();
	    }

	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D)g;
	        g2d.drawImage(image,0, 0, null);
	        g2d.setColor(centerColor);
	        g2d.fill(innerCircle);

	    }

	    @Override
	    public void mouseClicked(MouseEvent e) {
	        if(!innerCircle.contains(e.getPoint()) && outerCircle.contains(e.getPoint())) {
	            button.stop();
	            button.start();
	        	centerColor = new Color(bimage.getRGB(e.getX(), e.getY()));
	            //centerColor = new Color(bimage.getRGB(e.getX(), e.getY()));
	          //  System.out.println("New Color: " + centerColor);
	            this.repaint();
	            master.setDrawColor(centerColor);
	        }
	    }

	    @Override public void mousePressed(MouseEvent e) {}
	    @Override public void mouseReleased(MouseEvent e) {}
	    @Override public void mouseEntered(MouseEvent e) {}
	    @Override public void mouseExited(MouseEvent e) {}
	}
