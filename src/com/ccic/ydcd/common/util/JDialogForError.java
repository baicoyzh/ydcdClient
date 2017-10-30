package com.ccic.ydcd.common.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JDialogForError extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	final JLabel errorMessageLabel;
	  public void actionPerformed(ActionEvent e){
	  	 String cmd=e.getActionCommand();
	  	 if (cmd.equals("OK")){
	  		 this.dispose();
	  	 }
	  }
	  public JDialogForError(JFrame frame){
	  	super(frame,true);
	  	setSize(new Dimension(350, 128));
	  	setResizable(false);
	  	getContentPane().setLayout(null);
	  	setTitle("Error Message");

	  	errorMessageLabel = new JLabel();
	  	errorMessageLabel.setHorizontalAlignment(SwingConstants.LEFT);
	  	errorMessageLabel.setFont(new Font("dialog", Font.PLAIN, 14));
	  	errorMessageLabel.setText("");
	  	errorMessageLabel.setBounds(10, 5, 324, 50);
	  	getContentPane().add(errorMessageLabel);

	  	final JButton button = new JButton();
	  	button.setFont(new Font("dialog", Font.PLAIN, 14));
	  	button.setText("OK");
	  	button.setBounds(125, 65, 95, 25);
	  	button.addActionListener(this);
	  	getContentPane().add(button);
	  }
	  
	  public void setErrorMessage(String message)
	  {
		  errorMessageLabel.setText("<html><body>" + message + "</body></html>");
	  }
	  
	  public static void main(String[] args){

	  }
	}

