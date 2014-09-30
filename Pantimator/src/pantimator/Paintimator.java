package pantimator;

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

    private Canvas canvas;
    private JPanel toolPanel, animationPanel;
    private JMenuBar menuBar;

    public Paintimator(){
        super();
        this.setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(GUI_WIDTH, GUI_HEIGHT));
        this.setTitle(FRAME_TITLE);

        canvas = new Canvas();
        this.add(canvas, BorderLayout.CENTER);
        createMenu();
        createToolPanel();
        createAnimationPanel();

        this.pack();

        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
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
    }//end main

    private void createMenu(){
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        this.setJMenuBar(menuBar);
    }

    private void createToolPanel(){
        toolPanel = new JPanel(new GridLayout(0,1));
        toolPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        //TODO add tool buttons

        final JButton line, draw, text, shapes, erase;
        final JSlider lineSize = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        final JLabel lineSizeLabel = new JLabel("Line Size: " + lineSize.getValue());
        lineSize.setPreferredSize(new Dimension(10, lineSize.getHeight()));
        lineSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                canvas.setLineSize(lineSize.getValue());
                lineSizeLabel.setText("Line Size: " + lineSize.getValue());
            }
        });

        if(DEBUG){
            line = new JButton("Line");
            line.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    canvas.setTool(Drawable.Line);
                }//end actionPerformed
            });

            shapes = new JButton("Shapes");
            shapes.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    final Drawable tool = Drawable.Shape;

                    String[] shapes = {"Circle", "Rectangle", "Triangle"};

                    int type = JOptionPane.showOptionDialog(canvas, "Select the shape type",
                            "Shapes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, shapes, shapes[0]);

                    switch (type){
                        case JOptionPane.CLOSED_OPTION:
                            tool.setShapeType(Drawable.ShapeType.Circle);
                            break;
                        case 0:
                            tool.setShapeType(Drawable.ShapeType.Circle);
                            break;
                        case 1:
                            tool.setShapeType(Drawable.ShapeType.Rectangle);
                            break;
                        case 2:
                            tool.setShapeType(Drawable.ShapeType.Triangle);
                    }
                    canvas.setTool(tool);
                }//end actionPerformed
            });

            draw = new JButton("Draw");
            draw.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    canvas.setTool(Drawable.Draw);
                }//end actionPerformed
            });

            text = new JButton("Text");
            text.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    canvas.setTool(Drawable.Text);
                }//end actionPerformed
            });

            erase = new JButton("Erase");
            erase.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    canvas.setTool(Drawable.Erase);
                }//end actionPerformed
            });

            toolPanel.add(line);
            toolPanel.add(draw);
            toolPanel.add(text);
            toolPanel.add(shapes);
            toolPanel.add(erase);
            toolPanel.add(lineSizeLabel);
            toolPanel.add(lineSize);
        }//end if

        this.add(toolPanel, BorderLayout.LINE_END);
    }//end createToolPanel

    private void createAnimationPanel(){
        animationPanel = new JPanel(new GridLayout(1,0));
        animationPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        //TODO add animation controls, etc.

        if(DEBUG){
            animationPanel.add(new JButton("Play"));
            animationPanel.add(new JButton("Pause"));
            animationPanel.add(new JButton("Stop"));
        }//end if

        this.add(animationPanel, BorderLayout.PAGE_END);
    }//end createAnimationPanel

}//end Paintimator
