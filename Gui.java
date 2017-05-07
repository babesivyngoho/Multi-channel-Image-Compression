import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui extends JFrame implements ActionListener{
	
	public JMenu menu;
	
	public JMenuBar menuBar;
	
	public JMenuItem open;
	
	public JPanel imagePanel;
	public JPanel originalPanel;;
	public JPanel huffmanPanel;
	public JPanel openPanel;
	public JPanel compressPanel;
	public JPanel openCompressPanel;
	public JPanel createPanel;
	public JPanel updatePanel;
	
	public JButton openButton;
	public JButton compressButton;
	public JButton openCompressButton;
	public JButton createButton;
	public JButton updateButton;
	
	public JLabel image;
	
	public File selectedFile;
	
	public String string;
	
	public HuffImage huff;
	
	public Gui(){
		super("Image Compression");
		setExtendedState(MAXIMIZED_BOTH);
		setLayout(new BorderLayout(5,5));
		
		menu = new JMenu("File");
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(menu);
		
		open = new JMenuItem("Open");
		menu.add(open);
		
		imagePanel = new JPanel();
		imagePanel.setLayout(null);
		imagePanel.setBackground(new Color(0, 46, 70));
		add(imagePanel, BorderLayout.CENTER);
		
		openPanel = new JPanel();
		openPanel.setBackground(new Color(10, 29, 46));
		openPanel.setBounds(60,600,160,70);
		imagePanel.add(openPanel);
		
		openButton = new JButton("Open");
		openButton.setFocusable(false);
		openButton.setForeground(Color.WHITE);
		openButton.setPreferredSize(new Dimension(150, 60));
		openButton.setBackground(new Color(10, 29, 46));
		openButton.setFont(new Font("Serif", Font.PLAIN, 40));
		openPanel.add(openButton);
		
		compressPanel = new JPanel();
		compressPanel.setBackground(new Color(10, 29, 46));
		compressPanel.setBounds(440,600,160,70);
		imagePanel.add(compressPanel);
		
		compressButton = new JButton("Compress");
		compressButton.setFocusable(false);
		compressButton.setForeground(Color.WHITE);
		compressButton.setPreferredSize(new Dimension(150, 60));
		compressButton.setBackground(new Color(10, 29, 46));
		compressButton.setFont(new Font("Serif", Font.PLAIN, 29));
		compressPanel.add(compressButton);
		
		createPanel = new JPanel();
		createPanel.setBackground(new Color(10, 29, 46));
		createPanel.setBounds(730,600,160,70);
		imagePanel.add(createPanel);
		
		createButton = new JButton("Create");
		createButton.setFocusable(false);
		createButton.setForeground(Color.WHITE);
		createButton.setPreferredSize(new Dimension(150, 60));
		createButton.setBackground(new Color(10, 29, 46));
		createButton.setFont(new Font("Serif", Font.PLAIN, 40));
		createPanel.add(createButton);
		
		updatePanel = new JPanel();
		updatePanel.setBackground(new Color(10, 29, 46));
		updatePanel.setBounds(1180,600,160,70);
		imagePanel.add(updatePanel);
		
		updateButton = new JButton("Update");
		updateButton.setFocusable(false);
		updateButton.setForeground(Color.WHITE);
		updateButton.setPreferredSize(new Dimension(150, 60));
		updateButton.setBackground(new Color(10, 29, 46));
		updateButton.setFont(new Font("Serif", Font.PLAIN, 40));
		updatePanel.add(updateButton);
		
		openCompressPanel = new JPanel();
		openCompressPanel.setBackground(new Color(10, 29, 46));
		openCompressPanel.setBounds(895,600,280,70);
		imagePanel.add(openCompressPanel);
		
		openCompressButton = new JButton("Open Compress Image");
		openCompressButton.setFocusable(false);
		openCompressButton.setForeground(Color.WHITE);
		openCompressButton.setPreferredSize(new Dimension(270, 60));
		openCompressButton.setBackground(new Color(10, 29, 46));
		openCompressButton.setFont(new Font("Serif", Font.PLAIN, 25));
		openCompressPanel.add(openCompressButton);
		
		originalPanel = new JPanel();
		originalPanel.setBackground(new Color(30, 90, 132));
		originalPanel.setBounds(20,20,620,570);
		imagePanel.add(originalPanel);
		
		huffmanPanel = new JPanel();
		huffmanPanel.setBackground(new Color(30, 90, 132));
		huffmanPanel.setBounds(725,20,620,570);
		imagePanel.add(huffmanPanel);
		
		open.addActionListener(this);
		openButton.addActionListener(this);
		createButton.addActionListener(this);
		compressButton.addActionListener(this);
	}
	

	public void actionPerformed(ActionEvent event){
		if(event.getSource() == open){
			
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
			fileChooser.setFileFilter(filter);
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(this);
			
			if(result == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				try{
					image = new JLabel();
					originalPanel.add(image);
					image.setIcon(new ImageIcon(ImageIO.read(selectedFile)));
					originalPanel.add(new JScrollPane(image, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
				}catch(Exception e){ }
				
				string = "" + selectedFile.getAbsolutePath();
				System.out.println("Selected file: " + string);
			}
		}else if(event.getSource() == openButton){
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
			fileChooser.setFileFilter(filter);
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(this);
			
			
			if(result == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				try{
					image = new JLabel();
					originalPanel.add(image);
					image.setIcon(new ImageIcon(ImageIO.read(selectedFile)));
					originalPanel.add(new JScrollPane(image, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
				}catch(Exception e){ }
			
				string = "" + selectedFile.getAbsolutePath();
				System.out.println("Selected file: " + string);
			}
		
		}else if(event.getSource() == createButton){
			huff = new HuffImage(string);
		}else if(event.getSource() == compressButton){
			huff.createTree();
		}
	}
	
}