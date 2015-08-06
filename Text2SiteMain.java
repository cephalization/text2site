package text2site;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

public class Text2SiteMain implements ActionListener {

	static JTextArea textArea;
	static JButton convert;
	static JTextField textField;
	static String title;

	/**
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

	/**
	 * Parse the user text by char and determine if HTML tag conversion is
	 * needed
	 * 
	 * @param text
	 *            - text at the time of button press represented as a String
	 * @param convert
	 *            - boolean flag dictated if the parse adds HTML tags or not
	 * @return a string of the newly parsed and converted text
	 * @throws IOException
	 */
	private static String parse(String text, boolean convert) throws IOException {
		System.out.println("Starting parse operation!");

		String input = text;
		String formatted = "";
		int i = 0;

		for (i = 0; i < input.length(); i += 1) {
			char c = input.charAt(i);
			int code = (int) c;
			char lastC;
			int lastCode = 0;

			if (i > 0) {
				lastC = input.charAt(i - 1);
				lastCode = (int) lastC;
			}

			if (i == 0) {
				if (convert) {
					formatted += "<p>";
					formatted += (char) 10;
				}
				formatted += c;
			} else if (code > 31 && code < 65 || code > 64 && code < 127 || code == 10 && lastCode != 10) {
				formatted += c;
			} else if (code == 10 && lastCode == 10) {
				if (convert) {
					formatted += "</p>";
					formatted += (char) 10;
					formatted += "<p>";
					formatted += (char) 10;
				}
			} else if (i == input.length() - 1) {
				if (convert) {
					formatted += (char) 10;
					formatted += "</p>";
				}
			}
		}

		return formatted;
	}

	/**
	 * Print a string to a file
	 * 
	 * @param text
	 * @param name
	 * @return
	 * @throws IOException
	 */
	private static void printOut(String text, String name) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(name, true)));
		out.println(text);
		out.close();
	}

	/**
	 * Create the .html file to be formatted
	 */
	private void createFile(String title) {
		Writer writer = null;
		Text2SiteMain.title = title + ".html";
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(title + ".html"), "utf-8"));
		} catch (IOException ex) {
			// report
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
				/* ignore */}
		}
	}

	/**
	 * Create and append string of given file to the new HTML document
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void convert(String location) throws FileNotFoundException, IOException {
		String formatted = parse(fileReader(location), false);
		printOut(formatted, title);
	}

	/**
	 * @param text
	 * @param convert
	 * @throws IOException
	 */
	private void convert(String text, boolean convert) throws IOException {
		String formatted = parse(text, convert);
		printOut(formatted, title);
	}

	/**
	 * Read a file by each char and convert that to a large string including
	 * line breaks and punctuation.
	 * 
	 * @param location
	 *            - the location of the file to convert to a string represented
	 *            as a string
	 * @return - the newly formatted string of the file represented as a string
	 * @throws FileNotFoundException
	 */
	private String fileReader(String location) throws FileNotFoundException {
		Scanner fileRead = new Scanner(new File(location));
		fileRead.useDelimiter("");
		String formatted = "";

		while (fileRead.hasNext()) {
			String s = fileRead.next();
			char c = s.charAt(0);
			int code = (int) c;

			if (code > 31 && code < 65 || code > 64 && code < 127 || code == 10) {
				formatted += c;
			}
		}

		fileRead.close();
		return formatted;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(convert)) {
			String text = textArea.getText();
			String title = textField.getText();
			if (!title.isEmpty() && !text.isEmpty()) {
				createFile(title);
				// initialize
				try {
					convert("initial.html");
				} catch (IOException e1) {
					e1.printStackTrace();
					System.out.println("Please create initial.html in setup folder.");
				}
				// parse
				try {
					convert(text, true);
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				// finalize
				try {
					convert("final.html");
				} catch (IOException e1) {
					e1.printStackTrace();
					System.out.println("Please create final.html in setup folder.");
				}
				System.out.println("Done!");
			} else {
				System.out.println("Please enter title AND text...");
			}
		}
	}
}
