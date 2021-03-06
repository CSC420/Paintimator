package pantimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;
import java.util.logging.Logger;

public class Listener implements MouseListener, MouseMotionListener  {
    static final Logger LOG = Logger.getLogger(Listener.class.getName());

    private LisState currentState;
    private static LayeredPanel layeredPanel;
    private Point p1, p2, p3;
    private Vector<Integer> xDrawPoints;
    private Vector<Integer> yDrawPoints;

    private static Shape tempShape;
    private static Component context;

    public Listener(LayeredPanel lp, Component c){
        context = c;
        layeredPanel = lp;
        currentState = LisState.NONE;
        xDrawPoints = new Vector<Integer>();
        yDrawPoints = new Vector<Integer>();

        registerShiftListener();
    }
    
    

    public void setLisState(int num){
        if(num == 1){
            currentState = LisState.LINE;
        }else if(num == 2){
            currentState = LisState.DRAW;
        }else if(num == 3){
            currentState = LisState.TRIANGLE;
        }else if(num == 4){
            currentState = LisState.ERASE;
        }else if(num == 5){
            currentState = LisState.CIRCLE;
        }else if(num == 6){
            currentState = LisState.SQUARE;
        }else if(num == 7){
            currentState = LisState.TEXT;
        }else if(num == 8){
            currentState = LisState.MAGIC;
        }else{
            currentState = LisState.NONE;
        }
    }
    
    public void updateLayeredPanel(LayeredPanel lp){
    	layeredPanel = lp;
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

    private static enum LisState {
        LINE {
            public void mousePressed(Listener l, MouseEvent e) {
                l.p1 = e.getPoint();
            }

            public void mouseReleased(Listener l, MouseEvent e) {

                l.layeredPanel.clearGlassPane();
//                l.p2 = e.getPoint();
//                Line2D.Float line = new Line2D.Float(l.p1, l.p2);
                l.layeredPanel.drawOnRootPane(new ShapeWrapper(tempShape));
            }

            public void mouseDragged(Listener l, MouseEvent e) {
                l.layeredPanel.clearGlassPane();
                Line2D.Float line;
                double theta =  Math.toDegrees(Math.atan2(e.getY()-l.p1.y, e.getX()-l.p1.x));
                int dx = Math.abs(l.p1.x - e.getX());
               // LOG.log(Level.INFO, "Angle of line: " + theta);

                if(KeyChecker.isShiftPressed()){
                //    LOG.log(Level.INFO, "SHIFT key pressed!!");
                    //if the shift key is pressed, make a straight line...

                    if((theta < 22.5 && theta > -22.5) || (theta > 157.5 || theta < -157.5)){
                        line = new Line2D.Float(l.p1, new Point(e.getX(), l.p1.y));
                    }else if(theta < 67.5  && theta > 22.5){
                        line = new Line2D.Float(l.p1, new Point(l.p1.x+dx, l.p1.y+dx));
                    } else if(theta < 157.5 && theta > 112.5){
                        line = new Line2D.Float(l.p1, new Point(l.p1.x-dx, l.p1.y+dx));
                    } else if(theta < -22.5 && theta > -67.5){
                        line = new Line2D.Float(l.p1, new Point(l.p1.x+dx, l.p1.y-dx));
                    } else if(theta < -112.5 && theta > -157.5) {
                        line = new Line2D.Float(l.p1, new Point(l.p1.x-dx, l.p1.y-dx));
                    } else {
                        line = new Line2D.Float(l.p1, new Point(l.p1.x, e.getY()));
                    }

                } else {
                    line = new Line2D.Float(l.p1, e.getPoint());
                }

                l.layeredPanel.drawOnGlassPane(new ShapeWrapper(line));
                tempShape = line;
            }

        },
        DRAW{
            Path2D.Float path2 = new Path2D.Float();
            boolean dragging = false;

            public void mousePressed(Listener l, MouseEvent e) {
                l.p1 = e.getPoint();
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());

            }

            public void mouseReleased(Listener l, MouseEvent e) {
//                l.xDrawPoints.add(e.getX());
//                l.yDrawPoints.add(e.getY());

                l.layeredPanel.clearGlassPane();
//                Path2D p = gimmeThePath(l.xDrawPoints, l.yDrawPoints);
//                l.layeredPanel.clearGlassPane();
                l.layeredPanel.drawOnRootPane(new ShapeWrapper(tempShape));
                l.xDrawPoints.clear();
                l.yDrawPoints.clear();

            }

            public void mouseDragged(Listener l, MouseEvent e) {
                l.layeredPanel.clearGlassPane();
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());

                Path2D p = gimmeThePath(l.xDrawPoints, l.yDrawPoints);

                l.layeredPanel.drawOnGlassPane(new ShapeWrapper(p));
                tempShape = p;
            }

        },
        TRIANGLE{
            public void mousePressed(Listener l, MouseEvent e) {

                l.p1 = e.getPoint();
            }

            public void mouseReleased(Listener l, MouseEvent e) {
                l.layeredPanel.clearGlassPane();
//                int[] xs = new int[]{l.p1.x, l.p3.x, l.p2.x};
//                int[] ys = new int[]{l.p2.y, l.p3.y, l.p2.y};

//                Polygon p = new Polygon(xs, ys, xs.length);
                l.layeredPanel.drawOnRootPane(new ShapeWrapper(tempShape));
            }

            public void mouseDragged(Listener l, MouseEvent e) {
                l.p2 = e.getPoint();

                l.layeredPanel.clearGlassPane();
                Polygon p = null;
                int x1, y1, x2, y2, midx, midy;
                x1 = l.p1.x;
                y1 = l.p1.y;

                int dx = e.getX()-l.p1.x;

                if(KeyChecker.isShiftPressed()) {
                  //  LOG.log(Level.INFO, "SHIFT key pressed!!");
                    x2 = l.p1.x+dx;
                    y2 = l.p1.y+dx;
                } else {
                    x2 = l.p2.x;
                    y2 = l.p2.y;
                }
                midx = (x1 + (x2 - x1) / 2);
                midy = y1;

                l.p3 = new Point(midx, midy);

                p = new Polygon(new int[]{x1, midx, x2}, new int[]{y2, midy, y2}, 3);
                l.layeredPanel.drawOnGlassPane(new ShapeWrapper(p));
                tempShape = p;
            }

        },
        ERASE {

            public void mousePressed(Listener l, MouseEvent e) {
                l.p1 = e.getPoint();
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());
            }

