import java.util.Arrays;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Asked the user for a file. Then counts word occurrences in a given input file
 * and outputs an HTML document with a table of the words and counts listed in
 * alphabetical order.
 *
 * @author Yahui Zhou
 *
 */
public final class WordCount {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private WordCount() {
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @return the {@code Set} with all the seperators as elements
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    public static Set<Character> generateElements(String str) {
        assert str != null : "Violation of: str is not null";

        Set<Character> charSet = new Set1L<>();
        // store each character in the str to the set
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            // store the character when the set does not include it
            if (!charSet.contains(temp)) {
                charSet.add(temp);
            }
        }
        return charSet;
    }

    /**
     * Read {@code input} and restore each word and its count from the input
     * file {@code inputFile}.Finally return words with counts in pairs.
     *
     * @param input
     *            source of strings, one per line
     * @param separatorSet
     *            the {@code Set} of separator characters
     * @return words and counts in pairs read from {@code input}
     * @requires in.is_open
     * @ensures <pre>
     * in.is_open and
     * in.ext_name = #in.ext_name and
     * in.content = "" and
     * [storeWords contains words -> definitions from input]
     * </pre>
     */
    public static Map<String, Integer> storeWords(SimpleReader input,
            Set<Character> separatorSet) {
        assert input != null : "Violation of: input is not null";
        assert input.isOpen() : "Violation of: input.is_open";

        Map<String, Integer> wordWithCount = new Map1L<>();

        // read through the whole file line by line
        while (!input.atEOS()) {
            // read a line and get its length
            String line = input.nextLine();
            int lineLength = line.length();

            // initialize a word
            String word = "";
            int count;

            // read a line and store the words
            for (int i = 0; i < lineLength; i++) {
                char character = line.charAt(i);

                // check whether the char is separator or not
                // if it is not, then convert the char we get into string
                // and concate it to the word
                // otherwise, store the word we get so far and update the count
                if (!separatorSet.contains(character)) {
                    word += Character.toString(character);

                    // check whether the char is at the end of the line
                    // if it is and the word we get is not an empty string
                    // then store it to the map
                    if (i == line.length() - 1) {
                        if (!wordWithCount.hasKey(word)) {
                            count = 1;
                            wordWithCount.add(word, count);
                        } else {
                            count = wordWithCount.value(word) + 1;
                            wordWithCount.replaceValue(word, count);
                        }
                    }

                } else {
                    int length = word.length();

                    if (length != 0) {
                        if (!wordWithCount.hasKey(word)) {
                            count = 1;
                            wordWithCount.add(word, count);
                        } else {
                            // update the count value and the map
                            count = wordWithCount.value(word) + 1;
                            wordWithCount.replaceValue(word, count);
                        }

                    }
                    word = "";
                }

            }
        }

        return wordWithCount;

    }

    /**
     * Asked the user for a file. Then counts word occurrences in a given input
     * file and outputs an HTML document with a table of the words and counts
     * listed in alphabetical order.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // ask for the input file
        out.print("Please enter the name of input file: ");
        String inputFile = in.nextLine();

        // ask for the output file
        out.print("Please enter the name of the output folder: ");
        String outputFile = in.nextLine();

        SimpleWriter outFile = new SimpleWriter1L(outputFile);

        // set up page's title and heading
        outFile.println("<html> <head> <title>Words Counted in " + inputFile
                + "</title></head><body>");
        outFile.println("<h2>Words Counted in " + inputFile + "</h2>");
        outFile.println("<hr>");

        // set up the table
        outFile.println("<table border = 2 text-align = left>");
        outFile.println("<tr>");
        outFile.println("<th>Words</th>");
        outFile.println("<th>Counts</th>");
        outFile.println("</tr>");

        // store some separators and store them in a set
        final String separatorStr = ":,.?/;:'[]{}|`~ \"\\@#$%^&*()_+-=\t";
        Set<Character> separatorSet = generateElements(separatorStr);

        // read and store the words and counts in the file
        Map<String, Integer> wordWithCount = storeWords(inFile, separatorSet);

        // sort the words in case insensitive order
        int wordSum = wordWithCount.size();
        String[] array = new String[wordSum];
        Map<String, Integer> tempMap = wordWithCount.newInstance();
        tempMap.transferFrom(wordWithCount);
        for (int i = 0; i < wordSum; i++) {
            Map.Pair<String, Integer> x = tempMap.removeAny();
            String word = x.key();
            array[i] = word;
            wordWithCount.add(word, x.value());
        }

        Arrays.sort(array, String.CASE_INSENSITIVE_ORDER);

        // print each word and its count one by one
        for (int i = 0; i < wordSum; i++) {
            String wordPrint = array[i];
            if (wordWithCount.hasKey(wordPrint)) {
                int count = wordWithCount.value(wordPrint);
                outFile.println("<tr>");
                outFile.println("<td>" + wordPrint + "</td>");
                outFile.println("<td>" + count + "</td>");
                outFile.println("</tr>");
            }
        }

        outFile.println("</body></html>");
        /*
         * Close input and output streams
         */
        outFile.close();
        in.close();
        out.close();
    }

}
