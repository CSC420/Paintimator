package pantimator;

import pantimator.Canvas.State;
import pantimator.Listener.LisState;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class Paintimator extends JFrame{
    private final Logger LOG = Logger.getLogger(Paintimator.class.getName());
    private final boolean DEBUG = true;

    private final int GUI_WIDTH = 500, GUI_HEIGHT = 500;
    private final String FRAME_TITLE = "Paintimator!";
    private final Color canvasColor = new Color(50, 165, 13);

    private Canvas canvas;
    private JPanel contentPane;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;

    private LayeredPanel layeredPanel;

    private JInternalFrame canvasFrame;

    private JMenuBar menuBar;

    private Listener myListener;


    private StorageUtil su;
    public Paintimator(){
        super();
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //create a contentPane
        contentPane = new JPanel(new BorderLayout());
        layeredPanel = new LayeredPanel();

        this.setPreferredSize(new Dimension(1200, 750));
        this.setTitle(FRAME_TITLE);

        //draw panel
        canvas = new Canvas(canvasColor);
        layeredPanel.setCanvasBG(canvasColor);
        layeredPanel.setDrawColor(Color.BLACK);
        layeredPanel.setPreferredSize(new Dimension(700,500));
//        canvas.setLayout(null);
        //canvas.setBackground(Color.GREEN);//why doesn't this work
        canvas.setPreferredSize(new Dimension(700,510));
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

        canvas.addMouseListener(myListener);
        canvas.addMouseMotionListener(myListener);

        //add canvas to the canvasPanel so there appears to be a
        //
//        canvasFrame.add(layeredPanel);
        centerPanel.add(layeredPanel);
        //centerPanel.add(bottomPanel);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(rightPanel, BorderLayout.WEST);


        //add the content pane to the frame
//        this.getContentPane().add(contentPane);
        this.setContentPane(contentPane);
        this.pack();
        this.setVisible(true);
        su = new StorageUtil(this);
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
        final JPanel toolPanel = new JPanel(new GridLayout(6,2)); //this will need to be GridBag
        toolPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

        final JButton line, draw, text, erase, color, circle, square, triangle;

        line = new JButton("Line");
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setTool(State.LINE);
                myListener.setLisState(LisState.LINE);

            }
        });

        //this will need to change to a color chooser
        color = new JButton("Color");
        color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setColor(Color.BLUE);

            }
        });

        circle = new JButton("Circle");
        circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setTool(State.CIRCLE);
                myListener.setLisState(LisState.CIRCLE);
            }
        });

        square = new JButton("Square");
        square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setTool(State.SQUARE);
                myListener.setLisState(LisState.SQUARE);
            }
        });


        triangle = new JButton("Triangle");
        triangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setTool(State.TRIANGLE);
                myListener.setLisState(LisState.TRIANGLE);
            }
        });

        //Free drawing
        draw = new JButton("Draw");
        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setTool(State.DRAW);
                myListener.setLisState(LisState.DRAW);

            }
        });

        //if we allow for a text area we should allow for the font
        //to be changed and font size
        text = new JButton("Text");
        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //FileDialog fd = new FileDialog(toolPanel);
                System.out.println("Shit Happened");
                //StorageUtil su = new StorageUtil(toolPanel);
                su.saveProject(new LayeredPanelList());
                //canvas.setTool(Drawable.Text);
            }
        });

        //uses the current background color of the canvas panel
        //right now is just a circle like free drawing
        //uses the same brush size as free drawing
        erase = new JButton("Erase");
        erase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.setTool(State.ERASE);
                myListener.setLisState(LisState.ERASE);
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
                layeredPanel.setBrushSize(lineSize.getValue());
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
        toolPanel.add(color);
        toolPanel.add(lineSizeLabel);
        toolPanel.add(lineSize);
        toolPanel.setBackground(Color.RED);

        //add the toolPanel to the rightPanel
        rightPanel.add(toolPanel);

    }

    private void createMenu(){
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        //Mark Williams
        final JMenuItem jmSave, jmSaveAs, jmOpen;
        jmSave = new JMenuItem("Save");
        jmSaveAs = new JMenuItem("Save As...");
        jmOpen = new JMenuItem("Open...");

        jmSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //FileDialog fd = new FileDialog(toolPanel);
                System.out.println("Shit Happened");
                //StorageUtil su = new StorageUtil(jmSave);
                su.saveProject(new LayeredPanelList());
                //canvas.setTool(Drawable.Text);
            }
        });

        jmSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //FileDialog fd = new FileDialog(jmSaveAs);
                System.out.println("Shit Happened");
                //StorageUtil su = new StorageUtil(jmSave);
                su.saveProjectAs(new LayeredPanelList());
                //canvas.setTool(Drawable.Text);
            }
        });

        jmOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //FileDialog fd = new FileDialog(jmOpen);
                System.out.println("Shit Happened");
                //StorageUtil su = new StorageUtil(jmOpen);
                su.openProject();
                //canvas.setTool(Drawable.Text);
            }
        });

        fileMenu.add(jmOpen);
        fileMenu.add(jmSave);
        fileMenu.add(jmSaveAs);

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