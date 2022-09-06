package day94_binary_tree_inorder_traversal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
         this.left = left;
          this.right = right;
     }
 }


public class Day94Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        } else {
            var left = inorderTraversal(root.left);
            var right = inorderTraversal(root.right);
            left.add(root.val);
            left.addAll(right);
            return left;
        }
    }

    @Test
    void testEmptyTree() {
        Assertions.assertThat(inorderTraversal(null)).isEmpty();
    }

    @Test
    void testSingletonTree() {
        Assertions.assertThat(inorderTraversal(new TreeNode(5))).containsOnly(5);
    }


    @Test
    void testOneLevelTree() {
        var root = new TreeNode(1, null, new TreeNode(2, new TreeNode(3), null));
        Assertions.assertThat(inorderTraversal(root)).containsExactly(1, 3, 2);
    }
}
