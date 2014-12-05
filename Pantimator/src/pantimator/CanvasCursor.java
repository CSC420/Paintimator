package pantimator;

import java.awt.*;

/**
 * Created by wilhelmi on 11/11/14.
 */
public enum CanvasCursor {
    PENCIL {
        @Override
        public Cursor getCursor() {
           // cursorImg = toolkit.getImage(PENCIL_IMG);
            cursorImg = toolkit.createImage(CanvasCursor.class.getResource(PENCIL_IMG));
            cursor = toolkit.createCustomCursor(cursorImg,hotspot,"Pencil");
            return cursor;
        }
    },
    ERASER {
        @Override
        public Cursor getCursor() {
           // cursorImg = toolkit.getImage(ERASER_IMG);
            cursorImg = toolkit.createImage(CanvasCursor.class.getResource(ERASER_IMG));
            cursor = toolkit.createCustomCursor(cursorImg,hotspot,"Eraser");
            return cursor;
        }
    },
    LINE {
        @Override
        public Cursor getCursor() {
           // cursorImg = toolkit.getImage(LINE_IMG);
            cursorImg = toolkit.createImage(CanvasCursor.class.getResource(LINE_IMG));
            cursor = toolkit.createCustomCursor(cursorImg,hotspot,"Line");
            return cursor;
        }
    },
    TEXT {
        @Override
        public Cursor getCursor() {
            //cursorImg = toolkit.getImage(TEXT_IMG);
            cursorImg = toolkit.createImage(CanvasCursor.class.getResource(TEXT_IMG));
            cursor = toolkit.createCustomCursor(cursorImg,hotspot,"Text");
            return cursor;
        }
    },
    CIRCLE {
        @Override
        public Cursor getCursor() {
            //cursorImg = toolkit.getImage(CIRCLE_IMG);
            cursorImg = toolkit.createImage(CanvasCursor.class.getResource(CIRCLE_IMG));
            cursor = toolkit.createCustomCursor(cursorImg,hotspot,"Circle");
            return cursor;
        }
    },
    RECTANGLE {
        @Override
        public Cursor getCursor() {
            //cursorImg = toolkit.getImage(RECTANGLE_IMG);
            cursorImg = toolkit.createImage(CanvasCursor.class.getResource(RECTANGLE_IMG));
            cursor = toolkit.createCustomCursor(cursorImg,hotspot,"Rectangle");
            return cursor;
        }
    },
    TRIANGLE {
        @Override
        public Cursor getCursor() {
            cursorImg = toolkit.createImage(CanvasCursor.class.getResource(TRIANGLE_IMG));
            cursor = toolkit.createCustomCursor(cursorImg,hotspot,"Triangle");
            return cursor;
        }
    },
    DEFAULT{
        @Override
        public Cursor getCursor(){
            cursor = Cursor.getDefaultCursor();
            return cursor;
        }
    };

    private static final String PENCIL_IMG = "images/pencil.png",
        ERASER_IMG = "images/eraser.png",
        LINE_IMG = "images/line.png",
        TEXT_IMG = "images/text.png",
        CIRCLE_IMG = "images/circle.png",
        RECTANGLE_IMG = "images/rectangle.png",
        TRIANGLE_IMG = "images/triangle.png";

    private static Point hotspot = new Point(0,0);
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image cursorImg;
    private static Cursor cursor;


    public abstract Cursor getCursor();

}
