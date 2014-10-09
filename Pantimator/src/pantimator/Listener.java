package pantimator;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener implements MouseListener, MouseMotionListener  {
<<<<<<< HEAD
	final Logger LOG = Logger.getLogger(Canvas.class.getName());
	private LisState currentState;
	private Canvas canvas;

	public Listener(Canvas c){
		canvas = c;
=======
	static final Logger LOG = Logger.getLogger(Listener.class.getName());

//    static final ComponentMover cm = new ComponentMover();

    private LisState currentState;
    private LayeredPanel layeredPanel;
    private Point p1;
    private Point p2;
    private Vector<Integer> xDrawPoints;
    private Vector<Integer> yDrawPoints;

	public Listener(LayeredPanel lp){
        layeredPanel = lp;
>>>>>>> master
		currentState = LisState.NONE;

        xDrawPoints = new Vector<Integer>();
        yDrawPoints = new Vector<Integer>();


//        layer.setOpaque(false);
//        layer.setVisible(true);

//        jp = new JPanel();
//        layer.setBackground(new Color(125,0,0, 0));
//        jp.setOpaque(false);

//        cm.setEdgeInsets( new Insets(-100, -100, -100, -100) );
//        cm.setAutoLayout(true);
	}

	public void setLisState(LisState s){
		currentState = s;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		currentState.mouseDragged(this, e);
<<<<<<< HEAD

=======
>>>>>>> master
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		currentState.mouseMoved(this, e);
<<<<<<< HEAD

=======
>>>>>>> master
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		currentState.mouseClicked(this, e);
<<<<<<< HEAD

=======
>>>>>>> master
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		currentState.mouseEntered(this, e);
<<<<<<< HEAD

=======
>>>>>>> master
	}

	@Override
	public void mouseExited(MouseEvent e) {
		currentState.mouseExited(this, e);
<<<<<<< HEAD

=======
>>>>>>> master
	}

	@Override
	public void mousePressed(MouseEvent e) {
		currentState.mousePressed(this, e);
<<<<<<< HEAD

=======
>>>>>>> master
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		currentState.mouseReleased(this, e);
<<<<<<< HEAD

=======
>>>>>>> master
	}

	public static enum LisState {
		LINE {
			public void mousePressed(Listener l, MouseEvent e) {
<<<<<<< HEAD
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
			}

			public void mouseReleased(Listener l, MouseEvent e) {
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();

			}

=======
				LOG.log(Level.INFO, "Mouse Pressed");

                l.p1 = e.getPoint();
			}

			public void mouseReleased(Listener l, MouseEvent e) {
                LOG.log(Level.INFO, "Mouse Released");

                l.layeredPanel.clearGlassPane();
                l.p2 = e.getPoint();
                Line2D.Float line = new Line2D.Float(l.p1, l.p2);
                l.layeredPanel.drawOnRootPane(line);
            }
>>>>>>> master

			public void mouseDragged(Listener l, MouseEvent e) {
                l.layeredPanel.clearGlassPane();
                Line2D.Float line = new Line2D.Float(l.p1, e.getPoint());
                l.layeredPanel.drawOnGlassPane(line);
			}

		},
		DRAW{
			public void mousePressed(Listener l, MouseEvent e) {
<<<<<<< HEAD
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
				l.canvas.setPoint2(p);
				l.canvas.repaint();

=======
				 //LOG.log(Level.INFO, "Mouse Pressed");
//				 Point p = e.getPoint();
//				 l.canvas.setPoint1(p);
//				 l.canvas.setPoint2(p);
//		         l.canvas.repaint();

                l.p1 = e.getPoint();
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());
				
>>>>>>> master
			}

            public void mouseReleased(Listener l, MouseEvent e) {
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());

                l.layeredPanel.clearGlassPane();
                Path2D p = gimmeThePath(l.xDrawPoints, l.yDrawPoints);
                l.layeredPanel.clearGlassPane();
                l.layeredPanel.drawOnRootPane(p);
                l.xDrawPoints.clear();
                l.yDrawPoints.clear();

            }

			public void mouseDragged(Listener l, MouseEvent e) {
<<<<<<< HEAD
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();
=======
//				Point p = e.getPoint();
//				l.canvas.setPoint2(p);
//		        l.canvas.repaint();
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());

                Line2D.Float line = new Line2D.Float(l.p1, e.getPoint());
                l.layeredPanel.drawOnGlassPane(line);
                l.p1 = e.getPoint();
>>>>>>> master
			}	

		},
		ERASE {

			public void mousePressed(Listener l, MouseEvent e) {
<<<<<<< HEAD
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
				l.canvas.setPoint2(p);
				l.canvas.repaint();

=======
				 //LOG.log(Level.INFO, "Mouse Pressed");
//				 Point p = e.getPoint();
//				 l.canvas.setPoint1(p);
//				 l.canvas.setPoint2(p);
//		         l.canvas.repaint();
				
>>>>>>> master
			}

			public void mouseDragged(Listener l, MouseEvent e) {
<<<<<<< HEAD
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();
=======
//				Point p = e.getPoint();
//				l.canvas.setPoint2(p);
//		        l.canvas.repaint();
>>>>>>> master

			}	

		},
		TRIANGLE{
			public void mousePressed(Listener l, MouseEvent e) {
<<<<<<< HEAD
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
=======
				 //LOG.log(Level.INFO, "Mouse Pressed");
//				 Point p = e.getPoint();
//				 l.canvas.setPoint1(p);
>>>>>>> master
			}

			public void mouseReleased(Listener l, MouseEvent e) {
<<<<<<< HEAD
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();

=======
//				Point p = e.getPoint();
//				l.canvas.setPoint2(p);
//		        l.canvas.repaint();
				
>>>>>>> master
			}

			public void mouseDragged(Listener l, MouseEvent e) {
				//adam

			}

		},
		CIRCLE{
			public void mousePressed(Listener l, MouseEvent e) {
<<<<<<< HEAD
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
=======
				 //LOG.log(Level.INFO, "Mouse Pressed");
//				 Point p = e.getPoint();
//				 l.canvas.setPoint1(p);
>>>>>>> master
			}

			public void mouseReleased(Listener l, MouseEvent e) {
<<<<<<< HEAD
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();

=======
//				Point p = e.getPoint();
//				l.canvas.setPoint2(p);
//		        l.canvas.repaint();
				
>>>>>>> master
			}

			public void mouseDragged(Listener l, MouseEvent e) {
				//adam
			}

		},
		SQUARE{
			public void mousePressed(Listener l, MouseEvent e) {
<<<<<<< HEAD
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
=======
				 //LOG.log(Level.INFO, "Mouse Pressed");
//				 Point p = e.getPoint();
//				 l.canvas.setPoint1(p);
>>>>>>> master
			}

			public void mouseReleased(Listener l, MouseEvent e) {
<<<<<<< HEAD
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();

=======
//				Point p = e.getPoint();
//				l.canvas.setPoint2(p);
//		        l.canvas.repaint();
				
>>>>>>> master
			}

			public void mouseDragged(Listener l, MouseEvent e) {
				//adam

			}

		},
		NONE;

		//these are the possible methods each state can have
		public void mouseDragged(Listener l, MouseEvent e) {}
		public void mouseMoved(Listener l, MouseEvent e) {}
		public void mouseClicked(Listener l, MouseEvent e) {}
		public void mouseEntered(Listener l, MouseEvent e) {}
		public void mouseExited(Listener l, MouseEvent e) {}
		public void mousePressed(Listener l, MouseEvent e) {}
		public void mouseReleased(Listener l, MouseEvent e) {}

	}

    private static Path2D gimmeThePath(Vector<Integer> xs, Vector<Integer> ys){
        Path2D.Float path = new Path2D.Float();
        path.moveTo(xs.get(0), ys.get(0));

        for(int i=1;i<xs.size();i++){
            path.lineTo(xs.get(i), ys.get(i));
        }

        return path;
    }

}
