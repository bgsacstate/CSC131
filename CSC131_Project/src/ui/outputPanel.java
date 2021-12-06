package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class outputPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	final int BORDERLAYOUT_HGAP = 10;
	final int BORDERLAYOUT_VGAP = 0;
	
	final int EMPTYBORDER_TOP = 0;
	final int EMPTYBORDER_LEFT = 10;
	final int EMPTYBORDER_BOTTOM = 0;
	final int EMPTYBORDER_RIGHT = 10;
	
	final int TEXTBOXSIZE_X = 450;
	final int TEXTBOXSIZE_Y = 27;
	
	final int HASHCOUNT = 4;
	
	final int COPYBUTTONSIZE_WIDTH = 30;
	final int COPYBUTTONSIZE_HEIGHT = 0;
	
	JTextField md5TextField;
	JTextField sha1TextField;
	JTextField sha256TextField;
	JTextField sha512TextField;
	
	JButton[] copyButton;

	public outputPanel() {
		this.setLayout(new BorderLayout(BORDERLAYOUT_HGAP, BORDERLAYOUT_VGAP));
		this.setBorder(new EmptyBorder(EMPTYBORDER_TOP, EMPTYBORDER_LEFT, EMPTYBORDER_BOTTOM, EMPTYBORDER_RIGHT));
		
		//Creates panel for both label and textField
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(HASHCOUNT, 0));
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(HASHCOUNT, 0));
		
		//Checksum with label and textField
		md5TextField = new JTextField();
		md5TextField.setEditable(false);
		md5TextField.setPreferredSize(new Dimension(TEXTBOXSIZE_X, TEXTBOXSIZE_Y));
		JLabel md5Label = new JLabel("MD5");
		labelPanel.add(md5Label);
		textPanel.add(md5TextField);
		
		sha1TextField = new JTextField();
		sha1TextField.setEditable(false);
		sha1TextField.setPreferredSize(new Dimension(TEXTBOXSIZE_X, TEXTBOXSIZE_Y));
		JLabel sha1Label = new JLabel("SHA-1");
		labelPanel.add(sha1Label);
		textPanel.add(sha1TextField);
		
		sha256TextField = new JTextField();
		sha256TextField.setEditable(false);
		sha256TextField.setPreferredSize(new Dimension(TEXTBOXSIZE_X, TEXTBOXSIZE_Y));
		JLabel sha256Label = new JLabel("SHA-256");
		labelPanel.add(sha256Label);
		textPanel.add(sha256TextField);
		
		sha512TextField = new JTextField();
		sha512TextField.setEditable(false);
		sha512TextField.setPreferredSize(new Dimension(TEXTBOXSIZE_X, TEXTBOXSIZE_Y));
		JLabel sha512Label = new JLabel("SHA-512");
		labelPanel.add(sha512Label);
		textPanel.add(sha512TextField);
		
		//Copy button for each section
		JPanel copyPanel = new JPanel();
		copyPanel.setLayout(new GridLayout(HASHCOUNT, 0));
		copyButton = new JButton[4];
		for (int i = 0; i < copyButton.length; i++) {
			copyButton[i] = new JButton("");
			copyButton[i].addActionListener(this);
			URL url = outputPanel.class.getResource("/resources/copy.png");
			copyButton[i].setIcon(new ImageIcon(url));
			copyButton[i].setPreferredSize(new Dimension(COPYBUTTONSIZE_WIDTH, COPYBUTTONSIZE_HEIGHT));
			//copyButton[i].setBorderPainted(false);
			copyButton[i].setContentAreaFilled(false);
			copyButton[i].setOpaque(false);
			copyPanel.add(copyButton[i]);
		}
		
		this.add(labelPanel, BorderLayout.WEST);
		this.add(textPanel, BorderLayout.CENTER);
		this.add(copyPanel, BorderLayout.EAST);
	}
	
	//Copy button function
	public void copy(JTextField object) {
		StringSelection stringSelection = new StringSelection(object.getText());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == copyButton[0]) {
			copy(md5TextField);
		}
		if(e.getSource() == copyButton[1]) {
			copy(sha1TextField);
		}
		if(e.getSource() == copyButton[2]) {
			copy(sha256TextField);
		}
		if(e.getSource() == copyButton[3]) {
			copy(sha512TextField);
		}
	}

}
