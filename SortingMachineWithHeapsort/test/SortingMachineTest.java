import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Julia Rank, Yahui Zhou
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    /*
     * Test case for size
     */

    /**
     * Test with size of 0.
     */
    @Test
    public final void testSizeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);
        int s = m.size();

        assertEquals(0, s);
        assertEquals(mExpected, m);
    }

    /**
     * Test with size of 1.
     */
    @Test
    public final void testSizeOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1");
        int s = m.size();

        assertEquals(1, s);
        assertEquals(mExpected, m);
    }

    /**
     * Test with size of 2.
     */
    @Test
    public final void testSizeMoreThanOne1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "2");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "2");
        int s = m.size();

        assertEquals(2, s);
        assertEquals(mExpected, m);
    }

    /**
     * Test with size of 3.
     */
    @Test
    public final void testSizeMoreThanOne2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "2", "3");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "2", "3");
        int s = m.size();

        assertEquals(3, s);
        assertEquals(mExpected, m);
    }

    /*
     * Test case for order
     */

    /**
     * Test with for order.
     */
    @Test
    public final void testOrder1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "1",
                "2", "3");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "1", "2", "3");

        Comparator<String> orderM = m.order();

        assertEquals(ORDER, orderM);
        assertEquals(mExpected, m);
    }

    /**
     * Test with for order
     */
    @Test
    public final void testOrder2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "3",
                "2");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "3", "2");

        Comparator<String> orderM = m.order();

        assertEquals(ORDER, orderM);
        assertEquals(mExpected, m);
    }

    /*
     * Test of add method
     */

    /**
     * Test of add to a nonempty.
     */
    @Test
    public final void testAddNonEmptyOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue");
        m.add("blue");
        assertEquals(mExpected, m);
    }

    /**
     * Test of add to a nonempty.
     */
    @Test
    public final void testAddNonEmptyMoreThanOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow");
        m.add("yellow");
        assertEquals(mExpected, m);
    }

    /*
     * Test of changeToExtractionMode
     */

    /**
     * Test of changeToExtractionMode.
     */
    @Test
    public final void testOfChangeToExtractionModeNonEmptyOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /**
     * Test of changeToExtractionMode.
     */
    @Test
    public final void testOfChangeToExtractionModeNonEmptyMoreThanOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue");
        m.changeToExtractionMode();
        assertEquals(mExpected, m);
    }

    /*
     * Test of removeFirst
     */
    /**
     * Test of removeFirst with 1 element.
     */
    @Test
    public final void testOfRemoveFirst1() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        String element = m.removeFirst();

        assertEquals("green", element);
        assertEquals(mExpected, m);
    }

    /**
     * Test of removeFirst with 3 elements.
     */
    @Test
    public final void testOfRemoveFirst3() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "yellow");

        String element = m.removeFirst();

        assertEquals("blue", element);
        assertEquals(mExpected, m);
    }

    /*
     * Test of isInsertionMode
     */

    /**
     * Test of isInInsertionMode (true).
     */
    @Test
    public final void testOfIsInInsertionModeTrueOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        boolean insertionMode = m.isInInsertionMode();
        assertEquals(true, insertionMode);
        assertEquals(mExpected, m);
    }

    /**
     * Test of isInInsertionMode (false).
     */
    @Test
    public final void testOfIsInInsertionModeFalse() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");
        boolean insertionMode = m.isInInsertionMode();
        assertEquals(false, insertionMode);
        assertEquals(mExpected, m);
    }
}
