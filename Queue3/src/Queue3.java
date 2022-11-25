package components.queue;

import components.queue.QueueSecondary;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code Queue} represented as a {@code Sequence} of entries, with
 * implementations of primary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @correspondence this = $this.entries
 */
public class Queue3<T> extends QueueSecondary<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Entries included in {@code this}.
     */
    private Sequence<T> entries;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.entries = new Sequence1L<T>();
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Queue3() {
        this.createNewRep();
    }

    /*
     * Standard methods removed to reduce clutter...
     */

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void enqueue(T x) {
        assert x != null : "Violation of: x is not null";

        this.entries.add(this.entries.length(), x);

    }

    @Override
    public final T dequeue() {
        assert this.length() > 0 : "Violation of: this /= <>";

        T x = this.entries.remove(0);

        return x;
    }

    @Override
    public final int length() {

        int length = this.entries.length();

        return length;
    }

    /*
     * Iterator removed to reduce clutter...
     */
    @Override
    public final T front() {

        T x = this.entries.

        return x;
    }

}