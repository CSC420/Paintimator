package pantimator;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * Sets the thumbnail image to the thumbnail frame
 */
public class Thumb extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2517445643629913253L;
	
	private Image image;
	
	private boolean isFocused = false;

    public Thumb(Image img) {
        image = img;
        init();
    }

    public static Thumb newInstance(Image img) {
		Thumb tp = new Thumb(img);
		return tp;
	}
	
    private void init() {
    	addMouseListener(this);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.BLACK));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, null);
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (!isFocused) {
			this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.GREEN));
		} else {
			this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.BLACK));
		}

		isFocused = !isFocused;		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

