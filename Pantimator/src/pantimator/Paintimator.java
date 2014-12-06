package pantimator;

<<<<<<< HEAD
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
=======
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
>>>>>>> master
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
<<<<<<< HEAD
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
=======
>>>>>>> master

public class Paintimator extends JFrame{
	private static final long serialVersionUID = -9178351480074121591L;
	
	private final String FRAME_TITLE = "Paintimator!";
	private BackgroundPanel contentPane;
	private JPanel centerPanel;
	private JPanel sidePanel;
	private AnimationPane animationPane;
	private LayeredPanel layeredPanel;
	private ToolPanel toolPanel;
	private OptionsPanel optionsPanel;
	private ColorWheelPanel cwPanel;
	private MyMenu menu;
	private Listener myListener;
	private JFileChooser fc;
	private StorageUtil su;
	private LayeredPanelList layeredPanelList;
	private JButton backPage, fwdPage;
	private int totalPages = 0;
	private int currentPage = 0;
	
	private GridBagConstraints gbc;
	private int height = 900;
	private int width = 1440;
	
	private boolean debug = true;

	public Paintimator() throws IOException, UnsupportedAudioFileException, LineUnavailableException{
		super();
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle(FRAME_TITLE);

		
		su = new StorageUtil(this);
		layeredPanelList = new LayeredPanelList();
		fc = new JFileChooser();
		fc.addChoosableFileFilter(new ImageFilter());
		fc.setAcceptAllFileFilterUsed(false);
		

		//create a contentPane that can hold an image
        contentPane = new BackgroundPanel("images/background.png");
        contentPane.setLayout(new BorderLayout());
		

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int myWidth = gd.getDisplayMode().getWidth();
		int myHeight = gd.getDisplayMode().getHeight();
		//System.out.println(width + " X " + height);
		if(myHeight < height || myWidth < width){
			height = 768;
			width = 1366;
		}
		this.setPreferredSize(new Dimension(width, height));

        
        //canvas panel
		layeredPanel = new LayeredPanel();
		layeredPanel.setDrawColor(Color.BLACK);
		layeredPanel.setPreferredSize(new Dimension(width-450,height-300));
		layeredPanelList.add(layeredPanel);
		//listener
		myListener = new Listener(layeredPanel);
		layeredPanel.addMouseListener(myListener);
		layeredPanel.addMouseMotionListener(myListener);

		//center panel
		centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.setOpaque(false);
		this.createButtons();


		//animation panel
<<<<<<< HEAD
		animationPane = new AnimationPane(this);
		//animationPane.setPreferredSize(new Dimension(width-450, 150));
=======
		animationPane = new AnimationPane(layeredPanelList);
		animationPane.setPreferredSize(new Dimension(width-450, 150));
>>>>>>> master
		animationPane.setOpaque(false);

		//side panel
		sidePanel = new JPanel(new GridBagLayout());
		optionsPanel = new OptionsPanel(this);
		toolPanel = new ToolPanel(this, optionsPanel);
		cwPanel = new ColorWheelPanel(this);
		
		toolPanel.setOpaque(false);
		optionsPanel.setOpaque(false);
		cwPanel.setOpaque(false);
		sidePanel.setOpaque(false);
		sidePanel.setPreferredSize(new Dimension(300, height));

		//menu bar
		menu = new MyMenu(this);
		this.setJMenuBar(menu);

		

		//add everything to correct locations
		gbc = new GridBagConstraints();
		gbc.weightx = 0.50;
		gbc.weighty = 0.50;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		centerPanel.add(layeredPanelList.getSelected(), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LAST_LINE_START;
		centerPanel.add(backPage, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LAST_LINE_END;
		centerPanel.add(fwdPage, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		centerPanel.add(animationPane, gbc);

		gbc.weightx = 0.50;
		gbc.weighty = 0.50;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		sidePanel.add(toolPanel, gbc);
		
		gbc.weightx = 0.50;
		gbc.weighty = 0.50;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		sidePanel.add(optionsPanel, gbc);
		
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.gridy = 1;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		sidePanel.add(cwPanel, gbc);
		
		if(debug){
			sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			centerPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			animationPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			toolPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			optionsPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
			cwPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		}
		
		//add panels to the content pane
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(sidePanel, BorderLayout.WEST);

		//set it and show it
		this.setContentPane(contentPane);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
		refreshDrawPanel(layeredPanelList.getSelected());
		layeredPanelList.getSelected().clearRootPane();
		
	}
	/*
	 * Method to easily add/update listeners and canvas
	 */

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
		optionsPanel.setButtonBackgroundColor(c);
	}
	
	public void setCanvasCursor(CanvasCursor c){
		layeredPanel.setCanvasCursor(c);
	}
	
	public void setCurrentCanvas(LayeredPanel lp){
		myListener.updateLayeredPanel(lp);
	}
	
	private void createButtons(){
		java.net.URL buttonIcon = Paintimator.class.getResource("images/BackPage.png");
		backPage = new JButton(new ImageIcon(buttonIcon));
		backPage.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(currentPage < totalPages){
					setCurrentCanvas(layeredPanelList.getPrev());
					
				}
				
			}	
		});
		buttonIcon = Paintimator.class.getResource("images/FwPage.png");
		
		fwdPage = new JButton(new ImageIcon(buttonIcon));
		fwdPage.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setCurrentCanvas(layeredPanelList.getNext());
				
			}		
		});
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
				try {
					BufferedImage bi = layeredPanel.paneToBufferedImg();
					ImageIO.write(bi, ext, savedPane);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(this, "Image could not be saved.",
							"Image error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Extension not accepted. Please choose a new one.",
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
			animationPane.updateAnimation(lpTemp);
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
		int i = JOptionPane.showOptionDialog(this,
				"Do you want to save this frame?", 
				"Save Frame", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				new String[]{"Save", "Discard", "Cancel"},
				"default");
		
		switch (i) {
			case JOptionPane.YES_OPTION :	
				centerPanel.remove(layeredPanelList.getSelected());
				animationPane.updateAnimation(layeredPanelList);
				
				layeredPanel = new LayeredPanel();
				layeredPanel.addMouseListener(myListener);
				layeredPanel.addMouseMotionListener(myListener);
				this.setCurrentCanvas(layeredPanel);
				layeredPanel.setPreferredSize(new Dimension(width-450,height-300));
				layeredPanelList.add(layeredPanel);
				
				refreshDrawPanel(layeredPanelList.getSelected());
				
				totalPages++;
				currentPage = totalPages;
				break;
			case JOptionPane.NO_OPTION :
				centerPanel.remove(layeredPanelList.getSelected());
				layeredPanelList.remove(layeredPanelList.getSelected());
				
				layeredPanel = new LayeredPanel();
				layeredPanel.addMouseListener(myListener);
				layeredPanel.addMouseMotionListener(myListener);
				this.setCurrentCanvas(layeredPanel);
				layeredPanel.setDrawColor(Color.BLACK);
				layeredPanel.setPreferredSize(new Dimension(width-450,height-300));
				layeredPanelList.add(layeredPanel);
				
				
				refreshDrawPanel(layeredPanelList.getSelected());
				animationPane.updateAnimation(layeredPanelList);
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
	public void refreshDrawPanel(LayeredPanel lp) {

		gbc = new GridBagConstraints();
		gbc.weightx = 0.50;
		gbc.weighty = 0.50;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		
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
					e.printStackTrace();
                    System.exit(1);
				} catch (UnsupportedAudioFileException e) {
					e.printStackTrace();
                    System.exit(1);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
                    System.exit(1);
				}

			}
		});
	}

}
