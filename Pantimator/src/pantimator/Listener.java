package pantimator;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Logger;

public class Listener implements MouseListener, MouseMotionListener  {
	final Logger LOG = Logger.getLogger(Canvas.class.getName());
	private LisState currentState;
	private Canvas canvas;

	public Listener(Canvas c){
		canvas = c;
		currentState = LisState.NONE;
	}

	public void setLisState(LisState s){
		currentState = s;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		currentState.mouseDragged(this, e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		currentState.mouseMoved(this, e);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		currentState.mouseClicked(this, e);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		currentState.mouseEntered(this, e);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		currentState.mouseExited(this, e);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		currentState.mousePressed(this, e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		currentState.mouseReleased(this, e);

	}

	public static enum LisState {
		LINE {

			public void mousePressed(Listener l, MouseEvent e) {
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
			}

			public void mouseReleased(Listener l, MouseEvent e) {
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();

			}


			public void mouseDragged(Listener l, MouseEvent e) {
				//adam


			}

		},
		DRAW{
			public void mousePressed(Listener l, MouseEvent e) {
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
				l.canvas.setPoint2(p);
				l.canvas.repaint();

			}
			public void mouseDragged(Listener l, MouseEvent e) {
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();
			}	

		},
		ERASE {

			public void mousePressed(Listener l, MouseEvent e) {
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
				l.canvas.setPoint2(p);
				l.canvas.repaint();

			}

			public void mouseDragged(Listener l, MouseEvent e) {
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();

			}	

		},
		TRIANGLE{
			public void mousePressed(Listener l, MouseEvent e) {
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
			}

			public void mouseReleased(Listener l, MouseEvent e) {
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();

			}

			public void mouseDragged(Listener l, MouseEvent e) {
				//adam

			}

		},
		CIRCLE{
			public void mousePressed(Listener l, MouseEvent e) {
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
			}

			public void mouseReleased(Listener l, MouseEvent e) {
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();

			}

			public void mouseDragged(Listener l, MouseEvent e) {
				//adam

			}

		},
		SQUARE{
			public void mousePressed(Listener l, MouseEvent e) {
				//LOG.log(Level.INFO, "Mouse Pressed");
				Point p = e.getPoint();
				l.canvas.setPoint1(p);
			}

			public void mouseReleased(Listener l, MouseEvent e) {
				Point p = e.getPoint();
				l.canvas.setPoint2(p);
				l.canvas.repaint();

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

}
