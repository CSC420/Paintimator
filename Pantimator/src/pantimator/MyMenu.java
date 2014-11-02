package pantimator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenu extends JMenuBar{
	Paintimator master;

	public MyMenu(Paintimator p){
		super();
		master = p;
		final BufferedImage[] img = new BufferedImage[1];

		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenuItem newFrame = new JMenuItem("New Frame");
		JMenuItem newProj = new JMenuItem("New Project");
		JMenuItem loadImg = new JMenuItem("Import Image");
		JMenuItem saveImg = new JMenuItem("Export Image");
		JMenuItem saveProject = new JMenuItem("Save");
		JMenuItem saveProjectAs = new JMenuItem("Save as...");
		JMenuItem loadProject = new JMenuItem("Open");

		loadImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.loadImage();
			}
		});

		saveImg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.saveImage(e);
			}
		});

		saveProject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.saveProject();
			}
		});

		saveProjectAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.saveProjectAs();
			}
		});

		loadProject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				master.loadProject();
			}
		});
		
		newFrame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.newFrame();
			}
			
		});
		
		newProj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO set up new proj algos				
			}
			
		});
		
		fileMenu.add(newFrame);
		fileMenu.add(newProj);
		fileMenu.add(loadProject);
		fileMenu.add(saveProject);
		fileMenu.add(saveProjectAs);
		fileMenu.add(loadImg);
		fileMenu.add(saveImg);

		this.add(fileMenu);
		this.add(editMenu);
	}
}
