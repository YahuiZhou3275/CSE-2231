/**
 * Finds pair with first component {@code key} and, if such exists, moves it
 * to the front of {@code q}.
 *
 * @param <K>
 *            type of {@code Pair} key
 * @param <V>
 *            type of {@code Pair} value
 * @param q
 *            the {@code Queue} to be searched
 * @param key
 *            the key to be searched for
 * @updates q
 * @ensures <pre>
 * perms(q, #q)  and
 * if there exists value: V (<(key, value)> is substring of q)
 *  then there exists value: V (<(key, value)> is prefix of q)
 * </pre>
 */
private static <K, V> void moveToFront(Queue<Pair<K, V>> q, K key) {

    int length = q.length();
    Queue<Pair<K, V>> temptQueue = q.newInstance();

    for (int i = 0; i< length; i++) {
        Pair<K, V> tempt = q.dequeue();
        K check = temp.key();

        if (check == key) {
            temptQueue.enqueue(tempt);
        }
        q.enqueue(tempt);
    }
    temptQueue.append(q);
    q.transferFrom(temptQueue);
}
}
