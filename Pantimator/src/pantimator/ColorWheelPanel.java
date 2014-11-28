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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class ColorWheelPanel extends JPanel {
	private Paintimator master;
	private JButton currentColor;
	private JButton newColor;

	public ColorWheelPanel(Paintimator p) throws IOException{
		super();
		master = p;

		//Color Chooser
		JPanel colors = new JPanel(new GridLayout(0,2));
		colors.setPreferredSize(new Dimension(50,200));
		final JButton red = new JButton(), black = new JButton(), white = new JButton(),
				grey = new JButton(), ltgrey = new JButton(), orange = new JButton(),
				brown = new JButton(), yellow = new JButton(), green = new JButton(), 
				ltgreen = new JButton(), blue = new JButton(), ltblue = new JButton(), 
				purple = new JButton(), pink = new JButton(), ltbrown = new JButton(), 
				rosie = new JButton(), other = new JButton(), plus = new JButton();

		other.setBackground(Color.WHITE);
		other.setOpaque(true);
		other.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		other.setBorderPainted(false);
		other.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = other;
				changeSelectedColor();
				master.setDrawColor(other.getBackground());
			} 
		});

		plus.setBackground(Color.LIGHT_GRAY);
		BufferedImage buttonIcon = ImageIO.read(new File("images/plus-sign.png"));
		plus.setIcon(new ImageIcon(buttonIcon));
		plus.setOpaque(true);
		plus.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		plus.setBorderPainted(false);
		plus.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Color selected = JColorChooser.showDialog(master, "Select a Color", Color.WHITE);
				other.setBackground(selected);
				newColor = other;
				changeSelectedColor();
				master.setDrawColor(selected);
			} 
		});

		red.setBackground(Color.red);
		red.setOpaque(true);
		red.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		red.setBorderPainted(false);
		red.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = red;
				changeSelectedColor();
				master.setDrawColor(Color.red);
			} 
		});


		black.setOpaque(true); 
		black.setBackground(Color.BLACK);
		black.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		black.setBorderPainted(false);
		black.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = black;
				changeSelectedColor();
				master.setDrawColor(Color.black);
			} 
		});

		white.setOpaque(true);
		white.setBackground(Color.white);
		white.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		white.setBorderPainted(false);
		white.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = white;
				changeSelectedColor();
				master.setDrawColor(Color.white);
			} 
		});

		grey.setOpaque(true);
		grey.setBackground(Color.GRAY);
		grey.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		grey.setBorderPainted(false);
		grey.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = grey;
				changeSelectedColor();
				master.setDrawColor(Color.GRAY);
			} 
		});

		ltgrey.setOpaque(true);
		ltgrey.setBackground(Color.LIGHT_GRAY);
		ltgrey.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		ltgrey.setBorderPainted(false);
		ltgrey.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = ltgrey;
				changeSelectedColor();
				master.setDrawColor(Color.LIGHT_GRAY);
			} 
		});

		orange.setOpaque(true);
		orange.setBackground(new Color(255,128,0));
		orange.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		orange.setBorderPainted(false);
		orange.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = orange;
				changeSelectedColor();
				master.setDrawColor(new Color(255,128,0));
			} 
		});

		brown.setOpaque(true);
		brown.setBackground(new Color(139, 69, 19));
		brown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		brown.setBorderPainted(false);
		brown.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = brown;
				changeSelectedColor();
				master.setDrawColor(new Color(139, 69, 19));
			} 
		});

		yellow.setOpaque(true);
		yellow.setBackground(Color.yellow);
		yellow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		yellow.setBorderPainted(false);
		yellow.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = yellow;
				changeSelectedColor();
				master.setDrawColor(Color.yellow);
			} 
		});

		green.setOpaque(true);
		green.setBackground(new Color(0,128,0));
		green.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		green.setBorderPainted(false);
		green.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = green;
				changeSelectedColor();
				master.setDrawColor(new Color(0,128,0));
			} 
		});

		ltgreen.setOpaque(true);
		ltgreen.setBackground(new Color(124,252,0));
		ltgreen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		ltgreen.setBorderPainted(false);
		ltgreen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = ltgreen;
				changeSelectedColor();
				master.setDrawColor(new Color(124,252,0));
			} 
		});

		blue.setOpaque(true);
		blue.setBackground(Color.BLUE);
		blue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		blue.setBorderPainted(false);
		blue.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = blue;
				changeSelectedColor();
				master.setDrawColor(Color.BLUE);
			} 
		});

		ltblue.setOpaque(true);
		ltblue.setBackground(new Color(135,206,250));
		ltblue.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		ltblue.setBorderPainted(false);
		ltblue.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = ltblue;
				changeSelectedColor();
				master.setDrawColor(new Color(135,206,250));
			} 
		});

		purple.setOpaque(true);
		purple.setBackground(new Color(128, 0, 128));
		purple.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		purple.setBorderPainted(false);
		purple.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = purple;
				changeSelectedColor();
				master.setDrawColor(new Color(128, 0, 128));
			} 
		});

		pink.setOpaque(true);
		pink.setBackground(Color.pink);
		pink.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		pink.setBorderPainted(false);
		pink.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = pink;
				changeSelectedColor();
				master.setDrawColor(Color.pink);
			} 
		});

		ltbrown.setOpaque(true);
		ltbrown.setBackground(new Color(205,133,63));
		ltbrown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		ltbrown.setBorderPainted(false);
		ltbrown.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = ltbrown;
				changeSelectedColor();
				master.setDrawColor(new Color(205,133,63));
			} 
		});

		rosie.setOpaque(true);
		rosie.setBackground(new Color(255,239,213));
		rosie.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		rosie.setBorderPainted(false);
		rosie.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				newColor = rosie;
				changeSelectedColor();
				master.setDrawColor(new Color(255,239,213));
			} 
		});

		colors.add(black);
		colors.add(white);
		colors.add(grey);
		colors.add(ltgrey);
		colors.add(red);
		colors.add(orange);
		colors.add(ltbrown);
		colors.add(yellow);
		colors.add(green);
		colors.add(ltgreen);
		colors.add(blue);
		colors.add(ltblue);
		colors.add(purple);
		colors.add(pink);
		colors.add(brown);
		colors.add(rosie);
		colors.add(other);
		colors.add(plus);
		colors.setOpaque(false);

		this.add(colors);

		this.setOpaque(false);
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	}

	public void changeSelectedColor(){
		if(currentColor!= null){
			currentColor.setBorderPainted(false);
		}
		newColor.setBorderPainted(true);
		currentColor = newColor;

	}

}
