package pantimator;

import javax.swing.*;
import java.awt.*;
import java.io.*;


/**
 * Created by Mark
 */
public class StorageUtil {
    private JOptionPane jDPopup;
    private JFileChooser fc;
    private Component compParent;
    private String strProjectsDir = System.getProperty("user.home") + "/Paintimator/projects";
    private String strCurrentProject;
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

        //System.out.println(fc.getSelectedFile());
        try{
            if(fc.getSelectedFile() != null){
                this.strCurrentProject = fc.getSelectedFile().toString();
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()));
                System.out.println(ois.readObject());
                lstOut = (LayeredPanelList) ois.readObject();
                System.out.println(lstOut.size());
            }

        }catch (IOException ioe){

        }catch(ClassNotFoundException cnf){

        }
        return lstOut;
    }

    public void saveProject(LayeredPanelList panelListIn){
        //fc = new JFileChooser(strProjectsDir);
        //fc.showSaveDialog(compParent);

        try{
            FileOutputStream fos = new FileOutputStream(strCurrentProject);
            //FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+ "/testing" + "/projects/" + "test.pnt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(System.getProperty("user.home")+ "/Paintimator" + "/projects");
            oos.writeObject(panelListIn);
            oos.close();


        }catch(IOException ex){
            System.err.print(ex);
        }finally{


        }
    }

    public void saveProjectAs(LayeredPanelList panelListIn){
        fc = new JFileChooser(strProjectsDir);
        fc.showSaveDialog(compParent);

        try{
            if(fc.getSelectedFile() != null){
                this.strCurrentProject = fc.getSelectedFile().toString();
                FileOutputStream fos = new FileOutputStream(fc.getSelectedFile());
                //FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+ "/testing" + "/projects/" + "test.pnt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(System.getProperty("user.home")+ "/Paintimator" + "/projects");
                oos.writeObject(panelListIn);
                oos.close();
            }

        }catch(IOException ex){
            System.err.print(ex);
        }finally{

        }

    }


    /**
     *
     * @param strIn
     */
    private void setProjectName(String strIn){
        this.strCurrentProject = strIn;
    }

    /**
     *
     * @return
     */
    public String getProjectName(){
        return strCurrentProject;
    }


    /**
     *
     * @return
     */
    private boolean checkDirectory(){
        String strDirectory;

        //get User's home directory
        strDirectory = System.getProperty("user.home");
        strDirectory += "/Paintimator";
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
        strDirectory += "/Paintimator/bin";
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
        strDirectory += "/Paintimator/logs";
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
        strDirectory += "/Paintimator/projects";
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
                        "Could not find or create testing " + strInput[strInput.length - 1] +" directory.",
                        "Primary Directory Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
