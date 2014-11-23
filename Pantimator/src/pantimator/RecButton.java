package pantimator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;

public class RecButton extends JButton {

	private static final long serialVersionUID = 1L;
	protected Shape shape, base;
	int h;


	public RecButton(int height) {
		setModel(new DefaultButtonModel());
		h = height;
		setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		setBackground(Color.BLACK);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setAlignmentY(Component.TOP_ALIGNMENT);
		initShape();
	}

	protected void initShape() {
		if(!getBounds().equals(base)) {
			Dimension s = getPreferredSize();
			base = getBounds();
		//	shape = new Ellipse2D.Float(0, 0, d.width-1, d.height-1);
			shape = new Rectangle2D.Float(0, 0, s.width-1, s.height-1);
		}
	}
	
	@Override 
	public Dimension getPreferredSize() {
		Insets i = getInsets();
		return new Dimension(80 + i.right + i.left, h + i.top + i.bottom);
	}

	@Override 
	protected void paintBorder(Graphics g) {
		initShape();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		g2.draw(shape);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_OFF);
	}
	@Override 
	public boolean contains(int x, int y) {
		initShape();
		return shape.contains(x, y);
	}
}
