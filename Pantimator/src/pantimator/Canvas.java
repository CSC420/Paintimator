package pantimator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by wilhelmi on 9/24/14.
 */
public class Canvas extends JPanel {
    private final Logger LOG = Logger.getLogger(Canvas.class.getName());
    private State currentState;
	private Point p1;
	private Point p2;
	private Color currentColor;
	private Color currentBGColor;
	private int brushSize;

    private ArrayList<Shape> shapesToDraw = new ArrayList<Shape>();

    public Canvas(Color backColor){
        super();
        currentColor = Color.BLACK;
        brushSize = 1;
        currentBGColor = backColor;
        currentState = State.NONE;

        setLayout(new BorderLayout());
    }

    public void setTool(State d){
        this.currentState = d;
        System.out.println("State changed to " + currentState);
        //LOG.log(Level.INFO, "Setting tool to " + d);
    }

     public void setPoint1(Point p){
    	 p1 = p;
     }

     public void setColor(Color c){
    	 currentColor = c;
     }

    public Color getCurrentColor(){
        return currentColor;
    }

    public void setPoint2(Point p){
    	 p2 = p;
     }

    public Point getP1(){
        return p1;
    }

    public Point getP2(){
        return p2;
    }

    public void setBrushSize(int b){
    	 brushSize = b+2;
     }

    public int getBrushSize(){
        return brushSize;
    }


    @Override
    public void paintComponent(Graphics g) {
    	currentState.paintComponent(this, g);
    }
    
    
    public static enum State {
		LINE {
			public void paintComponent(Canvas c, Graphics g) {
				Graphics2D g2d = (Graphics2D) g; 
				g2d.setColor(c.currentColor);
				g2d.setStroke(new BasicStroke(c.brushSize));
				g2d.draw(new Line2D.Double(c.p1, c.p2));
                System.out.println("LINES!!!!");
            }
		},
		DRAW{
			public void paintComponent(Canvas c, Graphics g) {
				Graphics2D g2d = (Graphics2D) g; 
				g2d.setColor(c.currentColor);
				g2d.setStroke(new BasicStroke(c.brushSize));
				g2d.draw(new Line2D.Double(c.p1, c.p2));
				c.p1 = c.p2;
                System.out.println("DRAWING!!!!!");
            }
		},
		ERASE {
			public void paintComponent(Canvas c, Graphics g) {
				Graphics2D g2d = (Graphics2D) g; 
				g2d.setColor(c.currentBGColor);
				g2d.setStroke(new BasicStroke(c.brushSize));
				g2d.draw(new Line2D.Double(c.p1, c.p2));
				c.p1 = c.p2;
			}
		},
		TRIANGLE{
			public void paintComponent(Canvas c, Graphics g) {
				Graphics2D g2d = (Graphics2D) g; 
				int x3 = Math.abs((c.p2.x-c.p1.x)/2);
//		        TODO Fix the 3rd point!!
	            Double y3 = (Math.abs(c.p2.y-c.p1.y)/2) + (Math.abs(c.p2.y-c.p1.y)/2)*(Math.sqrt(3));
				g2d.setColor(c.currentColor);
				g2d.setStroke(new BasicStroke(c.brushSize));
				g2d.draw(new Polygon(new int[]{/*X points*/ c.p1.x, c.p2.x, x3},
		                  new int[]{/*Y points*/ c.p1.y, c.p2.y, y3.intValue()}, 3));
			}
		},
		CIRCLE{
			//having issues painting a circle
			public void paintComponent(Canvas c, Graphics g) {
				Graphics2D g2d = (Graphics2D) g; 
				g2d.setColor(c.currentColor);
				g2d.setStroke(new BasicStroke(c.brushSize));
				g2d.draw(new Ellipse2D.Float((c.p1.x<c.p2.x?c.p1.x:c.p2.x), (c.p1.y<c.p2.y?c.p1.y:c.p2.y), Math.abs(c.p1.x-c.p2.x), Math.abs(c.p1.y-c.p2.y)));
			}
		},
		SQUARE{
			
			//having issues painting the square
			public void paintComponent(Canvas c, Graphics g) {
				
				Graphics2D g2d = (Graphics2D) g; 
				g2d.setColor(c.currentColor);
				g2d.setStroke(new BasicStroke(c.brushSize));
				g2d.draw(new Rectangle2D.Float(c.p1.x, c.p1.y, Math.abs(c.p2.x-c.p1.x), Math.abs(c.p2.y-c.p1.y)));
			}
		},
		NONE;

		//these are the possible methods each state can have
		public void paintComponent(Canvas c, Graphics g) {}
	}
    
    //adams code from his listener
//	  switch (tool.getShapeType()){
//    case None:
//        break;
//    case Circle:
//        shapesToDraw.add(new Ellipse2D.Float((x1<x2?x1:x2), (y1<y2?y1:y2), Math.abs(x1-x2), Math.abs(y1-y2)));
//        break;
//    case Rectangle:
//        shapesToDraw.add(new Rectangle2D.Float(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1)));
//        break;
//    case Triangle:
//        int x3 = Math.abs((x2-x1)/2);
//        //TODO Fix the 3rd point!!
//        Double y3 = (Math.abs(y2-y1)/2) + (Math.abs(y2-y1)/2)*(Math.sqrt(3));
//        shapesToDraw.add(new Polygon(new int[]{/*X points*/ x1, x2, x3},
//                new int[]{/*Y points*/ y1, y2, y3.intValue()}, 3));
//        break;

}//end Canvas