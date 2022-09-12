package day102_binary_tree_level_order_traversal;

import model.TreeNode;
import model.TreeParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Day102BinaryTreeLevelOrderTraversal {

    @Test
    void testTwoLevelTree() {
        var root = TreeParser.parseInput(3, 9, 20, null, null, 15, 7);
        var res = levelOrder(root);
        assertThat(res).isEqualTo(
                List.of(
                        List.of(3),
                        List.of(9, 20),
                        List.of(15, 7)
                )
        );
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        // return levelOrderIterative(root);
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;
        levelOrderRecursive(levels, root, 0);
        return levels;

    }

    public void levelOrderRecursive(List<List<Integer>> levels, TreeNode root, int depth) {
        if(root == null) return; // Return if no value
        if(levels.size() == depth) {levels.add(new ArrayList<Integer>());} // put new empty list to the answer

        levels.get(depth).add(root.val);
        levelOrderRecursive(levels, root.left, depth + 1);
        levelOrderRecursive(levels, root.right, depth + 1);
    }

    private List<List<Integer>> levelOrderIterative(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {

            // remove nodes at the same level in one go and add to the list
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>(levelSize);
            while(levelSize-- > 0) {
                TreeNode next = queue.poll();
                level.add(next.val);
                if (next.left != null) queue.add(next.left);
                if (next.right != null) queue.add(next.right);
            }

            // add the current level to the list of levels
            levels.add(level);
        }

        return levels;
    }


}
