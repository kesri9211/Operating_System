package OS;  // The code is in the OS package.

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class readwrite {
    public static void main(String[] args) throws Exception {

        String str = "Hello I am KESRI, Adding content to this file";

        // Writing to the file "temp.txt"
        FileWriter fw = new FileWriter("temp.txt");
        System.out.println("Writing The file");
        for (int i = 0; i < str.length(); i++) {
            fw.write(str.charAt(i));
        }
        fw.close();  // Close the FileWriter when done writing.

        System.out.println("Reading the file");
        int ch;
        FileReader fr = null;

        try {
            fr = new FileReader("temp.txt");  // Attempt to open the file for reading.
        } catch (FileNotFoundException e) {
            System.out.println("File not found ");
        }

        // Read and print the contents of the file.
        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
        }
        fr.close();  // Close the FileReader when done reading.
    }
}
