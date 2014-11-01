package pantimator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ToolPanel extends JPanel {
private Paintimator master;
private JButton line, draw, text, erase, fg_color, bg_color, circle, square, triangle, magic;

public ToolPanel(Paintimator p) throws IOException{
	super(new GridLayout(0,1));
	// JPanel toolPanel = new JPanel(new GridLayout(0,1));
	master = p;
     
     this.setBorder(new BevelBorder(BevelBorder.LOWERED));
     JPanel undoRedoPanel = new JPanel();

   
     JButton undo = new JButton("undo");
     undo.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             master.undo();
         }
     });
     JButton redo = new JButton("redo");
     redo.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             master.redo();
         }
     });
     undoRedoPanel.add(undo);
     undoRedoPanel.add(redo);

     

     line = new JButton("Line");
     line.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // myListener.setLisState(LisState.LINE);
        	 master.setListenerState(1);

         }
     });

//     //this will need to change to a color chooser
//     fg_color = new JButton("Line Color");
//     fg_color.addActionListener(new ActionListener() {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             //layeredPanel.setDrawColor(JColorChooser.showDialog(null, "Choose a color", layeredPanel.getDrawColor()));
//             lp.getSelected().setDrawColor(JColorChooser.showDialog(null, "Choose a color", lp.getSelected().getDrawColor()));
//
//         }
//     });

//     bg_color = new JButton("Background Color");
//     bg_color.addActionListener(new ActionListener() {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//             //layeredPanel.setCanvasBG(JColorChooser.showDialog(null, "Choose a color", layeredPanel.getCanvasBG()));
//             lp.getSelected().setCanvasBG(JColorChooser.showDialog(null, "Choose a color", lp.getSelected().getCanvasBG()));
//         
//         }
//     });

     circle = new JButton("Circle");
     circle.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 master.setListenerState(5);
           //  lp.getSelected().setTool(LisState.CIRCLE);
         }
     });

     square = new JButton("Square");
     square.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 master.setListenerState(6);
           //  lp.getSelected().setTool(LisState.SQUARE);
         }
     });


     triangle = new JButton("Triangle");
     triangle.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 master.setListenerState(3);
           //  lp.getSelected().setTool(LisState.TRIANGLE);
         }
     });

     //Free drawing
     draw = new JButton("Draw");
     
     BufferedImage buttonIcon = ImageIO.read(new File("redButton.png"));
     RoundButton b = new RoundButton( new ImageIcon(buttonIcon));
    // RoundButton b = new RoundButton();

     
     b.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 master.setListenerState(2);
          //   lp.getSelected().setTool(LisState.DRAW);

         }
     });
     
     
     
     magic = new JButton("Magic Draw");
     magic.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 master.setListenerState(8);
          //   layeredPanel.setTool(LisState.MAGIC);
         }
     });

     //Text
     text = new JButton("Text");
     text.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 master.setListenerState(7);
         //    lp.getSelected().setTool(LisState.TEXT);

         }
     });

     //erasing
     erase = new JButton("Erase");
     erase.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 master.setListenerState(4);
         //    lp.getSelected().setTool(LisState.ERASE);
         }
     });


     final JSlider lineSize = new JSlider(JSlider.HORIZONTAL, 1, 15, 1);
     final JLabel lineSizeLabel = new JLabel("Line Size: " + lineSize.getValue());
     lineSize.setPreferredSize(new Dimension(10, lineSize.getHeight()));
     lineSize.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent e) {
        	 int tmp = lineSize.getValue();
             master.setBrushSize(tmp);
             lineSizeLabel.setText("Line Size: " + lineSize.getValue());
         }
     });

     //add all the buttons
     this.add(undoRedoPanel);
     this.add(line);
     this.add(b);
     this.add(magic);
     this.add(text);
     this.add(circle);
     this.add(square);
     this.add(triangle);
     this.add(erase);
     this.add(lineSizeLabel);
     this.add(lineSize);
     this.setBackground(Color.RED);

     //add the toolPanel to the rightPanel
    // rightPanel.add(toolPanel);
	
}



}
