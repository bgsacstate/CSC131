package ui;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class mainFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	JTabbedPane tp = new JTabbedPane();
	
	public mainFrame() {
		this.setTitle("Hash Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//Icon for Window
		URL url = outputPanel.class.getResource("/resources/icon.png");
		ImageIcon image = new ImageIcon(url);
		this.setIconImage(image.getImage());
		
		//Tabbed Panes for Text and File
		tp.add("Text", new textTab());
		tp.add("File", new fileTab());
		
		this.add(tp);
		
		this.pack();
		this.setVisible(true);
	}

}
