package pantimator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;



import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.text.IconView;

public class MyMenu extends JMenuBar{

	private static final long serialVersionUID = 1L;
	Paintimator master;
	Clip undoSound, redoSound;

	public MyMenu(Paintimator p) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		super();
		master = p;
		@SuppressWarnings("unused")
		
		InputStream is = getClass().getResourceAsStream("sounds/undo.wav");
		AudioInputStream ais = AudioSystem.getAudioInputStream(is);
        undoSound = AudioSystem.getClip();
        undoSound.open(ais);
        
        is = getClass().getResourceAsStream("sounds/redo.wav");
		ais = AudioSystem.getAudioInputStream(is);
        redoSound = AudioSystem.getClip();
        redoSound.open(ais);
		
		
		JButton jbNewFrame = createButton("images/newFrame32.png", "New Page");
		JButton jbNewProject = createButton("images/newProject322.png", "New Notebook");
		JButton jbSave = createButton("images/save32.png", "Save");
		JButton jbOpen = createButton("images/open32.png", "Open");
		JButton jbUndo = createButton("images/arrow_undo.png", "Undo");
		JButton jbRedo = createButton("images/arrow_redo.png", "Redo");
		
		
		JMenu fileMenu = new JMenu("File");		
		JMenuItem loadImg = new JMenuItem("Import Image");
		JMenuItem saveImg = new JMenuItem("Export Image");
		
		jbUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				undoSound.stop();
				undoSound.start();
				master.undo();
			}
			
		});
		
		jbRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				redoSound.stop();
				redoSound.start();
				master.redo();
			}
			
		});
		
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
		
		
		jbNewFrame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.newFrame();
			}
			
		});
		
		jbNewProject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO set up new proj algos				
			}
			
		});

		jbSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.saveProject();				
			}
			
		});
		
		jbOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				master.loadProject();				
			}
			
		});
		
		fileMenu.add(loadImg);
		fileMenu.add(saveImg);
		
		this.add(jbUndo);
		this.add(jbRedo);
		this.add(jbNewFrame);
		this.add(jbNewProject);
		this.add(jbOpen);
		this.add(jbSave);		
	}
	
	private JButton createButton(String imgPath, String strToolTip){
		JButton tempBtn = new JButton();
		Icon tempBtnIcon = createImageIcon(imgPath, "new Frame Icon");
		tempBtn.setIcon(tempBtnIcon);
		tempBtn.setOpaque(false);
		tempBtn.setContentAreaFilled(false);
		//tempBtn.setBorderPainted(false);
		tempBtn.setToolTipText(strToolTip);
		return tempBtn;
	}
	
	private ImageIcon createImageIcon(String path, String des){
		java.net.URL thicknessIcon = OptionsPanel.class.getResource(path);
		//BufferedImage thicknessIcon = ImageIO.read(new File(path));
		return new ImageIcon(thicknessIcon, des);
	}
	
}
