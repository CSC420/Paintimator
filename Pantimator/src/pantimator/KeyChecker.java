package pantimator;

/**
 * Created by wilhelmi on 11/8/14.
 */
public class KeyChecker {
    public static boolean shiftPressed = false;

    public static boolean isShiftPressed(){
        synchronized (KeyChecker.class) {
            return shiftPressed;
        }
    }

}
