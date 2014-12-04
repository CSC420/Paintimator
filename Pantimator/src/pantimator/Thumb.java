package pantimator;

import javax.swing.*;
import java.awt.*;

/*
 * Sets the thumbnail image to the thumbnail frame
 */
public class Thumb extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2517445643629913253L;
	
	private Image image;

    public Thumb(Image img){
        image = img;
    }


//	public static Thumb newInstance(Image img) {
//		Thumb tp = new Thumb();
//		image = img;
//		return tp;
//	}
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, null);
    }
}

