import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Sample JUnit test fixture for SequenceSmooth_Function.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmooth_FunctionTest {

    /**
     * Constructs and returns a sequence of the integers provided as arguments.
     *
     * @param args
     *            0 or more integer arguments
     * @return the sequence of the given arguments
     * @ensures createFromArgs= [the sequence of integers in args]
     */
    private Sequence<Integer> createFromArgs(Integer... args) {
        Sequence<Integer> s = new Sequence1L<Integer>();
        for (Integer x : args) {
            s.add(s.length(), x);
        }
        return s;
    }

    /**
     * Test smoothIterate with s1 = <2, 4, 6>.
     */
    @Test
    public void test1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3, 5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = <7>.
     */
    @Test
    public void test2() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = <2, 4, 6, 8>.
     */
    @Test
    public void test3() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6, 8);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6, 8);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3, 5, 7);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = <2>.
     */
    @Test
    public void test4() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = <2, 4, 6>.
     */
    @Test
    public void test5() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3, 5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = <2, 4, 6, 8, 10>.
     */
    @Test
    public void test6() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6, 8, 10);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6, 8, 10);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3, 5, 7, 9);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = <2, 4>.
     */
    @Test
    public void test7() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = <2, 4>.
     */
    @Test
    public void test8() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = < 1073741825, 1073741825 >.
     */
    @Test
    public void test_Extra1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(1073741825, 1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(1073741825,
                1073741825);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(1073741825);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = < 1073741825, -1073741825 >.
     */
    @Test
    public void test_Extra2() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(1073741825, -1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(1073741825,
                -1073741825);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = < -1073741825, 1073741825 >.
     */
    @Test
    public void test_Extra3() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(-1073741825, 1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(-1073741825,
                1073741825);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothRecursive with s1 = <2, 4, 6>.
     */
    @Test
    public void test11() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> result = SequenceSmooth_Function
                .smoothRecursive(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3, 5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothRecursive with s1 = <7>.
     */
    @Test
    public void test12() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7);
        Sequence<Integer> result = SequenceSmooth_Function
                .smoothRecursive(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothRecursive with s1 = <2, 4, 6, 8>.
     */
    @Test
    public void test13() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6, 8);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6, 8);
        Sequence<Integer> result = SequenceSmooth_Function
                .smoothRecursive(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3, 5, 7);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothRecursive with s1 = <2>.
     */
    @Test
    public void test14() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2);
        Sequence<Integer> result = SequenceSmooth_Function
                .smoothRecursive(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothRecursive with s1 = <2, 4, 6>.
     */
    @Test
    public void test15() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> result = SequenceSmooth_Function
                .smoothRecursive(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3, 5);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothRecursive with s1 = <2, 4>.
     */
    @Test
    public void test16() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothRecursive with s1 = <2, 4>.
     */
    @Test
    public void test17() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4);
        Sequence<Integer> result = SequenceSmooth_Function
                .smoothRecursive(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(3);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = < 1073741825, 1073741825 >.
     */
    @Test
    public void test_Extra11() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(1073741825, 1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(1073741825,
                1073741825);
        Sequence<Integer> result = SequenceSmooth_Function.smoothIterate(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(1073741825);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = < 1073741825, -1073741825 >.
     */
    @Test
    public void test_Extra12() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(1073741825, -1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(1073741825,
                -1073741825);
        Sequence<Integer> result = SequenceSmooth_Function
                .smoothRecursive(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

    /**
     * Test smoothIterate with s1 = < -1073741825, 1073741825 >.
     */
    @Test
    public void test_Extra13() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(-1073741825, 1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(-1073741825,
                1073741825);
        Sequence<Integer> result = SequenceSmooth_Function
                .smoothRecursive(seq1);
        Sequence<Integer> expectedResult = this.createFromArgs(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedResult, result);
    }

}
