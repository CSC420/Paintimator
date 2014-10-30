package pantimator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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

    private int canvasLayerIndex = 1,
                glassLayerIndex = 0,
                brushSize = 1;

    private JPanel canvas, glass;
    private ArrayList<ShapeWrapper> toDrawOnCanvas, toDrawOnGlass;
   // private Random random = new Random();
    private Color drawColor; 
    private Color canvasBG;
    private Listener.LisState tool = Listener.LisState.DRAW;

    public LayeredPanel(){
    	toDrawOnCanvas = new ArrayList<ShapeWrapper>();
        toDrawOnGlass = new ArrayList<ShapeWrapper>();

        canvas = new Layer(toDrawOnCanvas);
        glass = new Layer(toDrawOnGlass);
        
        drawColor = Color.BLACK; 
        glass.setBackground(new Color(0, 0, 0, 0));
        canvas.setBackground(new Color(0, 0, 0, 0));
        this.add(canvas, canvasLayerIndex);
        this.add(glass, glassLayerIndex);
    }

    public void setDrawColor(Color c){
        drawColor = c;
    }

    public void setCanvasBG(Color c){
    	canvasBG = c;
        canvas.setBackground(c);
        canvas.repaint();
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
    

    public void drawOnRootPane(ShapeWrapper s){
    	canvas.setBounds(0,0,getWidth(),getHeight());
        s.setLineSize(brushSize);
        s.setColor(drawColor);
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
    	canvas.setBounds(0,0,getWidth(),getHeight());//and this does what
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
    
    public void setTool(Listener.LisState t){
        this.tool = t;
    }

    private class Layer extends JPanel{
        ArrayList<ShapeWrapper> shapes;

        public Layer(ArrayList<ShapeWrapper> shapes){
            this.shapes = shapes;
        }

        @Override public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setBackground(canvasBG);

            for(ShapeWrapper s : shapes){
                g2d.setStroke(new BasicStroke(s.getLineSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                if (!s.isFill()) {
                    g2d.setColor(s.getColor());
                    g2d.draw(s.getShape());
                } else {
                    if(tool.equals(Listener.LisState.ERASE)) {
                        int x = ((Double) s.getShape().getBounds().getX()).intValue();
                        int y = ((Double) s.getShape().getBounds().getY()).intValue();
                        g2d.clearRect(x, y, brushSize, brushSize);
                    } else if(tool.equals(Listener.LisState.TEXT)){
                        Font font = new Font("Serif", Font.PLAIN, 96);
                        g2d.setFont(font);
                        int x = ((Double)s.getShape().getBounds().getX()).intValue();
                        int y = ((Double)s.getShape().getBounds().getY()).intValue();
                        g2d.drawString(s.getString(), x, y);
                    }
                }
            }

        }

    }
}
