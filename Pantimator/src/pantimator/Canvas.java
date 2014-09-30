package pantimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
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

    public Canvas(){
        super();

        shapesToDraw = new LinkedList<Shape>();
        pointsToDraw = new LinkedList<Point>();

        cm = new ComponentMover();
        cm.setEdgeInsets( new Insets(-100, -100, -100, -100) );
        cm.setAutoLayout(true);

        createAndSetMouseListener();

        this.setLayout(new DragLayout());
        this.setBackground(Color.WHITE);
    }//end constructor

    public void setTool(Drawable d){
        this.tool = d;
        LOG.log(Level.INFO, "Setting tool to " + d);
    }//end drawLine

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        while(!pointsToDraw.isEmpty() && (pointsToDraw.size()%2 == 0)){
            shapesToDraw.add(new Line2D.Float(pointsToDraw.pop(), pointsToDraw.pop()));
        }

        while(!shapesToDraw.isEmpty()){
            Shape s = shapesToDraw.pop();
            LOG.log(Level.INFO, "Drawing shape: " + s);
            g2d.draw(s);
        }
    }//end paintcomponent

    private void createAndSetMouseListener(){
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(tool.equals(Drawable.Draw)){
                    pointsToDraw.add(new Point(e.getX(), e.getY()));
                    canvas.repaint();
                }//end if
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
                        break;
                    case Shape:
                        shapesToDraw.add(new Ellipse2D.Float((x1<x2?x1:x2), (y1<y2?y1:y2), Math.abs(x1-x2), Math.abs(y1-y2)));
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
