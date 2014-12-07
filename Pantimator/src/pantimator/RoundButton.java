package pantimator;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.BorderFactory;
import javax.swing.DefaultButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;

class RoundButton extends JButton {

	private static final long serialVersionUID = 1L;
	protected Shape shape, base;

	public RoundButton(Icon icon) {
		this(null, icon);
	}

	public RoundButton(String text) {
		this(text, null);
	}

	public RoundButton(String text, Icon icon) {
		setModel(new DefaultButtonModel());
		init(text, icon);
		if(icon==null) {
			return;
		}
		setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		setBackground(new Color(0, 0, 0, 0));
		setContentAreaFilled(false);
		setFocusPainted(false);
		setAlignmentY(Component.TOP_ALIGNMENT);
		initShape();
	}

	protected void initShape() {
		if(!getBounds().equals(base)) {
			Dimension s = getPreferredSize();
			base = getBounds();
			shape = new Ellipse2D.Float(0, 0, s.width-1, s.height-1);
		}
	}
	@Override 
	public Dimension getPreferredSize() {
		Icon icon = getIcon();
		Insets i = getInsets();
		if (icon != null) {
			//this was to make a perfect circle now just use image
			//int iw = Math.max(icon.getIconWidth(), icon.getIconHeight());
			return new Dimension(icon.getIconWidth() + i.right + i.left, icon.getIconHeight() + i.top + i.bottom);
		}
		return new Dimension (10, 10);
	}
	@Override 
	protected void paintBorder(Graphics g) {
		initShape();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//g2.setColor(new Color(0, 0, 0, 0));
		g2.setColor(Color.BLACK);
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
