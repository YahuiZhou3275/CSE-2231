import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    // TODO - add test cases for constructor, add, remove, and length
    /*
     * Test cases for constructors
     */

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> q = this.createFromArgsTest();
        Sequence<String> qExpected = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for add
     */

    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest();
        Sequence<String> qExpected = this.createFromArgsRef("1");
        /*
         * Call method under test
         */
        q.add("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddNonEmptyOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("1");
        Sequence<String> qExpected = this.createFromArgsRef("1", "2");
        /*
         * Call method under test
         */
        q.add(1, "2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("1", "3");
        Sequence<String> qExpected = this.createFromArgsRef("1", "2", "3");
        /*
         * Call method under test
         */
        q.add(1, "2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for length
     */

    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest();
        Sequence<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int i = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(0, i);
    }

    @Test
    public final void testLengthNpnEmptyOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("1");
        Sequence<String> qExpected = this.createFromArgsRef("1");
        /*
         * Call method under test
         */
        int i = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(1, i);
    }

    @Test
    public final void testLengthNpnEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("1", "2", "3");
        Sequence<String> qExpected = this.createFromArgsRef("1", "2", "3");
        /*
         * Call method under test
         */
        int i = q.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(3, i);
    }

    /*
     * Test cases for remove
     */

    @Test
    public final void testRemoveLeavingEmpty() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("1");
        Sequence<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String x = q.remove(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("1", x);
    }

    @Test
    public final void testRemoveLeavingEmptyOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("1", "2");
        Sequence<String> qExpected = this.createFromArgsRef("1");
        /*
         * Call method under test
         */
        String x = q.remove(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("2", x);
    }

    @Test
    public final void testRemoveLeavingEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Sequence<String> q = this.createFromArgsTest("1", "2", "3", "4");
        Sequence<String> qExpected = this.createFromArgsRef("1", "3", "4");
        /*
         * Call method under test
         */
        String x = q.remove(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("2", x);
    }

}
