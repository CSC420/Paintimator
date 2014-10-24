package pantimator;

import javax.swing.*;
import java.awt.*;
import java.io.File;


/**
 * Created by Mark
 * Changes to push
 * I Cant push my file!
 */
public class StorageUtil {
    JOptionPane jDPopup;
    JFileChooser fc;
    Component compParent;
    String strProjectsDir = System.getProperty("user.home") + "/paintmator/projects";

    /**
     *
     * @param parentIn
     */
    public StorageUtil(Component parentIn){
        compParent = parentIn;
        if(checkDirectory()){
            if(checkBin()){

            }

            if(checkLog()){

            }

            if(checkProjects()){

            }
        }
    }



    public LayeredPanelList openProject(){
        LayeredPanelList lstOut = null;
        fc = new JFileChooser(strProjectsDir);
        fc.showOpenDialog(compParent);

        return lstOut;
    }

    public void saveProject(LayeredPanelList panelListIn){
        fc = new JFileChooser(strProjectsDir);
        fc.showSaveDialog(compParent);

    }

    public void saveProjectAs(LayeredPanelList panelListIn){
        fc = new JFileChooser(strProjectsDir);
        fc.showSaveDialog(compParent);

    }

    /**
     *
     * @return
     */
    private boolean checkDirectory(){
        String strDirectory;

        //get User's home directory
        strDirectory = System.getProperty("user.home");
        strDirectory += "/paintmator";
        return checkDirectory(strDirectory);
    }

    /**
     *
     * @return
     */
    private boolean checkBin(){
        String strDirectory;

        //get User's home directory
        strDirectory = System.getProperty("user.home");
        strDirectory += "/paintmator/bin";
        return checkDirectory(strDirectory);
    }

    /**
     *
     * @return
     */
    private boolean checkLog(){
        String strDirectory;

        //get User's home directory
        strDirectory = System.getProperty("user.home");
        strDirectory += "/paintmator/logs";
        return checkDirectory(strDirectory);
    }

    /**
     *
     * @return
     */
    private boolean checkProjects(){
        String strDirectory;

        //get User's home directory
        strDirectory = System.getProperty("user.home");
        strDirectory += "/paintmator/projects";
        return checkDirectory(strDirectory);
    }

    /**
     *
     * @param strDirectory
     * @return
     */
    private boolean checkDirectory(String strDirectory){
        File fDir;
        String[] strInput = strDirectory.split("/");
        fDir = new File(strDirectory);
        System.out.println(strInput[strInput.length - 1]);

        if(fDir.exists() && fDir.isDirectory()){
            //directory exists
            System.out.println("Found Directory");

        }else{
            //Directory Does not exist
            System.out.println("Directory does not exist");
            fDir.mkdir();

            if(!(fDir.exists())){
                jDPopup.showMessageDialog(compParent,
                        "Could not find or create Paintimator " + strInput[strInput.length - 1] +" directory.",
                        "Primary Directory Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
