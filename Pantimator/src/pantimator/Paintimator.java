package pantimator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Paintimator extends JFrame{

    private final String FRAME_TITLE = "Paintimator!";
    private final Color canvasColor = new Color(132, 165, 165);

    private JPanel contentPane;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private LayeredPanel layeredPanel;
    private ToolPanel toolPanel;
    private JMenuBar menuBar;
    private Listener myListener;
    private JFileChooser fc;
    private StorageUtil su = new StorageUtil(this);
    private LayeredPanelList layeredPanelList = new LayeredPanelList();

    public Paintimator() throws IOException{
        super();
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1200, 750));
        this.setTitle(FRAME_TITLE);

        fc = new JFileChooser();
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);

        //create a contentPane
        contentPane = new JPanel(new BorderLayout());
        layeredPanel = new LayeredPanel();

        //draw panel
        layeredPanel.setCanvasBG(canvasColor);
        layeredPanel.setDrawColor(Color.BLACK);
        layeredPanel.setPreferredSize(new Dimension(700,500));
        
        //center panel
        centerPanel = new JPanel();
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.setBackground(Color.GREEN);
        
        //bottom Panel
        bottomPanel = new JPanel();
        JButton button = new JButton();
        bottomPanel.add(button);

        //right panel
        toolPanel = new ToolPanel(this);
        
        //menu
        createMenu();

        //listener
        myListener = new Listener(layeredPanel);
        layeredPanel.addMouseListener(myListener);
        layeredPanel.addMouseMotionListener(myListener);

        //add everything to correct locations
        layeredPanelList.add(layeredPanel);
        centerPanel.add(layeredPanelList.getSelected());
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(toolPanel, BorderLayout.WEST);

        //set it and show it
        this.setContentPane(contentPane);
        this.pack();
        this.setVisible(true);
        layeredPanelList.getSelected().clearRootPane();
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Paintimator frame;
				try {
					frame = new Paintimator();
					frame.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }
        });
    }
    
    public void undo(){
    	layeredPanel.undo();
    }
    
    public void redo(){
    	layeredPanel.redo();
    }
    
    public void setListenerState(int num){
    	myListener.setLisState(num);
    }
    
    public void setPanelListTool(){
    	
    }
    
    public void setBrushSize(int size){
    	layeredPanelList.getSelected().setBrushSize(size);
    }


    private void createMenu(){
        menuBar = new JMenuBar();

        final BufferedImage[] img = new BufferedImage[1];

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenuItem loadImg = new JMenuItem("Import Image");
        JMenuItem saveImg = new JMenuItem("Export Image");
        JMenuItem saveProject = new JMenuItem("Save");
        JMenuItem saveProjectAs = new JMenuItem("Save as...");
        JMenuItem loadProject = new JMenuItem("Open");

        loadImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(Paintimator.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        img[0] = ImageIO.read(fc.getSelectedFile());
                        layeredPanel.importImgToPane(img[0]);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(new JPanel(), "Image could not be loaded.",
                                "Image error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        saveImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showSaveDialog(Paintimator.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        img[0] = ImageIO.read(fc.getSelectedFile());
                        layeredPanel.importImgToPane(img[0]);
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(new JPanel(), "Image could not be loaded.",
                                "Image error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        saveProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(su.getProjectName() !=null){
                    su.saveProject(layeredPanelList);
                }else{
                    su.saveProjectAs(layeredPanelList);
                }
            }
        });

        saveProjectAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                su.saveProjectAs(layeredPanelList);
            }
        });

        loadProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LayeredPanelList lpTemp;
                lpTemp = su.openProject();

                if(lpTemp != null){
                    centerPanel.remove(layeredPanelList.getSelected());
                    centerPanel.add(lpTemp.getSelected());
                    layeredPanelList = lpTemp;
                    centerPanel.validate();
                    centerPanel.repaint();
                }
            }
        });

        fileMenu.add(loadProject);
        fileMenu.add(saveProject);
        fileMenu.add(saveProjectAs);
        fileMenu.add(loadImg);
        fileMenu.add(saveImg);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        this.setJMenuBar(menuBar);
    }

}



