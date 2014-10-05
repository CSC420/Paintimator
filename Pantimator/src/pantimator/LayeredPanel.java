package pantimator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by wilhelmi on 10/3/14.
 */
public class LayeredPanel extends JLayeredPane{

    private int canvasLayerIndex = 0,
                glassLayerIndex = 1,
                brushSize = 1;

    private JPanel canvas, glass;
    private HashMap<Shape, Color> toDrawOnCanvas, toDrawOnGlass;
    private Random random = new Random();
    private Color drawColor = new Color(0,0,0,0), canvasBG = new Color(0,0,0,0);

    public LayeredPanel(){
        toDrawOnCanvas = new HashMap<Shape, Color>();
        toDrawOnGlass = new HashMap<Shape, Color>();

        canvas = new Layer(toDrawOnCanvas);
        glass = new Layer(toDrawOnGlass);

        glass.setBackground(new Color(0, 0, 0, 0));
        canvas.setBackground(new Color(0,0,0,0));

//        canvas = new JPanel();
//        glass = new JPanel();

        this.add(canvas, canvasLayerIndex);
        this.add(glass, glassLayerIndex);
    }

    public void setDrawColor(Color c){
        drawColor = c;
    }

    public void setCanvasBG(Color c){
        canvasBG = c;
//        canvas.setBackground(canvasBG);
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

    public void drawOnRootPane(Shape s){
//        setLayer(canvas, canvasLayerIndex);
        canvas.setBounds(0,0,getWidth(),getHeight());
        toDrawOnCanvas.put(s, drawColor);
//        Graphics2D g = (Graphics2D)canvas.getGraphics();
//        g.drawLine(0,0,canvas.getWidth(),canvas.getHeight());
        canvas.repaint();
        System.out.println("#Items in toDrawOnCanvas: " + toDrawOnCanvas.size());

//        canvas.add(new JLabel("LABEL!!!!!!!!!"));
    }

    public void drawOnGlassPane(Shape s){
//        setLayer(glass, glassLayerIndex);
        glass.setBounds(0,0,getWidth(),getHeight());
        toDrawOnGlass.put(s, drawColor);
        glass.repaint();
        System.out.println("#Items in toDrawOnGlass: " + toDrawOnGlass.size());
    }

    public void clearRootPane(){
        toDrawOnCanvas.clear();
        repaint();
    }

    public void clearGlassPane(){
        toDrawOnGlass.clear();
        toDrawOnGlass.put(new Rectangle2D.Float(0, 0, glass.getWidth(), glass.getHeight()), new Color(0,0,0,0));
//        setLayer(canvas, glassLayerIndex);
        repaint();
        toDrawOnGlass.clear();
    }

    private class Layer extends JPanel{
        HashMap<Shape, Color> shapes;

        public Layer(HashMap<Shape, Color> shapes){
            this.shapes = shapes;
        }//end constructor

        @Override public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(brushSize));
            for(Map.Entry<Shape, Color> e : shapes.entrySet()){
                g2d.setColor(e.getValue());
                g2d.draw(e.getKey());
            }
        }//end paintComponent

    }//end Layer
}
