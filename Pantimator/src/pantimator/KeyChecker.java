package pantimator;

public class KeyChecker {
    public static boolean shiftPressed = false;

    public static boolean isShiftPressed(){
        synchronized (KeyChecker.class) {
            return shiftPressed;
        }
    }

}
