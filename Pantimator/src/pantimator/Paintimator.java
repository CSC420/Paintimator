package pantimator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Paintimator extends JFrame{
<<<<<<< HEAD
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

    private StorageUtil su = new StorageUtil(this);
    private LayeredPanelList lp = new LayeredPanelList();

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
        centerPanel.add(lp.getSelected());
        //centerPanel.add(layeredPanel);
        //centerPanel.add(bottomPanel);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(rightPanel, BorderLayout.WEST);


        //add the content pane to the frame
//        this.getContentPane().add(contentPane);
        this.setContentPane(contentPane);
        this.pack();
        this.setVisible(true);
        //layeredPanel.clearRootPane();
        lp.getSelected().clearRootPane();
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
=======
	private static final long serialVersionUID = -9178351480074121591L;
	
	private final String FRAME_TITLE = "Paintimator!";
	private BackgroundPanel contentPane;
	private JPanel centerPanel;
	//private BackgroundPanel background;
	private AnimationPane animationPane;
	private LayeredPanel layeredPanel;
	private ToolPanel toolPanel;
	private MyMenu menu;
	private Listener myListener;
	private JFileChooser fc;
	private StorageUtil su;
	private LayeredPanelList layeredPanelList;
	
	private GridBagConstraints gbc;
	
	private int height, width;

	public Paintimator() throws IOException{
		super();
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle(FRAME_TITLE);

		
		su = new StorageUtil(this);
		layeredPanelList = new LayeredPanelList();
		fc = new JFileChooser();
		fc.addChoosableFileFilter(new ImageFilter());
		fc.setAcceptAllFileFilterUsed(false);
		

		//create a contentPane
       // contentPane = new BackgroundPanel("images/Background.png");
		contentPane = new BackgroundPanel();
        contentPane.setLayout(new BorderLayout());

        
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(screenSize);
		width = screenSize.width;
		height = screenSize.height;
        
        
        //canvas panel
		layeredPanel = new LayeredPanel();
		layeredPanel.setDrawColor(Color.BLACK);
		layeredPanel.setPreferredSize(new Dimension(width-450,height-300));
		layeredPanelList.add(layeredPanel);

		//center panel
		centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.setBackground(Color.LIGHT_GRAY);
		//centerPanel.setOpaque(false);

		//animation panel
		animationPane = new AnimationPane();

		//tool panel
		toolPanel = new ToolPanel(this);
		//toolPanel.setOpaque(false);

		//menu bar
		menu = new MyMenu(this);
		this.setJMenuBar(menu);

		//listener
		addListeners(layeredPanel);

		//add everything to correct locations
		gbc = new GridBagConstraints();
		//gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.50;
		gbc.weighty = 0.50;
		gbc.gridx = 0;
		gbc.gridy = 0;
		centerPanel.add(layeredPanelList.getSelected(), gbc);
		gbc.gridy = 1;
		animationPane.updateAnimation(layeredPanelList.getSelected(), true);
		centerPanel.add(animationPane, gbc);

		//add panels to the content pane
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(toolPanel, BorderLayout.WEST);

		//set it and show it
		this.setContentPane(contentPane);
		this.pack();
		this.setVisible(true);
		setSize(50,50) ;
		setSize(width,height); 
		layeredPanelList.getSelected().clearRootPane();
		
	}
	
	
	/*
	 * Method to easily add/update listeners and canvas
	 */
	private void addListeners(LayeredPanel lp) {
		myListener = new Listener(lp);
		lp.addMouseListener(myListener);
		lp.addMouseMotionListener(myListener);
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

	public void setBrushSize(int size){
		layeredPanelList.getSelected().setBrushSize(size);
	}

	public void setDrawColor(Color c){
		layeredPanelList.getSelected().setDrawColor(c);
	}

	//methods to load and save canvas
	public void loadImage(){
		int returnVal = fc.showOpenDialog(Paintimator.this);
		BufferedImage[] img = new BufferedImage[1];

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

	public void saveImage(ActionEvent e){
		int returnVal = fc.showSaveDialog(Paintimator.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File savedPane = fc.getSelectedFile();
			String ext = Utils.getExtension(savedPane);

			ImageFilter imgfltr = new ImageFilter();
			if (imgfltr.accept(savedPane)) {
>>>>>>> origin/Kelly2
				try {
					BufferedImage bi = layeredPanel.paneToBufferedImg();
					ImageIO.write(bi, ext, savedPane);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(new JPanel(), "Image could not be saved.",
							"Image error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(new JPanel(), "Extension not accepted. Please choose a new one.",
						"Extension error", JOptionPane.ERROR_MESSAGE);
				saveImage(e);
			}
		}

	}
	public void saveProject(){
		if(su.getProjectName() !=null){
			su.saveProject(layeredPanelList);
		}else{
			su.saveProjectAs(layeredPanelList);
		}
	}

	public void saveProjectAs(){
		su.saveProjectAs(layeredPanelList);
	}

	public void loadProject(){
		LayeredPanelList lpTemp;
		lpTemp = su.openProject();

		if(lpTemp != null){
			centerPanel.remove(layeredPanelList.getSelected());
			animationPane.updateAnimation(lpTemp.getSelected(), true);
			refreshDrawPanel(lpTemp.getSelected());
			layeredPanelList = lpTemp;
		}
	}
	
	/**
	 * Method for adding a new frame to the project
	 * Gives a dialog for the user to save or discard the current frame
	 * TODO will work on this after layeredpanellist returns an array larger than 0
	 */
	public void newFrame() {
		int i = JOptionPane.showConfirmDialog(new JPanel(), 
				"Do you want to save this frame?", "Save Frame", 
				JOptionPane.YES_NO_CANCEL_OPTION);
		
		switch (i) {
			case JOptionPane.YES_OPTION :
				animationPane.updateAnimation(layeredPanelList.getSelected(), false);	
				centerPanel.remove(layeredPanelList.getSelected());
				
				layeredPanel = new LayeredPanel();
				
				//draw panel
				layeredPanel.setDrawColor(Color.BLACK);
				layeredPanel.setPreferredSize(new Dimension(width-450,height-300));
				layeredPanelList.add(layeredPanel);
				
				refreshDrawPanel(layeredPanelList.getSelected());
				break;
			case JOptionPane.NO_OPTION :
				centerPanel.remove(layeredPanelList.getSelected());
				layeredPanelList.remove(layeredPanelList.getSelected());
				
				layeredPanel = new LayeredPanel();
				
				//draw panel
				layeredPanel.setDrawColor(Color.BLACK);
				layeredPanel.setPreferredSize(new Dimension(width-450,height-300));
				layeredPanelList.add(layeredPanel);
				
				
				refreshDrawPanel(layeredPanelList.getSelected());
				break;
			default :
				break;
		}
		layeredPanelList.getSelected().clearRootPane();
	}
	
	public void newProj() {
		//TODO set up method to start a new proj
	}
	
	/*
	 * Method to easily refresh the drawing panel
	 */
	private void refreshDrawPanel(LayeredPanel lp) {
		gbc.gridy = 0;
		
		addListeners(lp);
		
		centerPanel.add(lp, gbc);
		centerPanel.validate();
		centerPanel.repaint();
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

<<<<<<< HEAD
                Paintimator frame = new Paintimator();
                frame.setVisible(true);
            }
        });
    }

    private void createToolPanel(){
        JPanel toolPanel = new JPanel(new GridLayout(0,1)); //this will need to be GridBag
        toolPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        JPanel undoRedoPanel = new JPanel();
        undoRedoPanel.setBackground(new Color(255, 255, 196, 0));
        JButton undo = new JButton("undo");
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //layeredPanel.undo();
                lp.getSelected().undo();
            }
        });
        JButton redo = new JButton("redo");
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //layeredPanel.redo();
                lp.getSelected().redo();
            }
        });
        undoRedoPanel.add(undo);
        undoRedoPanel.add(redo);

        final JButton line, draw, text, erase, fg_color, bg_color, circle, square, triangle, magic;

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

            }
        });

        circle = new JButton("Circle");
        circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.CIRCLE);
                //layeredPanel.setTool(LisState.CIRCLE);
                lp.getSelected().setTool(LisState.CIRCLE);
            }
        });

        square = new JButton("Square");
        square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.SQUARE);
                //layeredPanel.setTool(LisState.SQUARE);
                lp.getSelected().setTool(LisState.SQUARE);
            }
        });


        triangle = new JButton("Triangle");
        triangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.TRIANGLE);
                //layeredPanel.setTool(LisState.TRIANGLE);
                lp.getSelected().setTool(LisState.TRIANGLE);
            }
        });

        //Free drawing
        draw = new JButton("Draw");
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.DRAW);
                //layeredPanel.setTool(LisState.DRAW);
                lp.getSelected().setTool(LisState.DRAW);

            }
        });
        magic = new JButton("Magic Draw");
        magic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.MAGIC);
                //layeredPanel.setTool(LisState.MAGIC);
                lp.getSelected().setTool(LisState.MAGIC);
            }
        });

        //if we allow for a text area we should allow for the font
        //to be changed and font size
        text = new JButton("Text");
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myListener.setLisState(LisState.TEXT);
                //layeredPanel.setTool(LisState.TEXT);
                lp.getSelected().setTool(LisState.TEXT);
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
                //layeredPanel.setTool(LisState.ERASE);
                lp.getSelected().setTool(LisState.ERASE);
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
        toolPanel.add(undoRedoPanel);
        toolPanel.add(line);
        toolPanel.add(draw);
        toolPanel.add(magic);
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
        JMenuItem loadImg = new JMenuItem("Load Image");
        JMenuItem saveImg = new JMenuItem("Save Image");
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
=======
			}
		});
	}

}
>>>>>>> origin/Kelly2
