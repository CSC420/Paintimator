package pantimator;

import java.awt.*;

/**
 * Created by wilhelmi on 10/7/14.
 */
public class ShapeWrapper implements Comparable<ShapeWrapper>{
    private Shape shape;
    private Color color;
    private int lineSize;
    private long timeStamp;
    private boolean fill = false;
    private String string = "";

    public ShapeWrapper(Shape s){
        shape = s;
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

    public void setString(String s){
        this.string = s;
    }

    public String getString(){
        return string;
    }

    @Override
    public int compareTo(ShapeWrapper o) {
        return Long.compare(this.getTimeStamp(), o.getTimeStamp());
    }
}
