package pantimator;

import java.awt.*;
<<<<<<< HEAD

public class ShapeWrapper implements Comparable<ShapeWrapper>{
=======
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.nio.Buffer;

/**
 * Created by wilhelmi on 10/7/14.
 */
public class ShapeWrapper implements Comparable<ShapeWrapper>, Serializable {
>>>>>>> Pres
    private Shape shape;
    private Color color;
    private int lineSize;
    private long timeStamp;
    private boolean fill = false;
    private String string = "";

<<<<<<< HEAD
=======
    private BufferedImage bi;
    private boolean img = false;

>>>>>>> Pres
    public ShapeWrapper(Shape s){
        shape = s;
    }

<<<<<<< HEAD
=======
    /* added by Jeremy
     * Constructor to create a ShapeWrapper as a Buffered Image
     */
    public ShapeWrapper(BufferedImage bi, boolean i) {
        this.bi = bi;
        img = i;
    }

>>>>>>> Pres
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

<<<<<<< HEAD
=======
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

>>>>>>> Pres
    public void setString(String s){
        this.string = s;
    }

    public String getString(){
        return string;
    }

    @Override
    public int compareTo(ShapeWrapper o) {
<<<<<<< HEAD
        return 1;
    	//return Long.compare(this.getTimeStamp(), o.getTimeStamp());
    }
}
=======
        return Long.compare(this.getTimeStamp(), o.getTimeStamp());
    }
}
>>>>>>> Pres
