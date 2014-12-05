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
	private AnimationPane ap;
	private boolean isFocused = false;

    public Thumb(AnimationPane ap, Image img) {
    	this.ap = ap;
        image = img;
        init();
    }

    public static Thumb newInstance(AnimationPane ap, Image img) {
		Thumb tp = new Thumb(ap, img);
		return tp;
	}
	
    private void init() {
    	addMouseListener(this);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.BLACK));
    }
    
    private void setFocusBorder() {
    	if (!isFocused) {
			this.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		} else {
			this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.BLACK));
		}
    	
    	isFocused = !isFocused;
    }
    
    public boolean isFocused() {
    	return isFocused;
    }
    
    public void changeFocus() {
    	setFocusBorder();
    }
    
    private void notifyAP() {
    	ap.updateThumbArray(this);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(image, 0, 0, null);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		notifyAP();		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (!isFocused) {
			this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.CYAN, Color.BLACK));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!isFocused) {
			this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.GRAY, Color.BLACK));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }
}

