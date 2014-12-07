package pantimator;

import java.awt.*;

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
    BUCKET {
        @Override
        public Cursor getCursor() {
            cursorImg = toolkit.createImage(CanvasCursor.class.getResource(BUCKET_IMG));
            cursor = toolkit.createCustomCursor(cursorImg,hotspot,"Bucket");
            return cursor;
        }
    },
    PAINT {
        @Override
        public Cursor getCursor() {
            cursorImg = toolkit.createImage(CanvasCursor.class.getResource(PAINT_IMG));
            cursor = toolkit.createCustomCursor(cursorImg,hotspot,"Paint");
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

    private static final String PENCIL_IMG = "images/drawCursor.png",
    	PAINT_IMG = "images/paintCursor.png",
        ERASER_IMG = "images/eraserCursor.png",
        LINE_IMG = "images/lineCursor.png",
        TEXT_IMG = "images/textCursor.png",
        CIRCLE_IMG = "images/cirCursor.png",
        RECTANGLE_IMG = "images/recCursor.png",
        TRIANGLE_IMG = "images/triCursor.png",
    	BUCKET_IMG = "images/bucketCursor.png";

    private static Point hotspot = new Point(0,0);
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static Image cursorImg;
    private static Cursor cursor;


    public abstract Cursor getCursor();

}
