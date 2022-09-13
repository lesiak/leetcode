package day112_path_sum;

import model.TreeNode;
import model.TreeParser;
import model.TreePrinter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * A leaf is a node with no children.
 */
public class Day112PathSum {

    /**
     *                         5
     *             ┌───────────┴───────────┐
     *             4                       8
     *       ┌─────┘                 ┌─────┴─────┐
     *      11                      13           4
     *    ┌──┴──┐                                └──┐
     *    7     2                                   1
     */
    @Test
    void testTree() {
        var root = TreeParser.parseInput(5,4,8,11,null,13,4,7,2,null,null,null,1);
        TreePrinter.print(root);
        assertThat(hasPathSum(root, 22)).isTrue();
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null) {
            return root.val == targetSum;
        } else {
            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
        }
    }

}
