package day285_inorder_successor_in_bst;

import model.TreeNode;
import model.TreeParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val.
 */
public class Day285InorderSuccessorInBst {

    @Test
    void testTrivial() {
        var root = TreeParser.parseInput(2,1,3);
        var res = inOrderSuccessor(root, 1);
        assertThat(res.val).isEqualTo(2);
    }

    /*
                           20
            ┌───────────┴───────────┐
            9                      25
      ┌─────┴─────┐
      5          12
               ┌──┴──┐
              11    14
     */
    @Test
    void test3Level() {
        var root = TreeParser.parseInput(20, 9, 25, 5, 12, null, null, null, null, 11, 14);
        assertThat(inOrderSuccessor(root, 9).val).isEqualTo(11);
        assertThat(inOrderSuccessor(root, 14).val).isEqualTo(20);

    }

//    @Test
//    void testFiveLevelTree() {
//        var root = TreeParser.parseInput(2,null,3,null,4,null,5,null,6);
//        var res = inOrderSuccessor(root);
//        assertThat(inOrderSuccessor(root)).isEqualTo(5);
//    }

    public TreeNode inOrderSuccessor(TreeNode root, int val) {
        TreeNode elem = findInBSTDepthFirst(root, val);
        // if elem has right subtree, pick leftmost elem in a subtree
        if (elem.right != null) {
            var current = elem.right;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        } else {
            while (elem.parent != null) {
                var parent = elem.parent;
                if (elem == parent.left) {
                    return parent;
                }
                elem = parent;
            }


        }
        return null;
    }

    public TreeNode findInBSTDepthFirst(TreeNode root, int val) {
        if (root == null) {
            return null;
        } else if (val == root.val) {
            return root;
        } else if (val < root.val) {
            return findInBSTDepthFirst(root.left, val);
        } else {
            return findInBSTDepthFirst(root.right, val);
        }
    }
}
