package pantimator;

import pantimator.Listener.LisState;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

public class Paintimator extends JFrame{
    private final Logger LOG = Logger.getLogger(Paintimator.class.getName());
    private final boolean DEBUG = true;

    private final int GUI_WIDTH = 500, GUI_HEIGHT = 500;
    private final String FRAME_TITLE = "Paintimator!";
    private final Color canvasColor = new Color(132, 165, 165);

    private JPanel contentPane;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;

    private LayeredPanel layeredPanel;

    private static JInternalFrame canvasFrame;

    private JMenuBar menuBar;

    private Listener myListener;

    private JFileChooser fc;

<<<<<<< HEAD
=======
    private StorageUtil su = new StorageUtil(this);
    private LayeredPanelList lp = new LayeredPanelList();

>>>>>>> origin/Pres
    public Paintimator(){
        super();
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        fc = new JFileChooser();
        fc.addChoosableFileFilter(new ImageFilter());
        fc.setAcceptAllFileFilterUsed(false);

        //create a contentPane
        contentPane = new JPanel(new BorderLayout());
        layeredPanel = new LayeredPanel();


        this.setPreferredSize(new Dimension(1200, 750));
        this.setTitle(FRAME_TITLE);

        //draw panel
        layeredPanel.setCanvasBG(canvasColor);
        layeredPanel.setDrawColor(Color.BLACK);
        layeredPanel.setPreferredSize(new Dimension(700,500));
//        canvas.setLayout(null);
        //background for canvas
        canvasFrame = new JInternalFrame();
        canvasFrame.setAlignmentX(Component.CENTER_ALIGNMENT);
//        canvasFrame.setBackground(canvasColor);
        canvasFrame.setPreferredSize(new Dimension(700, 500));
        //center panel
        centerPanel = new JPanel();
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        centerPanel.setBackground(Color.GREEN);

        //bottom Panel
        bottomPanel = new JPanel();
        JButton button = new JButton();
        bottomPanel.add(button);

        //right panel
        rightPanel = new JPanel();
        createToolPanel();
        createMenu();
        //createAnimationPanel(); //want to put this in centerPanel under the
        //canvas with GridBag layout

        //listener
        myListener = new Listener(layeredPanel);
        layeredPanel.addMouseListener(myListener);
        layeredPanel.addMouseMotionListener(myListener);

        //add canvas to the canvasPanel so there appears to be a
        //
//        canvasFrame.add(layeredPanel);
        lp.add(layeredPanel);
        //centerPanel.add(layeredPanel);
        centerPanel.add(lp.getSelected());
        //centerPanel.add(bottomPanel);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(rightPanel, BorderLayout.WEST);


        //add the content pane to the frame
//        this.getContentPane().add(contentPane);
        this.setContentPane(contentPane);
        this.pack();
        this.setVisible(true);
<<<<<<< HEAD
        layeredPanel.clearRootPane();
=======
        //layeredPanel.clearRootPane();
        lp.getSelected().clearRootPane();
>>>>>>> origin/Pres
    }

    public void setCanvasGlassPane(JPanel jp){
        canvasFrame.setGlassPane(jp);
    }

    public Component getCanvasGlassPane(){
        return canvasFrame.getGlassPane();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //nimbus is default so this is not needed unless we change it
                //should keep look and feels off while figuring out the display
                //some look and feels drastically change how some components
                //naturally look and may change your code from doing what you want
				try {
					for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					System.exit(1);
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Setting the Look and Feel to Nimbus failed - falling back to default.");
				} catch (InstantiationException e) {
					e.printStackTrace();
					System.exit(1);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					System.exit(1);
				}

                Paintimator frame = new Paintimator();
                frame.setVisible(true);
            }
        });
    }

    private void createToolPanel(){
        JPanel toolPanel = new JPanel(new GridLayout(6,2)); //this will need to be GridBag
        toolPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        final JButton line, draw, text, erase, fg_color, bg_color, circle, square, triangle;

        line = new JButton("Line");
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.LINE);

            }
        });

        //this will need to change to a color chooser
        fg_color = new JButton("Line Color");
        fg_color.addActionListener(new ActionListener() {
<<<<<<< HEAD
            @Override
            public void actionPerformed(ActionEvent e) {
                layeredPanel.setDrawColor(JColorChooser.showDialog(null, "Choose a color", layeredPanel.getDrawColor()));

            }
        });

        bg_color = new JButton("Background Color");
        bg_color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layeredPanel.setCanvasBG(JColorChooser.showDialog(null, "Choose a color", layeredPanel.getCanvasBG()));
=======
            @Override
            public void actionPerformed(ActionEvent e) {
                //layeredPanel.setDrawColor(JColorChooser.showDialog(null, "Choose a color", layeredPanel.getDrawColor()));
                lp.getSelected().setDrawColor(JColorChooser.showDialog(null, "Choose a color", lp.getSelected().getDrawColor()));

            }
        });

        bg_color = new JButton("Background Color");
        bg_color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //layeredPanel.setCanvasBG(JColorChooser.showDialog(null, "Choose a color", layeredPanel.getCanvasBG()));
                lp.getSelected().setCanvasBG(JColorChooser.showDialog(null, "Choose a color", lp.getSelected().getCanvasBG()));
>>>>>>> origin/Pres

            }
        });

        circle = new JButton("Circle");
        circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.CIRCLE);
