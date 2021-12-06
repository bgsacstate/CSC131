package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class topPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	final int BORDER_TOP = 5;
	final int BORDER_LEFT = 10;
	final int BORDER_BOTTOM = 5;
	final int BORDER_RIGHT = 10;
	
	final int TEXTSIZE_X = 450;
	final int TEXTSIZE_Y = 27;
	
	JTextField textField;
	JButton browseButton;
	
	public topPanel(String type) {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT));
		
		//If the tab is for text
		if (type == "text") {
			textField = new JTextField();
			textField.setPreferredSize(new Dimension(TEXTSIZE_X, TEXTSIZE_Y));
			
			this.add(textField, BorderLayout.CENTER);
		}
		
		//If the tab is for file
		if (type == "file") {
			textField = new JTextField();
			textField.setPreferredSize(new Dimension(TEXTSIZE_X, TEXTSIZE_Y));
			
			browseButton = new JButton("...");
			browseButton.addActionListener(this);
			
			this.add(textField, BorderLayout.CENTER);
			this.add(browseButton, BorderLayout.EAST);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Browse function for the browse button
		if(e.getSource() == browseButton) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDragEnabled(true);
			int response = fileChooser.showOpenDialog(null);
			
			if(response == JFileChooser.APPROVE_OPTION) {
				System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		}
	}

}
