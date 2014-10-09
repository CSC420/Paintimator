package pantimator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class LayeredPanel extends JLayeredPane{

    private int canvasLayerIndex = 0,
                glassLayerIndex = 1,
                brushSize = 1;

    private JPanel canvas, glass;
    private HashMap<Shape, Color> toDrawOnCanvas, toDrawOnGlass;
   // private Random random = new Random();
    private Color drawColor; 
    private Color canvasBG;

    public LayeredPanel(){
        toDrawOnCanvas = new HashMap<Shape, Color>();
        toDrawOnGlass = new HashMap<Shape, Color>();

        canvas = new Layer(toDrawOnCanvas);
        glass = new Layer(toDrawOnGlass);
        
        drawColor = Color.BLACK; 
        canvasBG = Color.LIGHT_GRAY;

        glass.setBackground(new Color(0, 0, 0, 0));
        canvas.setBackground(new Color(0, 0, 0, 0));
       // canvas.setBackground(Color.BLUE);
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
        canvas.setBounds(0,0,getWidth(),getHeight());
        toDrawOnCanvas.put(s, drawColor);
        canvas.repaint(); //if this is just repaint() then line draws
        				  // smoothy but causes draw to look bad
    }

    public void drawOnGlassPane(Shape s){
        glass.setBounds(0,0,getWidth(),getHeight());
        toDrawOnGlass.put(s, drawColor);
        glass.repaint();
    }

    public void clearRootPane(){
        toDrawOnCanvas.clear();
        repaint();
    }

    public void clearGlassPane(){
        toDrawOnGlass.clear();
       // toDrawOnGlass.put(new Rectangle2D.Float(0, 0, glass.getWidth(), glass.getHeight()), new Color(0,0,0,0));
        repaint();
       // toDrawOnGlass.clear();
    }

    private class Layer extends JPanel{
        HashMap<Shape, Color> shapes;

        public Layer(HashMap<Shape, Color> shapes){
            this.shapes = shapes;
        }

        @Override public void paintComponent(Graphics g){
            super.paintComponent(g);
            //if this calls super we CANT set the background color here
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(brushSize));
            g2d.setColor(drawColor);
            
            for(Map.Entry<Shape, Color> e : shapes.entrySet()){
                g2d.setColor(e.getValue());
                g2d.draw(e.getKey());
            }
            //shapes.clear();
        }

    }
}
