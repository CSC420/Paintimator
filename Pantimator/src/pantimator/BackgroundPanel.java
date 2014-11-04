package pantimator;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


class BackgroundPanel extends JPanel {

private static final long serialVersionUID = 1L;
private Image img;

  public BackgroundPanel(String img) {
    this(new ImageIcon(img).getImage());
  }

  public BackgroundPanel(Image img) {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    this.setLayout(null);
    setSize(size);
  }
  public BackgroundPanel() {

	  }

  public void paintComponent(Graphics g) {
    g.drawImage(img, 0, 0, null);
  }

}