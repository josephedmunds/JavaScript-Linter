/**
 * @author Joseph Edmunds
 * Software Tools and Practices
 * JavaScript Linter
 */

import java.io.*;
import java.util.Scanner;

public class linter {

    public static void main(String[] args)  {
        File file = new File("test.js");
        Scanner scanLinter;
        try {
            scanLinter = new Scanner(file);
            String line = "";
            if (scanLinter.hasNextLine()) {
                line = scanLinter.nextLine();
            }
            int lineNum = 1;
            boolean processing = true;

            while (processing) {
                if (!hasSemicolon(line))
                    System.out.println(lineNum + ". Statement should end with a semicolon.");
                if (trailingWhitespace(line))
                    System.out.println(lineNum + ". Statement should not have trailing whitespace.");
                boolean braces[] = checkBraces(line);
                if (!braces[0])
                    System.out.println(lineNum + ". Open curly brace should not stand-alone.");
                if (!braces[1])
                    System.out.println(lineNum + ". Closing curly brace should stand-alone.");

                if (strictEquality(line))
                    System.out.println(lineNum + ". Should use strict equality only.");

                boolean quotes[] = singleQuote(line);
                if (quotes[0])
                    System.out.println(lineNum + ". Should use double quotes. ");
                if (quotes[1] && !quotes[2])
                    System.out.println(lineNum + ". Should use single quotes. ");

                if (lineLength(line))
                    System.out.println(lineNum + ". Line should not be longer than 80 characters. ");
                if (oneStatement(line))
                    System.out.println(lineNum + ". Use only one statement per line. ");


                if (!scanLinter.hasNextLine()) {
                    if (!endsNewline(line)) {
                        System.out.println("File " + file + " should end with a newline character.");
                    }
                }
                processing = false;
                if (scanLinter.hasNextLine()) {
                    processing = true;
                    line = scanLinter.nextLine();
                }

                lineNum++;
            }

            scanLinter.close();
        } catch (FileNotFoundException e) {
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
        if (line.matches(".*\\{")) {
            return true;
        } else if (line.matches(".*\\}")) {
            return true;
        } else if (line.matches("")) {
            return true;
        } else if (line.matches(".*;\\s")) {
            return true;
        } else
            return line.matches(".*;");
    }

    /**
     * Checks if there is trailing whitespace on a line of code
     *
     * @param line The line to be tested
     * @return True if there is trailing whitespace on the line
     */
    public static boolean trailingWhitespace(String line) {
        return line.matches(".*\\s");
    }

    /**
     * Checks if the file ends in an newline
     *
     * @param currLine The line being tested
     * @return True if the file properly ends in a newline
     */
    public static boolean endsNewline(String currLine) {
            return currLine.matches("");
    }

    /**
     * Checks if braces stand-alone or not
     *
     * @param line The line to be tested
     * @return A boolean array, with results of the test for opening and closing curly brace
     */
    public static boolean[] checkBraces(String line) {
        boolean braces[] = new boolean[2];
        braces[0] = !line.matches("\\s*\\{\\s*"); //checks opening brace
        braces[1] = !line.matches("\\{.*\\}"); //checks closing brace
        return braces;
    }

    /**
     * Checks if a line uses equality correctly
     *
     * @param line The line to be tested
     * @return True if equality is not used correctly
     */
    public static boolean strictEquality(String line) {
        return line.matches(".*===.*");
    }

    /**
     * Checks if a line uses the correct quotation
     *
     * @param line The line to be tested
     * @return A boolean array, with the results of the tests for what quotation is used
     */
    public static boolean[] singleQuote(String line) {
        boolean[] quotes = new boolean[3];
        quotes[0] = line.matches(".*\'.*\'.*\'.*");
        quotes[1] = line.matches(".*\".*\".*");
        quotes[2] = line.matches(".*\".*\'.*\".*");
        return quotes;
    }

    /**
     * Checks if there are multiple statements on the same line
     *
     * @param line The line to be tested
     * @return True if there multiple statements on one line
     */
    public static boolean oneStatement(String line) {
        return line.matches(".*;.*;");
    }

    /**
     * Checks if the line is longer than 80 characters
     *
     * @param line The line to be tested
     * @return True if the line is longer than 80 characters
     */
    public static boolean lineLength(String line) {
        int lineLength = line.length();
        return lineLength > 80;
    }
}
