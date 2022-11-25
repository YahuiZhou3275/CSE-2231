import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value, hasKey, and size
    /*
     * Test cases for constructors
     */

    @Test
    public final void testConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.constructorTest();
        Map<String, String> qExpected = this.constructorRef();
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
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsTest("1", "2");
        /*
         * Assert that values of variables match expectations
         */
        q.add("1", "2");
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddNonEmptyOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2", "3",
                "4");
        /*
         * Assert that values of variables match expectations
         */
        q.add("3", "4");
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for add
     */

    @Test
    public final void testAddNonEmptyMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2", "3", "4");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2", "3",
                "4", "5", "6");
        /*
         * Assert that values of variables match expectations
         */
        q.add("5", "6");
        assertEquals(qExpected, q);
    }
    /*
     * Test cases for remove
     */

    @Test
    public final void testRemoveNonEmptyOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2");
        Map<String, String> qExpected = this.createFromArgsTest();
        /*
         * Assert that values of variables match expectations
         */
        q.add("1", "2");
        assertEquals(qExpected, q);
    }

    @Test
    public final void testRemoveNonEmptyMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2", "3", "4");
        Map<String, String> qExpected = this.createFromArgsTest("3", "4");
        /*
         * Assert that values of variables match expectations
         */
        q.add("1", "2");
        assertEquals(qExpected, q);
    }
    /*
     * Test cases for remove
     */

    @Test
    public final void testRemoveAnyNonEmptyOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2");
        /*
         * Assert that values of variables match expectations
         */
        Pair<String, String> remove = q.removeAny();
        String removedKey = remove.key();
        String removedValue = remove.value();
        assertEquals(true, qExpected.hasKey(removedKey));
        assertEquals(true, qExpected.hasValue(removedValue));
        qExpected.removeAny();
        assertEquals(qExpected, q);
    }

    @Test
    public final void testRemoveAnyNonEmptyMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2", "3", "4");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2", "3",
                "4");
        /*
         * Assert that values of variables match expectations
         */
        Pair<String, String> remove = q.removeAny();
        String removedKey = remove.key();
        String removedValue = remove.value();
        assertEquals(true, qExpected.hasKey(removedKey));
        assertEquals(true, qExpected.hasValue(removedValue));
        qExpected.removeAny();
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for value
     */

    @Test
    public final void testValueNonEmptyOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2");
        /*
         * Assert that values of variables match expectations
         */
        String value = q.value("1");
        assertEquals("2", value);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testValueNonEmptyMoreOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2", "3", "4");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2", "3",
                "4");
        /*
         * Assert that values of variables match expectations
         */
        String value = q.value("3");
        assertEquals("4", value);
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for haskey
     */

    @Test
    public final void testHasKeyEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsTest();
        /*
         * Assert that values of variables match expectations
         */
        boolean hasKey = q.hasKey("1");
        assertEquals(false, hasKey);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testHasKeyNonEmptyOneYes() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2");
        /*
         * Assert that values of variables match expectations
         */
        boolean hasKey = q.hasKey("1");
        assertEquals(true, hasKey);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testHasKeyNonEmptyOneNo() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2");
        /*
         * Assert that values of variables match expectations
         */
        boolean hasKey = q.hasKey("3");
        assertEquals(false, hasKey);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testHasKeyNonEmptyMoreThanOneYes() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2", "3", "4");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2", "3",
                "4");
        /*
         * Assert that values of variables match expectations
         */
        boolean hasKey = q.hasKey("3");
        assertEquals(true, hasKey);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testHasKeyNonEmptyMoreThanOneNo() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2", "3", "4");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2", "3",
                "4");
        /*
         * Assert that values of variables match expectations
         */
        boolean hasKey = q.hasKey("2");
        assertEquals(false, hasKey);
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for size
     */

    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest();
        Map<String, String> qExpected = this.createFromArgsTest();
        /*
         * Assert that values of variables match expectations
         */
        int size = q.size();
        assertEquals(0, size);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testSizeNonEmptyOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2");
        /*
         * Assert that values of variables match expectations
         */
        int size = q.size();
        assertEquals(1, size);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testSizeNonEmptyMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> q = this.createFromArgsTest("1", "2", "3", "4");
        Map<String, String> qExpected = this.createFromArgsTest("1", "2", "3",
                "4");
        /*
         * Assert that values of variables match expectations
         */
        int size = q.size();
        assertEquals(2, size);
        assertEquals(qExpected, q);
    }
}