import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * Program to copy a text file into another file.
 *
 * @author Put your name here
 *
 */
public final class FilterFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private FilterFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input1-file-name output-file-name
     */
    public static void main(String[] args) {

        BufferedReader input1;
        BufferedReader input2;
        PrintWriter output;

        try {
            input1 = new BufferedReader(new FileReader(args[0]));
            input2 = new BufferedReader(new FileReader(args[2]));
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(args[1])));

        } catch (IOException e) {
            System.err.println("Error opening the file");
            return;
        }

        Set<String> filter = new HashSet<>();
        try {
            String word = input2.readLine();
            while (word != null && !filter.contains(word)) {
                filter.add(word);
            }
        } catch (IOException e) {
            System.err.println("Error reading from the filter file");
        }

        try {
            boolean found = false;

            String line = input1.readLine();
            while (line != null) {
                for (String x : filter) {
                    if (line.contains(x)) {
                        found = true;
                    }
                }

                if (found) {
                    output.println(line);
                }

                line = input1.readLine();
            }

        } catch (IOException e) {
            System.err.println("Error reading from the input file");
        }

        try {
            input1.close();
            input2.close();
            output.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
        }
    }
}
