import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Sample JUnit test fixture for SequenceSmooth.
 *
 * @author Put your name here
 *
 */
public final class SequenceSmoothTest {

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
     * Test smoothIterate with s1 = <2, 4, 6> and s2 = <-5, 12>.
     */
    @Test
    public void test1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> seq2 = this.createFromArgs(-5, 12);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5);
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothIterate with s1 = <7> and s2 = <13, 17, 11>.
     */
    @Test
    public void test2() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7);
        Sequence<Integer> seq2 = this.createFromArgs(13, 17, 11);
        Sequence<Integer> expectedSeq2 = this.createFromArgs();
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothIterate with s1 = <2, 4, 6, 8> and s2 = <-5, 12, 7>.
     */
    @Test
    public void test3() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6, 8);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6, 8);
        Sequence<Integer> seq2 = this.createFromArgs(-5, 12, 7);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5, 7);
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothIterate with s1 = <2> and s2 = < >.
     */
    @Test
    public void test4() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs();
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothIterate with s1 = <2, 4, 6> and s2 = <1, 3, 5>.
     */
    @Test
    public void test5() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> seq2 = this.createFromArgs(1, 3, 5);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5);
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothIterate with s1 = <2, 4, 6, 8, 10> and s2 = <1, 3, 5>.
     */
    @Test
    public void test6() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6, 8, 10);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6, 8, 10);
        Sequence<Integer> seq2 = this.createFromArgs(1, 3, 5);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5, 7, 9);
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothIterate with s1 = <2, 4> and s2 = <1, 3, 5>.
     */
    @Test
    public void test7() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4);
        Sequence<Integer> seq2 = this.createFromArgs(1, 3, 5);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3);
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothIterate with s1 = <2, 4> and s2 = <1, 3, 5, 7>.
     */
    @Test
    public void test8() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4);
        Sequence<Integer> seq2 = this.createFromArgs(1, 3, 5, 7);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3);
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothIterate with s1 = < 1073741825, 1073741825 >, s2 = < >
     */
    @Test
    public void test_Extra1() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(1073741825, 1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(1073741825,
                1073741825);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs(1073741825);
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothIterate with s1 = < 1073741825, -1073741825 >, s2 = < >
     */
    @Test
    public void test_Extra2() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(1073741825, -1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(1073741825,
                -1073741825);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs(0);
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothIterate with s1 = < -1073741825, 1073741825 >, s2 = < >
     */
    @Test
    public void test_Extra3() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(-1073741825, 1073741825);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(-1073741825,
                1073741825);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs(0);
        SequenceSmooth.smoothIterate(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothRecursive with s1 = <2, 4, 6> and s2 = <-5, 12>.
     */
    @Test
    public void test11() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> seq2 = this.createFromArgs(-5, 12);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5);
        SequenceSmooth.smoothRecursive(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothRecursive with s1 = <7> and s2 = <13, 17, 11>.
     */
    @Test
    public void test12() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(7);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(7);
        Sequence<Integer> seq2 = this.createFromArgs(13, 17, 11);
        Sequence<Integer> expectedSeq2 = this.createFromArgs();
        SequenceSmooth.smoothRecursive(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothRecursive with s1 = <2, 4, 6, 8> and s2 = <-5, 12, 7>.
     */
    @Test
    public void test13() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6, 8);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6, 8);
        Sequence<Integer> seq2 = this.createFromArgs(-5, 12, 7);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5, 7);
        SequenceSmooth.smoothRecursive(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothRecursive with s1 = <2> and s2 = < >.
     */
    @Test
    public void test14() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2);
        Sequence<Integer> seq2 = this.createFromArgs();
        Sequence<Integer> expectedSeq2 = this.createFromArgs();
        SequenceSmooth.smoothRecursive(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothRecursive with s1 = <2, 4, 6> and s2 = <1, 3, 5>.
     */
    @Test
    public void test15() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4, 6);
        Sequence<Integer> seq2 = this.createFromArgs(1, 3, 5);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3, 5);
        SequenceSmooth.smoothRecursive(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothRecursive with s1 = <2, 4> and s2 = <1, 3, 5>.
     */
    @Test
    public void test16() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4);
        Sequence<Integer> seq2 = this.createFromArgs(1, 3, 5);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3);
        SequenceSmooth.smoothRecursive(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

    /**
     * Test smoothRecursive with s1 = <2, 4> and s2 = <1, 3, 5, 7>.
     */
    @Test
    public void test17() {
        /*
         * Set up variables and call method under test
         */
        Sequence<Integer> seq1 = this.createFromArgs(2, 4);
        Sequence<Integer> expectedSeq1 = this.createFromArgs(2, 4);
        Sequence<Integer> seq2 = this.createFromArgs(1, 3, 5, 7);
        Sequence<Integer> expectedSeq2 = this.createFromArgs(3);
        SequenceSmooth.smoothRecursive(seq1, seq2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSeq1, seq1);
        assertEquals(expectedSeq2, seq2);
    }

}