            public void mouseReleased(Listener l, MouseEvent e) {
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());

                l.layeredPanel.clearGlassPane();
                Path2D p = gimmeThePath(l.xDrawPoints, l.yDrawPoints);
                l.layeredPanel.clearGlassPane();
                l.layeredPanel.drawOnRootPane(new ShapeWrapper(p, true));
                l.xDrawPoints.clear();
                l.yDrawPoints.clear();

            }

            public void mouseDragged(Listener l, MouseEvent e) {
                l.layeredPanel.clearGlassPane();
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());

                Path2D p = gimmeThePath(l.xDrawPoints, l.yDrawPoints);

                l.layeredPanel.drawOnGlassPane(new ShapeWrapper(p, true));
            }

        },
        CIRCLE{
            public void mousePressed(Listener l, MouseEvent e) {
                l.p1 = e.getPoint();
            }

            public void mouseReleased(Listener l, MouseEvent e) {

//                l.p2 = e.getPoint();
                l.layeredPanel.clearGlassPane();
//                Ellipse2D.Float c = new Ellipse2D.Float((l.p1.x<l.p2.x?l.p1.x:l.p2.x),
//                        (l.p1.y<l.p2.y?l.p1.y:l.p2.y), Math.abs(l.p1.x-l.p2.x), Math.abs(l.p1.y-l.p2.y));
                l.layeredPanel.drawOnRootPane(new ShapeWrapper(tempShape));

            }

            public void mouseDragged(Listener l, MouseEvent e) {
                l.layeredPanel.clearGlassPane();
                int x2 = e.getX();
                int y2 = e.getY();


                Ellipse2D.Float c = null;

                if(KeyChecker.isShiftPressed()) {
               //     LOG.log(Level.INFO, "SHIFT key pressed!!");

                    float radius = (float)Math.sqrt((l.p1.x-x2)*(l.p1.x-x2) + (l.p1.y-y2)*(l.p1.y-y2))/2f;

                    c = new Ellipse2D.Float((l.p1.x<x2?l.p1.x:x2),
                            (l.p1.y<y2?l.p1.y:y2), radius, radius);



                } else{
                    c = new Ellipse2D.Float((l.p1.x<x2?l.p1.x:x2),
                            (l.p1.y<y2?l.p1.y:y2), Math.abs(l.p1.x-x2), Math.abs(l.p1.y-y2));
                }

                l.layeredPanel.drawOnGlassPane(new ShapeWrapper(c));
                tempShape = c;
            }

        },
        SQUARE{
            public void mousePressed(Listener l, MouseEvent e) {
                l.p1 = e.getPoint();
            }

            public void mouseReleased(Listener l, MouseEvent e) {

//                l.p2 = e.getPoint();
//
//                Rectangle2D.Float r = new Rectangle2D.Float((l.p1.x<l.p2.x?l.p1.x:l.p2.x),
//                        (l.p1.y<l.p2.y?l.p1.y:l.p2.y),Math.abs(l.p1.x-l.p2.x), Math.abs(l.p1.y-l.p2.y));

                l.layeredPanel.clearGlassPane();
                l.layeredPanel.drawOnRootPane(new ShapeWrapper(tempShape));

            }

            public void mouseDragged(Listener l, MouseEvent e) {
                l.layeredPanel.clearGlassPane();
                int x2 = e.getX();
                int y2 = e.getY();

                Rectangle2D.Float r = null;

                if(KeyChecker.isShiftPressed()) {
              //      LOG.log(Level.INFO, "SHIFT key pressed!!");
                    float radius = (float)Math.sqrt((l.p1.x-x2)*(l.p1.x-x2) + (l.p1.y-y2)*(l.p1.y-y2))/2f;
                    r = new Rectangle2D.Float((l.p1.x<x2?l.p1.x:x2),
                            (l.p1.y<y2?l.p1.y:y2),radius,radius);

                } else {
                    r = new Rectangle2D.Float((l.p1.x<x2?l.p1.x:x2),
                            (l.p1.y<y2?l.p1.y:y2),Math.abs(l.p1.x-x2), Math.abs(l.p1.y-y2));
                }

                l.layeredPanel.drawOnGlassPane(new ShapeWrapper(r));
                tempShape = r;
            }

        },

        TEXT{
            public void mouseClicked(Listener l, MouseEvent e){
                String s = JOptionPane.showInputDialog(context, "Enter text here:", "Text", JOptionPane.PLAIN_MESSAGE);
                l.layeredPanel.drawOnRootPane(new ShapeWrapper(new Rectangle2D.Float(e.getX(), e.getY(),0,0), s));

            }
        },
        MAGIC{
            public void mousePressed(Listener l, MouseEvent e) {
                l.p1 = e.getPoint();
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());

            }

            public void mouseReleased(Listener l, MouseEvent e) {
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());

                l.layeredPanel.clearGlassPane();
                Path2D p = gimmeThePath(l.xDrawPoints, l.yDrawPoints);
                l.layeredPanel.clearGlassPane();
                ShapeWrapper s = new ShapeWrapper(p);
                s.setMagic(true);
                l.layeredPanel.drawOnRootPane(s);
                l.xDrawPoints.clear();
                l.yDrawPoints.clear();

            }

            public void mouseDragged(Listener l, MouseEvent e) {
                l.layeredPanel.clearGlassPane();
                l.xDrawPoints.add(e.getX());
                l.yDrawPoints.add(e.getY());

                Path2D p = gimmeThePath(l.xDrawPoints, l.yDrawPoints);
                ShapeWrapper s = new ShapeWrapper(p);
                s.setMagic(true);

                l.layeredPanel.drawOnGlassPane(s);
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

    public LisState getCurrentState(){
        return currentState;
    }

    private static Path2D gimmeThePath(Vector<Integer> xs, Vector<Integer> ys){
        Path2D.Float path = new Path2D.Float();
        path.moveTo(xs.get(0), ys.get(0));

        for(int i=1;i<xs.size();i++){
            path.lineTo(xs.get(i), ys.get(i));
        }

        return path;
    }

    private void registerShiftListener(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                synchronized (KeyChecker.class){
                    switch (e.getID()){
                        case KeyEvent.KEY_PRESSED:
                            if(e.getKeyCode()==KeyEvent.VK_SHIFT){
                                KeyChecker.shiftPressed = true;
                            }
                            break;
                        case KeyEvent.KEY_RELEASED:
                            if(e.getKeyCode()==KeyEvent.VK_SHIFT){
                                KeyChecker.shiftPressed = false;
                            }
                            break;
                    }
                    return false;
                }
            }
        });
    }

}