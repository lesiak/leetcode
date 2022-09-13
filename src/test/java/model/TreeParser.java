package model;

import com.sun.source.tree.Tree;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeParser {

    public static TreeNode parseInput(Integer... a) {
        return parseInput(Arrays.asList(a));
    }
    public static TreeNode parseInput(List<Integer> input) {
        //return parseInput(input, 0);
        var root = new TreeNode(input.get(0));
        var index = 1;
        Queue<TreeNode> nodesToProcess = new LinkedList<>();
        nodesToProcess.add(root);
        while(index < input.size()) {
            var currentNode = nodesToProcess.poll();
            Integer valLeft = input.get(index++);
            Integer valRight = input.get(index++);
            if (valLeft != null) {
                var left = new TreeNode(valLeft);
                left.parent = currentNode;
                currentNode.left = left;
                nodesToProcess.add(left);
            }
            if (valRight != null) {
                var right = new TreeNode(valRight);
                right.parent = currentNode;
                currentNode.right = right;
                nodesToProcess.add(right);
            }
        }
        return root;

    }
}
