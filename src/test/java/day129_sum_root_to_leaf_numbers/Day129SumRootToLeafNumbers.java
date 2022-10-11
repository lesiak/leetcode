package day129_sum_root_to_leaf_numbers;

import model.TreeNode;
import model.TreeParser;
import model.TreePrinter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Day129SumRootToLeafNumbers {

    /**
     *    1
     * ┌──┴──┐
     * 2     3
     */
    @Test
    void testTwoLevelTree() {
        var root = TreeParser.parseInput(1, 2, 3);
        TreePrinter.print(root);
        var res = sumNumbers(root);
        assertThat(res).isEqualTo(12 + 13);
    }

    /**
     *              4
     *       ┌─────┴─────┐
     *       9           0
     *    ┌──┴──┐
     *    5     1
     */
    @Test
    void testThreeLevelTree() {
        var root = TreeParser.parseInput(4,9,0,5,1);
        TreePrinter.print(root);
        var res = sumNumbers(root);
        assertThat(res).isEqualTo(495 + 491 + 40);
    }

    public int sumNumbers(TreeNode root) {
        return sumNodes(root, 0);
    }

    private int sumNodes(TreeNode root, int prefixSum) {
        if (root == null) return 0;
        prefixSum = prefixSum * 10 + root.val;
        if (root.left == null && root.right == null) return prefixSum;
        int leftSum = sumNodes(root.left, prefixSum);
        int rightSum = sumNodes(root.right, prefixSum);
        return leftSum + rightSum;
    }
}