<<<<<<< HEAD
                layeredPanel.setTool(LisState.CIRCLE);
=======
                //layeredPanel.setTool(LisState.CIRCLE);
                lp.getSelected().setTool(LisState.CIRCLE);
>>>>>>> origin/Pres
            }
        });

        square = new JButton("Square");
        square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.SQUARE);
<<<<<<< HEAD
                layeredPanel.setTool(LisState.SQUARE);
=======
                //layeredPanel.setTool(LisState.SQUARE);
                lp.getSelected().setTool(LisState.SQUARE);
>>>>>>> origin/Pres
            }
        });


        triangle = new JButton("Triangle");
        triangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.TRIANGLE);
<<<<<<< HEAD
                layeredPanel.setTool(LisState.TRIANGLE);
=======
                //layeredPanel.setTool(LisState.TRIANGLE);
                lp.getSelected().setTool(LisState.TRIANGLE);
>>>>>>> origin/Pres
            }
        });

        //Free drawing
        draw = new JButton("Draw");
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.DRAW);
<<<<<<< HEAD
                layeredPanel.setTool(LisState.DRAW);
=======
                //layeredPanel.setTool(LisState.DRAW);
                lp.getSelected().setTool(LisState.DRAW);
>>>>>>> origin/Pres

            }
        });

        //if we allow for a text area we should allow for the font
        //to be changed and font size
        text = new JButton("Text");
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.TEXT);
<<<<<<< HEAD
                layeredPanel.setTool(LisState.TEXT);
=======
                //layeredPanel.setTool(LisState.TEXT);
                lp.getSelected().setTool(LisState.TEXT);
>>>>>>> origin/Pres
            }
        });

        //uses the current background color of the canvas panel
        //right now is just a circle like free drawing
        //uses the same brush size as free drawing
        erase = new JButton("Erase");
        erase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.ERASE);
<<<<<<< HEAD
                layeredPanel.setTool(LisState.ERASE);
=======
                //layeredPanel.setTool(LisState.ERASE);
                lp.getSelected().setTool(LisState.ERASE);
>>>>>>> origin/Pres
            }
        });

        //not sure if slider is best approach to selecting a brush size
        //but works just fine
        final JSlider lineSize = new JSlider(JSlider.HORIZONTAL, 1, 15, 1);
        final JLabel lineSizeLabel = new JLabel("Line Size: " + lineSize.getValue());
        lineSize.setPreferredSize(new Dimension(10, lineSize.getHeight()));
        lineSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //layeredPanel.setBrushSize(lineSize.getValue());
                lp.getSelected().setBrushSize(lineSize.getValue());
                lineSizeLabel.setText("Line Size: " + lineSize.getValue());
            }
        });

        //add all the buttons/slider/label to the toolpane
        toolPanel.add(line);
        toolPanel.add(draw);
        toolPanel.add(text);
        toolPanel.add(circle);
        toolPanel.add(square);
        toolPanel.add(triangle);
        toolPanel.add(erase);
        toolPanel.add(fg_color);
        toolPanel.add(bg_color);
        toolPanel.add(lineSizeLabel);
        toolPanel.add(lineSize);
        toolPanel.setBackground(Color.RED);

        //add the toolPanel to the rightPanel
        rightPanel.add(toolPanel);

    }

    private void createMenu(){
        menuBar = new JMenuBar();

        final BufferedImage[] img = new BufferedImage[1];

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
<<<<<<< HEAD
        JMenuItem loadImg = new JMenuItem("Load Image");
        JMenuItem saveImg = new JMenuItem("Save Image");
=======
        JMenuItem loadImg = new JMenuItem("Import Image");
        JMenuItem saveImg = new JMenuItem("Export Image");
        JMenuItem saveProject = new JMenuItem("Save");
        JMenuItem saveProjectAs = new JMenuItem("Save as...");
        JMenuItem loadProject = new JMenuItem("Open");
>>>>>>> origin/Pres

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

<<<<<<< HEAD
=======

        saveProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(su.getProjectName() !=null){
                    su.saveProject(lp);
                }else{
                    su.saveProjectAs(lp);
                }
            }
        });

        saveProjectAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                su.saveProjectAs(lp);
            }
        });

        loadProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LayeredPanelList lpTemp;
                lpTemp = su.openProject();

                if(lpTemp != null){
                    centerPanel.remove(lp.getSelected());
                    centerPanel.add(lpTemp.getSelected());
                    lp = lpTemp;
                    centerPanel.validate();
                    centerPanel.repaint();
                }
            }
        });

        fileMenu.add(loadProject);
        fileMenu.add(saveProject);
        fileMenu.add(saveProjectAs);
>>>>>>> origin/Pres
        fileMenu.add(loadImg);
        fileMenu.add(saveImg);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        this.setJMenuBar(menuBar);
    }

//	 private void createAnimationPanel(){
//	        animationPanel = new JPanel(new GridLayout(1,0));
//	        animationPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
//	        //TODO add animation controls, etc.
//
//	        if(DEBUG){
//	            animationPanel.add(new JButton("Play"));
//	            animationPanel.add(new JButton("Pause"));
//	            animationPanel.add(new JButton("Stop"));
//	        }//end if
//
//	        this.add(animationPanel, BorderLayout.PAGE_END);
//	    }//end createAnimationPanel
}//end Paintimator