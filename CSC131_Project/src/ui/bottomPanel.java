package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import hash.Hash;

public class bottomPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	final int BORDER_TOP = 5;
	final int BORDER_LEFT = 10;
	final int BORDER_BOTTOM = 5;
	final int BORDER_RIGHT = 10;
	
	final int VALIDATESIZE_X = 450;
	final int VALIDATESIZE_Y = 27;
	
	final int STATUSSIZE_X = 150;
	final int STATUSSIZE_Y = 27;
	
	JTextField validateField;
	JTextField statusField;
	JButton calcButton;
	JButton closeButton;
	JButton clearButton;
	JButton validateButton;
	int errorCount;

	public bottomPanel() {
		this.setLayout(new BorderLayout());
		
		//Validate Panel
		JPanel validate = new JPanel();
		validate.setLayout(new BorderLayout());
		validate.setBorder(new EmptyBorder(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT));
		validateField = new JTextField();
		validateField.setPreferredSize(new Dimension(VALIDATESIZE_X, VALIDATESIZE_Y));
		validateButton = new JButton("Validate");
		validate.add(validateField, BorderLayout.CENTER);
		validate.add(validateButton, BorderLayout.EAST);
		
		//Status Panel
		JPanel stats = new JPanel();
		Font f = new Font("Courier", Font.BOLD, 14);
		statusField = new JTextField();
		statusField.setFont(f);
		statusField.setHorizontalAlignment(JTextField.CENTER);
		statusField.setEditable(false);
		statusField.setPreferredSize(new Dimension(STATUSSIZE_X, STATUSSIZE_Y));
		stats.add(statusField);
		
		//Buttons Panel
		JPanel buttons = new JPanel();
		calcButton = new JButton("Calculate");
		clearButton = new JButton("Clear");
		closeButton = new JButton("Close");
		closeButton.addActionListener(this);
		buttons.add(calcButton);
		buttons.add(clearButton);
		buttons.add(closeButton);
		
		this.add(validate, BorderLayout.PAGE_START);
		this.add(stats, BorderLayout.WEST);
		this.add(buttons, BorderLayout.EAST);
	}
	
	//Clears all fields and set status to CLEARED
	public void clear(topPanel top, outputPanel output, bottomPanel bottom) {
		top.textField.setText("");
		output.md5TextField.setText("");
		output.sha1TextField.setText("");
		output.sha256TextField.setText("");
		output.sha512TextField.setText("");
		bottom.validateField.setText("");
		
		bottom.statusField.setForeground(Color.BLACK);
		bottom.statusField.setText("CLEARED");
	}
	
	//Calculates hash from File
	public void calcFile(topPanel top, outputPanel output, bottomPanel bottom) {
		try {
			String filelocation = top.textField.getText();
			filelocation = filelocation.replace("\"", "");
			output.md5TextField.setText(Hash.calcFile(filelocation, "MD5"));
			output.sha1TextField.setText(Hash.calcFile(filelocation, "SHA-1"));
			output.sha256TextField.setText(Hash.calcFile(filelocation, "SHA-256"));
			output.sha512TextField.setText(Hash.calcFile(filelocation, "SHA-512"));
			
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("CALCULATED");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			bottom.statusField.setForeground(Color.RED);
			bottom.statusField.setText("INVALID FILE");
			
			JFrame frame = new JFrame();
			errorCount++;
			if (errorCount == 2) {
				JOptionPane.showMessageDialog(frame, "PLEASE, make sure the input valid.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (errorCount == 3) {
				JOptionPane.showMessageDialog(frame, "Check the input box.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (errorCount == 4) {
				JOptionPane.showMessageDialog(frame, "yOu fuCKin wit mE m8?", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (errorCount == 5) {
				JOptionPane.showMessageDialog(frame, "Don't do this to me.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (errorCount == 6) {
				JOptionPane.showMessageDialog(frame, "You're driving me insane.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (errorCount == 7) {
				JOptionPane.showMessageDialog(frame, "Stop it please.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (errorCount == 8) {
				JOptionPane.showMessageDialog(frame, "I don't like this anymore.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(frame, "Make sure input is valid!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			e1.printStackTrace();
		}
	}
	
	//Calculate Hash from Text
	public void calcText(topPanel top, outputPanel output, bottomPanel bottom) {
		try {
			String string = top.textField.getText();
			output.md5TextField.setText(Hash.calcString(string, "MD5"));
			output.sha1TextField.setText(Hash.calcString(string, "SHA-1"));
			output.sha256TextField.setText(Hash.calcString(string, "SHA-256"));
			output.sha512TextField.setText(Hash.calcString(string, "SHA-512"));
			
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("CALCULATED");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//Validates inputted hash from all other hash
	public void validate(topPanel top, outputPanel output, bottomPanel bottom) {
		String validateString = bottom.validateField.getText().toLowerCase();
		if (validateString.isEmpty() || output.md5TextField.getText().equals("") || output.sha1TextField.getText().equals("") || output.sha256TextField.getText().equals("") || output.sha512TextField.getText().equals("")) {
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("EMPTY FIELDS");
		}
		else if (validateString.equals(output.md5TextField.getText())) {
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("VALID MD5");
		}
		else if (validateString.equals(output.sha1TextField.getText())) {
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("VALID SHA-1");
		} 
		else if (validateString.equals(output.sha256TextField.getText())) {
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("VALID SHA-256");
		}
		else if (validateString.equals(output.sha512TextField.getText())) {
			bottom.statusField.setForeground(Color.BLACK);
			bottom.statusField.setText("VALID SHA-512");
		}
		else {
			bottom.statusField.setForeground(Color.RED);
			bottom.statusField.setText("INVALID");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Close button exit application
		if(e.getSource() == closeButton) {
			System.exit(0);
		}
	}

}
