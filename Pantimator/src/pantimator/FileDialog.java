package pantimator;

import javax.swing.JFileChooser;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mark Williams on 10/7/2014.
 */
public class FileDialog {
    LayeredPanelList lstOut;
    //Need to check and make sure path is there
    final JFileChooser fc;

    FileDialog(JPanel jFrameIn){
        fc = new JFileChooser();
        fc.showOpenDialog(jFrameIn);

    }
    

}
