package text2site;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Text2SiteMain implements ActionListener{

	static JTextArea textArea;
	static JButton convert;
	
	/**
	 * Initialize and present the gui to the user
	 * @param args
	 */
	public static void main(String[] args) {
		//Basic JFrame initialization
		JFrame gui = new JFrame();
		gui.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel instruct1 = new JLabel("Enter your text!");
		gui.add(instruct1);
		//Listener initialization
		Text2SiteMain listener = new Text2SiteMain();
		//User text input area
		textArea = new JTextArea(24, 26);
		gui.add(textArea);
		//Buttons!
		convert = new JButton("Convert!");
		gui.add(convert);
		convert.addActionListener(listener);
		//Final JFrame initialization
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setTitle("Text2Site");
		gui.setSize(325, 500);
		gui.setVisible(true);
	}
	/**
	 * Add HTML Markup to text in the textArea
	 */
	private static void convert(){
		System.out.println("Running convert method.");
		String text = textArea.getText();
		System.out.println(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(convert)){
			System.out.println("Starting convert operation!");
			convert();
		}
	}
}
