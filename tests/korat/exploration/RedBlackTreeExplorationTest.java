package korat.exploration;

public class RedBlackTreeExplorationTest extends BaseExplorationTest {
    public void testEmptyTree() {
        doTestForAllConfigs("-c korat.examples.redblacktree.RedBlackTree -a 0", 1, -1);
    }

    public void testFiveNodes() {
        doTestForAllConfigs("-c korat.examples.redblacktree.RedBlackTree -a 5", 8, 2968);
    }

    public void testSevenNodes() {
        doTestForAllConfigs("-c korat.examples.redblacktree.RedBlackTree -a 7", 33, -1);
    }
}
