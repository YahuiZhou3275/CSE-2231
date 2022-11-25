/**
 * Returns the size of the given {@code BinaryTree<T>}.
 *
 * @param <T>
 *            the type of the {@code BinaryTree} node labels
 * @param t
 *            the {@code BinaryTree} whose size to return
 * @return the size of the given {@code BinaryTree}
 * @ensures size = |t|
 */
public static <T> int size(BinaryTree<T> t) {
    int size = 0;
    BinaryTree<T> left = t.newInstance();
    BinaryTree<T> right = t.newInstance();

    if (t.height() > 0) {
        T root = t.disassemble(left, right);
        size = 1+ size(left) + size(right);
        t.assemable(root, left, right);
    }
    return size;
}
