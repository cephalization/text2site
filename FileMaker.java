package text2site;

import java.io.*;
import java.util.Scanner;

public class FileMaker{

    private File head;
    private File foot;
    private File output;

    public FileMaker(){

    }

    public FileMaker(String title){
        this.head = new File("initial.html");
        this.foot = new File("final.html");
        this.output = new File(title + ".html");
    }

    /**
     * Parses input text to add html tags
     *
     * @param text The text to parse
     * @return The formatted text
     */
    private static String parse(String text){

        String input = text;
        String output = new String();

        for(int i = 0; i < input.length(); i++){
            char currentChar = input.charAt(i);
            char lastChar = (i > 0 ? input.charAt(i - 1) : 0);

            if(i == 0){
                output += "<p>" + (char)10 + currentChar;
            }else if(currentChar == 10 && lastChar == 10){
                output += "</p>" + (char)10 + "<p>" + (char)10;
            }else if(i == input.length() - 1){
                output += "" + currentChar + (char)10 + "</p>";
            }else{
                output += currentChar;
            }
        }

        return output;
    }

    /**
     * Writes the given text to a file after formatting it and appends the
     * head and foot found in initial.html and final.html
     *
     * @param text The text to write to the file
     */
    public void writeText(String text) throws IOException{
        PrintWriter writer = new PrintWriter(this.output);
        InputStreamReader headReader = new InputStreamReader(new FileInputStream(this.head));
        InputStreamReader footReader = new InputStreamReader(new FileInputStream(this.foot));

        char current = headReader.read();
        while(current != -1){
            writer.print(current);
            current = headReader.read();
        }
        writer.print("\n");
        writer.print(parse(text));
        writer.print("\n");
        current = footReader.read();
        while(current != -1){
            writer.print(footScanner.next() + " ");
            current = footReader.read();
        }

        writer.flush();
        writer.close();
    }
}
