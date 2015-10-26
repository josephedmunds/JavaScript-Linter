/**
 * @author Joseph Edmunds
 * Software Tools and Practices
 * JavaScript Linter
 */

import java.io.*;
import java.util.Scanner;

public class linter {

    public static void main(String[] args) {
        File file = new File(args[0]);
        try {
            BufferedReader buff = new BufferedReader(new FileReader(file));

            String line = buff.readLine();
            int lineNum = 0;
            while (line != null) {
                if (!hasSemicolon(line))
                    System.out.println(lineNum + ". Statement should end with a semicolon.");
                if (trailingWhitespace(line))
                    System.out.println(lineNum + ". Statement should not have trailing whitespace.");

                buff.readLine();
                lineNum++;
            }
            if (!endsNewline(buff))
                System.out.println("File " + file + "should end with a newline character. ");

            buff.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the line ends properly, either with or without a semicolon
     *
     * @param line The line to be tested
     * @return True if the line ends properly
     */
    public static boolean hasSemicolon(String line) {
        if (line.contains("{")) {
            return true;
        } else if (line.contains("}")) {
            return true;
        } else
            return line.endsWith(";");
    }

    /**
     * Checks if there is trailing whitespace on a line of code
     *
     * @param line The line to be tested
     * @return True if there is trailing whitespace on the line
     */
    public static boolean trailingWhitespace(String line) {
        if (line.matches(".*/s*"))
            return true;
        else return false;
    }

    /**
     * Checks if the file ends in an newline
     *
     * @param file The file being linted
     * @return True if the file properly ends in a newline
     */
    public static boolean endsNewline(BufferedReader file) {
        Scanner scanMan = new Scanner(file);
        String currLine = scanMan.nextLine();
        while (scanMan.hasNextLine()) {
            currLine = scanMan.nextLine();
        }
        if (currLine.matches(".*\n"))
            return true;
        else return false;
    }
}
