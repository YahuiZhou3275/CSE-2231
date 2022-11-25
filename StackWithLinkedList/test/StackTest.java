import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    // TODO - add test cases for constructor, push, pop, and length
    /*
     * Test cases for constructors
     */

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> q = this.constructorTest();
        Stack<String> qExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for push
     */

    @Test
    public final void testPushEmpty() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> q = this.createFromArgsTest();
        Stack<String> qExpected = this.createFromArgsRef("1");
        q.push("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testPushOne() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> q = this.createFromArgsTest("2");
        Stack<String> qExpected = this.createFromArgsRef("1", "2");
        q.push("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testPushMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> q = this.createFromArgsTest("2", "3");
        Stack<String> qExpected = this.createFromArgsRef("1", "2", "3");
        q.push("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for pop
     */

    @Test
    public final void testPopOne() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> q = this.createFromArgsTest("1");
        Stack<String> qExpected = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        String m = q.pop();

        assertEquals(qExpected, q);
        assertEquals("1", m);
    }

    @Test
    public final void testPopMoreThanOne1() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> q = this.createFromArgsTest("1", "2");
        Stack<String> qExpected = this.createFromArgsRef("2");
        /*
         * Assert that values of variables match expectations
         */
        String m = q.pop();

        assertEquals(qExpected, q);
        assertEquals("1", m);
    }

    @Test
    public final void testPopMoreThanOne2() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> q = this.createFromArgsTest("2", "3", "4");
        Stack<String> qExpected = this.createFromArgsRef("3", "4");
        /*
         * Assert that values of variables match expectations
         */
        String m = q.pop();

        assertEquals(qExpected, q);
        assertEquals("2", m);
    }

    /*
     * Test cases for push
     */

    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> q = this.createFromArgsTest();
        Stack<String> qExpected = this.createFromArgsRef();
        int length = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(0, length);
    }

    @Test
    public final void testLengthOne() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> q = this.createFromArgsTest("1");
        Stack<String> qExpected = this.createFromArgsRef("1");
        int length = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(1, length);
    }

    @Test
    public final void testLengthMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> q = this.createFromArgsTest("1", "2");
        Stack<String> qExpected = this.createFromArgsRef("1", "2");
        int length = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(2, length);
    }
}
