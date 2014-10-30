package pantimator;

import javax.swing.*;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by wilhelmi on 10/3/14.
 */
public class LayeredPanel extends JLayeredPane implements Serializable{

    private BufferedImage img;

    private int canvasLayerIndex = 1,
                glassLayerIndex = 0,
                brushSize = 1;

    private JPanel canvas, glass;
    private ArrayList<ShapeWrapper> toDrawOnCanvas, toDrawOnGlass, removedShapes;
    private Random random = new Random();
    private Color drawColor = new Color(0,0,0,0), canvasBG;
    private ComponentMover componentMover = new ComponentMover();

    private Font font = getFont();
    private GlyphVector glyphVector;

    private Listener.LisState tool = Listener.LisState.DRAW;

    public LayeredPanel(){
        toDrawOnCanvas = new ArrayList<ShapeWrapper>();
        toDrawOnGlass = new ArrayList<ShapeWrapper>();
        removedShapes = new ArrayList<ShapeWrapper>();

        canvas = new Layer(toDrawOnCanvas);
        glass = new Layer(toDrawOnGlass);

        glass.setBackground(new Color(0, 0, 0, 0));
        canvas.setBackground(new Color(0,0,0,0));

        this.add(canvas, canvasLayerIndex);
        this.add(glass, glassLayerIndex);

        componentMover.setEdgeInsets( new Insets(-100, -100, -100, -100) );
        componentMover.setAutoLayout(true);
        canvas.setLayout(new DragLayout());

//        JLabel test = new JLabel("HELLO WORLD!!");
//        canvas.add(test);
//        componentMover.registerComponent(test);
    }

    public void undo(){
        if (!toDrawOnCanvas.isEmpty()) {
            removedShapes.add(toDrawOnCanvas.remove(toDrawOnCanvas.size()-1));
            canvas.repaint();
        }
    }

    public void redo(){
        if (!removedShapes.isEmpty()) {
            toDrawOnCanvas.add(removedShapes.remove(removedShapes.size()-1));
            canvas.repaint();
        }
    }

    public void setDrawColor(Color c){
        drawColor = c;
    }

    public void setCanvasBG(Color c){
        canvasBG = c;
        canvas.setBackground(c);
        canvas.repaint();
        System.out.println("Canvas BG: " + canvasBG);
    }

    public Color getDrawColor(){
        return drawColor;
    }

    public Color getCanvasBG(){
        return canvasBG;
    }

    public void setBrushSize(int b){
        brushSize = b;
    }

    public int getBrushSize(){
        return brushSize;
    }

    public ComponentMover getComponentMover(){
        return componentMover;
    }

    public JPanel getCanvas(){
        return canvas;
    }

//    public void addText(String text, int x, int y){
//        System.out.println("TEXT: " + text);
//        JLabel l = new JLabel(text);
//        l.setFont(l.getFont().deriveFont((float)brushSize*3));
//        l.setForeground(drawColor);
//        canvas.add(l);
//        componentMover.registerComponent(l, x, y);
//        canvas.revalidate();
//
//    }

    public void drawOnRootPane(ShapeWrapper s){
        canvas.setBounds(0,0,getWidth(),getHeight());
        if (!s.isImg()) {
            s.setLineSize(brushSize);
            s.setColor(drawColor);
        }
        s.setTimeStamp(System.nanoTime());
        toDrawOnCanvas.add(s);
        canvas.repaint();

    }

    public void drawOnGlassPane(ShapeWrapper s){
        glass.setBounds(0,0,getWidth(),getHeight());
        s.setLineSize(brushSize);
        s.setColor(drawColor);
        s.setTimeStamp(System.nanoTime());
        toDrawOnGlass.add(s);
        glass.repaint();
    }

    public void clearRootPane(){
        canvas.setBounds(0,0,getWidth(),getHeight());
        toDrawOnCanvas.clear();
        repaint();
    }

    public void clearGlassPane(){
        glass.setBounds(0,0,getWidth(),getHeight());
        toDrawOnGlass.clear();
        toDrawOnGlass.add(new ShapeWrapper(new Rectangle2D.Float(0, 0,
                glass.getWidth(), glass.getHeight()), new Color(0,0,0,0), brushSize));
        repaint();
        toDrawOnGlass.clear();
    }

    /* added by Jeremy
     * method which imports an image to the root pane that can be "edited"
     */
    public void importImgToPane(BufferedImage img) {
        if (this.img != img) {
            this.img = img;
            drawOnRootPane(new ShapeWrapper(img, true));
        }
    }

    public void setTool(Listener.LisState t){
        this.tool = t;
    }

    private class Layer extends JPanel{
        ArrayList<ShapeWrapper> shapes;

        public Layer(ArrayList<ShapeWrapper> shapes){
            this.shapes = shapes;
        }//end constructor

        @Override public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setBackground(canvasBG);

            for (ShapeWrapper s : shapes) {
                g2d.setStroke(new BasicStroke(s.getLineSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                if (s.isImg()) { // added by Jeremy; draws image to pane
                    g2d.drawImage(s.getImg(), 0, 0, null);
                } else if (s.isErase()) {
                    g2d.setColor(getCanvasBG());
                    g2d.draw(s.getShape());

                }else if(s.isText() && s.getString() != null) {
                    //fixing the text tool...
                    font = getFont().deriveFont((float)s.getLineSize() * 3);
                    glyphVector = font.createGlyphVector(getFontMetrics(font).getFontRenderContext(), s.getString());
                    Shape textShape = glyphVector.getOutline((float)s.getShape().getBounds().getX(),(float)s.getShape().getBounds().getY());
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(s.getColor());
                    g2d.fill(textShape);
                }else {
//                    g2d.setStroke(new WobbleStroke(2f, 2f));
                    g2d.setColor(s.getColor());
                    g2d.draw(s.getShape());
                }
//                else {
//                    if(tool.equals(Listener.LisState.ERASE)) {
//                        g2d.setColor(getCanvasBG());
//                        g2d.fill(s.getShape());
//
////                        g2d.clearRect(x, y, brushSize, brushSize);
//                    } else if(tool.equals(Listener.LisState.TEXT)){
//                        //fixing the text tool...
//                        font = getFont().deriveFont((float)s.getLineSize() * 2);
//                        glyphVector = font.createGlyphVector(getFontMetrics(font).getFontRenderContext(), s.getString());
//                        Shape textShape = glyphVector.getOutline((float)s.getShape().getBounds().getX(),(float)s.getShape().getBounds().getY());
//
//
//                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
////                        g2d.translate(s.getShape().getBounds().getX(),s.getShape().getBounds().getY());
////                        g2d.translate(100, 150);
//                        g2d.setColor(s.getColor());
//                        g2d.fill(textShape);
//                        g2d.translate(0,0);
//
//                        System.out.println("Trying to show the string: " + s.getString());
//////                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//////                                RenderingHints.VALUE_ANTIALIAS_ON);
////                        Font font = new Font("Serif", Font.PLAIN, 96);
////                        g2d.setFont(font);
////                        int x = ((Double)s.getShape().getBounds().getX()).intValue();
////                        int y = ((Double)s.getShape().getBounds().getY()).intValue();
////                        g2d.drawString(s.getString(), x, y);
//                    }
//                }
            }//end for

        }//end paintComponent

    }//end Layer
}
