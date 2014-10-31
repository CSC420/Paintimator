package pantimator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.nio.Buffer;

public class ShapeWrapper implements Comparable<ShapeWrapper>, Serializable {
    private Shape shape;
    private Color color;
    private int lineSize;
    private long timeStamp;
    private boolean fill = false;
    private String string = "";

    private BufferedImage bi;
    private boolean img = false;

    public ShapeWrapper(Shape s){
        shape = s;
    }

    /* added by Jeremy
     * Constructor to create a ShapeWrapper as a Buffered Image
     */
    public ShapeWrapper(BufferedImage bi, boolean i) {
        this.bi = bi;
        img = i;
    }

    public ShapeWrapper(Shape s, String st){
        this.shape = s;
        this.string = st;
    }

    public ShapeWrapper(Shape s, boolean f){
        this.shape = s;
        this.fill = f;
    }

    public ShapeWrapper(Shape s, Color c){
        shape = s;
        color = c;
    }

    public ShapeWrapper(Shape s, Color c, int l){
        shape = s;
        color = c;
        lineSize = l;
    }

    public ShapeWrapper(Shape s, Color c, int l, long t){
        shape = s;
        color = c;
        lineSize = l;
        timeStamp = t;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public int getLineSize() {
        return lineSize;
    }

    public void setLineSize(int lineSize) {
        this.lineSize = lineSize;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isFill(){
        return fill;
    }

    public void setFill(boolean f){
        this.fill = f;
    }

    /* added by Jeremy
     * Buffered Image getter
     */
    public BufferedImage getImg () {
        return bi;
    }

    /* added by Jeremy
     * boolean to let paint method know that this ShapeWrapper object is an image
     */
    public boolean isImg() {
        return img;
    }

    public void setString(String s){
        this.string = s;
    }

    public String getString(){
        return string;
    }

    @Override
    public int compareTo(ShapeWrapper o) {
       return 1;
    	// return Long.compare(this.getTimeStamp(), o.getTimeStamp());
    }
}
