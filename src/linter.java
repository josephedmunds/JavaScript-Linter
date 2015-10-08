/**
 * @author Joseph Edmunds
 * Software Tools and Practices
 * JavaScript Linter
 */

import java.io.*;

public class linter {

    public static void main(String[] args) {
        File file = new File(args[0]);
        try {
            BufferedReader buff = new BufferedReader(new FileReader(file));

            String line = buff.readLine();
            int lineNum = 0;
            while(line != null) {
                if(hasSemicolon(line))
                    continue;
                else
                    System.out.println(lineNum + ". Statement should end with a semicolon.");
                buff.readLine();
                lineNum++;
            }
            buff.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasSemicolon(String line) {
        if (line.contains("{")) {
            return true;
        }
        else if (line.contains("}")) {
            return true;
        }
        else
            return line.endsWith(";");
    }
}
