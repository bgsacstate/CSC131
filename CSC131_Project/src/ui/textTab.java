package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class textTab extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	topPanel top;
	outputPanel output;
	bottomPanel bottom;
	
	public textTab() {
		this.setLayout(new BorderLayout());
		
		top = new topPanel("text");
		output = new outputPanel();
		bottom = new bottomPanel();
		
		this.add(top, BorderLayout.PAGE_START);
		this.add(output, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.PAGE_END);
		
		//Adds action listener to these buttons
		bottom.validateButton.addActionListener(this);
		bottom.calcButton.addActionListener(this);
		bottom.clearButton.addActionListener(this);
		for (int i = 0; i < output.copyButton.length; i++) {
			output.copyButton[i].addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Adds status when clicking copy button
		if(e.getSource() == output.copyButton[0]) {
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("COPIED MD5");
		}
		if(e.getSource() == output.copyButton[1]) {
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("COPIED SHA-1");
		}
		if(e.getSource() == output.copyButton[2]) {
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("COPIED SHA-256");
		}
		if(e.getSource() == output.copyButton[3]) {
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("COPIED SHA-512");
		}
		
		//Validate button function
		if(e.getSource() == bottom.validateButton) {
			bottom.validate(top, output, bottom);
		}
		//Clear button function
		if(e.getSource() == bottom.clearButton) {
			bottom.clear(top, output, bottom);
		}
		//Calculate button function
		if(e.getSource() == bottom.calcButton) {
			bottom.calcText(top, output, bottom);
		}
	}

}
