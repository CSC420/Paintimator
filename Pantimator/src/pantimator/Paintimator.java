package pantimator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Paintimator extends JFrame{
	private static final long serialVersionUID = -9178351480074121591L;
	
	private final String FRAME_TITLE = "Paintimator!";
	private JPanel contentPane;
	private JPanel centerPanel;
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

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setPreferredSize(screenSize);
		width = screenSize.width;
		height = screenSize.height;

		su = new StorageUtil(this);
		layeredPanelList = new LayeredPanelList();
		fc = new JFileChooser();
		fc.addChoosableFileFilter(new ImageFilter());
		fc.setAcceptAllFileFilterUsed(false);
		

		//create a contentPane
		contentPane = new JPanel(new BorderLayout());
		layeredPanel = new LayeredPanel();

		//draw panel
		layeredPanel.setDrawColor(Color.BLACK);
		layeredPanel.setPreferredSize(new Dimension(width-450,height-300));
		layeredPanelList.add(layeredPanel);

		//center panel
		centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.setBackground(Color.LIGHT_GRAY);

		//animation panel
		animationPane = new AnimationPane();

		//tool panel
		toolPanel = new ToolPanel(this);

		//menu
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
		layeredPanelList.getSelected().clearRootPane();
	}
	
	/*
	 * Method to easily add/update listeners
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

			}
		});
	}

}
