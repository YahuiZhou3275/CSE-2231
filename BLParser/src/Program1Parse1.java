import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Yahui Zhou, Julia Rank
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        // TODO - fill in body

        // Check the first token (prefix), which is "INSTRUCTION".
        String instruction = tokens.dequeue();
        Reporter.assertElseFatalError(instruction.equals("INSTRUCTION"),
                "Error: /n expected: INSTRUCTION, but is: " + instruction);

        // Check the second token, which is the name of the instruction.
        String beginName = tokens.dequeue();
        // check whether the name equal to the name of primitive instructions.
        if (beginName.equals("move") || beginName.equals("turnleft")
                || beginName.equals("turnright") || beginName.equals("infect")
                || beginName.equals("skip")) {
            Reporter.assertElseFatalError(
                    beginName.equals("move") || beginName.equals("turnleft")
                            || beginName.equals("turnright")
                            || beginName.equals("infect")
                            || beginName.equals("skip"),
                    "Error: the new instruction name should not equal to "
                            + "the name of primitive instructions.");
        }

        // check the third token, which is "IS"
        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "Error: /n expected: IS, but is: " + is);

        // parse the body
        body.parseBlock(tokens);

        // check the ending
        // check next token, which is "END"
        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Error: /n expected: END, but is: " + end);

        // check next token, which is the name of the instruction
        String endName = tokens.dequeue();
        Reporter.assertElseFatalError(endName.equals(beginName),
                "Error: /n expected: " + beginName + ", but is: " + endName);

        // return the instruction name
        return beginName;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body
        // create a new program for the result of parse
        Program p = new Program1Parse1();

        // check the first token, which is "PROGRAM"
        String program = tokens.dequeue();
        Reporter.assertElseFatalError(program.equals("PROGRAM"),
                "Error: /n expected: PROGRAM, but is: " + program);

        // check the second token, which is the name of program
        // check whether the name meet the requirement of identifier
        String beginName = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(beginName),
                "Error: /n the name of the program "
                        + "does not meet the requirement of identifier");

        // check the third token, which is "IS"
        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "Error: /n expected: IS, but is: " + is);

        // create context and check the instruction(s)
        Map<String, Statement> context = p.newContext();
        String instruction = tokens.front();
        // check all the instruction(s)
        while (instruction.equals("INSTRUCTION")) {
            Statement instrBody = p.newBody();
            String instrName = parseInstruction(tokens, instrBody);

            for (Map.Pair<String, Statement> x : context) {
                boolean duplicate = instrName.equals(x.key());
                Reporter.assertElseFatalError(!duplicate,
                        "Error: /n there is already an instruction has the name: "
                                + instrName);
            }

            // if the instruction's name has not been used in context, then the
            // instruction is valid, and should be added to the context.
            context.add(instrName, instrBody);

            // update instruction for loop
            instruction = tokens.front();
        }

        // create body
        Statement body = p.newBody();
        // check the "first" token for the body, which is "BEGIN"
        String begin = tokens.dequeue();
        Reporter.assertElseFatalError(begin.equals("BEGIN"),
                "Error: /n expected: BEGIN, but is: " + begin);

        body.parseBlock(tokens);

        // check the token after the body - the ending, the token should be "END"
        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Error: /n expected: END, but is: " + end);

        // check whether the name of the program at the end matches the name
        // in the beginning
        String endName = tokens.dequeue();
        Reporter.assertElseFatalError(endName.equals(beginName),
                "Error: /n expected: " + endName + ", but is: " + endName);

        // check for the the very end of the program
        String endProgram = tokens.dequeue();
        Reporter.assertElseFatalError(endProgram.equals("### END OF INPUT ###"),
                "Error: /n expected: ### END OF INPUT ###, but is: "
                        + endProgram);

        // replace the program
        this.swapContext(context);
        this.swapBody(body);
        this.setName(beginName);
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
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
