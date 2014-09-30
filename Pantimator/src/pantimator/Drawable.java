package pantimator;

/**
 * Created by wilhelmi on 9/24/14.
 */
public enum Drawable {
    Line(ShapeType.None),
    Draw(ShapeType.None),
    Shape(ShapeType.Circle),
    Text(ShapeType.None),
    Erase(ShapeType.None),
    None(ShapeType.None);

    private ShapeType shapeType;

    private Drawable(ShapeType x){
        shapeType = x;
    }

    public ShapeType getShapeType(){
        return shapeType;
    }

    public void setShapeType(ShapeType t){
        shapeType = t;
    }

    public enum ShapeType{
        None(0),
        Circle(1),
        Rectangle(2),
        Triangle(3);

        private int type;

        private ShapeType(int x){
            type = x;
        }

        public int getType(){
            return type;
        }
    }
}
