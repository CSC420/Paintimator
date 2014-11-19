package pantimator;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/*
 * Sets the thumbnail image to the thumbnail frame
 */
public class Thumb extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2517445643629913253L;
	
	static Image image;
	
	public static Thumb newInstance(Image img) {
		Thumb tp = new Thumb();
		image = img;
		return tp;
	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, null);
    }
}

