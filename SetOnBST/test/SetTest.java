import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Julia Rank, Yahui Zhou
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
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

    /*
     * Test cases for constructor
     */

    /**
     * Test for empty constructor.
     */
    @Test
    public final void testConstructorEmpty() {
        /*
         * Set up variables
         */
        Set<String> t = this.constructorTest();
        Set<String> tExpected = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(tExpected, t);
    }

    /**
     * Test case for an empty set.
     */
    @Test
    public final void testOfSize0() {
        Set<String> t = this.createFromArgsTest();
        Set<String> tExpected = this.createFromArgsRef();
        int size = t.size();
        int sizeExpected = 0;
        assertEquals(sizeExpected, size);
        assertEquals(tExpected, t);
    }

    /**
     * Test case for a set of size 3.
     */
    @Test
    public final void testOfSize3() {
        Set<String> t = this.createFromArgsTest("alpha", "beta", "gamma");
        Set<String> tExpected = this.createFromArgsRef("alpha", "beta",
                "gamma");
        int size = t.size();
        int sizeExpected = 3;
        assertEquals(sizeExpected, size);
        assertEquals(tExpected, t);
    }

    /**
     * Test case for contains method (true).
     */
    @Test
    public final void testOfContainsTrue() {
        Set<String> t = this.createFromArgsTest("alpha", "beta", "gamma");
        Set<String> tExpected = this.createFromArgsRef("alpha", "beta",
                "gamma");
        boolean contains = t.contains("alpha");
        boolean containsExpected = true;
        assertEquals(containsExpected, contains);
        assertEquals(tExpected, t);
    }

    /**
     * Test case for contains method (false).
     */
    @Test
    public final void testOfContainsFalse() {
        Set<String> t = this.createFromArgsTest("alpha", "beta", "gamma");
        Set<String> tExpected = this.createFromArgsRef("alpha", "beta",
                "gamma");
        boolean contains = t.contains("delta");
        boolean containsExpected = false;
        assertEquals(containsExpected, contains);
        assertEquals(tExpected, t);
    }

    /**
     * Test case for removeAny().
     */
    @Test
    public final void testOfRemoveAnySize1() {
        //The Setup
        Set<String> t = this.createFromArgsTest("alpha");
        Set<String> tExpected = this.createFromArgsRef("alpha");

        //The call
        String removed = t.removeAny();

        //The Evaluation
        assertEquals(true, tExpected.contains(removed));
        tExpected.remove(removed);
        assertEquals(tExpected, t);
    }

    /**
     * Test case for removeAny().
     */
    @Test
    public final void testOfRemoveAnySize3() {
        //The Setup
        Set<String> t = this.createFromArgsTest("alpha", "beta", "gamma");
        Set<String> tExpected = this.createFromArgsRef("alpha", "beta",
                "gamma");

        //The call
        String removed = t.removeAny();

        //The Evaluation
        assertEquals(true, tExpected.contains(removed));
        tExpected.remove(removed);
        assertEquals(tExpected, t);
    }

    /**
     * Test case for remove() for size of 1.
     */
    @Test
    public final void testOfRemoveSize1() {
        Set<String> t = this.createFromArgsTest("alpha");
        Set<String> tExpected = this.createFromArgsRef();
        String removed = t.remove("alpha");
        String removedExpected = "alpha";
        assertEquals(removedExpected, removed);
        assertEquals(tExpected, t);

    }

    /**
     * Test case for remove() for size of 3.
     */
    @Test
    public final void testOfRemoveSize3() {
        Set<String> t = this.createFromArgsTest("alpha", "beta", "gamma");
        Set<String> tExpected = this.createFromArgsRef("alpha", "gamma");
        String removed = t.remove("beta");
        String removedExpected = "beta";
        assertEquals(removedExpected, removed);
        assertEquals(tExpected, t);

    }

    /**
     * Test case for add() with starting size 0 adding one element.
     */
    @Test
    public final void testOfAddSize0() {
        Set<String> t = this.createFromArgsTest();
        Set<String> tExpected = this.createFromArgsRef("alpha");
        t.add("alpha");
        assertEquals(tExpected, t);

    }

    /**
     * Test case for add() with starting size 2 adding one element.
     */
    @Test
    public final void testOfAddSize2() {
        Set<String> t = this.createFromArgsTest("alpha", "beta");
        Set<String> tExpected = this.createFromArgsRef("alpha", "beta",
                "gamma");
        t.add("gamma");
        assertEquals(tExpected, t);

    }

    /**
     * Test case for add() with starting size 3 adding one element.
     */
    @Test
    public final void testOfAddSize3() {
        Set<String> t = this.createFromArgsTest("alpha", "beta", "gamma");
        Set<String> tExpected = this.createFromArgsRef("alpha", "beta", "gamma",
                "delta");
        t.add("delta");
        assertEquals(tExpected, t);

    }

}
