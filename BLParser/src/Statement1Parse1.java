import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Yahui Zhou, Julia Rank
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";

        // TODO - fill in body

        // "eat" the first token, which is "IF" here
        tokens.dequeue();

        // check weather the condition is met or not
        Reporter.assertElseFatalError(Tokenizer.isCondition(tokens.front()),
                "Error: /n The condition for If statement is not met");

        // convert the string of condition to Condition
        String conditionToken = tokens.dequeue();
        Condition condition = parseCondition(conditionToken);

        // check the next token, which is "THEN"
        String then = tokens.dequeue();
        Reporter.assertElseFatalError(then.equals("THEN"),
                "Error: /n expected: THEN, but is: " + then);

        // create a new statement for the body of if then parse it
        Statement ifStatements = s.newInstance();
        ifStatements.parseBlock(tokens);

        // check whether next token is "ELSE" or "END"
        String elseOrEnd = tokens.front();
        Reporter.assertElseFatalError(
                elseOrEnd.equals("ELSE") || elseOrEnd.equals("END"),
                "Error: /n expected: ELSE or END, but is: " + then);

        // if the token is "ELSE", then it is a IF-ELSE statement
        if (elseOrEnd.equals("ELSE")) {
            tokens.dequeue();
            Statement elseStatements = s.newInstance();
            elseStatements.parseBlock(tokens);
            s.assembleIfElse(condition, ifStatements, elseStatements);
        }
        // if not, then it is a IF statement
        else {
            s.assembleIf(condition, ifStatements);
        }

        // check next token, which is the "first last" token of the if-statement,
        // which is "END"
        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Error: /n expected: END, but is: " + end);

        // check next token, which is the "second last" token of the if-statement,
        // which is "IF"
        String ifEnd = tokens.dequeue();
        Reporter.assertElseFatalError(ifEnd.equals("IF"),
                "Error: /n expected: IF, but is: " + ifEnd);
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";

        // TODO - fill in body
        // eat the first token "WHILE"
        tokens.dequeue();

        // check weather the condition is met or not
        Reporter.assertElseFatalError(Tokenizer.isCondition(tokens.front()),
                "Error: /n The condition for If statement is not met");

        // convert the string of condition to Condition
        String conditionToken = tokens.dequeue();
        Condition condition = parseCondition(conditionToken);

        // check the next token, which is "DO"
        String doToken = tokens.dequeue();
        Reporter.assertElseFatalError(doToken.equals("DO"),
                "Error: /n expected: THEN, but is: " + doToken);

        // create a new statement for the body of while then parse it
        Statement whileStatements = s.newInstance();
        whileStatements.parseBlock(tokens);

        s.assembleWhile(condition, whileStatements);

        //check weather the "first last" token of while statement is "END"
        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Error: /n expected: END, but is: " + end);

        //check weather the "second last" token of while statement is "END"
        String whileEnd = tokens.dequeue();
        Reporter.assertElseFatalError(whileEnd.equals("WHILE"),
                "Error: /n expected: WHILE, but is: " + whileEnd);
    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";

        // TODO - fill in body
        String call = tokens.dequeue();
        s.assembleCall(call);
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body

        // take the token
        String statement = tokens.front();

        // check which statement it belongs to and do the recursion to parse the statement
        if (statement.equals("IF")) {
            parseIf(tokens, this);
        } else if (statement.equals("WHILE")) {
            parseWhile(tokens, this);
        } else {
            parseCall(tokens, this);
        }
    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body
        Statement s = this.newInstance();

        for (int i = 0; !tokens.front().equals("END")
                && !tokens.front().equals("ELSE")
                && !tokens.front().equals(Tokenizer.END_OF_INPUT); i++) {
            s.parse(tokens);
            this.addToBlock(i, s);
        }

    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
