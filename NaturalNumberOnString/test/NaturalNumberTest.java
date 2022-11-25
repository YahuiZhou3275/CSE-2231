import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Julia Rank & Yahui Zhou
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Test cases for four constructors
     */

    @Test
    public final void testConstructorEmpty() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest();
        NaturalNumber qExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorNaturalNumberEmpty() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber input = this.constructorRef();
        NaturalNumber q = this.constructorTest(input);
        NaturalNumber qExpected = this.constructorRef(input);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorNaturalNumberNotEmptyZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber input = this.constructorRef(0);
        NaturalNumber q = this.constructorTest(input);
        NaturalNumber qExpected = this.constructorRef(input);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorNaturalNumberNotEmptyOne() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber input = this.constructorRef(1);
        NaturalNumber q = this.constructorTest(input);
        NaturalNumber qExpected = this.constructorRef(input);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorNaturalNumberNotEmptyIntMax() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber input = this.constructorRef(Integer.MAX_VALUE);
        NaturalNumber q = this.constructorTest(input);
        NaturalNumber qExpected = this.constructorRef(input);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorNaturalNumberNotEmptyMoreThanIntMax() {
        /*
         * Set up variables and call method under test
         */
        String str = String.valueOf(Integer.MAX_VALUE);
        str += "0";
        NaturalNumber input = this.constructorRef(str);
        NaturalNumber q = this.constructorTest(input);
        NaturalNumber qExpected = this.constructorRef(input);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorIntZero() {
        /*
         * Set up variables and call method under test
         */
        int i = 0;
        NaturalNumber q = this.constructorTest(i);
        NaturalNumber qExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorIntOne() {
        /*
         * Set up variables and call method under test
         */
        int i = 1;
        NaturalNumber q = this.constructorTest(i);
        NaturalNumber qExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorIntMax() {
        /*
         * Set up variables and call method under test
         */
        int i = Integer.MAX_VALUE;
        NaturalNumber q = this.constructorTest(i);
        NaturalNumber qExpected = this.constructorRef(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorStringZero() {
        /*
         * Set up variables and call method under test
         */
        String input = "0";
        NaturalNumber q = this.constructorTest(input);
        NaturalNumber qExpected = this.constructorRef(input);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorStringToIntMax() {
        /*
         * Set up variables and call method under test
         */
        String input = String.valueOf(Integer.MAX_VALUE);
        NaturalNumber q = this.constructorTest(input);
        NaturalNumber qExpected = this.constructorRef(input);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testConstructorStringMoreThanIntMax() {
        /*
         * Set up variables and call method under test
         */
        String input = String.valueOf(Integer.MAX_VALUE);
        input += "0";
        NaturalNumber q = this.constructorTest(input);
        NaturalNumber qExpected = this.constructorRef(input);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for multiplyBy10
     */

    @Test
    public final void testMultiplyBy10_0_0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest();
        NaturalNumber qExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        int k = 0;
        q.multiplyBy10(k);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testMultiplyBy10_0_2() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest();
        NaturalNumber qExpected = this.constructorRef(2);
        /*
         * Assert that values of variables match expectations
         */
        int k = 2;
        q.multiplyBy10(k);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testMultiplyBy10_5_0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest(5);
        NaturalNumber qExpected = this.constructorRef(50);
        /*
         * Assert that values of variables match expectations
         */
        int k = 0;
        q.multiplyBy10(k);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testMultiplyBy10_5_9() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest(5);
        NaturalNumber qExpected = this.constructorRef(59);
        /*
         * Assert that values of variables match expectations
         */
        int k = 9;
        q.multiplyBy10(k);

        assertEquals(qExpected, q);
    }

    @Test
    public final void testMultiplyBy10_max_0() {
        /*
         * Set up variables and call method under test
         */
        int max = Integer.MAX_VALUE;
        NaturalNumber q = this.constructorTest(max);
        NaturalNumber qExpected = this.constructorRef("21474836470");
        /*
         * Assert that values of variables match expectations
         */
        int k = 0;
        q.multiplyBy10(k);
        assertEquals(qExpected, q);
    }

    @Test
    public final void testMultiplyBy10_max_9() {
        /*
         * Set up variables and call method under test
         */
        int max = Integer.MAX_VALUE;
        NaturalNumber q = this.constructorTest(max);
        NaturalNumber qExpected = this.constructorRef("21474836479");
        /*
         * Assert that values of variables match expectations
         */
        int k = 9;
        q.multiplyBy10(k);
        assertEquals(qExpected, q);
    }
    /*
     * Test cases for divideBy10
     */

    @Test
    public final void testDivideBy_0() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest();
        NaturalNumber qExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        int m = q.divideBy10();
        assertEquals(qExpected, q);
        assertEquals(0, m);
    }

    @Test
    public final void testDivideBy_2() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest(2);
        NaturalNumber qExpected = this.constructorRef(0);
        /*
         * Assert that values of variables match expectations
         */
        int m = q.divideBy10();
        assertEquals(qExpected, q);
        assertEquals(2, m);
    }

    @Test
    public final void testDivideBy_35() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest(35);
        NaturalNumber qExpected = this.constructorRef(3);
        /*
         * Assert that values of variables match expectations
         */
        int m = q.divideBy10();
        assertEquals(qExpected, q);
        assertEquals(5, m);
    }

    @Test
    public final void testDivideBy_IntMax() {
        /*
         * Set up variables and call method under test
         */
        int max = Integer.MAX_VALUE;
        NaturalNumber q = this.constructorTest(max);
        NaturalNumber qExpected = this.constructorRef(214748364);
        /*
         * Assert that values of variables match expectations
         */
        int m = q.divideBy10();
        assertEquals(qExpected, q);
        assertEquals(7, m);
    }

    @Test
    public final void testDivideBy_MoreThanIntMax() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest("21474836470");
        NaturalNumber qExpected = this.constructorRef("2147483647");
        /*
         * Assert that values of variables match expectations
         */
        int m = q.divideBy10();
        assertEquals(qExpected, q);
        assertEquals(0, m);
    }

    /*
     * Test cases for isZero
     */

    @Test
    public final void isZero_Yes() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest();
        /*
         * Assert that values of variables match expectations
         */
        boolean check = q.isZero();
        assertEquals(true, check);
    }

    @Test
    public final void isZero_No() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest(2);
        /*
         * Assert that values of variables match expectations
         */
        boolean check = q.isZero();
        assertEquals(false, check);
    }

}
