package day111_min_depth_of_binary_tree;

import model.TreeNode;
import model.TreeParser;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 */
public class Day111MinDepthOfBinaryTree {

    @Test
    void testTwoLevelTree() {
        var root = TreeParser.parseInput(3, 9, 20, null, null, 15, 7);
        var res = minNumNodes(root);
        assertThat(res).isEqualTo(2);
    }

    @Test
    void testFiveLevelTree() {
        var root = TreeParser.parseInput(2,null,3,null,4,null,5,null,6);
        var res = minNumNodes(root);
        assertThat(minNumNodes(root)).isEqualTo(5);
    }

    @Test
    void testTwoLevelTreeBF() {
        var root = TreeParser.parseInput(3, 9, 20, null, null, 15, 7);
        var res = minNumNodesBF(root);
        assertThat(res).isEqualTo(2);
    }

    @Test
    void testFiveLevelTreeBF() {
        var root = TreeParser.parseInput(2,null,3,null,4,null,5,null,6);
        var res = minNumNodes(root);
        assertThat(minNumNodesBF(root)).isEqualTo(5);
    }

    public int minNumNodes(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = minNumNodes(root.left);
            int rightDepth = minNumNodes(root.right);
            int depthOfNearestLeaf = 0;
            if (leftDepth > 0 && rightDepth > 0) {
                depthOfNearestLeaf = Math.min(leftDepth, rightDepth);
            } else {
                depthOfNearestLeaf = Math.max(leftDepth, rightDepth);
            }
            return depthOfNearestLeaf + 1;
        }
    }

    public int minNumNodesBF(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 1;
        queue.add(root);

        while (!queue.isEmpty()){
            // The size is the number of nodes at each level
            int size = queue.size();

            // Traverse all the nodes at each level
            for (int i = 0; i < size; i++){
                TreeNode curr = queue.poll();
                if(curr.left == null && curr.right == null) {
                    // this is the nearest leaf
                    return depth;
                }
                if(curr.left != null ) queue.add(curr.left);
                if(curr.right != null ) queue.add(curr.right);
            }
            depth++; //increment depth only after finishing traversing a level
        }
        return depth;
    }


}
