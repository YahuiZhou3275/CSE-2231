import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * Asked the user for a file. Then counts word occurrences in a given input file
 * and outputs an HTML document with a table of the words and counts listed in
 * alphabetical order.
 *
 * @author Yahui Zhou, Julia Rank
 *
 */
public final class TagCloudGeneratorJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGeneratorJava() {
    }

    /**
     * The minimum font size minus one.
     */
    private static final int BASE_FONT_SIZE = 10;
    /**
     * The maximum font size.
     */
    private static final int MAX_FONT_SIZE = 48;

    /**
     * Compare {@code Map}s based on the its value.
     */
    private static class CountOrder
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {
            Integer i1 = o1.getValue();
            Integer i2 = o2.getValue();
            int order = i2.compareTo(i1);
            if (order == 0) {
                String k1 = o1.getKey().toLowerCase();
                String k2 = o2.getKey().toLowerCase();
                order = k1.compareTo(k2);
            }
            return order;
        }
    }

    /**
     * Compare {@code Map}s based on the its key's value.
     */
    private static class AlphabetOrder
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {
            String i1 = o1.getKey();
            String i2 = o2.getKey();
            return i1.compareTo(i2);
        }
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @return the {@code Set} with all the separators as elements
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    public static Set<Character> generateElements(String str) {
        assert str != null : "Violation of: str is not null";

        Set<Character> charSet = new HashSet<Character>();
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
     * Returns the first "word" (maximal length string of characters not in
     * {@code SEPARATORS}) or "separator string" (maximal length string of
     * characters in {@code SEPARATORS}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separatorSet
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection entries(SEPARATORS) = {}
     * then
     *   entries(nextWordOrSeparator) intersection entries(SEPARATORS) = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection entries(SEPARATORS) /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of entries(SEPARATORS)  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of entries(SEPARATORS))
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separatorSet) {
        assert text != null : "Violation of: text is not null";
        assert position >= 0 : "Violation of: position >= 0";
        assert position < text
                .length() : "Violation of: position < text.length()";
        assert separatorSet != null : "Violation of: separatorSet is not null";

        // set two booleans to check whether next char is still separator or word
        // if two booleans have same value, then we increase position and check next one
        // until we meet separator when all we got before is word
        // or we meet a word when all we got before is separator(s)
        // then we subtract the word or separator based on the position we go through
        boolean isSeparatorStart = separatorSet.contains(text.charAt(position));
        boolean isSeparatorNow = separatorSet.contains(text.charAt(position));

        int i = 0;
        while ((position + i) < text.length()
                && isSeparatorStart == isSeparatorNow) {
            i++;
            if (position + i < text.length()) {
                isSeparatorNow = separatorSet
                        .contains(text.charAt(position + i));
            }
        }

        String result = text.substring(position, position + i);
        return result;
    }

    /**
     * Read {@code input} and restore each word and its count from the input
     * file {@code inputFile}. Finally return words with counts in pairs.
     *
     * @param inFile
     *            source of strings, one per line
     * @param separatorSet
     *            the {@code Set} of separator characters
     * @return words and counts in pairs read from {@code input}
     * @requires in.is_open
     * @ensures <pre>
     * in.is_open and
     * [storeWords contains the words with its count]
     * </pre>
     */
    public static Map<String, Integer> storeWords(BufferedReader inFile,
            Set<Character> separatorSet) {

        Map<String, Integer> wordWithCount = new HashMap<String, Integer>();

        // read through the whole file line by line
        String line;
        try {
            line = inFile.readLine();
            while (line != null) {
                // read a line and get its length
                int lineLength = line.length();

                // read a line and store the words
                int i = 0;
                while (i < lineLength) {

                    String next = nextWordOrSeparator(line, i, separatorSet);
                    // check whether the string we get is a word or separators
                    // based on the first character
                    char firstLetter = next.charAt(0);
                    boolean isSeparator = separatorSet.contains(firstLetter);

                    // if the text is a word, convert it to lower case
                    // and then store or update the map used for storage
                    if (!isSeparator) {
                        String strLowerCase = next.toLowerCase();
                        if (wordWithCount.containsKey(strLowerCase)) {
                            int count = wordWithCount.remove(strLowerCase);
                            wordWithCount.put(strLowerCase, count + 1);
                        } else {
                            wordWithCount.put(strLowerCase, 1);
                        }
                    }
                    i += next.length();
                }
                line = inFile.readLine();
            }

        } catch (IOException e) {
            System.err.println("Error reading from input file");
        }
        return wordWithCount;
    }

    /**
     * sort the words first in decreasing order of count (to find the N most
     * frequent words) and then in alphabetical order. Finally, find the minimun
     * count of the Top N words
     *
     * @param storeWords
     *            the {@code Map} of all the words and their count
     * @param num
     *            the number of words need to be shown
     * @param empty
     *            an empty {@code SortingMachine} used as an intermediation to
     *            hold the sorted words with its count
     * @return the minimum word count
     * @requires <pre>
     * sortWords is the minimum counts of the top N words
     * storeWords is not null
     * and num is less than the size of storeWords
     * </pre>
     */
    public static int sortWords(Map<String, Integer> storeWords, int num,
            List<Entry<String, Integer>> empty) {
        assert storeWords != null : "Violation of: storeWords is not null";

        Comparator<Map.Entry<String, Integer>> countOrd = new CountOrder();
        Comparator<Map.Entry<String, Integer>> alphabetOrd = new AlphabetOrder();

        /*
         * store all the values into the List for sorting
         */
        List<Map.Entry<String, Integer>> countSort = new ArrayList<Map.Entry<String, Integer>>(
                storeWords.entrySet());
        List<Map.Entry<String, Integer>> alphabetSort = new ArrayList<Map.Entry<String, Integer>>();
        // sort the words based in decreasing order
        Collections.sort(countSort, countOrd);

        // if the user ask for more words than are in the file.
        // In this situation all the words in the file should be output instead
        int numToShow;
        if (num > countSort.size()) {
            numToShow = countSort.size();
        } else {
            numToShow = num;
        }

        int min = 0;

        for (int i = 0; i < numToShow; i++) {
            Map.Entry<String, Integer> remove = countSort.remove(0);
            alphabetSort.add(remove);
            if (i == numToShow - 1) {
                min = remove.getValue();
            }

        }

        // sort the words alphabetically
        Collections.sort(alphabetSort, alphabetOrd);
        // add all the alphabetSort to the empty List;
        empty.addAll(alphabetSort);
        return min;
    }

    /**
     * output each word in order and shown in a the font size in the tag cloud
     * proportional to the number of occurrences of the word.
     *
     * @param outFile
     *            output the lines
     * @param sortedWords
     *            the {@code SortingMachine} of certain sorted words with its
     *            corresponding counts
     * @param fileName
     *            the name of output File
     * @param min
     *            minimum word count
     * @requires <pre>
     * out is open
     * min is larger than 0
     * fileName is not null
     * </pre>
     */
    public static void generatePage(PrintWriter outFile,
            List<Entry<String, Integer>> sortedWords, String fileName,
            int min) {
        assert min >= 0 : "Violation of: min > 0";
        assert fileName != null : "Violation of: fileName is not null";

        // set up page's title and heading
        outFile.println("<html> <head> <title> Top " + sortedWords.size()
                + " words in " + fileName + "</title>");
        outFile.println("<link href = \"data/tagcloud.css\" "
                + "rel=\"stylesheet\" type=\"text/css\">");
        outFile.println(
                "<link href = \"http://web.cse.ohio-state.edu/software/2231/"
                        + "web-sw2/assignments/projects/tag-cloud-generator/"
                        + "data/tagcloud.css\">");
        outFile.println("</head><body><h2> Top " + sortedWords.size()
                + " words in " + fileName + " </h2>");

        outFile.println("<div class = \"cdiv\"> <p class = \"cbox\">");

        //printing each span line with their font sizes
        while (sortedWords.size() > 0) {
            Map.Entry<String, Integer> current = sortedWords.remove(0);
            int wordCount = current.getValue();
            String word = current.getKey();
            //creating fontSize
            int fontSize = (wordCount / min) + BASE_FONT_SIZE;
            if (fontSize > MAX_FONT_SIZE) {
                fontSize = MAX_FONT_SIZE;
            }

            outFile.println("<span style = \"cursor:default\" class=\"f"
                    + fontSize + "\" title=\"count: " + wordCount + "\">" + word
                    + "</span>");
        }

        outFile.println("</p></div></body></html>");
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

        //set up the input stream
        Scanner in = new Scanner(System.in);

        /*
         * ask for the input file
         */
        System.out.print("Please enter the name of input file: ");

        // Code to receive input from keyboard
        // declare required variables
        // inputFile is for the name of the input file
        String inputFile = in.nextLine();

        // Code to open the input file
        // input is for reading the resource from the input file
        BufferedReader inFile = null;
        try {
            inFile = new BufferedReader(new FileReader(inputFile));
        } catch (IOException e) {
            System.err.println("Error opening the input file");
        }

        /*
         * Ask for the output file
         */
        System.out.print("Please enter the name of the output folder: ");

        // Code to receive input from keyboard
        // outputFile is for the name of the input file
        String outputFile = in.nextLine();

        // Code to open the output file
        PrintWriter outFile = null;
        try {
            outFile = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputFile)));
        } catch (IOException e) {

            System.err.println("Error opening the output file");
        }

        /*
         * Ask for the number of words to be shown
         */
        System.out.print("Please enter the number of words to be shown: ");

        // numword should be a non-negative
        int numWord = Integer.parseInt(in.nextLine());
        while (numWord < 0) {
            System.out.println("The number should be non-negative."
                    + " Please reenter a new number:  ");
            numWord = Integer.parseInt(in.nextLine());

            // store some separators and store them in a set
            final String separatorStr = " ,-.!?[]';:/()*`";
            Set<Character> separatorSet = generateElements(separatorStr);
            separatorSet.add('\t');
            separatorSet.add('\n');
            separatorSet.add('\r');
            separatorSet.add('\"');

            // read and store the words and counts in the file
            Map<String, Integer> wordWithCount = storeWords(inFile,
                    separatorSet);

            // sort the words first in decreasing order of count
            // (to find the N most frequent words)
            // and then in alphabetical order
            List<Map.Entry<String, Integer>> sortedWords = new ArrayList<Map.Entry<String, Integer>>();

            // find the min count of top N words for calculating the frequency
            // for setting the front size
            int min = sortWords(wordWithCount, numWord, sortedWords);

            // generate the page

            generatePage(outFile, sortedWords, inputFile, min);

            /*
             * Close input streams
             */
            in.close();

            /*
             * Close input File
             */
            try {
                inFile.close();
            } catch (IOException e) {
                System.err.println("Error closing the input File");
            }

            /*
             * Close output File
             */
            outFile.close();

        }
    }
}
