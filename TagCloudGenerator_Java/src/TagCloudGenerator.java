import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine3;

/**
 * Asked the user for a file. Then counts word occurrences in a given input file
 * and outputs an HTML document with a table of the words and counts listed in
 * alphabetical order.
 *
 * @author Yahui Zhou, Julia Rank
 *
 */
public final class TagCloudGenerator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TagCloudGenerator() {
    }

    /**
     * Compare {@code Map}s based on the its value.
     */
    private static class CountOrder
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {
            Integer i1 = o1.value();
            Integer i2 = o2.value();
            return i2.compareTo(i1);
        }
    }

    /**
     * Compare {@code Map}s based on the its key's value.
     */
    private static class AlphabetOrder
            implements Comparator<Map.Pair<String, Integer>> {
        @Override
        public int compare(Map.Pair<String, Integer> o1,
                Map.Pair<String, Integer> o2) {
            String i1 = o1.key();
            String i2 = o2.key();
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
     * @param input
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
                    if (wordWithCount.hasKey(strLowerCase)) {
                        int count = wordWithCount.value(strLowerCase) + 1;
                        wordWithCount.replaceValue(strLowerCase, count);
                    } else {
                        wordWithCount.add(strLowerCase, 1);
                    }
                }
                i += next.length();
            }
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
            SortingMachine<Map.Pair<String, Integer>> empty) {
        assert storeWords != null : "Violation of: storeWords is not null";
        assert num <= storeWords
                .size() : "Violation of: num is less than the size of storeWords";

        Comparator<Map.Pair<String, Integer>> countOrd = new CountOrder();
        Comparator<Map.Pair<String, Integer>> alphabetOrd = new AlphabetOrder();

        SortingMachine<Map.Pair<String, Integer>> countSort = new SortingMachine3<>(
                countOrd);
        SortingMachine<Map.Pair<String, Integer>> alphabetSort = new SortingMachine3<>(
                alphabetOrd);

        Map<String, Integer> temp = storeWords.newInstance();
        int length = storeWords.size();
        for (int i = 0; i < length; i++) {
            Map.Pair<String, Integer> remove = storeWords.removeAny();
            countSort.add(remove);
            temp.add(remove.key(), remove.value());
        }
        storeWords.transferFrom(temp);

        countSort.changeToExtractionMode();

        //removing the first and setting min equal to the value
        Map.Pair<String, Integer> removed = countSort.removeFirst();
        int min = removed.value();
        alphabetSort.add(removed);

        for (int i = 0; i < num - 1; i++) {
            Map.Pair<String, Integer> remove = countSort.removeFirst();
            int x = remove.value();
            if (x < min) {
                min = x;
            }

            alphabetSort.add(remove);
        }

        alphabetSort.changeToExtractionMode();

        empty.transferFrom(alphabetSort);

        return min;
    }

    /**
     * output each word in order and shown in a the font size in the tag cloud
     * proportional to the number of occurrences of the word.
     *
     * @param out
     *            output the lines
     * @param sortedWord
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
    public static void generatePage(SimpleWriter out,
            SortingMachine<Map.Pair<String, Integer>> sortedWord,
            String fileName, int min) {
        assert out.isOpen() : "Violation of: out.is_open";
        assert min > 0 : "Violation of: min > 0";
        assert fileName != null : "Violation of: fileName is not null";

        // set up page's title and heading
        out.println("<html> <head> <title> Top " + sortedWord.size()
                + " words in " + fileName + "</title>");
        out.println("<link href = \"data/tagcloud.css\" "
                + "rel=\"stylesheet\" type=\"text/css\">");
        out.println(
                "<link href = \"http://web.cse.ohio-state.edu/software/2231/"
                        + "web-sw2/assignments/projects/tag-cloud-generator/"
                        + "data/tagcloud.css\">");
        out.println("</head><body><h2> Top " + sortedWord.size() + " words in "
                + fileName + " </h2>");

        out.println("<div class = \"cdiv\"> <p class = \"cbox\">");

        //printing each span line with their font sizes
        while (sortedWord.size() > 0) {
            Map.Pair<String, Integer> current = sortedWord.removeFirst();
            int wordCount = current.value();
            String word = current.key();
            //creating fontSize
            int fontSize = (wordCount / min) + 10;
            if (fontSize > 48) {
                fontSize = 48;
            }

            out.println("<span style = \"cursor:default\" class=\"f" + fontSize
                    + "\" title=\"count: " + wordCount + "\">" + word
                    + "</span>");
        }

        out.println("</p></div></body></html>");
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
        SimpleReader inFile = new SimpleReader1L(inputFile);

        // ask for the output file
        out.print("Please enter the name of the output folder: ");
        String outputFile = in.nextLine();

        SimpleWriter outFile = new SimpleWriter1L(outputFile);

        // ask for the number of words to be shown
        out.print("Please enter the number of words to be shown: ");

        // numword should be a naturalNumber
        int numWord = in.nextInteger();

        while (numWord <= 0) {
            out.print("The number should be positive."
                    + " Please reenter a new positive number:  ");
            numWord = in.nextInteger();
        }

        // store some separators and store them in a set
        final String separatorStr = " ,-.!?[]';:/()*`";
        Set<Character> separatorSet = generateElements(separatorStr);
        separatorSet.add('\t');
        separatorSet.add('\n');
        separatorSet.add('\r');
        separatorSet.add('\"');

        // read and store the words and counts in the file
        Map<String, Integer> wordWithCount = storeWords(inFile, separatorSet);

        // sort the words first in decreasing order of count
        // (to find the N most frequent words)
        // and then in alphabetical order
        SortingMachine<Map.Pair<String, Integer>> sortedWords = new SortingMachine3<Map.Pair<String, Integer>>(
                null);

        // find the min count of top N words for calculating the frequency
        // for setting the front size
        int min = sortWords(wordWithCount, numWord, sortedWords);

        // generate the page
        generatePage(outFile, sortedWords, inputFile, min);

        /*
         * Close input and output streams
         */
        outFile.close();
        in.close();
        out.close();
    }

}
