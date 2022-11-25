/**
 * Finds {@code x} in {@code q} and, if such exists, moves it to the front
 * of {@code q}.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @param q
 *            the {@code Queue} to be searched
 * @param x
 *            the entry to be searched for
 * @updates q
 * @ensures <pre>
 * perms(q, #q)  and
 * if <x> is substring of q
 *  then <x> is prefix of q
 * </pre>
 */
private static <T> void moveToFront1(Queue<T> q, T x) {
    int lengthQ = q.length();
    Queue<T> left = q.newInstance();
    Queue<T> right = q.newInstance();

    for (int i = 0; i < lengthQ; i++) {
        T a = q.dequeue();

        if (a != x) {
            left.enqueue(a);
        }
        else {
            right.enqueue(a);
        }

    }
    right.append(left);
    q.transferFrom(right);
}

private static <T> void moveToFront2(Queue<T> q, T x) {
    int lengthQ = q.length();
    Queue<T> temptQ1 = q.newInstance();
    Queue<T> temptQ2 = q.newInstance();
    temptQ1.transferFrom(q);

    for (int i = 0; i < lengthQ; i++) {
        T a = temptQ1.dequeue();

        if (a != x) {
            temptQ2.enqueue(a);
        }
        else {
            temptQ1.enqueue(a);
        }
    }
    temptQ1.append(temptQ2);
    q.transferFrom(temptQ1);
}

}