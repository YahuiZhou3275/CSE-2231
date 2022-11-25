import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    /*
     * Test cases for constructors
     */

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for add
     */

    /**
     * Test add() by adding the String "gamma" to the testSet.
     */
    @Test
    public void testOfAdd() {
        Set<String> expected = this.createFromArgsRef("alpha", "beta", "gamma");
        Set<String> testSet = this.createFromArgsTest("alpha", "beta");
        testSet.add("gamma");
        assertEquals(expected, testSet);

    }

    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef("1");
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
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1");
        Set<String> qExpected = this.createFromArgsRef("1", "2");
        /*
         * Call method under test
         */
        q.add("2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddNonEmptyMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1", "3");
        Set<String> qExpected = this.createFromArgsRef("1", "2", "3");
        /*
         * Call method under test
         */
        q.add("2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Test remove() by removing the String "beta" from the testSet.
     */
    @Test
    public void testOfRemove() {
        Set<String> expected = this.createFromArgsRef("alpha", "gamma");
        Set<String> testSet = this.createFromArgsTest("alpha", "beta", "gamma");
        testSet.remove("beta");
        assertEquals(expected, testSet);

    }

    @Test
    public final void testRemoveNonEmptyOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1");
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String x = q.remove("1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("1", x);
    }

    @Test
    public final void testRemoveNonEmptyMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1", "2");
        Set<String> qExpected = this.createFromArgsRef("2");
        /*
         * Call method under test
         */
        String x = q.remove("1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals("1", x);
    }

    /**
     * Test removeAny().
     */
    @Test
    public void testRemoveAny() {
        //The Setup
        Set<String> expected = this.createFromArgsRef("alpha", "beta", "gamma");
        Set<String> actual = this.createFromArgsTest("alpha", "beta", "gamma");

        //The Call
        String removed = actual.removeAny();

        //The Evaluation
        assertEquals(true, expected.contains(removed));
        expected.remove(removed);
        assertEquals(expected, actual);

    }

    @Test
    public final void testRemoveAnyNonEmptyTwo() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1", "2");
        Set<String> qExpected = this.createFromArgsRef("1", "2");
        /*
         * Call method under test
         */
        String x = q.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, qExpected.contains(x));
        qExpected.remove(x);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testRemoveAnyNonEmptyMoreThanTwo() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1", "2", "3");
        Set<String> qExpected = this.createFromArgsRef("1", "2", "3");
        /*
         * Call method under test
         */
        String x = q.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, qExpected.contains(x));
        qExpected.remove(x);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testContainsNonEmptyOneContains() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1");
        Set<String> qExpected = this.createFromArgsRef("1");
        /*
         * Call method under test
         */
        boolean contains = q.contains("1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(true, contains);
    }

    @Test
    public final void testContainsNonEmptyOneNotContains() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1");
        Set<String> qExpected = this.createFromArgsRef("1");
        /*
         * Call method under test
         */
        boolean contains = q.contains("2");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(false, contains);
    }

    /**
     * Test contains() by checking if "delta" is present when it is not.
     */
    @Test
    public void testOfContainsF() {
        Set<String> testSet = this.createFromArgsTest("alpha", "beta", "gamma");
        boolean expected = false;
        boolean testCase = testSet.contains("delta");
        assertEquals(expected, testCase);

    }

    /**
     * Test contains() by checking if "alpha" is present when it is.
     */
    @Test
    public void testOfContainsT() {
        Set<String> testSet = this.createFromArgsTest("alpha", "beta", "gamma");
        boolean expected = true;
        boolean testCase = testSet.contains("alpha");
        assertEquals(expected, testCase);

    }

    /**
     * Test size() on a Set of size 3.
     */
    @Test
    public void testOfSize3() {
        Set<String> testSet = this.createFromArgsRef("alpha", "beta", "gamma");
        int expectedSize = 3;
        int testSize = testSet.size();
        assertEquals(expectedSize, testSize);

    }

    /**
     * Test size() on a Set of size 0.
     */
    @Test
    public void testOfSize0() {
        Set<String> testSet = this.createFromArgsRef();
        int expectedSize = 0;
        int testSize = testSet.size();
        assertEquals(expectedSize, testSize);

    }

    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int length = q.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(0, length);
    }

    public final void testSizeNonEmptyOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1");
        Set<String> qExpected = this.createFromArgsRef("1");
        /*
         * Call method under test
         */
        int length = q.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(1, length);
    }

    public final void testSizeNonEmptyMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1", "2", "3");
        Set<String> qExpected = this.createFromArgsRef("1", "2", "3");
        /*
         * Call method under test
         */
        int length = q.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(3, length);
    }
}
