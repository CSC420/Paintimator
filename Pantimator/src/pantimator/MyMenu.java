package pantimator;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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

	public MyMenu(Paintimator p){
		super();
		master = p;
		@SuppressWarnings("unused")
		
		JButton jbNewFrame = createButton("images/newFrame32.png", "New Frame");
		JButton jbNewProject = createButton("images/newProject322.png", "New Project");
		JButton jbSave = createButton("images/save32.png", "Save");
		JButton jbOpen = createButton("images/open32.png", "Open");
		
		
		JMenu fileMenu = new JMenu("File");		
		JMenuItem loadImg = new JMenuItem("Import Image");
		JMenuItem saveImg = new JMenuItem("Export Image");

		
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
		try {
			BufferedImage thicknessIcon = ImageIO.read(new File(path));
			return new ImageIcon(thicknessIcon, des);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		/*java.net.URL imgURL = getClass().getResource(path);
		if(imgURL != null){
			return new ImageIcon(imgURL, des);
		}else{
			System.err.println("Bad Path: " + path);
			return null;
		}*/
	}
	
}
