package pantimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by wilhelmi on 9/24/14.
 */
public class Canvas extends JPanel {
    private final Logger LOG = Logger.getLogger(Canvas.class.getName());
    private final ComponentMover cm;
    private final JPanel canvas = this;

    private Drawable tool = Drawable.None;
    private LinkedList<Shape> shapesToDraw;
    private LinkedList<Point> pointsToDraw;
    private Point lastPoint;

    private int lineSize = 1;
    private int eraseSize;

    public Canvas(){
        super();

        shapesToDraw = new LinkedList<Shape>();
        pointsToDraw = new LinkedList<Point>();

        cm = new ComponentMover();
        cm.setEdgeInsets( new Insets(-100, -100, -100, -100) );
        cm.setAutoLayout(true);

        createAndSetMouseListener();

        this.setLayout(new DragLayout());
//        this.setBackground(Color.WHITE);
    }//end constructor

    public void setTool(Drawable d){
        this.tool = d;
        LOG.log(Level.INFO, "Setting tool to " + d);
    }//end drawLine

    public void setLineSize(int l){
        lineSize = l;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if(lastPoint == null && !pointsToDraw.isEmpty()){
            lastPoint = pointsToDraw.pop();
        }

            while(!pointsToDraw.isEmpty()){
                Point next = pointsToDraw.pop();
                shapesToDraw.add(new Line2D.Float(lastPoint, next));
                lastPoint = next;
            }

        while(!shapesToDraw.isEmpty()){
            Shape s = shapesToDraw.pop();
            LOG.log(Level.INFO, "Drawing shape: " + s);
            if (tool.equals(Drawable.Erase)) {
                g2d.setColor(canvas.getBackground());
                g2d.fill(s);
            } else {
                g2d.setStroke(new BasicStroke(lineSize));
                g2d.draw(s);
            }
        }
    }//end paintcomponent

    private void createAndSetMouseListener(){
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(tool.equals(Drawable.Draw)){
                    pointsToDraw.add(new Point(e.getX(), e.getY()));
                    canvas.repaint();
                } else if(tool.equals(Drawable.Erase)){
                    shapesToDraw.add(new Rectangle2D.Float(e.getX(), e.getY(), 10, 10));
                    canvas.repaint();
                }
            }
            @Override public void mouseMoved(MouseEvent e) {}
        });
        addMouseListener(new MouseListener() {
            int x1, x2, y1, y2;
            @Override public void mouseClicked(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                LOG.log(Level.INFO, "Mouse Pressed, tool = " + tool);
                x1 = e.getX();
                y1 = e.getY();

            }//end mousePressed

            @Override
            public void mouseReleased(MouseEvent e) {
                LOG.log(Level.INFO, "Mouse Released");
                x2 = e.getX();
                y2 = e.getY();

                switch (tool){
                    case Line:
                        shapesToDraw.add(new Line2D.Float(x1, y1, x2, y2));
                        canvas.repaint();
                        break;
                    case Draw:
                        pointsToDraw.clear();
                        lastPoint = null;
                        break;
                    case Shape:
                        switch (tool.getShapeType()){
                            case None:
                                break;
                            case Circle:
                                shapesToDraw.add(new Ellipse2D.Float((x1<x2?x1:x2), (y1<y2?y1:y2), Math.abs(x1-x2), Math.abs(y1-y2)));
                                break;
                            case Rectangle:
                                shapesToDraw.add(new Rectangle2D.Float(x1, y1, Math.abs(x2-x1), Math.abs(y2-y1)));
                                break;
                            case Triangle:
                                int x3 = Math.abs((x2-x1)/2);
                                //TODO Fix the 3rd point!!
                                Double y3 = (Math.abs(y2-y1)/2) + (Math.abs(y2-y1)/2)*(Math.sqrt(3));
                                shapesToDraw.add(new Polygon(new int[]{/*X points*/ x1, x2, x3},
                                        new int[]{/*Y points*/ y1, y2, y3.intValue()}, 3));
                                break;
                        }
                        canvas.repaint();
                        break;
                    case Text:
                        break;
                    case Erase:
                        break;
                    case None:
                        //Do Nothing!
                        break;
                }
            }//end mouseReleased
        });
    }//end createAndSetMouseListener


//    private class Line extends JPanel{
//        private int x1, y1, x2, y2;
//        public Line(int x1, int y1, int x2, int y2){
//            if(x2>x1){
//                this.x1 = 0;
//                this.x2 = x2-x1;
//            }else{
//                this.x1 = x1-x2;
//                this.x2 = 0;
//            }//end if/else
//            if(y2>y1){
//                this.y1 = 0;
//                this.y2 = y2-y1;
//            }else{
//                this.y1 = y1-y2;
//                this.y2 = 0;
//            }//end if/else
//
//            this.setMinimumSize(new Dimension((x2>x1?x2:x1),(y2>y1?y2:y1)));
//            this.setPreferredSize(new Dimension((x2>x1?x2:x1),(y2>y1?y2:y1)));
//            this.setMaximumSize(new Dimension((x2>x1?x2:x1),(y2>y1?y2:y1)));
//        }//end constructor
//        @Override
//        public void paintComponent(Graphics g){
//            LOG.log(Level.INFO, "Drawing line: " + x1 + " " + y1 + " " + x2 + " " + y2);
//
//            Graphics2D g2d = (Graphics2D) g;
//            g2d.setStroke(new BasicStroke(2f));
//            g2d.setColor(Color.black);
//            g2d.drawLine(x1, y1, x2, y2);
//
//        }//end paintComponent
//    }//end Line
}//end Canvas
