package pantimator;


import javax.swing.*;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class LayeredPanel extends JLayeredPane implements Serializable{

    private BufferedImage img;
    private int canvasLayerIndex = 1,
            glassLayerIndex = 0,
            brushSize = 2;

    private JPanel canvas, glass;
    private ArrayList<ShapeWrapper> toDrawOnCanvas, toDrawOnGlass, removedShapes;
    private Color drawColor = new Color(0,0,0,0);

    private Font font = getFont();
    private GlyphVector glyphVector;

    private CanvasCursor canvasCursor = CanvasCursor.DEFAULT;

    private Image background;

/*    public void clearLayeredPanel(){
        toDrawOnCanvas.clear();
        toDrawOnGlass.clear();
        removedShapes.clear();
        canvas.repaint();
    }*/

    public LayeredPanel(){
        toDrawOnCanvas = new ArrayList<ShapeWrapper>();
        toDrawOnGlass = new ArrayList<ShapeWrapper>();
        removedShapes = new ArrayList<ShapeWrapper>();

        canvas = new Layer(toDrawOnCanvas, false);
        glass = new Layer(toDrawOnGlass, true);

        drawColor = Color.BLACK;
        glass.setBackground(new Color(0, 0, 0, 0));

        this.add(canvas, canvasLayerIndex);
        this.add(glass, glassLayerIndex);
        canvas.setBackground(Color.WHITE);
        canvas.repaint();

    }
    /*
    * Copy Constructor -
    * Allows for creating a new layered panel based on a given one.
    * Added by Adam
    */
//    public LayeredPanel(LayeredPanel lp){
//        toDrawOnCanvas = new ArrayList<ShapeWrapper>();
//        toDrawOnGlass = new ArrayList<ShapeWrapper>();
//        removedShapes = new ArrayList<ShapeWrapper>();
//
//        canvas = new Layer(toDrawOnCanvas);
//        glass = new Layer(toDrawOnGlass);
//
//        background = lp.paneToBufferedImg();
//
//        drawColor = lp.drawColor;
//        glass.setBackground(new Color(0, 0, 0, 0));
//
//        this.add(canvas, canvasLayerIndex);
//        this.add(glass, glassLayerIndex);
//        canvas.setBackground(Color.WHITE);
//        canvas.repaint();
//    }

    public void setBackground(Image img){
        this.background = img;
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

    public Color getDrawColor(){
        return drawColor;
    }

    public void setBrushSize(int b){
        brushSize = b;
    }

    public int getBrushSize(){
        return brushSize;
    }

    public ArrayList<ShapeWrapper> getToDrawOnCanvas(){
        return toDrawOnCanvas;
    }
    public void setToDrawOnCanvas(ArrayList<ShapeWrapper> shapes){
        this.toDrawOnCanvas = shapes;
    }


    public JPanel getCanvas(){
        return canvas;
    }

    public void addText(String text, int x, int y){
        System.out.println("TEXT: " + text);
        JLabel l = new JLabel(text);
        l.setFont(l.getFont().deriveFont((float)brushSize*3));
        l.setForeground(drawColor);
        canvas.add(l);
        canvas.revalidate();

    }

    public void drawOnRootPane(ShapeWrapper s){
        canvas.setBounds(0,0,getWidth(),getHeight());
        if (!s.isImg()) {
            s.setLineSize(brushSize);
            s.setColor(drawColor);
        } else {
            System.out.println("Adding an image...");
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

    public CanvasCursor getCanvasCursor() {
        return canvasCursor;
    }

    public void setCanvasCursor(CanvasCursor canvasCursor) {
        this.canvasCursor = canvasCursor;
        setCursor(canvasCursor.getCursor());
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

    /* added by Jeremy
     * helper method used for saving the root pane to an image file
     */
    public BufferedImage paneToBufferedImg() {
        BufferedImage bi;
        if (canvas.getWidth() <= 0 || canvas.getHeight() <= 0) {
            bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        } else {
            bi = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        }

        canvas.paint(bi.getGraphics());

        return bi;
    }
    
    /**
     * Casts a Buffered Image to Image and returns it
     * @return
     */
    public Image paneToImg() {
    	return (Image) paneToBufferedImg();
    }
    
    private class Layer extends JPanel{
        ArrayList<ShapeWrapper> shapes;
        boolean isGlass;

        public Layer(ArrayList<ShapeWrapper> shapes, boolean isGlass){
            this.shapes = shapes;
            this.isGlass = isGlass;
        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;

            /* added by Adam
            * this draws a background image if there is one...
            */
            if(background != null && !isGlass){
                g2d.drawImage(background,0,0,null);
            }

            g2d.setBackground(Color.WHITE);
            for (ShapeWrapper s : shapes) {
                g2d.setStroke(new BasicStroke(s.getLineSize(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                if (s.isImg()) { // added by Jeremy; draws image to pane
                    g2d.drawImage(s.getImg(), 0, 0, null);
                } else if (s.isErase()) {
                    g2d.setColor(Color.WHITE);
                    g2d.draw(s.getShape());

                }else if(s.isText() && s.getString() != null) {
                    //fixing the text tool...
                    font = getFont().deriveFont((float)s.getLineSize() * 3);
                    glyphVector = font.createGlyphVector(getFontMetrics(font).getFontRenderContext(), s.getString());
                    Shape textShape = glyphVector.getOutline((float)s.getShape().getBounds().getX(),(float)s.getShape().getBounds().getY());
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setColor(s.getColor());
                    g2d.fill(textShape);
                }else if(s.isMagic()) {
                    
                	g2d.setStroke(new WobbleStroke(s.getLineSize()/2f, s.getLineSize()/2f));
                    g2d.setColor(s.getColor());
                    g2d.draw(s.getShape());
                }else {
                    g2d.setColor(s.getColor());
                    g2d.draw(s.getShape());
                }
            }//end for:shapes

        }//end paintComponent

    }//end Layer
}//end LayeredPanel