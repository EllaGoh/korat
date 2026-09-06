package korat.examples.redblacktree;

import java.lang.reflect.Field;
import junit.framework.TestCase;

public class RedBlackTreeTest extends TestCase {
    private RedBlackTree tree(RedBlackTree.Node root, int size) throws Exception {
        RedBlackTree tree = new RedBlackTree();
        Field rootField = RedBlackTree.class.getDeclaredField("root");
        rootField.setAccessible(true);
        rootField.set(tree, root);
        Field sizeField = RedBlackTree.class.getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.setInt(tree, size);
        return tree;
    }

    public void testEmptyTree() throws Exception {
        assertTrue(tree(null, 0).repOK());
        assertFalse(tree(null, 1).repOK());
    }

    public void testRootMustBeBlack() throws Exception {
        RedBlackTree.Node root = new RedBlackTree.Node();
        root.key = 1;
        assertTrue(tree(root, 1).repOK());
        root.color = 0; // RED
        assertFalse(tree(root, 1).repOK());
        root.color = 2; // Not a valid root color either.
        assertFalse(tree(root, 1).repOK());
    }

    public void testRedRootWithBlackChildrenIsRejected() throws Exception {
        RedBlackTree.Node root = new RedBlackTree.Node();
        root.key = 1;
        root.left = new RedBlackTree.Node();
        root.left.key = 0;
        root.left.parent = root;
        root.right = new RedBlackTree.Node();
        root.right.key = 2;
        root.right.parent = root;
        assertTrue(tree(root, 3).repOK());
        root.color = 0;
        assertFalse(tree(root, 3).repOK());
        root.color = 1;
        root.left.color = root.right.color = 0;
        assertTrue(tree(root, 3).repOK());
    }

    public void testRootMustHaveNoParent() throws Exception {
        RedBlackTree.Node root = new RedBlackTree.Node();
        root.parent = new RedBlackTree.Node();
        assertFalse(tree(root, 1).repOK());
    }
}
