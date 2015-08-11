package text2site;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import FileMaker;

import javax.swing.*;

public class Text2SiteMain implements ActionListener {

	static JTextArea textArea;
	static JButton convert;
	static JTextField textField;
	static String title;

	/*
	 * Initialize and present the gui to the user
	 */
	public static void main(String[] args) {
		// Basic JFrame initialization
		JFrame gui = new JFrame();
		gui.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel instruct1 = new JLabel("Enter your title! (One Word)");
		gui.add(instruct1);
		textField = new JTextField(10);
		gui.add(textField);
		JLabel instruct2 = new JLabel("Enter your text!");
		gui.add(instruct2);
		// Listener initialization
		Text2SiteMain listener = new Text2SiteMain();
		// User text input area
		textArea = new JTextArea(24, 26);
		gui.add(textArea);
		// Buttons!
		convert = new JButton("Convert!");
		gui.add(convert);
		convert.addActionListener(listener);
		// Final JFrame initialization
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setTitle("Text2Site");
		gui.setSize(325, 500);
		gui.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// If the source is the convert button
		if (e.getSource().equals(convert)) {
			String text = textArea.getText();
			String title = textField.getText();
			if (!title.isEmpty() && !text.isEmpty()) {
				FileMaker fileMaker = new FileMaker(title);

				try{
					fileMaker.writeText(text);
				}catch(IOException exception){
					System.out.println(exception);
					exception.printStackTrace();
				}
			} else {
				System.out.println("Please enter title AND text...");
			}
		}
	}
}
